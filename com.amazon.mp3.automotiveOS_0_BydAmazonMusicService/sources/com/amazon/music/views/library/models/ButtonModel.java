package com.amazon.music.views.library.models;

import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.HeaderButtonActionType;
import com.amazon.music.views.library.models.enums.TargetType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ButtonModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B{\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+¢\u0006\u0004\b0\u00101J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\t\u0010\t\u001a\u00020\bHÖ\u0001J\u0013\u0010\f\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\nHÖ\u0003R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0016\u0010\u0010R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0018\u0010\u0014R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u001a\u0010\u0010R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u0003\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0017\u0010 \u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010%\u001a\u0004\u0018\u00010$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010)\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b)\u0010\u000e\u001a\u0004\b*\u0010\u0010R\u0019\u0010,\u001a\u0004\u0018\u00010+8\u0006¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/¨\u00062"}, d2 = {"Lcom/amazon/music/views/library/models/ButtonModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "isActive", "", "updateActiveState", "", "toString", "", "hashCode", "", "other", "equals", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "iconId", "Ljava/lang/Integer;", "getIconId", "()Ljava/lang/Integer;", "text", "getText", "activeIconId", "getActiveIconId", "activeText", "getActiveText", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "setActive", "(Ljava/lang/Boolean;)V", "Lcom/amazon/music/views/library/models/enums/HeaderButtonActionType;", "actionType", "Lcom/amazon/music/views/library/models/enums/HeaderButtonActionType;", "getActionType", "()Lcom/amazon/music/views/library/models/enums/HeaderButtonActionType;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "target", "getTarget", "Lcom/amazon/music/views/library/models/enums/TargetType;", "targetType", "Lcom/amazon/music/views/library/models/enums/TargetType;", "getTargetType", "()Lcom/amazon/music/views/library/models/enums/TargetType;", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Lcom/amazon/music/views/library/models/enums/HeaderButtonActionType;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/String;Lcom/amazon/music/views/library/models/enums/TargetType;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ButtonModel extends BaseViewModel {
    private final HeaderButtonActionType actionType;
    private final Integer activeIconId;
    private final String activeText;
    private final Integer iconId;
    private Boolean isActive;
    private final ContentMetadata metadata;
    private final String target;
    private final TargetType targetType;
    private final String text;
    private final String viewId;

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ButtonModel)) {
            return false;
        }
        ButtonModel buttonModel = (ButtonModel) other;
        return Intrinsics.areEqual(this.viewId, buttonModel.viewId) && Intrinsics.areEqual(this.iconId, buttonModel.iconId) && Intrinsics.areEqual(this.text, buttonModel.text) && Intrinsics.areEqual(this.activeIconId, buttonModel.activeIconId) && Intrinsics.areEqual(this.activeText, buttonModel.activeText) && Intrinsics.areEqual(this.isActive, buttonModel.isActive) && this.actionType == buttonModel.actionType && Intrinsics.areEqual(this.metadata, buttonModel.metadata) && Intrinsics.areEqual(this.target, buttonModel.target) && this.targetType == buttonModel.targetType;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        String str = this.viewId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.iconId;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.text;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num2 = this.activeIconId;
        int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str3 = this.activeText;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Boolean bool = this.isActive;
        int iHashCode6 = (((iHashCode5 + (bool == null ? 0 : bool.hashCode())) * 31) + this.actionType.hashCode()) * 31;
        ContentMetadata contentMetadata = this.metadata;
        int iHashCode7 = (iHashCode6 + (contentMetadata == null ? 0 : contentMetadata.hashCode())) * 31;
        String str4 = this.target;
        int iHashCode8 = (iHashCode7 + (str4 == null ? 0 : str4.hashCode())) * 31;
        TargetType targetType = this.targetType;
        return iHashCode8 + (targetType != null ? targetType.hashCode() : 0);
    }

    public String toString() {
        return "ButtonModel(viewId=" + ((Object) this.viewId) + ", iconId=" + this.iconId + ", text=" + ((Object) this.text) + ", activeIconId=" + this.activeIconId + ", activeText=" + ((Object) this.activeText) + ", isActive=" + this.isActive + ", actionType=" + this.actionType + ", metadata=" + this.metadata + ", target=" + ((Object) this.target) + ", targetType=" + this.targetType + ')';
    }

    public /* synthetic */ ButtonModel(String str, Integer num, String str2, Integer num2, String str3, Boolean bool, HeaderButtonActionType headerButtonActionType, ContentMetadata contentMetadata, String str4, TargetType targetType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : bool, headerButtonActionType, (i & 128) != 0 ? null : contentMetadata, (i & 256) != 0 ? null : str4, (i & 512) != 0 ? null : targetType);
    }

    public final String getViewId() {
        return this.viewId;
    }

    public final Integer getIconId() {
        return this.iconId;
    }

    public final String getText() {
        return this.text;
    }

    public final Integer getActiveIconId() {
        return this.activeIconId;
    }

    public final String getActiveText() {
        return this.activeText;
    }

    /* JADX INFO: renamed from: isActive, reason: from getter */
    public final Boolean getIsActive() {
        return this.isActive;
    }

    public final HeaderButtonActionType getActionType() {
        return this.actionType;
    }

    public final ContentMetadata getMetadata() {
        return this.metadata;
    }

    public final String getTarget() {
        return this.target;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ButtonModel(String str, Integer num, String str2, Integer num2, String str3, Boolean bool, HeaderButtonActionType actionType, ContentMetadata contentMetadata, String str4, TargetType targetType) {
        super(str, null, 2, null);
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        this.viewId = str;
        this.iconId = num;
        this.text = str2;
        this.activeIconId = num2;
        this.activeText = str3;
        this.isActive = bool;
        this.actionType = actionType;
        this.metadata = contentMetadata;
        this.target = str4;
        this.targetType = targetType;
    }

    public final TargetType getTargetType() {
        return this.targetType;
    }

    public final void updateActiveState(boolean isActive) {
        this.isActive = Boolean.valueOf(isActive);
        notifyChanged(6);
    }
}
