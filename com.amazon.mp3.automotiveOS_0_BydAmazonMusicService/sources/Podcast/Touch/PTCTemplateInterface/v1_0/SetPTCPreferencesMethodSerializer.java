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
public class SetPTCPreferencesMethodSerializer extends JsonSerializer<SetPTCPreferencesMethod> {
    public static final SetPTCPreferencesMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetPTCPreferencesMethodSerializer setPTCPreferencesMethodSerializer = new SetPTCPreferencesMethodSerializer();
        INSTANCE = setPTCPreferencesMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.SetPTCPreferencesMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetPTCPreferencesMethod.class, setPTCPreferencesMethodSerializer);
    }

    private SetPTCPreferencesMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetPTCPreferencesMethod setPTCPreferencesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setPTCPreferencesMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setPTCPreferencesMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetPTCPreferencesMethod setPTCPreferencesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("showPTCIngress");
        SimpleSerializers.serializeBoolean(setPTCPreferencesMethod.isShowPTCIngress(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setPTCPreferencesMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setPTCPreferencesMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
