package defpackage;

import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: PartnerBookmarksFaviconThrottle.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sw1 {
    public final SharedPreferences a;
    public Map<String, Long> b;
    public HashMap c;

    public sw1() {
        SharedPreferences sharedPreferences = xz.a.getSharedPreferences("partner_bookmarks_favicon_throttle", 0);
        this.a = sharedPreferences;
        this.b = sharedPreferences.getAll();
        this.c = new HashMap();
    }

    public final void a() {
        if (this.b.equals(this.c)) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.a.edit();
        editorEdit.clear();
        for (Map.Entry entry : this.c.entrySet()) {
            editorEdit.putLong((String) entry.getKey(), ((Long) entry.getValue()).longValue());
        }
        editorEdit.apply();
    }

    public final void b(int i, String str) {
        boolean z = true;
        if (i == 1) {
            this.c.put(str, Long.valueOf(System.currentTimeMillis() + 2592000000L));
            return;
        }
        if (i != 5 && i != 6) {
            z = false;
        }
        if (z || c(str) || System.currentTimeMillis() >= this.b.get(str).longValue()) {
            return;
        }
        this.c.put(str, this.b.get(str));
    }

    public final boolean c(String str) {
        Long l = this.b.containsKey(str) ? this.b.get(str) : null;
        return l == null || System.currentTimeMillis() >= l.longValue();
    }
}
