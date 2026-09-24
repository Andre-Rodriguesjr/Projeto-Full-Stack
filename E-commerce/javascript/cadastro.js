document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.querySelector('.cadastro-container');

    registerForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        // Object payload matching UserRegisterDTO.java properties directly
        const registerPayload = {
            name: document.getElementById('name').value,
            username: document.getElementById('username').value,
            email: document.getElementById('email').value,
            telephone: document.getElementById('telephone').value,
            password: document.getElementById('password').value
        };

        try {
            const response = await fetch('http://localhost:8080/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(registerPayload)
            });

            if (response.ok) {
                const createdUser = await response.json(); 

                alert(`Conta criada com sucesso!`);
                
                // Redirect to login page
                window.location.href = 'login.html';
            } else {
                const errorData = await response.json().catch(() => null);
                console.error('Spring Boot response error:', errorData);

                alert('Falha ao criar conta. Por favor, verifique as restrições de entrada');
            }
        } catch (error) {
            console.error('Request error:', error);
            alert('Failed to connect to the backend server.');
        }
    });
});