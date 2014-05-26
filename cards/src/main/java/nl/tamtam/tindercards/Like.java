package nl.tamtam.tindercards;

/**
 * Created by wesleyd on 23/05/14.
 */
public enum Like {

    NONE(0),
    LIKED(2),
    DISLIKED(1);

    private final int key;

    Like(int key) {
        this.key = key;

    }

    public int getKey() {

        return this.key;
    }

    public static Like fromKey(int key) {

        for (Like type : Like.values()) {

            if (type.getKey() == key) {

                return type;
            }
        }
        return null;
    }
}
