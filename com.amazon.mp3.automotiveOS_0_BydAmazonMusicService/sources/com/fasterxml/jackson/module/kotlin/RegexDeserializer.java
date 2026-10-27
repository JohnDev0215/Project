package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

/* JADX INFO: compiled from: KotlinDeserializers.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/RegexDeserializer;", "Lcom/fasterxml/jackson/databind/deser/std/StdDeserializer;", "Lkotlin/text/Regex;", "()V", "deserialize", "p", "Lcom/fasterxml/jackson/core/JsonParser;", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "jackson-module-kotlin"}, k = 1, mv = {1, 4, 1})
public final class RegexDeserializer extends StdDeserializer<Regex> {
    public static final RegexDeserializer INSTANCE = new RegexDeserializer();

    private RegexDeserializer() {
        super((Class<?>) Regex.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Regex deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Set setEmptySet;
        Intrinsics.checkNotNullParameter(p, "p");
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        JsonNode node = ctxt.readTree(p);
        Intrinsics.checkNotNullExpressionValue(node, "node");
        if (node.isTextual()) {
            String strAsText = node.asText();
            Intrinsics.checkNotNullExpressionValue(strAsText, "node.asText()");
            return new Regex(strAsText);
        }
        if (node.isObject()) {
            String pattern = node.get("pattern").asText();
            if (node.has("options")) {
                JsonNode optionsNode = node.get("options");
                Intrinsics.checkNotNullExpressionValue(optionsNode, "optionsNode");
                if (!optionsNode.isArray()) {
                    throw new IllegalStateException("Expected an array of strings for RegexOptions, but type was " + node.getNodeType());
                }
                Iterator<JsonNode> itElements = optionsNode.elements();
                Intrinsics.checkNotNullExpressionValue(itElements, "optionsNode.elements()");
                setEmptySet = SequencesKt___SequencesKt.toSet(SequencesKt___SequencesKt.map(SequencesKt__SequencesKt.asSequence(itElements), new Function1<JsonNode, RegexOption>() { // from class: com.fasterxml.jackson.module.kotlin.RegexDeserializer$deserialize$options$1
                    @Override // kotlin.jvm.functions.Function1
                    public final RegexOption invoke(JsonNode jsonNode) {
                        String strAsText2 = jsonNode.asText();
                        Intrinsics.checkNotNullExpressionValue(strAsText2, "it.asText()");
                        return RegexOption.valueOf(strAsText2);
                    }
                }));
            } else {
                setEmptySet = SetsKt__SetsKt.emptySet();
            }
            Intrinsics.checkNotNullExpressionValue(pattern, "pattern");
            return new Regex(pattern, (Set<? extends RegexOption>) setEmptySet);
        }
        throw new IllegalStateException("Expected a string or an object to deserialize a Regex, but type was " + node.getNodeType());
    }
}
