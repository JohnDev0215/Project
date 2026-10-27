package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiStateButtonModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\b\u0012\u0006\u0010\u0019\u001a\u00020\u0006\u0012\u0006\u0010\u001d\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010%\u001a\u00020\u0002\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010+\u001a\u00020*\u0012\b\b\u0002\u00100\u001a\u00020/\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u000104\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u000104¢\u0006\u0004\b;\u0010<J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\t\u0010\t\u001a\u00020\bHÖ\u0001J\u0013\u0010\f\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\nHÖ\u0003R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u001cR\"\u0010\u001d\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u000e\u001a\u0004\b\u001e\u0010\u0010\"\u0004\b\u001f\u0010\u001cR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0017\u0010%\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b%\u0010 \u001a\u0004\b%\u0010\"R\u0019\u0010&\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010+\u001a\u00020*8\u0006¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u0017\u00100\u001a\u00020/8\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u0019\u00105\u001a\u0004\u0018\u0001048\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0019\u00109\u001a\u0004\u0018\u0001048\u0006¢\u0006\f\n\u0004\b9\u00106\u001a\u0004\b:\u00108¨\u0006="}, d2 = {"Lcom/amazon/music/views/library/models/MultiStateButtonModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "showPrimary", "", "updateDisplayText", "", "toString", "", "hashCode", "", "other", "equals", "referenceId", "Ljava/lang/String;", "getReferenceId", "()Ljava/lang/String;", "maxButtonWidth", "Ljava/lang/Integer;", "getMaxButtonWidth", "()Ljava/lang/Integer;", "width", "I", "getWidth", "()I", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", "setPrimaryText", "(Ljava/lang/String;)V", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", "setSecondaryText", "Z", "getShowPrimary", "()Z", "setShowPrimary", "(Z)V", Constants.ENABLE_DISABLE, "shouldCenter", "Ljava/lang/Boolean;", "getShouldCenter", "()Ljava/lang/Boolean;", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "buttonSizeKey", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "getButtonSizeKey", "()Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "buttonStyleKey", "Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "getButtonStyleKey", "()Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "sideMarginSpacer", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "getSideMarginSpacer", "()Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "topMarginSpacer", "getTopMarginSpacer", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/String;Ljava/lang/String;ZZLjava/lang/Boolean;Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;Lcom/amazon/music/views/library/styles/keys/SpacerKey;Lcom/amazon/music/views/library/styles/keys/SpacerKey;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class MultiStateButtonModel extends BaseViewModel {
    private final ButtonSizeKey buttonSizeKey;
    private final ButtonStyleKey buttonStyleKey;
    private final boolean isEnabled;
    private final Integer maxButtonWidth;
    private String primaryText;
    private final String referenceId;
    private String secondaryText;
    private final Boolean shouldCenter;
    private boolean showPrimary;
    private final SpacerKey sideMarginSpacer;
    private final SpacerKey topMarginSpacer;
    private final int width;

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MultiStateButtonModel)) {
            return false;
        }
        MultiStateButtonModel multiStateButtonModel = (MultiStateButtonModel) other;
        return Intrinsics.areEqual(this.referenceId, multiStateButtonModel.referenceId) && Intrinsics.areEqual(this.maxButtonWidth, multiStateButtonModel.maxButtonWidth) && this.width == multiStateButtonModel.width && Intrinsics.areEqual(this.primaryText, multiStateButtonModel.primaryText) && Intrinsics.areEqual(this.secondaryText, multiStateButtonModel.secondaryText) && this.showPrimary == multiStateButtonModel.showPrimary && this.isEnabled == multiStateButtonModel.isEnabled && Intrinsics.areEqual(this.shouldCenter, multiStateButtonModel.shouldCenter) && this.buttonSizeKey == multiStateButtonModel.buttonSizeKey && this.buttonStyleKey == multiStateButtonModel.buttonStyleKey && this.sideMarginSpacer == multiStateButtonModel.sideMarginSpacer && this.topMarginSpacer == multiStateButtonModel.topMarginSpacer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [int] */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        String str = this.referenceId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.maxButtonWidth;
        int iHashCode2 = (((((((iHashCode + (num == null ? 0 : num.hashCode())) * 31) + Integer.hashCode(this.width)) * 31) + this.primaryText.hashCode()) * 31) + this.secondaryText.hashCode()) * 31;
        boolean z = this.showPrimary;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode2 + r2) * 31;
        boolean z2 = this.isEnabled;
        int i2 = (i + (z2 ? 1 : z2)) * 31;
        Boolean bool = this.shouldCenter;
        int iHashCode3 = (((((i2 + (bool == null ? 0 : bool.hashCode())) * 31) + this.buttonSizeKey.hashCode()) * 31) + this.buttonStyleKey.hashCode()) * 31;
        SpacerKey spacerKey = this.sideMarginSpacer;
        int iHashCode4 = (iHashCode3 + (spacerKey == null ? 0 : spacerKey.hashCode())) * 31;
        SpacerKey spacerKey2 = this.topMarginSpacer;
        return iHashCode4 + (spacerKey2 != null ? spacerKey2.hashCode() : 0);
    }

    public String toString() {
        return "MultiStateButtonModel(referenceId=" + ((Object) this.referenceId) + ", maxButtonWidth=" + this.maxButtonWidth + ", width=" + this.width + ", primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", showPrimary=" + this.showPrimary + ", isEnabled=" + this.isEnabled + ", shouldCenter=" + this.shouldCenter + ", buttonSizeKey=" + this.buttonSizeKey + ", buttonStyleKey=" + this.buttonStyleKey + ", sideMarginSpacer=" + this.sideMarginSpacer + ", topMarginSpacer=" + this.topMarginSpacer + ')';
    }

    public final int getWidth() {
        return this.width;
    }

    public final String getPrimaryText() {
        return this.primaryText;
    }

    public final String getSecondaryText() {
        return this.secondaryText;
    }

    public final boolean getShowPrimary() {
        return this.showPrimary;
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    public /* synthetic */ MultiStateButtonModel(String str, Integer num, int i, String str2, String str3, boolean z, boolean z2, Boolean bool, ButtonSizeKey buttonSizeKey, ButtonStyleKey buttonStyleKey, SpacerKey spacerKey, SpacerKey spacerKey2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : num, (i2 & 4) != 0 ? -1 : i, str2, str3, (i2 & 32) != 0 ? true : z, (i2 & 64) != 0 ? true : z2, (i2 & 128) != 0 ? Boolean.TRUE : bool, (i2 & 256) != 0 ? ButtonSizeKey.LARGE : buttonSizeKey, (i2 & 512) != 0 ? ButtonStyleKey.GLASS : buttonStyleKey, (i2 & 1024) != 0 ? null : spacerKey, (i2 & 2048) != 0 ? null : spacerKey2);
    }

    public final ButtonSizeKey getButtonSizeKey() {
        return this.buttonSizeKey;
    }

    public final ButtonStyleKey getButtonStyleKey() {
        return this.buttonStyleKey;
    }

    public final SpacerKey getSideMarginSpacer() {
        return this.sideMarginSpacer;
    }

    public final SpacerKey getTopMarginSpacer() {
        return this.topMarginSpacer;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiStateButtonModel(String str, Integer num, int i, String primaryText, String secondaryText, boolean z, boolean z2, Boolean bool, ButtonSizeKey buttonSizeKey, ButtonStyleKey buttonStyleKey, SpacerKey spacerKey, SpacerKey spacerKey2) {
        super(str, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(primaryText, "primaryText");
        Intrinsics.checkNotNullParameter(secondaryText, "secondaryText");
        Intrinsics.checkNotNullParameter(buttonSizeKey, "buttonSizeKey");
        Intrinsics.checkNotNullParameter(buttonStyleKey, "buttonStyleKey");
        this.referenceId = str;
        this.maxButtonWidth = num;
        this.width = i;
        this.primaryText = primaryText;
        this.secondaryText = secondaryText;
        this.showPrimary = z;
        this.isEnabled = z2;
        this.shouldCenter = bool;
        this.buttonSizeKey = buttonSizeKey;
        this.buttonStyleKey = buttonStyleKey;
        this.sideMarginSpacer = spacerKey;
        this.topMarginSpacer = spacerKey2;
    }

    public final void updateDisplayText(boolean showPrimary) {
        this.showPrimary = showPrimary;
        notifyChanged(5);
    }
}
