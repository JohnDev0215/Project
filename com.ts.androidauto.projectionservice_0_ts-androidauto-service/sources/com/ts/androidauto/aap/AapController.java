package com.ts.androidauto.aap;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.google.android.projection.proto.Protos;
import com.google.android.projection.protocol.CarWifiStub;
import com.google.android.projection.protocol.GalReceiver;
import com.google.android.projection.protocol.Transport;
import com.google.android.projection.transport.TransportFactory;
import com.google.android.projection.transport.net.TcpTransport;
import com.ts.androidauto.aap.audio.AapAudioManager;
import com.ts.androidauto.aap.audiofocus.AapAudioFocusManager;
import com.ts.androidauto.aap.audiofocus.StateManager;
import com.ts.androidauto.aap.bluetooth.AapBluetoothManager;
import com.ts.androidauto.aap.bluetooth.AapRfcommConnection;
import com.ts.androidauto.aap.bluetooth.AapRfcommConnectionCallbacks;
import com.ts.androidauto.aap.common.AapAesUtil;
import com.ts.androidauto.aap.common.AapConfig;
import com.ts.androidauto.aap.common.StatusConst;
import com.ts.androidauto.aap.common.SurfaceData;
import com.ts.androidauto.aap.common.UiConfig;
import com.ts.androidauto.aap.common.VehicleInfo;
import com.ts.androidauto.aap.common.WifiConnectInfo;
import com.ts.androidauto.aap.data.MediaMetadata;
import com.ts.androidauto.aap.data.NavigationPositionData;
import com.ts.androidauto.aap.data.NavigationStateData;
import com.ts.androidauto.aap.data.PhoneStatusData;
import com.ts.androidauto.aap.input.HandleInputEvent;
import com.ts.androidauto.aap.navigation.NavigationFocusManager;
import com.ts.androidauto.aap.sink.GalInitParameter;
import com.ts.androidauto.aap.sink.GalIntegration;
import com.ts.androidauto.aap.sink.InstrumentClusterListener;
import com.ts.androidauto.aap.vehicledata.VehicleDataManager;
import com.ts.androidauto.aap.video.AapClusterVideoManager;
import com.ts.androidauto.aap.video.AapVideoCallback;
import com.ts.androidauto.aap.video.AapVideoManager;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.BluetoothConst;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.NativePhoneConst;
import com.ts.androidauto.fsdk.common.ResourceConst;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;
import com.ts.androidauto.fsdk.interfaces.audio.IAudioControllerAdapter;
import com.ts.androidauto.fsdk.interfaces.bluetooth.IBluetoothControllerAdapter;
import com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerAdapter;
import com.ts.androidauto.fsdk.interfaces.video.IVideoControllerAdapter;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class AapController implements TransportFactory.Callback {
    private static final int AAP_HEADUNIT_SESSION_CONFIGURATION_NOT_DISPLAY_CLOCK = 1;
    private static final int AAP_HEADUNIT_SESSION_CONFIGURATION_NOT_DISPLAY_SIGNAL = 2;
    private static final int DELAY_SHOW_TIME = 1000;
    public static final String DUMP_AUDIO_TEST_END = "AAP_DUMP_AUDIO_TEST_END";
    public static final String DUMP_AUDIO_TEST_START = "AAP_DUMP_AUDIO_TEST_START";
    private static final String FINISH_SHOW_ACTIVITY_ACTION = "com.ts.androidauto.app.display.aapactivity.RECEIVER_FINISH_ACTION";
    private static final long KEY_CALLBACK_DEBOUNCE_DELAY = 500;
    private static final int MAX_FACTORY_ID_VALUE = 10000;
    public static final int MESSAGE_RELEASE_NOTIFY_TIMEOUT = 1;
    public static final int MESSAGE_START_HOST_MODE = 3;
    public static final int MESSAGE_WIRED_SERVICE_DISCOVERY_TIMEOUT = 2;
    public static final String NAVI_SIMULATION_TEST_END = "AAP_NAVI_SIMULATION_TEST_END";
    public static final String NAVI_SIMULATION_TEST_START = "AAP_NAVI_SIMULATION_TEST_START";
    private static final int SHOW_WARN_INFO = 4;
    private static final int SPLIC_MAC_ADDRESS_LENGTH = 6;
    private static final int SPLIT_MAC_ADDRESS_INDEX_0 = 0;
    private static final int SPLIT_MAC_ADDRESS_INDEX_3 = 3;
    public static final int TIME_RELEASE_NOTIFY_TIMEOUT = 5000;
    public static final int TIME_SERVICE_DISCOVERY_TIMEOUT = 15000;
    private static AapController mAapController;
    private AapAudioManager mAapAudioManager;
    private AapClusterVideoManager mAapClusterVideoManager;
    private MessageHandler mAapControllerHandler;
    private HandlerThread mAapControllerHandlerThread;
    private AapControllerListener mAapControllerListener;
    private Looper mAapControllerLooper;
    private AapDisplayManager mAapDisplayManager;
    private AapVideoManager mAapVideoManager;
    private IAudioControllerAdapter mAudioControllerAdapter;
    private GalIntegration mGal;
    private Handler mGalReceiverHandler;
    private HandlerThread mGalReceiverHandlerThread;
    private HandleInputEvent mHandleInputEvent;
    private InstrumentClusterListener mInstrumentClusterListener;
    private NavigationFocusManager mNavigationFocusManager;
    private VehicleDataManager mSensorDispatcher;
    private AndroidAutoConst.ShowMode mShowMode;
    private SurfaceData mSurfaceData;
    private Transport mTransport;
    private TransportFactory mTransportFactory;
    private TransportFactory.FactoryHandle mTransportHandle;
    private IVehicleControllerAdapter mVehicleControllerAdapter;
    private IVideoControllerAdapter mVideoControllerAdapter;
    private static final String TAG = "AAP" + AapController.class.getSimpleName();
    public static final int[] DEFAULT_WIFI_5G_CHANNELS = {5180};
    private static int mCurrFactoryId = 0;
    private static int mCurrWifiPort = AapConfig.WIFI_PORT_FIRST;
    private boolean mIsStartSimulation = false;
    private boolean mIsConnecteDelay = false;
    private boolean mIsTcpFirstConnected = true;
    private boolean mIsMdRequestConnect = false;
    private boolean mIsInTcpSetup = false;
    private boolean mIsCancelConnect = false;
    private boolean mIsStartTcpServer = false;
    private boolean mIsStartGal = false;
    private boolean mIsOtherProjectionActive = false;
    private boolean mIsCallAvailability = true;
    private long mLastNextKeyEventTime = 0;
    private long mLastPreKeyEventTime = 0;
    private final Object mLock = new Object();
    private final Object mStartTcpLock = new Object();
    private Context mContext = null;
    private CheckFirstUseThread mCheckFirstUseThread = null;
    private AndroidAutoDevice mLinkDevice = null;
    private AndroidAutoDevice mConnectingDevice = null;
    private HashMap<String, AndroidAutoDevice> mAapLinkMap = new HashMap<>();
    private Map<BluetoothDevice, AapRfcommConnection> mAapWirelessLinkMap = new HashMap();
    private AndroidAutoConst.LinkStatus mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_INIT;
    private HashMap<Integer, Integer> mChannelFrequencyMap = new HashMap<>();
    private WifiConnectInfo mWifiConnectInfo = null;
    private AndroidAutoConst.LinkErrorCode mFailedError = AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE;
    private Semaphore mWaitByebyeSemaphore = new Semaphore(0);
    private SoftApConfiguration mDefaultWifiInfo = null;
    private String mDefaultWifiMacAddress = null;
    private boolean mIsDuringRequestAoa = false;
    private boolean mIsSendFinishBroadcast = false;
    private boolean mIsSendWarnMessage = false;
    private int mPrePlayState = AndroidAutoConst.MusicStatus.MUSIC_STATUS_NOT_START.ordinal();
    private int mNativePhoneCallStatus = NativePhoneConst.NativePhoneCallStatus.IDLE.ordinal();
    private BroadcastReceiver reveiver = new BroadcastReceiver() { // from class: com.ts.androidauto.aap.AapController.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.info(AapController.TAG, "Test: action:" + action);
            if (action.equals(AapController.NAVI_SIMULATION_TEST_START)) {
                LogUtil.info(AapController.TAG, "Test: onReceive AAP_NAVI_SIMULATION_TEST_START");
                if (AapController.this.mSensorDispatcher != null && !AapController.this.mIsStartSimulation) {
                    LogUtil.info(AapController.TAG, "Test: startSimulationGuidance mIsStartSimulation:" + AapController.this.mIsStartSimulation);
                    AapController.this.mSensorDispatcher.startSimulationGuidance();
                    AapController.this.mIsStartSimulation = true;
                }
            }
            if (action.equals(AapController.NAVI_SIMULATION_TEST_END)) {
                LogUtil.info(AapController.TAG, "Test: onReceive AAP_NAVI_SIMULATION_TEST_END");
                if (AapController.this.mSensorDispatcher != null && AapController.this.mIsStartSimulation) {
                    LogUtil.info(AapController.TAG, "Test: stopSimulationGuidance mIsStartSimulation:" + AapController.this.mIsStartSimulation);
                    AapController.this.mSensorDispatcher.stopSimulationGuidance();
                    AapController.this.mIsStartSimulation = false;
                }
            }
            if (action.equals(AapController.DUMP_AUDIO_TEST_START)) {
                LogUtil.info(AapController.TAG, "Test: onReceive AAP_DUMP_AUDIO_TEST_START");
                if (AapController.this.mAapAudioManager != null) {
                    LogUtil.info(AapController.TAG, "Test: Start dumping audio !");
                    AapController.this.mAapAudioManager.setTtsStreamSwitcher(true);
                    AapController.this.mAapAudioManager.setMediaStreamSwitcher(true);
                    AapController.this.mAapAudioManager.setRecorderStreamSwitcher(true);
                }
            }
            if (action.equals(AapController.DUMP_AUDIO_TEST_END)) {
                LogUtil.info(AapController.TAG, "Test: onReceive AAP_DUMP_AUDIO_TEST_END");
                if (AapController.this.mAapAudioManager != null) {
                    LogUtil.info(AapController.TAG, "Test: Stop dumping audio !");
                    AapController.this.mAapAudioManager.setTtsStreamSwitcher(false);
                    AapController.this.mAapAudioManager.setMediaStreamSwitcher(false);
                    AapController.this.mAapAudioManager.setRecorderStreamSwitcher(false);
                    AapController.this.mAapAudioManager.closeDataFile();
                }
            }
        }
    };
    private AapRfcommConnectionCallbacks mRfcommConnectCallbacks = new AapRfcommConnectionCallbacks() { // from class: com.ts.androidauto.aap.AapController.2
        @Override // com.ts.androidauto.aap.bluetooth.AapRfcommConnectionCallbacks
        public void notifyWifiSupportState(AndroidAutoDevice device, boolean support, String seriNum) {
            LogUtil.info(AapController.TAG, "notifyWifiSupportState support:" + support + " seriNum:" + LogEncrypt.MacAddressEncrypt(seriNum) + " linkDevice:" + device + " mIsConnecteDelay:" + AapController.this.mIsConnecteDelay + " mIsMdRequestConnect:" + AapController.this.mIsMdRequestConnect);
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifiSupportAapWirelessResult(device, support, seriNum);
            }
            if (AapController.this.mIsMdRequestConnect) {
                AapController.this.mIsConnecteDelay = false;
            }
            if (AapController.this.mIsConnecteDelay && AapController.this.mConnectingDevice != null && AapController.this.mConnectingDevice.equals(device)) {
                AapController.this.mIsConnecteDelay = false;
                AapController aapController = AapController.this;
                aapController.connecting(aapController.mConnectingDevice);
            }
        }

        @Override // com.ts.androidauto.aap.bluetooth.AapRfcommConnectionCallbacks
        public void notifyRfcommDisconnect(AndroidAutoDevice device) {
            LogUtil.info(AapController.TAG, "notifyRfcommDisconnect start:" + device + " mIsInTcpSetup:" + AapController.this.mIsInTcpSetup);
            if (device != null) {
                AapController.this.delRfcommConnection(device.getBluetoothDevice());
            }
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.onVehicleReqRfcommDisconnect(device);
            }
            if ((AapController.this.mIsStartTcpServer || AapController.this.mIsConnecteDelay || AapController.this.mIsMdRequestConnect) && device != null && AapController.this.mConnectingDevice != null && AapController.this.mConnectingDevice.equals(device) && AapController.this.mConnectingDevice.isWireLessLink() && AapController.this.mConnectingDevice.getStatus() == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING.ordinal()) {
                if (AapController.this.mTransportHandle != null) {
                    try {
                        AapController.this.mTransportHandle.interrupt();
                    } catch (Exception exception) {
                        LogUtil.warning(AapController.TAG, "Failed to interrupt tcp " + exception.toString());
                    }
                }
                AapController.this.mIsConnecteDelay = false;
                LogUtil.debug(AapController.TAG, "RFCOMM is disconnected notify connect failed");
                AapController.this.mFailedError = AndroidAutoConst.LinkErrorCode.ERROR_TCP_CONNECTION_FAILURE;
                AapController.this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
                if (AapController.this.mAapControllerListener != null) {
                    AapController.this.mAapControllerListener.onPhoneManagerStatusChanged(device, AapController.this.mLinkStatus, AapController.this.mFailedError);
                }
            }
        }

        @Override // com.ts.androidauto.aap.bluetooth.AapRfcommConnectionCallbacks
        public void notifyMdRequestWifiStart(AndroidAutoDevice device) {
            LogUtil.info(AapController.TAG, "notifyMdRequestWifiStart start:" + device);
            AapController.this.mIsMdRequestConnect = true;
            AapController.this.mConnectingDevice = device;
            AapController.this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING;
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifyMdConnectRequest(device);
            }
        }

        @Override // com.ts.androidauto.aap.bluetooth.AapRfcommConnectionCallbacks
        public int[] getWifiChannels() {
            int[] channels = AapController.this.getSupportedChannels();
            return AapController.this.changeChannelToFrequency(channels);
        }

        @Override // com.ts.androidauto.aap.bluetooth.AapRfcommConnectionCallbacks
        public void notifyWifiConnectError(AndroidAutoDevice device, AndroidAutoConst.LinkErrorCode errorCode, String message) {
            LogUtil.verbose(AapController.TAG, "notifyWifiConnectError message:" + message);
            if (errorCode == AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED) {
                if (AapController.this.mConnectingDevice != null && AapController.this.mConnectingDevice.equals(device) && AapController.this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING) {
                    if (AapController.this.mTransportHandle != null) {
                        try {
                            AapController.this.mTransportHandle.interrupt();
                        } catch (Exception exception) {
                            LogUtil.warning(AapController.TAG, "Failed to interrupt tcp " + exception.toString());
                        }
                    }
                    AapController.this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
                    if (AapController.this.mAapControllerListener != null) {
                        AapController.this.mAapControllerListener.onPhoneManagerStatusChanged(device, AapController.this.mLinkStatus, errorCode, message);
                    }
                    AapController.this.releaseConnection(errorCode);
                    return;
                }
                return;
            }
            if (errorCode == AndroidAutoConst.LinkErrorCode.ERROR_WIFI_CONNECT_UNSOLICITED_MESSAGE) {
                if (AapController.this.mAapControllerListener != null) {
                    AapController.this.mAapControllerListener.onPhoneManagerStatusChanged(device, AapController.this.mLinkStatus, errorCode, message);
                }
                if (AapController.this.mConnectingDevice != null && AapController.this.mConnectingDevice.equals(device) && AapController.this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING) {
                    if (AapController.this.mTransportHandle != null) {
                        try {
                            AapController.this.mTransportHandle.interrupt();
                        } catch (Exception exception2) {
                            LogUtil.warning(AapController.TAG, "Failed to interrupt tcp " + exception2.toString());
                        }
                    }
                    AapController.this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
                    if (AapController.this.mAapControllerListener != null) {
                        AapController.this.mAapControllerListener.onPhoneManagerStatusChanged(device, AapController.this.mLinkStatus, errorCode, message);
                    }
                    AapController.this.releaseConnection(errorCode);
                    return;
                }
                return;
            }
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.onPhoneManagerStatusChanged(device, AapController.this.mLinkStatus, errorCode, message);
            }
        }
    };
    private final GalReceiver.AppMessageListener mAppMessageListener = new GalReceiver.AppMessageListener() { // from class: com.ts.androidauto.aap.AapController.3
        @Override // com.google.android.projection.protocol.GalReceiver.AppMessageListener
        public void onNavigationFocusRequest(int focusType) {
            LogUtil.info(AapController.TAG, "onNavigationFocusRequest: " + focusType);
            if (AapController.this.mNavigationFocusManager != null) {
                AapController.this.mNavigationFocusManager.handleNavigationFocusRequest(focusType);
            }
        }

        @Override // com.google.android.projection.protocol.GalReceiver.AppMessageListener
        public void onVoiceSessionNotification(int state) {
            LogUtil.debug(AapController.TAG, "onVoiceSessionNotification start, state:" + state);
            if (AapController.this.mAapAudioManager != null) {
                AapController.this.mAapAudioManager.setVoiceSessionState(state);
                if (state == GalReceiver.VOICE_RECOGNITION_STATE_STARTED) {
                    onVoiceSessionStart();
                } else {
                    onVoiceSessionStop();
                }
            }
            LogUtil.debug(AapController.TAG, "onVoiceSessionNotification end");
        }

        private void onVoiceSessionStart() {
            if (StateManager.getInstance().getMdCurrentFocusState() == 4) {
                AapAudioFocusManager.getInstance().setAudioFocus(1, true);
                StateManager.getInstance().setCurrentMdFocusReq(GalReceiver.AUDIO_FOCUS_GAIN);
            }
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifyVoiceSessionNotification(AndroidAutoConst.VoiceSessionStatus.VOICE_RECOGNITION_STATE_STARTED.ordinal());
            }
            LogUtil.info(AapController.TAG, "onVoiceSessionNotification start");
            AapController.this.requestMicFocus();
            if (AapController.this.mVideoControllerAdapter != null) {
                AapController.this.mVideoControllerAdapter.setScreenOn(true);
                if (!AapController.this.mVideoControllerAdapter.isInPowerOff() || StateManager.getInstance().getVrRunningInPowerOffState()) {
                    if (!AapController.this.mVideoControllerAdapter.isInPowerOff() && !StateManager.getInstance().getVrRunningInPowerOffState()) {
                        StateManager.getInstance().setPhoneStateByRunningVr(true);
                        return;
                    }
                    return;
                }
                LogUtil.debug(AapController.TAG, "onVoiceSessionNotification start in powerOff");
                StateManager.getInstance().setVrRunningInPowerOffState(true);
            }
        }

        private void onVoiceSessionStop() {
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifyVoiceSessionNotification(AndroidAutoConst.VoiceSessionStatus.VOICE_RECOGNITION_STATE_ENDED.ordinal());
            }
            AapController.this.releaseMicFocus();
            if (AapController.this.mAapAudioManager.getVrPlayState() == 2) {
                LogUtil.info(AapController.TAG, "onVoiceSessionNotification end, release focus in MD release");
                if (StateManager.getInstance().getMdCurrentFocusState() == 1) {
                    StateManager.getInstance().setCurrentMdFocusReq(GalReceiver.AUDIO_FOCUS_GAIN);
                }
            }
            if (AapController.this.mVideoControllerAdapter != null) {
                boolean isVrRunningInPowerOff = StateManager.getInstance().getVrRunningInPowerOffState();
                if (isVrRunningInPowerOff) {
                    LogUtil.debug(AapController.TAG, "onVoiceSessionNotification end in poweroff ");
                    AapController.this.handleVrStopInPoweroff();
                } else if (StateManager.getInstance().getPhoneStateByRunningVr()) {
                    StateManager.getInstance().setPhoneStateByRunningVr(false);
                }
            }
        }

        @Override // com.google.android.projection.protocol.GalReceiver.AppMessageListener
        public void onBatteryStatusNotification(int batteryLevel, int timeRemainingS, boolean batteryCritical) {
            LogUtil.debug(AapController.TAG, "onBatteryStatusNotification start:Battery status: " + batteryLevel + " percent remaining/" + timeRemainingS + " seconds remaining");
            String str = AapController.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onBatteryStatusNotification batteryCritical: ");
            sb.append(batteryCritical);
            LogUtil.debug(str, sb.toString());
            if (AapController.this.mLinkDevice != null && AapController.this.mLinkDevice.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal()) {
                LogUtil.debug(AapController.TAG, "onBatteryStatusNotification wirelss need show popup");
                if (AapController.this.mAapControllerListener != null) {
                    AapController.this.mAapControllerListener.onPhoneManagerBatteryStatusNotification(batteryLevel, timeRemainingS, batteryCritical);
                }
            }
            LogUtil.debug(AapController.TAG, "onBatteryStatusNotification end");
        }

        @Override // com.google.android.projection.protocol.GalReceiver.AppMessageListener
        public void onUnrecoverableError(Protos.MessageStatus status) {
            LogUtil.error(AapController.TAG, "onUnrecoverableError start error " + status + ". Shutting down.");
            if (status == Protos.MessageStatus.STATUS_AUTHENTICATION_FAILURE_CERT_NOT_YET_VALID || status == Protos.MessageStatus.STATUS_AUTHENTICATION_FAILURE_CERT_EXPIRED) {
                AapController.this.mFailedError = AndroidAutoConst.LinkErrorCode.ERROR_AUTHENTICATION_FAILURE_CERT_NOT_YET_VALID;
            } else {
                AapController.this.mFailedError = AndroidAutoConst.LinkErrorCode.ERROR_UNRECOVERABLE_ERROR;
            }
            if (AapController.this.mGal != null && AapController.this.mGal.galReceiver != null) {
                LogUtil.error(AapController.TAG, "Received unrecoverable send byebye request to md");
                AapController.this.mGal.galReceiver.sendByeByeRequest(1);
                try {
                    if (AapController.this.mWaitByebyeSemaphore == null || !AapController.this.mWaitByebyeSemaphore.tryAcquire(200L, TimeUnit.MILLISECONDS)) {
                        LogUtil.info(AapController.TAG, "onUnrecoverableError byebye Timeout");
                    } else {
                        LogUtil.info(AapController.TAG, "onUnrecoverableError byebye response");
                    }
                } catch (InterruptedException e) {
                    LogUtil.info(AapController.TAG, "onUnrecoverableError byebye InterruptedException");
                }
                if (status == Protos.MessageStatus.STATUS_PING_TIMEOUT) {
                    AapController.this.mGal.stop(Transport.StopReason.PING_TIMEOUT);
                } else {
                    AapController.this.mGal.stop(Transport.StopReason.CONNECTION_ERROR);
                }
            }
            LogUtil.error(AapController.TAG, "onUnrecoverableError end");
        }

        @Override // com.google.android.projection.protocol.GalReceiver.AppMessageListener
        public void onDisconnected(Transport.StopReason stopReason) {
            LogUtil.debug(AapController.TAG, "onDisconnected start Shuting down GAL/transport: " + stopReason + ". mFailedError: " + AapController.this.mFailedError);
            if (stopReason != Transport.StopReason.MD_BYEBYE && stopReason != Transport.StopReason.HU_BYEBYE) {
                if (stopReason == Transport.StopReason.PING_TIMEOUT) {
                    AapController.this.mFailedError = AndroidAutoConst.LinkErrorCode.ERROR_PING_TIMEOUT;
                } else if (stopReason == Transport.StopReason.USER_SWITCH) {
                    AapController.this.mFailedError = AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE;
                } else if (stopReason == Transport.StopReason.CONNECTION_ERROR && AapController.this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING && AapController.this.mFailedError != AndroidAutoConst.LinkErrorCode.ERROR_AUTHENTICATION_FAILURE_CERT_NOT_YET_VALID) {
                    LogUtil.info(AapController.TAG, "Shutting down connect error show fail message");
                    AapController.this.mFailedError = AndroidAutoConst.LinkErrorCode.ERROR_TCP_CONNECTION_FAILURE;
                }
            }
            synchronized (AapController.this.mLock) {
                if (AapController.this.mGal != null) {
                    LogUtil.debug(AapController.TAG, "Stop gal receiver after disconnected");
                    AapController.this.mGal.stop(Transport.StopReason.CONNECTION_TERMINATED);
                }
            }
            AapController aapController = AapController.this;
            aapController.releaseConnection(aapController.mFailedError);
            LogUtil.debug(AapController.TAG, "onDisconnected end");
        }

        @Override // com.google.android.projection.protocol.GalReceiver.AppMessageListener
        public void onPoorWirelessNetworkConnectivity() {
            LogUtil.warning(AapController.TAG, "Wireless connectivity is poor");
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.onPhoneManagerPoorWirelessNetworkConnectivity();
            }
        }
    };
    public final GalReceiver.ProjectedModeIconReceiver mProjectedModeIconReceiver = new GalReceiver.ProjectedModeIconReceiver() { // from class: com.ts.androidauto.aap.AapController.4
        @Override // com.google.android.projection.protocol.GalReceiver.ProjectedModeIconReceiver
        public void setProjectedModeIcon(byte[] iconArray, String label) {
            LogUtil.debug(AapController.TAG, "setProjectedModeIcon is start ");
            AapController.this.removeReleaseMessage(2);
            AapController.this.mFailedError = AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE;
            AapController.this.updateCallAvailabilityForServiceDiscovery();
            if (AapController.this.mSensorDispatcher != null) {
                LogUtil.info(AapController.TAG, "startSendDrivingStatusThread is start ");
                AapController.this.mSensorDispatcher.startSendDrivingStatus();
                LogUtil.info(AapController.TAG, "reportVehicleData is start ");
                AapController.this.mSensorDispatcher.reportVehicleData();
                LogUtil.info(AapController.TAG, "startSendVehicleThread is start ");
                AapController.this.mSensorDispatcher.startSendVehicleThread();
                if (AapController.this.mSensorDispatcher.checkSimulationFileExists()) {
                    LogUtil.info(AapController.TAG, "Simulation File is exists ");
                    AapController.this.mSensorDispatcher.startSimulationGuidance();
                }
            }
            if (AapController.this.mAapAudioManager != null) {
                File micFile = new File("/sdcard/dumpmic");
                if (micFile.exists()) {
                    LogUtil.debug(AapController.TAG, "Enable dumping mic audio data");
                    AapController.this.mAapAudioManager.setRecorderStreamSwitcher(true);
                }
                File audioFile = new File("/sdcard/dumpaudio");
                if (audioFile.exists()) {
                    LogUtil.debug(AapController.TAG, "Enable dumping audio sink data");
                    AapController.this.mAapAudioManager.setMediaStreamSwitcher(true);
                    AapController.this.mAapAudioManager.setTtsStreamSwitcher(true);
                }
            }
            if (AapController.this.mHandleInputEvent != null) {
                File debugTouch = new File("/sdcard/debugtouch");
                if (debugTouch.exists()) {
                    AapController.this.mHandleInputEvent.setTouchDebugSwitcher(true);
                }
            }
            AapController.this.startCheckFirstUseThread();
            LogUtil.debug(AapController.TAG, "setProjectedModeIcon is end ");
        }
    };
    private final GalReceiver.ByeByeHandler mByeByeHandler = new AnonymousClass5();
    private NavigationFocusManager.NavigationFocusListener mNavigationFocusListener = new NavigationFocusManager.NavigationFocusListener() { // from class: com.ts.androidauto.aap.AapController.7
        @Override // com.ts.androidauto.aap.navigation.NavigationFocusManager.NavigationFocusListener
        public void onNavigationFocusChanged(boolean state) {
            LogUtil.info(AapController.TAG, "Navigation focus change to " + state);
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifyNaviFocusChanged(state);
            }
        }
    };
    private AapVideoCallback mAapVideoCallback = new AapVideoCallback() { // from class: com.ts.androidauto.aap.AapController.8
        @Override // com.ts.androidauto.aap.video.AapVideoCallback
        public void onVideoFocusStatusChanged(int videoFocusStatus) {
            LogUtil.debug(AapController.TAG, "videoFocusStatus: " + videoFocusStatus);
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifyVideoFocusStatusChanged(videoFocusStatus);
            }
        }

        @Override // com.ts.androidauto.aap.video.AapVideoCallback
        public void onClusterVideoFocusStatusChanged(int videoFocusStatus) {
            LogUtil.debug(AapController.TAG, "onClusterVideoFocusStatusChanged: " + videoFocusStatus);
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifyClusterVideoFocusStatusChanged(videoFocusStatus);
            }
        }

        @Override // com.ts.androidauto.aap.video.AapVideoCallback
        public void onClusterVideoUpdateUiConfigReply(boolean replyOk) {
            LogUtil.debug(AapController.TAG, "onClusterVideoUpdateUiConfigReply replyOk:" + replyOk);
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifyClusterVideoUpdateUiConfigReply(replyOk);
            }
        }
    };
    private AapDisPlayCallback mDisPlayCallback = new AapDisPlayCallback() { // from class: com.ts.androidauto.aap.AapController.9
        @Override // com.ts.androidauto.aap.AapDisPlayCallback
        public void onStatus(int status) {
            LogUtil.debug(AapController.TAG, "onStatus status:" + status);
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifyNavigationStatusChanged(status);
            }
        }

        @Override // com.ts.androidauto.aap.AapDisPlayCallback
        public void onNavigationState(NavigationStateData navigationStateData) {
            if (navigationStateData != null && AapController.this.mAapControllerListener != null) {
                LogUtil.debug(AapController.TAG, "onNavigationState");
                AapController.this.mAapControllerListener.notifyNavigationState(navigationStateData);
            }
        }

        @Override // com.ts.androidauto.aap.AapDisPlayCallback
        public void onNavigationCurrentPosition(NavigationPositionData navigationPositionData) {
            LogUtil.debug(AapController.TAG, "onNavigationCurrentPosition ");
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifyNavigationCurrentPosition(navigationPositionData);
            }
        }

        @Override // com.ts.androidauto.aap.AapDisPlayCallback
        public void notifyMediaStatusChange(int mediaStatus) {
            LogUtil.debug(AapController.TAG, "notifyMediaStatusChange mediaStatus" + mediaStatus);
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifyMediaStatusChange(mediaStatus);
                if (mediaStatus != AapController.this.mPrePlayState) {
                    if (mediaStatus == AndroidAutoConst.MusicStatus.MUSIC_STATUS_PLAYING.ordinal()) {
                        AapController.this.sendWarnMessage();
                        AapController.this.mPrePlayState = mediaStatus;
                    } else {
                        AapController.this.removeWarnMessage();
                        AapController.this.mPrePlayState = mediaStatus;
                    }
                }
            }
        }

        @Override // com.ts.androidauto.aap.AapDisPlayCallback
        public void notifyMediaMetadataChange(MediaMetadata mediaMetadata) {
            if (mediaMetadata != null && AapController.this.mAapControllerListener != null) {
                LogUtil.debug(AapController.TAG, "notifyMediaMetadataChange mediaMetadata" + mediaMetadata);
                AapController.this.mAapControllerListener.notifyMediaMetadataChange(mediaMetadata);
            }
        }

        @Override // com.ts.androidauto.aap.AapDisPlayCallback
        public void notifyMediaProgressChange(int progress) {
            LogUtil.debug(AapController.TAG, "notifyMediaProgressChange progress:" + progress);
            if (AapController.this.mAapControllerListener != null) {
                AapController.this.mAapControllerListener.notifyMediaProgressChange(progress);
            }
        }

        @Override // com.ts.androidauto.aap.AapDisPlayCallback
        public void notifyPhoneStatus(ArrayList<PhoneStatusData> phoneStatusList) {
        }
    };
    private AapBluetoothManager mAapBluetoothManager = AapBluetoothManager.getInstance();

    private void initGalReceiverHandler() {
        HandlerThread handlerThread = new HandlerThread("GalReceiverHandler", -19);
        this.mGalReceiverHandlerThread = handlerThread;
        handlerThread.start();
        this.mGalReceiverHandler = new Handler(this.mGalReceiverHandlerThread.getLooper());
    }

    public AapController() {
        this.mAapDisplayManager = null;
        this.mAapDisplayManager = AapDisplayManager.getInstance();
        initGalReceiverHandler();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mAapControllerHandlerThread = handlerThread;
        handlerThread.start();
        this.mAapControllerLooper = this.mAapControllerHandlerThread.getLooper();
        this.mAapControllerHandler = new MessageHandler(this.mAapControllerHandlerThread.getLooper());
    }

    public static synchronized AapController getInstance() {
        if (mAapController == null) {
            mAapController = new AapController();
        }
        return mAapController;
    }

    public void init(IVehicleControllerAdapter vehicleControllerAdapter, IBluetoothControllerAdapter bluetoothController, IAudioControllerAdapter audioControllerAdapter, IVideoControllerAdapter videoControllerAdapter) {
        this.mVehicleControllerAdapter = vehicleControllerAdapter;
        this.mVideoControllerAdapter = videoControllerAdapter;
        this.mAapBluetoothManager.setAapController(getInstance());
        this.mAapBluetoothManager.setBluetoothController(bluetoothController);
        this.mAudioControllerAdapter = audioControllerAdapter;
        initChannelFrequencyMap();
        this.mAapDisplayManager.registDisPlayCallback(this.mDisPlayCallback);
        initDisplayActivityInfo();
    }

    public void deInit() {
        AapDisplayManager aapDisplayManager = this.mAapDisplayManager;
        if (aapDisplayManager != null) {
            aapDisplayManager.unRegistDisPlayCallback();
        }
    }

    public void initAap(TransportFactory factory, int wifiPort) {
        LogUtil.info(TAG, "initAap is context:" + this.mContext);
        LogUtil.info(TAG, "initAap wifi port:" + wifiPort);
        this.mIsSendFinishBroadcast = false;
        int i = mCurrFactoryId + 1;
        mCurrFactoryId = i;
        if (i > MAX_FACTORY_ID_VALUE) {
            mCurrFactoryId = 0;
        }
        this.mWaitByebyeSemaphore = new Semaphore(0);
        AapVideoManager aapVideoManager = AapVideoManager.getInstance();
        this.mAapVideoManager = aapVideoManager;
        aapVideoManager.registerVideoCallback(this.mAapVideoCallback);
        AapClusterVideoManager aapClusterVideoManager = AapClusterVideoManager.getInstance();
        this.mAapClusterVideoManager = aapClusterVideoManager;
        aapClusterVideoManager.registerVideoCallback(this.mAapVideoCallback);
        this.mAapAudioManager = AapAudioManager.getInstance();
        this.mHandleInputEvent = HandleInputEvent.getInstance(this.mContext);
        this.mInstrumentClusterListener = InstrumentClusterListener.getInstance(this.mAapDisplayManager);
        this.mSensorDispatcher = VehicleDataManager.getInstance();
        registerVideoDataListener();
        mCurrWifiPort = wifiPort;
        this.mIsInTcpSetup = false;
        this.mIsCancelConnect = false;
        if (factory == null) {
            this.mTransportFactory = TransportFactory.builder().setContext(this.mContext).setTcpListenPort(mCurrWifiPort).setFactoryId(mCurrFactoryId).build();
            LogUtil.info(TAG, "initAap mCurrFactoryId:" + mCurrFactoryId);
        } else {
            this.mTransportFactory = factory;
        }
        GalIntegration galIntegration = GalIntegration.getInstance();
        this.mGal = galIntegration;
        this.mSensorDispatcher.initSensorManager(this.mContext, galIntegration, this.mVehicleControllerAdapter, false);
        if (this.mNavigationFocusManager == null) {
            NavigationFocusManager navigationFocusManager = NavigationFocusManager.getInstance(this.mGal, this.mContext);
            this.mNavigationFocusManager = navigationFocusManager;
            navigationFocusManager.registerListener(this.mNavigationFocusListener);
        }
        this.mAapBluetoothManager.setVideoController(this.mVideoControllerAdapter);
        this.mIsTcpFirstConnected = true;
        this.mFailedError = AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE;
        StateManager.getInstance().resetAllState();
    }

    public static String getDefaultWifiApMac(String wifiMac) {
        LogUtil.debug(TAG, "getDefaultWifiApMac wifiMac: " + LogEncrypt.MacAddressEncrypt(wifiMac));
        String[] splitMacAddress = wifiMac.split(":");
        if (splitMacAddress.length == 6) {
            if (splitMacAddress[0].equals("00")) {
                splitMacAddress[0] = "02";
            }
            if (splitMacAddress[3].equals("fd")) {
                splitMacAddress[3] = "7e";
            }
            StringBuilder modified = new StringBuilder();
            int index = 0;
            while (index < 5) {
                modified.append(splitMacAddress[index]);
                modified.append(":");
                index++;
            }
            modified.append(splitMacAddress[index]);
            String modifiedMac = modified.toString();
            LogUtil.debug(TAG, "getDefaultWifiApMac modified mac: " + LogEncrypt.MacAddressEncrypt(modifiedMac));
            return modifiedMac;
        }
        LogUtil.debug(TAG, "Invalid mac wifi address: " + LogEncrypt.MacAddressEncrypt(wifiMac));
        return null;
    }

    public static int getCurrWifiPort() {
        LogUtil.info(TAG, "getCurrWifiPort " + mCurrWifiPort);
        return mCurrWifiPort;
    }

    public WifiConnectInfo getWifiApInfo() {
        return this.mWifiConnectInfo;
    }

    private void registerReceiver() {
        LogUtil.info(TAG, "Test: register receiver!");
        IntentFilter intent = new IntentFilter();
        intent.addAction(NAVI_SIMULATION_TEST_START);
        intent.addAction(NAVI_SIMULATION_TEST_END);
        intent.addAction(DUMP_AUDIO_TEST_START);
        intent.addAction(DUMP_AUDIO_TEST_END);
        this.mContext.registerReceiver(this.reveiver, intent);
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setVideoInfo(AndroidAutoDevice linkDevice, AndroidAutoConst.ShowMode showMode, SurfaceData surfaceData) {
        LogUtil.info(TAG, "setVideoInfo surface:" + surfaceData.getSurface());
        this.mSurfaceData = surfaceData;
        this.mShowMode = showMode;
    }

    public int show(AndroidAutoDevice linkDevice, AndroidAutoConst.ShowMode showMode, SurfaceData surfaceData) {
        LogUtil.info(TAG, "showVideo surface :" + surfaceData.getSurface() + " Width:" + surfaceData.getWidth() + " Height:" + surfaceData.getHeight());
        if (this.mAapVideoManager != null && isAapConnected()) {
            this.mAapVideoManager.showVideo(surfaceData.getSurface(), surfaceData.getWidth(), surfaceData.getHeight());
            return 0;
        }
        return 0;
    }

    public int hide(AndroidAutoDevice linkDevice) {
        LogUtil.info(TAG, "hide video is invoke~");
        if (this.mAapVideoManager != null && isAapConnected()) {
            this.mAapVideoManager.hideVideo();
            return 0;
        }
        return 0;
    }

    public int showCluster(AndroidAutoDevice linkDevice, AndroidAutoConst.ShowMode showMode, SurfaceData surfaceData) {
        LogUtil.info(TAG, "showClusterVideo surface :" + surfaceData.getSurface() + " Width:" + surfaceData.getWidth() + " Height:" + surfaceData.getHeight());
        if (this.mAapClusterVideoManager != null && isAapConnected()) {
            this.mAapClusterVideoManager.showVideo(surfaceData.getSurface(), surfaceData.getWidth(), surfaceData.getHeight());
            return 0;
        }
        return 0;
    }

    public int hideCluster(AndroidAutoDevice linkDevice) {
        LogUtil.info(TAG, "hide Cluster video is invoke~");
        if (this.mAapClusterVideoManager != null && isAapConnected()) {
            this.mAapClusterVideoManager.hideVideo();
            return 0;
        }
        return 0;
    }

    public void screenTouchEvent(MotionEvent event) {
        if (this.mHandleInputEvent != null && isAapConnected()) {
            this.mHandleInputEvent.onTouch(event);
        }
    }

    private void handleWifiChannelSetupFailed() {
        if (this.mConnectingDevice != null) {
            AndroidAutoConst.LinkStatus linkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
            this.mLinkStatus = linkStatus;
            AapControllerListener aapControllerListener = this.mAapControllerListener;
            if (aapControllerListener != null) {
                aapControllerListener.onPhoneManagerStatusChanged(this.mConnectingDevice, linkStatus, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED);
            }
            LogUtil.info(TAG, "notifiWifiChannelSetupFinish failed mIsMdRequestConnect:" + this.mIsMdRequestConnect);
            if (this.mIsMdRequestConnect) {
                this.mIsMdRequestConnect = false;
                AapRfcommConnection rfcomm = this.mAapWirelessLinkMap.get(this.mConnectingDevice.getBluetoothDevice());
                LogUtil.info(TAG, "notifiWifiChannelSetupFinish failed rfcomm:" + rfcomm);
                if (rfcomm != null) {
                    rfcomm.sendMdStartFailedResponse();
                }
            }
        }
    }

    public void notifiWifiChannelSetupFinish(WifiConnectInfo wifiConnectInfo, boolean isSuccess) {
        if (!isSuccess) {
            LogUtil.info(TAG, "notifiWifiChannelSetupFinish failed:" + isSuccess);
            handleWifiChannelSetupFailed();
            return;
        }
        if (wifiConnectInfo != null) {
            this.mWifiConnectInfo = wifiConnectInfo;
            LogUtil.info(TAG, "notifiWifiChannelSetupFinish mWifiConnectInfo:" + this.mWifiConnectInfo);
        }
        AndroidAutoDevice androidAutoDevice = this.mConnectingDevice;
        if (androidAutoDevice == null || androidAutoDevice.getBluetoothDevice() == null) {
            LogUtil.error(TAG, "notifiWifiChannelSetupFinish invalid mConnectingDevice: " + this.mConnectingDevice);
            return;
        }
        if (wifiConnectInfo == null) {
            LogUtil.info(TAG, "ap setup wifiConnectInfo is null ,connect failed");
            AndroidAutoConst.LinkStatus linkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
            this.mLinkStatus = linkStatus;
            AapControllerListener aapControllerListener = this.mAapControllerListener;
            if (aapControllerListener != null) {
                aapControllerListener.onPhoneManagerStatusChanged(this.mConnectingDevice, linkStatus, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED);
                return;
            }
            return;
        }
        AapRfcommConnection connection = this.mAapWirelessLinkMap.get(this.mConnectingDevice.getBluetoothDevice());
        if (connection != null) {
            LogUtil.info(TAG, "notifiWifiChannelSetupFinish start");
            connection.wifiApSetUpFinish(wifiConnectInfo);
            return;
        }
        LogUtil.error(TAG, "notifiWifiChannelSetupFinish invalid rfcomm connection: " + this.mConnectingDevice.getBluetoothDevice());
    }

    public void updateWifiApInfo(WifiConnectInfo wifiConnectInfo) {
        if (wifiConnectInfo != null) {
            this.mWifiConnectInfo = wifiConnectInfo;
            LogUtil.info(TAG, "updateWifiApInfo mWifiConnectInfo:" + this.mWifiConnectInfo);
        }
    }

    public void updateWirelessDevice(AndroidAutoDevice linkDevice) {
        LogUtil.verbose(TAG, "updateWirelessDevice linkDevice: " + linkDevice);
        this.mConnectingDevice = linkDevice;
    }

    public void connectWirelessDelay(BluetoothSocket bluetoothSocket, AndroidAutoDevice linkDevice) {
        LogUtil.info(TAG, "connectWirelessDelay is invoke");
        this.mIsConnecteDelay = true;
        this.mConnectingDevice = linkDevice;
        checkSupportAapWireless(bluetoothSocket, linkDevice);
        LogUtil.info(TAG, "connectWirelessDelay mIsCancel:" + this.mIsCancelConnect + " start Get socket:" + this.mIsStartTcpServer);
        synchronized (this.mStartTcpLock) {
            if (!this.mIsStartTcpServer) {
                this.mIsStartTcpServer = true;
                initAap(null, AapConfig.WIFI_PORT_FIRST);
                if (!this.mIsCancelConnect) {
                    startTcpServerMode();
                } else {
                    this.mIsCancelConnect = false;
                }
            }
        }
    }

    public boolean checkSupportAapWireless(BluetoothSocket bluetoothSocket, AndroidAutoDevice linkDevice) {
        LogUtil.verbose(TAG, "checkSupportAapWireless socket: " + bluetoothSocket);
        LogUtil.verbose(TAG, " linkDevice: " + linkDevice);
        LogUtil.verbose(TAG, " mAapBluetoothManager:" + this.mAapBluetoothManager);
        if (linkDevice == null) {
            LogUtil.info(TAG, "checkSupportAapWireless linkDevice is null");
            return false;
        }
        AapRfcommConnection connection = getRfcommConnection(linkDevice, bluetoothSocket);
        if (connection == null) {
            return false;
        }
        connection.sendVersionRequestMessage();
        return true;
    }

    public AapRfcommConnection getRfcommConnection(AndroidAutoDevice device, BluetoothSocket bluetoothSocket) {
        LogUtil.debug(TAG, "getRfcomm device: " + device);
        LogUtil.debug(TAG, "getRfcomm socket: " + bluetoothSocket);
        if (device == null) {
            LogUtil.error(TAG, "Invalid device to get rfcomm connection");
            return null;
        }
        BluetoothDevice btDevice = device.getBluetoothDevice();
        if (btDevice == null) {
            LogUtil.error(TAG, "Invalid bt device to get rfcomm connection");
            return null;
        }
        AapRfcommConnection connection = this.mAapWirelessLinkMap.get(btDevice);
        if (connection != null) {
            LogUtil.debug(TAG, "Already has a connection for bt device: " + btDevice);
            if (bluetoothSocket != null) {
                LogUtil.info(TAG, "Update rfcomm connection: " + connection + " with a new socket: " + bluetoothSocket);
                connection.updateWirelessSocket(bluetoothSocket);
            }
            return connection;
        }
        LogUtil.debug(TAG, "Create a new connection for bt device:" + btDevice);
        AapRfcommConnection rfcommConnection = new AapRfcommConnection(device, bluetoothSocket, this.mAapControllerLooper);
        this.mAapWirelessLinkMap.put(btDevice, rfcommConnection);
        rfcommConnection.registerRfcommConnectCallbacks(this.mRfcommConnectCallbacks);
        return rfcommConnection;
    }

    public void delRfcommConnection(BluetoothDevice device) {
        LogUtil.debug(TAG, "delRfcommConnection device: " + device);
        AapRfcommConnection connection = this.mAapWirelessLinkMap.get(device);
        if (connection != null) {
            connection.unRegisterRfcommConnectCallbacks();
            this.mAapWirelessLinkMap.remove(device);
        }
    }

    public void notifiAoaModeResult(StatusConst.AoaStatus aoaStatus, AndroidAutoDevice linkDevice) {
        LogUtil.info(TAG, "notifiAoaModeResult state:" + aoaStatus + " device: " + linkDevice);
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("notifiAoaModeResult linkDevice:");
        sb.append(this.mConnectingDevice);
        LogUtil.info(str, sb.toString());
        if (linkDevice == null) {
            LogUtil.error(TAG, "notifiAoaModeResult invalid device");
            if (this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING) {
                this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
                return;
            }
            return;
        }
        if (aoaStatus == StatusConst.AoaStatus.AOA_STATUS_SUCCESS) {
            if (linkDevice != null && linkDevice.equals(linkDevice)) {
                LogUtil.info(TAG, "notifiAoaModeResult linkType:" + linkDevice.getLinkType() + " mIsDuringRequestAoa:" + this.mIsDuringRequestAoa);
                if (!this.mIsDuringRequestAoa) {
                    return;
                }
                this.mIsDuringRequestAoa = false;
                if (linkDevice.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal()) {
                    this.mConnectingDevice = linkDevice;
                    LogUtil.info(TAG, "startHostMode start");
                    sendStartHostModeMessage(this.mConnectingDevice.getUsbDevice());
                    return;
                }
                return;
            }
            return;
        }
        LogUtil.info(TAG, "AOA change failed");
        if (this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING) {
            this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
        }
    }

    public AndroidAutoConst.LinkStatus connecting(AndroidAutoDevice device) {
        AapRfcommConnection connection;
        LogUtil.info(TAG, "Start connect with mLinkStatus:" + this.mLinkStatus);
        if (device != null) {
            if (this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING) {
                LogUtil.info(TAG, "Start connect durring disconneting, return failed");
                return AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
            }
            LogUtil.info(TAG, "connecting type:" + device.getLinkType());
            AndroidAutoDevice androidAutoDevice = this.mConnectingDevice;
            if (androidAutoDevice != null && device != androidAutoDevice && this.mIsMdRequestConnect && (connection = this.mAapWirelessLinkMap.get(androidAutoDevice.getBluetoothDevice())) != null) {
                connection.terminateConnectionFromMd();
            }
            this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING;
            this.mConnectingDevice = device;
            if (device.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal()) {
                initAap(null, AapConfig.WIFI_PORT_FIRST);
                this.mIsDuringRequestAoa = true;
                this.mAapLinkMap.put(device.getUsbDevice().getSerialNumber(), this.mConnectingDevice);
                LogUtil.info(TAG, "connecting onVehicleReqAoaMode Start");
                StatusConst.AoaStatus status = StatusConst.AoaStatus.AOA_STATUS_SUCCESS;
                notifiAoaModeResult(status, device);
                LogUtil.info(TAG, "connecting onVehicleReqAoaMode end");
            } else if (device.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal()) {
                LogUtil.info(TAG, "connecting mIsCancelConnect:" + this.mIsCancelConnect + " start Get socket:" + this.mIsStartTcpServer);
                synchronized (this.mStartTcpLock) {
                    if (!this.mIsStartTcpServer) {
                        this.mIsStartTcpServer = true;
                        initAap(null, AapConfig.WIFI_PORT_FIRST);
                        if (!this.mIsCancelConnect) {
                            startTcpServerMode();
                        } else {
                            this.mIsCancelConnect = false;
                        }
                    }
                }
                AapControllerListener aapControllerListener = this.mAapControllerListener;
                if (aapControllerListener != null) {
                    aapControllerListener.onPhoneManagerSetupWifiAp(this.mConnectingDevice, false);
                }
            }
            return AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING;
        }
        return AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
    }

    private void handleInterruptTransportHandle(AndroidAutoDevice device) {
        if (device != null) {
            if (device.isWireLessLink()) {
                if (this.mTransportHandle != null) {
                    try {
                        LogUtil.info(TAG, "disconnecting interrupt get tcp socket");
                        this.mTransportHandle.interrupt();
                        return;
                    } catch (Exception exception) {
                        LogUtil.warning(TAG, "disconnecting attempt to establish" + exception.toString());
                        return;
                    }
                }
                return;
            }
            if (device.isWireLink()) {
                this.mIsDuringRequestAoa = false;
            }
        }
    }

    public AndroidAutoConst.LinkStatus disconnecting(AndroidAutoDevice device, int reason) {
        LogUtil.verbose(TAG, "Start disconnect mLinkStatus: " + this.mLinkStatus + " mIsStartGal:" + this.mIsStartGal);
        handleInterruptTransportHandle(device);
        if (isAapConnected()) {
            this.mFailedError = AndroidAutoConst.LinkErrorCode.ERROR_USER_SELECT_DISCONNECT;
            if (reason == 6) {
                LogUtil.info(TAG, "Current device is not support AA Projection!");
                this.mFailedError = AndroidAutoConst.LinkErrorCode.ERROR_NOT_SUPPORTED;
            }
            GalIntegration galIntegration = this.mGal;
            if (galIntegration != null && galIntegration.galReceiver != null) {
                this.mGal.galReceiver.sendByeByeRequest(1);
            }
            try {
                if (this.mWaitByebyeSemaphore != null && this.mWaitByebyeSemaphore.tryAcquire(200L, TimeUnit.MILLISECONDS)) {
                    LogUtil.info(TAG, "disconnecting byebye response");
                } else {
                    LogUtil.info(TAG, "disconnecting byebye Timeout");
                }
            } catch (InterruptedException e) {
                LogUtil.info(TAG, "disconnecting byebye InterruptedException");
            }
            synchronized (this.mLock) {
                if (this.mGal != null) {
                    this.mGal.stop(Transport.StopReason.HU_BYEBYE);
                }
                if (this.mTransport != null) {
                    LogUtil.info(TAG, "disconnecting release mTransport stop");
                    this.mTransport.stopTransport(Transport.StopReason.HU_BYEBYE);
                }
            }
        } else if (this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING) {
            this.mConnectingDevice = device;
            if (this.mIsStartGal) {
                synchronized (this.mLock) {
                    LogUtil.info(TAG, "disconnecting stop Gal");
                    if (this.mGal != null) {
                        this.mGal.stop(Transport.StopReason.CONNECTION_TERMINATED);
                    }
                    if (this.mTransport != null) {
                        this.mTransport.stopTransport(Transport.StopReason.CONNECTION_DISCONNECTED);
                    }
                }
            } else {
                LogUtil.info(TAG, "disconnecting notify failed status");
                this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
                AndroidAutoConst.LinkErrorCode errorCode = AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE;
                releaseConnection(errorCode);
                AapControllerListener aapControllerListener = this.mAapControllerListener;
                if (aapControllerListener != null) {
                    aapControllerListener.onPhoneManagerStatusChanged(device, this.mLinkStatus, errorCode);
                }
            }
        } else {
            LogUtil.info(TAG, "disconnecting still available, can not disconnect...");
            return AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
        }
        return AndroidAutoConst.LinkStatus.LINK_STATUS_AVAILABLE;
    }

    public void onPhoneStatusChange(String address, BluetoothConst.BtPhoneStatus status) {
        LogUtil.verbose(TAG, "onPhoneStatusChange status is:" + status);
        LogUtil.debug(TAG, "onPhoneStatusChange: " + StateManager.getInstance().getPhoneRunningInPowerOffState() + " " + StateManager.getInstance().isVideoShowing());
        if (!isAapConnected()) {
            LogUtil.info(TAG, "AA is not connect, updateBtPhoneStatus:" + status);
            AapAudioFocusManager.updateBtPhoneStatus(status);
            return;
        }
        updateCallAvailability();
        AapBluetoothManager aapBluetoothManager = this.mAapBluetoothManager;
        if (aapBluetoothManager != null) {
            aapBluetoothManager.onPhoneStatusChange(address, status);
        }
        if (this.mVideoControllerAdapter != null) {
            LogUtil.debug(TAG, "onPhoneStatusChange is in power off: " + this.mVideoControllerAdapter.isInPowerOff());
            if (this.mVideoControllerAdapter.isInPowerOff() && status == BluetoothConst.BtPhoneStatus.PHONE_STATUS_ACTIVE) {
                LogUtil.info(TAG, "onPhoneStatusChange: change to display ON");
                StateManager.getInstance().setPhoneRunningInPowerOffState(true);
            } else if (status == BluetoothConst.BtPhoneStatus.PHONE_STATUS_IDLE) {
                StateManager.getInstance().setPhoneRunningInPowerOffState(false);
            }
        }
    }

    public boolean onPairRequest(String address, String pinCode) {
        LogUtil.verbose(TAG, "onPairRequest is invoke address:" + LogEncrypt.MacAddressEncrypt(address) + " pinCode: " + pinCode);
        if (this.mAapBluetoothManager != null && isAapConnected()) {
            return this.mAapBluetoothManager.onPairRequest(address, pinCode);
        }
        return false;
    }

    public void onNativePhoneCallStatusChange(int status) {
        LogUtil.info(TAG, "onNativePhoneCallStatusChange status:" + status);
        this.mNativePhoneCallStatus = status;
        if (isAapConnected()) {
            updateCallAvailability();
        }
    }

    public void updateAapMediaMetaData() {
        InstrumentClusterListener instrumentClusterListener = this.mInstrumentClusterListener;
        if (instrumentClusterListener != null) {
            instrumentClusterListener.updateMediaMetaData();
        }
    }

    public void onProfileChange(String address, String profile, BluetoothConst.ProfileStatus status) {
        LogUtil.verbose(TAG, "onProfileChange is invoke address:" + LogEncrypt.MacAddressEncrypt(address) + " profile: " + profile + " ProfileStatus: " + status);
        AapBluetoothManager aapBluetoothManager = this.mAapBluetoothManager;
        if (aapBluetoothManager != null) {
            aapBluetoothManager.onProfileChange(address, profile, status);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startHostMode(UsbDevice device) {
        LogUtil.info(TAG, "startHostMode is invoke! " + device);
        if (this.mTransportFactory != null) {
            try {
                LogUtil.info(TAG, "Starting in host mode");
                this.mTransportFactory.open(device, this);
            } catch (IOException exception) {
                LogUtil.error(TAG, "Failed to start connection with " + device, exception);
            }
        }
    }

    private void sendStartHostModeMessage(UsbDevice device) {
        Message msg = Message.obtain();
        msg.what = 3;
        msg.obj = device;
        this.mAapControllerHandler.sendMessage(msg);
    }

    private void startTcpServerMode() {
        LogUtil.debug(TAG, "Getting tcp socket mTransportHandle: " + this.mTransportHandle);
        LogUtil.debug(TAG, "mTransportFactory: " + this.mTransportFactory);
        this.mIsInTcpSetup = true;
        if (this.mTransportFactory != null) {
            try {
                LogUtil.info(TAG, "Getting TCP socket on port " + getCurrWifiPort());
                this.mTransportHandle = this.mTransportFactory.open(null, this);
            } catch (IOException exception) {
                LogUtil.error(TAG, "Failed to get TCP socket on port " + exception.toString());
            }
        }
    }

    private class MessageHandler extends Handler {
        public MessageHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                LogUtil.info(AapController.TAG, "release force mFailedError:" + AapController.this.mFailedError);
                if (AapController.this.mFailedError == AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE) {
                    AapController.this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED;
                    if (AapController.this.mAapControllerListener != null) {
                        AapController.this.mAapControllerListener.onPhoneManagerStatusChanged(AapController.this.mLinkDevice, AapController.this.mLinkStatus, AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE);
                    }
                } else {
                    AapController.this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
                    if (AapController.this.mAapControllerListener != null) {
                        AapController.this.mAapControllerListener.onPhoneManagerStatusChanged(AapController.this.mLinkDevice, AapController.this.mLinkStatus, AapController.this.mFailedError);
                    }
                }
                if (AapController.this.mTransportFactory != null) {
                    AapController.this.mTransportFactory = null;
                }
                if (AapController.this.mAapVideoManager != null) {
                    AapController.this.mAapVideoManager.onVideoFocusStatusChanged(ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_NATIVE.ordinal());
                    AapController.this.unregisterVideoDataListener();
                    AapController.this.unregisterVideoCallback();
                    AapController.this.mAapVideoManager = null;
                }
                if (AapController.this.mAapClusterVideoManager != null) {
                    AapController.this.mAapClusterVideoManager.onClusterVideoFocusStatusChanged(ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_NATIVE.ordinal());
                    AapController.this.mAapClusterVideoManager.unRegisterVideoCallback();
                    AapController.this.mAapClusterVideoManager = null;
                }
                if (AapController.this.mAapAudioManager != null) {
                    AapController.this.mAapAudioManager = null;
                }
                if (AapController.this.mNavigationFocusManager != null) {
                    AapController.this.mNavigationFocusManager.resetNaviStatus();
                    AapController.this.mNavigationFocusManager.unregisterListener(AapController.this.mNavigationFocusListener);
                    AapController.this.mNavigationFocusManager = null;
                }
                if (AapController.this.mInstrumentClusterListener != null) {
                    LogUtil.info(AapController.TAG, "release force clear media state and meta data");
                    AapController.this.mInstrumentClusterListener.clearMediaStateAndMetaData();
                }
                synchronized (AapController.this.mLock) {
                    if (AapController.this.mGal != null) {
                        AapController.this.mGal = null;
                        AapController.this.mTransport = null;
                    }
                }
                if (AapController.this.mSensorDispatcher != null) {
                    AapController.this.mSensorDispatcher = null;
                }
                AapController.this.stopCheckFirstUseThread();
                StateManager.getInstance().setVideoShowing(false);
                StateManager.getInstance().setPhoneRunningInPowerOffState(false);
                StateManager.getInstance().setVrRunningInPowerOffState(false);
                StateManager.getInstance().setPhoneStateByRunningVr(false);
                AapController.this.mIsStartSimulation = false;
                return;
            }
            if (msg.what == 2) {
                LogUtil.info(AapController.TAG, "Wired Android Auto service discovery timeout.");
                AapController.this.mFailedError = AndroidAutoConst.LinkErrorCode.ERROR_NOT_SUPPORTED;
                synchronized (AapController.this.mLock) {
                    if (AapController.this.mIsStartGal) {
                        LogUtil.info(AapController.TAG, "MESSAGE_WIRED_SERVICE_DISCOVERY_TIMEOUT stop Gal");
                        if (AapController.this.mGal != null) {
                            AapController.this.mGal.stop(Transport.StopReason.CONNECTION_TERMINATED);
                        }
                        if (AapController.this.mTransport != null) {
                            AapController.this.mTransport.stopTransport(Transport.StopReason.CONNECTION_DISCONNECTED);
                        }
                    }
                }
                return;
            }
            if (msg.what == 3) {
                UsbDevice device = (UsbDevice) msg.obj;
                AapController.this.startHostMode(device);
            } else if (msg.what == 4) {
                LogUtil.warning(AapController.TAG, "Audio MediaStream don't have data!");
            }
        }
    }

    private void sendReleaseEmptyMessageDelayed(int what, long delayTime) {
        if (this.mAapControllerHandler != null) {
            LogUtil.info(TAG, "sendReleaseEmptyMessageDelayed what:" + what);
            this.mAapControllerHandler.sendEmptyMessageDelayed(what, delayTime);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeReleaseMessage(int what) {
        MessageHandler messageHandler = this.mAapControllerHandler;
        if (messageHandler != null && messageHandler.hasMessages(what)) {
            LogUtil.info(TAG, "removeReleaseMessage what:" + what);
            this.mAapControllerHandler.removeMessages(what);
        }
    }

    public void removeWarnMessage() {
        MessageHandler messageHandler = this.mAapControllerHandler;
        if (messageHandler != null && messageHandler.hasMessages(4) && this.mIsSendWarnMessage) {
            LogUtil.debug(TAG, "removeMessage SHOW_WARN_INFO");
            this.mAapControllerHandler.removeMessages(4);
            this.mIsSendWarnMessage = false;
        }
    }

    public void sendWarnMessage() {
        if (this.mAapControllerHandler != null) {
            LogUtil.debug(TAG, "sendWarnMessage SHOW_WARN_INFO");
            this.mAapControllerHandler.sendEmptyMessageDelayed(4, 1000L);
            this.mIsSendWarnMessage = true;
        }
    }

    public void quitAapDisplayActivity() {
        LogUtil.info(TAG, "quitAapDisplayActivity is invoke");
        this.mIsSendFinishBroadcast = true;
        Intent finishShowActivityIntent = new Intent();
        finishShowActivityIntent.setAction(FINISH_SHOW_ACTIVITY_ACTION);
        this.mContext.sendBroadcast(finishShowActivityIntent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void releaseConnection(AndroidAutoConst.LinkErrorCode error) {
        LogUtil.debug(TAG, "releaseConnection --- mLinkStatus: " + this.mLinkStatus);
        boolean isNeedReportStatus = true;
        if (this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED || this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED || this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_ERROR || this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING) {
            LogUtil.info(TAG, "Already disconnect, do not report status");
            isNeedReportStatus = false;
        }
        this.mIsCallAvailability = true;
        this.mIsStartGal = false;
        synchronized (this.mStartTcpLock) {
            this.mIsStartTcpServer = false;
        }
        if (this.mLinkDevice != null && this.mLinkDevice.getUsbDevice() != null) {
            this.mAapLinkMap.remove(this.mLinkDevice.getUsbDevice().getSerialNumber());
        }
        if (isNeedReportStatus) {
            AndroidAutoConst.LinkStatus linkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING;
            this.mLinkStatus = linkStatus;
            if (this.mAapControllerListener != null) {
                this.mAapControllerListener.onPhoneManagerStatusChanged(this.mLinkDevice, linkStatus, error);
            }
        }
        quitAapDisplayActivity();
        if (error == AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE || error == AndroidAutoConst.LinkErrorCode.ERROR_USER_SELECT_DISCONNECT) {
            sendReleaseEmptyMessageDelayed(1, 5000L);
            if (this.mLinkDevice != null) {
                release(this.mLinkDevice.getUsbDevice());
            }
            removeReleaseMessage(1);
            if (isNeedReportStatus) {
                AndroidAutoConst.LinkStatus linkStatus2 = AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED;
                this.mLinkStatus = linkStatus2;
                if (this.mAapControllerListener != null) {
                    this.mAapControllerListener.onPhoneManagerStatusChanged(this.mLinkDevice, linkStatus2, error);
                }
            }
        } else {
            sendReleaseEmptyMessageDelayed(1, 5000L);
            if (this.mLinkDevice != null) {
                release(this.mLinkDevice.getUsbDevice());
            }
            removeReleaseMessage(1);
            if (isNeedReportStatus) {
                AndroidAutoConst.LinkStatus linkStatus3 = AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
                this.mLinkStatus = linkStatus3;
                if (this.mAapControllerListener != null) {
                    this.mAapControllerListener.onPhoneManagerStatusChanged(this.mLinkDevice, linkStatus3, error);
                }
            }
        }
        this.mFailedError = AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE;
        if (this.mAapBluetoothManager != null) {
            this.mAapBluetoothManager.onDisconnected();
        }
        this.mConnectingDevice = null;
    }

    protected void release(UsbDevice device) {
        LogUtil.info(TAG, "release is invoke!");
        TransportFactory transportFactory = this.mTransportFactory;
        if (transportFactory != null) {
            transportFactory.shutdown();
            this.mTransportFactory = null;
        }
        AapVideoManager aapVideoManager = this.mAapVideoManager;
        if (aapVideoManager != null) {
            aapVideoManager.onVideoFocusStatusChanged(ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_NATIVE.ordinal());
            this.mAapVideoManager.releaseVideoAndHandler();
            unregisterVideoDataListener();
            unregisterVideoCallback();
            this.mAapVideoManager = null;
        }
        AapClusterVideoManager aapClusterVideoManager = this.mAapClusterVideoManager;
        if (aapClusterVideoManager != null) {
            aapClusterVideoManager.onClusterVideoFocusStatusChanged(ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_NATIVE.ordinal());
            this.mAapClusterVideoManager.unRegisterVideoCallback();
            this.mAapClusterVideoManager.releaseVideoAndHandler();
            this.mAapClusterVideoManager = null;
        }
        NavigationFocusManager navigationFocusManager = this.mNavigationFocusManager;
        if (navigationFocusManager != null) {
            navigationFocusManager.resetNaviStatus();
            this.mNavigationFocusManager.unregisterListener(this.mNavigationFocusListener);
            this.mNavigationFocusManager = null;
        }
        if (this.mInstrumentClusterListener != null) {
            LogUtil.info(TAG, "release clear media state and meta data");
            this.mInstrumentClusterListener.clearMediaStateAndMetaData();
        }
        synchronized (this.mLock) {
            if (this.mGal != null) {
                if (this.mIsStartGal) {
                    this.mGal.stop(Transport.StopReason.CONNECTION_TERMINATED);
                    if (this.mTransport != null) {
                        this.mTransport.stopTransport(Transport.StopReason.CONNECTION_DISCONNECTED);
                    }
                    this.mIsStartGal = false;
                }
                this.mGal.destroy();
                this.mGal = null;
                this.mTransport = null;
            }
        }
        VehicleDataManager vehicleDataManager = this.mSensorDispatcher;
        if (vehicleDataManager != null) {
            vehicleDataManager.releaseSensor();
            this.mSensorDispatcher = null;
        }
        stopCheckFirstUseThread();
        StateManager.getInstance().setVideoShowing(false);
        StateManager.getInstance().setPhoneRunningInPowerOffState(false);
        StateManager.getInstance().setVrRunningInPowerOffState(false);
        StateManager.getInstance().setPhoneStateByRunningVr(false);
        this.mIsStartSimulation = false;
        HandleInputEvent handleInputEvent = this.mHandleInputEvent;
        if (handleInputEvent != null) {
            handleInputEvent.setTouchDebugSwitcher(false);
        }
        AapAudioManager aapAudioManager = this.mAapAudioManager;
        if (aapAudioManager != null) {
            aapAudioManager.releaseAudio();
            this.mAapAudioManager = null;
        }
    }

    public int[] getSupportedChannels() {
        AapControllerListener aapControllerListener = this.mAapControllerListener;
        if (aapControllerListener != null) {
            return aapControllerListener.getSupportedWiFiChannels();
        }
        return null;
    }

    private GalReceiver.CarInfo generateCarInfoForGal() {
        int driverPosition;
        VehicleInfo info = VehicleDataManager.getInstance().getVehicleInfo();
        if (info == null) {
            return null;
        }
        String displayName = info.mDisplayName;
        if (displayName == null) {
            LogUtil.verbose(TAG, "Gal displayName is null,set default value");
            displayName = "NULL";
        }
        int driverPosition2 = Protos.DriverPosition.DRIVER_POSITION_UNKNOWN.getNumber();
        if (info.mVehicleDriverPosition == null) {
            driverPosition = driverPosition2;
        } else {
            int driverPosition3 = info.mVehicleDriverPosition.getNumber();
            driverPosition = driverPosition3;
        }
        GalReceiver.CarInfo carInfo = new GalReceiver.CarInfo(info.getVehicleMaker(), info.getVehicleModel(), info.getVehicleYear(), info.getVehicleId(), driverPosition, info.getHeadUnitMaker(), info.getHeadUnitModel(), info.getHeadUnitSwBuild(), info.getHeadUnitSwVersion(), false, 3, displayName);
        LogUtil.debug(TAG, "Gal driverPosition: " + driverPosition + " HeadUnitSwBuild: " + info.getHeadUnitSwBuild() + " HeadUnitSwVersion: " + info.getHeadUnitSwVersion());
        return carInfo;
    }

    private CarWifiStub generateCarWifiStub() {
        Protos.WifiSecurityMode securityType;
        int[] apChannels = getSupportedChannels();
        int[] apFrequency = changeChannelToFrequency(apChannels);
        if (apFrequency == null || apFrequency.length <= 0) {
            return null;
        }
        WifiConnectInfo wifiConnectInfo = this.mWifiConnectInfo;
        if (wifiConnectInfo != null) {
            CarWifiStub carWifiStub = new CarWifiStub(wifiConnectInfo.getWifiSsid(), this.mWifiConnectInfo.getWifiPassword(), this.mWifiConnectInfo.getWifiSecurityType(), apFrequency, Protos.AccessPointType.STATIC);
            LogUtil.verbose(TAG, "start Gal getWifiSsid:" + LogEncrypt.MacAddressEncrypt(this.mWifiConnectInfo.getWifiSsid()) + " getWifiPassword:" + LogEncrypt.IpAddressEncrypt(this.mWifiConnectInfo.getWifiPassword()));
            return carWifiStub;
        }
        if (this.mDefaultWifiInfo == null) {
            WifiManager wifiMgr = (WifiManager) this.mContext.getSystemService("wifi");
            if (wifiMgr != null) {
                this.mDefaultWifiInfo = wifiMgr.getSoftApConfiguration();
            }
            if (this.mDefaultWifiInfo != null) {
                LogUtil.debug(TAG, "Get default AP info SSID: " + LogEncrypt.MacAddressEncrypt(this.mDefaultWifiInfo.getSsid()) + " Password: " + LogEncrypt.IpAddressEncrypt(this.mDefaultWifiInfo.getPassphrase()));
            } else {
                LogUtil.error(TAG, "Can't get default wifi ap info");
            }
        }
        if (this.mDefaultWifiInfo != null) {
            Protos.WifiSecurityMode wifiSecurityMode = Protos.WifiSecurityMode.WPA2_PERSONAL;
            if (this.mDefaultWifiInfo.getSecurityType() == 1) {
                Protos.WifiSecurityMode securityType2 = Protos.WifiSecurityMode.WPA2_PERSONAL;
                securityType = securityType2;
            } else if (this.mDefaultWifiInfo.getSecurityType() == 2) {
                Protos.WifiSecurityMode securityType3 = Protos.WifiSecurityMode.WPA2_WPA3_PERSONAL;
                securityType = securityType3;
            } else {
                Protos.WifiSecurityMode securityType4 = Protos.WifiSecurityMode.OPEN;
                securityType = securityType4;
            }
            CarWifiStub carWifiStub2 = new CarWifiStub(this.mDefaultWifiInfo.getSsid(), this.mDefaultWifiInfo.getPassphrase(), securityType, apFrequency, Protos.AccessPointType.STATIC);
            return carWifiStub2;
        }
        CarWifiStub carWifiStub3 = new CarWifiStub(apFrequency);
        return carWifiStub3;
    }

    private String generateCarWifiBssid() {
        WifiInfo info;
        int[] apChannels = getSupportedChannels();
        int[] apFrequency = changeChannelToFrequency(apChannels);
        if (apFrequency == null || apFrequency.length <= 0) {
            return null;
        }
        WifiConnectInfo wifiConnectInfo = this.mWifiConnectInfo;
        if (wifiConnectInfo != null) {
            String carWifiBssid = wifiConnectInfo.getHostBssid();
            LogUtil.verbose(TAG, "start Gal getHostBssid:" + LogEncrypt.MacAddressEncrypt(carWifiBssid));
            return carWifiBssid;
        }
        if (this.mDefaultWifiMacAddress == null) {
            WifiManager wifiMgr = (WifiManager) this.mContext.getSystemService("wifi");
            if (wifiMgr != null && (info = wifiMgr.getConnectionInfo()) != null) {
                this.mDefaultWifiMacAddress = info.getMacAddress();
            }
            if (this.mDefaultWifiMacAddress != null) {
                LogUtil.debug(TAG, "Get default wifi mac address: " + LogEncrypt.MacAddressEncrypt(this.mDefaultWifiMacAddress));
                this.mDefaultWifiMacAddress = getDefaultWifiApMac(this.mDefaultWifiMacAddress);
            } else {
                LogUtil.error(TAG, "Can't get default wifi mac address");
            }
        }
        if (this.mDefaultWifiMacAddress != null) {
            return this.mDefaultWifiMacAddress;
        }
        return "00:11:22:aa:bb:cc";
    }

    private void startGal() throws Exception {
        AapClusterVideoManager aapClusterVideoManager;
        LogUtil.verbose(TAG, "start Gal is invoke:" + this.mGal);
        VehicleDataManager vehicleDataManager = this.mSensorDispatcher;
        if (vehicleDataManager != null) {
            vehicleDataManager.registerSensorListener();
        }
        if (this.mGal == null) {
            return;
        }
        VehicleInfo vehicleInfo = VehicleDataManager.getInstance().getVehicleInfo();
        if (vehicleInfo == null) {
            LogUtil.error(TAG, "vehicleInfo is null");
            return;
        }
        boolean isSupportClusterProjection = vehicleInfo.mIsSupportClusterProjection;
        GalReceiver.CarInfo carInfo = generateCarInfoForGal();
        int[] sensors = new int[0];
        VehicleDataManager vehicleDataManager2 = this.mSensorDispatcher;
        if (vehicleDataManager2 != null) {
            List<Integer> supportedSensors = vehicleDataManager2.getSupportedSensors();
            sensors = new int[supportedSensors.size()];
            for (int i = 0; i < supportedSensors.size(); i++) {
                sensors[i] = supportedSensors.get(i).intValue();
            }
        }
        if (this.mAapBluetoothManager != null && this.mAapAudioManager != null && this.mAapVideoManager != null) {
            GalInitParameter galInitParameter = new GalInitParameter();
            galInitParameter.setCarBluetoothAddress(this.mAapBluetoothManager.getCarBluetoothAddress());
            galInitParameter.setSupportedBluetoothPairingMethods(this.mAapBluetoothManager.getSupportedBluetoothPairingMethods());
            galInitParameter.setSupportedSensors(sensors);
            galInitParameter.setProjectionListener(this.mAapVideoManager.getProjectionListener());
            galInitParameter.setAutoStartProjection(false);
            AapClusterVideoManager aapClusterVideoManager2 = this.mAapClusterVideoManager;
            if (aapClusterVideoManager2 != null && isSupportClusterProjection) {
                galInitParameter.setClusterProjectionListener(aapClusterVideoManager2.getProjectionListener());
                galInitParameter.setAutoStartClusterProjection(false);
            }
            galInitParameter.setAudioListeners(this.mAapAudioManager.getAudioListener());
            galInitParameter.setAudioSourceListener(this.mAapAudioManager.getAudioSourceListener());
            galInitParameter.setAppMessageListener(this.mAppMessageListener);
            galInitParameter.setAudioFocusListener(this.mAapAudioManager.getAudioFocusListener());
            galInitParameter.setProjectedModeIconReceiver(this.mProjectedModeIconReceiver);
            galInitParameter.setByeByeHandler(this.mByeByeHandler);
            galInitParameter.setBluetoothEventListener(this.mAapBluetoothManager.bluetoothEventListener);
            galInitParameter.setInstrumentClusterListener(this.mInstrumentClusterListener);
            galInitParameter.setAudioFocusManager(this.mAapAudioManager.getAapAudioFocusManager());
            galInitParameter.setCarInfo(carInfo);
            galInitParameter.setCarWifiBssid(generateCarWifiBssid());
            galInitParameter.setCarWifi(generateCarWifiStub());
            galInitParameter.setHandler(this.mGalReceiverHandler);
            String headUnitModel = vehicleInfo.getHeadUnitModel();
            String vehicleYear = vehicleInfo.getVehicleYear();
            String encryptionPssword = headUnitModel + vehicleYear;
            boolean isNeedDecryptCert = vehicleInfo.mIsNeedDecryptCert;
            String rootCert = vehicleInfo.mRootCert;
            String clientCert = vehicleInfo.mClientCert;
            String privateKey = vehicleInfo.mPrivateKey;
            if (isNeedDecryptCert) {
                try {
                    rootCert = AapAesUtil.decrypt(rootCert, encryptionPssword);
                    clientCert = AapAesUtil.decrypt(clientCert, encryptionPssword);
                    privateKey = AapAesUtil.decrypt(privateKey, encryptionPssword);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (rootCert == null) {
                LogUtil.error(TAG, "rootCert is null");
                return;
            }
            galInitParameter.setRootCert(rootCert);
            if (clientCert == null) {
                LogUtil.error(TAG, "clientCert is null");
                return;
            }
            galInitParameter.setClientCert(clientCert);
            if (privateKey == null) {
                LogUtil.error(TAG, "privateKey is null");
                return;
            }
            galInitParameter.setPrivateKey(privateKey);
            galInitParameter.setEnablePingTimeouts(true);
            galInitParameter.setCurrentFuelType(vehicleInfo.getFuelTypes());
            galInitParameter.setCurrentConnectorTypes(vehicleInfo.getEvConnectorTypes());
            this.mGal.init(galInitParameter, isWireConnect(this.mLinkDevice));
            this.mGal.registerCarService();
            AapVideoManager aapVideoManager = this.mAapVideoManager;
            if (aapVideoManager != null) {
                aapVideoManager.initVideoManager(this.mContext, this.mGal, this.mVideoControllerAdapter);
            }
            AapAudioManager aapAudioManager = this.mAapAudioManager;
            if (aapAudioManager != null) {
                aapAudioManager.initAudioManager(this.mContext, this.mGal, this.mAudioControllerAdapter, isWireConnect(this.mLinkDevice));
            }
            if (isSupportClusterProjection && (aapClusterVideoManager = this.mAapClusterVideoManager) != null) {
                aapClusterVideoManager.initVideoManager(this.mContext, this.mGal, this.mVideoControllerAdapter);
            }
            this.mHandleInputEvent.initInputEvent();
            LogUtil.verbose(TAG, "start Gal is start");
            this.mGal.start(this.mTransport);
            LogUtil.verbose(TAG, "start Gal is end");
            this.mIsStartGal = true;
        }
    }

    @Override // com.google.android.projection.transport.TransportFactory.Callback
    public void onConnected(int factoryId, Transport transport) throws Exception {
        LogUtil.debug(TAG, "Transport connected mLinkDevice:" + this.mLinkDevice);
        LogUtil.debug(TAG, "Transport connected id:" + factoryId + " Curr id:" + mCurrFactoryId);
        if (factoryId == mCurrFactoryId) {
            this.mTransportHandle = null;
            this.mLinkDevice = this.mConnectingDevice;
            this.mTransport = transport;
            this.mIsMdRequestConnect = false;
            parseWifiClinetIpAddr(transport);
            synchronized (this.mStartTcpLock) {
                this.mIsStartTcpServer = false;
            }
            if (this.mLinkDevice.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal()) {
                LogUtil.debug(TAG, "Set service discovery timeout before startGal.");
                sendReleaseEmptyMessageDelayed(2, 15000L);
            }
            startGal();
        }
    }

    @Override // com.google.android.projection.transport.TransportFactory.Callback
    public void onDisconnected(int factoryId) {
        LogUtil.debug(TAG, "Transport onDisconnected id: " + factoryId + " Curr id:" + mCurrFactoryId);
        if (factoryId != mCurrFactoryId) {
            return;
        }
        this.mIsMdRequestConnect = false;
        synchronized (this.mStartTcpLock) {
            this.mIsStartTcpServer = false;
        }
        if (isWireConnect(this.mConnectingDevice)) {
            synchronized (this.mLock) {
                if (this.mTransport != null) {
                    LogUtil.info(TAG, "onDisconnected usb detached mTransport stop");
                    this.mTransport.stopTransport(Transport.StopReason.CONNECTION_DISCONNECTED);
                }
            }
        }
        if (!isWireConnect(this.mConnectingDevice) && this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING) {
            LogUtil.verbose(TAG, "wireless Transport connect timeout");
            AndroidAutoConst.LinkStatus linkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
            this.mLinkStatus = linkStatus;
            AapControllerListener aapControllerListener = this.mAapControllerListener;
            if (aapControllerListener != null) {
                aapControllerListener.onPhoneManagerStatusChanged(this.mConnectingDevice, linkStatus, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED);
            }
        }
    }

    private void parseWifiClinetIpAddr(Transport transport) {
        if (transport != null && (transport instanceof TcpTransport)) {
            TcpTransport tcpTransport = (TcpTransport) transport;
            Socket wifiSocket = tcpTransport.getTcpSocket();
            if (wifiSocket != null) {
                InetAddress inetAddress = wifiSocket.getInetAddress();
                if (inetAddress != null) {
                    String ipAddr = inetAddress.getHostAddress();
                    LogUtil.verbose(TAG, "parseWifiClinetIpAddr" + LogEncrypt.IpAddressEncrypt(ipAddr));
                    AndroidAutoDevice androidAutoDevice = this.mConnectingDevice;
                    if (androidAutoDevice != null && androidAutoDevice.isWireLessLink() && ipAddr != null) {
                        this.mConnectingDevice.setWifiAddr(ipAddr);
                        return;
                    }
                    return;
                }
                LogUtil.verbose(TAG, "parseWifiClinetIpAddr:inetAddress==null");
                return;
            }
            LogUtil.verbose(TAG, "parseWifiClinetIpAddr:wifiSocket==null");
        }
    }

    private boolean isWireConnect(AndroidAutoDevice device) {
        if (device == null) {
            return false;
        }
        boolean isWire = device.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal();
        return isWire;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleVrStopInPoweroff() {
        boolean vrFocus = StateManager.getInstance().getVrFocusState();
        boolean naviFocus = StateManager.getInstance().getNaviFocusState();
        if (!vrFocus && !naviFocus) {
            LogUtil.debug(TAG, "onVoiceSession end in powerOff");
            StateManager.getInstance().setVrRunningInPowerOffState(false);
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.AapController$5, reason: invalid class name */
    class AnonymousClass5 implements GalReceiver.ByeByeHandler {
        AnonymousClass5() {
        }

        @Override // com.google.android.projection.protocol.GalReceiver.ByeByeHandler
        public void onByeByeRequest(int reason) {
            LogUtil.debug(AapController.TAG, "onByeByeRequest start with reason " + reason);
            if (AapController.this.mGal != null && AapController.this.mGal.galReceiver != null) {
                AapController.this.mGal.galReceiver.sendByeByeResponse();
                if (reason == 1) {
                    LogUtil.info(AapController.TAG, "received MD SELECTION ByeByeRequest");
                    AapController.this.mFailedError = AndroidAutoConst.LinkErrorCode.ERROR_USER_SELECT_DISCONNECT;
                } else if (reason == 3 || reason == 4) {
                    if (AapController.this.mAapControllerListener != null) {
                        AapController.this.mAapControllerListener.onPhoneManagerNotSupportAa(AapController.this.mLinkDevice);
                    }
                    AapController.this.mFailedError = AndroidAutoConst.LinkErrorCode.ERROR_NOT_SUPPORTED;
                } else {
                    AapController.this.mFailedError = AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE;
                }
                if (AapController.this.mAapControllerHandler != null) {
                    AapController.this.mAapControllerHandler.postDelayed(new Runnable() { // from class: com.ts.androidauto.aap.-$$Lambda$AapController$5$JDUn9ZNA7Jxn6rvQ3PK7Ut-Y8ZU
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onByeByeRequest$0$AapController$5();
                        }
                    }, 100L);
                } else {
                    AapController.this.mGal.stop(Transport.StopReason.MD_BYEBYE);
                }
            }
            LogUtil.debug(AapController.TAG, "onByeByeRequest end");
        }

        public /* synthetic */ void lambda$onByeByeRequest$0$AapController$5() {
            LogUtil.debug(AapController.TAG, "onByeByeRequest mGal.stop");
            if (AapController.this.mGal != null) {
                AapController.this.mGal.stop(Transport.StopReason.MD_BYEBYE);
            }
        }

        @Override // com.google.android.projection.protocol.GalReceiver.ByeByeHandler
        public void onByeByeResponse() {
            LogUtil.info(AapController.TAG, "received ByeByeResponse");
            if (AapController.this.mWaitByebyeSemaphore != null) {
                AapController.this.mWaitByebyeSemaphore.release();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCheckFirstUseThread() {
        LogUtil.verbose(TAG, "startCheckFirstUseThread is invoke!");
        if (this.mCheckFirstUseThread != null) {
            stopCheckFirstUseThread();
            LogUtil.verbose(TAG, "startCheckFirstUseThread stop Thread");
        }
        CheckFirstUseThread checkFirstUseThread = new CheckFirstUseThread();
        this.mCheckFirstUseThread = checkFirstUseThread;
        checkFirstUseThread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopCheckFirstUseThread() {
        LogUtil.verbose(TAG, "stopCheckFirstUseThread is invoke!");
        CheckFirstUseThread checkFirstUseThread = this.mCheckFirstUseThread;
        if (checkFirstUseThread != null) {
            try {
                checkFirstUseThread.finish();
                this.mCheckFirstUseThread.join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            this.mCheckFirstUseThread = null;
        }
    }

    public class CheckFirstUseThread extends Thread {
        private boolean checkFirstUseFlag = true;
        private int cnt = 0;

        public CheckFirstUseThread() {
        }

        public void finish() {
            this.checkFirstUseFlag = false;
            this.cnt = 0;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            LogUtil.verbose(AapController.TAG, "CheckFirstUseThread run cnt: " + this.cnt);
            boolean isNotifyActivated = false;
            boolean isNotifyFrx = false;
            while (true) {
                if (!this.checkFirstUseFlag) {
                    break;
                }
                if (this.cnt < 30) {
                    if (AapController.this.mAapVideoManager != null && AapController.this.mAapVideoManager.isVideoSetUp() && AapController.this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING) {
                        isNotifyActivated = true;
                        LogUtil.verbose(AapController.TAG, "Check has video data in 3s");
                        AapController.this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED;
                        if (AapController.this.mAapControllerListener != null) {
                            AapController.this.mAapControllerListener.onPhoneManagerStatusChanged(AapController.this.mLinkDevice, AapController.this.mLinkStatus, AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE);
                        }
                        AapController.this.updateWirelessBtAddress();
                        finish();
                    } else {
                        this.cnt++;
                        SystemClock.sleep(100L);
                    }
                } else {
                    if (AapController.this.mAapVideoManager != null && !AapController.this.mAapVideoManager.isVideoSetUp() && AapController.this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING) {
                        isNotifyFrx = true;
                        LogUtil.verbose(AapController.TAG, "Check Video is SetUp show first page.");
                        AapController.this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX;
                        if (AapController.this.mAapControllerListener != null) {
                            AapController.this.mAapControllerListener.onPhoneManagerStatusChanged(AapController.this.mLinkDevice, AapController.this.mLinkStatus, AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE);
                        }
                    }
                    finish();
                }
            }
            if (!isNotifyActivated && !isNotifyFrx) {
                LogUtil.verbose(AapController.TAG, "CheckFirstUseThread isNotifyActivated: " + isNotifyActivated);
                LogUtil.verbose(AapController.TAG, "CheckFirstUseThread isNotifyFrx: " + isNotifyFrx);
                if (AapController.this.mAapVideoManager != null && AapController.this.mAapVideoManager.isVideoSetUp() && AapController.this.mLinkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING) {
                    LogUtil.verbose(AapController.TAG, "Check has video data in 1s backup");
                    AapController.this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED;
                    if (AapController.this.mAapControllerListener != null) {
                        AapController.this.mAapControllerListener.onPhoneManagerStatusChanged(AapController.this.mLinkDevice, AapController.this.mLinkStatus, AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE);
                    }
                    AapController.this.updateWirelessBtAddress();
                }
            }
            LogUtil.verbose(AapController.TAG, "Check video data more than 3s, exit thread.");
        }
    }

    private void registerVideoDataListener() {
        LogUtil.verbose(TAG, "registerVideoDataListener is invoke~");
        AapVideoManager aapVideoManager = this.mAapVideoManager;
        if (aapVideoManager != null) {
            aapVideoManager.registerVideoDataListener(new AapVideoManager.VideoDataListener() { // from class: com.ts.androidauto.aap.AapController.6
                @Override // com.ts.androidauto.aap.video.AapVideoManager.VideoDataListener
                public void receiveVideoData() {
                    LogUtil.verbose(AapController.TAG, "Aap has receiveVideoData");
                }

                @Override // com.ts.androidauto.aap.video.AapVideoManager.VideoDataListener
                public void videoHasSetup() {
                    LogUtil.verbose(AapController.TAG, "videoHasSetup is setup mLinkStatus:" + AapController.this.mLinkStatus);
                    if (AapController.this.getLinkStatus() == AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX) {
                        AapController.this.mLinkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED;
                        if (AapController.this.mAapControllerListener != null) {
                            AapController.this.mAapControllerListener.onPhoneManagerStatusChanged(AapController.this.mLinkDevice, AapController.this.mLinkStatus, AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE);
                        }
                        AapController.this.updateWirelessBtAddress();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterVideoDataListener() {
        LogUtil.verbose(TAG, "unregisterVideoDataListener is invoke~");
        AapVideoManager aapVideoManager = this.mAapVideoManager;
        if (aapVideoManager != null) {
            aapVideoManager.unregisterVideoDataListener();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterVideoCallback() {
        LogUtil.verbose(TAG, "unregisterVideoCallback is invoke~");
        AapVideoManager aapVideoManager = this.mAapVideoManager;
        if (aapVideoManager != null) {
            aapVideoManager.unRegisterVideoCallback();
        }
    }

    public AndroidAutoConst.LinkStatus getLinkStatus() {
        return this.mLinkStatus;
    }

    public boolean isAapConnected() {
        return getLinkStatus() == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED || getLinkStatus() == AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX || this.mIsStartGal;
    }

    public boolean isOtherAapBusy(AndroidAutoDevice device) {
        boolean isOtherDevice = false;
        if (device == null || device != this.mConnectingDevice) {
            isOtherDevice = true;
        }
        AndroidAutoConst.LinkStatus status = getLinkStatus();
        LogUtil.verbose(TAG, "isOtherAapBusy target:" + device);
        LogUtil.verbose(TAG, "isOtherAapBusy connecting:" + this.mConnectingDevice + " status is:" + status);
        boolean isBusy = status == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED || status == AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX || status == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING || status == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING;
        return isBusy && isOtherDevice;
    }

    public boolean isSameAapBusy(AndroidAutoDevice device) {
        AndroidAutoDevice androidAutoDevice;
        if (device != null && (androidAutoDevice = this.mConnectingDevice) != null && device == androidAutoDevice) {
            AndroidAutoConst.LinkStatus status = getLinkStatus();
            return device.getLinkType() == this.mConnectingDevice.getLinkType() ? status == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED || status == AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX || status == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING : status == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED || status == AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX || status == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING || status == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING;
        }
        return false;
    }

    public void showMirrorHmi(AndroidAutoConst.HmiLastMode mode) {
        LogUtil.verbose(TAG, "showMirrorHmi mode is :" + mode);
        if (!isAapConnected()) {
            LogUtil.verbose(TAG, "showMirrorHmi aap do not connected return.");
            return;
        }
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null && galIntegration.input != null) {
            int i = AnonymousClass10.$SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$HmiLastMode[mode.ordinal()];
            if (i == 1) {
                this.mGal.input.sendKeyEvent(Protos.KeyCode.KEYCODE_MEDIA.getNumber(), true);
                this.mGal.input.sendKeyEvent(Protos.KeyCode.KEYCODE_MEDIA.getNumber(), false);
            } else if (i == 2) {
                this.mGal.input.sendKeyEvent(Protos.KeyCode.KEYCODE_TEL.getNumber(), true);
                this.mGal.input.sendKeyEvent(Protos.KeyCode.KEYCODE_TEL.getNumber(), false);
            }
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.AapController$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$HmiLastMode;

        static {
            int[] iArr = new int[AndroidAutoConst.HmiLastMode.values().length];
            $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$HmiLastMode = iArr;
            try {
                iArr[AndroidAutoConst.HmiLastMode.LAST_MODE_MEDIA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$HmiLastMode[AndroidAutoConst.HmiLastMode.LAST_MODE_PHONE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public void sendMediaPlayKey(boolean isDown) {
        if (isAapConnected()) {
            LogUtil.verbose(TAG, "sendMediaPlayKey isDown :" + isDown);
            HandleInputEvent handleInputEvent = this.mHandleInputEvent;
            if (handleInputEvent != null) {
                handleInputEvent.sendPlayKey(isDown);
            }
        }
    }

    public void sendMediaPauseKey(boolean isDown) {
        if (isAapConnected()) {
            LogUtil.verbose(TAG, "sendMediaPauseyKey isDown :" + isDown);
            HandleInputEvent handleInputEvent = this.mHandleInputEvent;
            if (handleInputEvent != null) {
                handleInputEvent.sendPauseKey(isDown);
            }
        }
    }

    public void sendVrKey(boolean isDown) {
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null && galIntegration.input != null && isAapConnected()) {
            LogUtil.verbose(TAG, "sendVrKey isDown :" + isDown);
            this.mGal.input.sendKeyEvent(Protos.KeyCode.KEYCODE_SEARCH.getNumber(), isDown);
        }
    }

    public void sendKeyEvent(int keyCode, int keyAction) {
        int voiceState = StateManager.getInstance().getVoiceSessionState();
        LogUtil.verbose(TAG, "onHardKeyEvent  key:" + keyCode + " keyAction:" + keyAction + " voiceState:" + voiceState);
        if (isAapConnected()) {
            this.mHandleInputEvent.handleKeyEvent(keyCode, keyAction);
        } else {
            LogUtil.info(TAG, "Do not handle hard key if AA is disconnecting");
        }
    }

    public void sendKeyLongPressEvent(int keyCode) {
        int voiceState = StateManager.getInstance().getVoiceSessionState();
        LogUtil.verbose(TAG, "onHardKeyLongPressEvent  key:" + keyCode + " voiceState:" + voiceState);
        if (isAapConnected()) {
            this.mHandleInputEvent.handleKeyLongPressEvent(keyCode);
        } else {
            LogUtil.info(TAG, "Do not handle hard key if AA is disconnecting");
        }
    }

    public void sendVehicleInfo(VehicleInfo vehicleInfo) {
        VehicleDataManager.getInstance().setVehicleInfo(vehicleInfo);
    }

    public void setClusterUiConfig(UiConfig uiConfig) {
        LogUtil.info(TAG, "setClusterUiConfig");
        AapClusterVideoManager.getInstance().setClusterUiConfig(uiConfig, isAapConnected());
    }

    public void next() {
        long now = SystemClock.uptimeMillis();
        if (now < this.mLastNextKeyEventTime + KEY_CALLBACK_DEBOUNCE_DELAY) {
            LogUtil.info(TAG, "Ignore operation due to sendNextKey in 500ms");
            return;
        }
        this.mLastNextKeyEventTime = now;
        LogUtil.info(TAG, "next");
        if (this.mHandleInputEvent != null && isAapConnected()) {
            this.mHandleInputEvent.sendNextKey(true);
            this.mHandleInputEvent.sendNextKey(false);
        }
    }

    public void previous() {
        long now = SystemClock.uptimeMillis();
        if (now < this.mLastPreKeyEventTime + KEY_CALLBACK_DEBOUNCE_DELAY) {
            LogUtil.info(TAG, "Ignore operation due to sendPreviousKey in 500ms");
            return;
        }
        this.mLastPreKeyEventTime = now;
        LogUtil.info(TAG, "previous");
        if (this.mHandleInputEvent != null && isAapConnected()) {
            this.mHandleInputEvent.sendPreviousKey(true);
            this.mHandleInputEvent.sendPreviousKey(false);
        }
    }

    public int getMusicStatus() {
        int musicStatus = AndroidAutoConst.MusicStatus.MUSIC_STATUS_NOT_START.ordinal();
        InstrumentClusterListener instrumentClusterListener = this.mInstrumentClusterListener;
        if (instrumentClusterListener != null) {
            musicStatus = instrumentClusterListener.getAapMediaPlayState();
        }
        LogUtil.info(TAG, "getMusicStatus musicStatus : " + musicStatus);
        return musicStatus;
    }

    public void play() {
        LogUtil.info(TAG, "play");
        int musicStatus = getMusicStatus();
        LogUtil.info(TAG, "play is media start:" + StateManager.getInstance().isMediaStart());
        if (this.mHandleInputEvent != null && isAapConnected()) {
            if (musicStatus != AndroidAutoConst.MusicStatus.MUSIC_STATUS_PLAYING.ordinal() || !StateManager.getInstance().isMediaStart()) {
                this.mHandleInputEvent.sendPlayKey(true);
                this.mHandleInputEvent.sendPlayKey(false);
            }
        }
    }

    public void pause() {
        LogUtil.info(TAG, "pause");
        if (this.mHandleInputEvent != null && isAapConnected()) {
            this.mHandleInputEvent.sendPauseKey(true);
            this.mHandleInputEvent.sendPauseKey(false);
        }
    }

    public MediaMetadata getMediaMetadata() {
        InstrumentClusterListener instrumentClusterListener = this.mInstrumentClusterListener;
        if (instrumentClusterListener != null) {
            return instrumentClusterListener.getMediaMetadata();
        }
        return null;
    }

    public int getMediaProgress() {
        int progress = 0;
        InstrumentClusterListener instrumentClusterListener = this.mInstrumentClusterListener;
        if (instrumentClusterListener != null) {
            progress = instrumentClusterListener.getMediaProgress();
        }
        LogUtil.info(TAG, "getMediaProgress progress : " + progress);
        return progress;
    }

    public void onBluetoothBondStateChanged(int state, BluetoothDevice device) {
        AapBluetoothManager aapBluetoothManager = this.mAapBluetoothManager;
        if (aapBluetoothManager != null) {
            aapBluetoothManager.onBluetoothBondStateChanged(state, device);
        }
    }

    public boolean getFinishBroadcastFlag() {
        LogUtil.info(TAG, "getFinishBroadcastFlag :" + this.mIsSendFinishBroadcast);
        return this.mIsSendFinishBroadcast;
    }

    private void initChannelFrequencyMap() {
        HashMap<Integer, Integer> map = this.mChannelFrequencyMap;
        if (map == null) {
            LogUtil.info(TAG, "initChannelFrequencyMap failed");
            return;
        }
        map.put(1, 2412);
        this.mChannelFrequencyMap.put(2, 2417);
        this.mChannelFrequencyMap.put(3, 2422);
        this.mChannelFrequencyMap.put(4, 2427);
        this.mChannelFrequencyMap.put(5, 2432);
        this.mChannelFrequencyMap.put(6, 2437);
        this.mChannelFrequencyMap.put(7, 2442);
        this.mChannelFrequencyMap.put(7, 2447);
        this.mChannelFrequencyMap.put(9, 2452);
        this.mChannelFrequencyMap.put(10, 2457);
        this.mChannelFrequencyMap.put(11, 2462);
        this.mChannelFrequencyMap.put(12, 2467);
        this.mChannelFrequencyMap.put(13, 2472);
        this.mChannelFrequencyMap.put(14, 2484);
        this.mChannelFrequencyMap.put(36, 5180);
        this.mChannelFrequencyMap.put(40, 5200);
        this.mChannelFrequencyMap.put(44, 5220);
        this.mChannelFrequencyMap.put(48, 5240);
        this.mChannelFrequencyMap.put(52, 5260);
        this.mChannelFrequencyMap.put(56, 5280);
        this.mChannelFrequencyMap.put(60, 5300);
        this.mChannelFrequencyMap.put(64, 5320);
        this.mChannelFrequencyMap.put(100, 5500);
        this.mChannelFrequencyMap.put(Integer.valueOf(Protos.KeyCode.KEYCODE_BUTTON_L2_VALUE), 5520);
        this.mChannelFrequencyMap.put(Integer.valueOf(Protos.KeyCode.KEYCODE_BUTTON_START_VALUE), 5540);
        this.mChannelFrequencyMap.put(Integer.valueOf(Protos.KeyCode.KEYCODE_FORWARD_DEL_VALUE), 5560);
        this.mChannelFrequencyMap.put(Integer.valueOf(Protos.KeyCode.KEYCODE_SCROLL_LOCK_VALUE), 5580);
        this.mChannelFrequencyMap.put(120, 5600);
        this.mChannelFrequencyMap.put(Integer.valueOf(Protos.KeyCode.KEYCODE_INSERT_VALUE), 5620);
        this.mChannelFrequencyMap.put(128, 5640);
        this.mChannelFrequencyMap.put(132, 5660);
        this.mChannelFrequencyMap.put(136, 5680);
        this.mChannelFrequencyMap.put(140, 5700);
        this.mChannelFrequencyMap.put(144, 5720);
        this.mChannelFrequencyMap.put(149, 5745);
        this.mChannelFrequencyMap.put(153, 5765);
        this.mChannelFrequencyMap.put(157, 5785);
        this.mChannelFrequencyMap.put(Integer.valueOf(Protos.KeyCode.KEYCODE_NUMPAD_EQUALS_VALUE), 5805);
        this.mChannelFrequencyMap.put(Integer.valueOf(Protos.KeyCode.KEYCODE_INFO_VALUE), 5825);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] changeChannelToFrequency(int[] channel) {
        LogUtil.info(TAG, "changeChannelToFrequency");
        int[] frequency = null;
        if (channel != null && channel.length > 0) {
            LogUtil.info(TAG, "changeChannelToFrequency channel length:" + channel.length);
            frequency = new int[channel.length];
            for (int count = 0; count < channel.length; count++) {
                HashMap<Integer, Integer> map = this.mChannelFrequencyMap;
                if (map != null && map.containsKey(Integer.valueOf(channel[count]))) {
                    frequency[count] = this.mChannelFrequencyMap.get(Integer.valueOf(channel[count])).intValue();
                    LogUtil.info(TAG, "changeChannelToFrequency frequency[" + count + "] = " + frequency[count] + " Channel[" + count + "] = " + channel[count]);
                }
            }
        }
        return frequency;
    }

    public void initDisplayActivityInfo() {
        LogUtil.info(TAG, "initDisplayActivityInfo");
        if (this.mVideoControllerAdapter != null) {
            VehicleInfo vehicleInfo = VehicleDataManager.getInstance().getVehicleInfo();
            this.mVideoControllerAdapter.setDisplayActivityInfo(vehicleInfo.mDisplayActivityPackageName, vehicleInfo.mDisplayActivityClassName);
        }
    }

    public void registerAapControllerListener(AapControllerListener listener) {
        this.mAapControllerListener = listener;
    }

    public void unregisterAapControllerListener() {
        this.mAapControllerListener = null;
    }

    public void onBtEnabled() {
        AapBluetoothManager aapBluetoothManager = this.mAapBluetoothManager;
        if (aapBluetoothManager != null) {
            aapBluetoothManager.onBtEnabled();
        }
    }

    public void onBtDisabled() {
        AapBluetoothManager aapBluetoothManager = this.mAapBluetoothManager;
        if (aapBluetoothManager != null) {
            aapBluetoothManager.onBtDisabled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestMicFocus() {
        LogUtil.debug(TAG, "voice session request mic focus.");
        boolean hasMicFocus = StateManager.getInstance().getMicFocusState();
        LogUtil.debug(TAG, "requestMicFocus hasMicFocus: " + hasMicFocus);
        if (!hasMicFocus) {
            int result = -1;
            IAudioControllerAdapter iAudioControllerAdapter = this.mAudioControllerAdapter;
            if (iAudioControllerAdapter != null) {
                result = iAudioControllerAdapter.requestMicFocus(ResourceConst.MicFocusRequest.MICFOCUS_REQUEST_ANDROIDAUTO_VR);
                LogUtil.debug(TAG, "requestMicFocus request : " + result);
            }
            if (result == ResourceConst.MicFocusReqResult.MICFOCUS_REQUEST_GRANTED.ordinal()) {
                StateManager.getInstance().setMicFocusState(true);
            } else {
                LogUtil.debug(TAG, "voice session can not get mic focus.");
                StateManager.getInstance().setMicFocusState(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseMicFocus() {
        if (this.mAudioControllerAdapter != null) {
            boolean hasMicFocus = StateManager.getInstance().getMicFocusState();
            LogUtil.debug(TAG, "releaseMicFocus hasMicFocus: " + hasMicFocus);
            if (hasMicFocus) {
                int ret = this.mAudioControllerAdapter.requestMicFocus(ResourceConst.MicFocusRequest.MICFOCUS_RELEASE_ANDROIDAUTO_VR);
                LogUtil.debug(TAG, "releaseMicFocus result: " + ret);
                if (ret == ResourceConst.MicFocusReqResult.MICFOCUS_REQUEST_GRANTED.ordinal()) {
                    StateManager.getInstance().setMicFocusState(false);
                }
            }
        }
    }

    public void sendNativeNaviStatus(boolean useNativeNavigation) {
        LogUtil.info(TAG, "sendNativeNaviStatus useNativeNavigation: " + useNativeNavigation);
        NavigationFocusManager navigationFocusManager = this.mNavigationFocusManager;
        if (navigationFocusManager != null) {
            navigationFocusManager.sendNativeNaviStatus(useNativeNavigation);
        }
    }

    public boolean isNativePhoneInCall() {
        LogUtil.verbose(TAG, "isNativePhoneInCall is invoke nativePhoneCallStatus:" + this.mNativePhoneCallStatus);
        if (this.mNativePhoneCallStatus == NativePhoneConst.NativePhoneCallStatus.ACTIVE.ordinal()) {
            LogUtil.verbose(TAG, "isNativePhoneInCall is true");
            return true;
        }
        LogUtil.verbose(TAG, "isNativePhoneInCall is false");
        return false;
    }

    private boolean isOtherHfpDeviceInCall() {
        LogUtil.info(TAG, "isOtherHfpDeviceInCall is invoke");
        AapBluetoothManager aapBluetoothManager = this.mAapBluetoothManager;
        if (aapBluetoothManager != null) {
            return aapBluetoothManager.isOtherHfpDeviceInCall();
        }
        return false;
    }

    public void updateCallAvailability() {
        LogUtil.verbose(TAG, "updateCallAvailability is invoke");
        if (this.mGal != null) {
            if (isOtherHfpDeviceInCall() || isNativePhoneInCall()) {
                if (this.mIsCallAvailability) {
                    LogUtil.verbose(TAG, "updateCallAvailability false.");
                    this.mGal.setCallAvailability(false);
                    this.mIsCallAvailability = false;
                    return;
                }
                return;
            }
            if (!this.mIsCallAvailability) {
                LogUtil.verbose(TAG, "updateCallAvailability true.");
                this.mGal.setCallAvailability(true);
                this.mIsCallAvailability = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCallAvailabilityForServiceDiscovery() {
        LogUtil.verbose(TAG, "updateCallAvailabilityForServiceDiscovery is invoke");
        if (this.mGal != null) {
            if (isOtherHfpDeviceInCall() || isNativePhoneInCall()) {
                LogUtil.verbose(TAG, "updateCallAvailabilityForServiceDiscovery false.");
                this.mGal.setCallAvailability(false);
            } else {
                LogUtil.verbose(TAG, "updateCallAvailabilityForServiceDiscovery true.");
                this.mGal.setCallAvailability(true);
            }
        }
    }

    public void setOtherProjectionActive(boolean active) {
        this.mIsOtherProjectionActive = active;
    }

    public boolean getOtherProjectionActive() {
        return this.mIsOtherProjectionActive;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWirelessBtAddress() {
        LogUtil.verbose(TAG, "updateWirelessBtAddress is invoke. device=" + this.mLinkDevice);
        AndroidAutoDevice androidAutoDevice = this.mLinkDevice;
        if (androidAutoDevice != null && androidAutoDevice.isWireLessLink() && !TextUtils.isEmpty(this.mLinkDevice.getBtAddr())) {
            this.mAapBluetoothManager.updateWirelessAaAddress(this.mLinkDevice.getBtAddr());
        }
    }

    public int getCurrVideoFocusStatus() {
        AapVideoManager aapVideoManager = this.mAapVideoManager;
        if (aapVideoManager != null) {
            return aapVideoManager.getCurrVideoFocusStatus();
        }
        return ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_NATIVE.ordinal();
    }

    public void onMdAuthFailed() {
        AapControllerListener aapControllerListener = this.mAapControllerListener;
        if (aapControllerListener != null) {
            aapControllerListener.onMdAuthFailed();
        }
    }

    public void appStateChangedTo(ResourceConst.AppType appType, ResourceConst.AppState appState) {
        if (appType == ResourceConst.AppType.REAR_CAMERA || appType == ResourceConst.AppType.NATIVE_ASR || appType == ResourceConst.AppType.NATIVE_SCREEN_OFF) {
            if (appState == ResourceConst.AppState.APP_ON) {
                AapVideoManager.getInstance().setHuVideoFocus(true);
            } else {
                AapVideoManager.getInstance().setHuVideoFocus(false);
            }
        }
    }

    public void onMergeDevice(String devName, String devMac) {
        AapControllerListener aapControllerListener = this.mAapControllerListener;
        if (aapControllerListener != null) {
            aapControllerListener.onMergeDevice(devName, devMac);
        }
    }

    public List<AndroidAutoDevice> getCurrConnectedDevice() {
        AapControllerListener aapControllerListener = this.mAapControllerListener;
        if (aapControllerListener == null) {
            return null;
        }
        List<AndroidAutoDevice> connectedDeviceList = aapControllerListener.getCurrConnectedDevice();
        return connectedDeviceList;
    }
}
