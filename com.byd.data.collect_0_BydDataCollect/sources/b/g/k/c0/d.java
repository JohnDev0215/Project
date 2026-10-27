package b.g.k.c0;

import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f2708a;

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    static class a extends AccessibilityNodeProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final d f2709a;

        a(d dVar) {
            this.f2709a = dVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i2) {
            c cVarA = this.f2709a.a(i2);
            if (cVarA == null) {
                return null;
            }
            return cVarA.B0();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i2) {
            List<c> listB = this.f2709a.b(str, i2);
            if (listB == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = listB.size();
            for (int i3 = 0; i3 < size; i3++) {
                arrayList.add(listB.get(i3).B0());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i2, int i3, Bundle bundle) {
            return this.f2709a.e(i2, i3, bundle);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    static class b extends a {
        b(d dVar) {
            super(dVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i2) {
            c cVarC = this.f2709a.c(i2);
            if (cVarC == null) {
                return null;
            }
            return cVarC.B0();
        }
    }

    public d() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 19) {
            this.f2708a = new b(this);
        } else if (i2 >= 16) {
            this.f2708a = new a(this);
        } else {
            this.f2708a = null;
        }
    }

    public c a(int i2) {
        return null;
    }

    public List<c> b(String str, int i2) {
        return null;
    }

    public c c(int i2) {
        return null;
    }

    public Object d() {
        return this.f2708a;
    }

    public boolean e(int i2, int i3, Bundle bundle) {
        return false;
    }

    public d(Object obj) {
        this.f2708a = obj;
    }
}
