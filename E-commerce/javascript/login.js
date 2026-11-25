const loginForm = document.querySelector(".login-container");
const errorEl = document.getElementById("login-error");

loginForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    if (errorEl) {
        errorEl.textContent = "";
        errorEl.style.display = "none";
    }

    try {
        const response = await fetch(
            "http://localhost:8080/users/login",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: senha
                })
            }
        );

        if (!response.ok) {
            const rawText = await response.text();
            let message = "Erro ao realizar login";

            try {
                const parsed = JSON.parse(rawText);
                message = parsed.message || message;
            } catch (_) {
                if (rawText) {
                    message = rawText;
                }
            }

            console.log("Status:", response.status);
            console.log("Resposta do Spring:", rawText);

            throw new Error(message);
        }

        const user = await response.json();

        console.log("Login realizado com sucesso!");
        console.log(user);

        /*
         * O usuário agora também terá:
         * - role: "USER"
         * ou
         * - role: "ADMIN"
         */

        localStorage.setItem(
            "user",
            JSON.stringify(user)
        );

        window.location.href = "index.html";

    } catch (error) {
        console.error("Erro:", error);

        if (errorEl) {
            errorEl.textContent = error.message;
            errorEl.style.display = "block";
        } else {
            alert(error.message);
        }
    }
});