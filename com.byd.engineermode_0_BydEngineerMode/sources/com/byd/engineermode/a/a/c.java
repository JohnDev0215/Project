package com.byd.engineermode.a.a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.R;
import com.byd.engineermode.bean.FunctionBean;
import com.byd.engineermode.bean.State;
import com.byd.engineermode.utils.StringArrayToList;
import java.util.List;

/* JADX INFO: compiled from: FunctionAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends RecyclerView.g<C0066c> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2249c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2250d;
    private List<FunctionBean> e;
    private com.byd.engineermode.a.b.a<FunctionBean> f;

    /* JADX INFO: compiled from: FunctionAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ FunctionBean f2251b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ C0066c f2252c;

        a(FunctionBean functionBean, C0066c c0066c) {
            this.f2251b = functionBean;
            this.f2252c = c0066c;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (c.this.f != null) {
                c.this.f.a(this.f2251b, this.f2252c.j(), -1);
            }
        }
    }

    /* JADX INFO: compiled from: FunctionAdapter.java */
    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2254a;

        static {
            int[] iArr = new int[State.values().length];
            f2254a = iArr;
            try {
                iArr[State.PASSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2254a[State.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: renamed from: com.byd.engineermode.a.a.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FunctionAdapter.java */
    protected static class C0066c extends RecyclerView.c0 {
        TextView t;

        public C0066c(View view) {
            super(view);
            this.t = (TextView) view.findViewById(R.id.tv_function);
        }
    }

    public c(List<FunctionBean> list) {
        this.e = list;
    }

    private int w(State state) {
        int i = b.f2254a[state.ordinal()];
        return i != 1 ? i != 2 ? R.drawable.item_normal_selector : R.drawable.item_fail_selector : R.drawable.item_pass_selector;
    }

    public void A(com.byd.engineermode.a.b.a<FunctionBean> aVar) {
        this.f = aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.e.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public void k(C0066c c0066c, int i) {
        FunctionBean functionBean = this.e.get(i);
        ViewGroup.LayoutParams layoutParams = c0066c.f1135b.getLayoutParams();
        layoutParams.height = this.f2249c;
        layoutParams.width = this.f2250d;
        c0066c.f1135b.setLayoutParams(layoutParams);
        c0066c.t.setText(StringArrayToList.h().a(functionBean.f()));
        c0066c.t.setBackgroundResource(w(functionBean.h()));
        c0066c.f1135b.setOnClickListener(new a(functionBean, c0066c));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public C0066c m(ViewGroup viewGroup, int i) {
        return new C0066c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_function, viewGroup, false));
    }

    public void z(int i, int i2) {
        this.f2250d = i;
        this.f2249c = i2;
    }
}
