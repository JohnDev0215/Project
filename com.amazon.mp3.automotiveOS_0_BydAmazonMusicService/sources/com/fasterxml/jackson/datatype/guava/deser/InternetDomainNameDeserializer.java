package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.google.common.net.InternetDomainName;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public class InternetDomainNameDeserializer extends FromStringDeserializer<InternetDomainName> {
    private static final long serialVersionUID = 1;
    public static final InternetDomainNameDeserializer std = new InternetDomainNameDeserializer();

    public InternetDomainNameDeserializer() {
        super(InternetDomainName.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public InternetDomainName _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
        return InternetDomainName.from(str);
    }
}
