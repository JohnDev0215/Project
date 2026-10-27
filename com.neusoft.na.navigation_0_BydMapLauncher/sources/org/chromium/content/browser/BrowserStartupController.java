package org.chromium.content.browser;

import android.content.Context;
import android.os.Handler;
import android.os.StrictMode;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Log;
import org.chromium.base.ResourceExtractor;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.content.app.ContentMain;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class BrowserStartupController {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean ALREADY_STARTED = true;
    private static final boolean NOT_ALREADY_STARTED = false;
    static final int STARTUP_FAILURE = 1;
    static final int STARTUP_SUCCESS = -1;
    private static final String TAG = "cr.BrowserStartup";
    private static boolean sBrowserMayStartAsynchronously = false;
    private static BrowserStartupController sInstance = null;
    private static boolean sShouldStartGpuProcessOnBrowserStartup = true;
    private final List<StartupCallback> mAsyncStartupCallbacks = new ArrayList();
    private final Context mContext;
    private boolean mHasStartedInitializingBrowserProcess;
    private int mLibraryProcessType;
    private boolean mPostResourceExtractionTasksCompleted;
    private boolean mStartupDone;
    private boolean mStartupSuccess;

    public interface StartupCallback {
        void onFailure();

        void onSuccess(boolean z);
    }

    private static native boolean nativeIsOfficialBuild();

    /* JADX INFO: Access modifiers changed from: private */
    public static native boolean nativeIsPluginEnabled();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetCommandLineFlags(boolean z, String str);

    private static void setAsynchronousStartup(boolean z) {
        sBrowserMayStartAsynchronously = z;
    }

    private static void setShouldStartGpuProcessOnBrowserStartup(boolean z) {
        sShouldStartGpuProcessOnBrowserStartup = z;
    }

    static boolean browserMayStartAsynchonously() {
        return sBrowserMayStartAsynchronously;
    }

    static void browserStartupComplete(int i) {
        BrowserStartupController browserStartupController = sInstance;
        if (browserStartupController != null) {
            browserStartupController.executeEnqueuedCallbacks(i, false);
        }
    }

    static boolean shouldStartGpuProcessOnBrowserStartup() {
        return sShouldStartGpuProcessOnBrowserStartup;
    }

    BrowserStartupController(Context context, int i) {
        this.mContext = context.getApplicationContext();
        this.mLibraryProcessType = i;
    }

    public static BrowserStartupController get(Context context, int i) {
        ThreadUtils.assertOnUiThread();
        if (sInstance == null) {
            sInstance = new BrowserStartupController(context, i);
        }
        return sInstance;
    }

    static BrowserStartupController overrideInstanceForTest(BrowserStartupController browserStartupController) {
        if (sInstance == null) {
            sInstance = browserStartupController;
        }
        return sInstance;
    }

    public void startBrowserProcessesAsync(boolean z, StartupCallback startupCallback) throws ProcessInitException {
        if (this.mStartupDone) {
            postStartupCompleted(startupCallback);
            return;
        }
        this.mAsyncStartupCallbacks.add(startupCallback);
        if (this.mHasStartedInitializingBrowserProcess) {
            return;
        }
        this.mHasStartedInitializingBrowserProcess = true;
        setAsynchronousStartup(true);
        setShouldStartGpuProcessOnBrowserStartup(z);
        prepareToStartBrowserProcess(false, new Runnable() { // from class: org.chromium.content.browser.BrowserStartupController.1
            @Override // java.lang.Runnable
            public void run() {
                ThreadUtils.assertOnUiThread();
                if (BrowserStartupController.this.contentStart() > 0) {
                    BrowserStartupController.this.enqueueCallbackExecution(1, false);
                }
            }
        });
    }

    public void startBrowserProcessesSync(boolean z) throws ProcessInitException {
        if (!this.mStartupDone) {
            if (!this.mHasStartedInitializingBrowserProcess || !this.mPostResourceExtractionTasksCompleted) {
                prepareToStartBrowserProcess(z, null);
            }
            setAsynchronousStartup(false);
            if (contentStart() > 0) {
                enqueueCallbackExecution(1, false);
            }
        }
        if (!this.mStartupSuccess) {
            throw new ProcessInitException(4);
        }
    }

    int contentStart() {
        return ContentMain.start();
    }

    public void addStartupCompletedObserver(StartupCallback startupCallback) {
        ThreadUtils.assertOnUiThread();
        if (this.mStartupDone) {
            postStartupCompleted(startupCallback);
        } else {
            this.mAsyncStartupCallbacks.add(startupCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeEnqueuedCallbacks(int i, boolean z) {
        this.mStartupDone = true;
        this.mStartupSuccess = i <= 0;
        for (StartupCallback startupCallback : this.mAsyncStartupCallbacks) {
            if (this.mStartupSuccess) {
                startupCallback.onSuccess(z);
            } else {
                startupCallback.onFailure();
            }
        }
        this.mAsyncStartupCallbacks.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enqueueCallbackExecution(final int i, final boolean z) {
        new Handler().post(new Runnable() { // from class: org.chromium.content.browser.BrowserStartupController.2
            @Override // java.lang.Runnable
            public void run() {
                BrowserStartupController.this.executeEnqueuedCallbacks(i, z);
            }
        });
    }

    private void postStartupCompleted(final StartupCallback startupCallback) {
        new Handler().post(new Runnable() { // from class: org.chromium.content.browser.BrowserStartupController.3
            @Override // java.lang.Runnable
            public void run() {
                if (BrowserStartupController.this.mStartupSuccess) {
                    startupCallback.onSuccess(true);
                } else {
                    startupCallback.onFailure();
                }
            }
        });
    }

    void prepareToStartBrowserProcess(final boolean z, final Runnable runnable) throws ProcessInitException {
        Log.i(TAG, "Initializing chromium process, singleProcess=%b", Boolean.valueOf(z));
        ResourceExtractor resourceExtractor = ResourceExtractor.get(this.mContext);
        resourceExtractor.startExtractingResources();
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            LibraryLoader.get(this.mLibraryProcessType).ensureInitialized(this.mContext);
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            Runnable runnable2 = new Runnable() { // from class: org.chromium.content.browser.BrowserStartupController.4
                @Override // java.lang.Runnable
                public void run() {
                    if (!BrowserStartupController.this.mPostResourceExtractionTasksCompleted) {
                        DeviceUtils.addDeviceSpecificUserAgentSwitch(BrowserStartupController.this.mContext);
                        BrowserStartupController.nativeSetCommandLineFlags(z, BrowserStartupController.nativeIsPluginEnabled() ? BrowserStartupController.this.getPlugins() : null);
                        BrowserStartupController.this.mPostResourceExtractionTasksCompleted = true;
                    }
                    Runnable runnable3 = runnable;
                    if (runnable3 != null) {
                        runnable3.run();
                    }
                }
            };
            if (runnable == null) {
                resourceExtractor.waitForCompletion();
                runnable2.run();
            } else {
                resourceExtractor.addCompletionCallback(runnable2);
            }
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            throw th;
        }
    }

    public void initChromiumBrowserProcessForTests() {
        ResourceExtractor resourceExtractor = ResourceExtractor.get(this.mContext);
        resourceExtractor.startExtractingResources();
        resourceExtractor.waitForCompletion();
        nativeSetCommandLineFlags(false, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getPlugins() {
        return PepperPluginManager.getPlugins(this.mContext);
    }
}
