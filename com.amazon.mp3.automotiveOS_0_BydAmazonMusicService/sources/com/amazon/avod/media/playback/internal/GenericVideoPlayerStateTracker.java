package com.amazon.avod.media.playback.internal;

import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public class GenericVideoPlayerStateTracker {
    private StateChangeListener mListener;
    private final Object mStateMutex = new Object();
    private GenericVideoPlayerState mState = GenericVideoPlayerState.UNINITIALIZED;

    public interface StateChangeListener {
        void onStateChange(GenericVideoPlayerState genericVideoPlayerState, GenericVideoPlayerState genericVideoPlayerState2);
    }

    public static class AsynchronousStateChangeListenerDelegate implements StateChangeListener {
        private final StateChangeListener mDelegate;
        private final ExecutorService mExecutor;

        public AsynchronousStateChangeListenerDelegate(ExecutorService executorService, StateChangeListener stateChangeListener) {
            this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService);
            this.mDelegate = (StateChangeListener) Preconditions.checkNotNull(stateChangeListener);
        }

        @Override // com.amazon.avod.media.playback.internal.GenericVideoPlayerStateTracker.StateChangeListener
        public void onStateChange(final GenericVideoPlayerState genericVideoPlayerState, final GenericVideoPlayerState genericVideoPlayerState2) {
            this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.playback.internal.GenericVideoPlayerStateTracker.AsynchronousStateChangeListenerDelegate.1
                @Override // java.lang.Runnable
                public void run() {
                    AsynchronousStateChangeListenerDelegate.this.mDelegate.onStateChange(genericVideoPlayerState, genericVideoPlayerState2);
                }
            });
        }
    }

    public GenericVideoPlayerState getState() {
        return this.mState;
    }

    public void setListener(StateChangeListener stateChangeListener) {
        this.mListener = stateChangeListener;
    }

    public void changeState(GenericVideoPlayerState genericVideoPlayerState) {
        synchronized (this.mStateMutex) {
            GenericVideoPlayerState genericVideoPlayerState2 = this.mState;
            if (genericVideoPlayerState2 != genericVideoPlayerState) {
                if (genericVideoPlayerState2.canTransitionTo(genericVideoPlayerState)) {
                    this.mState = genericVideoPlayerState;
                    StateChangeListener stateChangeListener = this.mListener;
                    DLog.logf("Player state transition %s --> %s", genericVideoPlayerState2, genericVideoPlayerState);
                    if (stateChangeListener != null) {
                        stateChangeListener.onStateChange(genericVideoPlayerState2, genericVideoPlayerState);
                    }
                } else {
                    DLog.warnf("There is no valid transition from state %s to %s", genericVideoPlayerState2, genericVideoPlayerState);
                }
            }
        }
    }
}
