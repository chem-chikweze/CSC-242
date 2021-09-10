public class Tile{
    int mark;
    int location;

    public Tile(int loc) {
        this.mark = -1;
        this.location = loc;
    }

    public Tile(Tile tile) {
        this.mark = tile.mark;
        this.location = tile.location;
    }

    public boolean tileIsTileMarked() {
        if(this.mark == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void tileSetMark(int n){
        mark = n;
    }

    public void tileResetMark() {
        mark = -1;
    }

    public int tileGetLocation(){
        return this.location;
    }

    public int tileGetMark() {
        return this.mark;
    }

}