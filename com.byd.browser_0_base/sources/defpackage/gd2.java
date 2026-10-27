package defpackage;

import android.view.View;

/* JADX INFO: compiled from: SadTab.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gd2 implements View.OnClickListener {
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Runnable f;

    public gd2(boolean z, z8 z8Var) {
        this.e = z;
        this.f = z8Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.e) {
            oj2.U(1, 3, "Tabs.SadTab.Feedback.Event");
        } else {
            oj2.U(1, 3, "Tabs.SadTab.Reload.Event");
        }
        this.f.run();
    }
}
