package p0;

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
    public final Object f6997a;

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class a extends AccessibilityNodeProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d f6998a;

        public a(d dVar) {
            this.f6998a = dVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i8) {
            c cVarA = this.f6998a.a(i8);
            if (cVarA == null) {
                return null;
            }
            return cVarA.E0();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i8) {
            List<c> listB = this.f6998a.b(str, i8);
            if (listB == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = listB.size();
            for (int i9 = 0; i9 < size; i9++) {
                arrayList.add(listB.get(i9).E0());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i8, int i9, Bundle bundle) {
            return this.f6998a.e(i8, i9, bundle);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class b extends a {
        public b(d dVar) {
            super(dVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i8) {
            c cVarC = this.f6998a.c(i8);
            if (cVarC == null) {
                return null;
            }
            return cVarC.E0();
        }
    }

    public d() {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 19) {
            this.f6997a = new b(this);
        } else if (i8 >= 16) {
            this.f6997a = new a(this);
        } else {
            this.f6997a = null;
        }
    }

    public c a(int i8) {
        return null;
    }

    public List<c> b(String str, int i8) {
        return null;
    }

    public c c(int i8) {
        return null;
    }

    public Object d() {
        return this.f6997a;
    }

    public boolean e(int i8, int i9, Bundle bundle) {
        return false;
    }

    public d(Object obj) {
        this.f6997a = obj;
    }
}
