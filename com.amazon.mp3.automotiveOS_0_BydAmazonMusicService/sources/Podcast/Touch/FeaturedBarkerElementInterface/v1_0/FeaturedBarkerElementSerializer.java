package Podcast.Touch.FeaturedBarkerElementInterface.v1_0;

import Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0.FeaturedBarkerItemElementSerializer;
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
public class FeaturedBarkerElementSerializer extends JsonSerializer<FeaturedBarkerElement> {
    public static final FeaturedBarkerElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedBarkerElementSerializer featuredBarkerElementSerializer = new FeaturedBarkerElementSerializer();
        INSTANCE = featuredBarkerElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FeaturedBarkerElement.class, featuredBarkerElementSerializer);
    }

    private FeaturedBarkerElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FeaturedBarkerElement featuredBarkerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (featuredBarkerElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(featuredBarkerElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FeaturedBarkerElement featuredBarkerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("barker");
        FeaturedBarkerItemElementSerializer.INSTANCE.serialize(featuredBarkerElement.getBarker(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(featuredBarkerElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(featuredBarkerElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(featuredBarkerElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
    }
}
