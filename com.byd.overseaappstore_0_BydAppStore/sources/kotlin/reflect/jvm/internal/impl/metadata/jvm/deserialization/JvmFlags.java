package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class JvmFlags {
    private static final Flags.BooleanFlagField ARE_INTERFACE_METHOD_BODIES_INSIDE;
    private static final Flags.BooleanFlagField IS_ALL_COMPATIBILITY_MODE;

    @NotNull
    public static final JvmFlags INSTANCE = new JvmFlags();
    private static final Flags.BooleanFlagField IS_MOVED_FROM_INTERFACE_COMPANION = Flags.FlagField.booleanFirst();

    static {
        Flags.BooleanFlagField booleanFlagFieldBooleanFirst = Flags.FlagField.booleanFirst();
        ARE_INTERFACE_METHOD_BODIES_INSIDE = booleanFlagFieldBooleanFirst;
        IS_ALL_COMPATIBILITY_MODE = Flags.FlagField.booleanAfter(booleanFlagFieldBooleanFirst);
    }

    private JvmFlags() {
    }

    public final Flags.BooleanFlagField getIS_MOVED_FROM_INTERFACE_COMPANION() {
        return IS_MOVED_FROM_INTERFACE_COMPANION;
    }
}
