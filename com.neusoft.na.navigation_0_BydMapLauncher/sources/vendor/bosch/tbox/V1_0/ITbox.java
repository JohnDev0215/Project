package vendor.bosch.tbox.V1_0;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import com.ecarx.xui.adaptapi.hvac.specific.Define;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.signers.PSSSigner;

/* JADX INFO: loaded from: classes4.dex */
public interface ITbox extends IBase {
    public static final String kInterfaceName = "vendor.bosch.tbox@1.0::ITbox";

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    byte getApn1State() throws RemoteException;

    byte getApn2State() throws RemoteException;

    byte getApn3State() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    String getExternalIccid() throws RemoteException;

    String getExternalImsi() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

    String getImei() throws RemoteException;

    String getInternalIccid() throws RemoteException;

    String getInternalImsi() throws RemoteException;

    String getMccMnc() throws RemoteException;

    byte getNetworkState() throws RemoteException;

    byte getRealApn2State() throws RemoteException;

    byte getSignalStrength() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<String> interfaceChain() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    String interfaceDescriptor() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void notifySyspropsChanged() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void ping() throws RemoteException;

    int registerTboxGnssCallback(ITboxGnssCallback iTboxGnssCallback) throws RemoteException;

    int registerTboxRilCallback(ITboxRilCallback iTboxRilCallback) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    int unregisterTboxGnssCallback(ITboxGnssCallback iTboxGnssCallback) throws RemoteException;

    int unregisterTboxRilCallback(ITboxRilCallback iTboxRilCallback) throws RemoteException;

