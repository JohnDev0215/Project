package com.ts.androidauto.aap.sink;

import android.os.Handler;
import com.google.android.projection.proto.Protos;
import com.google.android.projection.protocol.AudioSink;
import com.google.android.projection.protocol.AudioSource;
import com.google.android.projection.protocol.BluetoothEndpoint;
import com.google.android.projection.protocol.CarWifi;
import com.google.android.projection.protocol.GalReceiver;
import com.google.android.projection.protocol.Radio;
import com.google.android.projection.protocol.VideoSink;
import com.ts.androidauto.aap.audiofocus.AapAudioFocusManager;

/* JADX INFO: loaded from: classes2.dex */
public class GalInitParameter {
    private GalReceiver.AppMessageListener appMessageListener;
    private GalReceiver.AudioFocusListener audioFocusListener;
    private AapAudioFocusManager audioFocusManager;
    private AudioSink.AudioListener[] audioListeners;
    private AudioSource.AudioSourceListener audioSourceListener;
    private boolean autoStartClusterProjection;
    private boolean autoStartProjection;
    private BluetoothEndpoint.BluetoothEventListener bluetoothEventListener;
    private GalReceiver.ByeByeHandler byeByeHandler;
    private String carBluetoothAddress;
    private GalReceiver.CarInfo carInfo;
    private CarWifi carWifi;
    private String carWifiBssid;
    private String clientCert;
    private VideoSink.ProjectionListener clusterProjectionListener;
    private boolean enablePingTimeouts;
    private Handler handler;
    private InstrumentClusterListener instrumentClusterListener;
    private String privateKey;
    private GalReceiver.ProjectedModeIconReceiver projectedModeIconReceiver;
    private VideoSink.ProjectionListener projectionListener;
    private Radio radio;
    private String rootCert;
    private int[] supportedBluetoothPairingMethods;
    private int[] supportedSensors;
    private Protos.FuelType[] fuelTypes = {Protos.FuelType.FUEL_TYPE_OTHER};
    private Protos.EvConnectorType[] connectorTypes = new Protos.EvConnectorType[0];

    public String getCarBluetoothAddress() {
        return this.carBluetoothAddress;
    }

    public void setCarBluetoothAddress(String carBluetoothAddress) {
        this.carBluetoothAddress = carBluetoothAddress;
    }

    public int[] getSupportedBluetoothPairingMethods() {
        return this.supportedBluetoothPairingMethods;
    }

    public void setSupportedBluetoothPairingMethods(int[] supportedBluetoothPairingMethods) {
        this.supportedBluetoothPairingMethods = supportedBluetoothPairingMethods;
    }

    public int[] getSupportedSensors() {
        return this.supportedSensors;
    }

    public void setSupportedSensors(int[] supportedSensors) {
        this.supportedSensors = supportedSensors;
    }

    public VideoSink.ProjectionListener getProjectionListener() {
        return this.projectionListener;
    }

    public void setProjectionListener(VideoSink.ProjectionListener projectionListener) {
        this.projectionListener = projectionListener;
    }

    public boolean isAutoStartProjection() {
        return this.autoStartProjection;
    }

    public void setAutoStartProjection(boolean autoStartProjection) {
        this.autoStartProjection = autoStartProjection;
    }

    public VideoSink.ProjectionListener getClusterProjectionListener() {
        return this.clusterProjectionListener;
    }

    public void setClusterProjectionListener(VideoSink.ProjectionListener clusterProjectionListener) {
        this.clusterProjectionListener = clusterProjectionListener;
    }

    public boolean isAutoStartClusterProjection() {
        return this.autoStartClusterProjection;
    }

    public void setAutoStartClusterProjection(boolean autoStartClusterProjection) {
        this.autoStartClusterProjection = autoStartClusterProjection;
    }

    public AudioSink.AudioListener[] getAudioListeners() {
        return this.audioListeners;
    }

    public void setAudioListeners(AudioSink.AudioListener[] audioListeners) {
        this.audioListeners = audioListeners;
    }

