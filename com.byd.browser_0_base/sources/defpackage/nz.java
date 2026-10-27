package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.contextmenu.ContextMenuNativeDelegateImpl;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.styles.R$attr;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ContextMenuHeaderMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class nz implements View.OnClickListener {
    public final PropertyModel e;
    public final Context f;
    public final GURL g;

    public nz(Context context, PropertyModel propertyModel, ContextMenuParams contextMenuParams, Profile profile, uz uzVar) {
        this.f = context;
        GURL gurlA = contextMenuParams.a();
        this.g = gurlA;
        this.e = propertyModel;
        propertyModel.p(oz.d, this);
        boolean z = contextMenuParams.j;
        if (z) {
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.context_menu_header_image_max_size);
            xr xrVar = new xr(11, this);
            ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl = (ContextMenuNativeDelegateImpl) uzVar;
            long j = contextMenuNativeDelegateImpl.b;
            if (j == 0) {
                return;
            }
            N.MrTfYLQo(j, contextMenuNativeDelegateImpl, contextMenuNativeDelegateImpl.a, xrVar, dimensionPixelSize, dimensionPixelSize);
            return;
        }
        boolean z2 = contextMenuParams.k;
        if (!z && !z2) {
            new LargeIconBridge(profile).b(gurlA, context.getResources().getDimensionPixelSize(R$dimen.default_favicon_min_size), new pq2(8, this));
            return;
        }
        if (z2) {
            Drawable drawableC = c6.c(context.getResources(), R$drawable.gm_filled_videocam_24, 0);
            drawableC.setColorFilter(ii2.g(context, R$attr.colorOnSurface), PorterDuff.Mode.SRC_IN);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawableC.getIntrinsicWidth(), drawableC.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawableC.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawableC.draw(canvas);
            a(bitmapCreateBitmap, false);
        }
    }

    public final void a(Bitmap bitmap, boolean z) {
        PropertyModel propertyModel = this.e;
        if (z) {
            propertyModel.p(oz.f, bitmap);
        } else {
            propertyModel.l(oz.g, true);
            propertyModel.p(oz.f, bitmap);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        oj2.O("ContextMenu.URLClicked", true);
        PropertyModel.p pVar = oz.e;
        PropertyModel propertyModel = this.e;
        if (propertyModel.h(pVar) != Integer.MAX_VALUE) {
            propertyModel.n(pVar, Integer.MAX_VALUE);
            propertyModel.n(oz.b, Integer.MAX_VALUE);
        } else {
            propertyModel.n(pVar, TextUtils.isEmpty((CharSequence) propertyModel.j(oz.a)) ? 2 : 1);
            propertyModel.n(oz.b, TextUtils.isEmpty((CharSequence) propertyModel.j(oz.c)) ? 2 : 1);
        }
    }
}
