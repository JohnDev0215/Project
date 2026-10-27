package s1;

import android.content.Context;
import java.io.File;
import s1.d;

/* JADX INFO: compiled from: InternalCacheDiskCacheFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends d {

    /* JADX INFO: compiled from: InternalCacheDiskCacheFactory.java */
    class a implements d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f9476a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f9477b;

        a(Context context, String str) {
            this.f9476a = context;
            this.f9477b = str;
        }

        @Override // s1.d.a
        public File a() {
            File cacheDir = this.f9476a.getCacheDir();
            if (cacheDir == null) {
                return null;
            }
            return this.f9477b != null ? new File(cacheDir, this.f9477b) : cacheDir;
        }
    }

    public f(Context context) {
        this(context, "image_manager_disk_cache", 262144000L);
    }

    public f(Context context, String str, long j7) {
        super(new a(context, str), j7);
    }
}
