app = (() => {

    function loadGetMsg() {
        let nameVar = document.getElementById("name").value, pwd = document.getElementById("pwd");
        console.log("nombre usuario: "+ nameVar + "and " + pwd);
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function() {
            document.getElementById("getrespmsg").innerHTML =
            this.responseText;
        }
        xhttp.open("GET", "/loginservice?send="+nameVar+"&pd="+pwd);
        xhttp.send();
    }

    return {
        loadGetMsg
    };
})();