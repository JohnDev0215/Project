package Podcast.Desktop.NetworkInterface.v1_0;

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
public class ShowErrorMessageMethodSerializer extends JsonSerializer<ShowErrorMessageMethod> {
    public static final ShowErrorMessageMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ShowErrorMessageMethodSerializer showErrorMessageMethodSerializer = new ShowErrorMessageMethodSerializer();
        INSTANCE = showErrorMessageMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.NetworkInterface.v1_0.ShowErrorMessageMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ShowErrorMessageMethod.class, showErrorMessageMethodSerializer);
    }

    private ShowErrorMessageMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ShowErrorMessageMethod showErrorMessageMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (showErrorMessageMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(showErrorMessageMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ShowErrorMessageMethod showErrorMessageMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(showErrorMessageMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(showErrorMessageMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
