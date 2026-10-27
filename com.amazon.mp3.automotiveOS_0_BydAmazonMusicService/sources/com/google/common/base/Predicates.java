package com.google.common.base;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public final class Predicates {

    enum ObjectPredicate implements Predicate<Object> {
        ALWAYS_TRUE { // from class: com.google.common.base.Predicates.ObjectPredicate.1
            @Override // com.google.common.base.Predicates.ObjectPredicate, com.google.common.base.Predicate
            public boolean apply(@CheckForNull Object obj) {
                return true;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.alwaysTrue()";
            }
        },
        ALWAYS_FALSE { // from class: com.google.common.base.Predicates.ObjectPredicate.2
            @Override // com.google.common.base.Predicates.ObjectPredicate, com.google.common.base.Predicate
            public boolean apply(@CheckForNull Object obj) {
                return false;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.alwaysFalse()";
            }
        },
        IS_NULL { // from class: com.google.common.base.Predicates.ObjectPredicate.3
            @Override // com.google.common.base.Predicates.ObjectPredicate, com.google.common.base.Predicate
            public boolean apply(@CheckForNull Object obj) {
                return obj == null;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.isNull()";
            }
        },
        NOT_NULL { // from class: com.google.common.base.Predicates.ObjectPredicate.4
            @Override // com.google.common.base.Predicates.ObjectPredicate, com.google.common.base.Predicate
            public boolean apply(@CheckForNull Object obj) {
                return obj != null;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.notNull()";
            }
        };

        @Override // com.google.common.base.Predicate
        public abstract /* synthetic */ boolean apply(Object obj);

        <T> Predicate<T> withNarrowedType() {
            return this;
        }
    }

    private Predicates() {
    }

    public static <T> Predicate<T> alwaysTrue() {
        return ObjectPredicate.ALWAYS_TRUE.withNarrowedType();
    }

    public static <T> Predicate<T> isNull() {
        return ObjectPredicate.IS_NULL.withNarrowedType();
    }

    public static <T> Predicate<T> notNull() {
        return ObjectPredicate.NOT_NULL.withNarrowedType();
    }

    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return new NotPredicate(predicate);
    }

    public static <T> Predicate<T> and(Predicate<? super T> predicate, Predicate<? super T> predicate2) {
        return new AndPredicate(asList((Predicate) Preconditions.checkNotNull(predicate), (Predicate) Preconditions.checkNotNull(predicate2)));
    }

    public static <T> Predicate<T> equalTo(T t) {
        if (t == null) {
            return isNull();
        }
        return new IsEqualToPredicate(t).withNarrowedType();
    }

    public static <A, B> Predicate<A> compose(Predicate<B> predicate, Function<A, ? extends B> function) {
        return new CompositionPredicate(predicate, function);
    }

    private static class NotPredicate<T> implements Predicate<T>, Serializable {
        final Predicate<T> predicate;

        NotPredicate(Predicate<T> predicate) {
            this.predicate = (Predicate) Preconditions.checkNotNull(predicate);
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T t) {
            return !this.predicate.apply(t);
        }

        public int hashCode() {
            return ~this.predicate.hashCode();
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof NotPredicate) {
                return this.predicate.equals(((NotPredicate) obj).predicate);
            }
            return false;
        }

        public String toString() {
            String strValueOf = String.valueOf(this.predicate);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 16);
            sb.append("Predicates.not(");
            sb.append(strValueOf);
            sb.append(")");
            return sb.toString();
        }
    }

    private static class AndPredicate<T> implements Predicate<T>, Serializable {
        private final List<? extends Predicate<? super T>> components;

        private AndPredicate(List<? extends Predicate<? super T>> list) {
            this.components = list;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T t) {
            for (int i = 0; i < this.components.size(); i++) {
                if (!this.components.get(i).apply(t)) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            return this.components.hashCode() + 306654252;
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof AndPredicate) {
                return this.components.equals(((AndPredicate) obj).components);
            }
            return false;
        }

        public String toString() {
            return Predicates.toStringHelper("and", this.components);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String toStringHelper(String str, Iterable<?> iterable) {
        StringBuilder sb = new StringBuilder("Predicates.");
        sb.append(str);
        sb.append('(');
        boolean z = true;
        for (Object obj : iterable) {
            if (!z) {
                sb.append(',');
            }
            sb.append(obj);
            z = false;
        }
        sb.append(')');
        return sb.toString();
    }

    private static class IsEqualToPredicate implements Predicate<Object>, Serializable {
        private final Object target;

        <T> Predicate<T> withNarrowedType() {
            return this;
        }

        private IsEqualToPredicate(Object obj) {
            this.target = obj;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(@CheckForNull Object obj) {
            return this.target.equals(obj);
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof IsEqualToPredicate) {
                return this.target.equals(((IsEqualToPredicate) obj).target);
            }
            return false;
        }

        public String toString() {
            String strValueOf = String.valueOf(this.target);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 20);
            sb.append("Predicates.equalTo(");
            sb.append(strValueOf);
            sb.append(")");
            return sb.toString();
        }
    }

    private static class CompositionPredicate<A, B> implements Predicate<A>, Serializable {
        final Function<A, ? extends B> f;
        final Predicate<B> p;

        private CompositionPredicate(Predicate<B> predicate, Function<A, ? extends B> function) {
            this.p = (Predicate) Preconditions.checkNotNull(predicate);
            this.f = (Function) Preconditions.checkNotNull(function);
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(A a2) {
            return this.p.apply(this.f.apply(a2));
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof CompositionPredicate)) {
                return false;
            }
            CompositionPredicate compositionPredicate = (CompositionPredicate) obj;
            return this.f.equals(compositionPredicate.f) && this.p.equals(compositionPredicate.p);
        }

        public int hashCode() {
            return this.f.hashCode() ^ this.p.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.p);
            String strValueOf2 = String.valueOf(this.f);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 2 + strValueOf2.length());
            sb.append(strValueOf);
            sb.append("(");
            sb.append(strValueOf2);
            sb.append(")");
            return sb.toString();
        }
    }

    private static <T> List<Predicate<? super T>> asList(Predicate<? super T> predicate, Predicate<? super T> predicate2) {
        return Arrays.asList(predicate, predicate2);
    }
}
