package q;

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
import o0.b;
import q.n;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: compiled from: MenuItemImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class i implements i0.b {
    public View A;
    public o0.b B;
    public MenuItem.OnActionExpandListener C;
    public ContextMenu.ContextMenuInfo E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7160a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f7161b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f7162c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f7163d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f7164e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f7165f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Intent f7166g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public char f7167h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public char f7169j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable f7171l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public g f7173n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public r f7174o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Runnable f7175p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public MenuItem.OnMenuItemClickListener f7176q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f7177r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public CharSequence f7178s;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f7185z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f7168i = 4096;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f7170k = 4096;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7172m = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ColorStateList f7179t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public PorterDuff.Mode f7180u = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f7181v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f7182w = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f7183x = false;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f7184y = 16;
    public boolean D = false;

    /* JADX INFO: compiled from: MenuItemImpl.java */
    public class a implements b.InterfaceC0098b {
        public a() {
        }

        @Override // o0.b.InterfaceC0098b
        public void onActionProviderVisibilityChanged(boolean z7) {
            i iVar = i.this;
            iVar.f7173n.L(iVar);
        }
    }

    public i(g gVar, int i8, int i9, int i10, int i11, CharSequence charSequence, int i12) {
        this.f7185z = 0;
        this.f7173n = gVar;
        this.f7160a = i9;
        this.f7161b = i8;
        this.f7162c = i10;
        this.f7163d = i11;
        this.f7164e = charSequence;
        this.f7185z = i12;
    }

    public static void d(StringBuilder sb, int i8, int i9, String str) {
        if ((i8 & i9) == i9) {
            sb.append(str);
        }
    }

    public boolean A() {
        return (this.f7185z & 4) == 4;
    }

    @Override // i0.b
    public i0.b a(o0.b bVar) {
        o0.b bVar2 = this.B;
        if (bVar2 != null) {
            bVar2.h();
        }
        this.A = null;
        this.B = bVar;
        this.f7173n.M(true);
        o0.b bVar3 = this.B;
        if (bVar3 != null) {
            bVar3.j(new a());
        }
        return this;
    }

    @Override // i0.b
    public o0.b b() {
        return this.B;
    }

    public void c() {
        this.f7173n.K(this);
    }

    @Override // i0.b, android.view.MenuItem
    public boolean collapseActionView() {
        if ((this.f7185z & 8) == 0) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f7173n.f(this);
        }
        return false;
    }

    public final Drawable e(Drawable drawable) {
        if (drawable != null && this.f7183x && (this.f7181v || this.f7182w)) {
            drawable = h0.a.r(drawable).mutate();
            if (this.f7181v) {
                h0.a.o(drawable, this.f7179t);
            }
            if (this.f7182w) {
                h0.a.p(drawable, this.f7180u);
            }
            this.f7183x = false;
        }
        return drawable;
    }

    @Override // i0.b, android.view.MenuItem
    public boolean expandActionView() {
        if (!j()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f7173n.m(this);
        }
        return false;
    }

    public int f() {
        return this.f7163d;
    }

    public char g() {
        return this.f7173n.I() ? this.f7169j : this.f7167h;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // i0.b, android.view.MenuItem
    public View getActionView() {
        View view = this.A;
        if (view != null) {
            return view;
        }
        o0.b bVar = this.B;
        if (bVar == null) {
            return null;
        }
        View viewD = bVar.d(this);
        this.A = viewD;
        return viewD;
    }

    @Override // i0.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f7170k;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f7169j;
    }

    @Override // i0.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f7177r;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f7161b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable drawable = this.f7171l;
        if (drawable != null) {
            return e(drawable);
        }
        if (this.f7172m == 0) {
            return null;
        }
        Drawable drawableD = m.a.d(this.f7173n.w(), this.f7172m);
        this.f7172m = 0;
        this.f7171l = drawableD;
        return e(drawableD);
    }

    @Override // i0.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f7179t;
    }

    @Override // i0.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f7180u;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f7166g;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.f7160a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    @Override // i0.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f7168i;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f7167h;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f7162c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.f7174o;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.f7164e;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f7165f;
        if (charSequence == null) {
            charSequence = this.f7164e;
        }
        return (Build.VERSION.SDK_INT >= 18 || charSequence == null || (charSequence instanceof String)) ? charSequence : charSequence.toString();
    }

    @Override // i0.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f7178s;
    }

    public String h() {
        char cG = g();
        if (cG == 0) {
            return "";
        }
        Resources resources = this.f7173n.w().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.f7173n.w()).hasPermanentMenuKey()) {
            sb.append(resources.getString(R$string.abc_prepend_shortcut_label));
        }
        int i8 = this.f7173n.I() ? this.f7170k : this.f7168i;
        d(sb, i8, CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2, resources.getString(R$string.abc_menu_meta_shortcut_label));
        d(sb, i8, 4096, resources.getString(R$string.abc_menu_ctrl_shortcut_label));
        d(sb, i8, 2, resources.getString(R$string.abc_menu_alt_shortcut_label));
        d(sb, i8, 1, resources.getString(R$string.abc_menu_shift_shortcut_label));
        d(sb, i8, 4, resources.getString(R$string.abc_menu_sym_shortcut_label));
        d(sb, i8, 8, resources.getString(R$string.abc_menu_function_shortcut_label));
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
        return this.f7174o != null;
    }

    public CharSequence i(n.a aVar) {
        return (aVar == null || !aVar.d()) ? getTitle() : getTitleCondensed();
    }

    @Override // i0.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.D;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f7184y & 1) == 1;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f7184y & 2) == 2;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f7184y & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        o0.b bVar = this.B;
        return (bVar == null || !bVar.g()) ? (this.f7184y & 8) == 0 : (this.f7184y & 8) == 0 && this.B.b();
    }

    public boolean j() {
        o0.b bVar;
        if ((this.f7185z & 8) == 0) {
            return false;
        }
        if (this.A == null && (bVar = this.B) != null) {
            this.A = bVar.d(this);
        }
        return this.A != null;
    }

    public boolean k() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.f7176q;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        g gVar = this.f7173n;
        if (gVar.h(gVar, this)) {
            return true;
        }
        Runnable runnable = this.f7175p;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.f7166g != null) {
            try {
                this.f7173n.w().startActivity(this.f7166g);
                return true;
            } catch (ActivityNotFoundException e8) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e8);
            }
        }
        o0.b bVar = this.B;
        return bVar != null && bVar.e();
    }

    public boolean l() {
        return (this.f7184y & 32) == 32;
    }

    public boolean m() {
        return (this.f7184y & 4) != 0;
    }

    public boolean n() {
        return (this.f7185z & 1) == 1;
    }

    @Override // i0.b, android.view.MenuItem
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public i0.b setActionView(int i8) {
        Context contextW = this.f7173n.w();
        setActionView(LayoutInflater.from(contextW).inflate(i8, (ViewGroup) new LinearLayout(contextW), false));
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public i0.b setActionView(View view) {
        int i8;
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && (i8 = this.f7160a) > 0) {
            view.setId(i8);
        }
        this.f7173n.K(this);
        return this;
    }

    public void q(boolean z7) {
        this.D = z7;
        this.f7173n.M(false);
    }

    public void r(boolean z7) {
        int i8 = this.f7184y;
        int i9 = (z7 ? 2 : 0) | (i8 & (-3));
        this.f7184y = i9;
        if (i8 != i9) {
            this.f7173n.M(false);
        }
    }

    public boolean requiresActionButton() {
        return (this.f7185z & 2) == 2;
    }

    public boolean requiresOverflow() {
        return (requiresActionButton() || n()) ? false : true;
    }

    public void s(boolean z7) {
        this.f7184y = (z7 ? 4 : 0) | (this.f7184y & (-5));
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c8) {
        if (this.f7169j == c8) {
            return this;
        }
        this.f7169j = Character.toLowerCase(c8);
        this.f7173n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z7) {
        int i8 = this.f7184y;
        int i9 = (z7 ? 1 : 0) | (i8 & (-2));
        this.f7184y = i9;
        if (i8 != i9) {
            this.f7173n.M(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z7) {
        if ((this.f7184y & 4) != 0) {
            this.f7173n.X(this);
        } else {
            r(z7);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z7) {
        if (z7) {
            this.f7184y |= 16;
        } else {
            this.f7184y &= -17;
        }
        this.f7173n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f7172m = 0;
        this.f7171l = drawable;
        this.f7183x = true;
        this.f7173n.M(false);
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f7179t = colorStateList;
        this.f7181v = true;
        this.f7183x = true;
        this.f7173n.M(false);
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f7180u = mode;
        this.f7182w = true;
        this.f7183x = true;
        this.f7173n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f7166g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c8) {
        if (this.f7167h == c8) {
            return this;
        }
        this.f7167h = c8;
        this.f7173n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.C = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f7176q = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c8, char c9) {
        this.f7167h = c8;
        this.f7169j = Character.toLowerCase(c9);
        this.f7173n.M(false);
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public void setShowAsAction(int i8) {
        int i9 = i8 & 3;
        if (i9 != 0 && i9 != 1 && i9 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.f7185z = i8;
        this.f7173n.K(this);
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f7164e = charSequence;
        this.f7173n.M(false);
        r rVar = this.f7174o;
        if (rVar != null) {
            rVar.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f7165f = charSequence;
        this.f7173n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z7) {
        if (x(z7)) {
            this.f7173n.L(this);
        }
        return this;
    }

    public void t(boolean z7) {
        if (z7) {
            this.f7184y |= 32;
        } else {
            this.f7184y &= -33;
        }
    }

    public String toString() {
        CharSequence charSequence = this.f7164e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public void u(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.E = contextMenuInfo;
    }

    @Override // i0.b, android.view.MenuItem
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public i0.b setShowAsActionFlags(int i8) {
        setShowAsAction(i8);
        return this;
    }

    public void w(r rVar) {
        this.f7174o = rVar;
        rVar.setHeaderTitle(getTitle());
    }

    public boolean x(boolean z7) {
        int i8 = this.f7184y;
        int i9 = (z7 ? 0 : 8) | (i8 & (-9));
        this.f7184y = i9;
        return i8 != i9;
    }

    public boolean y() {
        return this.f7173n.C();
    }

    public boolean z() {
        return this.f7173n.J() && g() != 0;
    }

    @Override // android.view.MenuItem
    public i0.b setContentDescription(CharSequence charSequence) {
        this.f7177r = charSequence;
        this.f7173n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public i0.b setTooltipText(CharSequence charSequence) {
        this.f7178s = charSequence;
        this.f7173n.M(false);
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c8, int i8) {
        if (this.f7169j == c8 && this.f7170k == i8) {
            return this;
        }
        this.f7169j = Character.toLowerCase(c8);
        this.f7170k = KeyEvent.normalizeMetaState(i8);
        this.f7173n.M(false);
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c8, int i8) {
        if (this.f7167h == c8 && this.f7168i == i8) {
            return this;
        }
        this.f7167h = c8;
        this.f7168i = KeyEvent.normalizeMetaState(i8);
        this.f7173n.M(false);
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public MenuItem setShortcut(char c8, char c9, int i8, int i9) {
        this.f7167h = c8;
        this.f7168i = KeyEvent.normalizeMetaState(i8);
        this.f7169j = Character.toLowerCase(c9);
        this.f7170k = KeyEvent.normalizeMetaState(i9);
        this.f7173n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i8) {
        this.f7171l = null;
        this.f7172m = i8;
        this.f7183x = true;
        this.f7173n.M(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i8) {
        return setTitle(this.f7173n.w().getString(i8));
    }
}
