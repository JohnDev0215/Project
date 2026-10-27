package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import defpackage.ax0;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: CachedImageFetcher.java */
/* JADX INFO: loaded from: classes2.dex */
public final class al extends ax0 {
    public final a b;

    /* JADX INFO: compiled from: CachedImageFetcher.java */
    public static class a {
    }

    public al(cx0 cx0Var, a aVar) {
        super(cx0Var);
        this.b = aVar;
    }

    @Override // defpackage.ax0
    public final void c(final ax0.a aVar, final Callback<od> callback) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        PostTask.c(d73.h, new Runnable() { // from class: zk
            /* JADX WARN: Not initialized variable reg: 8, insn: 0x005a: MOVE (r0 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]) (LINE:91), block:B:20:0x005a */
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                FileInputStream fileInputStream;
                Closeable closeable;
                od odVar;
                int length;
                byte[] bArr;
                Callback callback2 = callback;
                long j = jCurrentTimeMillis;
                al alVar = this;
                alVar.getClass();
                ax0.a aVar2 = aVar;
                String strMDhGn9Di = N.MDhGn9Di(alVar.a.a, aVar2.a);
                alVar.b.getClass();
                Closeable closeable2 = null;
                try {
                    try {
                        length = (int) new File(strMDhGn9Di).length();
                        bArr = new byte[length];
                        fileInputStream = new FileInputStream(strMDhGn9Di);
                        try {
                        } catch (IOException e) {
                            e = e;
                            fa1.l("CachedImageFetcher", "Failed to read: %s", strMDhGn9Di, e);
                        }
                    } catch (Throwable th) {
                        th = th;
                        closeable2 = closeable;
                        a8.s(closeable2);
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                    fileInputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    a8.s(closeable2);
                    throw th;
                }
                if (fileInputStream.read(bArr) == length) {
                    odVar = new od(bArr);
                    a8.s(fileInputStream);
                    PostTask.c(rh3.c, new vk(j, odVar, callback2, alVar, aVar2));
                }
                a8.s(fileInputStream);
                odVar = null;
                PostTask.c(rh3.c, new vk(j, odVar, callback2, alVar, aVar2));
            }
        });
    }

    @Override // defpackage.ax0
    public final void d(final ax0.a aVar, final Callback<Bitmap> callback) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        PostTask.c(d73.h, new Runnable() { // from class: xk
            @Override // java.lang.Runnable
            public final void run() {
                final Callback callback2 = callback;
                final long j = jCurrentTimeMillis;
                final al alVar = this;
                alVar.getClass();
                final ax0.a aVar2 = aVar;
                String strMDhGn9Di = N.MDhGn9Di(alVar.a.a, aVar2.a);
                alVar.b.getClass();
                final Bitmap bitmapDecodeFile = new File(strMDhGn9Di).exists() ? BitmapFactory.decodeFile(strMDhGn9Di, null) : null;
                PostTask.c(rh3.c, new Runnable() { // from class: yk
                    @Override // java.lang.Runnable
                    public final void run() {
                        int i;
                        int i2;
                        ax0.a aVar3 = aVar2;
                        Callback callback3 = callback2;
                        long j2 = j;
                        al alVar2 = alVar;
                        cx0 cx0Var = alVar2.a;
                        Bitmap bitmapExtractThumbnail = bitmapDecodeFile;
                        if (bitmapExtractThumbnail == null) {
                            N.M3LHmG_m(cx0Var.a, 1, aVar3.a, aVar3.b, aVar3.c, aVar3.d, aVar3.f, new p62(aVar3, 16, new xo1(j2, callback3, alVar2, aVar3)));
                            return;
                        }
                        if (aVar3.e && (i = aVar3.c) > 0 && (i2 = aVar3.d) > 0 && bitmapExtractThumbnail.getWidth() != i && bitmapExtractThumbnail.getHeight() != i2) {
                            bitmapExtractThumbnail = ThumbnailUtils.extractThumbnail(bitmapExtractThumbnail, i, i2, 2);
                        }
                        callback3.onResult(bitmapExtractThumbnail);
                        alVar2.a.getClass();
                        String str = aVar3.b;
                        N.Mlt0uwKm(str, 9);
                        cx0Var.getClass();
                        N.MNxzlAnV(str, j2);
                    }
                });
            }
        });
    }

    @Override // defpackage.ax0
    public final int e() {
        return 1;
    }

    @Override // defpackage.ax0
    public final void a() {
    }

    @Override // defpackage.ax0
    public final void b() {
    }
}
