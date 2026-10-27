package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import defpackage.bz;
import defpackage.ow2;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MenuBuilder implements ow2 {
    public static final int[] y = {1, 4, 5, 3, 2, 0};
    public final Context a;
    public final Resources b;
    public boolean c;
    public boolean d;
    public Callback e;
    public final ArrayList<MenuItemImpl> f;
    public final ArrayList<MenuItemImpl> g;
    public boolean h;
    public final ArrayList<MenuItemImpl> i;
    public final ArrayList<MenuItemImpl> j;
    public boolean k;
    public CharSequence m;
    public Drawable n;
    public View o;
    public MenuItemImpl v;
    public boolean x;
    public int l = 0;
    public boolean p = false;
    public boolean q = false;
    public boolean r = false;
    public boolean s = false;
    public final ArrayList<MenuItemImpl> t = new ArrayList<>();
    public final CopyOnWriteArrayList<WeakReference<MenuPresenter>> u = new CopyOnWriteArrayList<>();
    public boolean w = false;

    public interface Callback {
        boolean a(MenuBuilder menuBuilder, MenuItem menuItem);

        void b(MenuBuilder menuBuilder);
    }

    public interface ItemInvoker {
        boolean a(MenuItemImpl menuItemImpl);
    }

    public MenuBuilder(Context context) {
        boolean z = false;
        this.a = context;
        Resources resources = context.getResources();
        this.b = resources;
        this.f = new ArrayList<>();
        this.g = new ArrayList<>();
        this.h = true;
        this.i = new ArrayList<>();
        this.j = new ArrayList<>();
        this.k = true;
        if (resources.getConfiguration().keyboard != 1 && ViewConfiguration.get(context).shouldShowMenuShortcutsWhenKeyboardPresent()) {
            z = true;
        }
        this.d = z;
    }

    public MenuItemImpl a(int i, int i2, int i3, CharSequence charSequence) {
        int i4;
        int i5 = ((-65536) & i3) >> 16;
        if (i5 < 0 || i5 >= 6) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        int i6 = (y[i5] << 16) | (65535 & i3);
        MenuItemImpl menuItemImpl = new MenuItemImpl(this, i, i2, i3, i6, charSequence, this.l);
        ArrayList<MenuItemImpl> arrayList = this.f;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                i4 = 0;
                break;
            }
            if (arrayList.get(size).d <= i6) {
                i4 = size + 1;
                break;
            }
        }
        arrayList.add(i4, menuItemImpl);
        p(true);
        return menuItemImpl;
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int i5;
        PackageManager packageManager = this.a.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i6 = 0; i6 < size; i6++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i6);
            int i7 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i7 < 0 ? intent : intentArr[i7]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent3 = add(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i5 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i5] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public final void b(MenuPresenter menuPresenter, Context context) {
        this.u.add(new WeakReference<>(menuPresenter));
        menuPresenter.h(context, this);
        this.k = true;
    }

    public final void c(boolean z) {
        if (this.s) {
            return;
        }
        this.s = true;
        CopyOnWriteArrayList<WeakReference<MenuPresenter>> copyOnWriteArrayList = this.u;
        for (WeakReference<MenuPresenter> weakReference : copyOnWriteArrayList) {
            MenuPresenter menuPresenter = weakReference.get();
            if (menuPresenter == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                menuPresenter.c(this, z);
            }
        }
        this.s = false;
    }

    @Override // android.view.Menu
    public final void clear() {
        MenuItemImpl menuItemImpl = this.v;
        if (menuItemImpl != null) {
            d(menuItemImpl);
        }
        this.f.clear();
        p(true);
    }

    public final void clearHeader() {
        this.n = null;
        this.m = null;
        this.o = null;
        p(false);
    }

    @Override // android.view.Menu
    public final void close() {
        c(true);
    }

    public boolean d(MenuItemImpl menuItemImpl) {
        CopyOnWriteArrayList<WeakReference<MenuPresenter>> copyOnWriteArrayList = this.u;
        boolean zG = false;
        if (!copyOnWriteArrayList.isEmpty() && this.v == menuItemImpl) {
            y();
            for (WeakReference<MenuPresenter> weakReference : copyOnWriteArrayList) {
                MenuPresenter menuPresenter = weakReference.get();
                if (menuPresenter != null) {
                    zG = menuPresenter.g(menuItemImpl);
                    if (zG) {
                        break;
                    }
                } else {
                    copyOnWriteArrayList.remove(weakReference);
                }
            }
            x();
            if (zG) {
                this.v = null;
            }
        }
        return zG;
    }

    public boolean e(MenuBuilder menuBuilder, MenuItem menuItem) {
        Callback callback = this.e;
        return callback != null && callback.a(menuBuilder, menuItem);
    }

    public boolean f(MenuItemImpl menuItemImpl) {
        CopyOnWriteArrayList<WeakReference<MenuPresenter>> copyOnWriteArrayList = this.u;
        boolean zM = false;
        if (copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        y();
        for (WeakReference<MenuPresenter> weakReference : copyOnWriteArrayList) {
            MenuPresenter menuPresenter = weakReference.get();
            if (menuPresenter != null) {
                zM = menuPresenter.m(menuItemImpl);
                if (zM) {
                    break;
                }
            } else {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
        x();
        if (zM) {
            this.v = menuItemImpl;
        }
        return zM;
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = this.f.get(i2);
            if (menuItemImpl.a == i) {
                return menuItemImpl;
            }
            if (menuItemImpl.hasSubMenu() && (menuItemFindItem = menuItemImpl.o.findItem(i)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public final MenuItemImpl g(int i, KeyEvent keyEvent) {
        ArrayList<MenuItemImpl> arrayList = this.t;
        arrayList.clear();
        h(arrayList, i, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return arrayList.get(0);
        }
        boolean zN = n();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = arrayList.get(i2);
            char c = zN ? menuItemImpl.j : menuItemImpl.h;
            char[] cArr = keyData.meta;
            if ((c == cArr[0] && (metaState & 2) == 0) || ((c == cArr[2] && (metaState & 2) != 0) || (zN && c == '\b' && i == 67))) {
                return menuItemImpl;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i) {
        return this.f.get(i);
    }

    public final void h(ArrayList arrayList, int i, KeyEvent keyEvent) {
        boolean zN = n();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            ArrayList<MenuItemImpl> arrayList2 = this.f;
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                MenuItemImpl menuItemImpl = arrayList2.get(i2);
                if (menuItemImpl.hasSubMenu()) {
                    menuItemImpl.o.h(arrayList, i, keyEvent);
                }
                char c = zN ? menuItemImpl.j : menuItemImpl.h;
                if (((modifiers & 69647) == ((zN ? menuItemImpl.k : menuItemImpl.i) & 69647)) && c != 0) {
                    char[] cArr = keyData.meta;
                    if ((c == cArr[0] || c == cArr[2] || (zN && c == '\b' && i == 67)) && menuItemImpl.isEnabled()) {
                        arrayList.add(menuItemImpl);
                    }
                }
            }
        }
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        if (this.x) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.f.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public final void i() {
        ArrayList<MenuItemImpl> arrayListL = l();
        if (this.k) {
            CopyOnWriteArrayList<WeakReference<MenuPresenter>> copyOnWriteArrayList = this.u;
            boolean zE = false;
            for (WeakReference<MenuPresenter> weakReference : copyOnWriteArrayList) {
                MenuPresenter menuPresenter = weakReference.get();
                if (menuPresenter == null) {
                    copyOnWriteArrayList.remove(weakReference);
                } else {
                    zE |= menuPresenter.e();
                }
            }
            ArrayList<MenuItemImpl> arrayList = this.i;
            ArrayList<MenuItemImpl> arrayList2 = this.j;
            if (zE) {
                arrayList.clear();
                arrayList2.clear();
                int size = arrayListL.size();
                for (int i = 0; i < size; i++) {
                    MenuItemImpl menuItemImpl = arrayListL.get(i);
                    if ((menuItemImpl.x & 32) == 32) {
                        arrayList.add(menuItemImpl);
                    } else {
                        arrayList2.add(menuItemImpl);
                    }
                }
            } else {
                arrayList.clear();
                arrayList2.clear();
                arrayList2.addAll(l());
            }
            this.k = false;
        }
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return g(i, keyEvent) != null;
    }

    public String j() {
        return "android:menu:actionviewstates";
    }

    public final ArrayList<MenuItemImpl> l() {
        boolean z = this.h;
        ArrayList<MenuItemImpl> arrayList = this.g;
        if (!z) {
            return arrayList;
        }
        arrayList.clear();
        ArrayList<MenuItemImpl> arrayList2 = this.f;
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            MenuItemImpl menuItemImpl = arrayList2.get(i);
            if (menuItemImpl.isVisible()) {
                arrayList.add(menuItemImpl);
            }
        }
        this.h = false;
        this.k = true;
        return arrayList;
    }

    public boolean m() {
        return this.w;
    }

    public boolean n() {
        return this.c;
    }

    public boolean o() {
        return this.d;
    }

    public void p(boolean z) {
        if (this.p) {
            this.q = true;
            if (z) {
                this.r = true;
                return;
            }
            return;
        }
        if (z) {
            this.h = true;
            this.k = true;
        }
        CopyOnWriteArrayList<WeakReference<MenuPresenter>> copyOnWriteArrayList = this.u;
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        y();
        for (WeakReference<MenuPresenter> weakReference : copyOnWriteArrayList) {
            MenuPresenter menuPresenter = weakReference.get();
            if (menuPresenter == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                menuPresenter.d(z);
            }
        }
        x();
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i, int i2) {
        return q(findItem(i), null, i2);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        MenuItemImpl menuItemImplG = g(i, keyEvent);
        boolean zQ = menuItemImplG != null ? q(menuItemImplG, null, i2) : false;
        if ((i2 & 2) != 0) {
            c(true);
        }
        return zQ;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean q(android.view.MenuItem r7, androidx.appcompat.view.menu.MenuPresenter r8, int r9) {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.MenuBuilder.q(android.view.MenuItem, androidx.appcompat.view.menu.MenuPresenter, int):boolean");
    }

    public final void r(MenuPresenter menuPresenter) {
        CopyOnWriteArrayList<WeakReference<MenuPresenter>> copyOnWriteArrayList = this.u;
        for (WeakReference<MenuPresenter> weakReference : copyOnWriteArrayList) {
            MenuPresenter menuPresenter2 = weakReference.get();
            if (menuPresenter2 == null || menuPresenter2 == menuPresenter) {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
    }

    @Override // android.view.Menu
    public final void removeGroup(int i) {
        ArrayList<MenuItemImpl> arrayList;
        int size = size();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            arrayList = this.f;
            if (i3 >= size) {
                i3 = -1;
                break;
            } else if (arrayList.get(i3).b == i) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 >= 0) {
            int size2 = arrayList.size() - i3;
            while (true) {
                int i4 = i2 + 1;
                if (i2 >= size2 || arrayList.get(i3).b != i) {
                    break;
                }
                if (i3 >= 0 && i3 < arrayList.size()) {
                    arrayList.remove(i3);
                }
                i2 = i4;
            }
            p(true);
        }
    }

    @Override // android.view.Menu
    public final void removeItem(int i) {
        ArrayList<MenuItemImpl> arrayList;
        int size = size();
        int i2 = 0;
        while (true) {
            arrayList = this.f;
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (arrayList.get(i2).a == i) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 < 0 || i2 >= arrayList.size()) {
            return;
        }
        arrayList.remove(i2);
        p(true);
    }

    public final void s(Bundle bundle) {
        MenuItem menuItemFindItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(j());
        int size = size();
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((SubMenuBuilder) item.getSubMenu()).s(bundle);
            }
        }
        int i2 = bundle.getInt("android:menu:expandedactionview");
        if (i2 <= 0 || (menuItemFindItem = findItem(i2)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i, boolean z, boolean z2) {
        ArrayList<MenuItemImpl> arrayList = this.f;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = arrayList.get(i2);
            if (menuItemImpl.b == i) {
                menuItemImpl.f(z2);
                menuItemImpl.setCheckable(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.w = z;
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i, boolean z) {
        ArrayList<MenuItemImpl> arrayList = this.f;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = arrayList.get(i2);
            if (menuItemImpl.b == i) {
                menuItemImpl.setEnabled(z);
            }
        }
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i, boolean z) {
        ArrayList<MenuItemImpl> arrayList = this.f;
        int size = arrayList.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = arrayList.get(i2);
            if (menuItemImpl.b == i) {
                int i3 = menuItemImpl.x;
                int i4 = (i3 & (-9)) | (z ? 0 : 8);
                menuItemImpl.x = i4;
                if (i3 != i4) {
                    z2 = true;
                }
            }
        }
        if (z2) {
            p(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.c = z;
        p(false);
    }

    @Override // android.view.Menu
    public final int size() {
        return this.f.size();
    }

    public final void t(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray != null) {
            CopyOnWriteArrayList<WeakReference<MenuPresenter>> copyOnWriteArrayList = this.u;
            if (copyOnWriteArrayList.isEmpty()) {
                return;
            }
            for (WeakReference<MenuPresenter> weakReference : copyOnWriteArrayList) {
                MenuPresenter menuPresenter = weakReference.get();
                if (menuPresenter == null) {
                    copyOnWriteArrayList.remove(weakReference);
                } else {
                    int id = menuPresenter.getId();
                    if (id > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(id)) != null) {
                        menuPresenter.i(parcelable);
                    }
                }
            }
        }
    }

    public final void u(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((SubMenuBuilder) item.getSubMenu()).u(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(j(), sparseArray);
        }
    }

    public final void v(Bundle bundle) {
        Parcelable parcelableL;
        CopyOnWriteArrayList<WeakReference<MenuPresenter>> copyOnWriteArrayList = this.u;
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        for (WeakReference<MenuPresenter> weakReference : copyOnWriteArrayList) {
            MenuPresenter menuPresenter = weakReference.get();
            if (menuPresenter == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                int id = menuPresenter.getId();
                if (id > 0 && (parcelableL = menuPresenter.l()) != null) {
                    sparseArray.put(id, parcelableL);
                }
            }
        }
        bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
    }

    public final void w(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        if (view != null) {
            this.o = view;
            this.m = null;
            this.n = null;
        } else {
            if (i > 0) {
                this.m = this.b.getText(i);
            } else if (charSequence != null) {
                this.m = charSequence;
            }
            if (i2 > 0) {
                Object obj = bz.a;
                this.n = this.a.getDrawable(i2);
            } else if (drawable != null) {
                this.n = drawable;
            }
            this.o = null;
        }
        p(false);
    }

    public final void x() {
        this.p = false;
        if (this.q) {
            this.q = false;
            p(this.r);
        }
    }

    public final void y() {
        if (this.p) {
            return;
        }
        this.p = true;
        this.q = false;
        this.r = false;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i) {
        return a(0, 0, 0, this.b.getString(i));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.b.getString(i));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        MenuItemImpl menuItemImplA = a(i, i2, i3, charSequence);
        SubMenuBuilder subMenuBuilder = new SubMenuBuilder(this.a, this, menuItemImplA);
        menuItemImplA.o = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(menuItemImplA.e);
        return subMenuBuilder;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, this.b.getString(i4));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.b.getString(i4));
    }

    public MenuBuilder k() {
        return this;
    }
}
