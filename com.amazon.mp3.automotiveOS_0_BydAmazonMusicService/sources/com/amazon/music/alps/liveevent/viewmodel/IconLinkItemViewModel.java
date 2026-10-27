package com.amazon.music.alps.liveevent.viewmodel;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IconLinkItemViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/alps/liveevent/viewmodel/IconLinkItemViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", ContentUtils.KEY_VIEW_REFERENCE_ID, "", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "iconUrl", "linkText", "linkTarget", "(Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getIconUrl", "()Ljava/lang/String;", "getLinkTarget", "getLinkText", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IconLinkItemViewModel extends BaseViewModel {
    private final ActionType actionType;
    private final String iconUrl;
    private final String linkTarget;
    private final String linkText;

    public /* synthetic */ IconLinkItemViewModel(String str, ActionType actionType, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, actionType, str2, str3, str4);
    }

    public final ActionType getActionType() {
        return this.actionType;
    }

    public final String getIconUrl() {
        return this.iconUrl;
    }

    public final String getLinkText() {
        return this.linkText;
    }

    public final String getLinkTarget() {
        return this.linkTarget;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IconLinkItemViewModel(String str, ActionType actionType, String str2, String str3, String str4) {
        super(str, null, 2, null);
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        this.actionType = actionType;
        this.iconUrl = str2;
        this.linkText = str3;
        this.linkTarget = str4;
    }
}
