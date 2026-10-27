package y6;

import A7.m;
import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class h extends ConstraintLayout {
    public x6.c B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public c f24175C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public a f24176D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public g f24177E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public w6.e f24178F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f24179G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final ArrayList f24180H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f24181I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f24182J;

    public h(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f24180H = new ArrayList();
        this.f24182J = 50;
    }

    public final void setKaraokePaint(w6.e eVar) {
        m.f(eVar, "karaokePaint");
        this.f24178F = eVar;
        for (d dVar : this.f24180H) {
            w6.e eVar2 = this.f24178F;
            if (eVar2 == null) {
                m.j("paints");
                throw null;
            }
            dVar.setColors(eVar2);
        }
        a aVar = this.f24176D;
        if (aVar == null) {
            m.j("breakView");
            throw null;
        }
        w6.e eVar3 = this.f24178F;
        if (eVar3 == null) {
            m.j("paints");
            throw null;
        }
        aVar.setBreakPaint(eVar3.f23342a.getColor());
        c cVar = this.f24175C;
        if (cVar == null) {
            m.j("cueBarView");
            throw null;
        }
        w6.e eVar4 = this.f24178F;
        if (eVar4 == null) {
            m.j("paints");
            throw null;
        }
        cVar.setColorPaint(eVar4.f23342a);
    }

    public final void setLineHeight(int i7) {
        this.f24182J = i7;
        for (d dVar : this.f24180H) {
            dVar.getLayoutParams().height = i7;
            dVar.requestLayout();
        }
    }
}
