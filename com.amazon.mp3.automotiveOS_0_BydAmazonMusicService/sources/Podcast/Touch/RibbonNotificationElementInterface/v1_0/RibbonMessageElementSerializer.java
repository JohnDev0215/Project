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
public class RibbonMessageElementSerializer extends JsonSerializer<RibbonMessageElement> {
    public static final RibbonMessageElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RibbonMessageElementSerializer ribbonMessageElementSerializer = new RibbonMessageElementSerializer();
        INSTANCE = ribbonMessageElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonMessageElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RibbonMessageElement.class, ribbonMessageElementSerializer);
    }

    private RibbonMessageElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RibbonMessageElement ribbonMessageElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (ribbonMessageElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(ribbonMessageElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RibbonMessageElement ribbonMessageElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("textWithLinks");
        TextWithLinksSerializer.INSTANCE.serialize(ribbonMessageElement.getTextWithLinks(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(ribbonMessageElement.getText(), jsonGenerator, serializerProvider);
    }
}
