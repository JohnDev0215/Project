package com.amazon.mp3.util;

import android.os.Handler;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class RunnableDebouncer {
    private final Handler mHandler;
    private final Map<Runnable, WrapperDelayTimePair> mDebounceMap = new HashMap();
    private final WeakHashMap<Runnable, WrapperDelayTimePair> mRunnableCache = new WeakHashMap<>();

    public RunnableDebouncer(Handler handler) {
        this.mHandler = handler;
    }

    public void remove(Runnable runnable) {
        WrapperDelayTimePair wrapperDelayTimePairRemove = this.mDebounceMap.remove(runnable);
        if (wrapperDelayTimePairRemove != null) {
            this.mHandler.removeCallbacks(wrapperDelayTimePairRemove.getRunnableWrapper());
        }
    }

    public void post(Runnable runnable) {
        postDelayed(runnable, 0L);
    }

    public void postDelayed(Runnable runnable, long delayInMs) {
        WrapperDelayTimePair wrapperDelayTimePair = this.mDebounceMap.get(runnable);
        if (wrapperDelayTimePair == null) {
            postNewRunnable(runnable, delayInMs);
        } else if (wrapperDelayTimePair.getDelay() != delayInMs) {
            this.mHandler.removeCallbacks(wrapperDelayTimePair.getRunnableWrapper());
            postNewRunnable(runnable, delayInMs);
        }
    }

    private void postNewRunnable(Runnable runnable, long delay) {
        WrapperDelayTimePair cachedRunnableDelayPair = getCachedRunnableDelayPair(runnable, delay);
        this.mDebounceMap.put(runnable, cachedRunnableDelayPair);
        this.mHandler.postDelayed(cachedRunnableDelayPair.getRunnableWrapper(), delay);
    }

    private WrapperDelayTimePair getCachedRunnableDelayPair(Runnable runnable, long delay) {
        WrapperDelayTimePair wrapperDelayTimePair = this.mRunnableCache.get(runnable);
        if (wrapperDelayTimePair == null) {
            WrapperDelayTimePair wrapperDelayTimePair2 = new WrapperDelayTimePair(new RunnableWrapper(runnable), delay);
            this.mRunnableCache.put(runnable, wrapperDelayTimePair2);
            return wrapperDelayTimePair2;
        }
        wrapperDelayTimePair.setDelay(delay);
        return wrapperDelayTimePair;
    }

    private class RunnableWrapper implements Runnable {
        private final Runnable mRunnable;

        public RunnableWrapper(Runnable runnable) {
            this.mRunnable = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            RunnableDebouncer.this.mDebounceMap.remove(this.mRunnable);
            this.mRunnable.run();
        }
    }

    private static class WrapperDelayTimePair {
        private long mDelay;
        private final RunnableWrapper mRunnableWrapper;

        public WrapperDelayTimePair(RunnableWrapper wrapper, long delay) {
            this.mRunnableWrapper = wrapper;
            this.mDelay = delay;
        }

        public RunnableWrapper getRunnableWrapper() {
            return this.mRunnableWrapper;
        }

        public long getDelay() {
            return this.mDelay;
        }

        public void setDelay(long delay) {
            this.mDelay = delay;
        }
    }
}
