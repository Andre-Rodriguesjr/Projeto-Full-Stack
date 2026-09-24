const loginForm = document.querySelector(".login-container");

loginForm.addEventListener("submit", async (event) => {

    event.preventDefault();

    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    try {

        const response = await fetch("http://localhost:8080/users/login", {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                email: email,
                password: senha
            })
        });

        if (!response.ok) {

            const erro = await response.text();

            console.log("Status:", response.status);
            console.log("Erro retornado pelo Spring:", erro);

            throw new Error(`Erro ${response.status}`);
        }

        const user = await response.json();

        console.log("Login realizado com sucesso!");
        console.log(user);

        localStorage.setItem("user", JSON.stringify(user));

        window.location.href = "index.html";

    } catch (error) {

        console.error("Erro:", error);

        alert(error.message);
    }
});