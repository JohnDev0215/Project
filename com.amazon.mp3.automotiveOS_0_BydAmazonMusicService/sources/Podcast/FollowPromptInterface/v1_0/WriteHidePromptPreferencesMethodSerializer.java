package Podcast.FollowPromptInterface.v1_0;

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
public class WriteHidePromptPreferencesMethodSerializer extends JsonSerializer<WriteHidePromptPreferencesMethod> {
    public static final WriteHidePromptPreferencesMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WriteHidePromptPreferencesMethodSerializer writeHidePromptPreferencesMethodSerializer = new WriteHidePromptPreferencesMethodSerializer();
        INSTANCE = writeHidePromptPreferencesMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.FollowPromptInterface.v1_0.WriteHidePromptPreferencesMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(WriteHidePromptPreferencesMethod.class, writeHidePromptPreferencesMethodSerializer);
    }

    private WriteHidePromptPreferencesMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull WriteHidePromptPreferencesMethod writeHidePromptPreferencesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (writeHidePromptPreferencesMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(writeHidePromptPreferencesMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(WriteHidePromptPreferencesMethod writeHidePromptPreferencesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(writeHidePromptPreferencesMethod.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("preference");
        HidePromptPreferenceElementSerializer.INSTANCE.serialize(writeHidePromptPreferencesMethod.getPreference(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(writeHidePromptPreferencesMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(writeHidePromptPreferencesMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
