package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import defpackage.om3;
import defpackage.p72;
import java.util.WeakHashMap;
import org.chromium.components.browser_ui.widget.R$id;

/* JADX INFO: compiled from: ViewHighlighter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wm3 {

    /* JADX INFO: compiled from: ViewHighlighter.java */
    public static class a {
        public final int a;
        public boolean b;
        public int c;
        public p72.c d;
        public int e;
        public int f;

        public a(int i) {
            this.a = i;
        }
    }

    /* JADX INFO: compiled from: ViewHighlighter.java */
    public static class b implements p72.f {
        public final View a;
        public int b;

        public b(View view, int i) {
            this.a = view;
            this.b = i;
        }

        @Override // p72.f
        public final boolean a() {
            int i = this.b - 1;
            this.b = i;
            if (i == 0) {
                wm3.a(this.a);
            }
            return this.b > 0;
        }
    }

    public static void a(View view) {
        if (view == null) {
            return;
        }
        int i = R$id.highlight_state;
        if (view.getTag(i) != null ? ((Boolean) view.getTag(i)).booleanValue() : false) {
            view.setTag(i, Boolean.FALSE);
            Resources resources = xz.a.getResources();
            Drawable background = view.getBackground();
            if (background instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) background;
                if (layerDrawable.getNumberOfLayers() >= 2) {
                    view.setBackground(layerDrawable.getDrawable(0).getConstantState().newDrawable(resources));
                } else {
                    view.setBackground(null);
                }
            }
        }
    }

    public static void b(View view, a aVar) {
        p72 p72Var;
        if (view == null) {
            return;
        }
        if (aVar.a == 1) {
            int i = aVar.c;
            boolean z = aVar.b;
            p72.c cVar = aVar.d;
            Context context = view.getContext();
            b bVar = i > 0 ? new b(view, i) : null;
            p72Var = cVar != null ? p72.c(context, cVar, bVar) : p72.b(context, bVar);
            if (z) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                p72Var.d(om3.e.e(view), view.getPaddingTop(), om3.e.d(view), view.getPaddingBottom());
            }
        } else {
            int i2 = aVar.c;
            boolean z2 = aVar.b;
            int i3 = aVar.e;
            int i4 = aVar.f;
            Context context2 = view.getContext();
            p72Var = i2 != 0 ? new p72(context2, h21.e, new o72(i4, i3), new b(view, i2)) : new p72(context2, h21.e, new o72(i4, i3), new p72.d());
            if (z2) {
                WeakHashMap<View, in3> weakHashMap2 = om3.a;
                p72Var.d(om3.e.e(view), view.getPaddingTop(), om3.e.d(view), view.getPaddingBottom());
            }
        }
        int i5 = R$id.highlight_state;
        if (view.getTag(i5) != null ? ((Boolean) view.getTag(i5)).booleanValue() : false) {
            return;
        }
        view.getContext().getResources();
        Drawable background = view.getBackground();
        if (background != null) {
            background = background.getConstantState().newDrawable();
        }
        view.setBackground(new LayerDrawable(background == null ? new Drawable[]{p72Var} : new Drawable[]{background, p72Var}));
        view.setTag(i5, Boolean.TRUE);
        p72Var.start();
    }
}
