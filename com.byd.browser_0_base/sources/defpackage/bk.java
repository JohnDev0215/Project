package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import defpackage.ak;

/* JADX INFO: compiled from: ButtonDataImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bk implements ak {
    public boolean a;
    public boolean b;
    public ak.a c;

    public bk() {
    }

    public bk(Drawable drawable, View.OnClickListener onClickListener, String str, int i, boolean z, hw0 hw0Var, int i2) {
        this.a = false;
        this.b = true;
        this.c = new ak.a(drawable, onClickListener, null, str, z, hw0Var, i2, i);
    }

    @Override // defpackage.ak
    public final boolean a() {
        return this.a;
    }

    @Override // defpackage.ak
    public final ak.a b() {
        return this.c;
    }

    @Override // defpackage.ak
    public final boolean isEnabled() {
        return this.b;
    }
}
