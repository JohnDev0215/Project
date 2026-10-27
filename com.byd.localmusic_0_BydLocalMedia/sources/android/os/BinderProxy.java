package android.os;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.media.MediaMetrics;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseIntArray;
import com.android.internal.os.BinderInternal;
import java.io.FileDescriptor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import libcore.util.NativeAllocationRegistry;

/* JADX INFO: loaded from: classes2.dex */
public final class BinderProxy implements IBinder {
    private static final int NATIVE_ALLOCATION_SIZE = 1000;
    private static final ProxyMap sProxyMap = new ProxyMap();
    private static volatile Binder.ProxyTransactListener sTransactListener;
    private final long mNativeData;
    volatile boolean mWarnOnBlocking = Binder.sWarnOnBlocking;

    /* JADX INFO: Access modifiers changed from: private */
    public static native long getNativeFinalizer();

    @Override // android.os.IBinder
    public native IBinder getExtension() throws RemoteException;

    @Override // android.os.IBinder
    public native String getInterfaceDescriptor() throws RemoteException;

    @Override // android.os.IBinder
    public native boolean isBinderAlive();

    @Override // android.os.IBinder
    public native void linkToDeath(IBinder.DeathRecipient deathRecipient, int i) throws RemoteException;

    @Override // android.os.IBinder
    public native boolean pingBinder();

    @Override // android.os.IBinder
    public IInterface queryLocalInterface(String str) {
        return null;
    }

