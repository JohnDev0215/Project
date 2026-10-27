package Podcast.PlaybackMetricsInterface.v1_0;

import SOAAppSyncInterface.v1_0.OperationsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackMetricsOperationsClientStateSerializer extends JsonSerializer<PlaybackMetricsOperationsClientState> {
    public static final PlaybackMetricsOperationsClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaybackMetricsOperationsClientStateSerializer playbackMetricsOperationsClientStateSerializer = new PlaybackMetricsOperationsClientStateSerializer();
        INSTANCE = playbackMetricsOperationsClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricsOperationsClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PlaybackMetricsOperationsClientState.class, playbackMetricsOperationsClientStateSerializer);
    }

    private PlaybackMetricsOperationsClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PlaybackMetricsOperationsClientState playbackMetricsOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (playbackMetricsOperationsClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(playbackMetricsOperationsClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PlaybackMetricsOperationsClientState playbackMetricsOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("operations");
        OperationsSerializer.INSTANCE.serialize(playbackMetricsOperationsClientState.getOperations(), jsonGenerator, serializerProvider);
    }
}
