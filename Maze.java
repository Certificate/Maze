/* Tämä harjoitus on tehty OS X käyttöjärjestelmällä käyttäen Sublime Text 3-sovellusta.
 * Tämänkin huvin teille tarjosi Valtteri Vuori
 * vuori.valtteri.j@student.uta.fi
 */

 
public class Maze{
   // Pelin vakiomerkit. Melko itseään selittävät =)
   private static char SUUNTA = 'V';
   private static final char REITTI = 'o';

   // Y- ja X-akselin nykysijainnit.
   private static int NYKY1 = 0; // Y-Akseli
   private static int NYKY2 = 1; // X-Akseli
   private static int CHECK = 0; // Apumuuttuja lopetukseen


   public static void main(String[] args){

      // Tulostetaan pelin nimi näytölle.
      System.out.println("-----------");
      System.out.println("| M A Z E |");
      System.out.println("-----------");

      // Komentoriviparametrit
      String kRivi1 = args[0];
      String kRivi2 = args[1];
      String kRivi3 = args[2];

      // Virheiden filtteröintiä.
      try {

         int komento = Integer.parseInt(kRivi1);
         int rivi = Integer.parseInt(kRivi2);
         int sarake = Integer.parseInt(kRivi3);
         Generator.initialise (komento);
         char[][] taulukko = Generator.generate(rivi, sarake);
         taulukko[0][1] = 'V';
         tulosta(taulukko);
         
         // Varsinainen silmukka, jolla suoritetaan peliä.
         while(CHECK == 0){
            System.out.println("m/move, s/solve, q/quit?");
            char vastaus = In.readChar();
            if(vastaus == 'm'){
               mSolve(taulukko, rivi, sarake);
            } else if (vastaus == 's'){
               sSolve(taulukko, rivi, sarake);
            } else if (vastaus == 'q'){
               byebye();
            } else{
               tulosta(taulukko);
            }
         
         }
      // Jos ilmenee virhe komentoriviparametrien kanssa, tulostetaan siitä
      // tieto käyttäjälle ja lopetetaan.
      } catch (NullPointerException e) {
         System.out.println("Invalid command-line argument!");
         byebye();
      }
   }
   