    static ITbox asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface(kInterfaceName);
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof ITbox)) {
            return (ITbox) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static ITbox castFrom(IHwInterface iHwInterface) {
        if (iHwInterface == null) {
            return null;
        }
        return asInterface(iHwInterface.asBinder());
    }

    static ITbox getService(String str, boolean z) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str, z));
    }

    static ITbox getService(boolean z) throws RemoteException {
        return getService("default", z);
    }

    static ITbox getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str));
    }

    static ITbox getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements ITbox {
        private IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.bosch.tbox@1.0::ITbox]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public int registerTboxGnssCallback(ITboxGnssCallback iTboxGnssCallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            hwParcel.writeStrongBinder(iTboxGnssCallback == null ? null : iTboxGnssCallback.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public int unregisterTboxGnssCallback(ITboxGnssCallback iTboxGnssCallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            hwParcel.writeStrongBinder(iTboxGnssCallback == null ? null : iTboxGnssCallback.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public int registerTboxRilCallback(ITboxRilCallback iTboxRilCallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            hwParcel.writeStrongBinder(iTboxRilCallback == null ? null : iTboxRilCallback.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public int unregisterTboxRilCallback(ITboxRilCallback iTboxRilCallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            hwParcel.writeStrongBinder(iTboxRilCallback == null ? null : iTboxRilCallback.asBinder());
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public byte getNetworkState() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(5, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt8();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public byte getSignalStrength() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt8();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public String getMccMnc() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public byte getApn1State() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(8, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt8();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public byte getApn2State() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(9, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt8();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public byte getApn3State() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(10, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt8();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public byte getRealApn2State() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(11, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt8();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public String getExternalImsi() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(12, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public String getExternalIccid() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(13, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public String getImei() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(14, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public String getInternalImsi() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(15, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox
        public String getInternalIccid() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(ITbox.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(16, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
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

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            hwParcel.writeNativeHandle(nativeHandle);
            hwParcel.writeStringVector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256131655, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public String interfaceDescriptor() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
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

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public ArrayList<byte[]> getHashChain() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256398152, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                ArrayList<byte[]> arrayList = new ArrayList<>();
                HwBlob buffer = hwParcel2.readBuffer(16L);
                int int32 = buffer.getInt32(8L);
                HwBlob embeddedBuffer = hwParcel2.readEmbeddedBuffer(int32 * 32, buffer.handle(), 0L, true);
                arrayList.clear();
                for (int i = 0; i < int32; i++) {
                    byte[] bArr = new byte[32];
                    embeddedBuffer.copyToInt8Array(i * 32, bArr, 32);
                    arrayList.add(bArr);
                }
                return arrayList;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public void setHALInstrumentation() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256462420, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException {
            return this.mRemote.linkToDeath(deathRecipient, j);
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public void ping() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256921159, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public DebugInfo getDebugInfo() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(257049926, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                DebugInfo debugInfo = new DebugInfo();
                debugInfo.readFromParcel(hwParcel2);
                return debugInfo;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public void notifySyspropsChanged() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(257120595, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(deathRecipient);
        }
    }

    public static abstract class Stub extends HwBinder implements ITbox {
        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return ITbox.kInterfaceName;
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(ITbox.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, ByteCompanionObject.MAX_VALUE, -41, -98, -48, Define.cSubCodeSetADS, -6, -123, PSSSigner.TRAILER_IMPLICIT, 73, -108, 38, -83, -82, Define.cSubCodeSetAcEco, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo debugInfo = new DebugInfo();
            debugInfo.pid = HidlSupport.getPidIfSharable();
            debugInfo.ptr = 0L;
            debugInfo.arch = 0;
            return debugInfo;
        }

        @Override // vendor.bosch.tbox.V1_0.ITbox, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        public IHwInterface queryLocalInterface(String str) {
            if (ITbox.kInterfaceName.equals(str)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String str) throws RemoteException {
            registerService(str);
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        public void onTransact(int i, HwParcel hwParcel, HwParcel hwParcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    int iRegisterTboxGnssCallback = registerTboxGnssCallback(ITboxGnssCallback.asInterface(hwParcel.readStrongBinder()));
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iRegisterTboxGnssCallback);
                    hwParcel2.send();
                    return;
                case 2:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    int iUnregisterTboxGnssCallback = unregisterTboxGnssCallback(ITboxGnssCallback.asInterface(hwParcel.readStrongBinder()));
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iUnregisterTboxGnssCallback);
                    hwParcel2.send();
                    return;
                case 3:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    int iRegisterTboxRilCallback = registerTboxRilCallback(ITboxRilCallback.asInterface(hwParcel.readStrongBinder()));
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iRegisterTboxRilCallback);
                    hwParcel2.send();
                    return;
                case 4:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    int iUnregisterTboxRilCallback = unregisterTboxRilCallback(ITboxRilCallback.asInterface(hwParcel.readStrongBinder()));
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iUnregisterTboxRilCallback);
                    hwParcel2.send();
                    return;
                case 5:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    byte networkState = getNetworkState();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt8(networkState);
                    hwParcel2.send();
                    return;
                case 6:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    byte signalStrength = getSignalStrength();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt8(signalStrength);
                    hwParcel2.send();
                    return;
                case 7:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    String mccMnc = getMccMnc();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeString(mccMnc);
                    hwParcel2.send();
                    return;
                case 8:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    byte apn1State = getApn1State();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt8(apn1State);
                    hwParcel2.send();
                    return;
                case 9:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    byte apn2State = getApn2State();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt8(apn2State);
                    hwParcel2.send();
                    return;
                case 10:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    byte apn3State = getApn3State();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt8(apn3State);
                    hwParcel2.send();
                    return;
                case 11:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    byte realApn2State = getRealApn2State();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt8(realApn2State);
                    hwParcel2.send();
                    return;
                case 12:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    String externalImsi = getExternalImsi();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeString(externalImsi);
                    hwParcel2.send();
                    return;
                case 13:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    String externalIccid = getExternalIccid();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeString(externalIccid);
                    hwParcel2.send();
                    return;
                case 14:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    String imei = getImei();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeString(imei);
                    hwParcel2.send();
                    return;
                case 15:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    String internalImsi = getInternalImsi();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeString(internalImsi);
                    hwParcel2.send();
                    return;
                case 16:
                    hwParcel.enforceInterface(ITbox.kInterfaceName);
                    String internalIccid = getInternalIccid();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeString(internalIccid);
                    hwParcel2.send();
                    return;
                default:
                    switch (i) {
                        case 256067662:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            ArrayList<String> arrayListInterfaceChain = interfaceChain();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeStringVector(arrayListInterfaceChain);
                            hwParcel2.send();
                            return;
                        case 256131655:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            debug(hwParcel.readNativeHandle(), hwParcel.readStringVector());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 256136003:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            String strInterfaceDescriptor = interfaceDescriptor();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strInterfaceDescriptor);
                            hwParcel2.send();
                            return;
                        case 256398152:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            ArrayList<byte[]> hashChain = getHashChain();
                            hwParcel2.writeStatus(0);
                            HwBlob hwBlob = new HwBlob(16);
                            int size = hashChain.size();
                            hwBlob.putInt32(8L, size);
                            hwBlob.putBool(12L, false);
                            HwBlob hwBlob2 = new HwBlob(size * 32);
                            for (int i3 = 0; i3 < size; i3++) {
                                long j = i3 * 32;
                                byte[] bArr = hashChain.get(i3);
                                if (bArr == null || bArr.length != 32) {
                                    throw new IllegalArgumentException("Array element is not of the expected length");
                                }
                                hwBlob2.putInt8Array(j, bArr);
                            }
                            hwBlob.putBlob(0L, hwBlob2);
                            hwParcel2.writeBuffer(hwBlob);
                            hwParcel2.send();
                            return;
                        case 256462420:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            setHALInstrumentation();
                            return;
                        case 256921159:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            ping();
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 257049926:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            DebugInfo debugInfo = getDebugInfo();
                            hwParcel2.writeStatus(0);
                            debugInfo.writeToParcel(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 257120595:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            notifySyspropsChanged();
                            return;
                        default:
                            return;
                    }
            }
        }
    }
}
