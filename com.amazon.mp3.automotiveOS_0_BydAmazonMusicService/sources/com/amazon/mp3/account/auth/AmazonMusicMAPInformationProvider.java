package com.amazon.mp3.account.auth;

import com.amazon.identity.auth.device.api.MAPInformationProvider;
import com.amazon.mp3.util.DeviceID;

/* JADX INFO: loaded from: classes3.dex */
public class AmazonMusicMAPInformationProvider extends MAPInformationProvider {
    @Override // com.amazon.identity.auth.device.api.MAPInformationProvider
    public String getDsnOverrideForChildDeviceType() {
        return DeviceID.getId(getContext());
    }
}
