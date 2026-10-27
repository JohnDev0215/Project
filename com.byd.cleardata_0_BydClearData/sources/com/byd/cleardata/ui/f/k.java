package com.byd.cleardata.ui.f;

import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import com.byd.cleardata.d.b;
import com.byd.cleardata.i.q;
import com.byd.cleardata.ui.e.a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class k extends i<com.byd.cleardata.f.c.a> implements com.byd.cleardata.f.a.b {
    private RecyclerView f0;
    private Button g0;
    private TextView h0;
    private com.byd.cleardata.ui.d.b i0;
    private int e0 = 0;
    private List<com.byd.cleardata.d.b> j0 = new ArrayList();

    class a implements com.byd.cleardata.ui.c<com.byd.cleardata.d.b> {

        /* JADX INFO: renamed from: com.byd.cleardata.ui.f.k$a$a, reason: collision with other inner class name */
        class C0105a implements a.c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ com.byd.cleardata.d.b f2858a;

            C0105a(com.byd.cleardata.d.b bVar) {
                this.f2858a = bVar;
            }

            @Override // com.byd.cleardata.ui.e.a.c
            public void a(int i, Object obj) {
                if (i == 0) {
                    ((com.byd.cleardata.f.c.a) k.this.Z).g(this.f2858a.y().packageName);
                }
            }
        }

        a() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(com.byd.cleardata.d.b bVar, int i) {
            com.byd.cleardata.ui.e.c cVar = new com.byd.cleardata.ui.e.c();
            cVar.V1(k.this.x());
            cVar.R1(new C0105a(bVar));
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((com.byd.cleardata.f.c.a) k.this.Z).f();
        }
    }

    private void J1() {
        ((com.byd.cleardata.f.c.a) this.Z).h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: K1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean L1() {
        J1();
        return false;
    }

    public static k M1(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        k kVar = new k();
        kVar.q1(bundle);
        return kVar;
    }

    private void N1() {
        this.h0.setText(com.byd.cleardata.i.e.k(com.byd.cleardata.i.e.A(y())));
    }

    @Override // com.byd.cleardata.ui.f.i
    protected void D1(Bundle bundle) {
        com.byd.cleardata.i.c.b("initData");
        P p = this.Z;
        if (p != 0) {
            ((com.byd.cleardata.f.c.a) p).a(this);
        }
        if (bundle == null) {
            Looper.getMainLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.byd.cleardata.ui.f.b
                @Override // android.os.MessageQueue.IdleHandler
                public final boolean queueIdle() {
                    return this.f2843a.L1();
                }
            });
            return;
        }
        com.byd.cleardata.i.c.b("restore");
        b.C0090b c0090b = (b.C0090b) bundle.getSerializable("data");
        if (c0090b != null) {
            this.j0.addAll(c0090b.a());
            this.i0.h();
        }
    }

    @Override // com.byd.cleardata.ui.f.i
    protected void E1(View view) {
        com.byd.cleardata.i.c.b("initView type = " + this.e0);
        if (this.e0 != 0) {
            Button button = (Button) view.findViewById(R.id.bt_clear_cache);
            this.g0 = button;
            button.setOnClickListener(new b());
            this.h0 = (TextView) view.findViewById(R.id.tv_current_cache);
            N1();
            return;
        }
        this.f0 = (RecyclerView) view.findViewById(R.id.recyclerView);
        com.byd.cleardata.ui.d.b bVar = new com.byd.cleardata.ui.d.b(this.j0);
        this.i0 = bVar;
        this.f0.setAdapter(bVar);
        this.f0.setLayoutManager(new LinearLayoutManager(this.c0, 1, false));
        androidx.recyclerview.widget.d dVar = new androidx.recyclerview.widget.d(this.c0, 1);
        dVar.l(androidx.core.content.a.d(this.c0, R.drawable.bg_divider));
        this.f0.h(dVar);
        this.i0.B(new a());
    }

    @Override // com.byd.cleardata.ui.f.i
    protected int F1() {
        return this.e0 == 0 ? R.layout.fragment_data : R.layout.fragment_cache;
    }

    @Override // com.byd.cleardata.ui.f.i, androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        super.G0(bundle);
        b.C0090b c0090b = new b.C0090b();
        if (this.e0 == 0) {
            c0090b.b(this.j0);
            bundle.putSerializable("data", c0090b);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.cleardata.ui.f.i
    /* JADX INFO: renamed from: I1, reason: merged with bridge method [inline-methods] */
    public com.byd.cleardata.f.c.a z1() {
        return new com.byd.cleardata.f.c.a(this);
    }

    @Override // com.byd.cleardata.f.a.b
    public void g(int i, boolean z) {
        if (i != 0) {
            if (z) {
                q.a(R.string.str_clear_cache_data_success);
            }
            N1();
        } else {
            com.byd.cleardata.i.c.b("clear data isSuccessful " + z);
            ((com.byd.cleardata.f.c.a) this.Z).h();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        this.e0 = w().getInt("type", 0);
    }

    @org.greenrobot.eventbus.m
    public void onMenuEvent(com.byd.cleardata.e.e eVar) {
        com.byd.cleardata.i.c.b("onMenuEvent liangsj ");
    }

    @Override // com.byd.cleardata.f.a.b
    public void s(List<com.byd.cleardata.d.b> list) {
        com.byd.cleardata.i.c.b("onFinished");
        A1();
        if (this.i0 != null) {
            this.j0.clear();
            if (list != null && list.size() > 0) {
                this.j0.addAll(list);
            }
            com.byd.cleardata.i.c.b("notifyDataSetChanged");
            this.i0.h();
        }
    }
}
