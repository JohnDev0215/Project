package defpackage;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: UiConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qh3 {
    public final Context c;
    public final ArrayList b = new ArrayList();
    public b a = b();

    /* JADX INFO: compiled from: UiConfig.java */
    public static final class b {
        public final int a;
        public final int b;

        public b(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return this.a == bVar.a && this.b == bVar.b;
        }

        public final int hashCode() {
            return (this.a * 31) + this.b;
        }
    }

    public qh3(View view) {
        this.c = view.getContext();
        view.addOnAttachStateChangeListener(new a());
    }

    public final void a(db0 db0Var) {
        this.b.add(db0Var);
        db0Var.d(this.a);
    }

    public final b b() {
        Context context = this.c;
        int i = context.getResources().getConfiguration().screenWidthDp;
        return new b(i <= 320 ? 0 : i >= 600 ? 2 : 1, context.getResources().getConfiguration().screenHeightDp <= 320 ? 0 : 1);
    }

    public final void c() {
        b bVarB = b();
        this.a = bVarB;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((db0) it.next()).d(bVarB);
        }
    }

    /* JADX INFO: compiled from: UiConfig.java */
    public class a implements View.OnAttachStateChangeListener {
        public a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            qh3.this.c();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
        }
    }
}
