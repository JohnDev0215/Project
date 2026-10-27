package androidx.test.runner.intercepting;

import android.app.Activity;
import android.content.Intent;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface InterceptingActivityFactory {
    Activity create(ClassLoader classLoader, String str, Intent intent);

    boolean shouldIntercept(ClassLoader classLoader, String str, Intent intent);
}
