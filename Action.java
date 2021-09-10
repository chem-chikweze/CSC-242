public class Action {
    int location;
    String player;

    public Action(int loc, String playerInput) {
        this.location = loc;
        this.player = playerInput;
    }

    public String actionGetPlayer(){
        return player;
    }
    
    public int actionGetLocation(){
        return location;
    }

    public void actionSetPlayer(String playerInput ) {
        this.player = playerInput;
    }

    public void actionSetLocation(int location ) {
        this.location = location;
    }
}
