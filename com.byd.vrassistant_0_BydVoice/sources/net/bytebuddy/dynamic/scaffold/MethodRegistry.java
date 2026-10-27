package net.bytebuddy.dynamic.scaffold;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.utility.CompoundList;

/* JADX INFO: loaded from: classes3.dex */
public interface MethodRegistry {

    public interface Compiled extends TypeWriter.MethodPool {
        MethodList<?> getInstrumentedMethods();

        TypeDescription getInstrumentedType();

        LoadedTypeInitializer getLoadedTypeInitializer();

        MethodList<?> getMethods();

        TypeInitializer getTypeInitializer();
    }

    public interface Prepared {
        Compiled compile(Implementation.Target.Factory factory, ClassFileVersion classFileVersion);

        MethodList<?> getInstrumentedMethods();

        TypeDescription getInstrumentedType();

        LoadedTypeInitializer getLoadedTypeInitializer();

        MethodList<?> getMethods();

        TypeInitializer getTypeInitializer();
    }

    MethodRegistry append(LatentMatcher<? super MethodDescription> latentMatcher, Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer);

    Prepared prepare(InstrumentedType instrumentedType, MethodGraph.Compiler compiler, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher);

    MethodRegistry prepend(LatentMatcher<? super MethodDescription> latentMatcher, Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer);

    public interface Handler extends InstrumentedType.Prepareable {

        public interface Compiled {
            TypeWriter.MethodPool.Record assemble(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility);
        }

        Compiled compile(Implementation.Target target);

        public enum ForAbstractMethod implements Handler, Compiled {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler
            public Compiled compile(Implementation.Target target) {
                return this;
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.Compiled
            public TypeWriter.MethodPool.Record assemble(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                return new TypeWriter.MethodPool.Record.ForDefinedMethod.WithoutBody(methodDescription, methodAttributeAppender, visibility);
            }
        }

        public enum ForVisibilityBridge implements Handler {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                throw new IllegalStateException("A visibility bridge handler must not apply any preparations");
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler
            public Compiled compile(Implementation.Target target) {
                return new Compiled(target.getInstrumentedType());
            }

            protected static class Compiled implements Compiled {
                private final TypeDescription instrumentedType;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Compiled;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Compiled)) {
                        return false;
                    }
                    Compiled compiled = (Compiled) obj;
                    if (!compiled.canEqual(this)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.instrumentedType;
                    TypeDescription typeDescription2 = compiled.instrumentedType;
                    return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                }

                public int hashCode() {
                    TypeDescription typeDescription = this.instrumentedType;
                    return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                }

                protected Compiled(TypeDescription typeDescription) {
                    this.instrumentedType = typeDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.Compiled
                public TypeWriter.MethodPool.Record assemble(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                    return TypeWriter.MethodPool.Record.ForDefinedMethod.OfVisibilityBridge.of(this.instrumentedType, methodDescription, methodAttributeAppender);
                }
            }
        }

