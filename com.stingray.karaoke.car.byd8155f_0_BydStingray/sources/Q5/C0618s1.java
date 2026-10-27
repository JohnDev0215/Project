package Q5;

import T.C0735d;
import T.C0753m;
import T.C0761q;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.ToastType;
import k6.C1706n;
import w.AbstractC2565A;
import x.AbstractC2613B;
import x.AbstractC2631e;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.s1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0618s1 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7058m;

    public /* synthetic */ C0618s1(Object obj, int i7) {
        this.l = i7;
        this.f7058m = obj;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        String strX;
        long j3;
        Integer numValueOf;
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    Q.c1.b((String) this.f7058m, null, l6.d.c(c0761q).f18708i, h6.f.g(FontSize.H10, c0761q), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).f5158a, c0761q, 0, 0, 65522);
                }
                return m7.q.f19267a;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    c0761q2.Q(494202127);
                    Object objG = c0761q2.G();
                    T.S s6 = C0753m.f8270a;
                    if (objG == s6) {
                        objG = C0735d.O(Boolean.FALSE, T.S.f8224q);
                        c0761q2.a0(objG);
                    }
                    T.X x8 = (T.X) objG;
                    c0761q2.p(false);
                    boolean zBooleanValue = ((Boolean) x8.getValue()).booleanValue();
                    w.F fB = AbstractC2565A.b(AbstractC2631e.q(800, 0, AbstractC2613B.f23388b, 2), 2);
                    x.C0 c0Q = AbstractC2631e.q(800, 0, null, 6);
                    c0761q2.Q(494217897);
                    Object objG2 = c0761q2.G();
                    if (objG2 == s6) {
                        objG2 = new U5.i(9);
                        c0761q2.a0(objG2);
                    }
                    c0761q2.p(false);
                    w.F fA = fB.a(AbstractC2565A.e(c0Q, (InterfaceC2782k) objG2));
                    w.G gC = AbstractC2565A.c(AbstractC2631e.q(800, 0, AbstractC2613B.f23387a, 2), 2);
                    x.C0 c0Q2 = AbstractC2631e.q(800, 0, null, 6);
                    c0761q2.Q(494235017);
                    Object objG3 = c0761q2.G();
                    if (objG3 == s6) {
                        objG3 = new U5.i(9);
                        c0761q2.a0(objG3);
                    }
                    c0761q2.p(false);
                    w.G gA = gC.a(AbstractC2565A.f(c0Q2, (InterfaceC2782k) objG3));
                    k6.v vVar = (k6.v) this.f7058m;
                    androidx.compose.animation.a.c(zBooleanValue, null, fA, gA, null, b0.g.b(271701163, new I5.y(vVar, 20), c0761q2), c0761q2, 196608, 18);
                    c0761q2.Q(494244916);
                    boolean zF = c0761q2.f(vVar);
                    Object objG4 = c0761q2.G();
                    if (zF || objG4 == s6) {
                        objG4 = new C1706n(vVar, x8, null);
                        c0761q2.a0(objG4);
                    }
                    c0761q2.p(false);
                    C0735d.f(c0761q2, vVar, (InterfaceC2785n) objG4);
                }
                return m7.q.f19267a;
            default:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    c0761q3.Q(1688942779);
                    int[] iArr = k6.w.f18302a;
                    ToastType toastType = (ToastType) this.f7058m;
                    switch (iArr[toastType.ordinal()]) {
                        case 1:
                            c0761q3.Q(-2045548922);
                            strX = AbstractC2673a.X(R.string.SongQueue_AddedToQueue, c0761q3);
                            c0761q3.p(false);
                            break;
                        case 2:
                            c0761q3.Q(-2045546200);
                            strX = AbstractC2673a.X(R.string.Favorite_AddedToFavorite, c0761q3);
                            c0761q3.p(false);
                            break;
                        case 3:
                            c0761q3.Q(-2045543252);
                            strX = AbstractC2673a.X(R.string.Favorite_RemovedFromFavorite, c0761q3);
                            c0761q3.p(false);
                            break;
                        case 4:
                        case 5:
                            c0761q3.Q(-2045538681);
                            strX = AbstractC2673a.X(R.string.Driving_FeatureDisabled, c0761q3);
                            c0761q3.p(false);
                            break;
                        case 6:
                            c0761q3.Q(-2045535280);
                            strX = AbstractC2673a.X(R.string.Driving_DisabledWithoutPassenger, c0761q3);
                            c0761q3.p(false);
                            break;
                        case 7:
                            c0761q3.Q(-2045532220);
                            strX = AbstractC2673a.X(R.string.Scoring_ToastWarning, c0761q3);
                            c0761q3.p(false);
                            break;
                        case 8:
                            c0761q3.Q(1013084679);
                            c0761q3.p(false);
                            strX = "";
                            break;
                        default:
                            throw M5.b.d(-2045550006, c0761q3, false);
                    }
                    c0761q3.p(false);
                    c0761q3.Q(-1211815395);
                    switch (k6.w.f18302a[toastType.ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                            c0761q3.Q(-916743599);
                            j3 = l6.d.c(c0761q3).f18703f0;
                            c0761q3.p(false);
                            break;
                        case 4:
                        case 6:
                        case 7:
                        case 8:
                            c0761q3.Q(-916734511);
                            j3 = l6.d.c(c0761q3).f18699d0;
                            c0761q3.p(false);
                            break;
                        case 5:
                            c0761q3.Q(-916740913);
                            j3 = l6.d.c(c0761q3).f18701e0;
                            c0761q3.p(false);
                            break;
                        default:
                            throw M5.b.d(-916747711, c0761q3, false);
                    }
                    c0761q3.p(false);
                    c0761q3.Q(181714480);
                    if (toastType != ToastType.ADD_TO_QUEUE && toastType != ToastType.ADD_TO_FAVORITE && toastType != ToastType.REMOVE_FROM_FAVORITE && toastType != ToastType.DRIVING_DISABLED_WARNING && toastType != ToastType.DRIVING_DISABLED_ERROR && toastType != ToastType.DRIVING_DISABLED_WITHOUT_PASSENGER && toastType != ToastType.SCORING_ON_HOLD && toastType != ToastType.DEFAULT) {
                        throw new A9.k();
                    }
                    long j10 = l6.d.c(c0761q3).f18705g0;
                    c0761q3.p(false);
                    c0761q3.Q(-671114979);
                    switch (k6.w.f18302a[toastType.ordinal()]) {
                        case 1:
                        case 2:
                            numValueOf = Integer.valueOf(R.drawable.ic_add);
                            break;
                        case 3:
                            numValueOf = Integer.valueOf(R.drawable.ic_remove);
                            break;
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            numValueOf = Integer.valueOf(R.drawable.ic_warning);
                            break;
                        case 8:
                            numValueOf = null;
                            break;
                        default:
                            throw new A9.k();
                    }
                    c0761q3.p(false);
                    com.bumptech.glide.d.l(strX, j3, j10, numValueOf, c0761q3, 0);
                }
                return m7.q.f19267a;
        }
    }
}
