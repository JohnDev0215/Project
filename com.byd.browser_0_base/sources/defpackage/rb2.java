package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ResourcesCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class rb2 {
    public static final ThreadLocal<TypedValue> a = new ThreadLocal<>();
    public static final WeakHashMap<b, SparseArray<a>> b = new WeakHashMap<>(0);
    public static final Object c = new Object();

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static class a {
        public final ColorStateList a;
        public final Configuration b;
        public final int c;

        public a(ColorStateList colorStateList, Configuration configuration, Resources.Theme theme) {
            this.a = colorStateList;
            this.b = configuration;
            this.c = theme == null ? 0 : theme.hashCode();
        }
    }

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static final class b {
        public final Resources a;
        public final Resources.Theme b;

        public b(Resources resources, Resources.Theme theme) {
            this.a = resources;
            this.b = theme;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return this.a.equals(bVar.a) && Objects.equals(this.b, bVar.b);
        }

        public final int hashCode() {
            return Objects.hash(this.a, this.b);
        }
    }

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static abstract class c {
        public final void a(int i) {
            new Handler(Looper.getMainLooper()).post(new sb2(i, 0, this));
        }

        public final void b(Typeface typeface) {
            new Handler(Looper.getMainLooper()).post(new l91(this, 2, typeface));
        }

        public abstract void c(int i);

        public abstract void d(Typeface typeface);
    }

    public static Typeface a(Context context, int i) {
        if (context.isRestricted()) {
            return null;
        }
        return c(context, i, new TypedValue(), 0, null, false, false);
    }

    public static void b(Context context, int i, c cVar) {
        if (context.isRestricted()) {
            cVar.a(-4);
        } else {
            c(context, i, new TypedValue(), 0, cVar, false, false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Typeface c(android.content.Context r15, int r16, android.util.TypedValue r17, int r18, rb2.c r19, boolean r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rb2.c(android.content.Context, int, android.util.TypedValue, int, rb2$c, boolean, boolean):android.graphics.Typeface");
    }
}
