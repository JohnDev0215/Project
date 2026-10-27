package net.bytebuddy;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.utility.RandomString;

/* JADX INFO: loaded from: classes3.dex */
public interface NamingStrategy {
    String rebase(TypeDescription typeDescription);

    String redefine(TypeDescription typeDescription);

    String subclass(TypeDescription.Generic generic);

    public static abstract class AbstractBase implements NamingStrategy {
        protected abstract String name(TypeDescription typeDescription);

        @Override // net.bytebuddy.NamingStrategy
        public String subclass(TypeDescription.Generic generic) {
            return name(generic.asErasure());
        }

        @Override // net.bytebuddy.NamingStrategy
        public String redefine(TypeDescription typeDescription) {
            return typeDescription.getName();
        }

        @Override // net.bytebuddy.NamingStrategy
        public String rebase(TypeDescription typeDescription) {
            return typeDescription.getName();
        }
    }

    public static class SuffixingRandom extends AbstractBase {
        public static final String BYTE_BUDDY_RENAME_PACKAGE = "net.bytebuddy.renamed";
        private static final String JAVA_PACKAGE = "java.";
        public static final String NO_PREFIX = "";
        private final BaseNameResolver baseNameResolver;
        private final String javaLangPackagePrefix;
        private final RandomString randomString;
        private final String suffix;

        protected boolean canEqual(Object obj) {
            return obj instanceof SuffixingRandom;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SuffixingRandom)) {
                return false;
            }
            SuffixingRandom suffixingRandom = (SuffixingRandom) obj;
            if (!suffixingRandom.canEqual(this)) {
                return false;
            }
            String str = this.suffix;
            String str2 = suffixingRandom.suffix;
            if (str != null ? !str.equals(str2) : str2 != null) {
                return false;
            }
            String str3 = this.javaLangPackagePrefix;
            String str4 = suffixingRandom.javaLangPackagePrefix;
            if (str3 != null ? !str3.equals(str4) : str4 != null) {
                return false;
            }
            BaseNameResolver baseNameResolver = this.baseNameResolver;
            BaseNameResolver baseNameResolver2 = suffixingRandom.baseNameResolver;
            return baseNameResolver != null ? baseNameResolver.equals(baseNameResolver2) : baseNameResolver2 == null;
        }

        public int hashCode() {
            String str = this.suffix;
            int iHashCode = str == null ? 43 : str.hashCode();
            String str2 = this.javaLangPackagePrefix;
            int iHashCode2 = ((iHashCode + 59) * 59) + (str2 == null ? 43 : str2.hashCode());
            BaseNameResolver baseNameResolver = this.baseNameResolver;
            return (iHashCode2 * 59) + (baseNameResolver != null ? baseNameResolver.hashCode() : 43);
        }

        public SuffixingRandom(String str) {
            this(str, BaseNameResolver.ForUnnamedType.INSTANCE);
        }

        public SuffixingRandom(String str, String str2) {
            this(str, BaseNameResolver.ForUnnamedType.INSTANCE, str2);
        }

        public SuffixingRandom(String str, BaseNameResolver baseNameResolver) {
            this(str, baseNameResolver, BYTE_BUDDY_RENAME_PACKAGE);
        }

        public SuffixingRandom(String str, BaseNameResolver baseNameResolver, String str2) {
            this.suffix = str;
            this.baseNameResolver = baseNameResolver;
            this.javaLangPackagePrefix = str2;
            this.randomString = new RandomString();
        }

        @Override // net.bytebuddy.NamingStrategy.AbstractBase
        protected String name(TypeDescription typeDescription) {
            String strResolve = this.baseNameResolver.resolve(typeDescription);
            if (strResolve.startsWith(JAVA_PACKAGE) && !this.javaLangPackagePrefix.equals("")) {
                strResolve = this.javaLangPackagePrefix + "." + strResolve;
            }
            return String.format("%s$%s$%s", strResolve, this.suffix, this.randomString.nextString());
        }

        public interface BaseNameResolver {
            String resolve(TypeDescription typeDescription);

            public enum ForUnnamedType implements BaseNameResolver {
                INSTANCE;

                @Override // net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver
                public String resolve(TypeDescription typeDescription) {
                    return typeDescription.getName();
                }
            }

            public static class ForGivenType implements BaseNameResolver {
                private final TypeDescription typeDescription;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForGivenType;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForGivenType)) {
                        return false;
                    }
                    ForGivenType forGivenType = (ForGivenType) obj;
                    if (!forGivenType.canEqual(this)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.typeDescription;
                    TypeDescription typeDescription2 = forGivenType.typeDescription;
                    return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                }

                public int hashCode() {
                    TypeDescription typeDescription = this.typeDescription;
                    return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                }

                public ForGivenType(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                @Override // net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver
                public String resolve(TypeDescription typeDescription) {
                    return this.typeDescription.getName();
                }
            }

            public static class ForFixedValue implements BaseNameResolver {
                private final String name;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForFixedValue;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForFixedValue)) {
                        return false;
                    }
                    ForFixedValue forFixedValue = (ForFixedValue) obj;
                    if (!forFixedValue.canEqual(this)) {
                        return false;
                    }
                    String str = this.name;
                    String str2 = forFixedValue.name;
                    return str != null ? str.equals(str2) : str2 == null;
                }

                public int hashCode() {
                    String str = this.name;
                    return 59 + (str == null ? 43 : str.hashCode());
                }

                public ForFixedValue(String str) {
                    this.name = str;
                }

                @Override // net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver
                public String resolve(TypeDescription typeDescription) {
                    return this.name;
                }
            }
        }
    }

    public static class PrefixingRandom extends AbstractBase {
        private final String prefix;
        private final RandomString randomString = new RandomString();

        protected boolean canEqual(Object obj) {
            return obj instanceof PrefixingRandom;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PrefixingRandom)) {
                return false;
            }
            PrefixingRandom prefixingRandom = (PrefixingRandom) obj;
            if (!prefixingRandom.canEqual(this)) {
                return false;
            }
            String str = this.prefix;
            String str2 = prefixingRandom.prefix;
            return str != null ? str.equals(str2) : str2 == null;
        }

        public int hashCode() {
            String str = this.prefix;
            return 59 + (str == null ? 43 : str.hashCode());
        }

        public PrefixingRandom(String str) {
            this.prefix = str;
        }

        @Override // net.bytebuddy.NamingStrategy.AbstractBase
        protected String name(TypeDescription typeDescription) {
            return String.format("%s.%s$%s", this.prefix, typeDescription.getName(), this.randomString.nextString());
        }
    }
}
