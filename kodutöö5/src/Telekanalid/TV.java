package Telekanalid;

public class TV implements BroadcastListener {
    private String owner;

    public TV(String owner) {
        this.owner = owner;
    }

    @Override
    public void listen(String m) {
        System.out.println(owner + " : " + m);
    }
}
