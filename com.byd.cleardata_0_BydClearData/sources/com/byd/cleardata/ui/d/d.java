package com.byd.cleardata.ui.d;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import com.byd.cleardata.i.r;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d extends RecyclerView.g<C0098d> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f2774c = {R.raw.lately, R.raw.all, R.raw.all};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int[] f2775d;
    private final String[] e;
    private com.byd.cleardata.ui.c<String> g;
    private Context j;
    private int k;
    private long l;
    private int h = -1;
    private int i = -1;
    private List<c> f = new ArrayList();

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    class b implements View.OnTouchListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ C0098d f2777b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f2778c;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (SystemClock.uptimeMillis() - d.this.l < 100) {
                    if (b.this.f2777b.j() != d.this.h) {
                        b bVar = b.this;
                        d.this.J(bVar.f2777b, false);
                        return;
                    }
                    return;
                }
                d.this.l = SystemClock.uptimeMillis();
                int iJ = b.this.f2777b.j();
                com.byd.cleardata.i.c.b("onClick currentPosition = " + iJ);
                if (d.this.g != null) {
                    d.this.g.a(b.this.f2778c, iJ);
                }
                if (d.this.h != iJ) {
                    d dVar = d.this;
                    dVar.i(dVar.h);
                    d.this.i = iJ;
                    d.this.h = iJ;
                    d dVar2 = d.this;
                    dVar2.i(dVar2.h);
                }
            }
        }

        b(C0098d c0098d, String str) {
            this.f2777b = c0098d;
            this.f2778c = str;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                d.this.J(this.f2777b, true);
            } else if (action == 3 || action == 4) {
                if (d.this.h != this.f2777b.j()) {
                    d.this.J(this.f2777b, false);
                }
            } else if (action == 1) {
                this.f2777b.f1454b.post(new a());
            }
            return false;
        }
    }

    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2781a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f2782b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2783c;

        public c(String str, int i) {
            this.f2781a = i;
            this.f2782b = str;
        }
    }

    /* JADX INFO: renamed from: com.byd.cleardata.ui.d.d$d, reason: collision with other inner class name */
    protected static class C0098d extends RecyclerView.d0 {
        ImageView t;
        TextView u;

        public C0098d(View view) {
            super(view);
            this.t = (ImageView) view.findViewById(R.id.iv_icon);
            this.u = (TextView) view.findViewById(R.id.tv_name);
        }
    }

    public d(Context context) {
        this.j = context;
        this.f2775d = r.a(context, R.array.icon_list_menu);
        this.e = context.getResources().getStringArray(R.array.menus);
        String[] stringArray = context.getResources().getStringArray(R.array.menus);
        for (int i = 0; i < stringArray.length; i++) {
            this.f.add(new c(stringArray[i], this.f2775d[i]));
        }
        this.k = !com.byd.cleardata.i.d.d(context) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(C0098d c0098d, boolean z) {
        TextView textView = c0098d.u;
        Resources resources = this.j.getResources();
        textView.setTextColor(z ? resources.getColor(R.color.color_highlight) : resources.getColor(R.color.text_menu));
        c0098d.t.setColorFilter(z ? this.j.getResources().getColor(R.color.color_highlight) : this.j.getResources().getColor(R.color.transparent));
        if (!z) {
            c0098d.f1454b.setBackgroundResource(R.color.transparent);
            return;
        }
        if (this.k != 0) {
            c0098d.f1454b.setBackgroundResource(R.drawable.img_1menu_bg_select_v);
        } else if (com.byd.cleardata.i.a.i()) {
            c0098d.f1454b.setBackground(new BitmapDrawable(com.byd.cleardata.i.b.a(BitmapFactory.decodeResource(this.j.getResources(), R.drawable.img_1menu_bg_select_h), 180.0f)));
        } else {
            c0098d.f1454b.setBackgroundResource(R.drawable.img_1menu_bg_select_h);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public void m(C0098d c0098d, int i) {
        c cVar = this.f.get(i);
        c0098d.t.setImageResource(cVar.f2781a);
        String str = this.e[i];
        c0098d.u.setText(cVar.f2782b);
        J(c0098d, this.h == i);
        if (this.i == i) {
            this.i = -1;
        }
        c0098d.f1454b.setOnClickListener(new a());
        c0098d.f1454b.setOnTouchListener(new b(c0098d, str));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public C0098d o(ViewGroup viewGroup, int i) {
        return i == 0 ? com.byd.cleardata.i.a.i() ? new C0098d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_menu_vertical_right_rudder, viewGroup, false)) : new C0098d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_menu_vertical, viewGroup, false)) : new C0098d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_menu_horizontal, viewGroup, false));
    }

    public void G(int i) {
        c cVar;
        if (this.h == i || (cVar = this.f.get(i)) == null) {
            return;
        }
        cVar.f2783c = true;
        int i2 = this.h;
        if (i2 != -1) {
            this.f.get(i2).f2783c = false;
        }
        this.h = i;
    }

    public void H(com.byd.cleardata.ui.c cVar) {
        this.g = cVar;
    }

    public void I(int i) {
        this.k = i;
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int e(int i) {
        return this.k;
    }
}
