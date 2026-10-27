package com.android.settingslib.wifi;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.SystemClock;
import com.android.settingslib.R;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class WifiUtils {
    private static final int INVALID_RSSI = -127;

    public static String buildLoggingSummary(AccessPoint accessPoint, WifiConfiguration config) {
        StringBuilder summary = new StringBuilder();
        WifiInfo info = accessPoint.getInfo();
        if (accessPoint.isActive() && info != null) {
            summary.append(" f=" + Integer.toString(info.getFrequency()));
        }
        summary.append(" " + getVisibilityStatus(accessPoint));
        if (config != null && config.getNetworkSelectionStatus().getNetworkSelectionStatus() != 0) {
            summary.append(" (" + config.getNetworkSelectionStatus().getNetworkStatusString());
            if (config.getNetworkSelectionStatus().getDisableTime() > 0) {
                long now = System.currentTimeMillis();
                long diff = (now - config.getNetworkSelectionStatus().getDisableTime()) / 1000;
                long sec = diff % 60;
                long min = (diff / 60) % 60;
                long hour = (min / 60) % 60;
                summary.append(", ");
                if (hour > 0) {
                    summary.append(Long.toString(hour) + "h ");
                }
                summary.append(Long.toString(min) + "m ");
                summary.append(Long.toString(sec) + "s ");
            }
            summary.append(")");
        }
        if (config != null) {
            WifiConfiguration.NetworkSelectionStatus networkStatus = config.getNetworkSelectionStatus();
            for (int reason = 0; reason <= WifiConfiguration.NetworkSelectionStatus.getMaxNetworkSelectionDisableReason(); reason++) {
                if (networkStatus.getDisableReasonCounter(reason) != 0) {
                    summary.append(" ");
                    summary.append(WifiConfiguration.NetworkSelectionStatus.getNetworkSelectionDisableReasonString(reason));
                    summary.append("=");
                    summary.append(networkStatus.getDisableReasonCounter(reason));
                }
            }
        }
        return summary.toString();
    }

    static String getVisibilityStatus(AccessPoint accessPoint) {
        WifiInfo info = accessPoint.getInfo();
        StringBuilder visibility = new StringBuilder();
        StringBuilder scans24GHz = new StringBuilder();
        StringBuilder scans5GHz = new StringBuilder();
        String bssid = null;
        if (accessPoint.isActive() && info != null) {
            bssid = info.getBSSID();
            if (bssid != null) {
                visibility.append(" ");
                visibility.append(bssid);
            }
            visibility.append(" standard = ");
            visibility.append(info.getWifiStandard());
            visibility.append(" rssi=");
            visibility.append(info.getRssi());
            visibility.append(" ");
            visibility.append(" score=");
            visibility.append(info.getScore());
            if (accessPoint.getSpeed() != 0) {
                visibility.append(" speed=");
                visibility.append(accessPoint.getSpeedLabel());
            }
            visibility.append(String.format(" tx=%.1f,", Double.valueOf(info.getSuccessfulTxPacketsPerSecond())));
            visibility.append(String.format("%.1f,", Double.valueOf(info.getRetriedTxPacketsPerSecond())));
            visibility.append(String.format("%.1f ", Double.valueOf(info.getLostTxPacketsPerSecond())));
            visibility.append(String.format("rx=%.1f", Double.valueOf(info.getSuccessfulRxPacketsPerSecond())));
        }
        int maxRssi5 = INVALID_RSSI;
        int maxRssi24 = INVALID_RSSI;
        int maxDisplayedScans = 4;
        int num5 = 0;
        int num24 = 0;
        long nowMs = SystemClock.elapsedRealtime();
        Iterator<ScanResult> it = accessPoint.getScanResults().iterator();
        while (true) {
            WifiInfo info2 = info;
            if (!it.hasNext()) {
                break;
            }
            ScanResult result = it.next();
            if (result == null) {
                info = info2;
            } else {
                int maxDisplayedScans2 = maxDisplayedScans;
                if (result.frequency >= 4900 && result.frequency <= 5900) {
                    num5++;
                    if (result.level > maxRssi5) {
                        maxRssi5 = result.level;
                    }
                    if (num5 <= 4) {
                        scans5GHz.append(verboseScanResultSummary(accessPoint, result, bssid, nowMs));
                    }
                } else if (result.frequency >= 2400 && result.frequency <= 2500) {
                    num24++;
                    if (result.level > maxRssi24) {
                        maxRssi24 = result.level;
                    }
                    if (num24 <= 4) {
                        scans24GHz.append(verboseScanResultSummary(accessPoint, result, bssid, nowMs));
                    }
                }
                info = info2;
                maxDisplayedScans = maxDisplayedScans2;
            }
        }
        visibility.append(" [");
        if (num24 > 0) {
            visibility.append("(");
            visibility.append(num24);
            visibility.append(")");
            if (num24 > 4) {
                visibility.append("max=");
                visibility.append(maxRssi24);
                visibility.append(",");
            }
            visibility.append(scans24GHz.toString());
        }
        visibility.append(";");
        if (num5 > 0) {
            visibility.append("(");
            visibility.append(num5);
            visibility.append(")");
            if (num5 > 4) {
                visibility.append("max=");
                visibility.append(maxRssi5);
                visibility.append(",");
            }
            visibility.append(scans5GHz.toString());
        }
        if (0 > 0) {
            visibility.append("!");
            visibility.append(0);
        }
        visibility.append("]");
        return visibility.toString();
    }

    static String verboseScanResultSummary(AccessPoint accessPoint, ScanResult result, String bssid, long nowMs) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" \n{");
        stringBuilder.append(result.BSSID);
        if (result.BSSID.equals(bssid)) {
            stringBuilder.append("*");
        }
        stringBuilder.append("=");
        stringBuilder.append(result.frequency);
        stringBuilder.append(",");
        stringBuilder.append(result.level);
        int speed = getSpecificApSpeed(result, accessPoint.getScoredNetworkCache());
        if (speed != 0) {
            stringBuilder.append(",");
            stringBuilder.append(accessPoint.getSpeedLabel(speed));
        }
        int ageSeconds = ((int) (nowMs - (result.timestamp / 1000))) / 1000;
        stringBuilder.append(",");
        stringBuilder.append(ageSeconds);
        stringBuilder.append("s");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static int getSpecificApSpeed(ScanResult result, Map<String, TimestampedScoredNetwork> scoredNetworkCache) {
        TimestampedScoredNetwork timedScore = scoredNetworkCache.get(result.BSSID);
        if (timedScore == null) {
            return 0;
        }
        return timedScore.getScore().calculateBadge(result.level);
    }

    public static String getMeteredLabel(Context context, WifiConfiguration config) {
        if (config.meteredOverride == 1 || (config.meteredHint && !isMeteredOverridden(config))) {
            return context.getString(R.string.wifi_metered_label);
        }
        return context.getString(R.string.wifi_unmetered_label);
    }

    public static boolean isMeteredOverridden(WifiConfiguration config) {
        return config.meteredOverride != 0;
    }
}
