package Podcast.Touch.WidgetElementsInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class JumpBackInElementSerializer extends JsonSerializer<JumpBackInElement> {
    public static final JumpBackInElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        JumpBackInElementSerializer jumpBackInElementSerializer = new JumpBackInElementSerializer();
        INSTANCE = jumpBackInElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.WidgetElementsInterface.v1_0.JumpBackInElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(JumpBackInElement.class, jumpBackInElementSerializer);
    }

    private JumpBackInElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull JumpBackInElement jumpBackInElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (jumpBackInElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(jumpBackInElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(JumpBackInElement jumpBackInElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(jumpBackInElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(jumpBackInElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(jumpBackInElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onSeeMoreSelected");
        jsonSerializer.serialize(jumpBackInElement.getOnSeeMoreSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(jumpBackInElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seeMoreText");
        SimpleSerializers.serializeString(jumpBackInElement.getSeeMoreText(), jsonGenerator, serializerProvider);
    }
}
