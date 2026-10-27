package SOACoreInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class AccountIdentitySerializer extends JsonSerializer<AccountIdentity> {
    public static final AccountIdentitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AccountIdentitySerializer accountIdentitySerializer = new AccountIdentitySerializer();
        INSTANCE = accountIdentitySerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.AccountIdentitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AccountIdentity.class, accountIdentitySerializer);
    }

    private AccountIdentitySerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AccountIdentity accountIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (accountIdentity == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(accountIdentity, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AccountIdentity accountIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(accountIdentity.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(accountIdentity.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("explicitLanguageFilterEnabled");
        SimpleSerializers.serializeBoolean(accountIdentity.isExplicitLanguageFilterEnabled(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("benefits");
        BenefitsSerializer.INSTANCE.serialize(accountIdentity.getBenefits(), jsonGenerator, serializerProvider);
    }
}
