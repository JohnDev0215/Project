package com.amazon.music.platform.graphql.generated.adapter;

import com.amazon.music.platform.graphql.generated.UpdateSocialProfileMutation;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Optional;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdateSocialProfileMutation_VariablesAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0002H\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/adapter/UpdateSocialProfileMutation_VariablesAdapter;", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/UpdateSocialProfileMutation;", "()V", "fromJson", "reader", "Lcom/apollographql/apollo3/api/json/JsonReader;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "toJson", "", "writer", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "value", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class UpdateSocialProfileMutation_VariablesAdapter implements Adapter<UpdateSocialProfileMutation> {
    public static final UpdateSocialProfileMutation_VariablesAdapter INSTANCE = new UpdateSocialProfileMutation_VariablesAdapter();

    private UpdateSocialProfileMutation_VariablesAdapter() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public UpdateSocialProfileMutation fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        throw new IllegalStateException("Input type used in output position");
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, UpdateSocialProfileMutation value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        if (value.getName() instanceof Optional.Present) {
            writer.name("name");
            Adapters.m2612optional(Adapters.NullableStringAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getName());
        }
        if (value.getVisibility() instanceof Optional.Present) {
            writer.name("visibility");
            Adapters.m2612optional(Adapters.NullableStringAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getVisibility());
        }
        if (value.getPlaybackVisibility() instanceof Optional.Present) {
            writer.name("playbackVisibility");
            Adapters.m2612optional(Adapters.NullableStringAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getPlaybackVisibility());
        }
        if (value.getImageUrl() instanceof Optional.Present) {
            writer.name("imageUrl");
            Adapters.m2612optional(Adapters.NullableStringAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getImageUrl());
        }
        if (value.getHandle() instanceof Optional.Present) {
            writer.name("handle");
            Adapters.m2612optional(Adapters.NullableStringAdapter).toJson(writer, customScalarAdapters, (Optional.Present) value.getHandle());
        }
    }
}
