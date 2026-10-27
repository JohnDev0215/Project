package com.byd.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.byd.mvp.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class TintImageView extends ImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3812a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3813b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3814c;

    public TintImageView(@NonNull Context context) {
        super(context);
        this.f3812a = -1;
        this.f3813b = -1;
        this.f3814c = -1;
    }

    public void a(int i) {
        Drawable background = getBackground();
        if (i == -1) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(background.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i));
        setBackgroundDrawable(drawableWrap);
    }

    public void b(int i) {
        if (i == -1) {
            a(this.f3814c);
        } else if (i == 0) {
            a(this.f3813b);
        } else {
            if (i != 1) {
                return;
            }
            a(this.f3812a);
        }
    }

    public void c(int i) {
        Drawable drawable = getDrawable();
        if (i == -1) {
            setImageDrawable(drawable);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i));
        setImageDrawable(drawableWrap);
    }

    public void d(int i) {
        if (i == -1) {
            c(this.f3814c);
        } else if (i == 0) {
            c(this.f3813b);
        } else {
            if (i != 1) {
                return;
            }
            c(this.f3812a);
        }
    }

    public final void e(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.tint_imageview);
        this.f3812a = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_imageview_color_open_state, this.f3812a);
        this.f3813b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_imageview_color_close_state, this.f3813b);
        this.f3814c = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_imageview_color_err_state, this.f3814c);
        typedArrayObtainStyledAttributes.recycle();
    }

    public TintImageView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3812a = -1;
        this.f3813b = -1;
        this.f3814c = -1;
        e(context, attributeSet);
    }

    public TintImageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3812a = -1;
        this.f3813b = -1;
        this.f3814c = -1;
        e(context, attributeSet);
    }
}
