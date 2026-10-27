package Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0;

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
public class EpisodeFeaturedPlayElementSerializer extends JsonSerializer<EpisodeFeaturedPlayElement> {
    public static final EpisodeFeaturedPlayElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeFeaturedPlayElementSerializer episodeFeaturedPlayElementSerializer = new EpisodeFeaturedPlayElementSerializer();
        INSTANCE = episodeFeaturedPlayElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodeFeaturedPlayElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EpisodeFeaturedPlayElement.class, episodeFeaturedPlayElementSerializer);
    }

    private EpisodeFeaturedPlayElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EpisodeFeaturedPlayElement episodeFeaturedPlayElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (episodeFeaturedPlayElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(episodeFeaturedPlayElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EpisodeFeaturedPlayElement episodeFeaturedPlayElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(episodeFeaturedPlayElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(episodeFeaturedPlayElement.getLabel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(episodeFeaturedPlayElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(episodeFeaturedPlayElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(episodeFeaturedPlayElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("foregroundImage");
        SimpleSerializers.serializeString(episodeFeaturedPlayElement.getForegroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImage");
        SimpleSerializers.serializeString(episodeFeaturedPlayElement.getBackgroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(episodeFeaturedPlayElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        jsonSerializer.serialize(episodeFeaturedPlayElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(PageUriUtils.DO_PLAY_VALUE);
        EpisodePlayElementSerializer.INSTANCE.serialize(episodeFeaturedPlayElement.getPlay(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.TERTIARY_TEXT);
        SimpleSerializers.serializeString(episodeFeaturedPlayElement.getTertiaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(episodeFeaturedPlayElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
    }
}
