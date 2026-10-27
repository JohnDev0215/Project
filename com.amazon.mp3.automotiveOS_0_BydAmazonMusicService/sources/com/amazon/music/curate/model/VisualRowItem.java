package com.amazon.music.curate.model;

import android.view.ContextMenu;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VisualRowItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002<=B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u00107\u001a\u0002022\b\u00108\u001a\u0004\u0018\u000109H\u0096\u0002J\b\u0010:\u001a\u00020;H\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0019\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR.\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$RC\u0010%\u001a4\u0012\u0013\u0012\u00110'¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b((\u0012\u0013\u0012\u00110)¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u000b\u0018\u00010&¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010-\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0016R\u0013\u0010/\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0016R\u0011\u00101\u001a\u000202¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0011\u00105\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0016¨\u0006>"}, d2 = {"Lcom/amazon/music/curate/model/VisualRowItem;", "Lcom/amazon/music/curate/model/VisualModel;", "builder", "Lcom/amazon/music/curate/model/VisualRowItem$Builder;", "(Lcom/amazon/music/curate/model/VisualRowItem$Builder;)V", "addButton", "Lcom/amazon/music/curate/model/VisualRowItem$AddButton;", "getAddButton", "()Lcom/amazon/music/curate/model/VisualRowItem$AddButton;", "addLiveData", "Landroidx/lifecycle/LiveData;", "", "getAddLiveData", "()Landroidx/lifecycle/LiveData;", "expiryTime", "", "getExpiryTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "id", "", "getId", "()Ljava/lang/String;", "indexText", "getIndexText", "onClick", "Lkotlin/Function0;", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "onMoreClick", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "getOnMoreClick", "()Lkotlin/jvm/functions/Function1;", "onShowContextMenu", "Lkotlin/Function2;", "Landroid/view/ContextMenu;", "menu", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "getOnShowContextMenu", "()Lkotlin/jvm/functions/Function2;", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", "showDownloadStatus", "", "getShowDownloadStatus", "()Z", "thumbnailImageUrl", "getThumbnailImageUrl", "equals", "other", "", "hashCode", "", "AddButton", "Builder", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VisualRowItem implements VisualModel {
    private final AddButton addButton;
    private final LiveData<Unit> addLiveData;
    private final Long expiryTime;
    private final String id;
    private final String indexText;
    private final Function0<Unit> onClick;
    private final Function1<View, Unit> onMoreClick;
    private final Function2<ContextMenu, FragmentActivity, Unit> onShowContextMenu;
    private final String primaryText;
    private final String secondaryText;
    private final boolean showDownloadStatus;
    private final String thumbnailImageUrl;

    public /* synthetic */ VisualRowItem(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private VisualRowItem(Builder builder) {
        this.id = builder.getId();
        this.indexText = builder.getIndexText();
        this.thumbnailImageUrl = builder.getThumbnailImageUrl();
        this.primaryText = builder.getPrimaryText();
        this.secondaryText = builder.getSecondaryText();
        this.addButton = builder.getAddButton();
        this.showDownloadStatus = builder.getShowDownloadStatus();
        this.addLiveData = builder.getAddLiveData();
        this.onClick = builder.getOnClick();
        this.onShowContextMenu = builder.getOnShowContextMenu();
        this.onMoreClick = builder.getOnMoreClick();
        this.expiryTime = builder.getExpiryTime();
    }

    public final String getId() {
        return this.id;
    }

    public final String getIndexText() {
        return this.indexText;
    }

    public final String getThumbnailImageUrl() {
        return this.thumbnailImageUrl;
    }

    public final String getPrimaryText() {
        return this.primaryText;
    }

    public final String getSecondaryText() {
        return this.secondaryText;
    }

    public final AddButton getAddButton() {
        return this.addButton;
    }

    public final boolean getShowDownloadStatus() {
        return this.showDownloadStatus;
    }

    public final LiveData<Unit> getAddLiveData() {
        return this.addLiveData;
    }

    public final Function0<Unit> getOnClick() {
        return this.onClick;
    }

    public final Function2<ContextMenu, FragmentActivity, Unit> getOnShowContextMenu() {
        return this.onShowContextMenu;
    }

    public final Function1<View, Unit> getOnMoreClick() {
        return this.onMoreClick;
    }

    public final Long getExpiryTime() {
        return this.expiryTime;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(VisualRowItem.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            VisualRowItem visualRowItem = (VisualRowItem) other;
            return Intrinsics.areEqual(this.id, visualRowItem.id) && Intrinsics.areEqual(this.indexText, visualRowItem.indexText) && Intrinsics.areEqual(this.thumbnailImageUrl, visualRowItem.thumbnailImageUrl) && Intrinsics.areEqual(this.primaryText, visualRowItem.primaryText) && Intrinsics.areEqual(this.secondaryText, visualRowItem.secondaryText) && Intrinsics.areEqual(this.addButton, visualRowItem.addButton) && Intrinsics.areEqual(this.onClick, visualRowItem.onClick) && Intrinsics.areEqual(this.onShowContextMenu, visualRowItem.onShowContextMenu) && this.showDownloadStatus == visualRowItem.showDownloadStatus && Intrinsics.areEqual(this.expiryTime, visualRowItem.expiryTime);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.curate.model.VisualRowItem");
    }

    public int hashCode() {
        int iHashCode = this.id.hashCode() * 31;
        String str = this.indexText;
        int iHashCode2 = (((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.thumbnailImageUrl.hashCode()) * 31) + this.primaryText.hashCode()) * 31;
        String str2 = this.secondaryText;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        AddButton addButton = this.addButton;
        int iHashCode4 = (iHashCode3 + (addButton == null ? 0 : addButton.hashCode())) * 31;
        Function0<Unit> function0 = this.onClick;
        int iHashCode5 = (iHashCode4 + (function0 == null ? 0 : function0.hashCode())) * 31;
        Function2<ContextMenu, FragmentActivity, Unit> function2 = this.onShowContextMenu;
        int iHashCode6 = (((iHashCode5 + (function2 == null ? 0 : function2.hashCode())) * 31) + Boolean.hashCode(this.showDownloadStatus)) * 31;
        Long l = this.expiryTime;
        return iHashCode6 + (l != null ? l.hashCode() : 0);
    }

    /* JADX INFO: compiled from: VisualRowItem.kt */
    @Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0014\u0010\r\u001a\u00020\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0006\u0010F\u001a\u00020GJ\t\u0010H\u001a\u00020\u0003HÆ\u0003J\t\u0010I\u001a\u00020\u0003HÆ\u0003J\t\u0010J\u001a\u00020\u0003HÆ\u0003J'\u0010K\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010L\u001a\u00020@2\b\u0010M\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\t\u0010N\u001a\u00020OHÖ\u0001J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0003J\u0014\u0010!\u001a\u00020\u00002\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000f0\"J)\u0010'\u001a\u00020\u00002!\u0010'\u001a\u001d\u0012\u0013\u0012\u00110)¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u000f0(J>\u00101\u001a\u00020\u000026\u00101\u001a2\u0012\u0013\u0012\u001103¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(4\u0012\u0013\u0012\u001105¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u000f02J\u000e\u0010<\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\u0003J\u000e\u0010?\u001a\u00020\u00002\u0006\u0010?\u001a\u00020@J\t\u0010P\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001c\"\u0004\b\u001f\u0010 R\"\u0010!\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R7\u0010'\u001a\u001f\u0012\u0013\u0012\u00110)¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u000f\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100RL\u00101\u001a4\u0012\u0013\u0012\u001103¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(4\u0012\u0013\u0012\u001105¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u000f\u0018\u000102X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u001cR\u001c\u0010<\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u001c\"\u0004\b>\u0010 R\u001a\u0010?\u001a\u00020@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u001c¨\u0006Q"}, d2 = {"Lcom/amazon/music/curate/model/VisualRowItem$Builder;", "", "id", "", "thumbnailImageUrl", ContextMappingConstants.PRIMARY_TEXT, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "addButton", "Lcom/amazon/music/curate/model/VisualRowItem$AddButton;", "getAddButton", "()Lcom/amazon/music/curate/model/VisualRowItem$AddButton;", "setAddButton", "(Lcom/amazon/music/curate/model/VisualRowItem$AddButton;)V", "addLiveData", "Landroidx/lifecycle/LiveData;", "", "getAddLiveData", "()Landroidx/lifecycle/LiveData;", "setAddLiveData", "(Landroidx/lifecycle/LiveData;)V", "expiryTime", "", "getExpiryTime", "()Ljava/lang/Long;", "setExpiryTime", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getId", "()Ljava/lang/String;", "indexText", "getIndexText", "setIndexText", "(Ljava/lang/String;)V", "onClick", "Lkotlin/Function0;", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "setOnClick", "(Lkotlin/jvm/functions/Function0;)V", "onMoreClick", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "getOnMoreClick", "()Lkotlin/jvm/functions/Function1;", "setOnMoreClick", "(Lkotlin/jvm/functions/Function1;)V", "onShowContextMenu", "Lkotlin/Function2;", "Landroid/view/ContextMenu;", "menu", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "getOnShowContextMenu", "()Lkotlin/jvm/functions/Function2;", "setOnShowContextMenu", "(Lkotlin/jvm/functions/Function2;)V", "getPrimaryText", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", "setSecondaryText", "showDownloadStatus", "", "getShowDownloadStatus", "()Z", "setShowDownloadStatus", "(Z)V", "getThumbnailImageUrl", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/curate/model/VisualRowItem;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class Builder {
        private AddButton addButton;
        private LiveData<Unit> addLiveData;
        private Long expiryTime;
        private final String id;
        private String indexText;
        private Function0<Unit> onClick;
        private Function1<? super View, Unit> onMoreClick;
        private Function2<? super ContextMenu, ? super FragmentActivity, Unit> onShowContextMenu;
        private final String primaryText;
        private String secondaryText;
        private boolean showDownloadStatus;
        private final String thumbnailImageUrl;

        public static /* synthetic */ Builder copy$default(Builder builder, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = builder.id;
            }
            if ((i & 2) != 0) {
                str2 = builder.thumbnailImageUrl;
            }
            if ((i & 4) != 0) {
                str3 = builder.primaryText;
            }
            return builder.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getThumbnailImageUrl() {
            return this.thumbnailImageUrl;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPrimaryText() {
            return this.primaryText;
        }

        public final Builder copy(String id, String thumbnailImageUrl, String primaryText) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(thumbnailImageUrl, "thumbnailImageUrl");
            Intrinsics.checkNotNullParameter(primaryText, "primaryText");
            return new Builder(id, thumbnailImageUrl, primaryText);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) other;
            return Intrinsics.areEqual(this.id, builder.id) && Intrinsics.areEqual(this.thumbnailImageUrl, builder.thumbnailImageUrl) && Intrinsics.areEqual(this.primaryText, builder.primaryText);
        }

        public int hashCode() {
            return (((this.id.hashCode() * 31) + this.thumbnailImageUrl.hashCode()) * 31) + this.primaryText.hashCode();
        }

        public String toString() {
            return "Builder(id=" + this.id + ", thumbnailImageUrl=" + this.thumbnailImageUrl + ", primaryText=" + this.primaryText + ')';
        }

        public Builder(String id, String thumbnailImageUrl, String primaryText) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(thumbnailImageUrl, "thumbnailImageUrl");
            Intrinsics.checkNotNullParameter(primaryText, "primaryText");
            this.id = id;
            this.thumbnailImageUrl = thumbnailImageUrl;
            this.primaryText = primaryText;
        }

        public final String getId() {
            return this.id;
        }

        public final String getThumbnailImageUrl() {
            return this.thumbnailImageUrl;
        }

        public final String getPrimaryText() {
            return this.primaryText;
        }

        public final String getIndexText() {
            return this.indexText;
        }

        public final void setIndexText(String str) {
            this.indexText = str;
        }

        public final String getSecondaryText() {
            return this.secondaryText;
        }

        public final void setSecondaryText(String str) {
            this.secondaryText = str;
        }

        public final Function0<Unit> getOnClick() {
            return this.onClick;
        }

        public final void setOnClick(Function0<Unit> function0) {
            this.onClick = function0;
        }

        public final Function2<ContextMenu, FragmentActivity, Unit> getOnShowContextMenu() {
            return this.onShowContextMenu;
        }

        public final void setOnShowContextMenu(Function2<? super ContextMenu, ? super FragmentActivity, Unit> function2) {
            this.onShowContextMenu = function2;
        }

        public final LiveData<Unit> getAddLiveData() {
            return this.addLiveData;
        }

        public final void setAddLiveData(LiveData<Unit> liveData) {
            this.addLiveData = liveData;
        }

        public final AddButton getAddButton() {
            return this.addButton;
        }

        public final void setAddButton(AddButton addButton) {
            this.addButton = addButton;
        }

        public final Function1<View, Unit> getOnMoreClick() {
            return this.onMoreClick;
        }

        public final void setOnMoreClick(Function1<? super View, Unit> function1) {
            this.onMoreClick = function1;
        }

        public final boolean getShowDownloadStatus() {
            return this.showDownloadStatus;
        }

        public final void setShowDownloadStatus(boolean z) {
            this.showDownloadStatus = z;
        }

        public final Long getExpiryTime() {
            return this.expiryTime;
        }

        public final void setExpiryTime(Long l) {
            this.expiryTime = l;
        }

        public final Builder indexText(String indexText) {
            Intrinsics.checkNotNullParameter(indexText, "indexText");
            setIndexText(indexText);
            return this;
        }

        public final Builder secondaryText(String secondaryText) {
            Intrinsics.checkNotNullParameter(secondaryText, "secondaryText");
            setSecondaryText(secondaryText);
            return this;
        }

        public final Builder addButton(AddButton addButton) {
            Intrinsics.checkNotNullParameter(addButton, "addButton");
            setAddButton(addButton);
            return this;
        }

        public final Builder addLiveData(LiveData<Unit> addLiveData) {
            Intrinsics.checkNotNullParameter(addLiveData, "addLiveData");
            setAddLiveData(addLiveData);
            return this;
        }

        public final Builder onShowContextMenu(Function2<? super ContextMenu, ? super FragmentActivity, Unit> onShowContextMenu) {
            Intrinsics.checkNotNullParameter(onShowContextMenu, "onShowContextMenu");
            setOnShowContextMenu(onShowContextMenu);
            return this;
        }

        public final Builder onClick(Function0<Unit> onClick) {
            Intrinsics.checkNotNullParameter(onClick, "onClick");
            setOnClick(onClick);
            return this;
        }

        public final Builder onMoreClick(Function1<? super View, Unit> onMoreClick) {
            Intrinsics.checkNotNullParameter(onMoreClick, "onMoreClick");
            setOnMoreClick(onMoreClick);
            return this;
        }

        public final Builder showDownloadStatus(boolean showDownloadStatus) {
            setShowDownloadStatus(showDownloadStatus);
            return this;
        }

        public final Builder expiryTime(long expiryTime) {
            setExpiryTime(Long.valueOf(expiryTime));
            return this;
        }

        public final VisualRowItem build() {
            return new VisualRowItem(this, null);
        }
    }

    /* JADX INFO: compiled from: VisualRowItem.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/curate/model/VisualRowItem$AddButton;", "", "style", "Lcom/amazon/music/curate/model/AddButtonStyle;", "click", "Lkotlin/Function0;", "", "(Lcom/amazon/music/curate/model/AddButtonStyle;Lkotlin/jvm/functions/Function0;)V", "getClick", "()Lkotlin/jvm/functions/Function0;", "getStyle", "()Lcom/amazon/music/curate/model/AddButtonStyle;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class AddButton {
        private final Function0<Unit> click;
        private final AddButtonStyle style;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AddButton copy$default(AddButton addButton, AddButtonStyle addButtonStyle, Function0 function0, int i, Object obj) {
            if ((i & 1) != 0) {
                addButtonStyle = addButton.style;
            }
            if ((i & 2) != 0) {
                function0 = addButton.click;
            }
            return addButton.copy(addButtonStyle, function0);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AddButtonStyle getStyle() {
            return this.style;
        }

        public final Function0<Unit> component2() {
            return this.click;
        }

        public final AddButton copy(AddButtonStyle style, Function0<Unit> click) {
            Intrinsics.checkNotNullParameter(style, "style");
            Intrinsics.checkNotNullParameter(click, "click");
            return new AddButton(style, click);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AddButton)) {
                return false;
            }
            AddButton addButton = (AddButton) other;
            return this.style == addButton.style && Intrinsics.areEqual(this.click, addButton.click);
        }

        public int hashCode() {
            return (this.style.hashCode() * 31) + this.click.hashCode();
        }

        public String toString() {
            return "AddButton(style=" + this.style + ", click=" + this.click + ')';
        }

        public AddButton(AddButtonStyle style, Function0<Unit> click) {
            Intrinsics.checkNotNullParameter(style, "style");
            Intrinsics.checkNotNullParameter(click, "click");
            this.style = style;
            this.click = click;
        }

        public final AddButtonStyle getStyle() {
            return this.style;
        }

        public final Function0<Unit> getClick() {
            return this.click;
        }
    }
}
