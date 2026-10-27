package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* JADX INFO: compiled from: BookmarkSaveFlowCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class vg {
    public final Context a;
    public final j<PropertyModel, ViewLookupCachingFrameLayout, v62> b;
    public final u80 c;
    public final Profile d;
    public final ji e;
    public a f;
    public xg g;
    public View h;
    public final BookmarkModel i;
    public final pk3 j;
    public boolean k;

    /* JADX INFO: compiled from: BookmarkSaveFlowCoordinator.java */
    public class a implements ii {
        public final View e;

        public a(View view) {
            this.e = view;
        }

        @Override // defpackage.ii
        public final View d() {
            return this.e;
        }

        @Override // defpackage.ii
        public final void destroy() {
            vg vgVar = vg.this;
            u80 u80Var = vgVar.c;
            u80Var.getClass();
            u80Var.e = true;
            if (vgVar.k) {
                n3.X0("MobileBookmark.SaveFlow.ClosedWithoutEditAction");
            }
            xg xgVar = vgVar.g;
            xgVar.i.r(xgVar);
            ShoppingService shoppingService = xgVar.n;
            if (shoppingService != null) {
                shoppingService.b.c(xgVar);
            }
            xgVar.i = null;
            xgVar.h = null;
            xgVar.j = null;
            ll llVar = xgVar.g;
            if (llVar != null) {
                llVar.a();
                xgVar.g = null;
            }
            vgVar.g = null;
            vgVar.h = null;
            vgVar.b.c();
        }

        @Override // defpackage.ii
        public final int e() {
            return 0;
        }

        @Override // defpackage.ii
        public final boolean f() {
            return true;
        }

        @Override // defpackage.ii
        public final View g() {
            return null;
        }

        @Override // defpackage.ii
        public final int h() {
            return R$string.bookmarks_save_flow_opened_half;
        }

        @Override // defpackage.ii
        public final int i() {
            return R$string.bookmarks_save_flow_closed_description;
        }

        @Override // defpackage.ii
        public final int j() {
            return -2;
        }

        @Override // defpackage.ii
        public final int m() {
            return 0;
        }

        @Override // defpackage.ii
        public final int p() {
            return R$string.bookmarks_save_flow_content_description;
        }

        @Override // defpackage.ii
        public final int r() {
            return R$string.bookmarks_save_flow_opened_full;
        }

        @Override // defpackage.ii
        public final float s() {
            return -1.0f;
        }

        @Override // defpackage.ii
        public final boolean u() {
            return true;
        }
    }

    public vg(Context context, ji jiVar, ShoppingService shoppingService, pk3 pk3Var, Profile profile) {
        PropertyModel propertyModel = new PropertyModel(yg.n);
        this.a = context;
        this.e = jiVar;
        this.j = pk3Var;
        BookmarkModel bookmarkModelV = BookmarkModel.v(Profile.d());
        this.i = bookmarkModelV;
        this.c = new u80();
        this.d = profile;
        this.h = LayoutInflater.from(context).inflate(R$layout.bookmark_save_flow, (ViewGroup) null);
        this.g = new xg(bookmarkModelV, propertyModel, context, new wv(19, this), shoppingService);
        this.b = j.b(propertyModel, (ViewLookupCachingFrameLayout) this.h, new zg());
    }

    public final void a() {
        Resources resources = this.h.getResources();
        int i = R$string.iph_shopping_list_save_flow;
        hw0 hw0Var = new hw0(resources, "IPH_ShoppingListSaveFlow", i, i);
        hw0Var.j = this.h.findViewById(R$id.bookmark_select_folder);
        this.j.a(hw0Var.a());
    }
}
