package b.b.p.j;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: ActionMenuItem.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b.f.h.a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f908a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f909b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f910c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f911d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f912e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Intent f913f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public char f914g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public char f916i;
    public Drawable k;
    public Context l;
    public MenuItem.OnMenuItemClickListener m;
    public CharSequence n;
    public CharSequence o;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f915h = 4096;
    public int j = 4096;
    public ColorStateList p = null;
    public PorterDuff.Mode q = null;
    public boolean r = false;
    public boolean s = false;
    public int t = 16;

    public a(Context context, int i2, int i3, int i4, int i5, CharSequence charSequence) {
        this.l = context;
        this.f908a = i3;
        this.f909b = i2;
        this.f910c = i5;
        this.f911d = charSequence;
    }

    @Override // b.f.h.a.b
    public b.f.h.a.b a(b.f.m.b bVar) {
        throw new UnsupportedOperationException();
    }

    @Override // b.f.h.a.b
    public b.f.m.b b() {
        return null;
    }

    public final void c() {
        Drawable drawable = this.k;
        if (drawable != null) {
            if (this.r || this.s) {
                Drawable drawableQ = b.f.g.l.a.q(drawable);
                this.k = drawableQ;
                Drawable drawableMutate = drawableQ.mutate();
                this.k = drawableMutate;
                if (this.r) {
                    b.f.g.l.a.n(drawableMutate, this.p);
                }
                if (this.s) {
                    b.f.g.l.a.o(this.k, this.q);
                }
            }
        }
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public b.f.h.a.b setActionView(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public b.f.h.a.b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public b.f.h.a.b setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.j;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f916i;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.n;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f909b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.k;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.p;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.q;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f913f;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f908a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f915h;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f914g;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f910c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f911d;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f912e;
        return charSequence != null ? charSequence : this.f911d;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.o;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.t & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.t & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.t & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.t & 8) == 0;
    }

    public boolean requiresActionButton() {
        return true;
    }

    public boolean requiresOverflow() {
        return false;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        this.f916i = Character.toLowerCase(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.t = (z ? 1 : 0) | (this.t & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.t = (z ? 2 : 0) | (this.t & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.t = (z ? 16 : 0) | (this.t & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.k = drawable;
        c();
        return this;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.p = colorStateList;
        this.r = true;
        c();
        return this;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.q = mode;
        this.s = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f913f = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        this.f914g = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.m = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.f914g = c2;
        this.f916i = Character.toLowerCase(c3);
        return this;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public void setShowAsAction(int i2) {
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f911d = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f912e = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        this.t = (this.t & 8) | (z ? 0 : 8);
        return this;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2, int i2) {
        this.f916i = Character.toLowerCase(c2);
        this.j = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public b.f.h.a.b setContentDescription(CharSequence charSequence) {
        this.n = charSequence;
        return this;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c2, int i2) {
        this.f914g = c2;
        this.f915h = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        this.f911d = this.l.getResources().getString(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public b.f.h.a.b setTooltipText(CharSequence charSequence) {
        this.o = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        this.k = b.f.f.a.c(this.l, i2);
        c();
        return this;
    }

    @Override // b.f.h.a.b, android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.f914g = c2;
        this.f915h = KeyEvent.normalizeMetaState(i2);
        this.f916i = Character.toLowerCase(c3);
        this.j = KeyEvent.normalizeMetaState(i3);
        return this;
    }
}
