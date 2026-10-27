package com.amazon.mp3.inappmessaging;

import android.content.Context;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.profile.DeviceProfileProvider;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.external.DeviceInformationVendor;

/* JADX INFO: loaded from: classes3.dex */
public class IAMDeviceInformationVendorImpl implements DeviceInformationVendor {
    private final Context mAppContext;

    public IAMDeviceInformationVendorImpl(final Context appContext) {
        this.mAppContext = appContext.getApplicationContext();
    }

    @Override // com.amazon.music.inappmessaging.external.DeviceInformationVendor
    public DeviceInformation getDeviceInformation() {
        DeviceProfileProvider deviceProfileProvider = new DeviceProfileProvider(this.mAppContext);
        return new DeviceInformation(deviceProfileProvider.getDeviceModel(), deviceProfileProvider.getDeviceManufacturer(), deviceProfileProvider.getCarrierName(), new BrowseLanguageProvider().getLocale(this.mAppContext).toString());
    }
}
