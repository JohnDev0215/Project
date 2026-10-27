package com.byd.logtools.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import com.byd.logtools.R;
import java.io.File;
import java.util.List;

/* JADX INFO: compiled from: BydAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f3218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<File> f3219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<String> f3220d;

    /* JADX INFO: renamed from: com.byd.logtools.tools.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydAdapter.java */
    private static class C0059b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        CheckBox f3221a;

        private C0059b() {
        }
    }

    public b(Context context, List<File> list) {
        this.f3218b = context;
        this.f3219c = list;
    }

    public void a(List<File> list, List<String> list2) {
        this.f3220d = list2;
        this.f3219c = list;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public String getItem(int i) {
        return this.f3219c.get(i).getName();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3219c.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"InflateParams"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        C0059b c0059b;
        if (view == null) {
            view = LayoutInflater.from(this.f3218b).inflate(R.layout.history_log_list_view_item, (ViewGroup) null);
            c0059b = new C0059b();
            c0059b.f3221a = (CheckBox) view.findViewById(R.id.checkBox1);
            view.setTag(c0059b);
        } else {
            c0059b = (C0059b) view.getTag();
        }
        c0059b.f3221a.setText(getItem(i));
        c0059b.f3221a.setClickable(false);
        c0059b.f3221a.setChecked(this.f3220d.contains(getItem(i)));
        return view;
    }
}
