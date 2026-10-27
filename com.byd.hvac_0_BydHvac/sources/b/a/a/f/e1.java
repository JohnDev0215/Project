package b.a.a.f;

import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile boolean f101a = false;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static e1 f102a = new e1();
    }

    public static e1 a() {
        return a.f102a;
    }

    public boolean b() {
        BydLogHelper.logW("Hvac3DManager", "getIsLoaded : " + f101a);
        return f101a;
    }

    public void c(int i) {
        BydLogHelper.logW("Hvac3DManager", "update3DConfigurationChanged mode  : " + i + "  --- isLoaded = " + f101a);
    }
}
