package android.car.drivingstate;

import android.car.drivingstate.CarUxRestrictionsConfiguration;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ c f230a = new c();

    private /* synthetic */ c() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((CarUxRestrictionsConfiguration.RestrictionsPerSpeedRange) obj).getSpeedRange();
    }
}
