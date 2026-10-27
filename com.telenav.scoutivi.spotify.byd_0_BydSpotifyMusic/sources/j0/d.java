package j0;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f7141a;

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    static class a extends AccessibilityNodeProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final d f7142a;

        a(d dVar) {
            this.f7142a = dVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i7) {
            j0.c cVarB = this.f7142a.b(i7);
            if (cVarB == null) {
                return null;
            }
            return cVarB.o0();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i7) {
            List<j0.c> listC = this.f7142a.c(str, i7);
            if (listC == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = listC.size();
            for (int i8 = 0; i8 < size; i8++) {
                arrayList.add(listC.get(i8).o0());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i7, int i8, Bundle bundle) {
            return this.f7142a.f(i7, i8, bundle);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    static class b extends a {
        b(d dVar) {
            super(dVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i7) {
            j0.c cVarD = this.f7142a.d(i7);
            if (cVarD == null) {
                return null;
            }
            return cVarD.o0();
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    static class c extends b {
        c(d dVar) {
            super(dVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public void addExtraDataToAccessibilityNodeInfo(int i7, AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
            this.f7142a.a(i7, j0.c.p0(accessibilityNodeInfo), str, bundle);
        }
    }

    public d() {
        this.f7141a = new c(this);
    }

    public void a(int i7, j0.c cVar, String str, Bundle bundle) {
    }

    public j0.c b(int i7) {
        return null;
    }

    public List<j0.c> c(String str, int i7) {
        return null;
    }

    public j0.c d(int i7) {
        return null;
    }

    public Object e() {
        return this.f7141a;
    }

    public boolean f(int i7, int i8, Bundle bundle) {
        return false;
    }

    public d(Object obj) {
        this.f7141a = obj;
    }
}
