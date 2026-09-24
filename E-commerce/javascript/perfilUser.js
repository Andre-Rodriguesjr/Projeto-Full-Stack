const perfilForm = document.getElementById("perfilForm");

const nome = document.getElementById("nome");
const username = document.getElementById("username");
const email = document.getElementById("email");
const telephone = document.getElementById("telephone");
const password = document.getElementById("password");

const editarBtn = document.getElementById("editarBtn");
const salvarBtn = document.getElementById("salvarBtn");
const cancelarBtn = document.getElementById("cancelarBtn");

const logoutButton = document.getElementById("logout");


// Pega o usuário salvo no localStorage

const user = localStorage.getItem("user");


// Se não estiver logado, manda para o login

if (!user) {

    window.location.href = "login.html";

} else {

    const userData = JSON.parse(user);

    carregarUsuario(userData);
}


// ==========================
// CARREGAR USUÁRIO
// ==========================

function carregarUsuario(userData) {

    nome.value = userData.name;
    username.value = userData.username;
    email.value = userData.email;
    telephone.value = userData.telephone;

}


// ==========================
// ATIVAR EDIÇÃO
// ==========================

editarBtn.addEventListener("click", () => {

    nome.disabled = false;
    username.disabled = false;
    email.disabled = false;
    telephone.disabled = false;
    password.disabled = false;

    document.getElementById("senhaContainer").style.display = "block";

    editarBtn.style.display = "none";

    salvarBtn.style.display = "block";
    cancelarBtn.style.display = "block";

});


// ==========================
// CANCELAR
// ==========================

cancelarBtn.addEventListener("click", () => {

    const userData = JSON.parse(
        localStorage.getItem("user")
    );

    carregarUsuario(userData);

    password.value = "";

    nome.disabled = true;
    username.disabled = true;
    email.disabled = true;
    telephone.disabled = true;
    password.disabled = true;

    document.getElementById("senhaContainer").style.display = "none";

    editarBtn.style.display = "block";

    salvarBtn.style.display = "none";
    cancelarBtn.style.display = "none";

});


// ==========================
// SALVAR ALTERAÇÕES
// ==========================

perfilForm.addEventListener("submit", async (event) => {

    event.preventDefault();


    const userData = JSON.parse(
        localStorage.getItem("user")
    );


    const dadosAtualizados = {

        name: nome.value,
        username: username.value,
        email: email.value,
        telephone: telephone.value,
        password: password.value

    };


    try {

        const response = await fetch(
            `http://localhost:8080/users/${userData.id}`,
            {
                method: "PUT",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(dadosAtualizados)
            }
        );


        if (!response.ok) {

            const erro = await response.text();

            console.log("Status:", response.status);
            console.log("Resposta do Spring:", erro);

            throw new Error(
                erro || "Não foi possível atualizar o perfil."
            );
        }


        // Usuário atualizado vindo do Spring

        const usuarioAtualizado = await response.json();


        // Atualiza o localStorage

        localStorage.setItem(
            "user",
            JSON.stringify(usuarioAtualizado)
        );


        // Atualiza os campos

        carregarUsuario(usuarioAtualizado);

        password.value = "";


        // Desativa edição

        nome.disabled = true;
        username.disabled = true;
        email.disabled = true;
        telephone.disabled = true;
        password.disabled = true;


        editarBtn.style.display = "block";

        salvarBtn.style.display = "none";
        cancelarBtn.style.display = "none";


        alert("Perfil atualizado com sucesso!");


    } catch (error) {

        console.error("Erro:", error);

        alert(error.message);

    }

});


// ==========================
// LOGOUT
// ==========================

logoutButton.addEventListener("click", () => {

    localStorage.removeItem("user");

    window.location.href = "index.html";

});