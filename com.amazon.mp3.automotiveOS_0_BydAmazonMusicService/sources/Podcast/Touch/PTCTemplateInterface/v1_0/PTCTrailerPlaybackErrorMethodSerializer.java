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
public class PTCTrailerPlaybackErrorMethodSerializer extends JsonSerializer<PTCTrailerPlaybackErrorMethod> {
    public static final PTCTrailerPlaybackErrorMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PTCTrailerPlaybackErrorMethodSerializer pTCTrailerPlaybackErrorMethodSerializer = new PTCTrailerPlaybackErrorMethodSerializer();
        INSTANCE = pTCTrailerPlaybackErrorMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.PTCTrailerPlaybackErrorMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PTCTrailerPlaybackErrorMethod.class, pTCTrailerPlaybackErrorMethodSerializer);
    }

    private PTCTrailerPlaybackErrorMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PTCTrailerPlaybackErrorMethod pTCTrailerPlaybackErrorMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (pTCTrailerPlaybackErrorMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(pTCTrailerPlaybackErrorMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PTCTrailerPlaybackErrorMethod pTCTrailerPlaybackErrorMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(pTCTrailerPlaybackErrorMethod.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(pTCTrailerPlaybackErrorMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(pTCTrailerPlaybackErrorMethod.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(pTCTrailerPlaybackErrorMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
