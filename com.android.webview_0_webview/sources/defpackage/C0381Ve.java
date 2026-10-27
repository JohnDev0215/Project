package defpackage;

import android.accounts.Account;
import android.view.View;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: Ve, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0381Ve {
    public final Account a;
    public final Set b;
    public final Set c;
    public final Map d;
    public final String e;
    public final String f;
    public final C0618cj g;
    public final boolean h;
    public Integer i;

    public C0381Ve(Account account, Set set, Map map, int i, View view, String str, String str2, C0618cj c0618cj, boolean z) {
        this.a = account;
        this.b = set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.d = map == null ? Collections.emptyMap() : map;
        this.e = str;
        this.f = str2;
        this.g = c0618cj;
        this.h = z;
        HashSet hashSet = new HashSet(this.b);
        Iterator it = this.d.values().iterator();
        if (it.hasNext()) {
            throw null;
        }
        this.c = Collections.unmodifiableSet(hashSet);
    }
}
