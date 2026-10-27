package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.platform.graphql.generated.fragment.Eligibility;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.NullableAdapter;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EligibilityImpl_ResponseAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"com/amazon/music/platform/graphql/generated/fragment/EligibilityImpl_ResponseAdapter$Eligibility", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "reader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "fromJson", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "value", "", "toJson", "", "", "RESPONSE_NAMES", "Ljava/util/List;", "getRESPONSE_NAMES", "()Ljava/util/List;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class EligibilityImpl_ResponseAdapter$Eligibility implements Adapter<Eligibility> {
    public static final EligibilityImpl_ResponseAdapter$Eligibility INSTANCE = new EligibilityImpl_ResponseAdapter$Eligibility();
    private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"isDownloadable", "isPreviewable", "isPrimeEligible", "isPurchaseable", "isPreorderable", "playbackSubscriptionTiers", "playback"});

    private EligibilityImpl_ResponseAdapter$Eligibility() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public Eligibility fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Boolean boolFromJson = null;
        Boolean boolFromJson2 = null;
        Boolean boolFromJson3 = null;
        Boolean boolFromJson4 = null;
        Boolean boolFromJson5 = null;
        List list = null;
        Eligibility.Playback playback = null;
        while (true) {
            switch (reader.selectName(RESPONSE_NAMES)) {
                case 0:
                    boolFromJson = Adapters.NullableBooleanAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 1:
                    boolFromJson2 = Adapters.NullableBooleanAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 2:
                    boolFromJson3 = Adapters.NullableBooleanAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 3:
                    boolFromJson4 = Adapters.NullableBooleanAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 4:
                    boolFromJson5 = Adapters.NullableBooleanAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 5:
                    list = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.NullableStringAdapter)).fromJson(reader, customScalarAdapters);
                    break;
                case 6:
                    playback = (Eligibility.Playback) Adapters.m2609nullable(Adapters.m2611obj$default(EligibilityImpl_ResponseAdapter$Playback.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                    break;
                default:
                    return new Eligibility(boolFromJson, boolFromJson2, boolFromJson3, boolFromJson4, boolFromJson5, list, playback);
            }
        }
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, Eligibility value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name("isDownloadable");
        NullableAdapter<Boolean> nullableAdapter = Adapters.NullableBooleanAdapter;
        nullableAdapter.toJson(writer, customScalarAdapters, value.getIsDownloadable());
        writer.name("isPreviewable");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getIsPreviewable());
        writer.name("isPrimeEligible");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getIsPrimeEligible());
        writer.name("isPurchaseable");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getIsPurchaseable());
        writer.name("isPreorderable");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getIsPreorderable());
        writer.name("playbackSubscriptionTiers");
        Adapters.m2609nullable(Adapters.m2608list(Adapters.NullableStringAdapter)).toJson(writer, customScalarAdapters, value.getPlaybackSubscriptionTiers());
        writer.name("playback");
        Adapters.m2609nullable(Adapters.m2611obj$default(EligibilityImpl_ResponseAdapter$Playback.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getPlayback());
    }
}
