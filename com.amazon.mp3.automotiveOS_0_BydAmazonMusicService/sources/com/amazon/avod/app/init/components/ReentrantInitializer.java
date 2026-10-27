package com.amazon.avod.app.init.components;

import amazon.android.di.AppInitializationTracker;
import com.amazon.avod.core.InitializationException;
import com.amazon.avod.threading.ProfiledThread;
import com.amazon.avod.util.InitializationLatch;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ReentrantInitializer {
    protected final InitializationLatch mDependenciesLatch = new InitializationLatch(String.format(Locale.US, "%s:%s", getClass().getSimpleName(), "Dependencies"));
    private final Object mStartInitializationLock = new Object();
    private boolean mHasInitializationStarted = false;

    protected abstract void initialize() throws InitializationException;

    public void startInitializationAsync() {
        synchronized (this.mStartInitializationLock) {
            if (this.mHasInitializationStarted) {
                return;
            }
            ProfiledThread.startFor(new Runnable() { // from class: com.amazon.avod.app.init.components.ReentrantInitializer.1RunInitialization
                @Override // java.lang.Runnable
                public void run() {
                    ReentrantInitializer.this.mDependenciesLatch.waitOnInitializationUninterruptibly();
                    try {
                        ReentrantInitializer.this.initialize();
                    } catch (InitializationException e) {
                        AppInitializationTracker.getInstance().notifyInitializationError(e);
                    }
                }
            }, String.format(Locale.US, "%s:%s", "Initialize", getClass().getSimpleName()));
            this.mHasInitializationStarted = true;
        }
    }
}
