package defpackage;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.om3;
import defpackage.qh3;
import java.util.WeakHashMap;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.components.browser_ui.widget.FadingShadowView;
import org.chromium.ui.modelutil.m;

/* JADX INFO: compiled from: TutorialListCoordinatorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sg3 {
    public final ViewGroup a;
    public qh3 b;

    /* JADX INFO: compiled from: TutorialListCoordinatorImpl.java */
    public class a extends RecyclerView.l {
        public final int a;
        public final int b;

        public a(Resources resources) {
            int i = R$dimen.card_padding;
            this.a = resources.getDimensionPixelOffset(i);
            this.b = resources.getDimensionPixelOffset(i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
            int i = this.a;
            rect.top = i / 2;
            rect.bottom = i / 2;
            int i2 = this.b;
            rect.left = i2;
            rect.right = i2;
        }
    }

    public sg3(ViewGroup viewGroup, em3 em3Var, ax0 ax0Var, c3 c3Var) {
        this.a = viewGroup;
        ya1 ya1Var = new ya1();
        m mVar = new m(ya1Var);
        mVar.H(3, new bd(1), new ag(20));
        RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R$id.recycler_view);
        recyclerView.setAdapter(mVar);
        recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        recyclerView.g(new a(recyclerView.getResources()));
        new ug3(ya1Var, recyclerView.getContext(), em3Var, ax0Var, c3Var);
        FadingShadowView fadingShadowView = (FadingShadowView) viewGroup.findViewById(R$id.toolbar_shadow);
        fadingShadowView.a(fadingShadowView.getContext().getColor(R$color.toolbar_shadow_color));
        recyclerView.addOnScrollListener(new qg3(fadingShadowView));
        qh3 qh3Var = new qh3(viewGroup);
        this.b = qh3Var;
        qh3Var.a(new db0() { // from class: pg3
            @Override // defpackage.db0
            public final void d(qh3.b bVar) {
                int iMax;
                ViewGroup viewGroup2 = this.e.a;
                Resources resources = viewGroup2.getResources();
                if (bVar.a == 2) {
                    iMax = Math.max(resources.getDimensionPixelSize(R$dimen.card_padding), (int) (((resources.getConfiguration().screenWidthDp - 600) / 2.0f) * resources.getDisplayMetrics().density));
                } else {
                    iMax = 0;
                }
                View viewFindViewById = viewGroup2.findViewById(R$id.recycler_view);
                View viewFindViewById2 = viewGroup2.findViewById(R$id.toolbar);
                int paddingTop = viewFindViewById.getPaddingTop();
                int paddingBottom = viewFindViewById.getPaddingBottom();
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.e.i(viewFindViewById, iMax, paddingTop, iMax, paddingBottom);
                om3.e.i(viewFindViewById2, iMax, viewFindViewById2.getPaddingTop(), iMax, viewFindViewById2.getPaddingBottom());
            }
        });
        viewGroup.addView(new rg3(this, viewGroup.getContext()));
    }
}
