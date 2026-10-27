package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class TabItem extends View {
    public final CharSequence e;
    public final Drawable f;
    public final int g;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TintTypedArray tintTypedArray = new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, R$styleable.TabItem));
        this.e = tintTypedArray.k(R$styleable.TabItem_android_text);
        this.f = tintTypedArray.e(R$styleable.TabItem_android_icon);
        this.g = tintTypedArray.i(R$styleable.TabItem_android_layout, 0);
        tintTypedArray.n();
    }
}
