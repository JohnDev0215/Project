package com.amazon.music.views.library.models;

import androidx.core.app.NotificationCompat;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PodcastTileModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u008b\u0001\b\u0007\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\u001e\u001a\u00020\u0012\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b!\u0010\"R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0004\u001a\u0004\b\u0018\u0010\u0006R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016R\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0004\u001a\u0004\b\u001c\u0010\u0006¨\u0006#"}, d2 = {"Lcom/amazon/music/views/library/models/PodcastTileModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "subTitle", "getSubTitle", ContextMappingConstants.LABEL, "getLabel", "imageUrl", "getImageUrl", "", "showPlayOverlay", "Z", "getShowPlayOverlay", "()Z", "", "totalDurationMilliseconds", "Ljava/lang/Integer;", "getTotalDurationMilliseconds", "()Ljava/lang/Integer;", "totalDurationText", "getTotalDurationText", NotificationCompat.CATEGORY_PROGRESS, "getProgress", "remainingDurationText", "getRemainingDurationText", "viewId", "contentType", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/amazon/music/views/library/metadata/ContentMetadata;ZLjava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public class PodcastTileModel extends BaseViewContentModel {
    private final String imageUrl;
    private final String label;
    private final Integer progress;
    private final String remainingDurationText;
    private final boolean showPlayOverlay;
    private final String subTitle;
    private final String title;
    private final Integer totalDurationMilliseconds;
    private final String totalDurationText;

    public /* synthetic */ PodcastTileModel(String str, String str2, String str3, String str4, String str5, int i, ContentMetadata contentMetadata, boolean z, Integer num, String str6, Integer num2, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, str5, i, contentMetadata, (i2 & 128) != 0 ? false : z, (i2 & 256) != 0 ? null : num, (i2 & 512) != 0 ? null : str6, (i2 & 1024) != 0 ? null : num2, (i2 & 2048) != 0 ? null : str7);
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final boolean getShowPlayOverlay() {
        return this.showPlayOverlay;
    }

    public final Integer getTotalDurationMilliseconds() {
        return this.totalDurationMilliseconds;
    }

    public final Integer getProgress() {
        return this.progress;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PodcastTileModel(String str, String str2, String str3, String str4, String imageUrl, int i, ContentMetadata metadata, boolean z, Integer num, String str5, Integer num2, String str6) {
        super(str, metadata, i, null, null, 24, null);
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.title = str2;
        this.subTitle = str3;
        this.label = str4;
        this.imageUrl = imageUrl;
        this.showPlayOverlay = z;
        this.totalDurationMilliseconds = num;
        this.totalDurationText = str5;
        this.progress = num2;
        this.remainingDurationText = str6;
    }

    public final String getRemainingDurationText() {
        return this.remainingDurationText;
    }
}
