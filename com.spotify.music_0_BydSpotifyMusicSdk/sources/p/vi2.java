package p;

import android.database.Cursor;
import com.spotify.connectivity.productstate.RxProductState;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.collections.builders.ListBuilder;
import kotlin.collections.builders.MapBuilder;
import kotlin.collections.builders.SetBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class vi2 {
    public final String a;
    public final Map b;
    public final Set c;
    public final Set d;

    public vi2(String str, Map map, AbstractSet abstractSet, AbstractSet abstractSet2) {
        this.a = str;
        this.b = map;
        this.c = abstractSet;
        this.d = abstractSet2;
    }

    /* JADX WARN: Finally extract failed */
    public static final vi2 a(androidx.sqlite.db.framework.b bVar, String str) {
        Map mapH0;
        List listH0;
        SetBuilder setBuilder;
        int i;
        int i2;
        String str2;
        int i3;
        Throwable th;
        ui2 ui2Var;
        androidx.sqlite.db.framework.b bVar2 = bVar;
        StringBuilder sb = new StringBuilder("PRAGMA table_info(`");
        sb.append(str);
        String str3 = "`)";
        sb.append("`)");
        Cursor cursorC = bVar2.c(sb.toString());
        try {
            String str4 = "name";
            if (cursorC.getColumnCount() <= 0) {
                mapH0 = kotlin.collections.c.h0();
                ux2.e(cursorC, null);
            } else {
                int columnIndex = cursorC.getColumnIndex("name");
                int columnIndex2 = cursorC.getColumnIndex(RxProductState.Keys.KEY_TYPE);
                int columnIndex3 = cursorC.getColumnIndex("notnull");
                int columnIndex4 = cursorC.getColumnIndex("pk");
                int columnIndex5 = cursorC.getColumnIndex("dflt_value");
                MapBuilder mapBuilder = new MapBuilder();
                while (cursorC.moveToNext()) {
                    String string = cursorC.getString(columnIndex);
                    String string2 = cursorC.getString(columnIndex2);
                    boolean z = cursorC.getInt(columnIndex3) != 0;
                    int i4 = cursorC.getInt(columnIndex4);
                    String string3 = cursorC.getString(columnIndex5);
                    v03.i(string, "name");
                    v03.i(string2, RxProductState.Keys.KEY_TYPE);
                    mapBuilder.put(string, new ri2(string, string2, z, i4, string3, 2));
                    columnIndex = columnIndex;
                }
                mapBuilder.g();
                ux2.e(cursorC, null);
                mapH0 = mapBuilder;
            }
            cursorC = bVar2.c("PRAGMA foreign_key_list(`" + str + "`)");
            try {
                int columnIndex6 = cursorC.getColumnIndex("id");
                int columnIndex7 = cursorC.getColumnIndex("seq");
                int columnIndex8 = cursorC.getColumnIndex("table");
                int columnIndex9 = cursorC.getColumnIndex("on_delete");
                int columnIndex10 = cursorC.getColumnIndex("on_update");
                int columnIndex11 = cursorC.getColumnIndex("id");
                int columnIndex12 = cursorC.getColumnIndex("seq");
                int columnIndex13 = cursorC.getColumnIndex("from");
                int columnIndex14 = cursorC.getColumnIndex("to");
                ListBuilder listBuilder = new ListBuilder();
                while (cursorC.moveToNext()) {
                    String str5 = str4;
                    int i5 = cursorC.getInt(columnIndex11);
                    int i6 = columnIndex11;
                    int i7 = cursorC.getInt(columnIndex12);
                    int i8 = columnIndex12;
                    String string4 = cursorC.getString(columnIndex13);
                    int i9 = columnIndex13;
                    v03.i(string4, "cursor.getString(fromColumnIndex)");
                    String string5 = cursorC.getString(columnIndex14);
                    v03.i(string5, "cursor.getString(toColumnIndex)");
                    listBuilder.add(new ti2(i5, string4, i7, string5));
                    mapH0 = mapH0;
                    str4 = str5;
                    columnIndex11 = i6;
                    columnIndex12 = i8;
                    columnIndex13 = i9;
                    columnIndex14 = columnIndex14;
                }
                Map map = mapH0;
                String str6 = str4;
                listBuilder.m();
                if (listBuilder.a() <= 1) {
                    listH0 = ou.n0(listBuilder);
                } else {
                    Object[] array = listBuilder.toArray(new Comparable[0]);
                    Comparable[] comparableArr = (Comparable[]) array;
                    if (comparableArr.length > 1) {
                        Arrays.sort(comparableArr);
                    }
                    listH0 = rb.h0(array);
                }
                cursorC.moveToPosition(-1);
                SetBuilder setBuilder2 = new SetBuilder();
                while (cursorC.moveToNext()) {
                    if (cursorC.getInt(columnIndex7) == 0) {
                        int i10 = cursorC.getInt(columnIndex6);
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        ArrayList<ti2> arrayList3 = new ArrayList();
                        for (Object obj : listH0) {
                            List list = listH0;
                            if (((ti2) obj).c == i10) {
                                arrayList3.add(obj);
                            }
                            listH0 = list;
                        }
                        List list2 = listH0;
                        for (ti2 ti2Var : arrayList3) {
                            arrayList.add(ti2Var.k);
                            arrayList2.add(ti2Var.l);
                        }
                        String string6 = cursorC.getString(columnIndex8);
                        v03.i(string6, "cursor.getString(tableColumnIndex)");
                        String string7 = cursorC.getString(columnIndex9);
                        v03.i(string7, "cursor.getString(onDeleteColumnIndex)");
                        String string8 = cursorC.getString(columnIndex10);
                        v03.i(string8, "cursor.getString(onUpdateColumnIndex)");
                        setBuilder2.add(new si2(string6, string7, string8, arrayList, arrayList2));
                        columnIndex6 = columnIndex6;
                        listH0 = list2;
                    }
                }
                setBuilder2.b();
                ux2.e(cursorC, null);
                cursorC = bVar2.c("PRAGMA index_list(`" + str + "`)");
                String str7 = str6;
                try {
                    int columnIndex15 = cursorC.getColumnIndex(str7);
                    int columnIndex16 = cursorC.getColumnIndex("origin");
                    int columnIndex17 = cursorC.getColumnIndex("unique");
                    if (columnIndex15 == -1 || columnIndex16 == -1 || columnIndex17 == -1) {
                        ux2.e(cursorC, null);
                        setBuilder = null;
                    } else {
                        setBuilder = new SetBuilder();
                        while (true) {
                            if (!cursorC.moveToNext()) {
                                setBuilder.b();
                                ux2.e(cursorC, null);
                                break;
                            }
                            if (v03.b("c", cursorC.getString(columnIndex16))) {
                                String string9 = cursorC.getString(columnIndex15);
                                boolean z2 = cursorC.getInt(columnIndex17) == 1;
                                v03.i(string9, str7);
                                cursorC = bVar2.c("PRAGMA index_xinfo(`" + string9 + str3);
                                try {
                                    int columnIndex18 = cursorC.getColumnIndex("seqno");
                                    int columnIndex19 = cursorC.getColumnIndex("cid");
                                    int columnIndex20 = cursorC.getColumnIndex(str7);
                                    int columnIndex21 = cursorC.getColumnIndex("desc");
                                    String str8 = str7;
                                    if (columnIndex18 == -1 || columnIndex19 == -1 || columnIndex20 == -1 || columnIndex21 == -1) {
                                        i = columnIndex15;
                                        i2 = columnIndex16;
                                        str2 = str3;
                                        i3 = columnIndex17;
                                        th = null;
                                        ux2.e(cursorC, null);
                                        ui2Var = null;
                                    } else {
                                        TreeMap treeMap = new TreeMap();
                                        i = columnIndex15;
                                        TreeMap treeMap2 = new TreeMap();
                                        while (cursorC.moveToNext()) {
                                            if (cursorC.getInt(columnIndex19) >= 0) {
                                                int i11 = cursorC.getInt(columnIndex18);
                                                int i12 = columnIndex16;
                                                String string10 = cursorC.getString(columnIndex20);
                                                int i13 = columnIndex21;
                                                String str9 = cursorC.getInt(columnIndex21) > 0 ? "DESC" : "ASC";
                                                String str10 = str3;
                                                Integer numValueOf = Integer.valueOf(i11);
                                                v03.i(string10, "columnName");
                                                treeMap.put(numValueOf, string10);
                                                treeMap2.put(Integer.valueOf(i11), str9);
                                                columnIndex16 = i12;
                                                str3 = str10;
                                                columnIndex21 = i13;
                                                columnIndex17 = columnIndex17;
                                            }
                                        }
                                        i2 = columnIndex16;
                                        str2 = str3;
                                        i3 = columnIndex17;
                                        Collection collectionValues = treeMap.values();
                                        v03.i(collectionValues, "columnsMap.values");
                                        List listN0 = ou.n0(collectionValues);
                                        Collection collectionValues2 = treeMap2.values();
                                        v03.i(collectionValues2, "ordersMap.values");
                                        ui2Var = new ui2(string9, z2, listN0, ou.n0(collectionValues2));
                                        ux2.e(cursorC, null);
                                        th = null;
                                    }
                                    if (ui2Var == null) {
                                        ux2.e(cursorC, th);
                                        setBuilder = null;
                                        break;
                                    }
                                    setBuilder.add(ui2Var);
                                    bVar2 = bVar;
                                    str7 = str8;
                                    columnIndex15 = i;
                                    columnIndex16 = i2;
                                    str3 = str2;
                                    columnIndex17 = i3;
                                } finally {
                                }
                            }
                        }
                    }
                    return new vi2(str, map, setBuilder2, setBuilder);
                } finally {
                }
            } catch (Throwable th2) {
                try {
                    throw th2;
                } finally {
                }
            }
        } finally {
            try {
                throw th2;
            } finally {
            }
        }
    }

    public final boolean equals(Object obj) {
        Set set;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof vi2)) {
            return false;
        }
        vi2 vi2Var = (vi2) obj;
        if (!v03.b(this.a, vi2Var.a) || !v03.b(this.b, vi2Var.b) || !v03.b(this.c, vi2Var.c)) {
            return false;
        }
        Set set2 = this.d;
        if (set2 == null || (set = vi2Var.d) == null) {
            return true;
        }
        return v03.b(set2, set);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "TableInfo{name='" + this.a + "', columns=" + this.b + ", foreignKeys=" + this.c + ", indices=" + this.d + '}';
    }
}
