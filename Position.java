public class Position {
    private int x;
    private int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**simple get method similar to C# variable access modifiers @return int[x, y] */
    public int[] Get() {
        return new int[] {this.x, this.y};
    }
}
