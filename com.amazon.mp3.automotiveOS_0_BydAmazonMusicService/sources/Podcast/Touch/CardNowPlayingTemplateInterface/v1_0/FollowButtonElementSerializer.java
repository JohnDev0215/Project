package Podcast.Touch.CardNowPlayingTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class FollowButtonElementSerializer extends JsonSerializer<FollowButtonElement> {
    public static final FollowButtonElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FollowButtonElementSerializer followButtonElementSerializer = new FollowButtonElementSerializer();
        INSTANCE = followButtonElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.FollowButtonElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FollowButtonElement.class, followButtonElementSerializer);
    }

    private FollowButtonElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FollowButtonElement followButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (followButtonElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(followButtonElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FollowButtonElement followButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onFollow");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(followButtonElement.getOnFollow(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(followButtonElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("followText");
        SimpleSerializers.serializeString(followButtonElement.getFollowText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("unfollowText");
        SimpleSerializers.serializeString(followButtonElement.getUnfollowText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onUnfollow");
        jsonSerializer.serialize(followButtonElement.getOnUnfollow(), jsonGenerator, serializerProvider);
    }
}
