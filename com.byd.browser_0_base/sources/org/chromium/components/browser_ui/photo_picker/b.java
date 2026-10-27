package org.chromium.components.browser_ui.photo_picker;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import defpackage.i9;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: DecodeVideoTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b extends i9<List<Bitmap>> {
    public final a h;
    public final Uri i;
    public final int j;
    public final boolean k;
    public final int l;
    public long m;
    public final ContentResolver n;
    public int o;
    public String p;
    public float q;

    /* JADX INFO: compiled from: DecodeVideoTask.java */
    public interface a {
    }

    public b(a aVar, ContentResolver contentResolver, Uri uri, int i, boolean z, int i2, long j) {
        this.h = aVar;
        this.n = contentResolver;
        this.i = uri;
        this.j = i;
        this.k = z;
        this.l = i2;
        this.m = j;
    }

    public static String o(Long l) {
        if (l == null) {
            return null;
        }
        long jLongValue = l.longValue() / 1000;
        long j = jLongValue / 3600;
        long j2 = jLongValue - (3600 * j);
        long j3 = j2 / 60;
        long j4 = j2 - (60 * j3);
        return j > 0 ? String.format(Locale.US, "%d:%02d:%02d", Long.valueOf(j), Long.valueOf(j3), Long.valueOf(j4)) : String.format(Locale.US, "%d:%02d", Long.valueOf(j3), Long.valueOf(j4));
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x00cb: MOVE (r2 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]) (LINE:204), block:B:56:0x00cb */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00ce A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00c5 A[EXC_TOP_SPLITTER, PHI: r5
  0x00c5: PHI (r5v5 android.media.MediaMetadataRetriever) = 
  (r5v0 android.media.MediaMetadataRetriever)
  (r5v1 android.media.MediaMetadataRetriever)
  (r5v2 android.media.MediaMetadataRetriever)
 binds: [B:48:0x00be, B:44:0x00b7, B:51:0x00c3] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    @Override // defpackage.i9
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<android.graphics.Bitmap> b() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.photo_picker.b.b():java.lang.Object");
    }

    @Override // defpackage.i9
    public final void l(List<Bitmap> list) {
        List<Bitmap> list2 = list;
        if (i()) {
            return;
        }
        a aVar = this.h;
        if (list2 == null) {
            ((c) aVar).D(this.i, null, "", this.k, this.o, 1.0f);
        } else {
            ((c) aVar).D(this.i, list2, this.p, this.k, this.o, this.q);
        }
    }
}