    public native boolean transactNative(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException;

    @Override // android.os.IBinder
    public native boolean unlinkToDeath(IBinder.DeathRecipient deathRecipient, int i);

    public static void setTransactListener(Binder.ProxyTransactListener proxyTransactListener) {
        sTransactListener = proxyTransactListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class ProxyMap {
        private static final int CRASH_AT_SIZE = 20000;
        private static final int LOG_MAIN_INDEX_SIZE = 8;
        private static final int MAIN_INDEX_MASK = 255;
        private static final int MAIN_INDEX_SIZE = 256;
        static final int MAX_NUM_INTERFACES_TO_DUMP = 10;
        private static final int WARN_INCREMENT = 10;
        private final Long[][] mMainIndexKeys;
        private final ArrayList<WeakReference<BinderProxy>>[] mMainIndexValues;
        private int mRandom;
        private int mWarnBucketSize;

        private static int hash(long j) {
            return ((int) ((j >> 10) ^ (j >> 2))) & 255;
        }

        private ProxyMap() {
            this.mWarnBucketSize = 20;
            this.mMainIndexKeys = new Long[256][];
            this.mMainIndexValues = new ArrayList[256];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int size() {
            int size = 0;
            for (ArrayList<WeakReference<BinderProxy>> arrayList : this.mMainIndexValues) {
                if (arrayList != null) {
                    size += arrayList.size();
                }
            }
            return size;
        }

        private int unclearedSize() {
            int i = 0;
            for (ArrayList<WeakReference<BinderProxy>> arrayList : this.mMainIndexValues) {
                if (arrayList != null) {
                    Iterator<WeakReference<BinderProxy>> it = arrayList.iterator();
                    while (it.hasNext()) {
                        if (it.next().get() != null) {
                            i++;
                        }
                    }
                }
            }
            return i;
        }

        private void remove(int i, int i2) {
            Long[] lArr = this.mMainIndexKeys[i];
            ArrayList<WeakReference<BinderProxy>> arrayList = this.mMainIndexValues[i];
            int size = arrayList.size() - 1;
            if (i2 != size) {
                lArr[i2] = lArr[size];
                arrayList.set(i2, arrayList.get(size));
            }
            arrayList.remove(size);
        }

        BinderProxy get(long j) {
            int iHash = hash(j);
            Long[] lArr = this.mMainIndexKeys[iHash];
            if (lArr == null) {
                return null;
            }
            ArrayList<WeakReference<BinderProxy>> arrayList = this.mMainIndexValues[iHash];
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (j == lArr[i].longValue()) {
                    BinderProxy binderProxy = arrayList.get(i).get();
                    if (binderProxy != null) {
                        return binderProxy;
                    }
                    remove(iHash, i);
                    return null;
                }
            }
            return null;
        }

        void set(long j, BinderProxy binderProxy) {
            int iHash = hash(j);
            ArrayList<WeakReference<BinderProxy>>[] arrayListArr = this.mMainIndexValues;
            ArrayList<WeakReference<BinderProxy>> arrayList = arrayListArr[iHash];
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                arrayListArr[iHash] = arrayList;
                this.mMainIndexKeys[iHash] = new Long[1];
            }
            int size = arrayList.size();
            WeakReference<BinderProxy> weakReference = new WeakReference<>(binderProxy);
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i).get() == null) {
                    arrayList.set(i, weakReference);
                    this.mMainIndexKeys[iHash][i] = Long.valueOf(j);
                    if (i < size - 1) {
                        int i2 = this.mRandom + 1;
                        this.mRandom = i2;
                        int i3 = i + 1;
                        int iFloorMod = i3 + Math.floorMod(i2, size - i3);
                        if (arrayList.get(iFloorMod).get() == null) {
                            remove(iHash, iFloorMod);
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            arrayList.add(size, weakReference);
            Long[] lArr = this.mMainIndexKeys[iHash];
            if (lArr.length == size) {
                Long[] lArr2 = new Long[(size / 2) + size + 2];
                System.arraycopy(lArr, 0, lArr2, 0, size);
                lArr2[size] = Long.valueOf(j);
                this.mMainIndexKeys[iHash] = lArr2;
            } else {
                lArr[size] = Long.valueOf(j);
            }
            if (size >= this.mWarnBucketSize) {
                int size2 = size();
                Log.v("Binder", "BinderProxy map growth! bucket size = " + size + " total = " + size2);
                this.mWarnBucketSize = this.mWarnBucketSize + 10;
                if (!Build.IS_DEBUGGABLE || size2 < 20000) {
                    return;
                }
                int iUnclearedSize = unclearedSize();
                if (iUnclearedSize >= 20000) {
                    dumpProxyInterfaceCounts();
                    dumpPerUidProxyCounts();
                    Runtime.getRuntime().gc();
                    throw new AssertionError("Binder ProxyMap has too many entries: " + size2 + " (total), " + iUnclearedSize + " (uncleared), " + unclearedSize() + " (uncleared after GC). BinderProxy leak?");
                }
                if (size2 > (iUnclearedSize * 3) / 2) {
                    Log.v("Binder", "BinderProxy map has many cleared entries: " + (size2 - iUnclearedSize) + " of " + size2 + " are cleared");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public InterfaceCount[] getSortedInterfaceCounts(int i) {
            int i2;
            String str;
            if (i < 0) {
                throw new IllegalArgumentException("negative interface count");
            }
            HashMap map = new HashMap();
            ArrayList arrayList = new ArrayList();
            synchronized (BinderProxy.sProxyMap) {
                for (ArrayList<WeakReference<BinderProxy>> arrayList2 : this.mMainIndexValues) {
                    if (arrayList2 != null) {
                        arrayList.addAll(arrayList2);
                    }
                }
            }
            try {
                ActivityManager.getService().enableAppFreezer(false);
            } catch (RemoteException unused) {
                Log.e("Binder", "RemoteException while disabling app freezer");
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BinderProxy binderProxy = (BinderProxy) ((WeakReference) it.next()).get();
                if (binderProxy == null) {
                    str = "<cleared weak-ref>";
                } else {
                    try {
                        String interfaceDescriptor = binderProxy.getInterfaceDescriptor();
                        str = ((interfaceDescriptor == null || interfaceDescriptor.isEmpty()) && !binderProxy.isBinderAlive()) ? "<proxy to dead node>" : interfaceDescriptor;
                    } catch (Throwable unused2) {
                        str = "<exception during getDescriptor>";
                    }
                }
                Integer num = (Integer) map.get(str);
                if (num == null) {
                    map.put(str, 1);
                } else {
                    map.put(str, Integer.valueOf(num.intValue() + 1));
                }
            }
            try {
                ActivityManager.getService().enableAppFreezer(true);
            } catch (RemoteException unused3) {
                Log.e("Binder", "RemoteException while re-enabling app freezer");
            }
            Map.Entry[] entryArr = (Map.Entry[]) map.entrySet().toArray(new Map.Entry[map.size()]);
            Arrays.sort(entryArr, new Comparator() { // from class: android.os.-$$Lambda$BinderProxy$ProxyMap$aKNUVKkR8bNu2XRFxaO2PW1AFBA
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ((Integer) ((Map.Entry) obj2).getValue()).compareTo((Integer) ((Map.Entry) obj).getValue());
                }
            });
            int iMin = Math.min(i, entryArr.length);
            InterfaceCount[] interfaceCountArr = new InterfaceCount[iMin];
            for (i2 = 0; i2 < iMin; i2++) {
                interfaceCountArr[i2] = new InterfaceCount((String) entryArr[i2].getKey(), ((Integer) entryArr[i2].getValue()).intValue());
            }
            return interfaceCountArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dumpProxyInterfaceCounts() {
            InterfaceCount[] sortedInterfaceCounts = getSortedInterfaceCounts(10);
            Log.v("Binder", "BinderProxy descriptor histogram (top " + Integer.toString(10) + "):");
            int i = 0;
            while (i < sortedInterfaceCounts.length) {
                StringBuilder sb = new StringBuilder();
                sb.append(" #");
                int i2 = i + 1;
                sb.append(i2);
                sb.append(": ");
                sb.append(sortedInterfaceCounts[i]);
                Log.v("Binder", sb.toString());
                i = i2;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dumpPerUidProxyCounts() {
            SparseIntArray sparseIntArrayNGetBinderProxyPerUidCounts = BinderInternal.nGetBinderProxyPerUidCounts();
            if (sparseIntArrayNGetBinderProxyPerUidCounts.size() == 0) {
                return;
            }
            Log.d("Binder", "Per Uid Binder Proxy Counts:");
            for (int i = 0; i < sparseIntArrayNGetBinderProxyPerUidCounts.size(); i++) {
                Log.d("Binder", "UID : " + sparseIntArrayNGetBinderProxyPerUidCounts.keyAt(i) + "  count = " + sparseIntArrayNGetBinderProxyPerUidCounts.valueAt(i));
            }
        }
    }

    public static final class InterfaceCount {
        private final int mCount;
        private final String mInterfaceName;

        InterfaceCount(String str, int i) {
            this.mInterfaceName = str;
            this.mCount = i;
        }

        public String toString() {
            return this.mInterfaceName + " x" + Integer.toString(this.mCount);
        }
    }

    public static InterfaceCount[] getSortedInterfaceCounts(int i) {
        return sProxyMap.getSortedInterfaceCounts(i);
    }

    public static int getProxyCount() {
        int size;
        synchronized (sProxyMap) {
            size = sProxyMap.size();
        }
        return size;
    }

    public static void dumpProxyDebugInfo() {
        if (Build.IS_DEBUGGABLE) {
            sProxyMap.dumpProxyInterfaceCounts();
            sProxyMap.dumpPerUidProxyCounts();
        }
    }

    private static BinderProxy getInstance(long j, long j2) {
        synchronized (sProxyMap) {
            try {
                BinderProxy binderProxy = sProxyMap.get(j2);
                if (binderProxy != null) {
                    return binderProxy;
                }
                BinderProxy binderProxy2 = new BinderProxy(j);
                NoImagePreloadHolder.sRegistry.registerNativeAllocation(binderProxy2, j);
                sProxyMap.set(j2, binderProxy2);
                return binderProxy2;
            } catch (Throwable th) {
                NativeAllocationRegistry.applyFreeFunction(NoImagePreloadHolder.sNativeFinalizer, j);
                throw th;
            }
        }
    }

    private BinderProxy(long j) {
        this.mNativeData = j;
    }

    private static class NoImagePreloadHolder {
        public static final long sNativeFinalizer = BinderProxy.getNativeFinalizer();
        public static final NativeAllocationRegistry sRegistry = new NativeAllocationRegistry(BinderProxy.class.getClassLoader(), sNativeFinalizer, 1000);

        private NoImagePreloadHolder() {
        }
    }

    @Override // android.os.IBinder
    public boolean transact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Binder.checkParcel(this, i, parcel, "Unreasonably large binder buffer");
        if (this.mWarnOnBlocking && (i2 & 1) == 0 && Binder.sWarnOnBlockingOnCurrentThread.get().booleanValue()) {
            this.mWarnOnBlocking = false;
        }
        boolean zIsTracingEnabled = Binder.isTracingEnabled();
        if (zIsTracingEnabled) {
            Throwable th = new Throwable();
            Binder.getTransactionTracker().addTrace(th);
            StackTraceElement stackTraceElement = th.getStackTrace()[1];
            Trace.traceBegin(1L, stackTraceElement.getClassName() + MediaMetrics.SEPARATOR + stackTraceElement.getMethodName());
        }
        Binder.ProxyTransactListener proxyTransactListener = sTransactListener;
        Object obj = null;
        if (proxyTransactListener != null) {
            int callingWorkSourceUid = Binder.getCallingWorkSourceUid();
            Object objOnTransactStarted = proxyTransactListener.onTransactStarted(this, i, i2);
            int callingWorkSourceUid2 = Binder.getCallingWorkSourceUid();
            if (callingWorkSourceUid != callingWorkSourceUid2) {
                parcel.replaceCallingWorkSourceUid(callingWorkSourceUid2);
            }
            obj = objOnTransactStarted;
        }
        AppOpsManager.PausedNotedAppOpsCollection pausedNotedAppOpsCollectionPauseNotedAppOpsCollection = AppOpsManager.pauseNotedAppOpsCollection();
        if ((i2 & 1) == 0 && AppOpsManager.isListeningForOpNoted()) {
            i2 |= 2;
        }
        try {
            return transactNative(i, parcel, parcel2, i2);
        } finally {
            AppOpsManager.resumeNotedAppOpsCollection(pausedNotedAppOpsCollectionPauseNotedAppOpsCollection);
            if (proxyTransactListener != null) {
                proxyTransactListener.onTransactEnded(obj);
            }
            if (zIsTracingEnabled) {
                Trace.traceEnd(1L);
            }
        }
    }

    @Override // android.os.IBinder
    public void dump(FileDescriptor fileDescriptor, String[] strArr) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeFileDescriptor(fileDescriptor);
        parcelObtain.writeStringArray(strArr);
        try {
            transact(IBinder.DUMP_TRANSACTION, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    @Override // android.os.IBinder
    public void dumpAsync(FileDescriptor fileDescriptor, String[] strArr) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeFileDescriptor(fileDescriptor);
        parcelObtain.writeStringArray(strArr);
        try {
            transact(IBinder.DUMP_TRANSACTION, parcelObtain, parcelObtain2, 1);
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    @Override // android.os.IBinder
    public void shellCommand(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, FileDescriptor fileDescriptor3, String[] strArr, ShellCallback shellCallback, ResultReceiver resultReceiver) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeFileDescriptor(fileDescriptor);
        parcelObtain.writeFileDescriptor(fileDescriptor2);
        parcelObtain.writeFileDescriptor(fileDescriptor3);
        parcelObtain.writeStringArray(strArr);
        ShellCallback.writeToParcel(shellCallback, parcelObtain);
        resultReceiver.writeToParcel(parcelObtain, 0);
        try {
            transact(IBinder.SHELL_COMMAND_TRANSACTION, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    private static void sendDeathNotice(IBinder.DeathRecipient deathRecipient, IBinder iBinder) {
        try {
            deathRecipient.binderDied(iBinder);
        } catch (RuntimeException e) {
            Log.w("BinderNative", "Uncaught exception from death notification", e);
        }
    }
}
