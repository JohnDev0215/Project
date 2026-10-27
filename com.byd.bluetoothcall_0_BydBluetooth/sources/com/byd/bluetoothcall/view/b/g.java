package com.byd.bluetoothcall.view.b;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bydux.api.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: compiled from: T9SearchContactsAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<com.byd.bluetoothcall.b.d> f3367b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3368c;

    /* JADX INFO: compiled from: T9SearchContactsAdapter.java */
    class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f3369a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f3370b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f3371c;

        a() {
        }
    }

    public g(List<com.byd.bluetoothcall.b.d> list, Context context) {
        this.f3367b = new ArrayList();
        this.f3367b = list;
        this.f3368c = context;
    }

    public String a(long j) {
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
                str = com.byd.bluetoothcall.f.f.u(this.f3368c) ? "aa hh:mm" : "HH:mm";
            } else if (i == 1) {
                return this.f3368c.getString(R.string.bt_yesterday);
            }
        }
        return new SimpleDateFormat(str).format(calendar2.getTime());
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3367b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.f3367b.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewInflate = LayoutInflater.from(this.f3368c).inflate(R.layout.t9_search_item, (ViewGroup) null);
        a aVar = new a();
        aVar.f3369a = (TextView) viewInflate.findViewById(R.id.t9_search_item_name);
        aVar.f3370b = (TextView) viewInflate.findViewById(R.id.t9_search_item_number);
        aVar.f3371c = (TextView) viewInflate.findViewById(R.id.t9_search_item_time);
        if (TextUtils.isEmpty(this.f3367b.get(i).e())) {
            aVar.f3369a.setText(com.byd.bluetoothcall.f.e.a(this.f3367b.get(i).g()));
        } else {
            aVar.f3369a.setText(com.byd.bluetoothcall.f.f.e(this.f3368c.getResources().getColor(R.color.search_text_color), this.f3367b.get(i).e(), this.f3367b.get(i).d()));
        }
        aVar.f3370b.setText(com.byd.bluetoothcall.f.f.e(this.f3368c.getResources().getColor(R.color.search_text_color), this.f3367b.get(i).g(), this.f3367b.get(i).d()));
        aVar.f3371c.setText(a(this.f3367b.get(i).h()));
        return viewInflate;
    }
}
