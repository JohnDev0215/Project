package androidx.compose.ui.tooling;

import A.C0044v0;
import A.L;
import A7.m;
import Q8.h;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import b0.b;
import h.n;
import i.AbstractC1396c;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import y7.C2749a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/tooling/PreviewActivity;", "Lh/n;", "<init>", "()V", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PreviewActivity extends n {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final String f11695F = "PreviewActivity";

    @Override // h.n, android.app.Activity
    public final void onCreate(Bundle bundle) {
        String stringExtra;
        Class<?> cls;
        super.onCreate(bundle);
        int i7 = getApplicationInfo().flags & 2;
        String str = this.f11695F;
        if (i7 == 0) {
            Log.d(str, "Application is not debuggable. Compose Preview not allowed.");
            finish();
            return;
        }
        Intent intent = getIntent();
        if (intent == null || (stringExtra = intent.getStringExtra("composable")) == null) {
            return;
        }
        Log.d(str, "PreviewActivity has composable ".concat(stringExtra));
        String strE1 = h.e1(stringExtra, '.');
        String strA1 = h.a1('.', stringExtra, stringExtra);
        String stringExtra2 = getIntent().getStringExtra("parameterProviderClassName");
        if (stringExtra2 == null) {
            Log.d(str, "Previewing '" + strA1 + "' without a parameter provider.");
            AbstractC1396c.a(this, new b(-840626948, new L(strE1, 13, strA1), true));
            return;
        }
        Log.d(str, "Previewing '" + strA1 + "' with parameter provider: '" + stringExtra2 + '\'');
        try {
            cls = Class.forName(stringExtra2);
        } catch (ClassNotFoundException e10) {
            h6.n.i("PreviewLogger", "Unable to find PreviewProvider '" + stringExtra2 + '\'', e10);
            cls = null;
        }
        getIntent().getIntExtra("parameterProviderIndex", -1);
        int i10 = 0;
        if (cls == null) {
            AbstractC1396c.a(this, new b(-1901447514, new C0044v0(strE1, strA1, new Object[0], 4), true));
            return;
        }
        try {
            Constructor<?>[] constructors = cls.getConstructors();
            int length = constructors.length;
            Constructor<?> constructor = null;
            boolean z6 = false;
            while (true) {
                if (i10 < length) {
                    Constructor<?> constructor2 = constructors[i10];
                    if (constructor2.getParameterTypes().length == 0) {
                        if (z6) {
                            break;
                        }
                        z6 = true;
                        constructor = constructor2;
                    }
                    i10++;
                } else if (!z6) {
                }
            }
            constructor = null;
            if (constructor == null) {
                throw new IllegalArgumentException("PreviewParameterProvider constructor can not have parameters");
            }
            constructor.setAccessible(true);
            m.d(constructor.newInstance(null), "null cannot be cast to non-null type androidx.compose.ui.tooling.preview.PreviewParameterProvider<*>");
            throw new ClassCastException();
        } catch (C2749a unused) {
            throw new IllegalStateException("Deploying Compose Previews with PreviewParameterProvider arguments requires adding a dependency to the kotlin-reflect library.\nConsider adding 'debugImplementation \"org.jetbrains.kotlin:kotlin-reflect:$kotlin_version\"' to the module's build.gradle.");
        }
    }
}
