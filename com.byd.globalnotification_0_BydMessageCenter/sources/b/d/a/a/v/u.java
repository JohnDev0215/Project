package b.d.a.a.v;

import android.content.Context;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class u extends LinearLayoutManager {

    public class a extends a.p.e.s {
        public a(u uVar, Context context) {
            super(context);
        }

        @Override // a.p.e.s
        public float g(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }
    }

    public u(Context context, int i, boolean z) {
        super(i, z);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public void W0(RecyclerView recyclerView, RecyclerView.y yVar, int i) {
        a aVar = new a(this, recyclerView.getContext());
        aVar.f1677a = i;
        X0(aVar);
    }
}
