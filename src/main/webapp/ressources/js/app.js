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

    function success(results){

        console.log("resultats :" + results)
        let tableau = document.querySelector("table")
        tableau.innerHTML = ""

        thead = document.createElement("thead")

        //noeud tr de thead

        tr_thead = document.createElement("tr")

        // noeuds td de l'en tête

        champs = ["Nom", "Prenom", "Moyenne", "Nombre absences", "Id groupe"]
        //construction en-tete
        for (let i = 0; i < champs.length; i++) {
            td = document.createElement("td")
            td.innerHTML = champs[i]
            tr_thead.appendChild(td)
        }

        thead.appendChild(tr_thead)
        tableau.appendChild(thead)

        //construction ligne courante
        for (let i = 0; i < results.length; i++) {
            tr = document.createElement("tr")
            etudiant_attributs = [
                nom = results[i][3],
                prenom = results[i][4],
                moyenne = results[i][1],
                nb_absences = results[i][2],
                groupe = results[i][5]
            ]

            for (let j = 0; j < etudiant_attributs.length; j++) {
                td = document.createElement("td")
                td.innerHTML = etudiant_attributs[j]
                tr.appendChild(td)
            }
            tableau.appendChild(tr)

        }



    }

    //requete Ajax

    promesseEtu(url)
        .then(data => success(data))
        .catch(e => console.log("erreur :" + e))

}



