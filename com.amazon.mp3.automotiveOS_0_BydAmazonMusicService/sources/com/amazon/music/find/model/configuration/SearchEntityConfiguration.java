package com.amazon.music.find.model.configuration;

import com.amazon.music.platform.find.common.EntityType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchEntityConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J4\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/find/model/configuration/SearchEntityConfiguration;", "", "count", "", "ttlDays", "localEntityTypes", "", "Lcom/amazon/music/platform/find/common/EntityType;", "(ILjava/lang/Integer;Ljava/util/Set;)V", "getCount", "()I", "getLocalEntityTypes", "()Ljava/util/Set;", "getTtlDays", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(ILjava/lang/Integer;Ljava/util/Set;)Lcom/amazon/music/find/model/configuration/SearchEntityConfiguration;", "equals", "", "other", "hashCode", "toString", "", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchEntityConfiguration {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<EntityType> SUPPORTED_LOCAL_ENTITY_TYPES;
    private static final Set<EntityType> SUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS;
    private final int count;
    private final Set<EntityType> localEntityTypes;
    private final Integer ttlDays;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SearchEntityConfiguration copy$default(SearchEntityConfiguration searchEntityConfiguration, int i, Integer num, Set set, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = searchEntityConfiguration.count;
        }
        if ((i2 & 2) != 0) {
            num = searchEntityConfiguration.ttlDays;
        }
        if ((i2 & 4) != 0) {
            set = searchEntityConfiguration.localEntityTypes;
        }
        return searchEntityConfiguration.copy(i, num, set);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCount() {
        return this.count;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getTtlDays() {
        return this.ttlDays;
    }

    public final Set<EntityType> component3() {
        return this.localEntityTypes;
    }

    public final SearchEntityConfiguration copy(int count, Integer ttlDays, Set<? extends EntityType> localEntityTypes) {
        Intrinsics.checkNotNullParameter(localEntityTypes, "localEntityTypes");
        return new SearchEntityConfiguration(count, ttlDays, localEntityTypes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchEntityConfiguration)) {
            return false;
        }
        SearchEntityConfiguration searchEntityConfiguration = (SearchEntityConfiguration) other;
        return this.count == searchEntityConfiguration.count && Intrinsics.areEqual(this.ttlDays, searchEntityConfiguration.ttlDays) && Intrinsics.areEqual(this.localEntityTypes, searchEntityConfiguration.localEntityTypes);
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.count) * 31;
        Integer num = this.ttlDays;
        return ((iHashCode + (num == null ? 0 : num.hashCode())) * 31) + this.localEntityTypes.hashCode();
    }

    public String toString() {
        return "SearchEntityConfiguration(count=" + this.count + ", ttlDays=" + this.ttlDays + ", localEntityTypes=" + this.localEntityTypes + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SearchEntityConfiguration(int i, Integer num, Set<? extends EntityType> localEntityTypes) {
        Intrinsics.checkNotNullParameter(localEntityTypes, "localEntityTypes");
        this.count = i;
        this.ttlDays = num;
        this.localEntityTypes = localEntityTypes;
    }

    public final int getCount() {
        return this.count;
    }

    public final Integer getTtlDays() {
        return this.ttlDays;
    }

    public /* synthetic */ SearchEntityConfiguration(int i, Integer num, Set set, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, num, (i2 & 4) != 0 ? SUPPORTED_LOCAL_ENTITY_TYPES : set);
    }

    public final Set<EntityType> getLocalEntityTypes() {
        return this.localEntityTypes;
    }

    /* JADX INFO: compiled from: SearchEntityConfiguration.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/music/find/model/configuration/SearchEntityConfiguration$Companion;", "", "()V", "SUPPORTED_LOCAL_ENTITY_TYPES", "", "Lcom/amazon/music/platform/find/common/EntityType;", "getSUPPORTED_LOCAL_ENTITY_TYPES", "()Ljava/util/Set;", "SUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS", "getSUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<EntityType> getSUPPORTED_LOCAL_ENTITY_TYPES() {
            return SearchEntityConfiguration.SUPPORTED_LOCAL_ENTITY_TYPES;
        }

        public final Set<EntityType> getSUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS() {
            return SearchEntityConfiguration.SUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS;
        }
    }

    static {
        EntityType entityType = EntityType.TRACK;
        Set<EntityType> of = SetsKt__SetsKt.setOf((Object[]) new EntityType[]{entityType, EntityType.ALBUM, EntityType.VIDEO, EntityType.PODCAST_SHOW, EntityType.PODCAST_EPISODE, EntityType.PLAYLIST, EntityType.STATION, EntityType.ARTIST, EntityType.VIDEO_PLAYLIST, EntityType.COMMUNITY_PLAYLIST});
        SUPPORTED_LOCAL_ENTITY_TYPES = of;
        SUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS = SetsKt___SetsKt.minus((Set<? extends EntityType>) ((Set<? extends Object>) of), entityType);
    }
}
