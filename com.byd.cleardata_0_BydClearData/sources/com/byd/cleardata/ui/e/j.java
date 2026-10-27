package com.byd.cleardata.ui.e;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import com.byd.cleardata.i.r;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class j extends com.byd.cleardata.ui.e.a<Integer> {
    private int A0;
    private b y0;
    private List<Integer> z0 = new ArrayList();

    class a implements com.byd.cleardata.ui.c<Integer> {
        a() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Integer num, int i) {
            com.byd.cleardata.b.h().D(num.intValue());
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.i(15));
            com.byd.cleardata.b.h().A(2);
            com.byd.cleardata.b.h().z(com.byd.cleardata.d.e.PASTING);
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.d(Integer.valueOf(j.this.A0)));
            j.this.z1();
        }
    }

    public static class b extends RecyclerView.g<C0100b> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int[] f2816c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String[] f2817d;
        private final List<Integer> e;
        private com.byd.cleardata.ui.c<Integer> f;

        class a implements View.OnClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ C0100b f2818b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int f2819c;

            a(C0100b c0100b, int i) {
                this.f2818b = c0100b;
                this.f2819c = i;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (b.this.f != null) {
                    b.this.f.a((Integer) b.this.e.get(this.f2818b.j()), this.f2819c);
                }
            }
        }

        /* JADX INFO: renamed from: com.byd.cleardata.ui.e.j$b$b, reason: collision with other inner class name */
        protected static class C0100b extends RecyclerView.d0 {
            ImageView t;
            TextView u;

            public C0100b(View view) {
                super(view);
                this.t = (ImageView) view.findViewById(R.id.iv_icon);
                this.u = (TextView) view.findViewById(R.id.tv_name);
            }
        }

        public b(Context context, List<Integer> list) {
            this.e = list;
            this.f2816c = r.a(context, R.array.icon_list_medium_large);
            this.f2817d = context.getResources().getStringArray(R.array.tabs);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public C0100b o(ViewGroup viewGroup, int i) {
            return new C0100b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_medium, viewGroup, false));
        }

        public void B(com.byd.cleardata.ui.c<Integer> cVar) {
            this.f = cVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.e.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public void m(C0100b c0100b, int i) {
            int iIntValue = this.e.get(i).intValue();
            c0100b.u.setText(this.f2817d[iIntValue]);
            c0100b.t.setImageResource(this.f2816c[iIntValue]);
            c0100b.f1454b.setOnClickListener(new a(c0100b, iIntValue));
        }
    }

    public static j X1(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("key1", i);
        j jVar = new j();
        jVar.q1(bundle);
        return jVar;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int H1() {
        return 1;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void I1(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(y(), 0, false));
        this.y0 = new b(y(), this.z0);
        recyclerView.h(new com.byd.cleardata.i.p(this.m0.getResources().getDimensionPixelSize(R.dimen.dp_44), 0));
        recyclerView.setAdapter(this.y0);
        this.y0.B(new a());
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void J1(Bundle bundle) {
        super.J1(bundle);
        for (int i = 0; i < 3; i++) {
            if (com.byd.cleardata.a.f().p(i)) {
                this.z0.add(Integer.valueOf(i));
            }
        }
        this.y0.h();
    }

    @Override // com.byd.cleardata.ui.e.a
    protected String L1() {
        int i = this.A0;
        int i2 = R.string.copy_to;
        if (i != 4) {
            if (i == 5) {
                i2 = R.string.move_to;
            } else if (i == 6) {
                i2 = R.string.compress_to;
            }
        }
        return this.m0.getString(i2);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int Q1() {
        return R.layout.dialog_medium;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void U1(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_412;
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        org.greenrobot.eventbus.c.c().o(this);
        Bundle bundleW = w();
        if (bundleW != null) {
            this.A0 = bundleW.getInt("key1");
        }
    }

    @org.greenrobot.eventbus.m(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.cleardata.e.b bVar) {
        com.byd.cleardata.i.c.b("onDiskEvent");
        int iB = bVar.b();
        if (com.byd.cleardata.a.f().p(iB)) {
            this.z0.clear();
            for (int i = 0; i < 3; i++) {
                if (com.byd.cleardata.a.f().p(i)) {
                    this.z0.add(Integer.valueOf(i));
                }
            }
        } else {
            int iIndexOf = this.z0.indexOf(Integer.valueOf(iB));
            if (iIndexOf == -1) {
                return;
            } else {
                this.z0.remove(iIndexOf);
            }
        }
        this.y0.h();
    }

    @Override // androidx.fragment.app.Fragment
    public void p0() {
        org.greenrobot.eventbus.c.c().q(this);
        super.p0();
    }
}
