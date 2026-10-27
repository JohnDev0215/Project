package defpackage;

import android.graphics.drawable.Drawable;
import android.util.IntProperty;

/* JADX INFO: compiled from: AnimatorProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x5 {
    public static final a a = new a();

    /* JADX INFO: compiled from: AnimatorProperties.java */
    public class a extends IntProperty<Drawable> {
        public a() {
            super("alpha");
        }

        @Override // android.util.Property
        public final Integer get(Object obj) {
            return Integer.valueOf(((Drawable) obj).getAlpha());
        }

        @Override // android.util.IntProperty
        public final void setValue(Drawable drawable, int i) {
            drawable.setAlpha(i);
        }
    }
}
