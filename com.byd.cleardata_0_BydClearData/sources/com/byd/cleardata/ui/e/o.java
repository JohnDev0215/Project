package com.byd.cleardata.ui.e;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import com.byd.cleardata.i.q;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class o extends com.byd.cleardata.ui.e.a<String> {
    private com.byd.cleardata.d.d A0;
    private com.byd.cleardata.ui.d.a y0;
    private List<com.byd.cleardata.d.a> z0 = new ArrayList();

    class a implements com.byd.cleardata.ui.c<String> {
        a() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(String str, int i) {
            com.byd.cleardata.d.a aVar = (com.byd.cleardata.d.a) o.this.z0.get(i);
            if (o.this.A0 != null) {
                o.this.a2(aVar);
            }
            o.this.z1();
        }
    }

    public static o Z1(com.byd.cleardata.d.d dVar) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("key1", dVar);
        o oVar = new o();
        oVar.q1(bundle);
        return oVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a2(com.byd.cleardata.d.a aVar) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType(com.byd.cleardata.i.e.r(this.A0.w()));
        intent.putExtra("android.intent.extra.STREAM", com.byd.cleardata.i.e.C(p(), this.A0));
        intent.addFlags(268435456);
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
        recyclerView.h(new com.byd.cleardata.i.f(4, L().getDimensionPixelSize(R.dimen.dp_24), false));
        recyclerView.setAdapter(this.y0);
        this.y0.A(new a());
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void J1(Bundle bundle) {
        super.J1(bundle);
        if (this.A0.N()) {
            this.z0.addAll(com.byd.cleardata.i.a.f(i1(), this.A0));
        }
        int size = this.z0.size();
        if (size == 0) {
            q.a(R.string.error_share);
        } else {
            if (size != 1) {
                this.y0.h();
                return;
            }
            a2(this.z0.get(0));
        }
        z1();
    }

    @Override // com.byd.cleardata.ui.e.a
    protected String L1() {
        return R(R.string.share_to);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int Q1() {
        return R.layout.dialog_share;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void U1(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_440;
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        Bundle bundleW = w();
        if (bundleW != null) {
            this.A0 = (com.byd.cleardata.d.d) bundleW.getParcelable("key1");
        }
    }
}
