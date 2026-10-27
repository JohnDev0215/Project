package Podcast.Touch.PTCTemplateInterface.v1_0;

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
public class ShowPTCTrailerMethodSerializer extends JsonSerializer<ShowPTCTrailerMethod> {
    public static final ShowPTCTrailerMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ShowPTCTrailerMethodSerializer showPTCTrailerMethodSerializer = new ShowPTCTrailerMethodSerializer();
        INSTANCE = showPTCTrailerMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.ShowPTCTrailerMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ShowPTCTrailerMethod.class, showPTCTrailerMethodSerializer);
    }

    private ShowPTCTrailerMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ShowPTCTrailerMethod showPTCTrailerMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (showPTCTrailerMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(showPTCTrailerMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ShowPTCTrailerMethod showPTCTrailerMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(showPTCTrailerMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(showPTCTrailerMethod.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(showPTCTrailerMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("aboutElement");
        AboutElementSerializer.INSTANCE.serialize(showPTCTrailerMethod.getAboutElement(), jsonGenerator, serializerProvider);
    }
}
