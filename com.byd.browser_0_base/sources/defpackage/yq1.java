package defpackage;

import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import defpackage.k81;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.home.view.SelectionView;
import org.chromium.components.browser_ui.widget.async_image.AsyncImageView;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;
import org.chromium.components.offline_items_collection.OfflineItemVisuals;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: OfflineItemViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public class yq1 extends i81 implements l81 {
    public static final /* synthetic */ int H = 0;
    public final AsyncImageView A;
    public final ListMenuButton B;
    public l91 C;
    public a9 D;
    public z8 E;
    public boolean F;
    public boolean G;
    public final SelectionView z;

    /* JADX INFO: compiled from: OfflineItemViewHolder.java */
    public static class a implements AsyncImageView.b {
        public final ImageView a;
        public final int b;

        public a(AsyncImageView asyncImageView, int i) {
            this.a = asyncImageView;
            this.b = i;
        }

        public final void a(Drawable drawable) {
            BitmapDrawable bitmapDrawable;
            boolean z = drawable instanceof BitmapDrawable;
            ImageView imageView = this.a;
            Matrix matrix = null;
            if (z && (bitmapDrawable = (BitmapDrawable) drawable) != null) {
                int width = bitmapDrawable.getBitmap().getWidth();
                float f = width;
                float width2 = imageView.getWidth() / f;
                float height = bitmapDrawable.getBitmap().getHeight();
                float height2 = imageView.getHeight() / height;
                oj2.S((int) (Math.max(width2, height2) * 100.0f), 10, 1000, 50, "Android.DownloadManager.Thumbnail.MaxRequiredStretch.".concat(oj2.s(this.b)));
                float fMin = Math.max(width2, height2) < 4.0f ? 1.0f : Math.min(Math.min(width2, height2), 4.0f);
                if (fMin > 1.0f) {
                    float height3 = (imageView.getHeight() - (height * fMin)) / 2.0f;
                    Matrix matrix2 = new Matrix();
                    matrix2.postScale(fMin, fMin);
                    matrix2.postTranslate((imageView.getWidth() - (f * fMin)) / 2.0f, height3);
                    matrix = matrix2;
                }
            }
            imageView.setImageMatrix(matrix);
            imageView.setScaleType(matrix == null ? ImageView.ScaleType.CENTER_CROP : ImageView.ScaleType.MATRIX);
        }
    }

    public yq1(View view) {
        super(view);
        this.z = (SelectionView) view.findViewById(R$id.selection);
        ListMenuButton listMenuButton = (ListMenuButton) view.findViewById(R$id.more);
        this.B = listMenuButton;
        this.A = (AsyncImageView) view.findViewById(R$id.thumbnail);
        if (listMenuButton != null) {
            listMenuButton.setDelegate(this);
        }
    }

    @Override // defpackage.l81
    public final k81 b() {
        ya1 ya1Var = new ya1();
        if (this.G) {
            ya1Var.t(zd.c(R$string.share, 0, 0));
        }
        if (this.F) {
            ya1Var.t(zd.c(R$string.rename, 0, 0));
        }
        ya1Var.t(zd.c(R$string.delete, 0, 0));
        return new zd(this.B.getContext(), ya1Var, new k81.a() { // from class: uq1
            @Override // k81.a
            public final void a(PropertyModel propertyModel) throws Throwable {
                z8 z8Var;
                yq1 yq1Var = this.e;
                yq1Var.getClass();
                int iH = propertyModel.h(o81.a);
                if (iH == R$string.share) {
                    l91 l91Var = yq1Var.C;
                    if (l91Var != null) {
                        l91Var.run();
                        return;
                    }
                    return;
                }
                if (iH == R$string.delete) {
                    a9 a9Var = yq1Var.D;
                    if (a9Var != null) {
                        a9Var.run();
                        return;
                    }
                    return;
                }
                if (iH != R$string.rename || (z8Var = yq1Var.E) == null) {
                    return;
                }
                z8Var.run();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    @Override // defpackage.i81
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void u(final org.chromium.ui.modelutil.PropertyModel r8, final defpackage.f81 r9) {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yq1.u(org.chromium.ui.modelutil.PropertyModel, f81):void");
    }

    @Override // defpackage.i81
    public final void v() {
        this.A.setImageDrawable(null);
    }

    public Drawable w(OfflineItemVisuals offlineItemVisuals) {
        if (offlineItemVisuals == null || offlineItemVisuals.a == null) {
            return null;
        }
        return new BitmapDrawable(this.e.getResources(), offlineItemVisuals.a);
    }
}
