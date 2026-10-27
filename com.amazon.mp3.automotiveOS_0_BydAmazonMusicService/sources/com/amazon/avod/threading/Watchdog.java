package com.amazon.avod.threading;

import android.os.Debug;
import android.os.SystemClock;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.amazon.avod.util.Throwables2;
import com.facebook.react.bridge.BaseJavaModule;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class Watchdog {
    private final List<CommittedPromise> mBrokenPromises;
    private final ScheduledExecutorService mExecutor;
    private volatile boolean mHasActivityLaunched;
    private volatile boolean mHasReportedBark;
    private volatile boolean mHasTerminalInitializationErrorOccurred;
    private final AtomicBoolean mIsInitialized;
    private final Object mLock;
    private final BiMap<Promise, ScheduledFuture<?>> mWatchedPromiseTimeouts;
    private final BiMap<Promise, CommittedPromise> mWatchedPromises;

    private static class SingletonHolder {
        private static final Watchdog INSTANCE = new Watchdog();

        private SingletonHolder() {
        }
    }

    @Nonnull
    public static Watchdog getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static abstract class Promise<T> {
        protected final WeakReference<T> mPromisor;
        private final Class<?> mPromisorClazz;
        protected final long mTimeout;
        protected final TimeUnit mTimeoutUnits;

        public abstract boolean isFulfilled(@Nonnull T t);

        protected Promise(@Nonnull T t, @Nonnegative long j, @Nonnull TimeUnit timeUnit) {
            Preconditions.checkArgument(j >= 0, "timeout < 0 (%s)", j);
            this.mPromisor = new WeakReference<>(Preconditions.checkNotNull(t, "objectToWatch"));
            this.mPromisorClazz = t.getClass();
            this.mTimeoutUnits = (TimeUnit) Preconditions.checkNotNull(timeUnit, "timeoutUnits");
            this.mTimeout = Preconditions2.checkNonNegative(j, "timeout");
        }

        final boolean isFulfilledOrObsolete() {
            T t = this.mPromisor.get();
            if (t == null) {
                return true;
            }
            return isFulfilled(t);
        }

        public String toString() {
            T t = this.mPromisor.get();
            return String.format(Locale.US, "%s:%s", this.mPromisorClazz.getSimpleName(), t == null ? "Obsolete" : t.toString());
        }
    }

    private static class CommittedPromise {
        private final Throwable mAllocationSite;
        private final Promise mPromise;
        private final long mStartTimeMillis;

        CommittedPromise(@Nonnull Promise promise) {
            Promise promise2 = (Promise) Preconditions.checkNotNull(promise);
            this.mPromise = promise2;
            this.mStartTimeMillis = SystemClock.elapsedRealtime();
            this.mAllocationSite = new WatchdogTimeout(String.format(Locale.US, "Initialization call stack for %s", promise2.mPromisorClazz.getSimpleName()));
        }

        public String toString() {
            return toString(true);
        }

        @Nonnull
        public String toString(boolean z) {
            float fElapsedRealtime = (float) ((SystemClock.elapsedRealtime() - this.mStartTimeMillis) / 1000.0d);
            Promise promise = this.mPromise;
            float fractionalSeconds = toFractionalSeconds(promise.mTimeout, promise.mTimeoutUnits);
            if (z) {
                return String.format(Locale.US, "%s (at %.1f of %.1f second timeout)", this.mPromise, Float.valueOf(fElapsedRealtime), Float.valueOf(fractionalSeconds));
            }
            Locale locale = Locale.US;
            Object[] objArr = new Object[3];
            objArr[0] = this.mPromise;
            objArr[1] = fElapsedRealtime < fractionalSeconds ? "BELOW" : "ABOVE";
            objArr[2] = Float.valueOf(fractionalSeconds);
            return String.format(locale, "%s (%s %.1f second timeout)", objArr);
        }

        private float toFractionalSeconds(long j, TimeUnit timeUnit) {
            if (TimeUnit.SECONDS.compareTo(timeUnit) > 0) {
                return (float) (j / timeUnit.convert(1L, r0));
            }
            return timeUnit.toSeconds(j);
        }
    }

    private static class WatchdogTimeout extends RuntimeException {
        WatchdogTimeout(String str) {
            super(str);
        }
    }

    Watchdog() {
        this(ScheduledExecutorBuilder.newBuilderFor(Watchdog.class, new String[0]).withFixedThreadPoolSize(1).build());
    }

    Watchdog(@Nonnull ScheduledExecutorService scheduledExecutorService) {
        this.mBrokenPromises = Collections.synchronizedList(new ArrayList());
        this.mIsInitialized = new AtomicBoolean(false);
        this.mHasTerminalInitializationErrorOccurred = false;
        this.mHasActivityLaunched = false;
        this.mHasReportedBark = false;
        this.mLock = new Object();
        this.mWatchedPromises = HashBiMap.create();
        this.mWatchedPromiseTimeouts = HashBiMap.create();
        this.mExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "executor");
    }

    public void onTerminalInitializationError() {
        this.mHasTerminalInitializationErrorOccurred = true;
    }

    public final void initialize() {
        this.mIsInitialized.compareAndSet(false, true);
    }

    public void watch(@Nonnull Promise promise) {
        watchInner(promise);
    }

    @Nullable
    ScheduledFuture<?> watchInner(@Nonnull Promise promise) {
        ScheduledFuture<?> scheduledFutureAssertPromiseFulfilled;
        Preconditions.checkNotNull(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (!this.mIsInitialized.get() || Debug.isDebuggerConnected()) {
            return null;
        }
        synchronized (this.mLock) {
            CommittedPromise committedPromise = new CommittedPromise(promise);
            this.mWatchedPromises.put(promise, committedPromise);
            scheduledFutureAssertPromiseFulfilled = assertPromiseFulfilled(promise, committedPromise);
        }
        return scheduledFutureAssertPromiseFulfilled;
    }

    public void update(@Nonnull Promise promise) {
        updateInner(promise);
    }

    @Nullable
    ScheduledFuture<?> updateInner(@Nonnull Promise promise) {
        Preconditions.checkNotNull(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (!this.mIsInitialized.get() || Debug.isDebuggerConnected()) {
            return null;
        }
        synchronized (this.mLock) {
            ScheduledFuture<?> scheduledFuture = this.mWatchedPromiseTimeouts.get(promise);
            if (scheduledFuture == null) {
                return null;
            }
            scheduledFuture.cancel(true);
            return assertPromiseFulfilled(promise, this.mWatchedPromises.get(promise));
        }
    }

    @Nonnull
    private ScheduledFuture<?> assertPromiseFulfilled(@Nonnull Promise promise, @Nonnull CommittedPromise committedPromise) {
        ScheduledFuture<?> scheduledFutureSchedule = this.mExecutor.schedule(new AssertPromiseFulfilled(committedPromise), promise.mTimeout, promise.mTimeoutUnits);
        this.mWatchedPromiseTimeouts.put(promise, scheduledFutureSchedule);
        return scheduledFutureSchedule;
    }

    protected void onPromiseBroken(@Nonnull CommittedPromise committedPromise, @Nonnull ImmutableSet<CommittedPromise> immutableSet) {
        Preconditions.checkNotNull(committedPromise, "brokenPromise");
        Preconditions.checkNotNull(immutableSet, "otherUnfulfilledPromises");
        if (Debug.isDebuggerConnected()) {
            return;
        }
        logDetailedPromiseInformation(committedPromise, immutableSet);
        String brokenPromiseErrorMessage = getBrokenPromiseErrorMessage(committedPromise, immutableSet);
        if (this.mHasTerminalInitializationErrorOccurred || this.mHasReportedBark) {
            return;
        }
        this.mHasReportedBark = true;
        Throwables2.propagateIfWeakMode(false, this.mHasActivityLaunched ? "Watchdog:Foreground" : "Watchdog:Background", brokenPromiseErrorMessage, committedPromise.mAllocationSite);
    }

    private void logDetailedPromiseInformation(@Nullable CommittedPromise committedPromise, @Nonnull ImmutableSet<CommittedPromise> immutableSet) {
        if (committedPromise != null) {
            this.mBrokenPromises.add(committedPromise);
            DLog.warnf("Broken Promise: %s", committedPromise.toString(true));
        }
        UnmodifiableIterator<CommittedPromise> it = immutableSet.iterator();
        while (it.hasNext()) {
            DLog.warnf("Pending Promise: %s", it.next().toString(true));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    @Nonnull
    public ImmutableSet<CommittedPromise> getUnfulfilledPromises() {
        ImmutableSet immutableSetCopyOf;
        synchronized (this.mLock) {
            immutableSetCopyOf = ImmutableSet.copyOf((Collection) this.mWatchedPromises.values());
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator it = immutableSetCopyOf.iterator();
        while (it.hasNext()) {
            CommittedPromise committedPromise = (CommittedPromise) it.next();
            if (!committedPromise.mPromise.isFulfilledOrObsolete()) {
                builder.add(committedPromise);
            }
        }
        return builder.build();
    }

    @Nonnull
    private String getBrokenPromiseErrorMessage(@Nonnull CommittedPromise committedPromise, @Nonnull ImmutableSet<CommittedPromise> immutableSet) {
        String str = String.format(Locale.US, "Promise %s was not fulfilled within allowed time window.", committedPromise.toString(false));
        if (immutableSet.isEmpty()) {
            return str;
        }
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        UnmodifiableIterator<CommittedPromise> it = immutableSet.iterator();
        while (it.hasNext()) {
            arrayListNewArrayList.add(it.next().toString(false));
        }
        Collections.sort(arrayListNewArrayList, String.CASE_INSENSITIVE_ORDER);
        return String.format(Locale.US, "%s%nThe following promises pending on this watchdog have also not been fulfilled: %s", str, Joiner.on(", ").join(arrayListNewArrayList));
    }

    private class AssertPromiseFulfilled implements Runnable {
        private final CommittedPromise mCommittedPromise;

        AssertPromiseFulfilled(@Nonnull CommittedPromise committedPromise) {
            this.mCommittedPromise = (CommittedPromise) Preconditions.checkNotNull(committedPromise, BaseJavaModule.METHOD_TYPE_PROMISE);
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (Watchdog.this.mLock) {
                Watchdog.this.mWatchedPromises.remove(this.mCommittedPromise.mPromise);
                Watchdog.this.mWatchedPromiseTimeouts.remove(this.mCommittedPromise.mPromise);
            }
            if (this.mCommittedPromise.mPromise.isFulfilledOrObsolete()) {
                return;
            }
            Watchdog.this.onPromiseBroken(this.mCommittedPromise, Watchdog.this.getUnfulfilledPromises());
        }
    }
}
