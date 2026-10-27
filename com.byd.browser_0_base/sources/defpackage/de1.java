package defpackage;

import J.N;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseArray;
import defpackage.ed1;
import defpackage.gd1;
import java.util.Collections;
import java.util.Set;
import org.chromium.base.SysUtils;
import org.chromium.chrome.R$id;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: MediaSessionHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class de1 implements cd1 {
    public final c a;
    public WebContents b;
    public b c;
    public fe1 d;
    public final dd1 e;
    public Bitmap f;
    public Bitmap g;
    public Bitmap h;
    public String i;
    public final int j;
    public gd1.a k;
    public String l;
    public boolean m;
    public MediaMetadata n;
    public MediaMetadata o;
    public MediaPosition q;
    public final Handler r;
    public Runnable s;
    public LargeIconBridge t;
    public Set<Integer> p = Collections.emptySet();
    public final a u = new a();

    /* JADX INFO: compiled from: MediaSessionHelper.java */
    public class a implements hd1 {
        public a() {
        }
    }

    /* JADX INFO: compiled from: MediaSessionHelper.java */
    public class b extends rq3 {
        public final /* synthetic */ WebContents f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(WebContents webContents, WebContents webContents2) {
            super(webContents);
            this.f = webContents2;
        }

        @Override // defpackage.rq3
        public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
            if (!navigationHandle.g || navigationHandle.c) {
                return;
            }
            String strMeroQv$e = N.MeroQv$e(this.f.s().f().j());
            de1 de1Var = de1.this;
            de1Var.i = strMeroQv$e;
            de1Var.g = null;
            de1Var.f = null;
            de1Var.n = null;
            de1Var.o = de1Var.e();
            de1Var.p = Collections.emptySet();
            if (de1Var.g()) {
                return;
            }
            gd1.a aVar = de1Var.k;
            aVar.c = de1Var.i;
            aVar.g = de1Var.g;
            aVar.i = de1Var.f;
            aVar.a = de1Var.o;
            aVar.n = de1Var.p;
            de1Var.i();
        }

        @Override // defpackage.rq3
        public final void titleWasSet(String str) {
            de1 de1Var = de1.this;
            de1Var.getClass();
            String strTrim = str.trim();
            if (strTrim.startsWith("▶")) {
                strTrim = strTrim.substring(1).trim();
            }
            if (TextUtils.equals(de1Var.l, strTrim)) {
                return;
            }
            de1Var.l = strTrim;
            de1.a(de1Var);
        }

        @Override // defpackage.rq3
        public final void wasShown() {
            int id = ((le1) de1.this.a).a.getId();
            ed1 ed1Var = id1.a.get(R$id.media_playback_notification);
            if (ed1Var == null) {
                return;
            }
            ed1Var.a(id);
        }
    }

    /* JADX INFO: compiled from: MediaSessionHelper.java */
    public interface c {
    }

    public de1(WebContents webContents, c cVar) {
        this.j = Integer.MIN_VALUE;
        this.a = cVar;
        this.e = new dd1(SysUtils.isLowEndDevice() ? 256 : 512);
        this.r = new Handler();
        h(webContents);
        Activity activityD = d();
        if (activityD != null) {
            this.j = activityD.getVolumeControlStream();
        }
    }

    public static void a(de1 de1Var) {
        if (de1Var.g()) {
            return;
        }
        MediaMetadata mediaMetadataE = de1Var.e();
        if (de1Var.o.equals(mediaMetadataE)) {
            return;
        }
        de1Var.o = mediaMetadataE;
        de1Var.k.a = mediaMetadataE;
        de1Var.i();
    }

    public static Integer c(int i) {
        if (i == 1000) {
            return 0;
        }
        if (i == 1001) {
            return 1;
        }
        return i == 1002 ? 2 : null;
    }

    public final void b() {
        fe1 fe1Var = this.d;
        if (fe1Var == null) {
            return;
        }
        MediaSessionImpl mediaSessionImpl = fe1Var.a;
        if (mediaSessionImpl != null) {
            mediaSessionImpl.l.c(fe1Var);
            fe1Var.a = null;
        }
        this.d = null;
        this.p = Collections.emptySet();
    }

    public final Activity d() {
        WindowAndroid windowAndroidV = this.b.V();
        if (windowAndroidV == null) {
            return null;
        }
        return (Activity) windowAndroidV.k().get();
    }

    public final MediaMetadata e() {
        String str;
        String str2;
        String str3 = this.l;
        MediaMetadata mediaMetadata = this.n;
        if (mediaMetadata == null) {
            str = "";
            str2 = "";
        } else {
            if (!TextUtils.isEmpty(mediaMetadata.a)) {
                return this.n;
            }
            MediaMetadata mediaMetadata2 = this.n;
            str = mediaMetadata2.b;
            str2 = mediaMetadata2.c;
        }
        MediaMetadata mediaMetadata3 = this.o;
        return (mediaMetadata3 != null && TextUtils.equals(str3, mediaMetadata3.a) && TextUtils.equals(str, this.o.b) && TextUtils.equals(str2, this.o.c)) ? this.o : new MediaMetadata(str3, str, str2);
    }

    public final void f() {
        gd1 gd1Var;
        int id = ((le1) this.a).a.getId();
        ed1 ed1Var = id1.a.get(R$id.media_playback_notification);
        if (ed1Var != null && (gd1Var = ed1Var.e) != null && id == gd1Var.e) {
            ed1Var.b();
        }
        Activity activityD = d();
        if (activityD != null) {
            activityD.setVolumeControlStream(this.j);
        }
    }

    public final boolean g() {
        return this.k == null;
    }

    public final void h(WebContents webContents) {
        if (this.b == webContents) {
            return;
        }
        this.b = webContents;
        b bVar = this.c;
        if (bVar != null) {
            bVar.destroy();
        }
        this.c = new b(webContents, webContents);
        MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) N.Mtun$qW8(webContents);
        fe1 fe1Var = this.d;
        if (fe1Var == null || mediaSessionImpl != fe1Var.a) {
            b();
            dd1 dd1Var = this.e;
            dd1Var.a = webContents;
            dd1Var.c = -1;
            dd1Var.d = null;
            if (mediaSessionImpl != null) {
                this.d = new fe1(this, mediaSessionImpl);
            }
        }
    }

    public final void i() {
        Runnable runnable = this.s;
        if (runnable != null) {
            this.r.removeCallbacks(runnable);
            this.s = null;
        }
        gd1.a aVar = this.k;
        MediaMetadata mediaMetadata = aVar.a;
        boolean z = aVar.b;
        String str = aVar.c;
        int i = aVar.d;
        boolean z2 = aVar.e;
        int i2 = aVar.f;
        Bitmap bitmap = aVar.g;
        int i3 = aVar.h;
        Bitmap bitmap2 = aVar.i;
        int i4 = aVar.j;
        int i5 = aVar.k;
        gd1 gd1Var = new gd1(mediaMetadata, z, str, i, z2, i2, bitmap, i3, bitmap2, i4, i5, aVar.l, aVar.m, aVar.n, aVar.o);
        ((le1) this.a).getClass();
        yo yoVar = new yo(9, gd1Var);
        SparseArray<ed1> sparseArray = id1.a;
        ed1 ed1Var = sparseArray.get(i5);
        if (ed1Var == null) {
            ed1Var = new ed1((ed1.b) yoVar.get());
            sparseArray.put(i5, ed1Var);
        }
        ed1.d dVar = ed1Var.g;
        gd1 gd1Var2 = dVar.d;
        if (gd1Var2 == null) {
            gd1Var2 = dVar.a.e;
        }
        if (ed1.g(gd1Var2, gd1Var)) {
            return;
        }
        if (dVar.c == null) {
            dVar.a(gd1Var);
        } else {
            dVar.d = gd1Var;
        }
    }

    public final void j(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        this.m = true;
        if (g() || this.f != null || SysUtils.isLowEndDevice()) {
            return;
        }
        if (bitmap.getWidth() >= 114 && bitmap.getHeight() >= 114) {
            if (this.g == null || (bitmap.getWidth() >= this.g.getWidth() && bitmap.getHeight() >= this.g.getHeight())) {
                Bitmap bitmapA = fd1.a(bitmap);
                this.g = bitmapA;
                if (this.h == bitmapA) {
                    return;
                }
                this.h = bitmapA;
                if (g()) {
                    return;
                }
                gd1.a aVar = this.k;
                aVar.g = this.h;
                aVar.i = this.f;
                i();
            }
        }
    }
}
