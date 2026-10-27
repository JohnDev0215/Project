package defpackage;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: Hq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0142Hq {
    public Map a = new HashMap();

    public C0142Hq(C0124Gq[] c0124GqArr) {
        for (C0124Gq c0124Gq : c0124GqArr) {
            this.a.put(c0124Gq.a, c0124Gq);
        }
    }

    public static List b(String str) {
        return Es.e().h(str) ? Arrays.asList(Es.e().f(str).split(",")) : new ArrayList();
    }

    public static void c(String str, List list) {
        if (list.isEmpty()) {
            Es.e().k(str);
        } else {
            Es.e().b(str, TextUtils.join(",", list));
        }
    }

    public void a(Map map) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        hashSet.addAll(b("enable-features"));
        hashSet2.addAll(b("disable-features"));
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (!this.a.containsKey(str)) {
                throw new RuntimeException(Z7.d("Unable to find flag '", str, "' in the list."));
            }
            C0124Gq c0124Gq = (C0124Gq) this.a.get(str);
            boolean zBooleanValue = ((Boolean) entry.getValue()).booleanValue();
            if (c0124Gq.d) {
                if (zBooleanValue) {
                    hashSet.add(c0124Gq.a);
                    hashSet2.remove(c0124Gq.a);
                } else {
                    hashSet.remove(c0124Gq.a);
                    hashSet2.add(c0124Gq.a);
                }
            } else if (zBooleanValue && c0124Gq.c != null) {
                Es.e().b(c0124Gq.a, c0124Gq.c);
            } else if (zBooleanValue) {
                Es.e().a(c0124Gq.a);
            } else {
                Es.e().k(c0124Gq.a);
            }
        }
        c("enable-features", new ArrayList(hashSet));
        c("disable-features", new ArrayList(hashSet2));
    }
}
