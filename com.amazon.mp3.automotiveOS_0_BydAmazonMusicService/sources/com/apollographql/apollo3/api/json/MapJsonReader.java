package com.apollographql.apollo3.api.json;

import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.internal.UtilsKt;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MapJsonReader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\r\u0018\u0000 92\u00020\u0001:\u00039:;B\u001d\u0012\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00020'¢\u0006\u0004\b7\u00108J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u0010\f\u001a\u00020\u0000H\u0016J\b\u0010\r\u001a\u00020\u0000H\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016J\b\u0010\u000f\u001a\u00020\u0000H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0016J\b\u0010 \u001a\u00020\u001fH\u0016J\b\u0010!\u001a\u00020\u0004H\u0016J\b\u0010\"\u001a\u00020\u0004H\u0016J\u0016\u0010%\u001a\u00020\u001b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00130#H\u0016J\b\u0010&\u001a\u00020\u0004H\u0016R%\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00020'8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u001c\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00101R\u0016\u00103\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0018\u00105\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00106¨\u0006<"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonReader;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "", "data", "", "push", "pop", "nextValue", "consumeValue", "any", "Lcom/apollographql/apollo3/api/json/JsonReader$Token;", "anyToToken", "beginArray", "endArray", "beginObject", "endObject", "", "hasNext", "peek", "", "nextName", "nextString", "nextBoolean", "", "nextNull", "", "nextDouble", "", "nextInt", "", "nextLong", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "nextNumber", "skipValue", "close", "", "names", "selectName", "rewind", "", "root", "Ljava/util/Map;", "getRoot", "()Ljava/util/Map;", "", "dataStack", "[Ljava/lang/Object;", "", "indexStack", "[I", "nameIndexStack", "stackSize", "I", "pendingName", "Ljava/lang/String;", "<init>", "(Ljava/util/Map;)V", "Companion", "Entry", "OrderedMap", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class MapJsonReader implements JsonReader {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Object[] dataStack;
    private final int[] indexStack;
    private final int[] nameIndexStack;
    private String pendingName;
    private final Map<String, Object> root;
    private int stackSize;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public MapJsonReader(Map<String, ? extends Object> root) {
        Intrinsics.checkNotNullParameter(root, "root");
        this.root = root;
        this.dataStack = new Object[256];
        this.indexStack = new int[256];
        this.nameIndexStack = new int[256];
        push(new OrderedMap(CollectionsKt__CollectionsJVMKt.listOf(new Entry("root", root))));
        this.pendingName = "root";
    }

    /* JADX INFO: compiled from: MapJsonReader.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonReader$OrderedMap;", "", "entries", "", "Lcom/apollographql/apollo3/api/json/MapJsonReader$Entry;", "(Ljava/util/List;)V", "getEntries", "()Ljava/util/List;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final class OrderedMap {
        private final List<Entry> entries;

        public OrderedMap(List<Entry> entries) {
            Intrinsics.checkNotNullParameter(entries, "entries");
            this.entries = entries;
        }

        public final List<Entry> getEntries() {
            return this.entries;
        }
    }

    /* JADX INFO: compiled from: MapJsonReader.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonReader$Entry;", "", "key", "", "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "getKey", "()Ljava/lang/String;", "getValue", "()Ljava/lang/Object;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final class Entry {
        private final String key;
        private final Object value;

        public Entry(String key, Object obj) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.key = key;
            this.value = obj;
        }

        public final String getKey() {
            return this.key;
        }

        public final Object getValue() {
            return this.value;
        }
    }

    private final void push(Object data) {
        int i = this.stackSize;
        if (!(i < 256)) {
            throw new IllegalStateException("Nesting too deep".toString());
        }
        this.dataStack[i] = data;
        this.indexStack[i] = 0;
        this.nameIndexStack[i] = 0;
        this.stackSize = i + 1;
        this.pendingName = null;
    }

    private final void pop() {
        Object[] objArr = this.dataStack;
        int i = this.stackSize;
        objArr[i] = null;
        this.stackSize = i - 1;
        this.pendingName = null;
    }

    private final Object nextValue() {
        Object[] objArr = this.dataStack;
        int i = this.stackSize;
        Object obj = objArr[i - 1];
        if (obj instanceof List) {
            return ((List) obj).get(this.indexStack[i - 1]);
        }
        if (obj instanceof OrderedMap) {
            if (!(this.pendingName != null)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            return ((OrderedMap) obj).getEntries().get(this.indexStack[this.stackSize - 1]).getValue();
        }
        throw new IllegalStateException("".toString());
    }

    private final void consumeValue() {
        this.pendingName = null;
        int[] iArr = this.indexStack;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public MapJsonReader beginArray() {
        Object objNextValue = nextValue();
        if (!(objNextValue instanceof List)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        consumeValue();
        push(objNextValue);
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public MapJsonReader endArray() {
        pop();
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public MapJsonReader beginObject() {
        Object objNextValue = nextValue();
        if (!(objNextValue instanceof Map)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        consumeValue();
        Set<Map.Entry> setEntrySet = ((Map) objNextValue).entrySet();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(setEntrySet, 10));
        for (Map.Entry entry : setEntrySet) {
            Object key = entry.getKey();
            if (key == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            arrayList.add(new Entry((String) key, entry.getValue()));
        }
        push(new OrderedMap(arrayList));
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public MapJsonReader endObject() {
        pop();
        return this;
    }

    private final JsonReader.Token anyToToken(Object any) {
        if (any == null) {
            return JsonReader.Token.NULL;
        }
        if (any instanceof List) {
            return JsonReader.Token.BEGIN_ARRAY;
        }
        if (any instanceof Map) {
            return JsonReader.Token.BEGIN_OBJECT;
        }
        if (!(any instanceof Integer) && !(any instanceof Long) && !(any instanceof Double)) {
            if (any instanceof String) {
                return JsonReader.Token.STRING;
            }
            if (any instanceof Boolean) {
                return JsonReader.Token.BOOLEAN;
            }
            throw new IllegalStateException(Intrinsics.stringPlus("Unsupported value ", any).toString());
        }
        return JsonReader.Token.NUMBER;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public boolean hasNext() {
        Object[] objArr = this.dataStack;
        int i = this.stackSize;
        Object obj = objArr[i - 1];
        if (obj instanceof List) {
            if (this.indexStack[i - 1] >= ((List) obj).size()) {
                return false;
            }
        } else if (obj instanceof OrderedMap) {
            if (this.indexStack[i - 1] >= ((OrderedMap) obj).getEntries().size()) {
                return false;
            }
        } else {
            throw new IllegalStateException("".toString());
        }
        return true;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public JsonReader.Token peek() {
        int i = this.stackSize;
        if (i == 1 && this.indexStack[0] == 1) {
            return JsonReader.Token.END_DOCUMENT;
        }
        Object obj = this.dataStack[i - 1];
        if (obj instanceof List) {
            List list = (List) obj;
            if (this.indexStack[i - 1] < list.size()) {
                return anyToToken(list.get(this.indexStack[this.stackSize - 1]));
            }
            return JsonReader.Token.END_ARRAY;
        }
        if (obj instanceof OrderedMap) {
            OrderedMap orderedMap = (OrderedMap) obj;
            if (this.indexStack[i - 1] < orderedMap.getEntries().size()) {
                String str = this.pendingName;
                if (str == null) {
                    return JsonReader.Token.NAME;
                }
                if (!Intrinsics.areEqual(str, orderedMap.getEntries().get(this.indexStack[this.stackSize - 1]).getKey())) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                return anyToToken(orderedMap.getEntries().get(this.indexStack[this.stackSize - 1]).getValue());
            }
            return JsonReader.Token.END_OBJECT;
        }
        throw new IllegalStateException("".toString());
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public String nextName() {
        Object obj = this.dataStack[this.stackSize - 1];
        if (!(obj instanceof OrderedMap)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!(this.pendingName == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        String key = ((OrderedMap) obj).getEntries().get(this.indexStack[this.stackSize - 1]).getKey();
        this.pendingName = key;
        Intrinsics.checkNotNull(key);
        return key;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public String nextString() {
        Object objNextValue = nextValue();
        String string = objNextValue == null ? null : objNextValue.toString();
        consumeValue();
        return string;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public boolean nextBoolean() {
        Object objNextValue = nextValue();
        if (objNextValue == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
        }
        boolean zBooleanValue = ((Boolean) objNextValue).booleanValue();
        consumeValue();
        return zBooleanValue;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public Void nextNull() {
        if (!(nextValue() == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        consumeValue();
        return null;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public double nextDouble() {
        double dDoubleValue;
        Object objNextValue = nextValue();
        if (objNextValue instanceof Integer) {
            dDoubleValue = ((Number) objNextValue).intValue();
        } else if (objNextValue instanceof Long) {
            dDoubleValue = UtilsKt.m2615LongToDoubleExact(((Number) objNextValue).longValue());
        } else if (objNextValue instanceof Double) {
            dDoubleValue = ((Number) objNextValue).doubleValue();
        } else if (objNextValue instanceof String) {
            dDoubleValue = Double.parseDouble((String) objNextValue);
        } else {
            if (!(objNextValue instanceof JsonNumber)) {
                throw new IllegalStateException(("Expected Double but got " + objNextValue + " instead").toString());
            }
            dDoubleValue = Double.parseDouble(((JsonNumber) objNextValue).getValue());
        }
        consumeValue();
        return dDoubleValue;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public int nextInt() {
        int iM2613DoubleToIntExact;
        Object objNextValue = nextValue();
        if (objNextValue instanceof Integer) {
            iM2613DoubleToIntExact = ((Number) objNextValue).intValue();
        } else if (objNextValue instanceof Long) {
            iM2613DoubleToIntExact = UtilsKt.m2616LongToIntExact(((Number) objNextValue).longValue());
        } else if (objNextValue instanceof Double) {
            iM2613DoubleToIntExact = UtilsKt.m2613DoubleToIntExact(((Number) objNextValue).doubleValue());
        } else if (objNextValue instanceof String) {
            iM2613DoubleToIntExact = Integer.parseInt((String) objNextValue);
        } else {
            if (!(objNextValue instanceof JsonNumber)) {
                throw new IllegalStateException(("Expected Int but got " + objNextValue + " instead").toString());
            }
            iM2613DoubleToIntExact = Integer.parseInt(((JsonNumber) objNextValue).getValue());
        }
        consumeValue();
        return iM2613DoubleToIntExact;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public long nextLong() {
        long jM2614DoubleToLongExact;
        Object objNextValue = nextValue();
        if (objNextValue instanceof Integer) {
            jM2614DoubleToLongExact = ((Number) objNextValue).intValue();
        } else if (objNextValue instanceof Long) {
            jM2614DoubleToLongExact = ((Number) objNextValue).longValue();
        } else if (objNextValue instanceof Double) {
            jM2614DoubleToLongExact = UtilsKt.m2614DoubleToLongExact(((Number) objNextValue).doubleValue());
        } else if (objNextValue instanceof String) {
            jM2614DoubleToLongExact = Long.parseLong((String) objNextValue);
        } else {
            if (!(objNextValue instanceof JsonNumber)) {
                throw new IllegalStateException(("Expected Int but got " + objNextValue + " instead").toString());
            }
            jM2614DoubleToLongExact = Long.parseLong(((JsonNumber) objNextValue).getValue());
        }
        consumeValue();
        return jM2614DoubleToLongExact;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public JsonNumber nextNumber() {
        JsonNumber jsonNumber;
        Object objNextValue = nextValue();
        if (objNextValue instanceof Integer ? true : objNextValue instanceof Long ? true : objNextValue instanceof Double) {
            jsonNumber = new JsonNumber(objNextValue.toString());
        } else if (objNextValue instanceof String) {
            jsonNumber = new JsonNumber((String) objNextValue);
        } else {
            if (!(objNextValue instanceof JsonNumber)) {
                throw new IllegalStateException(("Expected JsonNumber but got " + objNextValue + " instead").toString());
            }
            jsonNumber = (JsonNumber) objNextValue;
        }
        consumeValue();
        return jsonNumber;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public void skipValue() {
        consumeValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0053, code lost:
    
        skipValue();
     */
    @Override // com.apollographql.apollo3.api.json.JsonReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int selectName(java.util.List<java.lang.String> r7) {
        /*
            r6 = this;
            java.lang.String r0 = "names"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            boolean r0 = r7.isEmpty()
            r1 = -1
            if (r0 == 0) goto Ld
            return r1
        Ld:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L7e
            java.lang.String r0 = r6.nextName()
            int[] r2 = r6.nameIndexStack
            int r3 = r6.stackSize
            int r3 = r3 + (-1)
            r2 = r2[r3]
            java.lang.Object r3 = r7.get(r2)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
            r4 = 0
            if (r3 == 0) goto L47
            int[] r0 = r6.nameIndexStack
            int r1 = r6.stackSize
            int r3 = r1 + (-1)
            int r5 = r2 + 1
            r0[r3] = r5
            int r1 = r1 + (-1)
            r0 = r0[r1]
            int r7 = r7.size()
            if (r0 != r7) goto L46
            int[] r7 = r6.nameIndexStack
            int r0 = r6.stackSize
            int r0 = r0 + (-1)
            r7[r0] = r4
        L46:
            return r2
        L47:
            r3 = r2
        L48:
            int r3 = r3 + 1
            int r5 = r7.size()
            if (r3 != r5) goto L51
            r3 = r4
        L51:
            if (r3 != r2) goto L57
            r6.skipValue()
            goto Ld
        L57:
            java.lang.Object r5 = r7.get(r3)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
            if (r5 == 0) goto L48
            int[] r0 = r6.nameIndexStack
            int r1 = r6.stackSize
            int r2 = r1 + (-1)
            int r5 = r3 + 1
            r0[r2] = r5
            int r1 = r1 + (-1)
            r0 = r0[r1]
            int r7 = r7.size()
            if (r0 != r7) goto L7d
            int[] r7 = r6.nameIndexStack
            int r0 = r6.stackSize
            int r0 = r0 + (-1)
            r7[r0] = r4
        L7d:
            return r3
        L7e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.json.MapJsonReader.selectName(java.util.List):int");
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public void rewind() {
        int[] iArr = this.indexStack;
        int i = this.stackSize;
        iArr[i - 1] = 0;
        this.nameIndexStack[i - 1] = 0;
    }

    /* JADX INFO: compiled from: MapJsonReader.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonReader$Companion;", "", "()V", "buffer", "Lcom/apollographql/apollo3/api/json/MapJsonReader;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MapJsonReader buffer(JsonReader jsonReader) {
            Intrinsics.checkNotNullParameter(jsonReader, "<this>");
            if (jsonReader instanceof MapJsonReader) {
                return (MapJsonReader) jsonReader;
            }
            JsonReader.Token tokenPeek = jsonReader.peek();
            if (!(tokenPeek == JsonReader.Token.BEGIN_OBJECT)) {
                throw new IllegalStateException(("Failed to buffer json reader, expected `BEGIN_OBJECT` but found `" + tokenPeek + "` json token").toString());
            }
            Object any = JsonReaders.readAny(jsonReader);
            if (any == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
            }
            return new MapJsonReader((Map) any);
        }
    }
}
