package Podcast.Touch.GalleryTemplateInterface.v1_0;

import Podcast.Touch.CommonButtonElementInterface.v1_0.PrimaryButtonElementSerializer;
import Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElementsSerializer;
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
public class ExperienceVisualShovelerElementSerializer extends JsonSerializer<ExperienceVisualShovelerElement> {
    public static final ExperienceVisualShovelerElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ExperienceVisualShovelerElementSerializer experienceVisualShovelerElementSerializer = new ExperienceVisualShovelerElementSerializer();
        INSTANCE = experienceVisualShovelerElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v1_0.ExperienceVisualShovelerElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ExperienceVisualShovelerElement.class, experienceVisualShovelerElementSerializer);
    }

    private ExperienceVisualShovelerElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ExperienceVisualShovelerElement experienceVisualShovelerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (experienceVisualShovelerElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(experienceVisualShovelerElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ExperienceVisualShovelerElement experienceVisualShovelerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        MediaItemElementsSerializer.INSTANCE.serialize(experienceVisualShovelerElement.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(experienceVisualShovelerElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(experienceVisualShovelerElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(experienceVisualShovelerElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(experienceVisualShovelerElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(experienceVisualShovelerElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("primaryButton");
        PrimaryButtonElementSerializer.INSTANCE.serialize(experienceVisualShovelerElement.getPrimaryButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(experienceVisualShovelerElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
    }
}
