package SOAInteractionInterface.v1_0;

import SOACoreInterface.v1_0.ClientStatesListDeserializer;
import SOACoreInterface.v1_0.ExtraDeserializer;
import SOACoreInterface.v1_0.MethodsDeserializer;
import SOACoreInterface.v1_0.QueueDeserializer;
import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class GlobalInvokeRemoteSkillMethodDeserializer extends JsonDeserializer<GlobalInvokeRemoteSkillMethod> {
    public static final GlobalInvokeRemoteSkillMethodDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GlobalInvokeRemoteSkillMethodDeserializer globalInvokeRemoteSkillMethodDeserializer = new GlobalInvokeRemoteSkillMethodDeserializer();
        INSTANCE = globalInvokeRemoteSkillMethodDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOAInteractionInterface.v1_0.GlobalInvokeRemoteSkillMethodDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(GlobalInvokeRemoteSkillMethod.class, globalInvokeRemoteSkillMethodDeserializer);
    }

    private GlobalInvokeRemoteSkillMethodDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public GlobalInvokeRemoteSkillMethod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("SOAInteractionInterface.v1_0#GlobalInvokeRemoteSkillMethod".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public GlobalInvokeRemoteSkillMethod deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        GlobalInvokeRemoteSkillMethod globalInvokeRemoteSkillMethod = new GlobalInvokeRemoteSkillMethod();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("target".equals(currentName)) {
                globalInvokeRemoteSkillMethod.setTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("clientStates".equals(currentName)) {
                globalInvokeRemoteSkillMethod.setClientStates(ClientStatesListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ImagesContract.URL.equals(currentName)) {
                globalInvokeRemoteSkillMethod.setUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("before".equals(currentName)) {
                globalInvokeRemoteSkillMethod.setBefore(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("queue".equals(currentName)) {
                globalInvokeRemoteSkillMethod.setQueue(QueueDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("preset".equals(currentName)) {
                globalInvokeRemoteSkillMethod.setPreset(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("after".equals(currentName)) {
                globalInvokeRemoteSkillMethod.setAfter(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onSuccess".equals(currentName)) {
                globalInvokeRemoteSkillMethod.setOnSuccess(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("forced".equals(currentName)) {
                globalInvokeRemoteSkillMethod.setForced(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("onError".equals(currentName)) {
                globalInvokeRemoteSkillMethod.setOnError(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("extra".equals(currentName)) {
                globalInvokeRemoteSkillMethod.setExtra(ExtraDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return globalInvokeRemoteSkillMethod;
    }
}
