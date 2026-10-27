package com.amazon.music.platform.find.utils.database;

import com.amazon.music.platform.find.service.common.EntityProperties;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;

/* JADX INFO: compiled from: JsonSerializationHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/find/utils/database/JsonSerializationHelper;", "", "", "json", "Lcom/amazon/music/platform/find/service/common/EntityProperties;", "deserializeEntityProperties", "data", "serializeEntityProperties", "Lkotlinx/serialization/json/Json;", "jsonFormat", "Lkotlinx/serialization/json/Json;", "getJsonFormat", "()Lkotlinx/serialization/json/Json;", "<init>", "()V", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1})
public final class JsonSerializationHelper {
    public static final JsonSerializationHelper INSTANCE = new JsonSerializationHelper();
    private static final Json jsonFormat = JsonKt.Json$default(null, new Function1<JsonBuilder, Unit>() { // from class: com.amazon.music.platform.find.utils.database.JsonSerializationHelper$jsonFormat$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(JsonBuilder jsonBuilder) {
            invoke2(jsonBuilder);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(JsonBuilder Json) {
            Intrinsics.checkNotNullParameter(Json, "$this$Json");
            Json.setIgnoreUnknownKeys(true);
        }
    }, 1, null);

    private JsonSerializationHelper() {
    }

    public final EntityProperties deserializeEntityProperties(String json) {
        if (json == null) {
            return null;
        }
        try {
            return (EntityProperties) jsonFormat.decodeFromString(EntityProperties.INSTANCE.serializer(), json);
        } catch (Exception unused) {
            return null;
        }
    }

    public final String serializeEntityProperties(EntityProperties data) {
        if (data == null) {
            return null;
        }
        try {
            return jsonFormat.encodeToString(EntityProperties.INSTANCE.serializer(), data);
        } catch (Exception unused) {
            return null;
        }
    }
}
