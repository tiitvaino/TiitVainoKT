package Telekanalid;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TVStation extends Broadcaster {
    private List<String> newsList;

    public TVStation(String broadcasterName, List<String> newsList) {
        super(broadcasterName);
        this.newsList = newsList;
    }

    public  void sendNews(){
        String newsX = newsList.get(ThreadLocalRandom.current().nextInt(0, newsList.size()));
        super.broadcast(newsX);


    }
}
