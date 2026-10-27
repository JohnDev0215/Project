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
import com.bydux.api.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: Contacts_info_numbers_RecyclerViewAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<com.byd.bluetoothcall.b.c> f3347c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f3348d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private LayoutInflater f3349e;

    /* JADX INFO: compiled from: Contacts_info_numbers_RecyclerViewAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3350b;

        a(int i) {
            this.f3350b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.byd.bluetoothcall.f.f.n(1000)) {
                return;
            }
            c.a.b.b.f0().c(((com.byd.bluetoothcall.b.c) c.this.f3347c.get(this.f3350b)).a());
            com.byd.bluetoothcall.c.a.F().s();
        }
    }

    /* JADX INFO: compiled from: Contacts_info_numbers_RecyclerViewAdapter.java */
    public static class b extends RecyclerView.c0 {
        public TextView t;
        public TextView u;
        public TextView v;
        public View w;
        public ImageView x;

        public b(View view) {
            super(view);
        }
    }

    public c(Context context, List<com.byd.bluetoothcall.b.c> list) {
        this.f3347c = new ArrayList();
        this.f3349e = LayoutInflater.from(context);
        this.f3347c = list;
        this.f3348d = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3347c.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public void j(b bVar, @SuppressLint({"RecyclerView"}) int i) {
        if (com.byd.bluetoothcall.f.f.l(this.f3348d) == 1) {
            bVar.t.setTextColor(this.f3348d.getColor(R.color.ts_ff_white_color));
            bVar.u.setTextColor(this.f3348d.getColor(R.color.ts_ff_text_color));
            bVar.v.setTextColor(this.f3348d.getColor(R.color.ts_b3_text_color));
            bVar.x.setImageResource(R.drawable.denza_bt_call_out_icon);
            bVar.w.setBackgroundColor(this.f3348d.getColor(R.color.ts_list_line_1));
        } else {
            bVar.t.setTextColor(this.f3348d.getColor(R.color.ff_white_color));
            bVar.u.setTextColor(this.f3348d.getColor(R.color.ff_text_color));
            bVar.v.setTextColor(this.f3348d.getColor(R.color.b3_text_color));
            bVar.x.setImageResource(R.drawable.bt_call_out_icon);
            bVar.w.setBackgroundColor(this.f3348d.getColor(R.color.list_line_1));
        }
        bVar.t.setText((i + 1) + "");
        bVar.u.setText(com.byd.bluetoothcall.f.e.a(this.f3347c.get(i).a()));
        String strB = com.byd.bluetoothcall.f.d.b(BlueToothApplication.a().getApplicationContext(), this.f3347c.get(i).a());
        if (TextUtils.isEmpty(strB)) {
            bVar.v.setVisibility(8);
        } else {
            bVar.v.setText(strB);
            bVar.v.setVisibility(0);
        }
        bVar.x.setOnClickListener(new a(i));
        if (i == this.f3347c.size() - 1) {
            bVar.w.setVisibility(8);
        } else {
            bVar.w.setVisibility(0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public b l(ViewGroup viewGroup, int i) {
        View viewInflate = this.f3349e.inflate(R.layout.contacts_info_calls_item, viewGroup, false);
        b bVar = new b(viewInflate);
        bVar.t = (TextView) viewInflate.findViewById(R.id.serial_number);
        bVar.u = (TextView) viewInflate.findViewById(R.id.item_number);
        bVar.v = (TextView) viewInflate.findViewById(R.id.item_location);
        bVar.w = viewInflate.findViewById(R.id.line);
        bVar.x = (ImageView) viewInflate.findViewById(R.id.item_call_icon);
        return bVar;
    }
}
