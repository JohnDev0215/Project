package Podcast.Touch.PTCTemplateInterface.v1_0;

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
public class PTCPreferencesClientStateSerializer extends JsonSerializer<PTCPreferencesClientState> {
    public static final PTCPreferencesClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PTCPreferencesClientStateSerializer pTCPreferencesClientStateSerializer = new PTCPreferencesClientStateSerializer();
        INSTANCE = pTCPreferencesClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.PTCPreferencesClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PTCPreferencesClientState.class, pTCPreferencesClientStateSerializer);
    }

    private PTCPreferencesClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PTCPreferencesClientState pTCPreferencesClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (pTCPreferencesClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(pTCPreferencesClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PTCPreferencesClientState pTCPreferencesClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("showPTCIngress");
        SimpleSerializers.serializeBoolean(pTCPreferencesClientState.isShowPTCIngress(), jsonGenerator, serializerProvider);
    }
}
