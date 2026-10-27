package org.chromium.components.webapps.bottomsheet;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.aj3;
import defpackage.bh0;
import defpackage.c30;
import defpackage.ji;
import defpackage.u72;
import defpackage.y3;
import defpackage.zi3;
import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.webapps.R$id;
import org.chromium.components.webapps.R$string;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class PwaBottomSheetController implements zi3, y3, View.OnClickListener {
    public final Context e;
    public long f;
    public ji g;
    public final a h = new a();
    public u72 i;
    public c j;
    public WebContents k;

    public class a extends bh0 {
        public a() {
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void d(int i, int i2) {
            PwaBottomSheetController pwaBottomSheetController = PwaBottomSheetController.this;
            if (i == 0) {
                if (i2 == 1) {
                    N.MUs2wv$Q(pwaBottomSheetController.f);
                }
                pwaBottomSheetController.g.e(pwaBottomSheetController.h);
                pwaBottomSheetController.i = null;
                N.MGZHTWtH(pwaBottomSheetController.f);
                pwaBottomSheetController.f = 0L;
                return;
            }
            if (!pwaBottomSheetController.d() || i != 3) {
                pwaBottomSheetController.i.g = 1;
            } else {
                pwaBottomSheetController.i.g = 0;
                N.M55fWa5U(pwaBottomSheetController.f);
            }
        }
    }

    public class b extends RecyclerView.a0 {
        public b(ImageView imageView) {
            super(imageView);
        }
    }

    public class c extends RecyclerView.e<b> {
        public final Context h;
        public final ArrayList<Bitmap> i = new ArrayList<>();

        public c(PwaBottomSheetController pwaBottomSheetController, Context context) {
            this.h = context;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int k() {
            ArrayList<Bitmap> arrayList = this.i;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final void t(RecyclerView.a0 a0Var, int i) {
            Bitmap bitmap = this.i.get(i);
            ImageView imageView = (ImageView) ((b) a0Var).e;
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(bitmap);
            imageView.setContentDescription(this.h.getResources().getString(R$string.pwa_install_bottom_sheet_screenshot));
            imageView.setOnClickListener(new c30(this, 12, bitmap));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
            return new b(new ImageView(this.h));
        }
    }

    public PwaBottomSheetController(Context context) {
        this.e = context;
    }

    @CalledByNative
    public static void addWebAppScreenshot(Bitmap bitmap, WebContents webContents) {
        WindowAndroid windowAndroidV = webContents.V();
        if (windowAndroidV == null) {
            return;
        }
        aj3<PwaBottomSheetController> aj3Var = PwaBottomSheetControllerProvider.a;
        PwaBottomSheetController pwaBottomSheetController = (PwaBottomSheetController) PwaBottomSheetControllerProvider.a.e(windowAndroidV.r);
        if (pwaBottomSheetController == null) {
            return;
        }
        c cVar = pwaBottomSheetController.j;
        cVar.i.add(bitmap);
        cVar.o();
    }

    @Override // defpackage.y3
    public final boolean a() {
        return false;
    }

    @Override // defpackage.y3
    public final void b(String str) {
        N.MXiwiwPi(this.f, this.k);
    }

    public final boolean d() {
        return this.i != null && this.g.h() == this.i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        if (id == R$id.button_install) {
            N.MXiwiwPi(this.f, this.k);
            this.g.c(this.i, false);
        } else if (id == R$id.drag_handlebar) {
            if (this.g.m()) {
                this.g.k();
            } else {
                this.g.l();
            }
        }
    }

    @Override // defpackage.y3
    public final void c() {
    }
}
