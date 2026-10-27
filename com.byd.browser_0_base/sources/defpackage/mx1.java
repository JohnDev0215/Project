package defpackage;

import android.content.res.Resources;
import android.view.View;
import defpackage.di1;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$dimen;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PasswordManagerDialogMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mx1 implements View.OnLayoutChangeListener {
    public final bi1 e;
    public final View f;
    public final kj g;
    public final PropertyModel.b h;
    public PropertyModel i;
    public PropertyModel j;
    public Resources k;
    public int l;

    /* JADX INFO: compiled from: PasswordManagerDialogMediator.java */
    public static class a implements di1.a {
        public final Callback<Integer> e;

        public a(Callback<Integer> callback) {
            this.e = callback;
        }

        @Override // di1.a
        public final void b(int i) {
            this.e.onResult(Integer.valueOf(i));
        }

        @Override // di1.a
        public final void c(int i, PropertyModel propertyModel) {
            Callback<Integer> callback = this.e;
            if (i == 0) {
                callback.onResult(1);
            } else {
                if (i != 1) {
                    return;
                }
                callback.onResult(2);
            }
        }
    }

    public mx1(PropertyModel.b bVar, bi1 bi1Var, View view, fj fjVar) {
        this.e = bi1Var;
        this.h = bVar;
        this.f = view;
        this.g = fjVar;
        view.addOnLayoutChangeListener(this);
    }

    public final boolean a(int i) {
        Resources resources = this.k;
        if (resources == null) {
            return false;
        }
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.tab_modal_scrim_vertical_margin);
        kj kjVar = this.g;
        return (i - (kjVar.l() - dimensionPixelSize)) - kjVar.g() >= this.k.getDimensionPixelSize(R$dimen.password_manager_dialog_min_vertical_space_to_show_illustration);
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9;
        if (this.j == null || (i9 = i4 - i2) == i8 - i6) {
            return;
        }
        PostTask.c(rh3.a, new b50(i9, 7, this));
    }
}
