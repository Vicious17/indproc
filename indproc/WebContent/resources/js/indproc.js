function enviar(vT0,vT1,vT2,vT3,vT4,vT5,vT6,vT7,vT8,vT9,vT10,vT11,vT12,vT13,vT14,vT15,vT16,vT17,vT18,vT19){
	  //alert("dia1:" + vT11);
	  document.getElementById("formindproc:tabView:cat3_input").value= rTrim(vT0);
	  document.getElementById("formindproc:tabView:cat1").value= rTrim(vT1);
	  document.getElementById("formindproc:tabView:cat2").value= rTrim(vT2);
	  document.getElementById("formindproc:tabView:codalm").value= rTrim(vT3);
	  document.getElementById("formindproc:tabView:tipo_input").value= rTrim(vT4);
	  document.getElementById("formindproc:tabView:unidad_input").value= rTrim(vT5);
	  document.getElementById("formindproc:tabView:pesop").value= rTrim(vT6);
	  document.getElementById("formindproc:tabView:anocon_input").value= rTrim(vT7);
	  document.getElementById("formindproc:tabView:mescon_input").value= rTrim(vT8);
	  document.getElementById("formindproc:tabView:semcon_input").value= rTrim(vT9);
	  document.getElementById("formindproc:tabView:vop").value=rTrim(vT10);
	  document.getElementById("formindproc:dialun").value=rTrim(vT11);
	  document.getElementById("formindproc:diamar").value=rTrim(vT12);
	  document.getElementById("formindproc:diamie").value=rTrim(vT13);
	  document.getElementById("formindproc:diajue").value=rTrim(vT14);
	  document.getElementById("formindproc:diavie").value=rTrim(vT15);
	  document.getElementById("formindproc:diasab").value=rTrim(vT16);
	  document.getElementById("formindproc:diadom").value=rTrim(vT17);
	  document.getElementById("formindproc:anocon_input").value=rTrim(vT18);
	  document.getElementById("formindproc:pesop").value=rTrim(vT19);
	  updateInput('formindproc:tabView:cat3', '#F2F2F2');
	  updateInput('formindproc:tabView:cat1', '#F2F2F2');
	  updateInput('formindproc:tabView:cat2', '#F2F2F2');
	  updateInput('formindproc:tabView:codalm', '#F2F2F2');
	  updateInput('formindproc:tabView:tipo', '#F2F2F2');
	  updateInput('formindproc:tabView:anocon', '#F2F2F2');
	  updateInput('formindproc:tabView:mescon', '#F2F2F2');
	  updateInput('formindproc:tabView:semcon', '#F2F2F2');
	}

function limpiar()
{  //Llamado por el boton limpiar
	document.getElementById("formindproc:tabView:vop").value="0";
    clearUpdateInput('formindproc:tabView:cat1', 'white');
    clearUpdateInput('formindproc:tabView:cat2', '#F2F2F2');
    clearUpdateInput('formindproc:tabView:cat3', 'white');
    clearUpdateInput('formindproc:tabView:codalm', '#F2F2F2');
    clearUpdateInput('formindproc:tabView:anocon', 'white');
    clearUpdateInput('formindproc:tabView:mescon', 'white');
    clearUpdateInput('formindproc:tabView:semcon', 'white');
    clearUpdateInput('formindproc:tabView:unidad', 'white');
    clearUpdateInput('formindproc:tabView:tipo', 'white');
    clearUpdateInput('formindproc:tabView:pesop', 'white');
    clearUpdateInput('formindproc:dialun', 'white');
    clearUpdateInput('formindproc:diamar', 'white');
    clearUpdateInput('formindproc:diamie', 'white');
    clearUpdateInput('formindproc:diajue', 'white');
    clearUpdateInput('formindproc:diavie', 'white');
    clearUpdateInput('formindproc:diasab', 'white');
    clearUpdateInput('formindproc:diadom', 'white');
}