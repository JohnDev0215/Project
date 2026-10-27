package Podcast.Touch.NotificationElementInterface.v1_0;

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
public class CloseNotificationMethodSerializer extends JsonSerializer<CloseNotificationMethod> {
    public static final CloseNotificationMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CloseNotificationMethodSerializer closeNotificationMethodSerializer = new CloseNotificationMethodSerializer();
        INSTANCE = closeNotificationMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CloseNotificationMethod.class, closeNotificationMethodSerializer);
    }

    private CloseNotificationMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CloseNotificationMethod closeNotificationMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (closeNotificationMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(closeNotificationMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CloseNotificationMethod closeNotificationMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(closeNotificationMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(closeNotificationMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(closeNotificationMethod.getId(), jsonGenerator, serializerProvider);
    }
}
