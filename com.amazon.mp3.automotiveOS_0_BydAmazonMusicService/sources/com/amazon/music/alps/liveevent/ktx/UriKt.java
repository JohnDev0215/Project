package com.amazon.music.alps.liveevent.ktx;

import android.net.Uri;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Uri.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003¨\u0006\u0005"}, d2 = {"withRefAndTag", "Landroid/net/Uri;", "refMarker", "", "associateTag", "DMMALPS_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class UriKt {
    public static final Uri withRefAndTag(Uri uri, String refMarker, String associateTag) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        Intrinsics.checkNotNullParameter(refMarker, "refMarker");
        Intrinsics.checkNotNullParameter(associateTag, "associateTag");
        Uri.Builder builderBuildUpon = uri.buildUpon();
        if (refMarker.length() > 0) {
            builderBuildUpon.appendQueryParameter("ref", refMarker);
        }
        if (associateTag.length() > 0) {
            builderBuildUpon.appendQueryParameter(ParserUtil.TAG_QUERY_PARAM_NAME, associateTag);
        }
        Uri uriBuild = builderBuildUpon.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "builder.build()");
        return uriBuild;
    }
}
