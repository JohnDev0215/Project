package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.byd.carsettings.R;
import com.byd.mvp.ui.widget.TintImageView;

/* JADX INFO: loaded from: classes2.dex */
public class TintLinearItem extends LinearItem {
    public volatile boolean D;

    public TintLinearItem(Context context) {
        super(context);
        this.D = false;
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem
    public int getLayoutId() {
        return R.layout.item_tint_linear;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.D) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem, b.b.a.j.c.a
    public void setInvalid(boolean z) {
        this.D = z;
        int color = z ? getContext().getColor(R.color.text_color_main_gray) : getContext().getColor(R.color.text_color_main);
        TextView textView = this.f3428c;
        if (textView != null) {
            textView.setTextColor(color);
        }
        TextView textView2 = this.f3427b;
        if (textView2 != null) {
            textView2.setTextColor(color);
        }
        ImageView imageView = this.f3429d;
        if (imageView == null || !(imageView instanceof TintImageView)) {
            return;
        }
        ((TintImageView) imageView).d(!z ? 1 : 0);
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem, b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    public TintLinearItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.D = false;
    }

    public TintLinearItem(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.D = false;
    }
}
