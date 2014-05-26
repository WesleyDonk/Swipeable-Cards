package nl.tamtam.tindercards;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by wesleyd on 23/05/14.
 */
public class CardView extends RelativeLayout implements View.OnTouchListener {

    /**
     * The model of our car
     */
    public CardModel mCardModel;

    /**
     * Current status of the liked card.
     */
    private Like mLiked = Like.NONE;

    /**
     * The CardContainer to which the card is attached
     */
    public CardContainer mParentView;

    private int mLastX = 0;

    private int mLastY = 0;

    private int mCoordX;

    private int mCoordY;

    private int mScreenCenter;

    private CardDismissListener mCardDismissListener = null;

    public CardView(CardContainer parentView, CardModel cardModel) {
        super(parentView.getContext());

        this.mParentView = parentView;
        this.mCardModel = cardModel;

        initCard();
    }

    private void initCard() {

        mScreenCenter = getScreenSize().x / 2;

        LayoutInflater.from(getContext()).inflate(R.layout.card_layout, this, true);

        initViews();

        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        mCoordX = (int) event.getRawX();
        mCoordY = (int) event.getRawY();

        if (mLastX == 0 || mLastY == 0) {

            mLastX = mCoordX;
            mLastY = mCoordY;
        }

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:

                mCoordX = (int) event.getRawX();
                mCoordY = (int) event.getRawY();

                setX(getX() + (mCoordX - mLastX));
                setY(getY() + (mCoordY - mLastY));

                if (getCenterPosition() >= mScreenCenter) {

                    temporalMovementToTheRight();
                } else {

                    temporalMovementToTheLeft();
                }

                mLastX = mCoordX;
                mLastY = mCoordY;
                break;

            case MotionEvent.ACTION_UP:

                mCoordX = (int) event.getRawX();
                mCoordY = (int) event.getRawY();

                mLastX = 0;
                mLastY = 0;

                if (mLiked == Like.NONE) {

                    translateCardToCenter();
                } else if (mLiked == Like.DISLIKED) {

                    translateCardToLeft();

                    if (mCardDismissListener != null) {

                        //mCardDismissListener.onDislike(getCardModel());
                    }
                } else if (mLiked == Like.LIKED) {

                    translateCardToRight();

                    if (mCardDismissListener != null) {
                        //mCardDismissListener.onLike(getCardModel());
                    }
                }
                break;

            default:
                break;
        }
        return true;
    }

    private Point getScreenSize() {

        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        return size;
    }

    private int getCenterPosition() {

        return (int) getX() + (getWidth() / 2);
    }

    public void translateCardToLeft() {

        animate()
                .translationXBy(-1000.0F)
                .setDuration(600)
                .setInterpolator(new OvershootInterpolator())
                .setListener(mTranslateListener)
                .start();
    }

    public void translateCardToRight() {

        animate()
                .translationXBy(1000.0F)
                .setDuration(600)
                .setInterpolator(new OvershootInterpolator())
                .setListener(mTranslateListener)
                .start();
    }

    public void translateCardToCenter() {

        animate()
                .translationXBy(-getX())
                .translationYBy(-getY())
                .rotationBy(-getRotation())
                .setDuration(300)
                .setInterpolator(new OvershootInterpolator(1.0F))
                .start();
    }

    public void temporalMovementToTheRight() {

        setRotation(-(float) ((getCenterPosition() - mScreenCenter) * (Math.PI / 90)));

        if (getCenterPosition() > mScreenCenter) {

            if (getCenterPosition() > (getScreenSize().x - (mScreenCenter / 4))) {

                mLiked = Like.LIKED;
            } else {

                mLiked = Like.NONE;
            }
        } else {

            mLiked = Like.NONE;
        }
    }

    public void temporalMovementToTheLeft() {

        setRotation(-(float) ((getCenterPosition() - mScreenCenter) * (Math.PI / 90)));

        if (getCenterPosition() < (mScreenCenter)) {

            if (getCenterPosition() < mScreenCenter / 4) {

                mLiked = Like.DISLIKED;
            } else {

                mLiked = Like.NONE;
            }
        } else {

            mLiked = Like.NONE;
        }
    }

    private Animator.AnimatorListener mTranslateListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

            mParentView.removeView(CardView.this);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    public void setCardDismissListener(CardDismissListener listener) {

        this.mCardDismissListener = listener;
    }

    private void initViews() {

        
    }
}
