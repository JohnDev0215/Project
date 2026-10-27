package com.byd.hvac.view.xtablayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.TintTypedArray;
import com.byd.hvac.R;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class TabItem extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f1096a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Drawable f1097b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1098c;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.TabItem);
        this.f1096a = tintTypedArrayObtainStyledAttributes.getText(2);
        this.f1097b = tintTypedArrayObtainStyledAttributes.getDrawable(0);
        this.f1098c = tintTypedArrayObtainStyledAttributes.getResourceId(1, 0);
        tintTypedArrayObtainStyledAttributes.recycle();
    }
}
