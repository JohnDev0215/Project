package defpackage;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SmoothCalendarLayoutManager.java */
/* JADX INFO: loaded from: classes.dex */
public class sp2 extends LinearLayoutManager {
    public sp2(int i) {
        super(i);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final void F0(RecyclerView recyclerView, int i) {
        rp2 rp2Var = new rp2(recyclerView.getContext());
        rp2Var.a = i;
        G0(rp2Var);
    }
}
