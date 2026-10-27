package Podcast.Touch.RibbonNotificationElementInterface.v1_0;

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
public class RibbonNotificationElementSerializer extends JsonSerializer<RibbonNotificationElement> {
    public static final RibbonNotificationElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RibbonNotificationElementSerializer ribbonNotificationElementSerializer = new RibbonNotificationElementSerializer();
        INSTANCE = ribbonNotificationElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RibbonNotificationElement.class, ribbonNotificationElementSerializer);
    }

    private RibbonNotificationElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RibbonNotificationElement ribbonNotificationElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (ribbonNotificationElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(ribbonNotificationElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RibbonNotificationElement ribbonNotificationElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(ribbonNotificationElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("icon");
        SimpleSerializers.serializeString(ribbonNotificationElement.getIcon(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("message");
        RibbonMessageElementSerializer.INSTANCE.serialize(ribbonNotificationElement.getMessage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("rightButton");
        RibbonPrimaryButtonElementSerializer.INSTANCE.serialize(ribbonNotificationElement.getRightButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("closeButton");
        RibbonCloseButtonElementSerializer.INSTANCE.serialize(ribbonNotificationElement.getCloseButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("leftButton");
        RibbonSecondaryButtonElementSerializer.INSTANCE.serialize(ribbonNotificationElement.getLeftButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("timeoutSeconds");
        SimpleSerializers.serializeInteger(ribbonNotificationElement.getTimeoutSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(ribbonNotificationElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        jsonSerializer.serialize(ribbonNotificationElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
