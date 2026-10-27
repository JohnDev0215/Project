package n;

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
import l0.u;

/* JADX INFO: compiled from: MenuBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements f0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f3933a = {1, 4, 5, 3, 2, 0};
    public boolean A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f3934b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Resources f3935c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3936d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3937e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f3938f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ContextMenu.ContextMenuInfo f3946n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CharSequence f3947o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f3948p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public View f3949q;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public i f3957y;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3945m = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f3950r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f3951s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f3952t = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f3953u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f3954v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ArrayList<i> f3955w = new ArrayList<>();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public CopyOnWriteArrayList<WeakReference<m>> f3956x = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f3958z = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<i> f3939g = new ArrayList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<i> f3940h = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3941i = true;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList<i> f3942j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList<i> f3943k = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3944l = true;

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
        this.f3934b = context;
        this.f3935c = context.getResources();
        f0(true);
    }

    public static int D(int i3) {
        int i4 = ((-65536) & i3) >> 16;
        if (i4 >= 0) {
            int[] iArr = f3933a;
            if (i4 < iArr.length) {
                return (i3 & 65535) | (iArr[i4] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    public static int p(ArrayList<i> arrayList, int i3) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).f() <= i3) {
                return size + 1;
            }
        }
        return 0;
    }

    public View A() {
        return this.f3949q;
    }

    public ArrayList<i> B() {
        t();
        return this.f3943k;
    }

    public boolean C() {
        return this.f3953u;
    }

    public Resources E() {
        return this.f3935c;
    }

    public g F() {
        return this;
    }

    public ArrayList<i> G() {
        if (!this.f3941i) {
            return this.f3940h;
        }
        this.f3940h.clear();
        int size = this.f3939g.size();
        for (int i3 = 0; i3 < size; i3++) {
            i iVar = this.f3939g.get(i3);
            if (iVar.isVisible()) {
                this.f3940h.add(iVar);
            }
        }
        this.f3941i = false;
        this.f3944l = true;
        return this.f3940h;
    }

    public boolean H() {
        return this.f3958z;
    }

    public boolean I() {
        return this.f3936d;
    }

    public boolean J() {
        return this.f3937e;
    }

    public void K(i iVar) {
        this.f3944l = true;
        M(true);
    }

    public void L(i iVar) {
        this.f3941i = true;
        M(true);
    }

    public void M(boolean z3) {
        if (this.f3950r) {
            this.f3951s = true;
            if (z3) {
                this.f3952t = true;
                return;
            }
            return;
        }
        if (z3) {
            this.f3941i = true;
            this.f3944l = true;
        }
        i(z3);
    }

    public boolean N(MenuItem menuItem, int i3) {
        return O(menuItem, null, i3);
    }

    public boolean O(MenuItem menuItem, m mVar, int i3) {
        i iVar = (i) menuItem;
        if (iVar == null || !iVar.isEnabled()) {
            return false;
        }
        boolean zK = iVar.k();
        l0.b bVarB = iVar.b();
        boolean z3 = bVarB != null && bVarB.a();
        if (iVar.j()) {
            zK |= iVar.expandActionView();
            if (zK) {
                e(true);
            }
        } else if (iVar.hasSubMenu() || z3) {
            if ((i3 & 4) == 0) {
                e(false);
            }
            if (!iVar.hasSubMenu()) {
                iVar.w(new r(w(), this, iVar));
            }
            r rVar = (r) iVar.getSubMenu();
            if (z3) {
                bVarB.f(rVar);
            }
            zK |= l(rVar, mVar);
            if (!zK) {
                e(true);
            }
        } else if ((i3 & 1) == 0) {
            e(true);
        }
        return zK;
    }

    public final void P(int i3, boolean z3) {
        if (i3 < 0 || i3 >= this.f3939g.size()) {
            return;
        }
        this.f3939g.remove(i3);
        if (z3) {
            M(true);
        }
    }

    public void Q(m mVar) {
        for (WeakReference<m> weakReference : this.f3956x) {
            m mVar2 = weakReference.get();
            if (mVar2 == null || mVar2 == mVar) {
                this.f3956x.remove(weakReference);
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
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = getItem(i3);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((r) item.getSubMenu()).R(bundle);
            }
        }
        int i4 = bundle.getInt("android:menu:expandedactionview");
        if (i4 <= 0 || (menuItemFindItem = findItem(i4)) == null) {
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
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = getItem(i3);
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
        this.f3938f = aVar;
    }

    public g W(int i3) {
        this.f3945m = i3;
        return this;
    }

    public void X(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f3939g.size();
        h0();
        for (int i3 = 0; i3 < size; i3++) {
            i iVar = this.f3939g.get(i3);
            if (iVar.getGroupId() == groupId && iVar.m() && iVar.isCheckable()) {
                iVar.r(iVar == menuItem);
            }
        }
        g0();
    }

    public g Y(int i3) {
        a0(0, null, i3, null, null);
        return this;
    }

    public g Z(Drawable drawable) {
        a0(0, null, 0, drawable, null);
        return this;
    }

    public MenuItem a(int i3, int i4, int i5, CharSequence charSequence) {
        int iD = D(i5);
        i iVarG = g(i3, i4, i5, iD, charSequence, this.f3945m);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.f3946n;
        if (contextMenuInfo != null) {
            iVarG.u(contextMenuInfo);
        }
        ArrayList<i> arrayList = this.f3939g;
        arrayList.add(p(arrayList, iD), iVarG);
        M(true);
        return iVarG;
    }

    public final void a0(int i3, CharSequence charSequence, int i4, Drawable drawable, View view) {
        Resources resourcesE = E();
        if (view != null) {
            this.f3949q = view;
            this.f3947o = null;
            this.f3948p = null;
        } else {
            if (i3 > 0) {
                this.f3947o = resourcesE.getText(i3);
            } else if (charSequence != null) {
                this.f3947o = charSequence;
            }
            if (i4 > 0) {
                this.f3948p = b0.a.d(w(), i4);
            } else if (drawable != null) {
                this.f3948p = drawable;
            }
            this.f3949q = null;
        }
        M(false);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i3, int i4, int i5, ComponentName componentName, Intent[] intentArr, Intent intent, int i6, MenuItem[] menuItemArr) {
        int i7;
        PackageManager packageManager = this.f3934b.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i6 & 1) == 0) {
            removeGroup(i3);
        }
        for (int i8 = 0; i8 < size; i8++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i8);
            int i9 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i9 < 0 ? intent : intentArr[i9]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent3 = add(i3, i4, i5, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i7 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i7] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(m mVar) {
        c(mVar, this.f3934b);
    }

    public g b0(int i3) {
        a0(i3, null, 0, null, null);
        return this;
    }

    public void c(m mVar, Context context) {
        this.f3956x.add(new WeakReference<>(mVar));
        mVar.f(context, this);
        this.f3944l = true;
    }

    public g c0(CharSequence charSequence) {
        a0(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.Menu
    public void clear() {
        i iVar = this.f3957y;
        if (iVar != null) {
            f(iVar);
        }
        this.f3939g.clear();
        M(true);
    }

    public void clearHeader() {
        this.f3948p = null;
        this.f3947o = null;
        this.f3949q = null;
        M(false);
    }

    @Override // android.view.Menu
    public void close() {
        e(true);
    }

    public void d() {
        a aVar = this.f3938f;
        if (aVar != null) {
            aVar.b(this);
        }
    }

    public g d0(View view) {
        a0(0, null, 0, null, view);
        return this;
    }

    public final void e(boolean z3) {
        if (this.f3954v) {
            return;
        }
        this.f3954v = true;
        for (WeakReference<m> weakReference : this.f3956x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.f3956x.remove(weakReference);
            } else {
                mVar.a(this, z3);
            }
        }
        this.f3954v = false;
    }

    public void e0(boolean z3) {
        this.A = z3;
    }

    public boolean f(i iVar) {
        boolean zI = false;
        if (!this.f3956x.isEmpty() && this.f3957y == iVar) {
            h0();
            for (WeakReference<m> weakReference : this.f3956x) {
                m mVar = weakReference.get();
                if (mVar != null) {
                    zI = mVar.i(this, iVar);
                    if (zI) {
                        break;
                    }
                } else {
                    this.f3956x.remove(weakReference);
                }
            }
            g0();
            if (zI) {
                this.f3957y = null;
            }
        }
        return zI;
    }

    public final void f0(boolean z3) {
        this.f3937e = z3 && this.f3935c.getConfiguration().keyboard != 1 && u.e(ViewConfiguration.get(this.f3934b), this.f3934b);
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i3) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i4 = 0; i4 < size; i4++) {
            i iVar = this.f3939g.get(i4);
            if (iVar.getItemId() == i3) {
                return iVar;
            }
            if (iVar.hasSubMenu() && (menuItemFindItem = iVar.getSubMenu().findItem(i3)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public final i g(int i3, int i4, int i5, int i6, CharSequence charSequence, int i7) {
        return new i(this, i3, i4, i5, i6, charSequence, i7);
    }

    public void g0() {
        this.f3950r = false;
        if (this.f3951s) {
            this.f3951s = false;
            M(this.f3952t);
        }
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i3) {
        return this.f3939g.get(i3);
    }

    public boolean h(g gVar, MenuItem menuItem) {
        a aVar = this.f3938f;
        return aVar != null && aVar.a(gVar, menuItem);
    }

    public void h0() {
        if (this.f3950r) {
            return;
        }
        this.f3950r = true;
        this.f3951s = false;
        this.f3952t = false;
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.A) {
            return true;
        }
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.f3939g.get(i3).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public final void i(boolean z3) {
        if (this.f3956x.isEmpty()) {
            return;
        }
        h0();
        for (WeakReference<m> weakReference : this.f3956x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.f3956x.remove(weakReference);
            } else {
                mVar.n(z3);
            }
        }
        g0();
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i3, KeyEvent keyEvent) {
        return r(i3, keyEvent) != null;
    }

    public final void j(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray == null || this.f3956x.isEmpty()) {
            return;
        }
        for (WeakReference<m> weakReference : this.f3956x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.f3956x.remove(weakReference);
            } else {
                int iC = mVar.c();
                if (iC > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(iC)) != null) {
                    mVar.h(parcelable);
                }
            }
        }
    }

    public final void k(Bundle bundle) {
        Parcelable parcelableE;
        if (this.f3956x.isEmpty()) {
            return;
        }
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        for (WeakReference<m> weakReference : this.f3956x) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.f3956x.remove(weakReference);
            } else {
                int iC = mVar.c();
                if (iC > 0 && (parcelableE = mVar.e()) != null) {
                    sparseArray.put(iC, parcelableE);
                }
            }
        }
        bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
    }

    public final boolean l(r rVar, m mVar) {
        if (this.f3956x.isEmpty()) {
            return false;
        }
        boolean zM = mVar != null ? mVar.m(rVar) : false;
        for (WeakReference<m> weakReference : this.f3956x) {
            m mVar2 = weakReference.get();
            if (mVar2 == null) {
                this.f3956x.remove(weakReference);
            } else if (!zM) {
                zM = mVar2.m(rVar);
            }
        }
        return zM;
    }

    public boolean m(i iVar) {
        boolean zJ = false;
        if (this.f3956x.isEmpty()) {
            return false;
        }
        h0();
        for (WeakReference<m> weakReference : this.f3956x) {
            m mVar = weakReference.get();
            if (mVar != null) {
                zJ = mVar.j(this, iVar);
                if (zJ) {
                    break;
                }
            } else {
                this.f3956x.remove(weakReference);
            }
        }
        g0();
        if (zJ) {
            this.f3957y = iVar;
        }
        return zJ;
    }

    public int n(int i3) {
        return o(i3, 0);
    }

    public int o(int i3, int i4) {
        int size = size();
        if (i4 < 0) {
            i4 = 0;
        }
        while (i4 < size) {
            if (this.f3939g.get(i4).getGroupId() == i3) {
                return i4;
            }
            i4++;
        }
        return -1;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i3, int i4) {
        return N(findItem(i3), i4);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i3, KeyEvent keyEvent, int i4) {
        i iVarR = r(i3, keyEvent);
        boolean zN = iVarR != null ? N(iVarR, i4) : false;
        if ((i4 & 2) != 0) {
            e(true);
        }
        return zN;
    }

    public int q(int i3) {
        int size = size();
        for (int i4 = 0; i4 < size; i4++) {
            if (this.f3939g.get(i4).getItemId() == i3) {
                return i4;
            }
        }
        return -1;
    }

    public i r(int i3, KeyEvent keyEvent) {
        ArrayList<i> arrayList = this.f3955w;
        arrayList.clear();
        s(arrayList, i3, keyEvent);
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
        for (int i4 = 0; i4 < size; i4++) {
            i iVar = arrayList.get(i4);
            char alphabeticShortcut = zI ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (zI && alphabeticShortcut == '\b' && i3 == 67))) {
                return iVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public void removeGroup(int i3) {
        int iN = n(i3);
        if (iN >= 0) {
            int size = this.f3939g.size() - iN;
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                if (i4 >= size || this.f3939g.get(iN).getGroupId() != i3) {
                    break;
                }
                P(iN, false);
                i4 = i5;
            }
            M(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i3) {
        P(q(i3), true);
    }

    public void s(List<i> list, int i3, KeyEvent keyEvent) {
        boolean zI = I();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i3 == 67) {
            int size = this.f3939g.size();
            for (int i4 = 0; i4 < size; i4++) {
                i iVar = this.f3939g.get(i4);
                if (iVar.hasSubMenu()) {
                    ((g) iVar.getSubMenu()).s(list, i3, keyEvent);
                }
                char alphabeticShortcut = zI ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
                if (((modifiers & 69647) == ((zI ? iVar.getAlphabeticModifiers() : iVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zI && alphabeticShortcut == '\b' && i3 == 67)) && iVar.isEnabled()) {
                        list.add(iVar);
                    }
                }
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i3, boolean z3, boolean z4) {
        int size = this.f3939g.size();
        for (int i4 = 0; i4 < size; i4++) {
            i iVar = this.f3939g.get(i4);
            if (iVar.getGroupId() == i3) {
                iVar.s(z4);
                iVar.setCheckable(z3);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z3) {
        this.f3958z = z3;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i3, boolean z3) {
        int size = this.f3939g.size();
        for (int i4 = 0; i4 < size; i4++) {
            i iVar = this.f3939g.get(i4);
            if (iVar.getGroupId() == i3) {
                iVar.setEnabled(z3);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i3, boolean z3) {
        int size = this.f3939g.size();
        boolean z4 = false;
        for (int i4 = 0; i4 < size; i4++) {
            i iVar = this.f3939g.get(i4);
            if (iVar.getGroupId() == i3 && iVar.x(z3)) {
                z4 = true;
            }
        }
        if (z4) {
            M(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z3) {
        this.f3936d = z3;
        M(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f3939g.size();
    }

    public void t() {
        ArrayList<i> arrayListG = G();
        if (this.f3944l) {
            boolean zD = false;
            for (WeakReference<m> weakReference : this.f3956x) {
                m mVar = weakReference.get();
                if (mVar == null) {
                    this.f3956x.remove(weakReference);
                } else {
                    zD |= mVar.d();
                }
            }
            if (zD) {
                this.f3942j.clear();
                this.f3943k.clear();
                int size = arrayListG.size();
                for (int i3 = 0; i3 < size; i3++) {
                    i iVar = arrayListG.get(i3);
                    if (iVar.l()) {
                        this.f3942j.add(iVar);
                    } else {
                        this.f3943k.add(iVar);
                    }
                }
            } else {
                this.f3942j.clear();
                this.f3943k.clear();
                this.f3943k.addAll(G());
            }
            this.f3944l = false;
        }
    }

    public ArrayList<i> u() {
        t();
        return this.f3942j;
    }

    public String v() {
        return "android:menu:actionviewstates";
    }

    public Context w() {
        return this.f3934b;
    }

    public i x() {
        return this.f3957y;
    }

    public Drawable y() {
        return this.f3948p;
    }

    public CharSequence z() {
        return this.f3947o;
    }

    @Override // android.view.Menu
    public MenuItem add(int i3) {
        return a(0, 0, 0, this.f3935c.getString(i3));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i3) {
        return addSubMenu(0, 0, 0, this.f3935c.getString(i3));
    }

    @Override // android.view.Menu
    public MenuItem add(int i3, int i4, int i5, CharSequence charSequence) {
        return a(i3, i4, i5, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i3, int i4, int i5, CharSequence charSequence) {
        i iVar = (i) a(i3, i4, i5, charSequence);
        r rVar = new r(this.f3934b, this, iVar);
        iVar.w(rVar);
        return rVar;
    }

    @Override // android.view.Menu
    public MenuItem add(int i3, int i4, int i5, int i6) {
        return a(i3, i4, i5, this.f3935c.getString(i6));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i3, int i4, int i5, int i6) {
        return addSubMenu(i3, i4, i5, this.f3935c.getString(i6));
    }
}
