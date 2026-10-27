package com.byd.bluetoothcall.view.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.bluetoothcall.view.BlueToothApplication;
import com.byd.info.CallLogInfo;
import com.bydux.api.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: compiled from: MissCall_Adapter.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends RecyclerView.g<d> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<CallLogInfo> f3352c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f3353d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3354e = -1;
    private boolean f;
    private LayoutInflater g;
    private c h;

    /* JADX INFO: compiled from: MissCall_Adapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3355b;

        a(int i) {
            this.f3355b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f3355b >= e.this.f3352c.size()) {
                return;
            }
            String string = e.this.f3353d.getString(R.string.bt_call_log_recode);
            String string2 = e.this.f3353d.getString(R.string.bt_call_log_no_recode);
            com.byd.bluetoothcall.b.b bVar = new com.byd.bluetoothcall.b.b();
            if (TextUtils.isEmpty(((CallLogInfo) e.this.f3352c.get(this.f3355b)).n())) {
                bVar.s(((CallLogInfo) e.this.f3352c.get(this.f3355b)).o());
            } else {
                bVar.s(((CallLogInfo) e.this.f3352c.get(this.f3355b)).n());
            }
            if (TextUtils.isEmpty(bVar.i())) {
                bVar.s(e.this.f3353d.getString(R.string.unknown_number));
            }
            bVar.q(bVar.i());
            com.byd.bluetoothcall.b.c cVar = new com.byd.bluetoothcall.b.c();
            cVar.c(((CallLogInfo) e.this.f3352c.get(this.f3355b)).o());
            cVar.b(com.byd.bluetoothcall.f.d.b(BlueToothApplication.a().getApplicationContext(), cVar.a()));
            ArrayList arrayList = new ArrayList();
            arrayList.add(cVar);
            bVar.t(arrayList);
            if (TextUtils.isEmpty(bVar.i())) {
                bVar.o(" ");
            } else {
                bVar.o(com.byd.bluetoothcall.f.f.f(bVar.i(), 1));
            }
            bVar.p(0);
            com.byd.bluetoothcall.view.d.a.f().d();
            com.byd.bluetoothcall.view.d.a.f().h(bVar, string, string2);
        }
    }

    /* JADX INFO: compiled from: MissCall_Adapter.java */
    class b implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f3357b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f3358c;

        b(d dVar, int i) {
            this.f3357b = dVar;
            this.f3358c = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (e.this.h != null) {
                e.this.h.a(this.f3357b.f1637b, this.f3358c);
            }
            if (this.f3358c < e.this.f3352c.size() && !com.byd.bluetoothcall.f.f.n(1000)) {
                c.a.b.b.f0().c(((CallLogInfo) e.this.f3352c.get(this.f3358c)).o());
                com.byd.bluetoothcall.c.a.F().s();
            }
            e.this.f3354e = -1;
            e.this.h();
        }
    }

    /* JADX INFO: compiled from: MissCall_Adapter.java */
    public interface c {
        void a(View view, int i);
    }

    /* JADX INFO: compiled from: MissCall_Adapter.java */
    public static class d extends RecyclerView.c0 {
        TextView t;
        TextView u;
        TextView v;
        ImageView w;

        public d(View view) {
            super(view);
        }
    }

    public e(Context context, List<CallLogInfo> list, boolean z) {
        this.f3352c = new ArrayList();
        this.f3353d = null;
        this.g = LayoutInflater.from(context);
        this.f3352c = list;
        this.f = z;
        this.f3353d = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public void j(d dVar, @SuppressLint({"RecyclerView"}) int i) {
        dVar.w.setOnClickListener(new a(i));
        dVar.f1637b.setOnClickListener(new b(dVar, i));
        if (!TextUtils.isEmpty(this.f3352c.get(i).n())) {
            dVar.t.setText(this.f3352c.get(i).n());
        } else if (TextUtils.isEmpty(this.f3352c.get(i).o())) {
            dVar.t.setText(this.f3353d.getString(R.string.unknown_number));
        } else {
            dVar.t.setText(com.byd.bluetoothcall.f.e.a(this.f3352c.get(i).o()));
        }
        if (this.f3352c.get(i).j() > 1) {
            dVar.u.setVisibility(0);
            dVar.u.setText("(" + this.f3352c.get(i).j() + ")");
        } else {
            dVar.u.setVisibility(8);
            dVar.u.setText("");
        }
        dVar.v.setText(y(this.f3352c.get(i).q()));
        if (this.f3354e == i) {
            dVar.f1637b.setSelected(true);
        } else {
            dVar.f1637b.setSelected(false);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public d l(ViewGroup viewGroup, int i) {
        View viewInflate = this.f ? com.byd.bluetoothcall.f.f.r() ? this.g.inflate(R.layout.multiwindmode_missed_ar_calls_itme_layout, viewGroup, false) : this.g.inflate(R.layout.multiwindmode_missed_calls_itme_layout, viewGroup, false) : this.g.inflate(R.layout.missed_calls_itme_layout, viewGroup, false);
        d dVar = new d(viewInflate);
        dVar.t = (TextView) viewInflate.findViewById(R.id.missed_calls_name);
        dVar.u = (TextView) viewInflate.findViewById(R.id.missed_calls_count);
        dVar.v = (TextView) viewInflate.findViewById(R.id.missed_calls_time);
        dVar.w = (ImageView) viewInflate.findViewById(R.id.missed_calls_infomation);
        return dVar;
    }

    public void C(int i) {
        if (i > this.f3352c.size() - 1) {
            return;
        }
        this.f3354e = i;
        this.f3354e = i;
        if (i >= 0 && i < this.f3352c.size()) {
            com.byd.bluetoothcall.c.a.F().H0(this.f3352c.get(this.f3354e).o());
        } else if (this.f3354e == -1) {
            com.byd.bluetoothcall.c.a.F().H0("");
        }
        h();
    }

    public void D(List<CallLogInfo> list) {
        this.f3352c = list;
        C(-1);
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3352c.size();
    }

    public String y(long j) {
        String str;
        if (j == 0) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j);
        if (calendar.get(1) == calendar2.get(1)) {
            int i = calendar.get(6) - calendar2.get(6);
            if (i == 0) {
                str = com.byd.bluetoothcall.f.f.u(this.f3353d) ? "aa hh:mm" : "HH:mm";
            } else {
                if (i == 1) {
                    return this.f3353d.getString(R.string.bt_yesterday);
                }
                str = "yyyy/MM/dd";
            }
        } else {
            str = "yyyy/MM/dd HH:mm:ss";
        }
        return new SimpleDateFormat(str).format(calendar2.getTime());
    }

    public int z() {
        return this.f3354e;
    }
}
