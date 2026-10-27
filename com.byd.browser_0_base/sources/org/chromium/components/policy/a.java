package org.chromium.components.policy;

import android.content.SharedPreferences;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: PolicyCache.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a {
    public static a c;
    public SharedPreferences a;
    public final ThreadUtils.a b = new ThreadUtils.a();

    /* JADX INFO: renamed from: org.chromium.components.policy.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PolicyCache.java */
    public enum EnumC0141a {
        Integer,
        Boolean,
        String,
        List,
        Dict
    }
}
