package androidx.appcompat.view.menu;

import a.g.k.u;
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

/* JADX INFO: loaded from: classes.dex */
public class g implements a.g.e.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f811a = {1, 4, 5, 3, 2, 0};
    private boolean A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f812b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Resources f813c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f814d;
    private boolean e;
    private a f;
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
    private ArrayList<i> g = new ArrayList<>();
    private ArrayList<i> h = new ArrayList<>();
    private boolean i = true;
    private ArrayList<i> j = new ArrayList<>();
    private ArrayList<i> k = new ArrayList<>();
    private boolean l = true;

    public interface a {
        boolean a(g gVar, MenuItem menuItem);

        void b(g gVar);
    }

    public interface b {
        boolean a(i iVar);
    }

    public g(Context context) {
        this.f812b = context;
        this.f813c = context.getResources();
        b0(true);
    }

    private static int B(int i) {
        int i2 = ((-65536) & i) >> 16;
        if (i2 >= 0) {
            int[] iArr = f811a;
            if (i2 < iArr.length) {
                return (i & 65535) | (iArr[i2] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private void N(int i, boolean z) {
        if (i < 0 || i >= this.g.size()) {
            return;
        }
        this.g.remove(i);
        if (z) {
            K(true);
        }
    }

    private void W(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources resourcesC = C();
        if (view != null) {
            this.q = view;
            this.o = null;
            this.p = null;
        } else {
            if (i > 0) {
                this.o = resourcesC.getText(i);
            } else if (charSequence != null) {
                this.o = charSequence;
            }
            if (i2 > 0) {
                this.p = androidx.core.content.a.d(u(), i2);
            } else if (drawable != null) {
                this.p = drawable;
            }
            this.q = null;
        }
        K(false);
    }

    private void b0(boolean z) {
        this.e = z && this.f813c.getConfiguration().keyboard != 1 && u.e(ViewConfiguration.get(this.f812b), this.f812b);
    }

    private i g(int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        return new i(this, i, i2, i3, i4, charSequence, i5);
    }

    private void i(boolean z) {
        if (this.x.isEmpty()) {
            return;
        }
        d0();
        for (WeakReference<m> weakReference : this.x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.x.remove(weakReference);
            } else {
                mVar.j(z);
            }
        }
        c0();
    }

    private boolean j(r rVar, m mVar) {
        if (this.x.isEmpty()) {
            return false;
        }
        boolean zI = mVar != null ? mVar.i(rVar) : false;
        for (WeakReference<m> weakReference : this.x) {
            m mVar2 = weakReference.get();
            if (mVar2 == null) {
                this.x.remove(weakReference);
            } else if (!zI) {
                zI = mVar2.i(rVar);
            }
        }
        return zI;
    }

    private static int n(ArrayList<i> arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).f() <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    boolean A() {
        return this.u;
    }

    Resources C() {
        return this.f813c;
    }

    public g D() {
        return this;
    }

    public ArrayList<i> E() {
        if (!this.i) {
            return this.h;
        }
        this.h.clear();
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            i iVar = this.g.get(i);
            if (iVar.isVisible()) {
                this.h.add(iVar);
            }
        }
        this.i = false;
        this.l = true;
        return this.h;
    }

    public boolean F() {
        return this.z;
    }

    boolean G() {
        return this.f814d;
    }

    public boolean H() {
        return this.e;
    }

    void I(i iVar) {
        this.l = true;
        K(true);
    }

    void J(i iVar) {
        this.i = true;
        K(true);
    }

    public void K(boolean z) {
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

    public boolean L(MenuItem menuItem, int i) {
        return M(menuItem, null, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002d A[PHI: r1
  0x002d: PHI (r1v4 boolean) = (r1v2 boolean), (r1v1 boolean), (r1v5 boolean) binds: [B:35:0x0068, B:23:0x003c, B:16:0x002b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean M(android.view.MenuItem r7, androidx.appcompat.view.menu.m r8, int r9) {
        /*
            r6 = this;
            androidx.appcompat.view.menu.i r7 = (androidx.appcompat.view.menu.i) r7
            r0 = 0
            if (r7 == 0) goto L6c
            boolean r1 = r7.isEnabled()
            if (r1 != 0) goto Lc
            goto L6c
        Lc:
            boolean r1 = r7.k()
            a.g.k.b r2 = r7.b()
            r3 = 1
            if (r2 == 0) goto L1f
            boolean r4 = r2.a()
            if (r4 == 0) goto L1f
            r4 = r3
            goto L20
        L1f:
            r4 = r0
        L20:
            boolean r5 = r7.j()
            if (r5 == 0) goto L31
            boolean r7 = r7.expandActionView()
            r1 = r1 | r7
            if (r1 == 0) goto L6b
        L2d:
            r6.e(r3)
            goto L6b
        L31:
            boolean r5 = r7.hasSubMenu()
            if (r5 != 0) goto L3f
            if (r4 == 0) goto L3a
            goto L3f
        L3a:
            r7 = r9 & 1
            if (r7 != 0) goto L6b
            goto L2d
        L3f:
            r9 = r9 & 4
            if (r9 != 0) goto L46
            r6.e(r0)
        L46:
            boolean r9 = r7.hasSubMenu()
            if (r9 != 0) goto L58
            androidx.appcompat.view.menu.r r9 = new androidx.appcompat.view.menu.r
            android.content.Context r0 = r6.u()
            r9.<init>(r0, r6, r7)
            r7.w(r9)
        L58:
            android.view.SubMenu r7 = r7.getSubMenu()
            androidx.appcompat.view.menu.r r7 = (androidx.appcompat.view.menu.r) r7
            if (r4 == 0) goto L63
            r2.f(r7)
        L63:
            boolean r7 = r6.j(r7, r8)
            r1 = r1 | r7
            if (r1 != 0) goto L6b
            goto L2d
        L6b:
            return r1
        L6c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.g.M(android.view.MenuItem, androidx.appcompat.view.menu.m, int):boolean");
    }

    public void O(m mVar) {
        for (WeakReference<m> weakReference : this.x) {
            m mVar2 = weakReference.get();
            if (mVar2 == null || mVar2 == mVar) {
                this.x.remove(weakReference);
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
                ((r) item.getSubMenu()).P(bundle);
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
                ((r) item.getSubMenu()).Q(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(t(), sparseArray);
        }
    }

    public void R(a aVar) {
        this.f = aVar;
    }

    public g S(int i) {
        this.m = i;
        return this;
    }

    void T(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.g.size();
        d0();
        for (int i = 0; i < size; i++) {
            i iVar = this.g.get(i);
            if (iVar.getGroupId() == groupId && iVar.m() && iVar.isCheckable()) {
                iVar.r(iVar == menuItem);
            }
        }
        c0();
    }

    protected g U(int i) {
        W(0, null, i, null, null);
        return this;
    }

    protected g V(Drawable drawable) {
        W(0, null, 0, drawable, null);
        return this;
    }

    protected g X(int i) {
        W(i, null, 0, null, null);
        return this;
    }

    protected g Y(CharSequence charSequence) {
        W(0, charSequence, 0, null, null);
        return this;
    }

    protected g Z(View view) {
        W(0, null, 0, null, view);
        return this;
    }

    protected MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        int iB = B(i3);
        i iVarG = g(i, i2, i3, iB, charSequence, this.m);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.n;
        if (contextMenuInfo != null) {
            iVarG.u(contextMenuInfo);
        }
        ArrayList<i> arrayList = this.g;
        arrayList.add(n(arrayList, iB), iVarG);
        K(true);
        return iVarG;
    }

    public void a0(boolean z) {
        this.A = z;
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return a(0, 0, 0, this.f813c.getString(i));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, this.f813c.getString(i4));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int i5;
        PackageManager packageManager = this.f812b.getPackageManager();
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
    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.f813c.getString(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.f813c.getString(i4));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        i iVar = (i) a(i, i2, i3, charSequence);
        r rVar = new r(this.f812b, this, iVar);
        iVar.w(rVar);
        return rVar;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(m mVar) {
        c(mVar, this.f812b);
    }

    public void c(m mVar, Context context) {
        this.x.add(new WeakReference<>(mVar));
        mVar.d(context, this);
        this.l = true;
    }

    public void c0() {
        this.r = false;
        if (this.s) {
            this.s = false;
            K(this.t);
        }
    }

    @Override // android.view.Menu
    public void clear() {
        i iVar = this.y;
        if (iVar != null) {
            f(iVar);
        }
        this.g.clear();
        K(true);
    }

    public void clearHeader() {
        this.p = null;
        this.o = null;
        this.q = null;
        K(false);
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

    public void d0() {
        if (this.r) {
            return;
        }
        this.r = true;
        this.s = false;
        this.t = false;
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

    public boolean f(i iVar) {
        boolean zE = false;
        if (!this.x.isEmpty() && this.y == iVar) {
            d0();
            for (WeakReference<m> weakReference : this.x) {
                m mVar = weakReference.get();
                if (mVar != null) {
                    zE = mVar.e(this, iVar);
                    if (zE) {
                        break;
                    }
                } else {
                    this.x.remove(weakReference);
                }
            }
            c0();
            if (zE) {
                this.y = null;
            }
        }
        return zE;
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.g.get(i2);
            if (iVar.getItemId() == i) {
                return iVar;
            }
            if (iVar.hasSubMenu() && (menuItemFindItem = iVar.getSubMenu().findItem(i)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        return this.g.get(i);
    }

    boolean h(g gVar, MenuItem menuItem) {
        a aVar = this.f;
        return aVar != null && aVar.a(gVar, menuItem);
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

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return p(i, keyEvent) != null;
    }

    public boolean k(i iVar) {
        boolean zF = false;
        if (this.x.isEmpty()) {
            return false;
        }
        d0();
        for (WeakReference<m> weakReference : this.x) {
            m mVar = weakReference.get();
            if (mVar != null) {
                zF = mVar.f(this, iVar);
                if (zF) {
                    break;
                }
            } else {
                this.x.remove(weakReference);
            }
        }
        c0();
        if (zF) {
            this.y = iVar;
        }
        return zF;
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
            if (this.g.get(i2).getGroupId() == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public int o(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.g.get(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    i p(int i, KeyEvent keyEvent) {
        ArrayList<i> arrayList = this.w;
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
            i iVar = arrayList.get(i2);
            char alphabeticShortcut = zG ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (zG && alphabeticShortcut == '\b' && i == 67))) {
                return iVar;
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
        i iVarP = p(i, keyEvent);
        boolean zL = iVarP != null ? L(iVarP, i2) : false;
        if ((i2 & 2) != 0) {
            e(true);
        }
        return zL;
    }

    void q(List<i> list, int i, KeyEvent keyEvent) {
        boolean zG = G();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.g.size();
            for (int i2 = 0; i2 < size; i2++) {
                i iVar = this.g.get(i2);
                if (iVar.hasSubMenu()) {
                    ((g) iVar.getSubMenu()).q(list, i, keyEvent);
                }
                char alphabeticShortcut = zG ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
                if (((modifiers & 69647) == ((zG ? iVar.getAlphabeticModifiers() : iVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zG && alphabeticShortcut == '\b' && i == 67)) && iVar.isEnabled()) {
                        list.add(iVar);
                    }
                }
            }
        }
    }

    public void r() {
        ArrayList<i> arrayListE = E();
        if (this.l) {
            boolean zC = false;
            for (WeakReference<m> weakReference : this.x) {
                m mVar = weakReference.get();
                if (mVar == null) {
                    this.x.remove(weakReference);
                } else {
                    zC |= mVar.c();
                }
            }
            if (zC) {
                this.j.clear();
                this.k.clear();
                int size = arrayListE.size();
                for (int i = 0; i < size; i++) {
                    i iVar = arrayListE.get(i);
                    (iVar.l() ? this.j : this.k).add(iVar);
                }
            } else {
                this.j.clear();
                this.k.clear();
                this.k.addAll(E());
            }
            this.l = false;
        }
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
        int iL = l(i);
        if (iL >= 0) {
            int size = this.g.size() - iL;
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= size || this.g.get(iL).getGroupId() != i) {
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

    public ArrayList<i> s() {
        r();
        return this.j;
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.g.get(i2);
            if (iVar.getGroupId() == i) {
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
    public void setGroupEnabled(int i, boolean z) {
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.g.get(i2);
            if (iVar.getGroupId() == i) {
                iVar.setEnabled(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z) {
        int size = this.g.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.g.get(i2);
            if (iVar.getGroupId() == i && iVar.x(z)) {
                z2 = true;
            }
        }
        if (z2) {
            K(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.f814d = z;
        K(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.g.size();
    }

    protected String t() {
        return "android:menu:actionviewstates";
    }

    public Context u() {
        return this.f812b;
    }

    public i v() {
        return this.y;
    }

    public Drawable w() {
        return this.p;
    }

    public CharSequence x() {
        return this.o;
    }

    public View y() {
        return this.q;
    }

    public ArrayList<i> z() {
        r();
        return this.k;
    }
}