        public static class ForImplementation implements Handler {
            private final Implementation implementation;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForImplementation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForImplementation)) {
                    return false;
                }
                ForImplementation forImplementation = (ForImplementation) obj;
                if (!forImplementation.canEqual(this)) {
                    return false;
                }
                Implementation implementation = this.implementation;
                Implementation implementation2 = forImplementation.implementation;
                return implementation != null ? implementation.equals(implementation2) : implementation2 == null;
            }

            public int hashCode() {
                Implementation implementation = this.implementation;
                return 59 + (implementation == null ? 43 : implementation.hashCode());
            }

            public ForImplementation(Implementation implementation) {
                this.implementation = implementation;
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return this.implementation.prepare(instrumentedType);
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler
            public Compiled compile(Implementation.Target target) {
                return new Compiled(this.implementation.appender(target));
            }

            protected static class Compiled implements Compiled {
                private final ByteCodeAppender byteCodeAppender;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Compiled;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Compiled)) {
                        return false;
                    }
                    Compiled compiled = (Compiled) obj;
                    if (!compiled.canEqual(this)) {
                        return false;
                    }
                    ByteCodeAppender byteCodeAppender = this.byteCodeAppender;
                    ByteCodeAppender byteCodeAppender2 = compiled.byteCodeAppender;
                    return byteCodeAppender != null ? byteCodeAppender.equals(byteCodeAppender2) : byteCodeAppender2 == null;
                }

                public int hashCode() {
                    ByteCodeAppender byteCodeAppender = this.byteCodeAppender;
                    return 59 + (byteCodeAppender == null ? 43 : byteCodeAppender.hashCode());
                }

                protected Compiled(ByteCodeAppender byteCodeAppender) {
                    this.byteCodeAppender = byteCodeAppender;
                }

                @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.Compiled
                public TypeWriter.MethodPool.Record assemble(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                    return new TypeWriter.MethodPool.Record.ForDefinedMethod.WithBody(methodDescription, this.byteCodeAppender, methodAttributeAppender, visibility);
                }
            }
        }

        public static class ForAnnotationValue implements Handler, Compiled {
            private final AnnotationValue<?, ?> annotationValue;

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler
            public Compiled compile(Implementation.Target target) {
                return this;
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof ForAnnotationValue;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForAnnotationValue)) {
                    return false;
                }
                ForAnnotationValue forAnnotationValue = (ForAnnotationValue) obj;
                if (!forAnnotationValue.canEqual(this)) {
                    return false;
                }
                AnnotationValue<?, ?> annotationValue = this.annotationValue;
                AnnotationValue<?, ?> annotationValue2 = forAnnotationValue.annotationValue;
                return annotationValue != null ? annotationValue.equals(annotationValue2) : annotationValue2 == null;
            }

            public int hashCode() {
                AnnotationValue<?, ?> annotationValue = this.annotationValue;
                return 59 + (annotationValue == null ? 43 : annotationValue.hashCode());
            }

            public ForAnnotationValue(AnnotationValue<?, ?> annotationValue) {
                this.annotationValue = annotationValue;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.Compiled
            public TypeWriter.MethodPool.Record assemble(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                return new TypeWriter.MethodPool.Record.ForDefinedMethod.WithAnnotationDefaultValue(methodDescription, this.annotationValue, methodAttributeAppender);
            }
        }
    }

    public static class Default implements MethodRegistry {
        private final List<Entry> entries;

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
            Default r4 = (Default) obj;
            if (!r4.canEqual(this)) {
                return false;
            }
            List<Entry> list = this.entries;
            List<Entry> list2 = r4.entries;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<Entry> list = this.entries;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Default() {
            this.entries = Collections.emptyList();
        }

        private Default(List<Entry> list) {
            this.entries = list;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry
        public MethodRegistry prepend(LatentMatcher<? super MethodDescription> latentMatcher, Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
            return new Default(CompoundList.of(new Entry(latentMatcher, handler, factory, transformer), this.entries));
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry
        public MethodRegistry append(LatentMatcher<? super MethodDescription> latentMatcher, Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
            return new Default(CompoundList.of(this.entries, new Entry(latentMatcher, handler, factory, transformer)));
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry
        public Prepared prepare(InstrumentedType instrumentedType, MethodGraph.Compiler compiler, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            HashSet hashSet = new HashSet();
            MethodList<MethodDescription.InDefinedShape> declaredMethods = instrumentedType.getDeclaredMethods();
            for (Entry entry : this.entries) {
                if (hashSet.add(entry.getHandler())) {
                    instrumentedType = entry.getHandler().prepare(instrumentedType);
                    ElementMatcher.Junction junctionNoneOf = ElementMatchers.noneOf(declaredMethods);
                    MethodList<MethodDescription.InDefinedShape> declaredMethods2 = instrumentedType.getDeclaredMethods();
                    for (MethodDescription methodDescription : declaredMethods2.filter(junctionNoneOf)) {
                        linkedHashMap.put(methodDescription, entry.asSupplementaryEntry(methodDescription));
                    }
                    declaredMethods = declaredMethods2;
                }
            }
            MethodGraph.Linked linkedCompile = compiler.compile(instrumentedType);
            ElementMatcher.Junction junctionAnd = ElementMatchers.not(ElementMatchers.anyOf(linkedHashMap.keySet())).and(ElementMatchers.returns(ElementMatchers.isVisibleTo(instrumentedType))).and(ElementMatchers.hasParameters(ElementMatchers.whereNone(ElementMatchers.hasType(ElementMatchers.not(ElementMatchers.isVisibleTo(instrumentedType)))))).and(latentMatcher.resolve(instrumentedType));
            ArrayList arrayList = new ArrayList();
            for (MethodGraph.Node node : linkedCompile.listNodes()) {
                MethodDescription representative = node.getRepresentative();
                boolean z = false;
                boolean z2 = instrumentedType.isPublic() && !instrumentedType.isInterface();
                if (junctionAnd.matches(representative)) {
                    for (Entry entry2 : this.entries) {
                        if (entry2.resolve(instrumentedType).matches(representative)) {
                            linkedHashMap.put(representative, entry2.asPreparedEntry(instrumentedType, representative, node.getMethodTypes(), node.getVisibility()));
                            break;
                        }
                    }
                    z = z2;
                } else {
                    z = z2;
                }
                if (z && !node.getSort().isMadeVisible() && representative.isPublic() && !representative.isAbstract() && !representative.isFinal() && representative.getDeclaringType().isPackagePrivate()) {
                    linkedHashMap.put(representative, Prepared.Entry.forVisibilityBridge(representative, node.getVisibility()));
                }
                arrayList.add(representative);
            }
            for (MethodDescription methodDescription2 : CompoundList.of((List<? extends MethodDescription.Latent.TypeInitializer>) instrumentedType.getDeclaredMethods().filter(ElementMatchers.not(ElementMatchers.isVirtual()).and(junctionAnd)), new MethodDescription.Latent.TypeInitializer(instrumentedType))) {
                Iterator<Entry> it = this.entries.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Entry next = it.next();
                        if (next.resolve(instrumentedType).matches(methodDescription2)) {
                            linkedHashMap.put(methodDescription2, next.asPreparedEntry(instrumentedType, methodDescription2, methodDescription2.getVisibility()));
                            break;
                        }
                    }
                }
                arrayList.add(methodDescription2);
            }
            LoadedTypeInitializer loadedTypeInitializer = instrumentedType.getLoadedTypeInitializer();
            TypeInitializer typeInitializer = instrumentedType.getTypeInitializer();
            InstrumentedType instrumentedTypeValidated = instrumentedType;
            if (typeValidation.isEnabled()) {
                instrumentedTypeValidated = instrumentedType.validated();
            }
            return new Prepared(linkedHashMap, loadedTypeInitializer, typeInitializer, instrumentedTypeValidated, linkedCompile, new MethodList.Explicit(arrayList));
        }

        protected static class Entry implements LatentMatcher<MethodDescription> {
            private final MethodAttributeAppender.Factory attributeAppenderFactory;
            private final Handler handler;
            private final LatentMatcher<? super MethodDescription> matcher;
            private final Transformer<MethodDescription> transformer;

            protected boolean canEqual(Object obj) {
                return obj instanceof Entry;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Entry)) {
                    return false;
                }
                Entry entry = (Entry) obj;
                if (!entry.canEqual(this)) {
                    return false;
                }
                LatentMatcher<? super MethodDescription> latentMatcher = this.matcher;
                LatentMatcher<? super MethodDescription> latentMatcher2 = entry.matcher;
                if (latentMatcher != null ? !latentMatcher.equals(latentMatcher2) : latentMatcher2 != null) {
                    return false;
                }
                Handler handler = getHandler();
                Handler handler2 = entry.getHandler();
                if (handler != null ? !handler.equals(handler2) : handler2 != null) {
                    return false;
                }
                MethodAttributeAppender.Factory factory = this.attributeAppenderFactory;
                MethodAttributeAppender.Factory factory2 = entry.attributeAppenderFactory;
                if (factory != null ? !factory.equals(factory2) : factory2 != null) {
                    return false;
                }
                Transformer<MethodDescription> transformer = this.transformer;
                Transformer<MethodDescription> transformer2 = entry.transformer;
                return transformer != null ? transformer.equals(transformer2) : transformer2 == null;
            }

            public int hashCode() {
                LatentMatcher<? super MethodDescription> latentMatcher = this.matcher;
                int iHashCode = latentMatcher == null ? 43 : latentMatcher.hashCode();
                Handler handler = getHandler();
                int iHashCode2 = ((iHashCode + 59) * 59) + (handler == null ? 43 : handler.hashCode());
                MethodAttributeAppender.Factory factory = this.attributeAppenderFactory;
                int iHashCode3 = (iHashCode2 * 59) + (factory == null ? 43 : factory.hashCode());
                Transformer<MethodDescription> transformer = this.transformer;
                return (iHashCode3 * 59) + (transformer != null ? transformer.hashCode() : 43);
            }

            protected Entry(LatentMatcher<? super MethodDescription> latentMatcher, Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                this.matcher = latentMatcher;
                this.handler = handler;
                this.attributeAppenderFactory = factory;
                this.transformer = transformer;
            }

            protected Prepared.Entry asPreparedEntry(TypeDescription typeDescription, MethodDescription methodDescription, Visibility visibility) {
                return asPreparedEntry(typeDescription, methodDescription, Collections.emptySet(), visibility);
            }

            protected Prepared.Entry asPreparedEntry(TypeDescription typeDescription, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, Visibility visibility) {
                return new Prepared.Entry(this.handler, this.attributeAppenderFactory, this.transformer.transform(typeDescription, methodDescription), set, visibility, false);
            }

            protected Prepared.Entry asSupplementaryEntry(MethodDescription methodDescription) {
                return new Prepared.Entry(this.handler, MethodAttributeAppender.Explicit.of(methodDescription), methodDescription, Collections.emptySet(), methodDescription.getVisibility(), false);
            }

            protected Handler getHandler() {
                return this.handler;
            }

            @Override // net.bytebuddy.matcher.LatentMatcher
            public ElementMatcher<? super MethodDescription> resolve(TypeDescription typeDescription) {
                return this.matcher.resolve(typeDescription);
            }
        }

        protected static class Prepared implements Prepared {
            private final LinkedHashMap<MethodDescription, Entry> implementations;
            private final TypeDescription instrumentedType;
            private final LoadedTypeInitializer loadedTypeInitializer;
            private final MethodGraph.Linked methodGraph;
            private final MethodList<?> methods;
            private final TypeInitializer typeInitializer;

            protected boolean canEqual(Object obj) {
                return obj instanceof Prepared;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Prepared)) {
                    return false;
                }
                Prepared prepared = (Prepared) obj;
                if (!prepared.canEqual(this)) {
                    return false;
                }
                LinkedHashMap<MethodDescription, Entry> linkedHashMap = this.implementations;
                LinkedHashMap<MethodDescription, Entry> linkedHashMap2 = prepared.implementations;
                if (linkedHashMap != null ? !linkedHashMap.equals(linkedHashMap2) : linkedHashMap2 != null) {
                    return false;
                }
                LoadedTypeInitializer loadedTypeInitializer = getLoadedTypeInitializer();
                LoadedTypeInitializer loadedTypeInitializer2 = prepared.getLoadedTypeInitializer();
                if (loadedTypeInitializer != null ? !loadedTypeInitializer.equals(loadedTypeInitializer2) : loadedTypeInitializer2 != null) {
                    return false;
                }
                TypeInitializer typeInitializer = getTypeInitializer();
                TypeInitializer typeInitializer2 = prepared.getTypeInitializer();
                if (typeInitializer != null ? !typeInitializer.equals(typeInitializer2) : typeInitializer2 != null) {
                    return false;
                }
                TypeDescription instrumentedType = getInstrumentedType();
                TypeDescription instrumentedType2 = prepared.getInstrumentedType();
                if (instrumentedType != null ? !instrumentedType.equals(instrumentedType2) : instrumentedType2 != null) {
                    return false;
                }
                MethodGraph.Linked linked = this.methodGraph;
                MethodGraph.Linked linked2 = prepared.methodGraph;
                if (linked != null ? !linked.equals(linked2) : linked2 != null) {
                    return false;
                }
                MethodList<?> methods = getMethods();
                MethodList<?> methods2 = prepared.getMethods();
                return methods != null ? methods.equals(methods2) : methods2 == null;
            }

            public int hashCode() {
                LinkedHashMap<MethodDescription, Entry> linkedHashMap = this.implementations;
                int iHashCode = linkedHashMap == null ? 43 : linkedHashMap.hashCode();
                LoadedTypeInitializer loadedTypeInitializer = getLoadedTypeInitializer();
                int iHashCode2 = ((iHashCode + 59) * 59) + (loadedTypeInitializer == null ? 43 : loadedTypeInitializer.hashCode());
                TypeInitializer typeInitializer = getTypeInitializer();
                int iHashCode3 = (iHashCode2 * 59) + (typeInitializer == null ? 43 : typeInitializer.hashCode());
                TypeDescription instrumentedType = getInstrumentedType();
                int iHashCode4 = (iHashCode3 * 59) + (instrumentedType == null ? 43 : instrumentedType.hashCode());
                MethodGraph.Linked linked = this.methodGraph;
                int iHashCode5 = (iHashCode4 * 59) + (linked == null ? 43 : linked.hashCode());
                MethodList<?> methods = getMethods();
                return (iHashCode5 * 59) + (methods != null ? methods.hashCode() : 43);
            }

            protected Prepared(LinkedHashMap<MethodDescription, Entry> linkedHashMap, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeDescription typeDescription, MethodGraph.Linked linked, MethodList<?> methodList) {
                this.implementations = linkedHashMap;
                this.loadedTypeInitializer = loadedTypeInitializer;
                this.typeInitializer = typeInitializer;
                this.instrumentedType = typeDescription;
                this.methodGraph = linked;
                this.methods = methodList;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public TypeDescription getInstrumentedType() {
                return this.instrumentedType;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public LoadedTypeInitializer getLoadedTypeInitializer() {
                return this.loadedTypeInitializer;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public TypeInitializer getTypeInitializer() {
                return this.typeInitializer;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public MethodList<?> getMethods() {
                return this.methods;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public MethodList<?> getInstrumentedMethods() {
                return (MethodList) new MethodList.Explicit(new ArrayList(this.implementations.keySet())).filter(ElementMatchers.not(ElementMatchers.isTypeInitializer()));
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public Compiled compile(Implementation.Target.Factory factory, ClassFileVersion classFileVersion) {
                HashMap map = new HashMap();
                HashMap map2 = new HashMap();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                Implementation.Target targetMake = factory.make(this.instrumentedType, this.methodGraph, classFileVersion);
                for (Map.Entry<MethodDescription, Entry> entry : this.implementations.entrySet()) {
                    Handler.Compiled compiledCompile = (Handler.Compiled) map.get(entry.getValue().getHandler());
                    if (compiledCompile == null) {
                        compiledCompile = entry.getValue().getHandler().compile(targetMake);
                        map.put(entry.getValue().getHandler(), compiledCompile);
                    }
                    Handler.Compiled compiled = compiledCompile;
                    MethodAttributeAppender methodAttributeAppenderMake = (MethodAttributeAppender) map2.get(entry.getValue().getAppenderFactory());
                    if (methodAttributeAppenderMake == null) {
                        methodAttributeAppenderMake = entry.getValue().getAppenderFactory().make(this.instrumentedType);
                        map2.put(entry.getValue().getAppenderFactory(), methodAttributeAppenderMake);
                    }
                    linkedHashMap.put(entry.getKey(), new Compiled.Entry(compiled, methodAttributeAppenderMake, entry.getValue().getMethodDescription(), entry.getValue().resolveBridgeTypes(), entry.getValue().getVisibility(), entry.getValue().isBridgeMethod()));
                }
                return new Compiled(this.instrumentedType, this.loadedTypeInitializer, this.typeInitializer, this.methods, linkedHashMap, classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5));
            }

            protected static class Entry {
                private final MethodAttributeAppender.Factory attributeAppenderFactory;
                private final boolean bridgeMethod;
                private final Handler handler;
                private final MethodDescription methodDescription;
                private final Set<MethodDescription.TypeToken> typeTokens;
                private Visibility visibility;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Entry;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Entry)) {
                        return false;
                    }
                    Entry entry = (Entry) obj;
                    if (!entry.canEqual(this)) {
                        return false;
                    }
                    Handler handler = getHandler();
                    Handler handler2 = entry.getHandler();
                    if (handler != null ? !handler.equals(handler2) : handler2 != null) {
                        return false;
                    }
                    MethodAttributeAppender.Factory factory = this.attributeAppenderFactory;
                    MethodAttributeAppender.Factory factory2 = entry.attributeAppenderFactory;
                    if (factory != null ? !factory.equals(factory2) : factory2 != null) {
                        return false;
                    }
                    MethodDescription methodDescription = getMethodDescription();
                    MethodDescription methodDescription2 = entry.getMethodDescription();
                    if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                        return false;
                    }
                    Set<MethodDescription.TypeToken> set = this.typeTokens;
                    Set<MethodDescription.TypeToken> set2 = entry.typeTokens;
                    if (set != null ? !set.equals(set2) : set2 != null) {
                        return false;
                    }
                    Visibility visibility = getVisibility();
                    Visibility visibility2 = entry.getVisibility();
                    if (visibility != null ? visibility.equals(visibility2) : visibility2 == null) {
                        return isBridgeMethod() == entry.isBridgeMethod();
                    }
                    return false;
                }

                public int hashCode() {
                    Handler handler = getHandler();
                    int iHashCode = handler == null ? 43 : handler.hashCode();
                    MethodAttributeAppender.Factory factory = this.attributeAppenderFactory;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (factory == null ? 43 : factory.hashCode());
                    MethodDescription methodDescription = getMethodDescription();
                    int iHashCode3 = (iHashCode2 * 59) + (methodDescription == null ? 43 : methodDescription.hashCode());
                    Set<MethodDescription.TypeToken> set = this.typeTokens;
                    int iHashCode4 = (iHashCode3 * 59) + (set == null ? 43 : set.hashCode());
                    Visibility visibility = getVisibility();
                    return (((iHashCode4 * 59) + (visibility != null ? visibility.hashCode() : 43)) * 59) + (isBridgeMethod() ? 79 : 97);
                }

                protected Entry(Handler handler, MethodAttributeAppender.Factory factory, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, Visibility visibility, boolean z) {
                    this.handler = handler;
                    this.attributeAppenderFactory = factory;
                    this.methodDescription = methodDescription;
                    this.typeTokens = set;
                    this.visibility = visibility;
                    this.bridgeMethod = z;
                }

                protected static Entry forVisibilityBridge(MethodDescription methodDescription, Visibility visibility) {
                    return new Entry(Handler.ForVisibilityBridge.INSTANCE, MethodAttributeAppender.Explicit.of(methodDescription), methodDescription, Collections.emptySet(), visibility, true);
                }

                protected Handler getHandler() {
                    return this.handler;
                }

                protected MethodAttributeAppender.Factory getAppenderFactory() {
                    return this.attributeAppenderFactory;
                }

                protected MethodDescription getMethodDescription() {
                    return this.methodDescription;
                }

                protected Set<MethodDescription.TypeToken> resolveBridgeTypes() {
                    HashSet hashSet = new HashSet(this.typeTokens);
                    hashSet.remove(this.methodDescription.asTypeToken());
                    return hashSet;
                }

                protected Visibility getVisibility() {
                    return this.visibility;
                }

                protected boolean isBridgeMethod() {
                    return this.bridgeMethod;
                }
            }
        }

        protected static class Compiled implements Compiled {
            private final LinkedHashMap<MethodDescription, Entry> implementations;
            private final TypeDescription instrumentedType;
            private final LoadedTypeInitializer loadedTypeInitializer;
            private final MethodList<?> methods;
            private final boolean supportsBridges;
            private final TypeInitializer typeInitializer;

            protected boolean canEqual(Object obj) {
                return obj instanceof Compiled;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Compiled)) {
                    return false;
                }
                Compiled compiled = (Compiled) obj;
                if (!compiled.canEqual(this)) {
                    return false;
                }
                TypeDescription instrumentedType = getInstrumentedType();
                TypeDescription instrumentedType2 = compiled.getInstrumentedType();
                if (instrumentedType != null ? !instrumentedType.equals(instrumentedType2) : instrumentedType2 != null) {
                    return false;
                }
                LoadedTypeInitializer loadedTypeInitializer = getLoadedTypeInitializer();
                LoadedTypeInitializer loadedTypeInitializer2 = compiled.getLoadedTypeInitializer();
                if (loadedTypeInitializer != null ? !loadedTypeInitializer.equals(loadedTypeInitializer2) : loadedTypeInitializer2 != null) {
                    return false;
                }
                TypeInitializer typeInitializer = getTypeInitializer();
                TypeInitializer typeInitializer2 = compiled.getTypeInitializer();
                if (typeInitializer != null ? !typeInitializer.equals(typeInitializer2) : typeInitializer2 != null) {
                    return false;
                }
                MethodList<?> methods = getMethods();
                MethodList<?> methods2 = compiled.getMethods();
                if (methods != null ? !methods.equals(methods2) : methods2 != null) {
                    return false;
                }
                LinkedHashMap<MethodDescription, Entry> linkedHashMap = this.implementations;
                LinkedHashMap<MethodDescription, Entry> linkedHashMap2 = compiled.implementations;
                if (linkedHashMap != null ? linkedHashMap.equals(linkedHashMap2) : linkedHashMap2 == null) {
                    return this.supportsBridges == compiled.supportsBridges;
                }
                return false;
            }

            public int hashCode() {
                TypeDescription instrumentedType = getInstrumentedType();
                int iHashCode = instrumentedType == null ? 43 : instrumentedType.hashCode();
                LoadedTypeInitializer loadedTypeInitializer = getLoadedTypeInitializer();
                int iHashCode2 = ((iHashCode + 59) * 59) + (loadedTypeInitializer == null ? 43 : loadedTypeInitializer.hashCode());
                TypeInitializer typeInitializer = getTypeInitializer();
                int iHashCode3 = (iHashCode2 * 59) + (typeInitializer == null ? 43 : typeInitializer.hashCode());
                MethodList<?> methods = getMethods();
                int iHashCode4 = (iHashCode3 * 59) + (methods == null ? 43 : methods.hashCode());
                LinkedHashMap<MethodDescription, Entry> linkedHashMap = this.implementations;
                return (((iHashCode4 * 59) + (linkedHashMap != null ? linkedHashMap.hashCode() : 43)) * 59) + (this.supportsBridges ? 79 : 97);
            }

            protected Compiled(TypeDescription typeDescription, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, MethodList<?> methodList, LinkedHashMap<MethodDescription, Entry> linkedHashMap, boolean z) {
                this.instrumentedType = typeDescription;
                this.loadedTypeInitializer = loadedTypeInitializer;
                this.typeInitializer = typeInitializer;
                this.methods = methodList;
                this.implementations = linkedHashMap;
                this.supportsBridges = z;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Compiled
            public TypeDescription getInstrumentedType() {
                return this.instrumentedType;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Compiled
            public LoadedTypeInitializer getLoadedTypeInitializer() {
                return this.loadedTypeInitializer;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Compiled
            public TypeInitializer getTypeInitializer() {
                return this.typeInitializer;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Compiled
            public MethodList<?> getMethods() {
                return this.methods;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Compiled
            public MethodList<?> getInstrumentedMethods() {
                return (MethodList) new MethodList.Explicit(new ArrayList(this.implementations.keySet())).filter(ElementMatchers.not(ElementMatchers.isTypeInitializer()));
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool
            public TypeWriter.MethodPool.Record target(MethodDescription methodDescription) {
                Entry entry = this.implementations.get(methodDescription);
                if (entry == null) {
                    return new TypeWriter.MethodPool.Record.ForNonImplementedMethod(methodDescription);
                }
                return entry.bind(this.instrumentedType, this.supportsBridges);
            }

            protected static class Entry {
                private final MethodAttributeAppender attributeAppender;
                private final boolean bridgeMethod;
                private final Set<MethodDescription.TypeToken> bridgeTypes;
                private final Handler.Compiled handler;
                private final MethodDescription methodDescription;
                private final Visibility visibility;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Entry;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Entry)) {
                        return false;
                    }
                    Entry entry = (Entry) obj;
                    if (!entry.canEqual(this)) {
                        return false;
                    }
                    Handler.Compiled compiled = this.handler;
                    Handler.Compiled compiled2 = entry.handler;
                    if (compiled != null ? !compiled.equals(compiled2) : compiled2 != null) {
                        return false;
                    }
                    MethodAttributeAppender methodAttributeAppender = this.attributeAppender;
                    MethodAttributeAppender methodAttributeAppender2 = entry.attributeAppender;
                    if (methodAttributeAppender != null ? !methodAttributeAppender.equals(methodAttributeAppender2) : methodAttributeAppender2 != null) {
                        return false;
                    }
                    MethodDescription methodDescription = this.methodDescription;
                    MethodDescription methodDescription2 = entry.methodDescription;
                    if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                        return false;
                    }
                    Set<MethodDescription.TypeToken> set = this.bridgeTypes;
                    Set<MethodDescription.TypeToken> set2 = entry.bridgeTypes;
                    if (set != null ? !set.equals(set2) : set2 != null) {
                        return false;
                    }
                    Visibility visibility = this.visibility;
                    Visibility visibility2 = entry.visibility;
                    if (visibility != null ? visibility.equals(visibility2) : visibility2 == null) {
                        return this.bridgeMethod == entry.bridgeMethod;
                    }
                    return false;
                }

                public int hashCode() {
                    Handler.Compiled compiled = this.handler;
                    int iHashCode = compiled == null ? 43 : compiled.hashCode();
                    MethodAttributeAppender methodAttributeAppender = this.attributeAppender;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (methodAttributeAppender == null ? 43 : methodAttributeAppender.hashCode());
                    MethodDescription methodDescription = this.methodDescription;
                    int iHashCode3 = (iHashCode2 * 59) + (methodDescription == null ? 43 : methodDescription.hashCode());
                    Set<MethodDescription.TypeToken> set = this.bridgeTypes;
                    int iHashCode4 = (iHashCode3 * 59) + (set == null ? 43 : set.hashCode());
                    Visibility visibility = this.visibility;
                    return (((iHashCode4 * 59) + (visibility != null ? visibility.hashCode() : 43)) * 59) + (this.bridgeMethod ? 79 : 97);
                }

                protected Entry(Handler.Compiled compiled, MethodAttributeAppender methodAttributeAppender, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, Visibility visibility, boolean z) {
                    this.handler = compiled;
                    this.attributeAppender = methodAttributeAppender;
                    this.methodDescription = methodDescription;
                    this.bridgeTypes = set;
                    this.visibility = visibility;
                    this.bridgeMethod = z;
                }

                protected TypeWriter.MethodPool.Record bind(TypeDescription typeDescription, boolean z) {
                    if (this.bridgeMethod && !z) {
                        return new TypeWriter.MethodPool.Record.ForNonImplementedMethod(this.methodDescription);
                    }
                    TypeWriter.MethodPool.Record recordAssemble = this.handler.assemble(this.methodDescription, this.attributeAppender, this.visibility);
                    return z ? TypeWriter.MethodPool.Record.AccessBridgeWrapper.of(recordAssemble, typeDescription, this.methodDescription, this.bridgeTypes, this.attributeAppender) : recordAssemble;
                }
            }
        }
    }
}
