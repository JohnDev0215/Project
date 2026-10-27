package Podcast.Touch.DetailTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementSerializer;
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
public class FeaturedSingleItemElementSerializer extends JsonSerializer<FeaturedSingleItemElement> {
    public static final FeaturedSingleItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedSingleItemElementSerializer featuredSingleItemElementSerializer = new FeaturedSingleItemElementSerializer();
        INSTANCE = featuredSingleItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedSingleItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FeaturedSingleItemElement.class, featuredSingleItemElementSerializer);
    }

    private FeaturedSingleItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FeaturedSingleItemElement featuredSingleItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (featuredSingleItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(featuredSingleItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FeaturedSingleItemElement featuredSingleItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("download");
        DownloadElementSerializer.INSTANCE.serialize(featuredSingleItemElement.getDownload(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("episodeOptions");
        EpisodeOptionsElementSerializer.INSTANCE.serialize(featuredSingleItemElement.getEpisodeOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("totalDurationMilliseconds");
        SimpleSerializers.serializeLong(featuredSingleItemElement.getTotalDurationMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(featuredSingleItemElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(featuredSingleItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(featuredSingleItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPlaySelected");
        jsonSerializer.serialize(featuredSingleItemElement.getOnPlaySelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onImageSelected");
        jsonSerializer.serialize(featuredSingleItemElement.getOnImageSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("bookmarkId");
        SimpleSerializers.serializeString(featuredSingleItemElement.getBookmarkId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isDisabled");
        SimpleSerializers.serializeBoolean(featuredSingleItemElement.isIsDisabled(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(featuredSingleItemElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(featuredSingleItemElement.getLabel(), jsonGenerator, serializerProvider);
    }
}
