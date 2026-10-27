package com.byd.bluetoothcall.view.c;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bydux.api.R;
import java.util.List;

/* JADX INFO: compiled from: SortAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends RecyclerView.g<c> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f3423c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<com.byd.bluetoothcall.b.b> f3424d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f3425e;
    private int f = -1;
    private b g;

    /* JADX INFO: compiled from: SortAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3426b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c f3427c;

        a(int i, c cVar) {
            this.f3426b = i;
            this.f3427c = cVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f3426b >= i.this.f3424d.size()) {
                return;
            }
            if (i.this.g != null) {
                i.this.g.a(this.f3427c.f1637b, this.f3426b);
            }
            String string = i.this.f3425e.getString(R.string.bt_call_log_recode);
            String string2 = i.this.f3425e.getString(R.string.bt_call_log_no_recode);
            com.byd.bluetoothcall.view.d.a.f().d();
            com.byd.bluetoothcall.view.d.a.f().h((com.byd.bluetoothcall.b.b) i.this.f3424d.get(this.f3426b), string, string2);
            i.this.h();
        }
    }

    /* JADX INFO: compiled from: SortAdapter.java */
    public interface b {
        void a(View view, int i);
    }

    /* JADX INFO: compiled from: SortAdapter.java */
    public static class c extends RecyclerView.c0 {
        TextView t;
        ImageView u;
        TextView v;

        public c(View view) {
            super(view);
        }
    }

    public i(Context context, List<com.byd.bluetoothcall.b.b> list) {
        this.f3423c = LayoutInflater.from(context);
        this.f3424d = list;
        this.f3425e = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public void j(c cVar, @SuppressLint({"RecyclerView"}) int i) {
        cVar.f1637b.setOnClickListener(new a(i, cVar));
        cVar.t.setText(this.f3424d.get(i).i());
        cVar.u.setImageLevel(i % 5);
        cVar.v.setText(this.f3424d.get(i).e());
        if (this.f == i) {
            cVar.f1637b.setSelected(true);
        } else {
            cVar.f1637b.setSelected(false);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public c l(ViewGroup viewGroup, int i) {
        View viewInflate = this.f3423c.inflate(R.layout.contacts_item_layout, viewGroup, false);
        c cVar = new c(viewInflate);
        cVar.t = (TextView) viewInflate.findViewById(R.id.contacts_name);
        cVar.u = (ImageView) viewInflate.findViewById(R.id.first_word_bg);
        cVar.v = (TextView) viewInflate.findViewById(R.id.first_word);
        return cVar;
    }

    public void C(int i) {
        if (i > this.f3424d.size() - 1) {
            return;
        }
        this.f = i;
        if (i >= 0 && i < this.f3424d.size()) {
            com.byd.bluetoothcall.c.a.F().H0(this.f3424d.get(i).j().size() > 0 ? this.f3424d.get(i).j().get(0).a() : "");
        } else if (i == -1) {
            com.byd.bluetoothcall.c.a.F().H0("");
        }
        h();
    }

    public void D(List<com.byd.bluetoothcall.b.b> list) {
        this.f3424d = list;
        C(-1);
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3424d.size();
    }

    public int x(int i) {
        for (int i2 = 0; i2 < c(); i2++) {
            if (this.f3424d.get(i2).d().toUpperCase().charAt(0) == i) {
                return i2;
            }
        }
        return -1;
    }

    public String y(int i) {
        return (i <= 0 || i >= this.f3424d.size()) ? this.f3424d.get(0).d() : this.f3424d.get(i).d();
    }

    public int z() {
        return this.f;
    }
}
