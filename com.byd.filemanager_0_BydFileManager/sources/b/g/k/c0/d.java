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
    private final Object f1978a;

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    static class a extends AccessibilityNodeProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final d f1979a;

        a(d dVar) {
            this.f1979a = dVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            c cVarA = this.f1979a.a(i);
            if (cVarA == null) {
                return null;
            }
            return cVarA.C0();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            List<c> listB = this.f1979a.b(str, i);
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
            return this.f1979a.e(i, i2, bundle);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    static class b extends a {
        b(d dVar) {
            super(dVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i) {
            c cVarC = this.f1979a.c(i);
            if (cVarC == null) {
                return null;
            }
            return cVarC.C0();
        }
    }

    public d() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            this.f1978a = new b(this);
        } else if (i >= 16) {
            this.f1978a = new a(this);
        } else {
            this.f1978a = null;
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
        return this.f1978a;
    }

    public boolean e(int i, int i2, Bundle bundle) {
        return false;
    }

    public d(Object obj) {
        this.f1978a = obj;
    }
}
