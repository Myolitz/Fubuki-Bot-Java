package org.myolitz.fubukibot.utilities;

import org.myolitz.fubukibot.dicts.Lists;
import java.io.File;
import java.io.FileNotFoundException;

public class Utils extends Lists {


    public String GenerateQuote()
    {
        int index = (int)(Math.random() * ((7) + 1));
        return quoteList[index];
    }

    public File GenImage() {
        int index = (int)(Math.random() * ((13 - 1) + 1));
        System.out.println(index);
        File x = new File(imageList[index]);
        return x;
    }

    public String[] getSecrets() {
        return secrets;
    }

    //Below are literally pure Cat Chances

    public boolean angyChance() {
        int num = (int)(Math.random() * ((10000 - 1) + 1));

        return num >= 7000;
    }

    public boolean uniChance() {
        int num = (int)(Math.random() * ((100 - 1) + 1));

        return num >= 80;
    }

    public boolean ponyoChance() {
        int num = (int)(Math.random() * ((100 - 1) + 1));

        return num >= 95;
    }

    public File getCat(String special) {
        if (special.equalsIgnoreCase("uni")) {
            return new File(catLocations.get("uni"));
        }
        else if (special.equalsIgnoreCase("ponyo")) {
            return new File(catLocations.get("ponyo"));
        }
        else if (special.equalsIgnoreCase("angy")) {
            return new File(catLocations.get("angy"));
        }
        //Ponyo and Uni are both 1% chances (might get raised) so they'll get checked for first
        return null;
    }

    // Special Myo-only check
    public File myoCat() {
        int num = 0;
        while (num == 0) {
            num = (int) (Math.random() * ((4 - 1) + 1));

            System.out.println(num);

            if (num == 1) {
                return new File(catLocations.get("ponyo"));
            } else if (num == 2) {
                return new File(catLocations.get("uni"));
            } else if (num == 3) {
                return new File(catLocations.get("angy"));
            }
        }
        return null;
    }
    ;

}
