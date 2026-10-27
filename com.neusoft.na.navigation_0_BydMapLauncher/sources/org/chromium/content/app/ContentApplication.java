package org.chromium.content.app;

import android.os.Looper;
import android.os.MessageQueue;
import org.chromium.base.BaseChromiumApplication;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.content.browser.TracingControllerAndroid;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ContentApplication extends BaseChromiumApplication {
    private boolean mLibraryDependenciesInitialized;
    private TracingControllerAndroid mTracingController;

    protected void initializeLibraryDependencies() {
    }

    public ContentApplication() {
        this(true);
    }

    protected ContentApplication(boolean z) {
        super(z);
    }

    TracingControllerAndroid getTracingController() {
        if (this.mTracingController == null) {
            this.mTracingController = new TracingControllerAndroid(this);
        }
        return this.mTracingController;
    }

    @Override // org.chromium.base.BaseChromiumApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: org.chromium.content.app.ContentApplication.1
            @Override // android.os.MessageQueue.IdleHandler
            public boolean queueIdle() {
                if (!LibraryLoader.isInitialized()) {
                    return true;
                }
                try {
                    ContentApplication.this.getTracingController().registerReceiver(ContentApplication.this);
                    return false;
                } catch (SecurityException unused) {
                    return false;
                }
            }
        });
        initializeLibraryDependencies();
        this.mLibraryDependenciesInitialized = true;
    }

    public boolean areLibraryDependenciesInitialized() {
        return this.mLibraryDependenciesInitialized;
    }

    @Override // android.app.Application
    public void onTerminate() {
        try {
            getTracingController().unregisterReceiver(this);
            getTracingController().destroy();
        } catch (SecurityException unused) {
        }
        super.onTerminate();
    }
}
