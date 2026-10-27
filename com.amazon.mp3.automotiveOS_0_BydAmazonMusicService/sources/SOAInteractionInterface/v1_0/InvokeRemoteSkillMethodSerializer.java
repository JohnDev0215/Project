package SOAInteractionInterface.v1_0;

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
public class InvokeRemoteSkillMethodSerializer extends JsonSerializer<InvokeRemoteSkillMethod> {
    public static final InvokeRemoteSkillMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InvokeRemoteSkillMethodSerializer invokeRemoteSkillMethodSerializer = new InvokeRemoteSkillMethodSerializer();
        INSTANCE = invokeRemoteSkillMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAInteractionInterface.v1_0.InvokeRemoteSkillMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InvokeRemoteSkillMethod.class, invokeRemoteSkillMethodSerializer);
    }

    private InvokeRemoteSkillMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InvokeRemoteSkillMethod invokeRemoteSkillMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (invokeRemoteSkillMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(invokeRemoteSkillMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InvokeRemoteSkillMethod invokeRemoteSkillMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ImagesContract.URL);
        SimpleSerializers.serializeString(invokeRemoteSkillMethod.getUrl(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("extra");
        ExtraSerializer.INSTANCE.serialize(invokeRemoteSkillMethod.getExtra(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("before");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(invokeRemoteSkillMethod.getBefore(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(invokeRemoteSkillMethod.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("after");
        jsonSerializer.serialize(invokeRemoteSkillMethod.getAfter(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onSuccess");
        jsonSerializer.serialize(invokeRemoteSkillMethod.getOnSuccess(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("clientStates");
        ClientStatesListSerializer.INSTANCE.serialize(invokeRemoteSkillMethod.getClientStates(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("target");
        SimpleSerializers.serializeString(invokeRemoteSkillMethod.getTarget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(invokeRemoteSkillMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("preset");
        SimpleSerializers.serializeString(invokeRemoteSkillMethod.getPreset(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onError");
        jsonSerializer.serialize(invokeRemoteSkillMethod.getOnError(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(invokeRemoteSkillMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
