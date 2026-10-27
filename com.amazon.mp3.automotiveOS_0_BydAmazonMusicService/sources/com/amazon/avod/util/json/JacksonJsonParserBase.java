package com.amazon.avod.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class JacksonJsonParserBase<T> implements JacksonJsonParser<T> {

    @Nonnull
    protected final ObjectMapper mObjectMapper;

    @Nonnull
    public abstract T parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException;

    @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
    @Nonnull
    public abstract T parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException;

    public JacksonJsonParserBase(@Nonnull ObjectMapper objectMapper) {
        this.mObjectMapper = (ObjectMapper) Preconditions.checkNotNull(objectMapper, "ObjectMapper");
    }

    public static <T, P extends JacksonJsonParser<? extends T>> Supplier<P> supplierFor(final ObjectMapper objectMapper, final Class<P> cls) {
        return Suppliers.memoize(new Supplier<P>() { // from class: com.amazon.avod.util.json.JacksonJsonParserBase.1
            /* JADX WARN: Incorrect return type in method signature: ()TP; */
            @Override // com.google.common.base.Supplier
            public JacksonJsonParser get() {
                try {
                    return (JacksonJsonParser) cls.getConstructor(ObjectMapper.class).newInstance(objectMapper);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("should never happen; somehow the JacksonJsonParserBase class was misconfigured to not instantiate with an ObjectMapper", e);
                } catch (IllegalArgumentException e2) {
                    throw new IllegalStateException("should never happen; somehow the JacksonJsonParserBase class was misconfigured to not instantiate with an ObjectMapper", e2);
                } catch (InstantiationException e3) {
                    throw new IllegalStateException("should never happen; somehow the JacksonJsonParserBase class was misconfigured to not instantiate with an ObjectMapper", e3);
                } catch (NoSuchMethodException e4) {
                    throw new IllegalStateException("should never happen; somehow the JacksonJsonParserBase class was misconfigured to not instantiate with an ObjectMapper", e4);
                } catch (SecurityException e5) {
                    throw new IllegalStateException("should never happen; somehow the JacksonJsonParserBase class was misconfigured to not instantiate with an ObjectMapper", e5);
                } catch (InvocationTargetException e6) {
                    throw new IllegalStateException("should never happen; somehow the JacksonJsonParserBase class was misconfigured to not instantiate with an ObjectMapper", e6);
                }
            }
        });
    }
}
