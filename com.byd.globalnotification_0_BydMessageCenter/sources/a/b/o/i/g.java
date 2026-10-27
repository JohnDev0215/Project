package a.b.o.i;

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
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class g implements a.h.f.a.a {
    public static final int[] z = {1, 4, 5, 3, 2, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Resources f177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f178c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f179d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f180e;
    public CharSequence m;
    public Drawable n;
    public View o;
    public i w;
    public boolean y;
    public int l = 0;
    public boolean p = false;
    public boolean q = false;
    public boolean r = false;
    public boolean s = false;
    public boolean t = false;
    public ArrayList<i> u = new ArrayList<>();
    public CopyOnWriteArrayList<WeakReference<m>> v = new CopyOnWriteArrayList<>();
    public boolean x = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<i> f181f = new ArrayList<>();
    public ArrayList<i> g = new ArrayList<>();
    public boolean h = true;
    public ArrayList<i> i = new ArrayList<>();
    public ArrayList<i> j = new ArrayList<>();
    public boolean k = true;

    public interface a {
        void a(g gVar);

        boolean b(g gVar, MenuItem menuItem);
    }

    public interface b {
        boolean b(i iVar);
    }

    public g(Context context) {
        boolean z2 = false;
        this.f176a = context;
        this.f177b = context.getResources();
        if (this.f177b.getConfiguration().keyboard != 1 && ViewConfiguration.get(this.f176a).shouldShowMenuShortcutsWhenKeyboardPresent()) {
            z2 = true;
        }
        this.f179d = z2;
    }

    public MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        int i4;
        int i5 = ((-65536) & i3) >> 16;
        if (i5 >= 0) {
            int[] iArr = z;
            if (i5 < iArr.length) {
                int i6 = (iArr[i5] << 16) | (65535 & i3);
                i iVar = new i(this, i, i2, i3, i6, charSequence, this.l);
                ArrayList<i> arrayList = this.f181f;
                int size = arrayList.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        i4 = 0;
                        break;
                    }
                    if (arrayList.get(size).f188d <= i6) {
                        i4 = size + 1;
                        break;
                    }
                }
                arrayList.add(i4, iVar);
                q(true);
                return iVar;
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return a(0, 0, 0, this.f177b.getString(i));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, this.f177b.getString(i4));
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
        PackageManager packageManager = this.f176a.getPackageManager();
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
            i iVar = (i) a(i, i2, i3, resolveInfo.loadLabel(packageManager));
            iVar.setIcon(resolveInfo.loadIcon(packageManager));
            iVar.setIntent(intent2);
            if (menuItemArr != null && (i5 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i5] = iVar;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.f177b.getString(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.f177b.getString(i4));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        i iVar = (i) a(i, i2, i3, charSequence);
        r rVar = new r(this.f176a, this, iVar);
        iVar.o = rVar;
        rVar.setHeaderTitle(iVar.f189e);
        return rVar;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(m mVar, Context context) {
        this.v.add(new WeakReference<>(mVar));
        mVar.j(context, this);
        this.k = true;
    }

    public final void c(boolean z2) {
        if (this.t) {
            return;
        }
        this.t = true;
        for (WeakReference<m> weakReference : this.v) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.v.remove(weakReference);
            } else {
                mVar.b(this, z2);
            }
        }
        this.t = false;
    }

    @Override // android.view.Menu
    public void clear() {
        i iVar = this.w;
        if (iVar != null) {
            d(iVar);
        }
        this.f181f.clear();
        q(true);
    }

    public void clearHeader() {
        this.n = null;
        this.m = null;
        this.o = null;
        q(false);
    }

    @Override // android.view.Menu
    public void close() {
        c(true);
    }

    public boolean d(i iVar) {
        boolean zK = false;
        if (!this.v.isEmpty() && this.w == iVar) {
            z();
            for (WeakReference<m> weakReference : this.v) {
                m mVar = weakReference.get();
                if (mVar != null) {
                    zK = mVar.k(this, iVar);
                    if (zK) {
                        break;
                    }
                } else {
                    this.v.remove(weakReference);
                }
            }
            y();
            if (zK) {
                this.w = null;
            }
        }
        return zK;
    }

    public boolean e(g gVar, MenuItem menuItem) {
        a aVar = this.f180e;
        return aVar != null && aVar.b(gVar, menuItem);
    }

    public boolean f(i iVar) {
        boolean zC = false;
        if (this.v.isEmpty()) {
            return false;
        }
        z();
        for (WeakReference<m> weakReference : this.v) {
            m mVar = weakReference.get();
            if (mVar != null) {
                zC = mVar.c(this, iVar);
                if (zC) {
                    break;
                }
            } else {
                this.v.remove(weakReference);
            }
        }
        y();
        if (zC) {
            this.w = iVar;
        }
        return zC;
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.f181f.get(i2);
            if (iVar.f185a == i) {
                return iVar;
            }
            if (iVar.hasSubMenu() && (menuItemFindItem = iVar.o.findItem(i)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public i g(int i, KeyEvent keyEvent) {
        ArrayList<i> arrayList = this.u;
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
            i iVar = arrayList.get(i2);
            char c2 = zN ? iVar.j : iVar.h;
            if ((c2 == keyData.meta[0] && (metaState & 2) == 0) || ((c2 == keyData.meta[2] && (metaState & 2) != 0) || (zN && c2 == '\b' && i == 67))) {
                return iVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        return this.f181f.get(i);
    }

    public void h(List<i> list, int i, KeyEvent keyEvent) {
        boolean zN = n();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.f181f.size();
            for (int i2 = 0; i2 < size; i2++) {
                i iVar = this.f181f.get(i2);
                if (iVar.hasSubMenu()) {
                    iVar.o.h(list, i, keyEvent);
                }
                char c2 = zN ? iVar.j : iVar.h;
                if (((modifiers & 69647) == ((zN ? iVar.k : iVar.i) & 69647)) && c2 != 0) {
                    char[] cArr = keyData.meta;
                    if ((c2 == cArr[0] || c2 == cArr[2] || (zN && c2 == '\b' && i == 67)) && iVar.isEnabled()) {
                        list.add(iVar);
                    }
                }
            }
        }
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.y) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.f181f.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public void i() {
        ArrayList<i> arrayListL = l();
        if (this.k) {
            boolean zG = false;
            for (WeakReference<m> weakReference : this.v) {
                m mVar = weakReference.get();
                if (mVar == null) {
                    this.v.remove(weakReference);
                } else {
                    zG |= mVar.g();
                }
            }
            if (zG) {
                this.i.clear();
                this.j.clear();
                int size = arrayListL.size();
                for (int i = 0; i < size; i++) {
                    i iVar = arrayListL.get(i);
                    (iVar.g() ? this.i : this.j).add(iVar);
                }
            } else {
                this.i.clear();
                this.j.clear();
                this.j.addAll(l());
            }
            this.k = false;
        }
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return g(i, keyEvent) != null;
    }

    public String j() {
        return "android:menu:actionviewstates";
    }

    public g k() {
        return this;
    }

    public ArrayList<i> l() {
        if (!this.h) {
            return this.g;
        }
        this.g.clear();
        int size = this.f181f.size();
        for (int i = 0; i < size; i++) {
            i iVar = this.f181f.get(i);
            if (iVar.isVisible()) {
                this.g.add(iVar);
            }
        }
        this.h = false;
        this.k = true;
        return this.g;
    }

    public boolean m() {
        return this.x;
    }

    public boolean n() {
        return this.f178c;
    }

    public boolean o() {
        return this.f179d;
    }

    public void p() {
        this.k = true;
        q(true);
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i, int i2) {
        return r(findItem(i), i2);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        i iVarG = g(i, keyEvent);
        boolean zS = iVarG != null ? s(iVarG, null, i2) : false;
        if ((i2 & 2) != 0) {
            c(true);
        }
        return zS;
    }

    public void q(boolean z2) {
        if (this.p) {
            this.q = true;
            if (z2) {
                this.r = true;
                return;
            }
            return;
        }
        if (z2) {
            this.h = true;
            this.k = true;
        }
        if (this.v.isEmpty()) {
            return;
        }
        z();
        for (WeakReference<m> weakReference : this.v) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.v.remove(weakReference);
            } else {
                mVar.h(z2);
            }
        }
        y();
    }

    public boolean r(MenuItem menuItem, int i) {
        return s(menuItem, null, i);
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
        int size = size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (this.f181f.get(i2).f186b == i) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 >= 0) {
            int size2 = this.f181f.size() - i2;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= size2 || this.f181f.get(i2).f186b != i) {
                    break;
                }
                t(i2, false);
                i3 = i4;
            }
            q(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i) {
        int size = size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (this.f181f.get(i2).f185a == i) {
                break;
            } else {
                i2++;
            }
        }
        t(i2, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00db A[PHI: r1
  0x00db: PHI (r1v13 boolean) = (r1v12 boolean), (r1v11 boolean), (r1v15 boolean) binds: [B:68:0x00d9, B:43:0x0073, B:36:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean s(android.view.MenuItem r7, a.b.o.i.m r8, int r9) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.o.i.g.s(android.view.MenuItem, a.b.o.i.m, int):boolean");
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z2, boolean z3) {
        int size = this.f181f.size();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.f181f.get(i2);
            if (iVar.f186b == i) {
                iVar.x = (iVar.x & (-5)) | (z3 ? 4 : 0);
                iVar.setCheckable(z2);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z2) {
        this.x = z2;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i, boolean z2) {
        int size = this.f181f.size();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.f181f.get(i2);
            if (iVar.f186b == i) {
                iVar.setEnabled(z2);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z2) {
        int size = this.f181f.size();
        boolean z3 = false;
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = this.f181f.get(i2);
            if (iVar.f186b == i && iVar.m(z2)) {
                z3 = true;
            }
        }
        if (z3) {
            q(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z2) {
        this.f178c = z2;
        q(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f181f.size();
    }

    public final void t(int i, boolean z2) {
        if (i < 0 || i >= this.f181f.size()) {
            return;
        }
        this.f181f.remove(i);
        if (z2) {
            q(true);
        }
    }

    public void u(m mVar) {
        for (WeakReference<m> weakReference : this.v) {
            m mVar2 = weakReference.get();
            if (mVar2 == null || mVar2 == mVar) {
                this.v.remove(weakReference);
            }
        }
    }

    public void v(Bundle bundle) {
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
                ((r) item.getSubMenu()).v(bundle);
            }
        }
        int i2 = bundle.getInt("android:menu:expandedactionview");
        if (i2 <= 0 || (menuItemFindItem = findItem(i2)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    public void w(Bundle bundle) {
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
                ((r) item.getSubMenu()).w(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(j(), sparseArray);
        }
    }

    public final void x(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources resources = this.f177b;
        if (view != null) {
            this.o = view;
            this.m = null;
            this.n = null;
        } else {
            if (i > 0) {
                this.m = resources.getText(i);
            } else if (charSequence != null) {
                this.m = charSequence;
            }
            if (i2 > 0) {
                this.n = a.h.d.a.b(this.f176a, i2);
            } else if (drawable != null) {
                this.n = drawable;
            }
            this.o = null;
        }
        q(false);
    }

    public void y() {
        this.p = false;
        if (this.q) {
            this.q = false;
            q(this.r);
        }
    }

    public void z() {
        if (this.p) {
            return;
        }
        this.p = true;
        this.q = false;
        this.r = false;
    }
}
