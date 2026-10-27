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
public class ShowNotificationMethodSerializer extends JsonSerializer<ShowNotificationMethod> {
    public static final ShowNotificationMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ShowNotificationMethodSerializer showNotificationMethodSerializer = new ShowNotificationMethodSerializer();
        INSTANCE = showNotificationMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ShowNotificationMethod.class, showNotificationMethodSerializer);
    }

    private ShowNotificationMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ShowNotificationMethod showNotificationMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (showNotificationMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(showNotificationMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ShowNotificationMethod showNotificationMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("notification");
        NotificationElementSerializer.INSTANCE.serialize(showNotificationMethod.getNotification(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(showNotificationMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(showNotificationMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
