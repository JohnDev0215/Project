package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class EndMatchLinearItem extends LinearItem {
    public EndMatchLinearItem(Context context) {
        super(context);
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem
    public int getLayoutId() {
        return R.layout.end_match_item_linear;
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem, b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem, b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    public EndMatchLinearItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
