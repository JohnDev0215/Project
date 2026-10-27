package com.byd.engineermode.a.a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.R;
import com.byd.engineermode.DiagnoseApplication;
import com.byd.engineermode.bean.ElectricBean;
import com.byd.engineermode.bean.State;
import com.byd.engineermode.utils.StringArrayToList;
import java.util.List;

/* JADX INFO: compiled from: SubAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends RecyclerView.g<d> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<ElectricBean> f2265c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.byd.engineermode.a.b.a<ElectricBean> f2266d;
    private int e;

    /* JADX INFO: compiled from: SubAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ElectricBean f2267b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f2268c;

        a(ElectricBean electricBean, d dVar) {
            this.f2267b = electricBean;
            this.f2268c = dVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (f.this.f2266d != null) {
                f.this.f2266d.b(this.f2267b, this.f2268c.j(), f.this.e);
            }
        }
    }

    /* JADX INFO: compiled from: SubAdapter.java */
    class b implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ElectricBean f2270b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f2271c;

        b(ElectricBean electricBean, d dVar) {
            this.f2270b = electricBean;
            this.f2271c = dVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (f.this.f2266d != null) {
                f.this.f2266d.a(this.f2270b, this.f2271c.j(), f.this.e);
            }
        }
    }

    /* JADX INFO: compiled from: SubAdapter.java */
    static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2273a;

        static {
            int[] iArr = new int[State.values().length];
            f2273a = iArr;
            try {
                iArr[State.PASSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2273a[State.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: SubAdapter.java */
    protected static class d extends RecyclerView.c0 {
        private final TextView t;
        private final TextView u;
        private final TextView v;
        private final TextView w;
        private final TextView x;
        private final Button y;

        public d(View view) {
            super(view);
            this.t = (TextView) view.findViewById(R.id.tv_name);
            this.u = (TextView) view.findViewById(R.id.tv_short);
            this.v = (TextView) view.findViewById(R.id.tv_connect);
            this.w = (TextView) view.findViewById(R.id.tv_break);
            this.x = (TextView) view.findViewById(R.id.tv_supplier);
            this.y = (Button) view.findViewById(R.id.btn_test);
        }
    }

    public f(List<ElectricBean> list, int i) {
        this.f2265c = list;
        this.e = i;
    }

    private int x(State state) {
        int i = c.f2273a[state.ordinal()];
        return i != 1 ? i != 2 ? R.drawable.item_normal_selector : R.drawable.item_fail_selector : R.drawable.item_pass_selector;
    }

    private String y(State state) {
        int i = c.f2273a[state.ordinal()];
        return i != 1 ? i != 2 ? "--" : DiagnoseApplication.e().getResources().getString(R.string.test_fail) : DiagnoseApplication.e().getResources().getString(R.string.test_pass);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public d m(ViewGroup viewGroup, int i) {
        return new d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_electric_sub, viewGroup, false));
    }

    public void B(com.byd.engineermode.a.b.a<ElectricBean> aVar) {
        this.f2266d = aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f2265c.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public void k(d dVar, int i) {
        ElectricBean electricBean = this.f2265c.get(i);
        if (StringArrayToList.h().d(electricBean.h(), dVar.j(), this.e).equals("N/A")) {
            dVar.G(true);
        }
        dVar.t.setText(StringArrayToList.h().b(electricBean.e(), this.e));
        dVar.u.setText(y(electricBean.g()));
        dVar.v.setText(y(electricBean.b()));
        dVar.w.setText(y(electricBean.a()));
        dVar.x.setText(StringArrayToList.h().d(electricBean.h(), dVar.j(), this.e));
        dVar.u.setBackgroundResource(x(electricBean.g()));
        dVar.v.setBackgroundResource(x(electricBean.b()));
        dVar.w.setBackgroundResource(x(electricBean.a()));
        if (electricBean.i() != State.UNPERFORMED) {
            dVar.y.setText(R.string.test_again);
            dVar.y.setTextColor(x(electricBean.i()));
        }
        dVar.t.setOnClickListener(new a(electricBean, dVar));
        dVar.y.setOnClickListener(new b(electricBean, dVar));
    }
}
