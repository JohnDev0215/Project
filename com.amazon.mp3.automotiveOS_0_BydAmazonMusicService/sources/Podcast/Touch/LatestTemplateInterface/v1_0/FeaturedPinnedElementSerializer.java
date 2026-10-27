package Podcast.Touch.LatestTemplateInterface.v1_0;

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
public class FeaturedPinnedElementSerializer extends JsonSerializer<FeaturedPinnedElement> {
    public static final FeaturedPinnedElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedPinnedElementSerializer featuredPinnedElementSerializer = new FeaturedPinnedElementSerializer();
        INSTANCE = featuredPinnedElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.LatestTemplateInterface.v1_0.FeaturedPinnedElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FeaturedPinnedElement.class, featuredPinnedElementSerializer);
    }

    private FeaturedPinnedElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FeaturedPinnedElement featuredPinnedElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (featuredPinnedElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(featuredPinnedElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FeaturedPinnedElement featuredPinnedElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onViewed");
        MethodsSerializer.INSTANCE.serialize(featuredPinnedElement.getOnViewed(), jsonGenerator, serializerProvider);
    }
}
