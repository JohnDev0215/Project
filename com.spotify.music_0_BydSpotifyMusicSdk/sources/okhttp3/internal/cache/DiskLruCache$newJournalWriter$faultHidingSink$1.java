package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.jvm.internal.Lambda;
import okhttp3.internal.Util;
import p.lq2;
import p.ry0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class DiskLruCache$newJournalWriter$faultHidingSink$1 extends Lambda implements ry0 {
    final /* synthetic */ DiskLruCache this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiskLruCache$newJournalWriter$faultHidingSink$1(DiskLruCache diskLruCache) {
        super(1);
        this.this$0 = diskLruCache;
    }

    @Override // p.ry0
    public final Object invoke(Object obj) {
        v03.j((IOException) obj, "it");
        DiskLruCache diskLruCache = this.this$0;
        byte[] bArr = Util.a;
        diskLruCache.u = true;
        return lq2.a;
    }
}
