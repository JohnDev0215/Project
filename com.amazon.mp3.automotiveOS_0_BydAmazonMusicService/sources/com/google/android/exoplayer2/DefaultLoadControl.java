package com.google.android.exoplayer2;

import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: loaded from: classes6.dex */
public final class DefaultLoadControl implements LoadControl {
    private final DefaultAllocator allocator;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private boolean isBuffering;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final PriorityTaskManager priorityTaskManager;
    private int targetBufferSize;

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536));
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator) {
        this(defaultAllocator, 15000, 30000, 2500L, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i, int i2, long j, long j2) {
        this(defaultAllocator, i, i2, j, j2, null);
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i, int i2, long j, long j2, PriorityTaskManager priorityTaskManager) {
        this.allocator = defaultAllocator;
        this.minBufferUs = ((long) i) * 1000;
        this.maxBufferUs = ((long) i2) * 1000;
        this.bufferForPlaybackUs = j * 1000;
        this.bufferForPlaybackAfterRebufferUs = j2 * 1000;
        this.priorityTaskManager = priorityTaskManager;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onPrepared() {
        reset(false);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        this.targetBufferSize = 0;
        for (int i = 0; i < rendererArr.length; i++) {
            if (trackSelectionArray.get(i) != null) {
                this.targetBufferSize += Util.getDefaultBufferSize(rendererArr[i].getTrackType());
            }
        }
        this.allocator.setTargetBufferSize(this.targetBufferSize);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onStopped() {
        reset(true);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onReleased() {
        reset(true);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public Allocator getAllocator() {
        return this.allocator;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public boolean shouldStartPlayback(long j, boolean z) {
        long j2 = z ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        return j2 <= 0 || j >= j2;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public boolean shouldContinueLoading(long j) {
        int bufferTimeState = getBufferTimeState(j);
        boolean z = true;
        boolean z2 = this.allocator.getTotalBytesAllocated() >= this.targetBufferSize;
        boolean z3 = this.isBuffering;
        if (bufferTimeState != 2 && (bufferTimeState != 1 || !z3 || z2)) {
            z = false;
        }
        this.isBuffering = z;
        PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
        if (priorityTaskManager != null && z != z3) {
            if (z) {
                priorityTaskManager.add(0);
            } else {
                priorityTaskManager.remove(0);
            }
        }
        return this.isBuffering;
    }

    private int getBufferTimeState(long j) {
        if (j > this.maxBufferUs) {
            return 0;
        }
        return j < this.minBufferUs ? 2 : 1;
    }

    private void reset(boolean z) {
        this.targetBufferSize = 0;
        PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
        if (priorityTaskManager != null && this.isBuffering) {
            priorityTaskManager.remove(0);
        }
        this.isBuffering = false;
        if (z) {
            this.allocator.reset();
        }
    }
}
