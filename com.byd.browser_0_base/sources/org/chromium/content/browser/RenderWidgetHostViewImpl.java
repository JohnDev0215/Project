package org.chromium.content.browser;

import defpackage.uv;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class RenderWidgetHostViewImpl {
    public long a;
    public uv b;

    public RenderWidgetHostViewImpl(long j) {
        this.a = j;
    }

    @CalledByNative
    public static RenderWidgetHostViewImpl create(long j) {
        return new RenderWidgetHostViewImpl(j);
    }

    @CalledByNative
    public final void clearNativePtr() {
        this.a = 0L;
        this.b = new uv("clearNativePtr");
    }
}
