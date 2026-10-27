package Podcast.DeeplinkInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class DeeplinkClientStateSerializer extends JsonSerializer<DeeplinkClientState> {
    public static final DeeplinkClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DeeplinkClientStateSerializer deeplinkClientStateSerializer = new DeeplinkClientStateSerializer();
        INSTANCE = deeplinkClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.DeeplinkInterface.v1_0.DeeplinkClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DeeplinkClientState.class, deeplinkClientStateSerializer);
    }

    private DeeplinkClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DeeplinkClientState deeplinkClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (deeplinkClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(deeplinkClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DeeplinkClientState deeplinkClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ImagesContract.URL);
        SimpleSerializers.serializeString(deeplinkClientState.getUrl(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backStackRequired");
        SimpleSerializers.serializePrimitiveBoolean(deeplinkClientState.isBackStackRequired(), jsonGenerator, serializerProvider);
    }
}
