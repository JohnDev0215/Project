package Podcast.Touch.PodcastDetailTemplateInterface.v3_0;

import Podcast.Touch.EpisodeGroupItemsInterface.v1_0.EpisodeGroupItemElementsSerializer;
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
public class ReplaceEpisodeGroupItemsMethodSerializer extends JsonSerializer<ReplaceEpisodeGroupItemsMethod> {
    public static final ReplaceEpisodeGroupItemsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ReplaceEpisodeGroupItemsMethodSerializer replaceEpisodeGroupItemsMethodSerializer = new ReplaceEpisodeGroupItemsMethodSerializer();
        INSTANCE = replaceEpisodeGroupItemsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ReplaceEpisodeGroupItemsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ReplaceEpisodeGroupItemsMethod.class, replaceEpisodeGroupItemsMethodSerializer);
    }

    private ReplaceEpisodeGroupItemsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ReplaceEpisodeGroupItemsMethod replaceEpisodeGroupItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (replaceEpisodeGroupItemsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(replaceEpisodeGroupItemsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ReplaceEpisodeGroupItemsMethod replaceEpisodeGroupItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        EpisodeGroupItemElementsSerializer.INSTANCE.serialize(replaceEpisodeGroupItemsMethod.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(replaceEpisodeGroupItemsMethod.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        jsonSerializer.serialize(replaceEpisodeGroupItemsMethod.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(replaceEpisodeGroupItemsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(replaceEpisodeGroupItemsMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
