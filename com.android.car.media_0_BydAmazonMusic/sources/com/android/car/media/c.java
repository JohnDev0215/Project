package com.android.car.media;

import android.car.content.pm.XCarPackageManager;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.android.car.media.f;
import spi.b7;
import spi.uc;
import spi.v0;

/* JADX INFO: compiled from: ErrorScreenController.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends f {
    public final v0 i;

    public c(FragmentActivity fragmentActivity, XCarPackageManager xCarPackageManager, ViewGroup viewGroup) {
        super(fragmentActivity, xCarPackageManager, viewGroup, com.android.car.media.main.R$layout.fragment_error);
        this.i = new v0(this.e);
    }

    @Override // com.android.car.media.f
    public final void a(@Nullable b7 b7Var) {
        super.a(b7Var);
        this.f.j = new f.a();
        this.f.c.setTitle(b7Var != null ? b7Var.b : this.c.getResources().getString(com.android.car.media.main.R$string.media_app_title));
        v0 v0Var = this.i;
        uc.b(v0Var.a, 0, null);
        uc.b(v0Var.b, 0, null);
    }
}
