package b.b.o.a.b;

/* JADX INFO: compiled from: CarImageFinderFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static a a(String str) {
        str.hashCode();
        switch (str) {
            case "doorLocks":
                return new h();
            case "mirror":
                return new m();
            case "trailer":
                return new p();
            case "carLights":
                return new e();
            case "comDrive":
                return new g();
            case "freezer":
                return new i();
            case "reminder":
                return new n();
            case "hud":
                return new j();
            case "seat":
                return new o();
            case "diBar":
                return new l();
            case "cockpit":
                return new f();
            case "AirConditioner":
                return new c();
            case "instrument":
                return new k();
            case "aiChassis":
                return new b();
            default:
                return null;
        }
    }
}
