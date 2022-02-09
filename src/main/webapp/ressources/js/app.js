elts = document.getElementsByClassName("filtre")
console.log("elst" + elts)

for (let i = 0; i < elts.length; i++) {
    elts[i].addEventListener("change", filtrer)
}

let donneesFiltres = [];

function filtrer() {

    //Récupération des valeurs des paramètres
    donneesFiltres = []
    let nom_param;
    let valeur_param;
    for (let i = 0; i < elts.length; i++) {
        nom_param = elts[i].querySelector("option").innerHTML
        valeur_param = elts[i].value
        console.log("selected: " + elts[i].querySelector("option").innerHTML)
        donneesFiltres.push(
            {
                nom_param: nom_param,
                valeur_param: valeur_param
            }
        )
    }

    // Construction url avec passage des paramètres des filtres
    let url=urlgetetudiants + "?"   // il y aura un pb ici en prod
    for (let i = 0; i < donneesFiltres.length; i++) {
        if (i == donneesFiltres.length - 1){
            url += donneesFiltres[i].nom_param + "=" + donneesFiltres[i].valeur_param
        }else {
            url += donneesFiltres[i].nom_param + "=" + donneesFiltres[i].valeur_param + "&"
        }

    }
    console.log("url= " + url)

    function promesseEtu(url, data) {
        return new Promise(
            function (resolve, reject) {
                const xhr = new XMLHttpRequest();
                xhr.addEventListener("load", function () {
                    if (this.status===200){
                        resolve(JSON.parse(this.response))
                    }
                    else {reject(new Error('Pb: ' + this.statusText))}
                })

                xhr.addEventListener("error", function () {
                    reject(new Error('Ajax pb: ' + this.statusText))
                })

                xhr.overrideMimeType('application/json')
                xhr.open('GET', url)
                xhr.send(data?data:null)
            }
        )
    }

    function success(result){console.log("Nickel: " + result)}

    //requete Ajax

    promesseEtu(url)
        .then(data => {console.log("succes :" + data)})
        .catch(e => console.log("erreur catch by manu" + e))

}

async function asyncCall() {
    console.log("Requête async (norme ECMAScript 2017)");

    // Création des settings
    const settings = {
        method: 'POST',
        /*headers: {                    // Possibilité de modifier les headers de la demande
            Accept: 'application/json',
            'Content-Type': 'application/json',
        }*/
        body: 'test=testasync'  // Donnée envoyée au serveur
    };

    // Attente d'une réponse de l'URL getEtudiants
    // URL créée dans le fichier etudiants.jsp
    const request = await fetch(urlgetetudiants);

    // Retourne la réponse JSON du serveur
    return request.json();
}


