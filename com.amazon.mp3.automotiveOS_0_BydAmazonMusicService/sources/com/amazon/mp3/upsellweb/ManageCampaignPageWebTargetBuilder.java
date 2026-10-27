package com.amazon.mp3.upsellweb;

import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder;
import com.amazon.mp3.web.webtargetbuilder.MusicWebTargetEndpoints;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ManageCampaignPageWebTargetBuilder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B'\b\u0000\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0013\u0010\u0014B7\b\u0010\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002¢\u0006\u0004\b\u0013\u0010\u0015J\u0018\u0010\u0006\u001a\u00020\u00052\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u0002R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/upsellweb/ManageCampaignPageWebTargetBuilder;", "Lcom/amazon/mp3/web/webtargetbuilder/BaseWebTargetBuilder;", "", "", "segments", "", "addPath", "", "isCampaignPath", "Z", "()Z", "setCampaignPath", "(Z)V", "Lcom/amazon/mp3/account/AccountInfo;", "accountInfo", "Lcom/amazon/mp3/web/webtargetbuilder/MusicWebTargetEndpoints;", "endpoints", "Lcom/amazon/mp3/account/DeviceAttributes;", "deviceAttributes", "<init>", "(Lcom/amazon/mp3/account/AccountInfo;Lcom/amazon/mp3/web/webtargetbuilder/MusicWebTargetEndpoints;Lcom/amazon/mp3/account/DeviceAttributes;)V", "(Lcom/amazon/mp3/account/AccountInfo;Lcom/amazon/mp3/web/webtargetbuilder/MusicWebTargetEndpoints;Lcom/amazon/mp3/account/DeviceAttributes;Ljava/util/List;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ManageCampaignPageWebTargetBuilder extends BaseWebTargetBuilder {
    private boolean isCampaignPath;

    public ManageCampaignPageWebTargetBuilder(AccountInfo accountInfo, MusicWebTargetEndpoints musicWebTargetEndpoints, DeviceAttributes deviceAttributes) {
        super(accountInfo, musicWebTargetEndpoints, deviceAttributes);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ManageCampaignPageWebTargetBuilder(AccountInfo accountInfo, MusicWebTargetEndpoints musicWebTargetEndpoints, DeviceAttributes deviceAttributes, List<String> segments) {
        this(accountInfo, musicWebTargetEndpoints, deviceAttributes);
        Intrinsics.checkNotNullParameter(segments, "segments");
        if (!segments.isEmpty()) {
            this.isCampaignPath = true;
        }
        addPath(segments);
    }

    private final void addPath(List<String> segments) {
        if (!this.isCampaignPath) {
            this.uriBuilder.path("/campaign/invite");
            return;
        }
        this.uriBuilder.path("/campaign");
        Iterator<String> it = segments.iterator();
        while (it.hasNext()) {
            this.uriBuilder.appendPath(it.next());
        }
    }
}
