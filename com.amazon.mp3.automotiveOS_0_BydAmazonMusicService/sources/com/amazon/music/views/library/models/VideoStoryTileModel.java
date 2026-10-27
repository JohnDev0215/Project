package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryTileModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0091\u0001\b\u0007\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\"\u001a\u00020!\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\r\u0012\u0006\u0010#\u001a\u00020\u001b\u0012\u0006\u0010%\u001a\u00020$\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b&\u0010'R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R$\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006\"\u0004\b\t\u0010\nR\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006R\u001f\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0004\u001a\u0004\b\u0018\u0010\u0006R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0004\u001a\u0004\b\u001a\u0010\u0006R\u0017\u0010\u001c\u001a\u00020\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006("}, d2 = {"Lcom/amazon/music/views/library/models/VideoStoryTileModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "subTitle", "getSubTitle", "setSubTitle", "(Ljava/lang/String;)V", ContextMappingConstants.TERTIARY_TEXT, "getTertiaryText", "", ContextMappingConstants.CONTENT_ENCODING, "Ljava/util/List;", "getContentEncoding", "()Ljava/util/List;", "", "expirationTime", "Ljava/lang/Long;", "getExpirationTime", "()Ljava/lang/Long;", "asin", "getAsin", ContextMappingConstants.LABEL, "getLabel", "", "tilePosition", "I", "getTilePosition", "()I", "viewId", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "artworkFrameModel", "contentType", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/ArtworkFrameModel;Ljava/util/List;ILcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;I)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public class VideoStoryTileModel extends BaseViewContentModel {
    private final String asin;
    private final List<String> contentEncoding;
    private final Long expirationTime;
    private final String label;
    private String subTitle;
    private final String tertiaryText;
    private final int tilePosition;
    private final String title;

    public /* synthetic */ VideoStoryTileModel(String str, String str2, String str3, String str4, ArtworkFrameModel artworkFrameModel, List list, int i, ContentMetadata contentMetadata, Long l, String str5, String str6, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? null : str3, (i3 & 8) != 0 ? null : str4, artworkFrameModel, (i3 & 32) != 0 ? null : list, i, contentMetadata, (i3 & 256) != 0 ? null : l, (i3 & 512) != 0 ? null : str5, (i3 & 1024) != 0 ? null : str6, (i3 & 2048) != 0 ? 0 : i2);
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VideoStoryTileModel(String str, String str2, String str3, String str4, ArtworkFrameModel artworkFrameModel, List<String> list, int i, ContentMetadata metadata, Long l, String str5, String str6, int i2) {
        super(str, metadata, i, artworkFrameModel, null, 16, null);
        Intrinsics.checkNotNullParameter(artworkFrameModel, "artworkFrameModel");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.title = str2;
        this.subTitle = str3;
        this.tertiaryText = str4;
        this.contentEncoding = list;
        this.expirationTime = l;
        this.asin = str5;
        this.label = str6;
        this.tilePosition = i2;
    }

    public final int getTilePosition() {
        return this.tilePosition;
    }
}
