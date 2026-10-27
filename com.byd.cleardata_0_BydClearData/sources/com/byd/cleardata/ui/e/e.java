package com.byd.cleardata.ui.e;

import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.FileApplication;
import com.byd.cleardata.R;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class e extends com.byd.cleardata.ui.e.a {
    private com.byd.cleardata.d.d A0;
    private c B0;
    private List<com.byd.cleardata.d.c> C0 = new ArrayList();
    private final c.a.j.a D0 = new c.a.j.a();
    private String[] y0;
    private int z0;

    class a implements c.a.l.d<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.d.c f2802a;

        a(com.byd.cleardata.d.c cVar) {
            this.f2802a = cVar;
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(String str) {
            this.f2802a.c(str);
            e.this.B0.i(e.this.C0.indexOf(this.f2802a));
        }
    }

    class b implements c.a.l.d<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.d.c f2804a;

        b(com.byd.cleardata.d.c cVar) {
            this.f2804a = cVar;
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(String str) {
            this.f2804a.c(str);
            e.this.B0.i(e.this.C0.indexOf(this.f2804a));
        }
    }

    private static class c extends RecyclerView.g<a> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private List<com.byd.cleardata.d.c> f2806c;

        protected static class a extends RecyclerView.d0 {
            TextView t;
            TextView u;

            public a(View view) {
                super(view);
                this.t = (TextView) view.findViewById(R.id.tv_item);
                this.u = (TextView) view.findViewById(R.id.tv_desc);
            }
        }

        public c(List<com.byd.cleardata.d.c> list) {
            this.f2806c = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f2806c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void m(a aVar, int i) {
            com.byd.cleardata.d.c cVar = this.f2806c.get(i);
            aVar.t.setText(cVar.a());
            aVar.u.setText(cVar.b());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public a o(ViewGroup viewGroup, int i) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_detail, viewGroup, false));
        }
    }

    private String Y1(long j) {
        if (j <= 0) {
            return "";
        }
        return new SimpleDateFormat(FileApplication.a().getString("12".equals(Settings.System.getString(FileApplication.a().getContentResolver(), "time_12_24")) ? R.string.time_format_12 : R.string.time_format_24), Locale.getDefault()).format(Long.valueOf(j));
    }

    private String Z1(File file) {
        String strJ;
        Resources resources;
        int i;
        String path = file.getPath();
        if (com.byd.cleardata.a.f().q(file, 0)) {
            strJ = com.byd.cleardata.a.f().k(0);
            resources = FileApplication.a().getResources();
            i = R.string.local;
        } else if (com.byd.cleardata.a.f().q(file, 1)) {
            strJ = com.byd.cleardata.a.f().j(1);
            if (!path.startsWith(strJ)) {
                strJ = com.byd.cleardata.a.f().k(1);
            }
            resources = FileApplication.a().getResources();
            i = R.string.sdcard;
        } else {
            if (!com.byd.cleardata.a.f().q(file, 2)) {
                return path;
            }
            strJ = com.byd.cleardata.a.f().j(2);
            if (!path.startsWith(strJ)) {
                strJ = com.byd.cleardata.a.f().k(2);
            }
            resources = FileApplication.a().getResources();
            i = R.string.usb;
        }
        return path.replace(strJ, resources.getString(i));
    }

    private void a2(com.byd.cleardata.d.d dVar, com.byd.cleardata.d.c cVar) {
        this.D0.c(com.byd.cleardata.g.d.f(dVar).k(c.a.o.a.b()).f(c.a.i.b.a.a()).h(new b(cVar)));
    }

    private void b2() {
        com.byd.cleardata.i.c.b("initList");
        this.C0.clear();
        boolean zC2 = c2(this.A0);
        this.z0 = zC2 ? 1 : 0;
        if (zC2) {
            String[] stringArray = FileApplication.a().getResources().getStringArray(R.array.file_attr_img_items);
            this.y0 = stringArray;
            this.C0.add(new com.byd.cleardata.d.c(stringArray[0], this.A0.C()));
            this.C0.add(new com.byd.cleardata.d.c(this.y0[1], Y1(com.byd.cleardata.i.e.u(this.A0.w()))));
            com.byd.cleardata.d.c cVar = new com.byd.cleardata.d.c(this.y0[2], FileApplication.a().getString(R.string.unknown));
            this.C0.add(cVar);
            a2(this.A0, cVar);
            com.byd.cleardata.d.c cVar2 = new com.byd.cleardata.d.c(this.y0[3], FileApplication.a().getString(R.string.counting));
            this.C0.add(cVar2);
            e2(this.A0, cVar2);
            String strW = this.A0.G() == com.byd.cleardata.d.f.IMAGE ? com.byd.cleardata.i.e.w(this.A0.w()) : com.byd.cleardata.i.e.E(this.A0.w());
            if (strW != null) {
                this.C0.add(new com.byd.cleardata.d.c(this.y0[4], strW));
            } else {
                this.C0.add(new com.byd.cleardata.d.c(this.y0[4], FileApplication.a().getString(R.string.unknown)));
            }
            this.C0.add(new com.byd.cleardata.d.c(this.y0[5], Z1(this.A0.w())));
        } else {
            String[] stringArray2 = FileApplication.a().getResources().getStringArray(R.array.file_attr_items);
            this.y0 = stringArray2;
            this.C0.add(new com.byd.cleardata.d.c(stringArray2[0], this.A0.C()));
            com.byd.cleardata.d.c cVar3 = new com.byd.cleardata.d.c(this.y0[1], FileApplication.a().getString(R.string.counting));
            this.C0.add(cVar3);
            e2(this.A0, cVar3);
            this.C0.add(new com.byd.cleardata.d.c(this.y0[2], FileApplication.a().getString(this.A0.H())));
            this.C0.add(new com.byd.cleardata.d.c(this.y0[3], Z1(this.A0.w())));
        }
        this.B0.h();
    }

    private boolean c2(com.byd.cleardata.d.d dVar) {
        if (dVar.G() == com.byd.cleardata.d.f.IMAGE || dVar.G() == com.byd.cleardata.d.f.VIDEO) {
            String strE = dVar.E();
            String strK = com.byd.cleardata.a.f().k(0);
            if (strK != null) {
                if (strE.startsWith(strK + "/DCIM/Camera")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static e d2(com.byd.cleardata.d.d dVar) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("file", dVar);
        e eVar = new e();
        eVar.q1(bundle);
        return eVar;
    }

    private void e2(com.byd.cleardata.d.d dVar, com.byd.cleardata.d.c cVar) {
        this.D0.c(com.byd.cleardata.g.d.e(dVar).k(c.a.o.a.b()).f(c.a.i.b.a.a()).h(new a(cVar)));
    }

    @Override // com.byd.cleardata.ui.e.a, androidx.fragment.app.Fragment
    public void F0() {
        com.byd.cleardata.i.c.b("onResume");
        b2();
        super.F0();
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int H1() {
        return 2;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void I1(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(y(), 1, false));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        layoutParams.bottomMargin = (int) com.byd.cleardata.i.d.b(this.z0 == 0 ? 6.0f : 2.0f);
        recyclerView.setLayoutParams(layoutParams);
        c cVar = new c(this.C0);
        this.B0 = cVar;
        recyclerView.setAdapter(cVar);
        this.n0.setText(R.string.btn_known);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected String L1() {
        return R(R.string.detail);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int Q1() {
        return R.layout.dialog_detail;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void U1(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = this.z0 == 0 ? R.dimen.dp_422 : R.dimen.dp_538;
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        org.greenrobot.eventbus.c.c().o(this);
        Bundle bundleW = w();
        if (bundleW != null) {
            this.A0 = (com.byd.cleardata.d.d) bundleW.getParcelable("file");
        }
    }

    @org.greenrobot.eventbus.m(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.cleardata.e.b bVar) {
        com.byd.cleardata.i.c.b("onDiskEvent");
        int iB = bVar.b();
        if (iB != com.byd.cleardata.b.h().k() || com.byd.cleardata.a.f().p(iB)) {
            return;
        }
        z1();
    }

    @Override // androidx.fragment.app.Fragment
    public void p0() {
        org.greenrobot.eventbus.c.c().q(this);
        super.p0();
    }

    @Override // com.byd.cleardata.ui.e.a, com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void r0() {
        this.D0.e();
        super.r0();
    }
}
