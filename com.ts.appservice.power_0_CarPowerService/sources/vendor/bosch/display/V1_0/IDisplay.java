package vendor.bosch.display.V1_0;

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
public interface IDisplay extends IBase {
    public static final String kInterfaceName = "vendor.bosch.display@1.0::IDisplay";

    @FunctionalInterface
    public interface getAutoBrightnessInfoCallback {
        void onValues(boolean z, boolean z2);
    }

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    void getAutoBrightnessInfo(getAutoBrightnessInfoCallback getautobrightnessinfocallback) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    boolean getDisplayContent(int i) throws RemoteException;

    boolean getDisplayDayNightMode() throws RemoteException;

    boolean getDisplayForwardLinkMarginValue(int i) throws RemoteException;

    boolean getDisplayHeight(int i) throws RemoteException;

    boolean getDisplayInfo(int i) throws RemoteException;

    boolean getDisplayReverseLinkMarginValue(int i) throws RemoteException;

    boolean getDisplayRotation(int i) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

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

    boolean setAutoBrightnessInfo(boolean z) throws RemoteException;

    boolean setCallback(IDisplayCallback iDisplayCallback) throws RemoteException;

    boolean setDisplayBrightness(int i, int i2) throws RemoteException;

    boolean setDisplayContent(int i, int i2) throws RemoteException;

    boolean setDisplayHeight(int i, int i2) throws RemoteException;

    boolean setDisplayLvdsLinkState(int i, int i2) throws RemoteException;

    boolean setDisplayPowerState(int i, int i2) throws RemoteException;

