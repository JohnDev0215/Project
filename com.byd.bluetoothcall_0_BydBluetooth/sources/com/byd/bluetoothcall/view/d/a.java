package com.byd.bluetoothcall.view.d;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.bluetoothcall.f.f;
import com.byd.bluetoothcall.view.BlueToothApplication;
import com.byd.info.CallLogInfo;
import com.bydux.api.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: Contacts_Info_WM.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements View.OnClickListener {

    /* JADX INFO: renamed from: b */
    private static a f3440b;
    private transient View A;

    /* JADX INFO: renamed from: c */
    private transient Context f3441c;

    /* JADX INFO: renamed from: d */
    private transient View f3442d;
    private transient ImageView g;
    private transient TextView h;
    private transient TextView i;
    private transient RecyclerView j;
    private transient LinearLayoutManager m;
    private transient com.byd.bluetoothcall.view.b.c n;
    private transient com.byd.bluetoothcall.view.b.b o;
    private transient RelativeLayout p;
    private transient ListView q;
    private transient TextView r;
    private transient TextView s;
    private transient RelativeLayout t;
    private transient com.byd.bluetoothcall.b.b u;
    private transient WindowManager v;
    private transient WindowManager.LayoutParams w;
    private transient ImageView x;
    private transient RelativeLayout y;
    private transient LinearLayout z;

    /* JADX INFO: renamed from: e */
    private transient int f3443e = 1024;
    private transient boolean f = false;
    private transient List<com.byd.bluetoothcall.b.c> k = new ArrayList();
    private transient List<CallLogInfo> l = new ArrayList();

    /* JADX INFO: renamed from: com.byd.bluetoothcall.view.d.a$a */
    /* JADX INFO: compiled from: Contacts_Info_WM.java */
    class C0087a implements AdapterView.OnItemClickListener {
        C0087a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i >= a.this.l.size() || f.n(1000)) {
                return;
            }
            c.a.b.b.f0().c(((CallLogInfo) a.this.l.get(i)).o());
            com.byd.bluetoothcall.c.a.F().s();
        }
    }

    private a(Context context) {
        c(context);
    }

    private void b(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.contacts_popwm_back);
        this.x = imageView;
        imageView.setOnClickListener(this);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.contacts_wm_bg);
        this.y = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.A = view.findViewById(R.id.line);
        this.z = (LinearLayout) view.findViewById(R.id.id_pop_layout);
        this.r = (TextView) view.findViewById(R.id.recent_call_history);
        this.s = (TextView) view.findViewById(R.id.bt_call_log_no_recode_text);
        this.h = (TextView) view.findViewById(R.id.first_word);
        this.g = (ImageView) view.findViewById(R.id.first_word_bg);
        this.i = (TextView) view.findViewById(R.id.name);
        this.t = (RelativeLayout) view.findViewById(R.id.listview_relativelayout);
        this.j = (RecyclerView) view.findViewById(R.id.listview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.f3441c);
        this.m = linearLayoutManager;
        linearLayoutManager.F2(1);
        this.j.setLayoutManager(this.m);
        com.byd.bluetoothcall.view.b.c cVar = new com.byd.bluetoothcall.view.b.c(this.f3441c, this.k);
        this.n = cVar;
        this.j.setAdapter(cVar);
        this.j.h(new d(this.f3441c));
        ListView listView = (ListView) view.findViewById(R.id.call_records);
        this.q = listView;
        listView.setOnItemClickListener(new C0087a());
        this.p = (RelativeLayout) view.findViewById(R.id.no_call_records);
        this.l.clear();
        com.byd.bluetoothcall.view.b.b bVar = new com.byd.bluetoothcall.view.b.b(this.l, this.f3441c);
        this.o = bVar;
        this.q.setAdapter((ListAdapter) bVar);
    }

    private void c(Context context) {
        this.f3441c = context;
    }

    public static a f() {
        if (f3440b == null) {
            f3440b = new a(BlueToothApplication.a().getApplicationContext());
        }
        return f3440b;
    }

    private void k() {
        RelativeLayout relativeLayout = this.y;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundColor(this.f3441c.getColor(R.color.ts_popover_translucent_background));
        }
        ImageView imageView = this.x;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.denza_clear_btn_n);
        }
        LinearLayout linearLayout = this.z;
        if (linearLayout != null) {
            linearLayout.setBackgroundDrawable(this.f3441c.getDrawable(R.drawable.denza_contacts_list_wm_bg));
        }
        TextView textView = this.h;
        if (textView != null) {
            textView.setTextColor(this.f3441c.getColor(R.color.ts_ff_white_color));
        }
        ImageView imageView2 = this.g;
        if (imageView2 != null) {
            imageView2.setImageResource(R.drawable.first_word_bg);
        }
        TextView textView2 = this.i;
        if (textView2 != null) {
            textView2.setTextColor(this.f3441c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView3 = this.r;
        if (textView3 != null) {
            textView3.setTextColor(this.f3441c.getColor(R.color.ts_c8_text_color));
        }
        TextView textView4 = this.s;
        if (textView4 != null) {
            textView4.setTextColor(this.f3441c.getColor(R.color.ts_c8_text_color));
        }
        RelativeLayout relativeLayout2 = this.t;
        if (relativeLayout2 != null) {
            relativeLayout2.setBackgroundDrawable(this.f3441c.getDrawable(R.drawable.denza_contacts_info_list_bg));
        }
        View view = this.A;
        if (view != null) {
            view.setBackgroundColor(this.f3441c.getColor(R.color.ts_list_line_1));
        }
        com.byd.bluetoothcall.view.b.c cVar = this.n;
        if (cVar != null) {
            cVar.h();
        }
        com.byd.bluetoothcall.view.b.b bVar = this.o;
        if (bVar != null) {
            bVar.notifyDataSetChanged();
        }
    }

    private void l() {
        RelativeLayout relativeLayout = this.y;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundColor(this.f3441c.getColor(R.color.popover_translucent_background));
        }
        ImageView imageView = this.x;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.clear_btn_n);
        }
        LinearLayout linearLayout = this.z;
        if (linearLayout != null) {
            linearLayout.setBackgroundDrawable(this.f3441c.getDrawable(R.drawable.contacts_list_wm_bg));
        }
        TextView textView = this.h;
        if (textView != null) {
            textView.setTextColor(this.f3441c.getColor(R.color.ff_white_color));
        }
        ImageView imageView2 = this.g;
        if (imageView2 != null) {
            imageView2.setImageResource(R.drawable.first_word_bg);
        }
        TextView textView2 = this.i;
        if (textView2 != null) {
            textView2.setTextColor(this.f3441c.getColor(R.color.ff_text_color));
        }
        TextView textView3 = this.r;
        if (textView3 != null) {
            textView3.setTextColor(this.f3441c.getColor(R.color.c8_text_color));
        }
        TextView textView4 = this.s;
        if (textView4 != null) {
            textView4.setTextColor(this.f3441c.getColor(R.color.c8_text_color));
        }
        RelativeLayout relativeLayout2 = this.t;
        if (relativeLayout2 != null) {
            relativeLayout2.setBackgroundDrawable(this.f3441c.getDrawable(R.drawable.shape_contactsinfo_list_bg));
        }
        View view = this.A;
        if (view != null) {
            view.setBackgroundColor(this.f3441c.getColor(R.color.list_line_1));
        }
        com.byd.bluetoothcall.view.b.c cVar = this.n;
        if (cVar != null) {
            cVar.h();
        }
        com.byd.bluetoothcall.view.b.b bVar = this.o;
        if (bVar != null) {
            bVar.notifyDataSetChanged();
        }
    }

    public void a() {
        this.u = null;
        if (this.f3442d != null) {
            ((WindowManager) this.f3441c.getSystemService("window")).removeViewImmediate(this.f3442d);
            this.f3442d = null;
            this.f = false;
        }
    }

    public void d() {
        if (this.f) {
            return;
        }
        if (f.l(this.f3441c) == 1) {
            this.f3441c.setTheme(R.style.ThemeTS);
        } else {
            this.f3441c.setTheme(R.style.ThemeTang);
        }
        this.w = new WindowManager.LayoutParams(-1, -1, 2002, 8, -2);
        this.v = (WindowManager) this.f3441c.getSystemService("window");
        if (this.f3442d == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.f3441c.getSystemService("layout_inflater");
            if (f.k() == 1) {
                this.f3442d = layoutInflater.inflate(R.layout.right_driver_contacts_info_wm_layout, (ViewGroup) null);
            } else {
                this.f3442d = layoutInflater.inflate(R.layout.contacts_info_wm_layout, (ViewGroup) null);
            }
            this.f3442d.setSystemUiVisibility(this.f3443e);
            b(this.f3442d);
        }
        this.v.addView(this.f3442d, this.w);
        this.f = true;
    }

    public com.byd.bluetoothcall.b.b g() {
        return this.u;
    }

    public void h(com.byd.bluetoothcall.b.b bVar, String str, String str2) {
        if (bVar == null) {
            return;
        }
        this.u = bVar;
        TextView textView = this.h;
        if (textView != null) {
            textView.setText(bVar.e());
            this.g.setImageLevel(bVar.g());
        }
        TextView textView2 = this.i;
        if (textView2 != null) {
            textView2.setText(bVar.i());
        }
        this.k.clear();
        this.k.addAll(bVar.j());
        RecyclerView recyclerView = this.j;
        if (recyclerView != null) {
            ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.t.getLayoutParams();
            if (this.k.size() < 2) {
                layoutParams.height = (int) this.f3441c.getResources().getDimension(R.dimen.dimens_77);
                layoutParams2.height = (int) this.f3441c.getResources().getDimension(R.dimen.dimens_77);
                layoutParams2.topMargin = (int) this.f3441c.getResources().getDimension(R.dimen.dimens_97);
            } else {
                layoutParams.height = (int) this.f3441c.getResources().getDimension(R.dimen.dimens_152);
                layoutParams2.height = (int) this.f3441c.getResources().getDimension(R.dimen.dimens_152);
                layoutParams2.topMargin = (int) this.f3441c.getResources().getDimension(R.dimen.dimens_66);
            }
            this.j.setLayoutParams(layoutParams);
            this.t.setLayoutParams(layoutParams2);
        }
        com.byd.bluetoothcall.view.b.c cVar = this.n;
        if (cVar != null) {
            cVar.h();
        }
        this.l.clear();
        for (int i = 0; i < bVar.j().size(); i++) {
            List<CallLogInfo> listC = c.a.b.c.t().c(bVar.j().get(i).a());
            if (listC != null) {
                this.l.addAll(listC);
            }
        }
        Collections.sort(this.l);
        ListView listView = this.q;
        if (listView != null) {
            ViewGroup.LayoutParams layoutParams3 = listView.getLayoutParams();
            if (this.l.size() >= 3) {
                layoutParams3.height = (int) this.f3441c.getResources().getDimension(R.dimen.dimens_225);
            } else if (this.l.size() == 2) {
                layoutParams3.height = (int) this.f3441c.getResources().getDimension(R.dimen.dimens_152);
            } else {
                layoutParams3.height = (int) this.f3441c.getResources().getDimension(R.dimen.dimens_76);
            }
            this.q.setLayoutParams(layoutParams3);
        }
        com.byd.bluetoothcall.view.b.b bVar2 = this.o;
        if (bVar2 != null) {
            bVar2.notifyDataSetChanged();
        }
        if (this.l.size() > 0) {
            this.p.setVisibility(8);
            this.q.setVisibility(0);
        } else {
            this.p.setVisibility(0);
            this.q.setVisibility(8);
        }
    }

    public boolean i() {
        return this.f;
    }

    public void j(Configuration configuration) {
        if (f.l(this.f3441c) == 1) {
            k();
        } else {
            l();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.contacts_popwm_back || id == R.id.contacts_wm_bg) {
            a();
        }
    }
}
