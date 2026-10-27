package Podcast.PlaybackInterface.v1_0;

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
public class LocalEpisodeAlreadyPlayingClientStateSerializer extends JsonSerializer<LocalEpisodeAlreadyPlayingClientState> {
    public static final LocalEpisodeAlreadyPlayingClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LocalEpisodeAlreadyPlayingClientStateSerializer localEpisodeAlreadyPlayingClientStateSerializer = new LocalEpisodeAlreadyPlayingClientStateSerializer();
        INSTANCE = localEpisodeAlreadyPlayingClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.LocalEpisodeAlreadyPlayingClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(LocalEpisodeAlreadyPlayingClientState.class, localEpisodeAlreadyPlayingClientStateSerializer);
    }

    private LocalEpisodeAlreadyPlayingClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull LocalEpisodeAlreadyPlayingClientState localEpisodeAlreadyPlayingClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localEpisodeAlreadyPlayingClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(localEpisodeAlreadyPlayingClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(LocalEpisodeAlreadyPlayingClientState localEpisodeAlreadyPlayingClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("alreadyPlaying");
        SimpleSerializers.serializeBoolean(localEpisodeAlreadyPlayingClientState.isAlreadyPlaying(), jsonGenerator, serializerProvider);
    }
}
