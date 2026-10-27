package com.spotify.connectivity.flags;

import android.os.Parcelable;
import java.io.Serializable;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface Flags extends Parcelable {

    public static final class Util {
        public static final Util INSTANCE = new Util();

        private Util() {
        }

        public final boolean hasChangedValue(Flag<?> flag, Flags flags, Flags flags2) {
            v03.j(flag, "flag");
            v03.j(flags2, "newFlags");
            return (flags == null || flags.isSame(flags2, flag)) ? false : true;
        }
    }

    <T extends Serializable> T get(Flag<T> flag);

    <T extends Serializable> T getNonOverriddenValue(Flag<T> flag);

    <T extends Serializable> boolean isEnabled(Flag<T> flag);

    boolean isLoaded();

    boolean isOverridden(Flag<?> flag);

    boolean isSame(Flags flags, Flag<?> flag);
}
