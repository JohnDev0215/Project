package defpackage;

import android.view.View;
import java.util.Comparator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class E1 implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        float fR = AbstractC1251o3.r((View) obj);
        float z = ((View) obj2).getZ();
        if (fR > z) {
            return -1;
        }
        return fR < z ? 1 : 0;
    }
}
