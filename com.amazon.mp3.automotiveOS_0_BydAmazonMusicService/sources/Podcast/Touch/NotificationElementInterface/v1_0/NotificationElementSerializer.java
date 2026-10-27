package Podcast.Touch.NotificationElementInterface.v1_0;

import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElement;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElementSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class NotificationElementSerializer extends JsonSerializer<NotificationElement> {
    public static final NotificationElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        NotificationElementSerializer notificationElementSerializer = new NotificationElementSerializer();
        INSTANCE = notificationElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.NotificationElementInterface.v1_0.NotificationElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(NotificationElement.class, notificationElementSerializer);
    }

    private NotificationElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull NotificationElement notificationElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (notificationElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        boolean z = notificationElement instanceof RibbonNotificationElement;
        jsonGenerator.writeStartObject();
        if (!z) {
            serializeFields(notificationElement, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonNotificationElement");
            RibbonNotificationElementSerializer.INSTANCE.serializeFields((RibbonNotificationElement) notificationElement, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndObject();
        }
    }

    public void serializeFields(NotificationElement notificationElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
