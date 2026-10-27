package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.google.common.hash.HashCode;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
public class HashCodeDeserializer extends FromStringDeserializer<HashCode> {
    private static final long serialVersionUID = 1;
    public static final HashCodeDeserializer std = new HashCodeDeserializer();

    public HashCodeDeserializer() {
        super(HashCode.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public HashCode _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
        return HashCode.fromString(str.toLowerCase(Locale.ENGLISH));
    }
}
