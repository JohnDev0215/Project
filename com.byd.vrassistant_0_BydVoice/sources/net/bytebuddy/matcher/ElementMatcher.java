package net.bytebuddy.matcher;

/* JADX INFO: loaded from: classes3.dex */
public interface ElementMatcher<T> {
    boolean matches(T t);

    public interface Junction<S> extends ElementMatcher<S> {
        <U extends S> Junction<U> and(ElementMatcher<? super U> elementMatcher);

        <U extends S> Junction<U> or(ElementMatcher<? super U> elementMatcher);

        public static abstract class AbstractBase<V> implements Junction<V> {
            @Override // net.bytebuddy.matcher.ElementMatcher.Junction
            public <U extends V> Junction<U> and(ElementMatcher<? super U> elementMatcher) {
                return new Conjunction(this, elementMatcher);
            }

            @Override // net.bytebuddy.matcher.ElementMatcher.Junction
            public <U extends V> Junction<U> or(ElementMatcher<? super U> elementMatcher) {
                return new Disjunction(this, elementMatcher);
            }
        }

        public static class Conjunction<W> extends AbstractBase<W> {
            private final ElementMatcher<? super W> left;
            private final ElementMatcher<? super W> right;

            protected boolean canEqual(Object obj) {
                return obj instanceof Conjunction;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Conjunction)) {
                    return false;
                }
                Conjunction conjunction = (Conjunction) obj;
                if (!conjunction.canEqual(this)) {
                    return false;
                }
                ElementMatcher<? super W> elementMatcher = this.left;
                ElementMatcher<? super W> elementMatcher2 = conjunction.left;
                if (elementMatcher != null ? !elementMatcher.equals(elementMatcher2) : elementMatcher2 != null) {
                    return false;
                }
                ElementMatcher<? super W> elementMatcher3 = this.right;
                ElementMatcher<? super W> elementMatcher4 = conjunction.right;
                return elementMatcher3 != null ? elementMatcher3.equals(elementMatcher4) : elementMatcher4 == null;
            }

            public int hashCode() {
                ElementMatcher<? super W> elementMatcher = this.left;
                int iHashCode = elementMatcher == null ? 43 : elementMatcher.hashCode();
                ElementMatcher<? super W> elementMatcher2 = this.right;
                return ((iHashCode + 59) * 59) + (elementMatcher2 != null ? elementMatcher2.hashCode() : 43);
            }

            public Conjunction(ElementMatcher<? super W> elementMatcher, ElementMatcher<? super W> elementMatcher2) {
                this.left = elementMatcher;
                this.right = elementMatcher2;
            }

            @Override // net.bytebuddy.matcher.ElementMatcher
            public boolean matches(W w) {
                return this.left.matches(w) && this.right.matches(w);
            }

            public String toString() {
                return "(" + this.left + " and " + this.right + ')';
            }
        }

        public static class Disjunction<W> extends AbstractBase<W> {
            private final ElementMatcher<? super W> left;
            private final ElementMatcher<? super W> right;

            protected boolean canEqual(Object obj) {
                return obj instanceof Disjunction;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Disjunction)) {
                    return false;
                }
                Disjunction disjunction = (Disjunction) obj;
                if (!disjunction.canEqual(this)) {
                    return false;
                }
                ElementMatcher<? super W> elementMatcher = this.left;
                ElementMatcher<? super W> elementMatcher2 = disjunction.left;
                if (elementMatcher != null ? !elementMatcher.equals(elementMatcher2) : elementMatcher2 != null) {
                    return false;
                }
                ElementMatcher<? super W> elementMatcher3 = this.right;
                ElementMatcher<? super W> elementMatcher4 = disjunction.right;
                return elementMatcher3 != null ? elementMatcher3.equals(elementMatcher4) : elementMatcher4 == null;
            }

            public int hashCode() {
                ElementMatcher<? super W> elementMatcher = this.left;
                int iHashCode = elementMatcher == null ? 43 : elementMatcher.hashCode();
                ElementMatcher<? super W> elementMatcher2 = this.right;
                return ((iHashCode + 59) * 59) + (elementMatcher2 != null ? elementMatcher2.hashCode() : 43);
            }

            public Disjunction(ElementMatcher<? super W> elementMatcher, ElementMatcher<? super W> elementMatcher2) {
                this.left = elementMatcher;
                this.right = elementMatcher2;
            }

            @Override // net.bytebuddy.matcher.ElementMatcher
            public boolean matches(W w) {
                return this.left.matches(w) || this.right.matches(w);
            }

            public String toString() {
                return "(" + this.left + " or " + this.right + ')';
            }
        }
    }
}
