package android.os;

import android.annotation.SystemApi;
import android.os.IBinder;
import android.util.ExceptionUtils;
import android.util.Log;
import com.android.internal.os.BinderInternal;
import com.android.internal.util.FastPrintWriter;
import com.android.internal.util.FunctionalUtils;
import dalvik.annotation.optimization.CriticalNative;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Supplier;
import libcore.io.IoUtils;
import libcore.util.NativeAllocationRegistry;

/* JADX INFO: loaded from: classes2.dex */
public class Binder implements IBinder {
    public static final boolean CHECK_PARCEL_SIZE = false;
    private static final boolean FIND_POTENTIAL_LEAKS = false;
    public static boolean LOG_RUNTIME_EXCEPTION = false;
    private static final int NATIVE_ALLOCATION_SIZE = 500;
    static final String TAG = "Binder";
    public static final int UNSET_WORKSOURCE = -1;
    private static volatile String sDumpDisabled = null;
    private static BinderInternal.Observer sObserver = null;
    private static volatile boolean sTracingEnabled = false;
    private static volatile TransactionTracker sTransactionTracker = null;
    static volatile boolean sWarnOnBlocking = false;
    static ThreadLocal<Boolean> sWarnOnBlockingOnCurrentThread = ThreadLocal.withInitial(new Supplier() { // from class: android.os.-$$Lambda$Binder$EXGyLLLyNfmxPrnXI-oObsgHDA4
        @Override // java.util.function.Supplier
        public final Object get() {
            return Boolean.valueOf(Binder.sWarnOnBlocking);
        }
    });
    private static volatile BinderInternal.WorkSourceProvider sWorkSourceProvider = new BinderInternal.WorkSourceProvider() { // from class: android.os.-$$Lambda$Binder$EO-7UzWDhOP5gRu3fQGHAzEp-Xc
        @Override // com.android.internal.os.BinderInternal.WorkSourceProvider
        public final int resolveWorkSourceUid(int i) {
            return Binder.getCallingUid();
        }
    };
    private String mDescriptor;
    private final long mObject;
    private IInterface mOwner;

    public static final native void blockUntilThreadAvailable();

    static void checkParcel(IBinder iBinder, int i, Parcel parcel, String str) {
    }

    @CriticalNative
    public static final native long clearCallingIdentity();

    @CriticalNative
    public static final native long clearCallingWorkSource();

    public static final native void flushPendingCommands();

    @CriticalNative
    public static final native int getCallingPid();

    @CriticalNative
    public static final native int getCallingUid();

    @CriticalNative
    public static final native int getCallingWorkSourceUid();

    private static native long getFinalizer();

    private static native long getNativeBBinderHolder();

    /* JADX INFO: Access modifiers changed from: private */
    public static native long getNativeFinalizer();

    @CriticalNative
    public static final native int getThreadStrictModePolicy();

    @CriticalNative
    public static final native boolean isHandlingTransaction();

    public static final native void restoreCallingIdentity(long j);

    @CriticalNative
    public static final native void restoreCallingWorkSource(long j);

    @CriticalNative
    public static final native long setCallingWorkSourceUid(int i);

    @CriticalNative
    public static final native void setThreadStrictModePolicy(int i);

    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // android.os.IBinder
    public final native IBinder getExtension();

    public String getTransactionName(int i) {
        return null;
    }

    @Override // android.os.IBinder
    public boolean isBinderAlive() {
        return true;
    }

    @Override // android.os.IBinder
    public void linkToDeath(IBinder.DeathRecipient deathRecipient, int i) {
    }

    public final native void markVintfStability();

    @Override // android.os.IBinder
    public boolean pingBinder() {
        return true;
    }

    public final native void setExtension(IBinder iBinder);

    @Override // android.os.IBinder
    public boolean unlinkToDeath(IBinder.DeathRecipient deathRecipient, int i) {
        return true;
    }

    private static class NoImagePreloadHolder {
        public static final NativeAllocationRegistry sRegistry = new NativeAllocationRegistry(Binder.class.getClassLoader(), Binder.getNativeFinalizer(), 500);

        private NoImagePreloadHolder() {
        }
    }

    public static void enableTracing() {
        sTracingEnabled = true;
    }

    public static void disableTracing() {
        sTracingEnabled = false;
    }

    public static boolean isTracingEnabled() {
        return sTracingEnabled;
    }

    public static synchronized TransactionTracker getTransactionTracker() {
        if (sTransactionTracker == null) {
            sTransactionTracker = new TransactionTracker();
        }
        return sTransactionTracker;
    }

    public static void setObserver(BinderInternal.Observer observer) {
        sObserver = observer;
    }

    public static void setWarnOnBlocking(boolean z) {
        sWarnOnBlocking = z;
    }

