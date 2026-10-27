package com.amazon.avod.media.playback.android;

import com.amazon.avod.media.diagnostics.BaseDiagnosticsController;
import com.amazon.avod.media.framework.util.UrlUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidVideoPlayerDiagnosticsController extends BaseDiagnosticsController {
    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticInfoString() {
        boolean zIsLocal;
        if (this.mDiagnosticInfoStringEnabled || this.mAnchorView == null || this.mSpecification == null) {
            return;
        }
        try {
            zIsLocal = UrlUtils.isLocal(new URL(this.mSpecification.getUrl()));
        } catch (MalformedURLException unused) {
            zIsLocal = false;
        }
        showDiagnosticInfoString(String.format(Locale.US, "Resolution: %dx%d, Bitrate: %d kbps, Mime Type: %s, Cached: %s", Integer.valueOf(this.mSpecification.getAttributes().getResolution().getWidth()), Integer.valueOf(this.mSpecification.getAttributes().getResolution().getHeight()), Long.valueOf(this.mSpecification.getAttributes().getBitrate().getKiloBitsPerSecond()), this.mSpecification.getMimeType(), Boolean.valueOf(zIsLocal)));
    }
}
