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

/* JADX INFO: compiled from: CallLog_Adapter.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends RecyclerView.g<d> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<CallLogInfo> f3332c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f3333d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3334e = -1;
    private boolean f;
    private LayoutInflater g;
    private c h;

    /* JADX INFO: renamed from: com.byd.bluetoothcall.view.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CallLog_Adapter.java */
    class ViewOnClickListenerC0083a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3335b;

        ViewOnClickListenerC0083a(int i) {
            this.f3335b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f3335b >= a.this.f3332c.size()) {
                return;
            }
            String string = a.this.f3333d.getString(R.string.bt_call_log_recode);
            String string2 = a.this.f3333d.getString(R.string.bt_call_log_no_recode);
            com.byd.bluetoothcall.b.b bVar = new com.byd.bluetoothcall.b.b();
            if (TextUtils.isEmpty(((CallLogInfo) a.this.f3332c.get(this.f3335b)).n())) {
                bVar.s(((CallLogInfo) a.this.f3332c.get(this.f3335b)).o());
            } else {
                bVar.s(((CallLogInfo) a.this.f3332c.get(this.f3335b)).n());
            }
            if (TextUtils.isEmpty(bVar.i())) {
                bVar.s(a.this.f3333d.getString(R.string.unknown_number));
            }
            bVar.q(bVar.i());
            com.byd.bluetoothcall.b.c cVar = new com.byd.bluetoothcall.b.c();
            cVar.c(((CallLogInfo) a.this.f3332c.get(this.f3335b)).o());
            cVar.b(com.byd.bluetoothcall.f.d.b(BlueToothApplication.a().getApplicationContext(), cVar.a()));
            ArrayList arrayList = new ArrayList();
            arrayList.add(cVar);
            bVar.t(arrayList);
            bVar.o(com.byd.bluetoothcall.f.f.f(bVar.i(), 1));
            bVar.p(0);
            com.byd.bluetoothcall.view.d.a.f().d();
            com.byd.bluetoothcall.view.d.a.f().h(bVar, string, string2);
        }
    }

    /* JADX INFO: compiled from: CallLog_Adapter.java */
    class b implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f3337b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f3338c;

        b(d dVar, int i) {
            this.f3337b = dVar;
            this.f3338c = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (a.this.h != null) {
                a.this.h.a(this.f3337b.f1637b, this.f3338c);
            }
            if (this.f3338c < a.this.f3332c.size() && !com.byd.bluetoothcall.f.f.n(1000)) {
                c.a.b.b.f0().c(((CallLogInfo) a.this.f3332c.get(this.f3338c)).o());
                com.byd.bluetoothcall.c.a.F().s();
            }
            a.this.f3334e = -1;
            a.this.h();
        }
    }

    /* JADX INFO: compiled from: CallLog_Adapter.java */
    public interface c {
        void a(View view, int i);
    }

    /* JADX INFO: compiled from: CallLog_Adapter.java */
    public static class d extends RecyclerView.c0 {
        TextView t;
        TextView u;
        ImageView v;
        TextView w;
        ImageView x;

        public d(View view) {
            super(view);
        }
    }

    public a(Context context, List<CallLogInfo> list, boolean z) {
        this.f3332c = new ArrayList();
        this.f3333d = null;
        this.g = LayoutInflater.from(context);
        this.f3332c = list;
        this.f = z;
        this.f3333d = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public void j(d dVar, @SuppressLint({"RecyclerView"}) int i) {
        dVar.x.setOnClickListener(new ViewOnClickListenerC0083a(i));
        dVar.f1637b.setOnClickListener(new b(dVar, i));
        if (!TextUtils.isEmpty(this.f3332c.get(i).n())) {
            dVar.t.setText(this.f3332c.get(i).n());
        } else if (TextUtils.isEmpty(this.f3332c.get(i).o())) {
            dVar.t.setText(this.f3333d.getString(R.string.unknown_number));
        } else {
            dVar.t.setText(com.byd.bluetoothcall.f.e.a(this.f3332c.get(i).o()));
        }
        if (this.f3332c.get(i).j() > 1) {
            dVar.u.setVisibility(0);
            dVar.u.setText("(" + this.f3332c.get(i).j() + ")");
        } else {
            dVar.u.setVisibility(8);
            dVar.u.setText("");
        }
        if (this.f3332c.get(i).r().equals("DIALED")) {
            dVar.v.setVisibility(0);
            dVar.t.setTextColor(this.f3333d.getResources().getColor(R.color.ff_text_color));
            dVar.u.setTextColor(this.f3333d.getResources().getColor(R.color.ff_text_color));
        } else if (this.f3332c.get(i).r().equals("MISSED")) {
            dVar.v.setVisibility(4);
            dVar.t.setTextColor(this.f3333d.getResources().getColor(R.color.missed_call_red_color));
            dVar.u.setTextColor(this.f3333d.getResources().getColor(R.color.missed_call_red_color));
        } else if (this.f3332c.get(i).r().equals("RECEIVED")) {
            dVar.t.setTextColor(this.f3333d.getResources().getColor(R.color.ff_text_color));
            dVar.u.setTextColor(this.f3333d.getResources().getColor(R.color.ff_text_color));
            dVar.v.setVisibility(4);
        }
        dVar.w.setText(y(this.f3332c.get(i).q()));
        if (this.f3334e == i) {
            dVar.f1637b.setSelected(true);
        } else {
            dVar.f1637b.setSelected(false);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public d l(ViewGroup viewGroup, int i) {
        View viewInflate = this.f ? com.byd.bluetoothcall.f.f.r() ? this.g.inflate(R.layout.multiwindowmode_land_ar_call_log_item_layout, viewGroup, false) : this.g.inflate(R.layout.multiwindowmode_land_call_log_item_layout, viewGroup, false) : this.g.inflate(R.layout.call_log_itme_layout, viewGroup, false);
        d dVar = new d(viewInflate);
        dVar.t = (TextView) viewInflate.findViewById(R.id.call_log_name);
        dVar.u = (TextView) viewInflate.findViewById(R.id.call_log_count);
        dVar.v = (ImageView) viewInflate.findViewById(R.id.call_log_type);
        dVar.w = (TextView) viewInflate.findViewById(R.id.call_log_time);
        dVar.x = (ImageView) viewInflate.findViewById(R.id.call_log_infomation);
        return dVar;
    }

    public void C(int i) {
        if (i > this.f3332c.size() - 1) {
            return;
        }
        this.f3334e = i;
        this.f3334e = i;
        if (i >= 0 && i < this.f3332c.size()) {
            com.byd.bluetoothcall.c.a.F().H0(this.f3332c.get(this.f3334e).o());
        } else if (this.f3334e == -1) {
            com.byd.bluetoothcall.c.a.F().H0("");
        }
        h();
    }

    public void D(List<CallLogInfo> list) {
        this.f3332c = list;
        C(-1);
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3332c.size();
    }

    public String y(long j) {
        if (j == 0) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j);
        String str = "yyyy/MM/dd";
        if (calendar.get(1) == calendar2.get(1)) {
            int i = calendar.get(6) - calendar2.get(6);
            if (i == 0) {
                str = com.byd.bluetoothcall.f.f.u(this.f3333d) ? "aa hh:mm" : "HH:mm";
            } else if (i == 1) {
                return this.f3333d.getString(R.string.bt_yesterday);
            }
        }
        return new SimpleDateFormat(str).format(calendar2.getTime());
    }

    public int z() {
        return this.f3334e;
    }
}
