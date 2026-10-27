package SOACacheInterface.v1_0;

import Podcast.JumpBackInInterface.v1_0.InvalidateJumpBackInCacheMethod;
import Podcast.JumpBackInInterface.v1_0.InvalidateJumpBackInCacheMethodSerializer;
import Podcast.Touch.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethod;
import Podcast.Touch.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethodSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.InvalidatePTCCacheMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.InvalidatePTCCacheMethodSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class InvalidateCacheMethodSerializer extends JsonSerializer<InvalidateCacheMethod> {
    public static final InvalidateCacheMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InvalidateCacheMethodSerializer invalidateCacheMethodSerializer = new InvalidateCacheMethodSerializer();
        INSTANCE = invalidateCacheMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOACacheInterface.v1_0.InvalidateCacheMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InvalidateCacheMethod.class, invalidateCacheMethodSerializer);
    }

    private InvalidateCacheMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InvalidateCacheMethod invalidateCacheMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (invalidateCacheMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (invalidateCacheMethod instanceof InvalidatePTCCacheMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#InvalidatePTCCacheMethod");
            InvalidatePTCCacheMethodSerializer.INSTANCE.serializeFields((InvalidatePTCCacheMethod) invalidateCacheMethod, jsonGenerator, serializerProvider);
        } else if (invalidateCacheMethod instanceof InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v1_0#InvalidateFeaturedItemMethod");
            InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((InvalidateFeaturedItemMethod) invalidateCacheMethod, jsonGenerator, serializerProvider);
        } else if (invalidateCacheMethod instanceof Podcast.Touch.LatestTemplateInterface.v2_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v2_0#InvalidateFeaturedItemMethod");
            Podcast.Touch.LatestTemplateInterface.v2_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.LatestTemplateInterface.v2_0.InvalidateFeaturedItemMethod) invalidateCacheMethod, jsonGenerator, serializerProvider);
        } else if (invalidateCacheMethod instanceof InvalidateJumpBackInCacheMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.JumpBackInInterface.v1_0#InvalidateJumpBackInCacheMethod");
            InvalidateJumpBackInCacheMethodSerializer.INSTANCE.serializeFields((InvalidateJumpBackInCacheMethod) invalidateCacheMethod, jsonGenerator, serializerProvider);
        } else if (invalidateCacheMethod instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod) invalidateCacheMethod, jsonGenerator, serializerProvider);
        } else if (invalidateCacheMethod instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#InvalidateFeaturedItemMethod");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.InvalidateFeaturedItemMethod) invalidateCacheMethod, jsonGenerator, serializerProvider);
        } else if (invalidateCacheMethod instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod) invalidateCacheMethod, jsonGenerator, serializerProvider);
        } else if (invalidateCacheMethod instanceof Podcast.Desktop.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.LatestTemplateInterface.v1_0#InvalidateFeaturedItemMethod");
            Podcast.Desktop.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Desktop.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethod) invalidateCacheMethod, jsonGenerator, serializerProvider);
        } else if (invalidateCacheMethod instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#InvalidateFeaturedItemMethod");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.InvalidateFeaturedItemMethod) invalidateCacheMethod, jsonGenerator, serializerProvider);
        } else {
            boolean z = invalidateCacheMethod instanceof Podcast.Touch.DetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(invalidateCacheMethod, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod");
                Podcast.Touch.DetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.DetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod) invalidateCacheMethod, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InvalidateCacheMethod invalidateCacheMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
