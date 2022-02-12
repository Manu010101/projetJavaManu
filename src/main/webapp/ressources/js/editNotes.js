//Sélection de la liste déroulante

elt = document.querySelector("select")

elt.addEventListener("change", filtrer)

function filtrer() {

    let groupe_nom = elt.value

    let url=urlEditNotes + "?" + "nomGroupe=" + groupe_nom // il y aura un pb ici en prod

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

    function consulter(results){
        console.log("Nickel: " + results)
        conteneur = document.querySelector(".editNotes")
        conteneur.innerHTML = ""

        //noeud table
        tableau = document.createElement("table")
        tableau.setAttribute("class", "table")

        // noeud thead
        thead = document.createElement("thead")

        //noeud tr de thead

        tr_thead = document.createElement("tr")

        // noeuds td de l'en tête

        champs = ["Nom", "Prénom", "Moyenne"]

        for (let i = 0; i < champs.length; i++) {
            td = document.createElement("td")
            td.innerHTML = champs[i]
            tr_thead.appendChild(td)
        }

        thead.appendChild(tr_thead)
        tableau.appendChild(thead)

        for (let i = 0; i < results.length; i++) {

            tr = document.createElement("tr")
            // remplissage avec nom prénom notes
            for (let j = 3; j < 6; j++) {
                td = document.createElement("td")
                td.innerHTML = results[i][j]
                tr.appendChild(td)
            }
            tableau.appendChild(tr)
        }

        conteneur.appendChild(tableau)

    }

    function editer(results) {
        console.log("Nickel: " + results)
        conteneur = document.querySelector(".editNotes")
        conteneur.innerHTML = ""

        //noeud form

        formulaire = document.createElement("form")
        formulaire.setAttribute("method", "post")
        formulaire.setAttribute("action", urlSaveNotes)

        //noeud table
        tableau = document.createElement("table")
        tableau.setAttribute("class", "table")

        // noeud thead
        thead = document.createElement("thead")

        //noeud tr de thead

        tr_thead = document.createElement("tr")

        // noeuds td de l'en tête

        champs = ["Nom", "Prénom", "Moyenne"]

        for (let i = 0; i < champs.length; i++) {
            td = document.createElement("td")
            td.innerHTML = champs[i]
            tr_thead.appendChild(td)
        }


        thead.appendChild(tr_thead)
        tableau.appendChild(thead)


        for (let i = 0; i < results.length; i++) {

            tr = document.createElement("tr")
            // remplissage avec nom prénom notes
            for (let j = 3; j < 5; j++) {
                td = document.createElement("td")
                td.innerHTML = results[i][j]
                tr.appendChild(td)
            }
            //création de l'elt input puis rattacher à tr
            td = document.createElement("td")
            elt_input = document.createElement("input")
            elt_input.setAttribute("type", "number")
            let etu_id = results[i][0]
            let etu_note = results[i][1]
            console.log("note" + etu_note)
            elt_input.setAttribute("name", etu_id)
            elt_input.setAttribute("value", etu_note)
            td.appendChild(elt_input)
            tr.appendChild(td)
            tableau.appendChild(tr)
        }

        formulaire.appendChild(tableau)
        conteneur.appendChild(formulaire)
    }

    //requete Ajax

    promesseEtu(url)
        .then(data => editer(data))
        .catch(e => console.log("erreur catch by manu" + e))

}