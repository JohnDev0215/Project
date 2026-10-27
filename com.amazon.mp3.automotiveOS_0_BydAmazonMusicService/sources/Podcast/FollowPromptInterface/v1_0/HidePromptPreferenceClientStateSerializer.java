package Podcast.FollowPromptInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class HidePromptPreferenceClientStateSerializer extends JsonSerializer<HidePromptPreferenceClientState> {
    public static final HidePromptPreferenceClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        HidePromptPreferenceClientStateSerializer hidePromptPreferenceClientStateSerializer = new HidePromptPreferenceClientStateSerializer();
        INSTANCE = hidePromptPreferenceClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(HidePromptPreferenceClientState.class, hidePromptPreferenceClientStateSerializer);
    }

    private HidePromptPreferenceClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull HidePromptPreferenceClientState hidePromptPreferenceClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (hidePromptPreferenceClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(hidePromptPreferenceClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(HidePromptPreferenceClientState hidePromptPreferenceClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("preferenceMap");
        HidePromptPreferencesMapSerializer.INSTANCE.serialize(hidePromptPreferenceClientState.getPreferenceMap(), jsonGenerator, serializerProvider);
    }
}
