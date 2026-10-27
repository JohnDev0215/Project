package b.a.e.d;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.ActivityResult;

/* JADX INFO: compiled from: ActivityResultContracts.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends a<Intent, ActivityResult> {
    @Override // b.a.e.d.a
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Intent a(Context context, Intent intent) {
        return intent;
    }

    @Override // b.a.e.d.a
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public ActivityResult c(int i2, Intent intent) {
        return new ActivityResult(i2, intent);
    }
}
