package com.android.networkstack.metrics;

import android.net.captiveportal.CaptivePortalProbeResult;

/* JADX INFO: loaded from: classes.dex */
public class DataStallStatsUtils {
    public static int probeResultToEnum(CaptivePortalProbeResult captivePortalProbeResult) {
        if (captivePortalProbeResult == null) {
            return 2;
        }
        if (captivePortalProbeResult.isSuccessful()) {
            return 1;
        }
        if (captivePortalProbeResult.isPortal()) {
            return 3;
        }
        return captivePortalProbeResult.isPartialConnectivity() ? 4 : 2;
    }

    public static void write(DataStallDetectionStats dataStallDetectionStats, CaptivePortalProbeResult captivePortalProbeResult) {
        NetworkStackStatsLog.write(121, dataStallDetectionStats.mEvaluationType, probeResultToEnum(captivePortalProbeResult), dataStallDetectionStats.mNetworkType, dataStallDetectionStats.mWifiInfo, dataStallDetectionStats.mCellularInfo, dataStallDetectionStats.mDns, dataStallDetectionStats.mTcpFailRate, dataStallDetectionStats.mTcpSentSinceLastRecv);
    }
}
