package Podcast.Touch.PTCTemplateInterface.v1_0;

import com.amazon.music.find.utils.DeeplinksUtils;
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
public class PTCSelectionsClientStateSerializer extends JsonSerializer<PTCSelectionsClientState> {
    public static final PTCSelectionsClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PTCSelectionsClientStateSerializer pTCSelectionsClientStateSerializer = new PTCSelectionsClientStateSerializer();
        INSTANCE = pTCSelectionsClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.PTCSelectionsClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PTCSelectionsClientState.class, pTCSelectionsClientStateSerializer);
    }

    private PTCSelectionsClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PTCSelectionsClientState pTCSelectionsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (pTCSelectionsClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(pTCSelectionsClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PTCSelectionsClientState pTCSelectionsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("categories");
        JsonSerializer<List<String>> jsonSerializer = StringsSerializer.INSTANCE;
        jsonSerializer.serialize(pTCSelectionsClientState.getCategories(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(DeeplinksUtils.PODCASTS_PATH_SEGMENT);
        jsonSerializer.serialize(pTCSelectionsClientState.getPodcasts(), jsonGenerator, serializerProvider);
    }
}
