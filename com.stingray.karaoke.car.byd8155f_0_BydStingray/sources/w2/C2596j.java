package w2;

import android.media.Spatializer;
import android.media.Spatializer$OnSpatializerStateChangedListener;
import d5.b0;

/* JADX INFO: renamed from: w2.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2596j implements Spatializer$OnSpatializerStateChangedListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ o f23235a;

    public C2596j(o oVar) {
        this.f23235a = oVar;
    }

    public final void onSpatializerAvailableChanged(Spatializer spatializer, boolean z6) {
        o oVar = this.f23235a;
        b0 b0Var = o.f23261j;
        oVar.f();
    }

    public final void onSpatializerEnabledChanged(Spatializer spatializer, boolean z6) {
        o oVar = this.f23235a;
        b0 b0Var = o.f23261j;
        oVar.f();
    }
}
