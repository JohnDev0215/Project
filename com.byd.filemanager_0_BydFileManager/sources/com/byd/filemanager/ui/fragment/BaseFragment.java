package com.byd.filemanager.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseFragment extends Fragment {
    protected String Z = getClass().getSimpleName();
    protected AppCompatActivity a0;
    protected boolean b0;

    protected abstract void A1(View view);

    protected abstract int B1();

    @Override // androidx.fragment.app.Fragment
    public void h0(Context context) {
        super.h0(context);
        this.a0 = (AppCompatActivity) context;
        com.byd.filemanager.k.b.c(this.Z, "onAttach is end");
    }

    @Override // androidx.fragment.app.Fragment
    public View o0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean zZ1 = z1();
        this.b0 = zZ1;
        if (zZ1) {
            org.greenrobot.eventbus.c.c().o(this);
        }
        View viewInflate = layoutInflater.inflate(B1(), viewGroup, false);
        A1(viewInflate);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void r0() {
        if (this.b0) {
            org.greenrobot.eventbus.c.c().q(this);
        }
        super.r0();
    }

    @Override // androidx.fragment.app.Fragment
    public void s0() {
        super.s0();
        this.a0 = null;
        com.byd.filemanager.k.b.c(this.Z, "onDetach is end " + this.b0);
    }

    protected boolean z1() {
        return false;
    }
}
