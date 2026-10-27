package defpackage;

import android.R;
import android.content.Context;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class yn3 {

    /* JADX INFO: compiled from: ViewUtils.java */
    public class a implements wr1 {
        public final /* synthetic */ b e;
        public final /* synthetic */ c f;

        public a(b bVar, c cVar) {
            this.e = bVar;
            this.f = cVar;
        }

        @Override // defpackage.wr1
        public final pt3 k(View view, pt3 pt3Var) {
            return this.e.a(view, pt3Var, new c(this.f));
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public interface b {
        pt3 a(View view, pt3 pt3Var, c cVar);
    }

    public static void a(View view, b bVar) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.i.m(view, new a(bVar, new c(om3.e.e(view), view.getPaddingTop(), om3.e.d(view), view.getPaddingBottom())));
        if (om3.g.b(view)) {
            om3.h.c(view);
        } else {
            view.addOnAttachStateChangeListener(new zn3());
        }
    }

    public static float b(Context context, int i) {
        return TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static ViewGroup c(View view) {
        if (view == null) {
            return null;
        }
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(R.id.content);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView == view || !(rootView instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) rootView;
    }

    public static boolean d(View view) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return om3.e.c(view) == 1;
    }

    public static PorterDuff.Mode e(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class c {
        public int a;
        public int b;
        public int c;
        public int d;

        public c(int i, int i2, int i3, int i4) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }

        public c(c cVar) {
            this.a = cVar.a;
            this.b = cVar.b;
            this.c = cVar.c;
            this.d = cVar.d;
        }
    }
}
