<#--noinspection JSUnresolvedFunction,JSValidateTypes-->
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Hugo Cl&eacute;ment&eacute;">
    <title>Celcat To Ics</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    <style>

    </style>

</head>

<body class="bg-light">

<div class="container">
    <div class="py-5 text-center">
        <h2>Celcat to ICS</h2>
        <p class="lead">
            Ajoutez ces URL &agrave; votre calendrier &eacute;lectronique. Il y a plusieurs calendriers pour pouvoir s&eacute;parer
            les
            diff&eacute;rentes cat&eacute;gories de Celcat.
        </p>
    </div>

    <div class="">

        <form>

            <div class="form-group row">
                <label for="formationName" class="col-sm-3 col-form-label col-form-label-lg">Nom de la formation
                    :</label>
                <div class="col-sm-9">
                    <input class="form-control form-control-lg" type="text" id="formationName"
                           placeholder="Remplir ici...">
                    <small class="form-text text-muted">
                        Le nom de la formation correspond &agrave; la fin de l'URL du calendrier Celcat.<br/>
                        Exemple :
                        <i>https://edt.univ-tlse3.fr/calendar2/cal?vt=agendaWeek&dt=2019-09-02&et=group&fid0=</i>
                        <b><strong><span style="color: black; ">IINA9CMA</span></strong></b>
                    </small>
                </div>
            </div>

            <div class="form-group row">
                <label for="url_TD" class="col-sm-3 col-form-label">TDs :</label>
                <div class="col-sm-9">
                    <input class="form-control form-control-sm" type="text" id="url_TD" readonly>
                </div>
            </div>

            <div class="form-group row">
                <label for="url_TP" class="col-sm-3 col-form-label">TPs :</label>
                <div class="col-sm-9">
                    <input class="form-control form-control-sm" type="text" id="url_TP" readonly>
                </div>
            </div>

            <div class="form-group row">
                <label for="url_COURS" class="col-sm-3 col-form-label">Cours :</label>
                <div class="col-sm-9">
                    <input class="form-control form-control-sm" type="text" id="url_COURS" readonly>
                </div>
            </div>

            <div class="form-group row">
                <label for="url_EXAMEN" class="col-sm-3 col-form-label">Examens :</label>
                <div class="col-sm-9">
                    <input class="form-control form-control-sm" type="text" id="url_EXAMEN" readonly>
                </div>
            </div>

            <div class="form-group row">
                <label for="url_TP_NON_ENCADRE" class="col-sm-3 col-form-label">TP non encadres :</label>
                <div class="col-sm-9">
                    <input class="form-control form-control-sm" type="text" id="url_TP_NON_ENCADRE"
                           readonly>
                </div>
            </div>

            <div class="form-group row">
                <label for="url_TP_ET_TP_NON_ENCADRE" class="col-sm-3 col-form-label">TP et TP non encadres :</label>
                <div class="col-sm-9">
                    <input class="form-control form-control-sm" type="text"
                           id="url_TP_ET_TP_NON_ENCADRE" readonly>
                </div>
            </div>

            <div class="form-group">

                <h4 class="mb-3">Reste</h4>
                <div class="alert alert-warning">
                    Comme les cat&eacute;gories de Celcat ne sont pas fixes, il faut pouvoir traiter les cas tels que
                    "REUNION/RENCONTRE" ou encore "SOUTIEN". Cette URL est l&agrave; pour ca. Cochez les cases que vous
                    avez d&eacute;j&agrave; dans votre agenda, pour ne pas les avoir en double, ou prenez toute l'url si
                    vous ne voulez pas cat&eacute;goriser les cours.
                </div>

                <div class="form-check form-check-inline">
                    <input type="checkbox" class="form-check-input" id="TD_CHECK">
                    <label class="form-check-label" for="TD_CHECK">TDs</label>
                </div>

                <div class="form-check form-check-inline">
                    <input type="checkbox" class="form-check-input" id="TP_CHECK">
                    <label class="form-check-label" for="TP_CHECK">TPs</label>
                </div>

                <div class="form-check form-check-inline">
                    <input type="checkbox" class="form-check-input" id="COURS_CHECK">
                    <label class="form-check-label" for="COURS_CHECK">Cours</label>
                </div>

                <div class="form-check form-check-inline">
                    <input type="checkbox" class="form-check-input" id="EXAMEN_CHECK">
                    <label class="form-check-label" for="EXAMEN_CHECK">Examens</label>
                </div>

                <div class="form-check form-check-inline">
                    <input type="checkbox" class="form-check-input" id="TP_NON_ENCADRE_CHECK">
                    <label class="form-check-label" for="TP_NON_ENCADRE_CHECK">TPs non encadres</label>
                </div>


                <div class="form-group row">
                    <label for="url_RESTE" class="col-sm-3 col-form-label">Reste :</label>
                    <div class="col-sm-9">
                        <input class="form-control form-control-sm" type="text"
                               id="url_RESTE" readonly>
                    </div>
                </div>

            </div>


        </form>

    </div>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">Klo</p>
        <ul class="list-inline">
            <li class="list-inline-item"><a href="https://github.com/Kla-w/celcaca-to-ical">GitHub</a></li>
        </ul>
    </footer>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script>

    //On part sur de la gitanerie #Jquery ¯\_(ツ)_/¯

    //Champs des URLS
    var TD = $('#url_TD');
    var TP = $('#url_TP');
    var TP_NON_ENCADRE = $('#url_TP_NON_ENCADRE');
    var TP_ET_TP_NON_ENCADRE = $('#url_TP_ET_TP_NON_ENCADRE');
    var COURS = $('#url_COURS');
    var EXAMEN = $('#url_EXAMEN');
    var RESTE = $('#url_RESTE');

    //Check boxes
    var TD_CHECK = $('#TD_CHECK');
    var TP_CHECK = $('#TP_CHECK');
    var TP_NON_ENCADRE_CHECK = $('#TP_NON_ENCADRE_CHECK');
    var COURS_CHECK = $('#COURS_CHECK');
    var EXAMEN_CHECK = $('#EXAMEN_CHECK');

    var base_url = window.location.origin.replace("http://", '').replace("https://", '');
    var formation_name = "";
    var filtres_no = "";

    function updateResteValue() {
        var new_val = base_url + '/ical/' + formation_name + '?' + filtres_no;
        RESTE.val(new_val)
    }

    //Changement formation
    <#--noinspection JSUnusedLocalSymbols-->
    $('#formationName').on('input', function (e) {


        formation_name = $(this).val();

        if (formation_name !== "") {
            TD.val(base_url + '/ical/' + formation_name + "?filtre_yes=TD");
            TP.val(base_url + '/ical/' + formation_name + "?filtre_yes=TP");
            TP_NON_ENCADRE.val(base_url + '/ical/' + formation_name + "?filtre_yes=TP_NON_ENCADRE");
            TP_ET_TP_NON_ENCADRE.val(base_url + '/ical/' + formation_name + "?filtre_yes=TP&filtre_yes=TP_NON_ENCADRE");
            COURS.val(base_url + '/ical/' + formation_name + "?filtre_yes=COURS");
            EXAMEN.val(base_url + '/ical/' + formation_name + "?filtre_yes=EXAMEN");

            updateResteValue();
        } else {
            TD.val("");
            TP.val("");
            TP_NON_ENCADRE.val("");
            TP_ET_TP_NON_ENCADRE.val("");
            COURS.val("");
            EXAMEN.val("");
            RESTE.val("");

            TD_CHECK.prop("checked", false);
            TP_CHECK.prop("checked", false);
            TP_NON_ENCADRE_CHECK.prop("checked", false);
            COURS_CHECK.prop("checked", false);
            EXAMEN_CHECK.prop("checked", false);
        }


    });

    TD_CHECK.change(function () {
        if (this.checked) {
            if (formation_name === "") TD_CHECK.prop("checked", false);
            else {
                filtres_no += "&filtre_no=TD";
                updateResteValue();
            }
        } else {
            if (formation_name !== "") {
                filtres_no = filtres_no.replace("&filtre_no=TD", '');
                updateResteValue();
            }
        }
    });
    TP_CHECK.change(function () {
        if (this.checked) {
            if (formation_name === "") TP_CHECK.prop("checked", false);
            else {
                filtres_no += "&filtre_no=TP";
                updateResteValue();
            }
        } else {
            if (formation_name !== "") {
                filtres_no = filtres_no.replace("&filtre_no=TP", '');
                updateResteValue();
            }
        }
    });
    TP_NON_ENCADRE_CHECK.change(function () {
        if (this.checked) {
            if (formation_name === "") TP_NON_ENCADRE_CHECK.prop("checked", false);
            else {
                filtres_no += "&filtre_no=TP_NON_ENCADRE";
                updateResteValue();
            }
        } else {
            if (formation_name !== "") {
                filtres_no = filtres_no.replace("&filtre_no=TP_NON_ENCADRE", '');
                updateResteValue();
            }
        }
    });
    COURS_CHECK.change(function () {
        if (this.checked) {
            if (formation_name === "") COURS_CHECK.prop("checked", false);
            else {
                filtres_no += "&filtre_no=COURS";
                updateResteValue();
            }
        } else {
            if (formation_name !== "") {
                filtres_no = filtres_no.replace("&filtre_no=COURS", '');
                updateResteValue();
            }
        }
    });
    EXAMEN_CHECK.change(function () {
        if (this.checked) {
            if (formation_name === "") EXAMEN_CHECK.prop("checked", false);
            else {
                filtres_no += "&filtre_no=EXAMEN";
                updateResteValue();
            }
        } else {
            if (formation_name !== "") {
                filtres_no = filtres_no.replace("&filtre_no=EXAMEN", '');
                updateResteValue();
            }
        }
    });


</script>

</body>
</html>
