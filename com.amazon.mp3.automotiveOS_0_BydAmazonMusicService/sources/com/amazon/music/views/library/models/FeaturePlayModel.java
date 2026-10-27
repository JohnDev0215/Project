package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturePlayModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001BÃ\u0001\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010%\u001a\u00020$\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010&\u001a\u00020\u0016\u0012\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001b\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010$\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u001b\u0012\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010 ¢\u0006\u0004\b(\u0010)R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u0004\u001a\u0004\b\u000e\u0010\u0006R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0004\u001a\u0004\b\u0010\u0010\u0006R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0006R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0004\u001a\u0004\b\u0014\u0010\u0006R\u001f\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00158\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001c\u001a\u00020\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001c\u0010\u001eR\u0017\u0010\u001f\u001a\u00020\u001b8\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001d\u001a\u0004\b\u001f\u0010\u001eR\u001f\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010 8\u0006¢\u0006\f\n\u0004\b!\u0010\u0018\u001a\u0004\b\"\u0010\u001a¨\u0006*"}, d2 = {"Lcom/amazon/music/views/library/models/FeaturePlayModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "", "target", "Ljava/lang/String;", "getTarget", "()Ljava/lang/String;", "title", "getTitle", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", ContextMappingConstants.TERTIARY_TEXT, "getTertiaryText", "imageUrl", "getImageUrl", "altDescription", "getAltDescription", ContextMappingConstants.LABEL, "getLabel", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "Ljava/util/List;", "getMetadataList", "()Ljava/util/List;", "", "isNewUX", "Z", "()Z", "isFacetedNavUX", "", "imageUrls", "getImageUrls", "viewId", "", "contentType", "metadata", "playState", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/util/List;ZLjava/lang/Integer;ZLjava/util/List;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class FeaturePlayModel extends BaseViewContentModel {
    private final String altDescription;
    private final String imageUrl;
    private final List<String> imageUrls;
    private final boolean isFacetedNavUX;
    private final boolean isNewUX;
    private final String label;
    private final List<ContentMetadata> metadataList;
    private final String primaryText;
    private final String secondaryText;
    private final String target;
    private final String tertiaryText;
    private final String title;

    public /* synthetic */ FeaturePlayModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, String str9, ContentMetadata contentMetadata, List list, boolean z, Integer num, boolean z2, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7, (i2 & 128) != 0 ? null : str8, i, (i2 & 512) != 0 ? null : str9, contentMetadata, (i2 & 2048) != 0 ? null : list, (i2 & 4096) != 0 ? false : z, (i2 & 8192) != 0 ? null : num, (i2 & 16384) != 0 ? false : z2, (i2 & 32768) != 0 ? null : list2);
    }

    public final String getTitle() {
        return this.title;
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

    public final String getLabel() {
        return this.label;
    }

    public final List<ContentMetadata> getMetadataList() {
        return this.metadataList;
    }

    /* JADX INFO: renamed from: isNewUX, reason: from getter */
    public final boolean getIsNewUX() {
        return this.isNewUX;
    }

    /* JADX INFO: renamed from: isFacetedNavUX, reason: from getter */
    public final boolean getIsFacetedNavUX() {
        return this.isFacetedNavUX;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturePlayModel(String str, String str2, String title, String str3, String str4, String str5, String str6, String str7, int i, String str8, ContentMetadata metadata, List<ContentMetadata> list, boolean z, Integer num, boolean z2, List<String> list2) {
        super(str, metadata, i, null, num);
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.target = str2;
        this.title = title;
        this.primaryText = str3;
        this.secondaryText = str4;
        this.tertiaryText = str5;
        this.imageUrl = str6;
        this.altDescription = str7;
        this.label = str8;
        this.metadataList = list;
        this.isNewUX = z;
        this.isFacetedNavUX = z2;
        this.imageUrls = list2;
    }

    public final List<String> getImageUrls() {
        return this.imageUrls;
    }
}
