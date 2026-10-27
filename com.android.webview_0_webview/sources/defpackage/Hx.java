package defpackage;

import android.graphics.RectF;
import java.util.Comparator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Hx implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        RectF rectF = (RectF) obj;
        RectF rectF2 = (RectF) obj2;
        int iCompare = Float.compare(rectF.top, rectF2.top);
        return iCompare != 0 ? iCompare : Float.compare(rectF.left, rectF2.left);
    }
}
