package com.byd.automultipletheme.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommonTabLayout.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u0011R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/byd/automultipletheme/widget/CommonTabView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "tabIndicatorMode", "", "(Landroid/content/Context;I)V", "icon", "Landroid/graphics/drawable/Drawable;", "iconView", "Landroid/widget/ImageView;", "textView", "Landroid/widget/TextView;", "setIcon", "", "setIconSelected", "isSelected", "", "setIconSize", "size", "setText", "charSequence", "", "setTextColor", "colors", "Landroid/content/res/ColorStateList;", "setViewEnable", "enable", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CommonTabView extends LinearLayout {
    private Drawable icon;
    private ImageView iconView;
    private TextView textView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommonTabView(Context context, int i) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        LayoutInflater.from(context).inflate(R.layout.view_common_tab, this);
        View viewFindViewById = findViewById(R.id.commonTabIconView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.commonTabIconView)");
        this.iconView = (ImageView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.commonTabTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.commonTabTextView)");
        this.textView = (TextView) viewFindViewById2;
    }

    public final void setViewEnable(boolean enable) {
        setIconSelected(enable);
        this.textView.setEnabled(enable);
        requestLayout();
        invalidate();
    }

    public final void setText(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "charSequence");
        this.textView.setText(charSequence);
        invalidate();
    }

    public final void setTextColor(ColorStateList colors) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        this.textView.setTextColor(colors);
    }

    public final void setIcon(Drawable icon) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        this.icon = icon;
        this.iconView.setImageDrawable(icon);
        invalidate();
    }

    private final void setIconSelected(boolean isSelected) {
        if (isSelected) {
            Drawable drawable = this.icon;
            if (drawable != null) {
                Drawable drawableMutate = DrawableCompat.wrap(drawable).mutate();
                Intrinsics.checkNotNullExpressionValue(drawableMutate, "wrap(it).mutate()");
                DrawableCompat.setTint(drawableMutate, ContextCompat.getColor(Utils.getApp(), R.color.item_selected));
                this.iconView.setImageDrawable(drawableMutate);
                return;
            }
            return;
        }
        Drawable drawable2 = this.icon;
        if (drawable2 != null) {
            Drawable drawableMutate2 = DrawableCompat.wrap(drawable2).mutate();
            Intrinsics.checkNotNullExpressionValue(drawableMutate2, "wrap(it).mutate()");
            DrawableCompat.setTint(drawableMutate2, ContextCompat.getColor(Utils.getApp(), R.color.item_unselected));
            this.iconView.setImageDrawable(drawableMutate2);
        }
    }

    public final void setIconSize(int size) {
        this.iconView.setLayoutParams(new LinearLayout.LayoutParams(size, size));
        requestLayout();
        invalidate();
    }
}
