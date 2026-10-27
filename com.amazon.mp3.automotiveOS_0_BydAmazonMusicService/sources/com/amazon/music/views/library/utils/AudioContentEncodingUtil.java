package com.amazon.music.views.library.utils;

import android.content.Context;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.R$string;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioContentEncodingUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J&\u0010\n\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\bJ\u001e\u0010\f\u001a\u00020\u000b2\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\b¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/views/library/utils/AudioContentEncodingUtil;", "", "Landroid/content/Context;", "context", "", "", ContextMappingConstants.CONTENT_ENCODING, "getAudioQualityText", "Lcom/amazon/music/subscription/UserSubscription;", "userSubscription", "getImmersiveBadgeText", "", "containsBothAtmosAnd360", "<init>", "()V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class AudioContentEncodingUtil {
    public static final AudioContentEncodingUtil INSTANCE = new AudioContentEncodingUtil();

    private AudioContentEncodingUtil() {
    }

    public final String getAudioQualityText(Context context, List<String> contentEncoding) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (contentEncoding == null) {
            return "";
        }
        if (contentEncoding.contains("uhdAvailable")) {
            String string = context.getResources().getString(R$string.katana_uhd_audio_badge);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…g.katana_uhd_audio_badge)");
            return string;
        }
        if (!contentEncoding.contains("hdAvailable")) {
            return "";
        }
        String string2 = context.getResources().getString(R$string.katana_hd_audio_badge);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ng.katana_hd_audio_badge)");
        return string2;
    }

    public final String getImmersiveBadgeText(Context context, List<String> contentEncoding, UserSubscription userSubscription) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        if (!userSubscription.isIvy() || contentEncoding == null) {
            return "";
        }
        if (contentEncoding.contains("ra360Available")) {
            String string = context.getResources().getString(R$string.ivy_ra360_badge);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…R.string.ivy_ra360_badge)");
            return string;
        }
        if (contentEncoding.contains("atmosAvailable")) {
            String string2 = context.getResources().getString(R$string.ivy_atmos_badge);
            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…R.string.ivy_atmos_badge)");
            return string2;
        }
        if (!contentEncoding.contains("immersive")) {
            return "";
        }
        String string3 = context.getResources().getString(R$string.immersive_audio_badge);
        Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…ng.immersive_audio_badge)");
        return string3;
    }

    public final boolean containsBothAtmosAnd360(List<String> contentEncoding, UserSubscription userSubscription) {
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        if (userSubscription.isIvy()) {
            if (contentEncoding != null && contentEncoding.contains("ra360Available")) {
                if (contentEncoding != null && contentEncoding.contains("atmosAvailable")) {
                    return true;
                }
            }
        }
        return false;
    }
}
