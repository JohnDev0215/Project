package com.android.networkstack.metrics;

import android.net.util.NetworkStackUtils;
import android.net.util.Stopwatch;
import android.stats.connectivity.DhcpErrorCode;
import android.stats.connectivity.DhcpFeature;
import android.stats.connectivity.DisconnectCode;
import android.stats.connectivity.HostnameTransResult;
import com.android.networkstack.metrics.DhcpSession;
import com.android.networkstack.metrics.NetworkIpProvisioningReported;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class IpProvisioningMetrics {
    private final Set<DhcpFeature> mDhcpFeatures;
    private final Stopwatch mIpv4Watch;
    private final Stopwatch mIpv6Watch;
    private final Stopwatch mWatch;
    private final NetworkIpProvisioningReported.Builder mStatsBuilder = NetworkIpProvisioningReported.newBuilder();
    private final DhcpSession.Builder mDhcpSessionBuilder = DhcpSession.newBuilder();

    public IpProvisioningMetrics() {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        this.mIpv4Watch = stopwatch;
        Stopwatch stopwatch2 = new Stopwatch();
        stopwatch2.start();
        this.mIpv6Watch = stopwatch2;
        Stopwatch stopwatch3 = new Stopwatch();
        stopwatch3.start();
        this.mWatch = stopwatch3;
        this.mDhcpFeatures = new HashSet();
    }

    public void reset() {
        this.mStatsBuilder.clear();
        this.mDhcpSessionBuilder.clear();
        this.mDhcpFeatures.clear();
        this.mIpv4Watch.restart();
        this.mIpv6Watch.restart();
        this.mWatch.restart();
    }

    public void setIPv4ProvisionedLatencyOnFirstTime(boolean z) {
        if (!z || this.mStatsBuilder.hasIpv4LatencyMicros()) {
            return;
        }
        this.mStatsBuilder.setIpv4LatencyMicros(NetworkStackUtils.saturatedCast(this.mIpv4Watch.stop()));
    }

    public void setIPv6ProvisionedLatencyOnFirstTime(boolean z) {
        if (!z || this.mStatsBuilder.hasIpv6LatencyMicros()) {
            return;
        }
        this.mStatsBuilder.setIpv6LatencyMicros(NetworkStackUtils.saturatedCast(this.mIpv6Watch.stop()));
    }

    public void setDhcpEnabledFeature(DhcpFeature dhcpFeature) {
        if (dhcpFeature == DhcpFeature.DF_UNKNOWN) {
            return;
        }
        this.mDhcpFeatures.add(dhcpFeature);
    }

    public void incrementCountForDiscover() {
        DhcpSession.Builder builder = this.mDhcpSessionBuilder;
        builder.setDiscoverCount(builder.getDiscoverCount() + 1);
    }

    public void incrementCountForRequest() {
        DhcpSession.Builder builder = this.mDhcpSessionBuilder;
        builder.setRequestCount(builder.getRequestCount() + 1);
    }

    public void incrementCountForIpConflict() {
        DhcpSession.Builder builder = this.mDhcpSessionBuilder;
        builder.setConflictCount(builder.getConflictCount() + 1);
    }

    public void setHostnameTransinfo(boolean z, boolean z2) {
        HostnameTransResult hostnameTransResult;
        DhcpSession.Builder builder = this.mDhcpSessionBuilder;
        if (z) {
            hostnameTransResult = z2 ? HostnameTransResult.HTR_SUCCESS : HostnameTransResult.HTR_FAILURE;
        } else {
            hostnameTransResult = HostnameTransResult.HTR_DISABLE;
        }
        builder.setHtResult(hostnameTransResult);
    }

    private static DhcpErrorCode dhcpErrorFromNumberSafe(int i) {
        DhcpErrorCode dhcpErrorCodeForNumber = DhcpErrorCode.forNumber(i & (-65536));
        return dhcpErrorCodeForNumber == null ? DhcpErrorCode.ET_UNKNOWN : dhcpErrorCodeForNumber;
    }

    public void addDhcpErrorCode(int i) {
        if (this.mDhcpSessionBuilder.getErrorCodeCount() >= 20) {
            return;
        }
        this.mDhcpSessionBuilder.addErrorCode(dhcpErrorFromNumberSafe(i));
    }

    public void setDisconnectCode(DisconnectCode disconnectCode) {
        if (this.mStatsBuilder.hasDisconnectCode()) {
            return;
        }
        this.mStatsBuilder.setDisconnectCode(disconnectCode);
    }

    public NetworkIpProvisioningReported statsWrite() {
        if (!this.mWatch.isStarted()) {
            return null;
        }
        Iterator<DhcpFeature> it = this.mDhcpFeatures.iterator();
        while (it.hasNext()) {
            this.mDhcpSessionBuilder.addUsedFeatures(it.next());
        }
        this.mStatsBuilder.setDhcpSession(this.mDhcpSessionBuilder);
        this.mStatsBuilder.setProvisioningDurationMicros(this.mWatch.stop());
        this.mStatsBuilder.setRandomNumber((int) (Math.random() * 1000.0d));
        NetworkIpProvisioningReported networkIpProvisioningReportedBuild = this.mStatsBuilder.build();
        NetworkStackStatsLog.write(290, networkIpProvisioningReportedBuild.getTransportType().getNumber(), networkIpProvisioningReportedBuild.getIpv4LatencyMicros(), networkIpProvisioningReportedBuild.getIpv6LatencyMicros(), networkIpProvisioningReportedBuild.getProvisioningDurationMicros(), networkIpProvisioningReportedBuild.getDisconnectCode().getNumber(), networkIpProvisioningReportedBuild.getDhcpSession().toByteArray(), networkIpProvisioningReportedBuild.getRandomNumber());
        this.mWatch.reset();
        return networkIpProvisioningReportedBuild;
    }
}
