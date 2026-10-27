package Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0;

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
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class FeaturedBarkerItemElementSerializer extends JsonSerializer<FeaturedBarkerItemElement> {
    public static final FeaturedBarkerItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedBarkerItemElementSerializer featuredBarkerItemElementSerializer = new FeaturedBarkerItemElementSerializer();
        INSTANCE = featuredBarkerItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0.FeaturedBarkerItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FeaturedBarkerItemElement.class, featuredBarkerItemElementSerializer);
    }

    private FeaturedBarkerItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FeaturedBarkerItemElement featuredBarkerItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (featuredBarkerItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(featuredBarkerItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FeaturedBarkerItemElement featuredBarkerItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("backgroundImageLarge");
        SimpleSerializers.serializeString(featuredBarkerItemElement.getBackgroundImageLarge(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(featuredBarkerItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(featuredBarkerItemElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(featuredBarkerItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImageSmall");
        SimpleSerializers.serializeString(featuredBarkerItemElement.getBackgroundImageSmall(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(featuredBarkerItemElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(featuredBarkerItemElement.getLabel(), jsonGenerator, serializerProvider);
    }
}
