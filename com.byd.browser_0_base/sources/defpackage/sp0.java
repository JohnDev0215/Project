package defpackage;

import androidx.viewpager2.adapter.FragmentStateAdapter;

/* JADX INFO: compiled from: FragmentStateAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class sp0 implements Runnable {
    public final /* synthetic */ FragmentStateAdapter e;

    public sp0(FragmentStateAdapter fragmentStateAdapter) {
        this.e = fragmentStateAdapter;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FragmentStateAdapter fragmentStateAdapter = this.e;
        fragmentStateAdapter.o = false;
        fragmentStateAdapter.G();
    }
}
