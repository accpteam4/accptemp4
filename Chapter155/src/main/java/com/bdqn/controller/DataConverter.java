package com.bdqn.controller;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConverter implements Converter<String,Date> {
    private String dataPatter;

    public void setDataPatter(String dataPatter) {
        this.dataPatter = dataPatter;
    }

    public Date convert(String s) {
        Date data = null;
        try {
            data = new SimpleDateFormat(dataPatter).parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }
}
