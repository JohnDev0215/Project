package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.google.common.net.HostAndPort;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public class HostAndPortDeserializer extends FromStringDeserializer<HostAndPort> {
    private static final long serialVersionUID = 1;
    public static final HostAndPortDeserializer std = new HostAndPortDeserializer();

    public HostAndPortDeserializer() {
        super(HostAndPort.class);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public HostAndPort deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.START_OBJECT)) {
            JsonNode jsonNode = (JsonNode) jsonParser.readValueAsTree();
            JsonNode jsonNode2 = jsonNode.get("host");
            String strAsText = jsonNode2 == null ? jsonNode.path("hostText").asText() : jsonNode2.textValue();
            JsonNode jsonNode3 = jsonNode.get("port");
            if (jsonNode3 == null) {
                return HostAndPort.fromString(strAsText);
            }
            return HostAndPort.fromParts(strAsText, jsonNode3.asInt());
        }
        return (HostAndPort) super.deserialize(jsonParser, deserializationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public HostAndPort _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
        return HostAndPort.fromString(str);
    }
}
