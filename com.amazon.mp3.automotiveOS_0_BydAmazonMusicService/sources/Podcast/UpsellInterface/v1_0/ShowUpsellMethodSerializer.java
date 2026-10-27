package Podcast.UpsellInterface.v1_0;

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
public class ShowUpsellMethodSerializer extends JsonSerializer<ShowUpsellMethod> {
    public static final ShowUpsellMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ShowUpsellMethodSerializer showUpsellMethodSerializer = new ShowUpsellMethodSerializer();
        INSTANCE = showUpsellMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.UpsellInterface.v1_0.ShowUpsellMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ShowUpsellMethod.class, showUpsellMethodSerializer);
    }

    private ShowUpsellMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ShowUpsellMethod showUpsellMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (showUpsellMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(showUpsellMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ShowUpsellMethod showUpsellMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("trigger");
        SimpleSerializers.serializeString(showUpsellMethod.getTrigger(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("redirectDeeplinkUrl");
        SimpleSerializers.serializeString(showUpsellMethod.getRedirectDeeplinkUrl(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(showUpsellMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(showUpsellMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
