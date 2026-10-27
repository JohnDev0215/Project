package com.byd.systemsettings.cpaa;

import android.content.Context;
import android.view.View;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.systemsettings.R;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class CarPlayTipsPager extends a {
    public CarPlayTipsPager(Context context) {
        super(context);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.dialog_cp_tips;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ Object getP() {
        return super.getP();
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // z3.g
    public /* bridge */ /* synthetic */ f getP() {
        return super.getP();
    }

    @Override // z3.e
    public void initData(Context context) {
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.mRootView.findViewById(R.id.btn_ok_dialog_hint_1_btn).setOnClickListener(this);
    }

    @Override // z3.g
    public f newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        w3.a aVar;
        if (view.getId() != R.id.btn_ok_dialog_hint_1_btn || (aVar = this.mBtnCallback) == null) {
            return;
        }
        aVar.onBtnClicked(PagerBtnType.BOTTOM_MIDDLE);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }
}
