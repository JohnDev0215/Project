package q;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* JADX INFO: renamed from: q.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class MenuItemC2152i implements MenuItem {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public MenuItem.OnActionExpandListener f21066A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f21067a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f21068b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f21069e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f21070f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Intent f21071g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public char f21072h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public char f21074j;
    public Drawable l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final MenuC2151h f21077n;
    public SubMenuC2161r o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public MenuItem.OnMenuItemClickListener f21078p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public CharSequence f21079q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f21080r;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public View f21088z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f21073i = 4096;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f21075k = 4096;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f21076m = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ColorStateList f21081s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public PorterDuff.Mode f21082t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f21083u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f21084v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f21085w = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f21086x = 16;
    public boolean B = false;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f21087y = 0;

    public MenuItemC2152i(MenuC2151h menuC2151h, int i7, int i10, int i11, int i12, CharSequence charSequence) {
        this.f21077n = menuC2151h;
        this.f21067a = i10;
        this.f21068b = i7;
        this.c = i11;
        this.d = i12;
        this.f21069e = charSequence;
    }

    public static void a(int i7, int i10, String str, StringBuilder sb) {
        if ((i7 & i10) == i10) {
            sb.append(str);
        }
    }

    public final Drawable b(Drawable drawable) {
        if (drawable != null && this.f21085w && (this.f21083u || this.f21084v)) {
            drawable = drawable.mutate();
            if (this.f21083u) {
                drawable.setTintList(this.f21081s);
            }
            if (this.f21084v) {
                drawable.setTintMode(this.f21082t);
            }
            this.f21085w = false;
        }
        return drawable;
    }

    public final boolean c() {
        return ((this.f21087y & 8) == 0 || this.f21088z == null) ? false : true;
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        if ((this.f21087y & 8) == 0) {
            return false;
        }
        if (this.f21088z == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f21066A;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f21077n.d(this);
        }
        return false;
    }

    public final boolean d() {
        return (this.f21086x & 32) == 32;
    }

    public final MenuItemC2152i e(CharSequence charSequence) {
        this.f21079q = charSequence;
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        if (!c()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f21066A;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f21077n.f(this);
        }
        return false;
    }

    public final void f(boolean z6) {
        if (z6) {
            this.f21086x |= 32;
        } else {
            this.f21086x &= -33;
        }
    }

    public final MenuItemC2152i g(CharSequence charSequence) {
        this.f21080r = charSequence;
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        View view = this.f21088z;
        if (view != null) {
            return view;
        }
        return null;
    }

    @Override // android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.f21075k;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f21074j;
    }

    @Override // android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f21079q;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return this.f21068b;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        Drawable drawable = this.l;
        if (drawable != null) {
            return b(drawable);
        }
        int i7 = this.f21076m;
        if (i7 == 0) {
            return null;
        }
        Drawable drawableK = h6.f.k(this.f21077n.f21049a, i7);
        this.f21076m = 0;
        this.l = drawableK;
        return b(drawableK);
    }

    @Override // android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.f21081s;
    }

    @Override // android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f21082t;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f21071g;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return this.f21067a;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f21073i;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.f21072h;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return this.c;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return this.o;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.f21069e;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f21070f;
        return charSequence != null ? charSequence : this.f21069e;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.f21080r;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return this.o != null;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return this.B;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return (this.f21086x & 1) == 1;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return (this.f21086x & 2) == 2;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return (this.f21086x & 16) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        return (this.f21086x & 8) == 0;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        int i7;
        this.f21088z = view;
        if (view != null && view.getId() == -1 && (i7 = this.f21067a) > 0) {
            view.setId(i7);
        }
        MenuC2151h menuC2151h = this.f21077n;
        menuC2151h.f21057k = true;
        menuC2151h.o(true);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c) {
        if (this.f21074j == c) {
            return this;
        }
        this.f21074j = Character.toLowerCase(c);
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z6) {
        int i7 = this.f21086x;
        int i10 = (z6 ? 1 : 0) | (i7 & (-2));
        this.f21086x = i10;
        if (i7 != i10) {
            this.f21077n.o(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z6) {
        int i7 = this.f21086x;
        if ((i7 & 4) != 0) {
            MenuC2151h menuC2151h = this.f21077n;
            menuC2151h.getClass();
            ArrayList arrayList = menuC2151h.f21052f;
            int size = arrayList.size();
            menuC2151h.s();
            for (int i10 = 0; i10 < size; i10++) {
                MenuItemC2152i menuItemC2152i = (MenuItemC2152i) arrayList.get(i10);
                if (menuItemC2152i.f21068b == this.f21068b && (menuItemC2152i.f21086x & 4) != 0 && menuItemC2152i.isCheckable()) {
                    boolean z10 = menuItemC2152i == this;
                    int i11 = menuItemC2152i.f21086x;
                    int i12 = (z10 ? 2 : 0) | (i11 & (-3));
                    menuItemC2152i.f21086x = i12;
                    if (i11 != i12) {
                        menuItemC2152i.f21077n.o(false);
                    }
                }
            }
            menuC2151h.r();
        } else {
            int i13 = (i7 & (-3)) | (z6 ? 2 : 0);
            this.f21086x = i13;
            if (i7 != i13) {
                this.f21077n.o(false);
            }
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        e(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z6) {
        if (z6) {
            this.f21086x |= 16;
        } else {
            this.f21086x &= -17;
        }
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.f21076m = 0;
        this.l = drawable;
        this.f21085w = true;
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f21081s = colorStateList;
        this.f21083u = true;
        this.f21085w = true;
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f21082t = mode;
        this.f21084v = true;
        this.f21085w = true;
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f21071g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c) {
        if (this.f21072h == c) {
            return this;
        }
        this.f21072h = c;
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f21066A = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f21078p = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c, char c10) {
        this.f21072h = c;
        this.f21074j = Character.toLowerCase(c10);
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i7) {
        int i10 = i7 & 3;
        if (i10 != 0 && i10 != 1 && i10 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.f21087y = i7;
        MenuC2151h menuC2151h = this.f21077n;
        menuC2151h.f21057k = true;
        menuC2151h.o(true);
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i7) {
        setShowAsAction(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f21069e = charSequence;
        this.f21077n.o(false);
        SubMenuC2161r subMenuC2161r = this.o;
        if (subMenuC2161r != null) {
            subMenuC2161r.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f21070f = charSequence;
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        g(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z6) {
        int i7 = this.f21086x;
        int i10 = (z6 ? 0 : 8) | (i7 & (-9));
        this.f21086x = i10;
        if (i7 != i10) {
            MenuC2151h menuC2151h = this.f21077n;
            menuC2151h.f21054h = true;
            menuC2151h.o(true);
        }
        return this;
    }

    public final String toString() {
        CharSequence charSequence = this.f21069e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c, int i7) {
        if (this.f21074j == c && this.f21075k == i7) {
            return this;
        }
        this.f21074j = Character.toLowerCase(c);
        this.f21075k = KeyEvent.normalizeMetaState(i7);
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c, int i7) {
        if (this.f21072h == c && this.f21073i == i7) {
            return this;
        }
        this.f21072h = c;
        this.f21073i = KeyEvent.normalizeMetaState(i7);
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c, char c10, int i7, int i10) {
        this.f21072h = c;
        this.f21073i = KeyEvent.normalizeMetaState(i7);
        this.f21074j = Character.toLowerCase(c10);
        this.f21075k = KeyEvent.normalizeMetaState(i10);
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i7) {
        this.l = null;
        this.f21076m = i7;
        this.f21085w = true;
        this.f21077n.o(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i7) {
        setTitle(this.f21077n.f21049a.getString(i7));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i7) {
        int i10;
        Context context = this.f21077n.f21049a;
        View viewInflate = LayoutInflater.from(context).inflate(i7, (ViewGroup) new LinearLayout(context), false);
        this.f21088z = viewInflate;
        if (viewInflate != null && viewInflate.getId() == -1 && (i10 = this.f21067a) > 0) {
            viewInflate.setId(i10);
        }
        MenuC2151h menuC2151h = this.f21077n;
        menuC2151h.f21057k = true;
        menuC2151h.o(true);
        return this;
    }
}
