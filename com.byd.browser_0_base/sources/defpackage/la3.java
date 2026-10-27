package defpackage;

import java.util.List;
import org.chromium.components.query_tiles.QueryTile;

/* JADX INFO: compiled from: TileUmaLogger.java */
/* JADX INFO: loaded from: classes3.dex */
public final class la3 {
    public final String a = "QueryTiles.NTP";
    public List<QueryTile> b;

    public static int a(QueryTile queryTile, String str, int i) {
        if (str.equals(queryTile.a)) {
            return i;
        }
        int i2 = (i + 1) * 100;
        int i3 = 0;
        while (true) {
            List<QueryTile> list = queryTile.e;
            if (i3 >= list.size()) {
                return -1;
            }
            int iA = a(list.get(i3), str, i2 + i3);
            if (iA != -1) {
                return iA;
            }
            i3++;
        }
    }
}
