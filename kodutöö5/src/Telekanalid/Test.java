package Telekanalid;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> pKRUnews = new ArrayList<>();
        pKRUnews.add("North-Korea is the best place in the world to live!");
        pKRUnews.add("We have the greatest economy!");
        TVStation pKRU = new TVStation("Põhja-Korea rahvuslikud uudised", pKRUnews);

        List<String> fNnews = new ArrayList<>();
        fNnews.add("North-Korea is the WORST place in the world to live!");
        fNnews.add("North-Korea has the worst economy!");
        TVStation fN = new TVStation("Fox News", fNnews);

        PirateStation pirateStation = new PirateStation("Pirate");
        pKRU.subscribe(pirateStation);
        fN.subscribe(pirateStation);

        TV kimJongUn = new TV("Kim Jong-un");
        pKRU.subscribe(kimJongUn);
        fN.subscribe(kimJongUn);

        TV kimJongNam = new TV("Kim Jong-nam");
        pKRU.subscribe(kimJongNam);

        TV pakPongJu = new TV("Pak Pong-ju");
        pirateStation.subscribe(pakPongJu);

        pKRU.sendNews();
        fN.sendNews();


    }
}
