package com.ts.androidauto.aap.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.android.projection.proto.Protos;
import com.google.android.projection.protocol.BluetoothEndpoint;
import com.ts.androidauto.aap.AapController;
import com.ts.androidauto.aap.audiofocus.StateManager;
import com.ts.androidauto.aap.sink.GalIntegration;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.BluetoothConst;
import com.ts.androidauto.fsdk.common.BluetoothDeviceInfo;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;
import com.ts.androidauto.fsdk.interfaces.bluetooth.IBluetoothControllerAdapter;
import com.ts.androidauto.fsdk.interfaces.video.IVideoControllerAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AapBluetoothManager {
    private static final int AFTER_BOND_CONNECT_HFP_DELAY_TIME = 2000;
    private static final int AUTH_DATA_NUM = 6;
    private static final int DISCONNECT_RECONNECT_HFP_DELAY_TIME = 400;
    private static final int MD_AUTH_DATA_TIMEOUT_MS = 30000;
    private static final int MSG_HU_CONNECT_HFP = 1;
    private static final int MSG_MD_BT_AUTH_DATA_TIMEOUT = 2;
    private static final int PAIRINGREQUEST_RECONNECT_HFP_DELAY_TIME = 50;
    private static final int RECONNECT_A2DP_AFTER_DISCONNECT_TIME_MS = 300;
    private static final int RECONNECT_HFP_INTERAL_TIME = 6000;
    private static final String TAG = "AapBluetoothManager";
    private static AapBluetoothManager mAapBluetoothManager = null;
    private AapController mAapController;
    private BluetoothDeviceInfo mCurrPhoneBtDevice;
    private GalIntegration mGal;
    private IVideoControllerAdapter mVideoControllerAdapter;
    private IBluetoothControllerAdapter mBluetoothController = null;
    private PhoneCallStatusListener mPhoneCallStatusListener = null;
    private boolean mIsCheckA2dpConnected = false;
    private String mPhoneAddress = null;
    private boolean mHasPairRequest = false;
    private boolean mIsPowerOff = false;
    public BluetoothEndpoint.BluetoothEventListener bluetoothEventListener = new AnonymousClass1();
    private Handler mHandler = new Handler() { // from class: com.ts.androidauto.aap.bluetooth.AapBluetoothManager.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AapBluetoothManager.this.onReceiveMessage(msg);
        }
    };
    private AapPairState mPairState = AapPairState.PAIR_STATE_INVISIBLE;

    enum AapPairState {
        PAIR_STATE_INVISIBLE,
        PAIR_STATE_DELAYED,
        PAIR_STATE_VISIBLE,
        PAIR_STATE_CONNECTED,
        PAIR_STATE_DEVICE_CHANGED
    }

    public interface PhoneCallStatusListener {
        void notifyBtPhoneStatus(BluetoothConst.BtPhoneStatus btPhoneStatus);

        void notifyImsPhoneStatus(BluetoothConst.BtPhoneStatus btPhoneStatus);
    }

    public String getPhoneAddress() {
        LogUtil.debug(TAG, "getPhoneAddress mPhoneAddress: " + LogEncrypt.MacAddressEncrypt(this.mPhoneAddress));
        return this.mPhoneAddress;
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.bluetooth.AapBluetoothManager$1, reason: invalid class name */
    class AnonymousClass1 implements BluetoothEndpoint.BluetoothEventListener {
        AnonymousClass1() {
        }

        @Override // com.google.android.projection.protocol.BluetoothEndpoint.BluetoothEventListener
        public void onPairingRequest(final String phoneAddress, final int pairingMethod) {
            LogUtil.debug(AapBluetoothManager.TAG, "onGalCallback onPairingRequest start: phoneAddress=" + LogEncrypt.MacAddressEncrypt(phoneAddress) + " pairingMethod===" + pairingMethod);
            AapBluetoothManager.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.aap.bluetooth.-$$Lambda$AapBluetoothManager$1$s_ipYf_AWvF6DxzhDL2ewYC4XDo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onPairingRequest$0$AapBluetoothManager$1(phoneAddress, pairingMethod);
                }
            });
        }

        public /* synthetic */ void lambda$onPairingRequest$0$AapBluetoothManager$1(String phoneAddress, int pairingMethod) {
            AapBluetoothManager.this.removeReconnectMessage(2);
            AapBluetoothManager.this.removeReconnectMessage(1);
            AapBluetoothManager.this.handleMdPairingRequest(phoneAddress, pairingMethod);
            LogUtil.debug(AapBluetoothManager.TAG, "Md onPairingRequest start end");
        }

        @Override // com.google.android.projection.protocol.BluetoothEndpoint.BluetoothEventListener
        public void onAuthenticationResult(final int status) {
            LogUtil.info(AapBluetoothManager.TAG, "onGalCallback onAuthenticationResult status: " + status);
            AapBluetoothManager.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.aap.bluetooth.-$$Lambda$AapBluetoothManager$1$km_PJGACHoDJ9rt5HO-YmXOargE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onAuthenticationResult$1$AapBluetoothManager$1(status);
                }
            });
        }

        public /* synthetic */ void lambda$onAuthenticationResult$1$AapBluetoothManager$1(int status) {
            AapBluetoothManager.this.removeReconnectMessage(2);
            if (AapBluetoothManager.this.mBluetoothController != null) {
                if (status == Protos.MessageStatus.STATUS_SUCCESS.getNumber()) {
                    AapBluetoothManager.this.mBluetoothController.requestSetMdAuthState(true, AapBluetoothManager.this.mPhoneAddress);
                } else {
                    AapBluetoothManager.this.mBluetoothController.requestSetMdAuthState(false, AapBluetoothManager.this.mPhoneAddress);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMdPairingRequest(String phoneAddress, int pairingMethod) {
        this.mPhoneAddress = phoneAddress;
        this.mHasPairRequest = true;
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.updateCallAvailability();
        }
        this.mPairState = AapPairState.PAIR_STATE_INVISIBLE;
        IBluetoothControllerAdapter iBluetoothControllerAdapter = this.mBluetoothController;
        if (iBluetoothControllerAdapter == null) {
            LogUtil.error(TAG, "mBluetoothController not init, bt function error.");
            onGalRequestDelayedPairing();
            return;
        }
        if (!iBluetoothControllerAdapter.isBluetoothEnable()) {
            LogUtil.debug(TAG, "BT is not enable, turn on bluetooth.");
            onGalRequestDelayedPairing();
            this.mPairState = AapPairState.PAIR_STATE_DELAYED;
            this.mBluetoothController.enableBluetooth();
            return;
        }
        if (!this.mIsCheckA2dpConnected) {
            this.mIsCheckA2dpConnected = true;
        }
        BluetoothDeviceInfo connectedPhoneBluetoothDeviceInfo = this.mBluetoothController.getConnectedPhoneBluetoothDeviceInfo(this.mPhoneAddress);
        this.mCurrPhoneBtDevice = connectedPhoneBluetoothDeviceInfo;
        AapController aapController2 = this.mAapController;
        if (aapController2 != null) {
            aapController2.onMergeDevice(connectedPhoneBluetoothDeviceInfo.getName(), phoneAddress);
        }
        if (isAapMode() && isOtherHfpDeviceInCall()) {
            waitCallAvailabilityStatus();
        } else {
            setReadyToPaired();
        }
    }

    private void setReadyToPaired() {
        AapController aapController = this.mAapController;
        if (aapController != null && !aapController.isAapConnected()) {
            LogUtil.info(TAG, "setReadyToPaired, AA is not connected return!");
            return;
        }
        LogUtil.info(TAG, "setReadyToPaired is invoke!");
        if (this.mCurrPhoneBtDevice != null && !TextUtils.isEmpty(this.mPhoneAddress)) {
            requestDisconnectA2dp(this.mPhoneAddress);
            LogUtil.debug(TAG, "setReadyToPaired  mPhoneAddress:" + LogEncrypt.MacAddressEncrypt(this.mPhoneAddress));
            if (this.mCurrPhoneBtDevice.isHfpConnected()) {
                onGalReadyForPairing(true);
                this.mPairState = AapPairState.PAIR_STATE_CONNECTED;
                LogUtil.info(TAG, "setReadyToPaired hfp connected");
                return;
            }
            this.mPairState = AapPairState.PAIR_STATE_VISIBLE;
            if (this.mCurrPhoneBtDevice.isPaired()) {
                onGalReadyForPairing(true);
                LogUtil.info(TAG, "setReadyToPaired isPaired");
                tryConnectHfp(50);
            } else {
                onGalReadyForPairing(false);
                LogUtil.info(TAG, "setReadyToPaired unpaired");
            }
        }
    }

    private void waitCallAvailabilityStatus() {
        LogUtil.debug(TAG, "waitCallAvailabilityStatus is invoke mPhoneAddress:" + LogEncrypt.MacAddressEncrypt(this.mPhoneAddress));
        AapController aapController = this.mAapController;
        if (aapController != null && aapController.isAapConnected()) {
            this.mPairState = AapPairState.PAIR_STATE_DELAYED;
            onGalRequestDelayedPairing();
        }
    }

    public static synchronized AapBluetoothManager getInstance() {
        if (mAapBluetoothManager == null) {
            mAapBluetoothManager = new AapBluetoothManager();
        }
        return mAapBluetoothManager;
    }

    private AapBluetoothManager() {
        this.mGal = null;
        this.mGal = GalIntegration.getInstance();
    }

    public void setVideoController(IVideoControllerAdapter videoControllerAdapter) {
        this.mVideoControllerAdapter = videoControllerAdapter;
    }

    public void setAapController(AapController aapController) {
        this.mAapController = aapController;
    }

    public void setBluetoothController(IBluetoothControllerAdapter bluetoothController) {
        this.mBluetoothController = bluetoothController;
    }

    public int[] getSupportedBluetoothPairingMethods() {
        return new int[]{Protos.BluetoothPairingMethod.BLUETOOTH_PAIRING_NUMERIC_COMPARISON.getNumber()};
    }

    public String getCarBluetoothAddress() {
        LogUtil.verbose(TAG, "getCarBluetoothAddress is invoke");
        IBluetoothControllerAdapter iBluetoothControllerAdapter = this.mBluetoothController;
        if (iBluetoothControllerAdapter != null) {
            return iBluetoothControllerAdapter.getCarBluetoothAddress();
        }
        return null;
    }

    public boolean isOtherHfpDeviceInCall() {
        LogUtil.info(TAG, "isOtherHfpDeviceInCall is invoke~");
        IBluetoothControllerAdapter iBluetoothControllerAdapter = this.mBluetoothController;
        if (iBluetoothControllerAdapter != null) {
            return iBluetoothControllerAdapter.isOtherHfpDeviceInCall(this.mPhoneAddress);
        }
        return false;
    }

    public void requestDisconnectA2dp(String phoneAddress) {
        LogUtil.verbose(TAG, "requestDisconnectA2dp is invoke");
        IBluetoothControllerAdapter iBluetoothControllerAdapter = this.mBluetoothController;
        if (iBluetoothControllerAdapter != null) {
            iBluetoothControllerAdapter.setBluetoothAutoConnectEnabled(phoneAddress, false);
            this.mBluetoothController.requestDisconnectA2dp(phoneAddress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: requestReconnectA2dp, reason: merged with bridge method [inline-methods] */
    public void lambda$onDisconnected$1$AapBluetoothManager(String phoneAddress) {
        LogUtil.verbose(TAG, "requestReconnectA2dp is invoke, phoneAddress:" + LogEncrypt.MacAddressEncrypt(phoneAddress));
        if (this.mBluetoothController != null && !TextUtils.isEmpty(phoneAddress)) {
            this.mBluetoothController.setBluetoothAutoConnectEnabled(phoneAddress, true);
        }
    }

    public void onPhoneStatusChange(String address, BluetoothConst.BtPhoneStatus status) {
        String str;
        AapController aapController;
        String str2;
        LogUtil.verbose(TAG, "onPhoneStatusChange status = " + status + " address:" + LogEncrypt.MacAddressEncrypt(address));
        if (((address != null && (str2 = this.mPhoneAddress) != null && !address.equals(str2)) || address == null) && (aapController = this.mAapController) != null && aapController.isAapConnected() && status == BluetoothConst.BtPhoneStatus.PHONE_STATUS_IDLE) {
            if (this.mHasPairRequest) {
                if (this.mPairState == AapPairState.PAIR_STATE_DELAYED) {
                    setReadyToPaired();
                }
            } else {
                setReadyToPairedForWireless();
            }
        }
        if (address != null && (str = this.mPhoneAddress) != null && address.equals(str) && this.mVideoControllerAdapter != null) {
            if (status == BluetoothConst.BtPhoneStatus.PHONE_STATUS_ACTIVE) {
                LogUtil.info(TAG, "onPhoneStatus: phone run need turn screen on");
                this.mVideoControllerAdapter.setScreenOn(true);
                StateManager.getInstance().setPhoneRunningInScreenOffState(true);
            } else if (status == BluetoothConst.BtPhoneStatus.PHONE_STATUS_IDLE) {
                StateManager.getInstance().setPhoneRunningInScreenOffState(false);
            }
        }
        PhoneCallStatusListener phoneCallStatusListener = this.mPhoneCallStatusListener;
        if (phoneCallStatusListener != null) {
            phoneCallStatusListener.notifyBtPhoneStatus(status);
        }
    }

    public boolean onPairRequest(String address, String pinCode) {
        LogUtil.verbose(TAG, "onPairRequest address = " + LogEncrypt.MacAddressEncrypt(address) + " | pinCode = " + pinCode);
        String str = this.mPhoneAddress;
        if (str == null || !str.equals(address)) {
            return false;
        }
        removeReconnectMessage(1);
        String sendPinCode = pinCode;
        if (pinCode != null && pinCode.length() < 6) {
            String sendPinCode2 = "000000" + sendPinCode;
            sendPinCode = sendPinCode2.substring(sendPinCode2.length() - 6);
        }
        if (!hasReconnectMessage(2)) {
            sendEmptyMessageDelayed(2, 30000L);
        }
        LogUtil.verbose(TAG, "onPairRequest sendPinCode = " + sendPinCode);
        final String outPinCode = sendPinCode;
        AapController aapController = this.mAapController;
        if (aapController != null && aapController.isAapConnected()) {
            this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.aap.bluetooth.-$$Lambda$AapBluetoothManager$5EzIX5IP9klI6g94COPwJV6Qh08
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onPairRequest$0$AapBluetoothManager(outPinCode);
                }
            });
            this.mPairState = AapPairState.PAIR_STATE_VISIBLE;
            return true;
        }
        LogUtil.verbose(TAG, "onPairRequest AA is not connected return");
        return false;
    }

    public void onProfileChange(String address, String profile, BluetoothConst.ProfileStatus status) {
        boolean isConnected = false;
        AapController aapController = this.mAapController;
        if (aapController != null) {
            isConnected = aapController.isAapConnected();
        }
        LogUtil.verbose(TAG, "onProfileChange address: " + LogEncrypt.MacAddressEncrypt(address) + " profile: " + profile + " status: " + status + " AA connect:" + isConnected);
        if (isConnected && !TextUtils.isEmpty(this.mPhoneAddress) && this.mPhoneAddress.equals(address)) {
            if (BluetoothConst.BT_HFP_PROFILE_STR.equals(profile)) {
                if (status == BluetoothConst.ProfileStatus.PROFILE_STATUS_CONNECTED) {
                    removeReconnectMessage(1);
                    IBluetoothControllerAdapter iBluetoothControllerAdapter = this.mBluetoothController;
                    if (iBluetoothControllerAdapter != null) {
                        BluetoothDeviceInfo connectedPhoneBluetoothDeviceInfo = iBluetoothControllerAdapter.getConnectedPhoneBluetoothDeviceInfo(this.mPhoneAddress);
                        this.mCurrPhoneBtDevice = connectedPhoneBluetoothDeviceInfo;
                        String deviceName = connectedPhoneBluetoothDeviceInfo.getName();
                        LogUtil.info(TAG, "Md onProfileChange deviceName:" + deviceName);
                    }
                    if (this.mPairState == AapPairState.PAIR_STATE_VISIBLE) {
                        this.mPairState = AapPairState.PAIR_STATE_CONNECTED;
                    }
                    LogUtil.debug(TAG, "hfp connect add address:" + LogEncrypt.MacAddressEncrypt(address));
                    return;
                }
                if (status == BluetoothConst.ProfileStatus.PROFILE_STATUS_DISCONNECTED) {
                    LogUtil.debug(TAG, "hfp disconnect, try reconnect. address:" + LogEncrypt.MacAddressEncrypt(address));
                    tryConnectHfp(DISCONNECT_RECONNECT_HFP_DELAY_TIME);
                    return;
                }
                return;
            }
            if (BluetoothConst.BT_A2DP_PROFILE_STR.equals(profile) && status == BluetoothConst.ProfileStatus.PROFILE_STATUS_CONNECTED) {
                requestDisconnectA2dp(this.mPhoneAddress);
            }
        }
    }

    public void registerPhoneCallStatusListener(PhoneCallStatusListener phoneCallStatusListener) {
        this.mPhoneCallStatusListener = phoneCallStatusListener;
    }

    public void unRegisterPhoneCallStatusListener() {
        this.mPhoneCallStatusListener = null;
    }

    public void onDisconnected() {
        LogUtil.debug(TAG, "onDisconnected mPhoneAddress:" + LogEncrypt.MacAddressEncrypt(this.mPhoneAddress));
        this.mIsCheckA2dpConnected = false;
        this.mCurrPhoneBtDevice = null;
        final String reconnectA2dpAddr = this.mPhoneAddress;
        this.mPhoneAddress = null;
        this.mHasPairRequest = false;
        this.mPairState = AapPairState.PAIR_STATE_INVISIBLE;
        removeReconnectMessage(2);
        removeReconnectMessage(1);
        Handler handler = this.mHandler;
        if (handler == null) {
            LogUtil.warning(TAG, "onDisconnected mHandler is null.");
        } else {
            handler.postDelayed(new Runnable() { // from class: com.ts.androidauto.aap.bluetooth.-$$Lambda$AapBluetoothManager$lxnmDTa9lZFnMEPMhlM2F7w-TMM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDisconnected$1$AapBluetoothManager(reconnectA2dpAddr);
                }
            }, 300L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onReceiveMessage(Message msg) {
        AapController aapController;
        if (msg != null) {
            int what = msg.what;
            LogUtil.info(TAG, "onReceiveMessage what:" + what);
            if (what == 1) {
                if (this.mBluetoothController != null && isAapMode() && !TextUtils.isEmpty(this.mPhoneAddress)) {
                    requestDisconnectA2dp(this.mPhoneAddress);
                    boolean isHfpConnected = isHfpConnectedByAddr(this.mPhoneAddress);
                    LogUtil.debug(TAG, "phoneAddress:" + LogEncrypt.MacAddressEncrypt(this.mPhoneAddress) + " isHfpConnected:" + isHfpConnected);
                    if (!isHfpConnected && (aapController = this.mAapController) != null && aapController.isAapConnected()) {
                        handleConnectHfp(this.mPhoneAddress);
                        tryConnectHfp(RECONNECT_HFP_INTERAL_TIME);
                        return;
                    }
                    return;
                }
                return;
            }
            if (what == 2 && this.mBluetoothController != null) {
                LogUtil.info(TAG, "Md not auth in 30s show failed popoup");
                this.mBluetoothController.requestSetMdAuthState(false, this.mPhoneAddress);
                AapController aapController2 = this.mAapController;
                if (aapController2 != null) {
                    aapController2.onMdAuthFailed();
                }
                this.mBluetoothController.requestShowAuthFailedPop();
            }
        }
    }

    private boolean isHfpConnectedByAddr(String phoneAddress) {
        IBluetoothControllerAdapter iBluetoothControllerAdapter = this.mBluetoothController;
        if (iBluetoothControllerAdapter != null) {
            return iBluetoothControllerAdapter.isHfpConnected(phoneAddress);
        }
        return false;
    }

    private void tryConnectHfp(int delayMs) {
        LogUtil.debug(TAG, "reConnectHfp isAapMode :" + isAapMode() + " phoneAddress:" + LogEncrypt.MacAddressEncrypt(this.mPhoneAddress));
        if (isAapMode() && !TextUtils.isEmpty(this.mPhoneAddress) && !isHfpConnectedByAddr(this.mPhoneAddress)) {
            removeReconnectMessage(1);
            sendEmptyMessageDelayed(1, delayMs);
        }
    }

    private void handleConnectHfp(String phoneAddress) {
        if (this.mBluetoothController != null) {
            LogUtil.debug(TAG, "handleConnectHfp phoneAddress:" + LogEncrypt.MacAddressEncrypt(phoneAddress));
            this.mBluetoothController.connectHfp(phoneAddress);
        }
    }

    private boolean isAapMode() {
        List<AndroidAutoDevice> deviceList;
        boolean isAapMode = false;
        AapController aapController = this.mAapController;
        if (aapController != null && (deviceList = aapController.getCurrConnectedDevice()) != null && !deviceList.isEmpty()) {
            isAapMode = true;
        }
        LogUtil.debug(TAG, "isAapMode: " + isAapMode);
        return isAapMode;
    }

    public void onBluetoothBondStateChanged(int state, BluetoothDevice device) {
        String address = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        if (device != null) {
            address = device.getAddress();
        }
        boolean isAapMode = isAapMode();
        LogUtil.debug(TAG, "onBluetoothBond state:" + state + " address:" + LogEncrypt.MacAddressEncrypt(address));
        LogUtil.debug(TAG, "onBluetoothBond mPhoneAddress:" + LogEncrypt.MacAddressEncrypt(this.mPhoneAddress) + " isAapMode:" + isAapMode);
        if (isAapMode && !TextUtils.isEmpty(this.mPhoneAddress) && this.mPhoneAddress.equals(address) && state == 12) {
            tryConnectHfp(2000);
        }
    }

    public void onBtEnabled() {
        LogUtil.info(TAG, "onBtEnabled");
        if (this.mHasPairRequest && this.mPairState == AapPairState.PAIR_STATE_DELAYED && !TextUtils.isEmpty(this.mPhoneAddress)) {
            handleMdPairingRequest(this.mPhoneAddress, Protos.BluetoothPairingMethod.BLUETOOTH_PAIRING_NUMERIC_COMPARISON.getNumber());
        } else if (!TextUtils.isEmpty(this.mPhoneAddress)) {
            this.mCurrPhoneBtDevice = this.mBluetoothController.getConnectedPhoneBluetoothDeviceInfo(this.mPhoneAddress);
            setReadyToPairedForWireless();
        }
    }

    public void onBtDisabled() {
        Handler handler;
        boolean isConnected = false;
        AapController aapController = this.mAapController;
        if (aapController != null) {
            isConnected = aapController.isAapConnected();
        }
        LogUtil.info(TAG, "onBtDisabled mIsPowerOff:" + this.mIsPowerOff + ", isConnected:" + isConnected);
        if (!TextUtils.isEmpty(this.mPhoneAddress) && !this.mIsPowerOff && isConnected && (handler = this.mHandler) != null) {
            handler.postDelayed(new Runnable() { // from class: com.ts.androidauto.aap.bluetooth.-$$Lambda$AapBluetoothManager$d0LUhg2ZZO85fcX4bFM554Ce_jM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onBtDisabled$2$AapBluetoothManager();
                }
            }, 400L);
        }
    }

    public /* synthetic */ void lambda$onBtDisabled$2$AapBluetoothManager() {
        if (!this.mBluetoothController.isBluetoothEnable()) {
            LogUtil.debug(TAG, "BT is not enable, turn on bluetooth for wireless AAP.");
            this.mBluetoothController.enableBluetooth();
        }
    }

    public void updateWirelessAaAddress(String address) {
        LogUtil.debug(TAG, "updateWirelessAaAddress for gal 1.6. address=" + LogEncrypt.MacAddressEncrypt(address));
        if (!TextUtils.isEmpty(address)) {
            this.mHasPairRequest = false;
            this.mPhoneAddress = address;
            AapController aapController = this.mAapController;
            if (aapController != null) {
                aapController.updateCallAvailability();
            }
            if (!this.mIsCheckA2dpConnected) {
                this.mIsCheckA2dpConnected = true;
            }
            IBluetoothControllerAdapter iBluetoothControllerAdapter = this.mBluetoothController;
            if (iBluetoothControllerAdapter != null) {
                BluetoothDeviceInfo connectedPhoneBluetoothDeviceInfo = iBluetoothControllerAdapter.getConnectedPhoneBluetoothDeviceInfo(this.mPhoneAddress);
                this.mCurrPhoneBtDevice = connectedPhoneBluetoothDeviceInfo;
                AapController aapController2 = this.mAapController;
                if (aapController2 != null) {
                    aapController2.onMergeDevice(connectedPhoneBluetoothDeviceInfo.getName(), this.mPhoneAddress);
                }
            }
            if (!isAapMode() || !isOtherHfpDeviceInCall()) {
                setReadyToPairedForWireless();
            }
        }
    }

    private void setReadyToPairedForWireless() {
        AapController aapController = this.mAapController;
        if (aapController != null && !aapController.isAapConnected()) {
            LogUtil.info(TAG, "setReadyToPairedForWireless, AA is not connected return!");
            return;
        }
        LogUtil.info(TAG, "setReadyToPairedForWireless is invoke! mCurrPhoneBtDevice=" + this.mCurrPhoneBtDevice);
        if (this.mCurrPhoneBtDevice != null && !TextUtils.isEmpty(this.mPhoneAddress)) {
            requestDisconnectA2dp(this.mPhoneAddress);
            LogUtil.debug(TAG, "setReadyToPairedForWireless  mPhoneAddress:" + LogEncrypt.MacAddressEncrypt(this.mPhoneAddress));
            if (this.mCurrPhoneBtDevice.isHfpConnected()) {
                LogUtil.info(TAG, "setReadyToPairedForWireless hfp connected");
            } else if (this.mCurrPhoneBtDevice.isPaired()) {
                LogUtil.info(TAG, "setReadyToPairedForWireless isPaired");
                tryConnectHfp(50);
            } else {
                LogUtil.info(TAG, "setReadyToPairedForWireless unpaired");
                tryConnectHfp(50);
            }
        }
    }

    private void onGalReadyForPairing(boolean isPaired) {
        LogUtil.info(TAG, "onGalReadyForPairing isPaired=" + isPaired);
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null && galIntegration.bluetooth != null) {
            this.mGal.bluetooth.onReadyForPairing(isPaired);
        }
    }

    private void onGalRequestDelayedPairing() {
        LogUtil.info(TAG, "onGalRequestDelayedPairing");
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null && galIntegration.bluetooth != null) {
            this.mGal.bluetooth.requestDelayedPairing();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onGalSendAuthData, reason: merged with bridge method [inline-methods] */
    public void lambda$onPairRequest$0$AapBluetoothManager(String pinCode) {
        LogUtil.info(TAG, "onGalSendAuthData pinCode=" + pinCode);
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null && galIntegration.bluetooth != null) {
            this.mGal.bluetooth.sendAuthData(pinCode, Protos.BluetoothPairingMethod.BLUETOOTH_PAIRING_NUMERIC_COMPARISON.getNumber());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeReconnectMessage(int what) {
        Handler handler = this.mHandler;
        if (handler != null && handler.hasMessages(what)) {
            LogUtil.info(TAG, "removeReconnectMessage what:" + what);
            this.mHandler.removeMessages(what);
        }
    }

    private boolean hasReconnectMessage(int what) {
        LogUtil.info(TAG, "check hasReconnectMessage what:" + what);
        Handler handler = this.mHandler;
        if (handler != null && handler.hasMessages(what)) {
            return true;
        }
        return false;
    }

    private void sendEmptyMessageDelayed(int what, long delayTime) {
        if (this.mHandler != null) {
            AapController aapController = this.mAapController;
            if (aapController != null && aapController.isAapConnected()) {
                LogUtil.info(TAG, "sendEmptyMessageDelayed what:" + what);
                this.mHandler.sendEmptyMessageDelayed(what, delayTime);
                return;
            }
            LogUtil.info(TAG, "sendEmptyMessageDelayed AA is not connect:" + what);
        }
    }

    public void onPowerStatusChange(boolean powerOff) {
        LogUtil.info(TAG, "onPowerStatusChange powerOff" + powerOff);
        this.mIsPowerOff = powerOff;
    }
}
