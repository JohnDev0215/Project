package com.amazon.device.ads;

import com.amazon.device.ads.GooglePlayServices;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
class GooglePlayServicesAdapter {
    private static final String LOGTAG = "GooglePlayServicesAdapter";
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    GooglePlayServicesAdapter() {
    }

    public GooglePlayServices.AdvertisingInfo getAdvertisingIdentifierInfo() {
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(MobileAdsInfoStore.getInstance().getApplicationContext());
            this.logger.v("The Google Play Services Advertising Identifier was successfully retrieved.");
            if (advertisingIdInfo != null) {
                return new GooglePlayServices.AdvertisingInfo().setAdvertisingIdentifier(advertisingIdInfo.getId()).setLimitAdTrackingEnabled(advertisingIdInfo.isLimitAdTrackingEnabled());
            }
            return new GooglePlayServices.AdvertisingInfo();
        } catch (GooglePlayServicesNotAvailableException unused) {
            this.logger.v("Retrieving the Google Play Services Advertising Identifier caused a GooglePlayServicesNotAvailableException.");
            return GooglePlayServices.AdvertisingInfo.createNotAvailable();
        } catch (GooglePlayServicesRepairableException unused2) {
            this.logger.v("Retrieving the Google Play Services Advertising Identifier caused a GooglePlayServicesRepairableException.");
            return new GooglePlayServices.AdvertisingInfo();
        } catch (IOException unused3) {
            this.logger.e("Retrieving the Google Play Services Advertising Identifier caused an IOException.");
            return new GooglePlayServices.AdvertisingInfo();
        } catch (IllegalStateException e) {
            this.logger.e("The Google Play Services Advertising Identifier could not be retrieved: %s", e.getMessage());
            return new GooglePlayServices.AdvertisingInfo();
        } catch (Exception e2) {
            this.logger.v("Run time exception occured while retrieving Advertising Identifier:  %s", e2.getMessage());
            return new GooglePlayServices.AdvertisingInfo();
        }
    }
}