    public static IBinder allowBlocking(IBinder iBinder) {
        try {
            if (iBinder instanceof BinderProxy) {
                ((BinderProxy) iBinder).mWarnOnBlocking = false;
            } else if (iBinder != null && iBinder.getInterfaceDescriptor() != null && iBinder.queryLocalInterface(iBinder.getInterfaceDescriptor()) == null) {
                Log.w(TAG, "Unable to allow blocking on interface " + iBinder);
            }
        } catch (RemoteException unused) {
        }
        return iBinder;
    }

    public static IBinder defaultBlocking(IBinder iBinder) {
        if (iBinder instanceof BinderProxy) {
            ((BinderProxy) iBinder).mWarnOnBlocking = sWarnOnBlocking;
        }
        return iBinder;
    }

    public static void copyAllowBlocking(IBinder iBinder, IBinder iBinder2) {
        if ((iBinder instanceof BinderProxy) && (iBinder2 instanceof BinderProxy)) {
            ((BinderProxy) iBinder2).mWarnOnBlocking = ((BinderProxy) iBinder).mWarnOnBlocking;
        }
    }

    public static void allowBlockingForCurrentThread() {
        sWarnOnBlockingOnCurrentThread.set(false);
    }

    public static void defaultBlockingForCurrentThread() {
        sWarnOnBlockingOnCurrentThread.set(Boolean.valueOf(sWarnOnBlocking));
    }

    public static final int getCallingUidOrThrow() {
        if (!isHandlingTransaction()) {
            throw new IllegalStateException("Thread is not in a binder transcation");
        }
        return getCallingUid();
    }

    public static final UserHandle getCallingUserHandle() {
        return UserHandle.of(UserHandle.getUserId(getCallingUid()));
    }

    public static final void withCleanCallingIdentity(FunctionalUtils.ThrowingRunnable throwingRunnable) {
        long jClearCallingIdentity = clearCallingIdentity();
        try {
            throwingRunnable.runOrThrow();
            restoreCallingIdentity(jClearCallingIdentity);
        } catch (Throwable th) {
            restoreCallingIdentity(jClearCallingIdentity);
            throw ExceptionUtils.propagate(th);
        }
    }

    public static final <T> T withCleanCallingIdentity(FunctionalUtils.ThrowingSupplier<T> throwingSupplier) {
        long jClearCallingIdentity = clearCallingIdentity();
        try {
            T orThrow = throwingSupplier.getOrThrow();
            restoreCallingIdentity(jClearCallingIdentity);
            return orThrow;
        } catch (Throwable th) {
            restoreCallingIdentity(jClearCallingIdentity);
            throw ExceptionUtils.propagate(th);
        }
    }

    public static final void joinThreadPool() {
        BinderInternal.joinThreadPool();
    }

    public static final boolean isProxy(IInterface iInterface) {
        return iInterface.asBinder() != iInterface;
    }

    public Binder() {
        this(null);
    }

