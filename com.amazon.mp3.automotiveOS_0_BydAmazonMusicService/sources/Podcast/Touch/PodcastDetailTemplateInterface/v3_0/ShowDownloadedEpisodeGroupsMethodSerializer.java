package Podcast.Touch.PodcastDetailTemplateInterface.v3_0;

import SOACoreInterface.v1_0.QueueSerializer;
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
public class ShowDownloadedEpisodeGroupsMethodSerializer extends JsonSerializer<ShowDownloadedEpisodeGroupsMethod> {
    public static final ShowDownloadedEpisodeGroupsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ShowDownloadedEpisodeGroupsMethodSerializer showDownloadedEpisodeGroupsMethodSerializer = new ShowDownloadedEpisodeGroupsMethodSerializer();
        INSTANCE = showDownloadedEpisodeGroupsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ShowDownloadedEpisodeGroupsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ShowDownloadedEpisodeGroupsMethod.class, showDownloadedEpisodeGroupsMethodSerializer);
    }

    private ShowDownloadedEpisodeGroupsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ShowDownloadedEpisodeGroupsMethod showDownloadedEpisodeGroupsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (showDownloadedEpisodeGroupsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(showDownloadedEpisodeGroupsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ShowDownloadedEpisodeGroupsMethod showDownloadedEpisodeGroupsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(showDownloadedEpisodeGroupsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(showDownloadedEpisodeGroupsMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
