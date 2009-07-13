/* Generated by re2j 0.13.6.dev on Mon Jul 13 14:42:48 2009 */
package org.yecht;

// Equivalent to token.re
public class TokenScanner implements YAMLGrammarTokens, Scanner {
   private Parser parser;

   private Object lval;
   private int currentToken = -1;

   public static void error(String msg, Parser parser) {
   }

   public static Scanner createScanner(Parser parser) {
     switch(parser.input_type) {
       case YAML_UTF8:
         return new TokenScanner(parser);
       case Bytecode_UTF8:
         // TODO: fix
         return null;
       case YAML_UTF16:
         error("UTF-16 is not currently supported in Yecht.\nPlease contribute code to help this happen!", parser);
         return null;
       case YAML_UTF32:
         error("UTF-32 is not currently supported in Yecht.\nPlease contribute code to help this happen!", parser);
         return null;
     }
     return null;
   }

   public TokenScanner(Parser parser) {
     this.parser = parser;
   }

   public Object getLVal() {
     return lval;
   }

   public int currentToken() {
     return currentToken;
   }

   public int yylex() {
     try {
          currentToken = real_yylex();
          return currentToken;
     } catch(java.io.IOException ioe) {
          throw new RuntimeException(ioe);
     }
   }

   private int real_yylex() throws java.io.IOException {
     int doc_level = 0;
     if(parser.cursor == -1) {
       parser.read();
     }

     if(parser.force_token != 0) {
       int t = parser.force_token;
       parser.force_token = 0;
       return t;
     }


   }

   private void eatComments() {
     comment: while(true) {
       parser.token = parser.cursor;

        int gotoPoint = -1;
        byte yych;
        while(true) {
            gotoNext: while(true) {
                switch(gotoPoint) {
                case -1: 

                    if ((parser.limit - parser.cursor) < 2) YYFILL(2);
                    yych = parser.buffer.buffer[parser.cursor];
                    switch (yych) {
                    case 0x00:    gotoPoint = 2; break gotoNext;
                    case '\n':    gotoPoint = 4; break gotoNext;
                    case '\r':    gotoPoint = 5; break gotoNext;
                    default:    gotoPoint = 7; break gotoNext;
                    }
                case 2:
                    ++parser.cursor;
                case 3:
                    {   parser.cursor = parser.token;
                        return;
                    }
                case 4:
                    yych = parser.buffer.buffer[(parser.marker = ++parser.cursor)];
                    gotoPoint = 9; break gotoNext;
                case 5:
                    ++parser.cursor;
                    switch ((yych = parser.buffer.buffer[parser.cursor])) {
                    case '\n':    gotoPoint = 8; break gotoNext;
                    default:    gotoPoint = 6; break gotoNext;
                    }
                case 6:
                    {   continue comment; 
                    }
                case 7:
                    yych = parser.buffer.buffer[++parser.cursor];
                    gotoPoint = 6; break gotoNext;
                case 8:
                    parser.marker = ++parser.cursor;
                    if (parser.limit <= parser.cursor) YYFILL(1);
                    yych = parser.buffer.buffer[parser.cursor];
                case 9:
                    switch (yych) {
                    case '\n':    gotoPoint = 8; break gotoNext;
                    case '\r':    gotoPoint = 10; break gotoNext;
                    default:    gotoPoint = 3; break gotoNext;
                    }
                case 10:
                    ++parser.cursor;
                    if (parser.limit <= parser.cursor) YYFILL(1);
                    yych = parser.buffer.buffer[parser.cursor];
                    switch (yych) {
                    case '\n':    gotoPoint = 8; break gotoNext;
                    default:    gotoPoint = 11; break gotoNext;
                    }
                case 11:
                    parser.cursor = parser.marker;
                    gotoPoint = 3; break gotoNext;
                }
            }
        }

    }
  }
}
