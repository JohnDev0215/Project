package Podcast.Touch.CompactDetailTemplateInterface.v1_0;

import Podcast.Touch.WidgetsInterface.v1_0.VisualShovelerCompactElementSerializer;
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
public class ShowVisualShovelerMethodSerializer extends JsonSerializer<ShowVisualShovelerMethod> {
    public static final ShowVisualShovelerMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ShowVisualShovelerMethodSerializer showVisualShovelerMethodSerializer = new ShowVisualShovelerMethodSerializer();
        INSTANCE = showVisualShovelerMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShowVisualShovelerMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ShowVisualShovelerMethod.class, showVisualShovelerMethodSerializer);
    }

    private ShowVisualShovelerMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ShowVisualShovelerMethod showVisualShovelerMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (showVisualShovelerMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(showVisualShovelerMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ShowVisualShovelerMethod showVisualShovelerMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("visualShoveler");
        VisualShovelerCompactElementSerializer.INSTANCE.serialize(showVisualShovelerMethod.getVisualShoveler(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(showVisualShovelerMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(showVisualShovelerMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
