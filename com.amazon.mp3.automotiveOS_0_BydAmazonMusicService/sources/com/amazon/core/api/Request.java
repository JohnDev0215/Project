package com.amazon.core.api;

import com.amazon.core.api.ImmutableRequest;
import com.amazon.music.destination.parser.ParserUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes2.dex */
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE)
@JsonDeserialize(using = RequestDeserializer.class)
@JsonSerialize(as = ImmutableRequest.class)
@Value.Immutable
public abstract class Request {
    private final Map<String, Object> extra = Collections.synchronizedMap(new HashMap());
    private final Map<String, Object> parameterizedInterfaces = Collections.synchronizedMap(new HashMap());

    public abstract Map<Class, Annotation> annotations();

    public abstract Header header();

    public abstract String host();

    public abstract String ipAddress();

    public abstract Map<String, String> path();

    public abstract Map<String, String> querystring();

    public final Map<String, Object> extra() {
        return this.extra;
    }

    public final Map<String, Object> parameterizedInterfaces() {
        return this.parameterizedInterfaces;
    }

    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder extends ImmutableRequest.Builder {
        Builder() {
        }
    }

    static final class RequestDeserializer extends StdDeserializer<Request> {
        public RequestDeserializer() {
            this(null);
        }

        public RequestDeserializer(Class<Request> cls) {
            super(cls);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final Request deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonNode jsonNode = (JsonNode) jsonParser.getCodec().readTree(jsonParser);
            ObjectMapper objectMapper = new ObjectMapper();
            Header header = (Header) objectMapper.readValue(jsonNode.get("header").toString(), Header.class);
            Map<String, ? extends String> map = (Map) objectMapper.readValue(jsonNode.get("querystring").toString(), DecodedMap.class);
            Map<String, ? extends String> map2 = (Map) objectMapper.readValue(jsonNode.get("path").toString(), DecodedMap.class);
            return Request.builder().header(header).path(map2).querystring(map).ipAddress((String) objectMapper.readValue(jsonNode.get("ipAddress").toString(), String.class)).host((String) objectMapper.readValue(jsonNode.get("host").toString(), String.class)).build();
        }

        private static final class DecodedMap extends HashMap<String, String> {
            private DecodedMap() {
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public String put(String str, String str2) {
                try {
                    return (String) super.put(str, URLDecoder.decode(str2, ParserUtil.UTF_8));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
