package androidx.transition;

import android.view.View;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spi.d;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class TransitionValues {
    public View view;
    public final Map<String, Object> values = new HashMap();
    public final ArrayList<Transition> mTargetedTransitions = new ArrayList<>();

    @Deprecated
    public TransitionValues() {
    }

    public TransitionValues(@NonNull View view) {
        this.view = view;
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
        StringBuilder sbK = e.k("TransitionValues@");
        sbK.append(Integer.toHexString(hashCode()));
        sbK.append(":\n");
        StringBuilder sbM = e.m(sbK.toString(), "    view = ");
        sbM.append(this.view);
        sbM.append("\n");
        String strF = d.f(sbM.toString(), "    values:");
        for (String str : this.values.keySet()) {
            strF = strF + "    " + str + ": " + this.values.get(str) + "\n";
        }
        return strF;
    }
}
