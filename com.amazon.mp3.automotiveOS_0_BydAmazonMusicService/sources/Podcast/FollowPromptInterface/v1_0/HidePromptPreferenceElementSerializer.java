package Podcast.FollowPromptInterface.v1_0;

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
public class HidePromptPreferenceElementSerializer extends JsonSerializer<HidePromptPreferenceElement> {
    public static final HidePromptPreferenceElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        HidePromptPreferenceElementSerializer hidePromptPreferenceElementSerializer = new HidePromptPreferenceElementSerializer();
        INSTANCE = hidePromptPreferenceElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(HidePromptPreferenceElement.class, hidePromptPreferenceElementSerializer);
    }

    private HidePromptPreferenceElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull HidePromptPreferenceElement hidePromptPreferenceElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (hidePromptPreferenceElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(hidePromptPreferenceElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(HidePromptPreferenceElement hidePromptPreferenceElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("updatedTime");
        SimpleSerializers.serializePrimitiveLong(hidePromptPreferenceElement.getUpdatedTime(), jsonGenerator, serializerProvider);
    }
}
