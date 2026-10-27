package org.chromium.components.content_capture;

import android.graphics.Rect;
import defpackage.jy;
import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class ContentCaptureData extends jy {
    public final String d;

    public ContentCaptureData(String str, long j, int i, int i2, int i3, int i4) {
        super(j, new Rect(i, i2, i3 + i, i4 + i2));
        this.d = str;
    }

    @CalledByNative
    public static ContentCaptureData createContentCaptureData(Object obj, long j, String str, int i, int i2, int i3, int i4) {
        ContentCaptureData contentCaptureData = new ContentCaptureData(str, j, i, i2, i3, i4);
        if (obj != null) {
            jy jyVar = (jy) obj;
            if (jyVar.c == null) {
                jyVar.c = new ArrayList<>();
            }
            jyVar.c.add(contentCaptureData);
        }
        return contentCaptureData;
    }

    @Override // defpackage.jy
    public final String a() {
        return this.d;
    }

    @Override // defpackage.jy
    public final String toString() {
        return super.toString() + " value:" + this.d;
    }
}
