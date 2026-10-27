package Podcast.DeeplinkInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementDeserializer;
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
public class InvokeDeeplinkMethodDeserializer extends JsonDeserializer<InvokeDeeplinkMethod> {
    public static final InvokeDeeplinkMethodDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InvokeDeeplinkMethodDeserializer invokeDeeplinkMethodDeserializer = new InvokeDeeplinkMethodDeserializer();
        INSTANCE = invokeDeeplinkMethodDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.DeeplinkInterface.v1_0.InvokeDeeplinkMethodDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(InvokeDeeplinkMethod.class, invokeDeeplinkMethodDeserializer);
    }

    private InvokeDeeplinkMethodDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public InvokeDeeplinkMethod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.DeeplinkInterface.v1_0#InvokeDeeplinkMethod".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public InvokeDeeplinkMethod deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        InvokeDeeplinkMethod invokeDeeplinkMethod = new InvokeDeeplinkMethod();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("target".equals(currentName)) {
                invokeDeeplinkMethod.setTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ImagesContract.URL.equals(currentName)) {
                invokeDeeplinkMethod.setUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onError".equals(currentName)) {
                invokeDeeplinkMethod.setOnError(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("queue".equals(currentName)) {
                invokeDeeplinkMethod.setQueue(QueueDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("forced".equals(currentName)) {
                invokeDeeplinkMethod.setForced(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("backStackRequired".equals(currentName)) {
                invokeDeeplinkMethod.setBackStackRequired(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("deeplinkUrl".equals(currentName)) {
                invokeDeeplinkMethod.setDeeplinkUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("preset".equals(currentName)) {
                invokeDeeplinkMethod.setPreset(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("extra".equals(currentName)) {
                invokeDeeplinkMethod.setExtra(ExtraDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("before".equals(currentName)) {
                invokeDeeplinkMethod.setBefore(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("after".equals(currentName)) {
                invokeDeeplinkMethod.setAfter(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onSuccess".equals(currentName)) {
                invokeDeeplinkMethod.setOnSuccess(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("latencyMetricElement".equals(currentName)) {
                invokeDeeplinkMethod.setLatencyMetricElement(LatencyMetricElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("clientStates".equals(currentName)) {
                invokeDeeplinkMethod.setClientStates(ClientStatesListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return invokeDeeplinkMethod;
    }
}
