package Z7;

import java.util.ArrayList;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m f10946a = new m();

    public static final C a(String str, String str2, String str3, String str4) {
        ArrayList arrayList = F.f10913a;
        C2061f c2061fE = C2061f.e(str2);
        String str5 = str2 + '(' + str3 + ')' + str4;
        A7.m.f(str, "internalName");
        A7.m.f(str5, "jvmDescriptor");
        return new C(c2061fE, str + '.' + str5);
    }
}
