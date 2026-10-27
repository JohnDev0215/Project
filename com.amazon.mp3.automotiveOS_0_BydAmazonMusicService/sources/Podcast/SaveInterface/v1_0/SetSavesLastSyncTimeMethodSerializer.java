package Podcast.SaveInterface.v1_0;

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
public class SetSavesLastSyncTimeMethodSerializer extends JsonSerializer<SetSavesLastSyncTimeMethod> {
    public static final SetSavesLastSyncTimeMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetSavesLastSyncTimeMethodSerializer setSavesLastSyncTimeMethodSerializer = new SetSavesLastSyncTimeMethodSerializer();
        INSTANCE = setSavesLastSyncTimeMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.SaveInterface.v1_0.SetSavesLastSyncTimeMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetSavesLastSyncTimeMethod.class, setSavesLastSyncTimeMethodSerializer);
    }

    private SetSavesLastSyncTimeMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetSavesLastSyncTimeMethod setSavesLastSyncTimeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setSavesLastSyncTimeMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setSavesLastSyncTimeMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetSavesLastSyncTimeMethod setSavesLastSyncTimeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lastSyncTime");
        SimpleSerializers.serializeLong(setSavesLastSyncTimeMethod.getLastSyncTime(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setSavesLastSyncTimeMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setSavesLastSyncTimeMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
