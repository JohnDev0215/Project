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
import com.amazon.music.platform.data.identifiers.ObjectId;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.metadata.UpdatableContentMetadata;
import com.amazon.music.views.library.providers.LanguageOfPerformancesProvider;
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

/* JADX INFO: compiled from: PlaylistMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005BÃ\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0010\b\u0002\u0010$\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u000104\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0017\u0012\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u0015\u0012\f\u0010F\u001a\b\u0012\u0004\u0012\u00020E0D\u0012\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010N\u001a\u00020\u0015\u0012\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010V\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010X\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010[\u001a\u00020\u0015\u0012\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010^\u001a\u00020\u0015\u0012\n\b\u0002\u0010`\u001a\u0004\u0018\u00010_\u0012\u0010\b\u0002\u0010e\u001a\n\u0012\u0004\u0012\u00020d\u0018\u00010D\u0012\b\b\u0002\u0010g\u001a\u00020\u0015\u0012\u0010\b\u0002\u0010h\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0004\br\u0010sJ\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016J\u0014\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0013\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0016R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\"\u0010\u001e\u001a\u00020\u001d8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R*\u0010$\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0019\u0010*\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b*\u0010\u001a\u001a\u0004\b+\u0010\u001cR$\u0010,\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010\u001a\u001a\u0004\b-\u0010\u001c\"\u0004\b.\u0010/R\u0019\u00100\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b0\u0010\u001a\u001a\u0004\b1\u0010\u001cR\u0019\u00102\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b2\u0010\u001a\u001a\u0004\b3\u0010\u001cR\u0019\u00105\u001a\u0004\u0018\u0001048\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0019\u00109\u001a\u0004\u0018\u00010\u00178\u0006¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R\u001f\u0010=\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b=\u0010%\u001a\u0004\b>\u0010'R$\u0010?\u001a\u0004\u0018\u00010\u00158\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\b?\u0010A\"\u0004\bB\u0010CR(\u0010F\u001a\b\u0012\u0004\u0012\u00020E0D8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001d\u0010L\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006¢\u0006\f\n\u0004\bL\u0010%\u001a\u0004\bM\u0010'R\u0017\u0010N\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\bN\u0010O\u001a\u0004\bN\u0010PR\u0019\u0010Q\u001a\u0004\u0018\u00010\u00158\u0006¢\u0006\f\n\u0004\bQ\u0010@\u001a\u0004\bQ\u0010AR$\u0010R\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bR\u0010:\u001a\u0004\bS\u0010<\"\u0004\bT\u0010UR$\u0010V\u001a\u0004\u0018\u00010\u00158\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bV\u0010@\u001a\u0004\bV\u0010A\"\u0004\bW\u0010CR$\u0010X\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bX\u0010\u001a\u001a\u0004\bY\u0010\u001c\"\u0004\bZ\u0010/R\u0017\u0010[\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b[\u0010O\u001a\u0004\b[\u0010PR\u0019\u0010\\\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\\\u0010\u001a\u001a\u0004\b]\u0010\u001cR\u0017\u0010^\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b^\u0010O\u001a\u0004\b^\u0010PR\u0019\u0010`\u001a\u0004\u0018\u00010_8\u0006¢\u0006\f\n\u0004\b`\u0010a\u001a\u0004\bb\u0010cR\u001f\u0010e\u001a\n\u0012\u0004\u0012\u00020d\u0018\u00010D8\u0006¢\u0006\f\n\u0004\be\u0010G\u001a\u0004\bf\u0010IR\u0017\u0010g\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\bg\u0010O\u001a\u0004\bg\u0010PR\u001c\u0010h\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010%R\u0018\u0010j\u001a\u0004\u0018\u00010i8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bj\u0010kR\u0016\u0010o\u001a\u0004\u0018\u00010l8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bm\u0010nR\u001c\u0010q\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bp\u0010'¨\u0006t"}, d2 = {"Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "Lcom/amazon/music/views/library/metadata/UpdatableContentMetadata;", "Lcom/amazon/mp3/library/refinement/Refinable;", "Lcom/amazon/music/platform/data/entity/EntityItemCollection;", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "Lcom/amazon/music/views/library/providers/LanguageOfPerformancesProvider;", "", "refreshContentEligibility", "", "", "getLanguageOfPerformances", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "getEligibilities", "Lcom/amazon/music/platform/data/entity/EntityItem;", "getEntityItem", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "update", "", "other", "", "equals", "", "hashCode", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "asin", "getAsin", "title", "getTitle", "setTitle", "(Ljava/lang/String;)V", "imageUrl", "getImageUrl", MediaTrack.ROLE_DESCRIPTION, "getDescription", "", "rating", "Ljava/lang/Float;", "getRating", "()Ljava/lang/Float;", "numReviews", "Ljava/lang/Integer;", "getNumReviews", "()Ljava/lang/Integer;", "authors", "getAuthors", "isInLibrary", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "setInLibrary", "(Ljava/lang/Boolean;)V", "", "Lcom/amazon/music/ContentAccessType;", "supportedTiers", "Ljava/util/Set;", "getSupportedTiers", "()Ljava/util/Set;", "setSupportedTiers", "(Ljava/util/Set;)V", "contentTypes", "getContentTypes", BrushV2Converter.KEY_IS_EXPLICIT, "Z", "()Z", "isFree", "trackCount", "getTrackCount", "setTrackCount", "(Ljava/lang/Integer;)V", "isFollowed", "setFollowed", "luid", "getLuid", "setLuid", "isAllOwned", "version", "getVersion", "isPartiallyOwned", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "onDemandTiers", "getOnDemandTiers", "isDownloaded", "languageOfPerformances", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "contentEligibility", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getEntityItemIdentifier", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "entityItemIdentifier", "getItems", ContextMappingConstants.ITEMS, "<init>", "(Ljava/lang/String;Landroid/net/Uri;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Boolean;Ljava/util/Set;Ljava/util/List;ZLjava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;ZLjava/lang/String;ZLcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;Ljava/util/Set;ZLjava/util/List;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class PlaylistMetadata implements UpdatableContentMetadata, Refinable, EntityItemCollection, EntityEligibilitiesProvider, LanguageOfPerformancesProvider {
    private final String asin;
    private final List<String> authors;
    private ContentEligibility contentEligibility;
    private final List<String> contentTypes;
    private final String description;
    private final String imageUrl;
    private final boolean isAllOwned;
    private final boolean isDownloaded;
    private final boolean isExplicit;
    private Boolean isFollowed;
    private final Boolean isFree;
    private Boolean isInLibrary;
    private final boolean isPartiallyOwned;
    private final List<String> languageOfPerformances;
    private String luid;
    private final Integer numReviews;
    private final Set<SubscriptionType> onDemandTiers;
    private final PlaymodeEligibility playmodeEligibility;
    private final Float rating;
    private Set<? extends ContentAccessType> supportedTiers;
    private List<String> tags;
    private String title;
    private Integer trackCount;
    private Uri uri;
    private final String version;
    private final String viewId;

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    public EntityItem getEntityItem() {
        return this;
    }

    @Override // com.amazon.music.platform.data.entity.EntityItemCollection
    public List<EntityItem> getItems() {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PlaylistMetadata(String str, Uri uri, List<String> list, String str2, String str3, String str4, String str5, Float f, Integer num, List<String> list2, Boolean bool, Set<? extends ContentAccessType> supportedTiers, List<String> contentTypes, boolean z, Boolean bool2, Integer num2, Boolean bool3, String str6, boolean z2, String str7, boolean z3, PlaymodeEligibility playmodeEligibility, Set<? extends SubscriptionType> set, boolean z4, List<String> list3) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(supportedTiers, "supportedTiers");
        Intrinsics.checkNotNullParameter(contentTypes, "contentTypes");
        this.viewId = str;
        this.uri = uri;
        this.tags = list;
        this.asin = str2;
        this.title = str3;
        this.imageUrl = str4;
        this.description = str5;
        this.rating = f;
        this.numReviews = num;
        this.authors = list2;
        this.isInLibrary = bool;
        this.supportedTiers = supportedTiers;
        this.contentTypes = contentTypes;
        this.isExplicit = z;
        this.isFree = bool2;
        this.trackCount = num2;
        this.isFollowed = bool3;
        this.luid = str6;
        this.isAllOwned = z2;
        this.version = str7;
        this.isPartiallyOwned = z3;
        this.playmodeEligibility = playmodeEligibility;
        this.onDemandTiers = set;
        this.isDownloaded = z4;
        this.languageOfPerformances = list3;
    }

    public boolean areEqual(Object obj) {
        return UpdatableContentMetadata.DefaultImpls.areEqual(this, obj);
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

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final String getDescription() {
        return this.description;
    }

    public /* synthetic */ PlaylistMetadata(String str, Uri uri, List list, String str2, String str3, String str4, String str5, Float f, Integer num, List list2, Boolean bool, Set set, List list3, boolean z, Boolean bool2, Integer num2, Boolean bool3, String str6, boolean z2, String str7, boolean z3, PlaymodeEligibility playmodeEligibility, Set set2, boolean z4, List list4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, uri, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? Float.valueOf(0.0f) : f, (i & 256) != 0 ? 0 : num, (i & 512) != 0 ? null : list2, (i & 1024) != 0 ? Boolean.FALSE : bool, set, list3, (i & 8192) != 0 ? false : z, (i & 16384) != 0 ? null : bool2, (32768 & i) != 0 ? null : num2, (65536 & i) != 0 ? null : bool3, (131072 & i) != 0 ? null : str6, (262144 & i) != 0 ? false : z2, (524288 & i) != 0 ? null : str7, (1048576 & i) != 0 ? false : z3, (2097152 & i) != 0 ? null : playmodeEligibility, (4194304 & i) != 0 ? null : set2, (8388608 & i) != 0 ? false : z4, (i & 16777216) != 0 ? null : list4);
    }

    public final Float getRating() {
        return this.rating;
    }

    public final Integer getNumReviews() {
        return this.numReviews;
    }

    public final List<String> getAuthors() {
        return this.authors;
    }

    /* JADX INFO: renamed from: isInLibrary, reason: from getter */
    public final Boolean getIsInLibrary() {
        return this.isInLibrary;
    }

    public final void setInLibrary(Boolean bool) {
        this.isInLibrary = bool;
    }

    public final Set<ContentAccessType> getSupportedTiers() {
        return this.supportedTiers;
    }

    public final List<String> getContentTypes() {
        return this.contentTypes;
    }

    /* JADX INFO: renamed from: isExplicit, reason: from getter */
    public final boolean getIsExplicit() {
        return this.isExplicit;
    }

    /* JADX INFO: renamed from: isFree, reason: from getter */
    public final Boolean getIsFree() {
        return this.isFree;
    }

    public final Integer getTrackCount() {
        return this.trackCount;
    }

    public final void setTrackCount(Integer num) {
        this.trackCount = num;
    }

    /* JADX INFO: renamed from: isFollowed, reason: from getter */
    public final Boolean getIsFollowed() {
        return this.isFollowed;
    }

    public final void setFollowed(Boolean bool) {
        this.isFollowed = bool;
    }

    public final String getLuid() {
        return this.luid;
    }

    public final void setLuid(String str) {
        this.luid = str;
    }

    /* JADX INFO: renamed from: isAllOwned, reason: from getter */
    public final boolean getIsAllOwned() {
        return this.isAllOwned;
    }

    public final String getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: isPartiallyOwned, reason: from getter */
    public final boolean getIsPartiallyOwned() {
        return this.isPartiallyOwned;
    }

    public final PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public final Set<SubscriptionType> getOnDemandTiers() {
        return this.onDemandTiers;
    }

    /* JADX INFO: renamed from: isDownloaded, reason: from getter */
    public final boolean getIsDownloaded() {
        return this.isDownloaded;
    }

    @Override // com.amazon.music.views.library.providers.LanguageOfPerformancesProvider
    public List<String> getLanguageOfPerformances() {
        return this.languageOfPerformances;
    }

    @Override // com.amazon.music.platform.data.entity.EntityItem
    public EntityItemIdentifier getEntityItemIdentifier() {
        if (this.asin != null) {
            return new EntityItemIdentifier(new Asin(this.asin), CollectionType.Playlist.INSTANCE);
        }
        String str = this.luid;
        if (str == null) {
            return null;
        }
        return new EntityItemIdentifier(new ObjectId(str), CollectionType.Playlist.INSTANCE);
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

    private final void refreshContentEligibility() {
        EntityItemIdentifier entityItemIdentifier = getEntityItemIdentifier();
        if (entityItemIdentifier == null) {
            return;
        }
        EligibilityUtil eligibilityUtil = EligibilityUtil.INSTANCE;
        boolean isAllOwned = getIsAllOwned();
        boolean isDownloaded = getIsDownloaded();
        boolean z = getPlaymodeEligibility() != null;
        Set<SubscriptionType> subscriptionType = eligibilityUtil.toSubscriptionType(getSupportedTiers());
        Set<SubscriptionType> onDemandTiers = getOnDemandTiers();
        if (onDemandTiers == null) {
            onDemandTiers = SetsKt__SetsKt.emptySet();
        }
        this.contentEligibility = eligibilityUtil.getEligibility(entityItemIdentifier, isAllOwned, isDownloaded, z, subscriptionType, onDemandTiers, (64 & 64) != 0 ? false : false);
    }

    @Override // com.amazon.music.views.library.metadata.UpdatableContentMetadata
    public void update(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (equals(metadata)) {
            PlaylistMetadata playlistMetadata = metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null;
            if (playlistMetadata == null) {
                return;
            }
            setUri(playlistMetadata.getUri());
            setInLibrary(playlistMetadata.getIsInLibrary());
            setFollowed(playlistMetadata.getIsFollowed());
            setTrackCount(playlistMetadata.getTrackCount());
            setTitle(playlistMetadata.getTitle());
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(PlaylistMetadata.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            PlaylistMetadata playlistMetadata = (PlaylistMetadata) other;
            return areEqual(other) && Intrinsics.areEqual(this.title, playlistMetadata.title) && Intrinsics.areEqual(this.description, playlistMetadata.description) && Intrinsics.areEqual(this.rating, playlistMetadata.rating) && Intrinsics.areEqual(this.numReviews, playlistMetadata.numReviews) && Intrinsics.areEqual(this.authors, playlistMetadata.authors) && Intrinsics.areEqual(this.isInLibrary, playlistMetadata.isInLibrary) && Intrinsics.areEqual(this.supportedTiers, playlistMetadata.supportedTiers) && Intrinsics.areEqual(this.contentTypes, playlistMetadata.contentTypes) && this.isExplicit == playlistMetadata.isExplicit && Intrinsics.areEqual(this.isFree, playlistMetadata.isFree) && Intrinsics.areEqual(this.isFollowed, playlistMetadata.isFollowed) && Intrinsics.areEqual(this.luid, playlistMetadata.luid) && this.isAllOwned == playlistMetadata.isAllOwned && Intrinsics.areEqual(this.version, playlistMetadata.version) && this.isPartiallyOwned == playlistMetadata.isPartiallyOwned && Intrinsics.areEqual(this.playmodeEligibility, playlistMetadata.playmodeEligibility) && Intrinsics.areEqual(this.onDemandTiers, playlistMetadata.onDemandTiers) && Intrinsics.areEqual(this.languageOfPerformances, playlistMetadata.languageOfPerformances);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.PlaylistMetadata");
    }

    public int hashCode() {
        String viewId = getViewId();
        int iHashCode = (((viewId == null ? 0 : viewId.hashCode()) * 31) + getUri().hashCode()) * 31;
        String str = this.version;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }
}
