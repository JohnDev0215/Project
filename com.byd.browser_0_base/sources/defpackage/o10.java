package defpackage;

import android.content.Context;
import defpackage.wp2;
import java.util.LinkedList;

/* JADX INFO: compiled from: CreatorSnackbarController.java */
/* JADX INFO: loaded from: classes.dex */
public final class o10 {
    public final Context a;
    public final wp2 b;
    public final a c = new a();

    /* JADX INFO: compiled from: CreatorSnackbarController.java */
    public class a implements wp2.b {
        public a() {
        }

        @Override // wp2.b
        public final void j(Object obj) {
            wp2 wp2Var = o10.this.b;
            up2 up2Var = wp2Var.h;
            boolean zIsEmpty = up2Var.a.isEmpty();
            LinkedList linkedList = up2Var.b;
            if (zIsEmpty && linkedList.isEmpty()) {
                return;
            }
            while (true) {
                if (up2Var.a.isEmpty() && linkedList.isEmpty()) {
                    wp2Var.e();
                    return;
                }
                up2Var.b(false);
            }
        }
    }

    public o10(Context context, wp2 wp2Var) {
        this.a = context;
        this.b = wp2Var;
    }
}
