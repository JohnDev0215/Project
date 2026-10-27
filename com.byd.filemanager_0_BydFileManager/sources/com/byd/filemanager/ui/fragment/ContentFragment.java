package com.byd.filemanager.ui.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileState;
import com.byd.filemanager.bean.FileType;
import com.byd.filemanager.k.n;
import com.byd.filemanager.ui.MainActivity;
import com.byd.filemanager.ui.b.b;
import com.byd.filemanager.ui.c.a;
import com.byd.filemanager.ui.c.l;
import com.byd.filemanager.ui.c.m;
import com.byd.filemanager.ui.d.i;
import com.byd.filemanager.ui.view.EmptyRecyclerView;
import com.byd.music.f.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class ContentFragment extends BaseMvpFragment<com.byd.filemanager.i.c.b> implements com.byd.filemanager.i.a.c {
    private static final FileType[] f0 = {FileType.DOCUMENT, FileType.AUDIO, FileType.VIDEO, FileType.IMAGE, FileType.COMPRESS, FileType.APK};
    private EmptyRecyclerView i0;
    private com.byd.filemanager.ui.b.b j0;
    private androidx.recyclerview.widget.d k0;
    private ImageView l0;
    private TextView m0;
    private View n0;
    private ViewGroup o0;
    private boolean q0;
    private boolean r0;
    private MediaObserver s0;
    public com.byd.music.g.a.a t0;
    private ArrayList<FileItem> g0 = new ArrayList<>();
    private final Handler h0 = new Handler(Looper.getMainLooper()) { // from class: com.byd.filemanager.ui.fragment.ContentFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                ContentFragment.this.h0.removeMessages(2);
                ContentFragment.this.j0.h();
            } else if (i == 2) {
                ContentFragment.this.j0.h();
                if (ContentFragment.this.g0.size() < 100) {
                    ContentFragment.this.h0.sendEmptyMessageDelayed(2, 100L);
                }
            }
        }
    };
    private FileType p0 = FileType.OTHER;
    private final a.c<Object> u0 = new a.c<Object>() { // from class: com.byd.filemanager.ui.fragment.ContentFragment.5
        @Override // com.byd.filemanager.ui.c.a.c
        public void a(int i, Object obj) {
            if (i == 0) {
                com.byd.filemanager.k.b.b("cancel task");
                ContentFragment.this.h0.removeMessages(2);
                ((com.byd.filemanager.i.c.b) ContentFragment.this.a0).l();
                com.byd.filemanager.f.h().K();
            }
        }
    };

    private class MediaObserver extends ContentObserver {
        public MediaObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            com.byd.filemanager.k.b.c("onChange", z + ", " + uri);
            ContentFragment contentFragment = ContentFragment.this;
            contentFragment.q0 = contentFragment.b0() ^ true;
        }
    }

    private void N1() {
        int i = com.byd.filemanager.f.h().i();
        this.j0.O(i);
        com.byd.filemanager.k.b.b("viewType = " + i);
        O1(com.byd.filemanager.k.c.d(this.d0));
        if (i == 0) {
            EmptyRecyclerView emptyRecyclerView = this.i0;
            Context context = this.d0;
            emptyRecyclerView.setLayoutManager(new GridLayoutManager(context, com.byd.filemanager.k.c.d(context) ? 7 : 5));
            this.i0.Y0(this.k0);
            return;
        }
        this.i0.setLayoutManager(new LinearLayoutManager(this.d0));
        androidx.recyclerview.widget.d dVar = new androidx.recyclerview.widget.d(this.d0, 1);
        this.k0 = dVar;
        dVar.l(androidx.core.content.a.d(this.d0, R.drawable.bg_divider));
        this.i0.h(this.k0);
    }

    private void O1(boolean z) {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        int i = com.byd.filemanager.f.h().i();
        ConstraintLayout.b bVar = (ConstraintLayout.b) this.i0.getLayoutParams();
        if (z) {
            dimensionPixelSize = K().getDimensionPixelSize(i == 0 ? R.dimen.grid_start_land : R.dimen.list_start_land);
            dimensionPixelSize2 = K().getDimensionPixelSize(i == 0 ? R.dimen.grid_end_land : R.dimen.list_end_land);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.l0.getLayoutParams();
            layoutParams.topMargin = K().getDimensionPixelSize(R.dimen.dp_58);
            this.l0.setLayoutParams(layoutParams);
        } else {
            dimensionPixelSize = K().getDimensionPixelSize(i == 0 ? R.dimen.grid_start : R.dimen.list_start);
            dimensionPixelSize2 = K().getDimensionPixelSize(i == 0 ? R.dimen.grid_end : R.dimen.list_end);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.l0.getLayoutParams();
            layoutParams2.topMargin = K().getDimensionPixelSize(R.dimen.dp_233);
            this.l0.setLayoutParams(layoutParams2);
        }
        bVar.setMarginStart(dimensionPixelSize);
        bVar.setMarginEnd(dimensionPixelSize2);
        this.i0.setLayoutParams(bVar);
    }

    private void P1(boolean z) {
        if (z) {
            Iterator<FileItem> it = this.g0.iterator();
            while (it.hasNext()) {
                it.next().P(true);
            }
            com.byd.filemanager.f.h().a(this.g0);
        } else {
            Iterator<FileItem> it2 = this.g0.iterator();
            while (it2.hasNext()) {
                it2.next().P(false);
            }
            com.byd.filemanager.f.h().a(null);
        }
        this.j0.h();
        org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(11, Boolean.valueOf(z)));
    }

    private void R1() {
        if (!this.g0.isEmpty()) {
            this.g0.clear();
            this.j0.h();
        }
        P p = this.a0;
        if (p != 0) {
            ((com.byd.filemanager.i.c.b) p).B(com.byd.filemanager.f.h().j(), com.byd.filemanager.f.h().k());
        }
    }

    private void S1() {
        this.k0 = new androidx.recyclerview.widget.d(this.d0, 1);
        if (com.byd.filemanager.f.h().i() == 0) {
            EmptyRecyclerView emptyRecyclerView = this.i0;
            Context context = this.d0;
            emptyRecyclerView.setLayoutManager(new GridLayoutManager(context, com.byd.filemanager.k.c.d(context) ? 7 : 5));
            this.k0.l(androidx.core.content.a.d(this.d0, R.drawable.bg_divider_transparent));
        } else {
            this.i0.setLayoutManager(new LinearLayoutManager(this.d0));
            this.k0.l(androidx.core.content.a.d(this.d0, R.drawable.bg_divider));
        }
        this.i0.h(this.k0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: T1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void U1(int i, String str) {
        ((com.byd.filemanager.i.c.b) this.a0).C(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: V1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void W1(int i) {
        ((com.byd.filemanager.i.c.b) this.a0).B(i, com.byd.filemanager.f.h().k());
    }

    private void X1() {
        this.s0 = new MediaObserver(new Handler());
        j1().getContentResolver().registerContentObserver(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, true, this.s0);
        j1().getContentResolver().registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, this.s0);
        j1().getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.s0);
    }

    private void Y1(FileItem fileItem) {
        if (fileItem != null) {
            Z1(fileItem.E());
        }
    }

    private void Z1(String str) {
        if (str != null) {
            com.byd.filemanager.service.b.c().m(str);
            return;
        }
        List<String> listO = com.byd.filemanager.f.h().o();
        if (listO.isEmpty()) {
            return;
        }
        com.byd.filemanager.service.b.c().m((String[]) listO.toArray(new String[0]));
    }

    private void b2() {
        this.g0.clear();
        this.j0.h();
        FileType fileType = FileType.OTHER;
        this.p0 = fileType;
        ((com.byd.filemanager.i.c.b) this.a0).A(fileType);
    }

    @Override // com.byd.filemanager.ui.fragment.BaseMvpFragment
    protected void D1(Bundle bundle) {
        P p = this.a0;
        if (p != 0) {
            ((com.byd.filemanager.i.c.b) p).a(this);
        }
        List<FileItem> listF = com.byd.filemanager.f.h().f();
        if (bundle == null || listF == null || listF.size() <= 0) {
            if (TextUtils.isEmpty(((MainActivity) p()).Z())) {
                R1();
                return;
            } else {
                ((com.byd.filemanager.i.c.b) this.a0).C(com.byd.filemanager.f.h().j(), com.byd.filemanager.f.h().e());
                return;
            }
        }
        com.byd.filemanager.k.b.b("restore");
        m mVar = this.b0;
        if (mVar != null) {
            mVar.Y1(this.u0);
        }
        this.g0.addAll(listF);
        this.j0.h();
    }

    @Override // com.byd.filemanager.ui.fragment.BaseMvpFragment
    protected void E1(View view) {
        com.byd.filemanager.service.b.c().h(this);
        this.i0 = (EmptyRecyclerView) view.findViewById(R.id.recyclerView);
        this.n0 = view.findViewById(R.id.ly_empty);
        this.l0 = (ImageView) view.findViewById(R.id.iv_icon_empty);
        this.m0 = (TextView) view.findViewById(R.id.tv_desc_empty);
        this.o0 = (ViewGroup) view.findViewById(R.id.layout_loading);
        S1();
        com.byd.filemanager.ui.b.b bVar = new com.byd.filemanager.ui.b.b(this.d0, this.g0);
        this.j0 = bVar;
        bVar.O(com.byd.filemanager.f.h().i());
        this.j0.N(com.byd.filemanager.f.h().s());
        this.i0.setAdapter(this.j0);
        this.i0.setEmptyView(this.n0);
        this.i0.setNeedUpdateEmptyImage(true);
        this.j0.M(new b.g() { // from class: com.byd.filemanager.ui.fragment.ContentFragment.2
            @Override // com.byd.filemanager.ui.b.b.g
            public boolean a(View view2, FileItem fileItem) {
                if (com.byd.filemanager.f.h().g() == FileState.PASTING) {
                    n.a(R.string.pasting);
                    return false;
                }
                if (!com.byd.filemanager.f.h().s()) {
                    com.byd.filemanager.f.h().G(FileState.CHOOSING);
                }
                fileItem.P(true);
                com.byd.filemanager.f.h().b(fileItem);
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(11, Boolean.valueOf(com.byd.filemanager.f.h().l().size() == ContentFragment.this.g0.size())));
                return true;
            }
        });
        this.j0.L(new com.byd.filemanager.ui.a<FileItem>() { // from class: com.byd.filemanager.ui.fragment.ContentFragment.3
            @Override // com.byd.filemanager.ui.a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(FileItem fileItem, int i) {
                com.byd.filemanager.k.b.m("onItemClick fileItem = " + fileItem);
                if (com.byd.filemanager.f.h().s()) {
                    com.byd.filemanager.f.h().b(fileItem);
                    org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(11, Boolean.valueOf(com.byd.filemanager.f.h().l().size() == ContentFragment.this.g0.size())));
                    return;
                }
                if (fileItem.N()) {
                    if (!fileItem.u()) {
                        n.a(R.string.file_not_exists);
                        return;
                    }
                    if (fileItem.G() == FileType.APK) {
                        if (!com.byd.filemanager.k.d.E(fileItem)) {
                            n.a(R.string.apk_install);
                            return;
                        }
                        com.byd.filemanager.k.a.e(ContentFragment.this.j1(), fileItem);
                    }
                    com.byd.filemanager.bean.a aVarB = com.byd.filemanager.k.a.b(ContentFragment.this.d0, fileItem);
                    if (aVarB == null) {
                        l.i2(fileItem).c2(ContentFragment.this.w());
                        return;
                    } else if (!"com.amazon.mp3.automotiveOS".equals(aVarB.d())) {
                        com.byd.filemanager.k.d.J(ContentFragment.this.d0, fileItem);
                        return;
                    } else {
                        com.byd.filemanager.k.b.m("default app is amazon music, openDialog");
                        l.i2(fileItem).c2(ContentFragment.this.w());
                        return;
                    }
                }
                if (com.byd.filemanager.f.h().j() == 5) {
                    org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(10, fileItem));
                    ContentFragment.this.p0 = fileItem.v();
                    ContentFragment contentFragment = ContentFragment.this;
                    ((com.byd.filemanager.i.c.b) contentFragment.a0).A(contentFragment.p0);
                    com.byd.filemanager.f.h().D(fileItem.E());
                    return;
                }
                String strE = fileItem.E();
                String strK = com.byd.filemanager.e.f().k(com.byd.filemanager.f.h().k());
                if (com.byd.filemanager.e.v(strE, strK)) {
                    org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(10, fileItem));
                    com.byd.filemanager.f.h().D(strE);
                    ((com.byd.filemanager.i.c.b) ContentFragment.this.a0).C(com.byd.filemanager.f.h().j(), strE);
                } else {
                    com.byd.filemanager.k.b.b("path = " + strE + " root = " + strK);
                }
            }
        });
        this.i0.l(new RecyclerView.t() { // from class: com.byd.filemanager.ui.fragment.ContentFragment.4
            @Override // androidx.recyclerview.widget.RecyclerView.t
            public void a(RecyclerView recyclerView, int i) {
                super.a(recyclerView, i);
                try {
                    if (i == 0) {
                        com.bumptech.glide.c.t(ContentFragment.this.j1()).x();
                    } else if (i != 2) {
                    } else {
                        com.bumptech.glide.c.t(ContentFragment.this.j1()).w();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        i.a(this.i0, 0);
        O1(com.byd.filemanager.k.c.d(this.d0));
        X1();
    }

    @Override // com.byd.filemanager.ui.fragment.BaseMvpFragment, androidx.fragment.app.Fragment
    public void F0() {
        super.F0();
        int iJ = com.byd.filemanager.f.h().j();
        if (com.byd.filemanager.f.h().w() && this.q0) {
            if (iJ != 1) {
                ((com.byd.filemanager.i.c.b) this.a0).B(com.byd.filemanager.f.h().j(), com.byd.filemanager.f.h().k());
            } else {
                ((com.byd.filemanager.i.c.b) this.a0).C(com.byd.filemanager.f.h().j(), com.byd.filemanager.f.h().e());
            }
        }
        this.q0 = false;
        this.r0 = false;
    }

    @Override // com.byd.filemanager.ui.fragment.BaseMvpFragment
    protected int F1() {
        return R.layout.fragment_content;
    }

    @Override // com.byd.filemanager.ui.fragment.BaseMvpFragment, androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        super.G0(bundle);
        com.byd.filemanager.f.h().F(this.g0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.filemanager.ui.fragment.BaseMvpFragment
    /* JADX INFO: renamed from: Q1, reason: merged with bridge method [inline-methods] */
    public com.byd.filemanager.i.c.b z1() {
        return new com.byd.filemanager.i.c.b(this);
    }

    public void a2(com.byd.music.g.a.a aVar) {
        com.byd.filemanager.k.b.c(this.Z, "setHeadCallback head" + aVar);
        this.t0 = aVar;
    }

    @Override // com.byd.filemanager.i.a.c
    public void g(long j, long j2) {
        m mVar = this.b0;
        if (mVar != null) {
            mVar.f2(j, j2);
        }
    }

    @Override // com.byd.filemanager.i.a.c
    public void h(int i) {
        MainActivity mainActivity = (MainActivity) p();
        ArrayList<FileItem> arrayList = this.g0;
        if ((arrayList == null || arrayList.size() <= 0) && mainActivity != null) {
            final String strZ = mainActivity.Z();
            final int iJ = com.byd.filemanager.f.h().j();
            com.byd.filemanager.k.b.n(this.Z, "scanComplete targetPath=" + strZ);
            if (TextUtils.isEmpty(strZ)) {
                this.h0.post(new Runnable() { // from class: com.byd.filemanager.ui.fragment.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3856b.W1(iJ);
                    }
                });
            } else {
                this.h0.post(new Runnable() { // from class: com.byd.filemanager.ui.fragment.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3853b.U1(iJ, strZ);
                    }
                });
            }
        }
    }

    @Override // com.byd.filemanager.i.a.c
    public void k(int i) {
        if (i != 3) {
            if (i == 7) {
                this.o0.setVisibility(0);
                return;
            } else if (i != 16) {
                return;
            }
        }
        this.i0.setLoading(true);
        this.g0.clear();
        this.j0.h();
        this.o0.setVisibility(0);
    }

    @Override // com.byd.filemanager.i.a.c
    public void l(int i, FileItem fileItem) {
        int iP = com.byd.filemanager.f.h().p();
        switch (i) {
            case 0:
                if (fileItem != null) {
                    this.g0.add(fileItem);
                    this.j0.j(this.g0.indexOf(fileItem));
                    n.g(R.string.success_create);
                    Y1(fileItem);
                    ((com.byd.filemanager.i.c.b) this.a0).E(iP, this.g0);
                }
                break;
            case 1:
                C1();
                if (fileItem != null && !fileItem.O() && !fileItem.u()) {
                    this.g0.remove(fileItem);
                }
                break;
            case 2:
                Y1(fileItem);
                com.byd.filemanager.f.h().K();
                this.j0.i(this.g0.indexOf(fileItem));
                n.g(R.string.success_modify);
                if (iP == 0) {
                    ((com.byd.filemanager.i.c.b) this.a0).E(iP, this.g0);
                }
                break;
            case 3:
                if (fileItem != null) {
                    this.g0.add(fileItem);
                }
                break;
            case 4:
            case 5:
                C1();
                if (fileItem != null && !fileItem.O()) {
                    this.g0.add(fileItem);
                }
                break;
            case 6:
                if (!fileItem.O()) {
                    Y1(fileItem);
                    this.o0.setVisibility(8);
                    n.g(R.string.success_compress);
                    com.byd.filemanager.f.h().K();
                    org.greenrobot.eventbus.c.c().k(new com.byd.music.f.c());
                    this.g0.add(fileItem);
                    this.j0.i(this.g0.indexOf(fileItem));
                    ((com.byd.filemanager.i.c.b) this.a0).E(iP, this.g0);
                } else {
                    C1();
                }
                break;
        }
        this.i0.setLoading(false);
    }

    @Override // com.byd.filemanager.i.a.c
    public void n(int i, int i2, List<FileItem> list) {
        com.byd.filemanager.k.b.c(this.Z, "onFinished type =" + i + ",status =" + i2);
        B1();
        int iP = com.byd.filemanager.f.h().p();
        if (i == 1) {
            com.byd.filemanager.f.h().K();
            if (i2 == 1) {
                n.g(R.string.success_delete);
            } else if (i2 == 4) {
                n.e(R.string.error_delete);
            }
            if (com.byd.filemanager.k.d.H(list)) {
                com.byd.filemanager.service.b.c().m(com.byd.filemanager.e.h(list));
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.c());
                this.g0.removeAll(list);
                this.j0.h();
            }
        } else if (i == 16 || i == 3) {
            this.o0.setVisibility(8);
            if (i2 == 1) {
                if (com.byd.filemanager.k.d.H(list)) {
                    this.i0.j1(0);
                    this.g0.addAll(list);
                }
            } else if (i2 == 4) {
                com.byd.filemanager.k.b.b("query error");
            }
            this.j0.h();
        } else if (i == 4) {
            com.byd.filemanager.f.h().K();
            if (i2 == 1) {
                n.g(R.string.success_copy);
            } else if (i2 == 4) {
                n.e(R.string.error_copy);
            }
            if (com.byd.filemanager.k.d.H(list)) {
                com.byd.filemanager.service.b.c().m(com.byd.filemanager.e.h(list));
                com.byd.music.f.c cVar = new com.byd.music.f.c();
                cVar.d(i2);
                org.greenrobot.eventbus.c.c().k(cVar);
                this.g0.addAll(list);
                this.j0.h();
                ((com.byd.filemanager.i.c.b) this.a0).E(iP, this.g0);
            }
        } else if (i == 5) {
            com.byd.filemanager.f.h().K();
            if (i2 == 1) {
                n.g(R.string.success_move);
            } else if (i2 == 4) {
                n.e(R.string.error_move);
            }
            if (com.byd.filemanager.k.d.H(list)) {
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.c());
                this.g0.addAll(list);
                this.j0.h();
                ((com.byd.filemanager.i.c.b) this.a0).E(iP, this.g0);
            }
        } else if (i == 6) {
            com.byd.filemanager.f.h().K();
            if (i2 == 1) {
                n.g(R.string.success_compress);
            } else if (i2 == 4) {
                n.e(R.string.error_compress);
            }
            if (com.byd.filemanager.k.d.H(list)) {
                com.byd.filemanager.service.b.c().m(com.byd.filemanager.e.h(list));
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.c());
                this.g0.addAll(list);
                this.j0.h();
                ((com.byd.filemanager.i.c.b) this.a0).E(iP, this.g0);
            }
        }
        this.i0.setLoading(false);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean z = configuration.orientation == 2;
        int i = com.byd.filemanager.f.h().i();
        if (this.j0 != null) {
            if (i == 0) {
                EmptyRecyclerView emptyRecyclerView = this.i0;
                Context context = this.d0;
                emptyRecyclerView.setLayoutManager(new GridLayoutManager(context, com.byd.filemanager.k.c.d(context) ? 7 : 5));
                this.k0.l(androidx.core.content.a.d(this.d0, R.drawable.bg_divider_transparent));
            } else {
                this.i0.setLayoutManager(new LinearLayoutManager(this.d0));
                this.k0.l(androidx.core.content.a.d(this.d0, R.drawable.bg_divider));
            }
        }
        O1(z);
    }

    @org.greenrobot.eventbus.l(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.music.f.b bVar) {
        int iB = bVar.b();
        int iK = com.byd.filemanager.f.h().k();
        com.byd.filemanager.k.b.c(this.Z, "onDiskEvent root:" + iB + " mRootIndex:" + iK);
        if (iB == iK) {
            if (bVar.e()) {
                ((com.byd.filemanager.i.c.b) this.a0).m();
            } else {
                ((com.byd.filemanager.i.c.b) this.a0).l();
            }
            this.g0.clear();
            this.j0.h();
            if (com.byd.filemanager.e.f().s(iB)) {
                com.byd.filemanager.f.h().L(iB);
                ((com.byd.filemanager.i.c.b) this.a0).B(com.byd.filemanager.f.h().j(), iK);
            }
            com.byd.filemanager.f.h().J();
        }
    }

    @org.greenrobot.eventbus.l
    public void onFileStateEvent(com.byd.music.f.d dVar) {
        FileState fileStateG = com.byd.filemanager.f.h().g();
        com.byd.filemanager.k.b.c(this.Z, "onFileStateEvent =" + fileStateG);
        if (fileStateG == FileState.NORMAL || fileStateG == FileState.PASTING) {
            this.j0.N(false);
            ConstraintLayout.b bVar = (ConstraintLayout.b) this.i0.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = 0;
            FileState fileState = FileState.PASTING;
            if (fileStateG == fileState) {
                ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = K().getDimensionPixelSize(com.byd.filemanager.k.c.d(j1()) ? R.dimen.dp_100 : R.dimen.dp_120);
            }
            this.i0.setLayoutParams(bVar);
            if (fileStateG == fileState) {
                ((com.byd.filemanager.i.c.b) this.a0).B(com.byd.filemanager.f.h().j(), com.byd.filemanager.f.h().k());
                return;
            }
            return;
        }
        if (fileStateG == FileState.CHOOSING) {
            this.j0.N(true);
            return;
        }
        if (fileStateG == FileState.COPYING) {
            List<FileItem> listL = com.byd.filemanager.f.h().l();
            G1(R.string.copying, listL.size());
            ((com.byd.filemanager.i.c.b) this.a0).p(listL, com.byd.filemanager.f.h().e());
            this.b0.Y1(this.u0);
            return;
        }
        if (fileStateG == FileState.MOVING) {
            List<FileItem> listL2 = com.byd.filemanager.f.h().l();
            G1(R.string.moving, listL2.size());
            ((com.byd.filemanager.i.c.b) this.a0).u(listL2, com.byd.filemanager.f.h().e());
            this.b0.Y1(this.u0);
            return;
        }
        if (fileStateG == FileState.COMPRESSING) {
            List<FileItem> listL3 = com.byd.filemanager.f.h().l();
            G1(R.string.compressing, listL3.size());
            ((com.byd.filemanager.i.c.b) this.a0).o(listL3, com.byd.filemanager.f.h().e());
            this.b0.Y1(this.u0);
            return;
        }
        if (fileStateG == FileState.DELETING) {
            List<FileItem> listL4 = com.byd.filemanager.f.h().l();
            G1(R.string.deleting, listL4.size());
            ((com.byd.filemanager.i.c.b) this.a0).q(listL4);
            this.b0.Y1(this.u0);
        }
    }

    @org.greenrobot.eventbus.l
    public void onMenuEvent(com.byd.music.f.e eVar) {
        if (com.byd.filemanager.f.h().s()) {
            com.byd.filemanager.f.h().K();
        }
        if (com.byd.filemanager.f.h().j() != 5) {
            R1();
        } else {
            b2();
        }
    }

    @org.greenrobot.eventbus.l
    public void onOperateEvent(com.byd.music.f.f fVar) {
        int iB = fVar.b();
        com.byd.filemanager.k.b.c(this.Z, "operate =" + iB);
        if (iB == 9) {
            N1();
            return;
        }
        if (iB == 12) {
            P1(((Boolean) fVar.a()).booleanValue());
            return;
        }
        if (iB == 7) {
            ((com.byd.filemanager.i.c.b) this.a0).E(com.byd.filemanager.f.h().p(), this.g0);
            return;
        }
        if (iB == 2) {
            ((com.byd.filemanager.i.c.b) this.a0).E(com.byd.filemanager.f.h().p(), this.g0);
            String str = (String) fVar.a();
            FileItem fileItemN = com.byd.filemanager.f.h().n();
            if (fileItemN != null) {
                if (TextUtils.isEmpty(str) || str.equals(fileItemN.C())) {
                    com.byd.filemanager.f.h().K();
                    return;
                } else {
                    ((com.byd.filemanager.i.c.b) this.a0).D(fileItemN, str);
                    return;
                }
            }
            return;
        }
        if (iB == 8) {
            String str2 = (String) fVar.a();
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            ((com.byd.filemanager.i.c.b) this.a0).k(com.byd.filemanager.f.h().e(), str2);
            return;
        }
        if (iB != 14) {
            if (iB == 20) {
                com.byd.filemanager.k.b.b("cancel task");
                ((com.byd.filemanager.i.c.b) this.a0).l();
                return;
            }
            return;
        }
        FileItem fileItem = (FileItem) fVar.a();
        if (fileItem != null) {
            org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(10, fileItem));
            String strE = fileItem.E();
            com.byd.filemanager.f.h().D(strE);
            ((com.byd.filemanager.i.c.b) this.a0).C(com.byd.filemanager.f.h().j(), strE);
        }
    }

    @org.greenrobot.eventbus.l(threadMode = ThreadMode.MAIN)
    public void onOuterFileChangeEvent(com.byd.music.f.g gVar) {
        this.r0 = true;
    }

    @org.greenrobot.eventbus.l
    public void onPathEvent(h hVar) {
        if (com.byd.filemanager.f.h().j() != 5) {
            ((com.byd.filemanager.i.c.b) this.a0).C(com.byd.filemanager.f.h().j(), com.byd.filemanager.f.h().e());
        } else {
            b2();
        }
        com.byd.filemanager.f.h().J();
    }

    @org.greenrobot.eventbus.l(threadMode = ThreadMode.MAIN)
    public void onPlayEvent(com.byd.music.a aVar) {
        com.byd.filemanager.k.b.c(this.Z, "play error");
        n.a(R.string.error_open);
    }

    @org.greenrobot.eventbus.l
    public void onTabEvent(com.byd.music.f.i iVar) {
        if (iVar.b() != 15) {
            R1();
        }
    }

    @Override // com.byd.filemanager.i.a.c
    public void r(int i, boolean z, boolean z2) {
        com.byd.filemanager.k.b.b("onFinished type = " + i);
        B1();
        this.h0.removeMessages(2);
        this.j0.h();
        int iP = com.byd.filemanager.f.h().p();
        switch (i) {
            case 0:
                if (!z) {
                    n.e(R.string.error_create);
                }
                break;
            case 1:
                this.o0.setVisibility(8);
                Z1(com.byd.filemanager.f.h().e());
                com.byd.filemanager.f.h().K();
                if (!z2) {
                    n.d(z, R.string.success_delete, R.string.error_delete);
                }
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.c());
                break;
            case 2:
                if (!z) {
                    n.e(R.string.error_modify);
                }
                break;
            case 3:
                this.o0.setVisibility(8);
                if (z) {
                    this.i0.j1(0);
                }
                break;
            case 4:
                Z1(com.byd.filemanager.f.h().e());
                com.byd.filemanager.f.h().K();
                n.d(z, R.string.success_copy, R.string.error_copy);
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.c());
                ((com.byd.filemanager.i.c.b) this.a0).E(iP, this.g0);
                if (!z) {
                    ((com.byd.filemanager.i.c.b) this.a0).C(com.byd.filemanager.f.h().j(), com.byd.filemanager.f.h().e());
                }
                break;
            case 5:
                Z1(com.byd.filemanager.f.h().e());
                com.byd.filemanager.f.h().K();
                n.d(z, R.string.success_move, R.string.error_move);
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.c());
                ((com.byd.filemanager.i.c.b) this.a0).E(iP, this.g0);
                if (!z) {
                    ((com.byd.filemanager.i.c.b) this.a0).C(com.byd.filemanager.f.h().j(), com.byd.filemanager.f.h().e());
                }
                break;
            case 6:
                this.o0.setVisibility(8);
                n.d(z, R.string.success_compress, R.string.error_compress);
                break;
            case 7:
                this.o0.setVisibility(8);
                break;
        }
        this.i0.setLoading(false);
    }

    @Override // com.byd.filemanager.ui.fragment.BaseMvpFragment, androidx.fragment.app.Fragment
    public void r0() {
        this.t0 = null;
        if (this.s0 != null) {
            j1().getContentResolver().unregisterContentObserver(this.s0);
        }
        super.r0();
    }
}
