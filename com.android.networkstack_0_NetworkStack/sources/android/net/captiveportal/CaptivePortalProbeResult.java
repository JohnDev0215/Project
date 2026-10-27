package android.net.captiveportal;

/* JADX INFO: loaded from: classes.dex */
public class CaptivePortalProbeResult {
    public final String detectUrl;
    final int mHttpResponseCode;
    public final CaptivePortalProbeSpec probeSpec;
    public final int probeType;
    public final String redirectUrl;
    public static final CaptivePortalProbeResult PRIVATE_IP = new CaptivePortalProbeResult(-2, 2);
    public static final CaptivePortalProbeResult PARTIAL = new CaptivePortalProbeResult(-1, 6);

    public CaptivePortalProbeResult(int i, int i2) {
        this(i, null, null, null, i2);
    }

    public CaptivePortalProbeResult(int i, String str, String str2, int i2) {
        this(i, str, str2, null, i2);
    }

    public CaptivePortalProbeResult(int i, String str, String str2, CaptivePortalProbeSpec captivePortalProbeSpec, int i2) {
        this.mHttpResponseCode = i;
        this.redirectUrl = str;
        this.detectUrl = str2;
        this.probeSpec = captivePortalProbeSpec;
        this.probeType = i2;
    }

    public boolean isSuccessful() {
        return this.mHttpResponseCode == 204;
    }

    public boolean isPortal() {
        int i;
        return !isSuccessful() && (i = this.mHttpResponseCode) >= 200 && i <= 399;
    }

    public boolean isFailed() {
        return (isSuccessful() || isPortal()) ? false : true;
    }

    public boolean isPartialConnectivity() {
        return this.mHttpResponseCode == -1;
    }

    public boolean isDnsPrivateIpResponse() {
        return this.mHttpResponseCode == -2;
    }

    public static CaptivePortalProbeResult failed(int i) {
        return new CaptivePortalProbeResult(599, i);
    }

    public static CaptivePortalProbeResult success(int i) {
        return new CaptivePortalProbeResult(204, i);
    }

    public boolean isConcludedFromHttps() {
        return (this.probeType & 4) != 0;
    }

    public boolean isConcludedFromHttp() {
        return (this.probeType & 2) != 0;
    }
}
