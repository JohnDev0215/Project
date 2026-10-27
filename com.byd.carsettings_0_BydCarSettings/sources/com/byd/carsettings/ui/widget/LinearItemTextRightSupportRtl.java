package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class LinearItemTextRightSupportRtl extends LinearItem {
    public LinearItemTextRightSupportRtl(Context context) {
        super(context);
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem
    public int getLayoutId() {
        return R.layout.item_linear_text_right_support_rtl;
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem
    public void j() {
        this.f3427b = (TextView) this.f3426a.findViewById(R.id.tv_left_item_linear_text_right_support_rtl);
        this.f3428c = (TextView) this.f3426a.findViewById(R.id.tv_right_item_linear_text_right_support_rtl);
        this.f3429d = (ImageView) this.f3426a.findViewById(R.id.iv_right_item_linear_text_right_support_rtl);
        this.f3430e = this.f3426a.findViewById(R.id.ll_right_item_linear_text_right_support_rtl);
        this.f3431f = this.f3426a.findViewById(R.id.v_line_botoom_item_linear_text_right_support_rtl);
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem, b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem, b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    public LinearItemTextRightSupportRtl(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LinearItemTextRightSupportRtl(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
