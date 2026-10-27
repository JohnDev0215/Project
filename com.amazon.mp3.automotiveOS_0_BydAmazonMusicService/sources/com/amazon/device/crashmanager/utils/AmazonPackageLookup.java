package com.amazon.device.crashmanager.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.amazon.dp.logger.DPLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class AmazonPackageLookup {
    private static final Map<String, String> PACKAGE_NAME_TO_FRIENDLY_NAME;
    public static final Set<String> SYSTEM_PACKAGES;
    public static final String SYSTEM_PACKAGE_PREFIX = "/system/";
    public static final String THIRD_PARTY_NAME = "ThirdPartyApp";
    private static final DPLogger log = new DPLogger("AmazonPackageLookup");
    private final String mMyPackageName;
    private final PackageManager mPackageManager;

    static {
        HashSet hashSet = new HashSet();
        SYSTEM_PACKAGES = hashSet;
        hashSet.add("system_server");
        hashSet.add("zygote");
        hashSet.add("ath6kl");
        HashMap map = new HashMap();
        PACKAGE_NAME_TO_FRIENDLY_NAME = map;
        map.put("com.amazon.adc", "ADC");
        map.put("com.amazon.venezia", "AmazonAppStore");
        map.put("com.amazon.avod", "AmazonVideo");
        map.put("com.amazon.mp3", "AmazonMp3");
        map.put("com.imdb.mobile", "IMDB");
        map.put("com.amazon.kindle", "Kindle");
        map.put("com.amazon.dcp", "DCP");
        map.put("com.amazon.kindle.otter", "Launcher");
        map.put("com.lab126.otter", "QuickSettings");
        map.put("com.amazon.gardemanger", "AppManager");
        map.put("com.amazon.kindle.otter.oobe", "OOBE");
        map.put("com.amazon.otter.tutorial", "Tutorial");
        map.put("com.amazon.kindle.otter.settings", "Settings");
        map.put("com.amazon.csapp", "CSApp");
        map.put("com.amazon.windowshop", "Shop");
        map.put("com.amazon.cloud9", "Silk");
        map.put("com.amazon.cloud9[AdobeFlash]", "SilkFlash");
        map.put("com.amazon.cloud9[WebKit]", "SilkWebkit");
        map.put("com.alphonso.pulse", "Pulse");
        map.put("com.amazon.email", "Email");
        map.put("com.amazon.kindle.facebook", "Facebook");
        map.put("system_server", "SystemServer");
        map.put("/system/bin/mediaserver", "MediaServer");
        map.put("com.android.calendar", "com.android.calendar");
        map.put("com.android.contacts", "com.android.contacts");
        map.put("com.android.email", "com.android.email");
        map.put("com.android.exchange", "com.android.exchange");
        map.put("com.android.providers.contacts", "com.android.providers.contacts");
        map.put("com.android.systemui", "com.android.systemui");
        map.put("com.amazon.kindle.unifiedSearch", "com.amazon.kindle.unifiedSearch");
    }

    public AmazonPackageLookup(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        this.mPackageManager = context.getPackageManager();
        this.mMyPackageName = context.getPackageName();
    }

    public String packageNameToAmazonFriendlyName(String str) {
        if (str == null) {
            return null;
        }
        Map<String, String> map = PACKAGE_NAME_TO_FRIENDLY_NAME;
        String str2 = map.get(str);
        if (str2 != null) {
            return str2;
        }
        String strGenerateFriendlyName = generateFriendlyName(str);
        map.put(str, strGenerateFriendlyName);
        return strGenerateFriendlyName;
    }

    public boolean isTrustedPackage(String str) {
        return isAmazonPackage(str) || isSystemPackage(str);
    }

    public boolean isAmazonPackage(String str) {
        if (str == null) {
            return false;
        }
        return this.mPackageManager.checkSignatures(this.mMyPackageName, canonicalizePackageName(str)) == 0;
    }

    public boolean isSystemPackage(String str) {
        if (str == null) {
            return false;
        }
        if (str.startsWith(SYSTEM_PACKAGE_PREFIX) || SYSTEM_PACKAGES.contains(str)) {
            return true;
        }
        try {
            return this.mPackageManager.checkSignatures(1000, getUidForPackage(canonicalizePackageName(str))) == 0;
        } catch (PackageManager.NameNotFoundException unused) {
            log.warn("isSystemPackage", "Name not found for package: " + str, new Object[0]);
            return false;
        }
    }

    public String generateFriendlyName(String str) {
        return isTrustedPackage(str) ? str : THIRD_PARTY_NAME;
    }

    private int getUidForPackage(String str) throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo = this.mPackageManager.getApplicationInfo(str, 0);
        if (applicationInfo == null) {
            throw new PackageManager.NameNotFoundException();
        }
        return applicationInfo.uid;
    }

    private String canonicalizePackageName(String str) {
        if (str == null || !str.contains(":")) {
            return str;
        }
        String[] strArrSplit = str.split(":");
        return strArrSplit.length == 2 ? strArrSplit[0] : str;
    }
}
