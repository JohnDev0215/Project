package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import defpackage.di1;
import org.chromium.base.Callback;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.home.rename.RenameDialogCustomView;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: RenameDialogCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class ta2 {
    public final bi1 a;
    public final PropertyModel b;
    public final RenameDialogCustomView c;
    public final Callback<Boolean> d;
    public final Callback<Integer> e;

    /* JADX INFO: compiled from: RenameDialogCoordinator.java */
    public class a implements di1.a {
        public a() {
        }

        @Override // di1.a
        public final void b(int i) {
            ta2.this.e.onResult(Integer.valueOf(i));
        }

        @Override // di1.a
        public final void c(int i, PropertyModel propertyModel) {
            ta2 ta2Var = ta2.this;
            if (i == 0) {
                ta2Var.d.onResult(Boolean.TRUE);
            } else {
                if (i != 1) {
                    return;
                }
                ta2Var.d.onResult(Boolean.FALSE);
            }
        }
    }

    public ta2(Context context, bi1 bi1Var, va2 va2Var, so soVar) {
        this.a = bi1Var;
        RenameDialogCustomView renameDialogCustomView = (RenameDialogCustomView) LayoutInflater.from(context).inflate(R$layout.download_rename_custom_dialog, (ViewGroup) null);
        this.c = renameDialogCustomView;
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, new a());
        bVar.e(di1.c, context.getString(R$string.rename));
        bVar.e(di1.h, renameDialogCustomView);
        bVar.d(di1.j, context.getResources(), R$string.ok);
        bVar.d(di1.m, context.getResources(), R$string.cancel);
        this.b = bVar.a();
        this.d = va2Var;
        this.e = soVar;
        renameDialogCustomView.setEmptyInputObserver(new so(27, this));
    }
}
