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
import i0.v;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: MenuBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements b0.a {
    private static final int[] A = {1, 4, 5, 3, 2, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f662a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f663b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f664c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f665d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private a f666e;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private ContextMenu.ContextMenuInfo f674m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    CharSequence f675n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    Drawable f676o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    View f677p;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private g f685x;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f687z;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f673l = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f678q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f679r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f680s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private boolean f681t = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f682u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private ArrayList<g> f683v = new ArrayList<>();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private CopyOnWriteArrayList<WeakReference<j>> f684w = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private boolean f686y = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ArrayList<g> f667f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ArrayList<g> f668g = new ArrayList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f669h = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ArrayList<g> f670i = new ArrayList<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private ArrayList<g> f671j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f672k = true;

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface a {
        boolean a(e eVar, MenuItem menuItem);

        void b(e eVar);
    }

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface b {
        boolean a(g gVar);
    }

    public e(Context context) {
        this.f662a = context;
        this.f663b = context.getResources();
        b0(true);
    }

    private static int B(int i7) {
        int i8 = ((-65536) & i7) >> 16;
        if (i8 >= 0) {
            int[] iArr = A;
            if (i8 < iArr.length) {
                return (i7 & 65535) | (iArr[i8] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private void N(int i7, boolean z6) {
        if (i7 < 0 || i7 >= this.f667f.size()) {
            return;
        }
        this.f667f.remove(i7);
        if (z6) {
            K(true);
        }
    }

    private void W(int i7, CharSequence charSequence, int i8, Drawable drawable, View view) {
        Resources resourcesC = C();
        if (view != null) {
            this.f677p = view;
            this.f675n = null;
            this.f676o = null;
        } else {
            if (i7 > 0) {
                this.f675n = resourcesC.getText(i7);
            } else if (charSequence != null) {
                this.f675n = charSequence;
            }
            if (i8 > 0) {
                this.f676o = x.a.c(u(), i8);
            } else if (drawable != null) {
                this.f676o = drawable;
            }
            this.f677p = null;
        }
        K(false);
    }

    private void b0(boolean z6) {
        this.f665d = z6 && this.f663b.getConfiguration().keyboard != 1 && v.c(ViewConfiguration.get(this.f662a), this.f662a);
    }

    private g g(int i7, int i8, int i9, int i10, CharSequence charSequence, int i11) {
        return new g(this, i7, i8, i9, i10, charSequence, i11);
    }

    private void i(boolean z6) {
        if (this.f684w.isEmpty()) {
            return;
        }
        d0();
        for (WeakReference<j> weakReference : this.f684w) {
            j jVar = weakReference.get();
            if (jVar == null) {
                this.f684w.remove(weakReference);
            } else {
                jVar.j(z6);
            }
        }
        c0();
    }

    private boolean j(m mVar, j jVar) {
        if (this.f684w.isEmpty()) {
            return false;
        }
        boolean zI = jVar != null ? jVar.i(mVar) : false;
        for (WeakReference<j> weakReference : this.f684w) {
            j jVar2 = weakReference.get();
            if (jVar2 == null) {
                this.f684w.remove(weakReference);
            } else if (!zI) {
                zI = jVar2.i(mVar);
            }
        }
        return zI;
    }

    private static int n(ArrayList<g> arrayList, int i7) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).f() <= i7) {
                return size + 1;
            }
        }
        return 0;
    }

    boolean A() {
        return this.f681t;
    }

    Resources C() {
        return this.f663b;
    }

    public e D() {
        return this;
    }

    public ArrayList<g> E() {
        if (!this.f669h) {
            return this.f668g;
        }
        this.f668g.clear();
        int size = this.f667f.size();
        for (int i7 = 0; i7 < size; i7++) {
            g gVar = this.f667f.get(i7);
            if (gVar.isVisible()) {
                this.f668g.add(gVar);
            }
        }
        this.f669h = false;
        this.f672k = true;
        return this.f668g;
    }

    public boolean F() {
        return this.f686y;
    }

    boolean G() {
        return this.f664c;
    }

    public boolean H() {
        return this.f665d;
    }

    void I(g gVar) {
        this.f672k = true;
        K(true);
    }

    void J(g gVar) {
        this.f669h = true;
        K(true);
    }

    public void K(boolean z6) {
        if (this.f678q) {
            this.f679r = true;
            if (z6) {
                this.f680s = true;
                return;
            }
            return;
        }
        if (z6) {
            this.f669h = true;
            this.f672k = true;
        }
        i(z6);
    }

    public boolean L(MenuItem menuItem, int i7) {
        return M(menuItem, null, i7);
    }

    public boolean M(MenuItem menuItem, j jVar, int i7) {
        g gVar = (g) menuItem;
        if (gVar == null || !gVar.isEnabled()) {
            return false;
        }
        boolean zK = gVar.k();
        i0.b bVarB = gVar.b();
        boolean z6 = bVarB != null && bVarB.a();
        if (gVar.j()) {
            zK |= gVar.expandActionView();
            if (zK) {
                e(true);
            }
        } else if (gVar.hasSubMenu() || z6) {
            if ((i7 & 4) == 0) {
                e(false);
            }
            if (!gVar.hasSubMenu()) {
                gVar.x(new m(u(), this, gVar));
            }
            m mVar = (m) gVar.getSubMenu();
            if (z6) {
                bVarB.f(mVar);
            }
            zK |= j(mVar, jVar);
            if (!zK) {
                e(true);
            }
        } else if ((i7 & 1) == 0) {
            e(true);
        }
        return zK;
    }

    public void O(j jVar) {
        for (WeakReference<j> weakReference : this.f684w) {
            j jVar2 = weakReference.get();
            if (jVar2 == null || jVar2 == jVar) {
                this.f684w.remove(weakReference);
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
        for (int i7 = 0; i7 < size; i7++) {
            MenuItem item = getItem(i7);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((m) item.getSubMenu()).P(bundle);
            }
        }
        int i8 = bundle.getInt("android:menu:expandedactionview");
        if (i8 <= 0 || (menuItemFindItem = findItem(i8)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    public void Q(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i7 = 0; i7 < size; i7++) {
            MenuItem item = getItem(i7);
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
                ((m) item.getSubMenu()).Q(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(t(), sparseArray);
        }
    }

    public void R(a aVar) {
        this.f666e = aVar;
    }

    public e S(int i7) {
        this.f673l = i7;
        return this;
    }

    void T(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f667f.size();
        d0();
        for (int i7 = 0; i7 < size; i7++) {
            g gVar = this.f667f.get(i7);
            if (gVar.getGroupId() == groupId && gVar.m() && gVar.isCheckable()) {
                gVar.s(gVar == menuItem);
            }
        }
        c0();
    }

    protected e U(int i7) {
        W(0, null, i7, null, null);
        return this;
    }

    protected e V(Drawable drawable) {
        W(0, null, 0, drawable, null);
        return this;
    }

    protected e X(int i7) {
        W(i7, null, 0, null, null);
        return this;
    }

    protected e Y(CharSequence charSequence) {
        W(0, charSequence, 0, null, null);
        return this;
    }

    protected e Z(View view) {
        W(0, null, 0, null, view);
        return this;
    }

    protected MenuItem a(int i7, int i8, int i9, CharSequence charSequence) {
        int iB = B(i9);
        g gVarG = g(i7, i8, i9, iB, charSequence, this.f673l);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.f674m;
        if (contextMenuInfo != null) {
            gVarG.v(contextMenuInfo);
        }
        ArrayList<g> arrayList = this.f667f;
        arrayList.add(n(arrayList, iB), gVarG);
        K(true);
        return gVarG;
    }

    public void a0(boolean z6) {
        this.f687z = z6;
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i7, int i8, int i9, ComponentName componentName, Intent[] intentArr, Intent intent, int i10, MenuItem[] menuItemArr) {
        int i11;
        PackageManager packageManager = this.f662a.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i10 & 1) == 0) {
            removeGroup(i7);
        }
        for (int i12 = 0; i12 < size; i12++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i12);
            int i13 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i13 < 0 ? intent : intentArr[i13]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent3 = add(i7, i8, i9, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i11 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i11] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(j jVar) {
        c(jVar, this.f662a);
    }

    public void c(j jVar, Context context) {
        this.f684w.add(new WeakReference<>(jVar));
        jVar.d(context, this);
        this.f672k = true;
    }

    public void c0() {
        this.f678q = false;
        if (this.f679r) {
            this.f679r = false;
            K(this.f680s);
        }
    }

    @Override // android.view.Menu
    public void clear() {
        g gVar = this.f685x;
        if (gVar != null) {
            f(gVar);
        }
        this.f667f.clear();
        K(true);
    }

    public void clearHeader() {
        this.f676o = null;
        this.f675n = null;
        this.f677p = null;
        K(false);
    }

    @Override // android.view.Menu
    public void close() {
        e(true);
    }

    public void d() {
        a aVar = this.f666e;
        if (aVar != null) {
            aVar.b(this);
        }
    }

    public void d0() {
        if (this.f678q) {
            return;
        }
        this.f678q = true;
        this.f679r = false;
        this.f680s = false;
    }

    public final void e(boolean z6) {
        if (this.f682u) {
            return;
        }
        this.f682u = true;
        for (WeakReference<j> weakReference : this.f684w) {
            j jVar = weakReference.get();
            if (jVar == null) {
                this.f684w.remove(weakReference);
            } else {
                jVar.a(this, z6);
            }
        }
        this.f682u = false;
    }

    public boolean f(g gVar) {
        boolean zE = false;
        if (!this.f684w.isEmpty() && this.f685x == gVar) {
            d0();
            for (WeakReference<j> weakReference : this.f684w) {
                j jVar = weakReference.get();
                if (jVar != null) {
                    zE = jVar.e(this, gVar);
                    if (zE) {
                        break;
                    }
                } else {
                    this.f684w.remove(weakReference);
                }
            }
            c0();
            if (zE) {
                this.f685x = null;
            }
        }
        return zE;
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i7) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i8 = 0; i8 < size; i8++) {
            g gVar = this.f667f.get(i8);
            if (gVar.getItemId() == i7) {
                return gVar;
            }
            if (gVar.hasSubMenu() && (menuItemFindItem = gVar.getSubMenu().findItem(i7)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i7) {
        return this.f667f.get(i7);
    }

    boolean h(e eVar, MenuItem menuItem) {
        a aVar = this.f666e;
        return aVar != null && aVar.a(eVar, menuItem);
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.f687z) {
            return true;
        }
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            if (this.f667f.get(i7).isVisible()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i7, KeyEvent keyEvent) {
        return p(i7, keyEvent) != null;
    }

    public boolean k(g gVar) {
        boolean zF = false;
        if (this.f684w.isEmpty()) {
            return false;
        }
        d0();
        for (WeakReference<j> weakReference : this.f684w) {
            j jVar = weakReference.get();
            if (jVar != null) {
                zF = jVar.f(this, gVar);
                if (zF) {
                    break;
                }
            } else {
                this.f684w.remove(weakReference);
            }
        }
        c0();
        if (zF) {
            this.f685x = gVar;
        }
        return zF;
    }

    public int l(int i7) {
        return m(i7, 0);
    }

    public int m(int i7, int i8) {
        int size = size();
        if (i8 < 0) {
            i8 = 0;
        }
        while (i8 < size) {
            if (this.f667f.get(i8).getGroupId() == i7) {
                return i8;
            }
            i8++;
        }
        return -1;
    }

    public int o(int i7) {
        int size = size();
        for (int i8 = 0; i8 < size; i8++) {
            if (this.f667f.get(i8).getItemId() == i7) {
                return i8;
            }
        }
        return -1;
    }

    g p(int i7, KeyEvent keyEvent) {
        ArrayList<g> arrayList = this.f683v;
        arrayList.clear();
        q(arrayList, i7, keyEvent);
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
        for (int i8 = 0; i8 < size; i8++) {
            g gVar = arrayList.get(i8);
            char alphabeticShortcut = zG ? gVar.getAlphabeticShortcut() : gVar.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (zG && alphabeticShortcut == '\b' && i7 == 67))) {
                return gVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i7, int i8) {
        return L(findItem(i7), i8);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i7, KeyEvent keyEvent, int i8) {
        g gVarP = p(i7, keyEvent);
        boolean zL = gVarP != null ? L(gVarP, i8) : false;
        if ((i8 & 2) != 0) {
            e(true);
        }
        return zL;
    }

    void q(List<g> list, int i7, KeyEvent keyEvent) {
        boolean zG = G();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i7 == 67) {
            int size = this.f667f.size();
            for (int i8 = 0; i8 < size; i8++) {
                g gVar = this.f667f.get(i8);
                if (gVar.hasSubMenu()) {
                    ((e) gVar.getSubMenu()).q(list, i7, keyEvent);
                }
                char alphabeticShortcut = zG ? gVar.getAlphabeticShortcut() : gVar.getNumericShortcut();
                if (((modifiers & 69647) == ((zG ? gVar.getAlphabeticModifiers() : gVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zG && alphabeticShortcut == '\b' && i7 == 67)) && gVar.isEnabled()) {
                        list.add(gVar);
                    }
                }
            }
        }
    }

    public void r() {
        ArrayList<g> arrayListE = E();
        if (this.f672k) {
            boolean zC = false;
            for (WeakReference<j> weakReference : this.f684w) {
                j jVar = weakReference.get();
                if (jVar == null) {
                    this.f684w.remove(weakReference);
                } else {
                    zC |= jVar.c();
                }
            }
            if (zC) {
                this.f670i.clear();
                this.f671j.clear();
                int size = arrayListE.size();
                for (int i7 = 0; i7 < size; i7++) {
                    g gVar = arrayListE.get(i7);
                    if (gVar.l()) {
                        this.f670i.add(gVar);
                    } else {
                        this.f671j.add(gVar);
                    }
                }
            } else {
                this.f670i.clear();
                this.f671j.clear();
                this.f671j.addAll(E());
            }
            this.f672k = false;
        }
    }

    @Override // android.view.Menu
    public void removeGroup(int i7) {
        int iL = l(i7);
        if (iL >= 0) {
            int size = this.f667f.size() - iL;
            int i8 = 0;
            while (true) {
                int i9 = i8 + 1;
                if (i8 >= size || this.f667f.get(iL).getGroupId() != i7) {
                    break;
                }
                N(iL, false);
                i8 = i9;
            }
            K(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i7) {
        N(o(i7), true);
    }

    public ArrayList<g> s() {
        r();
        return this.f670i;
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i7, boolean z6, boolean z7) {
        int size = this.f667f.size();
        for (int i8 = 0; i8 < size; i8++) {
            g gVar = this.f667f.get(i8);
            if (gVar.getGroupId() == i7) {
                gVar.t(z7);
                gVar.setCheckable(z6);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z6) {
        this.f686y = z6;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i7, boolean z6) {
        int size = this.f667f.size();
        for (int i8 = 0; i8 < size; i8++) {
            g gVar = this.f667f.get(i8);
            if (gVar.getGroupId() == i7) {
                gVar.setEnabled(z6);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i7, boolean z6) {
        int size = this.f667f.size();
        boolean z7 = false;
        for (int i8 = 0; i8 < size; i8++) {
            g gVar = this.f667f.get(i8);
            if (gVar.getGroupId() == i7 && gVar.y(z6)) {
                z7 = true;
            }
        }
        if (z7) {
            K(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z6) {
        this.f664c = z6;
        K(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f667f.size();
    }

    protected String t() {
        return "android:menu:actionviewstates";
    }

    public Context u() {
        return this.f662a;
    }

    public g v() {
        return this.f685x;
    }

    public Drawable w() {
        return this.f676o;
    }

    public CharSequence x() {
        return this.f675n;
    }

    public View y() {
        return this.f677p;
    }

    public ArrayList<g> z() {
        r();
        return this.f671j;
    }

    @Override // android.view.Menu
    public MenuItem add(int i7) {
        return a(0, 0, 0, this.f663b.getString(i7));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i7) {
        return addSubMenu(0, 0, 0, this.f663b.getString(i7));
    }

    @Override // android.view.Menu
    public MenuItem add(int i7, int i8, int i9, CharSequence charSequence) {
        return a(i7, i8, i9, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i7, int i8, int i9, CharSequence charSequence) {
        g gVar = (g) a(i7, i8, i9, charSequence);
        m mVar = new m(this.f662a, this, gVar);
        gVar.x(mVar);
        return mVar;
    }

    @Override // android.view.Menu
    public MenuItem add(int i7, int i8, int i9, int i10) {
        return a(i7, i8, i9, this.f663b.getString(i10));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i7, int i8, int i9, int i10) {
        return addSubMenu(i7, i8, i9, this.f663b.getString(i10));
    }
}
