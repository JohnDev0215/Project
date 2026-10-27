package net.bytebuddy.implementation.auxiliary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.SyntheticState;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.utility.RandomString;

/* JADX INFO: loaded from: classes3.dex */
public interface AuxiliaryType {
    public static final ModifierContributor.ForType[] DEFAULT_TYPE_MODIFIER = {SyntheticState.SYNTHETIC};

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    public @interface SignatureRelevant {
    }

    DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory);

    public interface NamingStrategy {
        String name(TypeDescription typeDescription);

        public static class SuffixingRandom implements NamingStrategy {
            private final RandomString randomString = new RandomString();
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
                return str != null ? str.equals(str2) : str2 == null;
            }

            public int hashCode() {
                String str = this.suffix;
                return 59 + (str == null ? 43 : str.hashCode());
            }

            public SuffixingRandom(String str) {
                this.suffix = str;
            }

            @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType.NamingStrategy
            public String name(TypeDescription typeDescription) {
                return String.format("%s$%s$%s", typeDescription.getName(), this.suffix, this.randomString.nextString());
            }
        }
    }
}
