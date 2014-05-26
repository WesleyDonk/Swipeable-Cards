package nl.tamtam.tindercards;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by wesleyd on 23/05/14.
 */
public class CardContainer extends RelativeLayout {

    private ArrayList<CardView> mCardContainer = new ArrayList<CardView>();

    public CardContainer(Context context) {
        super(context);

    }

    public CardContainer(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CardContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public void addView(View child) {
        super.addView(child);

        if (child instanceof CardView) {

            mCardContainer.add((CardView) child);
        }
    }

    public void addViewOnlyLayout(View child) {
        super.addView(child);
    }

    public void displayAgain() throws CardContainerNotEmptyException {

        if (getChildCount() != 0) {

            throw new CardContainerNotEmptyException("The container is not empty");
        } else {

            for (CardView card : mCardContainer) {
                super.addView(card);

                card.translateCardToCenter();
            }
        }
    }
}