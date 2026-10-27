package org.chromium.chrome.browser.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ao3;
import defpackage.c81;
import defpackage.d81;
import defpackage.j81;
import defpackage.uh3;
import defpackage.zv0;
import java.util.ArrayList;
import org.chromium.chrome.browser.feed.f;

/* JADX INFO: loaded from: classes.dex */
public class NativeViewListRenderer extends RecyclerView.e<b> implements zv0, d81 {
    public final Context h;
    public c81 i;
    public a j;
    public RecyclerView k;

    public class a implements j81 {
        public final LinearLayoutManager a;

        public a(LinearLayoutManager linearLayoutManager) {
            this.a = linearLayoutManager;
        }

        public final void a(int i, int i2) {
            this.a.g1(i, i2);
        }

        @Override // defpackage.j81
        public final int b() {
            return this.a.b();
        }

        @Override // defpackage.j81
        public final int d() {
            return this.a.d();
        }
    }

    public static class b extends RecyclerView.a0 {
        public b(View view) {
            super(view);
        }
    }

    public NativeViewListRenderer(Context context) {
        this.h = context;
    }

    @Override // defpackage.d81
    public final void e(int i, int i2) {
        p(i, i2);
    }

    @Override // defpackage.d81
    public final void g(int i, int i2) {
        q(i, i2);
    }

    @Override // defpackage.d81
    public final void h(int i, int i2) {
        r(i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return ((f) this.i).d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int m(int i) {
        if (!((f) this.i).a.get(i).a()) {
            return -1;
        }
        f fVar = (f) this.i;
        f.c cVar = (f.c) fVar.a.get(i);
        if (cVar.d == 0) {
            int i2 = fVar.d + 1;
            fVar.d = i2;
            cVar.d = i2;
        }
        return cVar.d;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void t(RecyclerView.a0 a0Var, int i) {
        if (((f) this.i).a.get(i).a()) {
            this.i.getClass();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        View view;
        f.c cVar;
        if (i >= 0) {
            f fVar = (f) this.i;
            int i2 = 0;
            while (true) {
                ArrayList<f.b> arrayList = fVar.a;
                if (i2 >= arrayList.size()) {
                    cVar = null;
                    break;
                }
                f.b bVar = arrayList.get(i2);
                if (bVar.a()) {
                    cVar = (f.c) bVar;
                    if (cVar.d == i) {
                        break;
                    }
                }
                i2++;
            }
            cVar.getClass();
            Context context = recyclerView.getContext();
            if (cVar.b == null) {
                cVar.b = LayoutInflater.from(context).inflate(cVar.c, (ViewGroup) recyclerView, false);
            }
            uh3.l(cVar.b);
            FrameLayout frameLayout = new FrameLayout(recyclerView.getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(new ViewGroup.LayoutParams(-1, -2)));
            int i3 = cVar.e;
            frameLayout.setPadding(i3, 0, i3, 0);
            frameLayout.setClipToPadding(false);
            frameLayout.setClipChildren(false);
            frameLayout.addView(cVar.b);
            view = frameLayout;
        } else {
            Context context2 = this.h;
            AppCompatTextView appCompatTextView = new AppCompatTextView(context2);
            appCompatTextView.setText("Unable to render external view");
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
            marginLayoutParams.bottomMargin = ao3.a(context2, 25.0f);
            marginLayoutParams.topMargin = ao3.a(context2, 25.0f);
            appCompatTextView.setLayoutParams(marginLayoutParams);
            view = appCompatTextView;
        }
        return new b(view);
    }
}
