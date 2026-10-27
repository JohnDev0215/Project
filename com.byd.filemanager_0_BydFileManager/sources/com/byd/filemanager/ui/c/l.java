package com.byd.filemanager.ui.c;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileType;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: OpenDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends com.byd.filemanager.ui.c.a<String> {
    private FileItem A0;
    private boolean B0;
    private boolean C0;
    private com.byd.filemanager.ui.b.a y0;
    private List<com.byd.filemanager.bean.a> z0 = new ArrayList();

    /* JADX INFO: compiled from: OpenDialog.java */
    class a implements com.byd.filemanager.ui.a<String> {
        a() {
        }

        @Override // com.byd.filemanager.ui.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(String str, int i) {
            com.byd.filemanager.bean.a aVar = (com.byd.filemanager.bean.a) l.this.z0.get(i);
            if (l.this.B0 && !com.byd.filemanager.k.a.f(l.this.p(), aVar, l.this.A0)) {
                com.byd.filemanager.k.n.a(R.string.error_set_default);
            } else {
                l.this.k2(aVar);
                l.this.z1();
            }
        }
    }

    /* JADX INFO: compiled from: OpenDialog.java */
    class b implements CompoundButton.OnCheckedChangeListener {
        b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            l.this.B0 = z;
        }
    }

    public static l i2(FileItem fileItem) {
        return j2(fileItem, false);
    }

    public static l j2(FileItem fileItem, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("key1", fileItem);
        bundle.putBoolean("key2", z);
        l lVar = new l();
        lVar.q1(bundle);
        return lVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k2(com.byd.filemanager.bean.a aVar) {
        if (this.A0.G() == FileType.APK) {
            if (com.byd.filemanager.k.d.E(this.A0)) {
                com.byd.filemanager.k.a.e(j1(), this.A0);
                return;
            } else {
                com.byd.filemanager.k.n.a(R.string.apk_install);
                return;
            }
        }
        Intent intent = new Intent();
        intent.putExtra("filePath", this.A0.E());
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1);
        intent.setDataAndType(com.byd.filemanager.k.d.B(x(), this.A0), com.byd.filemanager.k.d.r(this.A0.w()));
        intent.setComponent(new ComponentName(aVar.d(), aVar.a()));
        i1().startActivity(intent);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int N1() {
        return 1;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void O1(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(x(), 4));
        this.y0 = new com.byd.filemanager.ui.b.a(R.layout.layout_item_share, this.z0);
        recyclerView.h(new com.byd.filemanager.k.e(4, (int) com.byd.filemanager.k.c.b(24.0f), false));
        recyclerView.setAdapter(this.y0);
        this.y0.z(new a());
        ((CheckBox) view.findViewById(R.id.rb_check)).setOnCheckedChangeListener(new b());
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void P1(Bundle bundle) {
        FileItem fileItem = this.A0;
        if (fileItem == null || !fileItem.u()) {
            com.byd.filemanager.k.b.g("mFileItem:" + this.A0);
            return;
        }
        if (this.A0.N()) {
            this.z0.addAll(com.byd.filemanager.k.a.c(i1(), this.A0));
        }
        int size = this.z0.size();
        if (this.C0) {
            if (!this.A0.K()) {
                this.y0.h();
                return;
            } else {
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(14, this.A0));
                z1();
                return;
            }
        }
        if (size == 0) {
            if (this.A0.N()) {
                com.byd.filemanager.k.n.a(R.string.error_open);
            } else {
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(14, this.A0));
            }
            z1();
            return;
        }
        if (size != 1) {
            this.y0.h();
        } else {
            k2(this.z0.get(0));
            z1();
        }
    }

    @Override // com.byd.filemanager.ui.c.a
    protected String R1() {
        return Q(R.string.open_mode);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int X1() {
        return R.layout.dialog_open;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void b2(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_526;
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        Bundle bundleV = v();
        if (bundleV != null) {
            this.A0 = (FileItem) bundleV.getParcelable("key1");
            this.C0 = bundleV.getBoolean("key2");
        }
    }
}
