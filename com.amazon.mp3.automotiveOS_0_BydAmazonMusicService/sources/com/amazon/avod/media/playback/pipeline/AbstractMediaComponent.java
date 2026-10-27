package com.amazon.avod.media.playback.pipeline;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public abstract class AbstractMediaComponent {
    private volatile State mCurrentState = State.UNCONFIGURED;

    public enum State {
        UNCONFIGURED,
        IDLE,
        RUNNING
    }

    protected void configure() {
        Preconditions.checkState(this.mCurrentState == State.UNCONFIGURED, appendCurrentStateToErrorMessage("Can only call configure() when in the UNCONFIGURED state!"));
        this.mCurrentState = State.IDLE;
    }

    public void start() {
        Preconditions.checkState(this.mCurrentState == State.IDLE, appendCurrentStateToErrorMessage("Can only call start() when in the IDLE state!"));
        this.mCurrentState = State.RUNNING;
    }

    public void stop() {
        Preconditions.checkState(this.mCurrentState == State.RUNNING, appendCurrentStateToErrorMessage("Can only call stop() when in the RUNNING state!"));
        this.mCurrentState = State.IDLE;
    }

    public void release() {
        Preconditions.checkState(this.mCurrentState == State.IDLE || this.mCurrentState == State.UNCONFIGURED, appendCurrentStateToErrorMessage("Can only call release() when in the IDLE/UNCONFIGURED state!"));
        this.mCurrentState = State.UNCONFIGURED;
    }

    public State getState() {
        return this.mCurrentState;
    }

    public boolean isUnconfigured() {
        return this.mCurrentState == State.UNCONFIGURED;
    }

    public boolean isRunning() {
        return this.mCurrentState == State.RUNNING;
    }

    public boolean isIdle() {
        return this.mCurrentState == State.IDLE;
    }

    @Nonnull
    private String appendCurrentStateToErrorMessage(@Nonnull String str) {
        return String.format(Locale.US, "%s current state: %s", str, this.mCurrentState);
    }
}
