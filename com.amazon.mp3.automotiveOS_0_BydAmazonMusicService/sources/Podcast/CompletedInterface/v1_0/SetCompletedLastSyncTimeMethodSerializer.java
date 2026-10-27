package Podcast.CompletedInterface.v1_0;

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
public class SetCompletedLastSyncTimeMethodSerializer extends JsonSerializer<SetCompletedLastSyncTimeMethod> {
    public static final SetCompletedLastSyncTimeMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetCompletedLastSyncTimeMethodSerializer setCompletedLastSyncTimeMethodSerializer = new SetCompletedLastSyncTimeMethodSerializer();
        INSTANCE = setCompletedLastSyncTimeMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.CompletedInterface.v1_0.SetCompletedLastSyncTimeMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetCompletedLastSyncTimeMethod.class, setCompletedLastSyncTimeMethodSerializer);
    }

    private SetCompletedLastSyncTimeMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetCompletedLastSyncTimeMethod setCompletedLastSyncTimeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setCompletedLastSyncTimeMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setCompletedLastSyncTimeMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetCompletedLastSyncTimeMethod setCompletedLastSyncTimeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lastSyncTime");
        SimpleSerializers.serializeLong(setCompletedLastSyncTimeMethod.getLastSyncTime(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setCompletedLastSyncTimeMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setCompletedLastSyncTimeMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
