package Podcast.PlaybackInterface.v1_0;

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
public class SetWriteAndReportFrequenciesMethodSerializer extends JsonSerializer<SetWriteAndReportFrequenciesMethod> {
    public static final SetWriteAndReportFrequenciesMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetWriteAndReportFrequenciesMethodSerializer setWriteAndReportFrequenciesMethodSerializer = new SetWriteAndReportFrequenciesMethodSerializer();
        INSTANCE = setWriteAndReportFrequenciesMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.SetWriteAndReportFrequenciesMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetWriteAndReportFrequenciesMethod.class, setWriteAndReportFrequenciesMethodSerializer);
    }

    private SetWriteAndReportFrequenciesMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetWriteAndReportFrequenciesMethod setWriteAndReportFrequenciesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setWriteAndReportFrequenciesMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setWriteAndReportFrequenciesMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetWriteAndReportFrequenciesMethod setWriteAndReportFrequenciesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("writeFrequencySeconds");
        SimpleSerializers.serializeLong(setWriteAndReportFrequenciesMethod.getWriteFrequencySeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setWriteAndReportFrequenciesMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("writeAndReportFrequencySeconds");
        SimpleSerializers.serializeLong(setWriteAndReportFrequenciesMethod.getWriteAndReportFrequencySeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setWriteAndReportFrequenciesMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
