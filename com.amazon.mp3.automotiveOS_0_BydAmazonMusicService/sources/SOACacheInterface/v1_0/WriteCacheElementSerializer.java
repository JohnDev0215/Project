package SOACacheInterface.v1_0;

import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FeaturedItemWriteCacheElement;
import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FeaturedItemWriteCacheElementSerializer;
import Podcast.JumpBackInInterface.v1_0.JumpBackInWriteCacheElement;
import Podcast.JumpBackInInterface.v1_0.JumpBackInWriteCacheElementSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCWriteCacheElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCWriteCacheElementSerializer;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class WriteCacheElementSerializer extends JsonSerializer<WriteCacheElement> {
    public static final WriteCacheElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WriteCacheElementSerializer writeCacheElementSerializer = new WriteCacheElementSerializer();
        INSTANCE = writeCacheElementSerializer;
        SimpleModule simpleModule = new SimpleModule("SOACacheInterface.v1_0.WriteCacheElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(WriteCacheElement.class, writeCacheElementSerializer);
    }

    private WriteCacheElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull WriteCacheElement writeCacheElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (writeCacheElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (writeCacheElement instanceof FeaturedItemWriteCacheElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#FeaturedItemWriteCacheElement");
            FeaturedItemWriteCacheElementSerializer.INSTANCE.serializeFields((FeaturedItemWriteCacheElement) writeCacheElement, jsonGenerator, serializerProvider);
        } else if (writeCacheElement instanceof Podcast.Desktop.LatestTemplateInterface.v1_0.FeaturedItemWriteCacheElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.LatestTemplateInterface.v1_0#FeaturedItemWriteCacheElement");
            Podcast.Desktop.LatestTemplateInterface.v1_0.FeaturedItemWriteCacheElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.LatestTemplateInterface.v1_0.FeaturedItemWriteCacheElement) writeCacheElement, jsonGenerator, serializerProvider);
        } else if (writeCacheElement instanceof Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedItemWriteCacheElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#FeaturedItemWriteCacheElement");
            Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedItemWriteCacheElementSerializer.INSTANCE.serializeFields((Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedItemWriteCacheElement) writeCacheElement, jsonGenerator, serializerProvider);
        } else if (writeCacheElement instanceof JumpBackInWriteCacheElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.JumpBackInInterface.v1_0#JumpBackInWriteCacheElement");
            JumpBackInWriteCacheElementSerializer.INSTANCE.serializeFields((JumpBackInWriteCacheElement) writeCacheElement, jsonGenerator, serializerProvider);
        } else if (writeCacheElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FeaturedItemWriteCacheElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#FeaturedItemWriteCacheElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FeaturedItemWriteCacheElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FeaturedItemWriteCacheElement) writeCacheElement, jsonGenerator, serializerProvider);
        } else if (writeCacheElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#FeaturedItemWriteCacheElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElement) writeCacheElement, jsonGenerator, serializerProvider);
        } else if (writeCacheElement instanceof Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedItemWriteCacheElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v2_0#FeaturedItemWriteCacheElement");
            Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedItemWriteCacheElementSerializer.INSTANCE.serializeFields((Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedItemWriteCacheElement) writeCacheElement, jsonGenerator, serializerProvider);
        } else if (writeCacheElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FeaturedItemWriteCacheElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#FeaturedItemWriteCacheElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FeaturedItemWriteCacheElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FeaturedItemWriteCacheElement) writeCacheElement, jsonGenerator, serializerProvider);
        } else if (writeCacheElement instanceof Podcast.Touch.LatestTemplateInterface.v1_0.FeaturedItemWriteCacheElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v1_0#FeaturedItemWriteCacheElement");
            Podcast.Touch.LatestTemplateInterface.v1_0.FeaturedItemWriteCacheElementSerializer.INSTANCE.serializeFields((Podcast.Touch.LatestTemplateInterface.v1_0.FeaturedItemWriteCacheElement) writeCacheElement, jsonGenerator, serializerProvider);
        } else {
            boolean z = writeCacheElement instanceof PTCWriteCacheElement;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(writeCacheElement, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PTCWriteCacheElement");
                PTCWriteCacheElementSerializer.INSTANCE.serializeFields((PTCWriteCacheElement) writeCacheElement, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(WriteCacheElement writeCacheElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onInvalidated");
        MethodsSerializer.INSTANCE.serialize(writeCacheElement.getOnInvalidated(), jsonGenerator, serializerProvider);
    }
}
