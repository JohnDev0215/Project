package com.byd.filemanager.ui.b;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.k;
import com.airbnb.lottie.p;
import com.byd.filemanager.R;
import com.byd.filemanager.f;
import com.byd.filemanager.k.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MenuAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends RecyclerView.g<d> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f3711c = {R.raw.lately, R.raw.all, R.raw.audio, R.raw.video, R.raw.photo};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int[] f3712d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String[] f3713e;
    private com.byd.filemanager.ui.a<String> g;
    private Context j;
    private long k;
    private int h = -1;
    private int i = -1;
    private List<C0089c> f = new ArrayList();

    /* JADX INFO: compiled from: MenuAdapter.java */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    /* JADX INFO: compiled from: MenuAdapter.java */
    class b implements View.OnTouchListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f3715b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f3716c;

        /* JADX INFO: compiled from: MenuAdapter.java */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (SystemClock.uptimeMillis() - c.this.k < 100) {
                    if (b.this.f3715b.j() != c.this.h) {
                        b bVar = b.this;
                        c.this.L(bVar.f3715b, false);
                        return;
                    }
                    return;
                }
                c.this.k = SystemClock.uptimeMillis();
                int iJ = b.this.f3715b.j();
                com.byd.filemanager.k.b.b("onClick currentPosition = " + iJ);
                if (c.this.g != null) {
                    c.this.g.a(b.this.f3716c, iJ);
                }
                if (c.this.h != iJ) {
                    c cVar = c.this;
                    cVar.i(cVar.h);
                    c.this.i = iJ;
                    c.this.h = iJ;
                    c cVar2 = c.this;
                    cVar2.i(cVar2.h);
                    return;
                }
                if (iJ < 0 || iJ >= c.f3711c.length) {
                    return;
                }
                b.this.f3715b.t.setAnimation(c.f3711c[iJ]);
                b bVar2 = b.this;
                c.this.J(bVar2.f3715b);
                b.this.f3715b.t.o();
            }
        }

        b(d dVar, String str) {
            this.f3715b = dVar;
            this.f3716c = str;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                c.this.L(this.f3715b, true);
            } else if (action == 3 || action == 4) {
                if (c.this.h != this.f3715b.j()) {
                    c.this.L(this.f3715b, false);
                }
            } else if (action == 1) {
                this.f3715b.f1300b.post(new a());
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: com.byd.filemanager.ui.b.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MenuAdapter.java */
    private static class C0089c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3719a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f3720b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f3721c;

        public C0089c(String str, int i) {
            this.f3719a = i;
            this.f3720b = str;
        }
    }

    /* JADX INFO: compiled from: MenuAdapter.java */
    protected static class d extends RecyclerView.c0 {
        LottieAnimationView t;
        TextView u;

        public d(View view) {
            super(view);
            this.t = (LottieAnimationView) view.findViewById(R.id.iv_icon);
            this.u = (TextView) view.findViewById(R.id.tv_name);
        }
    }

    public c(Context context) {
        this.j = context;
        this.f3712d = o.a(context, R.array.icon_list_menu);
        this.f3713e = context.getResources().getStringArray(R.array.menus);
        String[] stringArray = context.getResources().getStringArray(R.array.menus);
        for (int i = 0; i < stringArray.length; i++) {
            this.f.add(new C0089c(stringArray[i], this.f3712d[i]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(d dVar) {
        dVar.t.f(new com.airbnb.lottie.model.d("**"), k.C, new com.airbnb.lottie.v.c(f.h().v() ? new p(this.j.getResources().getColor(R.color.color_highlight)) : new p(this.j.getResources().getColor(R.color.menu_text_color_sel_denza))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L(d dVar, boolean z) {
        boolean zV = f.h().v();
        int i = R.color.transparent;
        if (zV) {
            dVar.u.setTextColor(z ? this.j.getResources().getColor(R.color.color_highlight) : this.j.getResources().getColor(R.color.text_menu));
            dVar.t.setColorFilter(z ? this.j.getResources().getColor(R.color.color_highlight) : this.j.getResources().getColor(R.color.transparent));
            if (f.h().x()) {
                View view = dVar.f1300b;
                if (z) {
                    i = R.drawable.img_1menu_bg_select_rh;
                }
                view.setBackgroundResource(i);
                return;
            }
            View view2 = dVar.f1300b;
            if (z) {
                i = R.drawable.img_1menu_bg_select;
            }
            view2.setBackgroundResource(i);
            return;
        }
        dVar.u.setTextColor(z ? this.j.getResources().getColor(R.color.menu_text_color_sel_denza) : this.j.getResources().getColor(R.color.menu_text_color_unsel_denza));
        dVar.t.setColorFilter(z ? this.j.getResources().getColor(R.color.menu_text_color_sel_denza) : this.j.getResources().getColor(R.color.menu_text_color_unsel_denza));
        if (f.h().x()) {
            View view3 = dVar.f1300b;
            if (z) {
                i = R.drawable.img_1menu_bg_select_denza_rh;
            }
            view3.setBackgroundResource(i);
            return;
        }
        View view4 = dVar.f1300b;
        if (z) {
            i = R.drawable.img_1menu_bg_select_denza;
        }
        view4.setBackgroundResource(i);
    }

    public void F(int i, int i2) {
        com.byd.filemanager.k.b.b("changeItem select =" + i + " ,last = " + i2);
        Iterator<C0089c> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().f3721c = false;
        }
        C0089c c0089c = this.f.get(i);
        if (c0089c != null) {
            c0089c.f3721c = true;
            this.h = i;
        }
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public void l(d dVar, int i) {
        C0089c c0089c = this.f.get(i);
        dVar.t.setImageResource(c0089c.f3719a);
        String str = this.f3713e[i];
        dVar.u.setText(c0089c.f3720b);
        L(dVar, this.h == i);
        if (this.i == i) {
            this.i = -1;
            dVar.t.setAnimation(f3711c[i]);
            J(dVar);
            dVar.t.o();
        }
        dVar.f1300b.setOnClickListener(new a());
        dVar.f1300b.setOnTouchListener(new b(dVar, str));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
    public d n(ViewGroup viewGroup, int i) {
        return f.h().v() ? new d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_menu, viewGroup, false)) : new d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_menu_denza, viewGroup, false));
    }

    public void I(int i) {
        C0089c c0089c;
        if (this.h == i || (c0089c = this.f.get(i)) == null) {
            return;
        }
        c0089c.f3721c = true;
        int i2 = this.h;
        if (i2 != -1) {
            this.f.get(i2).f3721c = false;
        }
        this.h = i;
    }

    public void K(com.byd.filemanager.ui.a aVar) {
        this.g = aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f.size();
    }
}
