package android.hardware.tetheroffload.control.V1_0;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface IOffloadControl extends IHwInterface {

    @FunctionalInterface
    public interface addDownstreamCallback {
        void onValues(boolean z, String str);
    }

    @FunctionalInterface
    public interface getForwardedStatsCallback {
        void onValues(long j, long j2);
    }

    @FunctionalInterface
    public interface initOffloadCallback {
        void onValues(boolean z, String str);
    }

    @FunctionalInterface
    public interface removeDownstreamCallback {
        void onValues(boolean z, String str);
    }

    @FunctionalInterface
    public interface setDataLimitCallback {
        void onValues(boolean z, String str);
    }

    @FunctionalInterface
    public interface setLocalPrefixesCallback {
        void onValues(boolean z, String str);
    }

    @FunctionalInterface
    public interface setUpstreamParametersCallback {
        void onValues(boolean z, String str);
    }

    @FunctionalInterface
    public interface stopOffloadCallback {
        void onValues(boolean z, String str);
    }

    void addDownstream(String str, String str2, addDownstreamCallback adddownstreamcallback) throws RemoteException;

    void getForwardedStats(String str, getForwardedStatsCallback getforwardedstatscallback) throws RemoteException;

    void initOffload(ITetheringOffloadCallback iTetheringOffloadCallback, initOffloadCallback initoffloadcallback) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    void removeDownstream(String str, String str2, removeDownstreamCallback removedownstreamcallback) throws RemoteException;

    void setDataLimit(String str, long j, setDataLimitCallback setdatalimitcallback) throws RemoteException;

    void setLocalPrefixes(ArrayList<String> arrayList, setLocalPrefixesCallback setlocalprefixescallback) throws RemoteException;

    void setUpstreamParameters(String str, String str2, String str3, ArrayList<String> arrayList, setUpstreamParametersCallback setupstreamparameterscallback) throws RemoteException;

    void stopOffload(stopOffloadCallback stopoffloadcallback) throws RemoteException;

    static IOffloadControl asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("android.hardware.tetheroffload.control@1.0::IOffloadControl");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IOffloadControl)) {
            return (IOffloadControl) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals("android.hardware.tetheroffload.control@1.0::IOffloadControl")) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static IOffloadControl getService(String str, boolean z) throws RemoteException {
        return asInterface(HwBinder.getService("android.hardware.tetheroffload.control@1.0::IOffloadControl", str, z));
    }

    static IOffloadControl getService(boolean z) throws RemoteException {
        return getService("default", z);
    }

    public static final class Proxy implements IOffloadControl {
        private IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.mRemote = iHwBinder;
        }

        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of android.hardware.tetheroffload.control@1.0::IOffloadControl]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl
        public void initOffload(ITetheringOffloadCallback iTetheringOffloadCallback, initOffloadCallback initoffloadcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hardware.tetheroffload.control@1.0::IOffloadControl");
            hwParcel.writeStrongBinder(iTetheringOffloadCallback == null ? null : iTetheringOffloadCallback.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                initoffloadcallback.onValues(hwParcel2.readBool(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl
        public void stopOffload(stopOffloadCallback stopoffloadcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hardware.tetheroffload.control@1.0::IOffloadControl");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                stopoffloadcallback.onValues(hwParcel2.readBool(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl
        public void setLocalPrefixes(ArrayList<String> arrayList, setLocalPrefixesCallback setlocalprefixescallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hardware.tetheroffload.control@1.0::IOffloadControl");
            hwParcel.writeStringVector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                setlocalprefixescallback.onValues(hwParcel2.readBool(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl
        public void getForwardedStats(String str, getForwardedStatsCallback getforwardedstatscallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hardware.tetheroffload.control@1.0::IOffloadControl");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getforwardedstatscallback.onValues(hwParcel2.readInt64(), hwParcel2.readInt64());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl
        public void setDataLimit(String str, long j, setDataLimitCallback setdatalimitcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hardware.tetheroffload.control@1.0::IOffloadControl");
            hwParcel.writeString(str);
            hwParcel.writeInt64(j);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(5, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                setdatalimitcallback.onValues(hwParcel2.readBool(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl
        public void setUpstreamParameters(String str, String str2, String str3, ArrayList<String> arrayList, setUpstreamParametersCallback setupstreamparameterscallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hardware.tetheroffload.control@1.0::IOffloadControl");
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            hwParcel.writeString(str3);
            hwParcel.writeStringVector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                setupstreamparameterscallback.onValues(hwParcel2.readBool(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl
        public void addDownstream(String str, String str2, addDownstreamCallback adddownstreamcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hardware.tetheroffload.control@1.0::IOffloadControl");
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                adddownstreamcallback.onValues(hwParcel2.readBool(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl
        public void removeDownstream(String str, String str2, removeDownstreamCallback removedownstreamcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hardware.tetheroffload.control@1.0::IOffloadControl");
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(8, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                removedownstreamcallback.onValues(hwParcel2.readBool(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        public String interfaceDescriptor() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }
    }
}
