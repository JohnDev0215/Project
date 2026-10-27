package Podcast.Touch.PodcastDetailTemplateInterface.v2_0;

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
public class AccentOutlineBadgeElementSerializer extends JsonSerializer<AccentOutlineBadgeElement> {
    public static final AccentOutlineBadgeElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AccentOutlineBadgeElementSerializer accentOutlineBadgeElementSerializer = new AccentOutlineBadgeElementSerializer();
        INSTANCE = accentOutlineBadgeElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AccentOutlineBadgeElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AccentOutlineBadgeElement.class, accentOutlineBadgeElementSerializer);
    }

    private AccentOutlineBadgeElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AccentOutlineBadgeElement accentOutlineBadgeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (accentOutlineBadgeElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(accentOutlineBadgeElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AccentOutlineBadgeElement accentOutlineBadgeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(accentOutlineBadgeElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(accentOutlineBadgeElement.getText(), jsonGenerator, serializerProvider);
    }
}
