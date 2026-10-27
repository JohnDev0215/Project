package org.chromium.components.query_tiles.bridges;

import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.query_tiles.QueryTile;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class TileConversionBridge {
    @CalledByNative
    public static List<QueryTile> createList() {
        return new ArrayList();
    }

    @CalledByNative
    public static QueryTile createTileAndMaybeAddToList(List<QueryTile> list, String str, String str2, String str3, String str4, GURL[] gurlArr, String[] strArr, List<QueryTile> list2) {
        QueryTile queryTile = new QueryTile(str, str2, str3, str4, gurlArr, strArr, list2);
        if (list != null) {
            list.add(queryTile);
        }
        return queryTile;
    }
}
