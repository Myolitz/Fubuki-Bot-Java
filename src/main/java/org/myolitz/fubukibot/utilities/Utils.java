package org.myolitz.fubukibot.utilities;

import org.myolitz.fubukibot.dicts.Lists;
import java.io.File;

public class Utils extends Lists {
    int catCount = 0;

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

    public boolean catChance() {
        int num = (int)(Math.random() * ((10000 - 1) + 1));

        return num >= 9900;
    }

    public boolean uniChance() {
        int num = (int)(Math.random() * ((100 - 1) + 1));

        return num == 100;
    }

    public File getCat(String cat) {
        switch (cat) {
            case "angry" -> {
                return new File(catLocation);
            }
            case "uni" -> {
                return new File(uniLocation);
            }
        }
        return null;
    }

    public String[] getSecrets() {
        return secrets;
    }

//    public int getCatCount() {
//        return this.catCount;
//    }
//
//    public void increaseCat() {
//        this.catCount += 1;
//    }

}
