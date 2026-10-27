package c.g.k.c0;

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
    private final Object f2308a;

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    static class a extends AccessibilityNodeProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final d f2309a;

        a(d dVar) {
            this.f2309a = dVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            c cVarA = this.f2309a.a(i);
            if (cVarA == null) {
                return null;
            }
            return cVarA.C0();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            List<c> listB = this.f2309a.b(str, i);
            if (listB == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = listB.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(listB.get(i2).C0());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.f2309a.e(i, i2, bundle);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    static class b extends a {
        b(d dVar) {
            super(dVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i) {
            c cVarC = this.f2309a.c(i);
            if (cVarC == null) {
                return null;
            }
            return cVarC.C0();
        }
    }

    public d() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            this.f2308a = new b(this);
        } else if (i >= 16) {
            this.f2308a = new a(this);
        } else {
            this.f2308a = null;
        }
    }

    public c a(int i) {
        return null;
    }

    public List<c> b(String str, int i) {
        return null;
    }

    public c c(int i) {
        return null;
    }

    public Object d() {
        return this.f2308a;
    }

    public boolean e(int i, int i2, Bundle bundle) {
        return false;
    }

    public d(Object obj) {
        this.f2308a = obj;
    }
}
