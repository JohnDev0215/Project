package com.spotify.connectivity.productstateflags;

import com.spotify.connectivity.flags.BooleanFlag;
import com.spotify.connectivity.flags.IntegerFlag;
import com.spotify.connectivity.flags.Overridable;
import com.spotify.connectivity.flags.StringFlag;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class FlagMaker {
    public static final FlagMaker INSTANCE = new FlagMaker();

    private FlagMaker() {
    }

    public final BooleanFlag makeBooleanProductStateFlag(String str, Overridable overridable) {
        v03.j(str, "identifier");
        v03.j(overridable, "overridable");
        return new BooleanFlag(str, new ProductStateSource(str), overridable);
    }

    public final IntegerFlag makeIntegerProductStateFlag(String str, Overridable overridable) {
        v03.j(str, "identifier");
        v03.j(overridable, "overridable");
        return new IntegerFlag(str, new ProductStateSource(str), overridable, 0, 8, null);
    }

    public final StringFlag makeStringProductStateFlag(String str, Overridable overridable) {
        v03.j(str, "identifier");
        v03.j(overridable, "overridable");
        return new StringFlag(str, new ProductStateSource(str), overridable);
    }
}
