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
        int index = (int)(Math.random() * ((14) + 1));
        File x = new File(imageList[index]);
        return x;
    }

    public boolean catChance() {
        int num = (int)(Math.random() * ((1000) + 1));

        if (num > 990) {
            return true;
        }
        else return false;
    }

    public File getCat() {
        return new File(catLocation);
    }

//    public int getCatCount() {
//        return this.catCount;
//    }
//
//    public void increaseCat() {
//        this.catCount += 1;
//    }

}
