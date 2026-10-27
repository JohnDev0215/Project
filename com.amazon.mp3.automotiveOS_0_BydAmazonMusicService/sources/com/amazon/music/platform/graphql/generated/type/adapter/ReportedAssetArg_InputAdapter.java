package com.amazon.music.platform.graphql.generated.type.adapter;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.graphql.generated.type.ReportedAssetArg;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Optional;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReportedAssetArg_InputAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0002H\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/type/adapter/ReportedAssetArg_InputAdapter;", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/type/ReportedAssetArg;", "()V", "fromJson", "reader", "Lcom/apollographql/apollo3/api/json/JsonReader;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "toJson", "", "writer", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "value", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ReportedAssetArg_InputAdapter implements Adapter<ReportedAssetArg> {
    public static final ReportedAssetArg_InputAdapter INSTANCE = new ReportedAssetArg_InputAdapter();

    private ReportedAssetArg_InputAdapter() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public ReportedAssetArg fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        throw new IllegalStateException("Input type used in output position");
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, ReportedAssetArg value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        if (value.getId() instanceof Optional.Present) {
            writer.name("id");
            Adapters.m2612optional(Adapters.NullableStringAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getId());
        }
        if (value.getType() instanceof Optional.Present) {
            writer.name(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            Adapters.m2612optional(Adapters.NullableStringAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getType());
        }
        if (value.getTitle() instanceof Optional.Present) {
            writer.name("title");
            Adapters.m2612optional(Adapters.NullableStringAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getTitle());
        }
        if (value.getImage() instanceof Optional.Present) {
            writer.name(ContextMappingConstants.IMAGE);
            Adapters.m2612optional(Adapters.NullableStringAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getImage());
        }
        if (value.getCreatorId() instanceof Optional.Present) {
            writer.name("creatorId");
            Adapters.m2612optional(Adapters.NullableStringAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getCreatorId());
        }
        if (value.getPublisherName() instanceof Optional.Present) {
            writer.name("publisherName");
            Adapters.m2612optional(Adapters.NullableStringAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getPublisherName());
        }
        if (value.getDurationInAsset() instanceof Optional.Present) {
            writer.name("durationInAsset");
            Adapters.m2612optional(Adapters.NullableDoubleAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getDurationInAsset());
        }
    }
}
