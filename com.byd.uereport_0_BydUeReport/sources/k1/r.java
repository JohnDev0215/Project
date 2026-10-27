package k1;

import android.view.View;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f5823b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f5822a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<l> f5824c = new ArrayList<>();

    @Deprecated
    public r() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.f5823b == rVar.f5823b && this.f5822a.equals(rVar.f5822a);
    }

    public int hashCode() {
        return (this.f5823b.hashCode() * 31) + this.f5822a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f5823b + BydAdbHelper.COMMAND_LINE_END) + "    values:";
        for (String str2 : this.f5822a.keySet()) {
            str = str + "    " + str2 + ": " + this.f5822a.get(str2) + BydAdbHelper.COMMAND_LINE_END;
        }
        return str;
    }

    public r(View view) {
        this.f5823b = view;
    }
}
