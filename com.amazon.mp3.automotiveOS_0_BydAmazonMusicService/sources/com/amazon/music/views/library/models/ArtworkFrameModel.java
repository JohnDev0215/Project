package com.amazon.music.views.library.models;

import android.graphics.drawable.Drawable;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtworkFrameModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0099\u0001\b\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010)\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000100¢\u0006\u0004\b5\u00106J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\f\u001a\u00020\u0002H\u0016R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0019\u0010\u0017R$\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u001dR\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u0015\u001a\u0004\b\u001f\u0010\u0017R\u0019\u0010!\u001a\u0004\u0018\u00010 8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u0019\u0010%\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b%\u0010\u0015\u001a\u0004\b&\u0010\u0017R\u0019\u0010'\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b'\u0010\u0015\u001a\u0004\b(\u0010\u0017R\u0019\u0010*\u001a\u0004\u0018\u00010)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0019\u0010.\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b.\u0010\u000f\u001a\u0004\b/\u0010\u0011R\u0019\u00101\u001a\u0004\u0018\u0001008\u0006¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104¨\u00067"}, d2 = {"Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "newPlayState", "", "updatePlayState$DMMViewLibrary_release", "(I)V", "updatePlayState", "", "other", "", "equals", "hashCode", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "imageUrl", "getImageUrl", "contentType", "Ljava/lang/Integer;", "getContentType", "()Ljava/lang/Integer;", "sashImageResource", "getSashImageResource", "playState", "getPlayState", "setPlayState", "(Ljava/lang/Integer;)V", "playIconType", "getPlayIconType", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "resourceId", "getResourceId", "imageSize", "getImageSize", "Landroid/graphics/drawable/Drawable;", "imageDrawable", "Landroid/graphics/drawable/Drawable;", "getImageDrawable", "()Landroid/graphics/drawable/Drawable;", "imageDescription", "getImageDescription", "Lcom/amazon/music/views/library/styles/keys/IconStyleKey;", "playIconStyleKey", "Lcom/amazon/music/views/library/styles/keys/IconStyleKey;", "getPlayIconStyleKey", "()Lcom/amazon/music/views/library/styles/keys/IconStyleKey;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/graphics/drawable/Drawable;Ljava/lang/String;Lcom/amazon/music/views/library/styles/keys/IconStyleKey;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ArtworkFrameModel extends BaseViewModel {
    private final Integer contentType;
    private final String imageDescription;
    private final Drawable imageDrawable;
    private final Integer imageSize;
    private final String imageUrl;
    private final ContentMetadata metadata;
    private final IconStyleKey playIconStyleKey;
    private final Integer playIconType;
    private Integer playState;
    private final Integer resourceId;
    private final Integer sashImageResource;
    private final String viewId;

    @JvmOverloads
    public ArtworkFrameModel() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
    }

    @JvmOverloads
    public ArtworkFrameModel(String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, ContentMetadata contentMetadata, Integer num5, Integer num6) {
        this(str, str2, num, num2, num3, num4, contentMetadata, num5, num6, null, null, null, 3584, null);
    }

    @JvmOverloads
    public ArtworkFrameModel(String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, ContentMetadata contentMetadata, Integer num5, Integer num6, Drawable drawable, String str3) {
        this(str, str2, num, num2, num3, num4, contentMetadata, num5, num6, drawable, str3, null, 2048, null);
    }

    public /* synthetic */ ArtworkFrameModel(String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, ContentMetadata contentMetadata, Integer num5, Integer num6, Drawable drawable, String str3, IconStyleKey iconStyleKey, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : num3, (i & 32) != 0 ? null : num4, (i & 64) != 0 ? null : contentMetadata, (i & 128) != 0 ? null : num5, (i & 256) != 0 ? null : num6, (i & 512) != 0 ? null : drawable, (i & 1024) != 0 ? null : str3, (i & 2048) == 0 ? iconStyleKey : null);
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final Integer getContentType() {
        return this.contentType;
    }

    public final Integer getSashImageResource() {
        return this.sashImageResource;
    }

    public final Integer getPlayState() {
        return this.playState;
    }

    public final Integer getPlayIconType() {
        return this.playIconType;
    }

    public final ContentMetadata getMetadata() {
        return this.metadata;
    }

    public final Integer getResourceId() {
        return this.resourceId;
    }

    public final Integer getImageSize() {
        return this.imageSize;
    }

    public final Drawable getImageDrawable() {
        return this.imageDrawable;
    }

    public final String getImageDescription() {
        return this.imageDescription;
    }

    public final IconStyleKey getPlayIconStyleKey() {
        return this.playIconStyleKey;
    }

    @JvmOverloads
    public ArtworkFrameModel(String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, ContentMetadata contentMetadata, Integer num5, Integer num6, Drawable drawable, String str3, IconStyleKey iconStyleKey) {
        super(str, null, 2, 0 == true ? 1 : 0);
        this.viewId = str;
        this.imageUrl = str2;
        this.contentType = num;
        this.sashImageResource = num2;
        this.playState = num3;
        this.playIconType = num4;
        this.metadata = contentMetadata;
        this.resourceId = num5;
        this.imageSize = num6;
        this.imageDrawable = drawable;
        this.imageDescription = str3;
        this.playIconStyleKey = iconStyleKey;
    }

    public final void updatePlayState$DMMViewLibrary_release(int newPlayState) {
        this.playState = Integer.valueOf(newPlayState);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(ArtworkFrameModel.class, other == null ? null : other.getClass()) || !super.equals(other)) {
            return false;
        }
        if (other != null) {
            ArtworkFrameModel artworkFrameModel = (ArtworkFrameModel) other;
            return Intrinsics.areEqual(this.viewId, artworkFrameModel.viewId) && Intrinsics.areEqual(this.imageUrl, artworkFrameModel.imageUrl) && Intrinsics.areEqual(this.contentType, artworkFrameModel.contentType) && Intrinsics.areEqual(this.sashImageResource, artworkFrameModel.sashImageResource) && Intrinsics.areEqual(this.playState, artworkFrameModel.playState) && Intrinsics.areEqual(this.playIconType, artworkFrameModel.playIconType) && Intrinsics.areEqual(this.metadata, artworkFrameModel.metadata) && Intrinsics.areEqual(this.resourceId, artworkFrameModel.resourceId) && Intrinsics.areEqual(this.imageSize, artworkFrameModel.imageSize) && Intrinsics.areEqual(this.imageDrawable, artworkFrameModel.imageDrawable) && Intrinsics.areEqual(this.imageDescription, artworkFrameModel.imageDescription) && this.playIconStyleKey == artworkFrameModel.playIconStyleKey;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.ArtworkFrameModel");
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        String str = this.viewId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.imageUrl;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.contentType;
        int iIntValue = (iHashCode2 + (num == null ? 0 : num.intValue())) * 31;
        Integer num2 = this.sashImageResource;
        int iIntValue2 = (iIntValue + (num2 == null ? 0 : num2.intValue())) * 31;
        Integer num3 = this.playState;
        int iIntValue3 = (iIntValue2 + (num3 == null ? 0 : num3.intValue())) * 31;
        Integer num4 = this.playIconType;
        int iIntValue4 = (iIntValue3 + (num4 == null ? 0 : num4.intValue())) * 31;
        ContentMetadata contentMetadata = this.metadata;
        int iHashCode3 = (iIntValue4 + (contentMetadata == null ? 0 : contentMetadata.hashCode())) * 31;
        Integer num5 = this.resourceId;
        int iIntValue5 = (iHashCode3 + (num5 == null ? 0 : num5.intValue())) * 31;
        Integer num6 = this.imageSize;
        int iIntValue6 = (iIntValue5 + (num6 == null ? 0 : num6.intValue())) * 31;
        Drawable drawable = this.imageDrawable;
        int iHashCode4 = (iIntValue6 + (drawable == null ? 0 : drawable.hashCode())) * 31;
        String str3 = this.imageDescription;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        IconStyleKey iconStyleKey = this.playIconStyleKey;
        return iHashCode5 + (iconStyleKey != null ? iconStyleKey.hashCode() : 0);
    }
}
