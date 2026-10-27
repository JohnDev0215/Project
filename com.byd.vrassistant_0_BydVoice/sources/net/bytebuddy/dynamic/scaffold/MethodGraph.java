package net.bytebuddy.dynamic.scaffold;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.FilterableList;

/* JADX INFO: loaded from: classes3.dex */
public interface MethodGraph {
    NodeList listNodes();

    Node locate(MethodDescription.SignatureToken signatureToken);

    public enum Empty implements Linked, Compiler {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        public Linked compile(TypeDefinition typeDefinition, TypeDescription typeDescription) {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        public Linked compile(TypeDescription typeDescription) {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Linked
        public MethodGraph getInterfaceGraph(TypeDescription typeDescription) {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Linked
        public MethodGraph getSuperClassGraph() {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
        public Node locate(MethodDescription.SignatureToken signatureToken) {
            return Node.Unresolved.INSTANCE;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
        public NodeList listNodes() {
            return new NodeList(Collections.emptyList());
        }
    }

    public interface Linked extends MethodGraph {
        MethodGraph getInterfaceGraph(TypeDescription typeDescription);

        MethodGraph getSuperClassGraph();

        public static class Delegation implements Linked {
            private final Map<TypeDescription, MethodGraph> interfaceGraphs;
            private final MethodGraph methodGraph;
            private final MethodGraph superClassGraph;

            protected boolean canEqual(Object obj) {
                return obj instanceof Delegation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Delegation)) {
                    return false;
                }
                Delegation delegation = (Delegation) obj;
                if (!delegation.canEqual(this)) {
                    return false;
                }
                MethodGraph methodGraph = this.methodGraph;
                MethodGraph methodGraph2 = delegation.methodGraph;
                if (methodGraph != null ? !methodGraph.equals(methodGraph2) : methodGraph2 != null) {
                    return false;
                }
                MethodGraph superClassGraph = getSuperClassGraph();
                MethodGraph superClassGraph2 = delegation.getSuperClassGraph();
                if (superClassGraph != null ? !superClassGraph.equals(superClassGraph2) : superClassGraph2 != null) {
                    return false;
                }
                Map<TypeDescription, MethodGraph> map = this.interfaceGraphs;
                Map<TypeDescription, MethodGraph> map2 = delegation.interfaceGraphs;
                return map != null ? map.equals(map2) : map2 == null;
            }

            public int hashCode() {
                MethodGraph methodGraph = this.methodGraph;
                int iHashCode = methodGraph == null ? 43 : methodGraph.hashCode();
                MethodGraph superClassGraph = getSuperClassGraph();
                int iHashCode2 = ((iHashCode + 59) * 59) + (superClassGraph == null ? 43 : superClassGraph.hashCode());
                Map<TypeDescription, MethodGraph> map = this.interfaceGraphs;
                return (iHashCode2 * 59) + (map != null ? map.hashCode() : 43);
            }

            public Delegation(MethodGraph methodGraph, MethodGraph methodGraph2, Map<TypeDescription, MethodGraph> map) {
                this.methodGraph = methodGraph;
                this.superClassGraph = methodGraph2;
                this.interfaceGraphs = map;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Linked
            public MethodGraph getSuperClassGraph() {
                return this.superClassGraph;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Linked
            public MethodGraph getInterfaceGraph(TypeDescription typeDescription) {
                MethodGraph methodGraph = this.interfaceGraphs.get(typeDescription);
                return methodGraph == null ? Empty.INSTANCE : methodGraph;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
            public Node locate(MethodDescription.SignatureToken signatureToken) {
                return this.methodGraph.locate(signatureToken);
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
            public NodeList listNodes() {
                return this.methodGraph.listNodes();
            }
        }
    }

    public interface Node {
        Set<MethodDescription.TypeToken> getMethodTypes();

        MethodDescription getRepresentative();

        Sort getSort();

        Visibility getVisibility();

        public enum Sort {
            VISIBLE(true, true, true),
            RESOLVED(true, true, false),
            AMBIGUOUS(true, false, false),
            UNRESOLVED(false, false, false);

            private final boolean madeVisible;
            private final boolean resolved;
            private final boolean unique;

            Sort(boolean z, boolean z2, boolean z3) {
                this.resolved = z;
                this.unique = z2;
                this.madeVisible = z3;
            }

            public boolean isResolved() {
                return this.resolved;
            }

            public boolean isUnique() {
                return this.unique;
            }

            public boolean isMadeVisible() {
                return this.madeVisible;
            }
        }

        public enum Unresolved implements Node {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public Sort getSort() {
                return Sort.UNRESOLVED;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public MethodDescription getRepresentative() {
                throw new IllegalStateException("Cannot resolve the method of an illegal node");
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public Set<MethodDescription.TypeToken> getMethodTypes() {
                throw new IllegalStateException("Cannot resolve bridge method of an illegal node");
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public Visibility getVisibility() {
                throw new IllegalStateException("Cannot resolve visibility of an illegal node");
            }
        }

        public static class Simple implements Node {
            private final MethodDescription methodDescription;

            protected boolean canEqual(Object obj) {
                return obj instanceof Simple;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Simple)) {
                    return false;
                }
                Simple simple = (Simple) obj;
                if (!simple.canEqual(this)) {
                    return false;
                }
                MethodDescription methodDescription = this.methodDescription;
                MethodDescription methodDescription2 = simple.methodDescription;
                return methodDescription != null ? methodDescription.equals(methodDescription2) : methodDescription2 == null;
            }

            public int hashCode() {
                MethodDescription methodDescription = this.methodDescription;
                return 59 + (methodDescription == null ? 43 : methodDescription.hashCode());
            }

            public Simple(MethodDescription methodDescription) {
                this.methodDescription = methodDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public Sort getSort() {
                return Sort.RESOLVED;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public MethodDescription getRepresentative() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public Set<MethodDescription.TypeToken> getMethodTypes() {
                return Collections.emptySet();
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public Visibility getVisibility() {
                return this.methodDescription.getVisibility();
            }
        }
    }

    public interface Compiler {
        public static final Compiler DEFAULT = Default.forJavaHierarchy();

        Linked compile(TypeDefinition typeDefinition, TypeDescription typeDescription);

        Linked compile(TypeDescription typeDescription);

        public enum ForDeclaredMethods implements Compiler {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            public Linked compile(TypeDescription typeDescription) {
                return compile(typeDescription, typeDescription);
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            public Linked compile(TypeDefinition typeDefinition, TypeDescription typeDescription) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (MethodDescription methodDescription : typeDefinition.getDeclaredMethods().filter(ElementMatchers.isVirtual().and(ElementMatchers.not(ElementMatchers.isBridge())).and(ElementMatchers.isVisibleTo(typeDescription)))) {
                    linkedHashMap.put(methodDescription.asSignatureToken(), new Node.Simple(methodDescription));
                }
                return new Linked.Delegation(new Simple(linkedHashMap), Empty.INSTANCE, Collections.emptyMap());
            }
        }

        public static abstract class AbstractBase implements Compiler {
            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            public Linked compile(TypeDescription typeDescription) {
                return compile(typeDescription, typeDescription);
            }
        }

        public static class Default<T> extends AbstractBase {
            private final Harmonizer<T> harmonizer;
            private final Merger merger;
            private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

            protected boolean canEqual(Object obj) {
                return obj instanceof Default;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Default)) {
                    return false;
                }
                Default r5 = (Default) obj;
                if (!r5.canEqual(this)) {
                    return false;
                }
                Harmonizer<T> harmonizer = this.harmonizer;
                Harmonizer<T> harmonizer2 = r5.harmonizer;
                if (harmonizer != null ? !harmonizer.equals(harmonizer2) : harmonizer2 != null) {
                    return false;
                }
                Merger merger = this.merger;
                Merger merger2 = r5.merger;
                if (merger != null ? !merger.equals(merger2) : merger2 != null) {
                    return false;
                }
                TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor = this.visitor;
                TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor2 = r5.visitor;
                return visitor != null ? visitor.equals(visitor2) : visitor2 == null;
            }

            public int hashCode() {
                Harmonizer<T> harmonizer = this.harmonizer;
                int iHashCode = harmonizer == null ? 43 : harmonizer.hashCode();
                Merger merger = this.merger;
                int iHashCode2 = ((iHashCode + 59) * 59) + (merger == null ? 43 : merger.hashCode());
                TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor = this.visitor;
                return (iHashCode2 * 59) + (visitor != null ? visitor.hashCode() : 43);
            }

            protected Default(Harmonizer<T> harmonizer, Merger merger, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
                this.harmonizer = harmonizer;
                this.merger = merger;
                this.visitor = visitor;
            }

            public static <S> Compiler of(Harmonizer<S> harmonizer, Merger merger) {
                return new Default(harmonizer, merger, TypeDescription.Generic.Visitor.Reifying.INITIATING);
            }

            public static <S> Compiler of(Harmonizer<S> harmonizer, Merger merger, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
                return new Default(harmonizer, merger, visitor);
            }

            public static Compiler forJavaHierarchy() {
                return of(Harmonizer.ForJavaMethod.INSTANCE, Merger.Directional.LEFT);
            }

            public static Compiler forJVMHierarchy() {
                return of(Harmonizer.ForJVMMethod.INSTANCE, Merger.Directional.LEFT);
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            public Linked compile(TypeDefinition typeDefinition, TypeDescription typeDescription) {
                Map<TypeDefinition, Key.Store<T>> map = new HashMap<>();
                Key.Store<T> storeDoAnalyze = doAnalyze(typeDefinition, map, ElementMatchers.isVirtual().and(ElementMatchers.isVisibleTo(typeDescription)));
                TypeDescription.Generic superClass = typeDefinition.getSuperClass();
                TypeList.Generic interfaces = typeDefinition.getInterfaces();
                HashMap map2 = new HashMap();
                for (TypeDescription.Generic generic : interfaces) {
                    map2.put(generic.asErasure(), map.get(generic).asGraph(this.merger));
                }
                return new Linked.Delegation(storeDoAnalyze.asGraph(this.merger), superClass == null ? Empty.INSTANCE : map.get(superClass).asGraph(this.merger), map2);
            }

            protected Key.Store<T> analyze(TypeDefinition typeDefinition, TypeDefinition typeDefinition2, Map<TypeDefinition, Key.Store<T>> map, ElementMatcher<? super MethodDescription> elementMatcher) {
                Key.Store<T> store = map.get(typeDefinition2);
                if (store != null) {
                    return store;
                }
                Key.Store<T> storeDoAnalyze = doAnalyze(typeDefinition, map, elementMatcher);
                map.put(typeDefinition2, storeDoAnalyze);
                return storeDoAnalyze;
            }

            protected Key.Store<T> analyzeNullable(TypeDescription.Generic generic, Map<TypeDefinition, Key.Store<T>> map, ElementMatcher<? super MethodDescription> elementMatcher) {
                if (generic == null) {
                    return new Key.Store<>();
                }
                return analyze((TypeDefinition) generic.accept(this.visitor), generic, map, elementMatcher);
            }

            protected Key.Store<T> doAnalyze(TypeDefinition typeDefinition, Map<TypeDefinition, Key.Store<T>> map, ElementMatcher<? super MethodDescription> elementMatcher) {
                Key.Store<T> storeAnalyzeNullable = analyzeNullable(typeDefinition.getSuperClass(), map, elementMatcher);
                Key.Store<T> store = new Key.Store<>();
                for (TypeDescription.Generic generic : typeDefinition.getInterfaces()) {
                    store = store.combineWith(analyze((TypeDefinition) generic.accept(this.visitor), generic, map, elementMatcher));
                }
                Key.Store<T> storeInject = storeAnalyzeNullable.inject(store);
                Iterator it = typeDefinition.getDeclaredMethods().filter(elementMatcher).iterator();
                while (it.hasNext()) {
                    storeInject = storeInject.registerTopLevel((MethodDescription) it.next(), this.harmonizer);
                }
                return storeInject;
            }

            public interface Harmonizer<S> {
                S harmonize(MethodDescription.TypeToken typeToken);

                public enum ForJavaMethod implements Harmonizer<Token> {
                    INSTANCE;

                    @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Harmonizer
                    public Token harmonize(MethodDescription.TypeToken typeToken) {
                        return new Token(typeToken);
                    }

                    protected static class Token {
                        private final MethodDescription.TypeToken typeToken;

                        protected Token(MethodDescription.TypeToken typeToken) {
                            this.typeToken = typeToken;
                        }

                        public boolean equals(Object obj) {
                            return this == obj || ((obj instanceof Token) && this.typeToken.getParameterTypes().equals(((Token) obj).typeToken.getParameterTypes()));
                        }

                        public int hashCode() {
                            return this.typeToken.getParameterTypes().hashCode();
                        }

                        public String toString() {
                            return this.typeToken.getParameterTypes().toString();
                        }
                    }
                }

                public enum ForJVMMethod implements Harmonizer<Token> {
                    INSTANCE;

                    @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Harmonizer
                    public Token harmonize(MethodDescription.TypeToken typeToken) {
                        return new Token(typeToken);
                    }

                    protected static class Token {
                        private final MethodDescription.TypeToken typeToken;

                        public Token(MethodDescription.TypeToken typeToken) {
                            this.typeToken = typeToken;
                        }

                        public boolean equals(Object obj) {
                            if (this != obj) {
                                if (obj instanceof Token) {
                                    Token token = (Token) obj;
                                    if (!this.typeToken.getReturnType().equals(token.typeToken.getReturnType()) || !this.typeToken.getParameterTypes().equals(token.typeToken.getParameterTypes())) {
                                    }
                                }
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            return this.typeToken.getReturnType().hashCode() + (this.typeToken.getParameterTypes().hashCode() * 31);
                        }

                        public String toString() {
                            return this.typeToken.toString();
                        }
                    }
                }
            }

            public interface Merger {
                MethodDescription merge(MethodDescription methodDescription, MethodDescription methodDescription2);

                public enum Directional implements Merger {
                    LEFT(true),
                    RIGHT(false);

                    private final boolean left;

                    Directional(boolean z) {
                        this.left = z;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Merger
                    public MethodDescription merge(MethodDescription methodDescription, MethodDescription methodDescription2) {
                        return this.left ? methodDescription : methodDescription2;
                    }
                }
            }

            protected static abstract class Key<S> {
                protected final String internalName;

                protected abstract Set<S> getIdentifiers();

                protected Key(String str) {
                    this.internalName = str;
                }

                public boolean equals(Object obj) {
                    if (obj != this) {
                        if (obj instanceof Key) {
                            Key key = (Key) obj;
                            if (!this.internalName.equals(key.internalName) || Collections.disjoint(getIdentifiers(), key.getIdentifiers())) {
                            }
                        }
                        return false;
                    }
                    return true;
                }

                public int hashCode() {
                    return this.internalName.hashCode();
                }

                protected static class Harmonized<V> extends Key<V> {
                    private final Map<V, Set<MethodDescription.TypeToken>> identifiers;

                    protected Harmonized(String str, Map<V, Set<MethodDescription.TypeToken>> map) {
                        super(str);
                        this.identifiers = map;
                    }

                    protected static <Q> Harmonized<Q> of(MethodDescription methodDescription, Harmonizer<Q> harmonizer) {
                        return new Harmonized<>(methodDescription.getInternalName(), Collections.singletonMap(harmonizer.harmonize(methodDescription.asTypeToken()), Collections.emptySet()));
                    }

                    protected Detached detach(MethodDescription.TypeToken typeToken) {
                        HashSet hashSet = new HashSet();
                        Iterator<Set<MethodDescription.TypeToken>> it = this.identifiers.values().iterator();
                        while (it.hasNext()) {
                            hashSet.addAll(it.next());
                        }
                        hashSet.add(typeToken);
                        return new Detached(this.internalName, hashSet);
                    }

                    protected Harmonized<V> combineWith(Harmonized<V> harmonized) {
                        HashMap map = new HashMap(this.identifiers);
                        for (Map.Entry<V, Set<MethodDescription.TypeToken>> entry : harmonized.identifiers.entrySet()) {
                            Set set = (Set) map.get(entry.getKey());
                            if (set == null) {
                                map.put(entry.getKey(), entry.getValue());
                            } else {
                                HashSet hashSet = new HashSet(set);
                                hashSet.addAll(entry.getValue());
                                map.put(entry.getKey(), hashSet);
                            }
                        }
                        return new Harmonized<>(this.internalName, map);
                    }

                    protected Harmonized<V> extend(MethodDescription.InDefinedShape inDefinedShape, Harmonizer<V> harmonizer) {
                        HashMap map = new HashMap(this.identifiers);
                        MethodDescription.TypeToken typeTokenAsTypeToken = inDefinedShape.asTypeToken();
                        V vHarmonize = harmonizer.harmonize(typeTokenAsTypeToken);
                        Set set = (Set) map.get(vHarmonize);
                        if (set == null) {
                            map.put(vHarmonize, Collections.singleton(typeTokenAsTypeToken));
                        } else {
                            HashSet hashSet = new HashSet(set);
                            hashSet.add(typeTokenAsTypeToken);
                            map.put(vHarmonize, hashSet);
                        }
                        return new Harmonized<>(this.internalName, map);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key
                    protected Set<V> getIdentifiers() {
                        return this.identifiers.keySet();
                    }
                }

                protected static class Detached extends Key<MethodDescription.TypeToken> {
                    private final Set<MethodDescription.TypeToken> identifiers;

                    protected Detached(String str, Set<MethodDescription.TypeToken> set) {
                        super(str);
                        this.identifiers = set;
                    }

                    protected static Detached of(MethodDescription.SignatureToken signatureToken) {
                        return new Detached(signatureToken.getName(), Collections.singleton(signatureToken.asTypeToken()));
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key
                    protected Set<MethodDescription.TypeToken> getIdentifiers() {
                        return this.identifiers;
                    }
                }

                protected static class Store<V> {
                    private final LinkedHashMap<Harmonized<V>, Entry<V>> entries;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Store;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Store)) {
                            return false;
                        }
                        Store store = (Store) obj;
                        if (!store.canEqual(this)) {
                            return false;
                        }
                        LinkedHashMap<Harmonized<V>, Entry<V>> linkedHashMap = this.entries;
                        LinkedHashMap<Harmonized<V>, Entry<V>> linkedHashMap2 = store.entries;
                        return linkedHashMap != null ? linkedHashMap.equals(linkedHashMap2) : linkedHashMap2 == null;
                    }

                    public int hashCode() {
                        LinkedHashMap<Harmonized<V>, Entry<V>> linkedHashMap = this.entries;
                        return 59 + (linkedHashMap == null ? 43 : linkedHashMap.hashCode());
                    }

                    protected Store() {
                        this(new LinkedHashMap());
                    }

                    private Store(LinkedHashMap<Harmonized<V>, Entry<V>> linkedHashMap) {
                        this.entries = linkedHashMap;
                    }

                    private static <W> Entry<W> combine(Entry<W> entry, Entry<W> entry2) {
                        Set<MethodDescription> candidates = entry.getCandidates();
                        Set<MethodDescription> candidates2 = entry2.getCandidates();
                        LinkedHashSet linkedHashSet = new LinkedHashSet(candidates.size() + candidates2.size());
                        linkedHashSet.addAll(candidates);
                        linkedHashSet.addAll(candidates2);
                        for (MethodDescription methodDescription : candidates) {
                            TypeDescription typeDescriptionAsErasure = methodDescription.getDeclaringType().asErasure();
                            Iterator<MethodDescription> it = candidates2.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    MethodDescription next = it.next();
                                    TypeDescription typeDescriptionAsErasure2 = next.getDeclaringType().asErasure();
                                    if (!typeDescriptionAsErasure.equals(typeDescriptionAsErasure2)) {
                                        if (typeDescriptionAsErasure.isAssignableTo(typeDescriptionAsErasure2)) {
                                            linkedHashSet.remove(next);
                                            break;
                                        }
                                        if (typeDescriptionAsErasure.isAssignableFrom(typeDescriptionAsErasure2)) {
                                            linkedHashSet.remove(methodDescription);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        Harmonized<W> harmonizedCombineWith = entry.getKey().combineWith(entry2.getKey());
                        Visibility visibilityExpandTo = entry.getVisibility().expandTo(entry2.getVisibility());
                        return linkedHashSet.size() == 1 ? new Entry.Resolved(harmonizedCombineWith, (MethodDescription) linkedHashSet.iterator().next(), visibilityExpandTo, false) : new Entry.Ambiguous(harmonizedCombineWith, linkedHashSet, visibilityExpandTo);
                    }

                    protected Store<V> registerTopLevel(MethodDescription methodDescription, Harmonizer<V> harmonizer) {
                        Harmonized harmonizedOf = Harmonized.of(methodDescription, harmonizer);
                        LinkedHashMap linkedHashMap = new LinkedHashMap(this.entries);
                        Entry initial = (Entry) linkedHashMap.remove(harmonizedOf);
                        if (initial == null) {
                            initial = new Entry.Initial(harmonizedOf);
                        }
                        Entry entryExtendBy = initial.extendBy(methodDescription, harmonizer);
                        linkedHashMap.put(entryExtendBy.getKey(), entryExtendBy);
                        return new Store<>(linkedHashMap);
                    }

                    protected Store<V> combineWith(Store<V> store) {
                        Iterator<Entry<V>> it = store.entries.values().iterator();
                        Store<V> storeCombineWith = this;
                        while (it.hasNext()) {
                            storeCombineWith = storeCombineWith.combineWith(it.next());
                        }
                        return storeCombineWith;
                    }

                    protected Store<V> combineWith(Entry<V> entry) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap(this.entries);
                        Entry entry2 = (Entry) linkedHashMap.remove(entry.getKey());
                        if (entry2 != null) {
                            entry = combine(entry2, entry);
                        }
                        linkedHashMap.put(entry.getKey(), entry);
                        return new Store<>(linkedHashMap);
                    }

                    protected Store<V> inject(Store<V> store) {
                        Iterator<Entry<V>> it = store.entries.values().iterator();
                        Store<V> storeInject = this;
                        while (it.hasNext()) {
                            storeInject = storeInject.inject(it.next());
                        }
                        return storeInject;
                    }

                    protected Store<V> inject(Entry<V> entry) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap(this.entries);
                        Entry entry2 = (Entry) linkedHashMap.remove(entry.getKey());
                        if (entry2 != null) {
                            entry = entry2.inject(entry.getKey(), entry.getVisibility());
                        }
                        linkedHashMap.put(entry.getKey(), entry);
                        return new Store<>(linkedHashMap);
                    }

                    protected MethodGraph asGraph(Merger merger) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        for (Entry<V> entry : this.entries.values()) {
                            Node nodeAsNode = entry.asNode(merger);
                            linkedHashMap.put(entry.getKey().detach(nodeAsNode.getRepresentative().asTypeToken()), nodeAsNode);
                        }
                        return new Graph(linkedHashMap);
                    }

                    protected interface Entry<W> {
                        Node asNode(Merger merger);

                        Entry<W> extendBy(MethodDescription methodDescription, Harmonizer<W> harmonizer);

                        Set<MethodDescription> getCandidates();

                        Harmonized<W> getKey();

                        Visibility getVisibility();

                        Entry<W> inject(Harmonized<W> harmonized, Visibility visibility);

                        public static class Initial<U> implements Entry<U> {
                            private final Harmonized<U> key;

                            protected Initial(Harmonized<U> harmonized) {
                                this.key = harmonized;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Harmonized<U> getKey() {
                                throw new IllegalStateException("Cannot extract key from initial entry:" + this);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Set<MethodDescription> getCandidates() {
                                throw new IllegalStateException("Cannot extract method from initial entry:" + this);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Visibility getVisibility() {
                                throw new IllegalStateException("Cannot extract visibility from initial entry:" + this);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> extendBy(MethodDescription methodDescription, Harmonizer<U> harmonizer) {
                                return new Resolved(this.key.extend(methodDescription.asDefined(), harmonizer), methodDescription, methodDescription.getVisibility(), false);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> inject(Harmonized<U> harmonized, Visibility visibility) {
                                throw new IllegalStateException("Cannot inject into initial entry without a registered method: " + this);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Node asNode(Merger merger) {
                                throw new IllegalStateException("Cannot transform initial entry without a registered method: " + this);
                            }

                            public boolean equals(Object obj) {
                                return this == obj || (obj != null && getClass() == obj.getClass() && this.key.equals(((Initial) obj).key));
                            }

                            public int hashCode() {
                                return this.key.hashCode();
                            }
                        }

                        public static class Resolved<U> implements Entry<U> {
                            private static final int MADE_VISIBLE = 5;
                            private static final boolean NOT_MADE_VISIBLE = false;
                            private final Harmonized<U> key;
                            private final boolean madeVisible;
                            private final MethodDescription methodDescription;
                            private final Visibility visibility;

                            protected boolean canEqual(Object obj) {
                                return obj instanceof Resolved;
                            }

                            public boolean equals(Object obj) {
                                if (obj == this) {
                                    return true;
                                }
                                if (!(obj instanceof Resolved)) {
                                    return false;
                                }
                                Resolved resolved = (Resolved) obj;
                                if (!resolved.canEqual(this)) {
                                    return false;
                                }
                                Harmonized<U> key = getKey();
                                Harmonized<U> key2 = resolved.getKey();
                                if (key != null ? !key.equals(key2) : key2 != null) {
                                    return false;
                                }
                                MethodDescription methodDescription = this.methodDescription;
                                MethodDescription methodDescription2 = resolved.methodDescription;
                                if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                                    return false;
                                }
                                Visibility visibility = getVisibility();
                                Visibility visibility2 = resolved.getVisibility();
                                if (visibility != null ? visibility.equals(visibility2) : visibility2 == null) {
                                    return this.madeVisible == resolved.madeVisible;
                                }
                                return false;
                            }

                            public int hashCode() {
                                Harmonized<U> key = getKey();
                                int iHashCode = key == null ? 43 : key.hashCode();
                                MethodDescription methodDescription = this.methodDescription;
                                int iHashCode2 = ((iHashCode + 59) * 59) + (methodDescription == null ? 43 : methodDescription.hashCode());
                                Visibility visibility = getVisibility();
                                return (((iHashCode2 * 59) + (visibility != null ? visibility.hashCode() : 43)) * 59) + (this.madeVisible ? 79 : 97);
                            }

                            protected Resolved(Harmonized<U> harmonized, MethodDescription methodDescription, Visibility visibility, boolean z) {
                                this.key = harmonized;
                                this.methodDescription = methodDescription;
                                this.visibility = visibility;
                                this.madeVisible = z;
                            }

                            private static <V> Entry<V> of(Harmonized<V> harmonized, MethodDescription methodDescription, MethodDescription methodDescription2, Visibility visibility) {
                                Visibility visibilityExpandTo = visibility.expandTo(methodDescription2.getVisibility()).expandTo(methodDescription.getVisibility());
                                if (methodDescription.isBridge()) {
                                    return new Resolved(harmonized, methodDescription2, visibilityExpandTo, (methodDescription2.getDeclaringType().getModifiers() & 5) == 0);
                                }
                                return new Resolved(harmonized, methodDescription, visibilityExpandTo, false);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Harmonized<U> getKey() {
                                return this.key;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Set<MethodDescription> getCandidates() {
                                return Collections.singleton(this.methodDescription);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Visibility getVisibility() {
                                return this.visibility;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> extendBy(MethodDescription methodDescription, Harmonizer<U> harmonizer) {
                                Harmonized<U> harmonizedExtend = this.key.extend(methodDescription.asDefined(), harmonizer);
                                Visibility visibilityExpandTo = this.visibility.expandTo(methodDescription.getVisibility());
                                if (methodDescription.getDeclaringType().equals(this.methodDescription.getDeclaringType())) {
                                    return Ambiguous.of(harmonizedExtend, methodDescription, this.methodDescription, visibilityExpandTo);
                                }
                                return of(harmonizedExtend, methodDescription, this.methodDescription, visibilityExpandTo);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> inject(Harmonized<U> harmonized, Visibility visibility) {
                                return new Resolved(this.key.combineWith(harmonized), this.methodDescription, this.visibility.expandTo(visibility), this.madeVisible);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Node asNode(Merger merger) {
                                return new Node(this.key.detach(this.methodDescription.asTypeToken()), this.methodDescription, this.visibility, this.madeVisible);
                            }

                            protected static class Node implements Node {
                                private final Detached key;
                                private final MethodDescription methodDescription;
                                private final Visibility visibility;
                                private final boolean visible;

                                protected boolean canEqual(Object obj) {
                                    return obj instanceof Node;
                                }

                                public boolean equals(Object obj) {
                                    if (obj == this) {
                                        return true;
                                    }
                                    if (!(obj instanceof Node)) {
                                        return false;
                                    }
                                    Node node = (Node) obj;
                                    if (!node.canEqual(this)) {
                                        return false;
                                    }
                                    Detached detached = this.key;
                                    Detached detached2 = node.key;
                                    if (detached != null ? !detached.equals(detached2) : detached2 != null) {
                                        return false;
                                    }
                                    MethodDescription methodDescription = this.methodDescription;
                                    MethodDescription methodDescription2 = node.methodDescription;
                                    if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                                        return false;
                                    }
                                    Visibility visibility = getVisibility();
                                    Visibility visibility2 = node.getVisibility();
                                    if (visibility != null ? visibility.equals(visibility2) : visibility2 == null) {
                                        return this.visible == node.visible;
                                    }
                                    return false;
                                }

                                public int hashCode() {
                                    Detached detached = this.key;
                                    int iHashCode = detached == null ? 43 : detached.hashCode();
                                    MethodDescription methodDescription = this.methodDescription;
                                    int iHashCode2 = ((iHashCode + 59) * 59) + (methodDescription == null ? 43 : methodDescription.hashCode());
                                    Visibility visibility = getVisibility();
                                    return (((iHashCode2 * 59) + (visibility != null ? visibility.hashCode() : 43)) * 59) + (this.visible ? 79 : 97);
                                }

                                protected Node(Detached detached, MethodDescription methodDescription, Visibility visibility, boolean z) {
                                    this.key = detached;
                                    this.methodDescription = methodDescription;
                                    this.visibility = visibility;
                                    this.visible = z;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Node.Sort getSort() {
                                    return this.visible ? Node.Sort.VISIBLE : Node.Sort.RESOLVED;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public MethodDescription getRepresentative() {
                                    return this.methodDescription;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Set<MethodDescription.TypeToken> getMethodTypes() {
                                    return this.key.getIdentifiers();
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Visibility getVisibility() {
                                    return this.visibility;
                                }
                            }
                        }

                        public static class Ambiguous<U> implements Entry<U> {
                            private final Harmonized<U> key;
                            private final LinkedHashSet<MethodDescription> methodDescriptions;
                            private final Visibility visibility;

                            protected boolean canEqual(Object obj) {
                                return obj instanceof Ambiguous;
                            }

                            public boolean equals(Object obj) {
                                if (obj == this) {
                                    return true;
                                }
                                if (!(obj instanceof Ambiguous)) {
                                    return false;
                                }
                                Ambiguous ambiguous = (Ambiguous) obj;
                                if (!ambiguous.canEqual(this)) {
                                    return false;
                                }
                                Harmonized<U> key = getKey();
                                Harmonized<U> key2 = ambiguous.getKey();
                                if (key != null ? !key.equals(key2) : key2 != null) {
                                    return false;
                                }
                                LinkedHashSet<MethodDescription> linkedHashSet = this.methodDescriptions;
                                LinkedHashSet<MethodDescription> linkedHashSet2 = ambiguous.methodDescriptions;
                                if (linkedHashSet != null ? !linkedHashSet.equals(linkedHashSet2) : linkedHashSet2 != null) {
                                    return false;
                                }
                                Visibility visibility = getVisibility();
                                Visibility visibility2 = ambiguous.getVisibility();
                                return visibility != null ? visibility.equals(visibility2) : visibility2 == null;
                            }

                            public int hashCode() {
                                Harmonized<U> key = getKey();
                                int iHashCode = key == null ? 43 : key.hashCode();
                                LinkedHashSet<MethodDescription> linkedHashSet = this.methodDescriptions;
                                int iHashCode2 = ((iHashCode + 59) * 59) + (linkedHashSet == null ? 43 : linkedHashSet.hashCode());
                                Visibility visibility = getVisibility();
                                return (iHashCode2 * 59) + (visibility != null ? visibility.hashCode() : 43);
                            }

                            protected Ambiguous(Harmonized<U> harmonized, LinkedHashSet<MethodDescription> linkedHashSet, Visibility visibility) {
                                this.key = harmonized;
                                this.methodDescriptions = linkedHashSet;
                                this.visibility = visibility;
                            }

                            protected static <Q> Entry<Q> of(Harmonized<Q> harmonized, MethodDescription methodDescription, MethodDescription methodDescription2, Visibility visibility) {
                                Visibility visibilityExpandTo = visibility.expandTo(methodDescription.getVisibility()).expandTo(methodDescription2.getVisibility());
                                if (methodDescription.isBridge() ^ methodDescription2.isBridge()) {
                                    if (methodDescription.isBridge()) {
                                        methodDescription = methodDescription2;
                                    }
                                    return new Resolved(harmonized, methodDescription, visibilityExpandTo, false);
                                }
                                return new Ambiguous(harmonized, new LinkedHashSet(Arrays.asList(methodDescription, methodDescription2)), visibilityExpandTo);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Harmonized<U> getKey() {
                                return this.key;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Set<MethodDescription> getCandidates() {
                                return this.methodDescriptions;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Visibility getVisibility() {
                                return this.visibility;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> extendBy(MethodDescription methodDescription, Harmonizer<U> harmonizer) {
                                Harmonized<U> harmonizedExtend = this.key.extend(methodDescription.asDefined(), harmonizer);
                                LinkedHashSet linkedHashSet = new LinkedHashSet(this.methodDescriptions.size() + 1);
                                TypeDescription typeDescriptionAsErasure = methodDescription.getDeclaringType().asErasure();
                                boolean zIsBridge = methodDescription.isBridge();
                                Visibility visibilityExpandTo = this.visibility;
                                for (MethodDescription methodDescription2 : this.methodDescriptions) {
                                    if (methodDescription2.getDeclaringType().asErasure().equals(typeDescriptionAsErasure)) {
                                        if (methodDescription2.isBridge() ^ zIsBridge) {
                                            linkedHashSet.add(zIsBridge ? methodDescription2 : methodDescription);
                                        } else {
                                            linkedHashSet.add(methodDescription);
                                            linkedHashSet.add(methodDescription2);
                                        }
                                    }
                                    visibilityExpandTo = visibilityExpandTo.expandTo(methodDescription2.getVisibility());
                                }
                                if (linkedHashSet.isEmpty()) {
                                    return new Resolved(harmonizedExtend, methodDescription, visibilityExpandTo, zIsBridge);
                                }
                                if (linkedHashSet.size() == 1) {
                                    return new Resolved(harmonizedExtend, (MethodDescription) linkedHashSet.iterator().next(), visibilityExpandTo, false);
                                }
                                return new Ambiguous(harmonizedExtend, linkedHashSet, visibilityExpandTo);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> inject(Harmonized<U> harmonized, Visibility visibility) {
                                return new Ambiguous(this.key.combineWith(harmonized), this.methodDescriptions, this.visibility.expandTo(visibility));
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Node asNode(Merger merger) {
                                Iterator<MethodDescription> it = this.methodDescriptions.iterator();
                                MethodDescription next = it.next();
                                while (it.hasNext()) {
                                    next = merger.merge(next, it.next());
                                }
                                return new Node(this.key.detach(next.asTypeToken()), next, this.visibility);
                            }

                            protected static class Node implements Node {
                                private final Detached key;
                                private final MethodDescription methodDescription;
                                private final Visibility visibility;

                                protected boolean canEqual(Object obj) {
                                    return obj instanceof Node;
                                }

                                public boolean equals(Object obj) {
                                    if (obj == this) {
                                        return true;
                                    }
                                    if (!(obj instanceof Node)) {
                                        return false;
                                    }
                                    Node node = (Node) obj;
                                    if (!node.canEqual(this)) {
                                        return false;
                                    }
                                    Detached detached = this.key;
                                    Detached detached2 = node.key;
                                    if (detached != null ? !detached.equals(detached2) : detached2 != null) {
                                        return false;
                                    }
                                    MethodDescription methodDescription = this.methodDescription;
                                    MethodDescription methodDescription2 = node.methodDescription;
                                    if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                                        return false;
                                    }
                                    Visibility visibility = getVisibility();
                                    Visibility visibility2 = node.getVisibility();
                                    return visibility != null ? visibility.equals(visibility2) : visibility2 == null;
                                }

                                public int hashCode() {
                                    Detached detached = this.key;
                                    int iHashCode = detached == null ? 43 : detached.hashCode();
                                    MethodDescription methodDescription = this.methodDescription;
                                    int iHashCode2 = ((iHashCode + 59) * 59) + (methodDescription == null ? 43 : methodDescription.hashCode());
                                    Visibility visibility = getVisibility();
                                    return (iHashCode2 * 59) + (visibility != null ? visibility.hashCode() : 43);
                                }

                                protected Node(Detached detached, MethodDescription methodDescription, Visibility visibility) {
                                    this.key = detached;
                                    this.methodDescription = methodDescription;
                                    this.visibility = visibility;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Node.Sort getSort() {
                                    return Node.Sort.AMBIGUOUS;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public MethodDescription getRepresentative() {
                                    return this.methodDescription;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Set<MethodDescription.TypeToken> getMethodTypes() {
                                    return this.key.getIdentifiers();
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Visibility getVisibility() {
                                    return this.visibility;
                                }
                            }
                        }
                    }

                    protected static class Graph implements MethodGraph {
                        private final LinkedHashMap<Key<MethodDescription.TypeToken>, Node> entries;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof Graph;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof Graph)) {
                                return false;
                            }
                            Graph graph = (Graph) obj;
                            if (!graph.canEqual(this)) {
                                return false;
                            }
                            LinkedHashMap<Key<MethodDescription.TypeToken>, Node> linkedHashMap = this.entries;
                            LinkedHashMap<Key<MethodDescription.TypeToken>, Node> linkedHashMap2 = graph.entries;
                            return linkedHashMap != null ? linkedHashMap.equals(linkedHashMap2) : linkedHashMap2 == null;
                        }

                        public int hashCode() {
                            LinkedHashMap<Key<MethodDescription.TypeToken>, Node> linkedHashMap = this.entries;
                            return 59 + (linkedHashMap == null ? 43 : linkedHashMap.hashCode());
                        }

                        protected Graph(LinkedHashMap<Key<MethodDescription.TypeToken>, Node> linkedHashMap) {
                            this.entries = linkedHashMap;
                        }

                        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
                        public Node locate(MethodDescription.SignatureToken signatureToken) {
                            Node node = this.entries.get(Detached.of(signatureToken));
                            return node == null ? Node.Unresolved.INSTANCE : node;
                        }

                        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
                        public NodeList listNodes() {
                            return new NodeList(new ArrayList(this.entries.values()));
                        }
                    }
                }
            }
        }
    }

    public static class NodeList extends FilterableList.AbstractBase<Node, NodeList> {
        private final List<? extends Node> nodes;

        public NodeList(List<? extends Node> list) {
            this.nodes = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public Node get(int i) {
            return this.nodes.get(i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.nodes.size();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.matcher.FilterableList.AbstractBase
        public NodeList wrap(List<Node> list) {
            return new NodeList(list);
        }

        public MethodList<?> asMethodList() {
            ArrayList arrayList = new ArrayList(size());
            Iterator<? extends Node> it = this.nodes.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getRepresentative());
            }
            return new MethodList.Explicit(arrayList);
        }
    }

    public static class Simple implements MethodGraph {
        private final LinkedHashMap<MethodDescription.SignatureToken, Node> nodes;

        protected boolean canEqual(Object obj) {
            return obj instanceof Simple;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Simple)) {
                return false;
            }
            Simple simple = (Simple) obj;
            if (!simple.canEqual(this)) {
                return false;
            }
            LinkedHashMap<MethodDescription.SignatureToken, Node> linkedHashMap = this.nodes;
            LinkedHashMap<MethodDescription.SignatureToken, Node> linkedHashMap2 = simple.nodes;
            return linkedHashMap != null ? linkedHashMap.equals(linkedHashMap2) : linkedHashMap2 == null;
        }

        public int hashCode() {
            LinkedHashMap<MethodDescription.SignatureToken, Node> linkedHashMap = this.nodes;
            return 59 + (linkedHashMap == null ? 43 : linkedHashMap.hashCode());
        }

        public Simple(LinkedHashMap<MethodDescription.SignatureToken, Node> linkedHashMap) {
            this.nodes = linkedHashMap;
        }

        public static MethodGraph of(List<? extends MethodDescription> list) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (MethodDescription methodDescription : list) {
                linkedHashMap.put(methodDescription.asSignatureToken(), new Node.Simple(methodDescription));
            }
            return new Simple(linkedHashMap);
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
        public Node locate(MethodDescription.SignatureToken signatureToken) {
            Node node = this.nodes.get(signatureToken);
            return node == null ? Node.Unresolved.INSTANCE : node;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
        public NodeList listNodes() {
            return new NodeList(new ArrayList(this.nodes.values()));
        }
    }
}
