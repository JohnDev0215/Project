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
import b.g.k.u;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: MenuBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements b.g.e.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f306a = {1, 4, 5, 3, 2, 0};
    private boolean A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f307b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Resources f308c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f309d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f310e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a f311f;
    private ContextMenu.ContextMenuInfo n;
    CharSequence o;
    Drawable p;
    View q;
    private i y;
    private int m = 0;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private ArrayList<i> w = new ArrayList<>();
    private CopyOnWriteArrayList<WeakReference<m>> x = new CopyOnWriteArrayList<>();
    private boolean z = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ArrayList<i> f312g = new ArrayList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ArrayList<i> f313h = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f314i = true;
    private ArrayList<i> j = new ArrayList<>();
    private ArrayList<i> k = new ArrayList<>();
    private boolean l = true;

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface a {
        boolean a(g gVar, MenuItem menuItem);

        void b(g gVar);
    }

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface b {
        boolean a(i iVar);
    }

    public g(Context context) {
        this.f307b = context;
        this.f308c = context.getResources();
        f0(true);
    }

    private static int D(int i2) {
        int i3 = ((-65536) & i2) >> 16;
        if (i3 >= 0) {
            int[] iArr = f306a;
            if (i3 < iArr.length) {
                return (i2 & 65535) | (iArr[i3] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private void P(int i2, boolean z) {
        if (i2 < 0 || i2 >= this.f312g.size()) {
            return;
        }
        this.f312g.remove(i2);
        if (z) {
            M(true);
        }
    }

    private void a0(int i2, CharSequence charSequence, int i3, Drawable drawable, View view) {
        Resources resourcesE = E();
        if (view != null) {
            this.q = view;
            this.o = null;
            this.p = null;
        } else {
            if (i2 > 0) {
                this.o = resourcesE.getText(i2);
            } else if (charSequence != null) {
                this.o = charSequence;
            }
            if (i3 > 0) {
                this.p = androidx.core.content.a.d(w(), i3);
            } else if (drawable != null) {
                this.p = drawable;
            }
            this.q = null;
        }
        M(false);
    }

    private void f0(boolean z) {
        this.f310e = z && this.f308c.getConfiguration().keyboard != 1 && u.e(ViewConfiguration.get(this.f307b), this.f307b);
    }

    private i g(int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        return new i(this, i2, i3, i4, i5, charSequence, i6);
    }

    private void i(boolean z) {
        if (this.x.isEmpty()) {
            return;
        }
        h0();
        for (WeakReference<m> weakReference : this.x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.x.remove(weakReference);
            } else {
                mVar.n(z);
            }
        }
        g0();
    }

    private void j(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray == null || this.x.isEmpty()) {
            return;
        }
        for (WeakReference<m> weakReference : this.x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.x.remove(weakReference);
            } else {
                int iC = mVar.c();
                if (iC > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(iC)) != null) {
                    mVar.h(parcelable);
                }
            }
        }
    }

    private void k(Bundle bundle) {
        Parcelable parcelableE;
        if (this.x.isEmpty()) {
            return;
        }
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        for (WeakReference<m> weakReference : this.x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.x.remove(weakReference);
            } else {
                int iC = mVar.c();
                if (iC > 0 && (parcelableE = mVar.e()) != null) {
                    sparseArray.put(iC, parcelableE);
                }
            }
        }
        bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
    }

    private boolean l(r rVar, m mVar) {
        if (this.x.isEmpty()) {
            return false;
        }
        boolean zM = mVar != null ? mVar.m(rVar) : false;
        for (WeakReference<m> weakReference : this.x) {
            m mVar2 = weakReference.get();
            if (mVar2 == null) {
                this.x.remove(weakReference);
            } else if (!zM) {
                zM = mVar2.m(rVar);
            }
        }
        return zM;
    }

    private static int p(ArrayList<i> arrayList, int i2) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).f() <= i2) {
                return size + 1;
            }
        }
        return 0;
    }

    public View A() {
        return this.q;
    }

    public ArrayList<i> B() {
        t();
        return this.k;
    }

    boolean C() {
        return this.u;
    }

    Resources E() {
        return this.f308c;
    }

    public g F() {
        return this;
    }

    public ArrayList<i> G() {
        if (!this.f314i) {
            return this.f313h;
        }
        this.f313h.clear();
        int size = this.f312g.size();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.f312g.get(i2);
            if (iVar.isVisible()) {
                this.f313h.add(iVar);
            }
        }
        this.f314i = false;
        this.l = true;
        return this.f313h;
    }

    public boolean H() {
        return this.z;
    }

    boolean I() {
        return this.f309d;
    }

    public boolean J() {
        return this.f310e;
    }

    void K(i iVar) {
        this.l = true;
        M(true);
    }

    void L(i iVar) {
        this.f314i = true;
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
            this.f314i = true;
            this.l = true;
        }
        i(z);
    }

    public boolean N(MenuItem menuItem, int i2) {
        return O(menuItem, null, i2);
    }

    public boolean O(MenuItem menuItem, m mVar, int i2) {
        i iVar = (i) menuItem;
        if (iVar == null || !iVar.isEnabled()) {
            return false;
        }
        boolean zK = iVar.k();
        b.g.k.b bVarB = iVar.b();
        boolean z = bVarB != null && bVarB.a();
        if (iVar.j()) {
            zK |= iVar.expandActionView();
            if (zK) {
                e(true);
            }
        } else if (iVar.hasSubMenu() || z) {
            if ((i2 & 4) == 0) {
                e(false);
            }
            if (!iVar.hasSubMenu()) {
                iVar.w(new r(w(), this, iVar));
            }
            r rVar = (r) iVar.getSubMenu();
            if (z) {
                bVarB.f(rVar);
            }
            zK |= l(rVar, mVar);
            if (!zK) {
                e(true);
            }
        } else if ((i2 & 1) == 0) {
            e(true);
        }
        return zK;
    }

    public void Q(m mVar) {
        for (WeakReference<m> weakReference : this.x) {
            m mVar2 = weakReference.get();
            if (mVar2 == null || mVar2 == mVar) {
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
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((r) item.getSubMenu()).R(bundle);
            }
        }
        int i3 = bundle.getInt("android:menu:expandedactionview");
        if (i3 <= 0 || (menuItemFindItem = findItem(i3)) == null) {
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
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
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
                ((r) item.getSubMenu()).T(bundle);
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
        this.f311f = aVar;
    }

    public g W(int i2) {
        this.m = i2;
        return this;
    }

    void X(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f312g.size();
        h0();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.f312g.get(i2);
            if (iVar.getGroupId() == groupId && iVar.m() && iVar.isCheckable()) {
                iVar.r(iVar == menuItem);
            }
        }
        g0();
    }

    protected g Y(int i2) {
        a0(0, null, i2, null, null);
        return this;
    }

    protected g Z(Drawable drawable) {
        a0(0, null, 0, drawable, null);
        return this;
    }

    protected MenuItem a(int i2, int i3, int i4, CharSequence charSequence) {
        int iD = D(i4);
        i iVarG = g(i2, i3, i4, iD, charSequence, this.m);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.n;
        if (contextMenuInfo != null) {
            iVarG.u(contextMenuInfo);
        }
        ArrayList<i> arrayList = this.f312g;
        arrayList.add(p(arrayList, iD), iVarG);
        M(true);
        return iVarG;
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        int i6;
        PackageManager packageManager = this.f307b.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i5 & 1) == 0) {
            removeGroup(i2);
        }
        for (int i7 = 0; i7 < size; i7++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i7);
            int i8 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i8 < 0 ? intent : intentArr[i8]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent3 = add(i2, i3, i4, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i6 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i6] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(m mVar) {
        c(mVar, this.f307b);
    }

    protected g b0(int i2) {
        a0(i2, null, 0, null, null);
        return this;
    }

    public void c(m mVar, Context context) {
        this.x.add(new WeakReference<>(mVar));
        mVar.f(context, this);
        this.l = true;
    }

    protected g c0(CharSequence charSequence) {
        a0(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.Menu
    public void clear() {
        i iVar = this.y;
        if (iVar != null) {
            f(iVar);
        }
        this.f312g.clear();
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
        a aVar = this.f311f;
        if (aVar != null) {
            aVar.b(this);
        }
    }

    protected g d0(View view) {
        a0(0, null, 0, null, view);
        return this;
    }

    public final void e(boolean z) {
        if (this.v) {
            return;
        }
        this.v = true;
        for (WeakReference<m> weakReference : this.x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.x.remove(weakReference);
            } else {
                mVar.a(this, z);
            }
        }
        this.v = false;
    }

    public void e0(boolean z) {
        this.A = z;
    }

    public boolean f(i iVar) {
        boolean zI = false;
        if (!this.x.isEmpty() && this.y == iVar) {
            h0();
            for (WeakReference<m> weakReference : this.x) {
                m mVar = weakReference.get();
                if (mVar != null) {
                    zI = mVar.i(this, iVar);
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

    @Override // android.view.Menu
    public MenuItem findItem(int i2) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            i iVar = this.f312g.get(i3);
            if (iVar.getItemId() == i2) {
                return iVar;
            }
            if (iVar.hasSubMenu() && (menuItemFindItem = iVar.getSubMenu().findItem(i2)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public void g0() {
        this.r = false;
        if (this.s) {
            this.s = false;
            M(this.t);
        }
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i2) {
        return this.f312g.get(i2);
    }

    boolean h(g gVar, MenuItem menuItem) {
        a aVar = this.f311f;
        return aVar != null && aVar.a(gVar, menuItem);
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
        for (int i2 = 0; i2 < size; i2++) {
            if (this.f312g.get(i2).isVisible()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return r(i2, keyEvent) != null;
    }

    public boolean m(i iVar) {
        boolean zJ = false;
        if (this.x.isEmpty()) {
            return false;
        }
        h0();
        for (WeakReference<m> weakReference : this.x) {
            m mVar = weakReference.get();
            if (mVar != null) {
                zJ = mVar.j(this, iVar);
                if (zJ) {
                    break;
                }
            } else {
                this.x.remove(weakReference);
            }
        }
        g0();
        if (zJ) {
            this.y = iVar;
        }
        return zJ;
    }

    public int n(int i2) {
        return o(i2, 0);
    }

    public int o(int i2, int i3) {
        int size = size();
        if (i3 < 0) {
            i3 = 0;
        }
        while (i3 < size) {
            if (this.f312g.get(i3).getGroupId() == i2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i2, int i3) {
        return N(findItem(i2), i3);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        i iVarR = r(i2, keyEvent);
        boolean zN = iVarR != null ? N(iVarR, i3) : false;
        if ((i3 & 2) != 0) {
            e(true);
        }
        return zN;
    }

    public int q(int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.f312g.get(i3).getItemId() == i2) {
                return i3;
            }
        }
        return -1;
    }

    i r(int i2, KeyEvent keyEvent) {
        ArrayList<i> arrayList = this.w;
        arrayList.clear();
        s(arrayList, i2, keyEvent);
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
        for (int i3 = 0; i3 < size; i3++) {
            i iVar = arrayList.get(i3);
            char alphabeticShortcut = zI ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (zI && alphabeticShortcut == '\b' && i2 == 67))) {
                return iVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public void removeGroup(int i2) {
        int iN = n(i2);
        if (iN >= 0) {
            int size = this.f312g.size() - iN;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= size || this.f312g.get(iN).getGroupId() != i2) {
                    break;
                }
                P(iN, false);
                i3 = i4;
            }
            M(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i2) {
        P(q(i2), true);
    }

    void s(List<i> list, int i2, KeyEvent keyEvent) {
        boolean zI = I();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i2 == 67) {
            int size = this.f312g.size();
            for (int i3 = 0; i3 < size; i3++) {
                i iVar = this.f312g.get(i3);
                if (iVar.hasSubMenu()) {
                    ((g) iVar.getSubMenu()).s(list, i2, keyEvent);
                }
                char alphabeticShortcut = zI ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
                if (((modifiers & 69647) == ((zI ? iVar.getAlphabeticModifiers() : iVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zI && alphabeticShortcut == '\b' && i2 == 67)) && iVar.isEnabled()) {
                        list.add(iVar);
                    }
                }
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i2, boolean z, boolean z2) {
        int size = this.f312g.size();
        for (int i3 = 0; i3 < size; i3++) {
            i iVar = this.f312g.get(i3);
            if (iVar.getGroupId() == i2) {
                iVar.s(z2);
                iVar.setCheckable(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.z = z;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i2, boolean z) {
        int size = this.f312g.size();
        for (int i3 = 0; i3 < size; i3++) {
            i iVar = this.f312g.get(i3);
            if (iVar.getGroupId() == i2) {
                iVar.setEnabled(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i2, boolean z) {
        int size = this.f312g.size();
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            i iVar = this.f312g.get(i3);
            if (iVar.getGroupId() == i2 && iVar.x(z)) {
                z2 = true;
            }
        }
        if (z2) {
            M(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.f309d = z;
        M(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f312g.size();
    }

    public void t() {
        ArrayList<i> arrayListG = G();
        if (this.l) {
            boolean zD = false;
            for (WeakReference<m> weakReference : this.x) {
                m mVar = weakReference.get();
                if (mVar == null) {
                    this.x.remove(weakReference);
                } else {
                    zD |= mVar.d();
                }
            }
            if (zD) {
                this.j.clear();
                this.k.clear();
                int size = arrayListG.size();
                for (int i2 = 0; i2 < size; i2++) {
                    i iVar = arrayListG.get(i2);
                    if (iVar.l()) {
                        this.j.add(iVar);
                    } else {
                        this.k.add(iVar);
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

    public ArrayList<i> u() {
        t();
        return this.j;
    }

    protected String v() {
        return "android:menu:actionviewstates";
    }

    public Context w() {
        return this.f307b;
    }

    public i x() {
        return this.y;
    }

    public Drawable y() {
        return this.p;
    }

    public CharSequence z() {
        return this.o;
    }

    @Override // android.view.Menu
    public MenuItem add(int i2) {
        return a(0, 0, 0, this.f308c.getString(i2));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2) {
        return addSubMenu(0, 0, 0, this.f308c.getString(i2));
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return a(i2, i3, i4, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        i iVar = (i) a(i2, i3, i4, charSequence);
        r rVar = new r(this.f307b, this, iVar);
        iVar.w(rVar);
        return rVar;
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, int i5) {
        return a(i2, i3, i4, this.f308c.getString(i5));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return addSubMenu(i2, i3, i4, this.f308c.getString(i5));
    }
}
