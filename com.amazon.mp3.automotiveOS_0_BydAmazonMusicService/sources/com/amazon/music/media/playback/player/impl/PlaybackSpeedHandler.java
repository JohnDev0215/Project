package com.amazon.music.media.playback.player.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.player.PositionTracker;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public abstract class PlaybackSpeedHandler<ITEM> {
    private static final long SEEK_PERIOD_MILLIS = TimeUnit.SECONDS.toMillis(1) / 2;
    private ControlSource controlSource;
    private ITEM currentItem;
    private float fastForwardSpeed = 4.0f;
    private float rewindSpeed = -4.0f;
    private PositionTracker positionTracker = new PositionTracker();
    private final Runnable updateHandler = new Runnable() { // from class: com.amazon.music.media.playback.player.impl.PlaybackSpeedHandler.1
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java.lang.Runnable
        public void run() {
            ThreadUtils.cancelOnMainThread(PlaybackSpeedHandler.this.updateHandler);
            long currentPosition = PlaybackSpeedHandler.this.positionTracker.getCurrentPosition();
            float runSpeed = PlaybackSpeedHandler.this.positionTracker.getRunSpeed();
            if (runSpeed > 1.0f) {
                long endSeekPositionMillis = PlaybackSpeedHandler.this.getEndSeekPositionMillis(1);
                long endSeekPositionMillis2 = PlaybackSpeedHandler.this.getEndSeekPositionMillis(2);
                if (currentPosition < endSeekPositionMillis) {
                    PlaybackSpeedHandler playbackSpeedHandler = PlaybackSpeedHandler.this;
                    playbackSpeedHandler.seekToMillis(playbackSpeedHandler.currentItem, currentPosition, ChangeReason.FAST_FORWARD, PlaybackSpeedHandler.this.controlSource);
                }
                if (currentPosition < endSeekPositionMillis2) {
                    ThreadUtils.postOnMainThreadDelayed(PlaybackSpeedHandler.this.updateHandler, PlaybackSpeedHandler.SEEK_PERIOD_MILLIS);
                    return;
                }
                PlaybackSpeedHandler.this.positionTracker.setRunSpeed(1.0f);
                PlaybackSpeedHandler playbackSpeedHandler2 = PlaybackSpeedHandler.this;
                playbackSpeedHandler2.onRunSpeedChanged(playbackSpeedHandler2.currentItem, 1.0f);
                return;
            }
            if (runSpeed < 0.0f) {
                if (currentPosition > 0) {
                    PlaybackSpeedHandler playbackSpeedHandler3 = PlaybackSpeedHandler.this;
                    playbackSpeedHandler3.seekToMillis(playbackSpeedHandler3.currentItem, currentPosition, ChangeReason.REWIND, PlaybackSpeedHandler.this.controlSource);
                    ThreadUtils.postOnMainThreadDelayed(PlaybackSpeedHandler.this.updateHandler, PlaybackSpeedHandler.SEEK_PERIOD_MILLIS);
                } else {
                    PlaybackSpeedHandler playbackSpeedHandler4 = PlaybackSpeedHandler.this;
                    playbackSpeedHandler4.seekToMillis(playbackSpeedHandler4.currentItem, 0L, ChangeReason.REWIND, PlaybackSpeedHandler.this.controlSource);
                    PlaybackSpeedHandler.this.positionTracker.setRunSpeed(1.0f);
                    PlaybackSpeedHandler playbackSpeedHandler5 = PlaybackSpeedHandler.this;
                    playbackSpeedHandler5.onRunSpeedChanged(playbackSpeedHandler5.currentItem, 1.0f);
                }
            }
        }
    };

    protected abstract long getCurrentPositionMillis(@NonNull ITEM item);

    protected abstract long getDurationMillis(@NonNull ITEM item);

    protected abstract void onRunSpeedChanged(@NonNull ITEM item, float f);

    protected abstract void seekToMillis(@NonNull ITEM item, long j, @NonNull ChangeReason changeReason, @NonNull ControlSource controlSource);

    public PlaybackSpeedHandler() {
        this.positionTracker.setRunSpeed(1.0f);
    }

    public void setSpeeds(float f, float f2) {
        if (f <= 1.0f || f2 >= 0.0f) {
            throw new IllegalArgumentException("Illegal params for setSpeeds(): fastForwardSpeed: " + f + ", rewindSpeed: " + f2);
        }
        this.fastForwardSpeed = f;
        this.rewindSpeed = f2;
        float runSpeed = this.positionTracker.getRunSpeed();
        if (runSpeed > 1.0f) {
            this.positionTracker.setRunSpeed(f);
        } else if (runSpeed < 0.0f) {
            this.positionTracker.setRunSpeed(f2);
        }
    }

    public void setCurrentItem(@Nullable ITEM item) {
        if (this.currentItem != item) {
            this.currentItem = item;
            this.positionTracker.setRunSpeed(1.0f);
            ThreadUtils.cancelOnMainThread(this.updateHandler);
        }
    }

    public void normalSpeed() {
        if (this.positionTracker.getRunSpeed() != 1.0f) {
            ThreadUtils.cancelOnMainThread(this.updateHandler);
            this.positionTracker.setRunSpeed(1.0f);
            onRunSpeedChanged(this.currentItem, 1.0f);
        }
    }

    public void fastForward(@NonNull ControlSource controlSource) {
        if (this.positionTracker.getRunSpeed() > 1.0f || this.currentItem == null) {
            return;
        }
        this.controlSource = (ControlSource) Validate.notNull(controlSource);
        long currentPositionMillis = getCurrentPositionMillis(this.currentItem);
        if (currentPositionMillis < getEndSeekPositionMillis(2)) {
            this.positionTracker.set(currentPositionMillis, Clock.now() - SEEK_PERIOD_MILLIS, this.fastForwardSpeed);
            onRunSpeedChanged(this.currentItem, this.fastForwardSpeed);
            this.updateHandler.run();
            return;
        }
        normalSpeed();
    }

    public void rewind(@NonNull ControlSource controlSource) {
        if (this.positionTracker.getRunSpeed() < 0.0f || this.currentItem == null) {
            return;
        }
        this.controlSource = (ControlSource) Validate.notNull(controlSource);
        long currentPositionMillis = getCurrentPositionMillis(this.currentItem);
        if (currentPositionMillis > 0) {
            this.positionTracker.set(currentPositionMillis, Clock.now() - SEEK_PERIOD_MILLIS, this.rewindSpeed);
            onRunSpeedChanged(this.currentItem, this.rewindSpeed);
            this.updateHandler.run();
            return;
        }
        normalSpeed();
    }

    public long getPositionMillis() {
        return this.positionTracker.getCurrentPosition();
    }

    public float getPlaybackSpeed() {
        return this.positionTracker.getRunSpeed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getEndSeekPositionMillis(int i) {
        return Math.max(0L, getDurationMillis(this.currentItem) - ((long) ((((long) i) * SEEK_PERIOD_MILLIS) * this.fastForwardSpeed)));
    }
}
