package com.byd.bluetoothcall.view.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bydux.api.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: SearchContactsAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<com.byd.bluetoothcall.b.b> f3360b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3361c;

    /* JADX INFO: compiled from: SearchContactsAdapter.java */
    class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f3362a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f3363b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f3364c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextView f3365d;

        a() {
        }
    }

    public f(List<com.byd.bluetoothcall.b.b> list, Context context) {
        this.f3360b = new ArrayList();
        this.f3360b = list;
        this.f3361c = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3360b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.f3360b.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.f3361c).inflate(R.layout.contacts_search_item, (ViewGroup) null);
            aVar = new a();
            aVar.f3362a = (ImageView) view.findViewById(R.id.contacts_search_item_first_word_bg);
            aVar.f3363b = (TextView) view.findViewById(R.id.contacts_search_item_first_word);
            aVar.f3364c = (TextView) view.findViewById(R.id.contacts_search_item_name);
            aVar.f3365d = (TextView) view.findViewById(R.id.contacts_search_item_number);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.f3363b.setText(this.f3360b.get(i).e());
        aVar.f3362a.setImageLevel(this.f3360b.get(i).g());
        if (com.byd.bluetoothcall.f.f.l(this.f3361c) == 1) {
            aVar.f3364c.setText(com.byd.bluetoothcall.f.f.e(this.f3361c.getResources().getColor(R.color.ts_search_text_color), this.f3360b.get(i).i(), this.f3360b.get(i).h()));
        } else {
            aVar.f3364c.setText(com.byd.bluetoothcall.f.f.e(this.f3361c.getResources().getColor(R.color.search_text_color), this.f3360b.get(i).i(), this.f3360b.get(i).h()));
        }
        int i2 = 0;
        String strA = this.f3360b.get(i).j().get(0).a();
        while (true) {
            if (i2 >= this.f3360b.get(i).j().size()) {
                break;
            }
            if (this.f3360b.get(i).j().get(i2).a().contains(this.f3360b.get(i).h())) {
                strA = this.f3360b.get(i).j().get(i2).a();
                break;
            }
            i2++;
        }
        if (com.byd.bluetoothcall.f.f.l(this.f3361c) == 1) {
            aVar.f3365d.setText(com.byd.bluetoothcall.f.f.e(this.f3361c.getResources().getColor(R.color.ts_search_text_color), strA, this.f3360b.get(i).h()));
        } else {
            aVar.f3365d.setText(com.byd.bluetoothcall.f.f.e(this.f3361c.getResources().getColor(R.color.search_text_color), strA, this.f3360b.get(i).h()));
        }
        return view;
    }
}
