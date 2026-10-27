package com.byd.filemanager.ui.c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileType;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MorePopup.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends com.byd.filemanager.ui.c.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<c> f3763c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private FileItem f3764d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b f3765e;

    /* JADX INFO: compiled from: MorePopup.java */
    class a implements com.byd.filemanager.ui.a<Integer> {
        a() {
        }

        @Override // com.byd.filemanager.ui.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Integer num, int i) {
            com.byd.filemanager.k.b.b("position = " + i);
            switch (num.intValue()) {
                case 0:
                    f.A2(k.this.f3764d).c2(k.this.f3739a.x());
                    break;
                case 1:
                    i.e2(5).c2(k.this.f3739a.x());
                    k.this.dismiss();
                    break;
                case 2:
                    if (!k.this.f3764d.u()) {
                        com.byd.filemanager.k.b.g("mFileItem:" + k.this.f3764d);
                        com.byd.filemanager.k.n.a(R.string.file_not_exists);
                    } else if (k.this.f3764d.G() != FileType.APK) {
                        k kVar = k.this;
                        if (com.byd.filemanager.k.a.b(kVar.f3739a, kVar.f3764d) != null) {
                            k kVar2 = k.this;
                            com.byd.filemanager.k.d.J(kVar2.f3739a, kVar2.f3764d);
                        } else {
                            l.i2(k.this.f3764d).c2(k.this.f3739a.x());
                        }
                    } else if (com.byd.filemanager.k.d.E(k.this.f3764d)) {
                        k kVar3 = k.this;
                        com.byd.filemanager.k.a.e(kVar3.f3739a, kVar3.f3764d);
                    } else {
                        com.byd.filemanager.k.n.a(R.string.apk_install);
                    }
                    com.byd.filemanager.f.h().K();
                    k.this.dismiss();
                    break;
                case 3:
                    i.e2(6).c2(k.this.f3739a.x());
                    k.this.dismiss();
                    break;
                case 4:
                    d.k2(k.this.f3764d).c2(k.this.f3739a.x());
                    com.byd.filemanager.f.h().K();
                    break;
                case 5:
                    if (k.this.f3764d.G() != FileType.APK || com.byd.filemanager.k.d.E(k.this.f3764d)) {
                        l.j2(k.this.f3764d, true).c2(k.this.f3739a.x());
                    } else {
                        com.byd.filemanager.k.n.a(R.string.apk_install);
                    }
                    com.byd.filemanager.f.h().K();
                    k.this.dismiss();
                    break;
                case 6:
                    n.g2(k.this.f3764d).c2(k.this.f3739a.x());
                    com.byd.filemanager.f.h().K();
                    k.this.dismiss();
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: MorePopup.java */
    public class b extends RecyclerView.g<C0093b> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private List<c> f3767c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private com.byd.filemanager.ui.a<Integer> f3768d;

        /* JADX INFO: compiled from: MorePopup.java */
        class a extends com.byd.filemanager.ui.view.a {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ C0093b f3770c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ c f3771d;

            a(C0093b c0093b, c cVar) {
                this.f3770c = c0093b;
                this.f3771d = cVar;
            }

            @Override // com.byd.filemanager.ui.view.a
            public void a(View view) {
                if (b.this.f3768d != null) {
                    b.this.f3768d.a(Integer.valueOf(this.f3771d.f3773a), this.f3770c.j());
                }
            }
        }

        /* JADX INFO: renamed from: com.byd.filemanager.ui.c.k$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: MorePopup.java */
        protected class C0093b extends RecyclerView.c0 {
            ImageView t;
            TextView u;

            public C0093b(View view) {
                super(view);
                this.t = (ImageView) view.findViewById(R.id.iv_icon);
                this.u = (TextView) view.findViewById(R.id.tv_name);
            }
        }

        public b(List<c> list) {
            this.f3767c = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f3767c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void l(C0093b c0093b, int i) {
            c cVar = this.f3767c.get(i);
            c0093b.t.setImageResource(cVar.f3775c);
            c0093b.u.setText(cVar.f3774b);
            c0093b.f1300b.setOnClickListener(new a(c0093b, cVar));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public C0093b n(ViewGroup viewGroup, int i) {
            return com.byd.filemanager.f.h().v() ? new C0093b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_more, viewGroup, false)) : new C0093b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_more_denza, viewGroup, false));
        }

        public void z(com.byd.filemanager.ui.a<Integer> aVar) {
            this.f3768d = aVar;
        }
    }

    /* JADX INFO: compiled from: MorePopup.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f3773a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f3774b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3775c;

        public c(int i, int i2, String str) {
            this.f3773a = i;
            this.f3774b = str;
            this.f3775c = i2;
        }
    }

    public k(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override // com.byd.filemanager.ui.c.b
    protected void a() {
        this.f3763c = new ArrayList();
        List<FileItem> listL = com.byd.filemanager.f.h().l();
        boolean z = listL.size() == 1;
        if (z) {
            this.f3764d = listL.get(0);
        }
        int[] iArrA = com.byd.filemanager.k.o.a(this.f3739a, R.array.icon_list_more);
        String[] stringArray = this.f3739a.getResources().getStringArray(R.array.more_items);
        for (int i = 0; i < stringArray.length; i++) {
            if (z) {
                if (i != 6 || !this.f3764d.K()) {
                    this.f3763c.add(new c(i, iArrA[i], stringArray[i]));
                }
            } else if (i == 1 || i == 3) {
                this.f3763c.add(new c(i, iArrA[i], stringArray[i]));
            }
        }
    }

    @Override // com.byd.filemanager.ui.c.b
    protected void b(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.f3739a));
        b bVar = new b(this.f3763c);
        this.f3765e = bVar;
        recyclerView.setAdapter(bVar);
        this.f3765e.h();
        com.byd.filemanager.k.i iVar = new com.byd.filemanager.k.i(this.f3739a, R.dimen.dp_24, R.dimen.dp_10);
        iVar.l(androidx.core.content.a.d(this.f3739a, R.drawable.bg_divider));
        recyclerView.h(iVar);
        recyclerView.setOverScrollMode(2);
        this.f3765e.z(new a());
    }

    @Override // com.byd.filemanager.ui.c.b
    protected int c() {
        return com.byd.filemanager.f.h().v() ? R.layout.popup_more : R.layout.popup_more_denza;
    }

    @Override // com.byd.filemanager.ui.c.b
    protected void d() {
        setWidth(com.byd.filemanager.k.c.g(this.f3739a) ? this.f3739a.getResources().getDimensionPixelSize(R.dimen.dp_184) : this.f3739a.getResources().getDimensionPixelSize(R.dimen.dp_236));
    }
}
