package defpackage;

import android.view.ActionMode;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class OC implements Runnable {
    public final /* synthetic */ ActionMode s;
    public final /* synthetic */ PC t;

    public OC(PC pc, ActionMode actionMode) {
        this.t = pc;
        this.s = actionMode;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.s.onDestroyActionMode(this.s);
    }
}
