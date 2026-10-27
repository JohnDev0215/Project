package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.MethodsSerializer;
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
public class JumpBackInSeeMoreElementSerializer extends JsonSerializer<JumpBackInSeeMoreElement> {
    public static final JumpBackInSeeMoreElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        JumpBackInSeeMoreElementSerializer jumpBackInSeeMoreElementSerializer = new JumpBackInSeeMoreElementSerializer();
        INSTANCE = jumpBackInSeeMoreElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInSeeMoreElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(JumpBackInSeeMoreElement.class, jumpBackInSeeMoreElementSerializer);
    }

    private JumpBackInSeeMoreElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull JumpBackInSeeMoreElement jumpBackInSeeMoreElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (jumpBackInSeeMoreElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(jumpBackInSeeMoreElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(JumpBackInSeeMoreElement jumpBackInSeeMoreElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(jumpBackInSeeMoreElement.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(jumpBackInSeeMoreElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
