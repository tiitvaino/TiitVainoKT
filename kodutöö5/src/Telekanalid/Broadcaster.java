package Telekanalid;

import java.util.HashSet;
import java.util.Set;

public class Broadcaster {
    private String broadcasterName;
    private Set<BroadcastListener> broadcastListenerSet = new HashSet<>();

    public Broadcaster(String broadcasterName) {
        this.broadcasterName = broadcasterName;
    }

    public void subscribe(BroadcastListener broadcastListener){
        broadcastListenerSet.add(broadcastListener);
    }

    public void broadcast(String news){
        for (BroadcastListener elem :
                broadcastListenerSet) {
            elem.listen(broadcasterName + " : " + news);
        }

    }
}
