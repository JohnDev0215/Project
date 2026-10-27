package com.byd.logtools.apkinstall.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.logtools.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ApkInfoAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<com.byd.logtools.apkinstall.b.a> f3100c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private i f3101d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private a f3102e;

    /* JADX INFO: compiled from: ApkInfoAdapter.java */
    interface a {
        void a(boolean z);
    }

    /* JADX INFO: compiled from: ApkInfoAdapter.java */
    class b extends RecyclerView.c0 {
        private TextView t;
        private CheckBox u;

        public b(View view) {
            super(view);
            this.t = (TextView) view.findViewById(R.id.tv_file_name);
            this.u = (CheckBox) view.findViewById(R.id.cb);
        }
    }

    private void D() {
        boolean z;
        Iterator<com.byd.logtools.apkinstall.b.a> it = this.f3100c.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            } else if (!it.next().d()) {
                z = false;
                break;
            }
        }
        a aVar = this.f3102e;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void y(b bVar, com.byd.logtools.apkinstall.b.a aVar, View view) {
        boolean z = !bVar.u.isChecked();
        bVar.u.setChecked(z);
        aVar.f(z);
        i iVar = this.f3101d;
        if (iVar != null) {
            iVar.a(aVar);
        }
        D();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public b m(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_apk_info_recycler_view, viewGroup, false));
    }

    public void B(boolean z) {
        for (int i = 0; i < c(); i++) {
            com.byd.logtools.apkinstall.b.a aVar = this.f3100c.get(i);
            if (aVar.c() != 1) {
                aVar.f(z);
            }
        }
        h();
    }

    public void C(a aVar) {
        this.f3102e = aVar;
    }

    public void E(String str, boolean z) {
        for (int i = 0; i < c(); i++) {
            com.byd.logtools.apkinstall.b.a aVar = this.f3100c.get(i);
            if (TextUtils.equals(aVar.a(), str)) {
                aVar.e(z ? 2 : 3);
                aVar.f(false);
                i(i);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        List<com.byd.logtools.apkinstall.b.a> list = this.f3100c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void v(List<com.byd.logtools.apkinstall.b.a> list) {
        if (this.f3100c == null) {
            this.f3100c = new ArrayList();
        }
        this.f3100c.clear();
        this.f3100c.addAll(list);
    }

    public List<com.byd.logtools.apkinstall.b.a> w() {
        return this.f3100c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public void k(final b bVar, int i) {
        final com.byd.logtools.apkinstall.b.a aVar = this.f3100c.get(i);
        BydLogHelper.logD("onBindViewHolder position = " + i + ", info = " + aVar.toString());
        bVar.t.setText(aVar.a());
        bVar.u.setChecked(aVar.d());
        bVar.f1721b.setAlpha(aVar.c() == 1 ? 0.5f : 1.0f);
        bVar.u.setEnabled(aVar.c() != 1);
        bVar.f1721b.setEnabled(aVar.c() != 1);
        bVar.f1721b.setOnClickListener(new View.OnClickListener() { // from class: com.byd.logtools.apkinstall.ui.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3089b.y(bVar, aVar, view);
            }
        });
    }
}
