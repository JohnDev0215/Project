package com.amazon.music.views.library.models;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PersistentBannerModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001:\u0001$BG\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b\u0012\u0016\b\u0002\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\"\u0010#J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\r\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016R%\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0004\u0012\u00020\u00180\u00178\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001e\u001a\u00020\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006%"}, d2 = {"Lcom/amazon/music/views/library/models/PersistentBannerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "toString", "", "hashCode", "", "other", "", "equals", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "text", "getText", "setText", "(Ljava/lang/String;)V", "isRestingState", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "setRestingState", "(Ljava/lang/Boolean;)V", "Lkotlin/Function1;", "", "onClick", "Lkotlin/jvm/functions/Function1;", "getOnClick", "()Lkotlin/jvm/functions/Function1;", "Lcom/amazon/music/views/library/models/PersistentBannerModel$PersistentBannerType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/music/views/library/models/PersistentBannerModel$PersistentBannerType;", "getType", "()Lcom/amazon/music/views/library/models/PersistentBannerModel$PersistentBannerType;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function1;Lcom/amazon/music/views/library/models/PersistentBannerModel$PersistentBannerType;)V", "PersistentBannerType", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class PersistentBannerModel extends BaseViewModel {
    private Boolean isRestingState;
    private final Function1<PersistentBannerModel, Unit> onClick;
    private String text;
    private final PersistentBannerType type;
    private final String viewId;

    /* JADX INFO: compiled from: PersistentBannerModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/views/library/models/PersistentBannerModel$PersistentBannerType;", "", "(Ljava/lang/String;I)V", "ACTIVE", "ALERT", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum PersistentBannerType {
        ACTIVE,
        ALERT
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PersistentBannerModel)) {
            return false;
        }
        PersistentBannerModel persistentBannerModel = (PersistentBannerModel) other;
        return Intrinsics.areEqual(this.viewId, persistentBannerModel.viewId) && Intrinsics.areEqual(this.text, persistentBannerModel.text) && Intrinsics.areEqual(this.isRestingState, persistentBannerModel.isRestingState) && Intrinsics.areEqual(this.onClick, persistentBannerModel.onClick) && this.type == persistentBannerModel.type;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        String str = this.viewId;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.text.hashCode()) * 31;
        Boolean bool = this.isRestingState;
        return ((((iHashCode + (bool != null ? bool.hashCode() : 0)) * 31) + this.onClick.hashCode()) * 31) + this.type.hashCode();
    }

    public String toString() {
        return "PersistentBannerModel(viewId=" + ((Object) this.viewId) + ", text=" + this.text + ", isRestingState=" + this.isRestingState + ", onClick=" + this.onClick + ", type=" + this.type + ')';
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }

    public /* synthetic */ PersistentBannerModel(String str, String str2, Boolean bool, Function1 function1, PersistentBannerType persistentBannerType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, (i & 4) != 0 ? Boolean.FALSE : bool, (i & 8) != 0 ? new Function1<PersistentBannerModel, Unit>() { // from class: com.amazon.music.views.library.models.PersistentBannerModel.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PersistentBannerModel persistentBannerModel) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PersistentBannerModel persistentBannerModel) {
                invoke2(persistentBannerModel);
                return Unit.INSTANCE;
            }
        } : function1, persistentBannerType);
    }

    /* JADX INFO: renamed from: isRestingState, reason: from getter */
    public final Boolean getIsRestingState() {
        return this.isRestingState;
    }

    public final void setRestingState(Boolean bool) {
        this.isRestingState = bool;
    }

    public final Function1<PersistentBannerModel, Unit> getOnClick() {
        return this.onClick;
    }

    public final PersistentBannerType getType() {
        return this.type;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PersistentBannerModel(String str, String text, Boolean bool, Function1<? super PersistentBannerModel, Unit> onClick, PersistentBannerType type) {
        super(str, null, 2, null);
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(type, "type");
        this.viewId = str;
        this.text = text;
        this.isRestingState = bool;
        this.onClick = onClick;
        this.type = type;
    }
}