    boolean setDisplayRotation(int i, int i2) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    boolean setResetDisplayStatus(int i) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    static IDisplay asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof IDisplay)) {
            return (IDisplay) iface;
        }
        IDisplay proxy = new Proxy(binder);
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

    static IDisplay castFrom(IHwInterface iface) {
        if (iface == null) {
            return null;
        }
        return asInterface(iface.asBinder());
    }

    static IDisplay getService(String serviceName, boolean retry) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName, retry));
    }

    static IDisplay getService(boolean retry) throws RemoteException {
        return getService("default", retry);
    }

    static IDisplay getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static IDisplay getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IDisplay {
        private IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            Objects.requireNonNull(remote);
            this.mRemote = remote;
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of vendor.bosch.display@1.0::IDisplay]@Proxy";
            }
        }

        public final boolean equals(Object other) {
            return HidlSupport.interfacesEqual(this, other);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean setDisplayBrightness(int displayPanelId, int brightnessLevel) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
            _hidl_request.writeInt32(brightnessLevel);
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

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean setDisplayPowerState(int displayPanelId, int powerState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
            _hidl_request.writeInt32(powerState);
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

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean getDisplayInfo(int displayPanelId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
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

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean setDisplayHeight(int displayPanelId, int heightLevel) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
            _hidl_request.writeInt32(heightLevel);
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

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean getDisplayHeight(int displayPanelId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
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

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean setDisplayRotation(int displayPanelId, int rotateLevel) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
            _hidl_request.writeInt32(rotateLevel);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean getDisplayRotation(int displayPanelId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean setDisplayContent(int displayPanelId, int content) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
            _hidl_request.writeInt32(content);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean getDisplayContent(int displayPanelId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean getDisplayDayNightMode() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean setCallback(IDisplayCallback callback) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeStrongBinder(callback == null ? null : callback.asBinder());
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean setDisplayLvdsLinkState(int displayPanelId, int linkState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
            _hidl_request.writeInt32(linkState);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean setResetDisplayStatus(int displayPanelId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public void getAutoBrightnessInfo(getAutoBrightnessInfoCallback _hidl_cb) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                boolean _hidl_out_autoBrightness = _hidl_reply.readBool();
                _hidl_cb.onValues(_hidl_out_success, _hidl_out_autoBrightness);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean setAutoBrightnessInfo(boolean autoBrightness) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeBool(autoBrightness);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean getDisplayReverseLinkMarginValue(int displayPanelId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay
        public boolean getDisplayForwardLinkMarginValue(int displayPanelId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IDisplay.kInterfaceName);
            _hidl_request.writeInt32(displayPanelId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(17, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
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

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
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

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
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

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
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

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
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

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException {
            return this.mRemote.linkToDeath(recipient, cookie);
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
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

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
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

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
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

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(recipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IDisplay {
        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IDisplay.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle fd, ArrayList<String> options) {
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IDisplay.kInterfaceName;
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{-116, 31, -18, -15, 14, 79, -31, -117, 34, -12, -83, -42, 70, -29, 85, 83, -87, 32, 23, 90, 109, -7, 12, 60, 78, 111, 70, 55, 48, 47, 66, -31}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo info = new DebugInfo();
            info.pid = HidlSupport.getPidIfSharable();
            info.ptr = 0L;
            info.arch = 0;
            return info;
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        @Override // vendor.bosch.display.V1_0.IDisplay, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) {
            return true;
        }

        public IHwInterface queryLocalInterface(String descriptor) {
            if (IDisplay.kInterfaceName.equals(descriptor)) {
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

        public void onTransact(int _hidl_code, HwParcel _hidl_request, final HwParcel _hidl_reply, int _hidl_flags) throws RemoteException {
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId = _hidl_request.readInt32();
                    int brightnessLevel = _hidl_request.readInt32();
                    boolean _hidl_out_success = setDisplayBrightness(displayPanelId, brightnessLevel);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success);
                    _hidl_reply.send();
                    return;
                case 2:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId2 = _hidl_request.readInt32();
                    int powerState = _hidl_request.readInt32();
                    boolean _hidl_out_success2 = setDisplayPowerState(displayPanelId2, powerState);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success2);
                    _hidl_reply.send();
                    return;
                case 3:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId3 = _hidl_request.readInt32();
                    boolean _hidl_out_success3 = getDisplayInfo(displayPanelId3);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success3);
                    _hidl_reply.send();
                    return;
                case 4:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId4 = _hidl_request.readInt32();
                    int heightLevel = _hidl_request.readInt32();
                    boolean _hidl_out_success4 = setDisplayHeight(displayPanelId4, heightLevel);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success4);
                    _hidl_reply.send();
                    return;
                case 5:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId5 = _hidl_request.readInt32();
                    boolean _hidl_out_success5 = getDisplayHeight(displayPanelId5);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success5);
                    _hidl_reply.send();
                    return;
                case 6:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId6 = _hidl_request.readInt32();
                    int rotateLevel = _hidl_request.readInt32();
                    boolean _hidl_out_success6 = setDisplayRotation(displayPanelId6, rotateLevel);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success6);
                    _hidl_reply.send();
                    return;
                case 7:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId7 = _hidl_request.readInt32();
                    boolean _hidl_out_success7 = getDisplayRotation(displayPanelId7);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success7);
                    _hidl_reply.send();
                    return;
                case 8:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId8 = _hidl_request.readInt32();
                    int content = _hidl_request.readInt32();
                    boolean _hidl_out_success8 = setDisplayContent(displayPanelId8, content);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success8);
                    _hidl_reply.send();
                    return;
                case 9:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId9 = _hidl_request.readInt32();
                    boolean _hidl_out_success9 = getDisplayContent(displayPanelId9);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success9);
                    _hidl_reply.send();
                    return;
                case 10:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    boolean _hidl_out_success10 = getDisplayDayNightMode();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success10);
                    _hidl_reply.send();
                    return;
                case 11:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    IDisplayCallback callback = IDisplayCallback.asInterface(_hidl_request.readStrongBinder());
                    boolean _hidl_out_success11 = setCallback(callback);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success11);
                    _hidl_reply.send();
                    return;
                case 12:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId10 = _hidl_request.readInt32();
                    int linkState = _hidl_request.readInt32();
                    boolean _hidl_out_success12 = setDisplayLvdsLinkState(displayPanelId10, linkState);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success12);
                    _hidl_reply.send();
                    return;
                case 13:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId11 = _hidl_request.readInt32();
                    boolean _hidl_out_success13 = setResetDisplayStatus(displayPanelId11);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success13);
                    _hidl_reply.send();
                    return;
                case 14:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    getAutoBrightnessInfo(new getAutoBrightnessInfoCallback() { // from class: vendor.bosch.display.V1_0.IDisplay.Stub.1
                        @Override // vendor.bosch.display.V1_0.IDisplay.getAutoBrightnessInfoCallback
                        public void onValues(boolean success, boolean autoBrightness) {
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeBool(success);
                            _hidl_reply.writeBool(autoBrightness);
                            _hidl_reply.send();
                        }
                    });
                    return;
                case 15:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    boolean autoBrightness = _hidl_request.readBool();
                    boolean _hidl_out_success14 = setAutoBrightnessInfo(autoBrightness);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success14);
                    _hidl_reply.send();
                    return;
                case 16:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId12 = _hidl_request.readInt32();
                    boolean _hidl_out_success15 = getDisplayReverseLinkMarginValue(displayPanelId12);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success15);
                    _hidl_reply.send();
                    return;
                case 17:
                    _hidl_request.enforceInterface(IDisplay.kInterfaceName);
                    int displayPanelId13 = _hidl_request.readInt32();
                    boolean _hidl_out_success16 = getDisplayForwardLinkMarginValue(displayPanelId13);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success16);
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
