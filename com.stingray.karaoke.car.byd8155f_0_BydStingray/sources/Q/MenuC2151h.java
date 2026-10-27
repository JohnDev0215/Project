package q;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.appcompat.widget.ActionMenuView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import l3.C1764u;
import r.InterfaceC2253l;
import r.u0;

/* JADX INFO: renamed from: q.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class MenuC2151h implements Menu {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final int[] f21048v = {1, 4, 5, 3, 2, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f21049a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Resources f21050b;
    public boolean c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C1764u f21051e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f21052f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f21053g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f21054h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f21055i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f21056j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f21057k;
    public CharSequence l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public View f21058m;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public MenuItemC2152i f21064t;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f21059n = false;
    public boolean o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f21060p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f21061q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ArrayList f21062r = new ArrayList();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final CopyOnWriteArrayList f21063s = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f21065u = false;

    public MenuC2151h(Context context) {
        boolean z6 = false;
        this.f21049a = context;
        Resources resources = context.getResources();
        this.f21050b = resources;
        this.f21052f = new ArrayList();
        this.f21053g = new ArrayList();
        this.f21054h = true;
        this.f21055i = new ArrayList();
        this.f21056j = new ArrayList();
        this.f21057k = true;
        if (resources.getConfiguration().keyboard != 1 && ViewConfiguration.get(context).shouldShowMenuShortcutsWhenKeyboardPresent()) {
            z6 = true;
        }
        this.d = z6;
    }

    public final MenuItemC2152i a(int i7, int i10, int i11, CharSequence charSequence) {
        int i12;
        int i13 = ((-65536) & i11) >> 16;
        if (i13 < 0 || i13 >= 6) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        int i14 = (f21048v[i13] << 16) | (65535 & i11);
        MenuItemC2152i menuItemC2152i = new MenuItemC2152i(this, i7, i10, i11, i14, charSequence);
        ArrayList arrayList = this.f21052f;
        int size = arrayList.size() - 1;
        while (true) {
            if (size < 0) {
                i12 = 0;
                break;
            }
            if (((MenuItemC2152i) arrayList.get(size)).d <= i14) {
                i12 = size + 1;
                break;
            }
            size--;
        }
        arrayList.add(i12, menuItemC2152i);
        o(true);
        return menuItemC2152i;
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i7, int i10, int i11, ComponentName componentName, Intent[] intentArr, Intent intent, int i12, MenuItem[] menuItemArr) {
        int i13;
        PackageManager packageManager = this.f21049a.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i12 & 1) == 0) {
            removeGroup(i7);
        }
        for (int i14 = 0; i14 < size; i14++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i14);
            int i15 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i15 < 0 ? intent : intentArr[i15]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItemC2152i menuItemC2152iA = a(i7, i10, i11, resolveInfo.loadLabel(packageManager));
            menuItemC2152iA.setIcon(resolveInfo.loadIcon(packageManager));
            menuItemC2152iA.f21071g = intent2;
            if (menuItemArr != null && (i13 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i13] = menuItemC2152iA;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public final void b(InterfaceC2157n interfaceC2157n, Context context) {
        this.f21063s.add(new WeakReference(interfaceC2157n));
        interfaceC2157n.h(context, this);
        this.f21057k = true;
    }

    public final void c(boolean z6) {
        if (this.f21061q) {
            return;
        }
        this.f21061q = true;
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f21063s;
        for (WeakReference weakReference : copyOnWriteArrayList) {
            InterfaceC2157n interfaceC2157n = (InterfaceC2157n) weakReference.get();
            if (interfaceC2157n == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                interfaceC2157n.a(this, z6);
            }
        }
        this.f21061q = false;
    }

    @Override // android.view.Menu
    public final void clear() {
        MenuItemC2152i menuItemC2152i = this.f21064t;
        if (menuItemC2152i != null) {
            d(menuItemC2152i);
        }
        this.f21052f.clear();
        o(true);
    }

    public final void clearHeader() {
        this.l = null;
        o(false);
    }

    @Override // android.view.Menu
    public final void close() {
        c(true);
    }

    public boolean d(MenuItemC2152i menuItemC2152i) {
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f21063s;
        boolean zK = false;
        if (!copyOnWriteArrayList.isEmpty() && this.f21064t == menuItemC2152i) {
            s();
            for (WeakReference weakReference : copyOnWriteArrayList) {
                InterfaceC2157n interfaceC2157n = (InterfaceC2157n) weakReference.get();
                if (interfaceC2157n != null) {
                    zK = interfaceC2157n.k(menuItemC2152i);
                    if (zK) {
                        break;
                    }
                } else {
                    copyOnWriteArrayList.remove(weakReference);
                }
            }
            r();
            if (zK) {
                this.f21064t = null;
            }
        }
        return zK;
    }

    public boolean e(MenuC2151h menuC2151h, MenuItem menuItem) {
        InterfaceC2253l interfaceC2253l;
        C1764u c1764u = this.f21051e;
        if (c1764u == null || (interfaceC2253l = ((ActionMenuView) c1764u.f18608m).f11340J) == null) {
            return false;
        }
        Iterator it = ((u0) interfaceC2253l).f21563a.f11367R.f2338a.iterator();
        if (!it.hasNext()) {
            return false;
        }
        ((X1.i) it.next()).getClass();
        throw null;
    }

    public boolean f(MenuItemC2152i menuItemC2152i) {
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f21063s;
        boolean zF = false;
        if (copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        s();
        for (WeakReference weakReference : copyOnWriteArrayList) {
            InterfaceC2157n interfaceC2157n = (InterfaceC2157n) weakReference.get();
            if (interfaceC2157n != null) {
                zF = interfaceC2157n.f(menuItemC2152i);
                if (zF) {
                    break;
                }
            } else {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
        r();
        if (zF) {
            this.f21064t = menuItemC2152i;
        }
        return zF;
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i7) {
        MenuItem menuItemFindItem;
        ArrayList arrayList = this.f21052f;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            MenuItemC2152i menuItemC2152i = (MenuItemC2152i) arrayList.get(i10);
            if (menuItemC2152i.f21067a == i7) {
                return menuItemC2152i;
            }
            if (menuItemC2152i.hasSubMenu() && (menuItemFindItem = menuItemC2152i.o.findItem(i7)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public final MenuItemC2152i g(int i7, KeyEvent keyEvent) {
        ArrayList arrayList = this.f21062r;
        arrayList.clear();
        h(arrayList, i7, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (MenuItemC2152i) arrayList.get(0);
        }
        boolean zM = m();
        for (int i10 = 0; i10 < size; i10++) {
            MenuItemC2152i menuItemC2152i = (MenuItemC2152i) arrayList.get(i10);
            char c = zM ? menuItemC2152i.f21074j : menuItemC2152i.f21072h;
            char[] cArr = keyData.meta;
            if ((c == cArr[0] && (metaState & 2) == 0) || ((c == cArr[2] && (metaState & 2) != 0) || (zM && c == '\b' && i7 == 67))) {
                return menuItemC2152i;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i7) {
        return (MenuItem) this.f21052f.get(i7);
    }

    public final void h(ArrayList arrayList, int i7, KeyEvent keyEvent) {
        boolean zM = m();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i7 == 67) {
            ArrayList arrayList2 = this.f21052f;
            int size = arrayList2.size();
            for (int i10 = 0; i10 < size; i10++) {
                MenuItemC2152i menuItemC2152i = (MenuItemC2152i) arrayList2.get(i10);
                if (menuItemC2152i.hasSubMenu()) {
                    menuItemC2152i.o.h(arrayList, i7, keyEvent);
                }
                char c = zM ? menuItemC2152i.f21074j : menuItemC2152i.f21072h;
                if ((modifiers & 69647) == ((zM ? menuItemC2152i.f21075k : menuItemC2152i.f21073i) & 69647) && c != 0) {
                    char[] cArr = keyData.meta;
                    if ((c == cArr[0] || c == cArr[2] || (zM && c == '\b' && i7 == 67)) && menuItemC2152i.isEnabled()) {
                        arrayList.add(menuItemC2152i);
                    }
                }
            }
        }
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        ArrayList arrayList = this.f21052f;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (((MenuItemC2152i) arrayList.get(i7)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public final void i() {
        ArrayList arrayListK = k();
        if (this.f21057k) {
            CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f21063s;
            boolean zG = false;
            for (WeakReference weakReference : copyOnWriteArrayList) {
                InterfaceC2157n interfaceC2157n = (InterfaceC2157n) weakReference.get();
                if (interfaceC2157n == null) {
                    copyOnWriteArrayList.remove(weakReference);
                } else {
                    zG |= interfaceC2157n.g();
                }
            }
            ArrayList arrayList = this.f21055i;
            ArrayList arrayList2 = this.f21056j;
            if (zG) {
                arrayList.clear();
                arrayList2.clear();
                int size = arrayListK.size();
                for (int i7 = 0; i7 < size; i7++) {
                    MenuItemC2152i menuItemC2152i = (MenuItemC2152i) arrayListK.get(i7);
                    if (menuItemC2152i.d()) {
                        arrayList.add(menuItemC2152i);
                    } else {
                        arrayList2.add(menuItemC2152i);
                    }
                }
            } else {
                arrayList.clear();
                arrayList2.clear();
                arrayList2.addAll(k());
            }
            this.f21057k = false;
        }
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i7, KeyEvent keyEvent) {
        return g(i7, keyEvent) != null;
    }

    public MenuC2151h j() {
        return this;
    }

    public final ArrayList k() {
        boolean z6 = this.f21054h;
        ArrayList arrayList = this.f21053g;
        if (!z6) {
            return arrayList;
        }
        arrayList.clear();
        ArrayList arrayList2 = this.f21052f;
        int size = arrayList2.size();
        for (int i7 = 0; i7 < size; i7++) {
            MenuItemC2152i menuItemC2152i = (MenuItemC2152i) arrayList2.get(i7);
            if (menuItemC2152i.isVisible()) {
                arrayList.add(menuItemC2152i);
            }
        }
        this.f21054h = false;
        this.f21057k = true;
        return arrayList;
    }

    public boolean l() {
        return this.f21065u;
    }

    public boolean m() {
        return this.c;
    }

    public boolean n() {
        return this.d;
    }

    public final void o(boolean z6) {
        if (this.f21059n) {
            this.o = true;
            if (z6) {
                this.f21060p = true;
                return;
            }
            return;
        }
        if (z6) {
            this.f21054h = true;
            this.f21057k = true;
        }
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f21063s;
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        s();
        for (WeakReference weakReference : copyOnWriteArrayList) {
            InterfaceC2157n interfaceC2157n = (InterfaceC2157n) weakReference.get();
            if (interfaceC2157n == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                interfaceC2157n.d();
            }
        }
        r();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean p(android.view.MenuItem r6, q.AbstractC2153j r7, int r8) {
        /*
            r5 = this;
            q.i r6 = (q.MenuItemC2152i) r6
            r0 = 0
            if (r6 == 0) goto Lab
            boolean r1 = r6.isEnabled()
            if (r1 != 0) goto Ld
            goto Lab
        Ld:
            android.view.MenuItem$OnMenuItemClickListener r1 = r6.f21078p
            r2 = 1
            if (r1 == 0) goto L1a
            boolean r1 = r1.onMenuItemClick(r6)
            if (r1 == 0) goto L1a
        L18:
            r1 = r2
            goto L36
        L1a:
            q.h r1 = r6.f21077n
            boolean r3 = r1.e(r1, r6)
            if (r3 == 0) goto L23
            goto L18
        L23:
            android.content.Intent r3 = r6.f21071g
            if (r3 == 0) goto L35
            android.content.Context r1 = r1.f21049a     // Catch: android.content.ActivityNotFoundException -> L2d
            r1.startActivity(r3)     // Catch: android.content.ActivityNotFoundException -> L2d
            goto L18
        L2d:
            r1 = move-exception
            java.lang.String r3 = "MenuItemImpl"
            java.lang.String r4 = "Can't find activity to handle intent; ignoring"
            h6.n.i(r3, r4, r1)
        L35:
            r1 = r0
        L36:
            boolean r3 = r6.c()
            if (r3 == 0) goto L47
            boolean r6 = r6.expandActionView()
            r1 = r1 | r6
            if (r1 == 0) goto Laa
            r5.c(r2)
            goto Laa
        L47:
            boolean r3 = r6.hasSubMenu()
            if (r3 != 0) goto L55
            r6 = r8 & 1
            if (r6 != 0) goto Laa
            r5.c(r2)
            goto Laa
        L55:
            r8 = r8 & 4
            if (r8 != 0) goto L5c
            r5.c(r0)
        L5c:
            boolean r8 = r6.hasSubMenu()
            if (r8 != 0) goto L70
            q.r r8 = new q.r
            android.content.Context r3 = r5.f21049a
            r8.<init>(r3, r5, r6)
            r6.o = r8
            java.lang.CharSequence r3 = r6.f21069e
            r8.setHeaderTitle(r3)
        L70:
            q.r r6 = r6.o
            java.util.concurrent.CopyOnWriteArrayList r8 = r5.f21063s
            boolean r3 = r8.isEmpty()
            if (r3 == 0) goto L7b
            goto La4
        L7b:
            if (r7 == 0) goto L81
            boolean r0 = r7.b(r6)
        L81:
            java.util.Iterator r7 = r8.iterator()
        L85:
            boolean r3 = r7.hasNext()
            if (r3 == 0) goto La4
            java.lang.Object r3 = r7.next()
            java.lang.ref.WeakReference r3 = (java.lang.ref.WeakReference) r3
            java.lang.Object r4 = r3.get()
            q.n r4 = (q.InterfaceC2157n) r4
            if (r4 != 0) goto L9d
            r8.remove(r3)
            goto L85
        L9d:
            if (r0 != 0) goto L85
            boolean r0 = r4.b(r6)
            goto L85
        La4:
            r1 = r1 | r0
            if (r1 != 0) goto Laa
            r5.c(r2)
        Laa:
            return r1
        Lab:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: q.MenuC2151h.p(android.view.MenuItem, q.j, int):boolean");
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i7, int i10) {
        return p(findItem(i7), null, i10);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i7, KeyEvent keyEvent, int i10) {
        MenuItemC2152i menuItemC2152iG = g(i7, keyEvent);
        boolean zP = menuItemC2152iG != null ? p(menuItemC2152iG, null, i10) : false;
        if ((i10 & 2) != 0) {
            c(true);
        }
        return zP;
    }

    public final void q(int i7, CharSequence charSequence, int i10, View view) {
        if (view != null) {
            this.f21058m = view;
            this.l = null;
        } else {
            if (i7 > 0) {
                this.l = this.f21050b.getText(i7);
            } else if (charSequence != null) {
                this.l = charSequence;
            }
            if (i10 > 0) {
                this.f21049a.getDrawable(i10);
            }
        }
        o(false);
    }

    public final void r() {
        this.f21059n = false;
        if (this.o) {
            this.o = false;
            o(this.f21060p);
        }
    }

    @Override // android.view.Menu
    public final void removeGroup(int i7) {
        ArrayList arrayList = this.f21052f;
        int size = arrayList.size();
        int i10 = 0;
        int i11 = 0;
        while (true) {
            if (i11 >= size) {
                i11 = -1;
                break;
            } else if (((MenuItemC2152i) arrayList.get(i11)).f21068b == i7) {
                break;
            } else {
                i11++;
            }
        }
        if (i11 >= 0) {
            int size2 = arrayList.size() - i11;
            while (true) {
                int i12 = i10 + 1;
                if (i10 >= size2 || ((MenuItemC2152i) arrayList.get(i11)).f21068b != i7) {
                    break;
                }
                if (i11 >= 0) {
                    ArrayList arrayList2 = this.f21052f;
                    if (i11 < arrayList2.size()) {
                        arrayList2.remove(i11);
                    }
                }
                i10 = i12;
            }
            o(true);
        }
    }

    @Override // android.view.Menu
    public final void removeItem(int i7) {
        ArrayList arrayList = this.f21052f;
        int size = arrayList.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                i10 = -1;
                break;
            } else if (((MenuItemC2152i) arrayList.get(i10)).f21067a == i7) {
                break;
            } else {
                i10++;
            }
        }
        if (i10 >= 0) {
            ArrayList arrayList2 = this.f21052f;
            if (i10 >= arrayList2.size()) {
                return;
            }
            arrayList2.remove(i10);
            o(true);
        }
    }

    public final void s() {
        if (this.f21059n) {
            return;
        }
        this.f21059n = true;
        this.o = false;
        this.f21060p = false;
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i7, boolean z6, boolean z10) {
        ArrayList arrayList = this.f21052f;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            MenuItemC2152i menuItemC2152i = (MenuItemC2152i) arrayList.get(i10);
            if (menuItemC2152i.f21068b == i7) {
                menuItemC2152i.f21086x = (menuItemC2152i.f21086x & (-5)) | (z10 ? 4 : 0);
                menuItemC2152i.setCheckable(z6);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z6) {
        this.f21065u = z6;
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i7, boolean z6) {
        ArrayList arrayList = this.f21052f;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            MenuItemC2152i menuItemC2152i = (MenuItemC2152i) arrayList.get(i10);
            if (menuItemC2152i.f21068b == i7) {
                menuItemC2152i.setEnabled(z6);
            }
        }
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i7, boolean z6) {
        ArrayList arrayList = this.f21052f;
        int size = arrayList.size();
        boolean z10 = false;
        for (int i10 = 0; i10 < size; i10++) {
            MenuItemC2152i menuItemC2152i = (MenuItemC2152i) arrayList.get(i10);
            if (menuItemC2152i.f21068b == i7) {
                int i11 = menuItemC2152i.f21086x;
                int i12 = (i11 & (-9)) | (z6 ? 0 : 8);
                menuItemC2152i.f21086x = i12;
                if (i11 != i12) {
                    z10 = true;
                }
            }
        }
        if (z10) {
            o(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z6) {
        this.c = z6;
        o(false);
    }

    @Override // android.view.Menu
    public final int size() {
        return this.f21052f.size();
    }

    @Override // android.view.Menu
    public final MenuItem add(int i7) {
        return a(0, 0, 0, this.f21050b.getString(i7));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i7) {
        return addSubMenu(0, 0, 0, this.f21050b.getString(i7));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i7, int i10, int i11, CharSequence charSequence) {
        return a(i7, i10, i11, charSequence);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i7, int i10, int i11, CharSequence charSequence) {
        MenuItemC2152i menuItemC2152iA = a(i7, i10, i11, charSequence);
        SubMenuC2161r subMenuC2161r = new SubMenuC2161r(this.f21049a, this, menuItemC2152iA);
        menuItemC2152iA.o = subMenuC2161r;
        subMenuC2161r.setHeaderTitle(menuItemC2152iA.f21069e);
        return subMenuC2161r;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i7, int i10, int i11, int i12) {
        return a(i7, i10, i11, this.f21050b.getString(i12));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i7, int i10, int i11, int i12) {
        return addSubMenu(i7, i10, i11, this.f21050b.getString(i12));
    }
}
