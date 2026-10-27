package Podcast.DeeplinkInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementSerializer;
import SOACoreInterface.v1_0.ClientStatesListSerializer;
import SOACoreInterface.v1_0.ExtraSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
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
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class InvokeDeeplinkMethodSerializer extends JsonSerializer<InvokeDeeplinkMethod> {
    public static final InvokeDeeplinkMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InvokeDeeplinkMethodSerializer invokeDeeplinkMethodSerializer = new InvokeDeeplinkMethodSerializer();
        INSTANCE = invokeDeeplinkMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.DeeplinkInterface.v1_0.InvokeDeeplinkMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InvokeDeeplinkMethod.class, invokeDeeplinkMethodSerializer);
    }

    private InvokeDeeplinkMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InvokeDeeplinkMethod invokeDeeplinkMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (invokeDeeplinkMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(invokeDeeplinkMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InvokeDeeplinkMethod invokeDeeplinkMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("target");
        SimpleSerializers.serializeString(invokeDeeplinkMethod.getTarget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ImagesContract.URL);
        SimpleSerializers.serializeString(invokeDeeplinkMethod.getUrl(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onError");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(invokeDeeplinkMethod.getOnError(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(invokeDeeplinkMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(invokeDeeplinkMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backStackRequired");
        SimpleSerializers.serializePrimitiveBoolean(invokeDeeplinkMethod.isBackStackRequired(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("deeplinkUrl");
        SimpleSerializers.serializeString(invokeDeeplinkMethod.getDeeplinkUrl(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("preset");
        SimpleSerializers.serializeString(invokeDeeplinkMethod.getPreset(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("extra");
        ExtraSerializer.INSTANCE.serialize(invokeDeeplinkMethod.getExtra(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("before");
        jsonSerializer.serialize(invokeDeeplinkMethod.getBefore(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("after");
        jsonSerializer.serialize(invokeDeeplinkMethod.getAfter(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onSuccess");
        jsonSerializer.serialize(invokeDeeplinkMethod.getOnSuccess(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(invokeDeeplinkMethod.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("clientStates");
        ClientStatesListSerializer.INSTANCE.serialize(invokeDeeplinkMethod.getClientStates(), jsonGenerator, serializerProvider);
    }
}
