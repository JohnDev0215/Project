package com.amazon.music.alps.liveevent.viewmodel;

import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SponsoredImageItemViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u0006R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0004\u001a\u0004\b\u0011\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/alps/liveevent/viewmodel/SponsoredImageItemViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;", "pageSubType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;", "getPageSubType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;", "liveEventId", "getLiveEventId", "imageUrl", "getImageUrl", "externalLink", "getExternalLink", "<init>", "(Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class SponsoredImageItemViewModel extends BaseViewModel {
    private final String externalLink;
    private final String imageUrl;
    private final String liveEventId;
    private final PageSubType pageSubType;
    private final String viewId;

    public SponsoredImageItemViewModel() {
        this(null, null, null, null, null, 31, null);
    }

    public /* synthetic */ SponsoredImageItemViewModel(String str, PageSubType pageSubType, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : pageSubType, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4);
    }

    public final PageSubType getPageSubType() {
        return this.pageSubType;
    }

    public final String getLiveEventId() {
        return this.liveEventId;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final String getExternalLink() {
        return this.externalLink;
    }

    public SponsoredImageItemViewModel(String str, PageSubType pageSubType, String str2, String str3, String str4) {
        super(str, null, 2, null);
        this.viewId = str;
        this.pageSubType = pageSubType;
        this.liveEventId = str2;
        this.imageUrl = str3;
        this.externalLink = str4;
    }
}
