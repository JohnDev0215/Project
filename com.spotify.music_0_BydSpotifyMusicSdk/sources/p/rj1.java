package p;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: loaded from: classes.dex */
public class rj1 extends fl implements Menu {
    public final ih2 c;

    public rj1(Context context, ih2 ih2Var) {
        super(context);
        if (ih2Var == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.c = ih2Var;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i) {
        return c(((si1) this.c).add(i));
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int iAddIntentOptions = ((si1) this.c).addIntentOptions(i, i2, i3, componentName, intentArr, intent, i4, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i5 = 0; i5 < length; i5++) {
                menuItemArr[i5] = c(menuItemArr2[i5]);
            }
        }
        return iAddIntentOptions;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i) {
        return ((si1) this.c).addSubMenu(i);
    }

    @Override // android.view.Menu
    public final void clear() {
        kd2 kd2Var = this.b;
        if (kd2Var != null) {
            kd2Var.clear();
        }
        ((si1) this.c).clear();
    }

    @Override // android.view.Menu
    public final void close() {
        ((si1) this.c).c(true);
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i) {
        return c(((si1) this.c).findItem(i));
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i) {
        return c(((si1) this.c).getItem(i));
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        return ((si1) this.c).hasVisibleItems();
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return ((si1) this.c).isShortcutKey(i, keyEvent);
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i, int i2) {
        return ((si1) this.c).performIdentifierAction(i, i2);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return ((si1) this.c).performShortcut(i, keyEvent, i2);
    }

    @Override // android.view.Menu
    public final void removeGroup(int i) {
        if (this.b != null) {
            int i2 = 0;
            while (true) {
                kd2 kd2Var = this.b;
                if (i2 >= kd2Var.k) {
                    break;
                }
                if (((mh2) kd2Var.f(i2)).getGroupId() == i) {
                    this.b.g(i2);
                    i2--;
                }
                i2++;
            }
        }
        ((si1) this.c).removeGroup(i);
    }

    @Override // android.view.Menu
    public final void removeItem(int i) {
        if (this.b != null) {
            int i2 = 0;
            while (true) {
                kd2 kd2Var = this.b;
                if (i2 >= kd2Var.k) {
                    break;
                }
                if (((mh2) kd2Var.f(i2)).getItemId() == i) {
                    this.b.g(i2);
                    break;
                }
                i2++;
            }
        }
        ((si1) this.c).removeItem(i);
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i, boolean z, boolean z2) {
        ((si1) this.c).setGroupCheckable(i, z, z2);
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i, boolean z) {
        ((si1) this.c).setGroupEnabled(i, z);
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i, boolean z) {
        ((si1) this.c).setGroupVisible(i, z);
    }

    @Override // android.view.Menu
    public final void setQwertyMode(boolean z) {
        this.c.setQwertyMode(z);
    }

    @Override // android.view.Menu
    public final int size() {
        return ((si1) this.c).f.size();
    }

    @Override // android.view.Menu
    public final MenuItem add(int i, int i2, int i3, int i4) {
        return c(((si1) this.c).add(i, i2, i3, i4));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return ((si1) this.c).addSubMenu(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return c(((si1) this.c).a(0, 0, 0, charSequence));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return ((si1) this.c).addSubMenu(i, i2, i3, i4);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return ((si1) this.c).addSubMenu(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return c(((si1) this.c).a(i, i2, i3, charSequence));
    }
}