   // 'm'-komennolla suoritettava ratkaisuoperaatio. Toiminnaltaan lähes samankaltaisia, pienillä eroilla
   // (mm. tulostaminen m-version kohdissa.)
   public static void mSolve(char[][] taulukko, int rivi, int sarake){

      // Riippuen nykyisestä suunnasta, operaatio määrittää eri suunnat eri tavalla. 
      // Jokaiselle mahdolliselle suunnalle (neliakselilla) on määritelty menosuunnat.
      // Jokainen suunta on identtinen toimintatavaltaan, vain merkit sekä X&Y-akselien siirrot eroavat.

      if(SUUNTA == 'A'){
         if(taulukko[NYKY1][NYKY2+1] != '.'){
            moveRight(taulukko);
            tulosta(taulukko);
            } 
            else if(
            taulukko[NYKY1-1][NYKY2] != '.'){
            moveUp(taulukko);
            tulosta(taulukko);
            } 
            else if(
            taulukko[NYKY1][NYKY2-1] != '.'){
            moveLeft(taulukko);
            tulosta(taulukko);
            } 
            else if(
            taulukko[NYKY1+1][NYKY2] != '.'){
            taulukko[NYKY1][NYKY2] = 'V';
            tulosta(taulukko);
            // Umpikujaan joutuessaan hieman erilainen tarkastus, sekä paikallaan kääntyminen. 
            if(taulukko[NYKY1-1][NYKY2]=='.'&&
               taulukko[NYKY1][NYKY2-1]=='.'&&
               taulukko[NYKY1][NYKY2+1]=='.'){
               System.out.println("m/move, s/solve, q/quit?");
               char vastaus = In.readChar();
                  if(vastaus == 's'){
                     sSolve(taulukko, rivi, sarake);
                     CHECK = 1;
                  }
                  if(vastaus == 'm'){
                     moveDown(taulukko);
                     tulosta(taulukko);
                  }
                  if(vastaus == 'q'){
                     byebye();
                     CHECK = 1;
                  }
               }
            }
         }
  
      else if(SUUNTA == '>'){
         if(taulukko[NYKY1+1][NYKY2] != '.'){
            moveRight(taulukko);
            tulosta(taulukko);
            }

         else if(taulukko[NYKY1][NYKY2+1] != '.'){
            moveUp(taulukko);
            tulosta(taulukko);
            }

         else if(taulukko[NYKY1-1][NYKY2] != '.'){
            moveLeft(taulukko);
            tulosta(taulukko);
            }

         else if(taulukko[NYKY1][NYKY2-1] != '.'){
            taulukko[NYKY1][NYKY2] = '<';
            tulosta(taulukko);
            if(taulukko[NYKY1+1][NYKY2]=='.'&&
               taulukko[NYKY1-1][NYKY2-1]=='.'&&
               taulukko[NYKY1][NYKY2+1]=='.'){
               System.out.println("m/move, s/solve, q/quit?");
               char vastaus = In.readChar();
                  if(vastaus == 's'){
                     sSolve(taulukko, rivi, sarake);
                     CHECK = 2;
                  }
                  if(vastaus == 'm'){
                     moveDown(taulukko);
                     tulosta(taulukko);
                  }
                  if(vastaus == 'q'){
                     byebye();
                     CHECK = 1;
                  }
               }
            }
         }
      
      else if(SUUNTA == 'V'){
      
         if(taulukko[NYKY1][NYKY2-1] != '.'){
            moveRight(taulukko);
            tulosta(taulukko);
            }

         else if(taulukko[NYKY1+1][NYKY2] != '.'){
            moveUp(taulukko);
            tulosta(taulukko);
            }

         else if(taulukko[NYKY1][NYKY2+1] != '.'){
            moveLeft(taulukko);
            tulosta(taulukko);
            }

         else if(taulukko[NYKY1-1][NYKY2] != '.'){
            taulukko[NYKY1][NYKY2] = 'A';
            tulosta(taulukko);
            if(taulukko[NYKY1+1][NYKY2]=='.'&&
               taulukko[NYKY1][NYKY2-1]=='.'&&
               taulukko[NYKY1][NYKY2+1]=='.'){
               System.out.println("m/move, s/solve, q/quit?");
               char vastaus = In.readChar();
                  if(vastaus == 's'){
                     sSolve(taulukko, rivi, sarake);
                     CHECK = 2;
                  }
                  if(vastaus == 'm'){
                     moveDown(taulukko);
                     tulosta(taulukko);
                  }
                  if(vastaus == 'q'){
                     byebye();
                     CHECK = 1;
                  }
               }
            }
         }

      else if(SUUNTA == '<'){
         if(taulukko[NYKY1-1][NYKY2] != '.'){
            moveRight(taulukko);
            tulosta(taulukko);
            }

         else if(taulukko[NYKY1][NYKY2-1] != '.'){
            moveUp(taulukko);
            tulosta(taulukko);
            }

         else if(taulukko[NYKY1+1][NYKY2] != '.'){
            moveLeft(taulukko);
            tulosta(taulukko);
            }

         else if(taulukko[NYKY1][NYKY2+1] != '.'){
            taulukko[NYKY1][NYKY2] = '>';
            tulosta(taulukko);
            if(taulukko[NYKY1+1][NYKY2]=='.'&&
               taulukko[NYKY1-1][NYKY2-1]=='.'&&
               taulukko[NYKY1][NYKY2-1]=='.'){
               System.out.println("m/move, s/solve, q/quit?");
               char vastaus = In.readChar();
                  if(vastaus == 's'){
                     sSolve(taulukko, rivi, sarake);
                     CHECK = 1;
                  }
                  if(vastaus == 'm'){
                     moveDown(taulukko);
                     tulosta(taulukko);
                  }
                  if(vastaus == 'q'){
                     byebye();
                     CHECK = 1;
                  }
               }
            }
         }

      if(taulukko[rivi-1][sarake-2]=='V'){
         byebye();
         }

   }
   // 's'-komennolla suoritettava ratkaisuoperaatio.
   public static void sSolve(char[][] taulukko, int rivi, int sarake){
      int u = 1;
      int z = 1;
      while(z == 1){
         // Koska siirrot tapahtuvat kaksi ruutua kerralla periaatteessa, on oikealle käännyttäessä maaliin ongelma. 
         // Tämä suorittaa ohjelman loppuun asti kunnolla.
         // Mukana myös apumuuttujat u & z.
         if(taulukko[rivi-2][sarake-3] == '>'){
            taulukko[rivi-2][sarake-3] = REITTI;
            taulukko[rivi-2][sarake-2] = REITTI;
            taulukko[rivi-1][sarake-2] = 'V';
            u = 0;
            z = 0;
            }

         // Jos taulukko loppuu normaalisti, eikä oikealla käännöksellä (ks. ylempi kommentti ^)
         if(taulukko[rivi-1][sarake-2] == SUUNTA){
            z = 0;
            }

         if(SUUNTA == 'A'&& z == 1 && u != 2){
            if(taulukko[NYKY1][NYKY2+1] != '.'){
               moveRight(taulukko);
               }
            else if(taulukko[NYKY1-1][NYKY2] != '.'){
               moveUp(taulukko);
               }

            else if(taulukko[NYKY1][NYKY2-1] != '.'){
               moveLeft(taulukko);
               }

            else if(taulukko[NYKY1+1][NYKY2] != '.'){
               taulukko[NYKY1][NYKY2] = 'V';
               moveDown(taulukko);
               }
            }
    
         if(SUUNTA == '>'&& z == 1 && u != 2){
           
            if(taulukko[NYKY1+1][NYKY2] != '.'){
               moveRight(taulukko);
               }

            else if(taulukko[NYKY1][NYKY2+1] != '.'){
               moveUp(taulukko);
               }

            else if(taulukko[NYKY1-1][NYKY2] != '.'){
               moveLeft(taulukko);
               }

            else if(taulukko[NYKY1][NYKY2-1] != '.'){
               taulukko[NYKY1][NYKY2] = '<';
               moveDown(taulukko);
               }
             }
         
         if(SUUNTA == 'V'&& z == 1 && u != 2){
            
            if(taulukko[NYKY1][NYKY2-1] != '.'){
               
               moveRight(taulukko);
               }

            else if(taulukko[NYKY1+1][NYKY2] != '.'){
               moveUp(taulukko);
               }

            else if(taulukko[NYKY1][NYKY2+1] != '.'){
               
               moveLeft(taulukko);
               }

            else if(taulukko[NYKY1-1][NYKY2] != '.'){
               
               taulukko[NYKY1][NYKY2] = 'A';
               moveDown(taulukko);
               }
             }

         if(SUUNTA == '<'&& z == 1 && u != 2){
            
            if(taulukko[NYKY1-1][NYKY2] != '.'){
               moveRight(taulukko);
               }

            else if(taulukko[NYKY1][NYKY2-1] != '.'){
               moveUp(taulukko);
               }

            else if(taulukko[NYKY1+1][NYKY2] != '.'){
               moveLeft(taulukko);
               }

            else if(taulukko[NYKY1][NYKY2+1] != '.'){
               taulukko[NYKY1][NYKY2] = '>';
               moveDown(taulukko);
               }
            }
         } 
      // Automaattisen ratkaisun tultua loppuun, tulostetaan taulukko ja hyvästellään
      tulosta(taulukko);
      // Jos lopetusta (CHECK = 1) ei ole vielä käynnistetty, käynnistää tämä sen.
      if(CHECK == 0)  
         byebye();

   }

