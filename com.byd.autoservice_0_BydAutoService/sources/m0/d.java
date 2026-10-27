package m0;

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
    public final Object f3831a;

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class a extends AccessibilityNodeProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d f3832a;

        public a(d dVar) {
            this.f3832a = dVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i3) {
            c cVarA = this.f3832a.a(i3);
            if (cVarA == null) {
                return null;
            }
            return cVarA.C0();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i3) {
            List<c> listB = this.f3832a.b(str, i3);
            if (listB == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = listB.size();
            for (int i4 = 0; i4 < size; i4++) {
                arrayList.add(listB.get(i4).C0());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i3, int i4, Bundle bundle) {
            return this.f3832a.e(i3, i4, bundle);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class b extends a {
        public b(d dVar) {
            super(dVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i3) {
            c cVarC = this.f3832a.c(i3);
            if (cVarC == null) {
                return null;
            }
            return cVarC.C0();
        }
    }

    public d() {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 19) {
            this.f3831a = new b(this);
        } else if (i3 >= 16) {
            this.f3831a = new a(this);
        } else {
            this.f3831a = null;
        }
    }

    public c a(int i3) {
        return null;
    }

    public List<c> b(String str, int i3) {
        return null;
    }

    public c c(int i3) {
        return null;
    }

    public Object d() {
        return this.f3831a;
    }

    public boolean e(int i3, int i4, Bundle bundle) {
        return false;
    }

    public d(Object obj) {
        this.f3831a = obj;
    }
}
