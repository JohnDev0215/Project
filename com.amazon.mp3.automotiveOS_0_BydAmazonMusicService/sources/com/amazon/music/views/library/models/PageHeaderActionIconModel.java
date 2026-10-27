package com.amazon.music.views.library.models;

import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.google.firebase.perf.util.Constants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageHeaderActionIconModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u0000 $2\u00020\u0001:\u0002%$BE\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\"\u0010#J\u0013\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0016R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u0017\u0010\u0014\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0014\u0010\r\u001a\u0004\b\u0014\u0010\u000eR$\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006&"}, d2 = {"Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;", "", "other", "", "equals", "", "hashCode", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "actionType", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "getActionType", "()Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", Constants.ENABLE_DISABLE, "Z", "()Z", "setEnabled", "(Z)V", "canClickWhenDisabled", "getCanClickWhenDisabled", "setCanClickWhenDisabled", "isLargeIcon", "", "largeButtonText", "Ljava/lang/String;", "getLargeButtonText", "()Ljava/lang/String;", "setLargeButtonText", "(Ljava/lang/String;)V", "viewId", "Ljava/lang/Integer;", "getViewId", "()Ljava/lang/Integer;", "setViewId", "(Ljava/lang/Integer;)V", "<init>", "(Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;ZZZLjava/lang/String;Ljava/lang/Integer;)V", "Companion", "ActionType", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class PageHeaderActionIconModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ActionType actionType;
    private boolean canClickWhenDisabled;
    private boolean isEnabled;
    private final boolean isLargeIcon;
    private String largeButtonText;
    private Integer viewId;

    /* JADX INFO: compiled from: PageHeaderActionIconModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "", "(Ljava/lang/String;I)V", "PLAY", "STATION", "SHUFFLE", Contexts.Upsell.add, "SHARE", "OVERFLOW", "DOWNLOAD", "FOLLOW", "FAVORITE", "EDIT", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ActionType {
        PLAY,
        STATION,
        SHUFFLE,
        ADD,
        SHARE,
        OVERFLOW,
        DOWNLOAD,
        FOLLOW,
        FAVORITE,
        EDIT
    }

    public PageHeaderActionIconModel(ActionType actionType, boolean z, boolean z2, boolean z3, String str, Integer num) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        this.actionType = actionType;
        this.isEnabled = z;
        this.canClickWhenDisabled = z2;
        this.isLargeIcon = z3;
        this.largeButtonText = str;
        this.viewId = num;
    }

    public /* synthetic */ PageHeaderActionIconModel(ActionType actionType, boolean z, boolean z2, boolean z3, String str, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(actionType, (i & 2) != 0 ? true : z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? false : z3, (i & 16) != 0 ? null : str, (i & 32) != 0 ? null : num);
    }

    public final ActionType getActionType() {
        return this.actionType;
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    public final boolean getCanClickWhenDisabled() {
        return this.canClickWhenDisabled;
    }

    /* JADX INFO: renamed from: isLargeIcon, reason: from getter */
    public final boolean getIsLargeIcon() {
        return this.isLargeIcon;
    }

    public final String getLargeButtonText() {
        return this.largeButtonText;
    }

    public final Integer getViewId() {
        return this.viewId;
    }

    /* JADX INFO: compiled from: PageHeaderActionIconModel.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$Companion;", "", "()V", "generateArtistActionIconModels", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;", "actionIconTypes", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "([Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;)Ljava/util/List;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ List generateArtistActionIconModels$default(Companion companion, ActionType[] actionTypeArr, int i, Object obj) {
            if ((i & 1) != 0) {
                actionTypeArr = new ActionType[]{ActionType.PLAY, ActionType.STATION, ActionType.SHUFFLE};
            }
            return companion.generateArtistActionIconModels(actionTypeArr);
        }

        public final List<PageHeaderActionIconModel> generateArtistActionIconModels(ActionType[] actionIconTypes) {
            Intrinsics.checkNotNullParameter(actionIconTypes, "actionIconTypes");
            ArrayList arrayList = new ArrayList(actionIconTypes.length);
            int length = actionIconTypes.length;
            int i = 0;
            while (i < length) {
                ActionType actionType = actionIconTypes[i];
                i++;
                arrayList.add(new PageHeaderActionIconModel(actionType, false, false, false, null, null, 62, null));
            }
            return arrayList;
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(PageHeaderActionIconModel.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            PageHeaderActionIconModel pageHeaderActionIconModel = (PageHeaderActionIconModel) other;
            return this.actionType == pageHeaderActionIconModel.actionType && this.isEnabled == pageHeaderActionIconModel.isEnabled && this.canClickWhenDisabled == pageHeaderActionIconModel.canClickWhenDisabled && this.isLargeIcon == pageHeaderActionIconModel.isLargeIcon && Intrinsics.areEqual(this.largeButtonText, pageHeaderActionIconModel.largeButtonText) && Intrinsics.areEqual(this.viewId, pageHeaderActionIconModel.viewId);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.PageHeaderActionIconModel");
    }

    public int hashCode() {
        int iHashCode = this.actionType.hashCode() * 31;
        Integer num = this.viewId;
        return iHashCode + (num == null ? 0 : num.intValue());
    }
}
