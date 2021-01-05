public class Position {
    private int x;
    private int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] Get() {
        return new int[] {this.x, this.y};
    }
}
