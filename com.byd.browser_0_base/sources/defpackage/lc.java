package defpackage;

import android.content.SharedPreferences;
import defpackage.xz;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: BackgroundTaskSchedulerUma.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lc extends vd1 {
    public static lc k;

    /* JADX INFO: compiled from: BackgroundTaskSchedulerUma.java */
    public static class a {
        public final String a;
        public final int b;
        public int c;

        public a(String str, int i, int i2) {
            this.a = str;
            this.b = i;
            this.c = i2;
        }

        public static a a(String str) {
            if (str == null) {
                return null;
            }
            String[] strArrSplit = str.split(":");
            if (strArrSplit.length == 3 && !strArrSplit[0].isEmpty() && !strArrSplit[1].isEmpty() && !strArrSplit[2].isEmpty()) {
                try {
                    return new a(strArrSplit[0], Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]));
                } catch (NumberFormatException unused) {
                }
            }
            return null;
        }

        public final String toString() {
            return this.a + ":" + this.b + ":" + this.c;
        }
    }

    public static void J(int i, String str) {
        String next;
        a aVar;
        SharedPreferences sharedPreferences = xz.a.a;
        Set<String> stringSet = sharedPreferences.getStringSet("bts_cached_uma", new HashSet());
        if (stringSet != null && stringSet.contains(null)) {
            stringSet.remove(null);
        }
        String str2 = str + ":" + i + ":";
        Iterator<String> it = stringSet.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.startsWith(str2)) {
                    break;
                }
            }
        }
        HashSet hashSet = new HashSet(stringSet);
        if (next != null) {
            aVar = a.a(next);
            if (aVar == null) {
                aVar = new a(str, i, 1);
            }
            hashSet.remove(next);
            aVar.c++;
        } else {
            aVar = new a(str, i, 1);
        }
        hashSet.add(aVar.toString());
        Object obj = ThreadUtils.a;
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        if (hashSet.contains(null)) {
            hashSet.remove(null);
        }
        editorEdit.putStringSet("bts_cached_uma", hashSet);
        editorEdit.apply();
    }

    public static void K() {
        Object obj = ThreadUtils.a;
        Set<String> stringSet = xz.a.a.getStringSet("bts_cached_uma", new HashSet());
        if (stringSet != null && stringSet.contains(null)) {
            stringSet.remove(null);
        }
        Iterator<String> it = stringSet.iterator();
        while (it.hasNext()) {
            a aVarA = a.a(it.next());
            if (aVarA != null) {
                for (int i = 0; i < aVarA.c; i++) {
                    oj2.U(aVarA.b, 29, aVarA.a);
                }
            }
        }
        Object obj2 = ThreadUtils.a;
        xz.a.a.edit().remove("bts_cached_uma").apply();
    }

    public static lc L() {
        if (k == null) {
            k = new lc();
        }
        return k;
    }
}
