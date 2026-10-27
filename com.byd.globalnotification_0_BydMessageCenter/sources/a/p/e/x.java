package a.p.e;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class x extends s {
    public final /* synthetic */ y q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(y yVar, Context context) {
        super(context);
        this.q = yVar;
    }

    @Override // a.p.e.s, androidx.recyclerview.widget.RecyclerView.x
    public void d(View view, RecyclerView.y yVar, RecyclerView.x.a aVar) {
        y yVar2 = this.q;
        int[] iArrA = yVar2.a(yVar2.f1151a.getLayoutManager(), view);
        int i = iArrA[0];
        int i2 = iArrA[1];
        int iCeil = (int) Math.ceil(((double) h(Math.max(Math.abs(i), Math.abs(i2)))) / 0.3356d);
        if (iCeil > 0) {
            aVar.b(i, i2, iCeil, this.j);
        }
    }

    @Override // a.p.e.s
    public float g(DisplayMetrics displayMetrics) {
        return 100.0f / displayMetrics.densityDpi;
    }

    @Override // a.p.e.s
    public int h(int i) {
        return Math.min(100, super.h(i));
    }
}
