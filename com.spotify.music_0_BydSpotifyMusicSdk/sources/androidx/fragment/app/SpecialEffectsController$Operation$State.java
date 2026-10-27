package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class SpecialEffectsController$Operation$State {
    public static final SpecialEffectsController$Operation$State c;
    public static final SpecialEffectsController$Operation$State e;
    public static final SpecialEffectsController$Operation$State k;
    public static final SpecialEffectsController$Operation$State l;
    public static final /* synthetic */ SpecialEffectsController$Operation$State[] m;

    static {
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State = new SpecialEffectsController$Operation$State("REMOVED", 0);
        c = specialEffectsController$Operation$State;
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State2 = new SpecialEffectsController$Operation$State("VISIBLE", 1);
        e = specialEffectsController$Operation$State2;
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State3 = new SpecialEffectsController$Operation$State("GONE", 2);
        k = specialEffectsController$Operation$State3;
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State4 = new SpecialEffectsController$Operation$State("INVISIBLE", 3);
        l = specialEffectsController$Operation$State4;
        m = new SpecialEffectsController$Operation$State[]{specialEffectsController$Operation$State, specialEffectsController$Operation$State2, specialEffectsController$Operation$State3, specialEffectsController$Operation$State4};
    }

    public static SpecialEffectsController$Operation$State valueOf(String str) {
        return (SpecialEffectsController$Operation$State) Enum.valueOf(SpecialEffectsController$Operation$State.class, str);
    }

    public static SpecialEffectsController$Operation$State[] values() {
        return (SpecialEffectsController$Operation$State[]) m.clone();
    }

    public final void a(View view) {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            ViewParent parent = view.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                if (Log.isLoggable("FragmentManager", 2)) {
                    view.toString();
                    viewGroup.toString();
                }
                viewGroup.removeView(view);
                return;
            }
            return;
        }
        if (iOrdinal == 1) {
            if (Log.isLoggable("FragmentManager", 2)) {
                view.toString();
            }
            view.setVisibility(0);
        } else if (iOrdinal == 2) {
            if (Log.isLoggable("FragmentManager", 2)) {
                view.toString();
            }
            view.setVisibility(8);
        } else {
            if (iOrdinal != 3) {
                return;
            }
            if (Log.isLoggable("FragmentManager", 2)) {
                view.toString();
            }
            view.setVisibility(4);
        }
    }
}
