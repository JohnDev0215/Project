package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElementSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class VisualListingElementSerializer extends JsonSerializer<VisualListingElement> {
    public static final VisualListingElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        VisualListingElementSerializer visualListingElementSerializer = new VisualListingElementSerializer();
        INSTANCE = visualListingElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.VisualListingElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(VisualListingElement.class, visualListingElementSerializer);
    }

    private VisualListingElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull VisualListingElement visualListingElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (visualListingElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(visualListingElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(VisualListingElement visualListingElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onEndOfWidget");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(visualListingElement.getOnEndOfWidget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        VerticalItemsSerializer.INSTANCE.serialize(visualListingElement.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImage");
        SimpleSerializers.serializeString(visualListingElement.getBackgroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(visualListingElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metadata");
        TemplateMetadataElementSerializer.INSTANCE.serialize(visualListingElement.getMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(visualListingElement.getOnViewed(), jsonGenerator, serializerProvider);
    }
}
