function cargarCategorias() {
    var html = "<option value=''>[SELECCIONAR]</option>";
    httpConnect("/categoria", {}, "GET", function (r) {
        for (var i = 0; i < r.data.length; i++) {
            var categoria = r.data[i];
            html += "<option value='" + categoria.catId + "'>" + categoria.catNombre + "</option>";
        }
        $("#catId").html(html);
    });
}

function cargarAutores() {
    var html = "";
    httpConnect("/autor", {}, "GET", function (r) {
        for (var i = 0; i < r.data.length; i++) {
            var autor = r.data[i];
            html += "<option value='" + autor.autId + "'>" + autor.autNombre + "</option>";
        }
        $("#autores").html(html);
        $('#autores').dashboardCodeBsMultiSelect();
    });
}


$(function () {
    //Cargar dependencias de la vista
    cargarCategorias();
    cargarAutores();
    //Asiganacion del evento submit para el formulario de crear libro
    $("#frmCrear").submit(function () {

        var entidad = new Object();

        $(".form-control").each(function () {
            var attr = $(this).attr("id");
            entidad[attr] = $(this).val();
        });

        entidad.catId = {
            catId: $("#catId").val()
        };

        var autores = $("#autores").val();
        var autoresAux = [];
        $(autores).each(function(){
            
        });

        var jentidad = JSON.stringify(entidad);

        httpConnect("/libro", jentidad, "POST", function (r) {
            alert(r.message + "-" + r.data.libNombre);
            $("button[type=reset]").click();
        }, function (r) {
            alert(r.message);
        });

        return false;
    });
});

//32 - 9.500.000 | (296.000 + 54.000) 