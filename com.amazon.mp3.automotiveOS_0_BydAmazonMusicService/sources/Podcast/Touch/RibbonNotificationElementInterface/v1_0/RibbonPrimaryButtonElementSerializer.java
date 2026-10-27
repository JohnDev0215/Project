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
public class RibbonPrimaryButtonElementSerializer extends JsonSerializer<RibbonPrimaryButtonElement> {
    public static final RibbonPrimaryButtonElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RibbonPrimaryButtonElementSerializer ribbonPrimaryButtonElementSerializer = new RibbonPrimaryButtonElementSerializer();
        INSTANCE = ribbonPrimaryButtonElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonPrimaryButtonElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RibbonPrimaryButtonElement.class, ribbonPrimaryButtonElementSerializer);
    }

    private RibbonPrimaryButtonElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RibbonPrimaryButtonElement ribbonPrimaryButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (ribbonPrimaryButtonElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(ribbonPrimaryButtonElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RibbonPrimaryButtonElement ribbonPrimaryButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(ribbonPrimaryButtonElement.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(ribbonPrimaryButtonElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("icon");
        SimpleSerializers.serializeString(ribbonPrimaryButtonElement.getIcon(), jsonGenerator, serializerProvider);
    }
}
