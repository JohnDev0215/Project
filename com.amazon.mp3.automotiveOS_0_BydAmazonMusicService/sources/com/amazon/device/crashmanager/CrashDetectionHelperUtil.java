package com.amazon.device.crashmanager;

import android.content.Context;
import com.amazon.device.crashmanager.utils.AmazonPackageLookup;
import com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil;
import com.amazon.device.utils.det.NetworkManager;

/* JADX INFO: loaded from: classes2.dex */
public class CrashDetectionHelperUtil {
    private static final String SHARED_PREF_NAME = "CrashDetectionHelper.crashManager";

    static CrashDetailsAggregator getCrashDetailsAggregator(Context context) {
        CrashDetailsAggregator crashDetailsAggregator = new CrashDetailsAggregator();
        crashDetailsAggregator.appendCollector(new CrashDetailsCollector(context));
        return crashDetailsAggregator;
    }

    static AppFileArtifactSource getAppFileArtifactSource(Context context, CrashDetailsAggregator crashDetailsAggregator) {
        return new AppFileArtifactSource(context, crashDetailsAggregator);
    }

    static NetworkManager getNetworkManager(Context context) {
        return NetworkManager.instance(context);
    }

    static AmazonPackageLookup getAmazonPackageLookup(Context context) {
        return new AmazonPackageLookup(context);
    }

    static CrashDescriptorDedupeUtil getCrashDescriptorDedupeUtil(Context context) {
        return new CrashDescriptorDedupeUtil(context.getSharedPreferences(SHARED_PREF_NAME, 0));
    }

    static DomainChooser getDomainChooser(Context context) {
        return new ThirdPartyDomainChooser(context.getApplicationInfo());
    }
}
