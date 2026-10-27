package org.chromium.components.query_tiles;

import android.text.TextUtils;
import defpackage.gx0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public final class QueryTile extends gx0 {
    public final String d;
    public final List<QueryTile> e;
    public final List<GURL> f;
    public final List<String> g;

    public QueryTile(String str, String str2, String str3, String str4, GURL[] gurlArr, String[] strArr, List<QueryTile> list) {
        super(str, str2, str3);
        this.d = str4;
        this.f = Arrays.asList(gurlArr);
        this.g = strArr == null ? new ArrayList<>() : Arrays.asList(strArr);
        this.e = Collections.unmodifiableList(list == null ? new ArrayList<>() : list);
    }

    @Override // defpackage.gx0
    public final boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof QueryTile)) {
            return false;
        }
        QueryTile queryTile = (QueryTile) obj;
        if (!TextUtils.equals(this.d, queryTile.d)) {
            return false;
        }
        List<QueryTile> list = this.e;
        List<QueryTile> list2 = queryTile.e;
        if (list != null && !list.equals(list2)) {
            return false;
        }
        if (list == null && list2 != null) {
            return false;
        }
        List<GURL> list3 = this.f;
        List<GURL> list4 = queryTile.f;
        if (list3 == null || list3.equals(list4)) {
            return list3 != null || list4 == null;
        }
        return false;
    }
}
