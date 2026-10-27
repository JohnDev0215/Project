package Podcast.BiteBookmarkInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class BiteBookmarkWriteElementSerializer extends JsonSerializer<BiteBookmarkWriteElement> {
    public static final BiteBookmarkWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BiteBookmarkWriteElementSerializer biteBookmarkWriteElementSerializer = new BiteBookmarkWriteElementSerializer();
        INSTANCE = biteBookmarkWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BiteBookmarkWriteElement.class, biteBookmarkWriteElementSerializer);
    }

    private BiteBookmarkWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BiteBookmarkWriteElement biteBookmarkWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (biteBookmarkWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(biteBookmarkWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BiteBookmarkWriteElement biteBookmarkWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(biteBookmarkWriteElement.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("progressMilliseconds");
        SimpleSerializers.serializeLong(biteBookmarkWriteElement.getProgressMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("totalDurationMilliseconds");
        SimpleSerializers.serializeLong(biteBookmarkWriteElement.getTotalDurationMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("episodeId");
        SimpleSerializers.serializeString(biteBookmarkWriteElement.getEpisodeId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(biteBookmarkWriteElement.getId(), jsonGenerator, serializerProvider);
    }
}
