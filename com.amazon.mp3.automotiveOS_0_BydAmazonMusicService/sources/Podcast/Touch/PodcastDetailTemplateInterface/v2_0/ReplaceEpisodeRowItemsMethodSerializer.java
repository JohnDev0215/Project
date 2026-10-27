package Podcast.Touch.PodcastDetailTemplateInterface.v2_0;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElementsSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import SOACoreInterface.v1_0.QueueSerializer;
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
public class ReplaceEpisodeRowItemsMethodSerializer extends JsonSerializer<ReplaceEpisodeRowItemsMethod> {
    public static final ReplaceEpisodeRowItemsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ReplaceEpisodeRowItemsMethodSerializer replaceEpisodeRowItemsMethodSerializer = new ReplaceEpisodeRowItemsMethodSerializer();
        INSTANCE = replaceEpisodeRowItemsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ReplaceEpisodeRowItemsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ReplaceEpisodeRowItemsMethod.class, replaceEpisodeRowItemsMethodSerializer);
    }

    private ReplaceEpisodeRowItemsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ReplaceEpisodeRowItemsMethod replaceEpisodeRowItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (replaceEpisodeRowItemsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(replaceEpisodeRowItemsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ReplaceEpisodeRowItemsMethod replaceEpisodeRowItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        EpisodeRowItemElementsSerializer.INSTANCE.serialize(replaceEpisodeRowItemsMethod.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(replaceEpisodeRowItemsMethod.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        jsonSerializer.serialize(replaceEpisodeRowItemsMethod.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(replaceEpisodeRowItemsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(replaceEpisodeRowItemsMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
