package com.netinsight.sye.syeClient.timeshift;

import a.b.a.a.k.e.b;
import android.graphics.Bitmap;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\t\u001a\u00020\u00068&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\r\u001a\u00020\n8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0013\u001a\u00020\u00068&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\bR\u0016\u0010\u0017\u001a\u00020\u00148&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u001b\u001a\u00020\u00188&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lcom/netinsight/sye/syeClient/timeshift/ISyeThumbnail;", "", "", "getData", "()[B", "data", "", "getWidth", "()I", "width", "La/b/a/a/k/e/b;", "getCodec", "()La/b/a/a/k/e/b;", "codec", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "bitmap", "getHeight", "height", "", "getMimeType", "()Ljava/lang/String;", "mimeType", "", "getServerUtcTimeMillis", "()J", "serverUtcTimeMillis", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyeThumbnail {
    Bitmap getBitmap();

    b getCodec();

    byte[] getData();

    int getHeight();

    String getMimeType();

    long getServerUtcTimeMillis();

    int getWidth();
}
