package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

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
public class RefinementOptionsClientStateSerializer extends JsonSerializer<RefinementOptionsClientState> {
    public static final RefinementOptionsClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RefinementOptionsClientStateSerializer refinementOptionsClientStateSerializer = new RefinementOptionsClientStateSerializer();
        INSTANCE = refinementOptionsClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v1_0.RefinementOptionsClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RefinementOptionsClientState.class, refinementOptionsClientStateSerializer);
    }

    private RefinementOptionsClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RefinementOptionsClientState refinementOptionsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (refinementOptionsClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(refinementOptionsClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RefinementOptionsClientState refinementOptionsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("unplayed");
        SimpleSerializers.serializeBoolean(refinementOptionsClientState.isUnplayed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seasonNumber");
        SimpleSerializers.serializeString(refinementOptionsClientState.getSeasonNumber(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("sort");
        SimpleSerializers.serializeString(refinementOptionsClientState.getSort(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("download");
        SimpleSerializers.serializeBoolean(refinementOptionsClientState.isDownload(), jsonGenerator, serializerProvider);
    }
}
