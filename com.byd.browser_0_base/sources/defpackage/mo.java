package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.widget.R$style;
import org.chromium.components.browser_ui.widget.chips.ChipView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.m;

/* JADX INFO: compiled from: ChipsCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mo {
    public final RecyclerView a;

    /* JADX INFO: compiled from: ChipsCoordinator.java */
    public static class a extends RecyclerView.l {
        public final int a;
        public final int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
            recyclerView.getClass();
            int I = RecyclerView.I(view);
            boolean z = I == 0;
            boolean z2 = I == recyclerView.getAdapter().k() - 1;
            int i = this.b;
            int i2 = this.a;
            rect.left = z ? i : i2;
            if (!z2) {
                i = i2;
            }
            rect.right = i;
        }
    }

    public mo(final Context context, ya1 ya1Var) {
        final int i = R$style.SuggestionChipThemeOverlay;
        RecyclerView recyclerView = new RecyclerView(context);
        this.a = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(0));
        recyclerView.getItemAnimator().f = 0L;
        m mVar = new m(ya1Var);
        mVar.H(0, new za1() { // from class: lo
            @Override // defpackage.za1
            public final View a(ViewGroup viewGroup) {
                return new ChipView(context, i);
            }
        }, new cg(26));
        recyclerView.setAdapter(mVar);
    }

    public static xa1 a(int i, int i2, String str, Callback callback) {
        PropertyModel.b bVar = new PropertyModel.b(ho.j);
        bVar.c(ho.f, i);
        bVar.e(ho.h, str);
        bVar.e(ho.b, str);
        bVar.e(ho.a, callback);
        bVar.c(ho.d, i2);
        bVar.b(ho.e, true);
        bVar.b(ho.c, true);
        bVar.b(ho.g, false);
        bVar.c(ho.i, 0);
        return new xa1(0, bVar.a());
    }
}
