package p;

import android.os.Build;
import com.spotify.contexts.DeviceAndroid;

/* JADX INFO: loaded from: classes.dex */
public final class k90 implements di0 {
    public final g90 a;

    public k90(g90 g90Var) {
        this.a = g90Var;
    }

    @Override // p.di0
    public final String a() {
        return "context_device_android";
    }

    @Override // p.di0
    public final com.google.protobuf.i getData() {
        j90 j90VarK = DeviceAndroid.k();
        j90VarK.e(Build.MANUFACTURER);
        j90VarK.g(Build.VERSION.RELEASE);
        j90VarK.h(Build.VERSION.SDK_INT);
        j90VarK.f(Build.MODEL);
        j90VarK.d(this.a.e());
        com.google.protobuf.i iVarBuild = j90VarK.build();
        v03.i(iVarBuild, "newBuilder()\n           …t())\n            .build()");
        return iVarBuild;
    }
}
