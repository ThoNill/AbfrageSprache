grammar Abfrage;


text : teil*;

teil :  TEXT | optional | varname;

optional : '{{' teil* '}}';

varname : '${' TEXT '}';

// ID : [a-zA-Z][0-9a-zA-Z]*;

TEXT : [ a-zA-Z0-9]+;


// WS : [ \t\r\n]+ -> skip;