package androidx.lifecycle;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.runtime.R$id;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class r {
    public static void a(@NonNull View view, @Nullable f fVar) {
        view.setTag(R$id.view_tree_lifecycle_owner, fVar);
    }
}
