package n4;

import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: renamed from: n4.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1956b implements e4.k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final e4.g f19932m = e4.g.a(90, "com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final e4.g f19933n = new e4.g("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat", null, e4.g.f14431e);
    public final F.l l;

    public C1956b(F.l lVar) {
        this.l = lVar;
    }

    @Override // e4.InterfaceC1226b
    public final boolean q(Object obj, File file, e4.h hVar) {
        boolean z6;
        Bitmap bitmap = (Bitmap) ((g4.x) obj).get();
        e4.g gVar = f19933n;
        Bitmap.CompressFormat compressFormat = (Bitmap.CompressFormat) hVar.c(gVar);
        if (compressFormat == null) {
            compressFormat = bitmap.hasAlpha() ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;
        }
        bitmap.getWidth();
        bitmap.getHeight();
        int i7 = A4.k.f423b;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        int iIntValue = ((Integer) hVar.c(f19932m)).intValue();
        OutputStream cVar = null;
        cVar = null;
        try {
            try {
                io.sentry.instrumentation.file.g gVarM = h6.m.m(new FileOutputStream(file), file);
                F.l lVar = this.l;
                cVar = gVarM;
                if (lVar != null) {
                    cVar = new com.bumptech.glide.load.data.c(gVarM, lVar);
                }
                bitmap.compress(compressFormat, iIntValue, cVar);
                cVar.close();
                try {
                    cVar.close();
                } catch (IOException unused) {
                }
                z6 = true;
            } catch (IOException e10) {
                if (Log.isLoggable("BitmapEncoder", 3)) {
                    Log.d("BitmapEncoder", "Failed to encode Bitmap", e10);
                }
                if (cVar != null) {
                    try {
                        cVar.close();
                    } catch (IOException unused2) {
                    }
                }
                z6 = false;
            }
            if (Log.isLoggable("BitmapEncoder", 2)) {
                Log.v("BitmapEncoder", "Compressed with type: " + compressFormat + " of size " + A4.q.c(bitmap) + " in " + A4.k.a(jElapsedRealtimeNanos) + ", options format: " + hVar.c(gVar) + ", hasAlpha: " + bitmap.hasAlpha());
            }
            return z6;
        } catch (Throwable th) {
            if (cVar != null) {
                try {
                    cVar.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    @Override // e4.k
    public final int t(e4.h hVar) {
        return 2;
    }
}
