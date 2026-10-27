package a.g.k.c0;

import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f404a;

    static class a extends AccessibilityNodeProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final d f405a;

        a(d dVar) {
            this.f405a = dVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            c cVarA = this.f405a.a(i);
            if (cVarA == null) {
                return null;
            }
            return cVarA.u0();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            List<c> listB = this.f405a.b(str, i);
            if (listB == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = listB.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(listB.get(i2).u0());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.f405a.e(i, i2, bundle);
        }
    }

    static class b extends a {
        b(d dVar) {
            super(dVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i) {
            c cVarC = this.f405a.c(i);
            if (cVarC == null) {
                return null;
            }
            return cVarC.u0();
        }
    }

    public d() {
        int i = Build.VERSION.SDK_INT;
        this.f404a = i >= 19 ? new b(this) : i >= 16 ? new a(this) : null;
    }

    public d(Object obj) {
        this.f404a = obj;
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
        return this.f404a;
    }

    public boolean e(int i, int i2, Bundle bundle) {
        return false;
    }
}
