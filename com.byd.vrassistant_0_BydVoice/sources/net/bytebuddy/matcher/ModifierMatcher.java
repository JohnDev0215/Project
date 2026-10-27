package net.bytebuddy.matcher;

import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class ModifierMatcher<T extends ModifierReviewable> extends ElementMatcher.Junction.AbstractBase<T> {
    private final Mode mode;

    protected boolean canEqual(Object obj) {
        return obj instanceof ModifierMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ModifierMatcher)) {
            return false;
        }
        ModifierMatcher modifierMatcher = (ModifierMatcher) obj;
        if (!modifierMatcher.canEqual(this)) {
            return false;
        }
        Mode mode = this.mode;
        Mode mode2 = modifierMatcher.mode;
        return mode != null ? mode.equals(mode2) : mode2 == null;
    }

    public int hashCode() {
        Mode mode = this.mode;
        return 59 + (mode == null ? 43 : mode.hashCode());
    }

    public ModifierMatcher(Mode mode) {
        this.mode = mode;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return (t.getModifiers() & this.mode.getModifiers()) != 0;
    }

    public String toString() {
        return this.mode.getDescription();
    }

    public enum Mode {
        PUBLIC(1, "isPublic()"),
        PROTECTED(4, "isProtected()"),
        PRIVATE(2, "isPrivate()"),
        FINAL(16, "isFinal()"),
        STATIC(8, "isStatic()"),
        SYNCHRONIZED(32, "isSynchronized()"),
        NATIVE(256, "isNative()"),
        STRICT(2048, "isStrict()"),
        VAR_ARGS(128, "isVarArgs()"),
        SYNTHETIC(4096, "isSynthetic()"),
        BRIDGE(64, "isBridge()"),
        ABSTRACT(1024, "isAbstract()"),
        INTERFACE(512, "isInterface()"),
        ANNOTATION(8192, "isAnnotation()"),
        VOLATILE(64, "isVolatile()"),
        TRANSIENT(128, "isTransient()"),
        MANDATED(32768, "isMandated()"),
        ENUMERATION(16384, "isEnum()");

        private final String description;
        private final int modifiers;

        Mode(int i, String str) {
            this.modifiers = i;
            this.description = str;
        }

        protected String getDescription() {
            return this.description;
        }

        protected int getModifiers() {
            return this.modifiers;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "ModifierMatcher.Mode." + name();
        }
    }
}
