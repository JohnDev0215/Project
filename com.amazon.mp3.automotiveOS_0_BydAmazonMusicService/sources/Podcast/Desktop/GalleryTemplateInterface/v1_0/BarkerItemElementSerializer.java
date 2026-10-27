package Podcast.Desktop.GalleryTemplateInterface.v1_0;

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
public class BarkerItemElementSerializer extends JsonSerializer<BarkerItemElement> {
    public static final BarkerItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BarkerItemElementSerializer barkerItemElementSerializer = new BarkerItemElementSerializer();
        INSTANCE = barkerItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.BarkerItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BarkerItemElement.class, barkerItemElementSerializer);
    }

    private BarkerItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BarkerItemElement barkerItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (barkerItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(barkerItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BarkerItemElement barkerItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(barkerItemElement.getLabel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImageSmall");
        SimpleSerializers.serializeString(barkerItemElement.getBackgroundImageSmall(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(barkerItemElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(barkerItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImageLarge");
        SimpleSerializers.serializeString(barkerItemElement.getBackgroundImageLarge(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(barkerItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(barkerItemElement.getPrimaryText(), jsonGenerator, serializerProvider);
    }
}
