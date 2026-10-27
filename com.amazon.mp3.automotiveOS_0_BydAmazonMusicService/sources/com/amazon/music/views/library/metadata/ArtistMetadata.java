package com.amazon.music.views.library.metadata;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.music.ContentAccessType;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.identifiers.ObjectId;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.metadata.UpdatableContentMetadata;
import com.amazon.music.views.library.utils.EligibilityUtil;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtistMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004Bû\u0001\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010 \u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0012\u0012\u0010\b\u0002\u00107\u001a\n\u0012\u0004\u0012\u000206\u0018\u000105\u0012\b\b\u0002\u0010=\u001a\u00020\u000b\u0012\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010E\u001a\u0004\u0018\u00010D\u0012\b\b\u0002\u0010I\u001a\u00020\u000b\u0012\n\b\u0002\u0010K\u001a\u0004\u0018\u00010J\u0012\u000e\b\u0002\u0010P\u001a\b\u0012\u0004\u0012\u00020O05\u0012\b\b\u0002\u0010R\u001a\u00020\u000b\u0012\b\b\u0002\u0010S\u001a\u00020\u000b\u0012\n\b\u0002\u0010T\u001a\u0004\u0018\u00010D¢\u0006\u0004\b]\u0010^J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\n\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0002J\u0013\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0096\u0002J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\u0003H\u0016J\u0014\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011H\u0016R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00128\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u001a\u001a\u00020\u00198\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR*\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010 8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0019\u0010'\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b'\u0010\u0016\u001a\u0004\b(\u0010\u0018R\u0019\u0010)\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b)\u0010\u0016\u001a\u0004\b*\u0010\u0018R\u0019\u0010+\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b+\u0010\u0016\u001a\u0004\b,\u0010\u0018R\u0019\u0010-\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b-\u0010\u0016\u001a\u0004\b.\u0010\u0018R\u0019\u0010/\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b/\u0010\u0016\u001a\u0004\b0\u0010\u0018R\u0019\u00101\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b1\u0010\u0016\u001a\u0004\b2\u0010\u0018R\u0019\u00103\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b3\u0010\u0016\u001a\u0004\b4\u0010\u0018R*\u00107\u001a\n\u0012\u0004\u0012\u000206\u0018\u0001058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\"\u0010=\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b=\u0010>\u001a\u0004\b=\u0010?\"\u0004\b@\u0010AR\u0019\u0010B\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\bB\u0010\u0016\u001a\u0004\bC\u0010\u0018R\u0019\u0010E\u001a\u0004\u0018\u00010D8\u0006¢\u0006\f\n\u0004\bE\u0010F\u001a\u0004\bG\u0010HR\u0017\u0010I\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\bI\u0010>\u001a\u0004\bI\u0010?R\u0019\u0010K\u001a\u0004\u0018\u00010J8\u0006¢\u0006\f\n\u0004\bK\u0010L\u001a\u0004\bM\u0010NR\u001d\u0010P\u001a\b\u0012\u0004\u0012\u00020O058\u0006¢\u0006\f\n\u0004\bP\u00108\u001a\u0004\bQ\u0010:R\u0017\u0010R\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\bR\u0010>\u001a\u0004\bR\u0010?R\u0017\u0010S\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\bS\u0010>\u001a\u0004\bS\u0010?R\u0019\u0010T\u001a\u0004\u0018\u00010D8\u0006¢\u0006\f\n\u0004\bT\u0010F\u001a\u0004\bU\u0010HR\"\u0010W\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130V8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010XR\u0016\u0010\\\u001a\u0004\u0018\u00010Y8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010[¨\u0006_"}, d2 = {"Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "Lcom/amazon/music/views/library/metadata/UpdatableContentMetadata;", "Lcom/amazon/mp3/library/refinement/Refinable;", "Lcom/amazon/music/platform/data/entity/EntityItem;", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "", "setupEligibilites", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "getContentEligibility", "", "other", "", "equals", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "update", "getEntityItem", "", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "getEligibilities", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "stationKey", "getStationKey", "asin", "getAsin", "title", "getTitle", "name", "getName", "imageUrl", "getImageUrl", "contributorAsin", "getContributorAsin", PageUriUtils.SOURCE_QUERY_PARAM, "getSource", "", "Lcom/amazon/music/ContentAccessType;", "supportedTiers", "Ljava/util/Set;", "getSupportedTiers", "()Ljava/util/Set;", "setSupportedTiers", "(Ljava/util/Set;)V", "isFollowing", "Z", "()Z", "setFollowing", "(Z)V", "artistId", "getArtistId", "", "trackCount", "Ljava/lang/Long;", "getTrackCount", "()Ljava/lang/Long;", "isAllOwned", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "onDemandTiers", "getOnDemandTiers", "isDownloaded", "isAvailable", "dateFollowed", "getDateFollowed", "", "eligibilites", "Ljava/util/Map;", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getEntityItemIdentifier", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "entityItemIdentifier", "<init>", "(Ljava/lang/String;Landroid/net/Uri;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;ZLjava/lang/String;Ljava/lang/Long;ZLcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;Ljava/util/Set;ZZLjava/lang/Long;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ArtistMetadata implements UpdatableContentMetadata, Refinable, EntityItem, EntityEligibilitiesProvider {
    private final String artistId;
    private final String asin;
    private final String contributorAsin;
    private final Long dateFollowed;
    private Map<String, Eligibility> eligibilites;
    private final String imageUrl;
    private final boolean isAllOwned;
    private final boolean isAvailable;
    private final boolean isDownloaded;
    private boolean isFollowing;
    private final String name;
    private final Set<SubscriptionType> onDemandTiers;
    private final PlaymodeEligibility playmodeEligibility;
    private final String source;
    private final String stationKey;
    private Set<? extends ContentAccessType> supportedTiers;
    private List<String> tags;
    private final String title;
    private final Long trackCount;
    private Uri uri;
    private final String viewId;

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    public EntityItem getEntityItem() {
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArtistMetadata(String str, Uri uri, List<String> list, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Set<? extends ContentAccessType> set, boolean z, String str9, Long l, boolean z2, PlaymodeEligibility playmodeEligibility, Set<? extends SubscriptionType> onDemandTiers, boolean z3, boolean z4, Long l2) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(onDemandTiers, "onDemandTiers");
        this.viewId = str;
        this.uri = uri;
        this.tags = list;
        this.stationKey = str2;
        this.asin = str3;
        this.title = str4;
        this.name = str5;
        this.imageUrl = str6;
        this.contributorAsin = str7;
        this.source = str8;
        this.supportedTiers = set;
        this.isFollowing = z;
        this.artistId = str9;
        this.trackCount = l;
        this.isAllOwned = z2;
        this.playmodeEligibility = playmodeEligibility;
        this.onDemandTiers = onDemandTiers;
        this.isDownloaded = z3;
        this.isAvailable = z4;
        this.dateFollowed = l2;
        this.eligibilites = new LinkedHashMap();
    }

    @Override // com.amazon.music.views.library.metadata.UpdatableContentMetadata
    public boolean areItemsTheSame(Object obj) {
        return UpdatableContentMetadata.DefaultImpls.areItemsTheSame(this, obj);
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

    public final String getName() {
        return this.name;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final String getContributorAsin() {
        return this.contributorAsin;
    }

    public final String getSource() {
        return this.source;
    }

    public final Set<ContentAccessType> getSupportedTiers() {
        return this.supportedTiers;
    }

    /* JADX INFO: renamed from: isFollowing, reason: from getter */
    public final boolean getIsFollowing() {
        return this.isFollowing;
    }

    public final void setFollowing(boolean z) {
        this.isFollowing = z;
    }

    public final String getArtistId() {
        return this.artistId;
    }

    public final Long getTrackCount() {
        return this.trackCount;
    }

    /* JADX INFO: renamed from: isAllOwned, reason: from getter */
    public final boolean getIsAllOwned() {
        return this.isAllOwned;
    }

    public final PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public /* synthetic */ ArtistMetadata(String str, Uri uri, List list, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Set set, boolean z, String str9, Long l, boolean z2, PlaymodeEligibility playmodeEligibility, Set set2, boolean z3, boolean z4, Long l2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, uri, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? null : str8, (i & 1024) != 0 ? null : set, (i & 2048) != 0 ? false : z, (i & 4096) != 0 ? null : str9, (i & 8192) != 0 ? null : l, (i & 16384) != 0 ? false : z2, (32768 & i) != 0 ? null : playmodeEligibility, (65536 & i) != 0 ? SetsKt__SetsKt.emptySet() : set2, (131072 & i) != 0 ? false : z3, (262144 & i) != 0 ? false : z4, (i & 524288) != 0 ? null : l2);
    }

    public final Set<SubscriptionType> getOnDemandTiers() {
        return this.onDemandTiers;
    }

    /* JADX INFO: renamed from: isDownloaded, reason: from getter */
    public final boolean getIsDownloaded() {
        return this.isDownloaded;
    }

    /* JADX INFO: renamed from: isAvailable, reason: from getter */
    public final boolean getIsAvailable() {
        return this.isAvailable;
    }

    public final Long getDateFollowed() {
        return this.dateFollowed;
    }

    @Override // com.amazon.music.platform.data.entity.EntityItem
    public EntityItemIdentifier getEntityItemIdentifier() {
        if (this.asin != null) {
            return new EntityItemIdentifier(new Asin(this.asin), CollectionType.Artist.INSTANCE);
        }
        if (this.artistId != null) {
            return new EntityItemIdentifier(new ObjectId(this.artistId), CollectionType.Artist.INSTANCE);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other instanceof ArtistMetadata) {
            return areItemsTheSame(other) || (!TextUtils.isEmpty(this.asin) && Intrinsics.areEqual(this.asin, ((ArtistMetadata) other).asin));
        }
        return false;
    }

    @Override // com.amazon.music.views.library.metadata.UpdatableContentMetadata
    public void update(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        ArtistMetadata artistMetadata = metadata instanceof ArtistMetadata ? (ArtistMetadata) metadata : null;
        if (artistMetadata == null) {
            return;
        }
        setUri(artistMetadata.getUri());
        setFollowing(artistMetadata.getIsFollowing());
    }

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    public Map<String, Eligibility> getEligibilities() {
        if (this.eligibilites.isEmpty() && getEntityItemIdentifier() != null) {
            setupEligibilites();
        }
        return this.eligibilites;
    }

    private final void setupEligibilites() {
        PlaymodeEligibility playmodeEligibility = this.playmodeEligibility;
        if (playmodeEligibility != null) {
            this.eligibilites.put(playmodeEligibility.getTYPE(), playmodeEligibility);
        }
        ContentEligibility contentEligibility = getContentEligibility();
        if (contentEligibility == null) {
            return;
        }
        this.eligibilites.put(contentEligibility.getTYPE(), contentEligibility);
    }

    private final ContentEligibility getContentEligibility() {
        EntityItemIdentifier entityItemIdentifier = getEntityItemIdentifier();
        if (entityItemIdentifier == null) {
            return null;
        }
        EligibilityUtil eligibilityUtil = EligibilityUtil.INSTANCE;
        return eligibilityUtil.getEligibility(entityItemIdentifier, getIsAllOwned(), getIsDownloaded(), getPlaymodeEligibility() != null, eligibilityUtil.toSubscriptionType(getSupportedTiers()), getOnDemandTiers(), getIsAvailable());
    }
}
