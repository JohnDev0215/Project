package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.f81;
import defpackage.om3;
import defpackage.qh3;
import java.util.WeakHashMap;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.l;

/* JADX INFO: compiled from: DateOrderedListView.java */
/* JADX INFO: loaded from: classes.dex */
public final class r60 {
    public final wc0 a;
    public final g70 b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final p60 i;
    public final a j;
    public final qh3 k;

    /* JADX INFO: compiled from: DateOrderedListView.java */
    public class a extends GridLayoutManager {

        /* JADX INFO: renamed from: r60$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DateOrderedListView.java */
        public class C0166a extends GridLayoutManager.b {
            public C0166a() {
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager.b
            public final int c(int i) {
                a aVar = a.this;
                f81 f81Var = r60.this.b.get(i);
                wc0 wc0Var = r60.this.a;
                int i2 = aVar.F;
                int iB = a91.b(f81Var, wc0Var);
                if (iB == 5 || iB == 10) {
                    return 1;
                }
                return i2;
            }
        }

        public a() {
            super(1);
            this.K = new C0166a();
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
        public final boolean H0() {
            return false;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
        public final void h0(RecyclerView.s sVar, RecyclerView.w wVar) {
            r60 r60Var = r60.this;
            p60 p60Var = r60Var.i;
            int width = p60Var.getWidth();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            int iE = ((width - om3.e.e(p60Var)) - om3.e.d(p60Var)) - (r60Var.f * 2);
            int i = r60Var.c - r60Var.d;
            int i2 = iE / i;
            if (((double) (iE - (i2 * i))) / ((double) i) > 0.5d) {
                i2++;
            }
            s1(Math.max(1, i2));
            super.h0(sVar, wVar);
        }
    }

    /* JADX INFO: compiled from: DateOrderedListView.java */
    public class b extends RecyclerView.l {
        public b() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // androidx.recyclerview.widget.RecyclerView.l
        public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
            boolean z;
            int i;
            recyclerView.getClass();
            int I = RecyclerView.I(view);
            if (I >= 0) {
                r60 r60Var = r60.this;
                if (I >= r60Var.b.size()) {
                    return;
                }
                g70 g70Var = r60Var.b;
                f81 f81Var = g70Var.get(I);
                int iB = a91.b(g70Var.get(I), r60Var.a);
                a aVar = r60Var.j;
                int i2 = r60Var.e;
                int i3 = r60Var.f;
                switch (iB) {
                    case 3:
                    case 9:
                        rect.left = i3;
                        rect.right = i3;
                        int i4 = i2 / 2;
                        rect.top = i4;
                        rect.bottom = i4;
                        z = true;
                        break;
                    case 4:
                    case 11:
                        rect.left = i3;
                        rect.right = i3;
                        int i5 = i2 / 2;
                        rect.top = i5;
                        rect.bottom = i5;
                        z = false;
                        break;
                    case 5:
                    case 6:
                    case 10:
                        z = ((f81.f) f81Var).f;
                        int i6 = r60Var.d;
                        if (z || (i = aVar.F) == 1) {
                            rect.left = i3;
                            rect.right = i3;
                        } else {
                            int iB2 = aVar.K.b(I, i);
                            int i7 = (((i - 1) * i6) + (i3 + i3)) / i;
                            int i8 = (i6 * iB2) + i3;
                            rect.left = i8 - (iB2 * i7);
                            rect.right = -(i8 - ((iB2 + 1) * i7));
                        }
                        int i9 = i6 / 2;
                        rect.top = i9;
                        rect.bottom = i9;
                        break;
                    case 7:
                    case 8:
                    default:
                        z = false;
                        break;
                    case 12:
                    case 13:
                    case 14:
                    case 16:
                        rect.left = i3;
                        rect.right = i3;
                        z = false;
                        break;
                    case 15:
                        rect.left = i3;
                        rect.right = i3;
                        rect.top = i2 / 2;
                        z = false;
                        break;
                    case 17:
                        rect.left = i3;
                        rect.right = i3;
                        rect.bottom = i2 / 2;
                        z = false;
                        break;
                }
                if (z && r60Var.k.a.a == 2) {
                    int i10 = rect.right;
                    p60 p60Var = r60Var.i;
                    int width = p60Var.getWidth();
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    rect.right = Math.max(((width - om3.e.e(p60Var)) - om3.e.d(p60Var)) - r60Var.h, 0) + i10;
                }
                boolean zA = r60.a(r60Var, I);
                int i11 = r60Var.g;
                if (zA) {
                    if (!(aVar.K.c(I) < aVar.F)) {
                        rect.bottom += i11;
                    }
                }
                if (I > 0) {
                    int i12 = I - 1;
                    if (r60.a(r60Var, i12)) {
                        if (aVar.K.c(i12) < aVar.F) {
                            rect.top += i11;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: DateOrderedListView.java */
    public class c extends org.chromium.ui.modelutil.a<Void> implements l.a<i81, Void> {
        public final g70 f;

        public c(g70 g70Var) {
            this.f = g70Var;
            g70Var.g(this);
        }

        @Override // org.chromium.ui.modelutil.l.a
        public final void a(i81 i81Var, int i, Void r3) {
            g70 g70Var = this.f;
            i81Var.u(g70Var.f.h, g70Var.get(i));
        }

        @Override // org.chromium.ui.modelutil.l.a
        public final void c(i81 i81Var) {
            i81Var.v();
        }

        @Override // org.chromium.ui.modelutil.l.a
        public final int d() {
            return this.f.size();
        }

        @Override // org.chromium.ui.modelutil.l.a
        public final int getItemViewType(int i) {
            return a91.b(this.f.get(i), r60.this.a);
        }
    }

    public r60(final Context context, wc0 wc0Var, g70 g70Var, tc0 tc0Var) {
        this.a = wc0Var;
        this.b = g70Var;
        this.c = context.getResources().getDimensionPixelSize(R$dimen.download_manager_ideal_image_width);
        this.d = context.getResources().getDimensionPixelOffset(R$dimen.download_manager_image_padding);
        this.f = context.getResources().getDimensionPixelSize(R$dimen.download_manager_horizontal_margin);
        this.e = context.getResources().getDimensionPixelSize(R$dimen.download_manager_prefetch_vertical_margin);
        this.g = context.getResources().getDimensionPixelSize(R$dimen.download_manager_vertical_margin_between_download_types);
        this.h = context.getResources().getDimensionPixelSize(R$dimen.download_manager_max_image_item_width_wide_screen);
        p60 p60Var = new p60(this, context);
        this.i = p60Var;
        p60Var.setId(R$id.download_home_recycler_view);
        p60Var.setHasFixedSize(true);
        ((androidx.recyclerview.widget.c) p60Var.getItemAnimator()).g = false;
        p60Var.getItemAnimator().e = 0L;
        a aVar = new a();
        this.j = aVar;
        p60Var.setLayoutManager(aVar);
        p60Var.g(new b());
        p60Var.setClipToPadding(false);
        j.b(g70Var.f.h, p60Var, new u81());
        s60 s60Var = new s60(g70Var, new c(g70Var), new wd0(7));
        p60Var.setAdapter(s60Var);
        p60Var.post(new qm0(1, s60Var));
        p60Var.addOnScrollListener(new q60(this, tc0Var));
        qh3 qh3Var = new qh3(p60Var);
        this.k = qh3Var;
        qh3Var.a(new db0() { // from class: o60
            @Override // defpackage.db0
            public final void d(qh3.b bVar) {
                int iMax;
                r60 r60Var = this.e;
                r60Var.getClass();
                Resources resources = context.getResources();
                if (bVar.a == 2) {
                    iMax = Math.max(resources.getDimensionPixelSize(R$dimen.download_manager_recycler_view_min_padding_wide_screen), (int) (((resources.getConfiguration().screenWidthDp - 600) / 2.0f) * resources.getDisplayMetrics().density));
                } else {
                    iMax = 0;
                }
                p60 p60Var2 = r60Var.i;
                int paddingTop = p60Var2.getPaddingTop();
                int paddingBottom = p60Var2.getPaddingBottom();
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.e.i(p60Var2, iMax, paddingTop, iMax, paddingBottom);
            }
        });
    }

    public static boolean a(r60 r60Var, int i) {
        g70 g70Var = r60Var.b;
        f81 f81Var = g70Var.get(i);
        if (!(f81Var instanceof f81.f)) {
            return false;
        }
        f81 f81Var2 = i >= g70Var.size() - 1 ? null : g70Var.get(i + 1);
        if (f81Var2 instanceof f81.f) {
            if (!(((f81.f) f81Var).e.h != ((f81.f) f81Var2).e.h)) {
                return false;
            }
        }
        return true;
    }
}
