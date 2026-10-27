package org.chromium.content.browser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Surface;
import com.neusoft.na.common.FileUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import org.chromium.base.CommandLine;
import org.chromium.base.CpuFeatures;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.library_loader.Linker;
import org.chromium.content.app.ChromiumLinkerParams;
import org.chromium.content.app.DownloadProcessService;
import org.chromium.content.app.PrivilegedProcessService;
import org.chromium.content.app.SandboxedProcessService;
import org.chromium.content.browser.ChildProcessConnection;
import org.chromium.content.common.ContentSwitches;
import org.chromium.content.common.IChildProcessCallback;
import org.chromium.content.common.SurfaceWrapper;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class ChildProcessLauncher {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int CALLBACK_FOR_DOWNLOAD_PROCESS = 4;
    static final int CALLBACK_FOR_GPU_PROCESS = 1;
    static final int CALLBACK_FOR_RENDERER_PROCESS = 2;
    static final int CALLBACK_FOR_UNKNOWN_PROCESS = 0;
    static final int CALLBACK_FOR_UTILITY_PROCESS = 3;
    private static final long FREE_CONNECTION_DELAY_MILLIS = 1;
    private static final int NULL_PROCESS_HANDLE = 0;
    private static final String NUM_PRIVILEGED_SERVICES_KEY = "org.chromium.content.browser.NUM_PRIVILEGED_SERVICES";
    private static final String NUM_SANDBOXED_SERVICES_KEY = "org.chromium.content.browser.NUM_SANDBOXED_SERVICES";
    private static final String SANDBOXED_SERVICES_NAME_KEY = "org.chromium.content.browser.SANDBOXED_SERVICES_NAME";
    public static final String SWITCH_NUM_SANDBOXED_SERVICES_FOR_TESTING = "num-sandboxed-services";
    public static final String SWITCH_SANDBOXED_SERVICES_NAME_FOR_TESTING = "sandboxed-services-name";
    private static final String TAG = "ChildProcLauncher";
    private static boolean sLinkerInitialized = false;
    private static long sLinkerLoadAddress;
    private static ChildConnectionAllocator sPrivilegedChildConnectionAllocator;
    private static Map<String, ChildConnectionAllocator> sSandboxedChildConnectionAllocatorMap;
    private static Map<Integer, ChildProcessConnection> sServiceMap = new ConcurrentHashMap();
    private static ChildProcessConnection sSpareSandboxedConnection = null;
    private static BindingManager sBindingManager = BindingManagerImpl.createBindingManager();
    private static Map<Integer, Surface> sViewSurfaceMap = new ConcurrentHashMap();
    private static Map<Pair<Integer, Integer>, Surface> sSurfaceTextureSurfaceMap = new ConcurrentHashMap();
    private static boolean sApplicationInForeground = true;

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeEstablishSurfacePeer(int i, Surface surface, int i2, int i3);

    private static native boolean nativeIsSingleProcess();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeOnChildProcessStarted(long j, int i);

    private static class ChildConnectionAllocator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final String mChildClassName;
        private final ChildProcessConnection[] mChildProcessConnections;
        private final ArrayList<Integer> mFreeConnectionIndices;
        private final boolean mInSandbox;
        private final Object mConnectionLock = new Object();
        private final PendingSpawnQueue mPendingSpawnQueue = new PendingSpawnQueue();

        public ChildConnectionAllocator(boolean z, int i, String str) {
            this.mChildProcessConnections = new ChildProcessConnectionImpl[i];
            this.mFreeConnectionIndices = new ArrayList<>(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.mFreeConnectionIndices.add(Integer.valueOf(i2));
            }
            this.mChildClassName = str;
            this.mInSandbox = z;
        }

        public ChildProcessConnection allocate(Context context, ChildProcessConnection.DeathCallback deathCallback, ChromiumLinkerParams chromiumLinkerParams, boolean z, ChildProcessCreationParams childProcessCreationParams) {
            synchronized (this.mConnectionLock) {
                if (this.mFreeConnectionIndices.isEmpty()) {
                    Log.d(ChildProcessLauncher.TAG, "Ran out of services to allocate.");
                    return null;
                }
                int iIntValue = this.mFreeConnectionIndices.remove(0).intValue();
                this.mChildProcessConnections[iIntValue] = new ChildProcessConnectionImpl(context, iIntValue, this.mInSandbox, deathCallback, this.mChildClassName, chromiumLinkerParams, z, childProcessCreationParams);
                Log.d(ChildProcessLauncher.TAG, "Allocator allocated a connection, sandbox: %b, slot: %d", Boolean.valueOf(this.mInSandbox), Integer.valueOf(iIntValue));
                return this.mChildProcessConnections[iIntValue];
            }
        }

        public void free(ChildProcessConnection childProcessConnection) {
            synchronized (this.mConnectionLock) {
                int serviceNumber = childProcessConnection.getServiceNumber();
                ChildProcessConnection[] childProcessConnectionArr = this.mChildProcessConnections;
                ChildProcessConnection childProcessConnection2 = childProcessConnectionArr[serviceNumber];
                if (childProcessConnection2 != childProcessConnection) {
                    Log.e(ChildProcessLauncher.TAG, "Unable to find connection to free in slot: %d already occupied by service: %d", Integer.valueOf(serviceNumber), Integer.valueOf(childProcessConnection2 == null ? -1 : childProcessConnection2.getServiceNumber()));
                } else {
                    childProcessConnectionArr[serviceNumber] = null;
                    this.mFreeConnectionIndices.add(Integer.valueOf(serviceNumber));
                    Log.d(ChildProcessLauncher.TAG, "Allocator freed a connection, sandbox: %b, slot: %d", Boolean.valueOf(this.mInSandbox), Integer.valueOf(serviceNumber));
                }
            }
        }

        public boolean isFreeConnectionAvailable() {
            boolean z;
            synchronized (this.mConnectionLock) {
                z = !this.mFreeConnectionIndices.isEmpty();
            }
            return z;
        }

        public PendingSpawnQueue getPendingSpawnQueue() {
            return this.mPendingSpawnQueue;
        }

        int allocatedConnectionsCountForTesting() {
            return this.mChildProcessConnections.length - this.mFreeConnectionIndices.size();
        }
    }

    private static class PendingSpawnData {
        private final int mCallbackType;
        private final int mChildProcessId;
        private final long mClientContext;
        private final String[] mCommandLine;
        private final Context mContext;
        private final ChildProcessCreationParams mCreationParams;
        private final FileDescriptorInfo[] mFilesToBeMapped;
        private final boolean mInSandbox;

        private PendingSpawnData(Context context, String[] strArr, int i, FileDescriptorInfo[] fileDescriptorInfoArr, long j, int i2, boolean z, ChildProcessCreationParams childProcessCreationParams) {
            this.mContext = context;
            this.mCommandLine = strArr;
            this.mChildProcessId = i;
            this.mFilesToBeMapped = fileDescriptorInfoArr;
            this.mClientContext = j;
            this.mCallbackType = i2;
            this.mInSandbox = z;
            this.mCreationParams = childProcessCreationParams;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Context context() {
            return this.mContext;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String[] commandLine() {
            return this.mCommandLine;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int childProcessId() {
            return this.mChildProcessId;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FileDescriptorInfo[] filesToBeMapped() {
            return this.mFilesToBeMapped;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long clientContext() {
            return this.mClientContext;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int callbackType() {
            return this.mCallbackType;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean inSandbox() {
            return this.mInSandbox;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ChildProcessCreationParams getCreationParams() {
            return this.mCreationParams;
        }
    }

    private static class PendingSpawnQueue {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Queue<PendingSpawnData> mPendingSpawns;
        final Object mPendingSpawnsLock;

        private PendingSpawnQueue() {
            this.mPendingSpawns = new LinkedList();
            this.mPendingSpawnsLock = new Object();
        }

        public void enqueueLocked(PendingSpawnData pendingSpawnData) {
            this.mPendingSpawns.add(pendingSpawnData);
        }

        public PendingSpawnData dequeueLocked() {
            return this.mPendingSpawns.poll();
        }

        public int sizeLocked() {
            return this.mPendingSpawns.size();
        }
    }

    private static int getNumberOfServices(Context context, boolean z, String str) {
        int i = -1;
        if (z && CommandLine.getInstance().hasSwitch(SWITCH_NUM_SANDBOXED_SERVICES_FOR_TESTING)) {
            String switchValue = CommandLine.getInstance().getSwitchValue(SWITCH_NUM_SANDBOXED_SERVICES_FOR_TESTING);
            if (!TextUtils.isEmpty(switchValue)) {
                try {
                    i = Integer.parseInt(switchValue);
                } catch (NumberFormatException unused) {
                    Log.w(TAG, "The value of --num-sandboxed-services is formatted wrongly: " + switchValue, new Object[0]);
                }
            }
        } else {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
                if (applicationInfo.metaData != null) {
                    i = applicationInfo.metaData.getInt(z ? NUM_SANDBOXED_SERVICES_KEY : NUM_PRIVILEGED_SERVICES_KEY, -1);
                }
            } catch (PackageManager.NameNotFoundException unused2) {
                throw new RuntimeException("Could not get application info");
            }
        }
        if (i >= 0) {
            return i;
        }
        throw new RuntimeException("Illegal meta data value for number of child services");
    }

    private static String getClassNameOfService(Context context, boolean z, String str) {
        if (!z) {
            return PrivilegedProcessService.class.getName();
        }
        if (CommandLine.getInstance().hasSwitch(SWITCH_SANDBOXED_SERVICES_NAME_FOR_TESTING)) {
            return CommandLine.getInstance().getSwitchValue(SWITCH_SANDBOXED_SERVICES_NAME_FOR_TESTING);
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            String string = applicationInfo.metaData != null ? applicationInfo.metaData.getString(SANDBOXED_SERVICES_NAME_KEY) : null;
            if (string != null) {
                try {
                    context.getPackageManager().getServiceInfo(new ComponentName(str, string + FileUtils.DEFAULT_VERSION), 0);
                    return string;
                } catch (PackageManager.NameNotFoundException unused) {
                    throw new RuntimeException("Illegal meta data value: the child service doesn't exist");
                }
            }
            return SandboxedProcessService.class.getName();
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new RuntimeException("Could not get application info.");
        }
    }

    private static void initConnectionAllocatorsIfNecessary(Context context, boolean z, String str) {
        synchronized (ChildProcessLauncher.class) {
            if (z) {
                if (sSandboxedChildConnectionAllocatorMap == null) {
                    sSandboxedChildConnectionAllocatorMap = new ConcurrentHashMap();
                }
                if (!sSandboxedChildConnectionAllocatorMap.containsKey(str)) {
                    Log.w(TAG, "Create a new ChildConnectionAllocator with package name = %s, inSandbox = true", str);
                    sSandboxedChildConnectionAllocatorMap.put(str, new ChildConnectionAllocator(true, getNumberOfServices(context, true, str), getClassNameOfService(context, true, str)));
                }
            } else if (sPrivilegedChildConnectionAllocator == null) {
                sPrivilegedChildConnectionAllocator = new ChildConnectionAllocator(false, getNumberOfServices(context, false, str), getClassNameOfService(context, false, str));
            }
        }
    }

    private static ChildConnectionAllocator getConnectionAllocator(String str, boolean z) {
        if (!z) {
            return sPrivilegedChildConnectionAllocator;
        }
        return sSandboxedChildConnectionAllocatorMap.get(str);
    }

    private static PendingSpawnQueue getPendingSpawnQueue(Context context, String str, boolean z) {
        initConnectionAllocatorsIfNecessary(context, z, str);
        return getConnectionAllocator(str, z).getPendingSpawnQueue();
    }

    private static ChildProcessConnection allocateConnection(Context context, boolean z, ChromiumLinkerParams chromiumLinkerParams, boolean z2, ChildProcessCreationParams childProcessCreationParams) {
        ChildProcessConnection.DeathCallback deathCallback = new ChildProcessConnection.DeathCallback() { // from class: org.chromium.content.browser.ChildProcessLauncher.1
            @Override // org.chromium.content.browser.ChildProcessConnection.DeathCallback
            public void onChildProcessDied(ChildProcessConnection childProcessConnection) {
                if (childProcessConnection.getPid() == 0) {
                    ChildProcessLauncher.freeConnection(childProcessConnection);
                } else {
                    ChildProcessLauncher.stop(childProcessConnection.getPid());
                }
            }
        };
        String packageName = childProcessCreationParams != null ? childProcessCreationParams.getPackageName() : context.getPackageName();
        initConnectionAllocatorsIfNecessary(context, z, packageName);
        return getConnectionAllocator(packageName, z).allocate(context, deathCallback, chromiumLinkerParams, z2, childProcessCreationParams);
    }

    private static ChromiumLinkerParams getLinkerParamsForNewConnection() {
        if (!sLinkerInitialized) {
            if (Linker.isUsed()) {
                long baseLoadAddress = Linker.getInstance().getBaseLoadAddress();
                sLinkerLoadAddress = baseLoadAddress;
                if (baseLoadAddress == 0) {
                    Log.i(TAG, "Shared RELRO support disabled!", new Object[0]);
                }
            }
            sLinkerInitialized = true;
        }
        if (sLinkerLoadAddress == 0) {
            return null;
        }
        if (Linker.areTestsEnabled()) {
            Linker linker = Linker.getInstance();
            return new ChromiumLinkerParams(sLinkerLoadAddress, true, linker.getTestRunnerClassNameForTesting(), linker.getImplementationForTesting());
        }
        return new ChromiumLinkerParams(sLinkerLoadAddress, true);
    }

    private static ChildProcessConnection allocateBoundConnection(Context context, String[] strArr, boolean z, boolean z2, ChildProcessCreationParams childProcessCreationParams) {
        ChildProcessConnection childProcessConnectionAllocateConnection = allocateConnection(context, z, getLinkerParamsForNewConnection(), z2, childProcessCreationParams);
        if (childProcessConnectionAllocateConnection != null) {
            childProcessConnectionAllocateConnection.start(strArr);
            String packageName = childProcessCreationParams != null ? childProcessCreationParams.getPackageName() : context.getPackageName();
            if (z && !getConnectionAllocator(packageName, z).isFreeConnectionAvailable()) {
                sBindingManager.releaseAllModerateBindings();
            }
        }
        return childProcessConnectionAllocateConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void freeConnection(final ChildProcessConnection childProcessConnection) {
        synchronized (ChildProcessLauncher.class) {
            if (childProcessConnection.equals(sSpareSandboxedConnection)) {
                sSpareSandboxedConnection = null;
            }
        }
        ThreadUtils.postOnUiThreadDelayed(new Runnable() { // from class: org.chromium.content.browser.ChildProcessLauncher.2
            @Override // java.lang.Runnable
            public void run() {
                final PendingSpawnData pendingSpawnDataFreeConnectionAndDequeuePending = ChildProcessLauncher.freeConnectionAndDequeuePending(childProcessConnection);
                if (pendingSpawnDataFreeConnectionAndDequeuePending != null) {
                    new Thread(new Runnable() { // from class: org.chromium.content.browser.ChildProcessLauncher.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ChildProcessLauncher.startInternal(pendingSpawnDataFreeConnectionAndDequeuePending.context(), pendingSpawnDataFreeConnectionAndDequeuePending.commandLine(), pendingSpawnDataFreeConnectionAndDequeuePending.childProcessId(), pendingSpawnDataFreeConnectionAndDequeuePending.filesToBeMapped(), pendingSpawnDataFreeConnectionAndDequeuePending.clientContext(), pendingSpawnDataFreeConnectionAndDequeuePending.callbackType(), pendingSpawnDataFreeConnectionAndDequeuePending.inSandbox(), pendingSpawnDataFreeConnectionAndDequeuePending.getCreationParams());
                        }
                    }).start();
                }
            }
        }, 1L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PendingSpawnData freeConnectionAndDequeuePending(ChildProcessConnection childProcessConnection) {
        PendingSpawnData pendingSpawnDataDequeueLocked;
        ChildConnectionAllocator connectionAllocator = getConnectionAllocator(childProcessConnection.getPackageName(), childProcessConnection.isInSandbox());
        PendingSpawnQueue pendingSpawnQueue = connectionAllocator.getPendingSpawnQueue();
        synchronized (pendingSpawnQueue.mPendingSpawnsLock) {
            connectionAllocator.free(childProcessConnection);
            pendingSpawnDataDequeueLocked = pendingSpawnQueue.dequeueLocked();
        }
        return pendingSpawnDataDequeueLocked;
    }

    public static void setBindingManagerForTesting(BindingManager bindingManager) {
        sBindingManager = bindingManager;
    }

    private static boolean isOomProtected(int i) {
        return sBindingManager.isOomProtected(i);
    }

    private static void registerViewSurface(int i, Surface surface) {
        if (!surface.isValid()) {
            throw new RuntimeException("Attempting to register invalid Surface.");
        }
        sViewSurfaceMap.put(Integer.valueOf(i), surface);
    }

    private static void unregisterViewSurface(int i) {
        sViewSurfaceMap.remove(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Surface getViewSurface(int i) {
        Surface surface = sViewSurfaceMap.get(Integer.valueOf(i));
        if (surface == null) {
            Log.e(TAG, "Invalid surfaceId.", new Object[0]);
            return null;
        }
        if (surface.isValid()) {
            return surface;
        }
        Log.e(TAG, "Requested surface is not valid.", new Object[0]);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void registerSurfaceTextureSurface(int i, int i2, Surface surface) {
        sSurfaceTextureSurfaceMap.put(new Pair<>(Integer.valueOf(i), Integer.valueOf(i2)), surface);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void unregisterSurfaceTextureSurface(int i, int i2) {
        Surface surfaceRemove = sSurfaceTextureSurfaceMap.remove(new Pair(Integer.valueOf(i), Integer.valueOf(i2)));
        if (surfaceRemove == null) {
            return;
        }
        surfaceRemove.release();
    }

    private static void createSurfaceTextureSurface(int i, int i2, SurfaceTexture surfaceTexture) {
        registerSurfaceTextureSurface(i, i2, new Surface(surfaceTexture));
    }

    private static void destroySurfaceTextureSurface(int i, int i2) {
        unregisterSurfaceTextureSurface(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SurfaceWrapper getSurfaceTextureSurface(int i, int i2) {
        Surface surface = sSurfaceTextureSurfaceMap.get(new Pair(Integer.valueOf(i), Integer.valueOf(i2)));
        if (surface == null) {
            Log.e(TAG, "Invalid Id for surface texture.", new Object[0]);
            return null;
        }
        return new SurfaceWrapper(surface);
    }

    public static void setInForeground(int i, boolean z) {
        sBindingManager.setInForeground(i, z);
    }

    public static void determinedVisibility(int i) {
        sBindingManager.determinedVisibility(i);
    }

    public static void onSentToBackground() {
        sApplicationInForeground = false;
        sBindingManager.onSentToBackground();
    }

    public static void startModerateBindingManagement(Context context, boolean z) {
        sBindingManager.startModerateBindingManagement(context, getNumberOfServices(context, true, context.getPackageName()), z);
    }

    public static void onBroughtToForeground() {
        sApplicationInForeground = true;
        sBindingManager.onBroughtToForeground();
    }

    static boolean isApplicationInForeground() {
        return sApplicationInForeground;
    }

    public static void warmUp(Context context) {
        synchronized (ChildProcessLauncher.class) {
            if (sSpareSandboxedConnection == null) {
                ChildProcessCreationParams childProcessCreationParamsCopy = ChildProcessCreationParams.get();
                if (childProcessCreationParamsCopy != null) {
                    childProcessCreationParamsCopy = childProcessCreationParamsCopy.copy();
                }
                sSpareSandboxedConnection = allocateBoundConnection(context, null, true, false, childProcessCreationParamsCopy);
            }
        }
    }

    private static FileDescriptorInfo makeFdInfo(int i, int i2, boolean z, long j, long j2) {
        ParcelFileDescriptor parcelFileDescriptorFromFd;
        if (z) {
            parcelFileDescriptorFromFd = ParcelFileDescriptor.adoptFd(i2);
        } else {
            try {
                parcelFileDescriptorFromFd = ParcelFileDescriptor.fromFd(i2);
            } catch (IOException e) {
                Log.e(TAG, "Invalid FD provided for process connection, aborting connection.", e);
                return null;
            }
        }
        return new FileDescriptorInfo(i, parcelFileDescriptorFromFd, j, j2);
    }

    private static void start(Context context, String[] strArr, int i, FileDescriptorInfo[] fileDescriptorInfoArr, long j) {
        ChildProcessCreationParams childProcessCreationParams;
        boolean z;
        int i2;
        int i3;
        String switchValue = ContentSwitches.getSwitchValue(strArr, "type");
        ChildProcessCreationParams childProcessCreationParams2 = ChildProcessCreationParams.get();
        if (childProcessCreationParams2 != null) {
            childProcessCreationParams2 = childProcessCreationParams2.copy();
        }
        if (!ContentSwitches.SWITCH_RENDERER_PROCESS.equals(switchValue)) {
            if (childProcessCreationParams2 != null && !childProcessCreationParams2.getPackageName().equals(context.getPackageName())) {
                childProcessCreationParams2 = new ChildProcessCreationParams(context.getPackageName(), childProcessCreationParams2.getExtraBindFlags(), childProcessCreationParams2.getLibraryProcessType());
            }
            if (ContentSwitches.SWITCH_GPU_PROCESS.equals(switchValue)) {
                childProcessCreationParams = childProcessCreationParams2;
                i2 = 1;
                z = false;
            } else if (ContentSwitches.SWITCH_UTILITY_PROCESS.equals(switchValue)) {
                i3 = 3;
            } else {
                childProcessCreationParams = childProcessCreationParams2;
                z = true;
                i2 = 0;
            }
            startInternal(context, strArr, i, fileDescriptorInfoArr, j, i2, z, childProcessCreationParams);
        }
        i3 = 2;
        i2 = i3;
        childProcessCreationParams = childProcessCreationParams2;
        z = true;
        startInternal(context, strArr, i, fileDescriptorInfoArr, j, i2, z, childProcessCreationParams);
    }

    private static void startDownloadProcessIfNecessary(Context context, String[] strArr) {
        ContentSwitches.getSwitchValue(strArr, "type");
        Intent intent = new Intent();
        intent.setClass(context, DownloadProcessService.class);
        intent.setPackage(context.getPackageName());
        intent.putExtra(ChildProcessConstants.EXTRA_COMMAND_LINE, strArr);
        Bundle bundleCreatesServiceBundle = createsServiceBundle(strArr, null, Linker.getInstance().getSharedRelros());
        bundleCreatesServiceBundle.putBinder(ChildProcessConstants.EXTRA_CHILD_PROCESS_CALLBACK, createCallback(0, 4).asBinder());
        intent.putExtras(bundleCreatesServiceBundle);
        ChromiumLinkerParams linkerParamsForNewConnection = getLinkerParamsForNewConnection();
        if (linkerParamsForNewConnection != null) {
            linkerParamsForNewConnection.addIntentExtras(intent);
        }
        context.startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startInternal(Context context, String[] strArr, int i, FileDescriptorInfo[] fileDescriptorInfoArr, long j, int i2, boolean z, ChildProcessCreationParams childProcessCreationParams) {
        ChildProcessConnection childProcessConnectionAllocateBoundConnection;
        try {
            TraceEvent.begin("ChildProcessLauncher.startInternal");
            String packageName = childProcessCreationParams != null ? childProcessCreationParams.getPackageName() : context.getPackageName();
            synchronized (ChildProcessLauncher.class) {
                childProcessConnectionAllocateBoundConnection = null;
                if (z) {
                    ChildProcessConnection childProcessConnection = sSpareSandboxedConnection;
                    if (childProcessConnection != null && childProcessConnection.getPackageName().equals(packageName)) {
                        ChildProcessConnection childProcessConnection2 = sSpareSandboxedConnection;
                        sSpareSandboxedConnection = null;
                        childProcessConnectionAllocateBoundConnection = childProcessConnection2;
                    }
                }
            }
            if (childProcessConnectionAllocateBoundConnection == null) {
                boolean z2 = i2 == 1;
                PendingSpawnQueue pendingSpawnQueue = getPendingSpawnQueue(context, packageName, z);
                synchronized (pendingSpawnQueue.mPendingSpawnsLock) {
                    childProcessConnectionAllocateBoundConnection = allocateBoundConnection(context, strArr, z, z2, childProcessCreationParams);
                    if (childProcessConnectionAllocateBoundConnection == null) {
                        Log.d(TAG, "Allocation of new service failed. Queuing up pending spawn.");
                        pendingSpawnQueue.enqueueLocked(new PendingSpawnData(context, strArr, i, fileDescriptorInfoArr, j, i2, z, childProcessCreationParams));
                        return;
                    }
                }
            }
            Log.d(TAG, "Setting up connection to process: slot=%d", Integer.valueOf(childProcessConnectionAllocateBoundConnection.getServiceNumber()));
            triggerConnectionSetup(childProcessConnectionAllocateBoundConnection, strArr, i, fileDescriptorInfoArr, i2, j);
        } finally {
            TraceEvent.end("ChildProcessLauncher.startInternal");
        }
    }

    protected static Bundle createsServiceBundle(String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putStringArray(ChildProcessConstants.EXTRA_COMMAND_LINE, strArr);
        bundle2.putParcelableArray(ChildProcessConstants.EXTRA_FILES, fileDescriptorInfoArr);
        bundle2.putInt(ChildProcessConstants.EXTRA_CPU_COUNT, CpuFeatures.getCount());
        bundle2.putLong(ChildProcessConstants.EXTRA_CPU_FEATURES, CpuFeatures.getMask());
        bundle2.putBundle(Linker.EXTRA_LINKER_SHARED_RELROS, bundle);
        return bundle2;
    }

    static void triggerConnectionSetup(final ChildProcessConnection childProcessConnection, String[] strArr, int i, FileDescriptorInfo[] fileDescriptorInfoArr, final int i2, final long j) {
        childProcessConnection.setupConnection(strArr, fileDescriptorInfoArr, createCallback(i, i2), new ChildProcessConnection.ConnectionCallback() { // from class: org.chromium.content.browser.ChildProcessLauncher.3
            @Override // org.chromium.content.browser.ChildProcessConnection.ConnectionCallback
            public void onConnected(int i3) {
                Log.d(ChildProcessLauncher.TAG, "on connect callback, pid=%d context=%d callbackType=%d", Integer.valueOf(i3), Long.valueOf(j), Integer.valueOf(i2));
                if (i3 != 0) {
                    ChildProcessLauncher.sBindingManager.addNewConnection(i3, childProcessConnection);
                    ChildProcessLauncher.sServiceMap.put(Integer.valueOf(i3), childProcessConnection);
                }
                long j2 = j;
                if (j2 != 0) {
                    ChildProcessLauncher.nativeOnChildProcessStarted(j2, i3);
                }
            }
        }, Linker.getInstance().getSharedRelros());
    }

    static void stop(int i) {
        Log.d(TAG, "stopping child connection: pid=%d", Integer.valueOf(i));
        ChildProcessConnection childProcessConnectionRemove = sServiceMap.remove(Integer.valueOf(i));
        if (childProcessConnectionRemove == null) {
            logPidWarning(i, "Tried to stop non-existent connection");
            return;
        }
        sBindingManager.clearConnection(i);
        childProcessConnectionRemove.stop();
        freeConnection(childProcessConnectionRemove);
    }

    private static IChildProcessCallback createCallback(final int i, final int i2) {
        return new IChildProcessCallback.Stub() { // from class: org.chromium.content.browser.ChildProcessLauncher.4
            @Override // org.chromium.content.common.IChildProcessCallback
            public void establishSurfacePeer(int i3, Surface surface, int i4, int i5) {
                if (i2 == 1) {
                    ChildProcessLauncher.nativeEstablishSurfacePeer(i3, surface, i4, i5);
                } else {
                    Log.e(ChildProcessLauncher.TAG, "Illegal callback for non-GPU process.", new Object[0]);
                }
            }

            @Override // org.chromium.content.common.IChildProcessCallback
            public SurfaceWrapper getViewSurface(int i3) {
                if (i2 == 1) {
                    Surface viewSurface = ChildProcessLauncher.getViewSurface(i3);
                    if (viewSurface == null) {
                        return null;
                    }
                    return new SurfaceWrapper(viewSurface);
                }
                Log.e(ChildProcessLauncher.TAG, "Illegal callback for non-GPU process.", new Object[0]);
                return null;
            }

            @Override // org.chromium.content.common.IChildProcessCallback
            public void registerSurfaceTextureSurface(int i3, int i4, Surface surface) {
                if (i2 == 1) {
                    ChildProcessLauncher.registerSurfaceTextureSurface(i3, i4, surface);
                } else {
                    Log.e(ChildProcessLauncher.TAG, "Illegal callback for non-GPU process.", new Object[0]);
                }
            }

            @Override // org.chromium.content.common.IChildProcessCallback
            public void unregisterSurfaceTextureSurface(int i3, int i4) {
                if (i2 == 1) {
                    ChildProcessLauncher.unregisterSurfaceTextureSurface(i3, i4);
                } else {
                    Log.e(ChildProcessLauncher.TAG, "Illegal callback for non-GPU process.", new Object[0]);
                }
            }

            @Override // org.chromium.content.common.IChildProcessCallback
            public SurfaceWrapper getSurfaceTextureSurface(int i3) {
                if (i2 == 2) {
                    return ChildProcessLauncher.getSurfaceTextureSurface(i3, i);
                }
                Log.e(ChildProcessLauncher.TAG, "Illegal callback for non-renderer process.", new Object[0]);
                return null;
            }

            @Override // org.chromium.content.common.IChildProcessCallback
            public void onDownloadStarted(boolean z, int i3) {
                if (i2 != 4) {
                    Log.e(ChildProcessLauncher.TAG, "Illegal callback for non-download process.", new Object[0]);
                }
            }
        };
    }

    static void logPidWarning(int i, String str) {
        if (i <= 0 || nativeIsSingleProcess()) {
            return;
        }
        Log.w(TAG, "%s, pid=%d", str, Integer.valueOf(i));
    }

    static ChildProcessConnection allocateBoundConnectionForTesting(Context context, ChildProcessCreationParams childProcessCreationParams) {
        return allocateBoundConnection(context, null, true, false, childProcessCreationParams);
    }

    static ChildProcessConnection allocateConnectionForTesting(Context context, ChildProcessCreationParams childProcessCreationParams) {
        return allocateConnection(context, true, getLinkerParamsForNewConnection(), false, childProcessCreationParams);
    }

    static void enqueuePendingSpawnForTesting(Context context, String[] strArr, ChildProcessCreationParams childProcessCreationParams, boolean z) {
        PendingSpawnQueue pendingSpawnQueue = getPendingSpawnQueue(context, childProcessCreationParams != null ? childProcessCreationParams.getPackageName() : context.getPackageName(), z);
        synchronized (pendingSpawnQueue.mPendingSpawnsLock) {
            pendingSpawnQueue.enqueueLocked(new PendingSpawnData(context, strArr, 1, new FileDescriptorInfo[0], 0L, 2, true, childProcessCreationParams));
        }
    }

    static int allocatedSandboxedConnectionsCountForTesting(Context context, String str) {
        initConnectionAllocatorsIfNecessary(context, true, str);
        return sSandboxedChildConnectionAllocatorMap.get(str).allocatedConnectionsCountForTesting();
    }

    static int connectedServicesCountForTesting() {
        return sServiceMap.size();
    }

    static int pendingSpawnsCountForTesting(Context context, String str, boolean z) {
        int iSizeLocked;
        PendingSpawnQueue pendingSpawnQueue = getPendingSpawnQueue(context, str, z);
        synchronized (pendingSpawnQueue.mPendingSpawnsLock) {
            iSizeLocked = pendingSpawnQueue.sizeLocked();
        }
        return iSizeLocked;
    }

    public static boolean crashProcessForTesting(int i) {
        if (sServiceMap.get(Integer.valueOf(i)) == null) {
            return false;
        }
        try {
            ((ChildProcessConnectionImpl) sServiceMap.get(Integer.valueOf(i))).crashServiceForTesting();
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }
}
