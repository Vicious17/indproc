function enviar(vT0,vT1,vT2,vT3,vT4,vT5,vT6,vT7,vT8,vT9,vT10,vT11,vT12,vT13,vT14){
	  //alert("ENTRE JS");
	  document.getElementById("formdespresar:tabView:codalm_o_input").value= rTrim(vT0);
	  document.getElementById("formdespresar:tabView:codalm_d_input").value= rTrim(vT1);
	  document.getElementById("formdespresar:tabView:unidaddes_input").value= rTrim(vT2);
	  document.getElementById("formdespresar:tabView:pesopdes").value= rTrim(vT3);
	  document.getElementById("formdespresar:tabView:anocondes_input").value= rTrim(vT4);
	  document.getElementById("formdespresar:tabView:mescondes_input").value= rTrim(vT5);
	  document.getElementById("formdespresar:tabView:semcondes_input").value= rTrim(vT6);
	  document.getElementById("formdespresar:tabView:vop").value=rTrim(vT7);
	  document.getElementById("formdespresar:dialundes").value= rTrim(vT8);
	  document.getElementById("formdespresar:diamardes").value= rTrim(vT9);
	  document.getElementById("formdespresar:diamiedes").value= rTrim(vT10);
	  document.getElementById("formdespresar:diajuedes").value= rTrim(vT11);
	  document.getElementById("formdespresar:diaviedes").value= rTrim(vT12);
	  document.getElementById("formdespresar:diasabdes").value= rTrim(vT13);
	  document.getElementById("formdespresar:diadomdes").value= rTrim(vT14);
	  updateInput('formdespresar:tabView:codalm_o', '#F2F2F2');
	  updateInput('formdespresar:tabView:codalm_d', '#F2F2F2');
	  updateInput('formdespresar:tabView:pesopdes', '#F2F2F2');
	  updateInput('formdespresar:tabView:unidaddes', '#F2F2F2');
	  updateInput('formdespresar:tabView:anocondes', '#F2F2F2');
	  updateInput('formdespresar:tabView:mescondes', '#F2F2F2');
	  updateInput('formdespresar:tabView:semcondes', '#F2F2F2');
	}

function limpiar()
{  //Llamado por el boton limpiar
	document.getElementById("formdespresar:tabView:vop").value="0";
    clearUpdateInput('formdespresar:tabView:codalm_o', 'white');
    clearUpdateInput('formdespresar:tabView:codalm_d', 'white');
    clearUpdateInput('formdespresar:tabView:unidaddes', 'white');
    clearUpdateInput('formdespresar:tabView:pesopdes', 'white');
    clearUpdateInput('formdespresar:tabView:anocondes', 'white');
    clearUpdateInput('formdespresar:tabView:mescondes', 'white');
    clearUpdateInput('formdespresar:tabView:semcondes', 'white');
    clearUpdateInput('formdespresar:dialundes', 'white');
    clearUpdateInput('formdespresar:diamardes', 'white');
    clearUpdateInput('formdespresar:diamiedes', 'white');
    clearUpdateInput('formdespresar:diajuedes', 'white');
    clearUpdateInput('formdespresar:diaviedes', 'white');
    clearUpdateInput('formdespresar:diasabdes', 'white');
    clearUpdateInput('formdespresar:diadomdes', 'white');
}