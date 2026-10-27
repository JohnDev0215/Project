package Podcast.CategoryFollowInterface.v1_0;

import SOACoreInterface.v1_0.QueueSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class SetCategoryFollowsLastSyncTimeMethodSerializer extends JsonSerializer<SetCategoryFollowsLastSyncTimeMethod> {
    public static final SetCategoryFollowsLastSyncTimeMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetCategoryFollowsLastSyncTimeMethodSerializer setCategoryFollowsLastSyncTimeMethodSerializer = new SetCategoryFollowsLastSyncTimeMethodSerializer();
        INSTANCE = setCategoryFollowsLastSyncTimeMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.CategoryFollowInterface.v1_0.SetCategoryFollowsLastSyncTimeMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetCategoryFollowsLastSyncTimeMethod.class, setCategoryFollowsLastSyncTimeMethodSerializer);
    }

    private SetCategoryFollowsLastSyncTimeMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetCategoryFollowsLastSyncTimeMethod setCategoryFollowsLastSyncTimeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setCategoryFollowsLastSyncTimeMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setCategoryFollowsLastSyncTimeMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetCategoryFollowsLastSyncTimeMethod setCategoryFollowsLastSyncTimeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lastSyncTime");
        SimpleSerializers.serializeLong(setCategoryFollowsLastSyncTimeMethod.getLastSyncTime(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setCategoryFollowsLastSyncTimeMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setCategoryFollowsLastSyncTimeMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
