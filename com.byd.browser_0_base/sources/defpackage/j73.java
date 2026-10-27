package defpackage;

import android.view.View;

/* JADX INFO: compiled from: TasksSurfaceMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j73 implements View.OnClickListener {
    public final /* synthetic */ m73 e;

    public j73(m73 m73Var) {
        this.e = m73Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.e.e.d().f(3);
        n3.X0("TasksSurface.FakeBox.VoiceSearch");
    }
}
