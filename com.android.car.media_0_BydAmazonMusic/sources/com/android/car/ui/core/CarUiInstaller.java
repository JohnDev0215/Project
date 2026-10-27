package com.android.car.ui.core;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.android.car.ui.core.CarUiInstaller;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.IntFunction;
import spi.d0;
import spi.i1;

/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(28)
@SuppressLint({"LogConditional"})
public class CarUiInstaller extends ContentProvider {
    public static final HashSet<Application> a = new HashSet<>();

    public static Object a(ClassLoader classLoader, Class cls, String str, Object obj, Object[] objArr) {
        try {
            Method declaredMethod = classLoader.loadClass(cls.getName()).getDeclaredMethod(str, (Class[]) Arrays.stream(objArr).map(d0.k).toArray(new IntFunction() { // from class: spi.h1
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    HashSet<Application> hashSet = CarUiInstaller.a;
                    return new Class[i];
                }
            }));
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(obj, objArr);
        } catch (ReflectiveOperationException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.content.ContentProvider
    public final int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final String getType(@NonNull Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        boolean z;
        Context context = getContext();
        if (context == null || !(context.getApplicationContext() instanceof Application)) {
            Log.e("CarUiInstaller", "CarUiInstaller had a null context, unable to call register! Need app to call register by itself");
            return false;
        }
        Application application = (Application) context.getApplicationContext();
        HashSet<Application> hashSet = a;
        synchronized (hashSet) {
            z = hashSet.add(application) ? false : true;
        }
        if (!z) {
            application.registerActivityLifecycleCallbacks(new i1());
        }
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }
}
