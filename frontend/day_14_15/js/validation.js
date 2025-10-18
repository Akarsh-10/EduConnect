function login() {
   
    // You can perform login validation and authentication here
    // For simplicity, let's just display an alert
    let testUser = document.getElementById("loginUsername").value;
    let testPassword = document.getElementById("loginPassword").value;
    console.log(`Login clicked. Username: ${testUser}, Password: ${testPassword}`);
    

    
}

function register() {
    const name=document.getElementById("registerName").value;
    const username=document.getElementById("registerUsername").value;
    const password=document.getElementById("registerPassword").value;
    const email=document.getElementById("registerEmail").value;
    
    // Frontend validation for registration form
    if(name=== "" || email==="" || username === "" || password === "")
    {
        alert("All fields are mandatory.");
        return;
    }

    // Validate email format
    var emailCheck= /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if(!emailCheck.test(email))
    {
        alert("Please enter a valid email address.");
        return;
    }
    
    // Validate username (no special characters)
    var userCheck= /^[a-zA-Z0-9]+$/;
    if(!userCheck.test(username))
    {
        alert("Username should not contain any special characters.");
        return;
    }

    // Validate password (at least 8 characters, one capital letter, and one numeric)
    var passwordCheck= /^(?=.*[A-Z])(?=.*\d).{8,}$/;
    if(!passwordCheck.test(password))
    {
        alert("Password does not follow the guidelines");
        return;
    }


    console.log(`Register clicked. Name: ${name}, Email: ${email}, Username: ${username}, Password: ${password}`)
    
}
module.exports = { login, register };
