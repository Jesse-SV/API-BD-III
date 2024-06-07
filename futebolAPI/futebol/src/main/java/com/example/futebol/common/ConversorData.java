package com.example.futebol.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorData {
    public static String converterParaFormatoDataHora(Date date){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return formatador.format(date);
    }    
}
