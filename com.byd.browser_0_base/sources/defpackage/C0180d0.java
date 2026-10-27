package defpackage;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: d0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class C0180d0 {
    public final Object e;

    /* JADX INFO: renamed from: d0$a */
    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class a extends AccessibilityNodeProvider {
        public final C0180d0 a;

        public a(C0180d0 c0180d0) {
            this.a = c0180d0;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompatG = this.a.g(i);
            if (accessibilityNodeInfoCompatG == null) {
                return null;
            }
            return accessibilityNodeInfoCompatG.a;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            List listH = this.a.h();
            if (listH == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = listH.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(((AccessibilityNodeInfoCompat) listH.get(i2)).a);
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public final boolean performAction(int i, int i2, Bundle bundle) {
            return this.a.j(i, i2, bundle);
        }
    }

    /* JADX INFO: renamed from: d0$b */
    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class b extends a {
        public b(C0180d0 c0180d0) {
            super(c0180d0);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public final AccessibilityNodeInfo findFocus(int i) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompatI = this.a.i(i);
            if (accessibilityNodeInfoCompatI == null) {
                return null;
            }
            return accessibilityNodeInfoCompatI.a;
        }
    }

    /* JADX INFO: renamed from: d0$c */
    /* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
    public static class c extends b {
        public c(C0180d0 c0180d0) {
            super(c0180d0);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public final void addExtraDataToAccessibilityNodeInfo(int i, AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
            this.a.e(i, new AccessibilityNodeInfoCompat(accessibilityNodeInfo), str, bundle);
        }
    }

    public C0180d0() {
        this.e = new c(this);
    }

    public AccessibilityNodeInfoCompat g(int i) {
        return null;
    }

    public List h() {
        return null;
    }

    public AccessibilityNodeInfoCompat i(int i) {
        return null;
    }

    public boolean j(int i, int i2, Bundle bundle) {
        return false;
    }

    public C0180d0(AccessibilityNodeProvider accessibilityNodeProvider) {
        this.e = accessibilityNodeProvider;
    }

    public void e(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str, Bundle bundle) {
    }
}
