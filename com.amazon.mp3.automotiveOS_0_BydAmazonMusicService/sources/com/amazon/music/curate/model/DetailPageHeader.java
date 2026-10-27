package com.amazon.music.curate.model;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DetailPageHeader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001&B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0019\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0019\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0019\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0019\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0019\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0013\u0010 \u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\bR\u0013\u0010\"\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\bR\u0013\u0010$\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\b¨\u0006'"}, d2 = {"Lcom/amazon/music/curate/model/DetailPageHeader;", "Lcom/amazon/music/curate/model/VisualModel;", "builder", "Lcom/amazon/music/curate/model/DetailPageHeader$Builder;", "(Lcom/amazon/music/curate/model/DetailPageHeader$Builder;)V", "headlineText", "", "getHeadlineText", "()Ljava/lang/String;", "id", "getId", "imageUrl", "getImageUrl", "labelText", "getLabelText", "onDownloadButtonClick", "Lkotlin/Function0;", "", "getOnDownloadButtonClick", "()Lkotlin/jvm/functions/Function0;", "onEditButtonClick", "getOnEditButtonClick", "onMoreButtonClick", "getOnMoreButtonClick", "onPlayButtonClick", "getOnPlayButtonClick", "onShareButtonClick", "getOnShareButtonClick", "onShuffleButtonClick", "getOnShuffleButtonClick", "onStationButtonClick", "getOnStationButtonClick", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", ContextMappingConstants.TERTIARY_TEXT, "getTertiaryText", "Builder", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DetailPageHeader implements VisualModel {
    private final String headlineText;
    private final String id;
    private final String imageUrl;
    private final String labelText;
    private final Function0<Unit> onDownloadButtonClick;
    private final Function0<Unit> onEditButtonClick;
    private final Function0<Unit> onMoreButtonClick;
    private final Function0<Unit> onPlayButtonClick;
    private final Function0<Unit> onShareButtonClick;
    private final Function0<Unit> onShuffleButtonClick;
    private final Function0<Unit> onStationButtonClick;
    private final String primaryText;
    private final String secondaryText;
    private final String tertiaryText;

    public /* synthetic */ DetailPageHeader(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private DetailPageHeader(Builder builder) {
        this.id = builder.getId();
        this.labelText = builder.getLabelText();
        this.headlineText = builder.getHeadlineText();
        this.imageUrl = builder.getImageUrl();
        this.primaryText = builder.getPrimaryText();
        this.secondaryText = builder.getSecondaryText();
        this.tertiaryText = builder.getTertiaryText();
        this.onPlayButtonClick = builder.getOnPlayButtonClick();
        this.onShuffleButtonClick = builder.getOnShuffleButtonClick();
        this.onDownloadButtonClick = builder.getOnDownloadButtonClick();
        this.onEditButtonClick = builder.getOnEditButtonClick();
        this.onShareButtonClick = builder.getOnShareButtonClick();
        this.onStationButtonClick = builder.getOnStationButtonClick();
        this.onMoreButtonClick = builder.getOnMoreButtonClick();
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabelText() {
        return this.labelText;
    }

    public final String getHeadlineText() {
        return this.headlineText;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final String getPrimaryText() {
        return this.primaryText;
    }

    public final String getSecondaryText() {
        return this.secondaryText;
    }

    public final String getTertiaryText() {
        return this.tertiaryText;
    }

    public final Function0<Unit> getOnPlayButtonClick() {
        return this.onPlayButtonClick;
    }

    public final Function0<Unit> getOnShuffleButtonClick() {
        return this.onShuffleButtonClick;
    }

    public final Function0<Unit> getOnDownloadButtonClick() {
        return this.onDownloadButtonClick;
    }

    public final Function0<Unit> getOnEditButtonClick() {
        return this.onEditButtonClick;
    }

    public final Function0<Unit> getOnShareButtonClick() {
        return this.onShareButtonClick;
    }

    public final Function0<Unit> getOnStationButtonClick() {
        return this.onStationButtonClick;
    }

    public final Function0<Unit> getOnMoreButtonClick() {
        return this.onMoreButtonClick;
    }

    /* JADX INFO: compiled from: DetailPageHeader.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0006\u00101\u001a\u000202J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J'\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020;HÖ\u0001J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0003J\u0014\u0010\u000f\u001a\u00020\u00002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u0014\u0010\u0016\u001a\u00020\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u0014\u0010\u0019\u001a\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u0014\u0010\u001c\u001a\u00020\u00002\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u0014\u0010\u001f\u001a\u00020\u00002\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u0014\u0010\"\u001a\u00020\u00002\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u0014\u0010%\u001a\u00020\u00002\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u000e\u0010(\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\u0003J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010+\u001a\u00020\u0003J\u000e\u0010.\u001a\u00020\u00002\u0006\u0010.\u001a\u00020\u0003J\t\u0010<\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\"\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\"\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R\"\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\"\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015R\"\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015R\u001c\u0010(\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\b\"\u0004\b*\u0010\rR\u001c\u0010+\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\b\"\u0004\b-\u0010\rR\u001c\u0010.\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\b\"\u0004\b0\u0010\r¨\u0006="}, d2 = {"Lcom/amazon/music/curate/model/DetailPageHeader$Builder;", "", "id", "", "labelText", "headlineText", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getHeadlineText", "()Ljava/lang/String;", "getId", "imageUrl", "getImageUrl", "setImageUrl", "(Ljava/lang/String;)V", "getLabelText", "onDownloadButtonClick", "Lkotlin/Function0;", "", "getOnDownloadButtonClick", "()Lkotlin/jvm/functions/Function0;", "setOnDownloadButtonClick", "(Lkotlin/jvm/functions/Function0;)V", "onEditButtonClick", "getOnEditButtonClick", "setOnEditButtonClick", "onMoreButtonClick", "getOnMoreButtonClick", "setOnMoreButtonClick", "onPlayButtonClick", "getOnPlayButtonClick", "setOnPlayButtonClick", "onShareButtonClick", "getOnShareButtonClick", "setOnShareButtonClick", "onShuffleButtonClick", "getOnShuffleButtonClick", "setOnShuffleButtonClick", "onStationButtonClick", "getOnStationButtonClick", "setOnStationButtonClick", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", "setPrimaryText", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", "setSecondaryText", ContextMappingConstants.TERTIARY_TEXT, "getTertiaryText", "setTertiaryText", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/curate/model/DetailPageHeader;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class Builder {
        private final String headlineText;
        private final String id;
        private String imageUrl;
        private final String labelText;
        private Function0<Unit> onDownloadButtonClick;
        private Function0<Unit> onEditButtonClick;
        private Function0<Unit> onMoreButtonClick;
        private Function0<Unit> onPlayButtonClick;
        private Function0<Unit> onShareButtonClick;
        private Function0<Unit> onShuffleButtonClick;
        private Function0<Unit> onStationButtonClick;
        private String primaryText;
        private String secondaryText;
        private String tertiaryText;

        public static /* synthetic */ Builder copy$default(Builder builder, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = builder.id;
            }
            if ((i & 2) != 0) {
                str2 = builder.labelText;
            }
            if ((i & 4) != 0) {
                str3 = builder.headlineText;
            }
            return builder.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLabelText() {
            return this.labelText;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getHeadlineText() {
            return this.headlineText;
        }

        public final Builder copy(String id, String labelText, String headlineText) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(labelText, "labelText");
            Intrinsics.checkNotNullParameter(headlineText, "headlineText");
            return new Builder(id, labelText, headlineText);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) other;
            return Intrinsics.areEqual(this.id, builder.id) && Intrinsics.areEqual(this.labelText, builder.labelText) && Intrinsics.areEqual(this.headlineText, builder.headlineText);
        }

        public int hashCode() {
            return (((this.id.hashCode() * 31) + this.labelText.hashCode()) * 31) + this.headlineText.hashCode();
        }

        public String toString() {
            return "Builder(id=" + this.id + ", labelText=" + this.labelText + ", headlineText=" + this.headlineText + ')';
        }

        public Builder(String id, String labelText, String headlineText) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(labelText, "labelText");
            Intrinsics.checkNotNullParameter(headlineText, "headlineText");
            this.id = id;
            this.labelText = labelText;
            this.headlineText = headlineText;
        }

        public final String getId() {
            return this.id;
        }

        public final String getLabelText() {
            return this.labelText;
        }

        public final String getHeadlineText() {
            return this.headlineText;
        }

        public final String getImageUrl() {
            return this.imageUrl;
        }

        public final void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public final String getPrimaryText() {
            return this.primaryText;
        }

        public final void setPrimaryText(String str) {
            this.primaryText = str;
        }

        public final String getSecondaryText() {
            return this.secondaryText;
        }

        public final void setSecondaryText(String str) {
            this.secondaryText = str;
        }

        public final String getTertiaryText() {
            return this.tertiaryText;
        }

        public final void setTertiaryText(String str) {
            this.tertiaryText = str;
        }

        public final Function0<Unit> getOnPlayButtonClick() {
            return this.onPlayButtonClick;
        }

        public final void setOnPlayButtonClick(Function0<Unit> function0) {
            this.onPlayButtonClick = function0;
        }

        public final Function0<Unit> getOnShuffleButtonClick() {
            return this.onShuffleButtonClick;
        }

        public final void setOnShuffleButtonClick(Function0<Unit> function0) {
            this.onShuffleButtonClick = function0;
        }

        public final Function0<Unit> getOnDownloadButtonClick() {
            return this.onDownloadButtonClick;
        }

        public final void setOnDownloadButtonClick(Function0<Unit> function0) {
            this.onDownloadButtonClick = function0;
        }

        public final Function0<Unit> getOnEditButtonClick() {
            return this.onEditButtonClick;
        }

        public final void setOnEditButtonClick(Function0<Unit> function0) {
            this.onEditButtonClick = function0;
        }

        public final Function0<Unit> getOnShareButtonClick() {
            return this.onShareButtonClick;
        }

        public final void setOnShareButtonClick(Function0<Unit> function0) {
            this.onShareButtonClick = function0;
        }

        public final Function0<Unit> getOnStationButtonClick() {
            return this.onStationButtonClick;
        }

        public final void setOnStationButtonClick(Function0<Unit> function0) {
            this.onStationButtonClick = function0;
        }

        public final Function0<Unit> getOnMoreButtonClick() {
            return this.onMoreButtonClick;
        }

        public final void setOnMoreButtonClick(Function0<Unit> function0) {
            this.onMoreButtonClick = function0;
        }

        public final Builder imageUrl(String imageUrl) {
            Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
            setImageUrl(imageUrl);
            return this;
        }

        public final Builder primaryText(String primaryText) {
            Intrinsics.checkNotNullParameter(primaryText, "primaryText");
            setPrimaryText(primaryText);
            return this;
        }

        public final Builder secondaryText(String secondaryText) {
            Intrinsics.checkNotNullParameter(secondaryText, "secondaryText");
            setSecondaryText(secondaryText);
            return this;
        }

        public final Builder tertiaryText(String tertiaryText) {
            Intrinsics.checkNotNullParameter(tertiaryText, "tertiaryText");
            setTertiaryText(tertiaryText);
            return this;
        }

        public final Builder onPlayButtonClick(Function0<Unit> onPlayButtonClick) {
            Intrinsics.checkNotNullParameter(onPlayButtonClick, "onPlayButtonClick");
            setOnPlayButtonClick(onPlayButtonClick);
            return this;
        }

        public final Builder onShuffleButtonClick(Function0<Unit> onShuffleButtonClick) {
            Intrinsics.checkNotNullParameter(onShuffleButtonClick, "onShuffleButtonClick");
            setOnShuffleButtonClick(onShuffleButtonClick);
            return this;
        }

        public final Builder onDownloadButtonClick(Function0<Unit> onDownloadButtonClick) {
            Intrinsics.checkNotNullParameter(onDownloadButtonClick, "onDownloadButtonClick");
            setOnDownloadButtonClick(onDownloadButtonClick);
            return this;
        }

        public final Builder onEditButtonClick(Function0<Unit> onEditButtonClick) {
            Intrinsics.checkNotNullParameter(onEditButtonClick, "onEditButtonClick");
            setOnEditButtonClick(onEditButtonClick);
            return this;
        }

        public final Builder onShareButtonClick(Function0<Unit> onShareButtonClick) {
            Intrinsics.checkNotNullParameter(onShareButtonClick, "onShareButtonClick");
            setOnShareButtonClick(onShareButtonClick);
            return this;
        }

        public final Builder onStationButtonClick(Function0<Unit> onStationButtonClick) {
            Intrinsics.checkNotNullParameter(onStationButtonClick, "onStationButtonClick");
            setOnStationButtonClick(onStationButtonClick);
            return this;
        }

        public final Builder onMoreButtonClick(Function0<Unit> onMoreButtonClick) {
            Intrinsics.checkNotNullParameter(onMoreButtonClick, "onMoreButtonClick");
            setOnMoreButtonClick(onMoreButtonClick);
            return this;
        }

        public final DetailPageHeader build() {
            return new DetailPageHeader(this, null);
        }
    }
}
