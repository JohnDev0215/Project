package com.apollographql.apollo3.api.json;

import com.apollographql.apollo3.api.Upload;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: MapJsonWriter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001%B\u0007¢\u0006\u0004\b#\u0010$J\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u001f\u0010\u0007\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u0004\u0018\u00010\u0002J\b\u0010\n\u001a\u00020\u0001H\u0016J\b\u0010\u000b\u001a\u00020\u0001H\u0016J\b\u0010\f\u001a\u00020\u0001H\u0016J\b\u0010\r\u001a\u00020\u0001H\u0016J\u0010\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u000eH\u0016J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0010H\u0016J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0011H\u0016J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0012H\u0016J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0013H\u0016J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0014H\u0016J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0000H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010\"\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006&"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonWriter;", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "", "other", "mergeWith", "T", "value", "valueInternal", "(Ljava/lang/Object;)Lcom/apollographql/apollo3/api/json/MapJsonWriter;", "root", "beginArray", "endArray", "beginObject", "endObject", "", "name", "", "", "", "", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "Lcom/apollographql/apollo3/api/Upload;", "nullValue", "", "close", "Ljava/lang/Object;", "rootSet", "Z", "", "Lcom/apollographql/apollo3/api/json/MapJsonWriter$State;", "stack", "Ljava/util/List;", "getPath", "()Ljava/lang/String;", "path", "<init>", "()V", "State", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class MapJsonWriter implements JsonWriter {
    private Object root;
    private boolean rootSet;
    private final List<State> stack = new ArrayList();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    /* JADX INFO: compiled from: MapJsonWriter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonWriter$State;", "", "()V", "List", "Map", "Lcom/apollographql/apollo3/api/json/MapJsonWriter$State$List;", "Lcom/apollographql/apollo3/api/json/MapJsonWriter$State$Map;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static abstract class State {
        public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: MapJsonWriter.kt */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0019\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonWriter$State$List;", "Lcom/apollographql/apollo3/api/json/MapJsonWriter$State;", "list", "", "", "(Ljava/util/List;)V", "getList", "()Ljava/util/List;", "toString", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class List extends State {
            private final java.util.List<Object> list;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public List(java.util.List<Object> list) {
                super(null);
                Intrinsics.checkNotNullParameter(list, "list");
                this.list = list;
            }

            public final java.util.List<Object> getList() {
                return this.list;
            }

            public String toString() {
                return "List (" + this.list.size() + ')';
            }
        }

        private State() {
        }

        /* JADX INFO: compiled from: MapJsonWriter.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\n\u0018\u00002\u00020\u0001B%\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u0004H\u0016R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonWriter$State$Map;", "Lcom/apollographql/apollo3/api/json/MapJsonWriter$State;", "map", "", "", "", "name", "(Ljava/util/Map;Ljava/lang/String;)V", "getMap", "()Ljava/util/Map;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "toString", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class Map extends State {
            private final java.util.Map<String, Object> map;
            private String name;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Map(java.util.Map<String, Object> map, String str) {
                super(null);
                Intrinsics.checkNotNullParameter(map, "map");
                this.map = map;
                this.name = str;
            }

            public final java.util.Map<String, Object> getMap() {
                return this.map;
            }

            public final String getName() {
                return this.name;
            }

            public final void setName(String str) {
                this.name = str;
            }

            public String toString() {
                return "Map (" + ((Object) this.name) + ')';
            }
        }
    }

    public final Object root() {
        if (!this.rootSet) {
            throw new IllegalStateException("Check failed.".toString());
        }
        return this.root;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter beginArray() {
        this.stack.add(new State.List(new ArrayList()));
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter endArray() {
        State stateRemove = this.stack.remove(r0.size() - 1);
        if (!(stateRemove instanceof State.List)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        valueInternal(((State.List) stateRemove).getList());
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter beginObject() {
        this.stack.add(new State.Map(new LinkedHashMap(), null));
        return this;
    }

    private final Object mergeWith(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj2 == null) {
            return obj;
        }
        if (obj instanceof List) {
            if (!(obj2 instanceof List)) {
                throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
            }
            List list = (List) obj;
            List list2 = (List) obj2;
            if (!(list.size() == list2.size())) {
                throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
            }
            IntRange indices = CollectionsKt__CollectionsKt.getIndices((Collection) obj);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10));
            Iterator<Integer> it = indices.iterator();
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                arrayList.add(mergeWith(list.get(iNextInt), list2.get(iNextInt)));
            }
            return arrayList;
        }
        if (obj instanceof Map) {
            if (!(obj2 instanceof Map)) {
                throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
            }
            Map map = (Map) obj;
            Map map2 = (Map) obj2;
            Set<String> setPlus = SetsKt___SetsKt.plus(map.keySet(), (Iterable) map2.keySet());
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(setPlus, 10));
            for (String str : setPlus) {
                arrayList2.add(TuplesKt.to(str, mergeWith(map.get(str), map2.get(str))));
            }
            return MapsKt__MapsKt.toMap(arrayList2);
        }
        if (Intrinsics.areEqual(obj, obj2)) {
            return obj;
        }
        throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter endObject() {
        State stateRemove = this.stack.remove(r0.size() - 1);
        if (!(stateRemove instanceof State.Map)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        valueInternal(((State.Map) stateRemove).getMap());
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter name(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        State state = (State) CollectionsKt___CollectionsKt.last((List) this.stack);
        if (!(state instanceof State.Map)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        State.Map map = (State.Map) state;
        if (!(map.getName() == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        map.setName(name);
        return this;
    }

    private final <T> MapJsonWriter valueInternal(T value) {
        State state = (State) CollectionsKt___CollectionsKt.lastOrNull((List) this.stack);
        if (state instanceof State.Map) {
            State.Map map = (State.Map) state;
            String name = map.getName();
            if (!(name != null)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (map.getMap().containsKey(name)) {
                map.getMap().put(name, mergeWith(map.getMap().get(name), value));
            } else {
                map.getMap().put(name, value);
            }
            map.setName(null);
        } else if (state instanceof State.List) {
            ((State.List) state).getList().add(value);
        } else {
            this.root = value;
            this.rootSet = true;
        }
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public String getPath() {
        String name;
        List<State> list = this.stack;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        for (State state : list) {
            if (state instanceof State.List) {
                name = String.valueOf(((State.List) state).getList().size());
            } else {
                if (!(state instanceof State.Map)) {
                    throw new NoWhenBranchMatchedException();
                }
                name = ((State.Map) state).getName();
                if (name == null) {
                    name = "?";
                }
            }
            arrayList.add(name);
        }
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList, ".", null, null, 0, null, null, 62, null);
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public MapJsonWriter value(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return valueInternal(value);
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public MapJsonWriter value(boolean value) {
        return valueInternal(Boolean.valueOf(value));
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public MapJsonWriter value(double value) {
        return valueInternal(Double.valueOf(value));
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public MapJsonWriter value(int value) {
        return valueInternal(Integer.valueOf(value));
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public MapJsonWriter value(long value) {
        return valueInternal(Long.valueOf(value));
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public MapJsonWriter value(JsonNumber value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return valueInternal(value);
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public MapJsonWriter value(Upload value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return valueInternal(null);
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public MapJsonWriter nullValue() {
        return valueInternal(null);
    }
}
