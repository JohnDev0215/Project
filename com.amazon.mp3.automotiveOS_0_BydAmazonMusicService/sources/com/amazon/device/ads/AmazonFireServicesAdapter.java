package com.amazon.device.ads;

import android.content.ContentResolver;
import android.provider.Settings;
import com.amazon.device.ads.GooglePlayServices;

/* JADX INFO: loaded from: classes2.dex */
public class AmazonFireServicesAdapter {
    private static final String LOGTAG = "AmazonFireServicesAdapter";
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    public static AmazonFireServicesAdapter newAdapter() {
        return new AmazonFireServicesAdapter();
    }

    private AmazonFireServicesAdapter() {
    }

    public GooglePlayServices.AdvertisingInfo getAdvertisingIdentifierInfo() {
        try {
            ContentResolver contentResolver = MobileAdsInfoStore.getInstance().getApplicationContext().getContentResolver();
            int i = Settings.Secure.getInt(contentResolver, "limit_ad_tracking");
            String string = Settings.Secure.getString(contentResolver, "advertising_id");
            boolean z = true;
            this.logger.v("Fire Id retrieved : %s", string);
            if (i != 0) {
                this.logger.v("Fire Device does not allow ad tracking : %s", string);
            } else {
                z = false;
            }
            GooglePlayServices.AdvertisingInfo advertisingInfo = new GooglePlayServices.AdvertisingInfo();
            advertisingInfo.setAdvertisingIdentifier(string);
            advertisingInfo.setLimitAdTrackingEnabled(z);
            return advertisingInfo;
        } catch (Settings.SettingNotFoundException e) {
            this.logger.v(" Advertising setting not found on this device : %s" + e.getLocalizedMessage());
            return new GooglePlayServices.AdvertisingInfo();
        } catch (Exception e2) {
            this.logger.v(" Attempt to retrieve fireID failed. Reason : %s " + e2.getLocalizedMessage());
            return new GooglePlayServices.AdvertisingInfo();
        }
    }
}
