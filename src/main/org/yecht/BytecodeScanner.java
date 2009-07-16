/* Generated by re2j 0.13.6.dev on Thu Jul 16 18:09:12 2009 */
package org.yecht;

import java.io.IOException;

// Equivalent to bytecode.re
public class BytecodeScanner implements YAMLGrammarTokens, Scanner {
   public final static int QUOTELEN = 128;
   private Parser parser;

   private Object lval;
   private int currentToken = -1;

   public BytecodeScanner(Parser parser) {
     this.parser = parser;
     yylex();
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

   private void YYPOS(int n) {
       parser.cursor = parser.token + n;
   }

   private void FORCE_NEXT_TOKEN(int n) {
       parser.force_token = n;
   }

   private void CHK_NL(int ptr) {
       if(parser.buffer.buffer[ptr - 1] == '\n' && ptr > parser.linectptr) {
           parser.lineptr = ptr;
           parser.linect++;
           parser.linectptr = parser.lineptr;
       }
   }

   private boolean ADD_BYTE_LEVEL(Level lvl, int len, LevelStatus s) {
       switch(lvl.status) {
           case seq:
               lvl.ncount++;
               parser.addLevel(len, LevelStatus.open);
               YYPOS(0);
               return true;
           case map:
               lvl.ncount++;
               parser.addLevel(len, s);
               return false;
           case open:
               lvl.status = s;
               return false;
           default:
               parser.addLevel(len, s);
               return false;
       }
   }

   private final static int Start = 1;
   private final static int Document = 2;
   private final static int Directive = 3;
   private final static int Comment = 4;
   private final static int Scalar = 5;
   private final static int Scalar2 = 6;
   private final static int ScalarEnd = 7;

   private static class QuotedString {
       public int idx = 0;
       public int capa = 100;
       public byte[] str;

       public QuotedString() {
           str = new byte[100];
       }

       public void cat(char l) {
           cat((byte)l);
       }
      
       public void cat(byte l) {
           if(idx + 1 >= capa) {
               capa += QUOTELEN;
               str = YAML.realloc(str, capa);
           }
           str[idx++] = l;
           str[idx] = 0;
       }
   }

   // sycklex_bytecode_utf8
   private int real_yylex() throws IOException {
       Level lvl = null;
       QuotedString q = null;
       int tok = -1;

       if(parser.cursor == -1) {
           parser.read();
       }

       if(parser.force_token != 0) {
           int t = parser.force_token;
           parser.force_token = 0;
           return t;
       }


       int mainLoopGoto = Start;
       lvl = parser.currentLevel();
       if(lvl.status == LevelStatus.doc) {
           mainLoopGoto = Document;
       }

       parser.token = parser.cursor;
       {

        int gotoPoint = -1;
        byte yych = (byte) 0;
        re2jgetout: while(true) {
            gotoNext: while(true) {
                switch(gotoPoint) {
                case -1: 

                    if ((parser.limit - parser.cursor) < 3) parser.read();
                    yych = parser.buffer.buffer[parser.cursor];
                    switch (yych) {
                    case 0x00:    {gotoPoint = 2; break gotoNext;}
                    case 'D':    {gotoPoint = 3; break gotoNext;}
                    default:    {gotoPoint = 5; break gotoNext;}
                    }
                case 2:
                    parser.cursor = parser.marker;
                    {gotoPoint = 4; break gotoNext;}
                case 3:
                    yych = parser.buffer.buffer[(parser.marker = ++parser.cursor)];
                    switch (yych) {
                    case '\n':    {gotoPoint = 6; break gotoNext;}
                    case '\r':    {gotoPoint = 8; break gotoNext;}
                    default:    {gotoPoint = 4; break gotoNext;}
                    }
                case 4:
                    {   YYPOS(0);
            mainLoopGoto = Document;
        }
                case 5:
                    yych = parser.buffer.buffer[++parser.cursor];
                    {gotoPoint = 4; break gotoNext;}
                case 6:
                    ++parser.cursor;
                    {   if(lvl.status == LevelStatus.header) {
                CHK_NL(parser.cursor);
                mainLoopGoto = Directive;
            } else  {
                if(lvl.spaces > -1) {
                    parser.popLevel();
                    YYPOS(0);
                    return YAML_IEND;
                }
                YYPOS(0);
                return 0;
            }
        }
                case 8:
                    ++parser.cursor;
                    switch ((yych = parser.buffer.buffer[parser.cursor])) {
                    case '\n':    {gotoPoint = 6; break gotoNext;}
                    default:    {gotoPoint = 2; break gotoNext;}
                    }
                }
            break re2jgetout;}
        }

       }

       do {
           gotoSomething: while(true) {
               switch(mainLoopGoto) {
               case Start: {
               }
               case Document: {
                   lvl = parser.currentLevel();
                   if(lvl.status == LevelStatus.header) {
                       lvl.status = LevelStatus.doc;
                   }
                   parser.token = parser.cursor;


        int gotoPoint = -1;
        byte yych = (byte) 0;
        re2jgetout: while(true) {
            gotoNext: while(true) {
                switch(gotoPoint) {
                case -1: 
                    if ((parser.limit - parser.cursor) < 3) parser.read();
                    yych = parser.buffer.buffer[parser.cursor];
                    switch (yych) {
                    case 0x00:    {gotoPoint = 30; break gotoNext;}
                    case '\n':    {gotoPoint = 27; break gotoNext;}
                    case '\r':    {gotoPoint = 29; break gotoNext;}
                    case 'A':    {gotoPoint = 19; break gotoNext;}
                    case 'D':    {gotoPoint = 12; break gotoNext;}
                    case 'E':    {gotoPoint = 16; break gotoNext;}
                    case 'M':    {gotoPoint = 14; break gotoNext;}
                    case 'P':    {gotoPoint = 13; break gotoNext;}
                    case 'Q':    {gotoPoint = 15; break gotoNext;}
                    case 'R':    {gotoPoint = 21; break gotoNext;}
                    case 'S':    {gotoPoint = 17; break gotoNext;}
                    case 'T':    {gotoPoint = 23; break gotoNext;}
                    case 'c':    {gotoPoint = 25; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                case 11:
                case 12:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case '\n':    {gotoPoint = 41; break gotoNext;}
                    case '\r':    {gotoPoint = 44; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                case 13:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case '\n':    {gotoPoint = 41; break gotoNext;}
                    case '\r':    {gotoPoint = 43; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                case 14:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case '\n':    {gotoPoint = 38; break gotoNext;}
                    case '\r':    {gotoPoint = 40; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                case 15:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case '\n':    {gotoPoint = 35; break gotoNext;}
                    case '\r':    {gotoPoint = 37; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                case 16:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case '\n':    {gotoPoint = 32; break gotoNext;}
                    case '\r':    {gotoPoint = 34; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                case 17:
                    ++parser.cursor;
                    {   if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.str)) {
                return '-';
            }
            mainLoopGoto = Scalar; break gotoSomething;
        }
                case 19:
                    ++parser.cursor;
                    {   if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.open)) {
                return '-';
            }
            lval = getInline();
            parser.removeAnchor((String)lval);
            CHK_NL(parser.cursor);
            return YAML_ANCHOR;
        }
                case 21:
                    ++parser.cursor;
                    {   if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.str)) {
                return '-';
            }
            lval = getInline();
            parser.popLevel();
            if( parser.buffer.buffer[parser.cursor - 1] == '\n') parser.cursor--;
            return YAML_ALIAS;
        }
                case 23:
                    ++parser.cursor;
                    {   
            if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.open)) {
                return '-';
            }
            String qstr = getInline();
            CHK_NL(parser.cursor);
            if(qstr.charAt(0) == '!' ) {
                int qidx = qstr.length();
                if(qidx == 1) {
                    return YAML_ITRANSFER;
                }

                lvl = parser.currentLevel();

                /*
                 * URL Prefixing
                 */
                if(qstr.charAt(1) == '^') {
                    lval = lvl.domain + qstr.substring(2);
                } else {
                    int carat = qstr.indexOf('^');
                    if(carat != -1) {
                        lvl.domain = qstr.substring(1, carat);
                        lval = lvl.domain + qstr.substring(carat + 1);
                    } else {
                        lval = qstr.substring(1);
                    }
                }
                return YAML_TRANSFER;
            }
            lval = qstr;
            return YAML_TAGURI;
        }
                case 25:
                    ++parser.cursor;
                    { mainLoopGoto = Comment; break gotoSomething; }
                case 27:
                    ++parser.cursor;
                    {   CHK_NL(parser.cursor);
            if(lvl.status == LevelStatus.seq) {
                return YAML_INDENT; 
            } else if(lvl.status == LevelStatus.map) {
                if(lvl.ncount % 2 == 1) return ':';
                else                    return YAML_INDENT;
            }
            mainLoopGoto = Document; break gotoSomething;
        }
                case 29:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case '\n':    {gotoPoint = 27; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                case 30:
                    ++parser.cursor;
                    {   if(lvl.spaces > -1) {
                    parser.popLevel();
                    YYPOS(0);
                    return YAML_IEND;
            }
            YYPOS(0);
            return 0;
        }
                case 32:
                    ++parser.cursor;
                    {   if(lvl.status == LevelStatus.seq && lvl.ncount == 0) {
                lvl.ncount++;
                YYPOS(0);
                FORCE_NEXT_TOKEN( ']' );
                return '[';
            } else if(lvl.status == LevelStatus.map && lvl.ncount == 0) {
                lvl.ncount++;
                YYPOS(0);
                FORCE_NEXT_TOKEN( '}' );
                return '{';
            }
            parser.popLevel();
            lvl = parser.currentLevel();
            if(lvl.status == LevelStatus.seq) {
                FORCE_NEXT_TOKEN(YAML_INDENT);   
            } else if(lvl.status == LevelStatus.map) {
                if(lvl.ncount % 2 == 1) {
                    FORCE_NEXT_TOKEN(':');
                } else {
                    FORCE_NEXT_TOKEN(YAML_INDENT);
                }
            }
            CHK_NL(parser.cursor);
            return YAML_IEND;
        }
                case 34:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case '\n':    {gotoPoint = 32; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                case 35:
                    ++parser.cursor;
                    {   boolean complex = false;
            if(lvl.ncount % 2 == 0 && ( lvl.status == LevelStatus.map || lvl.status == LevelStatus.seq)) {
                complex = true;
            }
            if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.seq)) {
                return '-';
            }
            CHK_NL(parser.cursor);
            if(complex) {
                FORCE_NEXT_TOKEN( YAML_IOPEN );
                return '?';
            }
            return YAML_IOPEN;
        }
                case 37:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case '\n':    {gotoPoint = 35; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                case 38:
                    ++parser.cursor;
                    {   boolean complex = false;
            if(lvl.ncount % 2 == 0 && ( lvl.status == LevelStatus.map || lvl.status == LevelStatus.seq)) {
                complex = true;
            }
            if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.map)) {
                return '-';
            }
            CHK_NL(parser.cursor);
            if(complex) {
                FORCE_NEXT_TOKEN( YAML_IOPEN );
                return '?';
            }
            return YAML_IOPEN;
        }
                case 40:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case '\n':    {gotoPoint = 38; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                case 41:
                    ++parser.cursor;
                    {   if(lvl.spaces > -1) {
                    parser.popLevel();
                    YYPOS(0);
                    return YAML_IEND;
                }
                YYPOS(0);
                return 0;
            }
                case 43:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case '\n':    {gotoPoint = 41; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                case 44:
                    ++parser.cursor;
                    switch ((yych = parser.buffer.buffer[parser.cursor])) {
                    case '\n':    {gotoPoint = 41; break gotoNext;}
                    default:    {gotoPoint = 11; break gotoNext;}
                    }
                }
            break re2jgetout;}
        }

               }
               case Directive: {
                   parser.token = parser.cursor;


        int gotoPoint = -1;
        byte yych = (byte) 0;
        re2jgetout: while(true) {
            gotoNext: while(true) {
                switch(gotoPoint) {
                case -1: 
                    if ((parser.limit - parser.cursor) < 2) parser.read();
                    yych = parser.buffer.buffer[parser.cursor];
                    switch (yych) {
                    case 0x00:    {gotoPoint = 47; break gotoNext;}
                    case 'V':    {gotoPoint = 48; break gotoNext;}
                    default:    {gotoPoint = 50; break gotoNext;}
                    }
                case 47:
                    parser.cursor = parser.marker;
                    {gotoPoint = 49; break gotoNext;}
                case 48:
                    yych = parser.buffer.buffer[(parser.marker = ++parser.cursor)];
                    switch (yych) {
                    case '.':
                    case '/':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case ':':
                    case ';':
                    case '<':
                    case '=':
                    case '>':
                    case '?':
                    case '@':
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                    case 'G':
                    case 'H':
                    case 'I':
                    case 'J':
                    case 'K':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'O':
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'S':
                    case 'T':
                    case 'U':
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    case 'Z':
                    case '[':
                    case '\\':
                    case ']':
                    case '^':
                    case '_':
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f':
                    case 'g':
                    case 'h':
                    case 'i':
                    case 'j':
                    case 'k':
                    case 'l':
                    case 'm':
                    case 'n':
                    case 'o':
                    case 'p':
                    case 'q':
                    case 'r':
                    case 's':
                    case 't':
                    case 'u':
                    case 'v':
                    case 'w':
                    case 'x':
                    case 'y':
                    case 'z':    {gotoPoint = 51; break gotoNext;}
                    default:    {gotoPoint = 49; break gotoNext;}
                    }
                case 49:
                    {   parser.cursor = parser.token;
               return YAML_DOCSEP;
           }
                case 50:
                    yych = parser.buffer.buffer[++parser.cursor];
                    {gotoPoint = 49; break gotoNext;}
                case 51:
                    ++parser.cursor;
                    if ((parser.limit - parser.cursor) < 2) parser.read();
                    yych = parser.buffer.buffer[parser.cursor];
                    switch (yych) {
                    case '.':
                    case '/':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case ';':
                    case '<':
                    case '=':
                    case '>':
                    case '?':
                    case '@':
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                    case 'G':
                    case 'H':
                    case 'I':
                    case 'J':
                    case 'K':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'O':
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'S':
                    case 'T':
                    case 'U':
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    case 'Z':
                    case '[':
                    case '\\':
                    case ']':
                    case '^':
                    case '_':
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f':
                    case 'g':
                    case 'h':
                    case 'i':
                    case 'j':
                    case 'k':
                    case 'l':
                    case 'm':
                    case 'n':
                    case 'o':
                    case 'p':
                    case 'q':
                    case 'r':
                    case 's':
                    case 't':
                    case 'u':
                    case 'v':
                    case 'w':
                    case 'x':
                    case 'y':
                    case 'z':    {gotoPoint = 51; break gotoNext;}
                    case ':':    {gotoPoint = 53; break gotoNext;}
                    default:    {gotoPoint = 47; break gotoNext;}
                    }
                case 53:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case '.':
                    case '/':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case ':':
                    case ';':
                    case '<':
                    case '=':
                    case '>':
                    case '?':
                    case '@':
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                    case 'G':
                    case 'H':
                    case 'I':
                    case 'J':
                    case 'K':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'O':
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'S':
                    case 'T':
                    case 'U':
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    case 'Z':
                    case '[':
                    case '\\':
                    case ']':
                    case '^':
                    case '_':
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f':
                    case 'g':
                    case 'h':
                    case 'i':
                    case 'j':
                    case 'k':
                    case 'l':
                    case 'm':
                    case 'n':
                    case 'o':
                    case 'p':
                    case 'q':
                    case 'r':
                    case 's':
                    case 't':
                    case 'u':
                    case 'v':
                    case 'w':
                    case 'x':
                    case 'y':
                    case 'z':    {gotoPoint = 54; break gotoNext;}
                    default:    {gotoPoint = 47; break gotoNext;}
                    }
                case 54:
                    ++parser.cursor;
                    if ((parser.limit - parser.cursor) < 2) parser.read();
                    yych = parser.buffer.buffer[parser.cursor];
                    switch (yych) {
                    case '\n':    {gotoPoint = 56; break gotoNext;}
                    case '\r':    {gotoPoint = 58; break gotoNext;}
                    case '.':
                    case '/':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case ':':
                    case ';':
                    case '<':
                    case '=':
                    case '>':
                    case '?':
                    case '@':
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                    case 'G':
                    case 'H':
                    case 'I':
                    case 'J':
                    case 'K':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'O':
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'S':
                    case 'T':
                    case 'U':
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    case 'Z':
                    case '[':
                    case '\\':
                    case ']':
                    case '^':
                    case '_':
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f':
                    case 'g':
                    case 'h':
                    case 'i':
                    case 'j':
                    case 'k':
                    case 'l':
                    case 'm':
                    case 'n':
                    case 'o':
                    case 'p':
                    case 'q':
                    case 'r':
                    case 's':
                    case 't':
                    case 'u':
                    case 'v':
                    case 'w':
                    case 'x':
                    case 'y':
                    case 'z':    {gotoPoint = 54; break gotoNext;}
                    default:    {gotoPoint = 47; break gotoNext;}
                    }
                case 56:
                    ++parser.cursor;
                    {   CHK_NL(parser.cursor);
               mainLoopGoto = Directive; break gotoSomething;
           }
                case 58:
                    ++parser.cursor;
                    switch ((yych = parser.buffer.buffer[parser.cursor])) {
                    case '\n':    {gotoPoint = 56; break gotoNext;}
                    default:    {gotoPoint = 47; break gotoNext;}
                    }
                }
            break re2jgetout;}
        }

}
               case Comment: {
                   parser.token = parser.cursor;


        int gotoPoint = -1;
        byte yych = (byte) 0;
        re2jgetout: while(true) {
            gotoNext: while(true) {
                switch(gotoPoint) {
                case -1: 
                    if ((parser.limit - parser.cursor) < 2) parser.read();
                    yych = parser.buffer.buffer[parser.cursor];
                    switch (yych) {
                    case 0x00:    {gotoPoint = 61; break gotoNext;}
                    case '\n':    {gotoPoint = 62; break gotoNext;}
                    case '\r':    {gotoPoint = 64; break gotoNext;}
                    default:    {gotoPoint = 66; break gotoNext;}
                    }
                case 61:
                case 62:
                    ++parser.cursor;
                case 63:
                    {   CHK_NL(parser.cursor);
                mainLoopGoto = Document; break gotoSomething; }
                case 64:
                    ++parser.cursor;
                    switch ((yych = parser.buffer.buffer[parser.cursor])) {
                    case '\n':    {gotoPoint = 67; break gotoNext;}
                    default:    {gotoPoint = 65; break gotoNext;}
                    }
                case 65:
                    {   mainLoopGoto = Comment; break gotoSomething; }
                case 66:
                    yych = parser.buffer.buffer[++parser.cursor];
                    {gotoPoint = 65; break gotoNext;}
                case 67:
                    ++parser.cursor;
                    yych = parser.buffer.buffer[parser.cursor];
                    {gotoPoint = 63; break gotoNext;}
                }
            break re2jgetout;}
        }

}
               case Scalar:
                   q = new QuotedString();
                   q.str[0] = 0;
               case Scalar2: {
                   tok = parser.cursor;


        int gotoPoint = -1;
        byte yych = (byte) 0;
        re2jgetout: while(true) {
            gotoNext: while(true) {
                switch(gotoPoint) {
                case -1: 
                    if ((parser.limit - parser.cursor) < 3) parser.read();
                    yych = parser.buffer.buffer[parser.cursor];
                    switch (yych) {
                    case 0x00:    {gotoPoint = 74; break gotoNext;}
                    case '\n':    {gotoPoint = 70; break gotoNext;}
                    case '\r':    {gotoPoint = 72; break gotoNext;}
                    default:    {gotoPoint = 76; break gotoNext;}
                    }
                case 70:
                    ++parser.cursor;
                    switch ((yych = parser.buffer.buffer[parser.cursor])) {
                    case 'C':    {gotoPoint = 78; break gotoNext;}
                    case 'N':    {gotoPoint = 80; break gotoNext;}
                    case 'Z':    {gotoPoint = 83; break gotoNext;}
                    default:    {gotoPoint = 71; break gotoNext;}
                    }
                case 71:
                    {   parser.cursor = tok;
            mainLoopGoto = ScalarEnd; break gotoSomething;
        }
                case 72:
                    ++parser.cursor;
                    switch ((yych = parser.buffer.buffer[parser.cursor])) {
                    case '\n':    {gotoPoint = 77; break gotoNext;}
                    default:    {gotoPoint = 73; break gotoNext;}
                    }
                case 73:
                    {   q.cat(parser.buffer.buffer[tok]);
            mainLoopGoto = Scalar2; break gotoSomething;
        }
                case 74:
                    ++parser.cursor;
                    {   parser.cursor = tok;
            mainLoopGoto = ScalarEnd; break gotoSomething;
        }
                case 76:
                    yych = parser.buffer.buffer[++parser.cursor];
                    {gotoPoint = 73; break gotoNext;}
                case 77:
                    yych = parser.buffer.buffer[++parser.cursor];
                    switch (yych) {
                    case 'C':    {gotoPoint = 78; break gotoNext;}
                    case 'N':    {gotoPoint = 80; break gotoNext;}
                    case 'Z':    {gotoPoint = 83; break gotoNext;}
                    default:    {gotoPoint = 71; break gotoNext;}
                    }
                case 78:
                    ++parser.cursor;
                    {   CHK_NL(tok+1);
            mainLoopGoto = Scalar2; break gotoSomething; }
                case 80:
                    ++parser.cursor;
                    if (parser.limit <= parser.cursor) parser.read();
                    yych = parser.buffer.buffer[parser.cursor];
                    switch (yych) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':    {gotoPoint = 80; break gotoNext;}
                    default:    {gotoPoint = 82; break gotoNext;}
                    }
                case 82:
                    {   CHK_NL(tok+1);
            if(tok + 2 < parser.cursor) {
                int count = tok + 2;
                int total = Integer.valueOf(new String(parser.buffer.buffer, tok + 2, parser.cursor - (tok + 2)), 10).intValue();
                for(int i=0; i<total; i++) {
                    q.cat('\n');
                }
            } else {
                q.cat('\n');
            }
            mainLoopGoto = Scalar2; break gotoSomething;
        }
                case 83:
                    ++parser.cursor;
                    {   CHK_NL(tok+1);
            q.cat((byte)0);
            mainLoopGoto = Scalar2; break gotoSomething;
        }
                }
            break re2jgetout;}
        }

}
               case ScalarEnd: {
                   Node n = Node.allocStr();
                   Data.Str dd = (Data.Str)n.data;
                   dd.ptr = Pointer.create(q.str, 0);
                   dd.len = q.idx;
                   lval = n;
                   parser.popLevel();
                   if(parser.implicit_typing) {
                       ImplicitScanner.tryTagImplicit(n, parser.taguri_expansion);
                   }          
                   return YAML_PLAIN;
               }
               }
           }
       } while(true);
   }

   private String getInline() throws IOException {
       String str = "";
       int tok = -1;
       
       while(true) {
           tok = parser.cursor;

        int gotoPoint = -1;
        byte yych = (byte) 0;
        re2jgetout: while(true) {
            gotoNext: while(true) {
                switch(gotoPoint) {
                case -1: 
                    if ((parser.limit - parser.cursor) < 2) parser.read();
                    yych = parser.buffer.buffer[parser.cursor];
                    switch (yych) {
                    case 0x00:    {gotoPoint = 91; break gotoNext;}
                    case '\n':    {gotoPoint = 87; break gotoNext;}
                    case '\r':    {gotoPoint = 89; break gotoNext;}
                    default:    {gotoPoint = 93; break gotoNext;}
                    }
                case 87:
                    ++parser.cursor;
                case 88:
                    {   CHK_NL(parser.cursor);
                return str; }
                case 89:
                    ++parser.cursor;
                    switch ((yych = parser.buffer.buffer[parser.cursor])) {
                    case '\n':    {gotoPoint = 94; break gotoNext;}
                    default:    {gotoPoint = 90; break gotoNext;}
                    }
                case 90:
                    {   
                str = str + (char)parser.buffer.buffer[tok];
            }
                case 91:
                    ++parser.cursor;
                    {   parser.cursor = tok;
                return str;
            }
                case 93:
                    yych = parser.buffer.buffer[++parser.cursor];
                    {gotoPoint = 90; break gotoNext;}
                case 94:
                    ++parser.cursor;
                    yych = parser.buffer.buffer[parser.cursor];
                    {gotoPoint = 88; break gotoNext;}
                }
            break re2jgetout;}
        }

       }
   }
}
