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
public class BorderedBadgeElementSerializer extends JsonSerializer<BorderedBadgeElement> {
    public static final BorderedBadgeElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BorderedBadgeElementSerializer borderedBadgeElementSerializer = new BorderedBadgeElementSerializer();
        INSTANCE = borderedBadgeElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CompactDetailTemplateInterface.v1_0.BorderedBadgeElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BorderedBadgeElement.class, borderedBadgeElementSerializer);
    }

    private BorderedBadgeElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BorderedBadgeElement borderedBadgeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (borderedBadgeElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(borderedBadgeElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BorderedBadgeElement borderedBadgeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(borderedBadgeElement.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(borderedBadgeElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
