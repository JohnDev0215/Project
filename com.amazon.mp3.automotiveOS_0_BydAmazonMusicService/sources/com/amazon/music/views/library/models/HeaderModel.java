package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HeaderModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\u0016\b\u0002\u0010(\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0&\u0018\u00010&¢\u0006\u0004\b,\u0010-J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0016\u0010\rR\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0018\u0010\u0014R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u001a\u0010\rR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u001c\u0010\rR\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u000b\u001a\u0004\b \u0010\rR\u0019\u0010\"\u001a\u0004\u0018\u00010!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R%\u0010(\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0&\u0018\u00010&8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+¨\u0006."}, d2 = {"Lcom/amazon/music/views/library/models/HeaderModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "toString", "", "hashCode", "", "other", "", "equals", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "labelText", "getLabelText", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "labelFontStyle", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "getLabelFontStyle", "()Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "headerText", "getHeaderText", "headerFontStyle", "getHeaderFontStyle", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", ContextMappingConstants.TERTIARY_TEXT, "getTertiaryText", "imageUrl", "getImageUrl", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "", "Lcom/amazon/music/views/library/models/ButtonModel;", "actionButtonRows", "Ljava/util/List;", "getActionButtonRows", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/styles/keys/FontStyleKey;Ljava/lang/String;Lcom/amazon/music/views/library/styles/keys/FontStyleKey;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/util/List;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class HeaderModel extends BaseViewModel {
    private final List<List<ButtonModel>> actionButtonRows;
    private final FontStyleKey headerFontStyle;
    private final String headerText;
    private final String imageUrl;
    private final FontStyleKey labelFontStyle;
    private final String labelText;
    private final ContentMetadata metadata;
    private final String primaryText;
    private final String secondaryText;
    private final String tertiaryText;
    private final String viewId;

    public HeaderModel() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HeaderModel)) {
            return false;
        }
        HeaderModel headerModel = (HeaderModel) other;
        return Intrinsics.areEqual(this.viewId, headerModel.viewId) && Intrinsics.areEqual(this.labelText, headerModel.labelText) && this.labelFontStyle == headerModel.labelFontStyle && Intrinsics.areEqual(this.headerText, headerModel.headerText) && this.headerFontStyle == headerModel.headerFontStyle && Intrinsics.areEqual(this.primaryText, headerModel.primaryText) && Intrinsics.areEqual(this.secondaryText, headerModel.secondaryText) && Intrinsics.areEqual(this.tertiaryText, headerModel.tertiaryText) && Intrinsics.areEqual(this.imageUrl, headerModel.imageUrl) && Intrinsics.areEqual(this.metadata, headerModel.metadata) && Intrinsics.areEqual(this.actionButtonRows, headerModel.actionButtonRows);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        String str = this.viewId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.labelText;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        FontStyleKey fontStyleKey = this.labelFontStyle;
        int iHashCode3 = (iHashCode2 + (fontStyleKey == null ? 0 : fontStyleKey.hashCode())) * 31;
        String str3 = this.headerText;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        FontStyleKey fontStyleKey2 = this.headerFontStyle;
        int iHashCode5 = (iHashCode4 + (fontStyleKey2 == null ? 0 : fontStyleKey2.hashCode())) * 31;
        String str4 = this.primaryText;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.secondaryText;
        int iHashCode7 = (iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.tertiaryText;
        int iHashCode8 = (iHashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.imageUrl;
        int iHashCode9 = (iHashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
        ContentMetadata contentMetadata = this.metadata;
        int iHashCode10 = (iHashCode9 + (contentMetadata == null ? 0 : contentMetadata.hashCode())) * 31;
        List<List<ButtonModel>> list = this.actionButtonRows;
        return iHashCode10 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "HeaderModel(viewId=" + ((Object) this.viewId) + ", labelText=" + ((Object) this.labelText) + ", labelFontStyle=" + this.labelFontStyle + ", headerText=" + ((Object) this.headerText) + ", headerFontStyle=" + this.headerFontStyle + ", primaryText=" + ((Object) this.primaryText) + ", secondaryText=" + ((Object) this.secondaryText) + ", tertiaryText=" + ((Object) this.tertiaryText) + ", imageUrl=" + ((Object) this.imageUrl) + ", metadata=" + this.metadata + ", actionButtonRows=" + this.actionButtonRows + ')';
    }

    public /* synthetic */ HeaderModel(String str, String str2, FontStyleKey fontStyleKey, String str3, FontStyleKey fontStyleKey2, String str4, String str5, String str6, String str7, ContentMetadata contentMetadata, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : fontStyleKey, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : fontStyleKey2, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? null : contentMetadata, (i & 1024) == 0 ? list : null);
    }

    public final String getLabelText() {
        return this.labelText;
    }

    public final FontStyleKey getLabelFontStyle() {
        return this.labelFontStyle;
    }

    public final String getHeaderText() {
        return this.headerText;
    }

    public final FontStyleKey getHeaderFontStyle() {
        return this.headerFontStyle;
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

    public final String getImageUrl() {
        return this.imageUrl;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HeaderModel(String str, String str2, FontStyleKey fontStyleKey, String str3, FontStyleKey fontStyleKey2, String str4, String str5, String str6, String str7, ContentMetadata contentMetadata, List<? extends List<ButtonModel>> list) {
        super(str, null, 2, null);
        this.viewId = str;
        this.labelText = str2;
        this.labelFontStyle = fontStyleKey;
        this.headerText = str3;
        this.headerFontStyle = fontStyleKey2;
        this.primaryText = str4;
        this.secondaryText = str5;
        this.tertiaryText = str6;
        this.imageUrl = str7;
        this.metadata = contentMetadata;
        this.actionButtonRows = list;
    }

    public final List<List<ButtonModel>> getActionButtonRows() {
        return this.actionButtonRows;
    }
}
