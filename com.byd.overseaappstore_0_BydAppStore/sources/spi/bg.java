package spi;

import android.content.Context;
import java.io.File;
import spi.n6;

/* JADX INFO: loaded from: classes.dex */
public final class bg extends n6 {

    public class a implements n6.c {
        public final /* synthetic */ Context a;
        public final /* synthetic */ String b;

        public a(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        @Override // spi.n6.c
        public final File a() {
            File cacheDir = this.a.getCacheDir();
            if (cacheDir == null) {
                return null;
            }
            return this.b != null ? new File(cacheDir, this.b) : cacheDir;
        }
    }

    public bg(Context context) {
        this(context, "image_manager_disk_cache", 262144000L);
    }

    public bg(Context context, long j) {
        this(context, "image_manager_disk_cache", j);
    }

    public bg(Context context, String str, long j) {
        super(new a(context, str), j);
    }
}