   /*
    *    Seuraavat 4 operaatioa ovat liikkumista. Samanlainen toimivuus kuin sSolve & mSolve operaatioissa
    *    jossa jokaiselle ilmansuunnalle on määritelty omat ehtonsa. Jos ohjelma pääsee moveRight-operaatioon
    *    asti niin merkki oikealle voikin olla 'v' jos kulkusuunta on '>'.
    *
    *    Riippuen nykyisestä "madon" suunnasta, liikuttaa ohjelma sitä joko X- tai Y-akselilla.
    */
   public static void moveUp(char[][] taulukko){
         taulukko[NYKY1][NYKY2] = REITTI;
         if(SUUNTA == 'A'){
            NYKY1--;
            SUUNTA = 'A';
         } else if (SUUNTA == 'V'){
            NYKY1++;
            SUUNTA = 'V';
         } else if (SUUNTA == '>'){
            NYKY2++;
            SUUNTA = '>';
         } else if (SUUNTA == '<'){
            NYKY2--;
            SUUNTA = '<';
         }
         taulukko[NYKY1][NYKY2] = SUUNTA;
   }

   public static void moveDown(char[][] taulukko){
      taulukko[NYKY1][NYKY2] = REITTI;
      if(SUUNTA == 'A'){
         NYKY1++;
         SUUNTA = 'V';
      }
      else if (SUUNTA == 'V'){
         NYKY1--;
         SUUNTA = 'A';
      }
      else if (SUUNTA == '>'){
         NYKY2--;
         SUUNTA = '<';
      }
      else if (SUUNTA == '<'){
         NYKY2++;
         SUUNTA = '>';
      }
      taulukko[NYKY1][NYKY2] = SUUNTA;
      //tulosta(taulukko);
   }

