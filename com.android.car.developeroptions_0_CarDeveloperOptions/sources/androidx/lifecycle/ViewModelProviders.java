package androidx.lifecycle;

import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class ViewModelProviders {
    @Deprecated
    public static ViewModelProvider of(Fragment fragment) {
        return new ViewModelProvider(fragment);
    }
}
