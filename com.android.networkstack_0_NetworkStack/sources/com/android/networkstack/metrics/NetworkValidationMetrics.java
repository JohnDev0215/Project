package com.android.networkstack.metrics;

import android.net.NetworkCapabilities;
import android.net.captiveportal.CaptivePortalProbeResult;
import android.net.util.NetworkStackUtils;
import android.net.util.Stopwatch;
import android.stats.connectivity.ProbeResult;
import android.stats.connectivity.ProbeType;
import android.stats.connectivity.TransportType;
import android.stats.connectivity.ValidationResult;
import com.android.networkstack.apishim.common.CaptivePortalDataShim;
import com.android.networkstack.metrics.CapportApiData;
import com.android.networkstack.metrics.NetworkValidationReported;
import com.android.networkstack.metrics.ProbeEvent;
import com.android.networkstack.metrics.ProbeEvents;

/* JADX INFO: loaded from: classes.dex */
public class NetworkValidationMetrics {
    private final NetworkValidationReported.Builder mStatsBuilder = NetworkValidationReported.newBuilder();
    private final ProbeEvents.Builder mProbeEventsBuilder = ProbeEvents.newBuilder();
    private final CapportApiData.Builder mCapportApiDataBuilder = CapportApiData.newBuilder();
    private final Stopwatch mWatch = new Stopwatch();
    private int mValidationIndex = 0;

    public void startCollection(NetworkCapabilities networkCapabilities) {
        this.mStatsBuilder.clear();
        this.mProbeEventsBuilder.clear();
        this.mCapportApiDataBuilder.clear();
        this.mWatch.restart();
        this.mStatsBuilder.setTransportType(getTransportTypeFromNC(networkCapabilities));
        this.mValidationIndex++;
    }

