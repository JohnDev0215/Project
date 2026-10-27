package com.android.wifitrackerlib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkKey;
import android.net.NetworkScoreManager;
import android.net.ScoredNetwork;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.Annotation;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import com.android.settingslib.HelpUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes6.dex */
class Utils {
    private static NetworkScoreManager sNetworkScoreManager;

    Utils() {
    }

    private static String getActiveScorerPackage(Context context) {
        if (sNetworkScoreManager == null) {
            sNetworkScoreManager = (NetworkScoreManager) context.getSystemService(NetworkScoreManager.class);
        }
        return sNetworkScoreManager.getActiveScorerPackage();
    }

    static ScanResult getBestScanResultByLevel(List<ScanResult> scanResults) {
        if (scanResults.isEmpty()) {
            return null;
        }
        return (ScanResult) Collections.max(scanResults, Comparator.comparingInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.-$$Lambda$Utils$wGn2sVTZ5l1wFLkqd7rxKtPh0RU
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((ScanResult) obj).level;
            }
        }));
    }

    static List<Integer> getSecurityTypesFromScanResult(ScanResult scan) {
        List<Integer> securityTypes = new ArrayList<>();
        if (scan.capabilities == null) {
            securityTypes.add(0);
        } else if (scan.capabilities.contains("PSK") && scan.capabilities.contains("SAE")) {
            securityTypes.add(2);
            securityTypes.add(5);
        } else if (scan.capabilities.contains("OWE_TRANSITION")) {
            securityTypes.add(0);
            securityTypes.add(4);
        } else if (scan.capabilities.contains("OWE")) {
            securityTypes.add(4);
        } else if (scan.capabilities.contains("WEP")) {
            securityTypes.add(1);
        } else if (scan.capabilities.contains("SAE")) {
            securityTypes.add(5);
        } else if (scan.capabilities.contains("PSK")) {
            securityTypes.add(2);
        } else if (scan.capabilities.contains("EAP_SUITE_B_192")) {
            securityTypes.add(6);
        } else if (scan.capabilities.contains("EAP")) {
            securityTypes.add(3);
        } else {
            securityTypes.add(0);
        }
        return securityTypes;
    }

    static int getSecurityTypeFromWifiConfiguration(WifiConfiguration config) {
        if (config.allowedKeyManagement.get(8)) {
            return 5;
        }
        if (config.allowedKeyManagement.get(1)) {
            return 2;
        }
        if (config.allowedKeyManagement.get(10)) {
            return 6;
        }
        if (config.allowedKeyManagement.get(2) || config.allowedKeyManagement.get(3)) {
            return 3;
        }
        if (config.allowedKeyManagement.get(9)) {
            return 4;
        }
        return config.wepKeys[0] != null ? 1 : 0;
    }

    static Map<String, List<ScanResult>> mapScanResultsToKey(List<ScanResult> scanResults, boolean chooseSingleSecurity, Map<String, WifiConfiguration> wifiConfigsByKey, boolean isWpa3SaeSupported, boolean isWpa3SuiteBSupported, boolean isEnhancedOpenSupported) {
        Map<String, WifiConfiguration> wifiConfigsByKey2;
        Map<String, WifiConfiguration> wifiConfigsByKey3;
        List<Integer> chosenSecurityTypes;
        boolean isOpen;
        if (wifiConfigsByKey != null) {
            wifiConfigsByKey2 = wifiConfigsByKey;
        } else {
            wifiConfigsByKey2 = new HashMap<>();
        }
        Map<String, List<ScanResult>> scanResultsBySsid = (Map) scanResults.stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$Utils$YXgA7eQ3EufOS8jlgf9HRQs4bfM
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Utils.lambda$mapScanResultsToKey$1((ScanResult) obj);
            }
        }).collect(Collectors.groupingBy(new Function() { // from class: com.android.wifitrackerlib.-$$Lambda$Utils$_MVjtMEczmHvXav1qgSkgxMj5iE
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ScanResult) obj).SSID;
            }
        }));
        Map<String, List<ScanResult>> scanResultsByKey = new HashMap<>();
        Iterator<String> it = scanResultsBySsid.keySet().iterator();
        while (it.hasNext()) {
            String ssid = it.next();
            int i = 2;
            boolean pskConfigExists = wifiConfigsByKey2.containsKey(StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(ssid, 2));
            int i2 = 5;
            boolean saeConfigExists = wifiConfigsByKey2.containsKey(StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(ssid, 5));
            boolean openConfigExists = wifiConfigsByKey2.containsKey(StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(ssid, 0));
            boolean oweConfigExists = wifiConfigsByKey2.containsKey(StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(ssid, 4));
            boolean pskInRange = false;
            boolean saeInRange = false;
            boolean oweInRange = false;
            boolean openInRange = false;
            Iterator<ScanResult> it2 = scanResultsBySsid.get(ssid).iterator();
            while (it2.hasNext()) {
                List<Integer> securityTypes = getSecurityTypesFromScanResult(it2.next());
                if (securityTypes.contains(Integer.valueOf(i))) {
                    pskInRange = true;
                }
                if (securityTypes.contains(5)) {
                    saeInRange = true;
                }
                if (securityTypes.contains(4)) {
                    oweInRange = true;
                }
                if (securityTypes.contains(0)) {
                    openInRange = true;
                }
                i = 2;
            }
            Iterator<ScanResult> it3 = scanResultsBySsid.get(ssid).iterator();
            while (it3.hasNext()) {
                ScanResult scan = it3.next();
                List<Integer> securityTypes2 = getSecurityTypesFromScanResult(scan);
                List<Integer> chosenSecurityTypes2 = new ArrayList<>();
                if (isWpa3SaeSupported) {
                    wifiConfigsByKey3 = wifiConfigsByKey2;
                } else {
                    wifiConfigsByKey3 = wifiConfigsByKey2;
                    securityTypes2.remove(Integer.valueOf(i2));
                }
                if (!isWpa3SuiteBSupported) {
                    securityTypes2.remove((Object) 6);
                }
                if (!isEnhancedOpenSupported) {
                    securityTypes2.remove((Object) 4);
                }
                boolean isSae = securityTypes2.contains(5) && !securityTypes2.contains(2);
                Map<String, List<ScanResult>> scanResultsBySsid2 = scanResultsBySsid;
                boolean isPsk = securityTypes2.contains(2) && !securityTypes2.contains(5);
                Iterator<String> it4 = it;
                boolean isPskSaeTransition = securityTypes2.contains(2) && securityTypes2.contains(5);
                Iterator<ScanResult> it5 = it3;
                boolean isOwe = securityTypes2.contains(4) && !securityTypes2.contains(0);
                boolean isOweTransition = securityTypes2.contains(0) && securityTypes2.contains(4);
                Map<String, List<ScanResult>> scanResultsByKey2 = scanResultsByKey;
                boolean isOpen2 = securityTypes2.contains(0) && !securityTypes2.contains(4);
                if (chooseSingleSecurity) {
                    if (!isPsk) {
                        chosenSecurityTypes = chosenSecurityTypes2;
                        if (isPskSaeTransition) {
                            if (pskConfigExists || !saeConfigExists) {
                                chosenSecurityTypes.add(2);
                            } else {
                                chosenSecurityTypes.add(5);
                            }
                        } else if (isSae) {
                            if (!pskConfigExists && (saeConfigExists || !pskInRange)) {
                                chosenSecurityTypes.add(5);
                            } else {
                                chosenSecurityTypes.add(2);
                            }
                        } else if (isOwe) {
                            if (openInRange && openConfigExists && !oweConfigExists) {
                                wifiConfigsByKey2 = wifiConfigsByKey3;
                                scanResultsBySsid = scanResultsBySsid2;
                                it = it4;
                                it3 = it5;
                                scanResultsByKey = scanResultsByKey2;
                                i2 = 5;
                            } else {
                                chosenSecurityTypes.add(4);
                            }
                        } else if (isOweTransition) {
                            if (oweConfigExists || !openConfigExists) {
                                chosenSecurityTypes.add(4);
                            } else {
                                chosenSecurityTypes.add(0);
                            }
                        } else if (isOpen2) {
                            if (oweInRange) {
                                if (oweConfigExists) {
                                    wifiConfigsByKey2 = wifiConfigsByKey3;
                                    scanResultsBySsid = scanResultsBySsid2;
                                    it = it4;
                                    it3 = it5;
                                    scanResultsByKey = scanResultsByKey2;
                                    i2 = 5;
                                } else if (!openConfigExists) {
                                    wifiConfigsByKey2 = wifiConfigsByKey3;
                                    scanResultsBySsid = scanResultsBySsid2;
                                    it = it4;
                                    it3 = it5;
                                    scanResultsByKey = scanResultsByKey2;
                                    i2 = 5;
                                }
                            }
                            chosenSecurityTypes.add(0);
                        } else {
                            chosenSecurityTypes.addAll(securityTypes2);
                        }
                    } else if (!pskConfigExists && saeConfigExists && saeInRange) {
                        wifiConfigsByKey2 = wifiConfigsByKey3;
                        scanResultsBySsid = scanResultsBySsid2;
                        it = it4;
                        it3 = it5;
                        scanResultsByKey = scanResultsByKey2;
                        i2 = 5;
                    } else {
                        chosenSecurityTypes = chosenSecurityTypes2;
                        chosenSecurityTypes.add(2);
                    }
                } else {
                    chosenSecurityTypes = chosenSecurityTypes2;
                    chosenSecurityTypes.addAll(securityTypes2);
                    if (isSae) {
                        chosenSecurityTypes.add(2);
                    }
                }
                Iterator<Integer> it6 = chosenSecurityTypes.iterator();
                while (it6.hasNext()) {
                    int security = it6.next().intValue();
                    boolean isSae2 = isSae;
                    String key = StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(ssid, security);
                    Map<String, List<ScanResult>> scanResultsByKey3 = scanResultsByKey2;
                    if (scanResultsByKey3.containsKey(key)) {
                        isOpen = isOpen2;
                    } else {
                        isOpen = isOpen2;
                        scanResultsByKey3.put(key, new ArrayList<>());
                    }
                    scanResultsByKey3.get(key).add(scan);
                    isSae = isSae2;
                    isOpen2 = isOpen;
                    scanResultsByKey2 = scanResultsByKey3;
                }
                scanResultsByKey = scanResultsByKey2;
                wifiConfigsByKey2 = wifiConfigsByKey3;
                scanResultsBySsid = scanResultsBySsid2;
                it = it4;
                it3 = it5;
                i2 = 5;
            }
            scanResultsBySsid = scanResultsBySsid;
        }
        return scanResultsByKey;
    }

    static /* synthetic */ boolean lambda$mapScanResultsToKey$1(ScanResult scanResult) {
        return !TextUtils.isEmpty(scanResult.SSID);
    }

    static int getAverageSpeedFromScanResults(WifiNetworkScoreCache scoreCache, List<ScanResult> scanResults) {
        int speed;
        int count = 0;
        int totalSpeed = 0;
        for (ScanResult scanResult : scanResults) {
            ScoredNetwork scoredNetwork = scoreCache.getScoredNetwork(scanResult);
            if (scoredNetwork != null && (speed = scoredNetwork.calculateBadge(scanResult.level)) != 0) {
                count++;
                totalSpeed += speed;
            }
        }
        if (count == 0) {
            return 0;
        }
        return roundToClosestSpeedEnum(totalSpeed / count);
    }

    static int getSpeedFromWifiInfo(WifiNetworkScoreCache scoreCache, WifiInfo wifiInfo) {
        ScoredNetwork scoredNetwork = scoreCache.getScoredNetwork(NetworkKey.createFromWifiInfo(wifiInfo));
        if (scoredNetwork == null) {
            return 0;
        }
        return roundToClosestSpeedEnum(scoredNetwork.calculateBadge(wifiInfo.getRssi()));
    }

    private static int roundToClosestSpeedEnum(int speed) {
        if (speed == 0) {
            return 0;
        }
        if (speed < 7) {
            return 5;
        }
        if (speed < 15) {
            return 10;
        }
        if (speed < 25) {
            return 20;
        }
        return 30;
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    static String getAppLabel(Context context, String packageName) {
        try {
            String openWifiPackageName = Settings.Global.getString(context.getContentResolver(), "use_open_wifi_package");
            if (!TextUtils.isEmpty(openWifiPackageName) && TextUtils.equals(packageName, getActiveScorerPackage(context))) {
                packageName = openWifiPackageName;
            }
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfoAsUser(packageName, 0, UserHandle.getUserId(-2));
            return appInfo.loadLabel(context.getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    static String getDisconnectedStateDescription(Context context, WifiEntry wifiEntry) {
        if (context == null || wifiEntry == null) {
            return "";
        }
        WifiConfiguration wifiConfiguration = wifiEntry.getWifiConfiguration();
        if (wifiConfiguration == null) {
            return null;
        }
        if (wifiConfiguration.hasNoInternetAccess()) {
            int messageID = wifiConfiguration.getNetworkSelectionStatus().getNetworkSelectionStatus() == 2 ? R.string.wifi_no_internet_no_reconnect : R.string.wifi_no_internet;
            return context.getString(messageID);
        }
        if (wifiConfiguration.getNetworkSelectionStatus().getNetworkSelectionStatus() != 0) {
            WifiConfiguration.NetworkSelectionStatus networkStatus = wifiConfiguration.getNetworkSelectionStatus();
            int networkSelectionDisableReason = networkStatus.getNetworkSelectionDisableReason();
            if (networkSelectionDisableReason == 1) {
                return context.getString(R.string.wifi_disabled_generic);
            }
            if (networkSelectionDisableReason == 2) {
                return context.getString(R.string.wifi_disabled_password_failure);
            }
            if (networkSelectionDisableReason == 3) {
                return context.getString(R.string.wifi_disabled_network_failure);
            }
            if (networkSelectionDisableReason == 8) {
                return context.getString(R.string.wifi_check_password_try_again);
            }
        } else if (wifiEntry.getLevel() != -1 && wifiConfiguration.getRecentFailureReason() == 17) {
            return context.getString(R.string.wifi_ap_unable_to_handle_new_sta);
        }
        return "";
    }

    static String getAutoConnectDescription(Context context, WifiEntry wifiEntry) {
        return (context == null || wifiEntry == null || !wifiEntry.canSetAutoJoinEnabled() || wifiEntry.isAutoJoinEnabled()) ? "" : context.getString(R.string.auto_connect_disable);
    }

    static String getMeteredDescription(Context context, WifiEntry wifiEntry) {
        if (context == null || wifiEntry == null) {
            return "";
        }
        if (!wifiEntry.canSetMeteredChoice() && wifiEntry.getMeteredChoice() != 1) {
            return "";
        }
        if (wifiEntry.getMeteredChoice() == 1) {
            return context.getString(R.string.wifi_metered_label);
        }
        if (wifiEntry.getMeteredChoice() == 2) {
            return context.getString(R.string.wifi_unmetered_label);
        }
        return wifiEntry.isMetered() ? context.getString(R.string.wifi_metered_label) : "";
    }

    static String getSpeedDescription(Context context, WifiEntry wifiEntry) {
        if (context == null || wifiEntry == null) {
            return "";
        }
        int speed = wifiEntry.getSpeed();
        if (speed == 5) {
            return context.getString(R.string.speed_label_slow);
        }
        if (speed == 10) {
            return context.getString(R.string.speed_label_okay);
        }
        if (speed == 20) {
            return context.getString(R.string.speed_label_fast);
        }
        if (speed != 30) {
            return "";
        }
        return context.getString(R.string.speed_label_very_fast);
    }

    static String getVerboseLoggingDescription(WifiEntry wifiEntry) {
        if (!BaseWifiTracker.isVerboseLoggingEnabled() || wifiEntry == null) {
            return "";
        }
        StringJoiner sj = new StringJoiner(" ");
        String wifiInfoDescription = wifiEntry.getWifiInfoDescription();
        if (!TextUtils.isEmpty(wifiInfoDescription)) {
            sj.add(wifiInfoDescription);
        }
        String networkCapabilityDescription = wifiEntry.getNetworkCapabilityDescription();
        if (!TextUtils.isEmpty(networkCapabilityDescription)) {
            sj.add(networkCapabilityDescription);
        }
        String scanResultsDescription = wifiEntry.getScanResultDescription();
        if (!TextUtils.isEmpty(scanResultsDescription)) {
            sj.add(scanResultsDescription);
        }
        String networkSelectionDescription = wifiEntry.getNetworkSelectionDescription();
        if (!TextUtils.isEmpty(networkSelectionDescription)) {
            sj.add(networkSelectionDescription);
        }
        return sj.toString();
    }

    static String getNetworkSelectionDescription(WifiConfiguration wifiConfig) {
        if (wifiConfig == null) {
            return "";
        }
        StringBuilder description = new StringBuilder();
        WifiConfiguration.NetworkSelectionStatus networkSelectionStatus = wifiConfig.getNetworkSelectionStatus();
        if (networkSelectionStatus.getNetworkSelectionStatus() != 0) {
            description.append(" (" + networkSelectionStatus.getNetworkStatusString());
            if (networkSelectionStatus.getDisableTime() > 0) {
                long now = System.currentTimeMillis();
                long elapsedSeconds = (now - networkSelectionStatus.getDisableTime()) / 1000;
                description.append(" " + DateUtils.formatElapsedTime(elapsedSeconds));
            }
            description.append(")");
        }
        int maxNetworkSelectionDisableReason = WifiConfiguration.NetworkSelectionStatus.getMaxNetworkSelectionDisableReason();
        for (int reason = 0; reason <= maxNetworkSelectionDisableReason; reason++) {
            int disableReasonCounter = networkSelectionStatus.getDisableReasonCounter(reason);
            if (disableReasonCounter != 0) {
                description.append(" ");
                description.append(WifiConfiguration.NetworkSelectionStatus.getNetworkSelectionDisableReasonString(reason));
                description.append("=");
                description.append(disableReasonCounter);
            }
        }
        return description.toString();
    }

    static String getCurrentNetworkCapabilitiesInformation(Context context, NetworkCapabilities networkCapabilities) {
        if (context == null || networkCapabilities == null) {
            return "";
        }
        if (networkCapabilities.hasCapability(17)) {
            return context.getString(context.getResources().getIdentifier("network_available_sign_in", "string", SystemMediaRouteProvider.PACKAGE_NAME));
        }
        if (networkCapabilities.hasCapability(24)) {
            return context.getString(R.string.wifi_limited_connection);
        }
        if (networkCapabilities.hasCapability(16)) {
            return "";
        }
        if (networkCapabilities.isPrivateDnsBroken()) {
            return context.getString(R.string.private_dns_broken);
        }
        return context.getString(R.string.wifi_connected_no_internet);
    }

    static String getNetworkDetailedState(Context context, NetworkInfo networkInfo) {
        NetworkInfo.DetailedState detailState;
        if (context == null || networkInfo == null || (detailState = networkInfo.getDetailedState()) == null) {
            return "";
        }
        String[] wifiStatusArray = context.getResources().getStringArray(R.array.wifi_status);
        int index = detailState.ordinal();
        return index >= wifiStatusArray.length ? "" : wifiStatusArray[index];
    }

    static boolean isSimPresent(Context context, final int carrierId) {
        List<SubscriptionInfo> subInfoList;
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
        if (subscriptionManager == null || (subInfoList = subscriptionManager.getActiveSubscriptionInfoList()) == null || subInfoList.isEmpty()) {
            return false;
        }
        return subInfoList.stream().anyMatch(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$Utils$7CC2_MUprPhJhoGFsuEYFL2piW0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Utils.lambda$isSimPresent$3(carrierId, (SubscriptionInfo) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$isSimPresent$3(int carrierId, SubscriptionInfo info) {
        return info.getCarrierId() == carrierId;
    }

    static String getCarrierNameForSubId(Context context, int subId) {
        TelephonyManager telephonyManager;
        TelephonyManager specifiedTm;
        CharSequence name;
        if (subId == -1 || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null || (specifiedTm = telephonyManager.createForSubscriptionId(subId)) == null || (name = specifiedTm.getSimCarrierIdName()) == null) {
            return null;
        }
        return name.toString();
    }

    static boolean isSimCredential(WifiConfiguration config) {
        return config.enterpriseConfig != null && config.enterpriseConfig.isAuthenticationSimBased();
    }

    static int getSubIdForConfig(Context context, WifiConfiguration config) {
        SubscriptionManager subscriptionManager;
        List<SubscriptionInfo> subInfoList;
        if (config.carrierId == -1 || (subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service")) == null || (subInfoList = subscriptionManager.getActiveSubscriptionInfoList()) == null || subInfoList.isEmpty()) {
            return -1;
        }
        int matchSubId = -1;
        int dataSubId = SubscriptionManager.getDefaultDataSubscriptionId();
        for (SubscriptionInfo subInfo : subInfoList) {
            if (subInfo.getCarrierId() == config.carrierId && (matchSubId = subInfo.getSubscriptionId()) == dataSubId) {
                break;
            }
        }
        return matchSubId;
    }

    static boolean isImsiPrivacyProtectionProvided(Context context, int subId) {
        PersistableBundle bundle;
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) context.getSystemService("carrier_config");
        return (carrierConfigManager == null || (bundle = carrierConfigManager.getConfigForSubId(subId)) == null || (bundle.getInt("imsi_key_availability_int") & 2) == 0) ? false : true;
    }

    static CharSequence getImsiProtectionDescription(Context context, WifiConfiguration wifiConfig) {
        int subId;
        if (context == null || wifiConfig == null || !isSimCredential(wifiConfig)) {
            return "";
        }
        if (wifiConfig.carrierId == -1) {
            subId = SubscriptionManager.getDefaultSubscriptionId();
        } else {
            subId = getSubIdForConfig(context, wifiConfig);
        }
        if (subId == -1 || isImsiPrivacyProtectionProvided(context, subId)) {
            return "";
        }
        return linkifyAnnotation(context, context.getText(R.string.imsi_protection_warning), "url", context.getString(R.string.help_url_imsi_protection));
    }

    static CharSequence linkifyAnnotation(final Context context, CharSequence rawText, String id, final String helpUriString) {
        SpannableString spannableText = new SpannableString(rawText);
        Annotation[] annotations = (Annotation[]) spannableText.getSpans(0, spannableText.length(), Annotation.class);
        for (Annotation annotation : annotations) {
            if (TextUtils.equals(annotation.getValue(), id)) {
                SpannableStringBuilder builder = new SpannableStringBuilder(spannableText);
                ClickableSpan link = new ClickableSpan() { // from class: com.android.wifitrackerlib.Utils.1
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        view.startActivityForResult(HelpUtils.getHelpIntent(context, helpUriString, view.getClass().getName()), 0);
                    }
                };
                builder.setSpan(link, spannableText.getSpanStart(annotation), spannableText.getSpanEnd(annotation), spannableText.getSpanFlags(link));
                return builder;
            }
        }
        return rawText;
    }
}
