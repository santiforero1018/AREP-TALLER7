app = (() => {

    function loadGetMsg() {
        let nameVar = document.getElementById("name").value, pwd = document.getElementById("pwd").value;
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function() {
            document.getElementById("getrespmsg").innerHTML =
            this.responseText;
        }
        xhttp.open("GET", "/loginservice?send="+nameVar+"&pd="+pwd);
        xhttp.send();
    }

    function setAuthName(){
        let name = new URLSearchParams(window.location.search).get('user');
        document.getElementById("nombre").innerHTML = name;
    }

    return {
        loadGetMsg,
        setAuthName
    };
})();