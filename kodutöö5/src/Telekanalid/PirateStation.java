package Telekanalid;

public class PirateStation extends  Broadcaster implements BroadcastListener {
    public PirateStation(String broadcasterName) {
        super(broadcasterName);
    }

    @Override
    public void listen(String newsX) {
        super.broadcast(newsX);
    }
}
