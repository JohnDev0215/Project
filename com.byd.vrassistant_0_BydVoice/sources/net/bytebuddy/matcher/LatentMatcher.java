package net.bytebuddy.matcher;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public interface LatentMatcher<T> {
    ElementMatcher<? super T> resolve(TypeDescription typeDescription);

    public enum ForSelfDeclaredMethod implements LatentMatcher<MethodDescription> {
        DECLARED(false),
        NOT_DECLARED(true);

        private final boolean inverted;

        ForSelfDeclaredMethod(boolean z) {
            this.inverted = z;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super MethodDescription> resolve(TypeDescription typeDescription) {
            if (this.inverted) {
                return ElementMatchers.not(ElementMatchers.isDeclaredBy(typeDescription));
            }
            return ElementMatchers.isDeclaredBy(typeDescription);
        }
    }

    public static class Resolved<S> implements LatentMatcher<S> {
        private final ElementMatcher<? super S> matcher;

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
            ElementMatcher<? super S> elementMatcher = this.matcher;
            ElementMatcher<? super S> elementMatcher2 = resolved.matcher;
            return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
        }

        public int hashCode() {
            ElementMatcher<? super S> elementMatcher = this.matcher;
            return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
        }

        public Resolved(ElementMatcher<? super S> elementMatcher) {
            this.matcher = elementMatcher;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super S> resolve(TypeDescription typeDescription) {
            return this.matcher;
        }
    }

    public static class ForFieldToken implements LatentMatcher<FieldDescription> {
        private final FieldDescription.Token token;

        protected boolean canEqual(Object obj) {
            return obj instanceof ForFieldToken;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForFieldToken)) {
                return false;
            }
            ForFieldToken forFieldToken = (ForFieldToken) obj;
            if (!forFieldToken.canEqual(this)) {
                return false;
            }
            FieldDescription.Token token = this.token;
            FieldDescription.Token token2 = forFieldToken.token;
            return token != null ? token.equals(token2) : token2 == null;
        }

        public int hashCode() {
            FieldDescription.Token token = this.token;
            return 59 + (token == null ? 43 : token.hashCode());
        }

        public ForFieldToken(FieldDescription.Token token) {
            this.token = token;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super FieldDescription> resolve(TypeDescription typeDescription) {
            return new ResolvedMatcher(this.token.asSignatureToken(typeDescription));
        }

        protected static class ResolvedMatcher implements ElementMatcher<FieldDescription> {
            private final FieldDescription.SignatureToken signatureToken;

            protected boolean canEqual(Object obj) {
                return obj instanceof ResolvedMatcher;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ResolvedMatcher)) {
                    return false;
                }
                ResolvedMatcher resolvedMatcher = (ResolvedMatcher) obj;
                if (!resolvedMatcher.canEqual(this)) {
                    return false;
                }
                FieldDescription.SignatureToken signatureToken = this.signatureToken;
                FieldDescription.SignatureToken signatureToken2 = resolvedMatcher.signatureToken;
                return signatureToken != null ? signatureToken.equals(signatureToken2) : signatureToken2 == null;
            }

            public int hashCode() {
                FieldDescription.SignatureToken signatureToken = this.signatureToken;
                return 59 + (signatureToken == null ? 43 : signatureToken.hashCode());
            }

            protected ResolvedMatcher(FieldDescription.SignatureToken signatureToken) {
                this.signatureToken = signatureToken;
            }

            @Override // net.bytebuddy.matcher.ElementMatcher
            public boolean matches(FieldDescription fieldDescription) {
                return fieldDescription.asSignatureToken().equals(this.signatureToken);
            }
        }
    }

    public static class ForMethodToken implements LatentMatcher<MethodDescription> {
        private final MethodDescription.Token token;

        protected boolean canEqual(Object obj) {
            return obj instanceof ForMethodToken;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForMethodToken)) {
                return false;
            }
            ForMethodToken forMethodToken = (ForMethodToken) obj;
            if (!forMethodToken.canEqual(this)) {
                return false;
            }
            MethodDescription.Token token = this.token;
            MethodDescription.Token token2 = forMethodToken.token;
            return token != null ? token.equals(token2) : token2 == null;
        }

        public int hashCode() {
            MethodDescription.Token token = this.token;
            return 59 + (token == null ? 43 : token.hashCode());
        }

        public ForMethodToken(MethodDescription.Token token) {
            this.token = token;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super MethodDescription> resolve(TypeDescription typeDescription) {
            return new ResolvedMatcher(this.token.asSignatureToken(typeDescription));
        }

        protected static class ResolvedMatcher implements ElementMatcher<MethodDescription> {
            private final MethodDescription.SignatureToken signatureToken;

            protected boolean canEqual(Object obj) {
                return obj instanceof ResolvedMatcher;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ResolvedMatcher)) {
                    return false;
                }
                ResolvedMatcher resolvedMatcher = (ResolvedMatcher) obj;
                if (!resolvedMatcher.canEqual(this)) {
                    return false;
                }
                MethodDescription.SignatureToken signatureToken = this.signatureToken;
                MethodDescription.SignatureToken signatureToken2 = resolvedMatcher.signatureToken;
                return signatureToken != null ? signatureToken.equals(signatureToken2) : signatureToken2 == null;
            }

            public int hashCode() {
                MethodDescription.SignatureToken signatureToken = this.signatureToken;
                return 59 + (signatureToken == null ? 43 : signatureToken.hashCode());
            }

            protected ResolvedMatcher(MethodDescription.SignatureToken signatureToken) {
                this.signatureToken = signatureToken;
            }

            @Override // net.bytebuddy.matcher.ElementMatcher
            public boolean matches(MethodDescription methodDescription) {
                return methodDescription.asSignatureToken().equals(this.signatureToken);
            }
        }
    }

    public static class Conjunction<S> implements LatentMatcher<S> {
        private final List<? extends LatentMatcher<? super S>> matchers;

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
            List<? extends LatentMatcher<? super S>> list = this.matchers;
            List<? extends LatentMatcher<? super S>> list2 = conjunction.matchers;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<? extends LatentMatcher<? super S>> list = this.matchers;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Conjunction(LatentMatcher<? super S>... latentMatcherArr) {
            this(Arrays.asList(latentMatcherArr));
        }

        public Conjunction(List<? extends LatentMatcher<? super S>> list) {
            this.matchers = list;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super S> resolve(TypeDescription typeDescription) {
            ElementMatcher.Junction junctionAny = ElementMatchers.any();
            Iterator<? extends LatentMatcher<? super S>> it = this.matchers.iterator();
            while (it.hasNext()) {
                junctionAny = junctionAny.and(it.next().resolve(typeDescription));
            }
            return junctionAny;
        }
    }

    public static class Disjunction<S> implements LatentMatcher<S> {
        private final List<? extends LatentMatcher<? super S>> matchers;

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
            List<? extends LatentMatcher<? super S>> list = this.matchers;
            List<? extends LatentMatcher<? super S>> list2 = disjunction.matchers;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<? extends LatentMatcher<? super S>> list = this.matchers;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Disjunction(LatentMatcher<? super S>... latentMatcherArr) {
            this(Arrays.asList(latentMatcherArr));
        }

        public Disjunction(List<? extends LatentMatcher<? super S>> list) {
            this.matchers = list;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super S> resolve(TypeDescription typeDescription) {
            ElementMatcher.Junction junctionNone = ElementMatchers.none();
            Iterator<? extends LatentMatcher<? super S>> it = this.matchers.iterator();
            while (it.hasNext()) {
                junctionNone = junctionNone.or(it.next().resolve(typeDescription));
            }
            return junctionNone;
        }
    }
}
