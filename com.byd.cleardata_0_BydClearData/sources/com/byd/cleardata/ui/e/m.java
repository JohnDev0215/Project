package com.byd.cleardata.ui.e;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import com.byd.cleardata.i.q;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class m extends com.byd.cleardata.ui.e.a<String> {
    private com.byd.cleardata.d.d A0;
    private boolean B0;
    private boolean C0;
    private com.byd.cleardata.ui.d.a y0;
    private List<com.byd.cleardata.d.a> z0 = new ArrayList();

    class a implements com.byd.cleardata.ui.c<String> {
        a() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(String str, int i) {
            com.byd.cleardata.d.a aVar = (com.byd.cleardata.d.a) m.this.z0.get(i);
            if (m.this.B0 && !com.byd.cleardata.i.a.j(m.this.p(), aVar, m.this.A0)) {
                q.a(R.string.error_set_default);
            } else {
                m.this.d2(aVar);
                m.this.z1();
            }
        }
    }

    class b implements CompoundButton.OnCheckedChangeListener {
        b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            m.this.B0 = z;
        }
    }

    public static m b2(com.byd.cleardata.d.d dVar) {
        return c2(dVar, false);
    }

    public static m c2(com.byd.cleardata.d.d dVar, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("key1", dVar);
        bundle.putBoolean("key2", z);
        m mVar = new m();
        mVar.q1(bundle);
        return mVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d2(com.byd.cleardata.d.a aVar) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1);
        intent.setDataAndType(com.byd.cleardata.i.e.C(y(), this.A0), com.byd.cleardata.i.e.r(this.A0.w()));
        intent.setComponent(new ComponentName(aVar.d(), aVar.a()));
        i1().startActivity(intent);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int H1() {
        return 1;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void I1(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(y(), 4));
        this.y0 = new com.byd.cleardata.ui.d.a(R.layout.layout_item_share, this.z0);
        recyclerView.h(new com.byd.cleardata.i.f(4, (int) com.byd.cleardata.i.d.b(24.0f), false));
        recyclerView.setAdapter(this.y0);
        this.y0.A(new a());
        ((CheckBox) view.findViewById(R.id.rb_check)).setOnCheckedChangeListener(new b());
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void J1(Bundle bundle) {
        org.greenrobot.eventbus.c cVarC;
        com.byd.cleardata.e.f fVar;
        super.J1(bundle);
        if (this.A0.N()) {
            this.z0.addAll(com.byd.cleardata.i.a.e(i1(), this.A0));
        }
        int size = this.z0.size();
        if (this.C0) {
            if (this.A0.K()) {
                cVarC = org.greenrobot.eventbus.c.c();
                fVar = new com.byd.cleardata.e.f(14, this.A0);
                cVarC.k(fVar);
            }
            this.y0.h();
            return;
        }
        if (size != 0) {
            if (size == 1) {
                d2(this.z0.get(0));
            }
            this.y0.h();
            return;
        } else if (this.A0.N()) {
            q.a(R.string.error_open);
        } else {
            cVarC = org.greenrobot.eventbus.c.c();
            fVar = new com.byd.cleardata.e.f(14, this.A0);
            cVarC.k(fVar);
        }
        z1();
    }

    @Override // com.byd.cleardata.ui.e.a
    protected String L1() {
        return R(R.string.open_mode);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int Q1() {
        return R.layout.dialog_open;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void U1(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_526;
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        Bundle bundleW = w();
        if (bundleW != null) {
            this.A0 = (com.byd.cleardata.d.d) bundleW.getParcelable("key1");
            this.C0 = bundleW.getBoolean("key2");
        }
    }
}
