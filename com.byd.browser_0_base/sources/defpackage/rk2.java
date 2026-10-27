package defpackage;

import android.util.Log;
import defpackage.uk2;
import java.util.Collections;
import java.util.Set;
import org.chromium.base.CollectionUtil;
import org.chromium.base.task.PostTask;
import org.chromium.components.browser_ui.share.a;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ShareServiceImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rk2 implements ok2 {
    public static final Set<String> g = Collections.unmodifiableSet(CollectionUtil.e("avif", "bmp", "css", "csv", "ehtml", "flac", "gif", "htm", "html", "ico", "jfif", "jpeg", "jpg", "m4a", "m4v", "mp3", "mp4", "mpeg", "mpg", "oga", "ogg", "ogm", "ogv", "opus", "pdf", "pjp", "pjpeg", "png", "shtm", "shtml", "svg", "svgz", "text", "tif", "tiff", "txt", "wav", "weba", "webm", "webp", "xbm"));
    public static final Set<String> h = Collections.unmodifiableSet(CollectionUtil.e("audio/flac", "application/pdf", "audio/mp3", "audio/mpeg", "audio/ogg", "audio/wav", "audio/webm", "audio/x-m4a", "image/avif", "image/bmp", "image/gif", "image/jpeg", "image/png", "image/svg+xml", "image/tiff", "image/webp", "image/x-icon", "image/x-ms-bmp", "image/x-xbitmap", "text/comma-separated-values", "text/css", "text/csv", "text/html", "text/plain", "video/mp4", "video/mpeg", "video/ogg", "video/webm"));
    public static final oi2 i = PostTask.a(d73.j);
    public final WindowAndroid e;
    public final a f;

    /* JADX INFO: compiled from: ShareServiceImpl.java */
    public interface a {
    }

    public rk2(WebContents webContents, sk2 sk2Var) {
        this.e = webContents.V();
        this.f = sk2Var;
    }

    @Override // defpackage.ok2
    public final void r(String str, String str2, qj3 qj3Var, ml2[] ml2VarArr, uk2.f fVar) {
        oj2.U(0, 2, "WebShare.ApiCount");
        sk2 sk2Var = (sk2) this.f;
        if (!(sk2Var.a() != null && sk2Var.a.e.y().f(78))) {
            oj2.U(1, 3, "WebShare.ShareOutcome");
            fVar.c(1);
            return;
        }
        pk2 pk2Var = new pk2(fVar);
        a.C0124a c0124a = new a.C0124a(this.e, str, qj3Var.b);
        c0124a.c = str2;
        c0124a.g = pk2Var;
        if (ml2VarArr == null || ml2VarArr.length == 0) {
            sk2Var.a().c(c0124a.a(), new jr(false, false, false, null, null, false, false, null, 0), 3);
            return;
        }
        if (ml2VarArr.length > 10) {
            fVar.c(2);
            return;
        }
        for (ml2 ml2Var : ml2VarArr) {
            String str3 = ml2Var.b.b.b;
            if (!(str3.indexOf(46) <= 0 || !g.contains(pm0.a(str3)))) {
                if (!(!h.contains(ml2Var.c.c))) {
                }
            }
            Log.i("cr_share", "Cannot share potentially dangerous \"" + ml2Var.c.c + "\" file \"" + ml2Var.b + "\".");
            fVar.c(2);
            return;
        }
        new qk2(this, fVar, ml2VarArr, c0124a).d(i);
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
