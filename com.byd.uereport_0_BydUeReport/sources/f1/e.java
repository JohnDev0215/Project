package f1;

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
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4832a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<String, a> f4833b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set<b> f4834c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Set<d> f4835d;

    /* JADX INFO: compiled from: TableInfo.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f4836a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f4837b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f4838c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f4839d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f4840e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final String f4841f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final int f4842g;

        public a(String str, String str2, boolean z7, int i8, String str3, int i9) {
            this.f4836a = str;
            this.f4837b = str2;
            this.f4839d = z7;
            this.f4840e = i8;
            this.f4838c = a(str2);
            this.f4841f = str3;
            this.f4842g = i9;
        }

        public static int a(String str) {
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
            return this.f4840e > 0;
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
                if (this.f4840e != aVar.f4840e) {
                    return false;
                }
            } else if (b() != aVar.b()) {
                return false;
            }
            if (!this.f4836a.equals(aVar.f4836a) || this.f4839d != aVar.f4839d) {
                return false;
            }
            if (this.f4842g == 1 && aVar.f4842g == 2 && (str3 = this.f4841f) != null && !str3.equals(aVar.f4841f)) {
                return false;
            }
            if (this.f4842g == 2 && aVar.f4842g == 1 && (str2 = aVar.f4841f) != null && !str2.equals(this.f4841f)) {
                return false;
            }
            int i8 = this.f4842g;
            return (i8 == 0 || i8 != aVar.f4842g || ((str = this.f4841f) == null ? aVar.f4841f == null : str.equals(aVar.f4841f))) && this.f4838c == aVar.f4838c;
        }

        public int hashCode() {
            return (((((this.f4836a.hashCode() * 31) + this.f4838c) * 31) + (this.f4839d ? 1231 : 1237)) * 31) + this.f4840e;
        }

        public String toString() {
            return "Column{name='" + this.f4836a + "', type='" + this.f4837b + "', affinity='" + this.f4838c + "', notNull=" + this.f4839d + ", primaryKeyPosition=" + this.f4840e + ", defaultValue='" + this.f4841f + "'}";
        }
    }

    /* JADX INFO: compiled from: TableInfo.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f4843a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f4844b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final String f4845c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final List<String> f4846d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final List<String> f4847e;

        public b(String str, String str2, String str3, List<String> list, List<String> list2) {
            this.f4843a = str;
            this.f4844b = str2;
            this.f4845c = str3;
            this.f4846d = Collections.unmodifiableList(list);
            this.f4847e = Collections.unmodifiableList(list2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.f4843a.equals(bVar.f4843a) && this.f4844b.equals(bVar.f4844b) && this.f4845c.equals(bVar.f4845c) && this.f4846d.equals(bVar.f4846d)) {
                return this.f4847e.equals(bVar.f4847e);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.f4843a.hashCode() * 31) + this.f4844b.hashCode()) * 31) + this.f4845c.hashCode()) * 31) + this.f4846d.hashCode()) * 31) + this.f4847e.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable='" + this.f4843a + "', onDelete='" + this.f4844b + "', onUpdate='" + this.f4845c + "', columnNames=" + this.f4846d + ", referenceColumnNames=" + this.f4847e + '}';
        }
    }

    /* JADX INFO: compiled from: TableInfo.java */
    public static class c implements Comparable<c> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f4848d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f4849e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final String f4850f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final String f4851g;

        public c(int i8, int i9, String str, String str2) {
            this.f4848d = i8;
            this.f4849e = i9;
            this.f4850f = str;
            this.f4851g = str2;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(c cVar) {
            int i8 = this.f4848d - cVar.f4848d;
            return i8 == 0 ? this.f4849e - cVar.f4849e : i8;
        }
    }

    /* JADX INFO: compiled from: TableInfo.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f4852a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f4853b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List<String> f4854c;

        public d(String str, boolean z7, List<String> list) {
            this.f4852a = str;
            this.f4853b = z7;
            this.f4854c = list;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || d.class != obj.getClass()) {
                return false;
            }
            d dVar = (d) obj;
            if (this.f4853b == dVar.f4853b && this.f4854c.equals(dVar.f4854c)) {
                return this.f4852a.startsWith("index_") ? dVar.f4852a.startsWith("index_") : this.f4852a.equals(dVar.f4852a);
            }
            return false;
        }

        public int hashCode() {
            return ((((this.f4852a.startsWith("index_") ? -1184239155 : this.f4852a.hashCode()) * 31) + (this.f4853b ? 1 : 0)) * 31) + this.f4854c.hashCode();
        }

        public String toString() {
            return "Index{name='" + this.f4852a + "', unique=" + this.f4853b + ", columns=" + this.f4854c + '}';
        }
    }

    public e(String str, Map<String, a> map, Set<b> set, Set<d> set2) {
        this.f4832a = str;
        this.f4833b = Collections.unmodifiableMap(map);
        this.f4834c = Collections.unmodifiableSet(set);
        this.f4835d = set2 == null ? null : Collections.unmodifiableSet(set2);
    }

    public static e a(h1.b bVar, String str) {
        return new e(str, b(bVar, str), d(bVar, str), f(bVar, str));
    }

    public static Map<String, a> b(h1.b bVar, String str) {
        Cursor cursorJ0 = bVar.j0("PRAGMA table_info(`" + str + "`)");
        HashMap map = new HashMap();
        try {
            if (cursorJ0.getColumnCount() > 0) {
                int columnIndex = cursorJ0.getColumnIndex("name");
                int columnIndex2 = cursorJ0.getColumnIndex("type");
                int columnIndex3 = cursorJ0.getColumnIndex("notnull");
                int columnIndex4 = cursorJ0.getColumnIndex("pk");
                int columnIndex5 = cursorJ0.getColumnIndex("dflt_value");
                while (cursorJ0.moveToNext()) {
                    String string = cursorJ0.getString(columnIndex);
                    map.put(string, new a(string, cursorJ0.getString(columnIndex2), cursorJ0.getInt(columnIndex3) != 0, cursorJ0.getInt(columnIndex4), cursorJ0.getString(columnIndex5), 2));
                }
            }
            return map;
        } finally {
            cursorJ0.close();
        }
    }

    public static List<c> c(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("id");
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex("from");
        int columnIndex4 = cursor.getColumnIndex("to");
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < count; i8++) {
            cursor.moveToPosition(i8);
            arrayList.add(new c(cursor.getInt(columnIndex), cursor.getInt(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4)));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static Set<b> d(h1.b bVar, String str) {
        HashSet hashSet = new HashSet();
        Cursor cursorJ0 = bVar.j0("PRAGMA foreign_key_list(`" + str + "`)");
        try {
            int columnIndex = cursorJ0.getColumnIndex("id");
            int columnIndex2 = cursorJ0.getColumnIndex("seq");
            int columnIndex3 = cursorJ0.getColumnIndex("table");
            int columnIndex4 = cursorJ0.getColumnIndex("on_delete");
            int columnIndex5 = cursorJ0.getColumnIndex("on_update");
            List<c> listC = c(cursorJ0);
            int count = cursorJ0.getCount();
            for (int i8 = 0; i8 < count; i8++) {
                cursorJ0.moveToPosition(i8);
                if (cursorJ0.getInt(columnIndex2) == 0) {
                    int i9 = cursorJ0.getInt(columnIndex);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (c cVar : listC) {
                        if (cVar.f4848d == i9) {
                            arrayList.add(cVar.f4850f);
                            arrayList2.add(cVar.f4851g);
                        }
                    }
                    hashSet.add(new b(cursorJ0.getString(columnIndex3), cursorJ0.getString(columnIndex4), cursorJ0.getString(columnIndex5), arrayList, arrayList2));
                }
            }
            return hashSet;
        } finally {
            cursorJ0.close();
        }
    }

    public static d e(h1.b bVar, String str, boolean z7) {
        Cursor cursorJ0 = bVar.j0("PRAGMA index_xinfo(`" + str + "`)");
        try {
            int columnIndex = cursorJ0.getColumnIndex("seqno");
            int columnIndex2 = cursorJ0.getColumnIndex("cid");
            int columnIndex3 = cursorJ0.getColumnIndex("name");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                TreeMap treeMap = new TreeMap();
                while (cursorJ0.moveToNext()) {
                    if (cursorJ0.getInt(columnIndex2) >= 0) {
                        treeMap.put(Integer.valueOf(cursorJ0.getInt(columnIndex)), cursorJ0.getString(columnIndex3));
                    }
                }
                ArrayList arrayList = new ArrayList(treeMap.size());
                arrayList.addAll(treeMap.values());
                return new d(str, z7, arrayList);
            }
            return null;
        } finally {
            cursorJ0.close();
        }
    }

    public static Set<d> f(h1.b bVar, String str) {
        Cursor cursorJ0 = bVar.j0("PRAGMA index_list(`" + str + "`)");
        try {
            int columnIndex = cursorJ0.getColumnIndex("name");
            int columnIndex2 = cursorJ0.getColumnIndex("origin");
            int columnIndex3 = cursorJ0.getColumnIndex("unique");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                HashSet hashSet = new HashSet();
                while (cursorJ0.moveToNext()) {
                    if ("c".equals(cursorJ0.getString(columnIndex2))) {
                        String string = cursorJ0.getString(columnIndex);
                        boolean z7 = true;
                        if (cursorJ0.getInt(columnIndex3) != 1) {
                            z7 = false;
                        }
                        d dVarE = e(bVar, string, z7);
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
            cursorJ0.close();
        }
    }

    public boolean equals(Object obj) {
        Set<d> set;
        if (this == obj) {
            return true;
        }
        if (obj == null || e.class != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        String str = this.f4832a;
        if (str == null ? eVar.f4832a != null : !str.equals(eVar.f4832a)) {
            return false;
        }
        Map<String, a> map = this.f4833b;
        if (map == null ? eVar.f4833b != null : !map.equals(eVar.f4833b)) {
            return false;
        }
        Set<b> set2 = this.f4834c;
        if (set2 == null ? eVar.f4834c != null : !set2.equals(eVar.f4834c)) {
            return false;
        }
        Set<d> set3 = this.f4835d;
        if (set3 == null || (set = eVar.f4835d) == null) {
            return true;
        }
        return set3.equals(set);
    }

    public int hashCode() {
        String str = this.f4832a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Map<String, a> map = this.f4833b;
        int iHashCode2 = (iHashCode + (map != null ? map.hashCode() : 0)) * 31;
        Set<b> set = this.f4834c;
        return iHashCode2 + (set != null ? set.hashCode() : 0);
    }

    public String toString() {
        return "TableInfo{name='" + this.f4832a + "', columns=" + this.f4833b + ", foreignKeys=" + this.f4834c + ", indices=" + this.f4835d + '}';
    }
}
