package defpackage;

import J.N;
import android.content.res.Resources;
import defpackage.wp2;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.commerce.core.a;

/* JADX INFO: compiled from: PowerBookmarkUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class e32 {

    /* JADX INFO: compiled from: PowerBookmarkUtils.java */
    public class a implements wp2.b {
        public final /* synthetic */ BookmarkModel e;
        public final /* synthetic */ BookmarkId f;
        public final /* synthetic */ boolean g;
        public final /* synthetic */ wp2 h;
        public final /* synthetic */ Resources i;
        public final /* synthetic */ Callback j;

        public a(BookmarkModel bookmarkModel, BookmarkId bookmarkId, boolean z, wp2 wp2Var, Resources resources, Callback callback) {
            this.e = bookmarkModel;
            this.f = bookmarkId;
            this.g = z;
            this.h = wp2Var;
            this.i = resources;
            this.j = callback;
        }

        @Override // wp2.b
        public final void j(Object obj) {
            e32.b(this.e, this.f, this.g, this.h, this.i, this.j);
        }
    }

    public static org.chromium.components.commerce.core.a a(c32 c32Var) {
        zl2 zl2VarP = c32Var.P();
        return new org.chromium.components.commerce.core.a(1, 2, oj2.h0(zl2VarP.V()), 2, new a.C0129a(zl2VarP.P().O(), oj2.h0(zl2VarP.T()), zl2VarP.O()));
    }

    public static void b(BookmarkModel bookmarkModel, BookmarkId bookmarkId, final boolean z, final wp2 wp2Var, final Resources resources, final Callback<Boolean> callback) {
        final a aVar = new a(bookmarkModel, bookmarkId, z, wp2Var, resources, callback);
        Callback callback2 = new Callback() { // from class: d32
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                tp2 tp2VarA;
                Boolean bool = (Boolean) obj;
                boolean zBooleanValue = bool.booleanValue();
                Resources resources2 = resources;
                if (zBooleanValue) {
                    tp2VarA = tp2.a(resources2.getString(z ? R$string.price_tracking_enabled_snackbar : R$string.price_tracking_disabled_snackbar), null, 1, 47);
                } else {
                    tp2VarA = tp2.a(resources2.getString(R$string.price_tracking_error_snackbar), aVar, 1, 48);
                    tp2VarA.d = resources2.getString(R$string.price_tracking_error_snackbar_action);
                    tp2VarA.e = null;
                }
                tp2VarA.i = false;
                wp2Var.c(tp2VarA);
                callback.onResult(bool);
            }
        };
        if (z) {
            n3.B().b();
        }
        N.M5sY3Y_p(Profile.d(), bookmarkId.getId(), z, callback2, false);
    }
}
