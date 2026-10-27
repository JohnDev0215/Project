package Podcast.FollowInterface.v1_0;

import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElementSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class RefinementBarElementSerializer extends JsonSerializer<RefinementBarElement> {
    public static final RefinementBarElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RefinementBarElementSerializer refinementBarElementSerializer = new RefinementBarElementSerializer();
        INSTANCE = refinementBarElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.FollowInterface.v1_0.RefinementBarElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RefinementBarElement.class, refinementBarElementSerializer);
    }

    private RefinementBarElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RefinementBarElement refinementBarElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (refinementBarElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(refinementBarElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RefinementBarElement refinementBarElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("sortingOptions");
        CheckBoxRefinementOptionElementSerializer.INSTANCE.serialize(refinementBarElement.getSortingOptions(), jsonGenerator, serializerProvider);
    }
}
