package N3;

import A7.m;
import A9.k;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import c9.u;
import java.io.Closeable;
import java.util.ArrayList;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Bitmap.Config f4575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final u f4576b;

    static {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap.Config config2 = Bitmap.Config.RGBA_F16;
        f4575a = Bitmap.Config.HARDWARE;
        f4576b = new u((String[]) new ArrayList(20).toArray(new String[0]));
    }

    public static final void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (RuntimeException e10) {
            throw e10;
        } catch (Exception unused) {
        }
    }

    public static final String b(MimeTypeMap mimeTypeMap, String str) {
        if (str == null || Q8.h.M0(str)) {
            return null;
        }
        String strE1 = Q8.h.e1(Q8.h.e1(str, '#'), '?');
        return mimeTypeMap.getMimeTypeFromExtension(Q8.h.a1('.', Q8.h.a1('/', strE1, strE1), ""));
    }

    public static final boolean c(Uri uri) {
        return m.a(uri.getScheme(), "file") && m.a((String) AbstractC1998m.a0(uri.getPathSegments()), "android_asset");
    }

    public static final int d(com.bumptech.glide.d dVar, L3.f fVar) {
        if (dVar instanceof L3.a) {
            return ((L3.a) dVar).d;
        }
        int iOrdinal = fVar.ordinal();
        if (iOrdinal == 0) {
            return Integer.MIN_VALUE;
        }
        if (iOrdinal == 1) {
            return Integer.MAX_VALUE;
        }
        throw new k();
    }
}
