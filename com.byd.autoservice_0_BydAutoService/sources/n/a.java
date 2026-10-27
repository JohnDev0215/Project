package n;

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
public class a implements f0.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3848a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3849b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3850c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f3851d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f3852e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Intent f3853f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public char f3854g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public char f3856i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Drawable f3858k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Context f3859l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public MenuItem.OnMenuItemClickListener f3860m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f3861n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CharSequence f3862o;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3855h = 4096;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3857j = 4096;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ColorStateList f3863p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public PorterDuff.Mode f3864q = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f3865r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f3866s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3867t = 16;

    public a(Context context, int i3, int i4, int i5, int i6, CharSequence charSequence) {
        this.f3859l = context;
        this.f3848a = i4;
        this.f3849b = i3;
        this.f3850c = i6;
        this.f3851d = charSequence;
    }

    @Override // f0.b
    public f0.b a(l0.b bVar) {
        throw new UnsupportedOperationException();
    }

    @Override // f0.b
    public l0.b b() {
        return null;
    }

    public final void c() {
        Drawable drawable = this.f3858k;
        if (drawable != null) {
            if (this.f3865r || this.f3866s) {
                Drawable drawableR = e0.a.r(drawable);
                this.f3858k = drawableR;
                Drawable drawableMutate = drawableR.mutate();
                this.f3858k = drawableMutate;
                if (this.f3865r) {
                    e0.a.o(drawableMutate, this.f3863p);
                }
                if (this.f3866s) {
                    e0.a.p(this.f3858k, this.f3864q);
                }
            }
        }
    }

    @Override // f0.b, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // f0.b, android.view.MenuItem
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public f0.b setActionView(int i3) {
        throw new UnsupportedOperationException();
    }

    @Override // f0.b, android.view.MenuItem
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public f0.b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // f0.b, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // f0.b, android.view.MenuItem
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public f0.b setShowAsActionFlags(int i3) {
        setShowAsAction(i3);
        return this;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // f0.b, android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // f0.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f3857j;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f3856i;
    }

    @Override // f0.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f3861n;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f3849b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f3858k;
    }

    @Override // f0.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f3863p;
    }

    @Override // f0.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f3864q;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f3853f;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f3848a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // f0.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f3855h;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f3854g;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f3850c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f3851d;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f3852e;
        return charSequence != null ? charSequence : this.f3851d;
    }

    @Override // f0.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f3862o;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // f0.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f3867t & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f3867t & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f3867t & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.f3867t & 8) == 0;
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
    public MenuItem setAlphabeticShortcut(char c3) {
        this.f3856i = Character.toLowerCase(c3);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z3) {
        this.f3867t = (z3 ? 1 : 0) | (this.f3867t & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z3) {
        this.f3867t = (z3 ? 2 : 0) | (this.f3867t & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z3) {
        this.f3867t = (z3 ? 16 : 0) | (this.f3867t & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f3858k = drawable;
        c();
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f3863p = colorStateList;
        this.f3865r = true;
        c();
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f3864q = mode;
        this.f3866s = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f3853f = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c3) {
        this.f3854g = c3;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f3860m = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c3, char c4) {
        this.f3854g = c3;
        this.f3856i = Character.toLowerCase(c4);
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public void setShowAsAction(int i3) {
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f3851d = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f3852e = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z3) {
        this.f3867t = (this.f3867t & 8) | (z3 ? 0 : 8);
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c3, int i3) {
        this.f3856i = Character.toLowerCase(c3);
        this.f3857j = KeyEvent.normalizeMetaState(i3);
        return this;
    }

    @Override // android.view.MenuItem
    public f0.b setContentDescription(CharSequence charSequence) {
        this.f3861n = charSequence;
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c3, int i3) {
        this.f3854g = c3;
        this.f3855h = KeyEvent.normalizeMetaState(i3);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i3) {
        this.f3851d = this.f3859l.getResources().getString(i3);
        return this;
    }

    @Override // android.view.MenuItem
    public f0.b setTooltipText(CharSequence charSequence) {
        this.f3862o = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i3) {
        this.f3858k = b0.a.d(this.f3859l, i3);
        c();
        return this;
    }

    @Override // f0.b, android.view.MenuItem
    public MenuItem setShortcut(char c3, char c4, int i3, int i4) {
        this.f3854g = c3;
        this.f3855h = KeyEvent.normalizeMetaState(i3);
        this.f3856i = Character.toLowerCase(c4);
        this.f3857j = KeyEvent.normalizeMetaState(i4);
        return this;
    }
}
