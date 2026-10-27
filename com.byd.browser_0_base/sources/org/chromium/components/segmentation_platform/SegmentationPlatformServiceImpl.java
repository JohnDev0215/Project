package org.chromium.components.segmentation_platform;

import J.N;
import defpackage.ph2;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class SegmentationPlatformServiceImpl implements ph2 {
    public long a;

    public SegmentationPlatformServiceImpl(long j) {
        this.a = j;
    }

    @CalledByNative
    public static SegmentationPlatformServiceImpl create(long j) {
        return new SegmentationPlatformServiceImpl(j);
    }

    @Override // defpackage.ph2
    public final void a(String str, Callback<SegmentSelectionResult> callback) {
        N.Mv7niEOS(this.a, this, str, callback);
    }

    @CalledByNative
    public final void clearNativePtr() {
        this.a = 0L;
    }
}
