package defpackage;

import android.content.Context;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: compiled from: AppMenuCoordinatorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d7 implements c7 {
    public final Context a;
    public final m7 b;
    public final i7 c;

    public d7(AppCompatActivity appCompatActivity, q2 q2Var, e7 e7Var, View view, View view2, fc2 fc2Var) {
        this.a = appCompatActivity;
        m7 m7VarC0 = e7Var.C0();
        this.b = m7VarC0;
        this.c = new i7(appCompatActivity, m7VarC0, e7Var, view, q2Var, view2, fc2Var);
    }

    @Override // defpackage.c7
    public final m7 a() {
        return this.b;
    }

    @Override // defpackage.c7
    public final i7 b() {
        return this.c;
    }
}
