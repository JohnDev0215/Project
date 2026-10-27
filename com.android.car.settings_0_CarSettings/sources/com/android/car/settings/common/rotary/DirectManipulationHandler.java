package com.android.car.settings.common.rotary;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.util.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public class DirectManipulationHandler implements View.OnKeyListener, View.OnGenericMotionListener {
    private static final String TAG = DirectManipulationHandler.class.getSimpleName();
    private final EventListener mBackDelegate;
    private final EventListener mCenterButtonDelegate;
    private final DirectManipulationState mDirectManipulationMode;
    private final View.OnKeyListener mNudgeDelegate;
    private final View.OnGenericMotionListener mRotationDelegate;

    public interface EventListener {
        boolean onEvent(boolean z);
    }

    public static void setDirectManipulationHandler(View view, DirectManipulationHandler handler) {
        if (view == null) {
            return;
        }
        view.setOnKeyListener(handler);
        view.setOnGenericMotionListener(handler);
    }

    public static class Builder {
        private EventListener mBackDelegate;
        private EventListener mCenterButtonDelegate;
        private final DirectManipulationState mDmState;
        private View.OnKeyListener mNudgeDelegate;
        private View.OnGenericMotionListener mRotationDelegate;

        public Builder(DirectManipulationState dmState) {
            Preconditions.checkNotNull(dmState);
            this.mDmState = dmState;
        }

        public Builder setNudgeHandler(View.OnKeyListener nudgeDelegate) {
            Preconditions.checkNotNull(nudgeDelegate);
            this.mNudgeDelegate = nudgeDelegate;
            return this;
        }

        public Builder setCenterButtonHandler(EventListener centerButtonDelegate) {
            Preconditions.checkNotNull(centerButtonDelegate);
            this.mCenterButtonDelegate = centerButtonDelegate;
            return this;
        }

        public Builder setBackHandler(EventListener backDelegate) {
            Preconditions.checkNotNull(backDelegate);
            this.mBackDelegate = backDelegate;
            return this;
        }

        public Builder setRotationHandler(View.OnGenericMotionListener rotationDelegate) {
            Preconditions.checkNotNull(rotationDelegate);
            this.mRotationDelegate = rotationDelegate;
            return this;
        }

        public DirectManipulationHandler build() {
            if (this.mNudgeDelegate == null && this.mRotationDelegate == null) {
                throw new IllegalStateException("Nudge and/or rotation delegate must be provided.");
            }
            return new DirectManipulationHandler(this.mDmState, this.mNudgeDelegate, this.mCenterButtonDelegate, this.mBackDelegate, this.mRotationDelegate);
        }
    }

    private DirectManipulationHandler(DirectManipulationState dmState, View.OnKeyListener nudgeDelegate, EventListener centerButtonDelegate, EventListener backDelegate, View.OnGenericMotionListener rotationDelegate) {
        this.mDirectManipulationMode = dmState;
        this.mNudgeDelegate = nudgeDelegate;
        this.mCenterButtonDelegate = centerButtonDelegate;
        this.mBackDelegate = backDelegate;
        this.mRotationDelegate = rotationDelegate;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        boolean isActionUp = keyEvent.getAction() == 1;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("View: ");
        sb.append(view);
        sb.append(" is handling ");
        sb.append(KeyEvent.keyCodeToString(keyCode));
        sb.append(" and action ");
        sb.append(KeyEvent.actionToString(keyEvent.getAction()));
        sb.append(" direct manipulation mode is ");
        sb.append(this.mDirectManipulationMode.isActive() ? "active" : "inactive");
        Log.d(str, sb.toString());
        boolean inDirectManipulationMode = this.mDirectManipulationMode.isActive();
        if (keyCode == 4) {
            if (inDirectManipulationMode && isActionUp) {
                this.mDirectManipulationMode.disable();
            }
            EventListener eventListener = this.mBackDelegate;
            if (eventListener == null) {
                return true;
            }
            return eventListener.onEvent(inDirectManipulationMode);
        }
        switch (keyCode) {
            case 19:
            case 20:
            case 21:
            case 22:
                if (!inDirectManipulationMode) {
                    return false;
                }
                View.OnKeyListener onKeyListener = this.mNudgeDelegate;
                if (onKeyListener == null) {
                    return true;
                }
                return onKeyListener.onKey(view, keyCode, keyEvent);
            case 23:
                if (isActionUp) {
                    if (inDirectManipulationMode) {
                        this.mDirectManipulationMode.disable();
                    } else {
                        this.mDirectManipulationMode.enable(view);
                    }
                }
                EventListener eventListener2 = this.mCenterButtonDelegate;
                if (eventListener2 == null) {
                    return true;
                }
                return eventListener2.onEvent(inDirectManipulationMode);
            default:
                return false;
        }
    }

    @Override // android.view.View.OnGenericMotionListener
    public boolean onGenericMotion(View v, MotionEvent event) {
        if (!this.mDirectManipulationMode.isActive()) {
            return false;
        }
        View.OnGenericMotionListener onGenericMotionListener = this.mRotationDelegate;
        if (onGenericMotionListener == null) {
            return true;
        }
        return onGenericMotionListener.onGenericMotion(v, event);
    }
}
