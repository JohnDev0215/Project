package org.chromium.chrome.browser.download.settings;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.c3;
import defpackage.ma0;
import defpackage.md0;
import defpackage.pu2;
import java.util.ArrayList;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.b;

/* JADX INFO: compiled from: DownloadDirectoryAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends ArrayAdapter<Object> {
    public static final /* synthetic */ int l = 0;
    public int e;
    public final Context f;
    public final LayoutInflater g;
    public final InterfaceC0080a h;
    public final ArrayList i;
    public final ArrayList j;
    public final ArrayList k;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.download.settings.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DownloadDirectoryAdapter.java */
    public interface InterfaceC0080a {
        void e();

        void g();
    }

    public a(Context context, InterfaceC0080a interfaceC0080a) {
        super(context, R.layout.simple_spinner_item);
        this.e = -2;
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.f = context;
        this.h = interfaceC0080a;
        this.g = LayoutInflater.from(context);
    }

    public final void a() {
        int size = this.j.size() + this.i.size();
        ArrayList arrayList = this.k;
        if (size > 0) {
            arrayList.clear();
        } else {
            arrayList.add(new ma0(this.f.getString(R$string.download_location_no_available_locations), null, 0L, 0L, 2));
        }
    }

    public final void c() {
        this.i.clear();
        this.j.clear();
        this.k.clear();
        b.e.a.a(new c3(19, this));
    }

    public final int d() {
        for (int i = 0; i < getCount(); i++) {
            ma0 ma0Var = (ma0) getItem(i);
            if (ma0Var != null && ma0Var.c > 0) {
                this.e = i;
                return i;
            }
        }
        a();
        return 0;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final int getCount() {
        return this.k.size() + this.j.size() + this.i.size();
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.g.inflate(R$layout.download_location_spinner_dropdown_item, (ViewGroup) null);
        }
        view.setTag(Integer.valueOf(i));
        ma0 ma0Var = (ma0) getItem(i);
        if (ma0Var == null) {
            return view;
        }
        TextView textView = (TextView) view.findViewById(R$id.title);
        TextView textView2 = (TextView) view.findViewById(R$id.description);
        boolean zIsEnabled = isEnabled(i);
        textView.setText(ma0Var.a);
        textView.setEnabled(zIsEnabled);
        textView2.setEnabled(zIsEnabled);
        Context context = this.f;
        if (zIsEnabled) {
            textView2.setText(md0.b(context, pu2.a, ma0Var.c));
        } else if (this.k.isEmpty()) {
            textView2.setText(context.getText(R$string.download_location_not_enough_space));
        } else {
            textView2.setVisibility(8);
        }
        ((ImageView) view.findViewById(R$id.start_icon)).setVisibility(8);
        return view;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final Object getItem(int i) {
        ArrayList arrayList = this.k;
        if (!arrayList.isEmpty()) {
            return arrayList.get(i);
        }
        ArrayList arrayList2 = this.i;
        return i < arrayList2.size() ? arrayList2.get(i) : this.j.get(i - arrayList2.size());
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.g.inflate(R$layout.download_location_spinner_item, (ViewGroup) null);
        }
        view.setTag(Integer.valueOf(i));
        ma0 ma0Var = (ma0) getItem(i);
        if (ma0Var == null) {
            return view;
        }
        ((TextView) view.findViewById(R$id.title)).setText(ma0Var.a);
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i) {
        ma0 ma0Var = (ma0) getItem(i);
        return (ma0Var == null || ma0Var.c == 0) ? false : true;
    }
}
