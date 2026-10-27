package spi;

import androidx.arch.core.util.Function;
import androidx.constraintlayout.core.state.Interpolator;
import androidx.constraintlayout.core.state.Transition;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class v0 implements Function, Interpolator {
    public static final /* synthetic */ v0 b = new v0(0);
    public static final /* synthetic */ v0 c = new v0(1);
    public static final /* synthetic */ v0 d = new v0(2);
    public static final /* synthetic */ v0 e = new v0(3);
    public static final /* synthetic */ v0 f = new v0(0);
    public static final /* synthetic */ v0 g = new v0(1);
    public final /* synthetic */ int a;

    public /* synthetic */ v0(int i) {
        this.a = i;
    }

    @Override // androidx.arch.core.util.Function
    public Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return Integer.valueOf(((SupportSQLiteDatabase) obj).getVersion());
            case 1:
                return Boolean.valueOf(((SupportSQLiteDatabase) obj).inTransaction());
            case 2:
                return ((SupportSQLiteStatement) obj).simpleQueryForString();
            default:
                return Long.valueOf(((SupportSQLiteStatement) obj).simpleQueryForLong());
        }
    }

    @Override // androidx.constraintlayout.core.state.Interpolator
    public float getInterpolation(float f2) {
        switch (this.a) {
            case 0:
                return Transition.lambda$getInterpolator$2(f2);
            default:
                return Transition.lambda$getInterpolator$5(f2);
        }
    }
}
