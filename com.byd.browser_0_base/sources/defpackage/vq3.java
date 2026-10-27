package defpackage;

import defpackage.di1;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: WebFeedDialogMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class vq3 {
    public final bi1 a;
    public PropertyModel b;

    /* JADX INFO: compiled from: WebFeedDialogMediator.java */
    public class a implements di1.a {
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
            vq3 vq3Var = vq3.this;
            if (i == 0) {
                vq3Var.a.b(1, vq3Var.b);
            } else {
                if (i != 1) {
                    return;
                }
                vq3Var.a.b(2, vq3Var.b);
            }
        }
    }

    public vq3(bi1 bi1Var) {
        this.a = bi1Var;
    }
}