    public Binder(String str) {
        long nativeBBinderHolder = getNativeBBinderHolder();
        this.mObject = nativeBBinderHolder;
        NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, nativeBBinderHolder);
        this.mDescriptor = str;
    }

    public void attachInterface(IInterface iInterface, String str) {
        this.mOwner = iInterface;
        this.mDescriptor = str;
    }

    @Override // android.os.IBinder
    public String getInterfaceDescriptor() {
        return this.mDescriptor;
    }

    @Override // android.os.IBinder
    public IInterface queryLocalInterface(String str) {
        String str2 = this.mDescriptor;
        if (str2 == null || !str2.equals(str)) {
            return null;
        }
        return this.mOwner;
    }

    public static void setDumpDisabled(String str) {
        sDumpDisabled = str;
    }

    @SystemApi
    public interface ProxyTransactListener {
        void onTransactEnded(Object obj);

        Object onTransactStarted(IBinder iBinder, int i);

        default Object onTransactStarted(IBinder iBinder, int i, int i2) {
            return onTransactStarted(iBinder, i);
        }
    }

    public static class PropagateWorkSourceTransactListener implements ProxyTransactListener {
        @Override // android.os.Binder.ProxyTransactListener
        public Object onTransactStarted(IBinder iBinder, int i) {
            int uid = ThreadLocalWorkSource.getUid();
            if (uid != -1) {
                return Long.valueOf(Binder.setCallingWorkSourceUid(uid));
            }
            return null;
        }

        @Override // android.os.Binder.ProxyTransactListener
        public void onTransactEnded(Object obj) {
            if (obj != null) {
                Binder.restoreCallingWorkSource(((Long) obj).longValue());
            }
        }
    }

    @SystemApi
    public static void setProxyTransactListener(ProxyTransactListener proxyTransactListener) {
        BinderProxy.setTransactListener(proxyTransactListener);
    }

    protected boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        ParcelFileDescriptor fileDescriptor;
        FileDescriptor fileDescriptor2;
        if (i == 1598968902) {
            parcel2.writeString(getInterfaceDescriptor());
            return true;
        }
        if (i == 1598311760) {
            fileDescriptor = parcel.readFileDescriptor();
            String[] stringArray = parcel.readStringArray();
            if (fileDescriptor != null) {
                try {
                    dump(fileDescriptor.getFileDescriptor(), stringArray);
                } finally {
                    IoUtils.closeQuietly(fileDescriptor);
                }
            }
            if (parcel2 != null) {
                parcel2.writeNoException();
            } else {
                StrictMode.clearGatheredViolations();
            }
            return true;
        }
        if (i != 1598246212) {
            return false;
        }
        ParcelFileDescriptor fileDescriptor3 = parcel.readFileDescriptor();
        ParcelFileDescriptor fileDescriptor4 = parcel.readFileDescriptor();
        fileDescriptor = parcel.readFileDescriptor();
        String[] stringArray2 = parcel.readStringArray();
        ShellCallback shellCallbackCreateFromParcel = ShellCallback.CREATOR.createFromParcel(parcel);
        ResultReceiver resultReceiverCreateFromParcel = ResultReceiver.CREATOR.createFromParcel(parcel);
        if (fileDescriptor4 != null) {
            if (fileDescriptor3 != null) {
                try {
                    fileDescriptor2 = fileDescriptor3.getFileDescriptor();
                } catch (Throwable th) {
                    IoUtils.closeQuietly(fileDescriptor3);
                    IoUtils.closeQuietly(fileDescriptor4);
                    if (parcel2 != null) {
                        parcel2.writeNoException();
                    } else {
                        StrictMode.clearGatheredViolations();
                    }
                    throw th;
                }
            } else {
                fileDescriptor2 = null;
            }
            shellCommand(fileDescriptor2, fileDescriptor4.getFileDescriptor(), fileDescriptor != null ? fileDescriptor.getFileDescriptor() : fileDescriptor4.getFileDescriptor(), stringArray2, shellCallbackCreateFromParcel, resultReceiverCreateFromParcel);
        }
        IoUtils.closeQuietly(fileDescriptor3);
        IoUtils.closeQuietly(fileDescriptor4);
        if (parcel2 != null) {
            parcel2.writeNoException();
        } else {
            StrictMode.clearGatheredViolations();
        }
        return true;
    }

    @Override // android.os.IBinder
    public void dump(FileDescriptor fileDescriptor, String[] strArr) {
        FastPrintWriter fastPrintWriter = new FastPrintWriter(new FileOutputStream(fileDescriptor));
        try {
            doDump(fileDescriptor, fastPrintWriter, strArr);
        } finally {
            fastPrintWriter.flush();
        }
    }

    void doDump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (sDumpDisabled == null) {
            try {
                dump(fileDescriptor, printWriter, strArr);
                return;
            } catch (SecurityException e) {
                printWriter.println("Security exception: " + e.getMessage());
                throw e;
            } catch (Throwable th) {
                printWriter.println();
                printWriter.println("Exception occurred while dumping:");
                th.printStackTrace(printWriter);
                return;
            }
        }
        printWriter.println(sDumpDisabled);
    }

    @Override // android.os.IBinder
    public void dumpAsync(final FileDescriptor fileDescriptor, final String[] strArr) {
        final FastPrintWriter fastPrintWriter = new FastPrintWriter(new FileOutputStream(fileDescriptor));
        new Thread("Binder.dumpAsync") { // from class: android.os.Binder.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    Binder.this.dump(fileDescriptor, fastPrintWriter, strArr);
                } finally {
                    fastPrintWriter.flush();
                }
            }
        }.start();
    }

    @Override // android.os.IBinder
    public void shellCommand(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, FileDescriptor fileDescriptor3, String[] strArr, ShellCallback shellCallback, ResultReceiver resultReceiver) throws RemoteException {
        onShellCommand(fileDescriptor, fileDescriptor2, fileDescriptor3, strArr, shellCallback, resultReceiver);
    }

    public void onShellCommand(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, FileDescriptor fileDescriptor3, String[] strArr, ShellCallback shellCallback, ResultReceiver resultReceiver) throws RemoteException {
        ParcelFileDescriptor parcelFileDescriptorDup;
        int callingUid = getCallingUid();
        int iHandleShellCommand = -1;
        if (callingUid != 0 && callingUid != 2000) {
            resultReceiver.send(-1, null);
            throw new SecurityException("Shell commands are only callable by ADB");
        }
        if (fileDescriptor == null) {
            try {
                fileDescriptor = new FileInputStream("/dev/null").getFD();
            } catch (IOException e) {
                if (fileDescriptor3 != null) {
                    fileDescriptor2 = fileDescriptor3;
                }
                FastPrintWriter fastPrintWriter = new FastPrintWriter(new FileOutputStream(fileDescriptor2));
                fastPrintWriter.println("Failed to open /dev/null: " + e.getMessage());
                fastPrintWriter.flush();
                return;
            }
        }
        if (fileDescriptor2 == null) {
            fileDescriptor2 = new FileOutputStream("/dev/null").getFD();
        }
        if (fileDescriptor3 == null) {
            fileDescriptor3 = fileDescriptor2;
        }
        if (strArr == null) {
            strArr = new String[0];
        }
        try {
            try {
                parcelFileDescriptorDup = ParcelFileDescriptor.dup(fileDescriptor);
            } catch (IOException e2) {
                FastPrintWriter fastPrintWriter2 = new FastPrintWriter(new FileOutputStream(fileDescriptor3));
                fastPrintWriter2.println("dup() failed: " + e2.getMessage());
                fastPrintWriter2.flush();
            }
            try {
                ParcelFileDescriptor parcelFileDescriptorDup2 = ParcelFileDescriptor.dup(fileDescriptor2);
                try {
                    ParcelFileDescriptor parcelFileDescriptorDup3 = ParcelFileDescriptor.dup(fileDescriptor3);
                    try {
                        iHandleShellCommand = handleShellCommand(parcelFileDescriptorDup, parcelFileDescriptorDup2, parcelFileDescriptorDup3, strArr);
                        if (parcelFileDescriptorDup3 != null) {
                            parcelFileDescriptorDup3.close();
                        }
                        if (parcelFileDescriptorDup2 != null) {
                            parcelFileDescriptorDup2.close();
                        }
                        if (parcelFileDescriptorDup != null) {
                            parcelFileDescriptorDup.close();
                        }
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                if (parcelFileDescriptorDup != null) {
                    try {
                        parcelFileDescriptorDup.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } finally {
            resultReceiver.send(-1, null);
        }
    }

    @SystemApi
    public int handleShellCommand(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, ParcelFileDescriptor parcelFileDescriptor3, String[] strArr) {
        FastPrintWriter fastPrintWriter = new FastPrintWriter(new FileOutputStream(parcelFileDescriptor3.getFileDescriptor()));
        fastPrintWriter.println("No shell command implementation.");
        fastPrintWriter.flush();
        return 0;
    }

    @Override // android.os.IBinder
    public final boolean transact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (parcel != null) {
            parcel.setDataPosition(0);
        }
        boolean zOnTransact = onTransact(i, parcel, parcel2, i2);
        if (parcel2 != null) {
            parcel2.setDataPosition(0);
        }
        return zOnTransact;
    }

    public static void setWorkSourceProvider(BinderInternal.WorkSourceProvider workSourceProvider) {
        if (workSourceProvider == null) {
            throw new IllegalArgumentException("workSourceProvider cannot be null");
        }
        sWorkSourceProvider = workSourceProvider;
    }

    private boolean execTransact(int i, long j, long j2, int i2) {
        int callingUid = getCallingUid();
        long uid = ThreadLocalWorkSource.setUid(callingUid);
        try {
            return execTransactInternal(i, j, j2, i2, callingUid);
        } finally {
            ThreadLocalWorkSource.restore(uid);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0053 A[Catch: all -> 0x004a, RemoteException | RuntimeException -> 0x004d, TRY_LEAVE, TryCatch #0 {RemoteException | RuntimeException -> 0x004d, blocks: (B:8:0x001c, B:12:0x003e, B:11:0x003a, B:17:0x004f, B:19:0x0053, B:21:0x005a, B:21:0x005a, B:23:0x0060, B:23:0x0060, B:24:0x0063, B:24:0x0063, B:25:0x0064, B:25:0x0064), top: B:54:0x001c, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0064 A[Catch: all -> 0x004a, RemoteException | RuntimeException -> 0x004d, RemoteException | RuntimeException -> 0x004d, TRY_LEAVE, TryCatch #0 {RemoteException | RuntimeException -> 0x004d, blocks: (B:8:0x001c, B:12:0x003e, B:11:0x003a, B:17:0x004f, B:19:0x0053, B:21:0x005a, B:21:0x005a, B:23:0x0060, B:23:0x0060, B:24:0x0063, B:24:0x0063, B:25:0x0064, B:25:0x0064), top: B:54:0x001c, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006f A[PHI: r12
  0x006f: PHI (r12v2 boolean) = (r12v0 boolean), (r12v4 boolean) binds: [B:45:0x00b5, B:28:0x006d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean execTransactInternal(int r8, long r9, long r11, int r13, int r14) {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.Binder.execTransactInternal(int, long, long, int, int):boolean");
    }
}
