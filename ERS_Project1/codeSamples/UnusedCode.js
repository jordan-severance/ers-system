const doc = this.document;

function login() {
    const usernameValue = document.getElementById("username").value;
    const passwordValue = document.getElementById("password").value;

    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200){

            //if credentials were valid then
            //homepage();
            //else
            //doc.getElementById("credentials").hidden = false;
        }
    }

    xmlHttpRequest.open("POST", "credentials");
    xmlHttpRequest.setRequestHeader("Content-type", "application/json");
    let credentialsObject = JSON.stringify({
        usernameValue: usernameValue,
        passwordValue: passwordValue,
        checkedCredentials: false
    })
    console.log("Json data to send: " + credentialsObject);
    xmlHttpRequest.send(credentialsObject);
}

function switchPages() {
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200){

            //if credentials were valid then
            //homepage();
            //else
            //doc.getElementById("credentials").hidden = false;
        }
    }
    xmlHttpRequest.open("POST", "credentials");
    xmlHttpRequest.setRequestHeader("Content-type", "application/json");
    let credentialsObject = JSON.stringify({
        usernameValue: usernameValue,
        passwordValue: passwordValue,
        checkedCredentials: false
    })
}

function homepage() {
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200){
            doc.open();
            doc.write(this.response);
            doc.close();
        }
    }
    xmlHttpRequest.open("get", "employee_homepage.html");
    xmlHttpRequest.send();
}