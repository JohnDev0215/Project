package org.chromium.components.favicon;

import android.graphics.Bitmap;
import android.util.LruCache;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: LargeIconBridge.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends LruCache<GURL, LargeIconBridge.b> {
    public a(int i) {
        super(i);
    }

    @Override // android.util.LruCache
    public final int sizeOf(GURL gurl, LargeIconBridge.b bVar) {
        Bitmap bitmap = bVar.a;
        return Math.max(1024, bitmap == null ? 0 : bitmap.getByteCount());
    }
}
