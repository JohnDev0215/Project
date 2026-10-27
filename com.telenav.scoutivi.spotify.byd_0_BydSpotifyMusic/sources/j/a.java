package j;

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
public class a implements b0.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f7071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f7072b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f7073c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private CharSequence f7074d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private CharSequence f7075e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Intent f7076f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private char f7077g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private char f7079i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Drawable f7081k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Context f7082l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private MenuItem.OnMenuItemClickListener f7083m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private CharSequence f7084n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private CharSequence f7085o;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f7078h = 4096;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f7080j = 4096;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private ColorStateList f7086p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private PorterDuff.Mode f7087q = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f7088r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f7089s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f7090t = 16;

    public a(Context context, int i7, int i8, int i9, int i10, CharSequence charSequence) {
        this.f7082l = context;
        this.f7071a = i8;
        this.f7072b = i7;
        this.f7073c = i10;
        this.f7074d = charSequence;
    }

    private void c() {
        Drawable drawable = this.f7081k;
        if (drawable != null) {
            if (this.f7088r || this.f7089s) {
                Drawable drawableL = a0.a.l(drawable);
                this.f7081k = drawableL;
                Drawable drawableMutate = drawableL.mutate();
                this.f7081k = drawableMutate;
                if (this.f7088r) {
                    a0.a.i(drawableMutate, this.f7086p);
                }
                if (this.f7089s) {
                    a0.a.j(this.f7081k, this.f7087q);
                }
            }
        }
    }

    @Override // b0.b
    public b0.b a(i0.b bVar) {
        throw new UnsupportedOperationException();
    }

    @Override // b0.b
    public i0.b b() {
        return null;
    }

    @Override // b0.b, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // b0.b, android.view.MenuItem
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public b0.b setActionView(int i7) {
        throw new UnsupportedOperationException();
    }

    @Override // b0.b, android.view.MenuItem
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public b0.b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // b0.b, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // b0.b, android.view.MenuItem
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public b0.b setShowAsActionFlags(int i7) {
        setShowAsAction(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // b0.b, android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // b0.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f7080j;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f7079i;
    }

    @Override // b0.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f7084n;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f7072b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f7081k;
    }

    @Override // b0.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f7086p;
    }

    @Override // b0.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f7087q;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f7076f;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f7071a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // b0.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f7078h;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f7077g;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f7073c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f7074d;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f7075e;
        return charSequence != null ? charSequence : this.f7074d;
    }

    @Override // b0.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f7085o;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // b0.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f7090t & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f7090t & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f7090t & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.f7090t & 8) == 0;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c7) {
        this.f7079i = Character.toLowerCase(c7);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z6) {
        this.f7090t = (z6 ? 1 : 0) | (this.f7090t & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z6) {
        this.f7090t = (z6 ? 2 : 0) | (this.f7090t & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z6) {
        this.f7090t = (z6 ? 16 : 0) | (this.f7090t & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f7081k = drawable;
        c();
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f7086p = colorStateList;
        this.f7088r = true;
        c();
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f7087q = mode;
        this.f7089s = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f7076f = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c7) {
        this.f7077g = c7;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f7083m = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c7, char c8) {
        this.f7077g = c7;
        this.f7079i = Character.toLowerCase(c8);
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public void setShowAsAction(int i7) {
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f7074d = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f7075e = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z6) {
        this.f7090t = (this.f7090t & 8) | (z6 ? 0 : 8);
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c7, int i7) {
        this.f7079i = Character.toLowerCase(c7);
        this.f7080j = KeyEvent.normalizeMetaState(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public b0.b setContentDescription(CharSequence charSequence) {
        this.f7084n = charSequence;
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c7, int i7) {
        this.f7077g = c7;
        this.f7078h = KeyEvent.normalizeMetaState(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i7) {
        this.f7074d = this.f7082l.getResources().getString(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public b0.b setTooltipText(CharSequence charSequence) {
        this.f7085o = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i7) {
        this.f7081k = x.a.c(this.f7082l, i7);
        c();
        return this;
    }

    @Override // b0.b, android.view.MenuItem
    public MenuItem setShortcut(char c7, char c8, int i7, int i8) {
        this.f7077g = c7;
        this.f7078h = KeyEvent.normalizeMetaState(i7);
        this.f7079i = Character.toLowerCase(c8);
        this.f7080j = KeyEvent.normalizeMetaState(i8);
        return this;
    }
}
