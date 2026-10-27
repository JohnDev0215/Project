package b.a.a.h;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.collection.LruCache;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LruCache<String, Drawable> f248a;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a extends LruCache<String, Drawable> {
        public a(int i) {
            super(i);
        }

        @Override // androidx.collection.LruCache
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int sizeOf(@NonNull String str, @NonNull Drawable drawable) {
            if (!(drawable instanceof BitmapDrawable)) {
                return super.sizeOf(str, drawable);
            }
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap == null) {
                return 0;
            }
            return bitmap.getByteCount();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static f f250a = new f(null);
    }

    public /* synthetic */ f(a aVar) {
        this();
    }

    public static f d() {
        return b.f250a;
    }

    public void a(String str, Drawable drawable) {
        if (c(str) != null || drawable == null) {
            return;
        }
        this.f248a.put(str, drawable);
    }

    public void b() {
        this.f248a.evictAll();
    }

    public Drawable c(String str) {
        return this.f248a.get(str);
    }

    public f() {
        this.f248a = new a(((int) Runtime.getRuntime().maxMemory()) / 8);
    }
}
