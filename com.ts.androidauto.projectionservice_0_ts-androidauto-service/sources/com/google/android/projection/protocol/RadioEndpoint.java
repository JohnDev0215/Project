package com.google.android.projection.protocol;

import com.google.android.projection.protocol.Radio;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RadioEndpoint implements CarServiceProvider, RadioCallbacks, Radio.RadioEndpoint {
    private static final String TAG = "GAL";
    private long mNativeRadioEndpoint;
    private Radio mRadio;

    private native int nativeInit(int i, long j) throws IllegalStateException;

    private native int nativeSendActiveRadioNotification(int i, int i2, Radio.RadioStationInfo radioStationInfo);

    private native int nativeSendCancelRadioOperationsResponse(int i, int i2);

    private native int nativeSendConfigureChannelSpacingResponse(int i, int i2, int i3);

    private native int nativeSendGetProgramListResponse(int i, int i2, boolean z, Radio.RadioStationInfo[] radioStationInfoArr);

    private native int nativeSendGetStationPresetsResponse(Radio.StationPresetList[] stationPresetListArr);

    private native int nativeSendGetTrafficUpdateResponse(int i, int i2, Radio.TrafficIncident[] trafficIncidentArr);

    private native int nativeSendMuteRadioResponse(int i, int i2, boolean z);

    private native int nativeSendRadioSourceResponse(int i, boolean z);

    private native int nativeSendRadioStationInfoNotification(int i, Radio.RadioStationInfo radioStationInfo);

    private native int nativeSendScanStationsResponse(int i, int i2, boolean z);

    private native int nativeSendSeekStationResponse(int i, int i2);

    private native int nativeSendStepChannelResponse(int i, int i2);

    private native int nativeSendTuneToStationResponse(int i, int i2);

    private native int nativeSetRadioConfiguration(Radio.RadioProperties[] radioPropertiesArr);

    private native void nativeShutdown();

    public RadioEndpoint(Radio radio) {
        this.mRadio = radio;
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int serviceId, long nativeGalReceiver) {
        if (nativeInit(serviceId, nativeGalReceiver) != 0) {
            return false;
        }
        nativeSetRadioConfiguration((Radio.RadioProperties[]) this.mRadio.getRadioProperties().toArray(new Radio.RadioProperties[0]));
        return true;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeRadioEndpoint;
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public Radio.RadioState onGetCurrentRadioState() {
        return this.mRadio.getCurrentRadioState();
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public int onTuneToStationRequest(int radioId, int channel, int subChannel) {
        return this.mRadio.tune(radioId, channel, subChannel);
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public int onStepChannelRequest(int radioId, boolean up, boolean skipSubChannel) {
        return this.mRadio.step(radioId, up, skipSubChannel);
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public int onSeekStationRequest(int radioId, boolean up, boolean skipSubChannel) {
        return this.mRadio.seek(radioId, up, skipSubChannel);
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public int onScanStationsRequest(int radioId, boolean start, boolean up, boolean skipSubChannel) {
        return this.mRadio.scan(radioId, start, up, skipSubChannel);
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public int onCancelRadioOperationsRequest(int radioId) {
        return this.mRadio.cancel(radioId);
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public int onConfigureChannelSpacingRequest(int radioId, int channelSpacing) {
        return this.mRadio.configureChannelSpacing(radioId, channelSpacing);
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public int onMuteRadioRequest(int radioId, boolean mute) {
        return this.mRadio.mute(radioId, mute);
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public int onRadioSourceRequest() {
        return this.mRadio.enableRadioSource();
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public int onSelectActiveRadioRequest(int radioId) {
        return this.mRadio.selectRadio(radioId);
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public int onGetTrafficUpdateRequest(int radioId) {
        return this.mRadio.getTrafficUpdate(radioId);
    }

    @Override // com.google.android.projection.protocol.RadioCallbacks
    public int onGetProgramListRequest(int radioId) {
        return this.mRadio.getProgramList(radioId);
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendStepResponse(int status, int radioId) {
        return nativeSendStepChannelResponse(0, radioId);
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendTuneResponse(int status, int radioId) {
        return nativeSendTuneToStationResponse(status, radioId);
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendSeekResponse(int status, int radioId) {
        return nativeSendSeekStationResponse(status, radioId);
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendScanResponse(int status, int radioId, boolean started) {
        return nativeSendScanStationsResponse(status, radioId, started);
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendRadioStationInfoNotification(int radioId, Radio.RadioStationInfo stationInfo) {
        return nativeSendRadioStationInfoNotification(radioId, stationInfo);
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendCancelResponse(int status, int radioId) {
        return nativeSendCancelRadioOperationsResponse(status, radioId);
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendMuteResponse(int status, int radioId, boolean muted) {
        return nativeSendMuteRadioResponse(status, radioId, muted);
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendRadioSourceResponse(int status, boolean radioSourceEnabled) {
        return nativeSendRadioSourceResponse(status, radioSourceEnabled);
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendActiveRadioResponse(int status, int radioId, Radio.RadioStationInfo stationInfo) {
        return nativeSendActiveRadioNotification(status, radioId, stationInfo);
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendProgramListResponse(int status, int radioId, boolean done, List<Radio.RadioStationInfo> programList) {
        return nativeSendGetProgramListResponse(status, radioId, done, (Radio.RadioStationInfo[]) programList.toArray(new Radio.RadioStationInfo[0]));
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendTrafficUpdateResponse(int status, int radioId, List<Radio.TrafficIncident> trafficUpdate) {
        return nativeSendGetTrafficUpdateResponse(status, radioId, (Radio.TrafficIncident[]) trafficUpdate.toArray(new Radio.TrafficIncident[0]));
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendChannelSpacingResponse(int status, int radioId, int channelSpacing) {
        return nativeSendConfigureChannelSpacingResponse(status, radioId, channelSpacing);
    }

    @Override // com.google.android.projection.protocol.Radio.RadioEndpoint
    public int sendStationPresetsResponse(List<Radio.StationPresetList> stationPresetLists) {
        return nativeSendGetStationPresetsResponse((Radio.StationPresetList[]) stationPresetLists.toArray(new Radio.StationPresetList[0]));
    }
}
