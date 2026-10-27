package b.d.a.a.v;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;

/* JADX INFO: loaded from: classes.dex */
public class i extends RecyclerView.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ r f2096a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MaterialButton f2097b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ f f2098c;

    public i(f fVar, r rVar, MaterialButton materialButton) {
        this.f2098c = fVar;
        this.f2096a = rVar;
        this.f2097b = materialButton;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.r
    public void a(RecyclerView recyclerView, int i) {
        if (i == 0) {
            recyclerView.announceForAccessibility(this.f2097b.getText());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.r
    public void b(RecyclerView recyclerView, int i, int i2) {
        LinearLayoutManager linearLayoutManagerQ = this.f2098c.Q();
        int iK1 = i < 0 ? linearLayoutManagerQ.k1() : linearLayoutManagerQ.m1();
        this.f2098c.Z = this.f2096a.j(iK1);
        MaterialButton materialButton = this.f2097b;
        r rVar = this.f2096a;
        materialButton.setText(rVar.f2119d.f2065a.i(iK1).h(rVar.f2118c));
    }
}
