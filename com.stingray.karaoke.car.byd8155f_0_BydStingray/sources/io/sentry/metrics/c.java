package io.sentry.metrics;

import io.sentry.util.g;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f16618a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f16619b = 0;

    static {
        Pattern.compile("\\W+");
        Pattern.compile("[^\\w\\-.]+");
        Pattern.compile("[^\\w\\-./]+");
        f16618a = (long) ((new g().a(24) / 1.6777216E7f) * 10000.0f);
    }
}
