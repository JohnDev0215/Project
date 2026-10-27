package a1;

import android.database.Cursor;
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
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f0a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<String, C0000a> f1b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set<b> f2c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Set<d> f3d;

    /* JADX INFO: renamed from: a1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TableInfo.java */
    public static class C0000a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f4a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f5b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f6c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f7d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f8e;

        public C0000a(String str, String str2, boolean z6, int i7) {
            this.f4a = str;
            this.f5b = str2;
            this.f7d = z6;
            this.f8e = i7;
            this.f6c = a(str2);
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

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C0000a c0000a = (C0000a) obj;
            return this.f8e == c0000a.f8e && this.f4a.equals(c0000a.f4a) && this.f7d == c0000a.f7d && this.f6c == c0000a.f6c;
        }

        public int hashCode() {
            return (((((this.f4a.hashCode() * 31) + this.f6c) * 31) + (this.f7d ? 1231 : 1237)) * 31) + this.f8e;
        }

        public String toString() {
            return "Column{name='" + this.f4a + "', type='" + this.f5b + "', affinity='" + this.f6c + "', notNull=" + this.f7d + ", primaryKeyPosition=" + this.f8e + '}';
        }
    }

    /* JADX INFO: compiled from: TableInfo.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f9a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f10b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final String f11c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final List<String> f12d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final List<String> f13e;

        public b(String str, String str2, String str3, List<String> list, List<String> list2) {
            this.f9a = str;
            this.f10b = str2;
            this.f11c = str3;
            this.f12d = Collections.unmodifiableList(list);
            this.f13e = Collections.unmodifiableList(list2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.f9a.equals(bVar.f9a) && this.f10b.equals(bVar.f10b) && this.f11c.equals(bVar.f11c) && this.f12d.equals(bVar.f12d)) {
                return this.f13e.equals(bVar.f13e);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.f9a.hashCode() * 31) + this.f10b.hashCode()) * 31) + this.f11c.hashCode()) * 31) + this.f12d.hashCode()) * 31) + this.f13e.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable='" + this.f9a + "', onDelete='" + this.f10b + "', onUpdate='" + this.f11c + "', columnNames=" + this.f12d + ", referenceColumnNames=" + this.f13e + '}';
        }
    }

    /* JADX INFO: compiled from: TableInfo.java */
    static class c implements Comparable<c> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final int f14e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final int f15g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final String f16h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final String f17i;

        c(int i7, int i8, String str, String str2) {
            this.f14e = i7;
            this.f15g = i8;
            this.f16h = str;
            this.f17i = str2;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(c cVar) {
            int i7 = this.f14e - cVar.f14e;
            return i7 == 0 ? this.f15g - cVar.f15g : i7;
        }
    }

    /* JADX INFO: compiled from: TableInfo.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f18a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f19b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List<String> f20c;

        public d(String str, boolean z6, List<String> list) {
            this.f18a = str;
            this.f19b = z6;
            this.f20c = list;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            d dVar = (d) obj;
            if (this.f19b == dVar.f19b && this.f20c.equals(dVar.f20c)) {
                return this.f18a.startsWith("index_") ? dVar.f18a.startsWith("index_") : this.f18a.equals(dVar.f18a);
            }
            return false;
        }

        public int hashCode() {
            return ((((this.f18a.startsWith("index_") ? -1184239155 : this.f18a.hashCode()) * 31) + (this.f19b ? 1 : 0)) * 31) + this.f20c.hashCode();
        }

        public String toString() {
            return "Index{name='" + this.f18a + "', unique=" + this.f19b + ", columns=" + this.f20c + '}';
        }
    }

    public a(String str, Map<String, C0000a> map, Set<b> set, Set<d> set2) {
        this.f0a = str;
        this.f1b = Collections.unmodifiableMap(map);
        this.f2c = Collections.unmodifiableSet(set);
        this.f3d = set2 == null ? null : Collections.unmodifiableSet(set2);
    }

    public static a a(b1.b bVar, String str) {
        return new a(str, b(bVar, str), d(bVar, str), f(bVar, str));
    }

    private static Map<String, C0000a> b(b1.b bVar, String str) {
        Cursor cursorO = bVar.O("PRAGMA table_info(`" + str + "`)");
        HashMap map = new HashMap();
        try {
            if (cursorO.getColumnCount() > 0) {
                int columnIndex = cursorO.getColumnIndex("name");
                int columnIndex2 = cursorO.getColumnIndex("type");
                int columnIndex3 = cursorO.getColumnIndex("notnull");
                int columnIndex4 = cursorO.getColumnIndex("pk");
                while (cursorO.moveToNext()) {
                    String string = cursorO.getString(columnIndex);
                    map.put(string, new C0000a(string, cursorO.getString(columnIndex2), cursorO.getInt(columnIndex3) != 0, cursorO.getInt(columnIndex4)));
                }
            }
            return map;
        } finally {
            cursorO.close();
        }
    }

    private static List<c> c(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("id");
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex("from");
        int columnIndex4 = cursor.getColumnIndex("to");
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < count; i7++) {
            cursor.moveToPosition(i7);
            arrayList.add(new c(cursor.getInt(columnIndex), cursor.getInt(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4)));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static Set<b> d(b1.b bVar, String str) {
        HashSet hashSet = new HashSet();
        Cursor cursorO = bVar.O("PRAGMA foreign_key_list(`" + str + "`)");
        try {
            int columnIndex = cursorO.getColumnIndex("id");
            int columnIndex2 = cursorO.getColumnIndex("seq");
            int columnIndex3 = cursorO.getColumnIndex("table");
            int columnIndex4 = cursorO.getColumnIndex("on_delete");
            int columnIndex5 = cursorO.getColumnIndex("on_update");
            List<c> listC = c(cursorO);
            int count = cursorO.getCount();
            for (int i7 = 0; i7 < count; i7++) {
                cursorO.moveToPosition(i7);
                if (cursorO.getInt(columnIndex2) == 0) {
                    int i8 = cursorO.getInt(columnIndex);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (c cVar : listC) {
                        if (cVar.f14e == i8) {
                            arrayList.add(cVar.f16h);
                            arrayList2.add(cVar.f17i);
                        }
                    }
                    hashSet.add(new b(cursorO.getString(columnIndex3), cursorO.getString(columnIndex4), cursorO.getString(columnIndex5), arrayList, arrayList2));
                }
            }
            return hashSet;
        } finally {
            cursorO.close();
        }
    }

    private static d e(b1.b bVar, String str, boolean z6) {
        Cursor cursorO = bVar.O("PRAGMA index_xinfo(`" + str + "`)");
        try {
            int columnIndex = cursorO.getColumnIndex("seqno");
            int columnIndex2 = cursorO.getColumnIndex("cid");
            int columnIndex3 = cursorO.getColumnIndex("name");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                TreeMap treeMap = new TreeMap();
                while (cursorO.moveToNext()) {
                    if (cursorO.getInt(columnIndex2) >= 0) {
                        treeMap.put(Integer.valueOf(cursorO.getInt(columnIndex)), cursorO.getString(columnIndex3));
                    }
                }
                ArrayList arrayList = new ArrayList(treeMap.size());
                arrayList.addAll(treeMap.values());
                return new d(str, z6, arrayList);
            }
            return null;
        } finally {
            cursorO.close();
        }
    }

    private static Set<d> f(b1.b bVar, String str) {
        Cursor cursorO = bVar.O("PRAGMA index_list(`" + str + "`)");
        try {
            int columnIndex = cursorO.getColumnIndex("name");
            int columnIndex2 = cursorO.getColumnIndex("origin");
            int columnIndex3 = cursorO.getColumnIndex("unique");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                HashSet hashSet = new HashSet();
                while (cursorO.moveToNext()) {
                    if ("c".equals(cursorO.getString(columnIndex2))) {
                        String string = cursorO.getString(columnIndex);
                        boolean z6 = true;
                        if (cursorO.getInt(columnIndex3) != 1) {
                            z6 = false;
                        }
                        d dVarE = e(bVar, string, z6);
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
            cursorO.close();
        }
    }

    public boolean equals(Object obj) {
        Set<d> set;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        String str = this.f0a;
        if (str == null ? aVar.f0a != null : !str.equals(aVar.f0a)) {
            return false;
        }
        Map<String, C0000a> map = this.f1b;
        if (map == null ? aVar.f1b != null : !map.equals(aVar.f1b)) {
            return false;
        }
        Set<b> set2 = this.f2c;
        if (set2 == null ? aVar.f2c != null : !set2.equals(aVar.f2c)) {
            return false;
        }
        Set<d> set3 = this.f3d;
        if (set3 == null || (set = aVar.f3d) == null) {
            return true;
        }
        return set3.equals(set);
    }

    public int hashCode() {
        String str = this.f0a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Map<String, C0000a> map = this.f1b;
        int iHashCode2 = (iHashCode + (map != null ? map.hashCode() : 0)) * 31;
        Set<b> set = this.f2c;
        return iHashCode2 + (set != null ? set.hashCode() : 0);
    }

    public String toString() {
        return "TableInfo{name='" + this.f0a + "', columns=" + this.f1b + ", foreignKeys=" + this.f2c + ", indices=" + this.f3d + '}';
    }
}
