package android.net.metrics;

import android.net.NetworkCapabilities;
import android.security.keystore.KeyProperties;
import com.android.internal.util.BitUtils;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultNetworkEvent {
    public final long creationTimeMs;
    public long durationMs;
    public int finalScore;
    public int initialScore;
    public boolean ipv4;
    public boolean ipv6;
    public int netId = 0;
    public int previousTransports;
    public int transports;
    public long validatedMs;

    public DefaultNetworkEvent(long j) {
        this.creationTimeMs = j;
    }

    public void updateDuration(long j) {
        this.durationMs = j - this.creationTimeMs;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "DefaultNetworkEvent(", ")");
        stringJoiner.add("netId=" + this.netId);
        for (int i : BitUtils.unpackBits(this.transports)) {
            stringJoiner.add(NetworkCapabilities.transportNameOf(i));
        }
        stringJoiner.add("ip=" + ipSupport());
        if (this.initialScore > 0) {
            stringJoiner.add("initial_score=" + this.initialScore);
        }
        if (this.finalScore > 0) {
            stringJoiner.add("final_score=" + this.finalScore);
        }
        stringJoiner.add(String.format("duration=%.0fs", Double.valueOf(this.durationMs / 1000.0d)));
        stringJoiner.add(String.format("validation=%04.1f%%", Double.valueOf((this.validatedMs * 100.0d) / this.durationMs)));
        return stringJoiner.toString();
    }

    private String ipSupport() {
        boolean z = this.ipv4;
        return (z && this.ipv6) ? "IPv4v6" : this.ipv6 ? "IPv6" : z ? "IPv4" : KeyProperties.DIGEST_NONE;
    }
}
