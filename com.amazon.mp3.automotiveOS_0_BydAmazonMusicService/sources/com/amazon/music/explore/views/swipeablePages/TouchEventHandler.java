package com.amazon.music.explore.views.swipeablePages;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class TouchEventHandler implements View.OnTouchListener {
    private GestureDetector gestureDetector;
    private boolean wasFlingedUp;
    private boolean wasSingleTapped;

    public void onSwipeDown() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeRight() {
    }

    public void onSwipeUp() {
    }

    public TouchEventHandler(Context context) {
        this.gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            reset();
        }
        return this.gestureDetector.onTouchEvent(motionEvent);
    }

    public boolean wasSingleTapped() {
        return this.wasSingleTapped;
    }

    public boolean wasFlingedUp() {
        return this.wasFlingedUp;
    }

    private void reset() {
        this.wasSingleTapped = false;
        this.wasFlingedUp = false;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        private GestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            TouchEventHandler.this.wasSingleTapped = true;
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent2.getRawY() < motionEvent.getRawY()) {
                TouchEventHandler.this.wasFlingedUp = true;
            }
            return true;
        }
    }
}
