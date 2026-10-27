package androidx.test.internal.platform.app;

import android.content.ComponentName;
import android.content.Intent;
import androidx.test.platform.app.InstrumentationRegistry;

/* JADX INFO: loaded from: classes5.dex */
public abstract /* synthetic */ class ActivityInvoker$$CC {
    public static Intent getIntentForActivity$$dflt$$(ActivityInvoker activityInvoker, Class cls) {
        Intent intent = Intent.makeMainActivity(new ComponentName(InstrumentationRegistry.getInstrumentation().getTargetContext(), (Class<?>) cls));
        if (InstrumentationRegistry.getInstrumentation().getTargetContext().getPackageManager().resolveActivity(intent, 0) != null) {
            return intent;
        }
        return Intent.makeMainActivity(new ComponentName(InstrumentationRegistry.getInstrumentation().getContext(), (Class<?>) cls));
    }
}
