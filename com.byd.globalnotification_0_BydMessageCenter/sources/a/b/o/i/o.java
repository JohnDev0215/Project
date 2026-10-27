package a.b.o.i;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: loaded from: classes.dex */
public class o extends c implements Menu {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a.h.f.a.a f210d;

    public o(Context context, a.h.f.a.a aVar) {
        super(context);
        if (aVar == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.f210d = aVar;
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return c(this.f210d.add(i));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return c(this.f210d.add(i, i2, i3, i4));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return c(this.f210d.add(i, i2, i3, charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return c(this.f210d.add(charSequence));
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int iAddIntentOptions = this.f210d.addIntentOptions(i, i2, i3, componentName, intentArr, intent, i4, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i5 = 0; i5 < length; i5++) {
                menuItemArr[i5] = c(menuItemArr2[i5]);
            }
        }
        return iAddIntentOptions;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return d(this.f210d.addSubMenu(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return d(this.f210d.addSubMenu(i, i2, i3, i4));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return d(this.f210d.addSubMenu(i, i2, i3, charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return d(this.f210d.addSubMenu(charSequence));
    }

    @Override // android.view.Menu
    public void clear() {
        a.e.h<a.h.f.a.b, MenuItem> hVar = this.f145b;
        if (hVar != null) {
            hVar.clear();
        }
        a.e.h<a.h.f.a.c, SubMenu> hVar2 = this.f146c;
        if (hVar2 != null) {
            hVar2.clear();
        }
        this.f210d.clear();
    }

    @Override // android.view.Menu
    public void close() {
        this.f210d.close();
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        return c(this.f210d.findItem(i));
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        return c(this.f210d.getItem(i));
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        return this.f210d.hasVisibleItems();
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return this.f210d.isShortcutKey(i, keyEvent);
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i, int i2) {
        return this.f210d.performIdentifierAction(i, i2);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return this.f210d.performShortcut(i, keyEvent, i2);
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
        if (this.f145b != null) {
            int i2 = 0;
            while (true) {
                a.e.h<a.h.f.a.b, MenuItem> hVar = this.f145b;
                if (i2 >= hVar.f490c) {
                    break;
                }
                if (hVar.h(i2).getGroupId() == i) {
                    this.f145b.i(i2);
                    i2--;
                }
                i2++;
            }
        }
        this.f210d.removeGroup(i);
    }

    @Override // android.view.Menu
    public void removeItem(int i) {
        if (this.f145b != null) {
            int i2 = 0;
            while (true) {
                a.e.h<a.h.f.a.b, MenuItem> hVar = this.f145b;
                if (i2 >= hVar.f490c) {
                    break;
                }
                if (hVar.h(i2).getItemId() == i) {
                    this.f145b.i(i2);
                    break;
                }
                i2++;
            }
        }
        this.f210d.removeItem(i);
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z, boolean z2) {
        this.f210d.setGroupCheckable(i, z, z2);
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i, boolean z) {
        this.f210d.setGroupEnabled(i, z);
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z) {
        this.f210d.setGroupVisible(i, z);
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.f210d.setQwertyMode(z);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f210d.size();
    }
}
