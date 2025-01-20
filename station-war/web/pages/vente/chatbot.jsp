<div class="content-wrapper">
    <section class="content-header">
        <h4>
            Chatbot
        </h4>
    </section>

    <section class="content">

        <div>
            <form id="prompt-form">
                <textarea name="prompt" id="prompt-field" cols="30" rows="3" class="form-control">


                </textarea>

                <button id="send-prompt-btn" class="btn btn-primary">Envoyer</button>
            </form>
        </div>


        <div id="rep-div" class="d-flex justify-content-center align-items-center"></div>
        <div id="rep-div2" class="d-flex justify-content-center align-items-center"></div>


    </section>
</div>

<script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/typed.js@2.0.16/dist/typed.umd.js"></script>
<script>
    function formatResponseText(text) {
        return marked.parse(text)
    }

    function displayMessage(text, id) {
        const messageDiv = document.createElement('div');
        messageDiv.className = 'message bot-message';
        messageDiv.innerHTML = '<span id="typed-text"></span>';
        document.getElementById(id).appendChild(messageDiv);
        const typed = new Typed('#typed-text', {
            strings: [text],
            typeSpeed: 7,
            showCursor: false,
            onComplete: () => {
                // window.scrollTo(0, document.body.scrollHeight);
            }
        });

        // window.scrollTo(0, document.body.scrollHeight);
    }

    function createTableFromObject(data) {
        if (!data || data.length === 0) {
            return document.createTextNode("...");
        }

        const headers = Object.keys(data[0]);
        console.log(data);
        console.log(headers);

        // Create table and set its styles
        const table = document.createElement("table");
        table.style.border = "1px solid black";
        table.style.borderCollapse = "collapse";
        table.style.width = "100%";
        table.style.textAlign = "left";

        // Create thead
        const thead = document.createElement("thead");
        const headerRow = document.createElement("tr");
        headers.forEach(header => {
            const th = document.createElement("th");
            th.textContent = header;
            th.style.border = "1px solid black";
            headerRow.appendChild(th);
        });
        thead.appendChild(headerRow);
        table.appendChild(thead);

        // Create tbody
        const tbody = document.createElement("tbody");
        data.forEach(item => {
            console.log(item);
            const row = document.createElement("tr");
            headers.forEach(header => {
                const td = document.createElement("td");
                let value = item[header];
                td.textContent = value || "";
                td.style.border = "1px solid black";
                row.appendChild(td);
            });
            tbody.appendChild(row);
        });
        table.appendChild(tbody);

        return table;
    }


    <%--function createTableFromObject(data) {--%>
    <%--    if(!data || data.length === 0) {--%>
    <%--        return "...";--%>
    <%--    }--%>

    <%--    const headers = Object.keys(data[0]);--%>
    <%--    console.log(data);--%>
    <%--    console.log(headers)--%>
    <%--    // let table = document.create--%>
    <%--    let table = "<table border='1' style='border-collapse: collapse; width: 100%; text-align: left'>"--%>
    <%--    table += "<thead><tr>";--%>
    <%--    headers.forEach(header => {--%>
    <%--        table += `<th>${header}</th>`--%>
    <%--    })--%>
    <%--    table += "</tr></thead>";--%>

    <%--    table += "<tbody>";--%>
    <%--    data.forEach(item => {--%>
    <%--        console.log(item);--%>
    <%--        table += "<tr>"--%>
    <%--        headers.forEach(header => {--%>
    <%--            let value = item[header];--%>
    <%--            console.log("value: " + value)--%>
    <%--            if(typeof  value == "object") {--%>
    <%--                value = JSON.stringify(value);--%>
    <%--            }--%>
    <%--            table += `<td>${value || ""}</td>`--%>
    <%--        })--%>
    <%--        table += "</tr>";--%>
    <%--    })--%>
    <%--    table += "</tbody></table>";--%>
    <%--    return table;--%>
    <%--}--%>

    function genererLien(data) {
        let date1 = data.date1;
        let date2 = data.date2;
        let colonneTri = data.colonneTri;
        let ordreTri = data.ordreTri;
        let lien = "http://localhost:8080/station/pages/module.jsp?but=vente/vente-analyse.jsp&currentMenu=MNDN0000000111";
        let apresLien = "";
        if (date1 != null) {
            apresLien += "&daty1=" + date1;
        }
        if (date2 != null) {
            apresLien += "&daty2=" + date2;
        }
        if (colonneTri != null) {
            apresLien += "&colonne=" + colonneTri;
        }
        if (ordreTri != null) {
            apresLien += "&ordre=" + ordreTri;
        }
        return lien + apresLien;
    }

    function fetchResult(data) {
        console.log("fetch result:" + data);
        let colAffiche = data.colonnesAffiche;
        let apresWhere = data.requeteSql;
        let lien = "http://localhost:8080/station/api/vente-analyse?";
        let apresLien = "";
        if(colAffiche != null) {
            apresLien += "&colAffiches=" + colAffiche;
        }
        if(apresWhere != null) {
            apresLien += "&apresWhere=" + apresWhere;
        }
        // lien += apresLien;

        // fetch(lien)
        //     .then(response => response.json())
        //     .then(data => {
        //         console.log("Response Data:" +  data[0]);
        //         const tableHtml = createTableFromObject(data);
        //         document.getElementById('rep-div2').innerHTML = tableHtml; // Replace with your target container
        //     })
        //     .catch(error => console.error("Error fetching data:", error));

        $.ajax({
            url: lien,
            contentType: "application/json",
            dataType: 'json',
            data: {
                colAffiches: colAffiche,
                apresWhere: apresWhere
            },
            type: 'GET',
            success: function (response) {
                console.log("response: " + response)
                // console.log("response stringify: " + JSON.stringify(response))
                // displayMessage(response, 'rep-div2')
                let ssss = JSON.stringify(response, null, 2);
                console.log("ssss: " + ssss );
                // let respJSON = JSON.parse(ssss)
                //
                // console.log(respJSON);
                console.table(ssss);
                console.table(JSON.parse(ssss));
                console.log(createTableFromObject(JSON.parse(ssss)));
                // console.log(createTableFromObject(response))
                // const data =
                // displayMessage(respJSON, 'rep-div2');
                if(document.getElementById('rep-div2') != null) {
                    document.getElementById('rep-div2').innerHTML = "";
                }
                document.getElementById('rep-div2').appendChild(createTableFromObject(JSON.parse(ssss)));
            },
            error: (err) => {
                console.error(err);
            }

        })
    }


    $(document).ready(function () {
        const $overlay = $('<div id="overlay" style="display:none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); z-index: 999;"></div>'); // z-index inférieur au loader
        $('body').append($overlay);
        const $loader = `
            <div>
                Chargement...
            </div>
        `
        // const $loader = $('<div id="loader" style="display:none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 1000; border: 16px solid #f3f3f3; border-top: 16px solid #3498db; border-radius: 50%; width: 120px; height: 120px; animation: spin 2s linear infinite;"></div>');
        // $('body').append($loader);
        $("#prompt-form").on("submit", function ($event) {
            $event.preventDefault();
            console.log("field value:" + $('#prompt-field').val());

            // if(document.getElementById('rep-div2') != null) {
            //     document.getElementById('rep-div2').remove()
            // }

            // evt.preventDefa
            $("#rep-div").html("");
            $("#overlay").show();
            $("#loader").show();
            var prompt = $(this).data("id");
            var formData = {
                prompt: $('#prompt-field').val()
            }

            $.ajax({
                url: "http://localhost:8000/generate",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({
                    prompt: `
                        La structure de ma table VENTE_DETAILS_CPL_2 est comme ceci: idproduitlib: string, daty: date, qte: decimal, putotal(pu * qte): decimal .
                        nom de table: VENTE_DETAILS_CPL_2
                        Base de donnee: Oracle 11g
                        Votre reponse doit être de cette forme (fromat JSON):
                        {
                            date1: dd/MM/yyyy,
                            date2: dd/MM/yyyy,
                            colonneTri: idproduit ou qte ou puRevient ou '',
                            ordreTri: ASC ou DESC,
                            requeteSql: string,
                            colonnesAffiche: string,
                            reponse: string,
                        }
                        - date1 et date2 sont facultatifs et rempli uniquement si il y a une intervalle de date dans la requete.
                        - colonneTri: facultatif et rempli uniquement si la requete demande du tri
                        - ordreTri: facultatif et rempli uniquement si la requete demande du tri
                        - requeteSql: la requete SQL oracle correpondant a la requete sans utiliser fetch first rows.
                        - colonneAffiches: les colonnes qu'on veut afficher parmi les colonnes sités séparé par ','.
                        - reponse: votre phrase pour introduire la reponse de la requete
                        bien verifier les erreurs dans la requete sql (ne pas utiliser FETCH FIRST ROWS dans la requete sql)
                        Ma requete:
                    ` + $('#prompt-field').val()
                }),
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true,
                success: function (response) {
                    let str = response.replace(/`/g, '').replace(/^json\s*/, '');
                    let data = JSON.parse(str);
                    const lien = genererLien(data);
                    let textAffiche = data.reponse + "\n Pour plus de detais, consulter <a href='" + lien + "'>ici</a>"
                    // console.log(data);
                    // console.table(data);
                    const formatted = formatResponseText(textAffiche);
                    fetchResult(data);
                    displayMessage(formatted, 'rep-div');

                },
                error: function (xhr, status, error) {
                    console.error("An error occurred: " + error);
                },
                complete: function () {
                    $("#overlay").hide();
                    $("#loader").hide();
                }
            });
        });
    });


    // document.addEventListener('DOMContentLoaded', () => {
    //     const form = document.getElementById("prompt-form");
    //     form.addEventListener('submit', (evt) => {
    //         evt.preventDefault();
    //         const formData = new FormData(form);
    //         sendData(formData);
    //     })
    //
    //     const sendData = (formData) => {
    //         const xhr = new XMLHttpRequest();
    //         xhr.onreadystatechange = () => {
    //             if(xhr.readyState < 4) return;
    //
    //             if(xhr.status === 200) {
    //                 onSuccess(xhr.response)
    //
    //             } else {
    //                 console.error(xhr.response)
    //             }
    //
    //         }
    //
    //         xhr.open('POST', 'http://localhost:80000/generate');
    //         xhr.send(formData);
    //     }
    //
    //     const onSuccess = (data) => {
    //
    //     }
    //
    //
    //
    //
    // })

</script>