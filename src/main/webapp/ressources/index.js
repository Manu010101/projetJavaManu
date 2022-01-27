/**
 * Contient le main code exécuté après chargement du DOM
 * @param e
 */
function afterLoad(e){

    let btn = document.getElementById("btn_ajax")
    url = "http://localhost:8080/projetJavaManu-1.0-SNAPSHOT/do/show"
    btn.addEventListener("click", getJSON(url, cBack, null))

}

function cBack(error, result) {
    if (error){console.log(error)}
    return processed(result)
}

function processed(result) {
    console.log(result)
}

function getJSON(url, cBack, data) {
    console.log("click sur btn ajax - rentré dans getJSON")
    const xhr = new XMLHttpRequest();

    xhr.addEventListener("error", function (){
        cBack("pb" + xhr.statusText)
    })
    xhr.addEventListener("load", function (){
        cBack(null, JSON.parse(xhr.response))
    })
    xhr.overrideMimeType("application/json")
    xhr.open("GET", url, true) // ouverture requete
    xhr.send(data||null)  // envoi requete



}

const start = window.addEventListener("load", afterLoad)