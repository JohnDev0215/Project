package SOATemplateListInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class ShardSerializer extends JsonSerializer<Shard> {
    public static final ShardSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ShardSerializer shardSerializer = new ShardSerializer();
        INSTANCE = shardSerializer;
        SimpleModule simpleModule = new SimpleModule("SOATemplateListInterface.v1_0.ShardSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Shard.class, shardSerializer);
    }

    private ShardSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Shard shard, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (shard == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(shard, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Shard shard, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("create");
        TemplateSerializer templateSerializer = TemplateSerializer.INSTANCE;
        templateSerializer.serialize(shard.getCreate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("createAndBind");
        templateSerializer.serialize(shard.getCreateAndBind(), jsonGenerator, serializerProvider);
    }
}