    public static TransportType getTransportTypeFromNC(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities == null) {
            return TransportType.TT_UNKNOWN;
        }
        int length = networkCapabilities.getTransportTypes().length;
        boolean zHasTransport = networkCapabilities.hasTransport(0);
        boolean zHasTransport2 = networkCapabilities.hasTransport(1);
        boolean zHasTransport3 = networkCapabilities.hasTransport(2);
        boolean zHasTransport4 = networkCapabilities.hasTransport(3);
        boolean zHasTransport5 = networkCapabilities.hasTransport(4);
        boolean zHasTransport6 = networkCapabilities.hasTransport(5);
        boolean zHasTransport7 = networkCapabilities.hasTransport(6);
        if (length == 3 && zHasTransport && zHasTransport2 && zHasTransport5) {
            return TransportType.TT_WIFI_CELLULAR_VPN;
        }
        if (length == 2 && zHasTransport5) {
            if (zHasTransport2) {
                return TransportType.TT_WIFI_VPN;
            }
            if (zHasTransport) {
                return TransportType.TT_CELLULAR_VPN;
            }
            if (zHasTransport3) {
                return TransportType.TT_BLUETOOTH_VPN;
            }
            if (zHasTransport4) {
                return TransportType.TT_ETHERNET_VPN;
            }
        }
        if (length == 1) {
            if (zHasTransport2) {
                return TransportType.TT_WIFI;
            }
            if (zHasTransport) {
                return TransportType.TT_CELLULAR;
            }
            if (zHasTransport3) {
                return TransportType.TT_BLUETOOTH;
            }
            if (zHasTransport4) {
                return TransportType.TT_ETHERNET;
            }
            if (zHasTransport6) {
                return TransportType.TT_WIFI_AWARE;
            }
            if (zHasTransport7) {
                return TransportType.TT_LOWPAN;
            }
        }
        return TransportType.TT_UNKNOWN;
    }

    public static ProbeType probeTypeToEnum(int i) {
        if (i == 0) {
            return ProbeType.PT_DNS;
        }
        if (i == 1) {
            return ProbeType.PT_HTTP;
        }
        if (i == 2) {
            return ProbeType.PT_HTTPS;
        }
        if (i == 3) {
            return ProbeType.PT_PAC;
        }
        if (i == 4) {
            return ProbeType.PT_FALLBACK;
        }
        if (i == 5) {
            return ProbeType.PT_PRIVDNS;
        }
        return ProbeType.PT_UNKNOWN;
    }

    public static ProbeResult httpProbeResultToEnum(CaptivePortalProbeResult captivePortalProbeResult) {
        if (captivePortalProbeResult == null) {
            return ProbeResult.PR_UNKNOWN;
        }
        if (captivePortalProbeResult.isSuccessful()) {
            return ProbeResult.PR_SUCCESS;
        }
        if (captivePortalProbeResult.isDnsPrivateIpResponse()) {
            return ProbeResult.PR_PRIVATE_IP_DNS;
        }
        if (captivePortalProbeResult.isFailed()) {
            return ProbeResult.PR_FAILURE;
        }
        if (captivePortalProbeResult.isPortal()) {
            return ProbeResult.PR_PORTAL;
        }
        return ProbeResult.PR_UNKNOWN;
    }

    public static ValidationResult validationResultToEnum(int i, String str) {
        if ((i & 1) != 0) {
            return ValidationResult.VR_SUCCESS;
        }
        if (str != null) {
            return ValidationResult.VR_PORTAL;
        }
        if ((i & 2) != 0) {
            return ValidationResult.VR_PARTIAL;
        }
        return ValidationResult.VR_FAILURE;
    }

    public void addProbeEvent(ProbeType probeType, long j, ProbeResult probeResult, CaptivePortalDataShim captivePortalDataShim) {
        if (this.mProbeEventsBuilder.getProbeEventCount() >= 20) {
            return;
        }
        int iSaturatedCast = NetworkStackUtils.saturatedCast(j);
        ProbeEvent.Builder builderNewBuilder = ProbeEvent.newBuilder();
        builderNewBuilder.setLatencyMicros(iSaturatedCast);
        builderNewBuilder.setProbeType(probeType);
        builderNewBuilder.setProbeResult(probeResult);
        if (captivePortalDataShim != null) {
            long expiryTimeMillis = (captivePortalDataShim.getExpiryTimeMillis() - System.currentTimeMillis()) / 1000;
            CapportApiData.Builder builder = this.mCapportApiDataBuilder;
            builder.setRemainingTtlSecs(NetworkStackUtils.saturatedCast(expiryTimeMillis));
            builder.setRemainingBytes(NetworkStackUtils.saturatedCast(captivePortalDataShim.getByteLimit() / 1000));
            builder.setHasPortalUrl(captivePortalDataShim.getUserPortalUrl() != null);
            builder.setHasVenueInfo(captivePortalDataShim.getVenueInfoUrl() != null);
            builderNewBuilder.setCapportApiData(this.mCapportApiDataBuilder);
        }
        this.mProbeEventsBuilder.addProbeEvent(builderNewBuilder);
    }

    public void setValidationResult(int i, String str) {
        this.mStatsBuilder.setValidationResult(validationResultToEnum(i, str));
    }

    public NetworkValidationReported maybeStopCollectionAndSend() {
        if (!this.mWatch.isStarted()) {
            return null;
        }
        this.mStatsBuilder.setProbeEvents(this.mProbeEventsBuilder);
        this.mStatsBuilder.setLatencyMicros(NetworkStackUtils.saturatedCast(this.mWatch.stop()));
        this.mStatsBuilder.setValidationIndex(this.mValidationIndex);
        this.mStatsBuilder.setRandomNumber((int) (Math.random() * 1000.0d));
        NetworkValidationReported networkValidationReportedBuild = this.mStatsBuilder.build();
        NetworkStackStatsLog.write(292, networkValidationReportedBuild.getTransportType().getNumber(), networkValidationReportedBuild.getProbeEvents().toByteArray(), networkValidationReportedBuild.getValidationResult().getNumber(), networkValidationReportedBuild.getLatencyMicros(), networkValidationReportedBuild.getValidationIndex(), networkValidationReportedBuild.getRandomNumber());
        this.mWatch.reset();
        return networkValidationReportedBuild;
    }
}
