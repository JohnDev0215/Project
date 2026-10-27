package com.byd.automultipletheme.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.TintTypedArray;
import com.byd.automultipletheme.R;
import com.google.android.material.internal.ViewUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommonTabItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/byd/automultipletheme/widget/CommonTabItem;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "icon", "Landroid/graphics/drawable/Drawable;", "getIcon", "()Landroid/graphics/drawable/Drawable;", "setIcon", "(Landroid/graphics/drawable/Drawable;)V", "iconSize", "", "getIconSize", "()I", "setIconSize", "(I)V", "text", "", "getText", "()Ljava/lang/CharSequence;", "setText", "(Ljava/lang/CharSequence;)V", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CommonTabItem extends View {
    private Drawable icon;
    private int iconSize;
    private CharSequence text;

    public CommonTabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.CommonTabItem);
        Intrinsics.checkNotNullExpressionValue(tintTypedArrayObtainStyledAttributes, "obtainStyledAttributes(c….styleable.CommonTabItem)");
        CharSequence text = tintTypedArrayObtainStyledAttributes.getText(2);
        Intrinsics.checkNotNullExpressionValue(text, "a.getText(R.styleable.CommonTabItem_android_text)");
        this.text = text;
        Drawable drawable = tintTypedArrayObtainStyledAttributes.getDrawable(0);
        Intrinsics.checkNotNullExpressionValue(drawable, "a.getDrawable(R.styleabl…mmonTabItem_android_icon)");
        this.icon = drawable;
        setBackground(tintTypedArrayObtainStyledAttributes.getDrawable(1));
        Intrinsics.checkNotNull(context);
        this.iconSize = tintTypedArrayObtainStyledAttributes.getDimensionPixelSize(3, (int) ViewUtils.dpToPx(context, 40));
        tintTypedArrayObtainStyledAttributes.recycle();
    }

    public final CharSequence getText() {
        return this.text;
    }

    public final void setText(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<set-?>");
        this.text = charSequence;
    }

    public final Drawable getIcon() {
        return this.icon;
    }

    public final void setIcon(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "<set-?>");
        this.icon = drawable;
    }

    public final int getIconSize() {
        return this.iconSize;
    }

    public final void setIconSize(int i) {
        this.iconSize = i;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CommonTabItem(Context context) {
        this(context, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
