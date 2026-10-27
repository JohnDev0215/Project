package com.amazon.mp3.skiplimit.impl;

import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.skiplimit.SkipLimitProvider;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkipLimitProviderImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nJ\b\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016J\u000e\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nJ\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0010H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/SkipLimitProviderImpl;", "Lcom/amazon/music/media/playback/skiplimit/SkipLimitProvider;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "numSkipRemaining", "Ljava/util/concurrent/atomic/AtomicInteger;", "skipLimitCountUpdateListeners", "", "Lcom/amazon/mp3/skiplimit/impl/SkipLimitProviderImpl$OnSkipLimitUpdateListener;", "addOnSkipLimitUpdateListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "decrement", "getCount", "", "onSkip", "removeOnSkipLimitUpdateListener", "setCount", "newCount", "OnSkipLimitUpdateListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SkipLimitProviderImpl implements SkipLimitProvider {
    public static final SkipLimitProviderImpl INSTANCE = new SkipLimitProviderImpl();
    private static final String TAG = SkipLimitProviderImpl.class.getSimpleName();
    private static AtomicInteger numSkipRemaining = new AtomicInteger(0);
    private static final List<OnSkipLimitUpdateListener> skipLimitCountUpdateListeners = new ArrayList();

    /* JADX INFO: compiled from: SkipLimitProviderImpl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/SkipLimitProviderImpl$OnSkipLimitUpdateListener;", "", "onSkipLimitUpdated", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnSkipLimitUpdateListener {
        void onSkipLimitUpdated();
    }

    private SkipLimitProviderImpl() {
    }

    @Override // com.amazon.music.media.playback.skiplimit.SkipLimitProvider
    public void setCount(int newCount) {
        Log.info(TAG, "Setting number of remaining skips to " + newCount + '.');
        numSkipRemaining.set(newCount);
        Iterator<OnSkipLimitUpdateListener> it = skipLimitCountUpdateListeners.iterator();
        while (it.hasNext()) {
            it.next().onSkipLimitUpdated();
        }
        HardCodedRefreshStrategy.INSTANCE.tryRefreshHardCodeLimitCountInBackground();
    }

    @Override // com.amazon.music.media.playback.skiplimit.SkipLimitProvider
    public void onSkip() {
        HardCodedRefreshStrategy.INSTANCE.startRecurringRefresh(false);
    }

    @Override // com.amazon.music.media.playback.skiplimit.SkipLimitProvider
    public int getCount() {
        return numSkipRemaining.get();
    }

    @Override // com.amazon.music.media.playback.skiplimit.SkipLimitProvider
    public void decrement() {
        synchronized (this) {
            CloudQueueSequencer cloudQueueSequencer = CloudQueueSequencer.INSTANCE.getCloudQueueSequencer();
            if (cloudQueueSequencer == null || cloudQueueSequencer.enforceSkipLimitOrTooltip()) {
                if (numSkipRemaining.get() <= 0) {
                    Log.warning(TAG, "Current skip count is 0, cannot decrement.");
                } else {
                    Log.info(TAG, "Decrementing number of remaining skips by 1.");
                    numSkipRemaining.decrementAndGet();
                    Iterator<OnSkipLimitUpdateListener> it = skipLimitCountUpdateListeners.iterator();
                    while (it.hasNext()) {
                        it.next().onSkipLimitUpdated();
                    }
                    HardCodedRefreshStrategy.INSTANCE.onDecrementSkipCount();
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final void addOnSkipLimitUpdateListener(OnSkipLimitUpdateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        List<OnSkipLimitUpdateListener> list = skipLimitCountUpdateListeners;
        if (list.contains(listener)) {
            return;
        }
        list.add(listener);
    }

    public final void removeOnSkipLimitUpdateListener(OnSkipLimitUpdateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        skipLimitCountUpdateListeners.remove(listener);
    }
}
