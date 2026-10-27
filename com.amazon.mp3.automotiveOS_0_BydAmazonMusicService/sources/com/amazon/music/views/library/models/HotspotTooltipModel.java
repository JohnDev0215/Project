package com.amazon.music.views.library.models;

import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HotspotTooltipModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/views/library/models/HotspotTooltipModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "title", "", "text", "buttonText", "hotspotTag", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "getButtonText", "()Ljava/lang/String;", "getHotspotTag", "getPageType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getText", "getTitle", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HotspotTooltipModel extends BaseViewModel {
    private final String buttonText;
    private final String hotspotTag;
    private final PageType pageType;
    private final String text;
    private final String title;

    public final String getTitle() {
        return this.title;
    }

    public final String getText() {
        return this.text;
    }

    public final String getButtonText() {
        return this.buttonText;
    }

    public final String getHotspotTag() {
        return this.hotspotTag;
    }

    public final PageType getPageType() {
        return this.pageType;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HotspotTooltipModel(String title, String text, String buttonText, String hotspotTag, PageType pageType) {
        super(null, 0 == true ? 1 : 0, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(buttonText, "buttonText");
        Intrinsics.checkNotNullParameter(hotspotTag, "hotspotTag");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.title = title;
        this.text = text;
        this.buttonText = buttonText;
        this.hotspotTag = hotspotTag;
        this.pageType = pageType;
    }
}
