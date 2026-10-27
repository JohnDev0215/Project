package com.byd.engineermode.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.R;
import java.util.List;

/* JADX INFO: compiled from: BydAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f2243b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<String> f2244c;

    /* JADX INFO: compiled from: BydAdapter.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        TextView f2245a;

        private b() {
        }
    }

    public a(Context context, List<String> list) {
        this.f2243b = context;
        this.f2244c = list;
    }

    public void a(List<String> list) {
        this.f2244c = list;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public String getItem(int i) {
        return this.f2244c.get(i);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f2244c.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"InflateParams"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = LayoutInflater.from(this.f2243b).inflate(R.layout.list_view_item, (ViewGroup) null);
            bVar = new b();
            bVar.f2245a = (TextView) view.findViewById(R.id.textView);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        bVar.f2245a.setText(getItem(i));
        return view;
    }
}
