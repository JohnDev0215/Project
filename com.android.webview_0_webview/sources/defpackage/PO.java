package defpackage;

import android.graphics.Rect;
import android.graphics.RectF;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PO {
    public PO(float f, QO qo) {
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        C0549bP c0549bPB = qo.b();
        if (c0549bPB != null) {
            rect = c0549bPB.c;
            rect2 = c0549bPB.f;
        }
        Rect rectA = qo.a();
        new RectF(rect.left * f, rect.top * f, rect.right * f, rect.bottom * f);
        new RectF(rectA.left * f, rectA.top * f, rectA.right * f, rectA.bottom * f);
        new RectF(rect2.left * f, rect2.top * f, rect2.right * f, rect2.bottom * f);
    }
}
