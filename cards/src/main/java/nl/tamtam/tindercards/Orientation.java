package nl.tamtam.tindercards;

/**
 * Created by wesleyd on 23/05/14.
 */
public enum Orientation {

    ORDERED(0),
    DISORDERED(1);

    private final int key;

    Orientation(int key) {
        this.key = key;

    }

    public int getKey() {

        return this.key;
    }

    public static Orientation fromKey(int key) {

        for (Orientation type : Orientation.values()) {

            if (type.getKey() == key) {

                return type;
            }
        }
        return null;
    }
}
