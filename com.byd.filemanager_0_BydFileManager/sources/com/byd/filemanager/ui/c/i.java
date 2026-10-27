package com.byd.filemanager.ui.c;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileState;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: MediumDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends com.byd.filemanager.ui.c.a<Integer> {
    private int A0;
    private b y0;
    private List<Integer> z0 = new ArrayList();

    /* JADX INFO: compiled from: MediumDialog.java */
    class a implements com.byd.filemanager.ui.a<Integer> {
        a() {
        }

        @Override // com.byd.filemanager.ui.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Integer num, int i) {
            com.byd.filemanager.f.h().L(num.intValue());
            org.greenrobot.eventbus.c.c().k(new com.byd.music.f.i(15));
            com.byd.filemanager.f.h().I(1);
            com.byd.filemanager.f.h().H(FileState.PASTING);
            org.greenrobot.eventbus.c.c().k(new com.byd.music.f.d(Integer.valueOf(i.this.A0)));
            i.this.z1();
        }
    }

    /* JADX INFO: compiled from: MediumDialog.java */
    public static class b extends RecyclerView.g<C0092b> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int[] f3757c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String[] f3758d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final List<Integer> f3759e;
        private com.byd.filemanager.ui.a<Integer> f;

        /* JADX INFO: compiled from: MediumDialog.java */
        class a implements View.OnClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ C0092b f3760b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int f3761c;

            a(C0092b c0092b, int i) {
                this.f3760b = c0092b;
                this.f3761c = i;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (b.this.f != null) {
                    b.this.f.a((Integer) b.this.f3759e.get(this.f3760b.j()), this.f3761c);
                }
            }
        }

        /* JADX INFO: renamed from: com.byd.filemanager.ui.c.i$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: MediumDialog.java */
        protected static class C0092b extends RecyclerView.c0 {
            ImageView t;
            TextView u;

            public C0092b(View view) {
                super(view);
                this.t = (ImageView) view.findViewById(R.id.iv_icon);
                this.u = (TextView) view.findViewById(R.id.tv_name);
            }
        }

        public b(Context context, List<Integer> list) {
            this.f3759e = list;
            this.f3757c = com.byd.filemanager.k.o.a(context, R.array.icon_list_medium_large);
            this.f3758d = context.getResources().getStringArray(R.array.tabs);
        }

        public void A(com.byd.filemanager.ui.a<Integer> aVar) {
            this.f = aVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f3759e.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void l(C0092b c0092b, int i) {
            int iIntValue = this.f3759e.get(i).intValue();
            c0092b.u.setText(this.f3758d[iIntValue]);
            c0092b.t.setImageResource(this.f3757c[iIntValue]);
            c0092b.f1300b.setOnClickListener(new a(c0092b, iIntValue));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public C0092b n(ViewGroup viewGroup, int i) {
            return new C0092b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_medium, viewGroup, false));
        }
    }

    public static i e2(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("key1", i);
        i iVar = new i();
        iVar.q1(bundle);
        return iVar;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int N1() {
        return 1;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void O1(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(x(), 0, false));
        this.y0 = new b(x(), this.z0);
        recyclerView.h(new com.byd.filemanager.k.m(this.m0.getResources().getDimensionPixelSize(R.dimen.dp_44), 0));
        recyclerView.setAdapter(this.y0);
        this.y0.A(new a());
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void P1(Bundle bundle) {
        for (int i = 0; i < 3; i++) {
            if (com.byd.filemanager.e.f().s(i)) {
                this.z0.add(Integer.valueOf(i));
            }
        }
        this.y0.h();
    }

    @Override // com.byd.filemanager.ui.c.a
    protected String R1() {
        int i = this.A0;
        int i2 = R.string.copy_to;
        if (i != 4) {
            if (i == 5) {
                i2 = R.string.move_to;
            } else if (i == 6) {
                i2 = R.string.compress_to;
            }
        }
        return this.m0.getString(i2);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int X1() {
        return R.layout.dialog_medium;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void b2(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_412;
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        org.greenrobot.eventbus.c.c().o(this);
        Bundle bundleV = v();
        if (bundleV != null) {
            this.A0 = bundleV.getInt("key1");
        }
    }

    @org.greenrobot.eventbus.l(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.music.f.b bVar) {
        com.byd.filemanager.k.b.b("onDiskEvent");
        int iB = bVar.b();
        if (!com.byd.filemanager.e.f().s(iB)) {
            int iIndexOf = this.z0.indexOf(Integer.valueOf(iB));
            if (iIndexOf != -1) {
                this.z0.remove(iIndexOf);
                this.y0.h();
                return;
            }
            return;
        }
        this.z0.clear();
        for (int i = 0; i < 3; i++) {
            if (com.byd.filemanager.e.f().s(i)) {
                this.z0.add(Integer.valueOf(i));
            }
        }
        this.y0.h();
    }

    @Override // androidx.fragment.app.Fragment
    public void p0() {
        org.greenrobot.eventbus.c.c().q(this);
        super.p0();
    }
}
