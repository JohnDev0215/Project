package Podcast.Desktop.GalleryTemplateInterface.v1_0;

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
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeDescriptiveShovelerElementSerializer extends JsonSerializer<EpisodeDescriptiveShovelerElement> {
    public static final EpisodeDescriptiveShovelerElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeDescriptiveShovelerElementSerializer episodeDescriptiveShovelerElementSerializer = new EpisodeDescriptiveShovelerElementSerializer();
        INSTANCE = episodeDescriptiveShovelerElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElementSerializer", new Version(1, 0, 0, null, null, null));
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
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        EpisodeHorizontalItemsSerializer.INSTANCE.serialize(episodeDescriptiveShovelerElement.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(episodeDescriptiveShovelerElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(PageUriUtils.DO_SEE_MORE_VALUE);
        EpisodeHorizontalSeeMoreItemElementSerializer.INSTANCE.serialize(episodeDescriptiveShovelerElement.getSeeMore(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        MethodsSerializer.INSTANCE.serialize(episodeDescriptiveShovelerElement.getOnViewed(), jsonGenerator, serializerProvider);
    }
}
