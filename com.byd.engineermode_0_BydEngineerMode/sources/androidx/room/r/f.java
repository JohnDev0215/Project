package androidx.room.r;

import android.database.Cursor;
import android.os.Build;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: compiled from: TableInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1382a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<String, a> f1383b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set<b> f1384c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Set<d> f1385d;

    /* JADX INFO: compiled from: TableInfo.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1386a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f1387b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1388c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f1389d;
        public final int e;
        public final String f;
        private final int g;

        public a(String str, String str2, boolean z, int i, String str3, int i2) {
            this.f1386a = str;
            this.f1387b = str2;
            this.f1389d = z;
            this.e = i;
            this.f1388c = a(str2);
            this.f = str3;
            this.g = i2;
        }

        private static int a(String str) {
            if (str == null) {
                return 5;
            }
            String upperCase = str.toUpperCase(Locale.US);
            if (upperCase.contains("INT")) {
                return 3;
            }
            if (upperCase.contains("CHAR") || upperCase.contains("CLOB") || upperCase.contains("TEXT")) {
                return 2;
            }
            if (upperCase.contains("BLOB")) {
                return 5;
            }
            return (upperCase.contains("REAL") || upperCase.contains("FLOA") || upperCase.contains("DOUB")) ? 4 : 1;
        }

        public boolean b() {
            return this.e > 0;
        }

        public boolean equals(Object obj) {
            String str;
            String str2;
            String str3;
            if (this == obj) {
                return true;
            }
            if (obj == null || a.class != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (Build.VERSION.SDK_INT >= 20) {
                if (this.e != aVar.e) {
                    return false;
                }
            } else if (b() != aVar.b()) {
                return false;
            }
            if (!this.f1386a.equals(aVar.f1386a) || this.f1389d != aVar.f1389d) {
                return false;
            }
            if (this.g == 1 && aVar.g == 2 && (str3 = this.f) != null && !str3.equals(aVar.f)) {
                return false;
            }
            if (this.g == 2 && aVar.g == 1 && (str2 = aVar.f) != null && !str2.equals(this.f)) {
                return false;
            }
            int i = this.g;
            return (i == 0 || i != aVar.g || ((str = this.f) == null ? aVar.f == null : str.equals(aVar.f))) && this.f1388c == aVar.f1388c;
        }

        public int hashCode() {
            return (((((this.f1386a.hashCode() * 31) + this.f1388c) * 31) + (this.f1389d ? 1231 : 1237)) * 31) + this.e;
        }

        public String toString() {
            return "Column{name='" + this.f1386a + "', type='" + this.f1387b + "', affinity='" + this.f1388c + "', notNull=" + this.f1389d + ", primaryKeyPosition=" + this.e + ", defaultValue='" + this.f + "'}";
        }
    }

    /* JADX INFO: compiled from: TableInfo.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1390a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f1391b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final String f1392c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final List<String> f1393d;
        public final List<String> e;

        public b(String str, String str2, String str3, List<String> list, List<String> list2) {
            this.f1390a = str;
            this.f1391b = str2;
            this.f1392c = str3;
            this.f1393d = Collections.unmodifiableList(list);
            this.e = Collections.unmodifiableList(list2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.f1390a.equals(bVar.f1390a) && this.f1391b.equals(bVar.f1391b) && this.f1392c.equals(bVar.f1392c) && this.f1393d.equals(bVar.f1393d)) {
                return this.e.equals(bVar.e);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.f1390a.hashCode() * 31) + this.f1391b.hashCode()) * 31) + this.f1392c.hashCode()) * 31) + this.f1393d.hashCode()) * 31) + this.e.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable='" + this.f1390a + "', onDelete='" + this.f1391b + "', onUpdate='" + this.f1392c + "', columnNames=" + this.f1393d + ", referenceColumnNames=" + this.e + '}';
        }
    }

    /* JADX INFO: compiled from: TableInfo.java */
    static class c implements Comparable<c> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f1394b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f1395c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final String f1396d;
        final String e;

        c(int i, int i2, String str, String str2) {
            this.f1394b = i;
            this.f1395c = i2;
            this.f1396d = str;
            this.e = str2;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(c cVar) {
            int i = this.f1394b - cVar.f1394b;
            return i == 0 ? this.f1395c - cVar.f1395c : i;
        }
    }

    /* JADX INFO: compiled from: TableInfo.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1397a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f1398b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List<String> f1399c;

        public d(String str, boolean z, List<String> list) {
            this.f1397a = str;
            this.f1398b = z;
            this.f1399c = list;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || d.class != obj.getClass()) {
                return false;
            }
            d dVar = (d) obj;
            if (this.f1398b == dVar.f1398b && this.f1399c.equals(dVar.f1399c)) {
                return this.f1397a.startsWith("index_") ? dVar.f1397a.startsWith("index_") : this.f1397a.equals(dVar.f1397a);
            }
            return false;
        }

        public int hashCode() {
            return ((((this.f1397a.startsWith("index_") ? -1184239155 : this.f1397a.hashCode()) * 31) + (this.f1398b ? 1 : 0)) * 31) + this.f1399c.hashCode();
        }

        public String toString() {
            return "Index{name='" + this.f1397a + "', unique=" + this.f1398b + ", columns=" + this.f1399c + '}';
        }
    }

    public f(String str, Map<String, a> map, Set<b> set, Set<d> set2) {
        this.f1382a = str;
        this.f1383b = Collections.unmodifiableMap(map);
        this.f1384c = Collections.unmodifiableSet(set);
        this.f1385d = set2 == null ? null : Collections.unmodifiableSet(set2);
    }

    public static f a(b.m.a.b bVar, String str) {
        return new f(str, b(bVar, str), d(bVar, str), f(bVar, str));
    }

    private static Map<String, a> b(b.m.a.b bVar, String str) {
        Cursor cursorQ = bVar.q("PRAGMA table_info(`" + str + "`)");
        HashMap map = new HashMap();
        try {
            if (cursorQ.getColumnCount() > 0) {
                int columnIndex = cursorQ.getColumnIndex("name");
                int columnIndex2 = cursorQ.getColumnIndex("type");
                int columnIndex3 = cursorQ.getColumnIndex("notnull");
                int columnIndex4 = cursorQ.getColumnIndex("pk");
                int columnIndex5 = cursorQ.getColumnIndex("dflt_value");
                while (cursorQ.moveToNext()) {
                    String string = cursorQ.getString(columnIndex);
                    map.put(string, new a(string, cursorQ.getString(columnIndex2), cursorQ.getInt(columnIndex3) != 0, cursorQ.getInt(columnIndex4), cursorQ.getString(columnIndex5), 2));
                }
            }
            return map;
        } finally {
            cursorQ.close();
        }
    }

    private static List<c> c(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("id");
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex("from");
        int columnIndex4 = cursor.getColumnIndex("to");
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            arrayList.add(new c(cursor.getInt(columnIndex), cursor.getInt(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4)));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static Set<b> d(b.m.a.b bVar, String str) {
        HashSet hashSet = new HashSet();
        Cursor cursorQ = bVar.q("PRAGMA foreign_key_list(`" + str + "`)");
        try {
            int columnIndex = cursorQ.getColumnIndex("id");
            int columnIndex2 = cursorQ.getColumnIndex("seq");
            int columnIndex3 = cursorQ.getColumnIndex("table");
            int columnIndex4 = cursorQ.getColumnIndex("on_delete");
            int columnIndex5 = cursorQ.getColumnIndex("on_update");
            List<c> listC = c(cursorQ);
            int count = cursorQ.getCount();
            for (int i = 0; i < count; i++) {
                cursorQ.moveToPosition(i);
                if (cursorQ.getInt(columnIndex2) == 0) {
                    int i2 = cursorQ.getInt(columnIndex);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (c cVar : listC) {
                        if (cVar.f1394b == i2) {
                            arrayList.add(cVar.f1396d);
                            arrayList2.add(cVar.e);
                        }
                    }
                    hashSet.add(new b(cursorQ.getString(columnIndex3), cursorQ.getString(columnIndex4), cursorQ.getString(columnIndex5), arrayList, arrayList2));
                }
            }
            return hashSet;
        } finally {
            cursorQ.close();
        }
    }

    private static d e(b.m.a.b bVar, String str, boolean z) {
        Cursor cursorQ = bVar.q("PRAGMA index_xinfo(`" + str + "`)");
        try {
            int columnIndex = cursorQ.getColumnIndex("seqno");
            int columnIndex2 = cursorQ.getColumnIndex("cid");
            int columnIndex3 = cursorQ.getColumnIndex("name");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                TreeMap treeMap = new TreeMap();
                while (cursorQ.moveToNext()) {
                    if (cursorQ.getInt(columnIndex2) >= 0) {
                        treeMap.put(Integer.valueOf(cursorQ.getInt(columnIndex)), cursorQ.getString(columnIndex3));
                    }
                }
                ArrayList arrayList = new ArrayList(treeMap.size());
                arrayList.addAll(treeMap.values());
                return new d(str, z, arrayList);
            }
            return null;
        } finally {
            cursorQ.close();
        }
    }

    private static Set<d> f(b.m.a.b bVar, String str) {
        Cursor cursorQ = bVar.q("PRAGMA index_list(`" + str + "`)");
        try {
            int columnIndex = cursorQ.getColumnIndex("name");
            int columnIndex2 = cursorQ.getColumnIndex("origin");
            int columnIndex3 = cursorQ.getColumnIndex("unique");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                HashSet hashSet = new HashSet();
                while (cursorQ.moveToNext()) {
                    if ("c".equals(cursorQ.getString(columnIndex2))) {
                        String string = cursorQ.getString(columnIndex);
                        boolean z = true;
                        if (cursorQ.getInt(columnIndex3) != 1) {
                            z = false;
                        }
                        d dVarE = e(bVar, string, z);
                        if (dVarE == null) {
                            return null;
                        }
                        hashSet.add(dVarE);
                    }
                }
                return hashSet;
            }
            return null;
        } finally {
            cursorQ.close();
        }
    }

    public boolean equals(Object obj) {
        Set<d> set;
        if (this == obj) {
            return true;
        }
        if (obj == null || f.class != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        String str = this.f1382a;
        if (str == null ? fVar.f1382a != null : !str.equals(fVar.f1382a)) {
            return false;
        }
        Map<String, a> map = this.f1383b;
        if (map == null ? fVar.f1383b != null : !map.equals(fVar.f1383b)) {
            return false;
        }
        Set<b> set2 = this.f1384c;
        if (set2 == null ? fVar.f1384c != null : !set2.equals(fVar.f1384c)) {
            return false;
        }
        Set<d> set3 = this.f1385d;
        if (set3 == null || (set = fVar.f1385d) == null) {
            return true;
        }
        return set3.equals(set);
    }

    public int hashCode() {
        String str = this.f1382a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Map<String, a> map = this.f1383b;
        int iHashCode2 = (iHashCode + (map != null ? map.hashCode() : 0)) * 31;
        Set<b> set = this.f1384c;
        return iHashCode2 + (set != null ? set.hashCode() : 0);
    }

    public String toString() {
        return "TableInfo{name='" + this.f1382a + "', columns=" + this.f1383b + ", foreignKeys=" + this.f1384c + ", indices=" + this.f1385d + '}';
    }
}
