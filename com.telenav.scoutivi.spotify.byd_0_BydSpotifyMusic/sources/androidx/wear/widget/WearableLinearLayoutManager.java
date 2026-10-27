package androidx.wear.widget;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class WearableLinearLayoutManager extends LinearLayoutManager {
    private void N2() {
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void X0(RecyclerView.v vVar, RecyclerView.z zVar) {
        super.X0(vVar, zVar);
        if (J() == 0) {
            return;
        }
        N2();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i7, RecyclerView.v vVar, RecyclerView.z zVar) {
        int iY1 = super.y1(i7, vVar, zVar);
        N2();
        return iY1;
    }
}
