package com.byd.fragment;

import android.graphics.drawable.Drawable;
import com.byd.auto_photo.R;
import com.byd.util.e;
import com.byd.util.x;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseDialogFragment extends BaseDialogSuperFragment {
    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected float getAmountValue() {
        return 0.5f;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getBaseLayout() {
        return e.H(getActivity()) ? R.layout.fragment_base_dialog_right : super.getBaseLayout();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected Drawable getCloseDrawable() {
        return x.s(getActivity(), R.drawable.icon_close_1);
    }
}
