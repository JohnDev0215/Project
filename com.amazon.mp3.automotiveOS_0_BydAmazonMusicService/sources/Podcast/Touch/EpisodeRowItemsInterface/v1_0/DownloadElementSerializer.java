package Podcast.Touch.EpisodeRowItemsInterface.v1_0;

import SOACoreInterface.v1_0.MethodsSerializer;
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
public class DownloadElementSerializer extends JsonSerializer<DownloadElement> {
    public static final DownloadElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DownloadElementSerializer downloadElementSerializer = new DownloadElementSerializer();
        INSTANCE = downloadElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.EpisodeRowItemsInterface.v1_0.DownloadElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DownloadElement.class, downloadElementSerializer);
    }

    private DownloadElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DownloadElement downloadElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (downloadElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(downloadElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DownloadElement downloadElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onDownload");
        MethodsSerializer.INSTANCE.serialize(downloadElement.getOnDownload(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(downloadElement.getId(), jsonGenerator, serializerProvider);
    }
}
