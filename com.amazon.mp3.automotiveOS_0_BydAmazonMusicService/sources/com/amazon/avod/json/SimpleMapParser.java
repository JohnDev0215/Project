package com.amazon.avod.json;

import com.amazon.avod.util.DLog;
import com.amazon.avod.util.json.JacksonJsonStreamParser;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonValidator;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleMapParser implements JacksonJsonStreamParser<ImmutableMap<String, String>> {
    private final Function<String, String> mValueTransform;

    public SimpleMapParser(@Nonnull Function<String, String> function) {
        this.mValueTransform = (Function) Preconditions.checkNotNull(function, "valueTransform");
    }

    @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
    @Nonnull
    public ImmutableMap<String, String> parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
        JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
        ImmutableMap.Builder builder = ImmutableMap.builder();
        ImmutableMap.Builder builder2 = ImmutableMap.builder();
        while (true) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (!JsonValidator.isInsideObject(jsonTokenNextToken)) {
                break;
            }
            if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                String strIntern = jsonParser.getCurrentName().intern();
                JsonToken jsonTokenNextToken2 = jsonParser.nextToken();
                if (jsonTokenNextToken2 == JsonToken.START_OBJECT) {
                    builder2.put(strIntern, parse(jsonParser));
                } else if (jsonTokenNextToken2 == JsonToken.START_ARRAY) {
                    while (true) {
                        String strNextTextValue = jsonParser.nextTextValue();
                        if (strNextTextValue != null) {
                            builder.put(strIntern + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + strNextTextValue, "true");
                        }
                    }
                } else {
                    String strApply = this.mValueTransform.apply(jsonParser.getText());
                    if (strApply == null) {
                        DLog.warnf("Skipping over null value for key %s", strIntern);
                    } else {
                        builder.put(strIntern, strApply);
                    }
                }
            }
        }
        ImmutableMap.Builder builder3 = ImmutableMap.builder();
        ImmutableMap immutableMapBuild = builder.build();
        builder3.putAll(immutableMapBuild);
        UnmodifiableIterator it = builder2.build().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            UnmodifiableIterator it2 = ((ImmutableMap) entry.getValue()).entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry entry2 = (Map.Entry) it2.next();
                String str2 = str + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + ((String) entry2.getKey());
                if (!immutableMapBuild.containsKey(str2)) {
                    builder3.put(str2, entry2.getValue());
                }
            }
        }
        return builder3.build();
    }
}
