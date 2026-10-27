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
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.view.s;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class f implements androidx.core.b.a.a {
    private static final int[] A = {1, 4, 5, 3, 2, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f222a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f223b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f224c;
    private boolean d;
    private a e;
    private ContextMenu.ContextMenuInfo m;
    CharSequence n;
    Drawable o;
    View p;
    private h x;
    private boolean z;
    private int l = 0;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private ArrayList<h> v = new ArrayList<>();
    private CopyOnWriteArrayList<WeakReference<l>> w = new CopyOnWriteArrayList<>();
    private boolean y = false;
    private ArrayList<h> f = new ArrayList<>();
    private ArrayList<h> g = new ArrayList<>();
    private boolean h = true;
    private ArrayList<h> i = new ArrayList<>();
    private ArrayList<h> j = new ArrayList<>();
    private boolean k = true;

    /* JADX INFO: compiled from: caradapter-bug */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public interface a {
        boolean a(@NonNull f fVar, @NonNull MenuItem menuItem);

        void b(@NonNull f fVar);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public interface b {
        boolean a(h hVar);
    }

    public f(Context context) {
        this.f222a = context;
        this.f223b = context.getResources();
        b0(true);
    }

    private static int B(int i) {
        int i2 = ((-65536) & i) >> 16;
        if (i2 >= 0) {
            int[] iArr = A;
            if (i2 < iArr.length) {
                return (i & 65535) | (iArr[i2] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private void N(int i, boolean z) {
        if (i < 0 || i >= this.f.size()) {
            return;
        }
        this.f.remove(i);
        if (z) {
            K(true);
        }
    }

    private void W(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources resourcesC = C();
        if (view != null) {
            this.p = view;
            this.n = null;
            this.o = null;
        } else {
            if (i > 0) {
                this.n = resourcesC.getText(i);
            } else if (charSequence != null) {
                this.n = charSequence;
            }
            if (i2 > 0) {
                this.o = androidx.core.content.a.d(u(), i2);
            } else if (drawable != null) {
                this.o = drawable;
            }
            this.p = null;
        }
        K(false);
    }

    private void b0(boolean z) {
        this.d = z && this.f223b.getConfiguration().keyboard != 1 && s.e(ViewConfiguration.get(this.f222a), this.f222a);
    }

    private h g(int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        return new h(this, i, i2, i3, i4, charSequence, i5);
    }

    private void i(boolean z) {
        if (this.w.isEmpty()) {
            return;
        }
        d0();
        for (WeakReference<l> weakReference : this.w) {
            l lVar = weakReference.get();
            if (lVar == null) {
                this.w.remove(weakReference);
            } else {
                lVar.j(z);
            }
        }
        c0();
    }

    private boolean j(q qVar, l lVar) {
        if (this.w.isEmpty()) {
            return false;
        }
        boolean zI = lVar != null ? lVar.i(qVar) : false;
        for (WeakReference<l> weakReference : this.w) {
            l lVar2 = weakReference.get();
            if (lVar2 == null) {
                this.w.remove(weakReference);
            } else if (!zI) {
                zI = lVar2.i(qVar);
            }
        }
        return zI;
    }

    private static int n(ArrayList<h> arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).e() <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    boolean A() {
        return this.t;
    }

    Resources C() {
        return this.f223b;
    }

    public f D() {
        return this;
    }

    @NonNull
    public ArrayList<h> E() {
        if (!this.h) {
            return this.g;
        }
        this.g.clear();
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            h hVar = this.f.get(i);
            if (hVar.isVisible()) {
                this.g.add(hVar);
            }
        }
        this.h = false;
        this.k = true;
        return this.g;
    }

    public boolean F() {
        return this.y;
    }

    boolean G() {
        return this.f224c;
    }

    public boolean H() {
        return this.d;
    }

    void I(h hVar) {
        this.k = true;
        K(true);
    }

    void J(h hVar) {
        this.h = true;
        K(true);
    }

    public void K(boolean z) {
        if (this.q) {
            this.r = true;
            if (z) {
                this.s = true;
                return;
            }
            return;
        }
        if (z) {
            this.h = true;
            this.k = true;
        }
        i(z);
    }

    public boolean L(MenuItem menuItem, int i) {
        return M(menuItem, null, i);
    }

    public boolean M(MenuItem menuItem, l lVar, int i) {
        h hVar = (h) menuItem;
        if (hVar == null || !hVar.isEnabled()) {
            return false;
        }
        boolean zJ = hVar.j();
        androidx.core.view.b bVarB = hVar.b();
        boolean z = bVarB != null && bVarB.b();
        if (hVar.i()) {
            zJ |= hVar.expandActionView();
            if (zJ) {
                e(true);
            }
        } else if (hVar.hasSubMenu() || z) {
            if ((i & 4) == 0) {
                e(false);
            }
            if (!hVar.hasSubMenu()) {
                hVar.v(new q(u(), this, hVar));
            }
            q qVar = (q) hVar.getSubMenu();
            if (z) {
                bVarB.g(qVar);
            }
            zJ |= j(qVar, lVar);
            if (!zJ) {
                e(true);
            }
        } else if ((i & 1) == 0) {
            e(true);
        }
        return zJ;
    }

    public void O(l lVar) {
        for (WeakReference<l> weakReference : this.w) {
            l lVar2 = weakReference.get();
            if (lVar2 == null || lVar2 == lVar) {
                this.w.remove(weakReference);
            }
        }
    }

    public void P(Bundle bundle) {
        MenuItem menuItemFindItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(t());
        int size = size();
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((q) item.getSubMenu()).P(bundle);
            }
        }
        int i2 = bundle.getInt("android:menu:expandedactionview");
        if (i2 <= 0 || (menuItemFindItem = findItem(i2)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    public void Q(Bundle bundle) {
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
                ((q) item.getSubMenu()).Q(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(t(), sparseArray);
        }
    }

    public void R(a aVar) {
        this.e = aVar;
    }

    public f S(int i) {
        this.l = i;
        return this;
    }

    void T(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f.size();
        d0();
        for (int i = 0; i < size; i++) {
            h hVar = this.f.get(i);
            if (hVar.getGroupId() == groupId && hVar.l() && hVar.isCheckable()) {
                hVar.q(hVar == menuItem);
            }
        }
        c0();
    }

    protected f U(int i) {
        W(0, null, i, null, null);
        return this;
    }

    protected f V(Drawable drawable) {
        W(0, null, 0, drawable, null);
        return this;
    }

    protected f X(int i) {
        W(i, null, 0, null, null);
        return this;
    }

    protected f Y(CharSequence charSequence) {
        W(0, charSequence, 0, null, null);
        return this;
    }

    protected f Z(View view) {
        W(0, null, 0, null, view);
        return this;
    }

    protected MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        int iB = B(i3);
        h hVarG = g(i, i2, i3, iB, charSequence, this.l);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.m;
        if (contextMenuInfo != null) {
            hVarG.t(contextMenuInfo);
        }
        ArrayList<h> arrayList = this.f;
        arrayList.add(n(arrayList, iB), hVarG);
        K(true);
        return hVarG;
    }

    public void a0(boolean z) {
        this.z = z;
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int i5;
        PackageManager packageManager = this.f222a.getPackageManager();
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

    public void b(l lVar) {
        c(lVar, this.f222a);
    }

    public void c(l lVar, Context context) {
        this.w.add(new WeakReference<>(lVar));
        lVar.d(context, this);
        this.k = true;
    }

    public void c0() {
        this.q = false;
        if (this.r) {
            this.r = false;
            K(this.s);
        }
    }

    @Override // android.view.Menu
    public void clear() {
        h hVar = this.x;
        if (hVar != null) {
            f(hVar);
        }
        this.f.clear();
        K(true);
    }

    public void clearHeader() {
        this.o = null;
        this.n = null;
        this.p = null;
        K(false);
    }

    @Override // android.view.Menu
    public void close() {
        e(true);
    }

    public void d() {
        a aVar = this.e;
        if (aVar != null) {
            aVar.b(this);
        }
    }

    public void d0() {
        if (this.q) {
            return;
        }
        this.q = true;
        this.r = false;
        this.s = false;
    }

    public final void e(boolean z) {
        if (this.u) {
            return;
        }
        this.u = true;
        for (WeakReference<l> weakReference : this.w) {
            l lVar = weakReference.get();
            if (lVar == null) {
                this.w.remove(weakReference);
            } else {
                lVar.a(this, z);
            }
        }
        this.u = false;
    }

    public boolean f(h hVar) {
        boolean zE = false;
        if (!this.w.isEmpty() && this.x == hVar) {
            d0();
            for (WeakReference<l> weakReference : this.w) {
                l lVar = weakReference.get();
                if (lVar != null) {
                    zE = lVar.e(this, hVar);
                    if (zE) {
                        break;
                    }
                } else {
                    this.w.remove(weakReference);
                }
            }
            c0();
            if (zE) {
                this.x = null;
            }
        }
        return zE;
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = this.f.get(i2);
            if (hVar.getItemId() == i) {
                return hVar;
            }
            if (hVar.hasSubMenu() && (menuItemFindItem = hVar.getSubMenu().findItem(i)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        return this.f.get(i);
    }

    boolean h(@NonNull f fVar, @NonNull MenuItem menuItem) {
        a aVar = this.e;
        return aVar != null && aVar.a(fVar, menuItem);
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.z) {
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

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return p(i, keyEvent) != null;
    }

    public boolean k(h hVar) {
        boolean zG = false;
        if (this.w.isEmpty()) {
            return false;
        }
        d0();
        for (WeakReference<l> weakReference : this.w) {
            l lVar = weakReference.get();
            if (lVar != null) {
                zG = lVar.g(this, hVar);
                if (zG) {
                    break;
                }
            } else {
                this.w.remove(weakReference);
            }
        }
        c0();
        if (zG) {
            this.x = hVar;
        }
        return zG;
    }

    public int l(int i) {
        return m(i, 0);
    }

    public int m(int i, int i2) {
        int size = size();
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < size) {
            if (this.f.get(i2).getGroupId() == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public int o(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.f.get(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    h p(int i, KeyEvent keyEvent) {
        ArrayList<h> arrayList = this.v;
        arrayList.clear();
        q(arrayList, i, keyEvent);
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
        boolean zG = G();
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = arrayList.get(i2);
            char alphabeticShortcut = zG ? hVar.getAlphabeticShortcut() : hVar.getNumericShortcut();
            if ((alphabeticShortcut == keyData.meta[0] && (metaState & 2) == 0) || ((alphabeticShortcut == keyData.meta[2] && (metaState & 2) != 0) || (zG && alphabeticShortcut == '\b' && i == 67))) {
                return hVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i, int i2) {
        return L(findItem(i), i2);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        h hVarP = p(i, keyEvent);
        boolean zL = hVarP != null ? L(hVarP, i2) : false;
        if ((i2 & 2) != 0) {
            e(true);
        }
        return zL;
    }

    void q(List<h> list, int i, KeyEvent keyEvent) {
        boolean zG = G();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.f.size();
            for (int i2 = 0; i2 < size; i2++) {
                h hVar = this.f.get(i2);
                if (hVar.hasSubMenu()) {
                    ((f) hVar.getSubMenu()).q(list, i, keyEvent);
                }
                char alphabeticShortcut = zG ? hVar.getAlphabeticShortcut() : hVar.getNumericShortcut();
                if (((modifiers & 69647) == ((zG ? hVar.getAlphabeticModifiers() : hVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zG && alphabeticShortcut == '\b' && i == 67)) && hVar.isEnabled()) {
                        list.add(hVar);
                    }
                }
            }
        }
    }

    public void r() {
        ArrayList<h> arrayListE = E();
        if (this.k) {
            boolean zC = false;
            for (WeakReference<l> weakReference : this.w) {
                l lVar = weakReference.get();
                if (lVar == null) {
                    this.w.remove(weakReference);
                } else {
                    zC |= lVar.c();
                }
            }
            if (zC) {
                this.i.clear();
                this.j.clear();
                int size = arrayListE.size();
                for (int i = 0; i < size; i++) {
                    h hVar = arrayListE.get(i);
                    if (hVar.k()) {
                        this.i.add(hVar);
                    } else {
                        this.j.add(hVar);
                    }
                }
            } else {
                this.i.clear();
                this.j.clear();
                this.j.addAll(E());
            }
            this.k = false;
        }
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
        int iL = l(i);
        if (iL >= 0) {
            int size = this.f.size() - iL;
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= size || this.f.get(iL).getGroupId() != i) {
                    break;
                }
                N(iL, false);
                i2 = i3;
            }
            K(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i) {
        N(o(i), true);
    }

    public ArrayList<h> s() {
        r();
        return this.i;
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.f.size();
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = this.f.get(i2);
            if (hVar.getGroupId() == i) {
                hVar.r(z2);
                hVar.setCheckable(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.y = z;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i, boolean z) {
        int size = this.f.size();
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = this.f.get(i2);
            if (hVar.getGroupId() == i) {
                hVar.setEnabled(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z) {
        int size = this.f.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = this.f.get(i2);
            if (hVar.getGroupId() == i && hVar.w(z)) {
                z2 = true;
            }
        }
        if (z2) {
            K(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.f224c = z;
        K(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f.size();
    }

    protected String t() {
        return "android:menu:actionviewstates";
    }

    public Context u() {
        return this.f222a;
    }

    public h v() {
        return this.x;
    }

    public Drawable w() {
        return this.o;
    }

    public CharSequence x() {
        return this.n;
    }

    public View y() {
        return this.p;
    }

    public ArrayList<h> z() {
        r();
        return this.j;
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return a(0, 0, 0, this.f223b.getString(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.f223b.getString(i));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        h hVar = (h) a(i, i2, i3, charSequence);
        q qVar = new q(this.f222a, this, hVar);
        hVar.v(qVar);
        return qVar;
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, this.f223b.getString(i4));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.f223b.getString(i4));
    }
}
