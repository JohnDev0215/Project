package com.bumptech.glide.load.n.b0;

import android.content.Context;
import com.bumptech.glide.load.n.b0.d;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class f extends d {

    class a implements d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f2073a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f2074b;

        a(Context context, String str) {
            this.f2073a = context;
            this.f2074b = str;
        }

        @Override // com.bumptech.glide.load.n.b0.d.a
        public File a() {
            File cacheDir = this.f2073a.getCacheDir();
            if (cacheDir == null) {
                return null;
            }
            return this.f2074b != null ? new File(cacheDir, this.f2074b) : cacheDir;
        }
    }

    public f(Context context) {
        this(context, "image_manager_disk_cache", 262144000L);
    }

    public f(Context context, String str, long j) {
        super(new a(context, str), j);
    }
}