   public static void moveLeft(char[][] taulukko){
      taulukko[NYKY1][NYKY2] = REITTI;
      if(SUUNTA == 'A'){
         NYKY2--;
         SUUNTA = '<';
      }
      else if (SUUNTA == 'V'){
         NYKY2++;
         SUUNTA = '>';
      }
      else if (SUUNTA == '>'){
         NYKY1--;
         SUUNTA = 'A';
      }
      else if (SUUNTA == '<'){
         NYKY1++;
         SUUNTA = 'V';
      }
      taulukko[NYKY1][NYKY2] = SUUNTA;
   }

   public static void moveRight(char[][] taulukko){
      taulukko[NYKY1][NYKY2] = REITTI;
      if(SUUNTA == 'A'){
         NYKY2++;
         SUUNTA = '>';
      }
      else if (SUUNTA == 'V'){
         NYKY2--;
         SUUNTA = '<';
      }
      else if (SUUNTA == '>'){
         NYKY1++;
         SUUNTA = 'V';
      }
      else if (SUUNTA == '<'){
         NYKY1--;
         SUUNTA = 'A';
      }
      taulukko[NYKY1][NYKY2] = SUUNTA;
   }

   // Taulukon tulostaminen. Simple as that.
   public static void tulosta(char[][] taulukko){
      for (int j = 0; j < taulukko.length; j++) {
         for (int i = 0; i < taulukko[0].length; i++)
            System.out.print(taulukko[j][i]);
         System.out.println();
         }
   }

   // Kun ohjelma pääsee loppuun tai käyttäjä antaa 'q'-komennon, suoritetaan tämä hyvästelyoperaatio.
   public static void byebye(){
      if (CHECK == 0){
         System.out.println("Bye, see you soon.");
         // Globaali apumuuttuja asentoon 1, jolloin ohjelma osaa lopettaa siististi.
         CHECK = 1;
      }
   }
}
