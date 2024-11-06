package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol newSymbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol newSymbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"		{ return newSymbol(sym.PROG, yytext());		}
"break"			{ return newSymbol(sym.BREAK, yytext()); 	}
"class"			{ return newSymbol(sym.CLASS, yytext()); 	}
"else"			{ return newSymbol(sym.ELSE, yytext()); 	}
"const"			{ return newSymbol(sym.CONST, yytext()); 	}
"if"			{ return newSymbol(sym.IF, yytext()); 		}
"new"			{ return newSymbol(sym.NEW, yytext()); 		}
"print"			{ return newSymbol(sym.PRINT, yytext()); 	}
"read"			{ return newSymbol(sym.READ, yytext()); 	}
"return"		{ return newSymbol(sym.RETURN, yytext()); 	}
"void"			{ return newSymbol(sym.VOID, yytext()); 	}
"extends"		{ return newSymbol(sym.EXTENDS, yytext()); 	}
"continue"		{ return newSymbol(sym.CONTINUE, yytext()); }
"for"		{ return newSymbol(sym.FOR, yytext());	}
"static"		{ return newSymbol(sym.STATIC, yytext());	}

"*"				{ return newSymbol(sym.MUL, yytext()); 	}
"/"				{ return newSymbol(sym.DIV, yytext()); 	}
"%"				{ return newSymbol(sym.PERCENT, yytext()); 	}
"=="			{ return newSymbol(sym.EQUAL, yytext()); 	}
"!="			{ return newSymbol(sym.NOTEQUAL, yytext()); 	}
">="			{ return newSymbol(sym.GTE, yytext());		}
">"				{ return newSymbol(sym.GT, yytext());		}
"<="			{ return newSymbol(sym.LTE, yytext());		}
"<"				{ return newSymbol(sym.LT, yytext());		}
"&&"			{ return newSymbol(sym.AND, yytext()); 		}
"||"			{ return newSymbol(sym.OR, yytext()); 		}
"=>"			{ return newSymbol(sym.LAMBDA, yytext()); 	}
"="				{ return newSymbol(sym.ASSIGN, yytext());	}
"++"			{ return newSymbol(sym.INC, yytext()); 	}
"+"				{ return newSymbol(sym.PLUS, yytext()); 	}
"--"			{ return newSymbol(sym.DEC, yytext()); 	}
"-"				{ return newSymbol(sym.MINUS, yytext()); 	}
";"				{ return newSymbol(sym.SEMI, yytext()); 	}
":"				{ return newSymbol(sym.COLON, yytext()); 	}
","				{ return newSymbol(sym.COMMA, yytext()); 	}
"."				{ return newSymbol(sym.PERIOD, yytext()); 	}
"("				{ return newSymbol(sym.LPAREN, yytext()); 	}
")"				{ return newSymbol(sym.RPAREN, yytext()); 	}
"["				{ return newSymbol(sym.LBRACKET, yytext());	}
"]"				{ return newSymbol(sym.RBRACKET, yytext());	}
"{"				{ return newSymbol(sym.LBRACE, yytext()); 	}
"}"				{ return newSymbol(sym.RBRACE, yytext()); 	}


<YYINITIAL> "//" 		     { yybegin(COMMENT); }
<COMMENT> .      { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

"true"					{ return newSymbol(sym.BOOLCONST, true); 	}
"false"					{ return newSymbol(sym.BOOLCONST, false); 	}
[0-9]+  				{ return newSymbol(sym.NUMCONST, Integer.valueOf(yytext()));	}
['].[']					{ return newSymbol(sym.CHARCONST, yytext().charAt(1));		}
[a-zA-Z][a-zA-Z0-9_]*	{ return newSymbol(sym.IDENT, yytext());}

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }
