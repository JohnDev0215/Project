package q;

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
import o0.u;

/* JADX INFO: compiled from: MenuBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements i0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f7130a = {1, 4, 5, 3, 2, 0};
    public boolean A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f7131b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Resources f7132c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f7133d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7134e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f7135f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ContextMenu.ContextMenuInfo f7143n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CharSequence f7144o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f7145p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public View f7146q;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public i f7154y;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7142m = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f7147r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f7148s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f7149t = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f7150u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f7151v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ArrayList<i> f7152w = new ArrayList<>();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public CopyOnWriteArrayList<WeakReference<m>> f7153x = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f7155z = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<i> f7136g = new ArrayList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<i> f7137h = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f7138i = true;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList<i> f7139j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList<i> f7140k = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f7141l = true;

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
        this.f7131b = context;
        this.f7132c = context.getResources();
        f0(true);
    }

    public static int D(int i8) {
        int i9 = ((-65536) & i8) >> 16;
        if (i9 >= 0) {
            int[] iArr = f7130a;
            if (i9 < iArr.length) {
                return (i8 & 65535) | (iArr[i9] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    public static int p(ArrayList<i> arrayList, int i8) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).f() <= i8) {
                return size + 1;
            }
        }
        return 0;
    }

    public View A() {
        return this.f7146q;
    }

    public ArrayList<i> B() {
        t();
        return this.f7140k;
    }

    public boolean C() {
        return this.f7150u;
    }

    public Resources E() {
        return this.f7132c;
    }

    public g F() {
        return this;
    }

    public ArrayList<i> G() {
        if (!this.f7138i) {
            return this.f7137h;
        }
        this.f7137h.clear();
        int size = this.f7136g.size();
        for (int i8 = 0; i8 < size; i8++) {
            i iVar = this.f7136g.get(i8);
            if (iVar.isVisible()) {
                this.f7137h.add(iVar);
            }
        }
        this.f7138i = false;
        this.f7141l = true;
        return this.f7137h;
    }

    public boolean H() {
        return this.f7155z;
    }

    public boolean I() {
        return this.f7133d;
    }

    public boolean J() {
        return this.f7134e;
    }

    public void K(i iVar) {
        this.f7141l = true;
        M(true);
    }

    public void L(i iVar) {
        this.f7138i = true;
        M(true);
    }

    public void M(boolean z7) {
        if (this.f7147r) {
            this.f7148s = true;
            if (z7) {
                this.f7149t = true;
                return;
            }
            return;
        }
        if (z7) {
            this.f7138i = true;
            this.f7141l = true;
        }
        i(z7);
    }

    public boolean N(MenuItem menuItem, int i8) {
        return O(menuItem, null, i8);
    }

    public boolean O(MenuItem menuItem, m mVar, int i8) {
        i iVar = (i) menuItem;
        if (iVar == null || !iVar.isEnabled()) {
            return false;
        }
        boolean zK = iVar.k();
        o0.b bVarB = iVar.b();
        boolean z7 = bVarB != null && bVarB.a();
        if (iVar.j()) {
            zK |= iVar.expandActionView();
            if (zK) {
                e(true);
            }
        } else if (iVar.hasSubMenu() || z7) {
            if ((i8 & 4) == 0) {
                e(false);
            }
            if (!iVar.hasSubMenu()) {
                iVar.w(new r(w(), this, iVar));
            }
            r rVar = (r) iVar.getSubMenu();
            if (z7) {
                bVarB.f(rVar);
            }
            zK |= l(rVar, mVar);
            if (!zK) {
                e(true);
            }
        } else if ((i8 & 1) == 0) {
            e(true);
        }
        return zK;
    }

    public final void P(int i8, boolean z7) {
        if (i8 < 0 || i8 >= this.f7136g.size()) {
            return;
        }
        this.f7136g.remove(i8);
        if (z7) {
            M(true);
        }
    }

    public void Q(m mVar) {
        for (WeakReference<m> weakReference : this.f7153x) {
            m mVar2 = weakReference.get();
            if (mVar2 == null || mVar2 == mVar) {
                this.f7153x.remove(weakReference);
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
        for (int i8 = 0; i8 < size; i8++) {
            MenuItem item = getItem(i8);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((r) item.getSubMenu()).R(bundle);
            }
        }
        int i9 = bundle.getInt("android:menu:expandedactionview");
        if (i9 <= 0 || (menuItemFindItem = findItem(i9)) == null) {
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
        for (int i8 = 0; i8 < size; i8++) {
            MenuItem item = getItem(i8);
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
        this.f7135f = aVar;
    }

    public g W(int i8) {
        this.f7142m = i8;
        return this;
    }

    public void X(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f7136g.size();
        h0();
        for (int i8 = 0; i8 < size; i8++) {
            i iVar = this.f7136g.get(i8);
            if (iVar.getGroupId() == groupId && iVar.m() && iVar.isCheckable()) {
                iVar.r(iVar == menuItem);
            }
        }
        g0();
    }

    public g Y(int i8) {
        a0(0, null, i8, null, null);
        return this;
    }

    public g Z(Drawable drawable) {
        a0(0, null, 0, drawable, null);
        return this;
    }

    public MenuItem a(int i8, int i9, int i10, CharSequence charSequence) {
        int iD = D(i10);
        i iVarG = g(i8, i9, i10, iD, charSequence, this.f7142m);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.f7143n;
        if (contextMenuInfo != null) {
            iVarG.u(contextMenuInfo);
        }
        ArrayList<i> arrayList = this.f7136g;
        arrayList.add(p(arrayList, iD), iVarG);
        M(true);
        return iVarG;
    }

    public final void a0(int i8, CharSequence charSequence, int i9, Drawable drawable, View view) {
        Resources resourcesE = E();
        if (view != null) {
            this.f7146q = view;
            this.f7144o = null;
            this.f7145p = null;
        } else {
            if (i8 > 0) {
                this.f7144o = resourcesE.getText(i8);
            } else if (charSequence != null) {
                this.f7144o = charSequence;
            }
            if (i9 > 0) {
                this.f7145p = e0.a.d(w(), i9);
            } else if (drawable != null) {
                this.f7145p = drawable;
            }
            this.f7146q = null;
        }
        M(false);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i8, int i9, int i10, ComponentName componentName, Intent[] intentArr, Intent intent, int i11, MenuItem[] menuItemArr) {
        int i12;
        PackageManager packageManager = this.f7131b.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i11 & 1) == 0) {
            removeGroup(i8);
        }
        for (int i13 = 0; i13 < size; i13++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i13);
            int i14 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i14 < 0 ? intent : intentArr[i14]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent3 = add(i8, i9, i10, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i12 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i12] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(m mVar) {
        c(mVar, this.f7131b);
    }

    public g b0(int i8) {
        a0(i8, null, 0, null, null);
        return this;
    }

    public void c(m mVar, Context context) {
        this.f7153x.add(new WeakReference<>(mVar));
        mVar.b(context, this);
        this.f7141l = true;
    }

    public g c0(CharSequence charSequence) {
        a0(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.Menu
    public void clear() {
        i iVar = this.f7154y;
        if (iVar != null) {
            f(iVar);
        }
        this.f7136g.clear();
        M(true);
    }

    public void clearHeader() {
        this.f7145p = null;
        this.f7144o = null;
        this.f7146q = null;
        M(false);
    }

    @Override // android.view.Menu
    public void close() {
        e(true);
    }

    public void d() {
        a aVar = this.f7135f;
        if (aVar != null) {
            aVar.b(this);
        }
    }

    public g d0(View view) {
        a0(0, null, 0, null, view);
        return this;
    }

    public final void e(boolean z7) {
        if (this.f7151v) {
            return;
        }
        this.f7151v = true;
        for (WeakReference<m> weakReference : this.f7153x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.f7153x.remove(weakReference);
            } else {
                mVar.a(this, z7);
            }
        }
        this.f7151v = false;
    }

    public void e0(boolean z7) {
        this.A = z7;
    }

    public boolean f(i iVar) {
        boolean zL = false;
        if (!this.f7153x.isEmpty() && this.f7154y == iVar) {
            h0();
            for (WeakReference<m> weakReference : this.f7153x) {
                m mVar = weakReference.get();
                if (mVar != null) {
                    zL = mVar.l(this, iVar);
                    if (zL) {
                        break;
                    }
                } else {
                    this.f7153x.remove(weakReference);
                }
            }
            g0();
            if (zL) {
                this.f7154y = null;
            }
        }
        return zL;
    }

    public final void f0(boolean z7) {
        this.f7134e = z7 && this.f7132c.getConfiguration().keyboard != 1 && u.e(ViewConfiguration.get(this.f7131b), this.f7131b);
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i8) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            i iVar = this.f7136g.get(i9);
            if (iVar.getItemId() == i8) {
                return iVar;
            }
            if (iVar.hasSubMenu() && (menuItemFindItem = iVar.getSubMenu().findItem(i8)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public final i g(int i8, int i9, int i10, int i11, CharSequence charSequence, int i12) {
        return new i(this, i8, i9, i10, i11, charSequence, i12);
    }

    public void g0() {
        this.f7147r = false;
        if (this.f7148s) {
            this.f7148s = false;
            M(this.f7149t);
        }
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i8) {
        return this.f7136g.get(i8);
    }

    public boolean h(g gVar, MenuItem menuItem) {
        a aVar = this.f7135f;
        return aVar != null && aVar.a(gVar, menuItem);
    }

    public void h0() {
        if (this.f7147r) {
            return;
        }
        this.f7147r = true;
        this.f7148s = false;
        this.f7149t = false;
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.A) {
            return true;
        }
        int size = size();
        for (int i8 = 0; i8 < size; i8++) {
            if (this.f7136g.get(i8).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public final void i(boolean z7) {
        if (this.f7153x.isEmpty()) {
            return;
        }
        h0();
        for (WeakReference<m> weakReference : this.f7153x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.f7153x.remove(weakReference);
            } else {
                mVar.g(z7);
            }
        }
        g0();
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i8, KeyEvent keyEvent) {
        return r(i8, keyEvent) != null;
    }

    public final void j(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray == null || this.f7153x.isEmpty()) {
            return;
        }
        for (WeakReference<m> weakReference : this.f7153x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.f7153x.remove(weakReference);
            } else {
                int i8 = mVar.i();
                if (i8 > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(i8)) != null) {
                    mVar.d(parcelable);
                }
            }
        }
    }

    public final void k(Bundle bundle) {
        Parcelable parcelableK;
        if (this.f7153x.isEmpty()) {
            return;
        }
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        for (WeakReference<m> weakReference : this.f7153x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.f7153x.remove(weakReference);
            } else {
                int i8 = mVar.i();
                if (i8 > 0 && (parcelableK = mVar.k()) != null) {
                    sparseArray.put(i8, parcelableK);
                }
            }
        }
        bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
    }

    public final boolean l(r rVar, m mVar) {
        if (this.f7153x.isEmpty()) {
            return false;
        }
        boolean zF = mVar != null ? mVar.f(rVar) : false;
        for (WeakReference<m> weakReference : this.f7153x) {
            m mVar2 = weakReference.get();
            if (mVar2 == null) {
                this.f7153x.remove(weakReference);
            } else if (!zF) {
                zF = mVar2.f(rVar);
            }
        }
        return zF;
    }

    public boolean m(i iVar) {
        boolean zM = false;
        if (this.f7153x.isEmpty()) {
            return false;
        }
        h0();
        for (WeakReference<m> weakReference : this.f7153x) {
            m mVar = weakReference.get();
            if (mVar != null) {
                zM = mVar.m(this, iVar);
                if (zM) {
                    break;
                }
            } else {
                this.f7153x.remove(weakReference);
            }
        }
        g0();
        if (zM) {
            this.f7154y = iVar;
        }
        return zM;
    }

    public int n(int i8) {
        return o(i8, 0);
    }

    public int o(int i8, int i9) {
        int size = size();
        if (i9 < 0) {
            i9 = 0;
        }
        while (i9 < size) {
            if (this.f7136g.get(i9).getGroupId() == i8) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i8, int i9) {
        return N(findItem(i8), i9);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i8, KeyEvent keyEvent, int i9) {
        i iVarR = r(i8, keyEvent);
        boolean zN = iVarR != null ? N(iVarR, i9) : false;
        if ((i9 & 2) != 0) {
            e(true);
        }
        return zN;
    }

    public int q(int i8) {
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            if (this.f7136g.get(i9).getItemId() == i8) {
                return i9;
            }
        }
        return -1;
    }

    public i r(int i8, KeyEvent keyEvent) {
        ArrayList<i> arrayList = this.f7152w;
        arrayList.clear();
        s(arrayList, i8, keyEvent);
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
        for (int i9 = 0; i9 < size; i9++) {
            i iVar = arrayList.get(i9);
            char alphabeticShortcut = zI ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (zI && alphabeticShortcut == '\b' && i8 == 67))) {
                return iVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public void removeGroup(int i8) {
        int iN = n(i8);
        if (iN >= 0) {
            int size = this.f7136g.size() - iN;
            int i9 = 0;
            while (true) {
                int i10 = i9 + 1;
                if (i9 >= size || this.f7136g.get(iN).getGroupId() != i8) {
                    break;
                }
                P(iN, false);
                i9 = i10;
            }
            M(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i8) {
        P(q(i8), true);
    }

    public void s(List<i> list, int i8, KeyEvent keyEvent) {
        boolean zI = I();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i8 == 67) {
            int size = this.f7136g.size();
            for (int i9 = 0; i9 < size; i9++) {
                i iVar = this.f7136g.get(i9);
                if (iVar.hasSubMenu()) {
                    ((g) iVar.getSubMenu()).s(list, i8, keyEvent);
                }
                char alphabeticShortcut = zI ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
                if (((modifiers & 69647) == ((zI ? iVar.getAlphabeticModifiers() : iVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zI && alphabeticShortcut == '\b' && i8 == 67)) && iVar.isEnabled()) {
                        list.add(iVar);
                    }
                }
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i8, boolean z7, boolean z8) {
        int size = this.f7136g.size();
        for (int i9 = 0; i9 < size; i9++) {
            i iVar = this.f7136g.get(i9);
            if (iVar.getGroupId() == i8) {
                iVar.s(z8);
                iVar.setCheckable(z7);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z7) {
        this.f7155z = z7;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i8, boolean z7) {
        int size = this.f7136g.size();
        for (int i9 = 0; i9 < size; i9++) {
            i iVar = this.f7136g.get(i9);
            if (iVar.getGroupId() == i8) {
                iVar.setEnabled(z7);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i8, boolean z7) {
        int size = this.f7136g.size();
        boolean z8 = false;
        for (int i9 = 0; i9 < size; i9++) {
            i iVar = this.f7136g.get(i9);
            if (iVar.getGroupId() == i8 && iVar.x(z7)) {
                z8 = true;
            }
        }
        if (z8) {
            M(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z7) {
        this.f7133d = z7;
        M(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f7136g.size();
    }

    public void t() {
        ArrayList<i> arrayListG = G();
        if (this.f7141l) {
            boolean zJ = false;
            for (WeakReference<m> weakReference : this.f7153x) {
                m mVar = weakReference.get();
                if (mVar == null) {
                    this.f7153x.remove(weakReference);
                } else {
                    zJ |= mVar.j();
                }
            }
            if (zJ) {
                this.f7139j.clear();
                this.f7140k.clear();
                int size = arrayListG.size();
                for (int i8 = 0; i8 < size; i8++) {
                    i iVar = arrayListG.get(i8);
                    if (iVar.l()) {
                        this.f7139j.add(iVar);
                    } else {
                        this.f7140k.add(iVar);
                    }
                }
            } else {
                this.f7139j.clear();
                this.f7140k.clear();
                this.f7140k.addAll(G());
            }
            this.f7141l = false;
        }
    }

    public ArrayList<i> u() {
        t();
        return this.f7139j;
    }

    public String v() {
        return "android:menu:actionviewstates";
    }

    public Context w() {
        return this.f7131b;
    }

    public i x() {
        return this.f7154y;
    }

    public Drawable y() {
        return this.f7145p;
    }

    public CharSequence z() {
        return this.f7144o;
    }

    @Override // android.view.Menu
    public MenuItem add(int i8) {
        return a(0, 0, 0, this.f7132c.getString(i8));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i8) {
        return addSubMenu(0, 0, 0, this.f7132c.getString(i8));
    }

    @Override // android.view.Menu
    public MenuItem add(int i8, int i9, int i10, CharSequence charSequence) {
        return a(i8, i9, i10, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i8, int i9, int i10, CharSequence charSequence) {
        i iVar = (i) a(i8, i9, i10, charSequence);
        r rVar = new r(this.f7131b, this, iVar);
        iVar.w(rVar);
        return rVar;
    }

    @Override // android.view.Menu
    public MenuItem add(int i8, int i9, int i10, int i11) {
        return a(i8, i9, i10, this.f7132c.getString(i11));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i8, int i9, int i10, int i11) {
        return addSubMenu(i8, i9, i10, this.f7132c.getString(i11));
    }
}
