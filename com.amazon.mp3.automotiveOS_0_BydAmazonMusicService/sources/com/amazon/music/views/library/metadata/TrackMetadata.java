package com.amazon.music.views.library.metadata;

import android.net.Uri;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.music.ContentAccessType;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.models.AssetQuality;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemSingle;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.platform.providers.NestedEligibilitiesProvider;
import com.amazon.music.views.library.metadata.EligibilityMetadata;
import com.amazon.music.views.library.models.DownloadStateModelKt;
import com.amazon.music.views.library.models.base.ContentEligibilityData;
import com.amazon.music.views.library.providers.LanguageOfPerformancesProvider;
import com.amazon.music.views.library.utils.EligibilityUtil;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u0006\n\u0002\b\u0016\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 ¨\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0002¨\u0001BÙ\u0003\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\t\u0012\u0006\u0010%\u001a\u00020$\u0012\u0010\b\u0002\u0010+\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u000107\u0012\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010I\u001a\u0004\u0018\u00010H\u0012\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\u001b\u0012\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\u001b\u0012\u0010\b\u0002\u0010a\u001a\n\u0012\u0004\u0012\u00020`\u0018\u00010_\u0012\u0006\u0010g\u001a\u00020\t\u0012\u0010\b\u0002\u0010l\u001a\n\u0012\u0004\u0012\u00020k\u0018\u00010\b\u0012\b\b\u0002\u0010o\u001a\u00020\u001b\u0012\n\b\u0002\u0010u\u001a\u0004\u0018\u000107\u0012\n\b\u0002\u0010w\u001a\u0004\u0018\u00010\u0014\u0012\u0010\b\u0002\u0010y\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010|\u001a\u0004\u0018\u000107\u0012\n\b\u0002\u0010~\u001a\u0004\u0018\u00010\u001b\u0012\u000b\b\u0002\u0010\u0081\u0001\u001a\u0004\u0018\u00010\t\u0012\t\b\u0002\u0010\u0083\u0001\u001a\u00020\u0014\u0012\u000b\b\u0002\u0010\u0086\u0001\u001a\u0004\u0018\u00010\t\u0012\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u0014\u0012\f\b\u0002\u0010\u008a\u0001\u001a\u0005\u0018\u00010\u0089\u0001\u0012\u0012\b\u0002\u0010\u008f\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u008e\u0001\u0018\u00010_\u0012\u000b\b\u0002\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u0016\u0012\u0011\b\u0002\u0010\u0098\u0001\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0006\b¦\u0001\u0010§\u0001J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016J\u0014\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u001f\u001a\u00020\u001bH\u0016R\u001c\u0010 \u001a\u0004\u0018\u00010\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\"\u0010%\u001a\u00020$8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R*\u0010+\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0019\u00101\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b1\u0010!\u001a\u0004\b2\u0010#R\u0019\u00103\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b3\u0010!\u001a\u0004\b4\u0010#R\u0019\u00105\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b5\u0010!\u001a\u0004\b6\u0010#R\u0019\u00108\u001a\u0004\u0018\u0001078\u0006¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R\u0019\u0010<\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b<\u0010!\u001a\u0004\b=\u0010#R\u0019\u0010>\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b>\u0010!\u001a\u0004\b?\u0010#R\u0019\u0010@\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b@\u0010!\u001a\u0004\bA\u0010#R\u0019\u0010B\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\bB\u0010!\u001a\u0004\bC\u0010#R\u0019\u0010D\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\bD\u0010!\u001a\u0004\bE\u0010#R\u0019\u0010F\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\bF\u0010!\u001a\u0004\bG\u0010#R\u0019\u0010I\u001a\u0004\u0018\u00010H8\u0006¢\u0006\f\n\u0004\bI\u0010J\u001a\u0004\bK\u0010LR$\u0010M\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bM\u0010N\u001a\u0004\bM\u0010O\"\u0004\bP\u0010QR$\u0010R\u001a\u0004\u0018\u00010\u00148\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bR\u0010N\u001a\u0004\bR\u0010O\"\u0004\bS\u0010QR$\u0010T\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bT\u0010U\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR$\u0010Z\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bZ\u0010N\u001a\u0004\bZ\u0010O\"\u0004\b[\u0010QR$\u0010\\\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\\\u0010U\u001a\u0004\b]\u0010W\"\u0004\b^\u0010YR*\u0010a\u001a\n\u0012\u0004\u0012\u00020`\u0018\u00010_8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\ba\u0010b\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\"\u0010g\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bg\u0010!\u001a\u0004\bh\u0010#\"\u0004\bi\u0010jR*\u0010l\u001a\n\u0012\u0004\u0012\u00020k\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bl\u0010,\u001a\u0004\bm\u0010.\"\u0004\bn\u00100R\"\u0010o\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bo\u0010p\u001a\u0004\bq\u0010r\"\u0004\bs\u0010tR\u0019\u0010u\u001a\u0004\u0018\u0001078\u0006¢\u0006\f\n\u0004\bu\u00109\u001a\u0004\bv\u0010;R\u0019\u0010w\u001a\u0004\u0018\u00010\u00148\u0006¢\u0006\f\n\u0004\bw\u0010N\u001a\u0004\bx\u0010OR*\u0010y\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\by\u0010,\u001a\u0004\bz\u0010.\"\u0004\b{\u00100R\u0019\u0010|\u001a\u0004\u0018\u0001078\u0006¢\u0006\f\n\u0004\b|\u00109\u001a\u0004\b}\u0010;R%\u0010~\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0013\n\u0004\b~\u0010U\u001a\u0004\b\u007f\u0010W\"\u0005\b\u0080\u0001\u0010YR\u001c\u0010\u0081\u0001\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\u000e\n\u0005\b\u0081\u0001\u0010!\u001a\u0005\b\u0082\u0001\u0010#R\u001c\u0010\u0083\u0001\u001a\u00020\u00148\u0006¢\u0006\u0010\n\u0006\b\u0083\u0001\u0010\u0084\u0001\u001a\u0006\b\u0083\u0001\u0010\u0085\u0001R\u001c\u0010\u0086\u0001\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\u000e\n\u0005\b\u0086\u0001\u0010!\u001a\u0005\b\u0087\u0001\u0010#R\u001c\u0010\u0088\u0001\u001a\u00020\u00148\u0006¢\u0006\u0010\n\u0006\b\u0088\u0001\u0010\u0084\u0001\u001a\u0006\b\u0088\u0001\u0010\u0085\u0001R\u001f\u0010\u008a\u0001\u001a\u0005\u0018\u00010\u0089\u00018\u0006¢\u0006\u0010\n\u0006\b\u008a\u0001\u0010\u008b\u0001\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R/\u0010\u008f\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u008e\u0001\u0018\u00010_8\u0016@\u0016X\u0096\u000e¢\u0006\u0015\n\u0005\b\u008f\u0001\u0010b\u001a\u0005\b\u0090\u0001\u0010d\"\u0005\b\u0091\u0001\u0010fR+\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u0092\u0001\u0010\u0093\u0001\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001\"\u0006\b\u0096\u0001\u0010\u0097\u0001R\u001e\u0010\u0098\u0001\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0098\u0001\u0010,R\u001c\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u0099\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009a\u0001\u0010\u009b\u0001R\u001a\u0010\u009f\u0001\u001a\u0005\u0018\u00010\u009c\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R,\u0010¡\u0001\u001a\u0004\u0018\u00010\u00142\t\u0010 \u0001\u001a\u0004\u0018\u00010\u00148V@VX\u0096\u000e¢\u0006\u000e\u001a\u0005\b¡\u0001\u0010O\"\u0005\b¢\u0001\u0010QR8\u0010¥\u0001\u001a\n\u0012\u0004\u0012\u00020`\u0018\u00010_2\u000f\u0010 \u0001\u001a\n\u0012\u0004\u0012\u00020`\u0018\u00010_8V@VX\u0096\u000e¢\u0006\u000e\u001a\u0005\b£\u0001\u0010d\"\u0005\b¤\u0001\u0010f¨\u0006©\u0001"}, d2 = {"Lcom/amazon/music/views/library/metadata/TrackMetadata;", "Lcom/amazon/music/views/library/metadata/EligibilityMetadata;", "Lcom/amazon/mp3/library/refinement/Refinable;", "Lcom/amazon/music/platform/data/entity/EntityItemSingle;", "Lcom/amazon/music/platform/providers/NestedEligibilitiesProvider;", "Lcom/amazon/music/views/library/providers/LanguageOfPerformancesProvider;", "", "refreshContentEligibility", "", "", "getLanguageOfPerformances", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "getEligibilities", "Lcom/amazon/music/platform/data/entity/EntityItem;", "getEntityItem", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "getParentEligibilities", "", "other", "", "areItemsTheSame", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "update", "Lcom/amazon/music/views/library/models/base/ContentEligibilityData;", "eligibilityData", "", "updateEligibilityData", "(Lcom/amazon/music/views/library/models/base/ContentEligibilityData;)Ljava/lang/Integer;", "equals", "hashCode", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "asin", "getAsin", "title", "getTitle", "imageUrl", "getImageUrl", "", "cachedImageId", "Ljava/lang/Long;", "getCachedImageId", "()Ljava/lang/Long;", "albumAsin", "getAlbumAsin", "albumTitle", "getAlbumTitle", "albumArtistTitle", "getAlbumArtistTitle", "artistAsin", "getArtistAsin", "artistTitle", "getArtistTitle", PageUriUtils.SOURCE_QUERY_PARAM, "getSource", "", "popularity", "Ljava/lang/Double;", "getPopularity", "()Ljava/lang/Double;", "isInLibrary", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "setInLibrary", "(Ljava/lang/Boolean;)V", "isOwned", "setOwned", "downloadState", "Ljava/lang/Integer;", "getDownloadState", "()Ljava/lang/Integer;", "setDownloadState", "(Ljava/lang/Integer;)V", BrushV2Converter.KEY_IS_EXPLICIT, "setExplicit", "rowNumber", "getRowNumber", "setRowNumber", "", "Lcom/amazon/music/ContentAccessType;", "supportedTiers", "Ljava/util/Set;", "getSupportedTiers", "()Ljava/util/Set;", "setSupportedTiers", "(Ljava/util/Set;)V", "assetType", "getAssetType", "setAssetType", "(Ljava/lang/String;)V", "Lcom/amazon/music/models/AssetQuality;", "assetQuality", "getAssetQuality", "setAssetQuality", "duration", "I", "getDuration", "()I", "setDuration", "(I)V", "trackId", "getTrackId", "hasLyrics", "getHasLyrics", "contentEncodings", "getContentEncodings", "setContentEncodings", "dateCreated", "getDateCreated", "trackPosition", "getTrackPosition", "setTrackPosition", "luid", "getLuid", "isSideLoaded", "Z", "()Z", "sortTitle", "getSortTitle", "isSuggestion", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "onDemandTiers", "getOnDemandTiers", "setOnDemandTiers", "containerMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getContainerMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "setContainerMetadata", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;)V", "languageOfPerformances", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "contentEligibility", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getEntityItemIdentifier", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "entityItemIdentifier", "value", "isDownloaded", "setDownloaded", "getContentTiers", "setContentTiers", "contentTiers", "<init>", "(Ljava/lang/String;Landroid/net/Uri;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/util/Set;Ljava/lang/String;Ljava/util/List;ILjava/lang/Long;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;ZLjava/lang/String;ZLcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;Ljava/util/Set;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/util/List;)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class TrackMetadata implements EligibilityMetadata, Refinable, EntityItemSingle, NestedEligibilitiesProvider, LanguageOfPerformancesProvider {
    private final String albumArtistTitle;
    private final String albumAsin;
    private final String albumTitle;
    private final String artistAsin;
    private final String artistTitle;
    private final String asin;
    private List<AssetQuality> assetQuality;
    private String assetType;
    private final Long cachedImageId;
    private ContentMetadata containerMetadata;
    private ContentEligibility contentEligibility;
    private List<String> contentEncodings;
    private final Long dateCreated;
    private Integer downloadState;
    private int duration;
    private final Boolean hasLyrics;
    private final String imageUrl;
    private Boolean isExplicit;
    private Boolean isInLibrary;
    private Boolean isOwned;
    private final boolean isSideLoaded;
    private final boolean isSuggestion;
    private final List<String> languageOfPerformances;
    private final String luid;
    private Set<? extends SubscriptionType> onDemandTiers;
    private final PlaymodeEligibility playmodeEligibility;
    private final Double popularity;
    private Integer rowNumber;
    private final String sortTitle;
    private final String source;
    private Set<? extends ContentAccessType> supportedTiers;
    private List<String> tags;
    private final String title;
    private final Long trackId;
    private Integer trackPosition;
    private Uri uri;
    private final String viewId;

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    public EntityItem getEntityItem() {
        return this;
    }

    public TrackMetadata(String str, Uri uri, List<String> list, String str2, String str3, String str4, Long l, String str5, String str6, String str7, String str8, String str9, String str10, Double d, Boolean bool, Boolean bool2, Integer num, Boolean bool3, Integer num2, Set<? extends ContentAccessType> set, String assetType, List<AssetQuality> list2, int i, Long l2, Boolean bool4, List<String> list3, Long l3, Integer num3, String str11, boolean z, String str12, boolean z2, PlaymodeEligibility playmodeEligibility, Set<? extends SubscriptionType> set2, ContentMetadata contentMetadata, List<String> list4) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(assetType, "assetType");
        this.viewId = str;
        this.uri = uri;
        this.tags = list;
        this.asin = str2;
        this.title = str3;
        this.imageUrl = str4;
        this.cachedImageId = l;
        this.albumAsin = str5;
        this.albumTitle = str6;
        this.albumArtistTitle = str7;
        this.artistAsin = str8;
        this.artistTitle = str9;
        this.source = str10;
        this.popularity = d;
        this.isInLibrary = bool;
        this.isOwned = bool2;
        this.downloadState = num;
        this.isExplicit = bool3;
        this.rowNumber = num2;
        this.supportedTiers = set;
        this.assetType = assetType;
        this.assetQuality = list2;
        this.duration = i;
        this.trackId = l2;
        this.hasLyrics = bool4;
        this.contentEncodings = list3;
        this.dateCreated = l3;
        this.trackPosition = num3;
        this.luid = str11;
        this.isSideLoaded = z;
        this.sortTitle = str12;
        this.isSuggestion = z2;
        this.playmodeEligibility = playmodeEligibility;
        this.onDemandTiers = set2;
        this.containerMetadata = contentMetadata;
        this.languageOfPerformances = list4;
    }

    public boolean areEqual(Object obj) {
        return EligibilityMetadata.DefaultImpls.areEqual(this, obj);
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public String getViewId() {
        return this.viewId;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public Uri getUri() {
        return this.uri;
    }

    public void setUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<set-?>");
        this.uri = uri;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public List<String> getTags() {
        return this.tags;
    }

    public final String getAsin() {
        return this.asin;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final Long getCachedImageId() {
        return this.cachedImageId;
    }

    public final String getAlbumAsin() {
        return this.albumAsin;
    }

    public final String getAlbumTitle() {
        return this.albumTitle;
    }

    public final String getAlbumArtistTitle() {
        return this.albumArtistTitle;
    }

    public final String getArtistAsin() {
        return this.artistAsin;
    }

    public final String getArtistTitle() {
        return this.artistTitle;
    }

    public final String getSource() {
        return this.source;
    }

    public final Double getPopularity() {
        return this.popularity;
    }

    public /* synthetic */ TrackMetadata(String str, Uri uri, List list, String str2, String str3, String str4, Long l, String str5, String str6, String str7, String str8, String str9, String str10, Double d, Boolean bool, Boolean bool2, Integer num, Boolean bool3, Integer num2, Set set, String str11, List list2, int i, Long l2, Boolean bool4, List list3, Long l3, Integer num3, String str12, boolean z, String str13, boolean z2, PlaymodeEligibility playmodeEligibility, Set set2, ContentMetadata contentMetadata, List list4, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, uri, (i2 & 4) != 0 ? null : list, (i2 & 8) != 0 ? null : str2, (i2 & 16) != 0 ? null : str3, (i2 & 32) != 0 ? null : str4, (i2 & 64) != 0 ? null : l, (i2 & 128) != 0 ? null : str5, (i2 & 256) != 0 ? null : str6, (i2 & 512) != 0 ? null : str7, (i2 & 1024) != 0 ? null : str8, (i2 & 2048) != 0 ? null : str9, (i2 & 4096) != 0 ? null : str10, (i2 & 8192) != 0 ? null : d, (i2 & 16384) != 0 ? Boolean.FALSE : bool, (32768 & i2) != 0 ? Boolean.FALSE : bool2, (65536 & i2) != 0 ? null : num, (131072 & i2) != 0 ? Boolean.FALSE : bool3, (262144 & i2) != 0 ? null : num2, (524288 & i2) != 0 ? null : set, str11, (2097152 & i2) != 0 ? null : list2, (4194304 & i2) != 0 ? 0 : i, (8388608 & i2) != 0 ? null : l2, (16777216 & i2) != 0 ? null : bool4, (33554432 & i2) != 0 ? null : list3, (67108864 & i2) != 0 ? -1L : l3, (134217728 & i2) != 0 ? null : num3, (268435456 & i2) != 0 ? null : str12, (536870912 & i2) != 0 ? false : z, (1073741824 & i2) != 0 ? null : str13, (i2 & Integer.MIN_VALUE) != 0 ? false : z2, (i3 & 1) != 0 ? null : playmodeEligibility, (i3 & 2) != 0 ? null : set2, (i3 & 4) != 0 ? null : contentMetadata, (i3 & 8) != 0 ? null : list4);
    }

    /* JADX INFO: renamed from: isInLibrary, reason: from getter */
    public final Boolean getIsInLibrary() {
        return this.isInLibrary;
    }

    public final void setInLibrary(Boolean bool) {
        this.isInLibrary = bool;
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    /* JADX INFO: renamed from: isOwned, reason: from getter */
    public Boolean getIsOwned() {
        return this.isOwned;
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    public void setOwned(Boolean bool) {
        this.isOwned = bool;
    }

    public final Integer getDownloadState() {
        return this.downloadState;
    }

    public final void setDownloadState(Integer num) {
        this.downloadState = num;
    }

    /* JADX INFO: renamed from: isExplicit, reason: from getter */
    public final Boolean getIsExplicit() {
        return this.isExplicit;
    }

    public final Integer getRowNumber() {
        return this.rowNumber;
    }

    public final void setRowNumber(Integer num) {
        this.rowNumber = num;
    }

    public final Set<ContentAccessType> getSupportedTiers() {
        return this.supportedTiers;
    }

    public final String getAssetType() {
        return this.assetType;
    }

    public final List<AssetQuality> getAssetQuality() {
        return this.assetQuality;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public final Long getTrackId() {
        return this.trackId;
    }

    public final Boolean getHasLyrics() {
        return this.hasLyrics;
    }

    public final List<String> getContentEncodings() {
        return this.contentEncodings;
    }

    public final void setContentEncodings(List<String> list) {
        this.contentEncodings = list;
    }

    public final Long getDateCreated() {
        return this.dateCreated;
    }

    public final Integer getTrackPosition() {
        return this.trackPosition;
    }

    public final void setTrackPosition(Integer num) {
        this.trackPosition = num;
    }

    public final String getLuid() {
        return this.luid;
    }

    /* JADX INFO: renamed from: isSideLoaded, reason: from getter */
    public final boolean getIsSideLoaded() {
        return this.isSideLoaded;
    }

    public final String getSortTitle() {
        return this.sortTitle;
    }

    /* JADX INFO: renamed from: isSuggestion, reason: from getter */
    public final boolean getIsSuggestion() {
        return this.isSuggestion;
    }

    public final PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    public Set<SubscriptionType> getOnDemandTiers() {
        return this.onDemandTiers;
    }

    public final ContentMetadata getContainerMetadata() {
        return this.containerMetadata;
    }

    public final void setContainerMetadata(ContentMetadata contentMetadata) {
        this.containerMetadata = contentMetadata;
    }

    @Override // com.amazon.music.platform.data.entity.EntityItem
    public EntityItemIdentifier getEntityItemIdentifier() {
        if (this.asin != null) {
            return new EntityItemIdentifier(new Asin(this.asin), SingleType.Track.INSTANCE);
        }
        return null;
    }

    @Override // com.amazon.music.views.library.providers.LanguageOfPerformancesProvider
    public List<String> getLanguageOfPerformances() {
        return this.languageOfPerformances;
    }

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    public Map<String, Eligibility> getEligibilities() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (this.contentEligibility == null) {
            refreshContentEligibility();
        }
        ContentEligibility contentEligibility = this.contentEligibility;
        if (contentEligibility != null) {
            linkedHashMap.put(contentEligibility.getTYPE(), contentEligibility);
        }
        PlaymodeEligibility playmodeEligibility = this.playmodeEligibility;
        if (playmodeEligibility != null) {
            linkedHashMap.put(playmodeEligibility.getTYPE(), playmodeEligibility);
        }
        return linkedHashMap;
    }

    @Override // com.amazon.music.platform.providers.NestedEligibilitiesProvider
    public EntityEligibilitiesProvider getParentEligibilities() {
        ContentMetadata contentMetadata = this.containerMetadata;
        if (contentMetadata instanceof EntityEligibilitiesProvider) {
            return (EntityEligibilitiesProvider) contentMetadata;
        }
        return null;
    }

    @Override // com.amazon.music.views.library.metadata.UpdatableContentMetadata
    public boolean areItemsTheSame(Object other) {
        if (!EligibilityMetadata.DefaultImpls.areItemsTheSame(this, other)) {
            if (other instanceof TrackMetadata) {
                TrackMetadata trackMetadata = (TrackMetadata) other;
                if (Intrinsics.areEqual(this.asin, trackMetadata.asin) || Intrinsics.areEqual(this.luid, trackMetadata.luid)) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // com.amazon.music.views.library.metadata.UpdatableContentMetadata
    public void update(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
        if (trackMetadata == null) {
            return;
        }
        setUri(trackMetadata.getUri());
        setInLibrary(trackMetadata.getIsInLibrary());
        setOwned(trackMetadata.getIsOwned());
        setDownloadState(trackMetadata.getDownloadState());
        setRowNumber(trackMetadata.getRowNumber());
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    /* JADX INFO: renamed from: isDownloaded */
    public Boolean getIsDownloaded() {
        return Boolean.valueOf(DownloadStateModelKt.isDownloaded(this.downloadState));
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    public void setDownloaded(Boolean bool) {
        this.downloadState = Intrinsics.areEqual(bool, Boolean.TRUE) ? 0 : null;
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    public Set<ContentAccessType> getContentTiers() {
        return this.supportedTiers;
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    public void setContentTiers(Set<? extends ContentAccessType> set) {
        this.supportedTiers = set;
    }

    private final void refreshContentEligibility() {
        EntityItemIdentifier entityItemIdentifier = getEntityItemIdentifier();
        if (entityItemIdentifier == null) {
            return;
        }
        EligibilityUtil eligibilityUtil = EligibilityUtil.INSTANCE;
        Boolean isOwned = getIsOwned();
        boolean zBooleanValue = isOwned == null ? false : isOwned.booleanValue();
        Boolean isDownloaded = getIsDownloaded();
        boolean zBooleanValue2 = isDownloaded == null ? false : isDownloaded.booleanValue();
        boolean z = getPlaymodeEligibility() != null;
        Set<SubscriptionType> subscriptionType = eligibilityUtil.toSubscriptionType(getSupportedTiers());
        Set<SubscriptionType> onDemandTiers = getOnDemandTiers();
        if (onDemandTiers == null) {
            onDemandTiers = SetsKt__SetsKt.emptySet();
        }
        this.contentEligibility = eligibilityUtil.getEligibility(entityItemIdentifier, zBooleanValue, zBooleanValue2, z, subscriptionType, onDemandTiers, (64 & 64) != 0 ? false : false);
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    public Integer updateEligibilityData(ContentEligibilityData eligibilityData) {
        Intrinsics.checkNotNullParameter(eligibilityData, "eligibilityData");
        Integer numUpdateEligibilityData = EligibilityMetadata.DefaultImpls.updateEligibilityData(this, eligibilityData);
        if (numUpdateEligibilityData != null) {
            refreshContentEligibility();
        }
        return numUpdateEligibilityData;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(TrackMetadata.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            TrackMetadata trackMetadata = (TrackMetadata) other;
            return areEqual(other) && Intrinsics.areEqual(this.asin, trackMetadata.asin) && Intrinsics.areEqual(this.title, trackMetadata.title) && Intrinsics.areEqual(this.imageUrl, trackMetadata.imageUrl) && Intrinsics.areEqual(this.cachedImageId, trackMetadata.cachedImageId) && Intrinsics.areEqual(this.albumAsin, trackMetadata.albumAsin) && Intrinsics.areEqual(this.albumTitle, trackMetadata.albumTitle) && Intrinsics.areEqual(this.albumArtistTitle, trackMetadata.albumArtistTitle) && Intrinsics.areEqual(this.artistAsin, trackMetadata.artistAsin) && Intrinsics.areEqual(this.artistTitle, trackMetadata.artistTitle) && Intrinsics.areEqual(this.source, trackMetadata.source) && Intrinsics.areEqual(this.popularity, trackMetadata.popularity) && Intrinsics.areEqual(this.isInLibrary, trackMetadata.isInLibrary) && Intrinsics.areEqual(getIsOwned(), trackMetadata.getIsOwned()) && Intrinsics.areEqual(this.downloadState, trackMetadata.downloadState) && Intrinsics.areEqual(this.isExplicit, trackMetadata.isExplicit) && Intrinsics.areEqual(this.rowNumber, trackMetadata.rowNumber) && Intrinsics.areEqual(this.supportedTiers, trackMetadata.supportedTiers) && Intrinsics.areEqual(this.assetType, trackMetadata.assetType) && Intrinsics.areEqual(this.assetQuality, trackMetadata.assetQuality) && this.duration == trackMetadata.duration && Intrinsics.areEqual(this.trackId, trackMetadata.trackId) && Intrinsics.areEqual(this.hasLyrics, trackMetadata.hasLyrics) && Intrinsics.areEqual(this.contentEncodings, trackMetadata.contentEncodings) && Intrinsics.areEqual(this.trackPosition, trackMetadata.trackPosition) && Intrinsics.areEqual(this.luid, trackMetadata.luid) && this.isSideLoaded == trackMetadata.isSideLoaded && this.isSuggestion == trackMetadata.isSuggestion && Intrinsics.areEqual(this.sortTitle, trackMetadata.sortTitle) && Intrinsics.areEqual(this.playmodeEligibility, trackMetadata.playmodeEligibility) && Intrinsics.areEqual(this.containerMetadata, trackMetadata.containerMetadata) && Intrinsics.areEqual(getOnDemandTiers(), trackMetadata.getOnDemandTiers()) && Intrinsics.areEqual(this.languageOfPerformances, trackMetadata.languageOfPerformances);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.TrackMetadata");
    }

    public int hashCode() {
        String viewId = getViewId();
        return ((viewId == null ? 0 : viewId.hashCode()) * 31) + getUri().hashCode();
    }
}
