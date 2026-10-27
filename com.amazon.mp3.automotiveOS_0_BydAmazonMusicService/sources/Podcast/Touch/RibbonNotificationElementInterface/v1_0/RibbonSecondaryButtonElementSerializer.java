package Podcast.Touch.RibbonNotificationElementInterface.v1_0;

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
public class RibbonSecondaryButtonElementSerializer extends JsonSerializer<RibbonSecondaryButtonElement> {
    public static final RibbonSecondaryButtonElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RibbonSecondaryButtonElementSerializer ribbonSecondaryButtonElementSerializer = new RibbonSecondaryButtonElementSerializer();
        INSTANCE = ribbonSecondaryButtonElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonSecondaryButtonElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RibbonSecondaryButtonElement.class, ribbonSecondaryButtonElementSerializer);
    }

    private RibbonSecondaryButtonElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RibbonSecondaryButtonElement ribbonSecondaryButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (ribbonSecondaryButtonElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(ribbonSecondaryButtonElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RibbonSecondaryButtonElement ribbonSecondaryButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(ribbonSecondaryButtonElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("icon");
        SimpleSerializers.serializeString(ribbonSecondaryButtonElement.getIcon(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(ribbonSecondaryButtonElement.getText(), jsonGenerator, serializerProvider);
    }
}
