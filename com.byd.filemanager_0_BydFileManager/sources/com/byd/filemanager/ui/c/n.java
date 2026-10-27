package com.byd.filemanager.ui.c;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileItem;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ShareDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends com.byd.filemanager.ui.c.a<String> {
    private FileItem A0;
    private com.byd.filemanager.ui.b.a y0;
    private List<com.byd.filemanager.bean.a> z0 = new ArrayList();

    /* JADX INFO: compiled from: ShareDialog.java */
    class a implements com.byd.filemanager.ui.a<String> {
        a() {
        }

        @Override // com.byd.filemanager.ui.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(String str, int i) {
            com.byd.filemanager.bean.a aVar = (com.byd.filemanager.bean.a) n.this.z0.get(i);
            if (n.this.A0 != null) {
                n.this.h2(aVar);
            }
            n.this.z1();
        }
    }

    public static n g2(FileItem fileItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("key1", fileItem);
        n nVar = new n();
        nVar.q1(bundle);
        return nVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h2(com.byd.filemanager.bean.a aVar) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType(com.byd.filemanager.k.d.r(this.A0.w()));
        intent.putExtra("android.intent.extra.STREAM", com.byd.filemanager.k.d.B(p(), this.A0));
        intent.addFlags(268435456);
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
        recyclerView.h(new com.byd.filemanager.k.e(4, K().getDimensionPixelSize(R.dimen.dp_24), false));
        recyclerView.setAdapter(this.y0);
        this.y0.z(new a());
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void P1(Bundle bundle) {
        if (this.A0.N()) {
            this.z0.addAll(com.byd.filemanager.k.a.d(i1(), this.A0));
        }
        int size = this.z0.size();
        if (size == 0) {
            com.byd.filemanager.k.n.a(R.string.error_share);
            z1();
        } else if (size != 1) {
            this.y0.h();
        } else {
            h2(this.z0.get(0));
            z1();
        }
    }

    @Override // com.byd.filemanager.ui.c.a
    protected String R1() {
        return Q(R.string.share_to);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int X1() {
        return R.layout.dialog_share;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void b2(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_440;
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        Bundle bundleV = v();
        if (bundleV != null) {
            this.A0 = (FileItem) bundleV.getParcelable("key1");
        }
    }
}
