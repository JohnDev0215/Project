package defpackage;

import android.content.Intent;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;

/* JADX INFO: compiled from: ActivityResultContracts.kt */
/* JADX INFO: loaded from: classes.dex */
public final class v2 extends t2<Intent, ActivityResult> {
    @Override // defpackage.t2
    public final Intent a(ComponentActivity componentActivity, Object obj) {
        Intent intent = (Intent) obj;
        i21.e(componentActivity, "context");
        i21.e(intent, "input");
        return intent;
    }

    @Override // defpackage.t2
    public final Object c(Intent intent, int i) {
        return new ActivityResult(intent, i);
    }
}
