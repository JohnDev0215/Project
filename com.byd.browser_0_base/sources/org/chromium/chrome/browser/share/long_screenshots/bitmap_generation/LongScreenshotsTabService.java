package org.chromium.chrome.browser.share.long_screenshots.bitmap_generation;

import J.N;
import defpackage.pk1;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class LongScreenshotsTabService implements pk1 {
    public a a;
    public long b;

    public interface a {
    }

    @CalledByNative
    public LongScreenshotsTabService(long j) {
        this.b = j;
    }

    @Override // defpackage.pk1
    public final long a() {
        return this.b;
    }

    @CalledByNative
    public void onNativeDestroyed() {
        this.b = 0L;
    }

    @CalledByNative
    public void processCaptureTabStatus(int i) {
        a aVar = this.a;
        if (aVar != null) {
            ((org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.a) aVar).a(i, 0L);
        }
    }

    @CalledByNative
    public void processPaintPreviewResponse(long j) {
        a aVar = this.a;
        if (aVar != null) {
            ((org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.a) aVar).a(1, j);
        } else {
            if (j == 0) {
                return;
            }
            N.Mj0DaLs7(j);
        }
    }
}
