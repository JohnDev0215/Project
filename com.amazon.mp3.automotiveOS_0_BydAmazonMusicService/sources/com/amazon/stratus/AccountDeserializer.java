package com.amazon.stratus;

import com.amazon.cirrus.shared.model.TermsOfUseAcceptedListDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class AccountDeserializer extends JsonDeserializer<Account> {
    public static final AccountDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AccountDeserializer accountDeserializer = new AccountDeserializer();
        INSTANCE = accountDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.AccountDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Account.class, accountDeserializer);
    }

    private AccountDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Account deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("com.amazon.stratus#Account".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Account deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Account account = new Account();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("storageLocation".equals(currentName)) {
                account.setStorageLocation(StorageLocationDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("accountBenefits".equals(currentName)) {
                account.setAccountBenefits(BenefitMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("preferencesByNamespace".equals(currentName)) {
                account.setPreferencesByNamespace(PreferencesByNamespaceMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("accountVerified".equals(currentName)) {
                account.setAccountVerified(AccountVerifiedDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("customerBenefits".equals(currentName)) {
                account.setCustomerBenefits(CustomerBenefitMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("termsOfUse".equals(currentName)) {
                account.setTermsOfUse(TermsOfUseAcceptedListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("debugInfo".equals(currentName)) {
                account.setDebugInfo(DebugInfoMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("accountHistory".equals(currentName)) {
                account.setAccountHistory(AccountHistoryDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("accountInfo".equals(currentName)) {
                account.setAccountInfo(AccountInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("accountLocation".equals(currentName)) {
                account.setAccountLocation(AccountLocationDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("preferences".equals(currentName)) {
                account.setPreferences(PreferenceMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return account;
    }
}
