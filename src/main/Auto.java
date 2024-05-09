package main;

public class Auto {

    /* ADATTAGOK */
    private static int objektumDb = 0; //osztály adattagja, lehet itt inicializálni

    /* objektum adattagokat a konstruktor inicializál: */
    private boolean uzemanyag; //példány v. másnéven az objektum adattagja
    private boolean beinditva; //példány v. másnéven az objektum adattagja
    private int potkerek;
    private boolean defekt;

    /* TAGFÜGGVÉNYEK */
 /* kstr hívási lánc: túlterhelt kstr hívja a másik kstr-t */
    public Auto() {
        /* semmi nem lehet a kstr. hívás előtt, mert nem fordul le! */
        //int i = 7;

        /* kstr csak kstr-ból hívunk, this kulcsszóval, nem a nevével */
        this(true, false);
    }

    public Auto(boolean beinditva) {
        this(false, beinditva);
    }

    public Auto(boolean uzemanyag, boolean beinditva) {
        Auto.objektumDb++;
        this.potkerek = 1;
        this.defekt = false;
        System.out.println("*********************************");
        System.out.printf("********** %d. bemutató **********\n".formatted(Auto.objektumDb));
        this.uzemanyag = uzemanyag;
        this.beinditva = beinditva;
        final String VAN = "✔ (van)";
        final String NINCS = "❌ (nincs)";
        String info = uzemanyag ? VAN : NINCS;
        System.out.println("üzemanyag: " + info);
        info = beinditva ? VAN : NINCS;
        System.out.println("beindítva: " + info);
        System.out.println("Pót kerekek száma: " + potkerek);
        System.out.println("-----------------");
    }

    public void setBeinditva(boolean beinditva) {
        /* lehetne további ellenőrzés, pl.:
        csak akkor lehet beindítani, ha van üzemanyag
         */
        this.beinditva = beinditva;
        if (beinditva) {
            System.out.println("A motor be van indítva.");
        } else {
            System.out.println("A motor le van állítva.");
        }
    }

    //setUzemanyag(false)
    public void megy() {
        double esely = Math.random();
        if (beinditva && uzemanyag && !defekt) {
            if (esely > 0.1 && esely < 0.3) {
                this.defekt = true;
                kilyukat();
                setBeinditva(false);
                kerekCsere();
                setBeinditva(true);
                if (uzemanyag) {
                    System.out.println("Haladtunk, az autó megérkezett,de üres a tank.");
                } else {
                    System.out.println("Haladtunk, az autó megérkezett");
                }
            } else {
                this.uzemanyag = false;
                System.out.println("Haladtunk, az autó megérkezett, de üres a tank.");
            }
        } else if (!beinditva) {
            System.out.println("Nem haladtunk, az autó nincs beindítva.");
        } else if (!uzemanyag) {
            System.out.println("Nem haladtunk, mert üres a tank.");
        } else if (defekt) {
            System.out.println("Nem haladtunk, mert defekt van.");
        }
    }

    //setUzemanyag(true)
    public void tankol() {
        if (!beinditva) {
            this.uzemanyag = true;
            System.out.println("Sikerült tankolni, tele a tank.");
        } else {
            System.out.println("Sikertelen tankolás, mert be van indítva a motor.");
        }
    }

    public void kilyukat() {
        if (defekt) {
            System.out.println("************************");
            System.out.println("Defektet kapot az autó,kikel cserélni a kereket!");
            System.out.println("************************");
        }
    }

    public void kerekCsere() {
        if (!defekt) {
            System.out.println("============================");
            System.out.println("Kilett cserélve a kerék mehetünk tövább!");
            System.out.println("============================");
        } else if (defekt && potkerek > 0) {
            potkerek = potkerek--;
            defekt = false;
            System.out.println("============================");
            System.out.println("Kilett cserélve a defektes kerék mehetünk tövább!");
            System.out.println("============================");
        } else if (defekt && potkerek == 0) {
            System.out.println("============================");
            System.out.println("Nincs potkerék!\nNem lehet kicserélni a kereket!");
            System.out.println("============================");
        }
    }
}
