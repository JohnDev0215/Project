package n;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import androidx.appcompat.R$string;
import l0.b;
import n.n;

/* JADX INFO: compiled from: MenuItemImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class i implements f0.b {
    public View A;
    public l0.b B;
    public MenuItem.OnActionExpandListener C;
    public ContextMenu.ContextMenuInfo E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3963a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3964b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3965c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f3966d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f3967e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f3968f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Intent f3969g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public char f3970h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public char f3972j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable f3974l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public g f3976n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public r f3977o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Runnable f3978p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public MenuItem.OnMenuItemClickListener f3979q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f3980r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public CharSequence f3981s;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f3988z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3971i = 4096;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3973k = 4096;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3975m = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ColorStateList f3982t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public PorterDuff.Mode f3983u = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f3984v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f3985w = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f3986x = false;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f3987y = 16;
    public boolean D = false;

    /* JADX INFO: compiled from: MenuItemImpl.java */
    public class a implements b.InterfaceC0033b {
        public a() {
        }

        @Override // l0.b.InterfaceC0033b
        public void onActionProviderVisibilityChanged(boolean z3) {
            i iVar = i.this;
            iVar.f3976n.L(iVar);
        }
    }

    public i(g gVar, int i3, int i4, int i5, int i6, CharSequence charSequence, int i7) {
        this.f3988z = 0;
        this.f3976n = gVar;
        this.f3963a = i4;
        this.f3964b = i3;
        this.f3965c = i5;
        this.f3966d = i6;
        this.f3967e = charSequence;
        this.f3988z = i7;
    }

    public static void d(StringBuilder sb, int i3, int i4, String str) {
        if ((i3 & i4) == i4) {
            sb.append(str);
        }
    }

    public boolean A() {
        return (this.f3988z & 4) == 4;
    }

    @Override // f0.b
    public f0.b a(l0.b bVar) {
        l0.b bVar2 = this.B;
        if (bVar2 != null) {
            bVar2.h();
        }
        this.A = null;
        this.B = bVar;
        this.f3976n.M(true);
        l0.b bVar3 = this.B;
        if (bVar3 != null) {
            bVar3.j(new a());
        }
        return this;
    }

    @Override // f0.b
    public l0.b b() {
        return this.B;
    }

    public void c() {
        this.f3976n.K(this);
    }

    @Override // f0.b, android.view.MenuItem
    public boolean collapseActionView() {
        if ((this.f3988z & 8) == 0) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f3976n.f(this);
        }
        return false;
    }

    public final Drawable e(Drawable drawable) {
        if (drawable != null && this.f3986x && (this.f3984v || this.f3985w)) {
            drawable = e0.a.r(drawable).mutate();
            if (this.f3984v) {
                e0.a.o(drawable, this.f3982t);
            }
            if (this.f3985w) {
                e0.a.p(drawable, this.f3983u);
            }
            this.f3986x = false;
        }
        return drawable;
    }

    @Override // f0.b, android.view.MenuItem
    public boolean expandActionView() {
        if (!j()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f3976n.m(this);
        }
        return false;
    }

    public int f() {
        return this.f3966d;
    }

    public char g() {
        return this.f3976n.I() ? this.f3972j : this.f3970h;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // f0.b, android.view.MenuItem
    public View getActionView() {
        View view = this.A;
        if (view != null) {
            return view;
        }
        l0.b bVar = this.B;
        if (bVar == null) {
            return null;
        }
        View viewD = bVar.d(this);
        this.A = viewD;
        return viewD;
    }

    @Override // f0.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f3973k;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f3972j;
    }

    @Override // f0.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f3980r;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f3964b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable drawable = this.f3974l;
        if (drawable != null) {
            return e(drawable);
        }
        if (this.f3975m == 0) {
            return null;
        }
        Drawable drawableD = j.a.d(this.f3976n.w(), this.f3975m);
        this.f3975m = 0;
        this.f3974l = drawableD;
        return e(drawableD);
    }

    @Override // f0.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f3982t;
    }

    @Override // f0.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f3983u;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f3969g;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.f3963a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    @Override // f0.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f3971i;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f3970h;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f3965c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.f3977o;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.f3967e;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f3968f;
        if (charSequence == null) {
            charSequence = this.f3967e;
        }
        return (Build.VERSION.SDK_INT >= 18 || charSequence == null || (charSequence instanceof String)) ? charSequence : charSequence.toString();
    }

    @Override // f0.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f3981s;
    }

    public String h() {
        char cG = g();
        if (cG == 0) {
            return "";
        }
        Resources resources = this.f3976n.w().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.f3976n.w()).hasPermanentMenuKey()) {
            sb.append(resources.getString(R$string.abc_prepend_shortcut_label));
        }
        int i3 = this.f3976n.I() ? this.f3973k : this.f3971i;
        d(sb, i3, 65536, resources.getString(R$string.abc_menu_meta_shortcut_label));
        d(sb, i3, 4096, resources.getString(R$string.abc_menu_ctrl_shortcut_label));
        d(sb, i3, 2, resources.getString(R$string.abc_menu_alt_shortcut_label));
        d(sb, i3, 1, resources.getString(R$string.abc_menu_shift_shortcut_label));
        d(sb, i3, 4, resources.getString(R$string.abc_menu_sym_shortcut_label));
        d(sb, i3, 8, resources.getString(R$string.abc_menu_function_shortcut_label));
        if (cG == '\b') {
            sb.append(resources.getString(R$string.abc_menu_delete_shortcut_label));
        } else if (cG == '\n') {
            sb.append(resources.getString(R$string.abc_menu_enter_shortcut_label));
        } else if (cG != ' ') {
            sb.append(cG);
        } else {
            sb.append(resources.getString(R$string.abc_menu_space_shortcut_label));
        }
        return sb.toString();
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.f3977o != null;
    }

    public CharSequence i(n.a aVar) {
        return (aVar == null || !aVar.d()) ? getTitle() : getTitleCondensed();
    }

    @Override // f0.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.D;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f3987y & 1) == 1;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f3987y & 2) == 2;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f3987y & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        l0.b bVar = this.B;
        return (bVar == null || !bVar.g()) ? (this.f3987y & 8) == 0 : (this.f3987y & 8) == 0 && this.B.b();
    }

    public boolean j() {
        l0.b bVar;
        if ((this.f3988z & 8) == 0) {
            return false;
        }
        if (this.A == null && (bVar = this.B) != null) {
            this.A = bVar.d(this);
        }
        return this.A != null;
    }

    public boolean k() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.f3979q;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        g gVar = this.f3976n;
        if (gVar.h(gVar, this)) {
            return true;
        }
        Runnable runnable = this.f3978p;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.f3969g != null) {
            try {
                this.f3976n.w().startActivity(this.f3969g);
                return true;
            } catch (ActivityNotFoundException e3) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e3);
            }
        }
        l0.b bVar = this.B;
        return bVar != null && bVar.e();
    }

    public boolean l() {
        return (this.f3987y & 32) == 32;
    }

    public boolean m() {
        return (this.f3987y & 4) != 0;
    }

    public boolean n() {
        return (this.f3988z & 1) == 1;
    }

    @Override // f0.b, android.view.MenuItem
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public f0.b setActionView(int i3) {
        Context contextW = this.f3976n.w();
        setActionView(LayoutInflater.from(contextW).inflate(i3, (ViewGroup) new LinearLayout(contextW), false));
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public f0.b setActionView(View view) {
        int i3;
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && (i3 = this.f3963a) > 0) {
            view.setId(i3);
        }
        this.f3976n.K(this);
        return this;
    }

    public void q(boolean z3) {
        this.D = z3;
        this.f3976n.M(false);
    }

    public void r(boolean z3) {
        int i3 = this.f3987y;
        int i4 = (z3 ? 2 : 0) | (i3 & (-3));
        this.f3987y = i4;
        if (i3 != i4) {
            this.f3976n.M(false);
        }
    }

    public boolean requiresActionButton() {
        return (this.f3988z & 2) == 2;
    }

    public boolean requiresOverflow() {
        return (requiresActionButton() || n()) ? false : true;
    }

    public void s(boolean z3) {
        this.f3987y = (z3 ? 4 : 0) | (this.f3987y & (-5));
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c3) {
        if (this.f3972j == c3) {
            return this;
        }
        this.f3972j = Character.toLowerCase(c3);
        this.f3976n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z3) {
        int i3 = this.f3987y;
        int i4 = (z3 ? 1 : 0) | (i3 & (-2));
        this.f3987y = i4;
        if (i3 != i4) {
            this.f3976n.M(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z3) {
        if ((this.f3987y & 4) != 0) {
            this.f3976n.X(this);
        } else {
            r(z3);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z3) {
        if (z3) {
            this.f3987y |= 16;
        } else {
            this.f3987y &= -17;
        }
        this.f3976n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f3975m = 0;
        this.f3974l = drawable;
        this.f3986x = true;
        this.f3976n.M(false);
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f3982t = colorStateList;
        this.f3984v = true;
        this.f3986x = true;
        this.f3976n.M(false);
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f3983u = mode;
        this.f3985w = true;
        this.f3986x = true;
        this.f3976n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f3969g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c3) {
        if (this.f3970h == c3) {
            return this;
        }
        this.f3970h = c3;
        this.f3976n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.C = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f3979q = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c3, char c4) {
        this.f3970h = c3;
        this.f3972j = Character.toLowerCase(c4);
        this.f3976n.M(false);
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public void setShowAsAction(int i3) {
        int i4 = i3 & 3;
        if (i4 != 0 && i4 != 1 && i4 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.f3988z = i3;
        this.f3976n.K(this);
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f3967e = charSequence;
        this.f3976n.M(false);
        r rVar = this.f3977o;
        if (rVar != null) {
            rVar.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f3968f = charSequence;
        this.f3976n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z3) {
        if (x(z3)) {
            this.f3976n.L(this);
        }
        return this;
    }

    public void t(boolean z3) {
        if (z3) {
            this.f3987y |= 32;
        } else {
            this.f3987y &= -33;
        }
    }

    public String toString() {
        CharSequence charSequence = this.f3967e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public void u(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.E = contextMenuInfo;
    }

    @Override // f0.b, android.view.MenuItem
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public f0.b setShowAsActionFlags(int i3) {
        setShowAsAction(i3);
        return this;
    }

    public void w(r rVar) {
        this.f3977o = rVar;
        rVar.setHeaderTitle(getTitle());
    }

    public boolean x(boolean z3) {
        int i3 = this.f3987y;
        int i4 = (z3 ? 0 : 8) | (i3 & (-9));
        this.f3987y = i4;
        return i3 != i4;
    }

    public boolean y() {
        return this.f3976n.C();
    }

    public boolean z() {
        return this.f3976n.J() && g() != 0;
    }

    @Override // android.view.MenuItem
    public f0.b setContentDescription(CharSequence charSequence) {
        this.f3980r = charSequence;
        this.f3976n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public f0.b setTooltipText(CharSequence charSequence) {
        this.f3981s = charSequence;
        this.f3976n.M(false);
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c3, int i3) {
        if (this.f3972j == c3 && this.f3973k == i3) {
            return this;
        }
        this.f3972j = Character.toLowerCase(c3);
        this.f3973k = KeyEvent.normalizeMetaState(i3);
        this.f3976n.M(false);
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c3, int i3) {
        if (this.f3970h == c3 && this.f3971i == i3) {
            return this;
        }
        this.f3970h = c3;
        this.f3971i = KeyEvent.normalizeMetaState(i3);
        this.f3976n.M(false);
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public MenuItem setShortcut(char c3, char c4, int i3, int i4) {
        this.f3970h = c3;
        this.f3971i = KeyEvent.normalizeMetaState(i3);
        this.f3972j = Character.toLowerCase(c4);
        this.f3973k = KeyEvent.normalizeMetaState(i4);
        this.f3976n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i3) {
        this.f3974l = null;
        this.f3975m = i3;
        this.f3986x = true;
        this.f3976n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i3) {
        return setTitle(this.f3976n.w().getString(i3));
    }
}
