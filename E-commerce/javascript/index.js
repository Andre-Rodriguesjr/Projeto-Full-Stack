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

    userLink.href = "perfil.html";

    userName.textContent = userData.name;

} else {

    userLink.href = "login.html";

    userName.textContent = "Entrar";
}