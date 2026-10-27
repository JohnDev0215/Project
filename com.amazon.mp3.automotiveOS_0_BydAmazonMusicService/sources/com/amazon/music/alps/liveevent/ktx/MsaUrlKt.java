package com.amazon.music.alps.liveevent.ktx;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.msautils.MsaUrl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MsaUrl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u0019\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"toLiveEventUrl", "", "Lcom/amazon/music/msautils/MsaUrl;", ContextMappingConstants.PRIMARY_TEXT, ContextMappingConstants.SECONDARY_TEXT, "toMerchUrl", "showBadge", "", "(Lcom/amazon/music/msautils/MsaUrl;Ljava/lang/Boolean;)Ljava/lang/String;", "DMMALPS_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class MsaUrlKt {
    public static final String toLiveEventUrl(MsaUrl msaUrl, String primaryText, String secondaryText) {
        Intrinsics.checkNotNullParameter(msaUrl, "<this>");
        Intrinsics.checkNotNullParameter(primaryText, "primaryText");
        Intrinsics.checkNotNullParameter(secondaryText, "secondaryText");
        msaUrl.scaleToSquare(450);
        msaUrl.border(350, 8, 8, 8);
        msaUrl.crop(350, 350, 800, 450);
        msaUrl.centeredText(primaryText, 450, 135, 100, 350, "AmazonEmberBold", 70, 244, 62, 33, 5);
        msaUrl.centeredText(secondaryText, 450, 245, 100, 350, "AmazonEmberBold", 38, 255, 255, 255, 5);
        msaUrl.scaleToHeight(500);
        msaUrl.qualityPercent(100);
        msaUrl.forceMediaExtension("jpg");
        String url = msaUrl.toUrl();
        Intrinsics.checkNotNullExpressionValue(url, "toUrl()");
        return url;
    }

    public static final String toMerchUrl(MsaUrl msaUrl, Boolean bool) {
        Intrinsics.checkNotNullParameter(msaUrl, "<this>");
        msaUrl.upscaleToSquare(450);
        msaUrl.border(25, 255, 255, 255);
        if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
            msaUrl.stickerBottom("MerchBadge");
        }
        msaUrl.qualityPercent(100);
        msaUrl.forceMediaExtension("jpg");
        String url = msaUrl.toUrl();
        Intrinsics.checkNotNullExpressionValue(url, "toUrl()");
        return url;
    }
}
