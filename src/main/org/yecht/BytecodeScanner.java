/* Generated by re2j 0.13.6.dev on Fri Jul 24 22:24:36 2009 */
package org.yecht;

import java.io.IOException;

// Equivalent to bytecode.re
public class BytecodeScanner implements DefaultYAMLParser.yyInput {
   public final static int QUOTELEN = 128;
   private Parser parser;

   private Object lval;
   private int currentToken = -1;

   public BytecodeScanner(Parser parser) {
     this.parser = parser;
   }

   public Object value() {
     return lval;
   }

   public int token() {
     return currentToken;
   }

   public boolean advance() throws java.io.IOException {
     currentToken = real_yylex();
     return currentToken == 0 ? false : true;
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
        gotoNext: while(gotoPoint != -2) {
            int currentGoto = gotoPoint; gotoPoint = -2;
            switch(currentGoto) {
            case -1: 

                if ((parser.limit - parser.cursor) < 3) parser.read();
                yych = parser.buffer.buffer[parser.cursor];
                switch (yych) {
                case 0x00:    {gotoPoint = 2; continue gotoNext;}
                case 'D':    {gotoPoint = 3; continue gotoNext;}
                default:    {gotoPoint = 5; continue gotoNext;}
                }
            case 2:
                parser.cursor = parser.marker;
                {gotoPoint = 4; continue gotoNext;}
            case 3:
                yych = parser.buffer.buffer[(parser.marker = ++parser.cursor)];
                switch (yych) {
                case '\n':    {gotoPoint = 6; continue gotoNext;}
                case '\r':    {gotoPoint = 8; continue gotoNext;}
                default:    {gotoPoint = 4; continue gotoNext;}
                }
            case 4:
                {   YYPOS(0);
            mainLoopGoto = Document;
        }
            case 5:
                yych = parser.buffer.buffer[++parser.cursor];
                {gotoPoint = 4; continue gotoNext;}
            case 6:
                ++parser.cursor;
                {   if(lvl.status == LevelStatus.header) {
                CHK_NL(parser.cursor);
                mainLoopGoto = Directive;
            } else  {
                if(lvl.spaces > -1) {
                    parser.popLevel();
                    YYPOS(0);
                    return DefaultYAMLParser.YAML_IEND;
                }
                YYPOS(0);
                return 0;
            }
        }
            case 8:
                ++parser.cursor;
                switch ((yych = parser.buffer.buffer[parser.cursor])) {
                case '\n':    {gotoPoint = 6; continue gotoNext;}
                default:    {gotoPoint = 2; continue gotoNext;}
                }
            }
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
        gotoNext: while(gotoPoint != -2) {
            int currentGoto = gotoPoint; gotoPoint = -2;
            switch(currentGoto) {
            case -1: 
                if ((parser.limit - parser.cursor) < 3) parser.read();
                yych = parser.buffer.buffer[parser.cursor];
                switch (yych) {
                case 0x00:    {gotoPoint = 30; continue gotoNext;}
                case '\n':    {gotoPoint = 27; continue gotoNext;}
                case '\r':    {gotoPoint = 29; continue gotoNext;}
                case 'A':    {gotoPoint = 19; continue gotoNext;}
                case 'D':    {gotoPoint = 12; continue gotoNext;}
                case 'E':    {gotoPoint = 16; continue gotoNext;}
                case 'M':    {gotoPoint = 14; continue gotoNext;}
                case 'P':    {gotoPoint = 13; continue gotoNext;}
                case 'Q':    {gotoPoint = 15; continue gotoNext;}
                case 'R':    {gotoPoint = 21; continue gotoNext;}
                case 'S':    {gotoPoint = 17; continue gotoNext;}
                case 'T':    {gotoPoint = 23; continue gotoNext;}
                case 'c':    {gotoPoint = 25; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
                }
            case 11:
            case 12:
                yych = parser.buffer.buffer[++parser.cursor];
                switch (yych) {
                case '\n':    {gotoPoint = 41; continue gotoNext;}
                case '\r':    {gotoPoint = 44; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
                }
            case 13:
                yych = parser.buffer.buffer[++parser.cursor];
                switch (yych) {
                case '\n':    {gotoPoint = 41; continue gotoNext;}
                case '\r':    {gotoPoint = 43; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
                }
            case 14:
                yych = parser.buffer.buffer[++parser.cursor];
                switch (yych) {
                case '\n':    {gotoPoint = 38; continue gotoNext;}
                case '\r':    {gotoPoint = 40; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
                }
            case 15:
                yych = parser.buffer.buffer[++parser.cursor];
                switch (yych) {
                case '\n':    {gotoPoint = 35; continue gotoNext;}
                case '\r':    {gotoPoint = 37; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
                }
            case 16:
                yych = parser.buffer.buffer[++parser.cursor];
                switch (yych) {
                case '\n':    {gotoPoint = 32; continue gotoNext;}
                case '\r':    {gotoPoint = 34; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
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
            return DefaultYAMLParser.YAML_ANCHOR;
        }
            case 21:
                ++parser.cursor;
                {   if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.str)) {
                return '-';
            }
            lval = getInline();
            parser.popLevel();
            if( parser.buffer.buffer[parser.cursor - 1] == '\n') parser.cursor--;
            return DefaultYAMLParser.YAML_ALIAS;
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
                    return DefaultYAMLParser.YAML_ITRANSFER;
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
                return DefaultYAMLParser.YAML_TRANSFER;
            }
            lval = qstr;
            return DefaultYAMLParser.YAML_TAGURI;
        }
            case 25:
                ++parser.cursor;
                { mainLoopGoto = Comment; break gotoSomething; }
            case 27:
                ++parser.cursor;
                {   CHK_NL(parser.cursor);
            if(lvl.status == LevelStatus.seq) {
                return DefaultYAMLParser.YAML_INDENT; 
            } else if(lvl.status == LevelStatus.map) {
                if(lvl.ncount % 2 == 1) return ':';
                else                    return DefaultYAMLParser.YAML_INDENT;
            }
            mainLoopGoto = Document; break gotoSomething;
        }
            case 29:
                yych = parser.buffer.buffer[++parser.cursor];
                switch (yych) {
                case '\n':    {gotoPoint = 27; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
                }
            case 30:
                ++parser.cursor;
                {   if(lvl.spaces > -1) {
                    parser.popLevel();
                    YYPOS(0);
                    return DefaultYAMLParser.YAML_IEND;
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
                FORCE_NEXT_TOKEN(DefaultYAMLParser.YAML_INDENT);   
            } else if(lvl.status == LevelStatus.map) {
                if(lvl.ncount % 2 == 1) {
                    FORCE_NEXT_TOKEN(':');
                } else {
                    FORCE_NEXT_TOKEN(DefaultYAMLParser.YAML_INDENT);
                }
            }
            CHK_NL(parser.cursor);
            return DefaultYAMLParser.YAML_IEND;
        }
            case 34:
                yych = parser.buffer.buffer[++parser.cursor];
                switch (yych) {
                case '\n':    {gotoPoint = 32; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
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
                FORCE_NEXT_TOKEN( DefaultYAMLParser.YAML_IOPEN );
                return '?';
            }
            return DefaultYAMLParser.YAML_IOPEN;
        }
            case 37:
                yych = parser.buffer.buffer[++parser.cursor];
                switch (yych) {
                case '\n':    {gotoPoint = 35; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
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
                FORCE_NEXT_TOKEN( DefaultYAMLParser.YAML_IOPEN );
                return '?';
            }
            return DefaultYAMLParser.YAML_IOPEN;
        }
            case 40:
                yych = parser.buffer.buffer[++parser.cursor];
                switch (yych) {
                case '\n':    {gotoPoint = 38; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
                }
            case 41:
                ++parser.cursor;
                {   if(lvl.spaces > -1) {
                    parser.popLevel();
                    YYPOS(0);
                    return DefaultYAMLParser.YAML_IEND;
                }
                YYPOS(0);
                return 0;
            }
            case 43:
                yych = parser.buffer.buffer[++parser.cursor];
                switch (yych) {
                case '\n':    {gotoPoint = 41; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
                }
            case 44:
                ++parser.cursor;
                switch ((yych = parser.buffer.buffer[parser.cursor])) {
                case '\n':    {gotoPoint = 41; continue gotoNext;}
                default:    {gotoPoint = 11; continue gotoNext;}
                }
            }
        }

               }
               case Directive: {
                   parser.token = parser.cursor;


        int gotoPoint = -1;
        byte yych = (byte) 0;
        gotoNext: while(gotoPoint != -2) {
            int currentGoto = gotoPoint; gotoPoint = -2;
            switch(currentGoto) {
            case -1: 
                if ((parser.limit - parser.cursor) < 2) parser.read();
                yych = parser.buffer.buffer[parser.cursor];
                switch (yych) {
                case 0x00:    {gotoPoint = 47; continue gotoNext;}
                case 'V':    {gotoPoint = 48; continue gotoNext;}
                default:    {gotoPoint = 50; continue gotoNext;}
                }
            case 47:
                parser.cursor = parser.marker;
                {gotoPoint = 49; continue gotoNext;}
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
                case 'z':    {gotoPoint = 51; continue gotoNext;}
                default:    {gotoPoint = 49; continue gotoNext;}
                }
            case 49:
                {   parser.cursor = parser.token;
               return DefaultYAMLParser.YAML_DOCSEP;
           }
            case 50:
                yych = parser.buffer.buffer[++parser.cursor];
                {gotoPoint = 49; continue gotoNext;}
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
                case 'z':    {gotoPoint = 51; continue gotoNext;}
                case ':':    {gotoPoint = 53; continue gotoNext;}
                default:    {gotoPoint = 47; continue gotoNext;}
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
                case 'z':    {gotoPoint = 54; continue gotoNext;}
                default:    {gotoPoint = 47; continue gotoNext;}
                }
            case 54:
                ++parser.cursor;
                if ((parser.limit - parser.cursor) < 2) parser.read();
                yych = parser.buffer.buffer[parser.cursor];
                switch (yych) {
                case '\n':    {gotoPoint = 56; continue gotoNext;}
                case '\r':    {gotoPoint = 58; continue gotoNext;}
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
                case 'z':    {gotoPoint = 54; continue gotoNext;}
                default:    {gotoPoint = 47; continue gotoNext;}
                }
            case 56:
                ++parser.cursor;
                {   CHK_NL(parser.cursor);
               mainLoopGoto = Directive; break gotoSomething;
           }
            case 58:
                ++parser.cursor;
                switch ((yych = parser.buffer.buffer[parser.cursor])) {
                case '\n':    {gotoPoint = 56; continue gotoNext;}
                default:    {gotoPoint = 47; continue gotoNext;}
                }
            }
        }

}
               case Comment: {
                   parser.token = parser.cursor;


        int gotoPoint = -1;
        byte yych = (byte) 0;
        gotoNext: while(gotoPoint != -2) {
            int currentGoto = gotoPoint; gotoPoint = -2;
            switch(currentGoto) {
            case -1: 
                if ((parser.limit - parser.cursor) < 2) parser.read();
                yych = parser.buffer.buffer[parser.cursor];
                switch (yych) {
                case 0x00:    {gotoPoint = 61; continue gotoNext;}
                case '\n':    {gotoPoint = 62; continue gotoNext;}
                case '\r':    {gotoPoint = 64; continue gotoNext;}
                default:    {gotoPoint = 66; continue gotoNext;}
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
                case '\n':    {gotoPoint = 67; continue gotoNext;}
                default:    {gotoPoint = 65; continue gotoNext;}
                }
            case 65:
                {   mainLoopGoto = Comment; break gotoSomething; }
            case 66:
                yych = parser.buffer.buffer[++parser.cursor];
                {gotoPoint = 65; continue gotoNext;}
            case 67:
                ++parser.cursor;
                yych = parser.buffer.buffer[parser.cursor];
                {gotoPoint = 63; continue gotoNext;}
            }
        }

}
               case Scalar:
                   q = new QuotedString();
                   q.str[0] = 0;
               case Scalar2: {
                   tok = parser.cursor;


        int gotoPoint = -1;
        byte yych = (byte) 0;
        gotoNext: while(gotoPoint != -2) {
            int currentGoto = gotoPoint; gotoPoint = -2;
            switch(currentGoto) {
            case -1: 
                if ((parser.limit - parser.cursor) < 3) parser.read();
                yych = parser.buffer.buffer[parser.cursor];
                switch (yych) {
                case 0x00:    {gotoPoint = 74; continue gotoNext;}
                case '\n':    {gotoPoint = 70; continue gotoNext;}
                case '\r':    {gotoPoint = 72; continue gotoNext;}
                default:    {gotoPoint = 76; continue gotoNext;}
                }
            case 70:
                ++parser.cursor;
                switch ((yych = parser.buffer.buffer[parser.cursor])) {
                case 'C':    {gotoPoint = 78; continue gotoNext;}
                case 'N':    {gotoPoint = 80; continue gotoNext;}
                case 'Z':    {gotoPoint = 83; continue gotoNext;}
                default:    {gotoPoint = 71; continue gotoNext;}
                }
            case 71:
                {   parser.cursor = tok;
            mainLoopGoto = ScalarEnd; break gotoSomething;
        }
            case 72:
                ++parser.cursor;
                switch ((yych = parser.buffer.buffer[parser.cursor])) {
                case '\n':    {gotoPoint = 77; continue gotoNext;}
                default:    {gotoPoint = 73; continue gotoNext;}
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
                {gotoPoint = 73; continue gotoNext;}
            case 77:
                yych = parser.buffer.buffer[++parser.cursor];
                switch (yych) {
                case 'C':    {gotoPoint = 78; continue gotoNext;}
                case 'N':    {gotoPoint = 80; continue gotoNext;}
                case 'Z':    {gotoPoint = 83; continue gotoNext;}
                default:    {gotoPoint = 71; continue gotoNext;}
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
                case '9':    {gotoPoint = 80; continue gotoNext;}
                default:    {gotoPoint = 82; continue gotoNext;}
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
                       ImplicitScanner2.tryTagImplicit(n, parser.taguri_expansion);
                   }          
                   return DefaultYAMLParser.YAML_PLAIN;
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
        gotoNext: while(gotoPoint != -2) {
            int currentGoto = gotoPoint; gotoPoint = -2;
            switch(currentGoto) {
            case -1: 
                if ((parser.limit - parser.cursor) < 2) parser.read();
                yych = parser.buffer.buffer[parser.cursor];
                switch (yych) {
                case 0x00:    {gotoPoint = 91; continue gotoNext;}
                case '\n':    {gotoPoint = 87; continue gotoNext;}
                case '\r':    {gotoPoint = 89; continue gotoNext;}
                default:    {gotoPoint = 93; continue gotoNext;}
                }
            case 87:
                ++parser.cursor;
            case 88:
                {   CHK_NL(parser.cursor);
                return str; }
            case 89:
                ++parser.cursor;
                switch ((yych = parser.buffer.buffer[parser.cursor])) {
                case '\n':    {gotoPoint = 94; continue gotoNext;}
                default:    {gotoPoint = 90; continue gotoNext;}
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
                {gotoPoint = 90; continue gotoNext;}
            case 94:
                ++parser.cursor;
                yych = parser.buffer.buffer[parser.cursor];
                {gotoPoint = 88; continue gotoNext;}
            }
        }

       }
   }
}
