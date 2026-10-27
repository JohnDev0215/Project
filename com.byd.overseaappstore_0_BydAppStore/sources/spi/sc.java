package spi;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class sc {

    @NotNull
    public static final sc a = new sc();

    private sc() {
    }

    @Nullable
    public final DialogFragment a(@Nullable FragmentActivity fragmentActivity, @Nullable String str) {
        if (fragmentActivity != null) {
            return (DialogFragment) fragmentActivity.getSupportFragmentManager().findFragmentByTag(str);
        }
        ct.b("getDialogFragment activity is null", new Object[0]);
        return null;
    }

    public final boolean b(@Nullable FragmentActivity fragmentActivity, @Nullable String str) {
        if (fragmentActivity == null) {
            ct.b(e.d("isAddedDialogFg activity is null fgTag:", str, " ret = false"), new Object[0]);
            return false;
        }
        Fragment fragmentFindFragmentByTag = fragmentActivity.getSupportFragmentManager().findFragmentByTag(str);
        if (fragmentFindFragmentByTag == null) {
            ct.a(e.d("isAddedDialogFg dialogFg is null fgTag:", str, " ret = false"), new Object[0]);
            return false;
        }
        if (fragmentFindFragmentByTag.isAdded()) {
            ct.a(e.d("isAddedDialogFg dialogFg.isAdded fgTag:", str, " ret = true"), new Object[0]);
            return true;
        }
        ct.a(e.d("isAddedDialogFg dialogFg is not null fgTag:", str, " ret = false"), new Object[0]);
        return false;
    }
}
