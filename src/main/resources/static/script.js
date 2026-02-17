<script>
document.addEventListener('DOMContentLoaded', function() {
    const contactForm = document.querySelector('.contact-form form');

    if (contactForm) {
        contactForm.addEventListener('submit', async function(e) {
            e.preventDefault();

            const submitButton = contactForm.querySelector('button[type="submit"]');
            const originalButtonText = submitButton.innerHTML;

            // Get form data
            const formData = {
                name: document.getElementById('name').value,
                email: document.getElementById('email').value,
                message: document.getElementById('message').value
            };

            // Show loading state
            submitButton.innerHTML = 'Sending... <i class="fas fa-spinner fa-spin"></i>';
            submitButton.disabled = true;

            try {
                const response = await fetch('http://localhost:8080/api/contact', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formData)
                });

                const result = await response.text();

                if (response.ok) {
                    // Show success message
                    alert('Message sent successfully!');
                    contactForm.reset();
                } else {
                    // Show error message
                    alert('Error: ' + result);
                }
            } catch (error) {
                console.error('Error:', error);
                alert('An error occurred. Please try again later.');
            } finally {
                // Reset button state
                submitButton.innerHTML = originalButtonText;
                submitButton.disabled = false;
            }
        });
    }
});
</script>