package defpackage;

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
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: MenuBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class p1 implements e8 {
    public static final int[] a = {1, 4, 5, 3, 2, 0};
    public boolean A;
    public final Context b;
    public final Resources c;
    public boolean d;
    public boolean e;
    public a f;
    public ContextMenu.ContextMenuInfo n;
    public CharSequence o;
    public Drawable p;
    public View q;
    public r1 y;
    public int m = 0;
    public boolean r = false;
    public boolean s = false;
    public boolean t = false;
    public boolean u = false;
    public boolean v = false;
    public ArrayList<r1> w = new ArrayList<>();
    public CopyOnWriteArrayList<WeakReference<v1>> x = new CopyOnWriteArrayList<>();
    public boolean z = false;
    public ArrayList<r1> g = new ArrayList<>();
    public ArrayList<r1> h = new ArrayList<>();
    public boolean i = true;
    public ArrayList<r1> j = new ArrayList<>();
    public ArrayList<r1> k = new ArrayList<>();
    public boolean l = true;

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface a {
        boolean a(p1 p1Var, MenuItem menuItem);

        void b(p1 p1Var);
    }

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface b {
        boolean a(r1 r1Var);
    }

    public p1(Context context) {
        this.b = context;
        this.c = context.getResources();
        f0(true);
    }

    public static int D(int i) {
        int i2 = ((-65536) & i) >> 16;
        if (i2 >= 0) {
            int[] iArr = a;
            if (i2 < iArr.length) {
                return (i & 65535) | (iArr[i2] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    public static int p(ArrayList<r1> arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).f() <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    public View A() {
        return this.q;
    }

    public ArrayList<r1> B() {
        t();
        return this.k;
    }

    public boolean C() {
        return this.u;
    }

    public Resources E() {
        return this.c;
    }

    public p1 F() {
        return this;
    }

    public ArrayList<r1> G() {
        if (!this.i) {
            return this.h;
        }
        this.h.clear();
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            r1 r1Var = this.g.get(i);
            if (r1Var.isVisible()) {
                this.h.add(r1Var);
            }
        }
        this.i = false;
        this.l = true;
        return this.h;
    }

    public boolean H() {
        return this.z;
    }

    public boolean I() {
        return this.d;
    }

    public boolean J() {
        return this.e;
    }

    public void K(r1 r1Var) {
        this.l = true;
        M(true);
    }

    public void L(r1 r1Var) {
        this.i = true;
        M(true);
    }

    public void M(boolean z) {
        if (this.r) {
            this.s = true;
            if (z) {
                this.t = true;
                return;
            }
            return;
        }
        if (z) {
            this.i = true;
            this.l = true;
        }
        i(z);
    }

    public boolean N(MenuItem menuItem, int i) {
        return O(menuItem, null, i);
    }

    public boolean O(MenuItem menuItem, v1 v1Var, int i) {
        r1 r1Var = (r1) menuItem;
        if (r1Var == null || !r1Var.isEnabled()) {
            return false;
        }
        boolean zK = r1Var.k();
        b9 b9VarB = r1Var.b();
        boolean z = b9VarB != null && b9VarB.a();
        if (r1Var.j()) {
            zK |= r1Var.expandActionView();
            if (zK) {
                e(true);
            }
        } else if (r1Var.hasSubMenu() || z) {
            if ((i & 4) == 0) {
                e(false);
            }
            if (!r1Var.hasSubMenu()) {
                r1Var.w(new a2(w(), this, r1Var));
            }
            a2 a2Var = (a2) r1Var.getSubMenu();
            if (z) {
                b9VarB.f(a2Var);
            }
            zK |= l(a2Var, v1Var);
            if (!zK) {
                e(true);
            }
        } else if ((i & 1) == 0) {
            e(true);
        }
        return zK;
    }

    public final void P(int i, boolean z) {
        if (i < 0 || i >= this.g.size()) {
            return;
        }
        this.g.remove(i);
        if (z) {
            M(true);
        }
    }

    public void Q(v1 v1Var) {
        for (WeakReference<v1> weakReference : this.x) {
            v1 v1Var2 = weakReference.get();
            if (v1Var2 == null || v1Var2 == v1Var) {
                this.x.remove(weakReference);
            }
        }
    }

    public void R(Bundle bundle) {
        MenuItem menuItemFindItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(v());
        int size = size();
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((a2) item.getSubMenu()).R(bundle);
            }
        }
        int i2 = bundle.getInt("android:menu:expandedactionview");
        if (i2 <= 0 || (menuItemFindItem = findItem(i2)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    public void S(Bundle bundle) {
        j(bundle);
    }

    public void T(Bundle bundle) {
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
                ((a2) item.getSubMenu()).T(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(v(), sparseArray);
        }
    }

    public void U(Bundle bundle) {
        k(bundle);
    }

    public void V(a aVar) {
        this.f = aVar;
    }

    public p1 W(int i) {
        this.m = i;
        return this;
    }

    public void X(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.g.size();
        h0();
        for (int i = 0; i < size; i++) {
            r1 r1Var = this.g.get(i);
            if (r1Var.getGroupId() == groupId && r1Var.m() && r1Var.isCheckable()) {
                r1Var.r(r1Var == menuItem);
            }
        }
        g0();
    }

    public p1 Y(int i) {
        a0(0, null, i, null, null);
        return this;
    }

    public p1 Z(Drawable drawable) {
        a0(0, null, 0, drawable, null);
        return this;
    }

    public MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        int iD = D(i3);
        r1 r1VarG = g(i, i2, i3, iD, charSequence, this.m);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.n;
        if (contextMenuInfo != null) {
            r1VarG.u(contextMenuInfo);
        }
        ArrayList<r1> arrayList = this.g;
        arrayList.add(p(arrayList, iD), r1VarG);
        M(true);
        return r1VarG;
    }

    public final void a0(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources resourcesE = E();
        if (view != null) {
            this.q = view;
            this.o = null;
            this.p = null;
        } else {
            if (i > 0) {
                this.o = resourcesE.getText(i);
            } else if (charSequence != null) {
                this.o = charSequence;
            }
            if (i2 > 0) {
                this.p = e7.d(w(), i2);
            } else if (drawable != null) {
                this.p = drawable;
            }
            this.q = null;
        }
        M(false);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int i5;
        PackageManager packageManager = this.b.getPackageManager();
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
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(v1 v1Var) {
        c(v1Var, this.b);
    }

    public p1 b0(int i) {
        a0(i, null, 0, null, null);
        return this;
    }

    public void c(v1 v1Var, Context context) {
        this.x.add(new WeakReference<>(v1Var));
        v1Var.f(context, this);
        this.l = true;
    }

    public p1 c0(CharSequence charSequence) {
        a0(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.Menu
    public void clear() {
        r1 r1Var = this.y;
        if (r1Var != null) {
            f(r1Var);
        }
        this.g.clear();
        M(true);
    }

    public void clearHeader() {
        this.p = null;
        this.o = null;
        this.q = null;
        M(false);
    }

    @Override // android.view.Menu
    public void close() {
        e(true);
    }

    public void d() {
        a aVar = this.f;
        if (aVar != null) {
            aVar.b(this);
        }
    }

    public p1 d0(View view) {
        a0(0, null, 0, null, view);
        return this;
    }

    public final void e(boolean z) {
        if (this.v) {
            return;
        }
        this.v = true;
        for (WeakReference<v1> weakReference : this.x) {
            v1 v1Var = weakReference.get();
            if (v1Var == null) {
                this.x.remove(weakReference);
            } else {
                v1Var.a(this, z);
            }
        }
        this.v = false;
    }

    public void e0(boolean z) {
        this.A = z;
    }

    public boolean f(r1 r1Var) {
        boolean zI = false;
        if (!this.x.isEmpty() && this.y == r1Var) {
            h0();
            for (WeakReference<v1> weakReference : this.x) {
                v1 v1Var = weakReference.get();
                if (v1Var != null) {
                    zI = v1Var.i(this, r1Var);
                    if (zI) {
                        break;
                    }
                } else {
                    this.x.remove(weakReference);
                }
            }
            g0();
            if (zI) {
                this.y = null;
            }
        }
        return zI;
    }

    public final void f0(boolean z) {
        this.e = z && this.c.getConfiguration().keyboard != 1 && u9.e(ViewConfiguration.get(this.b), this.b);
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            r1 r1Var = this.g.get(i2);
            if (r1Var.getItemId() == i) {
                return r1Var;
            }
            if (r1Var.hasSubMenu() && (menuItemFindItem = r1Var.getSubMenu().findItem(i)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public final r1 g(int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        return new r1(this, i, i2, i3, i4, charSequence, i5);
    }

    public void g0() {
        this.r = false;
        if (this.s) {
            this.s = false;
            M(this.t);
        }
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        return this.g.get(i);
    }

    public boolean h(p1 p1Var, MenuItem menuItem) {
        a aVar = this.f;
        return aVar != null && aVar.a(p1Var, menuItem);
    }

    public void h0() {
        if (this.r) {
            return;
        }
        this.r = true;
        this.s = false;
        this.t = false;
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.A) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.g.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public final void i(boolean z) {
        if (this.x.isEmpty()) {
            return;
        }
        h0();
        for (WeakReference<v1> weakReference : this.x) {
            v1 v1Var = weakReference.get();
            if (v1Var == null) {
                this.x.remove(weakReference);
            } else {
                v1Var.n(z);
            }
        }
        g0();
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return r(i, keyEvent) != null;
    }

    public final void j(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray == null || this.x.isEmpty()) {
            return;
        }
        for (WeakReference<v1> weakReference : this.x) {
            v1 v1Var = weakReference.get();
            if (v1Var == null) {
                this.x.remove(weakReference);
            } else {
                int iC = v1Var.c();
                if (iC > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(iC)) != null) {
                    v1Var.h(parcelable);
                }
            }
        }
    }

    public final void k(Bundle bundle) {
        Parcelable parcelableE;
        if (this.x.isEmpty()) {
            return;
        }
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        for (WeakReference<v1> weakReference : this.x) {
            v1 v1Var = weakReference.get();
            if (v1Var == null) {
                this.x.remove(weakReference);
            } else {
                int iC = v1Var.c();
                if (iC > 0 && (parcelableE = v1Var.e()) != null) {
                    sparseArray.put(iC, parcelableE);
                }
            }
        }
        bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
    }

    public final boolean l(a2 a2Var, v1 v1Var) {
        if (this.x.isEmpty()) {
            return false;
        }
        boolean zM = v1Var != null ? v1Var.m(a2Var) : false;
        for (WeakReference<v1> weakReference : this.x) {
            v1 v1Var2 = weakReference.get();
            if (v1Var2 == null) {
                this.x.remove(weakReference);
            } else if (!zM) {
                zM = v1Var2.m(a2Var);
            }
        }
        return zM;
    }

    public boolean m(r1 r1Var) {
        boolean zJ = false;
        if (this.x.isEmpty()) {
            return false;
        }
        h0();
        for (WeakReference<v1> weakReference : this.x) {
            v1 v1Var = weakReference.get();
            if (v1Var != null) {
                zJ = v1Var.j(this, r1Var);
                if (zJ) {
                    break;
                }
            } else {
                this.x.remove(weakReference);
            }
        }
        g0();
        if (zJ) {
            this.y = r1Var;
        }
        return zJ;
    }

    public int n(int i) {
        return o(i, 0);
    }

    public int o(int i, int i2) {
        int size = size();
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < size) {
            if (this.g.get(i2).getGroupId() == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i, int i2) {
        return N(findItem(i), i2);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        r1 r1VarR = r(i, keyEvent);
        boolean zN = r1VarR != null ? N(r1VarR, i2) : false;
        if ((i2 & 2) != 0) {
            e(true);
        }
        return zN;
    }

    public int q(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.g.get(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public r1 r(int i, KeyEvent keyEvent) {
        ArrayList<r1> arrayList = this.w;
        arrayList.clear();
        s(arrayList, i, keyEvent);
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
        boolean zI = I();
        for (int i2 = 0; i2 < size; i2++) {
            r1 r1Var = arrayList.get(i2);
            char alphabeticShortcut = zI ? r1Var.getAlphabeticShortcut() : r1Var.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (zI && alphabeticShortcut == '\b' && i == 67))) {
                return r1Var;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
        int iN = n(i);
        if (iN >= 0) {
            int size = this.g.size() - iN;
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= size || this.g.get(iN).getGroupId() != i) {
                    break;
                }
                P(iN, false);
                i2 = i3;
            }
            M(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i) {
        P(q(i), true);
    }

    public void s(List<r1> list, int i, KeyEvent keyEvent) {
        boolean zI = I();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.g.size();
            for (int i2 = 0; i2 < size; i2++) {
                r1 r1Var = this.g.get(i2);
                if (r1Var.hasSubMenu()) {
                    ((p1) r1Var.getSubMenu()).s(list, i, keyEvent);
                }
                char alphabeticShortcut = zI ? r1Var.getAlphabeticShortcut() : r1Var.getNumericShortcut();
                if (((modifiers & 69647) == ((zI ? r1Var.getAlphabeticModifiers() : r1Var.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zI && alphabeticShortcut == '\b' && i == 67)) && r1Var.isEnabled()) {
                        list.add(r1Var);
                    }
                }
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            r1 r1Var = this.g.get(i2);
            if (r1Var.getGroupId() == i) {
                r1Var.s(z2);
                r1Var.setCheckable(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.z = z;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i, boolean z) {
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            r1 r1Var = this.g.get(i2);
            if (r1Var.getGroupId() == i) {
                r1Var.setEnabled(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z) {
        int size = this.g.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            r1 r1Var = this.g.get(i2);
            if (r1Var.getGroupId() == i && r1Var.x(z)) {
                z2 = true;
            }
        }
        if (z2) {
            M(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.d = z;
        M(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.g.size();
    }

    public void t() {
        ArrayList<r1> arrayListG = G();
        if (this.l) {
            boolean zD = false;
            for (WeakReference<v1> weakReference : this.x) {
                v1 v1Var = weakReference.get();
                if (v1Var == null) {
                    this.x.remove(weakReference);
                } else {
                    zD |= v1Var.d();
                }
            }
            if (zD) {
                this.j.clear();
                this.k.clear();
                int size = arrayListG.size();
                for (int i = 0; i < size; i++) {
                    r1 r1Var = arrayListG.get(i);
                    if (r1Var.l()) {
                        this.j.add(r1Var);
                    } else {
                        this.k.add(r1Var);
                    }
                }
            } else {
                this.j.clear();
                this.k.clear();
                this.k.addAll(G());
            }
            this.l = false;
        }
    }

    public ArrayList<r1> u() {
        t();
        return this.j;
    }

    public String v() {
        return "android:menu:actionviewstates";
    }

    public Context w() {
        return this.b;
    }

    public r1 x() {
        return this.y;
    }

    public Drawable y() {
        return this.p;
    }

    public CharSequence z() {
        return this.o;
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return a(0, 0, 0, this.c.getString(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.c.getString(i));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        r1 r1Var = (r1) a(i, i2, i3, charSequence);
        a2 a2Var = new a2(this.b, this, r1Var);
        r1Var.w(a2Var);
        return a2Var;
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, this.c.getString(i4));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.c.getString(i4));
    }
}
