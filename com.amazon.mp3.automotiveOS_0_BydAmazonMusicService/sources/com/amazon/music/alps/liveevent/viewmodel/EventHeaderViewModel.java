package com.amazon.music.alps.liveevent.viewmodel;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.platform.graphql.generated.fragment.Lineup;
import com.amazon.music.views.library.models.ButtonModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: EventHeaderViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u007f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012R\u0013\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0013\u0010\r\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016¨\u0006\""}, d2 = {"Lcom/amazon/music/alps/liveevent/viewmodel/EventHeaderViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", ContentUtils.KEY_VIEW_REFERENCE_ID, "", ContextMappingConstants.LABEL, "headline", ContextMappingConstants.PRIMARY_TEXT, ContextMappingConstants.SECONDARY_TEXT, "thumbnail", "primaryButton", "Lcom/amazon/music/views/library/models/ButtonModel;", "secondaryButton", "addToCalendarButton", "shareButton", "showCountdown", "", "nextLineup", "Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/ButtonModel;Lcom/amazon/music/views/library/models/ButtonModel;Lcom/amazon/music/views/library/models/ButtonModel;Lcom/amazon/music/views/library/models/ButtonModel;ZLcom/amazon/music/platform/graphql/generated/fragment/Lineup;)V", "getAddToCalendarButton", "()Lcom/amazon/music/views/library/models/ButtonModel;", "getHeadline", "()Ljava/lang/String;", "getLabel", "getNextLineup", "()Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;", "getPrimaryButton", "getPrimaryText", "getSecondaryButton", "getSecondaryText", "getShareButton", "getShowCountdown", "()Z", "getThumbnail", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EventHeaderViewModel extends BaseViewModel {
    private final ButtonModel addToCalendarButton;
    private final String headline;
    private final String label;
    private final Lineup nextLineup;
    private final ButtonModel primaryButton;
    private final String primaryText;
    private final ButtonModel secondaryButton;
    private final String secondaryText;
    private final ButtonModel shareButton;
    private final boolean showCountdown;
    private final String thumbnail;

    public /* synthetic */ EventHeaderViewModel(String str, String str2, String str3, String str4, String str5, String str6, ButtonModel buttonModel, ButtonModel buttonModel2, ButtonModel buttonModel3, ButtonModel buttonModel4, boolean z, Lineup lineup, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, str3, str4, str5, str6, buttonModel, buttonModel2, buttonModel3, buttonModel4, (i & 1024) != 0 ? false : z, lineup);
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getHeadline() {
        return this.headline;
    }

    public final String getPrimaryText() {
        return this.primaryText;
    }

    public final String getSecondaryText() {
        return this.secondaryText;
    }

    public final String getThumbnail() {
        return this.thumbnail;
    }

    public final ButtonModel getPrimaryButton() {
        return this.primaryButton;
    }

    public final ButtonModel getSecondaryButton() {
        return this.secondaryButton;
    }

    public final ButtonModel getAddToCalendarButton() {
        return this.addToCalendarButton;
    }

    public final ButtonModel getShareButton() {
        return this.shareButton;
    }

    public final boolean getShowCountdown() {
        return this.showCountdown;
    }

    public final Lineup getNextLineup() {
        return this.nextLineup;
    }

    public EventHeaderViewModel(String str, String str2, String str3, String str4, String str5, String str6, ButtonModel buttonModel, ButtonModel buttonModel2, ButtonModel buttonModel3, ButtonModel buttonModel4, boolean z, Lineup lineup) {
        super(str, null, 2, null);
        this.label = str2;
        this.headline = str3;
        this.primaryText = str4;
        this.secondaryText = str5;
        this.thumbnail = str6;
        this.primaryButton = buttonModel;
        this.secondaryButton = buttonModel2;
        this.addToCalendarButton = buttonModel3;
        this.shareButton = buttonModel4;
        this.showCountdown = z;
        this.nextLineup = lineup;
    }
}
