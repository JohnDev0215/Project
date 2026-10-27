package com.amazon.music.platform.graphql.generated.fragment;

import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.NullableAdapter;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageImpl_ResponseAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"com/amazon/music/platform/graphql/generated/fragment/ImageImpl_ResponseAdapter$Image", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "reader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "fromJson", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "value", "", "toJson", "", "", "RESPONSE_NAMES", "Ljava/util/List;", "getRESPONSE_NAMES", "()Ljava/util/List;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class ImageImpl_ResponseAdapter$Image implements Adapter<Image> {
    public static final ImageImpl_ResponseAdapter$Image INSTANCE = new ImageImpl_ResponseAdapter$Image();
    private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"width", "height", "aspectRatio", ImagesContract.URL, "externalUrl", "imageType", MediaTrack.ROLE_DESCRIPTION});

    private ImageImpl_ResponseAdapter$Image() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.apollographql.apollo3.api.Adapter
    public Image fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Double dFromJson = null;
        Double dFromJson2 = null;
        Double dFromJson3 = null;
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        while (true) {
            switch (reader.selectName(RESPONSE_NAMES)) {
                case 0:
                    dFromJson = Adapters.NullableDoubleAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 1:
                    dFromJson2 = Adapters.NullableDoubleAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 2:
                    dFromJson3 = Adapters.NullableDoubleAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 3:
                    strFromJson = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 4:
                    strFromJson2 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 5:
                    strFromJson3 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                case 6:
                    strFromJson4 = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters);
                    break;
                default:
                    return new Image(dFromJson, dFromJson2, dFromJson3, strFromJson, strFromJson2, strFromJson3, strFromJson4);
            }
        }
    }

    @Override // com.apollographql.apollo3.api.Adapter
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, Image value) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(value, "value");
        writer.name("width");
        NullableAdapter<Double> nullableAdapter = Adapters.NullableDoubleAdapter;
        nullableAdapter.toJson(writer, customScalarAdapters, value.getWidth());
        writer.name("height");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getHeight());
        writer.name("aspectRatio");
        nullableAdapter.toJson(writer, customScalarAdapters, value.getAspectRatio());
        writer.name(ImagesContract.URL);
        NullableAdapter<String> nullableAdapter2 = Adapters.NullableStringAdapter;
        nullableAdapter2.toJson(writer, customScalarAdapters, value.getUrl());
        writer.name("externalUrl");
        nullableAdapter2.toJson(writer, customScalarAdapters, value.getExternalUrl());
        writer.name("imageType");
        nullableAdapter2.toJson(writer, customScalarAdapters, value.getImageType());
        writer.name(MediaTrack.ROLE_DESCRIPTION);
        nullableAdapter2.toJson(writer, customScalarAdapters, value.getDescription());
    }
}
