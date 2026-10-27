package androidx.activity.result.d;

import android.content.Intent;
import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class c extends a<Intent, ActivityResult> {
    @Override // androidx.activity.result.d.a
    @NonNull
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ActivityResult a(int i, @Nullable Intent intent) {
        return new ActivityResult(i, intent);
    }
}
