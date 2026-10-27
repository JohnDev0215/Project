package com.byd.filemanager.ui.c;

import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.FileApplication;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileType;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: DetailDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends com.byd.filemanager.ui.c.a {
    private FileItem A0;
    private c B0;
    private List<com.byd.filemanager.bean.b> C0 = new ArrayList();
    private final io.reactivex.disposables.a D0 = new io.reactivex.disposables.a();
    private String[] y0;
    private int z0;

    /* JADX INFO: compiled from: DetailDialog.java */
    class a implements d.a.m.e<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.byd.filemanager.bean.b f3742a;

        a(com.byd.filemanager.bean.b bVar) {
            this.f3742a = bVar;
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(String str) throws Exception {
            this.f3742a.c(str);
            d.this.B0.i(d.this.C0.indexOf(this.f3742a));
        }
    }

    /* JADX INFO: compiled from: DetailDialog.java */
    class b implements d.a.m.e<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.byd.filemanager.bean.b f3744a;

        b(com.byd.filemanager.bean.b bVar) {
            this.f3744a = bVar;
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(String str) throws Exception {
            this.f3744a.c(str);
            d.this.B0.i(d.this.C0.indexOf(this.f3744a));
        }
    }

    /* JADX INFO: compiled from: DetailDialog.java */
    private static class c extends RecyclerView.g<a> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private List<com.byd.filemanager.bean.b> f3746c;

        /* JADX INFO: compiled from: DetailDialog.java */
        protected static class a extends RecyclerView.c0 {
            TextView t;
            TextView u;

            public a(View view) {
                super(view);
                this.t = (TextView) view.findViewById(R.id.tv_item);
                this.u = (TextView) view.findViewById(R.id.tv_desc);
            }
        }

        public c(List<com.byd.filemanager.bean.b> list) {
            this.f3746c = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f3746c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public void l(a aVar, int i) {
            com.byd.filemanager.bean.b bVar = this.f3746c.get(i);
            aVar.t.setText(bVar.a());
            aVar.u.setText(bVar.b());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public a n(ViewGroup viewGroup, int i) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_detail, viewGroup, false));
        }
    }

    private String f2(long j) {
        if (j <= 0) {
            return "";
        }
        return new SimpleDateFormat(FileApplication.a().getString(TimeManagerImpl.TIME_FORMAT_TWELVE.equals(Settings.System.getString(FileApplication.a().getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24)) ? R.string.time_format_12 : R.string.time_format_24), Locale.getDefault()).format(Long.valueOf(j));
    }

    private String g2(File file) {
        String path = file.getPath();
        if (com.byd.filemanager.e.f().t(file, 0)) {
            return path.replace(com.byd.filemanager.e.f().l(0), FileApplication.a().getResources().getString(R.string.local));
        }
        if (com.byd.filemanager.e.f().t(file, 1)) {
            String strK = com.byd.filemanager.e.f().k(1);
            if (!path.startsWith(strK)) {
                strK = com.byd.filemanager.e.f().l(1);
            }
            return path.replace(strK, FileApplication.a().getResources().getString(R.string.sdcard));
        }
        if (!com.byd.filemanager.e.f().t(file, 2)) {
            return path;
        }
        String strK2 = com.byd.filemanager.e.f().k(2);
        if (!path.startsWith(strK2)) {
            strK2 = com.byd.filemanager.e.f().l(2);
        }
        return path.replace(strK2, FileApplication.a().getResources().getString(R.string.usb));
    }

    private void h2(FileItem fileItem, com.byd.filemanager.bean.b bVar) {
        if (fileItem.E() == null) {
            com.byd.filemanager.k.b.b("getPath is null");
        } else {
            this.D0.c(com.byd.filemanager.j.d.c(fileItem).k(d.a.p.a.c()).f(d.a.l.b.a.a()).h(new b(bVar)));
        }
    }

    private void i2() {
        int i;
        com.byd.filemanager.k.b.b("initList");
        this.C0.clear();
        boolean zJ2 = j2(this.A0);
        this.z0 = zJ2 ? 1 : 0;
        int i2 = 2;
        if (zJ2) {
            String[] stringArray = FileApplication.a().getResources().getStringArray(R.array.file_attr_img_items);
            this.y0 = stringArray;
            this.C0.add(new com.byd.filemanager.bean.b(stringArray[0], this.A0.C()));
            if (this.A0.E() != null) {
                this.C0.add(new com.byd.filemanager.bean.b(this.y0[1], f2(com.byd.filemanager.k.d.u(this.A0.w()))));
            } else {
                i2 = 1;
            }
            int i3 = i2 + 1;
            com.byd.filemanager.bean.b bVar = new com.byd.filemanager.bean.b(this.y0[i2], FileApplication.a().getString(R.string.unknown));
            this.C0.add(bVar);
            h2(this.A0, bVar);
            int i4 = i3 + 1;
            com.byd.filemanager.bean.b bVar2 = new com.byd.filemanager.bean.b(this.y0[i3], FileApplication.a().getString(R.string.counting));
            this.C0.add(bVar2);
            l2(this.A0, bVar2);
            String strW = this.A0.E() != null ? this.A0.G() == FileType.IMAGE ? com.byd.filemanager.k.d.w(this.A0.w()) : com.byd.filemanager.k.d.D(this.A0.w()) : null;
            if (strW != null) {
                i = i4 + 1;
                this.C0.add(new com.byd.filemanager.bean.b(this.y0[i4], strW));
            } else {
                this.C0.add(new com.byd.filemanager.bean.b(this.y0[i4], FileApplication.a().getString(R.string.unknown)));
                i = i4 + 1;
            }
            if (this.A0.E() != null) {
                this.C0.add(new com.byd.filemanager.bean.b(this.y0[i], g2(this.A0.w())));
            }
        } else {
            String[] stringArray2 = FileApplication.a().getResources().getStringArray(R.array.file_attr_items);
            this.y0 = stringArray2;
            this.C0.add(new com.byd.filemanager.bean.b(stringArray2[0], this.A0.C()));
            com.byd.filemanager.bean.b bVar3 = new com.byd.filemanager.bean.b(this.y0[1], FileApplication.a().getString(R.string.counting));
            this.C0.add(bVar3);
            l2(this.A0, bVar3);
            this.C0.add(new com.byd.filemanager.bean.b(this.y0[2], FileApplication.a().getString(this.A0.H())));
            if (this.A0.E() != null) {
                this.C0.add(new com.byd.filemanager.bean.b(this.y0[3], g2(this.A0.w())));
            }
        }
        this.B0.h();
    }

    private boolean j2(FileItem fileItem) {
        if (fileItem.G() == FileType.IMAGE || fileItem.G() == FileType.VIDEO) {
            String strE = fileItem.E();
            String strL = com.byd.filemanager.e.f().l(0);
            if (!TextUtils.isEmpty(strE) && strL != null) {
                if (strE.startsWith(strL + "/DCIM/Camera")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static d k2(FileItem fileItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("file", fileItem);
        d dVar = new d();
        dVar.q1(bundle);
        return dVar;
    }

    private void l2(FileItem fileItem, com.byd.filemanager.bean.b bVar) {
        if (fileItem.E() == null) {
            com.byd.filemanager.k.b.b("getPath is null");
        } else {
            this.D0.c(com.byd.filemanager.j.d.b(fileItem).k(d.a.p.a.c()).f(d.a.l.b.a.a()).h(new a(bVar)));
        }
    }

    @Override // com.byd.filemanager.ui.c.a, androidx.fragment.app.Fragment
    public void F0() {
        com.byd.filemanager.k.b.b("onResume");
        i2();
        super.F0();
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int N1() {
        return 2;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void O1(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(x(), 1, false));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        layoutParams.bottomMargin = (int) com.byd.filemanager.k.c.b(this.z0 == 0 ? 6.0f : 2.0f);
        recyclerView.setLayoutParams(layoutParams);
        c cVar = new c(this.C0);
        this.B0 = cVar;
        recyclerView.setAdapter(cVar);
        this.n0.setText(R.string.btn_known);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void P1(Bundle bundle) {
    }

    @Override // com.byd.filemanager.ui.c.a
    protected String R1() {
        return Q(R.string.detail);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int X1() {
        return R.layout.dialog_detail;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void b2(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = this.z0 == 0 ? R.dimen.dp_422 : R.dimen.dp_538;
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        org.greenrobot.eventbus.c.c().o(this);
        Bundle bundleV = v();
        if (bundleV != null) {
            this.A0 = (FileItem) bundleV.getParcelable("file");
        }
    }

    @org.greenrobot.eventbus.l(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.music.f.b bVar) {
        com.byd.filemanager.k.b.b("onDiskEvent");
        int iB = bVar.b();
        if (iB != com.byd.filemanager.f.h().k() || com.byd.filemanager.e.f().s(iB)) {
            return;
        }
        z1();
    }

    @Override // androidx.fragment.app.Fragment
    public void p0() {
        org.greenrobot.eventbus.c.c().q(this);
        super.p0();
    }

    @Override // com.byd.filemanager.ui.c.a, com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void r0() {
        this.D0.dispose();
        super.r0();
    }
}
