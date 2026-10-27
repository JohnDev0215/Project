package com.amazon.mp3.navigation.webtargetconverter;

import android.content.Context;
import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.upsellweb.ManageCampaignPageWebTargetBuilder;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.mp3.web.webtargetbuilder.MusicWebTargetEndpoints;
import com.amazon.music.destination.CampaignDestination;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CampaignWebTargetConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/navigation/webtargetconverter/CampaignWebTargetConverter;", "", "Lcom/amazon/music/destination/CampaignDestination;", "Landroid/content/Context;", "context", "destination", "Lcom/amazon/mp3/web/webtarget/WebTarget;", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class CampaignWebTargetConverter {
    public WebTarget convert(Context context, CampaignDestination destination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        AccountInfo accountInfo = new AccountInfo(context);
        MusicWebTargetEndpoints musicWebTargetEndpoints = new MusicWebTargetEndpoints();
        DeviceAttributes deviceAttributes = new DeviceAttributes(context);
        List<String> segments = destination.getSegments();
        Intrinsics.checkNotNullExpressionValue(segments, "destination.segments");
        WebTarget webTargetBuild = new ManageCampaignPageWebTargetBuilder(accountInfo, musicWebTargetEndpoints, deviceAttributes, segments).withQueryParamsToForward(destination.getQueryParameters()).build();
        Intrinsics.checkNotNullExpressionValue(webTargetBuild, "ManageCampaignPageWebTar…\n                .build()");
        return webTargetBuild;
    }
}
