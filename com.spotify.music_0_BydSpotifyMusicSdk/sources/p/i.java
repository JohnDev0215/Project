package p;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.MimeTypeMap;
import coil.size.Scale;
import com.spotify.music.R;
import java.io.Closeable;
import java.io.File;
import kotlin.NoWhenBranchMatchedException;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {
    public static final Bitmap.Config[] a = {Bitmap.Config.ARGB_8888, Bitmap.Config.RGBA_F16};
    public static final Bitmap.Config b = Bitmap.Config.HARDWARE;
    public static final Headers c = new Headers.Builder().e();

    public static final void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }

    public static final String b(MimeTypeMap mimeTypeMap, String str) {
        if (str == null || ng2.c0(str)) {
            return null;
        }
        String strF0 = kotlin.text.b.F0(kotlin.text.b.F0(str, '#'), '?');
        return mimeTypeMap.getMimeTypeFromExtension(kotlin.text.b.D0(kotlin.text.b.D0(strF0, '/', strF0), '.', ""));
    }

    public static final av2 c(View view) {
        Object tag = view.getTag(R.id.coil_request_manager);
        av2 av2Var = tag instanceof av2 ? (av2) tag : null;
        if (av2Var == null) {
            synchronized (view) {
                try {
                    Object tag2 = view.getTag(R.id.coil_request_manager);
                    av2 av2Var2 = tag2 instanceof av2 ? (av2) tag2 : null;
                    if (av2Var2 != null) {
                        av2Var = av2Var2;
                    } else {
                        av2Var = new av2(view);
                        view.addOnAttachStateChangeListener(av2Var);
                        view.setTag(R.id.coil_request_manager, av2Var);
                    }
                } finally {
                }
            }
        }
        return av2Var;
    }

    public static final File d(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            throw new IllegalStateException("cacheDir == null".toString());
        }
        cacheDir.mkdirs();
        return cacheDir;
    }

    public static final boolean e(Uri uri) {
        return v03.b(uri.getScheme(), "file") && v03.b((String) ou.d0(uri.getPathSegments()), "android_asset");
    }

    public static final int f(ci1 ci1Var, Scale scale) {
        if (ci1Var instanceof t90) {
            return ((t90) ci1Var).f187p;
        }
        int iOrdinal = scale.ordinal();
        if (iOrdinal == 0) {
            return Integer.MIN_VALUE;
        }
        if (iOrdinal == 1) {
            return Integer.MAX_VALUE;
        }
        throw new NoWhenBranchMatchedException();
    }
}
