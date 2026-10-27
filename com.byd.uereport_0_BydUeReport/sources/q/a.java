package q;

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
public class a implements i0.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7047a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f7048b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f7049c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f7050d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f7051e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Intent f7052f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public char f7053g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public char f7055i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Drawable f7057k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Context f7058l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public MenuItem.OnMenuItemClickListener f7059m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f7060n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CharSequence f7061o;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f7054h = 4096;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f7056j = 4096;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ColorStateList f7062p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public PorterDuff.Mode f7063q = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f7064r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f7065s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f7066t = 16;

    public a(Context context, int i8, int i9, int i10, int i11, CharSequence charSequence) {
        this.f7058l = context;
        this.f7047a = i9;
        this.f7048b = i8;
        this.f7049c = i11;
        this.f7050d = charSequence;
    }

    @Override // i0.b
    public i0.b a(o0.b bVar) {
        throw new UnsupportedOperationException();
    }

    @Override // i0.b
    public o0.b b() {
        return null;
    }

    public final void c() {
        Drawable drawable = this.f7057k;
        if (drawable != null) {
            if (this.f7064r || this.f7065s) {
                Drawable drawableR = h0.a.r(drawable);
                this.f7057k = drawableR;
                Drawable drawableMutate = drawableR.mutate();
                this.f7057k = drawableMutate;
                if (this.f7064r) {
                    h0.a.o(drawableMutate, this.f7062p);
                }
                if (this.f7065s) {
                    h0.a.p(this.f7057k, this.f7063q);
                }
            }
        }
    }

    @Override // i0.b, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // i0.b, android.view.MenuItem
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public i0.b setActionView(int i8) {
        throw new UnsupportedOperationException();
    }

    @Override // i0.b, android.view.MenuItem
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public i0.b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // i0.b, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // i0.b, android.view.MenuItem
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public i0.b setShowAsActionFlags(int i8) {
        setShowAsAction(i8);
        return this;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // i0.b, android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // i0.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f7056j;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f7055i;
    }

    @Override // i0.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f7060n;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f7048b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f7057k;
    }

    @Override // i0.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f7062p;
    }

    @Override // i0.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f7063q;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f7052f;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f7047a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // i0.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f7054h;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f7053g;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f7049c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f7050d;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f7051e;
        return charSequence != null ? charSequence : this.f7050d;
    }

    @Override // i0.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f7061o;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // i0.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f7066t & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f7066t & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f7066t & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.f7066t & 8) == 0;
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
    public MenuItem setAlphabeticShortcut(char c8) {
        this.f7055i = Character.toLowerCase(c8);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z7) {
        this.f7066t = (z7 ? 1 : 0) | (this.f7066t & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z7) {
        this.f7066t = (z7 ? 2 : 0) | (this.f7066t & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z7) {
        this.f7066t = (z7 ? 16 : 0) | (this.f7066t & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f7057k = drawable;
        c();
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f7062p = colorStateList;
        this.f7064r = true;
        c();
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f7063q = mode;
        this.f7065s = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f7052f = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c8) {
        this.f7053g = c8;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f7059m = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c8, char c9) {
        this.f7053g = c8;
        this.f7055i = Character.toLowerCase(c9);
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public void setShowAsAction(int i8) {
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f7050d = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f7051e = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z7) {
        this.f7066t = (this.f7066t & 8) | (z7 ? 0 : 8);
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c8, int i8) {
        this.f7055i = Character.toLowerCase(c8);
        this.f7056j = KeyEvent.normalizeMetaState(i8);
        return this;
    }

    @Override // android.view.MenuItem
    public i0.b setContentDescription(CharSequence charSequence) {
        this.f7060n = charSequence;
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c8, int i8) {
        this.f7053g = c8;
        this.f7054h = KeyEvent.normalizeMetaState(i8);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i8) {
        this.f7050d = this.f7058l.getResources().getString(i8);
        return this;
    }

    @Override // android.view.MenuItem
    public i0.b setTooltipText(CharSequence charSequence) {
        this.f7061o = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i8) {
        this.f7057k = e0.a.d(this.f7058l, i8);
        c();
        return this;
    }

    @Override // i0.b, android.view.MenuItem
    public MenuItem setShortcut(char c8, char c9, int i8, int i9) {
        this.f7053g = c8;
        this.f7054h = KeyEvent.normalizeMetaState(i8);
        this.f7055i = Character.toLowerCase(c9);
        this.f7056j = KeyEvent.normalizeMetaState(i9);
        return this;
    }
}
