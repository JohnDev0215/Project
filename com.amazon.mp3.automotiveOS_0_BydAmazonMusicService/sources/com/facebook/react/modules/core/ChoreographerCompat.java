package com.facebook.react.modules.core;

import android.view.Choreographer;
import com.facebook.react.bridge.UiThreadUtil;

/* JADX INFO: loaded from: classes6.dex */
public class ChoreographerCompat {
    private static ChoreographerCompat sInstance;
    private Choreographer mChoreographer = getChoreographer();

    public static ChoreographerCompat getInstance() {
        UiThreadUtil.assertOnUiThread();
        if (sInstance == null) {
            sInstance = new ChoreographerCompat();
        }
        return sInstance;
    }

    private ChoreographerCompat() {
    }

    public void postFrameCallback(FrameCallback frameCallback) {
        choreographerPostFrameCallback(frameCallback.getFrameCallback());
    }

    public void removeFrameCallback(FrameCallback frameCallback) {
        choreographerRemoveFrameCallback(frameCallback.getFrameCallback());
    }

    private Choreographer getChoreographer() {
        return Choreographer.getInstance();
    }

    private void choreographerPostFrameCallback(Choreographer.FrameCallback frameCallback) {
        this.mChoreographer.postFrameCallback(frameCallback);
    }

    private void choreographerRemoveFrameCallback(Choreographer.FrameCallback frameCallback) {
        this.mChoreographer.removeFrameCallback(frameCallback);
    }

    public static abstract class FrameCallback {
        private Choreographer.FrameCallback mFrameCallback;

        public abstract void doFrame(long j);

        Choreographer.FrameCallback getFrameCallback() {
            if (this.mFrameCallback == null) {
                this.mFrameCallback = new Choreographer.FrameCallback() { // from class: com.facebook.react.modules.core.ChoreographerCompat.FrameCallback.1
                    @Override // android.view.Choreographer.FrameCallback
                    public void doFrame(long j) {
                        FrameCallback.this.doFrame(j);
                    }
                };
            }
            return this.mFrameCallback;
        }
    }
}
