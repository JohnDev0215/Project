package com.android.car.ui.imewidescreen;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.android.car.ui.recyclerview.CarUiContentListItem;

/* JADX INFO: loaded from: classes6.dex */
public class CarUiImeSearchListItem extends CarUiContentListItem {
    private int mIconResId;
    private int mSupplementalIconResId;

    public CarUiImeSearchListItem(CarUiContentListItem.Action action) {
        super(action);
    }

    @Override // com.android.car.ui.recyclerview.CarUiContentListItem
    public void setIcon(Drawable icon) {
        if ((icon instanceof BitmapDrawable) || icon == null) {
            super.setIcon(icon);
            return;
        }
        throw new RuntimeException("icon should be of type BitmapDrawable");
    }

    @Override // com.android.car.ui.recyclerview.CarUiContentListItem
    public void setSupplementalIcon(Drawable icon, CarUiContentListItem.OnClickListener listener) {
        if ((icon instanceof BitmapDrawable) || icon == null) {
            super.setSupplementalIcon(icon, listener);
            return;
        }
        throw new RuntimeException("icon should be of type BitmapDrawable");
    }

    public int getIconResId() {
        return this.mIconResId;
    }

    public void setIconResId(int iconResId) {
        this.mIconResId = iconResId;
    }

    public int getSupplementalIconResId() {
        return this.mSupplementalIconResId;
    }

    public void setSupplementalIconResId(int supplementalIconResId) {
        this.mSupplementalIconResId = supplementalIconResId;
    }
}
