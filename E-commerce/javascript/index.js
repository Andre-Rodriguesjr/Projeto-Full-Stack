const menuBtn =
document.getElementById("menuBtn");

const sidebar =
document.getElementById("sidebar");

menuBtn.addEventListener("click", () => {

    sidebar.classList.toggle("active");

});


//Verifica se o usuario está logado ou não.
const userLink = document.getElementById("userLink");
const userName = document.getElementById("userName");

const user = localStorage.getItem("user");

if (user) {

    const userData = JSON.parse(user);

    /*
     * Usuário logado
     */

    userLink.href = "perfilUser.html";

    userName.textContent = userData.username;


    /*
     * Verifica se é ADMIN
     */

    if (userData.role === "ADMIN") {

        adminLink.style.display = "block";

    }

} else {

    /*
     * Ninguém logado
     */

    userLink.href = "login.html";

    userName.textContent = "Entrar";

}