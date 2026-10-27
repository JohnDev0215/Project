package com.amazon.music.find.model.configuration;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.music.find.model.SearchSuggestionFieldType;
import com.amazon.music.platform.find.common.EntityType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InstantSearchSuggestionConfig.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 72\u00020\u0001:\u00017Bu\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000e¢\u0006\u0002\u0010\u0012J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000eHÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\fHÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eHÆ\u0003J{\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000eHÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u00020\u0005HÖ\u0001J\u0006\u00100\u001a\u00020-J\u0006\u00101\u001a\u00020-J\u0006\u00102\u001a\u00020-J\u0006\u00103\u001a\u00020-J\u0006\u00104\u001a\u00020-J\t\u00105\u001a\u000206HÖ\u0001R\u0019\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00068"}, d2 = {"Lcom/amazon/music/find/model/configuration/InstantSearchSuggestionConfig;", "", "suggestionMode", "Lcom/amazon/music/find/model/configuration/InstantSearchSuggestionMode;", "maxElementsToDisplay", "", "minEntityQueryLength", "entityQuerySize", "maxEntitiesCount", "minLocalEntitiesCount", "minLocalSuggestionsCount", "suggestionRecentEntityDateRange", "", "localEntityTypes", "", "Lcom/amazon/music/platform/find/common/EntityType;", "apiLevel", "Lcom/amazon/music/find/model/SearchSuggestionFieldType;", "(Lcom/amazon/music/find/model/configuration/InstantSearchSuggestionMode;IIIIIIJLjava/util/Set;Ljava/util/Set;)V", "getApiLevel", "()Ljava/util/Set;", "getEntityQuerySize", "()I", "getLocalEntityTypes", "getMaxElementsToDisplay", "getMaxEntitiesCount", "getMinEntityQueryLength", "getMinLocalEntitiesCount", "getMinLocalSuggestionsCount", "getSuggestionMode", "()Lcom/amazon/music/find/model/configuration/InstantSearchSuggestionMode;", "getSuggestionRecentEntityDateRange", "()J", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "isAnyEntitySpecEnabled", "isArtistSpecEnabled", "isPodcastShowSpecEnabled", "isTextSpecEnabled", "isTrackSpecEnabled", "toString", "", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class InstantSearchSuggestionConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<EntityType> SUPPORTED_LOCAL_ENTITY_TYPES;
    private static final Set<EntityType> SUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS;
    private final Set<SearchSuggestionFieldType> apiLevel;
    private final int entityQuerySize;
    private final Set<EntityType> localEntityTypes;
    private final int maxElementsToDisplay;
    private final int maxEntitiesCount;
    private final int minEntityQueryLength;
    private final int minLocalEntitiesCount;
    private final int minLocalSuggestionsCount;
    private final InstantSearchSuggestionMode suggestionMode;
    private final long suggestionRecentEntityDateRange;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final InstantSearchSuggestionMode getSuggestionMode() {
        return this.suggestionMode;
    }

    public final Set<SearchSuggestionFieldType> component10() {
        return this.apiLevel;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getMaxElementsToDisplay() {
        return this.maxElementsToDisplay;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getMinEntityQueryLength() {
        return this.minEntityQueryLength;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getEntityQuerySize() {
        return this.entityQuerySize;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getMaxEntitiesCount() {
        return this.maxEntitiesCount;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getMinLocalEntitiesCount() {
        return this.minLocalEntitiesCount;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getMinLocalSuggestionsCount() {
        return this.minLocalSuggestionsCount;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getSuggestionRecentEntityDateRange() {
        return this.suggestionRecentEntityDateRange;
    }

    public final Set<EntityType> component9() {
        return this.localEntityTypes;
    }

    public final InstantSearchSuggestionConfig copy(InstantSearchSuggestionMode suggestionMode, int maxElementsToDisplay, int minEntityQueryLength, int entityQuerySize, int maxEntitiesCount, int minLocalEntitiesCount, int minLocalSuggestionsCount, long suggestionRecentEntityDateRange, Set<? extends EntityType> localEntityTypes, Set<? extends SearchSuggestionFieldType> apiLevel) {
        Intrinsics.checkNotNullParameter(suggestionMode, "suggestionMode");
        Intrinsics.checkNotNullParameter(localEntityTypes, "localEntityTypes");
        return new InstantSearchSuggestionConfig(suggestionMode, maxElementsToDisplay, minEntityQueryLength, entityQuerySize, maxEntitiesCount, minLocalEntitiesCount, minLocalSuggestionsCount, suggestionRecentEntityDateRange, localEntityTypes, apiLevel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InstantSearchSuggestionConfig)) {
            return false;
        }
        InstantSearchSuggestionConfig instantSearchSuggestionConfig = (InstantSearchSuggestionConfig) other;
        return this.suggestionMode == instantSearchSuggestionConfig.suggestionMode && this.maxElementsToDisplay == instantSearchSuggestionConfig.maxElementsToDisplay && this.minEntityQueryLength == instantSearchSuggestionConfig.minEntityQueryLength && this.entityQuerySize == instantSearchSuggestionConfig.entityQuerySize && this.maxEntitiesCount == instantSearchSuggestionConfig.maxEntitiesCount && this.minLocalEntitiesCount == instantSearchSuggestionConfig.minLocalEntitiesCount && this.minLocalSuggestionsCount == instantSearchSuggestionConfig.minLocalSuggestionsCount && this.suggestionRecentEntityDateRange == instantSearchSuggestionConfig.suggestionRecentEntityDateRange && Intrinsics.areEqual(this.localEntityTypes, instantSearchSuggestionConfig.localEntityTypes) && Intrinsics.areEqual(this.apiLevel, instantSearchSuggestionConfig.apiLevel);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((this.suggestionMode.hashCode() * 31) + Integer.hashCode(this.maxElementsToDisplay)) * 31) + Integer.hashCode(this.minEntityQueryLength)) * 31) + Integer.hashCode(this.entityQuerySize)) * 31) + Integer.hashCode(this.maxEntitiesCount)) * 31) + Integer.hashCode(this.minLocalEntitiesCount)) * 31) + Integer.hashCode(this.minLocalSuggestionsCount)) * 31) + Long.hashCode(this.suggestionRecentEntityDateRange)) * 31) + this.localEntityTypes.hashCode()) * 31;
        Set<SearchSuggestionFieldType> set = this.apiLevel;
        return iHashCode + (set == null ? 0 : set.hashCode());
    }

    public String toString() {
        return "InstantSearchSuggestionConfig(suggestionMode=" + this.suggestionMode + ", maxElementsToDisplay=" + this.maxElementsToDisplay + ", minEntityQueryLength=" + this.minEntityQueryLength + ", entityQuerySize=" + this.entityQuerySize + ", maxEntitiesCount=" + this.maxEntitiesCount + ", minLocalEntitiesCount=" + this.minLocalEntitiesCount + ", minLocalSuggestionsCount=" + this.minLocalSuggestionsCount + ", suggestionRecentEntityDateRange=" + this.suggestionRecentEntityDateRange + ", localEntityTypes=" + this.localEntityTypes + ", apiLevel=" + this.apiLevel + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public InstantSearchSuggestionConfig(InstantSearchSuggestionMode suggestionMode, int i, int i2, int i3, int i4, int i5, int i6, long j, Set<? extends EntityType> localEntityTypes, Set<? extends SearchSuggestionFieldType> set) {
        Intrinsics.checkNotNullParameter(suggestionMode, "suggestionMode");
        Intrinsics.checkNotNullParameter(localEntityTypes, "localEntityTypes");
        this.suggestionMode = suggestionMode;
        this.maxElementsToDisplay = i;
        this.minEntityQueryLength = i2;
        this.entityQuerySize = i3;
        this.maxEntitiesCount = i4;
        this.minLocalEntitiesCount = i5;
        this.minLocalSuggestionsCount = i6;
        this.suggestionRecentEntityDateRange = j;
        this.localEntityTypes = localEntityTypes;
        this.apiLevel = set;
    }

    public final InstantSearchSuggestionMode getSuggestionMode() {
        return this.suggestionMode;
    }

    public final int getMaxElementsToDisplay() {
        return this.maxElementsToDisplay;
    }

    public final int getMinEntityQueryLength() {
        return this.minEntityQueryLength;
    }

    public final int getEntityQuerySize() {
        return this.entityQuerySize;
    }

    public final int getMaxEntitiesCount() {
        return this.maxEntitiesCount;
    }

    public final int getMinLocalEntitiesCount() {
        return this.minLocalEntitiesCount;
    }

    public final int getMinLocalSuggestionsCount() {
        return this.minLocalSuggestionsCount;
    }

    public final long getSuggestionRecentEntityDateRange() {
        return this.suggestionRecentEntityDateRange;
    }

    public /* synthetic */ InstantSearchSuggestionConfig(InstantSearchSuggestionMode instantSearchSuggestionMode, int i, int i2, int i3, int i4, int i5, int i6, long j, Set set, Set set2, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(instantSearchSuggestionMode, (i7 & 2) != 0 ? 7 : i, (i7 & 4) != 0 ? 2 : i2, (i7 & 8) != 0 ? ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION : i3, (i7 & 16) != 0 ? 3 : i4, (i7 & 32) != 0 ? 1 : i5, (i7 & 64) != 0 ? 0 : i6, (i7 & 128) != 0 ? 90L : j, (i7 & 256) != 0 ? SUPPORTED_LOCAL_ENTITY_TYPES : set, (i7 & 512) != 0 ? SetsKt__SetsJVMKt.setOf(SearchSuggestionFieldType.TEXT) : set2);
    }

    public final Set<EntityType> getLocalEntityTypes() {
        return this.localEntityTypes;
    }

    public final Set<SearchSuggestionFieldType> getApiLevel() {
        return this.apiLevel;
    }

    /* JADX INFO: compiled from: InstantSearchSuggestionConfig.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/music/find/model/configuration/InstantSearchSuggestionConfig$Companion;", "", "()V", "SUPPORTED_LOCAL_ENTITY_TYPES", "", "Lcom/amazon/music/platform/find/common/EntityType;", "getSUPPORTED_LOCAL_ENTITY_TYPES", "()Ljava/util/Set;", "SUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS", "getSUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<EntityType> getSUPPORTED_LOCAL_ENTITY_TYPES() {
            return InstantSearchSuggestionConfig.SUPPORTED_LOCAL_ENTITY_TYPES;
        }

        public final Set<EntityType> getSUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS() {
            return InstantSearchSuggestionConfig.SUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS;
        }
    }

    static {
        EntityType entityType = EntityType.TRACK;
        Set<EntityType> of = SetsKt__SetsKt.setOf((Object[]) new EntityType[]{entityType, EntityType.ALBUM, EntityType.VIDEO, EntityType.PODCAST_SHOW, EntityType.PODCAST_EPISODE, EntityType.PLAYLIST, EntityType.STATION, EntityType.ARTIST, EntityType.VIDEO_PLAYLIST, EntityType.COMMUNITY_PLAYLIST});
        SUPPORTED_LOCAL_ENTITY_TYPES = of;
        SUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS = SetsKt___SetsKt.minus((Set<? extends EntityType>) ((Set<? extends Object>) of), entityType);
    }

    public final boolean isTextSpecEnabled() {
        Set<SearchSuggestionFieldType> set = this.apiLevel;
        return set != null && set.contains(SearchSuggestionFieldType.TEXT);
    }

    public final boolean isArtistSpecEnabled() {
        Set<SearchSuggestionFieldType> set = this.apiLevel;
        return set != null && set.contains(SearchSuggestionFieldType.ARTIST);
    }

    public final boolean isTrackSpecEnabled() {
        Set<SearchSuggestionFieldType> set = this.apiLevel;
        return set != null && set.contains(SearchSuggestionFieldType.TRACK);
    }

    public final boolean isPodcastShowSpecEnabled() {
        Set<SearchSuggestionFieldType> set = this.apiLevel;
        return set != null && set.contains(SearchSuggestionFieldType.PODCAST_SHOW);
    }

    public final boolean isAnyEntitySpecEnabled() {
        return isTrackSpecEnabled() || isArtistSpecEnabled() || isPodcastShowSpecEnabled();
    }
}
