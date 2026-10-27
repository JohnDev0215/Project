package com.bydux.api.common.views.ctrl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes2.dex */
public class BydImageView extends ImageView {
    private Bitmap mBitmap;

    public BydImageView(Context context, AttributeSet attrs) {
        super(SkinManager.getInstance().getContext(context), attrs);
        this.mBitmap = null;
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bmp) {
        if (this.mBitmap == bmp) {
            return;
        }
        this.mBitmap = bmp;
        super.setImageBitmap(bmp);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        super.onDraw(canvas);
    }
}
