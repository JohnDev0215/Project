package com.amazon.music.platform.graphql.generated.adapter;

import com.amazon.music.platform.graphql.generated.AlbumDetailQuery;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumDetailQuery_VariablesAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0002H\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/adapter/AlbumDetailQuery_VariablesAdapter;", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/AlbumDetailQuery;", "()V", "fromJson", "reader", "Lcom/apollographql/apollo3/api/json/JsonReader;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "toJson", "", "writer", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "value", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class AlbumDetailQuery_VariablesAdapter implements Adapter<AlbumDetailQuery> {
    public static final AlbumDetailQuery_VariablesAdapter INSTANCE = new AlbumDetailQuery_VariablesAdapter();

    private AlbumDetailQuery_VariablesAdapter() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public AlbumDetailQuery fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        throw new IllegalStateException("Input type used in output position");
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, AlbumDetailQuery value) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name("id");
        Adapters.StringAdapter.toJson(writer, customScalarAdapters, value.getId());
    }
}
