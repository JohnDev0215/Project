package Podcast.EpisodeOptionsInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CarPresetElementSerializer extends JsonSerializer<CarPresetElement> {
    public static final CarPresetElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CarPresetElementSerializer carPresetElementSerializer = new CarPresetElementSerializer();
        INSTANCE = carPresetElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.EpisodeOptionsInterface.v1_0.CarPresetElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CarPresetElement.class, carPresetElementSerializer);
    }

    private CarPresetElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CarPresetElement carPresetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (carPresetElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        boolean z = carPresetElement instanceof PodcastShowCarPresetElement;
        jsonGenerator.writeStartObject();
        if (!z) {
            serializeFields(carPresetElement, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#PodcastShowCarPresetElement");
            PodcastShowCarPresetElementSerializer.INSTANCE.serializeFields((PodcastShowCarPresetElement) carPresetElement, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndObject();
        }
    }

    public void serializeFields(CarPresetElement carPresetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
