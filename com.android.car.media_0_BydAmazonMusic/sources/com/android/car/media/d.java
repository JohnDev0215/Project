package com.android.car.media;

import android.car.content.pm.XCarPackageManager;
import android.car.utils.Logs;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.media.MediaActivity;
import com.android.car.media.a;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.browse.MediaItemsRepository;
import com.android.car.media.common.source.a;
import com.android.car.media.f;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.rotary.FocusParkingView;
import com.android.car.ui.toolbar.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import spi.a1;
import spi.b7;
import spi.d4;
import spi.m;
import spi.q6;
import spi.uc;
import spi.yc;

/* JADX INFO: compiled from: MediaActivityController.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends com.android.car.media.f {
    public Handler A;
    public a B;
    public b C;

    @Nullable
    public List<MediaItemMetadata> D;
    public final Observer<a.c> E;
    public C0009d F;
    public final e G;
    public final q6 H;
    public final MediaItemsRepository i;
    public final g j;
    public final ViewGroup k;
    public final FocusParkingView l;
    public Insets m;
    public boolean n;
    public final Map<MediaItemMetadata, com.android.car.media.a> o;
    public final Map<View, com.android.car.media.a> p;
    public final com.android.car.media.a q;
    public final com.android.car.media.a r;
    public Stack<MediaItemMetadata> s;
    public Stack<MediaItemMetadata> t;
    public final MediaActivity.ViewModel u;
    public int v;
    public int w;
    public boolean x;
    public boolean y;
    public boolean z;

    /* JADX INFO: compiled from: MediaActivityController.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (d.this.i.e.getValue().a) {
                    Logs.d("MediaActivityCtr", "mSearchTimeOutRunnable search timeout,stop loading");
                    MediaItemsRepository.MediaItemsLiveData mediaItemsLiveData = d.this.i.e;
                    Objects.requireNonNull(mediaItemsLiveData);
                    mediaItemsLiveData.setValue(new d4(false, null));
                }
            } catch (Throwable th) {
                Logs.d("MediaActivityCtr", "handle search timeout error");
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: MediaActivityController.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                Logs.d("MediaActivityCtr", "handleTimeout tab size:" + d.this.f.a());
                if (d.this.f.a() == 0) {
                    com.android.car.media.a aVar = d.this.q;
                    aVar.k();
                    aVar.j.setText(com.android.car.media.main.R$string.nothing_to_play);
                    uc.b(aVar.h.getView(), 0, null);
                    uc.b(aVar.i, 0, null);
                    uc.g(aVar.j, 0, null);
                }
            } catch (Throwable th) {
                Logs.d("MediaActivityCtr", "handle root loading timeout error");
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: MediaActivityController.java */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            d.d(d.this);
        }
    }

    /* JADX INFO: renamed from: com.android.car.media.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaActivityController.java */
    public class C0009d extends f.a {
        public C0009d() {
            super();
        }

        @Override // spi.m.a
        public final void b(String str) {
            Logs.d("MediaActivityCtr", "onSearch: " + str);
            d.this.u.c().f = str;
            d.this.u.g(0);
            d.this.r(str);
        }

        @Override // spi.m.a
        public final void c() {
            d.this.k();
        }

        @Override // spi.m.a
        public final void e(MediaItemMetadata mediaItemMetadata) {
            d dVar = d.this;
            if (!dVar.y || mediaItemMetadata == null || mediaItemMetadata == dVar.u.d()) {
                return;
            }
            d dVar2 = d.this;
            dVar2.e(dVar2.s);
            d.this.s.push(mediaItemMetadata);
            d.this.m(true);
        }
    }

    /* JADX INFO: compiled from: MediaActivityController.java */
    public class e implements a.c {
        public e() {
        }

        public final FragmentActivity a() {
            MediaActivity mediaActivity = (MediaActivity) d.this.j;
            Objects.requireNonNull(mediaActivity);
            return mediaActivity;
        }
    }

    /* JADX INFO: compiled from: MediaActivityController.java */
    public static /* synthetic */ class f {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[a.e.values().length];
            a = iArr;
            try {
                iArr[a.e.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[a.e.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[a.e.DISCONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[a.e.REJECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[a.e.SUSPENDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: compiled from: MediaActivityController.java */
    public interface g {
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public d(g gVar, MediaItemsRepository mediaItemsRepository, XCarPackageManager xCarPackageManager, ViewGroup viewGroup) {
        MediaActivity mediaActivity = (MediaActivity) gVar;
        Objects.requireNonNull(mediaActivity);
        super(mediaActivity, xCarPackageManager, viewGroup, com.android.car.media.main.R$layout.fragment_browse);
        this.o = new HashMap();
        this.p = new HashMap();
        final int i = 1;
        this.y = true;
        final int i2 = 0;
        this.z = false;
        this.A = new Handler(Looper.getMainLooper());
        this.B = new a();
        this.C = new b();
        Observer<a.c> observer = new Observer(this) { // from class: spi.r6
            public final /* synthetic */ com.android.car.media.d b;

            {
                this.b = this;
            }

            /* JADX WARN: Removed duplicated region for block: B:56:0x012e  */
            /* JADX WARN: Removed duplicated region for block: B:69:0x0155  */
            /* JADX WARN: Removed duplicated region for block: B:82:0x017c  */
            /* JADX WARN: Type inference failed for: r0v27, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            /* JADX WARN: Type inference failed for: r0v30, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            /* JADX WARN: Type inference failed for: r8v3, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onChanged(java.lang.Object r8) {
                /*
                    Method dump skipped, instruction units count: 566
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: spi.r6.onChanged(java.lang.Object):void");
            }
        };
        this.E = observer;
        this.F = new C0009d();
        e eVar = new e();
        this.G = eVar;
        this.H = new q6(this);
        this.j = gVar;
        this.i = mediaItemsRepository;
        MediaActivity.ViewModel viewModel = (MediaActivity.ViewModel) ViewModelProviders.of(mediaActivity).get(MediaActivity.ViewModel.class);
        this.u = viewModel;
        this.t = viewModel.c().c;
        this.s = viewModel.c().b;
        ViewGroup viewGroup2 = (ViewGroup) this.e.requireViewById(com.android.car.media.main.R$id.browse_content_area);
        this.k = viewGroup2;
        this.l = (FocusParkingView) mediaActivity.requireViewById(com.android.car.media.main.R$id.fpv);
        MediaItemsRepository.MediaItemsLiveData mediaItemsLiveData = mediaItemsRepository.d;
        com.android.car.media.a aVarF = com.android.car.media.a.f(eVar, viewGroup2, mediaItemsLiveData);
        this.q = aVarF;
        aVarF.g.setAlpha(1.0f);
        com.android.car.media.a aVarG = com.android.car.media.a.g(eVar, viewGroup2, mediaItemsRepository.e);
        this.r = aVarG;
        aVarG.q = true;
        Logs.d("BrowseViewController", "BrowseViewController isSearchView: true");
        aVarG.m.j.f = true;
        boolean zF = viewModel.f();
        uc.e(aVarG.g, zF);
        if (zF) {
            aVarG.g.setAlpha(1.0f);
        }
        aVarG.b.setOnClickListener(new c());
        m mVar = this.f;
        mVar.j = this.F;
        mVar.c.setSearchQuery(viewModel.c().f);
        if (this.f.c.getSearchCapabilities().a) {
            RecyclerView recyclerView = new RecyclerView(mediaActivity);
            recyclerView.setAdapter(aVarG.m);
            recyclerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            recyclerView.setLayoutManager(new LinearLayoutManager(mediaActivity));
            recyclerView.setBackground(mediaActivity.getDrawable(com.android.car.media.main.R$drawable.car_ui_ime_wide_screen_background));
            m mVar2 = this.f;
            e.b bVarA = com.android.car.ui.toolbar.e.a();
            bVarA.a = recyclerView;
            mVar2.c.setSearchConfig(bVarA.a());
        }
        if (a1.a()) {
            this.f.c(com.android.car.media.main.R$drawable.bg_controller_denza);
            m mVar3 = this.f;
            mVar3.c.setSearchBack(com.android.car.media.main.R$drawable.byd_car_toolbar_search_bg_selector_denza);
        } else {
            int i3 = a1.a;
            this.f.c(com.android.car.media.main.R$drawable.bg_controller);
        }
        this.f.c.setBackgroundShown(true);
        q();
        mediaItemsRepository.c.observeForever(observer);
        viewModel.c.observeForever(new Observer(this) { // from class: spi.r6
            public final /* synthetic */ com.android.car.media.d b;

            {
                this.b = this;
            }

            /* JADX WARN: Removed duplicated region for block: B:56:0x012e  */
            /* JADX WARN: Removed duplicated region for block: B:69:0x0155  */
            /* JADX WARN: Removed duplicated region for block: B:82:0x017c  */
            /* JADX WARN: Type inference failed for: r0v27, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            /* JADX WARN: Type inference failed for: r0v30, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            /* JADX WARN: Type inference failed for: r8v3, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onChanged(java.lang.Object r8) {
                /*
                    Method dump skipped, instruction units count: 566
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: spi.r6.onChanged(java.lang.Object):void");
            }
        });
        final int i4 = 2;
        mediaItemsLiveData.observe(mediaActivity, new Observer(this) { // from class: spi.r6
            public final /* synthetic */ com.android.car.media.d b;

            {
                this.b = this;
            }

            /* JADX WARN: Removed duplicated region for block: B:56:0x012e  */
            /* JADX WARN: Removed duplicated region for block: B:69:0x0155  */
            /* JADX WARN: Removed duplicated region for block: B:82:0x017c  */
            /* JADX WARN: Type inference failed for: r0v27, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            /* JADX WARN: Type inference failed for: r0v30, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            /* JADX WARN: Type inference failed for: r8v3, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onChanged(java.lang.Object r8) {
                /*
                    Method dump skipped, instruction units count: 566
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: spi.r6.onChanged(java.lang.Object):void");
            }
        });
        final int i5 = 3;
        viewModel.e.observe(mediaActivity, new Observer(this) { // from class: spi.r6
            public final /* synthetic */ com.android.car.media.d b;

            {
                this.b = this;
            }

            /* JADX WARN: Removed duplicated region for block: B:56:0x012e  */
            /* JADX WARN: Removed duplicated region for block: B:69:0x0155  */
            /* JADX WARN: Removed duplicated region for block: B:82:0x017c  */
            /* JADX WARN: Type inference failed for: r0v27, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            /* JADX WARN: Type inference failed for: r0v30, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            /* JADX WARN: Type inference failed for: r8v3, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onChanged(java.lang.Object r8) {
                /*
                    Method dump skipped, instruction units count: 566
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: spi.r6.onChanged(java.lang.Object):void");
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void c(d dVar, d4 d4Var) {
        Objects.requireNonNull(dVar);
        b7 b7Var = d4Var.a ? null : (b7) d4Var.c;
        super.a(b7Var);
        dVar.s(b7Var == null ? new ArrayList() : null);
        dVar.t = dVar.u.c().c;
        dVar.s = dVar.u.c().b;
        dVar.q();
    }

    public static void d(d dVar) {
        MediaActivity mediaActivity = (MediaActivity) dVar.j;
        Objects.requireNonNull(mediaActivity);
        ((InputMethodManager) mediaActivity.getSystemService("input_method")).hideSoftInputFromWindow(dVar.e.getWindowToken(), 0);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
    @Override // com.android.car.media.f, spi.z4
    public final void b(@NonNull Insets insets) {
        this.m = insets;
        Iterator it = this.o.values().iterator();
        while (it.hasNext()) {
            ((com.android.car.media.a) it.next()).h(this.m);
        }
        this.q.h(this.m);
        this.r.h(this.m);
    }

    public final void e(List<MediaItemMetadata> list) {
        Iterator<MediaItemMetadata> it = list.iterator();
        while (it.hasNext()) {
            i(it.next());
        }
        list.clear();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
    public final com.android.car.media.a f(@NonNull MediaItemMetadata mediaItemMetadata) {
        com.android.car.media.a aVarE = (com.android.car.media.a) this.o.get(mediaItemMetadata);
        if (aVarE == null) {
            aVarE = com.android.car.media.a.e(this.G, this.k, mediaItemMetadata, this.i.c(mediaItemMetadata.b()), this.v, this.w);
            Insets insets = this.m;
            if (insets != null) {
                aVarE.h(insets);
            }
            aVarE.i();
            this.o.put(mediaItemMetadata, aVarE);
        }
        return aVarE;
    }

    @Nullable
    public final MediaItemMetadata g() {
        Stack<MediaItemMetadata> stackH = h();
        if (stackH.isEmpty()) {
            return null;
        }
        return stackH.lastElement();
    }

    public final Stack<MediaItemMetadata> h() {
        return this.u.e() ? this.t : this.s;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.HashMap, java.util.Map<android.view.View, com.android.car.media.a>] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
    public final void i(@Nullable MediaItemMetadata mediaItemMetadata) {
        com.android.car.media.a aVar;
        if (mediaItemMetadata == null || (aVar = (com.android.car.media.a) this.o.get(mediaItemMetadata)) == null) {
            return;
        }
        if (aVar.g.getVisibility() == 0) {
            View view = aVar.g;
            this.p.put(view, aVar);
            n(false, view, this.H);
        } else {
            aVar.b();
        }
        this.o.remove(mediaItemMetadata);
    }

    public final boolean j() {
        return this.u.e() ? this.t.isEmpty() : this.s.size() <= 1;
    }

    public final void k() {
        if (!this.u.e()) {
            o(true);
            q();
        } else {
            e(this.t);
            p(true);
            q();
        }
    }

    public final void l() {
        CarUiRecyclerView carUiRecyclerView;
        MediaItemMetadata mediaItemMetadataG = g();
        if (mediaItemMetadataG == null || (carUiRecyclerView = (CarUiRecyclerView) f(mediaItemMetadataG).g.findViewById(com.android.car.media.main.R$id.browse_list)) == null || !(carUiRecyclerView instanceof yc.a) || carUiRecyclerView.getView().hasFocus() || carUiRecyclerView.getView().isInTouchMode()) {
            return;
        }
        this.l.performAccessibilityAction(1, null);
        yc.a aVar = (yc.a) carUiRecyclerView;
        if (yc.k(aVar)) {
            return;
        }
        yc.j(aVar);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.HashMap, java.util.Map<com.android.car.media.common.MediaItemMetadata, com.android.car.media.a>] */
    public final void m(boolean z) {
        MediaItemMetadata mediaItemMetadataG = g();
        if (mediaItemMetadataG == null) {
            return;
        }
        com.android.car.media.a aVarF = z ? f(mediaItemMetadataG) : (com.android.car.media.a) this.o.get(mediaItemMetadataG);
        if (aVarF != null) {
            n(z, aVarF.g, this.H);
        }
    }

    public final void n(boolean z, @NonNull View view, @Nullable uc.d dVar) {
        View viewFindFocus;
        CarUiRecyclerView carUiRecyclerView = (CarUiRecyclerView) view.findViewById(com.android.car.media.main.R$id.browse_list);
        if (carUiRecyclerView != null && (carUiRecyclerView instanceof yc.a) && !carUiRecyclerView.getView().isInTouchMode()) {
            if (!z && carUiRecyclerView.getView().hasFocus()) {
                this.l.performAccessibilityAction(1, null);
            }
            if (z && ((viewFindFocus = carUiRecyclerView.getView().getRootView().findFocus()) == null || (viewFindFocus instanceof FocusParkingView))) {
                yc.a aVar = (yc.a) carUiRecyclerView;
                if (!yc.k(aVar)) {
                    yc.j(aVar);
                }
            }
        }
        int i = this.d;
        if (z) {
            uc.g(view, i, dVar);
        } else {
            uc.b(view, i, dVar);
        }
    }

    public final void o(boolean z) {
        if (this.u.e() != z) {
            if (z) {
                m(false);
            }
            t(z);
            this.u.c().d = z;
            p(z);
            if (z) {
                return;
            }
            m(true);
        }
    }

    public final void p(boolean z) {
        if (this.u.f() != z) {
            this.u.c().e = z;
            n(z, this.r.g, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void q() {
        /*
            r7 = this;
            com.android.car.media.MediaActivity$ViewModel r0 = r7.u
            boolean r0 = r0.e()
            boolean r1 = r7.j()
            r2 = 1
            r1 = r1 ^ r2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "App bar is in stacked state: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "MediaActivityCtr"
            android.car.utils.Logs.d(r4, r3)
            spi.m r3 = r7.f
            if (r0 == 0) goto L29
            spi.v9 r4 = spi.v9.SEARCH
            goto L2b
        L29:
            spi.v9 r4 = spi.v9.DISABLED
        L2b:
            com.android.car.ui.toolbar.j r5 = r3.c
            spi.v9 r5 = r5.getSearchMode()
            if (r5 == r4) goto L3e
            com.android.car.ui.toolbar.j r5 = r3.c
            r5.setSearchMode(r4)
            r3.g()
            r3.f()
        L3e:
            spi.m r3 = r7.f
            if (r1 != 0) goto L48
            if (r0 == 0) goto L45
            goto L48
        L45:
            spi.s7 r4 = spi.s7.DISABLED
            goto L4a
        L48:
            spi.s7 r4 = spi.s7.BACK
        L4a:
            r3.e(r4)
            spi.m r3 = r7.f
            r4 = 0
            if (r0 != 0) goto L9a
            boolean r5 = r7.j()
            r5 = r5 ^ r2
            if (r5 == 0) goto L64
            com.android.car.media.common.MediaItemMetadata r5 = r7.g()
            if (r5 == 0) goto L68
            java.lang.CharSequence r5 = r5.f()
            goto L9b
        L64:
            java.util.List<com.android.car.media.common.MediaItemMetadata> r5 = r7.D
            if (r5 != 0) goto L6b
        L68:
            java.lang.String r5 = ""
            goto L9b
        L6b:
            int r5 = r5.size()
            if (r5 != r2) goto L7e
            java.util.List<com.android.car.media.common.MediaItemMetadata> r5 = r7.D
            java.lang.Object r5 = r5.get(r4)
            com.android.car.media.common.MediaItemMetadata r5 = (com.android.car.media.common.MediaItemMetadata) r5
            java.lang.CharSequence r5 = r5.f()
            goto L9b
        L7e:
            com.android.car.media.common.source.MediaSourceViewModel r5 = r7.g
            androidx.lifecycle.MutableLiveData<spi.b7> r5 = r5.c
            java.lang.Object r5 = r5.getValue()
            spi.b7 r5 = (spi.b7) r5
            if (r5 == 0) goto L8d
            java.lang.CharSequence r5 = r5.b
            goto L9b
        L8d:
            androidx.fragment.app.FragmentActivity r5 = r7.c
            android.content.res.Resources r5 = r5.getResources()
            int r6 = com.android.car.media.main.R$string.media_app_title
            java.lang.String r5 = r5.getString(r6)
            goto L9b
        L9a:
            r5 = 0
        L9b:
            com.android.car.ui.toolbar.j r3 = r3.c
            r3.setTitle(r5)
            spi.m r7 = r7.f
            if (r0 == 0) goto La9
            if (r1 == 0) goto La7
            goto La9
        La7:
            r0 = r4
            goto Laa
        La9:
            r0 = r2
        Laa:
            r7.l = r0
            com.android.car.ui.toolbar.b r1 = r7.f
            if (r0 == 0) goto Lb5
            boolean r7 = r7.k
            if (r7 == 0) goto Lb5
            goto Lb6
        Lb5:
            r2 = r4
        Lb6:
            r1.e(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.media.d.q():void");
    }

    public final void r(@Nullable String str) {
        MediaItemsRepository mediaItemsRepository = this.i;
        mediaItemsRepository.f = str;
        if (TextUtils.isEmpty(str)) {
            MediaItemsRepository.MediaItemsLiveData mediaItemsLiveData = mediaItemsRepository.e;
            int i = MediaItemsRepository.MediaItemsLiveData.a;
            Objects.requireNonNull(mediaItemsLiveData);
            mediaItemsLiveData.setValue(new d4(false, null));
        } else {
            MediaItemsRepository.MediaItemsLiveData mediaItemsLiveData2 = mediaItemsRepository.e;
            int i2 = MediaItemsRepository.MediaItemsLiveData.a;
            Objects.requireNonNull(mediaItemsLiveData2);
            mediaItemsLiveData2.setValue(new d4(true, null));
            try {
                Logs.d("MediaItemsRepository", "searchByManual: mBrowser search  = " + str);
                mediaItemsRepository.a.b.search(mediaItemsRepository.f, null, mediaItemsRepository.j);
            } catch (Exception e2) {
                Logs.e("MediaItemsRepository", "search error: " + e2);
            }
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.A.hasCallbacks(this.B)) {
            this.A.removeCallbacks(this.B);
        }
        this.A.postDelayed(this.B, 10000L);
        try {
            Logs.d("MediaActivityCtr", "go search unShowNoMedia tab size" + this.f.a());
            if (this.f.a() == 0) {
                com.android.car.media.a aVar = this.q;
                uc.g(aVar.h.getView(), 0, null);
                uc.b(aVar.i, 0, null);
                uc.b(aVar.j, 0, null);
            }
        } catch (Throwable th) {
            Logs.d("MediaActivityCtr", "go search error");
            th.printStackTrace();
        }
    }

    public final void s(@Nullable List<MediaItemMetadata> list) {
        if (Objects.equals(this.D, list)) {
            return;
        }
        this.D = list;
        List<MediaItemMetadata> list2 = null;
        if (list == null || list.isEmpty()) {
            this.f.d(null);
            this.f.b(null);
            if (list != null) {
                e(this.s);
            }
            q();
            return;
        }
        MediaItemMetadata mediaItemMetadataD = this.u.d();
        MediaItemMetadata mediaItemMetadata = list.contains(mediaItemMetadataD) ? mediaItemMetadataD : list.get(0);
        try {
            this.y = false;
            m mVar = this.f;
            if (this.D.size() != 1) {
                list2 = this.D;
            }
            mVar.d(list2);
            this.f.b(mediaItemMetadata);
            if (mediaItemMetadataD != mediaItemMetadata) {
                e(this.s);
                this.s.push(mediaItemMetadata);
            }
            if (!this.u.f()) {
                m(true);
            }
            this.y = true;
            q();
        } catch (Throwable th) {
            this.y = true;
            throw th;
        }
    }

    public final void t(boolean z) {
        try {
            this.c.findViewById(com.android.car.media.main.R$id.car_ui_toolbar_line).setVisibility(z ? 4 : 0);
        } catch (Exception unused) {
            Logs.e("MediaActivityCtr", "updateToolBarBottomLine error");
        }
    }
}
