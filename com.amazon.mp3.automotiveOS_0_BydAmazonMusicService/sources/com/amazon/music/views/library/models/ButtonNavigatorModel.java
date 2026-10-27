package com.amazon.music.views.library.models;

import android.graphics.drawable.Drawable;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ButtonNavigatorModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001B{\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u001c\u001a\u00020\b\u0012\b\b\u0002\u0010 \u001a\u00020\u001f\u0012\b\b\u0002\u0010%\u001a\u00020$\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010)\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010)\u0012\b\b\u0002\u00100\u001a\u00020\u0004¢\u0006\u0004\b4\u00105J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001c\u0010\u001eR\u0017\u0010 \u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010%\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010*\u001a\u0004\u0018\u00010)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0019\u0010.\u001a\u0004\u0018\u00010)8\u0006¢\u0006\f\n\u0004\b.\u0010+\u001a\u0004\b/\u0010-R\u0017\u00100\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103¨\u00066"}, d2 = {"Lcom/amazon/music/views/library/models/ButtonNavigatorModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "toString", "", "hashCode", "", "other", "", "equals", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "text", "getText", "target", "getTarget", "Landroid/graphics/drawable/Drawable;", "icon", "Landroid/graphics/drawable/Drawable;", "getIcon", "()Landroid/graphics/drawable/Drawable;", "Lcom/amazon/music/views/library/models/enums/TargetType;", "targetType", "Lcom/amazon/music/views/library/models/enums/TargetType;", "getTargetType", "()Lcom/amazon/music/views/library/models/enums/TargetType;", Constants.ENABLE_DISABLE, "Z", "()Z", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "buttonSizeKey", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "getButtonSizeKey", "()Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "buttonStyleKey", "Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "getButtonStyleKey", "()Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "sideMarginSpacer", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "getSideMarginSpacer", "()Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "topMarginSpacer", "getTopMarginSpacer", "width", "I", "getWidth", "()I", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;Lcom/amazon/music/views/library/models/enums/TargetType;ZLcom/amazon/music/views/library/styles/keys/ButtonSizeKey;Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;Lcom/amazon/music/views/library/styles/keys/SpacerKey;Lcom/amazon/music/views/library/styles/keys/SpacerKey;I)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ButtonNavigatorModel extends BaseViewModel {
    private final ButtonSizeKey buttonSizeKey;
    private final ButtonStyleKey buttonStyleKey;
    private final Drawable icon;
    private final boolean isEnabled;
    private final SpacerKey sideMarginSpacer;
    private final String target;
    private final TargetType targetType;
    private final String text;
    private final SpacerKey topMarginSpacer;
    private final String viewId;
    private final int width;

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ButtonNavigatorModel)) {
            return false;
        }
        ButtonNavigatorModel buttonNavigatorModel = (ButtonNavigatorModel) other;
        return Intrinsics.areEqual(this.viewId, buttonNavigatorModel.viewId) && Intrinsics.areEqual(this.text, buttonNavigatorModel.text) && Intrinsics.areEqual(this.target, buttonNavigatorModel.target) && Intrinsics.areEqual(this.icon, buttonNavigatorModel.icon) && this.targetType == buttonNavigatorModel.targetType && this.isEnabled == buttonNavigatorModel.isEnabled && this.buttonSizeKey == buttonNavigatorModel.buttonSizeKey && this.buttonStyleKey == buttonNavigatorModel.buttonStyleKey && this.sideMarginSpacer == buttonNavigatorModel.sideMarginSpacer && this.topMarginSpacer == buttonNavigatorModel.topMarginSpacer && this.width == buttonNavigatorModel.width;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [int] */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v24 */
    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        String str = this.viewId;
        int iHashCode = (((((str == null ? 0 : str.hashCode()) * 31) + this.text.hashCode()) * 31) + this.target.hashCode()) * 31;
        Drawable drawable = this.icon;
        int iHashCode2 = (iHashCode + (drawable == null ? 0 : drawable.hashCode())) * 31;
        TargetType targetType = this.targetType;
        int iHashCode3 = (iHashCode2 + (targetType == null ? 0 : targetType.hashCode())) * 31;
        boolean z = this.isEnabled;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int iHashCode4 = (((((iHashCode3 + r2) * 31) + this.buttonSizeKey.hashCode()) * 31) + this.buttonStyleKey.hashCode()) * 31;
        SpacerKey spacerKey = this.sideMarginSpacer;
        int iHashCode5 = (iHashCode4 + (spacerKey == null ? 0 : spacerKey.hashCode())) * 31;
        SpacerKey spacerKey2 = this.topMarginSpacer;
        return ((iHashCode5 + (spacerKey2 != null ? spacerKey2.hashCode() : 0)) * 31) + Integer.hashCode(this.width);
    }

    public String toString() {
        return "ButtonNavigatorModel(viewId=" + ((Object) this.viewId) + ", text=" + this.text + ", target=" + this.target + ", icon=" + this.icon + ", targetType=" + this.targetType + ", isEnabled=" + this.isEnabled + ", buttonSizeKey=" + this.buttonSizeKey + ", buttonStyleKey=" + this.buttonStyleKey + ", sideMarginSpacer=" + this.sideMarginSpacer + ", topMarginSpacer=" + this.topMarginSpacer + ", width=" + this.width + ')';
    }

    public final String getText() {
        return this.text;
    }

    public final String getTarget() {
        return this.target;
    }

    public final Drawable getIcon() {
        return this.icon;
    }

    public final TargetType getTargetType() {
        return this.targetType;
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    public /* synthetic */ ButtonNavigatorModel(String str, String str2, String str3, Drawable drawable, TargetType targetType, boolean z, ButtonSizeKey buttonSizeKey, ButtonStyleKey buttonStyleKey, SpacerKey spacerKey, SpacerKey spacerKey2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, str2, str3, (i2 & 8) != 0 ? null : drawable, (i2 & 16) != 0 ? null : targetType, (i2 & 32) != 0 ? true : z, (i2 & 64) != 0 ? ButtonSizeKey.LARGE : buttonSizeKey, (i2 & 128) != 0 ? ButtonStyleKey.GLASS : buttonStyleKey, (i2 & 256) != 0 ? null : spacerKey, (i2 & 512) != 0 ? null : spacerKey2, (i2 & 1024) != 0 ? -1 : i);
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
    public ButtonNavigatorModel(String str, String text, String target, Drawable drawable, TargetType targetType, boolean z, ButtonSizeKey buttonSizeKey, ButtonStyleKey buttonStyleKey, SpacerKey spacerKey, SpacerKey spacerKey2, int i) {
        super(str, null, 2, null);
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(buttonSizeKey, "buttonSizeKey");
        Intrinsics.checkNotNullParameter(buttonStyleKey, "buttonStyleKey");
        this.viewId = str;
        this.text = text;
        this.target = target;
        this.icon = drawable;
        this.targetType = targetType;
        this.isEnabled = z;
        this.buttonSizeKey = buttonSizeKey;
        this.buttonStyleKey = buttonStyleKey;
        this.sideMarginSpacer = spacerKey;
        this.topMarginSpacer = spacerKey2;
        this.width = i;
    }

    public final int getWidth() {
        return this.width;
    }
}
