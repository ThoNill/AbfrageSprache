lexer grammar AbfrageLexer;

VARNAME : [\.a-zA-Z0-9]+;

DDO : '{{';

DDC : '}}';

VO : '${';

VC : '}';

TEXT : ~[a-zA-Z0-9\/\.\$\{\}]+;

RESTART : [/] -> mode(INSIDERE) ;

mode INSIDERE;

RESTOP : [/] ->mode(DEFAULT_MODE);

RE : ~[/]+;

