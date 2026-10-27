package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.core.view.ActionProvider;
import defpackage.bz;
import defpackage.pw2;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItem implements pw2 {
    public CharSequence d;
    public CharSequence e;
    public Intent f;
    public char g;
    public char i;
    public Drawable k;
    public final Context l;
    public CharSequence m;
    public CharSequence n;
    public int h = 4096;
    public int j = 4096;
    public ColorStateList o = null;
    public PorterDuff.Mode p = null;
    public boolean q = false;
    public boolean r = false;
    public int s = 16;
    public final int a = R.id.home;
    public final int b = 0;
    public final int c = 0;

    public ActionMenuItem(Context context, CharSequence charSequence) {
        this.l = context;
        this.d = charSequence;
    }

    @Override // defpackage.pw2
    public final pw2 a(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // defpackage.pw2
    public final ActionProvider b() {
        return null;
    }

    public final void c() {
        Drawable drawable = this.k;
        if (drawable != null) {
            if (this.q || this.r) {
                this.k = drawable;
                Drawable drawableMutate = drawable.mutate();
                this.k = drawableMutate;
                if (this.q) {
                    drawableMutate.setTintList(this.o);
                }
                if (this.r) {
                    this.k.setTintMode(this.p);
                }
            }
        }
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final boolean collapseActionView() {
        return false;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final View getActionView() {
        return null;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.j;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.i;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.m;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return this.b;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        return this.k;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.o;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.p;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return this.a;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.h;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.g;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return this.c;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.d;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.e;
        return charSequence != null ? charSequence : this.d;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.n;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return false;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return (this.s & 1) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return (this.s & 2) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return (this.s & 16) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        return (this.s & 8) == 0;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c) {
        this.i = Character.toLowerCase(c);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z) {
        this.s = (z ? 1 : 0) | (this.s & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z) {
        this.s = (z ? 2 : 0) | (this.s & (-3));
        return this;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final pw2 setContentDescription(CharSequence charSequence) {
        this.m = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z) {
        this.s = (z ? 16 : 0) | (this.s & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.k = drawable;
        c();
        return this;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.o = colorStateList;
        this.q = true;
        c();
        return this;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.p = mode;
        this.r = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c) {
        this.g = c;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c, char c2) {
        this.g = c;
        this.i = Character.toLowerCase(c2);
        return this;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.d = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.e = charSequence;
        return this;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final pw2 setTooltipText(CharSequence charSequence) {
        this.n = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z) {
        this.s = (this.s & 8) | (z ? 0 : 8);
        return this;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final MenuItem setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c, int i) {
        this.i = Character.toLowerCase(c);
        this.j = KeyEvent.normalizeMetaState(i);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setContentDescription(CharSequence charSequence) {
        this.m = charSequence;
        return this;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c, int i) {
        this.g = c;
        this.h = KeyEvent.normalizeMetaState(i);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i) {
        this.d = this.l.getResources().getString(i);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTooltipText(CharSequence charSequence) {
        this.n = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i) {
        Object obj = bz.a;
        this.k = this.l.getDrawable(i);
        c();
        return this;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.g = c;
        this.h = KeyEvent.normalizeMetaState(i);
        this.i = Character.toLowerCase(c2);
        this.j = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    @Override // defpackage.pw2, android.view.MenuItem
    public final void setShowAsAction(int i) {
    }
}
