package defpackage;

import android.content.Context;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SkinCompatDrawableManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ko2 {
    public static ko2 c;
    public static final a d = new a();
    public final Object a = new Object();
    public final WeakHashMap<Context, sa1<WeakReference<Drawable.ConstantState>>> b = new WeakHashMap<>(0);

    /* JADX INFO: compiled from: SkinCompatDrawableManager.java */
    public static class a extends ua1<Integer, PorterDuffColorFilter> {
        public a() {
            super(6);
        }
    }
}
