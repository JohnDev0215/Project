package com.byd.data.collect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

/* JADX INFO: loaded from: classes.dex */
public class SecondFragment extends Fragment {
    private com.byd.data.collect.i.c Y;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: p1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void q1(View view) {
        NavHostFragment.q1(this).k(R.id.action_SecondFragment_to_FirstFragment);
    }

    @Override // androidx.fragment.app.Fragment
    public void C0(View view, Bundle bundle) {
        super.C0(view, bundle);
        this.Y.f3146b.setOnClickListener(new View.OnClickListener() { // from class: com.byd.data.collect.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f3132b.q1(view2);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View h0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        com.byd.data.collect.i.c cVarC = com.byd.data.collect.i.c.c(layoutInflater, viewGroup, false);
        this.Y = cVarC;
        return cVarC.b();
    }

    @Override // androidx.fragment.app.Fragment
    public void k0() {
        super.k0();
        this.Y = null;
    }
}
