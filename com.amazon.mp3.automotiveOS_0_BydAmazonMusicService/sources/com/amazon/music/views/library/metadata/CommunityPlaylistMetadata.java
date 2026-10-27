package com.amazon.music.views.library.metadata;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.music.ContentAccessType;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemCollection;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.identifiers.ObjectId;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.metadata.UpdatableContentMetadata;
import com.amazon.music.views.library.utils.EligibilityUtil;
import com.google.android.gms.cast.MediaTrack;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommunityPlaylistMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003BÁ\u0001\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010 \u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010/\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u00108\u001a\u00020\f\u0012\u0006\u0010=\u001a\u00020\u0005\u0012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010?\u0012\u0010\b\u0002\u0010F\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010D\u0012\u0010\b\u0002\u0010K\u001a\n\u0012\u0004\u0012\u00020J\u0018\u00010D\u0012\b\b\u0002\u0010M\u001a\u00020\f\u0012\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\bZ\u0010[J\u0014\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0013\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0096\u0002J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u001a\u001a\u00020\u00198\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR*\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010 8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010'\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010\u0016\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010*R\u0019\u0010+\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b+\u0010\u0016\u001a\u0004\b,\u0010\u0018R\u0019\u0010-\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b-\u0010\u0016\u001a\u0004\b.\u0010\u0018R$\u00100\u001a\u0004\u0018\u00010/8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0019\u00106\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b6\u0010\u0016\u001a\u0004\b7\u0010\u0018R\"\u00108\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b8\u0010:\"\u0004\b;\u0010<R\u0017\u0010=\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b=\u0010\u0016\u001a\u0004\b>\u0010\u0018R\u0019\u0010@\u001a\u0004\u0018\u00010?8\u0006¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u001f\u0010F\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010D8\u0006¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bH\u0010IR\u001f\u0010K\u001a\n\u0012\u0004\u0012\u00020J\u0018\u00010D8\u0006¢\u0006\f\n\u0004\bK\u0010G\u001a\u0004\bL\u0010IR\u0017\u0010M\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\bM\u00109\u001a\u0004\bM\u0010:R$\u0010N\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bN\u0010\u0016\u001a\u0004\bO\u0010\u0018\"\u0004\bP\u0010*R\u0018\u0010R\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010W\u001a\u0004\u0018\u00010T8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bU\u0010VR\u001c\u0010Y\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bX\u0010$¨\u0006\\"}, d2 = {"Lcom/amazon/music/views/library/metadata/CommunityPlaylistMetadata;", "Lcom/amazon/music/views/library/metadata/UpdatableContentMetadata;", "Lcom/amazon/music/platform/data/entity/EntityItemCollection;", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "getEligibilities", "Lcom/amazon/music/platform/data/entity/EntityItem;", "getEntityItem", "", "other", "", "equals", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "", "update", "", "hashCode", "toString", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "title", "getTitle", "setTitle", "(Ljava/lang/String;)V", "imageUrl", "getImageUrl", MediaTrack.ROLE_DESCRIPTION, "getDescription", "", "trackCount", "Ljava/lang/Long;", "getTrackCount", "()Ljava/lang/Long;", "setTrackCount", "(Ljava/lang/Long;)V", "sharingUrl", "getSharingUrl", "isOwned", "Z", "()Z", "setOwned", "(Z)V", "playlistId", "getPlaylistId", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "", "Lcom/amazon/music/ContentAccessType;", "contentTiers", "Ljava/util/Set;", "getContentTiers", "()Ljava/util/Set;", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "onDemandTiers", "getOnDemandTiers", "isDownloaded", "curator", "getCurator", "setCurator", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "contentEligibility", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getEntityItemIdentifier", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "entityItemIdentifier", "getItems", ContextMappingConstants.ITEMS, "<init>", "(Ljava/lang/String;Landroid/net/Uri;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;ZLjava/lang/String;Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;Ljava/util/Set;Ljava/util/Set;ZLjava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class CommunityPlaylistMetadata implements UpdatableContentMetadata, EntityItemCollection, EntityEligibilitiesProvider {
    private ContentEligibility contentEligibility;
    private final Set<ContentAccessType> contentTiers;
    private String curator;
    private final String description;
    private final String imageUrl;
    private final boolean isDownloaded;
    private boolean isOwned;
    private final Set<SubscriptionType> onDemandTiers;
    private final String playlistId;
    private final PlaymodeEligibility playmodeEligibility;
    private final String sharingUrl;
    private List<String> tags;
    private String title;
    private Long trackCount;
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

    public String toString() {
        return "CommunityPlaylistMetadata(viewId=" + ((Object) getViewId()) + ", uri=" + getUri() + ", tags=" + getTags() + ", title=" + ((Object) this.title) + ", imageUrl=" + ((Object) this.imageUrl) + ", description=" + ((Object) this.description) + ", trackCount=" + this.trackCount + ", sharingUrl=" + ((Object) this.sharingUrl) + ", isOwned=" + this.isOwned + ", playlistId=" + this.playlistId + ", playmodeEligibility=" + this.playmodeEligibility + ", contentTiers=" + this.contentTiers + ", onDemandTiers=" + this.onDemandTiers + ", isDownloaded=" + this.isDownloaded + ", curator=" + ((Object) this.curator) + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CommunityPlaylistMetadata(String str, Uri uri, List<String> list, String str2, String str3, String str4, Long l, String str5, boolean z, String playlistId, PlaymodeEligibility playmodeEligibility, Set<? extends ContentAccessType> set, Set<? extends SubscriptionType> set2, boolean z2, String str6) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        this.viewId = str;
        this.uri = uri;
        this.tags = list;
        this.title = str2;
        this.imageUrl = str3;
        this.description = str4;
        this.trackCount = l;
        this.sharingUrl = str5;
        this.isOwned = z;
        this.playlistId = playlistId;
        this.playmodeEligibility = playmodeEligibility;
        this.contentTiers = set;
        this.onDemandTiers = set2;
        this.isDownloaded = z2;
        this.curator = str6;
        EntityItemIdentifier entityItemIdentifier = getEntityItemIdentifier();
        if (entityItemIdentifier == null) {
            return;
        }
        EligibilityUtil eligibilityUtil = EligibilityUtil.INSTANCE;
        boolean isOwned = getIsOwned();
        boolean isDownloaded = getIsDownloaded();
        boolean z3 = getPlaymodeEligibility() != null;
        Set<SubscriptionType> subscriptionType = eligibilityUtil.toSubscriptionType(getContentTiers());
        subscriptionType = subscriptionType == null ? SetsKt__SetsKt.emptySet() : subscriptionType;
        Set<SubscriptionType> onDemandTiers = getOnDemandTiers();
        this.contentEligibility = eligibilityUtil.getEligibility(entityItemIdentifier, isOwned, isDownloaded, z3, subscriptionType, onDemandTiers == null ? SetsKt__SetsKt.emptySet() : onDemandTiers, (64 & 64) != 0 ? false : false);
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

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final String getDescription() {
        return this.description;
    }

    public /* synthetic */ CommunityPlaylistMetadata(String str, Uri uri, List list, String str2, String str3, String str4, Long l, String str5, boolean z, String str6, PlaymodeEligibility playmodeEligibility, Set set, Set set2, boolean z2, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, uri, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? 0L : l, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? false : z, str6, (i & 1024) != 0 ? null : playmodeEligibility, (i & 2048) != 0 ? null : set, (i & 4096) != 0 ? null : set2, (i & 8192) != 0 ? false : z2, (i & 16384) != 0 ? null : str7);
    }

    public final Long getTrackCount() {
        return this.trackCount;
    }

    public final void setTrackCount(Long l) {
        this.trackCount = l;
    }

    /* JADX INFO: renamed from: isOwned, reason: from getter */
    public final boolean getIsOwned() {
        return this.isOwned;
    }

    public final void setOwned(boolean z) {
        this.isOwned = z;
    }

    public final String getPlaylistId() {
        return this.playlistId;
    }

    public final PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public final Set<ContentAccessType> getContentTiers() {
        return this.contentTiers;
    }

    public final Set<SubscriptionType> getOnDemandTiers() {
        return this.onDemandTiers;
    }

    /* JADX INFO: renamed from: isDownloaded, reason: from getter */
    public final boolean getIsDownloaded() {
        return this.isDownloaded;
    }

    public final String getCurator() {
        return this.curator;
    }

    public final void setCurator(String str) {
        this.curator = str;
    }

    @Override // com.amazon.music.platform.data.entity.EntityItem
    public EntityItemIdentifier getEntityItemIdentifier() {
        return new EntityItemIdentifier(new ObjectId(this.playlistId), CollectionType.Playlist.INSTANCE);
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

    public boolean equals(Object other) {
        return (other instanceof CommunityPlaylistMetadata) && areItemsTheSame(other) && !TextUtils.isEmpty(this.playlistId) && Intrinsics.areEqual(this.playlistId, ((CommunityPlaylistMetadata) other).playlistId);
    }

    @Override // com.amazon.music.views.library.metadata.UpdatableContentMetadata
    public void update(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (equals(metadata)) {
            CommunityPlaylistMetadata communityPlaylistMetadata = metadata instanceof CommunityPlaylistMetadata ? (CommunityPlaylistMetadata) metadata : null;
            if (communityPlaylistMetadata == null) {
                return;
            }
            setUri(communityPlaylistMetadata.getUri());
            setOwned(communityPlaylistMetadata.getIsOwned());
            setTrackCount(communityPlaylistMetadata.getTrackCount());
            setTitle(communityPlaylistMetadata.getTitle());
            setCurator(communityPlaylistMetadata.getCurator());
        }
    }

    public int hashCode() {
        String viewId = getViewId();
        return ((((viewId == null ? 0 : viewId.hashCode()) * 31) + getUri().hashCode()) * 31) + this.playlistId.hashCode();
    }
}
