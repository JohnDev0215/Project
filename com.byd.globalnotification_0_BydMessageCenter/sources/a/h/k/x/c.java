package a.h.k.x;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f867a;

    public static class a extends AccessibilityNodeProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c f868a;

        public a(c cVar) {
            this.f868a = cVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            a.h.k.x.b bVarA = this.f868a.a(i);
            if (bVarA == null) {
                return null;
            }
            return bVarA.f856a;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            if (this.f868a != null) {
                return null;
            }
            throw null;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.f868a.c(i, i2, bundle);
        }
    }

    public static class b extends a {
        public b(c cVar) {
            super(cVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i) {
            a.h.k.x.b bVarB = this.f868a.b(i);
            if (bVarB == null) {
                return null;
            }
            return bVarB.f856a;
        }
    }

    /* JADX INFO: renamed from: a.h.k.x.c$c, reason: collision with other inner class name */
    public static class C0017c extends b {
        public C0017c(c cVar) {
            super(cVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public void addExtraDataToAccessibilityNodeInfo(int i, AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
            if (this.f868a == null) {
                throw null;
            }
        }
    }

    public c() {
        this.f867a = new C0017c(this);
    }

    public c(Object obj) {
        this.f867a = obj;
    }

    public a.h.k.x.b a(int i) {
        return null;
    }

    public a.h.k.x.b b(int i) {
        return null;
    }

    public boolean c(int i, int i2, Bundle bundle) {
        return false;
    }
}
