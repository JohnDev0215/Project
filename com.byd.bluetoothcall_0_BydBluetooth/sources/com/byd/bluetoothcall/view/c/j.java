package com.byd.bluetoothcall.view.c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.r;
import com.byd.bluetoothcall.c.a;
import com.bydux.api.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: T9_SearchFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends Fragment {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient f f3429b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected transient LinearLayout f3431d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected transient ListView f3432e;
    protected transient com.byd.bluetoothcall.view.b.g f;
    private transient View g;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private transient List<com.byd.bluetoothcall.b.d> f3430c = new ArrayList();
    private a.h h = new a();

    /* JADX INFO: compiled from: T9_SearchFragment.java */
    class a implements a.h {
        a() {
        }

        @Override // com.byd.bluetoothcall.c.a.h
        public void a(String str, List<com.byd.bluetoothcall.b.d> list) {
            j.this.a(str, list);
        }
    }

    /* JADX INFO: compiled from: T9_SearchFragment.java */
    class b implements AdapterView.OnItemClickListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (com.byd.bluetoothcall.f.f.n(1000)) {
                return;
            }
            c.a.b.b.f0().c(((com.byd.bluetoothcall.b.d) j.this.f3430c.get(i)).g());
            com.byd.bluetoothcall.c.a.F().s();
        }
    }

    private void c(View view) {
        ListView listView = (ListView) view.findViewById(R.id.t9_search_contacts_list);
        this.f3432e = listView;
        listView.setOnItemClickListener(new b());
        this.f3431d = (LinearLayout) view.findViewById(R.id.no_matching_result_text);
    }

    private void d() {
        com.byd.bluetoothcall.c.a.F().j0(this.h);
        com.byd.bluetoothcall.view.b.g gVar = new com.byd.bluetoothcall.view.b.g(this.f3430c, getActivity());
        this.f = gVar;
        this.f3432e.setAdapter((ListAdapter) gVar);
    }

    public static j e(int i) {
        j jVar = new j();
        Bundle bundle = new Bundle();
        bundle.putInt("section_number", i);
        jVar.setArguments(bundle);
        return jVar;
    }

    public void a(String str, List<com.byd.bluetoothcall.b.d> list) {
        this.f3430c.clear();
        this.f3430c.addAll(list);
        com.byd.bluetoothcall.view.b.g gVar = this.f;
        if (gVar != null) {
            gVar.notifyDataSetChanged();
        }
        if (this.f3430c.size() > 0) {
            ListView listView = this.f3432e;
            if (listView != null) {
                listView.setVisibility(0);
            }
            LinearLayout linearLayout = this.f3431d;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
                return;
            }
            return;
        }
        if (str != null && str.length() == 0) {
            LinearLayout linearLayout2 = this.f3431d;
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(8);
                return;
            }
            return;
        }
        ListView listView2 = this.f3432e;
        if (listView2 != null) {
            listView2.setVisibility(8);
        }
        LinearLayout linearLayout3 = this.f3431d;
        if (linearLayout3 != null) {
            linearLayout3.setVisibility(0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3429b = (f) new r(this, r.a.b(getActivity().getApplication())).a(f.class);
        this.f3429b.d(getArguments() != null ? getArguments().getInt("section_number") : 1);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = this.g;
        if (view != null) {
            return view;
        }
        View viewInflate = layoutInflater.inflate(R.layout.t9_contacts_layout, viewGroup, false);
        this.g = viewInflate;
        c(viewInflate);
        d();
        return this.g;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        com.byd.bluetoothcall.c.a.F().M0();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        com.byd.bluetoothcall.c.a.F().M0();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }
}
