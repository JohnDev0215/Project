package org.chromium.components.content_capture;

import android.graphics.Rect;
import defpackage.jy;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class ContentCaptureFrame extends jy {
    public final String d;
    public final String e;
    public final String f;

    public ContentCaptureFrame(long j, String str, int i, int i2, int i3, int i4, String str2, String str3) {
        super(j, new Rect(i, i2, i3 + i, i4 + i2));
        this.d = str;
        this.e = str2;
        this.f = str3;
    }

    @CalledByNative
    public static ContentCaptureFrame createContentCaptureFrame(long j, String str, int i, int i2, int i3, int i4, String str2, String str3) {
        return new ContentCaptureFrame(j, str, i, i2, i3, i4, str2, str3);
    }

    @Override // defpackage.jy
    public final String a() {
        return this.e;
    }

    @Override // defpackage.jy
    public final String toString() {
        return super.toString() + " Url:" + this.d + " Title:" + this.e;
    }
}
