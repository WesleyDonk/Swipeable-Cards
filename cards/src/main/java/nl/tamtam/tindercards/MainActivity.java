package nl.tamtam.tindercards;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private CardContainer mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainer = (CardContainer) findViewById(R.id.container);

        CardModel model1 = new CardModel(R.drawable.image);
        CardView cardView1 = new CardView(mContainer, model1);
        mContainer.addView(cardView1);

        CardModel model2 = new CardModel(R.drawable.image);
        CardView cardView2 = new CardView(mContainer, model2);
        mContainer.addView(cardView2);
    }
}
