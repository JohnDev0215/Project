package com.amazon.music.views.library.models;

import androidx.core.app.FrameMetricsAggregator;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToggleButtonModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u001d\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010$\u001a\u00020\b¢\u0006\u0004\b*\u0010+J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0013\u0010\rR\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\rR\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011R\u0017\u0010\u0019\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001e\u001a\u00020\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010\"\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\"\u0010\u000f\u001a\u0004\b#\u0010\u0011R\"\u0010$\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006,"}, d2 = {"Lcom/amazon/music/views/library/models/ToggleButtonModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "toString", "", "hashCode", "", "other", "", "equals", "referenceId", "Ljava/lang/String;", "getReferenceId", "()Ljava/lang/String;", "buttonId", "Ljava/lang/Integer;", "getButtonId", "()Ljava/lang/Integer;", "buttonTag", "getButtonTag", "text", "getText", "iconId", "getIconId", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "buttonSizeKey", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "getButtonSizeKey", "()Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "iconSizeKey", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "getIconSizeKey", "()Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "maxButtonWidth", "getMaxButtonWidth", "hasBeenPressed", "Z", "getHasBeenPressed", "()Z", "setHasBeenPressed", "(Z)V", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;Lcom/amazon/music/views/library/styles/keys/IconSizeKey;Ljava/lang/Integer;Z)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ToggleButtonModel extends BaseViewModel {
    private final Integer buttonId;
    private final ButtonSizeKey buttonSizeKey;
    private final String buttonTag;
    private boolean hasBeenPressed;
    private final Integer iconId;
    private final IconSizeKey iconSizeKey;
    private final Integer maxButtonWidth;
    private final String referenceId;
    private final String text;

    public ToggleButtonModel() {
        this(null, null, null, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToggleButtonModel)) {
            return false;
        }
        ToggleButtonModel toggleButtonModel = (ToggleButtonModel) other;
        return Intrinsics.areEqual(this.referenceId, toggleButtonModel.referenceId) && Intrinsics.areEqual(this.buttonId, toggleButtonModel.buttonId) && Intrinsics.areEqual(this.buttonTag, toggleButtonModel.buttonTag) && Intrinsics.areEqual(this.text, toggleButtonModel.text) && Intrinsics.areEqual(this.iconId, toggleButtonModel.iconId) && this.buttonSizeKey == toggleButtonModel.buttonSizeKey && this.iconSizeKey == toggleButtonModel.iconSizeKey && Intrinsics.areEqual(this.maxButtonWidth, toggleButtonModel.maxButtonWidth) && this.hasBeenPressed == toggleButtonModel.hasBeenPressed;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        String str = this.referenceId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.buttonId;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.buttonTag;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.text;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num2 = this.iconId;
        int iHashCode5 = (((((iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31) + this.buttonSizeKey.hashCode()) * 31) + this.iconSizeKey.hashCode()) * 31;
        Integer num3 = this.maxButtonWidth;
        int iHashCode6 = (iHashCode5 + (num3 != null ? num3.hashCode() : 0)) * 31;
        boolean z = this.hasBeenPressed;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode6 + r1;
    }

    public String toString() {
        return "ToggleButtonModel(referenceId=" + ((Object) this.referenceId) + ", buttonId=" + this.buttonId + ", buttonTag=" + ((Object) this.buttonTag) + ", text=" + ((Object) this.text) + ", iconId=" + this.iconId + ", buttonSizeKey=" + this.buttonSizeKey + ", iconSizeKey=" + this.iconSizeKey + ", maxButtonWidth=" + this.maxButtonWidth + ", hasBeenPressed=" + this.hasBeenPressed + ')';
    }

    public final Integer getButtonId() {
        return this.buttonId;
    }

    public final String getButtonTag() {
        return this.buttonTag;
    }

    public final String getText() {
        return this.text;
    }

    public final Integer getIconId() {
        return this.iconId;
    }

    public /* synthetic */ ToggleButtonModel(String str, Integer num, String str2, String str3, Integer num2, ButtonSizeKey buttonSizeKey, IconSizeKey iconSizeKey, Integer num3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? ButtonSizeKey.SMALL : buttonSizeKey, (i & 64) != 0 ? IconSizeKey.MEDIUM : iconSizeKey, (i & 128) == 0 ? num3 : null, (i & 256) != 0 ? false : z);
    }

    public final ButtonSizeKey getButtonSizeKey() {
        return this.buttonSizeKey;
    }

    public final IconSizeKey getIconSizeKey() {
        return this.iconSizeKey;
    }

    public final Integer getMaxButtonWidth() {
        return this.maxButtonWidth;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToggleButtonModel(String str, Integer num, String str2, String str3, Integer num2, ButtonSizeKey buttonSizeKey, IconSizeKey iconSizeKey, Integer num3, boolean z) {
        super(str, null, 2, null);
        Intrinsics.checkNotNullParameter(buttonSizeKey, "buttonSizeKey");
        Intrinsics.checkNotNullParameter(iconSizeKey, "iconSizeKey");
        this.referenceId = str;
        this.buttonId = num;
        this.buttonTag = str2;
        this.text = str3;
        this.iconId = num2;
        this.buttonSizeKey = buttonSizeKey;
        this.iconSizeKey = iconSizeKey;
        this.maxButtonWidth = num3;
        this.hasBeenPressed = z;
    }
}
