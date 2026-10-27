package net.bytebuddy.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.NexusAccessor;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import net.bytebuddy.implementation.LoadedTypeInitializer;

/* JADX INFO: loaded from: classes3.dex */
public interface TypeResolutionStrategy {

    public interface Resolved {
        <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, S s, ClassLoadingStrategy<? super S> classLoadingStrategy);

        TypeInitializer injectedInto(TypeInitializer typeInitializer);
    }

    Resolved resolve();

    public enum Passive implements TypeResolutionStrategy, Resolved {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public TypeInitializer injectedInto(TypeInitializer typeInitializer) {
            return typeInitializer;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy
        public Resolved resolve() {
            return this;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
            Map<TypeDescription, Class<?>> mapLoad = classLoadingStrategy.load(s, dynamicType.getAllTypes());
            for (Map.Entry<TypeDescription, LoadedTypeInitializer> entry : dynamicType.getLoadedTypeInitializers().entrySet()) {
                entry.getValue().onLoad(mapLoad.get(entry.getKey()));
            }
            return new HashMap(mapLoad);
        }
    }

    public static class Active implements TypeResolutionStrategy {
        private final NexusAccessor nexusAccessor;

        protected boolean canEqual(Object obj) {
            return obj instanceof Active;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Active)) {
                return false;
            }
            Active active = (Active) obj;
            if (!active.canEqual(this)) {
                return false;
            }
            NexusAccessor nexusAccessor = this.nexusAccessor;
            NexusAccessor nexusAccessor2 = active.nexusAccessor;
            return nexusAccessor != null ? nexusAccessor.equals(nexusAccessor2) : nexusAccessor2 == null;
        }

        public int hashCode() {
            NexusAccessor nexusAccessor = this.nexusAccessor;
            return 59 + (nexusAccessor == null ? 43 : nexusAccessor.hashCode());
        }

        public Active() {
            this(new NexusAccessor());
        }

        public Active(NexusAccessor nexusAccessor) {
            this.nexusAccessor = nexusAccessor;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy
        public Resolved resolve() {
            return new Resolved(this.nexusAccessor, new Random().nextInt());
        }

        protected static class Resolved implements Resolved {
            private final int identification;
            private final NexusAccessor nexusAccessor;

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
                NexusAccessor nexusAccessor = this.nexusAccessor;
                NexusAccessor nexusAccessor2 = resolved.nexusAccessor;
                if (nexusAccessor != null ? nexusAccessor.equals(nexusAccessor2) : nexusAccessor2 == null) {
                    return this.identification == resolved.identification;
                }
                return false;
            }

            public int hashCode() {
                NexusAccessor nexusAccessor = this.nexusAccessor;
                return (((nexusAccessor == null ? 43 : nexusAccessor.hashCode()) + 59) * 59) + this.identification;
            }

            protected Resolved(NexusAccessor nexusAccessor, int i) {
                this.nexusAccessor = nexusAccessor;
                this.identification = i;
            }

            @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
            public TypeInitializer injectedInto(TypeInitializer typeInitializer) {
                return typeInitializer.expandWith(new NexusAccessor.InitializationAppender(this.identification));
            }

            @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
            public <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
                HashMap map = new HashMap(dynamicType.getLoadedTypeInitializers());
                TypeDescription typeDescription = dynamicType.getTypeDescription();
                Map<TypeDescription, Class<?>> mapLoad = classLoadingStrategy.load(s, dynamicType.getAllTypes());
                this.nexusAccessor.register(typeDescription.getName(), mapLoad.get(typeDescription).getClassLoader(), this.identification, (LoadedTypeInitializer) map.remove(typeDescription));
                for (Map.Entry entry : map.entrySet()) {
                    ((LoadedTypeInitializer) entry.getValue()).onLoad(mapLoad.get(entry.getKey()));
                }
                return mapLoad;
            }
        }
    }

    public enum Lazy implements TypeResolutionStrategy, Resolved {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public TypeInitializer injectedInto(TypeInitializer typeInitializer) {
            return typeInitializer;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy
        public Resolved resolve() {
            return this;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
            return classLoadingStrategy.load(s, dynamicType.getAllTypes());
        }
    }

    public enum Disabled implements TypeResolutionStrategy, Resolved {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public TypeInitializer injectedInto(TypeInitializer typeInitializer) {
            return typeInitializer;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy
        public Resolved resolve() {
            return this;
        }

        @Override // net.bytebuddy.dynamic.TypeResolutionStrategy.Resolved
        public <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType, S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
            throw new IllegalStateException("Cannot initialize a dynamic type for a disabled type resolution strategy");
        }
    }
}
