package com.amazon.avod.util.json;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public interface ParserMapFunction<T> {
    @Nonnull
    JacksonJsonParser<? extends T> apply(@Nonnull ObjectMapper objectMapper, @Nonnull JsonNode jsonNode) throws JsonContractException;

    public static class Builder<T, P extends JacksonJsonParser<? extends T>> {
        private final Map<Class<P>, Map<String, String>> mParserClassMap = Maps.newHashMap();
        private final Set<String> mFieldNameSet = Sets.newHashSet();

        public static <T> Builder<T, JacksonJsonParser<? extends T>> newBuilder() {
            return new Builder<>();
        }

        public Builder<T, P> addMatch(@Nonnull Class<? extends JacksonJsonParserBase<? extends T>> cls, @Nonnull String str, @Nonnull String str2) {
            this.mFieldNameSet.add(str);
            if (!this.mParserClassMap.containsKey(cls)) {
                this.mParserClassMap.put(cls, Maps.newHashMap());
            }
            this.mParserClassMap.get(cls).put(str, str2);
            return this;
        }

        public Builder<T, P> addMatch(@Nonnull Class<? extends JacksonJsonParserBase<? extends T>> cls, @Nonnull String str, @Nonnull String str2, @Nonnull String... strArr) {
            Preconditions.checkArgument(strArr.length % 2 == 0, "additionalFields must be of even length; ");
            addMatch(cls, str, str2);
            for (int i = 0; i < strArr.length; i += 2) {
                addMatch(cls, strArr[i], strArr[i + 1]);
            }
            return this;
        }

        public ParserMapFunction<T> build() {
            MatchKeyFactory matchKeyFactory = new MatchKeyFactory(ImmutableSet.copyOf((Collection) this.mFieldNameSet));
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (Map.Entry<Class<P>, Map<String, String>> entry : this.mParserClassMap.entrySet()) {
                builder.put(matchKeyFactory.forMap(entry.getValue()), entry.getKey());
            }
            return new ParserMapFunctionImpl(builder.build(), matchKeyFactory);
        }

        @ThreadSafe
        private static class ParserMapFunctionImpl<T, P extends JacksonJsonParser<? extends T>> implements ParserMapFunction<T> {
            private final MatchKeyFactory mMatchKeyFactory;
            private final ImmutableMap<String, Class<P>> mParserClassMap;
            private final AtomicReference<ImmutableMap<String, Supplier<P>>> mParserMap;

            private ParserMapFunctionImpl(@Nonnull ImmutableMap<String, Class<P>> immutableMap, @Nonnull MatchKeyFactory matchKeyFactory) {
                this.mParserMap = new AtomicReference<>();
                this.mParserClassMap = immutableMap;
                this.mMatchKeyFactory = matchKeyFactory;
            }

            @Override // com.amazon.avod.util.json.ParserMapFunction
            @Nonnull
            public JacksonJsonParser<? extends T> apply(@Nonnull ObjectMapper objectMapper, @Nonnull JsonNode jsonNode) throws JsonContractException {
                if (this.mParserMap.get() == null) {
                    CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.mParserMap, null, transform(objectMapper, this.mParserClassMap));
                }
                String strForJsonNode = this.mMatchKeyFactory.forJsonNode(jsonNode);
                Supplier<P> supplier = this.mParserMap.get().get(strForJsonNode);
                if (supplier == null) {
                    throw new JsonContractException(String.format("could not find parser match for %s", strForJsonNode));
                }
                return supplier.get();
            }

            private ImmutableMap<String, Supplier<P>> transform(@Nonnull ObjectMapper objectMapper, @Nonnull Map<String, Class<P>> map) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                for (Map.Entry<String, Class<P>> entry : map.entrySet()) {
                    builder.put(entry.getKey(), JacksonJsonParserBase.supplierFor(objectMapper, entry.getValue()));
                }
                return builder.build();
            }
        }

        @ThreadSafe
        private static class MatchKeyFactory {
            private final ThreadLocal<StringBuilder> mBuilder = new ThreadLocal<StringBuilder>() { // from class: com.amazon.avod.util.json.ParserMapFunction.Builder.MatchKeyFactory.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // java.lang.ThreadLocal
                public StringBuilder initialValue() {
                    return new StringBuilder();
                }
            };
            private final ImmutableSet<String> mFieldNameSet;

            public MatchKeyFactory(@Nonnull ImmutableSet<String> immutableSet) {
                this.mFieldNameSet = immutableSet;
            }

            @Nonnull
            public String forMap(@Nonnull Map<String, String> map) {
                this.mBuilder.get().setLength(0);
                UnmodifiableIterator<String> it = this.mFieldNameSet.iterator();
                while (it.hasNext()) {
                    tryAppendField(it.next(), map);
                }
                return this.mBuilder.get().toString().intern();
            }

            @Nonnull
            public String forJsonNode(@Nonnull JsonNode jsonNode) throws JsonContractException {
                this.mBuilder.get().setLength(0);
                UnmodifiableIterator<String> it = this.mFieldNameSet.iterator();
                while (it.hasNext()) {
                    tryAppendField(it.next(), jsonNode);
                }
                return this.mBuilder.get().toString().intern();
            }

            private void tryAppendField(@Nonnull String str, @Nonnull Map<String, String> map) {
                String str2 = map.get(str);
                if (str2 == null) {
                    return;
                }
                appendField(str, str2);
            }

            private void tryAppendField(@Nonnull String str, @Nonnull JsonNode jsonNode) throws JsonContractException {
                if (jsonNode.has(str)) {
                    JsonNode jsonNode2 = jsonNode.get(str);
                    if (!jsonNode2.isValueNode()) {
                        throw new JsonContractException(String.format("field name %s doesn't match to value (is referring to nested object/array)", str));
                    }
                    appendField(str, jsonNode2.asText());
                }
            }

            private void appendField(@Nonnull String str, @Nonnull String str2) {
                StringBuilder sb = this.mBuilder.get();
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(str);
                sb.append("=");
                sb.append(str2);
            }
        }
    }
}
