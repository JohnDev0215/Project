package Podcast.PlaybackInterface.v1_0;

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
public class PlayMediaMethodSerializer extends JsonSerializer<PlayMediaMethod> {
    public static final PlayMediaMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlayMediaMethodSerializer playMediaMethodSerializer = new PlayMediaMethodSerializer();
        INSTANCE = playMediaMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.PlayMediaMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PlayMediaMethod.class, playMediaMethodSerializer);
    }

    private PlayMediaMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PlayMediaMethod playMediaMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (playMediaMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(playMediaMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PlayMediaMethod playMediaMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("mediaId");
        SimpleSerializers.serializeString(playMediaMethod.getMediaId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(playMediaMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("resumeAtBookmark");
        SimpleSerializers.serializeBoolean(playMediaMethod.isResumeAtBookmark(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("rewindOffsetMilliseconds");
        SimpleSerializers.serializeLong(playMediaMethod.getRewindOffsetMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(playMediaMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
