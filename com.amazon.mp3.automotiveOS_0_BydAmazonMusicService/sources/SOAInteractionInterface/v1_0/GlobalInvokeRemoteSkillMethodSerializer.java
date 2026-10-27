package SOAInteractionInterface.v1_0;

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
public class GlobalInvokeRemoteSkillMethodSerializer extends JsonSerializer<GlobalInvokeRemoteSkillMethod> {
    public static final GlobalInvokeRemoteSkillMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GlobalInvokeRemoteSkillMethodSerializer globalInvokeRemoteSkillMethodSerializer = new GlobalInvokeRemoteSkillMethodSerializer();
        INSTANCE = globalInvokeRemoteSkillMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAInteractionInterface.v1_0.GlobalInvokeRemoteSkillMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GlobalInvokeRemoteSkillMethod.class, globalInvokeRemoteSkillMethodSerializer);
    }

    private GlobalInvokeRemoteSkillMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GlobalInvokeRemoteSkillMethod globalInvokeRemoteSkillMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (globalInvokeRemoteSkillMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(globalInvokeRemoteSkillMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GlobalInvokeRemoteSkillMethod globalInvokeRemoteSkillMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("target");
        SimpleSerializers.serializeString(globalInvokeRemoteSkillMethod.getTarget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("clientStates");
        ClientStatesListSerializer.INSTANCE.serialize(globalInvokeRemoteSkillMethod.getClientStates(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ImagesContract.URL);
        SimpleSerializers.serializeString(globalInvokeRemoteSkillMethod.getUrl(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("before");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(globalInvokeRemoteSkillMethod.getBefore(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(globalInvokeRemoteSkillMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("preset");
        SimpleSerializers.serializeString(globalInvokeRemoteSkillMethod.getPreset(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("after");
        jsonSerializer.serialize(globalInvokeRemoteSkillMethod.getAfter(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onSuccess");
        jsonSerializer.serialize(globalInvokeRemoteSkillMethod.getOnSuccess(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(globalInvokeRemoteSkillMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onError");
        jsonSerializer.serialize(globalInvokeRemoteSkillMethod.getOnError(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("extra");
        ExtraSerializer.INSTANCE.serialize(globalInvokeRemoteSkillMethod.getExtra(), jsonGenerator, serializerProvider);
    }
}
