package defpackage;

import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class fa {
    public final Object a;

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class a extends AccessibilityNodeProvider {
        public final fa a;

        public a(fa faVar) {
            this.a = faVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            ea eaVarA = this.a.a(i);
            if (eaVarA == null) {
                return null;
            }
            return eaVarA.C0();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            List<ea> listB = this.a.b(str, i);
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
            return this.a.e(i, i2, bundle);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class b extends a {
        public b(fa faVar) {
            super(faVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i) {
            ea eaVarC = this.a.c(i);
            if (eaVarC == null) {
                return null;
            }
            return eaVarC.C0();
        }
    }

    public fa() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            this.a = new b(this);
        } else if (i >= 16) {
            this.a = new a(this);
        } else {
            this.a = null;
        }
    }

    public ea a(int i) {
        return null;
    }

    public List<ea> b(String str, int i) {
        return null;
    }

    public ea c(int i) {
        return null;
    }

    public Object d() {
        return this.a;
    }

    public boolean e(int i, int i2, Bundle bundle) {
        return false;
    }

    public fa(Object obj) {
        this.a = obj;
    }
}
