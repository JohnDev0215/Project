package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: BaseCarouselSuggestionSelectionManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cd implements RecyclerView.n {
    public int a = -1;
    public final RecyclerView.m b;

    public cd(RecyclerView.m mVar) {
        this.b = mVar;
    }

    public final void a(int i, boolean z) {
        RecyclerView.m mVar = this.b;
        if (mVar == null) {
            return;
        }
        if (i == -1 || (i >= 0 && i < mVar.D())) {
            if (z || i != this.a) {
                View viewU = mVar.u(this.a);
                if (viewU != null) {
                    viewU.setSelected(false);
                }
                this.a = i;
                mVar.v0(i);
                View viewU2 = mVar.u(i);
                if (viewU2 != null) {
                    viewU2.setSelected(true);
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public final void c(View view) {
        view.setSelected(false);
        a(this.a, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public final void d(View view) {
        a(this.a, true);
    }
}
