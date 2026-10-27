package com.vivid.spotify.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: BaseCommonFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends Fragment {

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private final String f5215g0 = "BaseCommonFragment";

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private List<k> f5216h0 = new ArrayList();

    private void O1() {
        this.f5216h0.clear();
    }

    private void P1() {
        Iterator<k> it = this.f5216h0.iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
    }

    private void Q1() {
        Iterator<k> it = this.f5216h0.iterator();
        while (it.hasNext()) {
            it.next().onPause();
        }
    }

    private void R1() {
        Iterator<k> it = this.f5216h0.iterator();
        while (it.hasNext()) {
            it.next().onResume();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void B0() {
        super.B0();
        P1();
        O1();
    }

    @Override // androidx.fragment.app.Fragment
    public void K0() {
        super.K0();
        Q1();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void N1(k kVar) {
        if (this.f5216h0.contains(kVar)) {
            return;
        }
        this.f5216h0.add(kVar);
        if (k0()) {
            kVar.onResume();
        } else {
            kVar.onPause();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void P0() {
        super.P0();
        R1();
    }

    @Override // androidx.fragment.app.Fragment
    public void S0() {
        super.S0();
    }

    @Override // androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.y0(layoutInflater, viewGroup, bundle);
    }
}
