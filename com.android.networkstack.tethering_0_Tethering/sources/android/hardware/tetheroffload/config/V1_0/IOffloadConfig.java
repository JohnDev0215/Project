package android.hardware.tetheroffload.config.V1_0;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface IOffloadConfig extends IHwInterface {

    @FunctionalInterface
    public interface setHandlesCallback {
        void onValues(boolean z, String str);
    }

    ArrayList<String> interfaceChain() throws RemoteException;

    void setHandles(NativeHandle nativeHandle, NativeHandle nativeHandle2, setHandlesCallback sethandlescallback) throws RemoteException;

    static IOffloadConfig asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("android.hardware.tetheroffload.config@1.0::IOffloadConfig");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IOffloadConfig)) {
            return (IOffloadConfig) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals("android.hardware.tetheroffload.config@1.0::IOffloadConfig")) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static IOffloadConfig getService(String str, boolean z) throws RemoteException {
        return asInterface(HwBinder.getService("android.hardware.tetheroffload.config@1.0::IOffloadConfig", str, z));
    }

    static IOffloadConfig getService(boolean z) throws RemoteException {
        return getService("default", z);
    }

    public static final class Proxy implements IOffloadConfig {
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
                return "[class or subclass of android.hardware.tetheroffload.config@1.0::IOffloadConfig]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // android.hardware.tetheroffload.config.V1_0.IOffloadConfig
        public void setHandles(NativeHandle nativeHandle, NativeHandle nativeHandle2, setHandlesCallback sethandlescallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hardware.tetheroffload.config@1.0::IOffloadConfig");
            hwParcel.writeNativeHandle(nativeHandle);
            hwParcel.writeNativeHandle(nativeHandle2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                sethandlescallback.onValues(hwParcel2.readBool(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.tetheroffload.config.V1_0.IOffloadConfig
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
