package n3;

import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class P {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final LinkedHashMap f19843b = new LinkedHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f19844a = new LinkedHashMap();

    public final void a(O o) {
        A7.m.f(o, "navigator");
        String strL = h6.j.l(o.getClass());
        if (strL.length() <= 0) {
            throw new IllegalArgumentException("navigator name cannot be an empty string");
        }
        LinkedHashMap linkedHashMap = this.f19844a;
        O o4 = (O) linkedHashMap.get(strL);
        if (A7.m.a(o4, o)) {
            return;
        }
        boolean z6 = false;
        if (o4 != null && o4.f19842b) {
            z6 = true;
        }
        if (z6) {
            throw new IllegalStateException(("Navigator " + o + " is replacing an already attached " + o4).toString());
        }
        if (!o.f19842b) {
            return;
        }
        throw new IllegalStateException(("Navigator " + o + " is already attached to another NavController").toString());
    }

    public final O b(String str) {
        A7.m.f(str, "name");
        if (str.length() <= 0) {
            throw new IllegalArgumentException("navigator name cannot be an empty string");
        }
        O o = (O) this.f19844a.get(str);
        if (o != null) {
            return o;
        }
        throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.i("Could not find Navigator with name \"", str, "\". You must call NavController.addNavigator() for each navigation type."));
    }
}