    public AudioSource.AudioSourceListener getAudioSourceListener() {
        return this.audioSourceListener;
    }

    public void setAudioSourceListener(AudioSource.AudioSourceListener audioSourceListener) {
        this.audioSourceListener = audioSourceListener;
    }

    public GalReceiver.AppMessageListener getAppMessageListener() {
        return this.appMessageListener;
    }

    public void setAppMessageListener(GalReceiver.AppMessageListener appMessageListener) {
        this.appMessageListener = appMessageListener;
    }

    public GalReceiver.AudioFocusListener getAudioFocusListener() {
        return this.audioFocusListener;
    }

    public void setAudioFocusListener(GalReceiver.AudioFocusListener audioFocusListener) {
        this.audioFocusListener = audioFocusListener;
    }

    public GalReceiver.ProjectedModeIconReceiver getProjectedModeIconReceiver() {
        return this.projectedModeIconReceiver;
    }

    public void setProjectedModeIconReceiver(GalReceiver.ProjectedModeIconReceiver projectedModeIconReceiver) {
        this.projectedModeIconReceiver = projectedModeIconReceiver;
    }

    public GalReceiver.ByeByeHandler getByeByeHandler() {
        return this.byeByeHandler;
    }

    public void setByeByeHandler(GalReceiver.ByeByeHandler byeByeHandler) {
        this.byeByeHandler = byeByeHandler;
    }

    public BluetoothEndpoint.BluetoothEventListener getBluetoothEventListener() {
        return this.bluetoothEventListener;
    }

    public void setBluetoothEventListener(BluetoothEndpoint.BluetoothEventListener bluetoothEventListener) {
        this.bluetoothEventListener = bluetoothEventListener;
    }

    public InstrumentClusterListener getInstrumentClusterListener() {
        return this.instrumentClusterListener;
    }

    public void setInstrumentClusterListener(InstrumentClusterListener instrumentClusterListener) {
        this.instrumentClusterListener = instrumentClusterListener;
    }

    public AapAudioFocusManager getAudioFocusManager() {
        return this.audioFocusManager;
    }

    public void setAudioFocusManager(AapAudioFocusManager audioFocusManager) {
        this.audioFocusManager = audioFocusManager;
    }

    public GalReceiver.CarInfo getCarInfo() {
        return this.carInfo;
    }

    public void setCarInfo(GalReceiver.CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    public Radio getRadio() {
        return this.radio;
    }

    public void setRadio(Radio radio) {
        this.radio = radio;
    }

    public CarWifi getCarWifi() {
        return this.carWifi;
    }

    public void setCarWifi(CarWifi carWifi) {
        this.carWifi = carWifi;
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public String getRootCert() {
        return this.rootCert;
    }

    public void setRootCert(String rootCert) {
        this.rootCert = rootCert;
    }

    public String getClientCert() {
        return this.clientCert;
    }

    public void setClientCert(String clientCert) {
        this.clientCert = clientCert;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getCarWifiBssid() {
        return this.carWifiBssid;
    }

    public void setCarWifiBssid(String carWifiBssid) {
        this.carWifiBssid = carWifiBssid;
    }

    public boolean isEnablePingTimeouts() {
        return this.enablePingTimeouts;
    }

    public void setEnablePingTimeouts(boolean enablePingTimeouts) {
        this.enablePingTimeouts = enablePingTimeouts;
    }

    public void setCurrentFuelType(Protos.FuelType[] fuels) {
        if (fuels != null) {
            this.fuelTypes = fuels;
        } else {
            this.fuelTypes = new Protos.FuelType[]{Protos.FuelType.FUEL_TYPE_OTHER};
        }
    }

    public Protos.FuelType[] getSupportedFuelTypes() {
        return this.fuelTypes;
    }

    public void setCurrentConnectorTypes(Protos.EvConnectorType[] connectors) {
        if (connectors != null) {
            this.connectorTypes = connectors;
        } else {
            this.connectorTypes = new Protos.EvConnectorType[0];
        }
    }

    public Protos.EvConnectorType[] getSupporteConnectorTypes() {
        return this.connectorTypes;
    }
}
