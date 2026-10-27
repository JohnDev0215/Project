package org.chromium.components.embedder_support.application;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.LayoutInflater;
import defpackage.wt3;
import defpackage.xz;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class ClassLoaderContextWrapperFactory {
    public static final WeakHashMap<Context, WeakReference<ClassLoaderContextWrapper>> a = new WeakHashMap<>();
    public static final Object b = new Object();

    public static class ClassLoaderContextWrapper extends ContextWrapper {
        public Context a;

        public ClassLoaderContextWrapper(Context context) {
            super(context);
        }

        public final void a() {
            if ((getBaseContext().getResources().getConfiguration().uiMode & 48) == 32) {
                WeakHashMap<Context, WeakReference<ClassLoaderContextWrapper>> weakHashMap = ClassLoaderContextWrapperFactory.a;
            } else {
                WeakHashMap<Context, WeakReference<ClassLoaderContextWrapper>> weakHashMap2 = ClassLoaderContextWrapperFactory.a;
            }
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final Context getApplicationContext() {
            if (this.a == null) {
                Context applicationContext = getBaseContext().getApplicationContext();
                if (applicationContext == getBaseContext()) {
                    this.a = this;
                } else {
                    WeakHashMap<Context, WeakReference<ClassLoaderContextWrapper>> weakHashMap = ClassLoaderContextWrapperFactory.a;
                    if (!(applicationContext instanceof ClassLoaderContextWrapper)) {
                        synchronized (ClassLoaderContextWrapperFactory.b) {
                            WeakHashMap<Context, WeakReference<ClassLoaderContextWrapper>> weakHashMap2 = ClassLoaderContextWrapperFactory.a;
                            WeakReference<ClassLoaderContextWrapper> weakReference = weakHashMap2.get(applicationContext);
                            ClassLoaderContextWrapper classLoaderContextWrapper = weakReference == null ? null : weakReference.get();
                            if (classLoaderContextWrapper == null) {
                                classLoaderContextWrapper = new ClassLoaderContextWrapper(applicationContext);
                                weakHashMap2.put(applicationContext, new WeakReference<>(classLoaderContextWrapper));
                            }
                            applicationContext = classLoaderContextWrapper;
                        }
                    }
                    this.a = applicationContext;
                }
            }
            return this.a;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final AssetManager getAssets() {
            a();
            return getBaseContext().getAssets();
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final ClassLoader getClassLoader() {
            return new wt3(ClassLoaderContextWrapper.class.getClassLoader(), getBaseContext().getClassLoader());
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final Resources getResources() {
            a();
            return getBaseContext().getResources();
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final Object getSystemService(String str) {
            if (!"layout_inflater".equals(str)) {
                return getBaseContext().getSystemService(str);
            }
            a();
            return ((LayoutInflater) getBaseContext().getSystemService(str)).cloneInContext(this);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final Resources.Theme getTheme() {
            a();
            return getBaseContext().getTheme();
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {
            getBaseContext().registerComponentCallbacks(componentCallbacks);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final void startActivity(Intent intent) {
            if (xz.a(this) == null) {
                intent.addFlags(268435456);
            }
            super.startActivity(intent);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {
            getBaseContext().unregisterComponentCallbacks(componentCallbacks);
        }
    }
}
