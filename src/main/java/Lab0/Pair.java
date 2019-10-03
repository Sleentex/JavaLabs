package Lab0;

public class Pair {
    public  Integer first;
    public  Integer second;

    public Pair() {
    }

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Pair pair = (Pair)obj;
        return first.equals(pair.first) && second.equals(pair.second);
    }

    @Override
    public String toString() { return first.toString() + " " + second.toString(); }
}
