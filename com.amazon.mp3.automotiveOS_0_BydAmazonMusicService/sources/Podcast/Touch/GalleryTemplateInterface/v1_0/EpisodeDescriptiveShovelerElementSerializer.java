package Podcast.Touch.GalleryTemplateInterface.v1_0;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElementsSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
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
public class EpisodeDescriptiveShovelerElementSerializer extends JsonSerializer<EpisodeDescriptiveShovelerElement> {
    public static final EpisodeDescriptiveShovelerElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeDescriptiveShovelerElementSerializer episodeDescriptiveShovelerElementSerializer = new EpisodeDescriptiveShovelerElementSerializer();
        INSTANCE = episodeDescriptiveShovelerElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EpisodeDescriptiveShovelerElement.class, episodeDescriptiveShovelerElementSerializer);
    }

    private EpisodeDescriptiveShovelerElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EpisodeDescriptiveShovelerElement episodeDescriptiveShovelerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (episodeDescriptiveShovelerElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(episodeDescriptiveShovelerElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EpisodeDescriptiveShovelerElement episodeDescriptiveShovelerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(episodeDescriptiveShovelerElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(episodeDescriptiveShovelerElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(PageUriUtils.DO_SEE_MORE_VALUE);
        EpisodeDescriptiveSeeMoreItemElementSerializer.INSTANCE.serialize(episodeDescriptiveShovelerElement.getSeeMore(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        EpisodeRowItemElementsSerializer.INSTANCE.serialize(episodeDescriptiveShovelerElement.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(episodeDescriptiveShovelerElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(episodeDescriptiveShovelerElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
    }
}
