/*
 * Funciones estandart que pueden ser utilizadas en todas las páginas
 * 
 */
/*
 *  Copyright (C) 2011  ANDRES DOMINGUEZ, CHRISTIAN DÍAZ

    Este programa es software libre: usted puede redistribuirlo y/o modificarlo 
    bajo los términos de la Licencia Pública General GNU publicada 
    por la Fundación para el Software Libre, ya sea la versión 3 
    de la Licencia, o (a su elección) cualquier versión posterior.

    Este programa se distribuye con la esperanza de que sea útil, pero 
    SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita 
    MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO. 
    Consulte los detalles de la Licencia Pública General GNU para obtener 
    una información más detallada. 

    Debería haber recibido una copia de la Licencia Pública General GNU 
    junto a este programa. 
    En caso contrario, consulte <http://www.gnu.org/licenses/>.
 */

//Crea el foco en el input usuario, al cargar la página
function inicio(){
    	document.getElementById("loginform:usuario").focus();
}

function ingresar(){
return true;

}

function exit(){
	top.location.href ='/bizview/faces/login.xhtml';
}
/******************************************************************************
 ******************************************************************************/

function lTrim(sStr){
    while (sStr.charAt(0) == " ")
        sStr = sStr.substr(1, sStr.length - 1);
    return sStr;
}
/******************************************************************************
 *                   Modifica el estilo para IE
 ******************************************************************************/

function ini_mnu(){//Modifica el menú para visualizar en modo de compatibilidad
	if (navigator.appName.indexOf("Microsoft Internet Explorer") !=-1 && document.documentMode <= 7) {
		document.getElementById("menu").style.marginLeft="-9px";
		document.getElementById("menu").style.marginTop="-28px";
		$('#menu').corner('8px');
		document.getElementById("ControlFrame").style.marginTop="-10px";
	}
}

function ini_btn_cianom(){//Modifica el menú para visualizar en modo de compatibilidad
	if (navigator.appName.indexOf("Microsoft Internet Explorer") !=-1 && document.documentMode <= 7) {
		document.getElementById("btnBus").style.height="28px";
		document.getElementById("btnLimp").style.height="28px";
		document.getElementById("btnSig").style.height="28px";
		document.getElementById("botonera").style.marginLeft="6px";
	}
}


/******************************************************************************
  *********************Click para acceso directo en lista de valores************
 ******************************************************************************/
function fm_mlista(vtId)
{
    document.getElementById(vtId).value='%';
    document.getElementById(vtId).focus();   
        $('#'+vtId).keydown();

}

function limpiarMensaje(vtId){
    if(vtId != "")
        document.getElementById(vtId).innerHTML = "";
    document.getElementById(vtId).className = "";
}

function info(){
    // Si el mensaje que retorna es acceso
    window.open('../jsf/acercade.jsp','target_blank','height=190,width=550','top=100,resizable=false,scrollbars=no,toolbar=no,status=no');

}
/******************************************************************************
  *********************Funciones genéricas************
 ******************************************************************************/
function limpiarInput(vtId){
    if(vtId != "")
        document.getElementById(vtId).value = "";
}

function limpiarMensaje(vtId){
    if(vtId != "")
        document.getElementById(vtId).innerHTML = "";
    document.getElementById(vtId).className = "";
}

function mensajes(){
    var tiempo = document.getElementById("claseResult").value;
    var duracion;
    if (tiempo=="exito"){
        duracion = 250000;
    } else {
        duracion = 500000;
    }
    setTimeout("limpiarMensaje('mensaje');",duracion);
}

function IsNumeric(sText)
{
    var ValidChars = "-0123456789.";
    var IsNumber=true;
    var Char;
    var i;

    for (i = 0; i < sText.length && IsNumber == true; i++)
    {
        Char = sText.charAt(i);
        if (ValidChars.indexOf(Char) == -1)
        {
            IsNumber = false;
        }
    }
    return IsNumber;

}

function IsInt(sText)
{
    var ValidChars = "0123456789";
    var IsInt=true;
    var Char;
    var i;

    for (i = 0; i < sText.length && IsInt == true; i++)
    {
        Char = sText.charAt(i);
        if (ValidChars.indexOf(Char) == -1)
        {
            IsInt = false;
        }
    }
    return IsInt;

}


function rTrim(sStr){
    while (sStr.charAt(sStr.length - 1) == " ")
        sStr = sStr.substr(0, sStr.length - 1);
    return sStr;
}

function fm_check(vTcheck){

	var chkBoxes = $('input[name='+vTcheck+']');
    chkBoxes.prop("checked", !chkBoxes.prop("checked"));
}

function cursor(){ 
    document.body.style.cursor = "pointer"; 
} 

function uncursor(){ 
    document.body.style.cursor = ""; 
} 

/******************************************************************************
  *********************Funciones para accesos directos************
 ******************************************************************************/
function fm_pgdirect(){//Para mostrar el fragmento pages_accdirect
    $(document).ready(function(){
        $(document).ready(function () {
            $('#pgdirect').animate({width: 'toggle'});
        });
    });
} 

/******************************************************************************
 *********************Funciones para validar correo************
******************************************************************************/
function validateEmail($email) {
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	var IsOk = true;
	if( !emailReg.test( $email ) ) {
		IsOk = false;
	} 
	return IsOk;
}


function updateInput(vinputid, vcolor){
	document.getElementById(vinputid).style.backgroundColor = vcolor;
	document.getElementById(vinputid).readOnly = true;
}

function clearUpdateInput(vinputid, vcolor){
	//alert("ENTRE");
	document.getElementById(vinputid).style.backgroundColor = vcolor;
	document.getElementById(vinputid).style.color = 'white';
}

function focusInput(vinputid){
	document.getElementById(vinputid).style.backgroundColor = '#fff';
	document.getElementById(vinputid).style.color = 'black';
}




