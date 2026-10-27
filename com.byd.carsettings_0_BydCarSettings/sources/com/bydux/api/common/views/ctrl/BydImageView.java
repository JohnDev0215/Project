package com.bydux.api.common.views.ctrl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"AppCompatCustomView"})
public class BydImageView extends ImageView {
    private Bitmap mBitmap;

    public BydImageView(Context context, AttributeSet attributeSet) {
        super(SkinManager.getInstance().getContext(context), attributeSet);
        this.mBitmap = null;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        if (this.mBitmap == bitmap) {
            return;
        }
        this.mBitmap = bitmap;
        super.setImageBitmap(bitmap);
    }
}
