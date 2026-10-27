package com.byd.carsettings.ui.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import b.b.g.r.t;
import com.byd.base.view.dialog.FrameDialogView;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.BydInvalidButton;
import com.byd.mvp.ui.widget.TintCheckBox;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class SettingItemsRecoveryView extends FrameDialogView implements View.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CustomViewDialog f2979e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f2980f;
    public ViewGroup g;
    public ViewGroup h;
    public BydInvalidButton i;
    public a j;
    public SparseArray<Integer> k;

    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<SettingItemsRecoveryView> f2981a;

        public a(SettingItemsRecoveryView settingItemsRecoveryView) {
            this.f2981a = new WeakReference<>(settingItemsRecoveryView);
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            WeakReference<SettingItemsRecoveryView> weakReference = this.f2981a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f2981a.get().l(compoundButton, z);
        }
    }

    public SettingItemsRecoveryView(Context context, CustomViewDialog customViewDialog) {
        super(context);
        this.f2979e = customViewDialog;
    }

    @Override // com.byd.base.view.dialog.FrameDialogView
    public void a(View view) {
        this.f2980f = (TextView) view.findViewById(R.id.tv_title_dialog_common_titles);
        view.findViewById(R.id.iv_back_dialog_common_title).setVisibility(8);
        this.h = (ViewGroup) view.findViewById(R.id.ll_opertion_dialog_bottom);
        this.g = (ViewGroup) view.findViewById(R.id.ll_content_dialog_setting_items_recovery);
        this.i = (BydInvalidButton) view.findViewById(R.id.btn_ok_dialog_bottom);
        view.findViewById(R.id.btn_cancel_dialog_bottom).setOnClickListener(this);
        this.i.setOnClickListener(this);
        j(getContext());
    }

    @Override // com.byd.base.view.dialog.FrameDialogView
    public void e(boolean z) {
        g(this.h, true);
    }

    @Override // com.byd.base.view.dialog.FrameDialogView
    public void f(boolean z) {
        g(this.h, false);
        g(this.g, true);
    }

    @Override // com.byd.base.view.dialog.FrameDialogView
    public int getDialogViewRes() {
        return R.layout.byd_dialog_setting_items_recovery;
    }

    public int i(ViewGroup viewGroup, int i) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (i == viewGroup.getChildAt(i2).getId()) {
                return i2;
            }
        }
        return -1;
    }

    public void j(Context context) {
        this.f2980f.setText(context.getString(R.string.version_recover_setting));
        if (this.j == null) {
            this.j = new a(this);
        }
        k(this.g, this.j);
    }

    public final void k(ViewGroup viewGroup, a aVar) {
        if (viewGroup == null || viewGroup.getChildCount() <= 0) {
            return;
        }
        this.i.setInvalid(true);
        this.k = new SparseArray<>();
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof TintCheckBox) {
                TintCheckBox tintCheckBox = (TintCheckBox) childAt;
                tintCheckBox.setOnCheckedChangeListener(aVar);
                tintCheckBox.setChecked(true);
                this.k.put(i, Integer.valueOf(i));
            } else {
                b.b.m.c.a.c(this.f2486a, "--setOnMutiBtnCheckedListener--This view is not a RadioButton");
            }
        }
    }

    public final void l(CompoundButton compoundButton, boolean z) {
        int i = i(this.g, compoundButton.getId());
        if (i != -1) {
            if (z) {
                this.k.put(i, Integer.valueOf(i));
            } else {
                this.k.remove(i);
            }
            this.i.setInvalid(this.k.size() <= 0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel_dialog_bottom) {
            this.f2979e.dismiss();
        } else if (id == R.id.btn_ok_dialog_bottom && !this.i.a()) {
            b.b.m.c.a.a(this.f2486a, "--onClick--resetSettingOptions.");
            t.a(getContext(), this.k);
            this.f2979e.dismiss();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b.b.m.c.a.a(this.f2486a, "--onDetachedFromWindow--");
    }
}
