package Podcast.Touch.RibbonNotificationElementInterface.v1_0;

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
public class TextWithLinkElementSerializer extends JsonSerializer<TextWithLinkElement> {
    public static final TextWithLinkElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TextWithLinkElementSerializer textWithLinkElementSerializer = new TextWithLinkElementSerializer();
        INSTANCE = textWithLinkElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.RibbonNotificationElementInterface.v1_0.TextWithLinkElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(TextWithLinkElement.class, textWithLinkElementSerializer);
    }

    private TextWithLinkElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull TextWithLinkElement textWithLinkElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (textWithLinkElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(textWithLinkElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(TextWithLinkElement textWithLinkElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(textWithLinkElement.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("link");
        SimpleSerializers.serializeString(textWithLinkElement.getLink(), jsonGenerator, serializerProvider);
    }
}
