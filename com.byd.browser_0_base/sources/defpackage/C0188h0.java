package defpackage;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityManager;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: renamed from: h0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccessibilityUtil.java */
/* JADX INFO: loaded from: classes3.dex */
public class C0188h0 {
    public Boolean a;
    public Boolean b;
    public org.chromium.base.b<b> c;
    public a d;

    /* JADX INFO: renamed from: h0$a */
    /* JADX INFO: compiled from: AccessibilityUtil.java */
    public final class a implements AccessibilityManager.AccessibilityStateChangeListener, AccessibilityManager.TouchExplorationStateChangeListener {
        public a() {
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public final void onAccessibilityStateChanged(boolean z) {
            C0188h0.this.e();
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public final void onTouchExplorationStateChanged(boolean z) {
            C0188h0.this.e();
        }
    }

    /* JADX INFO: renamed from: h0$b */
    /* JADX INFO: compiled from: AccessibilityUtil.java */
    public interface b {
        void y0(boolean z);
    }

    public static AccessibilityManager b() {
        return (AccessibilityManager) xz.a.getSystemService("accessibility");
    }

    public final void a(b bVar) {
        if (this.c == null) {
            this.c = new org.chromium.base.b<>();
        }
        this.c.a(bVar);
        bVar.y0(c());
    }

    public boolean c() {
        if (this.d == null) {
            this.d = new a();
            AccessibilityManager accessibilityManagerB = b();
            accessibilityManagerB.addAccessibilityStateChangeListener(this.d);
            accessibilityManagerB.addTouchExplorationStateChangeListener(this.d);
        }
        Boolean bool = this.a;
        if (bool != null) {
            return bool.booleanValue();
        }
        TraceEvent.q("AccessibilityManager::isAccessibilityEnabled", null);
        AccessibilityManager accessibilityManagerB2 = b();
        boolean z = true;
        boolean z2 = accessibilityManagerB2 != null && accessibilityManagerB2.isEnabled() && accessibilityManagerB2.isTouchExplorationEnabled();
        this.b = Boolean.valueOf(z2);
        if (accessibilityManagerB2 == null || !accessibilityManagerB2.isEnabled() || z2) {
            z = z2;
        } else {
            Iterator<AccessibilityServiceInfo> it = accessibilityManagerB2.getEnabledAccessibilityServiceList(-1).iterator();
            while (it.hasNext()) {
                if ((it.next().getCapabilities() & 32) != 0) {
                    break;
                }
            }
            z = z2;
        }
        this.a = Boolean.valueOf(z);
        TraceEvent.C("AccessibilityManager::isAccessibilityEnabled");
        return this.a.booleanValue();
    }

    public boolean d() {
        if (this.d == null) {
            this.d = new a();
            AccessibilityManager accessibilityManagerB = b();
            accessibilityManagerB.addAccessibilityStateChangeListener(this.d);
            accessibilityManagerB.addTouchExplorationStateChangeListener(this.d);
        }
        Boolean bool = this.b;
        if (bool != null) {
            return bool.booleanValue();
        }
        TraceEvent.q("AccessibilityManager::isTouchExplorationEnabled", null);
        AccessibilityManager accessibilityManagerB2 = b();
        this.b = Boolean.valueOf(accessibilityManagerB2 != null && accessibilityManagerB2.isEnabled() && accessibilityManagerB2.isTouchExplorationEnabled());
        TraceEvent.C("AccessibilityManager::isTouchExplorationEnabled");
        return this.b.booleanValue();
    }

    public final void e() {
        boolean zC = c();
        this.a = null;
        this.b = null;
        if (zC == c()) {
            return;
        }
        boolean zC2 = c();
        if (this.c == null) {
            this.c = new org.chromium.base.b<>();
        }
        Iterator<b> it = this.c.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((b) aVar.next()).y0(zC2);
            }
        }
    }
}
