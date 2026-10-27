package com.amazon.music.find.data.database;

import androidx.room.TypeConverter;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.find.service.common.EntityProperties;
import com.amazon.music.platform.find.utils.database.JsonSerializationHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchTypeConverters.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0007J\u0014\u0010\r\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\bH\u0007¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/find/data/database/SearchTypeConverters;", "", "()V", "toEntityIdType", "Lcom/amazon/music/platform/find/common/EntityIdType;", ContextMappingConstants.ENTITY_ID_TYPE, "", "toEntityProperties", "Lcom/amazon/music/platform/find/service/common/EntityProperties;", "entityPropertyAsString", "toEntityType", "Lcom/amazon/music/platform/find/common/EntityType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "toString", "entityProperties", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchTypeConverters {
    @TypeConverter
    public final String toString(EntityIdType entityIdType) {
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        return entityIdType.name();
    }

    @TypeConverter
    public final String toString(EntityType entityType) {
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        return entityType.name();
    }

    @TypeConverter
    public final String toString(EntityProperties entityProperties) {
        return JsonSerializationHelper.INSTANCE.serializeEntityProperties(entityProperties);
    }

    @TypeConverter
    public final EntityIdType toEntityIdType(String entityIdType) {
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        return EntityIdType.valueOf(entityIdType);
    }

    @TypeConverter
    public final EntityType toEntityType(String entityType) {
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        return EntityType.valueOf(entityType);
    }

    @TypeConverter
    public final EntityProperties toEntityProperties(String entityPropertyAsString) {
        return JsonSerializationHelper.INSTANCE.deserializeEntityProperties(entityPropertyAsString);
    }
}
