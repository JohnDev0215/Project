package androidx.appcompat.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.k;
import i0.b;

/* JADX INFO: compiled from: MenuItemImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements b0.b {
    private View A;
    private i0.b B;
    private MenuItem.OnActionExpandListener C;
    private ContextMenu.ContextMenuInfo E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f692a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f693b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f694c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f695d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private CharSequence f696e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CharSequence f697f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Intent f698g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private char f699h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private char f701j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Drawable f703l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    e f705n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private m f706o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private Runnable f707p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private MenuItem.OnMenuItemClickListener f708q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private CharSequence f709r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private CharSequence f710s;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private int f717z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f700i = 4096;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f702k = 4096;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f704m = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private ColorStateList f711t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private PorterDuff.Mode f712u = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f713v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f714w = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private boolean f715x = false;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f716y = 16;
    private boolean D = false;

    /* JADX INFO: compiled from: MenuItemImpl.java */
    class a implements b.InterfaceC0084b {
        a() {
        }

        @Override // i0.b.InterfaceC0084b
        public void onActionProviderVisibilityChanged(boolean z6) {
            g gVar = g.this;
            gVar.f705n.J(gVar);
        }
    }

    g(e eVar, int i7, int i8, int i9, int i10, CharSequence charSequence, int i11) {
        this.f705n = eVar;
        this.f692a = i8;
        this.f693b = i7;
        this.f694c = i9;
        this.f695d = i10;
        this.f696e = charSequence;
        this.f717z = i11;
    }

    private static void d(StringBuilder sb, int i7, int i8, String str) {
        if ((i7 & i8) == i8) {
            sb.append(str);
        }
    }

    private Drawable e(Drawable drawable) {
        if (drawable != null && this.f715x && (this.f713v || this.f714w)) {
            drawable = a0.a.l(drawable).mutate();
            if (this.f713v) {
                a0.a.i(drawable, this.f711t);
            }
            if (this.f714w) {
                a0.a.j(drawable, this.f712u);
            }
            this.f715x = false;
        }
        return drawable;
    }

    boolean A() {
        return this.f705n.H() && g() != 0;
    }

    public boolean B() {
        return (this.f717z & 4) == 4;
    }

    @Override // b0.b
    public b0.b a(i0.b bVar) {
        i0.b bVar2 = this.B;
        if (bVar2 != null) {
            bVar2.h();
        }
        this.A = null;
        this.B = bVar;
        this.f705n.K(true);
        i0.b bVar3 = this.B;
        if (bVar3 != null) {
            bVar3.j(new a());
        }
        return this;
    }

    @Override // b0.b
    public i0.b b() {
        return this.B;
    }

    public void c() {
        this.f705n.I(this);
    }

    @Override // b0.b, android.view.MenuItem
    public boolean collapseActionView() {
        if ((this.f717z & 8) == 0) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f705n.f(this);
        }
        return false;
    }

    @Override // b0.b, android.view.MenuItem
    public boolean expandActionView() {
        if (!j()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f705n.k(this);
        }
        return false;
    }

    public int f() {
        return this.f695d;
    }

    char g() {
        return this.f705n.G() ? this.f701j : this.f699h;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // b0.b, android.view.MenuItem
    public View getActionView() {
        View view = this.A;
        if (view != null) {
            return view;
        }
        i0.b bVar = this.B;
        if (bVar == null) {
            return null;
        }
        View viewD = bVar.d(this);
        this.A = viewD;
        return viewD;
    }

    @Override // b0.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f702k;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f701j;
    }

    @Override // b0.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f709r;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f693b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable drawable = this.f703l;
        if (drawable != null) {
            return e(drawable);
        }
        if (this.f704m == 0) {
            return null;
        }
        Drawable drawableB = f.a.b(this.f705n.u(), this.f704m);
        this.f704m = 0;
        this.f703l = drawableB;
        return e(drawableB);
    }

    @Override // b0.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f711t;
    }

    @Override // b0.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f712u;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f698g;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.f692a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    @Override // b0.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f700i;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f699h;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f694c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.f706o;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.f696e;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f697f;
        return charSequence != null ? charSequence : this.f696e;
    }

    @Override // b0.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f710s;
    }

    String h() {
        char cG = g();
        if (cG == 0) {
            return "";
        }
        Resources resources = this.f705n.u().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.f705n.u()).hasPermanentMenuKey()) {
            sb.append(resources.getString(e.h.abc_prepend_shortcut_label));
        }
        int i7 = this.f705n.G() ? this.f702k : this.f700i;
        d(sb, i7, 65536, resources.getString(e.h.abc_menu_meta_shortcut_label));
        d(sb, i7, 4096, resources.getString(e.h.abc_menu_ctrl_shortcut_label));
        d(sb, i7, 2, resources.getString(e.h.abc_menu_alt_shortcut_label));
        d(sb, i7, 1, resources.getString(e.h.abc_menu_shift_shortcut_label));
        d(sb, i7, 4, resources.getString(e.h.abc_menu_sym_shortcut_label));
        d(sb, i7, 8, resources.getString(e.h.abc_menu_function_shortcut_label));
        if (cG == '\b') {
            sb.append(resources.getString(e.h.abc_menu_delete_shortcut_label));
        } else if (cG == '\n') {
            sb.append(resources.getString(e.h.abc_menu_enter_shortcut_label));
        } else if (cG != ' ') {
            sb.append(cG);
        } else {
            sb.append(resources.getString(e.h.abc_menu_space_shortcut_label));
        }
        return sb.toString();
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.f706o != null;
    }

    CharSequence i(k.a aVar) {
        return (aVar == null || !aVar.d()) ? getTitle() : getTitleCondensed();
    }

    @Override // b0.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.D;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f716y & 1) == 1;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f716y & 2) == 2;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f716y & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        i0.b bVar = this.B;
        return (bVar == null || !bVar.g()) ? (this.f716y & 8) == 0 : (this.f716y & 8) == 0 && this.B.b();
    }

    public boolean j() {
        i0.b bVar;
        if ((this.f717z & 8) == 0) {
            return false;
        }
        if (this.A == null && (bVar = this.B) != null) {
            this.A = bVar.d(this);
        }
        return this.A != null;
    }

    public boolean k() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.f708q;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        e eVar = this.f705n;
        if (eVar.h(eVar, this)) {
            return true;
        }
        Runnable runnable = this.f707p;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.f698g != null) {
            try {
                this.f705n.u().startActivity(this.f698g);
                return true;
            } catch (ActivityNotFoundException e7) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e7);
            }
        }
        i0.b bVar = this.B;
        return bVar != null && bVar.e();
    }

    public boolean l() {
        return (this.f716y & 32) == 32;
    }

    public boolean m() {
        return (this.f716y & 4) != 0;
    }

    public boolean n() {
        return (this.f717z & 1) == 1;
    }

    public boolean o() {
        return (this.f717z & 2) == 2;
    }

    @Override // b0.b, android.view.MenuItem
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public b0.b setActionView(int i7) {
        Context contextU = this.f705n.u();
        setActionView(LayoutInflater.from(contextU).inflate(i7, (ViewGroup) new LinearLayout(contextU), false));
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public b0.b setActionView(View view) {
        int i7;
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && (i7 = this.f692a) > 0) {
            view.setId(i7);
        }
        this.f705n.I(this);
        return this;
    }

    public void r(boolean z6) {
        this.D = z6;
        this.f705n.K(false);
    }

    void s(boolean z6) {
        int i7 = this.f716y;
        int i8 = (z6 ? 2 : 0) | (i7 & (-3));
        this.f716y = i8;
        if (i7 != i8) {
            this.f705n.K(false);
        }
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c7) {
        if (this.f701j == c7) {
            return this;
        }
        this.f701j = Character.toLowerCase(c7);
        this.f705n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z6) {
        int i7 = this.f716y;
        int i8 = (z6 ? 1 : 0) | (i7 & (-2));
        this.f716y = i8;
        if (i7 != i8) {
            this.f705n.K(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z6) {
        if ((this.f716y & 4) != 0) {
            this.f705n.T(this);
        } else {
            s(z6);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z6) {
        if (z6) {
            this.f716y |= 16;
        } else {
            this.f716y &= -17;
        }
        this.f705n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f704m = 0;
        this.f703l = drawable;
        this.f715x = true;
        this.f705n.K(false);
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f711t = colorStateList;
        this.f713v = true;
        this.f715x = true;
        this.f705n.K(false);
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f712u = mode;
        this.f714w = true;
        this.f715x = true;
        this.f705n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f698g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c7) {
        if (this.f699h == c7) {
            return this;
        }
        this.f699h = c7;
        this.f705n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.C = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f708q = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c7, char c8) {
        this.f699h = c7;
        this.f701j = Character.toLowerCase(c8);
        this.f705n.K(false);
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public void setShowAsAction(int i7) {
        int i8 = i7 & 3;
        if (i8 != 0 && i8 != 1 && i8 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.f717z = i7;
        this.f705n.I(this);
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f696e = charSequence;
        this.f705n.K(false);
        m mVar = this.f706o;
        if (mVar != null) {
            mVar.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f697f = charSequence;
        this.f705n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z6) {
        if (y(z6)) {
            this.f705n.J(this);
        }
        return this;
    }

    public void t(boolean z6) {
        this.f716y = (z6 ? 4 : 0) | (this.f716y & (-5));
    }

    public String toString() {
        CharSequence charSequence = this.f696e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public void u(boolean z6) {
        if (z6) {
            this.f716y |= 32;
        } else {
            this.f716y &= -33;
        }
    }

    void v(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.E = contextMenuInfo;
    }

    @Override // b0.b, android.view.MenuItem
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public b0.b setShowAsActionFlags(int i7) {
        setShowAsAction(i7);
        return this;
    }

    public void x(m mVar) {
        this.f706o = mVar;
        mVar.setHeaderTitle(getTitle());
    }

    boolean y(boolean z6) {
        int i7 = this.f716y;
        int i8 = (z6 ? 0 : 8) | (i7 & (-9));
        this.f716y = i8;
        return i7 != i8;
    }

    public boolean z() {
        return this.f705n.A();
    }

    @Override // android.view.MenuItem
    public b0.b setContentDescription(CharSequence charSequence) {
        this.f709r = charSequence;
        this.f705n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public b0.b setTooltipText(CharSequence charSequence) {
        this.f710s = charSequence;
        this.f705n.K(false);
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c7, int i7) {
        if (this.f701j == c7 && this.f702k == i7) {
            return this;
        }
        this.f701j = Character.toLowerCase(c7);
        this.f702k = KeyEvent.normalizeMetaState(i7);
        this.f705n.K(false);
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c7, int i7) {
        if (this.f699h == c7 && this.f700i == i7) {
            return this;
        }
        this.f699h = c7;
        this.f700i = KeyEvent.normalizeMetaState(i7);
        this.f705n.K(false);
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public MenuItem setShortcut(char c7, char c8, int i7, int i8) {
        this.f699h = c7;
        this.f700i = KeyEvent.normalizeMetaState(i7);
        this.f701j = Character.toLowerCase(c8);
        this.f702k = KeyEvent.normalizeMetaState(i8);
        this.f705n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i7) {
        this.f703l = null;
        this.f704m = i7;
        this.f715x = true;
        this.f705n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i7) {
        return setTitle(this.f705n.u().getString(i7));
    }
}
