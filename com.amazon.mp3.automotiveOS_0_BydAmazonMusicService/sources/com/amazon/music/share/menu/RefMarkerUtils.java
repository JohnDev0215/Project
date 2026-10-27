package com.amazon.music.share.menu;

import android.net.Uri;
import com.google.android.gms.common.internal.ImagesContract;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: RefMarkerUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0003\u001a\u00020\u0002J\u0018\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002¨\u0006\t"}, d2 = {"Lcom/amazon/music/share/menu/RefMarkerUtils;", "", "", "generateRefMarker", ImagesContract.URL, "refMarker", "appendRefMarker", "<init>", "()V", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final class RefMarkerUtils {
    public static final RefMarkerUtils INSTANCE = new RefMarkerUtils();

    private RefMarkerUtils() {
    }

    public final String generateRefMarker() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        sb.append("dm_sh_");
        IntRange intRangeUntil = RangesKt___RangesKt.until(0, 25);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            ((IntIterator) it).nextInt();
            arrayList.add(Character.valueOf("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(secureRandom.nextInt(62))));
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            sb.append(((Character) it2.next()).charValue());
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String appendRefMarker(String url, String refMarker) {
        Intrinsics.checkNotNullParameter(url, "url");
        if (refMarker == null) {
            return url;
        }
        String queryParameter = Uri.parse(url).getQueryParameter("ref");
        if (queryParameter != null && StringsKt__StringsKt.contains$default((CharSequence) queryParameter, (CharSequence) refMarker, false, 2, (Object) null)) {
            return url;
        }
        String string = Uri.parse(url).buildUpon().appendQueryParameter("ref", refMarker).build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "parse(url)\n             …              .toString()");
        return string;
    }
}
