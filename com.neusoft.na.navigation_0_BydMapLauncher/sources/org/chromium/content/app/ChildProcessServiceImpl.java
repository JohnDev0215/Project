package org.chromium.content.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.view.Surface;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.library_loader.Linker;
import org.chromium.content.browser.ChildProcessConstants;
import org.chromium.content.browser.ChildProcessCreationParams;
import org.chromium.content.browser.FileDescriptorInfo;
import org.chromium.content.common.ContentSwitches;
import org.chromium.content.common.IChildProcessCallback;
import org.chromium.content.common.IChildProcessService;
import org.chromium.content.common.SurfaceWrapper;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class ChildProcessServiceImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String MAIN_THREAD_NAME = "ChildProcessMain";
    private static final String TAG = "ChildProcessService";
    private IChildProcessCallback mCallback;
    private String[] mCommandLineParams;
    private int mCpuCount;
    private long mCpuFeatures;
    private FileDescriptorInfo[] mFdInfos;
    private ClassLoader mHostClassLoader;
    private int mLibraryProcessType;
    private ChromiumLinkerParams mLinkerParams;
    private Thread mMainThread;
    protected static final FileDescriptorInfo[] EMPTY_FILE_DESCRIPTOR_INFO = new FileDescriptorInfo[0];
    private static AtomicReference<Context> sContext = new AtomicReference<>(null);
    private boolean mLibraryInitialized = false;
    private boolean mIsBound = false;
    private final Semaphore mActivitySemaphore = new Semaphore(1);
    private final IChildProcessService.Stub mBinder = new IChildProcessService.Stub() { // from class: org.chromium.content.app.ChildProcessServiceImpl.1
        @Override // org.chromium.content.common.IChildProcessService
        public int setupConnection(Bundle bundle, IChildProcessCallback iChildProcessCallback) {
            ChildProcessServiceImpl.this.mCallback = iChildProcessCallback;
            ChildProcessServiceImpl.this.getServiceInfo(bundle);
            return Process.myPid();
        }

        @Override // org.chromium.content.common.IChildProcessService
        public void crashIntentionallyForTesting() {
            Process.killProcess(Process.myPid());
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeExitChildProcess();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeInitChildProcessImpl(ChildProcessServiceImpl childProcessServiceImpl, int i, long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeRegisterGlobalFileDescriptor(int i, int i2, long j, long j2);

    private native void nativeShutdownMainThread();

    /* JADX INFO: Access modifiers changed from: private */
    public Linker getLinker() {
        if (Linker.areTestsEnabled()) {
            Linker.setupForTesting(this.mLinkerParams.mLinkerImplementationForTesting, this.mLinkerParams.mTestRunnerClassNameForTesting);
        }
        return Linker.getInstance();
    }

    static Context getContext() {
        return sContext.get();
    }

    public void create(Context context, final Context context2) {
        this.mHostClassLoader = context2.getClassLoader();
        Log.i(TAG, "Creating new ChildProcessService pid=%d", Integer.valueOf(Process.myPid()));
        if (sContext.get() != null) {
            throw new RuntimeException("Illegal child process reuse.");
        }
        sContext.set(context);
        ContextUtils.initApplicationContext(context);
        Thread thread = new Thread(new Runnable() { // from class: org.chromium.content.app.ChildProcessServiceImpl.2
            /* JADX WARN: Can't wrap try/catch for region: R(18:0|2|76|3|9|9|(4:11|32|17|(15:19|27|(1:29)|72|30|31|(4:38|79|39|40)|(1:44)|45|f3|52|(1:54)|84|55|(2:57|87)(1:85))(1:20))(1:25)|26|27|(0)|72|30|31|(4:38|79|39|40)|(0)|45|f3|(1:(0))) */
            /* JADX WARN: Code restructure failed: missing block: B:32:0x008d, code lost:
            
                r5 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:33:0x008e, code lost:
            
                if (r4 != false) goto L34;
             */
            /* JADX WARN: Code restructure failed: missing block: B:34:0x0090, code lost:
            
                org.chromium.base.Log.w(org.chromium.content.app.ChildProcessServiceImpl.TAG, "Failed to load native library with shared RELRO, retrying without", new java.lang.Object[0]);
                r5 = false;
                r6 = true;
             */
            /* JADX WARN: Code restructure failed: missing block: B:35:0x009c, code lost:
            
                org.chromium.base.Log.e(org.chromium.content.app.ChildProcessServiceImpl.TAG, "Failed to load native library", r5);
                r5 = false;
                r6 = false;
             */
            /* JADX WARN: Removed duplicated region for block: B:29:0x0078 A[Catch: ProcessInitException -> 0x015b, InterruptedException -> 0x016c, TRY_LEAVE, TryCatch #3 {ProcessInitException -> 0x015b, blocks: (B:3:0x0003, B:4:0x0009, B:9:0x001d, B:11:0x002c, B:12:0x0032, B:17:0x0046, B:19:0x0056, B:27:0x006c, B:29:0x0078, B:38:0x00ad, B:42:0x00c2, B:44:0x00cf, B:45:0x00d3, B:46:0x00f3, B:52:0x0115, B:54:0x011f, B:55:0x0133, B:57:0x014e, B:61:0x0157, B:34:0x0090, B:35:0x009c, B:20:0x0063, B:24:0x0069, B:64:0x015a), top: B:76:0x0003 }] */
            /* JADX WARN: Removed duplicated region for block: B:44:0x00cf A[Catch: ProcessInitException -> 0x015b, InterruptedException -> 0x016c, TryCatch #3 {ProcessInitException -> 0x015b, blocks: (B:3:0x0003, B:4:0x0009, B:9:0x001d, B:11:0x002c, B:12:0x0032, B:17:0x0046, B:19:0x0056, B:27:0x006c, B:29:0x0078, B:38:0x00ad, B:42:0x00c2, B:44:0x00cf, B:45:0x00d3, B:46:0x00f3, B:52:0x0115, B:54:0x011f, B:55:0x0133, B:57:0x014e, B:61:0x0157, B:34:0x0090, B:35:0x009c, B:20:0x0063, B:24:0x0069, B:64:0x015a), top: B:76:0x0003 }] */
            /* JADX WARN: Removed duplicated region for block: B:70:0x00f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 381
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.app.ChildProcessServiceImpl.AnonymousClass2.run():void");
            }
        }, MAIN_THREAD_NAME);
        this.mMainThread = thread;
        thread.start();
    }

    public void destroy() {
        Log.i(TAG, "Destroying ChildProcessService pid=%d", Integer.valueOf(Process.myPid()));
        if (this.mActivitySemaphore.tryAcquire()) {
            System.exit(0);
            return;
        }
        synchronized (this.mMainThread) {
            while (!this.mLibraryInitialized) {
                try {
                    this.mMainThread.wait();
                } catch (InterruptedException unused) {
                }
            }
        }
        nativeShutdownMainThread();
    }

    public IBinder bind(Intent intent) {
        initializeParams(intent);
        return this.mBinder;
    }

    void initializeParams(Intent intent) {
        synchronized (this.mMainThread) {
            this.mCommandLineParams = intent.getStringArrayExtra(ChildProcessConstants.EXTRA_COMMAND_LINE);
            this.mLinkerParams = new ChromiumLinkerParams(intent);
            this.mLibraryProcessType = ChildProcessCreationParams.getLibraryProcessType(intent);
            this.mIsBound = true;
            this.mMainThread.notifyAll();
        }
    }

    void getServiceInfo(Bundle bundle) {
        bundle.setClassLoader(this.mHostClassLoader);
        synchronized (this.mMainThread) {
            if (this.mCommandLineParams == null) {
                this.mCommandLineParams = bundle.getStringArray(ChildProcessConstants.EXTRA_COMMAND_LINE);
            }
            this.mCpuCount = bundle.getInt(ChildProcessConstants.EXTRA_CPU_COUNT);
            this.mCpuFeatures = bundle.getLong(ChildProcessConstants.EXTRA_CPU_FEATURES);
            Parcelable[] parcelableArray = bundle.getParcelableArray(ChildProcessConstants.EXTRA_FILES);
            if (parcelableArray != null) {
                FileDescriptorInfo[] fileDescriptorInfoArr = new FileDescriptorInfo[parcelableArray.length];
                this.mFdInfos = fileDescriptorInfoArr;
                System.arraycopy(parcelableArray, 0, fileDescriptorInfoArr, 0, parcelableArray.length);
            } else {
                ContentSwitches.getSwitchValue(this.mCommandLineParams, "type");
                this.mFdInfos = EMPTY_FILE_DESCRIPTOR_INFO;
            }
            Bundle bundle2 = bundle.getBundle(Linker.EXTRA_LINKER_SHARED_RELROS);
            if (bundle2 != null) {
                getLinker().useSharedRelros(bundle2);
            }
            this.mMainThread.notifyAll();
        }
    }

    private void establishSurfaceTexturePeer(int i, Object obj, int i2, int i3) {
        Surface surface;
        boolean z;
        if (this.mCallback == null) {
            Log.e(TAG, "No callback interface has been provided.", new Object[0]);
            return;
        }
        if (obj instanceof Surface) {
            surface = (Surface) obj;
            z = false;
        } else if (obj instanceof SurfaceTexture) {
            surface = new Surface((SurfaceTexture) obj);
            z = true;
        } else {
            Log.e(TAG, "Not a valid surfaceObject: %s", obj);
            return;
        }
        try {
            try {
                this.mCallback.establishSurfacePeer(i, surface, i2, i3);
                if (z) {
                    surface.release();
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to call establishSurfaceTexturePeer: %s", e);
                if (z) {
                    surface.release();
                }
            }
        } catch (Throwable th) {
            if (z) {
                surface.release();
            }
            throw th;
        }
    }

    private Surface getViewSurface(int i) {
        IChildProcessCallback iChildProcessCallback = this.mCallback;
        if (iChildProcessCallback == null) {
            Log.e(TAG, "No callback interface has been provided.", new Object[0]);
            return null;
        }
        try {
            SurfaceWrapper viewSurface = iChildProcessCallback.getViewSurface(i);
            if (viewSurface != null) {
                return viewSurface.getSurface();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to call getViewSurface: %s", e);
            return null;
        }
    }

    private void createSurfaceTextureSurface(int i, int i2, SurfaceTexture surfaceTexture) {
        if (this.mCallback == null) {
            Log.e(TAG, "No callback interface has been provided.", new Object[0]);
            return;
        }
        Surface surface = new Surface(surfaceTexture);
        try {
            this.mCallback.registerSurfaceTextureSurface(i, i2, surface);
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to call registerSurfaceTextureSurface: %s", e);
        }
        surface.release();
    }

    private void destroySurfaceTextureSurface(int i, int i2) {
        IChildProcessCallback iChildProcessCallback = this.mCallback;
        if (iChildProcessCallback == null) {
            Log.e(TAG, "No callback interface has been provided.", new Object[0]);
            return;
        }
        try {
            iChildProcessCallback.unregisterSurfaceTextureSurface(i, i2);
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to call unregisterSurfaceTextureSurface: %s", e);
        }
    }

    private Surface getSurfaceTextureSurface(int i) {
        IChildProcessCallback iChildProcessCallback = this.mCallback;
        if (iChildProcessCallback == null) {
            Log.e(TAG, "No callback interface has been provided.", new Object[0]);
            return null;
        }
        try {
            return iChildProcessCallback.getSurfaceTextureSurface(i).getSurface();
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to call getSurfaceTextureSurface: %s", e);
            return null;
        }
    }
}
