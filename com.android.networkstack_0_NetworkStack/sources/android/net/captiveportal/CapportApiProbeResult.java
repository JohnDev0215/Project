package android.net.captiveportal;

import com.android.networkstack.apishim.common.CaptivePortalDataShim;

/* JADX INFO: loaded from: classes.dex */
public class CapportApiProbeResult extends CaptivePortalProbeResult {
    private final CaptivePortalDataShim mCapportData;

    public CapportApiProbeResult(CaptivePortalProbeResult captivePortalProbeResult, CaptivePortalDataShim captivePortalDataShim) {
        this(captivePortalProbeResult.mHttpResponseCode, captivePortalProbeResult.redirectUrl, captivePortalProbeResult.detectUrl, captivePortalDataShim, captivePortalProbeResult.probeType);
    }

    public CapportApiProbeResult(int i, String str, String str2, CaptivePortalDataShim captivePortalDataShim, int i2) {
        super(i, str, str2, i2);
        this.mCapportData = captivePortalDataShim;
    }

    public CaptivePortalDataShim getCaptivePortalData() {
        return this.mCapportData;
    }
}
