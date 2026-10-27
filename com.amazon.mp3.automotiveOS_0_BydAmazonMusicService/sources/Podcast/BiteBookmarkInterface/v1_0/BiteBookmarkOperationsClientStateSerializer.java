package Podcast.BiteBookmarkInterface.v1_0;

import SOAAppSyncInterface.v1_0.OperationsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class BiteBookmarkOperationsClientStateSerializer extends JsonSerializer<BiteBookmarkOperationsClientState> {
    public static final BiteBookmarkOperationsClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BiteBookmarkOperationsClientStateSerializer biteBookmarkOperationsClientStateSerializer = new BiteBookmarkOperationsClientStateSerializer();
        INSTANCE = biteBookmarkOperationsClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkOperationsClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BiteBookmarkOperationsClientState.class, biteBookmarkOperationsClientStateSerializer);
    }

    private BiteBookmarkOperationsClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BiteBookmarkOperationsClientState biteBookmarkOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (biteBookmarkOperationsClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(biteBookmarkOperationsClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BiteBookmarkOperationsClientState biteBookmarkOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("operations");
        OperationsSerializer.INSTANCE.serialize(biteBookmarkOperationsClientState.getOperations(), jsonGenerator, serializerProvider);
    }
}
