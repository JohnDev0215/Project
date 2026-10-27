package com.byd.engineermode.ui.functiontest.gpstest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.R;
import java.util.List;

/* JADX INFO: compiled from: GPSLocationAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f2455b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<String> f2456c;

    /* JADX INFO: renamed from: com.byd.engineermode.ui.functiontest.gpstest.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GPSLocationAdapter.java */
    class C0072a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2457a;

        C0072a() {
        }
    }

    public a(List<String> list, Context context) {
        this.f2456c = list;
        this.f2455b = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f2456c.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.f2456c.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        C0072a c0072a;
        Context context;
        if (view != null || (context = this.f2455b) == null) {
            c0072a = (C0072a) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.gps_location_item, (ViewGroup) null);
            c0072a = new C0072a();
            c0072a.f2457a = (TextView) view.findViewById(R.id.tv_svinfo);
            view.setTag(c0072a);
        }
        c0072a.f2457a.setText(this.f2456c.get(i));
        return view;
    }
}
