grammar Abfrage;

options { tokenVocab=AbfrageLexer;}

text : teil*;

teil :  VARNAME | TEXT | optional | varname;

regexp : RESTART RE RESTOP ;

optional : DDO teil* DDC;

varname : VO VARNAME regexp* VC ;

