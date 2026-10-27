package j;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import i0.b;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: MenuItemWrapperICS.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends j.b implements MenuItem {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final b0.b f7094d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Method f7095e;

    /* JADX INFO: compiled from: MenuItemWrapperICS.java */
    private class a extends i0.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final ActionProvider f7096d;

        a(Context context, ActionProvider actionProvider) {
            super(context);
            this.f7096d = actionProvider;
        }

        @Override // i0.b
        public boolean a() {
            return this.f7096d.hasSubMenu();
        }

        @Override // i0.b
        public View c() {
            return this.f7096d.onCreateActionView();
        }

        @Override // i0.b
        public boolean e() {
            return this.f7096d.onPerformDefaultAction();
        }

        @Override // i0.b
        public void f(SubMenu subMenu) {
            this.f7096d.onPrepareSubMenu(c.this.d(subMenu));
        }
    }

    /* JADX INFO: compiled from: MenuItemWrapperICS.java */
    private class b extends a implements ActionProvider.VisibilityListener {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private b.InterfaceC0084b f7098f;

        b(Context context, ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        @Override // i0.b
        public boolean b() {
            return this.f7096d.isVisible();
        }

        @Override // i0.b
        public View d(MenuItem menuItem) {
            return this.f7096d.onCreateActionView(menuItem);
        }

        @Override // i0.b
        public boolean g() {
            return this.f7096d.overridesItemVisibility();
        }

        @Override // i0.b
        public void j(b.InterfaceC0084b interfaceC0084b) {
            this.f7098f = interfaceC0084b;
            this.f7096d.setVisibilityListener(interfaceC0084b != null ? this : null);
        }

        @Override // android.view.ActionProvider.VisibilityListener
        public void onActionProviderVisibilityChanged(boolean z6) {
            b.InterfaceC0084b interfaceC0084b = this.f7098f;
            if (interfaceC0084b != null) {
                interfaceC0084b.onActionProviderVisibilityChanged(z6);
            }
        }
    }

    /* JADX INFO: renamed from: j.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MenuItemWrapperICS.java */
    static class C0089c extends FrameLayout implements i.c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final CollapsibleActionView f7100e;

        /* JADX WARN: Multi-variable type inference failed */
        C0089c(View view) {
            super(view.getContext());
            this.f7100e = (CollapsibleActionView) view;
            addView(view);
        }

        View a() {
            return (View) this.f7100e;
        }

        @Override // i.c
        public void c() {
            this.f7100e.onActionViewExpanded();
        }

        @Override // i.c
        public void f() {
            this.f7100e.onActionViewCollapsed();
        }
    }

    /* JADX INFO: compiled from: MenuItemWrapperICS.java */
    private class d implements MenuItem.OnActionExpandListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final MenuItem.OnActionExpandListener f7101a;

        d(MenuItem.OnActionExpandListener onActionExpandListener) {
            this.f7101a = onActionExpandListener;
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return this.f7101a.onMenuItemActionCollapse(c.this.c(menuItem));
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return this.f7101a.onMenuItemActionExpand(c.this.c(menuItem));
        }
    }

    /* JADX INFO: compiled from: MenuItemWrapperICS.java */
    private class e implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final MenuItem.OnMenuItemClickListener f7103a;

        e(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            this.f7103a = onMenuItemClickListener;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            return this.f7103a.onMenuItemClick(c.this.c(menuItem));
        }
    }

    public c(Context context, b0.b bVar) {
        super(context);
        if (bVar == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.f7094d = bVar;
    }

    @Override // android.view.MenuItem
    public boolean collapseActionView() {
        return this.f7094d.collapseActionView();
    }

    @Override // android.view.MenuItem
    public boolean expandActionView() {
        return this.f7094d.expandActionView();
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        i0.b bVarB = this.f7094d.b();
        if (bVarB instanceof a) {
            return ((a) bVarB).f7096d;
        }
        return null;
    }

    @Override // android.view.MenuItem
    public View getActionView() {
        View actionView = this.f7094d.getActionView();
        return actionView instanceof C0089c ? ((C0089c) actionView).a() : actionView;
    }

    @Override // android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f7094d.getAlphabeticModifiers();
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f7094d.getAlphabeticShortcut();
    }

    @Override // android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f7094d.getContentDescription();
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f7094d.getGroupId();
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f7094d.getIcon();
    }

    @Override // android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f7094d.getIconTintList();
    }

    @Override // android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f7094d.getIconTintMode();
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f7094d.getIntent();
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f7094d.getItemId();
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.f7094d.getMenuInfo();
    }

    @Override // android.view.MenuItem
    public int getNumericModifiers() {
        return this.f7094d.getNumericModifiers();
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f7094d.getNumericShortcut();
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f7094d.getOrder();
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return d(this.f7094d.getSubMenu());
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f7094d.getTitle();
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return this.f7094d.getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f7094d.getTooltipText();
    }

    public void h(boolean z6) {
        try {
            if (this.f7095e == null) {
                this.f7095e = this.f7094d.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.f7095e.invoke(this.f7094d, Boolean.valueOf(z6));
        } catch (Exception e7) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e7);
        }
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.f7094d.hasSubMenu();
    }

    @Override // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.f7094d.isActionViewExpanded();
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return this.f7094d.isCheckable();
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return this.f7094d.isChecked();
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return this.f7094d.isEnabled();
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return this.f7094d.isVisible();
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        b bVar = new b(this.f7091a, actionProvider);
        b0.b bVar2 = this.f7094d;
        if (actionProvider == null) {
            bVar = null;
        }
        bVar2.a(bVar);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new C0089c(view);
        }
        this.f7094d.setActionView(view);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c7) {
        this.f7094d.setAlphabeticShortcut(c7);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z6) {
        this.f7094d.setCheckable(z6);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z6) {
        this.f7094d.setChecked(z6);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setContentDescription(CharSequence charSequence) {
        this.f7094d.setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z6) {
        this.f7094d.setEnabled(z6);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f7094d.setIcon(drawable);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f7094d.setIconTintList(colorStateList);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f7094d.setIconTintMode(mode);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f7094d.setIntent(intent);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c7) {
        this.f7094d.setNumericShortcut(c7);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f7094d.setOnActionExpandListener(onActionExpandListener != null ? new d(onActionExpandListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f7094d.setOnMenuItemClickListener(onMenuItemClickListener != null ? new e(onMenuItemClickListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c7, char c8) {
        this.f7094d.setShortcut(c7, c8);
        return this;
    }

    @Override // android.view.MenuItem
    public void setShowAsAction(int i7) {
        this.f7094d.setShowAsAction(i7);
    }

    @Override // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i7) {
        this.f7094d.setShowAsActionFlags(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f7094d.setTitle(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f7094d.setTitleCondensed(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTooltipText(CharSequence charSequence) {
        this.f7094d.setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z6) {
        return this.f7094d.setVisible(z6);
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c7, int i7) {
        this.f7094d.setAlphabeticShortcut(c7, i7);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i7) {
        this.f7094d.setIcon(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c7, int i7) {
        this.f7094d.setNumericShortcut(c7, i7);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c7, char c8, int i7, int i8) {
        this.f7094d.setShortcut(c7, c8, i7, i8);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i7) {
        this.f7094d.setTitle(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int i7) {
        this.f7094d.setActionView(i7);
        View actionView = this.f7094d.getActionView();
        if (actionView instanceof CollapsibleActionView) {
            this.f7094d.setActionView(new C0089c(actionView));
        }
        return this;
    }
}
