package com.ts.androidauto.aap.sink;

import android.view.KeyEvent;
import android.view.MotionEvent;
import com.google.android.projection.proto.Protos;
import com.google.android.projection.protocol.AudioSink;
import com.google.android.projection.protocol.AudioSource;
import com.google.android.projection.protocol.BluetoothEndpoint;
import com.google.android.projection.protocol.CarWifi;
import com.google.android.projection.protocol.GalReceiver;
import com.google.android.projection.protocol.GalVerificationVendorExtension;
import com.google.android.projection.protocol.InputSource;
import com.google.android.projection.protocol.MediaPlaybackStatus;
import com.google.android.projection.protocol.NavigationStatus;
import com.google.android.projection.protocol.PhoneStatus;
import com.google.android.projection.protocol.RadioEndpoint;
import com.google.android.projection.protocol.SensorSource;
import com.google.android.projection.protocol.Transport;
import com.google.android.projection.protocol.VideoSink;
import com.google.android.projection.protocol.WifiProjectionEndpoint;
import com.ts.androidauto.aap.common.VehicleInfo;
import com.ts.androidauto.aap.vehicledata.VehicleDataManager;
import com.ts.androidauto.fsdk.common.LogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class GalIntegration {
    private static final int AUDIO_SERVICE_MEDIA_AAC_ID = 19;
    private static final int AUDIO_SERVICE_MEDIA_ID = 4;
    private static final int AUDIO_SERVICE_MEDIA_PCM_ID = 17;
    private static final int AUDIO_SERVICE_SYSTEM_ID = 6;
    private static final int AUDIO_SERVICE_TTS_AAC_ID = 20;
    private static final int AUDIO_SERVICE_TTS_ID = 5;
    private static final int AUDIO_SERVICE_TTS_PCM_ID = 18;
    public static final int AUDIO_SINK_INDEX_MEDIA = 0;
    public static final int AUDIO_SINK_INDEX_MEDIA_AAC = 2;
    public static final int AUDIO_SINK_INDEX_MEDIA_PCM = 0;
    public static final int AUDIO_SINK_INDEX_TTS = 1;
    public static final int AUDIO_SINK_INDEX_TTS_AAC = 3;
    public static final int AUDIO_SINK_INDEX_TTS_PCM = 1;
    private static final int AUDIO_SOURCE_SERVICE_ID = 7;
    private static final int BLUETOOTH_SERVICE_ID = 8;
    private static final int CLUSTER_VIEWING_DISTANCE = 738;
    private static final int COLOUR_DEPTH_BITS = 16;
    private static final int DISPLAY_CLUSTER_INPUT_SERVICE_ID = 22;
    private static final int DISPLAY_CLUSTER_SERVICE_ID = 21;
    private static final int DISPLAY_SERVICE_ID = 1;
    private static final int ECHO_VENDOR_EXTENSION_SERVICE_ID = 14;
    private static final int GAL_VERIFICATION_VENDOR_EXTENSION_SERVICE_ID = 13;
    private static final int INPUT_SERVICE_ID = 2;
    private static final int MEDIA_BROWSE_SERVICE_ID = 11;
    private static final int MEDIA_PLAYBACK_STATUS_SERVICE_ID = 10;
    private static final int MIN_INTERVAL_MS = 500;
    private static final int NAVIGATION_STATUS_SERVICE_ID = 9;
    private static final int NAVI_STATUS_HEIGHT = 0;
    private static final int NAVI_STATUS_WIDTH = 0;
    public static final int NUM_AUDIO_SINKS = 4;
    private static final int PHONE_STATUS_SERVICE_ID = 12;
    private static final int RADIO_SERVICE_ID = 15;
    private static final int SENSOR_SERVICE_ID = 3;
    private static final String TAG = "AAP" + GalIntegration.class.getSimpleName();
    public static final String USB_DISCONNECT_INTENT = "usb-disconnect";
    private static final int VIEWING_DISTANCE = 950;
    private static final int WIFI_PROJECTION_SERVICE_ID = 16;
    private static GalIntegration mGal;
    public AudioSink[] audioSinks;
    public AudioSource audioSource;
    public BluetoothEndpoint bluetooth;
    public VideoSink display;
    public VideoSink displayCluster;
    public GalReceiver galReceiver;
    public GalVerificationVendorExtension galVerificationVendorExtension;
    public InputSource input;
    public InputSource inputCluster;
    private CarWifi mCarWifi;
    private String mCarWifiBssid;
    public MediaPlaybackStatus mediaPlaybackStatus;
    public NavigationStatus navigationStatus;
    public PhoneStatus phoneStatus;
    public RadioEndpoint radioEndpoint;
    public SensorSource sensors;
    public WifiProjectionEndpoint wifiProjEndpoint;
    private int mViewingDistance = VIEWING_DISTANCE;
    private int mClusterViewingDistance = CLUSTER_VIEWING_DISTANCE;
    private boolean mIsWireConnect = true;

    public static synchronized GalIntegration getInstance() {
        if (mGal == null) {
            mGal = new GalIntegration();
        }
        return mGal;
    }

    public boolean init(GalInitParameter galInitParameter, boolean isWireConnect) {
        this.mIsWireConnect = isWireConnect;
        this.galReceiver = new GalReceiver(galInitParameter.getAppMessageListener(), galInitParameter.getAudioFocusListener(), galInitParameter.getProjectedModeIconReceiver(), galInitParameter.getByeByeHandler(), galInitParameter.getHandler(), galInitParameter.getCarInfo(), galInitParameter.getRootCert(), galInitParameter.getClientCert(), galInitParameter.getPrivateKey());
        this.input = new InputSource(new InputSource.InputInjector() { // from class: com.ts.androidauto.aap.sink.GalIntegration.1
            @Override // com.google.android.projection.protocol.InputSource.InputInjector
            public void onMotionEvent(MotionEvent event) {
            }

            @Override // com.google.android.projection.protocol.InputSource.InputInjector
            public void onKeyEvent(KeyEvent event) {
            }

            @Override // com.google.android.projection.protocol.InputSource.InputInjector
            public void onRelativeEvent(int keyCode, int delta) {
                GalIntegration.this.input.sendRelativeEvent(keyCode, delta);
            }
        });
        this.inputCluster = new InputSource(new InputSource.InputInjector() { // from class: com.ts.androidauto.aap.sink.GalIntegration.2
            @Override // com.google.android.projection.protocol.InputSource.InputInjector
            public void onMotionEvent(MotionEvent event) {
            }

            @Override // com.google.android.projection.protocol.InputSource.InputInjector
            public void onKeyEvent(KeyEvent event) {
            }

            @Override // com.google.android.projection.protocol.InputSource.InputInjector
            public void onRelativeEvent(int keyCode, int delta) {
                GalIntegration.this.inputCluster.sendRelativeEvent(keyCode, delta);
            }
        });
        this.sensors = new SensorSource(galInitParameter.getSupportedSensors(), 128, galInitParameter.getSupportedFuelTypes(), galInitParameter.getSupporteConnectorTypes());
        VehicleInfo info = VehicleDataManager.getInstance().getVehicleInfo();
        if (info != null) {
            this.mViewingDistance = info.mViewingDistance;
            this.mClusterViewingDistance = info.mClusterViewingDistance;
        }
        this.display = new VideoSink(galInitParameter.getProjectionListener(), galInitParameter.isAutoStartProjection(), this.mViewingDistance);
        this.displayCluster = new VideoSink(galInitParameter.getClusterProjectionListener(), galInitParameter.isAutoStartClusterProjection(), this.mClusterViewingDistance);
        AudioSink.AudioListener[] audioListeners = galInitParameter.getAudioListeners();
        this.audioSinks = new AudioSink[4];
        LogUtil.info(TAG, "audioSinks size:" + this.audioSinks.length);
        int i = 0;
        while (true) {
            AudioSink[] audioSinkArr = this.audioSinks;
            if (i < audioSinkArr.length) {
                audioSinkArr[i] = new AudioSink(audioListeners[i]);
                LogUtil.info(TAG, "audioSinks audioSinks[i]:" + this.audioSinks[i]);
                i++;
            } else {
                this.audioSource = new AudioSource(Protos.MediaCodecType.MEDIA_CODEC_AUDIO_PCM, galInitParameter.getAudioSourceListener());
                this.bluetooth = new BluetoothEndpoint(galInitParameter.getBluetoothEventListener(), galInitParameter.getCarBluetoothAddress(), galInitParameter.getSupportedBluetoothPairingMethods());
                this.navigationStatus = new NavigationStatus(galInitParameter.getInstrumentClusterListener(), 500, Protos.NavigationStatusService.InstrumentClusterType.ENUM, 0, 0, 16);
                this.mediaPlaybackStatus = new MediaPlaybackStatus(galInitParameter.getInstrumentClusterListener());
                this.phoneStatus = new PhoneStatus(galInitParameter.getInstrumentClusterListener());
                this.mCarWifi = galInitParameter.getCarWifi();
                this.mCarWifiBssid = galInitParameter.getCarWifiBssid();
                return true;
            }
        }
    }

    public void registerCarService() {
        GalReceiver galReceiver = this.galReceiver;
        if (galReceiver != null) {
            galReceiver.registerCarService(2, this.input);
            this.galReceiver.registerCarService(3, this.sensors);
            this.galReceiver.registerCarService(1, this.display);
            this.galReceiver.registerCarService(8, this.bluetooth);
            LogUtil.info(TAG, "registerCarService mIsWireConnect:" + this.mIsWireConnect);
            this.galReceiver.registerCarService(17, this.audioSinks[0]);
            this.galReceiver.registerCarService(18, this.audioSinks[1]);
            this.galReceiver.registerCarService(19, this.audioSinks[2]);
            this.galReceiver.registerCarService(20, this.audioSinks[3]);
            this.galReceiver.registerCarService(7, this.audioSource);
            VehicleInfo info = VehicleDataManager.getInstance().getVehicleInfo();
            if (info.mIsSupportDisplayNaviDataUpdate) {
                this.galReceiver.registerCarService(9, this.navigationStatus);
            }
            if (info.mIsSupportClusterProjection) {
                this.galReceiver.registerCarService(21, this.displayCluster);
                this.galReceiver.registerCarService(22, this.inputCluster);
            }
            this.galReceiver.registerCarService(10, this.mediaPlaybackStatus);
            this.galReceiver.registerCarService(12, this.phoneStatus);
            String str = this.mCarWifiBssid;
            if (str != null) {
                WifiProjectionEndpoint wifiProjectionEndpoint = new WifiProjectionEndpoint(str, this.mCarWifi);
                this.wifiProjEndpoint = wifiProjectionEndpoint;
                this.galReceiver.registerCarService(16, wifiProjectionEndpoint);
            }
        }
    }

    public void start(Transport transport) {
        GalReceiver galReceiver = this.galReceiver;
        if (galReceiver != null) {
            galReceiver.start(transport);
        }
    }

    public void stop(Transport.StopReason reason) {
        GalReceiver galReceiver = this.galReceiver;
        if (galReceiver != null) {
            galReceiver.stop(reason);
        }
    }

    public void destroy() {
        if (this.galReceiver != null) {
            LogUtil.info(TAG, "GalReceiver is destory, reset  galReceiver");
            this.galReceiver.destroy();
            this.galReceiver = null;
        }
    }

    public void setCallAvailability(boolean callAvailable) {
        GalReceiver galReceiver = this.galReceiver;
        if (galReceiver != null) {
            galReceiver.setCallAvailability(callAvailable);
        }
    }
}
