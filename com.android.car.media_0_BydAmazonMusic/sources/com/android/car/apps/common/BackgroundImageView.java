package com.android.car.apps.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.car.ui.R$attr;
import com.android.car.ui.R$dimen;
import com.android.car.ui.R$id;
import com.android.car.ui.R$integer;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$styleable;
import spi.x;

/* JADX INFO: loaded from: classes.dex */
public class BackgroundImageView extends ConstraintLayout {
    public CrossfadeImageView a;
    public CrossfadeImageView b;
    public Size c;
    public float d;
    public View e;

    public BackgroundImageView(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        CrossfadeImageView crossfadeImageView = this.a;
        if (crossfadeImageView != null) {
            crossfadeImageView.setBackgroundColor(i);
        }
        CrossfadeImageView crossfadeImageView2 = this.b;
        if (crossfadeImageView2 != null) {
            crossfadeImageView2.setBackgroundColor(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        if (drawable == null) {
            CrossfadeImageView crossfadeImageView = this.a;
            if (crossfadeImageView != null) {
                crossfadeImageView.b(null, false);
            }
            CrossfadeImageView crossfadeImageView2 = this.b;
            if (crossfadeImageView2 != null) {
                crossfadeImageView2.b(null, false);
                return;
            }
            return;
        }
        Bitmap bitmapB = x.b(drawable, this.c);
        Context context = getContext();
        Size size = this.c;
        float f = this.d;
        if (bitmapB.getWidth() > size.getWidth() || bitmapB.getHeight() > size.getHeight()) {
            float height = ((size.getHeight() + size.getWidth()) / 2) / ((bitmapB.getHeight() + bitmapB.getWidth()) / 2);
            bitmapB = Bitmap.createScaledBitmap(bitmapB, Math.round(bitmapB.getWidth() * height), Math.round(height * bitmapB.getHeight()), false);
        }
        float height2 = f * ((bitmapB.getHeight() + bitmapB.getWidth()) / 2);
        if (height2 > 0.1f) {
            if (height2 > 25.0f) {
                height2 = 25.0f;
            }
            Bitmap.Config config = bitmapB.getConfig();
            Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
            if (config != config2) {
                bitmapB = bitmapB.copy(config2, true);
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapB);
            RenderScript renderScriptCreate = RenderScript.create(context);
            ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
            Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapB);
            Allocation allocationCreateFromBitmap2 = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateBitmap);
            scriptIntrinsicBlurCreate.setRadius(height2);
            scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
            scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap2);
            allocationCreateFromBitmap2.copyTo(bitmapCreateBitmap);
            bitmapB = bitmapCreateBitmap;
        }
        CrossfadeImageView crossfadeImageView3 = this.a;
        if (crossfadeImageView3 != null) {
            crossfadeImageView3.b(bitmapB, true);
        }
        CrossfadeImageView crossfadeImageView4 = this.b;
        if (crossfadeImageView4 != null) {
            crossfadeImageView4.b(bitmapB, true);
        }
        invalidate();
        requestLayout();
    }

    public void setDimmed(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }

    public void setImageAdditionalScale(float f) {
        CrossfadeImageView crossfadeImageView = this.a;
        if (crossfadeImageView != null) {
            crossfadeImageView.setImageAdditionalScale(f);
        }
        CrossfadeImageView crossfadeImageView2 = this.b;
        if (crossfadeImageView2 != null) {
            crossfadeImageView2.setImageAdditionalScale(f);
        }
    }

    public BackgroundImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.backgroundImageViewStyle);
    }

    public BackgroundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.BackgroundImageView, i, 0);
        try {
            float f = typedArrayObtainStyledAttributes.getFloat(R$styleable.BackgroundImageView_imageAdditionalScale, 1.05f);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BackgroundImageView_contentLayout, R$layout.background_image);
            this.d = typedArrayObtainStyledAttributes.getFloat(R$styleable.BackgroundImageView_bitmap_blur_percent, getResources().getFloat(R$dimen.background_bitmap_blur_percent));
            int integer = typedArrayObtainStyledAttributes.getInteger(R$styleable.BackgroundImageView_bitmap_target_size_px, getResources().getInteger(R$integer.background_bitmap_target_size_px));
            this.c = new Size(integer, integer);
            typedArrayObtainStyledAttributes.recycle();
            View.inflate(getContext(), resourceId, this);
            this.a = (CrossfadeImageView) findViewById(R$id.background_image_image);
            this.b = (CrossfadeImageView) findViewById(R$id.background_image_another);
            this.e = findViewById(R$id.background_image_darkening_scrim);
            setImageAdditionalScale(f);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }
}
