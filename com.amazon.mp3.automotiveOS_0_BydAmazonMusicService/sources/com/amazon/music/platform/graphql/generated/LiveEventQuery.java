package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.LiveEventQuery;
import com.amazon.music.platform.graphql.generated.adapter.LiveEventQuery_VariablesAdapter;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveEventQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001b\u001c\u001dB\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/LiveEventQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/LiveEventQuery$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "Companion", "Data", "LiveEvent", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class LiveEventQuery implements Query<Data> {
    private final String id;

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query LiveEvent($id: String!) { liveEvent(id: $id) { __typename ...liveEvent } }  fragment image on Image { width height aspectRatio url externalUrl imageType description }  fragment artistEmbedded on Artist { id name images { __typename ...image } }  fragment lineup on Lineup { id scheduledStartDate scheduledEndDate actualStartDate actualEndDate status endpoints { type url } artists { artist { __typename ...artistEmbedded } startDate endDate } }  fragment albumEmbedded on Album { id title releaseDate images { __typename ...image } }  fragment eligibility on Eligibility { isDownloadable isPreviewable isPrimeEligible isPurchaseable isPreorderable playbackSubscriptionTiers playback { shuffleType canPlayOnDemand } }  fragment merch on Merch { id name images { __typename ...image } price format assetType relatedAssetTypes eligibility { __typename ...eligibility } }  fragment playlistEmbedded on Playlist { id title images { __typename ...image } }  fragment liveEvent on LiveEvent { id name location venue presentedBy description startDate endDate status lineupGroups { id lineups { __typename ...lineup } } lineupGroupNameType images { __typename ...image } albums { __typename ...albumEmbedded } merch { __typename ...merch } playlists { __typename ...playlistEmbedded } }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LiveEventQuery) && Intrinsics.areEqual(this.id, ((LiveEventQuery) other).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "dd1fd3a7c8107644c7133fde892d9eb6d64742621d73384020113cd2da5dd495";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "LiveEvent";
    }

    public String toString() {
        return "LiveEventQuery(id=" + this.id + ')';
    }

    public LiveEventQuery(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }

    public final String getId() {
        return this.id;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        LiveEventQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.LiveEventQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("liveEvent");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public LiveEventQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                LiveEventQuery.LiveEvent liveEvent = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    liveEvent = (LiveEventQuery.LiveEvent) Adapters.m2609nullable(Adapters.m2610obj(LiveEventQuery_ResponseAdapter$LiveEvent.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                }
                return new LiveEventQuery.Data(liveEvent);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, LiveEventQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("liveEvent");
                Adapters.m2609nullable(Adapters.m2610obj(LiveEventQuery_ResponseAdapter$LiveEvent.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getLiveEvent());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: LiveEventQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/LiveEventQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/LiveEventQuery$LiveEvent;", "liveEvent", "Lcom/amazon/music/platform/graphql/generated/LiveEventQuery$LiveEvent;", "getLiveEvent", "()Lcom/amazon/music/platform/graphql/generated/LiveEventQuery$LiveEvent;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/LiveEventQuery$LiveEvent;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final LiveEvent liveEvent;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.liveEvent, ((Data) other).liveEvent);
        }

        public int hashCode() {
            LiveEvent liveEvent = this.liveEvent;
            if (liveEvent == null) {
                return 0;
            }
            return liveEvent.hashCode();
        }

        public String toString() {
            return "Data(liveEvent=" + this.liveEvent + ')';
        }

        public Data(LiveEvent liveEvent) {
            this.liveEvent = liveEvent;
        }

        public final LiveEvent getLiveEvent() {
            return this.liveEvent;
        }
    }

    /* JADX INFO: compiled from: LiveEventQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/LiveEventQuery$LiveEvent;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "liveEvent", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "getLiveEvent", "()Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class LiveEvent {
        private final String __typename;
        private final com.amazon.music.platform.graphql.generated.fragment.LiveEvent liveEvent;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LiveEvent)) {
                return false;
            }
            LiveEvent liveEvent = (LiveEvent) other;
            return Intrinsics.areEqual(this.__typename, liveEvent.__typename) && Intrinsics.areEqual(this.liveEvent, liveEvent.liveEvent);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.liveEvent.hashCode();
        }

        public String toString() {
            return "LiveEvent(__typename=" + this.__typename + ", liveEvent=" + this.liveEvent + ')';
        }

        public LiveEvent(String __typename, com.amazon.music.platform.graphql.generated.fragment.LiveEvent liveEvent) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(liveEvent, "liveEvent");
            this.__typename = __typename;
            this.liveEvent = liveEvent;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final com.amazon.music.platform.graphql.generated.fragment.LiveEvent getLiveEvent() {
            return this.liveEvent;
        }
    }
}
