package com.google.android.projection.protocol;

import com.google.android.projection.protocol.Radio;

/* JADX INFO: loaded from: classes.dex */
interface RadioCallbacks extends NativeCallback {
    int onCancelRadioOperationsRequest(int i);

    int onConfigureChannelSpacingRequest(int i, int i2);

    Radio.RadioState onGetCurrentRadioState();

    int onGetProgramListRequest(int i);

    int onGetTrafficUpdateRequest(int i);

    int onMuteRadioRequest(int i, boolean z);

    int onRadioSourceRequest();

    int onScanStationsRequest(int i, boolean z, boolean z2, boolean z3);

    int onSeekStationRequest(int i, boolean z, boolean z2);

    int onSelectActiveRadioRequest(int i);

    int onStepChannelRequest(int i, boolean z, boolean z2);

    int onTuneToStationRequest(int i, int i2, int i3);
}
