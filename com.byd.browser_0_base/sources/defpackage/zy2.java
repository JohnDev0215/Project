package defpackage;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: TabContext.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zy2 {
    public final List<b> a;
    public final List<a> b;

    /* JADX INFO: compiled from: TabContext.java */
    public static class a {
        public final int a;
        public final List<b> b;

        public a(int i, ArrayList arrayList) {
            this.a = i;
            this.b = Collections.unmodifiableList(arrayList);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            List<b> list = this.b;
            int i = this.a;
            int i2 = aVar.a;
            List<b> list2 = aVar.b;
            return (i == i2 && list == null) ? list2 == null : list.equals(list2);
        }

        public final int hashCode() {
            List<b> list = this.b;
            return ((list == null ? 0 : list.hashCode()) * 31 * 31) + this.a;
        }
    }

    /* JADX INFO: compiled from: TabContext.java */
    public static class b implements Comparable<b> {
        public final String e;
        public final long f;
        public final int g;
        public final String h;

        public b(int i, String str, long j, String str2) {
            this.g = i;
            this.e = str;
            this.f = j;
            this.h = str2;
        }

        public static b a(Tab tab) {
            int id = tab.getId();
            tab.getTitle();
            String strJ = tab.getUrl().j();
            tab.v().j();
            long j = CriticalPersistedTabData.m(tab).r;
            String strJ2 = tab.getUrl().j();
            tab.isIncognito();
            return new b(id, strJ, j, strJ2);
        }

        @Override // java.lang.Comparable
        public final int compareTo(b bVar) {
            return Integer.compare(this.g, bVar.g);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.g == bVar.g && TextUtils.equals(this.e, bVar.e);
        }

        public final int hashCode() {
            int i = 527 + this.g;
            StringBuilder sb = new StringBuilder();
            sb.append(i * 31);
            String str = this.e;
            sb.append(str);
            if (sb.toString() == null) {
                return 0;
            }
            return str.hashCode();
        }
    }

    public zy2(ArrayList arrayList, ArrayList arrayList2) {
        this.a = Collections.unmodifiableList(arrayList);
        this.b = Collections.unmodifiableList(arrayList2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zy2)) {
            return false;
        }
        zy2 zy2Var = (zy2) obj;
        List<a> list = zy2Var.b;
        List<a> list2 = this.b;
        if (list2 != null ? list2.equals(list) : list == null) {
            List<b> list3 = zy2Var.a;
            List<b> list4 = this.a;
            if (list4 == null) {
                if (list3 == null) {
                    return true;
                }
            } else if (list4.equals(list3)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        List<a> list = this.b;
        int iHashCode = (527 + (list == null ? 0 : list.hashCode())) * 31;
        List<b> list2 = this.a;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }
}
