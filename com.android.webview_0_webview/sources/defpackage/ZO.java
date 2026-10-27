package defpackage;

import android.util.SparseArray;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ZO extends UO {
    public final SparseArray c;

    public ZO(int i, TO to) {
        super(i, to);
        this.c = new SparseArray();
    }

    @Override // defpackage.UO
    public void a(int i) {
        YO yo = (YO) this.c.get(i);
        if (yo != null && (!((ViewOnLayoutChangeListenerC0493aP) yo).b.isEmpty())) {
            b(i, yo);
        }
    }

    @Override // defpackage.UO
    public void c(int i) {
    }
}
