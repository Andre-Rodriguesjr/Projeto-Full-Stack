const perfilForm = document.getElementById("perfilForm");

const nome = document.getElementById("nome");
const username = document.getElementById("username");
const email = document.getElementById("email");
const telephone = document.getElementById("telephone");
const password = document.getElementById("password");

const senhaContainer =
    document.getElementById("senhaContainer");

const editarBtn =
    document.getElementById("editarBtn");

const salvarBtn =
    document.getElementById("salvarBtn");

const cancelarBtn =
    document.getElementById("cancelarBtn");

const logoutButton =
    document.getElementById("logout");


const user = localStorage.getItem("user");


if (!user) {

    window.location.href = "login.html";

} else {

    const userData = JSON.parse(user);

    carregarUsuario(userData);
}


function carregarUsuario(userData) {

    nome.value =
        userData.name || "";

    username.value =
        userData.username || "";

    email.value =
        userData.email || "";

    telephone.value =
        userData.telephone || "";

}


editarBtn.addEventListener("click", () => {

    nome.disabled = false;

    username.disabled = false;

    email.disabled = false;

    telephone.disabled = false;

    password.disabled = false;


    senhaContainer.style.display =
        "block";


    editarBtn.style.display =
        "none";

    salvarBtn.style.display =
        "block";

    cancelarBtn.style.display =
        "block";

});


cancelarBtn.addEventListener("click", () => {

    const userData =
        JSON.parse(
            localStorage.getItem("user")
        );


    carregarUsuario(userData);

    password.value = "";


    nome.disabled = true;

    username.disabled = true;

    email.disabled = true;

    telephone.disabled = true;

    password.disabled = true;


    senhaContainer.style.display =
        "none";


    editarBtn.style.display =
        "block";

    salvarBtn.style.display =
        "none";

    cancelarBtn.style.display =
        "none";

});


perfilForm.addEventListener(
    "submit",
    async (event) => {

        event.preventDefault();


        const userData =
            JSON.parse(
                localStorage.getItem("user")
            );


        const dadosAtualizados = {

            name:
                nome.value.trim(),

            username:
                username.value.trim(),

            email:
                email.value.trim(),

            telephone:
                telephone.value.trim()

        };


        /*
         * Só envia a senha se
         * realmente foi preenchida.
         */

        if (
            password.value.trim() !== ""
        ) {

            dadosAtualizados.password =
                password.value.trim();

        }


        console.log(
            "Dados enviados para o Spring:",
            dadosAtualizados
        );


        try {

            const response =
                await fetch(
                    `http://localhost:8080/users/${userData.id}`,
                    {
                        method: "PUT",

                        headers: {
                            "Content-Type":
                                "application/json"
                        },

                        body:
                            JSON.stringify(
                                dadosAtualizados
                            )
                    }
                );


            if (!response.ok) {

                const erro =
                    await response.text();


                console.error(
                    "Status:",
                    response.status
                );


                console.error(
                    "Resposta do Spring:",
                    erro
                );


                throw new Error(
                    erro ||
                    `Erro ao atualizar perfil. Status: ${response.status}`
                );
            }


            const usuarioAtualizado =
                await response.json();


            console.log(
                "Usuário atualizado:",
                usuarioAtualizado
            );


            /*
             * Atualiza o localStorage.
             *
             * O role também continua aqui.
             */

            localStorage.setItem(
                "user",
                JSON.stringify(
                    usuarioAtualizado
                )
            );


            carregarUsuario(
                usuarioAtualizado
            );


            password.value = "";


            nome.disabled = true;

            username.disabled = true;

            email.disabled = true;

            telephone.disabled = true;

            password.disabled = true;


            senhaContainer.style.display =
                "none";


            editarBtn.style.display =
                "block";

            salvarBtn.style.display =
                "none";

            cancelarBtn.style.display =
                "none";


            alert(
                "Perfil atualizado com sucesso!"
            );


        } catch (error) {

            console.error(
                "Erro ao atualizar:",
                error
            );


            alert(
                error.message
            );

        }

    }
);


logoutButton.addEventListener(
    "click",
    () => {

        localStorage.removeItem(
            "user"
        );

        window.location.href =
            "index.html";

    }
);