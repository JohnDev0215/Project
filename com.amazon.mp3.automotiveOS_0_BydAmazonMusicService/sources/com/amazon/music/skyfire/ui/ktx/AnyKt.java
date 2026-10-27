package com.amazon.music.skyfire.ui.ktx;

import android.R;
import android.app.Activity;
import android.net.Uri;
import android.util.SparseArray;
import android.view.View;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Any.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010!\n\u0000\u001a0\u0010\u0005\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u0001H\u0007H\u0086\u0002¢\u0006\u0002\u0010\f\u001a\n\u0010\r\u001a\u00020\u000e*\u00020\u000f\u001a\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0011\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0012\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0013"}, d2 = {"contentView", "Landroid/view/View;", "Landroid/app/Activity;", "getContentView", "(Landroid/app/Activity;)Landroid/view/View;", "set", "", "T", "Landroid/util/SparseArray;", "key", "", "value", "(Landroid/util/SparseArray;ILjava/lang/Object;)V", "toUri", "Landroid/net/Uri;", "", "unmodifiable", "", "", "DMSFCoreAndroidUI_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class AnyKt {
    public static final Uri toUri(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Uri uri = Uri.parse(str);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
        return uri;
    }

    public static final View getContentView(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        View viewFindViewById = activity.findViewById(R.id.content);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(android.R.id.content)");
        return viewFindViewById;
    }

    public static final <T> List<T> unmodifiable(List<T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        List<T> listUnmodifiableList = Collections.unmodifiableList(list);
        Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList<T>(this)");
        return listUnmodifiableList;
    }

    public static final <T> void set(SparseArray<T> sparseArray, int i, T t) {
        Intrinsics.checkNotNullParameter(sparseArray, "<this>");
        sparseArray.put(i, t);
    }
}
