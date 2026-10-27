package com.byd.cleardata.ui.f;

import android.content.Context;
import android.content.pm.IPackageDataObserver;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import com.byd.cleardata.i.q;
import com.byd.cleardata.service.FileScanService;
import com.byd.cleardata.ui.d.c;
import com.byd.cleardata.ui.e.a;
import com.byd.cleardata.ui.view.EmptyRecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class j extends i<com.byd.cleardata.f.c.c> implements com.byd.cleardata.f.a.e {
    private static final com.byd.cleardata.d.f[] e0 = {com.byd.cleardata.d.f.DOCUMENT, com.byd.cleardata.d.f.AUDIO, com.byd.cleardata.d.f.VIDEO, com.byd.cleardata.d.f.IMAGE, com.byd.cleardata.d.f.COMPRESS, com.byd.cleardata.d.f.APK};
    private EmptyRecyclerView h0;
    private com.byd.cleardata.ui.d.c i0;
    private androidx.recyclerview.widget.d j0;
    private ImageView k0;
    private TextView l0;
    private View m0;
    private View n0;
    private Button o0;
    private TextView p0;
    private boolean r0;
    private boolean s0;
    private h t0;
    private ArrayList<com.byd.cleardata.d.d> f0 = new ArrayList<>();
    private final Handler g0 = new a(Looper.getMainLooper());
    private com.byd.cleardata.d.f q0 = com.byd.cleardata.d.f.OTHER;
    private final a.c<Object> u0 = new f();

    class a extends Handler {

        /* JADX INFO: renamed from: com.byd.cleardata.ui.f.j$a$a, reason: collision with other inner class name */
        class C0104a implements g {
            C0104a() {
            }

            @Override // com.byd.cleardata.ui.f.j.g
            public void a(int i) {
            }

            public IBinder asBinder() {
                return null;
            }

            public void onRemoveCompleted(String str, boolean z) {
                com.byd.cleardata.i.c.m("clear cache finish onRemoveCompleted");
                j.this.g0.sendEmptyMessage(4);
            }
        }

        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            com.byd.cleardata.i.c.b("handleMessage what = " + message.what);
            int i = message.what;
            if (i == 1) {
                j.this.g0.removeMessages(2);
                j.this.i0.h();
                return;
            }
            if (i == 2) {
                j.this.i0.h();
                if (j.this.f0.size() < 100) {
                    j.this.g0.sendEmptyMessageDelayed(2, 100L);
                    return;
                }
                return;
            }
            if (i == 3) {
                Context contextY = j.this.y();
                Objects.requireNonNull(contextY);
                com.byd.cleardata.i.e.c(contextY, new C0104a());
            } else {
                if (i == 4) {
                    q.a(R.string.str_clear_cache_data_success);
                } else if (i != 5) {
                    return;
                }
                j.this.p0.setText(com.byd.cleardata.i.e.k(com.byd.cleardata.i.e.A(j.this.y())));
            }
        }
    }

    class b implements c.g {
        b() {
        }

        @Override // com.byd.cleardata.ui.d.c.g
        public boolean a(View view, com.byd.cleardata.d.d dVar) {
            if (com.byd.cleardata.b.h().g() == com.byd.cleardata.d.e.PASTING) {
                q.a(R.string.pasting);
                return false;
            }
            if (!com.byd.cleardata.b.h().r()) {
                com.byd.cleardata.b.h().y(com.byd.cleardata.d.e.CHOOSING);
            }
            dVar.P(true);
            com.byd.cleardata.b.h().c(dVar);
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(11, Boolean.valueOf(com.byd.cleardata.b.h().l().size() == j.this.f0.size())));
            return true;
        }
    }

    class c implements com.byd.cleardata.ui.c<com.byd.cleardata.d.d> {
        c() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(com.byd.cleardata.d.d dVar, int i) {
            if (com.byd.cleardata.b.h().r()) {
                com.byd.cleardata.b.h().c(dVar);
                org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(11, Boolean.valueOf(com.byd.cleardata.b.h().l().size() == j.this.f0.size())));
                return;
            }
            if (dVar.N()) {
                q.a(!dVar.u() ? R.string.file_not_exists : R.string.error_open);
                return;
            }
            if (com.byd.cleardata.b.h().j() == 6) {
                org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(10, dVar));
                j.this.q0 = dVar.v();
                j jVar = j.this;
                ((com.byd.cleardata.f.c.c) jVar.Z).y(jVar.q0);
                com.byd.cleardata.b.h().w(dVar.E());
                return;
            }
            String strE = dVar.E();
            String strJ = com.byd.cleardata.a.f().j(com.byd.cleardata.b.h().k());
            if (com.byd.cleardata.a.s(strE, strJ)) {
                org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(10, dVar));
                com.byd.cleardata.b.h().w(strE);
                ((com.byd.cleardata.f.c.c) j.this.Z).A(com.byd.cleardata.b.h().j(), strE);
            } else {
                com.byd.cleardata.i.c.b("path = " + strE + " root = " + strJ);
            }
        }
    }

    class d extends RecyclerView.t {
        d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            super.a(recyclerView, i);
            try {
                if (i == 0) {
                    com.bumptech.glide.c.t(j.this.j1()).x();
                } else if (i != 2) {
                } else {
                    com.bumptech.glide.c.t(j.this.j1()).w();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            j.this.g0.sendEmptyMessage(3);
        }
    }

    class f implements a.c<Object> {
        f() {
        }

        @Override // com.byd.cleardata.ui.e.a.c
        public void a(int i, Object obj) {
            if (i == 0) {
                com.byd.cleardata.i.c.b("cancel task");
                j.this.g0.removeMessages(2);
                ((com.byd.cleardata.f.c.c) j.this.Z).l();
                com.byd.cleardata.b.h().C();
            }
        }
    }

    public interface g extends IPackageDataObserver {
        void a(int i);
    }

    private class h extends ContentObserver {
        public h(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            com.byd.cleardata.i.c.c("onChange", z + ", " + uri);
            j jVar = j.this;
            jVar.r0 = jVar.b0() ^ true;
        }
    }

    private void P1() {
        int i = com.byd.cleardata.b.h().i();
        this.i0.P(i);
        com.byd.cleardata.i.c.b("viewType = " + i);
        Q1(com.byd.cleardata.i.d.d(this.c0));
        if (i == 0) {
            EmptyRecyclerView emptyRecyclerView = this.h0;
            Context context = this.c0;
            emptyRecyclerView.setLayoutManager(new GridLayoutManager(context, com.byd.cleardata.i.d.d(context) ? 7 : 5));
            this.h0.X0(this.j0);
            return;
        }
        this.h0.setLayoutManager(new LinearLayoutManager(this.c0));
        androidx.recyclerview.widget.d dVar = new androidx.recyclerview.widget.d(this.c0, 1);
        this.j0 = dVar;
        dVar.l(androidx.core.content.a.d(this.c0, R.drawable.bg_divider));
        this.h0.h(this.j0);
    }

    private void Q1(boolean z) {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        RelativeLayout.LayoutParams layoutParams;
        Resources resourcesL;
        int i;
        int i2 = com.byd.cleardata.b.h().i();
        ConstraintLayout.b bVar = (ConstraintLayout.b) this.h0.getLayoutParams();
        if (z) {
            dimensionPixelSize = L().getDimensionPixelSize(i2 == 0 ? R.dimen.grid_start_land : R.dimen.list_start_land);
            dimensionPixelSize2 = L().getDimensionPixelSize(i2 == 0 ? R.dimen.grid_end_land : R.dimen.list_end_land);
            layoutParams = (RelativeLayout.LayoutParams) this.k0.getLayoutParams();
            resourcesL = L();
            i = R.dimen.dp_58;
        } else {
            dimensionPixelSize = L().getDimensionPixelSize(i2 == 0 ? R.dimen.grid_start : R.dimen.list_start);
            dimensionPixelSize2 = L().getDimensionPixelSize(i2 == 0 ? R.dimen.grid_end : R.dimen.list_end);
            layoutParams = (RelativeLayout.LayoutParams) this.k0.getLayoutParams();
            resourcesL = L();
            i = R.dimen.dp_233;
        }
        layoutParams.topMargin = resourcesL.getDimensionPixelSize(i);
        this.k0.setLayoutParams(layoutParams);
        bVar.setMarginStart(dimensionPixelSize);
        bVar.setMarginEnd(dimensionPixelSize2);
        this.h0.setLayoutParams(bVar);
    }

    private void R1(boolean z) {
        com.byd.cleardata.b bVarH;
        ArrayList<com.byd.cleardata.d.d> arrayList;
        Iterator<com.byd.cleardata.d.d> it = this.f0.iterator();
        if (z) {
            while (it.hasNext()) {
                it.next().P(true);
            }
            bVarH = com.byd.cleardata.b.h();
            arrayList = this.f0;
        } else {
            while (it.hasNext()) {
                it.next().P(false);
            }
            bVarH = com.byd.cleardata.b.h();
            arrayList = null;
        }
        bVarH.b(arrayList);
        this.i0.h();
        org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(11, Boolean.valueOf(z)));
    }

    private void T1() {
        if (!this.f0.isEmpty()) {
            this.f0.clear();
            this.i0.h();
        }
        ((com.byd.cleardata.f.c.c) this.Z).z(com.byd.cleardata.b.h().j(), com.byd.cleardata.b.h().k());
    }

    private void U1() {
        androidx.recyclerview.widget.d dVar;
        Context context;
        int i;
        this.j0 = new androidx.recyclerview.widget.d(this.c0, 1);
        if (com.byd.cleardata.b.h().i() == 0) {
            EmptyRecyclerView emptyRecyclerView = this.h0;
            Context context2 = this.c0;
            emptyRecyclerView.setLayoutManager(new GridLayoutManager(context2, com.byd.cleardata.i.d.d(context2) ? 7 : 5));
            dVar = this.j0;
            context = this.c0;
            i = R.drawable.bg_divider_transparent;
        } else {
            this.h0.setLayoutManager(new LinearLayoutManager(this.c0));
            dVar = this.j0;
            context = this.c0;
            i = R.drawable.bg_divider;
        }
        dVar.l(androidx.core.content.a.d(context, i));
        this.h0.h(this.j0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: V1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean W1() {
        T1();
        return false;
    }

    private void X1() {
        this.t0 = new h(new Handler());
        j1().getContentResolver().registerContentObserver(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, true, this.t0);
        j1().getContentResolver().registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, this.t0);
        j1().getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.t0);
    }

    private void Y1(com.byd.cleardata.d.d dVar) {
        if (dVar != null) {
            Z1(dVar.E());
        }
    }

    private void Z1(String str) {
        if (str != null) {
            FileScanService.e(str);
            return;
        }
        List<String> listO = com.byd.cleardata.b.h().o();
        if (listO.isEmpty()) {
            return;
        }
        FileScanService.e((String[]) listO.toArray(new String[0]));
    }

    private void a2() {
        this.f0.clear();
        this.i0.h();
        com.byd.cleardata.d.f fVar = com.byd.cleardata.d.f.OTHER;
        this.q0 = fVar;
        ((com.byd.cleardata.f.c.c) this.Z).y(fVar);
    }

    private void b2() {
        TextView textView;
        int iK = com.byd.cleardata.b.h().k();
        com.byd.cleardata.i.c.b("setEmpty rootIndex = " + iK);
        int i = R.string.empty_data;
        if (iK == 0) {
            this.k0.setImageResource(R.drawable.img_empty);
            textView = this.l0;
        } else if (iK == 1) {
            this.k0.setImageResource(R.drawable.img_empty_sd);
            textView = this.l0;
            if (!com.byd.cleardata.a.f().p(1)) {
                i = R.string.empty_sdcard;
            }
        } else {
            if (iK != 2) {
                return;
            }
            this.k0.setImageResource(R.drawable.img_empty_usb);
            textView = this.l0;
            if (!com.byd.cleardata.a.f().p(2)) {
                i = R.string.empty_usb;
            }
        }
        textView.setText(R(i));
    }

    @Override // com.byd.cleardata.ui.f.i
    protected void D1(Bundle bundle) {
        com.byd.cleardata.i.c.b("initData");
        b2();
        P p = this.Z;
        if (p != 0) {
            ((com.byd.cleardata.f.c.c) p).a(this);
        }
        if (bundle == null) {
            Looper.getMainLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.byd.cleardata.ui.f.a
                @Override // android.os.MessageQueue.IdleHandler
                public final boolean queueIdle() {
                    return this.f2842a.W1();
                }
            });
            return;
        }
        com.byd.cleardata.i.c.b("restore");
        com.byd.cleardata.ui.e.n nVar = this.a0;
        if (nVar != null) {
            nVar.R1(this.u0);
        }
        this.f0.addAll(com.byd.cleardata.b.h().f());
        this.i0.h();
    }

    @Override // com.byd.cleardata.ui.f.i
    protected void E1(View view) {
        this.h0 = (EmptyRecyclerView) view.findViewById(R.id.recyclerView);
        this.m0 = view.findViewById(R.id.ly_empty);
        this.k0 = (ImageView) view.findViewById(R.id.iv_icon_empty);
        this.l0 = (TextView) view.findViewById(R.id.tv_desc_empty);
        this.n0 = view.findViewById(R.id.ly_clear_cache);
        this.o0 = (Button) view.findViewById(R.id.bt_clear_cache);
        this.p0 = (TextView) view.findViewById(R.id.tv_current_cache);
        U1();
        com.byd.cleardata.ui.d.c cVar = new com.byd.cleardata.ui.d.c(this.c0, this.f0);
        this.i0 = cVar;
        cVar.P(com.byd.cleardata.b.h().i());
        this.i0.O(com.byd.cleardata.b.h().r());
        this.h0.setAdapter(this.i0);
        this.h0.A1(this.m0, this.n0);
        this.i0.N(new b());
        this.i0.M(new c());
        this.h0.k(new d());
        com.byd.cleardata.ui.g.i.a(this.h0, 0);
        Q1(com.byd.cleardata.i.d.d(this.c0));
        X1();
        this.o0.setOnClickListener(new e());
        this.g0.sendEmptyMessage(5);
    }

    @Override // com.byd.cleardata.ui.f.i, androidx.fragment.app.Fragment
    public void F0() {
        super.F0();
        int iJ = com.byd.cleardata.b.h().j();
        com.byd.cleardata.i.c.f("isMediaChange = %s ,isStartDeleteFileApp = %s", Boolean.valueOf(this.r0), Boolean.valueOf(this.s0));
        if (com.byd.cleardata.b.h().t() && this.r0) {
            if (iJ != 2) {
                ((com.byd.cleardata.f.c.c) this.Z).z(com.byd.cleardata.b.h().j(), com.byd.cleardata.b.h().k());
            } else {
                ((com.byd.cleardata.f.c.c) this.Z).A(com.byd.cleardata.b.h().j(), com.byd.cleardata.b.h().e());
            }
        }
        this.r0 = false;
        this.s0 = false;
    }

    @Override // com.byd.cleardata.ui.f.i
    protected int F1() {
        return R.layout.fragment_content;
    }

    @Override // com.byd.cleardata.ui.f.i, androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        super.G0(bundle);
        com.byd.cleardata.i.c.b("onSaveInstanceState");
        com.byd.cleardata.b.h().x(this.f0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.cleardata.ui.f.i
    /* JADX INFO: renamed from: S1, reason: merged with bridge method [inline-methods] */
    public com.byd.cleardata.f.c.c z1() {
        return new com.byd.cleardata.f.c.c(this);
    }

    @Override // com.byd.cleardata.ui.f.i, androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        super.e0(bundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0056  */
    @Override // com.byd.cleardata.f.a.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(int r3, com.byd.cleardata.d.d r4) {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.cleardata.ui.f.j.f(int, com.byd.cleardata.d.d):void");
    }

    @Override // com.byd.cleardata.f.a.e
    public void h(long j, long j2) {
        com.byd.cleardata.ui.e.n nVar = this.a0;
        if (nVar != null) {
            nVar.Y1(j, j2);
        }
    }

    @Override // com.byd.cleardata.f.a.e
    public void k(int i) {
        if (i == 3) {
            this.h0.setLoading(true);
            this.f0.clear();
            this.i0.h();
        } else if (i != 7) {
            if (i != 16) {
                return;
            }
            this.h0.setLoading(true);
            this.f0.clear();
            this.i0.h();
        }
        G1();
    }

    @Override // com.byd.cleardata.f.a.e
    public void m(int i, int i2, List<com.byd.cleardata.d.d> list) {
        org.greenrobot.eventbus.c cVarC;
        com.byd.cleardata.e.c cVar;
        com.byd.cleardata.i.c.b("onFinished type = " + i);
        B1();
        int iP = com.byd.cleardata.b.h().p();
        if (i == 1) {
            com.byd.cleardata.b.h().C();
            if (i2 == 1) {
                q.g(R.string.success_delete);
            } else if (i2 == 4) {
                q.e(R.string.error_delete);
            }
            if (com.byd.cleardata.i.e.H(list)) {
                FileScanService.e(com.byd.cleardata.a.g(list));
                org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.c());
                this.f0.removeAll(list);
                this.i0.h();
            }
        } else if (i == 16 || i == 3) {
            A1();
            if (i2 == 1) {
                if (com.byd.cleardata.i.e.H(list)) {
                    this.h0.i1(0);
                    this.f0.addAll(list);
                }
            } else if (i2 == 4) {
                com.byd.cleardata.i.c.b("query error");
            }
            if (com.byd.cleardata.b.h().j() == 1) {
                this.f0.clear();
            }
            if (this.f0.isEmpty()) {
                b2();
            }
            this.i0.h();
        } else if (i == 4) {
            com.byd.cleardata.b.h().C();
            if (i2 == 1) {
                q.g(R.string.success_copy);
            } else if (i2 == 4) {
                q.e(R.string.error_copy);
            }
            if (com.byd.cleardata.i.e.H(list)) {
                FileScanService.e(com.byd.cleardata.a.g(list));
                cVarC = org.greenrobot.eventbus.c.c();
                cVar = new com.byd.cleardata.e.c();
                cVarC.k(cVar);
                this.f0.addAll(list);
                this.i0.h();
                ((com.byd.cleardata.f.c.c) this.Z).C(iP, this.f0);
            }
        } else if (i == 5) {
            com.byd.cleardata.b.h().C();
            if (i2 == 1) {
                q.g(R.string.success_move);
            } else if (i2 == 4) {
                q.e(R.string.error_move);
            }
            if (com.byd.cleardata.i.e.H(list)) {
                FileScanService.e(com.byd.cleardata.a.g(list));
                cVarC = org.greenrobot.eventbus.c.c();
                cVar = new com.byd.cleardata.e.c();
                cVarC.k(cVar);
                this.f0.addAll(list);
                this.i0.h();
                ((com.byd.cleardata.f.c.c) this.Z).C(iP, this.f0);
            }
        } else if (i == 6) {
            com.byd.cleardata.b.h().C();
            if (i2 == 1) {
                q.g(R.string.success_compress);
            } else if (i2 == 4) {
                q.e(R.string.error_compress);
            }
            if (com.byd.cleardata.i.e.H(list)) {
                FileScanService.e(com.byd.cleardata.a.g(list));
                cVarC = org.greenrobot.eventbus.c.c();
                cVar = new com.byd.cleardata.e.c();
                cVarC.k(cVar);
                this.f0.addAll(list);
                this.i0.h();
                ((com.byd.cleardata.f.c.c) this.Z).C(iP, this.f0);
            }
        }
        this.h0.setLoading(false);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        androidx.recyclerview.widget.d dVar;
        Context context;
        int i;
        super.onConfigurationChanged(configuration);
        boolean z = configuration.orientation == 2;
        int i2 = com.byd.cleardata.b.h().i();
        if (this.i0 != null) {
            if (i2 == 0) {
                EmptyRecyclerView emptyRecyclerView = this.h0;
                Context context2 = this.c0;
                emptyRecyclerView.setLayoutManager(new GridLayoutManager(context2, com.byd.cleardata.i.d.d(context2) ? 7 : 5));
                dVar = this.j0;
                context = this.c0;
                i = R.drawable.bg_divider_transparent;
            } else {
                this.h0.setLayoutManager(new LinearLayoutManager(this.c0));
                dVar = this.j0;
                context = this.c0;
                i = R.drawable.bg_divider;
            }
            dVar.l(androidx.core.content.a.d(context, i));
        }
        Q1(z);
    }

    @org.greenrobot.eventbus.m(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.cleardata.e.b bVar) {
        int iB = bVar.b();
        int iK = com.byd.cleardata.b.h().k();
        if (iB == iK) {
            ((com.byd.cleardata.f.c.c) this.Z).l();
            this.f0.clear();
            this.i0.h();
            b2();
            if (com.byd.cleardata.a.f().p(iB)) {
                com.byd.cleardata.b.h().D(iB);
                ((com.byd.cleardata.f.c.c) this.Z).z(com.byd.cleardata.b.h().j(), iK);
            }
            com.byd.cleardata.b.h().B();
        }
    }

    @org.greenrobot.eventbus.m
    public void onFileStateEvent(com.byd.cleardata.e.d dVar) {
        com.byd.cleardata.i.c.b("onFileStateEvent");
        com.byd.cleardata.d.e eVarG = com.byd.cleardata.b.h().g();
        if (eVarG == com.byd.cleardata.d.e.NORMAL || eVarG == com.byd.cleardata.d.e.PASTING) {
            this.i0.O(false);
            ConstraintLayout.b bVar = (ConstraintLayout.b) this.h0.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = 0;
            if (eVarG == com.byd.cleardata.d.e.PASTING) {
                ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = L().getDimensionPixelSize(com.byd.cleardata.i.d.d(j1()) ? R.dimen.dp_100 : R.dimen.dp_120);
            }
            this.h0.setLayoutParams(bVar);
            return;
        }
        if (eVarG == com.byd.cleardata.d.e.CHOOSING) {
            this.i0.O(true);
            return;
        }
        if (eVarG == com.byd.cleardata.d.e.COPYING) {
            List<com.byd.cleardata.d.d> listL = com.byd.cleardata.b.h().l();
            H1(R.string.copying, listL.size());
            ((com.byd.cleardata.f.c.c) this.Z).o(listL, com.byd.cleardata.b.h().e());
        } else if (eVarG == com.byd.cleardata.d.e.MOVING) {
            List<com.byd.cleardata.d.d> listL2 = com.byd.cleardata.b.h().l();
            H1(R.string.moving, listL2.size());
            ((com.byd.cleardata.f.c.c) this.Z).s(listL2, com.byd.cleardata.b.h().e());
        } else if (eVarG == com.byd.cleardata.d.e.COMPRESSING) {
            List<com.byd.cleardata.d.d> listL3 = com.byd.cleardata.b.h().l();
            H1(R.string.compressing, listL3.size());
            ((com.byd.cleardata.f.c.c) this.Z).n(listL3, com.byd.cleardata.b.h().e());
        } else {
            if (eVarG != com.byd.cleardata.d.e.DELETING) {
                return;
            }
            List<com.byd.cleardata.d.d> listL4 = com.byd.cleardata.b.h().l();
            H1(R.string.deleting, listL4.size());
            ((com.byd.cleardata.f.c.c) this.Z).p(listL4);
        }
        this.a0.R1(this.u0);
    }

    @org.greenrobot.eventbus.m
    public void onMenuEvent(com.byd.cleardata.e.e eVar) {
        com.byd.cleardata.i.c.b("onMenuEvent liangsj ");
        if (com.byd.cleardata.b.h().r()) {
            com.byd.cleardata.b.h().C();
        }
        if (com.byd.cleardata.b.h().j() == 6) {
            a2();
        } else {
            if (com.byd.cleardata.b.h().j() != 1) {
                T1();
                return;
            }
            if (!this.f0.isEmpty()) {
                this.f0.clear();
            }
            this.i0.h();
        }
    }

    @org.greenrobot.eventbus.m
    public void onOperateEvent(com.byd.cleardata.e.f fVar) {
        int iB = fVar.b();
        com.byd.cleardata.i.c.b("operate = " + iB);
        if (iB == 9) {
            P1();
            return;
        }
        if (iB == 12) {
            R1(((Boolean) fVar.a()).booleanValue());
            return;
        }
        if (iB == 7) {
            ((com.byd.cleardata.f.c.c) this.Z).C(com.byd.cleardata.b.h().p(), this.f0);
            return;
        }
        if (iB == 2) {
            ((com.byd.cleardata.f.c.c) this.Z).C(com.byd.cleardata.b.h().p(), this.f0);
            String str = (String) fVar.a();
            com.byd.cleardata.d.d dVarN = com.byd.cleardata.b.h().n();
            if (dVarN != null) {
                if (TextUtils.isEmpty(str) || str.equals(dVarN.C())) {
                    com.byd.cleardata.b.h().C();
                    return;
                } else {
                    ((com.byd.cleardata.f.c.c) this.Z).B(dVarN, str);
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
            ((com.byd.cleardata.f.c.c) this.Z).k(com.byd.cleardata.b.h().e(), str2);
            return;
        }
        if (iB != 14) {
            if (iB == 20) {
                com.byd.cleardata.i.c.b("cancel task");
                ((com.byd.cleardata.f.c.c) this.Z).l();
                return;
            }
            return;
        }
        com.byd.cleardata.d.d dVar = (com.byd.cleardata.d.d) fVar.a();
        if (dVar != null) {
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(10, dVar));
            String strE = dVar.E();
            com.byd.cleardata.b.h().w(strE);
            ((com.byd.cleardata.f.c.c) this.Z).A(com.byd.cleardata.b.h().j(), strE);
        }
    }

    @org.greenrobot.eventbus.m(threadMode = ThreadMode.MAIN)
    public void onOuterFileChangeEvent(com.byd.cleardata.e.g gVar) {
        this.s0 = true;
    }

    @org.greenrobot.eventbus.m
    public void onPathEvent(com.byd.cleardata.e.h hVar) {
        if (com.byd.cleardata.b.h().j() != 6) {
            ((com.byd.cleardata.f.c.c) this.Z).A(com.byd.cleardata.b.h().j(), com.byd.cleardata.b.h().e());
        } else {
            a2();
        }
        com.byd.cleardata.b.h().B();
    }

    @org.greenrobot.eventbus.m
    public void onTabEvent(com.byd.cleardata.e.i iVar) {
        if (iVar.b() != 15) {
            b2();
            T1();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00a8  */
    @Override // com.byd.cleardata.f.a.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void r(int r3, boolean r4, boolean r5) {
        /*
            Method dump skipped, instruction units count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.cleardata.ui.f.j.r(int, boolean, boolean):void");
    }

    @Override // com.byd.cleardata.ui.f.i, androidx.fragment.app.Fragment
    public void r0() {
        if (this.t0 != null) {
            j1().getContentResolver().unregisterContentObserver(this.t0);
        }
        super.r0();
    }
}
