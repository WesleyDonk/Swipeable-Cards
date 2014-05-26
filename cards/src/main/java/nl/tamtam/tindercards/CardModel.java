package nl.tamtam.tindercards;

/**
 * Created by wesleyd on 23/05/14.
 */
public class CardModel {

    private int mCardImageResource;

    public CardModel() {

        this.mCardImageResource = -1;
    }

    public CardModel(int cardImageResource) {

        this.mCardImageResource = cardImageResource;
    }

    public int getCardImageResource() {

        return this.mCardImageResource;
    }
}