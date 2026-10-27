package com.bydux.api.common.views.ctrl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import d.a.a.f.c.a.c.c;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class BydImageView extends ImageView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Bitmap f3580b;

    public BydImageView(Context context, AttributeSet attributeSet) {
        super(c.n().k(context), attributeSet);
        this.f3580b = null;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = this.f3580b;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        if (this.f3580b == bitmap) {
            return;
        }
        this.f3580b = bitmap;
        super.setImageBitmap(bitmap);
    }
}
