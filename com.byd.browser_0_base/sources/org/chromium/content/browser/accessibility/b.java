package org.chromium.content.browser.accessibility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;

/* JADX INFO: compiled from: AccessibilityEventDispatcher.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b {
    public final Map<Integer, Integer> a;
    public final Set<Integer> b;
    public Set<Integer> c;
    public final a f;
    public final HashMap d = new HashMap();
    public final HashMap e = new HashMap();
    public boolean g = false;

    /* JADX INFO: compiled from: AccessibilityEventDispatcher.java */
    public interface a {
    }

    public b(WebContentsAccessibilityImpl.c cVar, HashMap map, HashSet hashSet, HashSet hashSet2) {
        this.f = cVar;
        this.a = map;
        this.b = hashSet;
        this.c = hashSet2;
    }

    public final void a() {
        HashMap map = this.e;
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            WebContentsAccessibilityImpl.this.n.removeCallbacks((Runnable) map.get((Long) it.next()));
        }
        map.clear();
    }
}
