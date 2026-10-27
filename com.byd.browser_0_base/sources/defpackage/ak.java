package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: ButtonData.java */
/* JADX INFO: loaded from: classes2.dex */
public interface ak {

    /* JADX INFO: compiled from: ButtonData.java */
    public static final class a {
        public final Drawable a;
        public final View.OnClickListener b;
        public final View.OnLongClickListener c;
        public final String d;
        public final boolean e;
        public final hw0 f;
        public final int g;
        public final boolean h;
        public final int i;

        public a(Drawable drawable, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener, String str, boolean z, hw0 hw0Var, int i, int i2) {
            this.a = drawable;
            this.b = onClickListener;
            this.c = onLongClickListener;
            this.d = str;
            this.e = z;
            this.f = hw0Var;
            this.g = i;
            this.h = i == 6 || i == 7;
            this.i = i2;
        }
    }

    boolean a();

    a b();

    boolean isEnabled();
}
