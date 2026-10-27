package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.MethodsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class FeatureBarkerElementSerializer extends JsonSerializer<FeatureBarkerElement> {
    public static final FeatureBarkerElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeatureBarkerElementSerializer featureBarkerElementSerializer = new FeatureBarkerElementSerializer();
        INSTANCE = featureBarkerElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.FeatureBarkerElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FeatureBarkerElement.class, featureBarkerElementSerializer);
    }

    private FeatureBarkerElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FeatureBarkerElement featureBarkerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (featureBarkerElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(featureBarkerElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FeatureBarkerElement featureBarkerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("barker");
        BarkerItemElementSerializer.INSTANCE.serialize(featureBarkerElement.getBarker(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        MethodsSerializer.INSTANCE.serialize(featureBarkerElement.getOnViewed(), jsonGenerator, serializerProvider);
    }
}
