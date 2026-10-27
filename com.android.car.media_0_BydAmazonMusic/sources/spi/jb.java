package spi;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.car.utils.Logs;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$drawable;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$string;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.recyclerview.a;
import com.android.car.ui.rotary.FocusParkingView;
import com.android.car.ui.toolbar.SearchView;
import com.android.car.ui.toolbar.TabLayout;
import com.android.car.ui.toolbar.b;
import com.android.car.ui.widget.CarUiTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;
import spi.u1;

/* JADX INFO: compiled from: ToolbarControllerImplByd.java */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(30)
public final class jb implements com.android.car.ui.toolbar.j {
    public final Set<Supplier<Boolean>> A;
    public final Set<com.android.car.ui.toolbar.i> B;
    public final com.android.car.ui.toolbar.b C;
    public final boolean D;
    public boolean E;
    public eb F;
    public boolean G;
    public s7 H;
    public v9 I;

    @NonNull
    public List<com.android.car.ui.toolbar.b> J;
    public List<com.android.car.ui.toolbar.b> K;
    public final List<l1> L;
    public final CarUiListItemAdapter M;
    public final List<f7> N;
    public final List<com.android.car.ui.toolbar.a> O;
    public View[] P;
    public AlertDialog Q;
    public final boolean R;
    public final boolean S;
    public final boolean T;
    public com.android.car.ui.toolbar.e U;
    public final hb V;
    public final ConstraintLayout W;

    @Nullable
    public View a;
    public int b = R$drawable.car_ui_toolbar_background;
    public ImageView c;
    public ImageView d;
    public ViewGroup e;
    public ViewGroup f;
    public CarUiTextView g;

    @Nullable
    public View h;
    public CarUiTextView i;

    @NonNull
    public u1 j;
    public ImageView k;
    public ViewGroup l;
    public TabLayout m;
    public ViewGroup n;
    public final FrameLayout o;
    public SearchView p;
    public int q;
    public String r;
    public Drawable s;
    public String t;
    public final Context u;
    public final Set<Consumer<String>> v;
    public final Set<Runnable> w;
    public final Set<db> x;
    public final Set<cb> y;
    public final Set<bb> z;

