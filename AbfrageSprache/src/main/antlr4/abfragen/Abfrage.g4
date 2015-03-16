grammar Abfrage;


text : teil*;

teil :  TEXT | optional | varname;

regexp : '/' INREGEXP '/';

optional : '{{' teil* '}}';

varname : '${' TEXT regexp* '}';

// ID : [a-zA-Z][0-9a-zA-Z]*;

TEXT : [ a-zA-Z0-9]+;

INREGEXP : [^/]*;


// WS : [ \t\r\n]+ -> skip;