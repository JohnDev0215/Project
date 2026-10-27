package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseMenuPresenter implements MenuPresenter {
    public final Context e;
    public Context f;
    public MenuBuilder g;
    public final LayoutInflater h;
    public MenuPresenter.Callback i;
    public final int j;
    public final int k;
    public MenuView l;
    public int m;

    public BaseMenuPresenter(Context context, int i, int i2) {
        this.e = context;
        this.h = LayoutInflater.from(context);
        this.j = i;
        this.k = i2;
    }

    public abstract void a(MenuItemImpl menuItemImpl, MenuView.ItemView itemView);

    public boolean b(int i, ViewGroup viewGroup) {
        viewGroup.removeViewAt(i);
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void c(MenuBuilder menuBuilder, boolean z) {
        MenuPresenter.Callback callback = this.i;
        if (callback != null) {
            callback.c(menuBuilder, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void d(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.l;
        if (viewGroup == null) {
            return;
        }
        MenuBuilder menuBuilder = this.g;
        int i = 0;
        if (menuBuilder != null) {
            menuBuilder.i();
            ArrayList<MenuItemImpl> arrayListL = this.g.l();
            int size = arrayListL.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                MenuItemImpl menuItemImpl = arrayListL.get(i3);
                if (n(menuItemImpl)) {
                    View childAt = viewGroup.getChildAt(i2);
                    MenuItemImpl itemData = childAt instanceof MenuView.ItemView ? ((MenuView.ItemView) childAt).getItemData() : null;
                    View viewJ = j(menuItemImpl, childAt, viewGroup);
                    if (menuItemImpl != itemData) {
                        viewJ.setPressed(false);
                        viewJ.jumpDrawablesToCurrentState();
                    }
                    if (viewJ != childAt) {
                        ViewGroup viewGroup2 = (ViewGroup) viewJ.getParent();
                        if (viewGroup2 != null) {
                            viewGroup2.removeView(viewJ);
                        }
                        ((ViewGroup) this.l).addView(viewJ, i2);
                    }
                    i2++;
                }
            }
            i = i2;
        }
        while (i < viewGroup.getChildCount()) {
            if (!b(i, viewGroup)) {
                i++;
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean e() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void f(MenuPresenter.Callback callback) {
        this.i = callback;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean g(MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final int getId() {
        return this.m;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void h(Context context, MenuBuilder menuBuilder) {
        this.f = context;
        LayoutInflater.from(context);
        this.g = menuBuilder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View j(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        MenuView.ItemView itemView = view instanceof MenuView.ItemView ? (MenuView.ItemView) view : (MenuView.ItemView) this.h.inflate(this.k, viewGroup, false);
        a(menuItemImpl, itemView);
        return (View) itemView;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean k(SubMenuBuilder subMenuBuilder) {
        MenuPresenter.Callback callback = this.i;
        MenuBuilder menuBuilder = subMenuBuilder;
        if (callback == null) {
            return false;
        }
        if (subMenuBuilder == null) {
            menuBuilder = this.g;
        }
        return callback.d(menuBuilder);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean m(MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean n(MenuItemImpl menuItemImpl) {
        return true;
    }
}
