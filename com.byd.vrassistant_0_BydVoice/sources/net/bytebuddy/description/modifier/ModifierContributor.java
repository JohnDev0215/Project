package net.bytebuddy.description.modifier;

import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public interface ModifierContributor {
    public static final int EMPTY_MASK = 0;

    public interface ForField extends ModifierContributor {
        public static final int MASK = 151775;
    }

    public interface ForMethod extends ModifierContributor {
        public static final int MASK = 7679;
    }

    public interface ForParameter extends ModifierContributor {
        public static final int MASK = 36880;
    }

    public interface ForType extends ModifierContributor {
        public static final int MASK = 161311;
    }

    int getMask();

    int getRange();

    boolean isDefault();

    public static class Resolver<T extends ModifierContributor> {
        private final Collection<? extends T> modifierContributors;

        protected boolean canEqual(Object obj) {
            return obj instanceof Resolver;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Resolver)) {
                return false;
            }
            Resolver resolver = (Resolver) obj;
            if (!resolver.canEqual(this)) {
                return false;
            }
            Collection<? extends T> collection = this.modifierContributors;
            Collection<? extends T> collection2 = resolver.modifierContributors;
            return collection != null ? collection.equals(collection2) : collection2 == null;
        }

        public int hashCode() {
            Collection<? extends T> collection = this.modifierContributors;
            return 59 + (collection == null ? 43 : collection.hashCode());
        }

        protected Resolver(Collection<? extends T> collection) {
            this.modifierContributors = collection;
        }

        public static Resolver<ForType> of(ForType... forTypeArr) {
            return of(Arrays.asList(forTypeArr));
        }

        public static Resolver<ForField> of(ForField... forFieldArr) {
            return of(Arrays.asList(forFieldArr));
        }

        public static Resolver<ForMethod> of(ForMethod... forMethodArr) {
            return of(Arrays.asList(forMethodArr));
        }

        public static Resolver<ForParameter> of(ForParameter... forParameterArr) {
            return of(Arrays.asList(forParameterArr));
        }

        public static <S extends ModifierContributor> Resolver<S> of(Collection<? extends S> collection) {
            return new Resolver<>(collection);
        }

        public int resolve() {
            return resolve(0);
        }

        public int resolve(int i) {
            for (T t : this.modifierContributors) {
                i = (i & (~t.getRange())) | t.getMask();
            }
            return i;
        }
    }
}
