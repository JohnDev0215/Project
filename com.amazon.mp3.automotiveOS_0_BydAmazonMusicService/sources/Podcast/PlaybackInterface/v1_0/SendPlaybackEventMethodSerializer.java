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
public class SendPlaybackEventMethodSerializer extends JsonSerializer<SendPlaybackEventMethod> {
    public static final SendPlaybackEventMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SendPlaybackEventMethodSerializer sendPlaybackEventMethodSerializer = new SendPlaybackEventMethodSerializer();
        INSTANCE = sendPlaybackEventMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.SendPlaybackEventMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SendPlaybackEventMethod.class, sendPlaybackEventMethodSerializer);
    }

    private SendPlaybackEventMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SendPlaybackEventMethod sendPlaybackEventMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (sendPlaybackEventMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(sendPlaybackEventMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SendPlaybackEventMethod sendPlaybackEventMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("playbackEvent");
        SimpleSerializers.serializeString(sendPlaybackEventMethod.getPlaybackEvent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(sendPlaybackEventMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(sendPlaybackEventMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
