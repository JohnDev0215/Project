package c.a.d.n;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c.a.d.i;
import c.a.d.o.c;
import c.a.d.p.a;
import c.a.d.s.n;
import c.b.b.d;
import com.byd.trafficmonitor.ProtocolActivity;
import com.byd.trafficmonitor.model.TrafficInfo;
import com.byd.trafficmonitor.view.WaveView;
import com.byd.trafficmonitor.widget.MultiSizeTextView;
import com.bydux.api.R;
import com.bydux.api.common.views.window.BydFragment;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/* JADX INFO: compiled from: Service5gFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends BydFragment implements View.OnClickListener, c.a.d.r.c.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.d.t.a f3582c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WaveView f3583d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f3584e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public MultiSizeTextView f3585f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public MultiSizeTextView f3586g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.a.d.r.c.a f3587h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View f3588i;
    public c.a.a.h.a.a l;
    public View m;
    public c.a.a.h.a.a n;
    public View o;
    public View p;
    public i q;
    public List<TrafficInfo> r;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f3581b = getClass().getSimpleName();
    public boolean j = false;
    public boolean k = false;

    /* JADX INFO: compiled from: Service5gFragment.java */
    public class a implements a.b {

        /* JADX INFO: renamed from: c.a.d.n.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Service5gFragment.java */
        public class RunnableC0076a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ c.a f3590b;

            public RunnableC0076a(c.a aVar) {
                this.f3590b = aVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.p(this.f3590b);
            }
        }

        public a() {
        }

        @Override // c.a.d.p.a.b
        public void a(String str) {
        }

        @Override // c.a.d.p.a.b
        public void b(String str) {
            d dVar = new d();
            c cVar = (c) dVar.i(str, c.class);
            if (TextUtils.isEmpty(cVar.f3597b)) {
                return;
            }
            b.this.getActivity().runOnUiThread(new RunnableC0076a((c.a) dVar.i(cVar.f3597b, c.a.class)));
        }
    }

    /* JADX INFO: renamed from: c.a.d.n.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Service5gFragment.java */
    public class RunnableC0077b implements Runnable {

        /* JADX INFO: renamed from: c.a.d.n.b$b$a */
        /* JADX INFO: compiled from: Service5gFragment.java */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.q.w(b.this.r);
            }
        }

        public RunnableC0077b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = b.this;
            bVar.r = n.c(bVar.getActivity().getApplicationContext(), c.a.d.s.d.a(TimeZone.getDefault()));
            Collections.sort(b.this.r);
            if (b.this.getActivity() != null) {
                b.this.getActivity().runOnUiThread(new a());
            }
        }
    }

    public static b o() {
        return new b();
    }

    @Override // c.a.d.r.c.b
    public void a(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence) || this.f3584e == null) {
        }
    }

    public String f() {
        return this.f3581b;
    }

    public final void g() {
        c.a.a.h.a.a aVar = new c.a.a.h.a.a(getContext());
        this.n = aVar;
        aVar.setTitle(R.string.buy_5g_title);
        this.n.j(0);
        this.n.k(this.o);
        int dimension = (int) getContext().getResources().getDimension(R.dimen.dp_28);
        this.n.m(dimension, 0, dimension, (int) getContext().getResources().getDimension(R.dimen.dp_40));
    }

    public final void h() {
        View viewInflate = View.inflate(getContext(), R.layout.dialog_5g_buy, null);
        this.o = viewInflate;
        float f2 = getContext().getResources().getDisplayMetrics().density;
    }

    public final void i(View view) {
        WaveView waveView = (WaveView) view.findViewById(R.id.wave_view_call);
        this.f3583d = waveView;
        waveView.g(Color.parseColor("#2878D5F4"), Color.parseColor("#3c78D5F4"));
        this.f3583d.setUnitFormat(1);
    }

    public final void j() {
        c.a.a.h.a.a aVar = new c.a.a.h.a.a(getContext());
        this.l = aVar;
        aVar.setTitle(R.string.flow_where_about_title);
        this.l.j(0);
        this.l.k(this.m);
        this.l.m(0, 0, 0, (int) getContext().getResources().getDimension(R.dimen.dp_40));
        this.l.h();
    }

    public final View k() {
        if (this.p == null) {
            this.p = View.inflate(getContext(), R.layout.head_flow_where_about, null);
        }
        return this.p;
    }

    public final void l() {
        View viewInflate = View.inflate(getContext(), R.layout.dialog_where_about_flow, null);
        this.m = viewInflate;
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.rv_flow_where);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(this.q);
        this.q.x(k());
    }

    public final void m() {
        new c.a.d.p.a(getContext()).d(new a());
    }

    public final void n() {
        this.q = new i(getContext());
        new Thread(new RunnableC0077b()).start();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.j) {
            return;
        }
        int id = view.getId();
        if (id == R.id.ll_5g_whereabouts) {
            if (this.m == null) {
                l();
            }
            if (this.l == null) {
                j();
            }
            this.l.show();
            this.l.l((int) getResources().getDimension(R.dimen.dp_560), -2);
            return;
        }
        if (id == R.id.fl_jihuo) {
            startActivity(new Intent(getContext(), (Class<?>) ProtocolActivity.class));
            return;
        }
        if (id == R.id.fl_flow_buy) {
            if (this.o == null) {
                h();
            }
            if (this.n == null) {
                g();
            }
            this.n.show();
            this.n.l((int) getResources().getDimension(R.dimen.dp_560), (int) getResources().getDimension(R.dimen.dp_352));
        }
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.flow_5g_serivce_fragment, viewGroup, false);
        this.f3588i = viewInflate;
        viewInflate.findViewById(R.id.ll_5g_whereabouts).setOnClickListener(this);
        this.f3588i.findViewById(R.id.fl_jihuo).setOnClickListener(this);
        this.f3588i.findViewById(R.id.fl_flow_buy).setOnClickListener(this);
        this.f3584e = (TextView) this.f3588i.findViewById(R.id.tv_call_expire_des);
        this.f3585f = (MultiSizeTextView) this.f3588i.findViewById(R.id.tv_month_used);
        this.f3586g = (MultiSizeTextView) this.f3588i.findViewById(R.id.tv_total);
        this.f3587h = c.a.d.r.b.c(getActivity(), this);
        return this.f3588i;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        c.a.d.r.c.a aVar = this.f3587h;
        if (aVar != null) {
            aVar.a();
        }
        c.a.d.t.a aVar2 = this.f3582c;
        if (aVar2 != null) {
            aVar2.a();
        }
        this.k = false;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.k) {
            return;
        }
        this.k = true;
        n();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        i(view);
        m();
    }

    public final void p(c.a aVar) {
        String str = aVar.j;
        String str2 = aVar.f3605h;
        String str3 = aVar.k;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            int i2 = Integer.parseInt(str);
            int i3 = Integer.parseInt(str2);
            int i4 = i2 / 1024;
            if (i4 >= 1024) {
                this.f3583d.f(getString(R.string.flow_5g_remain), (long) (((double) i2) / Math.pow(1024.0d, 2.0d)), "GB");
            } else {
                this.f3583d.f(getString(R.string.flow_5g_remain), i4, "MB");
            }
            String str4 = this.f3581b;
            StringBuilder sb = new StringBuilder();
            sb.append("ratio:");
            float f2 = (i3 * 1.0f) / i2;
            sb.append(f2);
            Log.i(str4, sb.toString());
            this.f3583d.setWaterLevelRatio(f2);
            c.a.d.t.a aVar2 = new c.a.d.t.a(this.f3583d);
            this.f3582c = aVar2;
            aVar2.c();
            this.f3586g.setText(n.d(str));
        }
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        this.f3585f.setText(n.d(str3));
    }
}
