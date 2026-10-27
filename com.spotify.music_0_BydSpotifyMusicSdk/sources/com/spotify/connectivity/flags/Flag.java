package com.spotify.connectivity.flags;

import com.spotify.connectivity.productstate.RxProductState;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class Flag<T extends Serializable> {
    public static final Companion Companion = new Companion(null);
    private static final Map<String, Flag<?>> flagIdentifiers = new HashMap(64);
    private static final Set<Integer> identifierSet = Collections.synchronizedSet(new HashSet());
    private final String defaultValue;
    private final int hash;
    private final String identifier;
    private final Overridable overridable;
    private final Source source;
    private final Class<T> type;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getIdentifierSet$annotations() {
        }

        public final Flag<?> getFlagForIdentifier(String str) {
            v03.j(str, "identifier");
            Flag<?> flag = (Flag) Flag.flagIdentifiers.get(str);
            if (flag != null) {
                return flag;
            }
            throw new IllegalStateException(u4.j("Flag ", str, " does not exist"));
        }

        public final void resetFlagIdentifiers() {
            Flag.flagIdentifiers.clear();
            Flag.identifierSet.clear();
        }
    }

    public Flag(Class<T> cls, String str, Source source, Overridable overridable, String str2) {
        v03.j(cls, RxProductState.Keys.KEY_TYPE);
        v03.j(str, "identifier");
        v03.j(source, "source");
        v03.j(overridable, "overridable");
        v03.j(str2, "defaultValue");
        Map<String, Flag<?>> map = flagIdentifiers;
        if (map.containsKey(str)) {
            throw new AssertionError("There can be only one feature flag instance with identifier ".concat(str));
        }
        map.put(str, this);
        int iGenerateHashCode = generateHashCode(str);
        this.hash = iGenerateHashCode;
        if (!identifierSet.add(Integer.valueOf(iGenerateHashCode))) {
            throw new AssertionError("There can be only one feature flag instance with the same hashcode identifier ".concat(str));
        }
        this.type = cls;
        this.source = source;
        this.identifier = str;
        this.overridable = overridable;
        this.defaultValue = str2;
    }

    private final int generateHashCode(String str) {
        int length = str.length();
        if (length <= 0) {
            return 0;
        }
        int iCharAt = 0;
        for (int i = 0; i < length; i++) {
            iCharAt = (iCharAt * 31) + str.charAt(i);
        }
        return iCharAt;
    }

    public static final Flag<?> getFlagForIdentifier(String str) {
        return Companion.getFlagForIdentifier(str);
    }

    public static final void resetFlagIdentifiers() {
        Companion.resetFlagIdentifiers();
    }

    public final String getDefaultValue() {
        return this.defaultValue;
    }

    public abstract T getDisabledValue();

    public abstract T getEnabledValue();

    public final int getHash() {
        return this.hash;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final Source getSource() {
        return this.source;
    }

    public final Class<T> getType() {
        return this.type;
    }

    public abstract boolean isEnabled(T t);

    public final boolean isOverridableAt(Overridable overridable) {
        v03.j(overridable, "level");
        return this.overridable.compareTo(overridable) >= 0;
    }

    public abstract T mapValue(String str);
}
