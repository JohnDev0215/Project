package Podcast.DeeplinkInterface.v1_0;

import SOACoreInterface.v1_0.QueueSerializer;
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
public class NavigateToDeeplinkMethodSerializer extends JsonSerializer<NavigateToDeeplinkMethod> {
    public static final NavigateToDeeplinkMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        NavigateToDeeplinkMethodSerializer navigateToDeeplinkMethodSerializer = new NavigateToDeeplinkMethodSerializer();
        INSTANCE = navigateToDeeplinkMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.DeeplinkInterface.v1_0.NavigateToDeeplinkMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(NavigateToDeeplinkMethod.class, navigateToDeeplinkMethodSerializer);
    }

    private NavigateToDeeplinkMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull NavigateToDeeplinkMethod navigateToDeeplinkMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (navigateToDeeplinkMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(navigateToDeeplinkMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(NavigateToDeeplinkMethod navigateToDeeplinkMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ImagesContract.URL);
        SimpleSerializers.serializeString(navigateToDeeplinkMethod.getUrl(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(navigateToDeeplinkMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(navigateToDeeplinkMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
