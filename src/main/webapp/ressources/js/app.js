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

        if (nom_param != valeur_param) {//enlève les params qui ont la valeur par défaut
            donneesFiltres.push(
                {
                    nom_param: nom_param,
                    valeur_param: valeur_param
                }
            )
        }
    }

    // Construction url avec passage des paramètres des filtres
    let url=urlgetetudiants + "?"   // il y aura un pb ici en prod
    for (let i = 0; i < donneesFiltres.length; i++) {
        if (i == donneesFiltres.length - 1){//cas du dernier paramètre
            url += donneesFiltres[i].nom_param + "=" + donneesFiltres[i].valeur_param
        }else {//cas courant
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



