package defpackage;

import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/* JADX INFO: compiled from: FragmentStateAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class rp0 extends FragmentManager.k {
    public final /* synthetic */ Fragment a;
    public final /* synthetic */ FrameLayout b;
    public final /* synthetic */ FragmentStateAdapter c;

    public rp0(FragmentStateAdapter fragmentStateAdapter, Fragment fragment, FrameLayout frameLayout) {
        this.c = fragmentStateAdapter;
        this.a = fragment;
        this.b = frameLayout;
    }
}
