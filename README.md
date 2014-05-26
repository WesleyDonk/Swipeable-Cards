Swipeable cards: Tinder-like cards library for Android
=================

Swipeable-cards is a native library for Android that provide a Tinder card like effect. A card can be constructed using an image and displayed with animation effects, dismiss-to-like and dismiss-to-unlike, and use different sorting mechanisms.

This is a fork of the Swipeable cards lib with some changes to it :)

The original project can be found here:
https://github.com/kikoso/Swipeable-Cards

- Animations are now done with the ViewPropertyAnimators
- Setting card width & height are done in xml and not in code. (So the potential bug with an initial setX()/setY() is now fixed)
- Like it now at the right & Dislike is at the left.
- Removed the DisOrdered type of orientation
- CardView is now a RelativeLayout in stead of a ViewGroup (So no more measuring() & onLayout())

