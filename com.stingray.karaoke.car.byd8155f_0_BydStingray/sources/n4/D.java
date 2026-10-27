package n4;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import h4.InterfaceC1359a;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class D implements e4.j {
    public static final e4.g d = new e4.g("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new j(2));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e4.g f19925e = new e4.g("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new j(3));

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final m6.b f19926f = new m6.b();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final List f19927g = Collections.unmodifiableList(Arrays.asList("TP1A", "TD1A.220804.031"));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C f19928a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC1359a f19929b;
    public final m6.b c = f19926f;

    public D(InterfaceC1359a interfaceC1359a, C c) {
        this.f19929b = interfaceC1359a;
        this.f19928a = c;
    }

    @Override // e4.j
    public final g4.x a(Object obj, int i7, int i10, e4.h hVar) throws IOException {
        long jLongValue = ((Long) hVar.c(d)).longValue();
        if (jLongValue < 0 && jLongValue != -1) {
            throw new IllegalArgumentException(M5.b.f("Requested frame must be non-negative, or DEFAULT_FRAME, given: ", jLongValue));
        }
        Integer num = (Integer) hVar.c(f19925e);
        if (num == null) {
            num = 2;
        }
        n nVar = (n) hVar.c(n.f19950g);
        if (nVar == null) {
            nVar = n.f19949f;
        }
        n nVar2 = nVar;
        this.c.getClass();
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            this.f19928a.q(mediaMetadataRetriever, obj);
            Bitmap bitmapC = c(obj, mediaMetadataRetriever, jLongValue, num.intValue(), i7, i10, nVar2);
            mediaMetadataRetriever.close();
            return C1958d.c(bitmapC, this.f19929b);
        } catch (Throwable th) {
            mediaMetadataRetriever.close();
            throw th;
        }
    }

    @Override // e4.j
    public final boolean b(Object obj, e4.h hVar) {
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0067 A[Catch: all -> 0x006d, TRY_LEAVE, TryCatch #3 {all -> 0x006d, blocks: (B:24:0x0061, B:26:0x0067), top: B:92:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0122 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0124 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0181 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0182  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.Bitmap c(java.lang.Object r14, android.media.MediaMetadataRetriever r15, long r16, int r18, int r19, int r20, n4.n r21) {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n4.D.c(java.lang.Object, android.media.MediaMetadataRetriever, long, int, int, int, n4.n):android.graphics.Bitmap");
    }
}
