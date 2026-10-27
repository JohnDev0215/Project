package org.chromium.components.content_capture;

import android.graphics.Rect;
import defpackage.Z7;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ContentCaptureData {
    public long a;
    public String b;
    public Rect c;
    public ArrayList d;

    public ContentCaptureData(long j, String str, int i, int i2, int i3, int i4) {
        this.a = j;
        this.b = str;
        this.c = new Rect(i, i2, i3 + i, i4 + i2);
    }

    public static ContentCaptureData createContentCaptureData(Object obj, long j, String str, int i, int i2, int i3, int i4) {
        ContentCaptureData contentCaptureData = new ContentCaptureData(j, str, i, i2, i3, i4);
        if (obj != null) {
            ContentCaptureData contentCaptureData2 = (ContentCaptureData) obj;
            if (contentCaptureData2.d == null) {
                contentCaptureData2.d = new ArrayList();
            }
            contentCaptureData2.d.add(contentCaptureData);
        }
        return contentCaptureData;
    }

    public boolean a() {
        ArrayList arrayList = this.d;
        return (arrayList == null || arrayList.isEmpty()) ? false : true;
    }

    public String toString() {
        StringBuilder sbF = Z7.f("id:");
        sbF.append(this.a);
        sbF.append(" value:");
        sbF.append(this.b);
        sbF.append(" bounds:");
        sbF.append(this.c);
        sbF.append('\n');
        if (a()) {
            sbF.append("children:");
            sbF.append(this.d.size());
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                sbF.append(((ContentCaptureData) it.next()).toString());
            }
        }
        return sbF.toString();
    }
}
