package com.byd.auto_photo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import b.a.h.i;
import com.byd.bean.ImageEditItem;
import com.byd.imageedit.ScrawlImageView;
import com.byd.taskmanager.b.c;
import com.byd.util.e;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public abstract class MosicSuperActivity extends BaseScrawlActivity {
    protected int i0 = -1;
    private ImageEditItem j0;
    private long k0;

    class a extends com.byd.taskmanager.b.a<ImageEditItem[]> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ImageEditItem[] doTask() {
            int dimensionPixelSize = MosicSuperActivity.this.getResources().getDimensionPixelSize(R.dimen.mosic_img_size);
            int dimensionPixelSize2 = MosicSuperActivity.this.getResources().getDimensionPixelSize(R.dimen.mosic_img_border_width);
            int dimensionPixelSize3 = MosicSuperActivity.this.getResources().getDimensionPixelSize(R.dimen.mosic_img_border_offset);
            int iL = e.l(MosicSuperActivity.this, R.attr.text_color_press);
            i.a aVar = new i.a();
            aVar.f(2).g(dimensionPixelSize, dimensionPixelSize).e(dimensionPixelSize2).d(dimensionPixelSize3);
            return new ImageEditItem[]{MosicSuperActivity.this.j0 = new ImageEditItem(R.drawable.icon_edit_mosic_eraser).setSelectColor(iL).setEnable(false).setDisableColor(e.l(MosicSuperActivity.this, R.attr.text_color_no_enable)), new ImageEditItem(MosicSuperActivity.this.T0(R.drawable.icon_mosic_def, aVar, iL)), new ImageEditItem(MosicSuperActivity.this.T0(R.drawable.icon_edit_mosic_0, aVar, iL)), new ImageEditItem(MosicSuperActivity.this.T0(R.drawable.icon_edit_mosic_1, aVar, iL)), new ImageEditItem(MosicSuperActivity.this.T0(R.drawable.icon_edit_mosic_2, aVar, iL)), new ImageEditItem(MosicSuperActivity.this.T0(R.drawable.icon_edit_mosic_3, aVar, iL))};
        }
    }

    class b extends c<ImageEditItem[]> {
        b() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, ImageEditItem[] imageEditItemArr) {
            MosicSuperActivity.this.w.q(imageEditItemArr);
            MosicSuperActivity.this.j0();
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    private void S0() {
        if (z(15)) {
            return;
        }
        v(15, new a(), new b(), true);
        w(15);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Drawable T0(int i, i.a aVar, int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(i)).getBitmap();
        aVar.c(0);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(i.a(bitmap, aVar));
        aVar.c(i2);
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, new BitmapDrawable(i.a(bitmap, aVar)));
        stateListDrawable.addState(new int[0], bitmapDrawable);
        return stateListDrawable;
    }

    private Bitmap U0(int i) {
        return ((BitmapDrawable) getResources().getDrawable(i)).getBitmap();
    }

    private boolean V0(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.k0 <= 500) {
            w.c("MosicActivity item click too fast");
            return false;
        }
        this.k0 = jCurrentTimeMillis;
        if (this.i0 == i) {
            return true;
        }
        if (i == 0 && !this.Q.h()) {
            return false;
        }
        W0(i);
        if (i <= 0) {
            return true;
        }
        P0("masic_style", i);
        return true;
    }

    private void W0(int i) {
        ScrawlImageView scrawlImageView;
        Bitmap bitmapU0;
        int i2;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    this.Q.n(1);
                    scrawlImageView = this.Q;
                    i2 = R.drawable.img_mosic_0;
                } else if (i == 3) {
                    this.Q.n(1);
                    scrawlImageView = this.Q;
                    i2 = R.drawable.img_mosic_1;
                } else if (i == 4) {
                    this.Q.n(1);
                    scrawlImageView = this.Q;
                    i2 = R.drawable.img_mosic_2;
                } else if (i == 5) {
                    this.Q.n(1);
                    scrawlImageView = this.Q;
                    i2 = R.drawable.img_mosic_3;
                }
                bitmapU0 = U0(i2);
            } else {
                this.Q.n(1);
                scrawlImageView = this.Q;
                bitmapU0 = null;
            }
            scrawlImageView.setMasicBitmap(bitmapU0);
        } else {
            this.Q.n(10);
        }
        this.i0 = i;
    }

    @Override // com.byd.auto_photo.BaseScrawlActivity
    protected String M0() {
        return getResources().getString(R.string.masc);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity
    protected void j0() {
        if (this.w.getItemCount() == 0) {
            return;
        }
        super.j0();
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public boolean o(int i) {
        return V0(i);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.Q.o(true);
        this.w.w(true);
        this.w.v(true);
        int iJ0 = J0("masic_style", 1);
        W0(iJ0);
        this.w.n(iJ0);
        int iJ02 = J0("masic_size", 2);
        this.Q.p(this.X[iJ02]);
        this.T.f(iJ02);
        S0();
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public boolean onItemClick(int i) {
        return V0(i);
    }

    @Override // com.byd.auto_photo.BaseScrawlActivity, b.a.e.q.a
    public void onStyleItemClick(int i) {
        super.onStyleItemClick(i);
        this.Q.p(this.X[i]);
        P0("masic_size", i);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void z0(boolean z) {
        super.z0(z);
        ImageEditItem imageEditItem = this.j0;
        if (imageEditItem == null || imageEditItem.isEnable() == z) {
            return;
        }
        this.j0.setEnable(z);
        this.w.notifyDataSetChanged();
    }
}
