package com.amazon.music.views.library.metadata;

import android.net.Uri;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.music.ContentAccessType;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemCollection;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.metadata.EligibilityMetadata;
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

/* JADX INFO: compiled from: AlbumMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B\u008b\u0002\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\"\u001a\u00020!\u0012\u0010\b\u0002\u0010(\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u000104\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u001a\u0012\f\u0010F\u001a\b\u0012\u0004\u0012\u00020E0D\u0012\b\b\u0002\u0010L\u001a\u00020\u001a\u0012\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u001a\u0012\u0010\b\u0002\u0010U\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010X\u001a\u0004\u0018\u00010W\u0012\u0010\b\u0002\u0010]\u001a\n\u0012\u0004\u0012\u00020\\\u0018\u00010D\u0012\n\b\u0002\u0010`\u001a\u0004\u0018\u00010\u001a\u0012\u0010\b\u0002\u0010b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0004\bp\u0010qJ\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016J\u0014\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J\u0013\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u001c\u001a\u00020\u0012H\u0016R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\"\u0010\"\u001a\u00020!8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R*\u0010(\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0019\u0010.\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b.\u0010\u001e\u001a\u0004\b/\u0010 R\u0019\u00100\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b0\u0010\u001e\u001a\u0004\b1\u0010 R\u0019\u00102\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b2\u0010\u001e\u001a\u0004\b3\u0010 R\u0019\u00105\u001a\u0004\u0018\u0001048\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0019\u00109\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b9\u0010\u001e\u001a\u0004\b:\u0010 R\u0019\u0010;\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b;\u0010\u001e\u001a\u0004\b<\u0010 R$\u0010=\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b=\u0010>\u001a\u0004\b=\u0010?\"\u0004\b@\u0010AR$\u0010B\u001a\u0004\u0018\u00010\u001a8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bB\u0010>\u001a\u0004\bB\u0010?\"\u0004\bC\u0010AR(\u0010F\u001a\b\u0012\u0004\u0012\u00020E0D8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u0017\u0010L\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\bL\u0010M\u001a\u0004\bL\u0010NR\u0019\u0010O\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010RR$\u0010S\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bS\u0010>\u001a\u0004\bS\u0010?\"\u0004\bT\u0010AR\u001f\u0010U\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\bU\u0010)\u001a\u0004\bV\u0010+R\u0019\u0010X\u001a\u0004\u0018\u00010W8\u0006¢\u0006\f\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010[R*\u0010]\u001a\n\u0012\u0004\u0012\u00020\\\u0018\u00010D8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b]\u0010G\u001a\u0004\b^\u0010I\"\u0004\b_\u0010KR$\u0010`\u001a\u0004\u0018\u00010\u001a8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b`\u0010>\u001a\u0004\b`\u0010?\"\u0004\ba\u0010AR\u001c\u0010b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010)R\u0018\u0010d\u001a\u0004\u0018\u00010c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bd\u0010eR4\u0010i\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010D2\u000e\u0010f\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010D8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bg\u0010I\"\u0004\bh\u0010KR\u0016\u0010m\u001a\u0004\u0018\u00010j8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bk\u0010lR\u001c\u0010o\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bn\u0010+¨\u0006r"}, d2 = {"Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "Lcom/amazon/music/views/library/metadata/EligibilityMetadata;", "Lcom/amazon/mp3/library/refinement/Refinable;", "Lcom/amazon/music/platform/data/entity/EntityItemCollection;", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "Lcom/amazon/music/views/library/providers/LanguageOfPerformancesProvider;", "", "refreshContentEligibility", "", "", "getLanguageOfPerformances", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "getEligibilities", "Lcom/amazon/music/platform/data/entity/EntityItem;", "getEntityItem", "Lcom/amazon/music/views/library/models/base/ContentEligibilityData;", "eligibilityData", "", "updateEligibilityData", "(Lcom/amazon/music/views/library/models/base/ContentEligibilityData;)Ljava/lang/Integer;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "update", "", "other", "", "equals", "hashCode", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "asin", "getAsin", "title", "getTitle", "imageUrl", "getImageUrl", "", "cachedImageId", "Ljava/lang/Long;", "getCachedImageId", "()Ljava/lang/Long;", "artistAsin", "getArtistAsin", "artistTitle", "getArtistTitle", "isInLibrary", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "setInLibrary", "(Ljava/lang/Boolean;)V", "isOwned", "setOwned", "", "Lcom/amazon/music/ContentAccessType;", "supportedTiers", "Ljava/util/Set;", "getSupportedTiers", "()Ljava/util/Set;", "setSupportedTiers", "(Ljava/util/Set;)V", BrushV2Converter.KEY_IS_EXPLICIT, "Z", "()Z", "trackCount", "Ljava/lang/Integer;", "getTrackCount", "()Ljava/lang/Integer;", "isFollowed", "setFollowed", "contentEncodings", "getContentEncodings", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "onDemandTiers", "getOnDemandTiers", "setOnDemandTiers", "isDownloaded", "setDownloaded", "languageOfPerformances", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "contentEligibility", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "value", "getContentTiers", "setContentTiers", "contentTiers", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getEntityItemIdentifier", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "entityItemIdentifier", "getItems", ContextMappingConstants.ITEMS, "<init>", "(Ljava/lang/String;Landroid/net/Uri;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/Set;ZLjava/lang/Integer;Ljava/lang/Boolean;Ljava/util/List;Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;Ljava/util/Set;Ljava/lang/Boolean;Ljava/util/List;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class AlbumMetadata implements EligibilityMetadata, Refinable, EntityItemCollection, EntityEligibilitiesProvider, LanguageOfPerformancesProvider {
    private final String artistAsin;
    private final String artistTitle;
    private final String asin;
    private final Long cachedImageId;
    private ContentEligibility contentEligibility;
    private final List<String> contentEncodings;
    private final String imageUrl;
    private Boolean isDownloaded;
    private final boolean isExplicit;
    private Boolean isFollowed;
    private Boolean isInLibrary;
    private Boolean isOwned;
    private final List<String> languageOfPerformances;
    private Set<? extends SubscriptionType> onDemandTiers;
    private final PlaymodeEligibility playmodeEligibility;
    private Set<? extends ContentAccessType> supportedTiers;
    private List<String> tags;
    private final String title;
    private final Integer trackCount;
    private Uri uri;
    private final String viewId;

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    public EntityItem getEntityItem() {
        return this;
    }

    @Override // com.amazon.music.platform.data.entity.EntityItemCollection
    public List<EntityItem> getItems() {
        return null;
    }

    public AlbumMetadata(String str, Uri uri, List<String> list, String str2, String str3, String str4, Long l, String str5, String str6, Boolean bool, Boolean bool2, Set<? extends ContentAccessType> supportedTiers, boolean z, Integer num, Boolean bool3, List<String> list2, PlaymodeEligibility playmodeEligibility, Set<? extends SubscriptionType> set, Boolean bool4, List<String> list3) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(supportedTiers, "supportedTiers");
        this.viewId = str;
        this.uri = uri;
        this.tags = list;
        this.asin = str2;
        this.title = str3;
        this.imageUrl = str4;
        this.cachedImageId = l;
        this.artistAsin = str5;
        this.artistTitle = str6;
        this.isInLibrary = bool;
        this.isOwned = bool2;
        this.supportedTiers = supportedTiers;
        this.isExplicit = z;
        this.trackCount = num;
        this.isFollowed = bool3;
        this.contentEncodings = list2;
        this.playmodeEligibility = playmodeEligibility;
        this.onDemandTiers = set;
        this.isDownloaded = bool4;
        this.languageOfPerformances = list3;
        refreshContentEligibility();
    }

    public boolean areEqual(Object obj) {
        return EligibilityMetadata.DefaultImpls.areEqual(this, obj);
    }

    @Override // com.amazon.music.views.library.metadata.UpdatableContentMetadata
    public boolean areItemsTheSame(Object obj) {
        return EligibilityMetadata.DefaultImpls.areItemsTheSame(this, obj);
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

    public final String getArtistAsin() {
        return this.artistAsin;
    }

    public final String getArtistTitle() {
        return this.artistTitle;
    }

    public /* synthetic */ AlbumMetadata(String str, Uri uri, List list, String str2, String str3, String str4, Long l, String str5, String str6, Boolean bool, Boolean bool2, Set set, boolean z, Integer num, Boolean bool3, List list2, PlaymodeEligibility playmodeEligibility, Set set2, Boolean bool4, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, uri, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : l, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? Boolean.FALSE : bool, (i & 1024) != 0 ? Boolean.FALSE : bool2, set, (i & 4096) != 0 ? false : z, (i & 8192) != 0 ? null : num, (i & 16384) != 0 ? null : bool3, (32768 & i) != 0 ? null : list2, (65536 & i) != 0 ? null : playmodeEligibility, (131072 & i) != 0 ? null : set2, (262144 & i) != 0 ? null : bool4, (i & 524288) != 0 ? null : list3);
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

    public final Set<ContentAccessType> getSupportedTiers() {
        return this.supportedTiers;
    }

    /* JADX INFO: renamed from: isExplicit, reason: from getter */
    public final boolean getIsExplicit() {
        return this.isExplicit;
    }

    public final Integer getTrackCount() {
        return this.trackCount;
    }

    /* JADX INFO: renamed from: isFollowed, reason: from getter */
    public final Boolean getIsFollowed() {
        return this.isFollowed;
    }

    public final void setFollowed(Boolean bool) {
        this.isFollowed = bool;
    }

    public final List<String> getContentEncodings() {
        return this.contentEncodings;
    }

    public final PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    public Set<SubscriptionType> getOnDemandTiers() {
        return this.onDemandTiers;
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    /* JADX INFO: renamed from: isDownloaded, reason: from getter */
    public Boolean getIsDownloaded() {
        return this.isDownloaded;
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    public void setDownloaded(Boolean bool) {
        this.isDownloaded = bool;
    }

    @Override // com.amazon.music.views.library.providers.LanguageOfPerformancesProvider
    public List<String> getLanguageOfPerformances() {
        return this.languageOfPerformances;
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    public Set<ContentAccessType> getContentTiers() {
        return this.supportedTiers;
    }

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    public void setContentTiers(Set<? extends ContentAccessType> set) {
        if (set == null) {
            set = SetsKt__SetsKt.emptySet();
        }
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

    @Override // com.amazon.music.platform.data.entity.EntityItem
    public EntityItemIdentifier getEntityItemIdentifier() {
        if (this.asin != null) {
            return new EntityItemIdentifier(new Asin(this.asin), CollectionType.Album.INSTANCE);
        }
        return null;
    }

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    public Map<String, Eligibility> getEligibilities() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
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

    @Override // com.amazon.music.views.library.metadata.EligibilityMetadata
    public Integer updateEligibilityData(ContentEligibilityData eligibilityData) {
        Intrinsics.checkNotNullParameter(eligibilityData, "eligibilityData");
        Integer numUpdateEligibilityData = EligibilityMetadata.DefaultImpls.updateEligibilityData(this, eligibilityData);
        if (numUpdateEligibilityData != null) {
            refreshContentEligibility();
        }
        return numUpdateEligibilityData;
    }

    @Override // com.amazon.music.views.library.metadata.UpdatableContentMetadata
    public void update(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        AlbumMetadata albumMetadata = metadata instanceof AlbumMetadata ? (AlbumMetadata) metadata : null;
        if (albumMetadata == null) {
            return;
        }
        setUri(albumMetadata.getUri());
        setInLibrary(albumMetadata.getIsInLibrary());
        setOwned(albumMetadata.getIsOwned());
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(AlbumMetadata.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            AlbumMetadata albumMetadata = (AlbumMetadata) other;
            return areEqual(other) && Intrinsics.areEqual(this.asin, albumMetadata.asin) && Intrinsics.areEqual(this.title, albumMetadata.title) && Intrinsics.areEqual(this.imageUrl, albumMetadata.imageUrl) && Intrinsics.areEqual(this.cachedImageId, albumMetadata.cachedImageId) && Intrinsics.areEqual(this.artistAsin, albumMetadata.artistAsin) && Intrinsics.areEqual(this.artistTitle, albumMetadata.artistTitle) && Intrinsics.areEqual(this.isInLibrary, albumMetadata.isInLibrary) && Intrinsics.areEqual(getIsOwned(), albumMetadata.getIsOwned()) && Intrinsics.areEqual(this.supportedTiers, albumMetadata.supportedTiers) && this.isExplicit == albumMetadata.isExplicit && Intrinsics.areEqual(this.trackCount, albumMetadata.trackCount) && Intrinsics.areEqual(this.contentEncodings, albumMetadata.contentEncodings) && Intrinsics.areEqual(this.playmodeEligibility, albumMetadata.playmodeEligibility) && Intrinsics.areEqual(getOnDemandTiers(), albumMetadata.getOnDemandTiers()) && Intrinsics.areEqual(this.languageOfPerformances, albumMetadata.languageOfPerformances);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.AlbumMetadata");
    }

    public int hashCode() {
        String viewId = getViewId();
        return ((viewId == null ? 0 : viewId.hashCode()) * 31) + getUri().hashCode();
    }
}
