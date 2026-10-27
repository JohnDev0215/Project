package com.amazon.mp3.merch;

import android.net.Uri;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MerchUriUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J \u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u0002¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/merch/MerchUriUtils;", "", "Lcom/amazon/mp3/merch/MerchMshopCampaign;", "campaign", "", "artistId", "getRefMarker", "merchAsin", "Landroid/net/Uri;", "getMerchUri", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class MerchUriUtils {
    public static final MerchUriUtils INSTANCE = new MerchUriUtils();

    private MerchUriUtils() {
    }

    public final String getRefMarker(MerchMshopCampaign campaign, String artistId) {
        Intrinsics.checkNotNullParameter(campaign, "campaign");
        Intrinsics.checkNotNullParameter(artistId, "artistId");
        return campaign.getValue() + "Android_" + artistId;
    }

    public static /* synthetic */ Uri getMerchUri$default(MerchUriUtils merchUriUtils, String str, String str2, MerchMshopCampaign merchMshopCampaign, int i, Object obj) {
        if ((i & 4) != 0) {
            merchMshopCampaign = MerchMshopCampaign.LISTING;
        }
        return merchUriUtils.getMerchUri(str, str2, merchMshopCampaign);
    }

    public final Uri getMerchUri(String merchAsin, String artistId, MerchMshopCampaign campaign) {
        Intrinsics.checkNotNullParameter(merchAsin, "merchAsin");
        Intrinsics.checkNotNullParameter(artistId, "artistId");
        Intrinsics.checkNotNullParameter(campaign, "campaign");
        String str = String.format("https://www.amazon.com/dp/%1$s/ref=%2$s", Arrays.copyOf(new Object[]{merchAsin, getRefMarker(campaign, artistId)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
        Uri uri = Uri.parse(str);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
        return uri;
    }
}