    /* JADX INFO: compiled from: ToolbarControllerImplByd.java */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[s7.values().length];
            b = iArr;
            try {
                iArr[s7.CLOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[s7.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[ab.values().length];
            a = iArr2;
            try {
                iArr2[ab.BACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ab.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ab.CLOSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ab.DISABLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: compiled from: ToolbarControllerImplByd.java */
    public enum b {
        LEFT,
        /* JADX INFO: Fake field, exist only in values array */
        RIGHT_NO_TABS,
        /* JADX INFO: Fake field, exist only in values array */
        RIGHT
    }

    public jb(View view) {
        new u1.b("").a();
        this.j = new u1.b("").a();
        this.q = R$drawable.byd_car_toolbar_search_bg_selector;
        this.v = new HashSet();
        this.w = new HashSet();
        this.x = new HashSet();
        this.y = new HashSet();
        this.z = new HashSet();
        this.A = new HashSet();
        this.B = new HashSet();
        this.E = false;
        this.F = eb.HOME;
        this.G = false;
        this.H = s7.DISABLED;
        this.I = v9.DISABLED;
        this.J = Collections.emptyList();
        this.K = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.L = arrayList;
        this.N = new ArrayList();
        this.O = new ArrayList();
        this.V = new hb(this, 0);
        b bVar = b.LEFT;
        this.W = (ConstraintLayout) ((ViewGroup) ((ViewGroup) view).getChildAt(0)).getChildAt(0);
        Context context = view.getContext();
        this.u = context;
        b.C0013b c0013b = new b.C0013b(context);
        c0013b.b(R$drawable.car_ui_icon_overflow_menu);
        c0013b.c(R$string.car_ui_toolbar_menu_item_overflow_title);
        c0013b.i = new q6(this);
        this.C = c0013b.a();
        this.D = context.getResources().getBoolean(R$bool.car_ui_toolbar_tabs_on_second_row);
        this.R = context.getResources().getBoolean(R$bool.car_ui_toolbar_nav_icon_reserve_space);
        this.S = context.getResources().getBoolean(R$bool.car_ui_toolbar_logo_fills_nav_icon_space);
        this.T = context.getResources().getBoolean(R$bool.car_ui_toolbar_show_logo);
        this.r = context.getString(R$string.car_ui_toolbar_default_search_hint);
        this.a = b2.d(view, R$id.car_ui_toolbar_background);
        this.h = b2.d(view, R$id.car_ui_toolbar_line);
        this.m = (TabLayout) b2.n(view, R$id.car_ui_toolbar_tabs);
        this.c = (ImageView) b2.n(view, R$id.car_ui_toolbar_nav_icon);
        this.d = (ImageView) b2.n(view, R$id.car_ui_toolbar_logo);
        this.e = (ViewGroup) b2.n(view, R$id.car_ui_toolbar_nav_icon_container);
        this.n = (ViewGroup) b2.n(view, R$id.car_ui_toolbar_menu_items_container);
        this.f = (ViewGroup) b2.n(view, R$id.car_ui_toolbar_title_container);
        this.i = (CarUiTextView) b2.n(view, R$id.car_ui_toolbar_subtitle);
        this.g = (CarUiTextView) b2.n(view, R$id.car_ui_toolbar_title);
        this.l = (ViewGroup) b2.n(view, R$id.car_ui_toolbar_title_logo_container);
        this.k = (ImageView) b2.n(view, R$id.car_ui_toolbar_title_logo);
        this.o = (FrameLayout) b2.n(view, R$id.car_ui_toolbar_search_view_container);
        new i9((ProgressBar) b2.n(view, R$id.car_ui_toolbar_progress_bar));
        com.android.car.ui.toolbar.e.a();
        setBackgroundShown(true);
        this.M = new CarUiListItemAdapter(arrayList);
        c();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void a() {
        this.L.clear();
        for (com.android.car.ui.toolbar.b bVar : this.K) {
            if (bVar.p) {
                this.L.add(b(bVar));
            }
        }
        i iVar = new i(this.u);
        CarUiListItemAdapter carUiListItemAdapter = this.M;
        View viewInflate = LayoutInflater.from(iVar.b).inflate(R$layout.car_ui_alert_dialog_list, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) b2.n(viewInflate, R$id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(iVar.b));
        recyclerView.setAdapter(carUiListItemAdapter);
        recyclerView.setFocusable(false);
        iVar.a.setView(viewInflate);
        iVar.e = true;
        View viewInflate2 = LayoutInflater.from(iVar.b).inflate(R$layout.car_ui_alert_dialog_title_with_subtitle, (ViewGroup) null);
        TextView textView = (TextView) b2.n(viewInflate2, R$id.car_ui_alert_title);
        TextView textView2 = (TextView) b2.n(viewInflate2, R$id.car_ui_alert_subtitle);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        ImageView imageView = (ImageView) b2.n(viewInflate2, R$id.car_ui_alert_icon);
        textView.setText((CharSequence) null);
        textView.setVisibility(TextUtils.isEmpty(null) ? 8 : 0);
        textView2.setText((CharSequence) null);
        textView2.setVisibility(TextUtils.isEmpty(null) ? 8 : 0);
        imageView.setImageDrawable(null);
        imageView.setVisibility(8);
        if (!TextUtils.isEmpty(null) || !TextUtils.isEmpty(null)) {
            iVar.a.setCustomTitle(viewInflate2);
        }
        if (!iVar.d && !iVar.e) {
            throw new RuntimeException("The dialog must have at least one button to disable the dismiss button");
        }
        if (iVar.b.getResources().getBoolean(R$bool.car_ui_alert_dialog_force_dismiss_button) && iVar.d) {
            iVar.a.setNegativeButton(iVar.b.getString(R$string.car_ui_alert_dialog_default_button), new DialogInterface.OnClickListener() { // from class: spi.f
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        }
        AlertDialog alertDialogCreate = iVar.a.create();
        iVar.c = (ViewGroup) alertDialogCreate.getWindow().getDecorView().getRootView();
        iVar.c.addView(new FocusParkingView(iVar.b));
        iVar.c.setOnApplyWindowInsetsListener(iVar.f);
        iVar.a.setOnDismissListener(iVar.g);
        this.Q = alertDialogCreate;
    }

    public final com.android.car.ui.recyclerview.a b(com.android.car.ui.toolbar.b bVar) {
        com.android.car.ui.recyclerview.a aVar = bVar.a ? new com.android.car.ui.recyclerview.a(a.EnumC0011a.SWITCH) : new com.android.car.ui.recyclerview.a(a.EnumC0011a.NONE);
        aVar.b(bVar.j);
        aVar.g = bVar.q;
        aVar.a(bVar.o);
        aVar.f = bVar.n;
        aVar.c(bVar.i);
        aVar.setOnItemClickedListener(new b6(this, bVar));
        return aVar;
    }

    /* JADX WARN: Type inference failed for: r13v1, types: [java.util.ArrayList, java.util.List<com.android.car.ui.toolbar.b>] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.util.ArrayList, java.util.List<spi.f7>] */
    public final void c() {
        eb ebVar;
        s7 s7Var = this.H;
        boolean z = this.G;
        if (z) {
            if (this.F == eb.HOME) {
                s7Var = s7.DISABLED;
            } else if (s7Var == s7.DISABLED) {
                s7Var = s7.BACK;
            }
        }
        v9 v9Var = this.I;
        if (z) {
            eb ebVar2 = this.F;
            v9Var = ebVar2 == eb.SEARCH ? v9.SEARCH : ebVar2 == eb.EDIT ? v9.EDIT : v9.DISABLED;
        }
        boolean z2 = this.E;
        boolean z3 = false;
        if (z && ((ebVar = this.F) == eb.SEARCH || ebVar == eb.EDIT)) {
            z2 = false;
        }
        boolean z4 = !this.m.b.isEmpty();
        if (this.G && this.F != eb.HOME) {
            eb ebVar3 = eb.SUBPAGE;
            z4 = false;
        }
        boolean z5 = v9Var != v9.DISABLED;
        if (this.p == null && z5) {
            SearchView searchView = new SearchView(this.u);
            searchView.setHint(this.r);
            searchView.setIcon(this.s);
            searchView.setSearchQuery(this.t);
            Set<db> set = this.x;
            searchView.k = this.v;
            searchView.m = set;
            Set<cb> set2 = this.y;
            searchView.l = this.w;
            searchView.n = set2;
            searchView.setVisibility(8);
            this.o.addView(searchView, new FrameLayout.LayoutParams(-1, -1));
            searchView.setSearchConfig(this.U);
            searchView.setmSearchTextBack(this.q);
            this.p = searchView;
        }
        for (f7 f7Var : this.N) {
            boolean z6 = v9Var == v9.SEARCH;
            if (z6 != f7Var.b) {
                f7Var.b = z6;
                if (f7Var.c.c) {
                    f7Var.c();
                }
            }
        }
        int i = 4;
        k8 k8Var = new k8(this, 4);
        int i2 = a.b[s7Var.ordinal()];
        if (i2 == 1) {
            this.c.setImageResource(R$drawable.car_ui_icon_close);
        } else if (i2 != 2) {
            this.c.setImageResource(R$drawable.byd_car_toolbar_back_ic);
        } else {
            this.c.setImageResource(R$drawable.car_ui_icon_down);
        }
        ImageView imageView = this.c;
        s7 s7Var2 = s7.DISABLED;
        imageView.setVisibility(s7Var != s7Var2 ? 0 : 8);
        this.d.setVisibility((z2 && s7Var == s7Var2 && this.S) ? 0 : 4);
        this.l.setVisibility((!z2 || (s7Var == s7Var2 && this.S)) ? 8 : 0);
        ViewGroup viewGroup = this.e;
        if (s7Var != s7Var2 || (z2 && this.S)) {
            i = 0;
        } else if (!this.R) {
            i = 8;
        }
        viewGroup.setVisibility(i);
        ViewGroup viewGroup2 = this.e;
        if (s7Var == s7Var2) {
            k8Var = null;
        }
        viewGroup2.setOnClickListener(k8Var);
        this.e.setClickable(s7Var != s7Var2);
        this.e.setContentDescription(s7Var != s7Var2 ? this.u.getString(R$string.car_ui_toolbar_nav_icon_content_description) : null);
        this.f.setVisibility(((!z4 || this.D) && !z5) ? 0 : 8);
        this.i.setVisibility(TextUtils.isEmpty(this.j.b()) ? 8 : 0);
        this.m.setVisibility((z4 && (this.I == v9.DISABLED || this.D)) ? 0 : 8);
        SearchView searchView2 = this.p;
        if (searchView2 != null) {
            if (z5) {
                searchView2.setPlainText(v9Var == v9.EDIT);
                this.p.setVisibility(0);
            } else {
                searchView2.setVisibility(8);
                this.p.d.getText().clear();
            }
        }
        boolean z7 = !z5;
        this.n.setVisibility(z7 ? 0 : 8);
        com.android.car.ui.toolbar.b bVar = this.C;
        if (z7) {
            Iterator it = this.K.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                if (((com.android.car.ui.toolbar.b) it.next()).p) {
                    i3++;
                }
            }
            if (i3 > 0) {
                z3 = true;
            }
        }
        bVar.e(z3);
    }

    @Override // com.android.car.ui.toolbar.j
    public final s7 getNavButtonMode() {
        return this.H;
    }

    @Override // com.android.car.ui.toolbar.j
    public final com.android.car.ui.toolbar.d getSearchCapabilities() {
        return com.android.car.ui.toolbar.f.e(this.u);
    }

    @Override // com.android.car.ui.toolbar.j
    public final v9 getSearchMode() {
        return this.I;
    }

    @Override // com.android.car.ui.toolbar.j
    public final boolean isStateSet() {
        return this.G;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<spi.bb>] */
    @Override // com.android.car.ui.toolbar.j
    public void registerOnBackListener(bb bbVar) {
        this.z.add(bbVar);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<spi.cb>] */
    @Override // com.android.car.ui.toolbar.j
    public void registerOnSearchCompletedListener(cb cbVar) {
        this.y.add(cbVar);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<spi.db>] */
    @Override // com.android.car.ui.toolbar.j
    public void registerOnSearchListener(db dbVar) {
        this.x.add(dbVar);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<com.android.car.ui.toolbar.i>] */
    @Override // com.android.car.ui.toolbar.j
    public void registerOnTabSelectedListener(com.android.car.ui.toolbar.i iVar) {
        this.B.add(iVar);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<java.lang.Runnable>] */
    @Override // com.android.car.ui.toolbar.j
    public final void registerSearchCompletedListener(Runnable runnable) {
        this.w.add(runnable);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<java.util.function.Consumer<java.lang.String>>] */
    @Override // com.android.car.ui.toolbar.j
    public final void registerSearchListener(Consumer<String> consumer) {
        this.v.add(consumer);
    }

    @Override // com.android.car.ui.toolbar.j
    public final void selectTab(int i) {
        this.m.b(i);
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setBackground(int i) {
        Logs.d("CarUiToolbarController", "setBackground resId：" + i + ",this:" + this);
        this.b = i;
        Logs.d("CarUiToolbarController", "setBackground end resId：" + i + ",this:" + this);
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setBackgroundShown(boolean z) {
        if (this.a == null) {
            return;
        }
        Logs.d("CarUiToolbarController", "setBackgroundShown shown：" + z + ",mBackResId:" + this.b + ",this:" + this);
        if (!z) {
            this.a.setBackground(null);
            return;
        }
        View view = this.a;
        int i = this.b;
        view.setBackground(i != 0 ? ContextCompat.getDrawable(this.u, i) : null);
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setLogo(Drawable drawable) {
        if (this.T) {
            if (drawable != null) {
                this.d.setImageDrawable(drawable);
                this.k.setImageDrawable(drawable);
                this.E = true;
            } else {
                this.E = false;
            }
            c();
        }
    }

    /* JADX WARN: Type inference failed for: r10v3, types: [java.util.ArrayList, java.util.List<spi.f7>] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.ArrayList, java.util.List<spi.f7>] */
    @Override // com.android.car.ui.toolbar.j
    public final void setMenuItems(@Nullable List<com.android.car.ui.toolbar.b> list) {
        if (list == null) {
            list = Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        synchronized (this) {
            if (list.equals(this.J)) {
                return;
            }
            for (com.android.car.ui.toolbar.b bVar : list) {
                if (bVar.l == b.c.NEVER) {
                    arrayList2.add(bVar);
                    bVar.d(this.V);
                } else {
                    arrayList.add(bVar);
                }
            }
            this.J = new ArrayList(list);
            this.K = arrayList2;
            this.N.clear();
            this.n.removeAllViews();
            if (!arrayList2.isEmpty()) {
                arrayList.add(this.C);
                a();
            }
            final View[] viewArr = new View[arrayList.size()];
            this.P = viewArr;
            for (final int i = 0; i < arrayList.size(); i++) {
                f7 f7Var = new f7((com.android.car.ui.toolbar.b) arrayList.get(i), this.n);
                this.N.add(f7Var);
                Consumer consumer = new Consumer() { // from class: spi.ib
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        jb jbVar = this.a;
                        View[] viewArr2 = viewArr;
                        int i2 = i;
                        AtomicInteger atomicInteger2 = atomicInteger;
                        View view = (View) obj;
                        synchronized (jbVar) {
                            if (viewArr2 != jbVar.P) {
                                return;
                            }
                            viewArr2[i2] = view;
                            if (atomicInteger2.addAndGet(1) == viewArr2.length) {
                                for (View view2 : viewArr2) {
                                    jbVar.n.addView(view2);
                                }
                            }
                        }
                    }
                };
                AsyncLayoutInflater asyncLayoutInflater = new AsyncLayoutInflater(f7Var.d.getContext());
                Objects.requireNonNull(f7Var.c);
                asyncLayoutInflater.inflate(R$layout.car_ui_toolbar_menu_item, f7Var.d, new b6(f7Var, consumer));
            }
            c();
        }
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setNavButtonMode(ab abVar) {
        int i = a.a[abVar.ordinal()];
        setNavButtonMode(i != 1 ? i != 2 ? i != 3 ? s7.DISABLED : s7.CLOSE : s7.DOWN : s7.BACK);
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setSearchBack(int i) {
        this.q = i;
        SearchView searchView = this.p;
        if (searchView != null) {
            searchView.setmSearchTextBack(i);
        }
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setSearchConfig(com.android.car.ui.toolbar.e eVar) {
        this.U = eVar;
        SearchView searchView = this.p;
        if (searchView != null) {
            searchView.setSearchConfig(eVar);
        }
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setSearchIcon(Drawable drawable) {
        if (Objects.equals(drawable, this.s)) {
            return;
        }
        this.s = drawable;
        SearchView searchView = this.p;
        if (searchView != null) {
            searchView.setIcon(drawable);
        }
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setSearchMode(v9 v9Var) {
        if (this.G) {
            throw new IllegalStateException("Cannot set search mode when using setState()");
        }
        if (this.I != v9Var) {
            this.I = v9Var;
            c();
        }
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setSearchQuery(String str) {
        Logs.e("ToolbarControllerImpByd", "setSearchQuery " + str);
        SearchView searchView = this.p;
        if (searchView != null) {
            searchView.setSearchQuery(str);
        } else {
            this.t = str;
        }
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setState(eb ebVar) {
        if (this.F == ebVar && this.G) {
            return;
        }
        this.F = ebVar;
        this.G = true;
        c();
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setSubtitle(CharSequence charSequence) {
        u1 u1VarA = new u1.b("").a();
        this.j = u1VarA;
        this.i.setText(u1VarA);
        c();
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setTabs(@Nullable List<com.android.car.ui.toolbar.g> list) {
        setTabs(list, 0);
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setTitle(@StringRes int i) {
        setTitle(i == 0 ? null : this.u.getString(i));
    }

    @Override // com.android.car.ui.toolbar.j
    public final void showLineView(boolean z) {
        View view = this.h;
        if (view != null) {
            view.setVisibility(z ? 0 : 4);
        }
    }

    @Override // com.android.car.ui.toolbar.j
    public final void showOtherView() {
        this.W.setVisibility(0);
    }

    @Override // com.android.car.ui.toolbar.j
    public final void showSignInView() {
        this.W.setVisibility(8);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<com.android.car.ui.toolbar.a>] */
    @Override // com.android.car.ui.toolbar.j
    public final void setTabs(@Nullable List<com.android.car.ui.toolbar.g> list, int i) {
        this.O.clear();
        if (list == null || list.isEmpty()) {
            i = -1;
        } else if (i < 0 || i >= list.size()) {
            throw new IllegalArgumentException(c.d("Tab position is invalid: ", i));
        }
        this.m.c(list, i);
        c();
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setTitle(CharSequence charSequence) {
        u1 u1VarA;
        if (charSequence == null) {
            u1VarA = new u1.b("").a();
        } else {
            u1VarA = new u1.b(charSequence).a();
        }
        this.g.setText(u1VarA);
        c();
    }

    @Override // com.android.car.ui.toolbar.j
    public final void setNavButtonMode(s7 s7Var) {
        if (s7Var != this.H) {
            this.H = s7Var;
            c();
        }
    }
}
