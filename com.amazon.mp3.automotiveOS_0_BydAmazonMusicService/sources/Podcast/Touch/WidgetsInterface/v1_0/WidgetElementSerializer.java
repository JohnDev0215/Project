package Podcast.Touch.WidgetsInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class WidgetElementSerializer extends JsonSerializer<WidgetElement> {
    public static final WidgetElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WidgetElementSerializer widgetElementSerializer = new WidgetElementSerializer();
        INSTANCE = widgetElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.WidgetsInterface.v1_0.WidgetElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(WidgetElement.class, widgetElementSerializer);
    }

    private WidgetElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull WidgetElement widgetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (widgetElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (widgetElement instanceof DescriptiveShowcaseElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetsInterface.v1_0#DescriptiveShowcaseElement");
            DescriptiveShowcaseElementSerializer.INSTANCE.serializeFields((DescriptiveShowcaseElement) widgetElement, jsonGenerator, serializerProvider);
        } else {
            boolean z = widgetElement instanceof VisualShovelerCompactElement;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(widgetElement, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("Podcast.Touch.WidgetsInterface.v1_0#VisualShovelerCompactElement");
                VisualShovelerCompactElementSerializer.INSTANCE.serializeFields((VisualShovelerCompactElement) widgetElement, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(WidgetElement widgetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
