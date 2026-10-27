package com.amazon.music.curate.data;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: PlaylistCollage.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistCollage;", "", "()V", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistCollage {
    private static final String BACKGROUND_START_TAG = "_BG";
    private static final String BLUR_START_TAG = "_BL";
    private static final String COLLAGE_START_TAG = "_CLa";
    private static final String DIMENSION_SEPARATOR = ",";
    private static final char DOT = '.';
    private static final char END_TAG = '_';
    private static final String SECTION_SEPARATOR = "%7C";
    private static final String SIZES_SEPARATOR = "+";
    private static final String UPSCALE_SQUARE_START_TAG = "_US";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG = LoggerFactory.getLogger(PlaylistCollage.class.getSimpleName());

    /* JADX INFO: compiled from: PlaylistCollage.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004J\u0016\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistCollage$Companion;", "", "()V", "BACKGROUND_START_TAG", "", "BLUR_START_TAG", "COLLAGE_START_TAG", "DIMENSION_SEPARATOR", "DOT", "", "END_TAG", "LOG", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "SECTION_SEPARATOR", "SIZES_SEPARATOR", "UPSCALE_SQUARE_START_TAG", "isCollageUrl", "", ImagesContract.URL, "updateSize", "size", "", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isCollageUrl(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return StringsKt__StringsKt.contains$default((CharSequence) url, (CharSequence) PlaylistCollage.COLLAGE_START_TAG, false, 2, (Object) null);
        }

        public final String updateSize(String url, int size) {
            Intrinsics.checkNotNullParameter(url, "url");
            if (!StringsKt__StringsKt.contains$default((CharSequence) url, (CharSequence) "m.media-amazon.com/images/", false, 2, (Object) null)) {
                PlaylistCollage.LOG.debug("URL is not a media central URL");
                return url;
            }
            if (!StringsKt__StringsKt.contains$default((CharSequence) url, (CharSequence) PlaylistCollage.COLLAGE_START_TAG, false, 2, (Object) null)) {
                PlaylistCollage.LOG.debug("URL is not a collage URL");
                return url;
            }
            PlaylistCollage.LOG.debug(Intrinsics.stringPlus("Sizing url to ", Integer.valueOf(size)));
            String strSubstring = url.substring(0, StringsKt__StringsKt.indexOf$default((CharSequence) url, "._", 0, false, 6, (Object) null));
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            String strSubstring2 = url.substring(StringsKt__StringsKt.lastIndexOf$default((CharSequence) url, ".", 0, false, 6, (Object) null), url.length());
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
            int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.UPSCALE_SQUARE_START_TAG, 0, false, 6, (Object) null) + 3;
            String strSubstring3 = url.substring(iIndexOf$default, StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.END_TAG, iIndexOf$default, false, 4, (Object) null));
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
            int i = Integer.parseInt(strSubstring3);
            final float f = size / i;
            PlaylistCollage.LOG.debug("Scaling " + i + " to " + size + " (ratio " + f + ')');
            int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.BLUR_START_TAG, 0, false, 6, (Object) null) + 3;
            String strSubstring4 = url.substring(iIndexOf$default2, StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.END_TAG, iIndexOf$default2, false, 4, (Object) null));
            Intrinsics.checkNotNullExpressionValue(strSubstring4, "this as java.lang.String…ing(startIndex, endIndex)");
            int i2 = Integer.parseInt(strSubstring4);
            int iIndexOf$default3 = StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.BACKGROUND_START_TAG, 0, false, 6, (Object) null) + 3;
            int iIndexOf$default4 = StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.DIMENSION_SEPARATOR, iIndexOf$default3, false, 4, (Object) null);
            String strSubstring5 = url.substring(iIndexOf$default3, iIndexOf$default4);
            Intrinsics.checkNotNullExpressionValue(strSubstring5, "this as java.lang.String…ing(startIndex, endIndex)");
            int i3 = iIndexOf$default4 + 1;
            int iIndexOf$default5 = StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.DIMENSION_SEPARATOR, i3, false, 4, (Object) null);
            String strSubstring6 = url.substring(i3, iIndexOf$default5);
            Intrinsics.checkNotNullExpressionValue(strSubstring6, "this as java.lang.String…ing(startIndex, endIndex)");
            int i4 = iIndexOf$default5 + 1;
            String strSubstring7 = url.substring(i4, StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.END_TAG, i4, false, 4, (Object) null));
            Intrinsics.checkNotNullExpressionValue(strSubstring7, "this as java.lang.String…ing(startIndex, endIndex)");
            int iIndexOf$default6 = StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.COLLAGE_START_TAG, 0, false, 6, (Object) null) + 4 + 3;
            int iIndexOf$default7 = StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.DIMENSION_SEPARATOR, iIndexOf$default6, false, 4, (Object) null);
            String strSubstring8 = url.substring(iIndexOf$default6, iIndexOf$default7);
            Intrinsics.checkNotNullExpressionValue(strSubstring8, "this as java.lang.String…ing(startIndex, endIndex)");
            Integer.parseInt(strSubstring8);
            int i5 = iIndexOf$default7 + 1;
            int iIndexOf$default8 = StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.SECTION_SEPARATOR, i5, false, 4, (Object) null);
            String strSubstring9 = url.substring(i5, iIndexOf$default8);
            Intrinsics.checkNotNullExpressionValue(strSubstring9, "this as java.lang.String…ing(startIndex, endIndex)");
            Integer.parseInt(strSubstring9);
            int i6 = iIndexOf$default8 + 3;
            int iIndexOf$default9 = StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.SECTION_SEPARATOR, i6, false, 4, (Object) null);
            String strSubstring10 = url.substring(i6, iIndexOf$default9);
            Intrinsics.checkNotNullExpressionValue(strSubstring10, "this as java.lang.String…ing(startIndex, endIndex)");
            int i7 = iIndexOf$default9 + 3;
            String strSubstring11 = url.substring(i7, StringsKt__StringsKt.indexOf$default((CharSequence) url, PlaylistCollage.DOT, i7, false, 4, (Object) null));
            Intrinsics.checkNotNullExpressionValue(strSubstring11, "this as java.lang.String…ing(startIndex, endIndex)");
            List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) strSubstring11, new String[]{"+"}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                arrayList.add(CollectionsKt___CollectionsKt.joinToString$default(StringsKt__StringsKt.split$default((CharSequence) it.next(), new String[]{PlaylistCollage.DIMENSION_SEPARATOR}, false, 0, 6, (Object) null), PlaylistCollage.DIMENSION_SEPARATOR, null, null, 0, null, new Function1<String, CharSequence>() { // from class: com.amazon.music.curate.data.PlaylistCollage$Companion$updateSize$sizes$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final CharSequence invoke(String dimen) {
                        Intrinsics.checkNotNullParameter(dimen, "dimen");
                        return String.valueOf((int) (Float.parseFloat(dimen) * f));
                    }
                }, 30, null));
            }
            String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, "+", null, null, 0, null, null, 62, null);
            PlaylistCollage.LOG.debug(Intrinsics.stringPlus("sizes are ", strJoinToString$default));
            String str = strSubstring + PlaylistCollage.DOT + PlaylistCollage.UPSCALE_SQUARE_START_TAG + size + PlaylistCollage.BLUR_START_TAG + String.valueOf(i2) + PlaylistCollage.BACKGROUND_START_TAG + strSubstring5 + PlaylistCollage.DIMENSION_SEPARATOR + strSubstring6 + PlaylistCollage.DIMENSION_SEPARATOR + strSubstring7 + PlaylistCollage.COLLAGE_START_TAG + PlaylistCollage.SECTION_SEPARATOR + size + PlaylistCollage.DIMENSION_SEPARATOR + size + PlaylistCollage.SECTION_SEPARATOR + strSubstring10 + PlaylistCollage.SECTION_SEPARATOR + strJoinToString$default + strSubstring2;
            Intrinsics.checkNotNullExpressionValue(str, "StringBuilder()\n        …              .toString()");
            PlaylistCollage.LOG.debug(Intrinsics.stringPlus("original URL is ", url));
            PlaylistCollage.LOG.debug(Intrinsics.stringPlus("output URL is ", str));
            return str;
        }
    }
}
