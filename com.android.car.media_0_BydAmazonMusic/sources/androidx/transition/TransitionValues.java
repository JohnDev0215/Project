package androidx.transition;

import android.view.View;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public class TransitionValues {
    public View view;
    public final Map<String, Object> values = new HashMap();
    public final ArrayList<Transition> mTargetedTransitions = new ArrayList<>();

    @Deprecated
    public TransitionValues() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TransitionValues)) {
            return false;
        }
        TransitionValues transitionValues = (TransitionValues) obj;
        return this.view == transitionValues.view && this.values.equals(transitionValues.values);
    }

    public int hashCode() {
        return this.values.hashCode() + (this.view.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sbK = c.k("TransitionValues@");
        sbK.append(Integer.toHexString(hashCode()));
        sbK.append(":\n");
        StringBuilder sbN = c.n(sbK.toString(), "    view = ");
        sbN.append(this.view);
        sbN.append("\n");
        String strF = c.f(sbN.toString(), "    values:");
        for (String str : this.values.keySet()) {
            strF = strF + "    " + str + ": " + this.values.get(str) + "\n";
        }
        return strF;
    }

    public TransitionValues(@NonNull View view) {
        this.view = view;
    }
}
