package defpackage;

import android.view.View;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public class ee {
    public View b;
    public final Map<String, Object> a = new HashMap();
    public final ArrayList<yd> c = new ArrayList<>();

    @Deprecated
    public ee() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ee)) {
            return false;
        }
        ee eeVar = (ee) obj;
        return this.b == eeVar.b && this.a.equals(eeVar.a);
    }

    public int hashCode() {
        return (this.b.hashCode() * 31) + this.a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.b + BydAdbHelper.COMMAND_LINE_END) + "    values:";
        for (String str2 : this.a.keySet()) {
            str = str + "    " + str2 + ": " + this.a.get(str2) + BydAdbHelper.COMMAND_LINE_END;
        }
        return str;
    }

    public ee(View view) {
        this.b = view;
    }
}
