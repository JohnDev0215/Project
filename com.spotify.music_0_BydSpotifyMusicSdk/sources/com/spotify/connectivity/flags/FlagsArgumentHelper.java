package com.spotify.connectivity.flags;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import p.lc;
import p.v03;
import p.ww0;

/* JADX INFO: loaded from: classes.dex */
public final class FlagsArgumentHelper {
    private static final String FLAGS = "FlagsArgumentHelper.Flags";
    public static final FlagsArgumentHelper INSTANCE = new FlagsArgumentHelper();

    private FlagsArgumentHelper() {
    }

    public static final void addFlagsArgument(Intent intent, Flags flags) {
        v03.j(intent, "intent");
        v03.j(flags, "flags");
        intent.putExtra(FLAGS, flags);
    }

    public static final void addFlagsFromOnSaveInstanceState(Bundle bundle, Flags flags) {
        v03.j(bundle, "bundle");
        bundle.putParcelable(FLAGS, flags);
    }

    public static final void addFlagsToBundle(Bundle bundle, Flags flags) {
        v03.j(bundle, "bundle");
        v03.j(flags, "flags");
        bundle.putParcelable(FLAGS, flags);
    }

    public static final Flags getFlags(ww0 ww0Var) {
        v03.j(ww0Var, "fragment");
        lc.a(ww0Var);
        Bundle bundle = ww0Var.n;
        lc.b(bundle, "The Fragment must have an argument Bundle");
        Flags flags = getFlags(bundle);
        lc.b(flags, "The Fragment must have a Flags argument");
        if (flags != null) {
            return flags;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public static final Flags getFlagsIfPresent(ww0 ww0Var) {
        v03.j(ww0Var, "fragment");
        lc.a(ww0Var);
        Bundle bundle = ww0Var.n;
        if (bundle == null || !bundle.containsKey(FLAGS)) {
            return null;
        }
        return (Flags) bundle.getParcelable(FLAGS);
    }

    public static final void updateFlagsIfNecessary(ww0 ww0Var, Flags flags) {
        v03.j(ww0Var, "fragment");
        Bundle bundle = ww0Var.n;
        if (bundle == null || !bundle.containsKey(FLAGS)) {
            return;
        }
        bundle.putParcelable(FLAGS, flags);
    }

    public final boolean hasFlags(Intent intent) {
        v03.j(intent, "intent");
        return intent.hasExtra(FLAGS);
    }

    public static final void addFlagsArgument(ww0 ww0Var, Flags flags) {
        v03.j(ww0Var, "fragment");
        v03.j(flags, "flags");
        Bundle bundle = ww0Var.n;
        if (bundle == null) {
            bundle = new Bundle();
            ww0Var.E(bundle);
        }
        bundle.putParcelable(FLAGS, flags);
    }

    public static final Flags getFlags(Activity activity) {
        v03.j(activity, "activity");
        lc.a(activity);
        Intent intent = activity.getIntent();
        lc.b(intent, "The activity must have an Intent");
        v03.i(intent, "intent");
        return getFlags(intent);
    }

    public static final Flags getFlags(Intent intent) {
        v03.j(intent, "intent");
        lc.b(intent, "The Intent must not be null");
        Flags flags = (Flags) intent.getParcelableExtra(FLAGS);
        lc.b(flags, "The Intent must have a Flags argument. Actual intent: " + intent);
        v03.h(flags);
        return flags;
    }

    public static final Flags getFlags(Bundle bundle) {
        lc.a(bundle);
        v03.h(bundle);
        lc.f("The Bundle must have a Flags argument", bundle.containsKey(FLAGS));
        return (Flags) bundle.getParcelable(FLAGS);
    }
}
