package com.amplifyframework.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class Casing {

    public enum CaseType {
        SCREAMING_SNAKE_CASE,
        CAMEL_CASE,
        PASCAL_CASE
    }

    private Casing() {
    }

    @NonNull
    public static CasingSource from(@NonNull CaseType caseType) {
        Objects.requireNonNull(caseType);
        return new CasingSource(caseType, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static String fromPascalCase(@Nullable String str, @NonNull CaseType caseType) {
        Objects.requireNonNull(caseType);
        if (str == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$util$Casing$CaseType[caseType.ordinal()];
        if (i == 1) {
            return toScreamingSnake(str);
        }
        if (i == 2) {
            return pascalToCamel(str);
        }
        if (i == 3) {
            return str;
        }
        throw new IllegalStateException("No such casing = " + caseType);
    }

    /* JADX INFO: renamed from: com.amplifyframework.util.Casing$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$util$Casing$CaseType;

        static {
            int[] iArr = new int[CaseType.values().length];
            $SwitchMap$com$amplifyframework$util$Casing$CaseType = iArr;
            try {
                iArr[CaseType.SCREAMING_SNAKE_CASE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$util$Casing$CaseType[CaseType.CAMEL_CASE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$util$Casing$CaseType[CaseType.PASCAL_CASE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static String fromCamelCase(@Nullable String str, @NonNull CaseType caseType) {
        Objects.requireNonNull(caseType);
        if (str == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$util$Casing$CaseType[caseType.ordinal()];
        if (i == 1) {
            return toScreamingSnake(str);
        }
        if (i == 2) {
            return str;
        }
        if (i == 3) {
            return camelToPascal(str);
        }
        throw new IllegalStateException("No such casing = " + caseType);
    }

    private static String toScreamingSnake(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (Character.isLetterOrDigit(c)) {
                if (Character.isLowerCase(c)) {
                    sb.append(Character.toUpperCase(c));
                } else if (i != 0) {
                    sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
                    sb.append(c);
                } else {
                    sb.append(c);
                }
            } else {
                sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static String fromScreamingSnakeCase(@Nullable String str, CaseType caseType) {
        int i;
        Objects.requireNonNull(caseType);
        if (str == null || str.length() == 0 || (i = AnonymousClass1.$SwitchMap$com$amplifyframework$util$Casing$CaseType[caseType.ordinal()]) == 1) {
            return str;
        }
        if (i == 2) {
            return screamingSnakeToCamel(str);
        }
        if (i == 3) {
            return screamingSnakeToPascal(str);
        }
        throw new IllegalStateException("Unknown target casing = " + caseType);
    }

    @Nullable
    private static String screamingSnakeToCamel(@Nullable String str) {
        return (str == null || str.length() == 0) ? str : pascalToCamel(screamingSnakeToPascal(str));
    }

    @Nullable
    private static String pascalToCamel(@Nullable String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(0, 1).toLowerCase(Locale.getDefault()) + str.substring(1);
    }

    @Nullable
    private static String camelToPascal(@Nullable String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(0, 1).toUpperCase(Locale.getDefault()) + str.substring(1);
    }

    @Nullable
    private static String screamingSnakeToPascal(@Nullable String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String[] strArrSplit = str.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArrSplit) {
            sb.append(capitalize(str2));
        }
        return sb.toString();
    }

    public static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(0, 1).toUpperCase(Locale.getDefault()) + str.substring(1).toLowerCase(Locale.getDefault());
    }

    public static String capitalizeFirst(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(0, 1).toUpperCase(Locale.getDefault()) + str.substring(1);
    }

    public static final class CasingSource {
        private final CaseType sourceCasing;

        /* synthetic */ CasingSource(CaseType caseType, AnonymousClass1 anonymousClass1) {
            this(caseType);
        }

        private CasingSource(CaseType caseType) {
            this.sourceCasing = caseType;
        }

        public CasingTarget to(CaseType caseType) {
            return new CasingTarget(this.sourceCasing, caseType, null);
        }
    }

    public static final class CasingTarget {
        private CaseType sourceCasing;
        private CaseType targetCasing;

        /* synthetic */ CasingTarget(CaseType caseType, CaseType caseType2, AnonymousClass1 anonymousClass1) {
            this(caseType, caseType2);
        }

        private CasingTarget(CaseType caseType, CaseType caseType2) {
            this.sourceCasing = caseType;
            this.targetCasing = caseType2;
        }

        public String convert(String str) {
            if (str == null || str.length() == 0) {
                return str;
            }
            int i = AnonymousClass1.$SwitchMap$com$amplifyframework$util$Casing$CaseType[this.sourceCasing.ordinal()];
            if (i == 1) {
                return Casing.fromScreamingSnakeCase(str, this.targetCasing);
            }
            if (i == 2) {
                return Casing.fromCamelCase(str, this.targetCasing);
            }
            if (i == 3) {
                return Casing.fromPascalCase(str, this.targetCasing);
            }
            throw new IllegalStateException("Unknown source casing = " + this.sourceCasing);
        }
    }
}
