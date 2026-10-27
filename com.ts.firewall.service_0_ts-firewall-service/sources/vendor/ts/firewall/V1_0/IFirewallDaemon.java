package vendor.ts.firewall.V1_0;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface IFirewallDaemon extends IBase {
    public static final String kInterfaceName = "vendor.ts.firewall@1.0::IFirewallDaemon";

    void addApplicationRule(int i, boolean z, ArrayList<ApplicationRule> arrayList) throws RemoteException;

    void addIpRule(int i, boolean z, ArrayList<String> arrayList, ArrayList<String> arrayList2) throws RemoteException;

    void addPortRule(int i, boolean z, ArrayList<Integer> arrayList, ArrayList<String> arrayList2) throws RemoteException;

    void addUrlRule(int i, boolean z, ArrayList<String> arrayList) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

    ArrayList<String> getInterfaces() throws RemoteException;

    void getLogs(int i, long j, long j2) throws RemoteException;

    void getRules(int i, int i2) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<String> interfaceChain() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    String interfaceDescriptor() throws RemoteException;

    boolean isFirewallEnabled() throws RemoteException;

    boolean isWhiteListEnabled() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void notifySyspropsChanged() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void ping() throws RemoteException;

    boolean registerCallback(IFirewallDaemonListener iFirewallDaemonListener, IFirewallDaemonResponse iFirewallDaemonResponse) throws RemoteException;

    void removeApplicationRule(int i, boolean z, ArrayList<Integer> arrayList) throws RemoteException;

    void removeIpRule(int i, boolean z, ArrayList<String> arrayList, ArrayList<String> arrayList2) throws RemoteException;

    void removePortRule(int i, boolean z, ArrayList<Integer> arrayList, ArrayList<String> arrayList2) throws RemoteException;

    void removeUrlRule(int i, boolean z, ArrayList<String> arrayList) throws RemoteException;

    boolean setFirewallEnable(boolean z) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    boolean setWhiteListEnable(boolean z) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    static IFirewallDaemon asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof IFirewallDaemon)) {
            return (IFirewallDaemon) iface;
        }
        IFirewallDaemon proxy = new Proxy(binder);
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

    static IFirewallDaemon castFrom(IHwInterface iface) {
        if (iface == null) {
            return null;
        }
        return asInterface(iface.asBinder());
    }

    static IFirewallDaemon getService(String serviceName, boolean retry) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName, retry));
    }

    static IFirewallDaemon getService(boolean retry) throws RemoteException {
        return getService("default", retry);
    }

    static IFirewallDaemon getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static IFirewallDaemon getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IFirewallDaemon {
        private IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            Objects.requireNonNull(remote);
            this.mRemote = remote;
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of vendor.ts.firewall@1.0::IFirewallDaemon]@Proxy";
            }
        }

        public final boolean equals(Object other) {
            return HidlSupport.interfacesEqual(this, other);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public boolean registerCallback(IFirewallDaemonListener listener, IFirewallDaemonResponse response) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeStrongBinder(listener == null ? null : listener.asBinder());
            _hidl_request.writeStrongBinder(response != null ? response.asBinder() : null);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public boolean setFirewallEnable(boolean enable) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeBool(enable);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public boolean isFirewallEnabled() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public boolean setWhiteListEnable(boolean enable) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeBool(enable);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public boolean isWhiteListEnabled() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public ArrayList<String> getInterfaces() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<String> _hidl_out_ifaces = _hidl_reply.readStringVector();
                return _hidl_out_ifaces;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public void getRules(int serial, int type) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeInt32(type);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public void getLogs(int serial, long startTime, long endTime) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeInt64(startTime);
            _hidl_request.writeInt64(endTime);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public void addApplicationRule(int serial, boolean isWhiteList, ArrayList<ApplicationRule> uidRules) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeBool(isWhiteList);
            ApplicationRule.writeVectorToParcel(_hidl_request, uidRules);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public void removeApplicationRule(int serial, boolean isWhiteList, ArrayList<Integer> uids) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeBool(isWhiteList);
            _hidl_request.writeInt32Vector(uids);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public void addIpRule(int serial, boolean isWhiteList, ArrayList<String> ipaddress, ArrayList<String> ifaces) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeBool(isWhiteList);
            _hidl_request.writeStringVector(ipaddress);
            _hidl_request.writeStringVector(ifaces);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public void removeIpRule(int serial, boolean isWhiteList, ArrayList<String> ipaddress, ArrayList<String> ifaces) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeBool(isWhiteList);
            _hidl_request.writeStringVector(ipaddress);
            _hidl_request.writeStringVector(ifaces);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public void addPortRule(int serial, boolean isWhiteList, ArrayList<Integer> port, ArrayList<String> iface) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeBool(isWhiteList);
            _hidl_request.writeInt32Vector(port);
            _hidl_request.writeStringVector(iface);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public void removePortRule(int serial, boolean isWhiteList, ArrayList<Integer> port, ArrayList<String> iface) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeBool(isWhiteList);
            _hidl_request.writeInt32Vector(port);
            _hidl_request.writeStringVector(iface);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public void addUrlRule(int serial, boolean isWhiteList, ArrayList<String> url) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeBool(isWhiteList);
            _hidl_request.writeStringVector(url);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon
        public void removeUrlRule(int serial, boolean isWhiteList, ArrayList<String> url) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IFirewallDaemon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeBool(isWhiteList);
            _hidl_request.writeStringVector(url);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
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

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
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

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
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

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
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

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
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

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException {
            return this.mRemote.linkToDeath(recipient, cookie);
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
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

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
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

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
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

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(recipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IFirewallDaemon {
        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IFirewallDaemon.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle fd, ArrayList<String> options) {
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IFirewallDaemon.kInterfaceName;
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo info = new DebugInfo();
            info.pid = HidlSupport.getPidIfSharable();
            info.ptr = 0L;
            info.arch = 0;
            return info;
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemon, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) {
            return true;
        }

        public IHwInterface queryLocalInterface(String descriptor) {
            if (IFirewallDaemon.kInterfaceName.equals(descriptor)) {
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
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    IFirewallDaemonListener listener = IFirewallDaemonListener.asInterface(_hidl_request.readStrongBinder());
                    IFirewallDaemonResponse response = IFirewallDaemonResponse.asInterface(_hidl_request.readStrongBinder());
                    boolean _hidl_out_success = registerCallback(listener, response);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success);
                    _hidl_reply.send();
                    return;
                case 2:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    boolean enable = _hidl_request.readBool();
                    boolean _hidl_out_success2 = setFirewallEnable(enable);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success2);
                    _hidl_reply.send();
                    return;
                case ResultType.ADD_IP_RULE /* 3 */:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    boolean _hidl_out_success3 = isFirewallEnabled();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success3);
                    _hidl_reply.send();
                    return;
                case ResultType.REMOVE_IP_RULE /* 4 */:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    boolean enable2 = _hidl_request.readBool();
                    boolean _hidl_out_success4 = setWhiteListEnable(enable2);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success4);
                    _hidl_reply.send();
                    return;
                case ResultType.ADD_PORT_RULE /* 5 */:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    boolean _hidl_out_success5 = isWhiteListEnabled();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success5);
                    _hidl_reply.send();
                    return;
                case ResultType.REMOVE_PORT_RULE /* 6 */:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    ArrayList<String> _hidl_out_ifaces = getInterfaces();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeStringVector(_hidl_out_ifaces);
                    _hidl_reply.send();
                    return;
                case ResultType.ADD_URL_RULE /* 7 */:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    int serial = _hidl_request.readInt32();
                    int type = _hidl_request.readInt32();
                    getRules(serial, type);
                    return;
                case ResultType.REMOVE_URL_RULE /* 8 */:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    int serial2 = _hidl_request.readInt32();
                    long startTime = _hidl_request.readInt64();
                    long endTime = _hidl_request.readInt64();
                    getLogs(serial2, startTime, endTime);
                    return;
                case 9:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    int serial3 = _hidl_request.readInt32();
                    boolean isWhiteList = _hidl_request.readBool();
                    ArrayList<ApplicationRule> uidRules = ApplicationRule.readVectorFromParcel(_hidl_request);
                    addApplicationRule(serial3, isWhiteList, uidRules);
                    return;
                case 10:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    int serial4 = _hidl_request.readInt32();
                    boolean isWhiteList2 = _hidl_request.readBool();
                    ArrayList<Integer> uids = _hidl_request.readInt32Vector();
                    removeApplicationRule(serial4, isWhiteList2, uids);
                    return;
                case 11:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    int serial5 = _hidl_request.readInt32();
                    boolean isWhiteList3 = _hidl_request.readBool();
                    ArrayList<String> ipaddress = _hidl_request.readStringVector();
                    ArrayList<String> ifaces = _hidl_request.readStringVector();
                    addIpRule(serial5, isWhiteList3, ipaddress, ifaces);
                    return;
                case 12:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    int serial6 = _hidl_request.readInt32();
                    boolean isWhiteList4 = _hidl_request.readBool();
                    ArrayList<String> ipaddress2 = _hidl_request.readStringVector();
                    ArrayList<String> ifaces2 = _hidl_request.readStringVector();
                    removeIpRule(serial6, isWhiteList4, ipaddress2, ifaces2);
                    return;
                case 13:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    int serial7 = _hidl_request.readInt32();
                    boolean isWhiteList5 = _hidl_request.readBool();
                    ArrayList<Integer> port = _hidl_request.readInt32Vector();
                    ArrayList<String> iface = _hidl_request.readStringVector();
                    addPortRule(serial7, isWhiteList5, port, iface);
                    return;
                case 14:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    int serial8 = _hidl_request.readInt32();
                    boolean isWhiteList6 = _hidl_request.readBool();
                    ArrayList<Integer> port2 = _hidl_request.readInt32Vector();
                    ArrayList<String> iface2 = _hidl_request.readStringVector();
                    removePortRule(serial8, isWhiteList6, port2, iface2);
                    return;
                case 15:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    int serial9 = _hidl_request.readInt32();
                    boolean isWhiteList7 = _hidl_request.readBool();
                    ArrayList<String> url = _hidl_request.readStringVector();
                    addUrlRule(serial9, isWhiteList7, url);
                    return;
                case 16:
                    _hidl_request.enforceInterface(IFirewallDaemon.kInterfaceName);
                    int serial10 = _hidl_request.readInt32();
                    boolean isWhiteList8 = _hidl_request.readBool();
                    ArrayList<String> url2 = _hidl_request.readStringVector();
                    removeUrlRule(serial10, isWhiteList8, url2);
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
