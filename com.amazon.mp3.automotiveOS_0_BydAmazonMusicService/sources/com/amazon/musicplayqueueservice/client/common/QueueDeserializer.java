package com.amazon.musicplayqueueservice.client.common;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class QueueDeserializer extends JsonDeserializer<Queue> {
    public static final QueueDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        QueueDeserializer queueDeserializer = new QueueDeserializer();
        INSTANCE = queueDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.common.QueueDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Queue.class, queueDeserializer);
    }

    private QueueDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Queue deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.common#Queue".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Queue deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Queue queue = new Queue();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("queueId".equals(currentName)) {
                queue.setQueueId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("ttlInSeconds".equals(currentName)) {
                queue.setTtlInSeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("pageToken".equals(currentName)) {
                queue.setPageToken(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("shufflePlayback".equals(currentName)) {
                queue.setShufflePlayback(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("queueMetadata".equals(currentName)) {
                queue.setQueueMetadata(QueueMetadataDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return queue;
    }
}
