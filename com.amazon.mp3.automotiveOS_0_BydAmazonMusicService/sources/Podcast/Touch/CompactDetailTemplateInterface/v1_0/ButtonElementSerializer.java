package Podcast.Touch.CompactDetailTemplateInterface.v1_0;

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
public class ButtonElementSerializer extends JsonSerializer<ButtonElement> {
    public static final ButtonElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ButtonElementSerializer buttonElementSerializer = new ButtonElementSerializer();
        INSTANCE = buttonElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CompactDetailTemplateInterface.v1_0.ButtonElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ButtonElement.class, buttonElementSerializer);
    }

    private ButtonElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ButtonElement buttonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (buttonElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(buttonElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ButtonElement buttonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(buttonElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(buttonElement.getText(), jsonGenerator, serializerProvider);
    }
}
