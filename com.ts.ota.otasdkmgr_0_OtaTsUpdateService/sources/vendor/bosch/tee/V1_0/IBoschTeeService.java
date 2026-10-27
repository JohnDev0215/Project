package vendor.bosch.tee.V1_0;

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
import com.engine.tbox.data.ReservationCharging;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface IBoschTeeService extends IBase {
    public static final String kInterfaceName = "vendor.bosch.tee@1.0::IBoschTeeService";

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    BoschResult createData(int i, int i2, int i3, int i4) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    BoschResult decryptAsymPrivkey(int i, int i2, int i3, int i4, ArrayList<Byte> arrayList) throws RemoteException;

    BoschResult decryptAsymPubkey(int i, int i2, int i3, int i4, ArrayList<Byte> arrayList) throws RemoteException;

    BoschResult decryptSymmKey(ArrayList<Byte> arrayList, ArrayList<Byte> arrayList2, int i) throws RemoteException;

    BoschResult encryptAsymPrivkey(int i, int i2, int i3, int i4, ArrayList<Byte> arrayList) throws RemoteException;

    BoschResult encryptAsymPubkey(int i, int i2, int i3, int i4, ArrayList<Byte> arrayList) throws RemoteException;

    BoschResult encryptSymmKey(ArrayList<Byte> arrayList, ArrayList<Byte> arrayList2, int i) throws RemoteException;

    BoschResult exportAsymPubkey(int i, int i2, int i3) throws RemoteException;

    BoschResult exportCert(int i, int i2) throws RemoteException;

    BoschResult exportSymmKey(int i, int i2) throws RemoteException;

    BoschResult genAsymKey(int i, int i2, int i3, int i4) throws RemoteException;

    BoschResult genP10(String str, int i, int i2, int i3, int i4) throws RemoteException;

    BoschResult genP12(String str, String str2, int i, int i2, int i3) throws RemoteException;

    BoschResult genSymmKey(int i, int i2, int i3) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    BoschResult getHSMVersion() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

    BoschResult getTAVersion() throws RemoteException;

    BoschResult getVersion() throws RemoteException;

    BoschResult hashFinal(ArrayList<Byte> arrayList) throws RemoteException;

    BoschResult hashInit(int i) throws RemoteException;

    BoschResult hashOnce(int i, ArrayList<Byte> arrayList) throws RemoteException;

    BoschResult hashUpdate(ArrayList<Byte> arrayList, ArrayList<Byte> arrayList2) throws RemoteException;

    BoschResult hsmOta(String str) throws RemoteException;

    BoschResult importAsymPrivKey(int i, String str, int i2, int i3) throws RemoteException;

    BoschResult importCert(int i, String str, int i2) throws RemoteException;

    BoschResult importSymmKey(int i, int i2, ArrayList<Byte> arrayList, int i3) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<String> interfaceChain() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    String interfaceDescriptor() throws RemoteException;

    BoschResult isASymKeyExist(int i) throws RemoteException;

    BoschResult isCertExist(int i) throws RemoteException;

    BoschResult isDataExist(int i) throws RemoteException;

    BoschResult isHsmExist() throws RemoteException;

    BoschResult isRpmbOn() throws RemoteException;

    BoschResult isSymmKeyExist(int i) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void notifySyspropsChanged() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void ping() throws RemoteException;

    BoschResult readData(int i, int i2, int i3) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    BoschResult signHash(ArrayList<Byte> arrayList, int i, int i2, int i3, int i4, int i5) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    BoschResult verifyCert(int i, String str, int i2, int i3) throws RemoteException;

    BoschResult verifySignHash(ArrayList<Byte> arrayList, ArrayList<Byte> arrayList2, int i, int i2, int i3, int i4, int i5) throws RemoteException;

    BoschResult writeData(int i, int i2, ArrayList<Byte> arrayList) throws RemoteException;

    static IBoschTeeService asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof IBoschTeeService)) {
            return (IBoschTeeService) iface;
        }
        IBoschTeeService proxy = new Proxy(binder);
        try {
            for (String descriptor : proxy.interfaceChain()) {
                if (descriptor.equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (RemoteException e) {
        }
        return null;
    }

    static IBoschTeeService castFrom(IHwInterface iface) {
        if (iface == null) {
            return null;
        }
        return asInterface(iface.asBinder());
    }

    static IBoschTeeService getService(String serviceName, boolean retry) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName, retry));
    }

    static IBoschTeeService getService(boolean retry) throws RemoteException {
        return getService("default", retry);
    }

    static IBoschTeeService getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static IBoschTeeService getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IBoschTeeService {
        private IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            Objects.requireNonNull(remote);
            this.mRemote = remote;
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of vendor.bosch.tee@1.0::IBoschTeeService]@Proxy";
            }
        }

        public final boolean equals(Object other) {
            return HidlSupport.interfacesEqual(this, other);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult isHsmExist() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult getVersion() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult getTAVersion() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult getHSMVersion() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult genAsymKey(int key_type, int key_len, int privkey_id, int pubkey_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(key_len);
            _hidl_request.writeInt32(privkey_id);
            _hidl_request.writeInt32(pubkey_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult importAsymPrivKey(int key_type, String pem_data, int privkey_id, int pubkey_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeString(pem_data);
            _hidl_request.writeInt32(privkey_id);
            _hidl_request.writeInt32(pubkey_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult exportAsymPubkey(int pubkey_id, int key_type, int format) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(pubkey_id);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(format);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult encryptAsymPubkey(int pubkey_id, int key_type, int bit_length, int padding, ArrayList<Byte> plain_data) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(pubkey_id);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(bit_length);
            _hidl_request.writeInt32(padding);
            _hidl_request.writeInt8Vector(plain_data);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult decryptAsymPrivkey(int privkey_id, int key_type, int bit_length, int padding, ArrayList<Byte> enc_data) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(privkey_id);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(bit_length);
            _hidl_request.writeInt32(padding);
            _hidl_request.writeInt8Vector(enc_data);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult encryptAsymPrivkey(int privkey_id, int key_type, int bit_length, int padding, ArrayList<Byte> plain_data) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(privkey_id);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(bit_length);
            _hidl_request.writeInt32(padding);
            _hidl_request.writeInt8Vector(plain_data);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult decryptAsymPubkey(int pubkey_id, int key_type, int bit_length, int padding, ArrayList<Byte> enc_data) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(pubkey_id);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(bit_length);
            _hidl_request.writeInt32(padding);
            _hidl_request.writeInt8Vector(enc_data);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult signHash(ArrayList<Byte> hash_data, int key_type, int bit_length, int padding, int digest, int privkey_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt8Vector(hash_data);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(bit_length);
            _hidl_request.writeInt32(padding);
            _hidl_request.writeInt32(digest);
            _hidl_request.writeInt32(privkey_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult verifySignHash(ArrayList<Byte> signed_data, ArrayList<Byte> hash_data, int key_type, int bit_length, int padding, int digest, int pubkey_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt8Vector(signed_data);
            _hidl_request.writeInt8Vector(hash_data);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(bit_length);
            _hidl_request.writeInt32(padding);
            _hidl_request.writeInt32(digest);
            _hidl_request.writeInt32(pubkey_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult isASymKeyExist(int key_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(key_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult genSymmKey(int key_type, int bit_length, int symkey_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(bit_length);
            _hidl_request.writeInt32(symkey_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult importSymmKey(int key_type, int bit_length, ArrayList<Byte> key_data, int symkey_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(bit_length);
            _hidl_request.writeInt8Vector(key_data);
            _hidl_request.writeInt32(symkey_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult exportSymmKey(int key_type, int symkey_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(symkey_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(17, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult encryptSymmKey(ArrayList<Byte> plan_data, ArrayList<Byte> iv, int symkey_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt8Vector(plan_data);
            _hidl_request.writeInt8Vector(iv);
            _hidl_request.writeInt32(symkey_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(18, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult decryptSymmKey(ArrayList<Byte> enc_data, ArrayList<Byte> iv, int symkey_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt8Vector(enc_data);
            _hidl_request.writeInt8Vector(iv);
            _hidl_request.writeInt32(symkey_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(19, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult isSymmKeyExist(int symkey_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(symkey_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(20, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult importCert(int key_type, String cert_pem, int cert_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeString(cert_pem);
            _hidl_request.writeInt32(cert_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(21, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult exportCert(int key_type, int cert_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(cert_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(22, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult verifyCert(int key_type, String cert_pem, int root_ca_id, int sub_ca_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeString(cert_pem);
            _hidl_request.writeInt32(root_ca_id);
            _hidl_request.writeInt32(sub_ca_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(23, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult isCertExist(int cert_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(cert_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(24, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult genP10(String subject, int key_type, int digest, int privkey_id, int pubkey_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeString(subject);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(digest);
            _hidl_request.writeInt32(privkey_id);
            _hidl_request.writeInt32(pubkey_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(25, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult genP12(String name, String passwd, int key_type, int privkey_id, int cert_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeString(name);
            _hidl_request.writeString(passwd);
            _hidl_request.writeInt32(key_type);
            _hidl_request.writeInt32(privkey_id);
            _hidl_request.writeInt32(cert_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(26, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult hashInit(int digest) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(digest);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(27, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult hashUpdate(ArrayList<Byte> hash_handle, ArrayList<Byte> raw_data) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt8Vector(hash_handle);
            _hidl_request.writeInt8Vector(raw_data);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(28, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult hashFinal(ArrayList<Byte> hash_handle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt8Vector(hash_handle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(29, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult hashOnce(int digest, ArrayList<Byte> raw_data) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(digest);
            _hidl_request.writeInt8Vector(raw_data);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(30, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult readData(int data_id, int read_pos, int read_len) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(data_id);
            _hidl_request.writeInt32(read_pos);
            _hidl_request.writeInt32(read_len);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(31, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult isDataExist(int data_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(data_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(32, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult createData(int data_id, int size, int type, int encrypt) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(data_id);
            _hidl_request.writeInt32(size);
            _hidl_request.writeInt32(type);
            _hidl_request.writeInt32(encrypt);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(33, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult writeData(int data_id, int write_pos, ArrayList<Byte> input) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeInt32(data_id);
            _hidl_request.writeInt32(write_pos);
            _hidl_request.writeInt8Vector(input);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(34, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult hsmOta(String firmware_path) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            _hidl_request.writeString(firmware_path);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(35, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService
        public BoschResult isRpmbOn() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBoschTeeService.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(36, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                BoschResult _hidl_out_result = new BoschResult();
                _hidl_out_result.readFromParcel(_hidl_reply);
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256067662, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<String> _hidl_out_descriptors = _hidl_reply.readStringVector();
                return _hidl_out_descriptors;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle fd, ArrayList<String> options) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            _hidl_request.writeNativeHandle(fd);
            _hidl_request.writeStringVector(options);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256131655, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public String interfaceDescriptor() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256136003, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_descriptor = _hidl_reply.readString();
                return _hidl_out_descriptor;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public ArrayList<byte[]> getHashChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256398152, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<byte[]> _hidl_out_hashchain = new ArrayList<>();
                HwBlob _hidl_blob = _hidl_reply.readBuffer(16L);
                int _hidl_vec_size = _hidl_blob.getInt32(8L);
                HwBlob childBlob = _hidl_reply.readEmbeddedBuffer(_hidl_vec_size * 32, _hidl_blob.handle(), 0L, true);
                _hidl_out_hashchain.clear();
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                    byte[] _hidl_vec_element = new byte[32];
                    long _hidl_array_offset_1 = _hidl_index_0 * 32;
                    childBlob.copyToInt8Array(_hidl_array_offset_1, _hidl_vec_element, 32);
                    _hidl_out_hashchain.add(_hidl_vec_element);
                }
                return _hidl_out_hashchain;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public void setHALInstrumentation() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256462420, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException {
            return this.mRemote.linkToDeath(recipient, cookie);
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public void ping() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256921159, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public DebugInfo getDebugInfo() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257049926, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                DebugInfo _hidl_out_info = new DebugInfo();
                _hidl_out_info.readFromParcel(_hidl_reply);
                return _hidl_out_info;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public void notifySyspropsChanged() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257120595, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(recipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IBoschTeeService {
        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IBoschTeeService.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle fd, ArrayList<String> options) {
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IBoschTeeService.kInterfaceName;
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, ReservationCharging.MAX_MIN, 24, -54, 76}));
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo info = new DebugInfo();
            info.pid = HidlSupport.getPidIfSharable();
            info.ptr = 0L;
            info.arch = 0;
            return info;
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        @Override // vendor.bosch.tee.V1_0.IBoschTeeService, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) {
            return true;
        }

        public IHwInterface queryLocalInterface(String descriptor) {
            if (IBoschTeeService.kInterfaceName.equals(descriptor)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String serviceName) throws RemoteException {
            registerService(serviceName);
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        public void onTransact(int _hidl_code, HwParcel _hidl_request, HwParcel _hidl_reply, int _hidl_flags) throws RemoteException {
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    BoschResult _hidl_out_result = isHsmExist();
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 2:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    BoschResult _hidl_out_result2 = getVersion();
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result2.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 3:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    BoschResult _hidl_out_result3 = getTAVersion();
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result3.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 4:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    BoschResult _hidl_out_result4 = getHSMVersion();
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result4.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 5:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int key_type = _hidl_request.readInt32();
                    int key_len = _hidl_request.readInt32();
                    int privkey_id = _hidl_request.readInt32();
                    int pubkey_id = _hidl_request.readInt32();
                    BoschResult _hidl_out_result5 = genAsymKey(key_type, key_len, privkey_id, pubkey_id);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result5.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 6:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int key_type2 = _hidl_request.readInt32();
                    String pem_data = _hidl_request.readString();
                    int privkey_id2 = _hidl_request.readInt32();
                    int pubkey_id2 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result6 = importAsymPrivKey(key_type2, pem_data, privkey_id2, pubkey_id2);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result6.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 7:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int pubkey_id3 = _hidl_request.readInt32();
                    int key_type3 = _hidl_request.readInt32();
                    int format = _hidl_request.readInt32();
                    BoschResult _hidl_out_result7 = exportAsymPubkey(pubkey_id3, key_type3, format);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result7.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 8:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int pubkey_id4 = _hidl_request.readInt32();
                    int key_type4 = _hidl_request.readInt32();
                    int bit_length = _hidl_request.readInt32();
                    int padding = _hidl_request.readInt32();
                    ArrayList<Byte> plain_data = _hidl_request.readInt8Vector();
                    BoschResult _hidl_out_result8 = encryptAsymPubkey(pubkey_id4, key_type4, bit_length, padding, plain_data);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result8.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 9:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int privkey_id3 = _hidl_request.readInt32();
                    int key_type5 = _hidl_request.readInt32();
                    int bit_length2 = _hidl_request.readInt32();
                    int padding2 = _hidl_request.readInt32();
                    ArrayList<Byte> enc_data = _hidl_request.readInt8Vector();
                    BoschResult _hidl_out_result9 = decryptAsymPrivkey(privkey_id3, key_type5, bit_length2, padding2, enc_data);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result9.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 10:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int privkey_id4 = _hidl_request.readInt32();
                    int key_type6 = _hidl_request.readInt32();
                    int bit_length3 = _hidl_request.readInt32();
                    int padding3 = _hidl_request.readInt32();
                    ArrayList<Byte> plain_data2 = _hidl_request.readInt8Vector();
                    BoschResult _hidl_out_result10 = encryptAsymPrivkey(privkey_id4, key_type6, bit_length3, padding3, plain_data2);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result10.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 11:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int pubkey_id5 = _hidl_request.readInt32();
                    int key_type7 = _hidl_request.readInt32();
                    int bit_length4 = _hidl_request.readInt32();
                    int padding4 = _hidl_request.readInt32();
                    ArrayList<Byte> enc_data2 = _hidl_request.readInt8Vector();
                    BoschResult _hidl_out_result11 = decryptAsymPubkey(pubkey_id5, key_type7, bit_length4, padding4, enc_data2);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result11.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 12:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    ArrayList<Byte> hash_data = _hidl_request.readInt8Vector();
                    int key_type8 = _hidl_request.readInt32();
                    int bit_length5 = _hidl_request.readInt32();
                    int padding5 = _hidl_request.readInt32();
                    int digest = _hidl_request.readInt32();
                    int privkey_id5 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result12 = signHash(hash_data, key_type8, bit_length5, padding5, digest, privkey_id5);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result12.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 13:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    ArrayList<Byte> signed_data = _hidl_request.readInt8Vector();
                    ArrayList<Byte> hash_data2 = _hidl_request.readInt8Vector();
                    int key_type9 = _hidl_request.readInt32();
                    int bit_length6 = _hidl_request.readInt32();
                    int padding6 = _hidl_request.readInt32();
                    int digest2 = _hidl_request.readInt32();
                    int pubkey_id6 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result13 = verifySignHash(signed_data, hash_data2, key_type9, bit_length6, padding6, digest2, pubkey_id6);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result13.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 14:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int key_id = _hidl_request.readInt32();
                    BoschResult _hidl_out_result14 = isASymKeyExist(key_id);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result14.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 15:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int key_type10 = _hidl_request.readInt32();
                    int bit_length7 = _hidl_request.readInt32();
                    int symkey_id = _hidl_request.readInt32();
                    BoschResult _hidl_out_result15 = genSymmKey(key_type10, bit_length7, symkey_id);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result15.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 16:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int key_type11 = _hidl_request.readInt32();
                    int bit_length8 = _hidl_request.readInt32();
                    ArrayList<Byte> key_data = _hidl_request.readInt8Vector();
                    int symkey_id2 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result16 = importSymmKey(key_type11, bit_length8, key_data, symkey_id2);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result16.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 17:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int key_type12 = _hidl_request.readInt32();
                    int symkey_id3 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result17 = exportSymmKey(key_type12, symkey_id3);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result17.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 18:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    ArrayList<Byte> plan_data = _hidl_request.readInt8Vector();
                    ArrayList<Byte> iv = _hidl_request.readInt8Vector();
                    int symkey_id4 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result18 = encryptSymmKey(plan_data, iv, symkey_id4);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result18.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 19:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    ArrayList<Byte> enc_data3 = _hidl_request.readInt8Vector();
                    ArrayList<Byte> iv2 = _hidl_request.readInt8Vector();
                    int symkey_id5 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result19 = decryptSymmKey(enc_data3, iv2, symkey_id5);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result19.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 20:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int symkey_id6 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result20 = isSymmKeyExist(symkey_id6);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result20.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 21:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int key_type13 = _hidl_request.readInt32();
                    String cert_pem = _hidl_request.readString();
                    int cert_id = _hidl_request.readInt32();
                    BoschResult _hidl_out_result21 = importCert(key_type13, cert_pem, cert_id);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result21.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 22:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int key_type14 = _hidl_request.readInt32();
                    int cert_id2 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result22 = exportCert(key_type14, cert_id2);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result22.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 23:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int key_type15 = _hidl_request.readInt32();
                    String cert_pem2 = _hidl_request.readString();
                    int root_ca_id = _hidl_request.readInt32();
                    int sub_ca_id = _hidl_request.readInt32();
                    BoschResult _hidl_out_result23 = verifyCert(key_type15, cert_pem2, root_ca_id, sub_ca_id);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result23.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 24:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int cert_id3 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result24 = isCertExist(cert_id3);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result24.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 25:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    String subject = _hidl_request.readString();
                    int key_type16 = _hidl_request.readInt32();
                    int digest3 = _hidl_request.readInt32();
                    int privkey_id6 = _hidl_request.readInt32();
                    int pubkey_id7 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result25 = genP10(subject, key_type16, digest3, privkey_id6, pubkey_id7);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result25.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 26:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    String name = _hidl_request.readString();
                    String passwd = _hidl_request.readString();
                    int key_type17 = _hidl_request.readInt32();
                    int privkey_id7 = _hidl_request.readInt32();
                    int cert_id4 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result26 = genP12(name, passwd, key_type17, privkey_id7, cert_id4);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result26.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 27:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int digest4 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result27 = hashInit(digest4);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result27.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 28:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    ArrayList<Byte> hash_handle = _hidl_request.readInt8Vector();
                    ArrayList<Byte> raw_data = _hidl_request.readInt8Vector();
                    BoschResult _hidl_out_result28 = hashUpdate(hash_handle, raw_data);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result28.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 29:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    ArrayList<Byte> hash_handle2 = _hidl_request.readInt8Vector();
                    BoschResult _hidl_out_result29 = hashFinal(hash_handle2);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result29.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 30:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int digest5 = _hidl_request.readInt32();
                    ArrayList<Byte> raw_data2 = _hidl_request.readInt8Vector();
                    BoschResult _hidl_out_result30 = hashOnce(digest5, raw_data2);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result30.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 31:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int data_id = _hidl_request.readInt32();
                    int read_pos = _hidl_request.readInt32();
                    int read_len = _hidl_request.readInt32();
                    BoschResult _hidl_out_result31 = readData(data_id, read_pos, read_len);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result31.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 32:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int data_id2 = _hidl_request.readInt32();
                    BoschResult _hidl_out_result32 = isDataExist(data_id2);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result32.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 33:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int data_id3 = _hidl_request.readInt32();
                    int size = _hidl_request.readInt32();
                    int type = _hidl_request.readInt32();
                    int encrypt = _hidl_request.readInt32();
                    BoschResult _hidl_out_result33 = createData(data_id3, size, type, encrypt);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result33.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 34:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    int data_id4 = _hidl_request.readInt32();
                    int write_pos = _hidl_request.readInt32();
                    ArrayList<Byte> input = _hidl_request.readInt8Vector();
                    BoschResult _hidl_out_result34 = writeData(data_id4, write_pos, input);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result34.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 35:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    String firmware_path = _hidl_request.readString();
                    BoschResult _hidl_out_result35 = hsmOta(firmware_path);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result35.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 36:
                    _hidl_request.enforceInterface(IBoschTeeService.kInterfaceName);
                    BoschResult _hidl_out_result36 = isRpmbOn();
                    _hidl_reply.writeStatus(0);
                    _hidl_out_result36.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                default:
                    switch (_hidl_code) {
                        case 256067662:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            ArrayList<String> _hidl_out_descriptors = interfaceChain();
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeStringVector(_hidl_out_descriptors);
                            _hidl_reply.send();
                            return;
                        case 256131655:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            NativeHandle fd = _hidl_request.readNativeHandle();
                            ArrayList<String> options = _hidl_request.readStringVector();
                            debug(fd, options);
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.send();
                            return;
                        case 256136003:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            String _hidl_out_descriptor = interfaceDescriptor();
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeString(_hidl_out_descriptor);
                            _hidl_reply.send();
                            return;
                        case 256398152:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            ArrayList<byte[]> _hidl_out_hashchain = getHashChain();
                            _hidl_reply.writeStatus(0);
                            HwBlob _hidl_blob = new HwBlob(16);
                            int _hidl_vec_size = _hidl_out_hashchain.size();
                            _hidl_blob.putInt32(8L, _hidl_vec_size);
                            _hidl_blob.putBool(12L, false);
                            HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
                            for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                                long _hidl_array_offset_1 = _hidl_index_0 * 32;
                                byte[] _hidl_array_item_1 = _hidl_out_hashchain.get(_hidl_index_0);
                                if (_hidl_array_item_1 == null || _hidl_array_item_1.length != 32) {
                                    throw new IllegalArgumentException("Array element is not of the expected length");
                                }
                                childBlob.putInt8Array(_hidl_array_offset_1, _hidl_array_item_1);
                            }
                            _hidl_blob.putBlob(0L, childBlob);
                            _hidl_reply.writeBuffer(_hidl_blob);
                            _hidl_reply.send();
                            return;
                        case 256462420:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            setHALInstrumentation();
                            return;
                        case 256921159:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            ping();
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.send();
                            return;
                        case 257049926:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            DebugInfo _hidl_out_info = getDebugInfo();
                            _hidl_reply.writeStatus(0);
                            _hidl_out_info.writeToParcel(_hidl_reply);
                            _hidl_reply.send();
                            return;
                        case 257120595:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            notifySyspropsChanged();
                            return;
                        default:
                            return;
                    }
            }
        }
    }
}
