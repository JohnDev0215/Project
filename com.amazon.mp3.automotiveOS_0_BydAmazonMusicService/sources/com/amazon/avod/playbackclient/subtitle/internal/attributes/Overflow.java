package com.amazon.avod.playbackclient.subtitle.internal.attributes;

import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum Overflow {
    VISIBLE,
    HIDDEN;

    private static final ImmutableMap<String, Overflow> LOOKUP = (ImmutableMap) Preconditions2.checkFullValueMapping(Overflow.class, ImmutableMap.of("visible", VISIBLE, "hidden", HIDDEN));

    public static boolean isValid(@Nullable String str) {
        return str != null && LOOKUP.containsKey(str);
    }

    @Nonnull
    public static Overflow lookup(@Nonnull String str) {
        Preconditions.checkNotNull(str, "Cannot lookup a null overflow");
        ImmutableMap<String, Overflow> immutableMap = LOOKUP;
        Preconditions.checkArgument(immutableMap.containsKey(str), "No Overflow enum exists for %s", str);
        return immutableMap.get(str);
    }
}
