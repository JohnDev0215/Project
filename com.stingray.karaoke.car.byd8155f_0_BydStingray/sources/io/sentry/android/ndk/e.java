package io.sentry.android.ndk;

import A7.m;
import android.util.Log;
import io.sentry.android.replay.o;
import io.sentry.android.replay.x;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int l;

    public /* synthetic */ e(int i7) {
        this.l = i7;
    }

    private final void a() {
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Object, m7.f] */
    @Override // java.lang.Runnable
    public final void run() {
        Field field;
        switch (this.l) {
            case 0:
                SentryNdk.lambda$static$0();
                break;
            case 1:
                try {
                    Object value = x.f16461b.getValue();
                    if (value != null && (field = (Field) x.c.getValue()) != null) {
                        Object obj = field.get(value);
                        m.d(obj, "null cannot be cast to non-null type java.util.ArrayList<android.view.View>{ kotlin.collections.TypeAliasesKt.ArrayList<android.view.View> }");
                        io.sentry.android.replay.m mVar = o.c;
                        mVar.addAll((ArrayList) obj);
                        field.set(value, mVar);
                        break;
                    }
                } catch (Throwable th) {
                    Log.w("WindowManagerSpy", th);
                    return;
                }
                break;
        }
    }
}
