package androidx.savedstate;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    public static void a(@NonNull View view, @Nullable b bVar) {
        view.setTag(R$id.view_tree_saved_state_registry_owner, bVar);
    }
}
