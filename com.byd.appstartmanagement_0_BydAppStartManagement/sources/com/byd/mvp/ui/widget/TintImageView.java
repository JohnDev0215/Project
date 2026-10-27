package com.byd.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.mvp.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class TintImageView extends ImageView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f736b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f737c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f738d;

    public TintImageView(@NonNull Context context) {
        super(context);
        this.f736b = -1;
        this.f737c = -1;
        this.f738d = -1;
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.tint_imageview);
        this.f736b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_imageview_color_open_state, this.f736b);
        this.f737c = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_imageview_color_close_state, this.f737c);
        this.f738d = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_imageview_color_err_state, this.f738d);
        typedArrayObtainStyledAttributes.recycle();
    }

    public TintImageView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f736b = -1;
        this.f737c = -1;
        this.f738d = -1;
        a(context, attributeSet);
    }

    public TintImageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f736b = -1;
        this.f737c = -1;
        this.f738d = -1;
        a(context, attributeSet);
    }
}
