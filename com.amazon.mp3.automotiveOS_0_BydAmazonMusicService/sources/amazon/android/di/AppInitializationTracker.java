package amazon.android.di;

import android.os.SystemClock;
import com.amazon.avod.core.AppInitializationListener;
import com.amazon.avod.core.InitializationErrorCode;
import com.amazon.avod.core.InitializationException;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.threading.ProfiledLock;
import com.amazon.avod.threading.ProfiledThread;
import com.amazon.avod.threading.Watchdog;
import com.amazon.avod.util.AppVisibilityTracker;
import com.amazon.avod.util.Throwables2;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.Uninterruptibles;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public class AppInitializationTracker {
    private final Watchdog mWatchdog;
    private final CountDownLatch mAppInjectionLatch = new CountDownLatch(1);
    private final CountDownLatch mAppInitializationLatch = new CountDownLatch(1);
    private final CountDownLatch mActivityInitializationLatch = new CountDownLatch(1);
    private final ProfiledLock mLock = new ProfiledLock(this, ProfiledLock.ThreadUsage.ANY);

    @GuardedBy("mLock")
    private final Set<AppInitializationListener> mRegisteredListeners = Sets.newHashSet();

    @GuardedBy("mLock")
    private final Set<AppInitializationListener> mPreviouslyNotifiedListeners = Sets.newHashSet();

    @GuardedBy("mLock")
    private Optional<InitializationErrorCode> mInitializationErrorCode = Optional.absent();

    private static class SingletonHolder {
        private static volatile AppInitializationTracker sInstance = new AppInitializationTracker(Watchdog.getInstance());

        private SingletonHolder() {
        }
    }

    AppInitializationTracker(@Nonnull Watchdog watchdog) {
        this.mWatchdog = (Watchdog) Preconditions.checkNotNull(watchdog, "watchdog");
    }

    public static AppInitializationTracker getInstance() {
        return SingletonHolder.sInstance;
    }

    public void waitUntilAfterApplicationInitializationUninterruptibly(@Nonnull String str) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "waitingComponent must not be empty/null");
        waitOnLatchUninterruptibly(this.mAppInitializationLatch, "AppInitialization", str);
    }

    public void waitUntilAfterActivityInitializationUninterruptibly(@Nonnull String str) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "waitingComponent must not be empty/null");
        waitOnLatchUninterruptibly(this.mActivityInitializationLatch, "ActivityInitialization", str);
    }

    public void releaseApplicationInitializationLatch() {
        this.mAppInitializationLatch.countDown();
        new ProfiledThread(new Runnable() { // from class: amazon.android.di.AppInitializationTracker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$releaseApplicationInitializationLatch$0();
            }
        }, "ReleaseActivityLatchFromAppOnDelay").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releaseApplicationInitializationLatch$0() {
        SystemClock.sleep(2000L);
        if (AppVisibilityTracker.getInstance().getApplicationVisibility().isAppInForeground()) {
            return;
        }
        this.mActivityInitializationLatch.countDown();
    }

    private void waitOnLatchUninterruptibly(@Nonnull CountDownLatch countDownLatch, @Nonnull String str, @Nonnull String str2) {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.VERBOSE, "WaitFor%sUninterruptibly:%s", str, str2);
        Uninterruptibles.awaitUninterruptibly(countDownLatch);
        Profiler.endTrace(traceKeyBeginTrace);
    }

    public void addForegroundInitErrorCallback(@Nonnull AppInitializationListener appInitializationListener) {
        Preconditions.checkNotNull(appInitializationListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        ProfiledLock.Key keyLock = this.mLock.lock("addForegroundInitErrorCallback");
        try {
            Preconditions.checkState(this.mRegisteredListeners.add(appInitializationListener), "Tried to set new listener that is already set");
            notifyIfNecessary();
        } finally {
            this.mLock.unlock(keyLock);
        }
    }

    public void notifyInitializationError(@Nonnull InitializationException initializationException) {
        Throwables2.propagateIfWeakMode("InitializationError", initializationException);
        this.mWatchdog.onTerminalInitializationError();
        ProfiledLock.Key keyLock = this.mLock.lock("notifyInitializationError");
        try {
            Profiler.incrementCounter("InitializationErrorCode:" + initializationException.getErrorCode());
            this.mInitializationErrorCode = Optional.of(this.mInitializationErrorCode.or(initializationException.getErrorCode()));
            notifyIfNecessary();
        } finally {
            this.mLock.unlock(keyLock);
        }
    }

    private void notifyIfNecessary() {
        this.mLock.checkLocked("notifyIfNecessary");
        if (this.mInitializationErrorCode.isPresent()) {
            for (AppInitializationListener appInitializationListener : this.mRegisteredListeners) {
                if (!this.mPreviouslyNotifiedListeners.contains(appInitializationListener)) {
                    appInitializationListener.onInitializationError(this.mInitializationErrorCode.get());
                    this.mPreviouslyNotifiedListeners.add(appInitializationListener);
                }
            }
        }
    }
}
