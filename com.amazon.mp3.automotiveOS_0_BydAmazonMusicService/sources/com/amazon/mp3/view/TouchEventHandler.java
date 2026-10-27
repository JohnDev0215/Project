package com.amazon.mp3.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class TouchEventHandler {
    private boolean gestureCompleted;
    private GestureDetector mGestureDetector;
    private View.OnClickListener mOnClickListener;
    private OnDragEventListener mOnDragEventListener;
    private Runnable mOnLongClickListener;
    private OnSwipeEventListener mOnSwipeEventListener;
    private float posX = 0.0f;
    private float posY = 0.0f;
    private float posDiff = 0.0f;
    private DragAction dragAction = DragAction.NONE;
    private float mMinSwipeDetectionX = 0.0f;
    private float mMinSwipeDetectionY = 0.0f;
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() { // from class: com.amazon.mp3.view.TouchEventHandler.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            if (TouchEventHandler.this.mGestureDetector != null && TouchEventHandler.this.mGestureDetector.onTouchEvent(motionEvent)) {
                if (TouchEventHandler.this.mOnClickListener != null) {
                    TouchEventHandler.this.mOnClickListener.onClick(view);
                }
                return false;
            }
            int action = motionEvent.getAction();
            if (action == 0) {
                TouchEventHandler.this.posX = motionEvent.getRawX();
                TouchEventHandler.this.posY = motionEvent.getRawY();
                if (TouchEventHandler.this.mOnDragEventListener != null) {
                    TouchEventHandler.this.mOnDragEventListener.onStartDrag();
                }
                TouchEventHandler.this.gestureCompleted = false;
            } else if (action == 1) {
                TouchEventHandler touchEventHandler = TouchEventHandler.this;
                touchEventHandler.onSwipe(touchEventHandler.dragAction);
            } else if (action == 2) {
                float rawX = motionEvent.getRawX() - TouchEventHandler.this.posX;
                float rawY = motionEvent.getRawY() - TouchEventHandler.this.posY;
                if (Math.abs(rawY) > Math.abs(rawX)) {
                    if (rawY < 0.0f) {
                        TouchEventHandler.this.dragAction = DragAction.UP;
                    } else {
                        TouchEventHandler.this.dragAction = DragAction.DOWN;
                    }
                    TouchEventHandler.this.posDiff = Math.abs(rawY);
                } else {
                    if (rawX < 0.0f) {
                        TouchEventHandler.this.dragAction = DragAction.LEFT;
                    } else {
                        TouchEventHandler.this.dragAction = DragAction.RIGHT;
                    }
                    TouchEventHandler.this.posDiff = Math.abs(rawX);
                }
                TouchEventHandler touchEventHandler2 = TouchEventHandler.this;
                touchEventHandler2.onDrag(touchEventHandler2.dragAction, rawX, rawY);
            }
            return true;
        }
    };

    private enum DragAction {
        RIGHT,
        LEFT,
        UP,
        DOWN,
        NONE
    }

    public View.OnTouchListener getTouchEventListener() {
        return this.mOnTouchListener;
    }

    public void setOnSwipeEventListener(final OnSwipeEventListener listener) {
        this.mOnSwipeEventListener = listener;
    }

    public void setOnDragEventListener(final OnDragEventListener listener) {
        this.mOnDragEventListener = listener;
    }

    public void setOnLongClickListener(final Runnable runnable) {
        this.mOnLongClickListener = runnable;
    }

    public void setOnClickListener(final Context context, final View.OnClickListener listener) {
        this.mOnClickListener = listener;
        this.mGestureDetector = new GestureDetector(context, new SingleTap());
    }

    public void setMinSwipeDetectionX(final float minSwipeDetectionX) {
        this.mMinSwipeDetectionX = minSwipeDetectionX;
    }

    public void setMinSwipeDetectionY(final float minSwipeDetectionY) {
        this.mMinSwipeDetectionY = minSwipeDetectionY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDrag(final DragAction action, final float x, final float y) {
        if (this.mOnDragEventListener == null) {
            return;
        }
        int i = AnonymousClass2.$SwitchMap$com$amazon$mp3$view$TouchEventHandler$DragAction[action.ordinal()];
        if (i == 1) {
            this.mOnDragEventListener.onDragRight(x);
            return;
        }
        if (i == 2) {
            this.mOnDragEventListener.onDragLeft(x);
        } else if (i == 3) {
            this.mOnDragEventListener.onDragDown(y);
        } else {
            if (i != 4) {
                return;
            }
            this.mOnDragEventListener.onDragUp(y);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.view.TouchEventHandler$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$view$TouchEventHandler$DragAction;

        static {
            int[] iArr = new int[DragAction.values().length];
            $SwitchMap$com$amazon$mp3$view$TouchEventHandler$DragAction = iArr;
            try {
                iArr[DragAction.RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$view$TouchEventHandler$DragAction[DragAction.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$view$TouchEventHandler$DragAction[DragAction.DOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$view$TouchEventHandler$DragAction[DragAction.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$view$TouchEventHandler$DragAction[DragAction.NONE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSwipe(final DragAction action) {
        if (this.mOnSwipeEventListener == null || this.gestureCompleted) {
            this.dragAction = DragAction.NONE;
            return;
        }
        int i = AnonymousClass2.$SwitchMap$com$amazon$mp3$view$TouchEventHandler$DragAction[action.ordinal()];
        if (i == 1) {
            float f = this.posDiff;
            if (f >= this.mMinSwipeDetectionX) {
                this.mOnSwipeEventListener.onSwipeRight(f);
            }
        } else if (i == 2) {
            float f2 = this.posDiff;
            if (f2 >= this.mMinSwipeDetectionX) {
                this.mOnSwipeEventListener.onSwipeLeft(f2);
            }
        } else if (i == 3) {
            float f3 = this.posDiff;
            if (f3 >= this.mMinSwipeDetectionY) {
                this.mOnSwipeEventListener.onSwipeDown(f3);
            }
        } else if (i == 4) {
            float f4 = this.posDiff;
            if (f4 >= this.mMinSwipeDetectionY) {
                this.mOnSwipeEventListener.onSwipeUp(f4);
            }
        }
        this.gestureCompleted = true;
        this.dragAction = DragAction.NONE;
    }

    private class SingleTap extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(final MotionEvent e) {
            return true;
        }

        private SingleTap() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(final MotionEvent e) {
            if (TouchEventHandler.this.mOnLongClickListener != null) {
                TouchEventHandler.this.mOnLongClickListener.run();
            }
        }
    }
}
