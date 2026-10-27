package com.byd.cleardata.ui.f;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends Fragment {
    protected androidx.appcompat.app.c Z;
    protected boolean a0;

    protected abstract void A1(View view);

    protected abstract int B1();

    @Override // androidx.fragment.app.Fragment
    public void h0(Context context) {
        super.h0(context);
        this.Z = (androidx.appcompat.app.c) context;
    }

    @Override // androidx.fragment.app.Fragment
    public View o0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean zZ1 = z1();
        this.a0 = zZ1;
        if (zZ1) {
            org.greenrobot.eventbus.c.c().o(this);
        }
        View viewInflate = layoutInflater.inflate(B1(), viewGroup, false);
        A1(viewInflate);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void r0() {
        if (this.a0) {
            org.greenrobot.eventbus.c.c().q(this);
        }
        super.r0();
    }

    @Override // androidx.fragment.app.Fragment
    public void s0() {
        super.s0();
        this.Z = null;
    }

    protected boolean z1() {
        return false;
    }
}
