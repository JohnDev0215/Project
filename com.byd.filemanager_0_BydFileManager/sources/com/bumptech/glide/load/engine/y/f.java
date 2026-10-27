package com.bumptech.glide.load.engine.y;

import android.content.Context;
import com.bumptech.glide.load.engine.y.d;
import java.io.File;

/* JADX INFO: compiled from: InternalCacheDiskCacheFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends d {

    /* JADX INFO: compiled from: InternalCacheDiskCacheFactory.java */
    class a implements d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f3138a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3139b;

        a(Context context, String str) {
            this.f3138a = context;
            this.f3139b = str;
        }

        @Override // com.bumptech.glide.load.engine.y.d.a
        public File a() {
            File cacheDir = this.f3138a.getCacheDir();
            if (cacheDir == null) {
                return null;
            }
            return this.f3139b != null ? new File(cacheDir, this.f3139b) : cacheDir;
        }
    }

    public f(Context context) {
        this(context, "image_manager_disk_cache", 262144000L);
    }

    public f(Context context, String str, long j) {
        super(new a(context, str), j);
    }
}
