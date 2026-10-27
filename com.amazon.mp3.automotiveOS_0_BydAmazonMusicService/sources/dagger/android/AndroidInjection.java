package dagger.android;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentCallbacks2;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes7.dex */
public final class AndroidInjection {
    public static void inject(Activity activity) {
        Preconditions.checkNotNull(activity, MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        ComponentCallbacks2 application = activity.getApplication();
        if (!(application instanceof HasAndroidInjector)) {
            throw new RuntimeException(String.format("%s does not implement %s", application.getClass().getCanonicalName(), HasAndroidInjector.class.getCanonicalName()));
        }
        inject(activity, (HasAndroidInjector) application);
    }

    public static void inject(Service service) {
        Preconditions.checkNotNull(service, NotificationCompat.CATEGORY_SERVICE);
        ComponentCallbacks2 application = service.getApplication();
        if (!(application instanceof HasAndroidInjector)) {
            throw new RuntimeException(String.format("%s does not implement %s", application.getClass().getCanonicalName(), HasAndroidInjector.class.getCanonicalName()));
        }
        inject(service, (HasAndroidInjector) application);
    }

    private static void inject(Object obj, HasAndroidInjector hasAndroidInjector) {
        AndroidInjector<Object> androidInjector = hasAndroidInjector.androidInjector();
        Preconditions.checkNotNull(androidInjector, "%s.androidInjector() returned null", hasAndroidInjector.getClass());
        androidInjector.inject(obj);
    }

    private AndroidInjection() {
    }
}
