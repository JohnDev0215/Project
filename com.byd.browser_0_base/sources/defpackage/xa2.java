package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ScrollView;
import defpackage.di1;
import org.chromium.base.Callback;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: RenameExtensionDialogCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class xa2 {
    public final bi1 a;
    public final PropertyModel b;
    public final Callback<Boolean> c;
    public final Callback<Integer> d;

    /* JADX INFO: compiled from: RenameExtensionDialogCoordinator.java */
    public class a implements di1.a {
        public a() {
        }

        @Override // di1.a
        public final void b(int i) {
            xa2.this.d.onResult(Integer.valueOf(i));
        }

        @Override // di1.a
        public final void c(int i, PropertyModel propertyModel) {
            xa2 xa2Var = xa2.this;
            if (i == 0) {
                xa2Var.c.onResult(Boolean.TRUE);
            } else {
                if (i != 1) {
                    return;
                }
                xa2Var.c.onResult(Boolean.FALSE);
            }
        }
    }

    public xa2(Context context, bi1 bi1Var, wb wbVar, c3 c3Var) {
        this.a = bi1Var;
        ScrollView scrollView = (ScrollView) LayoutInflater.from(context).inflate(R$layout.download_rename_extension_custom_dialog, (ViewGroup) null);
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, new a());
        bVar.e(di1.c, context.getString(R$string.rename_extension_confirmation));
        bVar.e(di1.h, scrollView);
        bVar.d(di1.j, context.getResources(), R$string.confirm);
        bVar.d(di1.m, context.getResources(), R$string.cancel);
        this.b = bVar.a();
        this.c = wbVar;
        this.d = c3Var;
    }
}
