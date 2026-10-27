package org.chromium.chrome.browser.ntp;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.a8;
import defpackage.ag2;
import defpackage.bq;
import defpackage.by1;
import defpackage.c01;
import defpackage.cg2;
import defpackage.dg2;
import defpackage.hw0;
import defpackage.il0;
import defpackage.im;
import defpackage.jk1;
import defpackage.ko0;
import defpackage.lj1;
import defpackage.ll;
import defpackage.n3;
import defpackage.nm1;
import defpackage.oj2;
import defpackage.om1;
import defpackage.pk3;
import defpackage.pm1;
import defpackage.q2;
import defpackage.qh3;
import defpackage.ql0;
import defpackage.qm1;
import defpackage.qt3;
import defpackage.rm1;
import defpackage.s61;
import defpackage.si1;
import defpackage.sk;
import defpackage.sm1;
import defpackage.so3;
import defpackage.tl0;
import defpackage.tp2;
import defpackage.uc2;
import defpackage.um1;
import defpackage.wp2;
import defpackage.x63;
import defpackage.x80;
import defpackage.ym1;
import defpackage.yp;
import defpackage.z7;
import defpackage.zc0;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.logo.LogoView;
import org.chromium.chrome.browser.logo.a;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.ntp.h;
import org.chromium.chrome.browser.ntp.sql.CardDBHelper;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.favicon.R$color;
import org.chromium.chrome.browser.ui.favicon.R$dimen;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class NewTabPageLayout extends LinearLayout implements CardDBHelper.a {
    public static final /* synthetic */ int L = 0;
    public WindowAndroid A;
    public int B;
    public tl0 C;
    public um1 D;
    public ArrayList E;
    public g F;
    public RecyclerView G;
    public PopupWindow H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public wp2 f70J;
    public ql0 K;
    public final int e;
    public final Context f;
    public View g;
    public org.chromium.chrome.browser.logo.a h;
    public ag2 i;
    public ImageView j;
    public ViewGroup k;
    public h.d l;
    public sm1 m;
    public Activity n;
    public qh3 o;
    public ll p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public float w;
    public boolean x;
    public boolean y;
    public boolean z;

    public class a implements LargeIconBridge.LargeIconCallback {
        public final /* synthetic */ int e;
        public final /* synthetic */ int[] f;

        public a(int i, int[] iArr) {
            this.e = i;
            this.f = iArr;
        }

        @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
        public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
            int i3 = this.e;
            NewTabPageLayout newTabPageLayout = NewTabPageLayout.this;
            if (bitmap == null) {
                Context context = newTabPageLayout.f;
                int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.card_monogram_size);
                ((im) newTabPageLayout.E.get(i3)).a = new uc2(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize / 2, context.getColor(R$color.explanation_text_color), r0.getDimensionPixelSize(R$dimen.card_monogram_text_size)).b(((im) newTabPageLayout.E.get(i3)).c, false);
            } else {
                ((im) newTabPageLayout.E.get(i3)).a = bitmap;
                ((im) newTabPageLayout.E.get(i3)).b = -1;
            }
            int[] iArr = this.f;
            int i4 = iArr[0] + 1;
            iArr[0] = i4;
            if (i4 == newTabPageLayout.E.size()) {
                newTabPageLayout.F.D(newTabPageLayout.E);
            }
        }
    }

    public class b implements View.OnClickListener {
        public final /* synthetic */ int e;

        public b(int i) {
            this.e = i;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NewTabPageLayout newTabPageLayout = NewTabPageLayout.this;
            Intent intent = new Intent(newTabPageLayout.f, (Class<?>) CardEditActivity.class);
            intent.putExtra("CardType", 1);
            intent.putExtra("CARD_POS", this.e);
            newTabPageLayout.n.startActivity(intent);
            newTabPageLayout.H.dismiss();
        }
    }

    public class c implements View.OnClickListener {
        public final /* synthetic */ int e;

        public c(int i) {
            this.e = i;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NewTabPageLayout newTabPageLayout = NewTabPageLayout.this;
            ArrayList arrayList = newTabPageLayout.E;
            if (arrayList != null && !arrayList.isEmpty()) {
                ArrayList arrayList2 = newTabPageLayout.E;
                int i = this.e;
                im imVar = (im) arrayList2.get(i);
                if (imVar != null) {
                    Context context = newTabPageLayout.f;
                    CardDBHelper cardDBHelperQ = CardDBHelper.q(context);
                    String str = imVar.e;
                    cardDBHelperQ.getClass();
                    CardDBHelper.i.remove(i);
                    NewTabPageLayout newTabPageLayout2 = (NewTabPageLayout) cardDBHelperQ.g;
                    newTabPageLayout2.F.D(newTabPageLayout2.E);
                    cardDBHelperQ.e.delete("card", "id=?", new String[]{String.valueOf(str)});
                    wp2 wp2Var = newTabPageLayout.f70J;
                    tp2 tp2VarA = tp2.a(imVar.d, new qm1(newTabPageLayout, i, imVar), 0, 11);
                    tp2VarA.c = context.getString(R$string.card_delete_msg);
                    tp2VarA.d = context.getString(R$string.undo);
                    tp2VarA.e = null;
                    wp2Var.c(tp2VarA);
                }
            }
            newTabPageLayout.H.dismiss();
        }
    }

    public NewTabPageLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.p = new ll();
        this.s = true;
        this.I = 0;
        this.f = context;
        this.e = getResources().getDimensionPixelSize(org.chromium.chrome.R$dimen.tile_grid_layout_bleed);
    }

    public static hw0 a(Resources resources, int i, int i2, View view, boolean z) {
        hw0 hw0Var = new hw0(resources, "IPH_FeatureNotificationGuideVoiceSearchHelpBubble", i, i2);
        hw0Var.j = view;
        hw0Var.n = new Rect(0, 0, 0, -resources.getDimensionPixelOffset(org.chromium.chrome.R$dimen.video_tutorial_try_now_iph_ntp_searchbox_y_inset));
        if (z) {
            hw0Var.k = new om1(view, 0);
            hw0Var.m = new qt3(view, 1);
        }
        return hw0Var;
    }

    private void getCardData() {
        Context context = this.f;
        this.E = CardDBHelper.q(context).C();
        CardDBHelper.q(context).g = this;
    }

    private void getCardImg() {
        LargeIconBridge largeIconBridge = new LargeIconBridge(Profile.d());
        int dimensionPixelSize = this.f.getResources().getDimensionPixelSize(org.chromium.chrome.browser.settings.R$dimen.card_size);
        int[] iArr = {0};
        for (int i = 0; i < this.E.size(); i++) {
            largeIconBridge.b((GURL) N.M1WDPiaY(((im) this.E.get(i)).c), dimensionPixelSize, new a(i, iArr));
        }
    }

    private int getGridMvtBottomMargin() {
        int i = org.chromium.chrome.R$dimen.tile_grid_layout_bottom_margin;
        if (!this.s) {
            return getResources().getDimensionPixelOffset(i);
        }
        if (il0.c()) {
            i = org.chromium.chrome.R$dimen.tile_grid_layout_bottom_margin_push_down_large;
        } else if (il0.d()) {
            i = org.chromium.chrome.R$dimen.tile_grid_layout_bottom_margin_push_down_small;
        } else if (il0.b()) {
            i = org.chromium.chrome.R$dimen.tile_grid_layout_bottom_margin_pull_up;
        }
        return getResources().getDimensionPixelOffset(i);
    }

    private int getGridMvtTopMargin() {
        if (!this.s) {
            return getResources().getDimensionPixelOffset(org.chromium.chrome.R$dimen.tile_grid_layout_no_logo_top_margin);
        }
        int i = org.chromium.chrome.R$dimen.tile_grid_layout_top_margin;
        if (il0.c()) {
            i = org.chromium.chrome.R$dimen.tile_grid_layout_top_margin_push_down_large;
        } else if (il0.d()) {
            i = org.chromium.chrome.R$dimen.tile_grid_layout_top_margin_push_down_small;
        } else if (il0.b()) {
            i = org.chromium.chrome.R$dimen.tile_grid_layout_top_margin_pull_up;
        }
        return getResources().getDimensionPixelOffset(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(android.content.res.Configuration r6) {
        /*
            r5 = this;
            android.app.Activity r0 = r5.n
            boolean r0 = defpackage.z7.M(r0)
            if (r0 != 0) goto L50
            androidx.recyclerview.widget.RecyclerView r0 = r5.G
            if (r0 == 0) goto L50
            android.app.Activity r0 = r5.n
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            android.app.Activity r1 = r5.n
            r2 = 1123811328(0x42fc0000, float:126.0)
            int r1 = defpackage.ao3.a(r1, r2)
            int r1 = r1 * 3
            androidx.recyclerview.widget.RecyclerView r2 = r5.G
            android.view.ViewParent r2 = r2.getParent()
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            android.view.ViewGroup$LayoutParams r3 = r2.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r3 = (android.view.ViewGroup.MarginLayoutParams) r3
            int r6 = r6.orientation
            r4 = 1
            if (r6 != r4) goto L4a
            vj1 r6 = defpackage.vj1.h
            android.content.Context r5 = r5.getContext()
            android.app.Activity r5 = (android.app.Activity) r5
            r6.getClass()
            boolean r5 = defpackage.vj1.o(r5)
            if (r5 != 0) goto L4a
            int r5 = r0.heightPixels
            int r5 = r5 - r1
            int r5 = r5 / 3
            goto L4b
        L4a:
            r5 = 0
        L4b:
            r3.topMargin = r5
            r2.setLayoutParams(r3)
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ntp.NewTabPageLayout.b(android.content.res.Configuration):void");
    }

    public final void c(h.b bVar, Activity activity, boolean z, org.chromium.chrome.browser.feed.e eVar, qh3 qh3Var, q2 q2Var, um1 um1Var, boolean z2, WindowAndroid windowAndroid, wp2 wp2Var) {
        TraceEvent.q("NewTabPageLayout.initialize()", null);
        this.C = eVar;
        this.m = bVar;
        this.n = activity;
        this.o = qh3Var;
        this.D = um1Var;
        this.A = windowAndroid;
        this.f70J = wp2Var;
        Profile.d();
        ag2 ag2Var = new ag2(getContext(), this);
        this.i = ag2Var;
        WindowAndroid windowAndroid2 = this.A;
        cg2 cg2Var = ag2Var.c;
        cg2Var.i = q2Var;
        q2Var.f(cg2Var);
        if (cg2Var.i.h()) {
            cg2Var.G0();
        }
        ag2Var.d = windowAndroid2;
        if (!DeviceFormFactor.a(activity)) {
            this.B = getResources().getDimensionPixelSize(org.chromium.chrome.R$dimen.ntp_search_box_bounds_vertical_inset_modern);
        }
        getCardData();
        final int i = 1;
        org.chromium.chrome.browser.logo.a aVar = new org.chromium.chrome.browser.logo.a(this.f, this.p.b(new lj1(2, this)), (LogoView) findViewById(R$id.search_provider_logo), !il0.b(), this.p.b(new ko0(18, this)), this.p.c(new zc0(18, this)), true, null);
        this.h = aVar;
        aVar.b();
        final int i2 = 0;
        j(z, false);
        RecyclerView recyclerView = (RecyclerView) this.k.findViewById(R$id.card_recycleview);
        this.G = recyclerView;
        recyclerView.setHasFixedSize(true);
        RecyclerView recyclerView2 = this.G;
        Context context = this.f;
        recyclerView2.setLayoutManager(new GridLayoutManager(context, 4));
        g gVar = new g(context, this.E);
        this.F = gVar;
        this.G.setAdapter(gVar);
        getCardImg();
        this.F.j = new i(this);
        b(this.n.getResources().getConfiguration());
        findViewById(R$id.search_box).setBackgroundTintList(ColorStateList.valueOf(yp.c(getContext(), bq.d.a() ? org.chromium.chrome.R$dimen.default_elevation_4 : org.chromium.chrome.R$dimen.toolbar_text_box_elevation)));
        TraceEvent.q("NewTabPageLayout.initializeSearchBoxTextView()", null);
        this.i.setSearchBoxClickListener(new View.OnClickListener(this) { // from class: mm1
            public final /* synthetic */ NewTabPageLayout f;

            {
                this.f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i;
                NewTabPageLayout newTabPageLayout = this.f;
                switch (i3) {
                    case 0:
                        ((h.b) newTabPageLayout.m).b(null, true);
                        break;
                    default:
                        ((h.b) newTabPageLayout.m).b(null, false);
                        break;
                }
            }
        });
        this.i.a.p(dg2.m, new rm1(this));
        TraceEvent.C("NewTabPageLayout.initializeSearchBoxTextView()");
        TraceEvent.q("NewTabPageLayout.initializeVoiceSearchButton()", null);
        this.i.addVoiceSearchButtonClickListener(new View.OnClickListener(this) { // from class: mm1
            public final /* synthetic */ NewTabPageLayout f;

            {
                this.f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i2;
                NewTabPageLayout newTabPageLayout = this.f;
                switch (i3) {
                    case 0:
                        ((h.b) newTabPageLayout.m).b(null, true);
                        break;
                    default:
                        ((h.b) newTabPageLayout.m).b(null, false);
                        break;
                }
            }
        });
        l();
        TraceEvent.C("NewTabPageLayout.initializeVoiceSearchButton()");
        TraceEvent.q("NewTabPageLayout.initializeLensButton()", null);
        this.i.addLensButtonClickListener(new by1(14, this));
        l();
        TraceEvent.C("NewTabPageLayout.initializeLensButton()");
        TraceEvent.q("NewTabPageLayout.initializeLayoutChangeListener()", null);
        addOnLayoutChangeListener(new nm1(i2, this));
        TraceEvent.C("NewTabPageLayout.initializeLayoutChangeListener()");
        bVar.a.add(new x80() { // from class: lm1
            @Override // defpackage.x80
            public final void onDestroy() {
                NewTabPageLayout newTabPageLayout = this.a;
                PopupWindow popupWindow = newTabPageLayout.H;
                if (popupWindow != null && popupWindow.isShowing()) {
                    newTabPageLayout.H.dismiss();
                }
                ll llVar = newTabPageLayout.p;
                if (llVar != null) {
                    llVar.a();
                    newTabPageLayout.p = null;
                }
                a aVar2 = newTabPageLayout.h;
                if (aVar2 != null) {
                    aVar2.a();
                    newTabPageLayout.h = null;
                }
                newTabPageLayout.i.c.onDestroy();
            }
        });
        this.v = true;
        TraceEvent.C("NewTabPageLayout.initialize()");
    }

    public final boolean d() {
        return !((org.chromium.chrome.browser.feed.e) this.C).g(0) || ((org.chromium.chrome.browser.feed.e) this.C).f() > getSearchBoxView().getTop();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e() {
        /*
            Method dump skipped, instruction units count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ntp.NewTabPageLayout.e():void");
    }

    public final void f(boolean z) {
        getCardImg();
        if (z) {
            int i = R$drawable.icon_dark_public_green_cherk;
            int i2 = R$string.card_add_sucess;
            Context context = this.f;
            Toast toast = new Toast(context);
            View viewInflate = LayoutInflater.from(context).inflate(R$layout.layout_toast_clear_success, (ViewGroup) null, false);
            ImageView imageView = (ImageView) viewInflate.findViewById(R$id.iv_toast);
            TextView textView = (TextView) viewInflate.findViewById(R$id.tv_toast);
            toast.setView(viewInflate);
            if (imageView != null) {
                imageView.setImageResource(i);
            }
            if (textView != null) {
                textView.setText(i2);
            }
            toast.show();
        }
    }

    public final void g() {
        getCardImg();
    }

    public org.chromium.chrome.browser.logo.a getLogoCoordinatorForTesting() {
        return this.h;
    }

    public si1 getMostVisitedTilesCoordinatorForTesting() {
        return null;
    }

    public tl0 getScrollDelegate() {
        return this.C;
    }

    public View getSearchBoxView() {
        return this.i.b;
    }

    public float getToolbarTransitionPercentage() {
        if (!((org.chromium.chrome.browser.feed.e) this.C).h()) {
            return 0.0f;
        }
        if (d()) {
            return 1.0f;
        }
        int top = getSearchBoxView().getTop();
        if (top == 0) {
            return 0.0f;
        }
        int paddingTop = getSearchBoxView().getPaddingTop() + top;
        int iF = ((org.chromium.chrome.browser.feed.e) this.C).f();
        float dimensionPixelSize = getResources().getDimensionPixelSize(org.chromium.chrome.R$dimen.ntp_search_box_transition_length);
        return a8.o((((iF - paddingTop) + getResources().getDimensionPixelSize(org.chromium.chrome.R$dimen.tab_strip_height)) + dimensionPixelSize) / dimensionPixelSize, 0.0f, 1.0f);
    }

    public float getUrlFocusChangeAnimationPercent() {
        return this.w;
    }

    public final void h() {
        if (this.r && this.q) {
            h hVar = h.this;
            if (!hVar.D) {
                oj2.b0((System.nanoTime() - hVar.A) / 1000000, "Tab.NewTabOnload");
                hVar.C = true;
                oj2.U(0, 2, "Android.NTP.Impression");
                if (!hVar.e.isHidden()) {
                    h.s(hVar);
                }
            }
            org.chromium.chrome.browser.logo.b bVar = this.h.a;
            bVar.d(bVar.o, false, true);
        }
    }

    public final void i() {
        if (this.x || this.y) {
            return;
        }
        float f = this.s ? this.w : 0.0f;
        int paddingTop = getPaddingTop() + ((org.chromium.chrome.browser.feed.e) this.C).f();
        setTranslationY((paddingTop - Math.max(paddingTop, (getSearchBoxView().getBottom() - getSearchBoxView().getPaddingBottom()) - this.B)) * f);
    }

    public final void j(boolean z, boolean z2) {
        if (z == this.s && z2 == this.t && this.v) {
            return;
        }
        this.s = z;
        this.t = z2;
        this.i.a.l(dg2.c, false);
        i();
        this.z = true;
    }

    public final void k(View view, int i) {
        if (z7.M(this.n)) {
            return;
        }
        Context context = this.f;
        this.H = new PopupWindow(context, (AttributeSet) null, 0, 0);
        View viewInflate = LayoutInflater.from(getContext()).inflate(R$layout.card_popup, (ViewGroup) null);
        viewInflate.measure(0, 0);
        this.H.setContentView(viewInflate);
        this.H.setWidth(-2);
        this.H.setHeight(-2);
        view.getLocationOnScreen(new int[2]);
        this.H.setOutsideTouchable(true);
        int measuredWidth = ((view.getMeasuredWidth() + ((int) context.getResources().getDimension(org.chromium.chrome.browser.settings.R$dimen.item_img_size))) / 2) + ((int) context.getResources().getDimension(org.chromium.chrome.browser.settings.R$dimen.item_marg_size));
        TextView textView = (TextView) view.findViewById(R$id.tv_card);
        this.H.showAsDropDown(view, measuredWidth, -(textView.getMeasuredHeight() + viewInflate.getMeasuredHeight()), 3);
        viewInflate.findViewById(R$id.edit).setOnClickListener(new b(i));
        viewInflate.findViewById(R$id.del).setOnClickListener(new c(i));
        this.H.setOnDismissListener(new pm1(0, this));
    }

    public final void l() {
        ag2 ag2Var = this.i;
        so3 so3Var = h.this.z;
        ag2Var.a.l(dg2.d, so3Var != null && so3Var.a());
        ag2 ag2Var2 = this.i;
        DeviceFormFactor.b(ag2Var2.d);
        ag2Var2.c.getClass();
        s61.b.a.getClass();
        this.i.a.l(dg2.h, false);
    }

    public final void m() {
        h.d dVar;
        if (this.x || this.y) {
            return;
        }
        h hVar = h.this;
        boolean z = false;
        if (!hVar.D && hVar.y != null) {
            Tab tab = hVar.f.get();
            jk1 jk1VarU = tab != null ? tab.u() : null;
            if ((jk1VarU instanceof h ? (h) jk1VarU : null) == hVar) {
                z = true;
            }
        }
        if (z && (dVar = this.l) != null) {
            ((Callback) ((c01) dVar).f).onResult(Float.valueOf(getToolbarTransitionPercentage()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.r) {
            this.r = true;
            h();
            um1 um1Var = this.D;
            if (um1Var.b.get().longValue() == 0) {
                long jElapsedRealtime = SystemClock.elapsedRealtime() - org.chromium.chrome.browser.b.l(um1Var.d.get());
                if (um1Var.c) {
                    oj2.Y(jElapsedRealtime, "NewTabPage.SearchAvailableLoadTime2.WarmStart");
                } else {
                    oj2.Y(jElapsedRealtime, "NewTabPage.SearchAvailableLoadTime2.ColdStart");
                }
            }
            TraceEvent.R("NewTabPageSearchAvailable)");
        }
        b(this.n.getResources().getConfiguration());
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        RecyclerView recyclerView;
        ql0 ql0Var = this.K;
        if (ql0Var != null && (recyclerView = this.G) != null) {
            recyclerView.removeCallbacks(ql0Var);
        }
        super.onConfigurationChanged(configuration);
        b(configuration);
        PopupWindow popupWindow = this.H;
        if (popupWindow != null && popupWindow.isShowing() && this.G != null) {
            this.H.dismiss();
            ql0 ql0Var2 = new ql0(16, this);
            this.K = ql0Var2;
            this.G.post(ql0Var2);
        }
        g gVar = this.F;
        if (gVar != null) {
            gVar.o();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        RecyclerView recyclerView;
        ql0 ql0Var = this.K;
        if (ql0Var != null && (recyclerView = this.G) != null) {
            recyclerView.removeCallbacks(ql0Var);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.g = findViewById(R$id.ntp_middle_spacer);
        new ym1((ViewStub) findViewById(R$id.video_iph_stub), Profile.d());
        int iIndexOfChild = indexOfChild(this.g) + 1;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R$layout.card_layout, (ViewGroup) this, false);
        this.k = viewGroup;
        viewGroup.setVisibility(0);
        addView(this.k, iIndexOfChild);
        if (getVisibility() != 0) {
            setVisibility(0);
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        View searchBoxView = getSearchBoxView();
        if (this.k.getVisibility() != 8) {
            sk skVar = bq.a;
            boolean z = N.M09VlOh_("ShowScrollableMVTOnNTPAndroid") && !DeviceFormFactor.a(this.f);
            int i3 = this.e;
            if (z) {
                int measuredWidth = getMeasuredWidth() - i3;
                searchBoxView.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(searchBoxView.getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                org.chromium.chrome.browser.logo.a aVar = this.h;
                aVar.c.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(aVar.c.getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                return;
            }
            int measuredWidth2 = this.k.getMeasuredWidth() - i3;
            searchBoxView.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(searchBoxView.getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            org.chromium.chrome.browser.logo.a aVar2 = this.h;
            aVar2.c.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(aVar2.c.getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.o.c();
        if (i == 0) {
            l();
            if (getToolbarTransitionPercentage() <= 0.0f) {
                x63 x63Var = n3.F;
                pk3 pk3Var = new pk3(this.n, new Handler());
                if (x63Var.a == 4) {
                    Resources resources = this.n.getResources();
                    int i2 = R$string.video_tutorials_iph_tap_here_to_start;
                    pk3Var.a(a(resources, i2, i2, this.i.b, false).a());
                    x63Var.a = 0;
                }
                if (x63Var.a == 5) {
                    Resources resources2 = this.n.getResources();
                    int i3 = R$string.video_tutorials_iph_tap_voice_icon_to_start;
                    pk3Var.a(a(resources2, i3, i3, this.i.b.findViewById(R$id.voice_search_button), true).a());
                    x63Var.a = 0;
                }
            }
            g gVar = this.F;
            if (gVar != null) {
                gVar.o();
            }
        }
    }

    public void setIsViewMoving(boolean z) {
        this.y = z;
    }

    public void setSearchBoxAlpha(float f) {
        this.i.a.m(dg2.a, f);
    }

    public void setSearchBoxBackground(Drawable drawable) {
        this.i.a.p(dg2.b, drawable);
    }

    public void setSearchBoxScrollListener(h.d dVar) {
        this.l = dVar;
        if (dVar != null) {
            m();
        }
    }

    public void setSearchProviderBottomMargin(int i) {
        this.h.b.n(org.chromium.chrome.browser.logo.d.c, i);
    }

    public void setSearchProviderLogoAlpha(float f) {
        this.h.b.m(org.chromium.chrome.browser.logo.d.a, f);
    }

    public void setSearchProviderTopMargin(int i) {
        this.h.b.n(org.chromium.chrome.browser.logo.d.b, i);
    }

    public void setUrlFocusAnimationsDisabled(boolean z) {
        if (z == this.x) {
            return;
        }
        this.x = z;
        if (z) {
            return;
        }
        i();
    }

    public void setUrlFocusChangeAnimationPercent(float f) {
        this.w = f;
        i();
    }
}
