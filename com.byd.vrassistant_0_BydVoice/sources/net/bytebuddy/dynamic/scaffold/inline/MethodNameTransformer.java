package net.bytebuddy.dynamic.scaffold.inline;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.utility.RandomString;

/* JADX INFO: loaded from: classes3.dex */
public interface MethodNameTransformer {
    String transform(MethodDescription methodDescription);

    public static class Suffixing implements MethodNameTransformer {
        private static final String DEFAULT_SUFFIX = "original$";
        private final String suffix;

        protected boolean canEqual(Object obj) {
            return obj instanceof Suffixing;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Suffixing)) {
                return false;
            }
            Suffixing suffixing = (Suffixing) obj;
            if (!suffixing.canEqual(this)) {
                return false;
            }
            String str = this.suffix;
            String str2 = suffixing.suffix;
            return str != null ? str.equals(str2) : str2 == null;
        }

        public int hashCode() {
            String str = this.suffix;
            return 59 + (str == null ? 43 : str.hashCode());
        }

        public static MethodNameTransformer withRandomSuffix() {
            return new Suffixing(DEFAULT_SUFFIX + RandomString.make());
        }

        public Suffixing(String str) {
            this.suffix = str;
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer
        public String transform(MethodDescription methodDescription) {
            return String.format("%s$%s", methodDescription.getInternalName(), this.suffix);
        }
    }

    public static class Prefixing implements MethodNameTransformer {
        private static final String DEFAULT_PREFIX = "original";
        private final String prefix;

        protected boolean canEqual(Object obj) {
            return obj instanceof Prefixing;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Prefixing)) {
                return false;
            }
            Prefixing prefixing = (Prefixing) obj;
            if (!prefixing.canEqual(this)) {
                return false;
            }
            String str = this.prefix;
            String str2 = prefixing.prefix;
            return str != null ? str.equals(str2) : str2 == null;
        }

        public int hashCode() {
            String str = this.prefix;
            return 59 + (str == null ? 43 : str.hashCode());
        }

        public Prefixing() {
            this(DEFAULT_PREFIX);
        }

        public Prefixing(String str) {
            this.prefix = str;
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer
        public String transform(MethodDescription methodDescription) {
            return String.format("%s%s", this.prefix, methodDescription.getInternalName());
        }
    }
}
