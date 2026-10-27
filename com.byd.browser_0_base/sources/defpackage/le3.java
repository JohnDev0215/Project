package defpackage;

import android.graphics.Bitmap;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.touch_to_fill.data.Credential;
import org.chromium.chrome.browser.touch_to_fill.data.WebAuthnCredential;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TouchToFillProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class le3 {
    public static final PropertyModel.n a = new PropertyModel.n("visible");
    public static final PropertyModel.l<p81<xa1>> b = new PropertyModel.l<>("sheet_items");
    public static final PropertyModel.l<Callback<Integer>> c = new PropertyModel.l<>("dismiss_handler");

    /* JADX INFO: compiled from: TouchToFillProperties.java */
    public static class a {
        public static final PropertyModel.r<b> a;
        public static final PropertyModel.l<Credential> b;
        public static final PropertyModel.l<String> c;
        public static final PropertyModel.l<Boolean> d;
        public static final PropertyModel.l<Callback<Credential>> e;
        public static final v62[] f;

        static {
            PropertyModel.r<b> rVar = new PropertyModel.r<>("favicon", false);
            a = rVar;
            PropertyModel.l<Credential> lVar = new PropertyModel.l<>("credential");
            b = lVar;
            PropertyModel.l<String> lVar2 = new PropertyModel.l<>("formatted_url");
            c = lVar2;
            PropertyModel.l<Boolean> lVar3 = new PropertyModel.l<>("submit_credential");
            d = lVar3;
            PropertyModel.l<Callback<Credential>> lVar4 = new PropertyModel.l<>("on_click_listener");
            e = lVar4;
            f = new v62[]{rVar, lVar, lVar2, lVar4, lVar3};
        }
    }

    /* JADX INFO: compiled from: TouchToFillProperties.java */
    public static class b {
        public final String a;
        public final Bitmap b;
        public final int c;
        public final int d;

        public b(String str, Bitmap bitmap, int i, int i2) {
            this.a = str;
            this.b = bitmap;
            this.c = i;
            this.d = i2;
        }
    }

    /* JADX INFO: compiled from: TouchToFillProperties.java */
    public static class c {
        public static final PropertyModel.r<Runnable> a;
        public static final PropertyModel.r<String> b;
        public static final v62[] c;

        static {
            PropertyModel.r<Runnable> rVar = new PropertyModel.r<>("on_click_manage", false);
            a = rVar;
            PropertyModel.r<String> rVar2 = new PropertyModel.r<>("manage_button_text", false);
            b = rVar2;
            c = new v62[]{rVar, rVar2};
        }
    }

    /* JADX INFO: compiled from: TouchToFillProperties.java */
    public static class d {
        public static final PropertyModel.h a;
        public static final PropertyModel.l<String> b;
        public static final PropertyModel.h c;
        public static final PropertyModel.j d;
        public static final PropertyModel.l<String> e;
        public static final v62[] f;

        static {
            PropertyModel.h hVar = new PropertyModel.h("submit_credential");
            a = hVar;
            PropertyModel.l<String> lVar = new PropertyModel.l<>("formatted_url");
            b = lVar;
            PropertyModel.h hVar2 = new PropertyModel.h("origin_secure");
            c = hVar2;
            PropertyModel.j jVar = new PropertyModel.j("image_drawable_id");
            d = jVar;
            PropertyModel.l<String> lVar2 = new PropertyModel.l<>("title");
            e = lVar2;
            f = new v62[]{hVar, lVar, hVar2, jVar, lVar2};
        }
    }

    /* JADX INFO: compiled from: TouchToFillProperties.java */
    public static class e {
        public static final PropertyModel.r<b> a;
        public static final PropertyModel.l<WebAuthnCredential> b;
        public static final PropertyModel.l<Boolean> c;
        public static final PropertyModel.l<Callback<WebAuthnCredential>> d;
        public static final v62[] e;

        static {
            PropertyModel.r<b> rVar = new PropertyModel.r<>("favicon", false);
            a = rVar;
            PropertyModel.l<WebAuthnCredential> lVar = new PropertyModel.l<>("webauthn_credential");
            b = lVar;
            PropertyModel.l<Boolean> lVar2 = new PropertyModel.l<>("submit_credential");
            c = lVar2;
            PropertyModel.l<Callback<WebAuthnCredential>> lVar3 = new PropertyModel.l<>("on_webauthn_click_listener");
            d = lVar3;
            e = new v62[]{lVar, rVar, lVar3, lVar2};
        }
    }
}
