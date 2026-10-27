package X5;

import E0.AbstractC0156f;
import android.graphics.Rect;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.player.PlaybackType;
import com.stingray.karaoke.car.ui.player.SideMenuType;
import com.stingray.shared.logs.segment.ActionOwner;
import java.util.ArrayList;
import java.util.Collection;
import l0.AbstractC1713d;
import l0.C1711b;
import l0.C1723n;
import l0.InterfaceC1716g;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class G extends A7.j implements InterfaceC2782k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f10170t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ G(int i7, Object obj, Class cls, String str, String str2, int i10, int i11) {
        super(i7, i10, cls, obj, str, str2);
        this.f10170t = i11;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f10170t) {
            case 0:
                ((D0) this.f455m).f10105D.getClass();
                return m7.q.f19267a;
            case 1:
                SideMenuType sideMenuType = (SideMenuType) obj;
                A7.m.f(sideMenuType, "p0");
                D0 d02 = (D0) this.f455m;
                d02.getClass();
                if (((V8.f0) d02.f10143m0.l).getValue() == sideMenuType) {
                    d02.p(sideMenuType);
                } else {
                    if (((Boolean) ((V8.f0) d02.f10133c0.l).getValue()).booleanValue()) {
                        N5.Z z6 = d02.f10158x;
                        if (((V8.f0) z6.f4635N.l).getValue() == PlayerStatus.PLAYING) {
                            z6.s(ActionOwner.HOST);
                        }
                    }
                    d02.s();
                    V8.f0 f0Var = d02.f10118N;
                    ArrayList arrayListA0 = AbstractC1998m.A0((Collection) f0Var.getValue());
                    arrayListA0.remove(sideMenuType);
                    arrayListA0.add(sideMenuType);
                    f0Var.m(null, arrayListA0);
                }
                return m7.q.f19267a;
            case 2:
                float fFloatValue = ((Number) obj).floatValue();
                D0 d03 = (D0) this.f455m;
                long j3 = (long) fFloatValue;
                C0886u c0886u = d03.f10160y;
                V8.f0 f0Var2 = c0886u.f10387f;
                Boolean bool = Boolean.TRUE;
                f0Var2.getClass();
                f0Var2.m(null, bool);
                c0886u.d = j3;
                Long lValueOf = Long.valueOf(j3);
                V8.f0 f0Var3 = c0886u.f10389h;
                f0Var3.getClass();
                f0Var3.m(null, lValueOf);
                d03.s();
                return m7.q.f19267a;
            case 3:
                PlaybackType playbackType = (PlaybackType) obj;
                A7.m.f(playbackType, "p0");
                ((D0) this.f455m).r(playbackType);
                return m7.q.f19267a;
            case 4:
                int i7 = ((C1711b) obj).f18325a;
                d1.m mVar = (d1.m) this.f455m;
                mVar.getClass();
                View viewC = d1.j.c(mVar);
                if (viewC.isFocused() || viewC.hasFocus()) {
                    return C1723n.f18348b;
                }
                return AbstractC1713d.D(viewC, AbstractC1713d.I(i7), d1.j.b(AbstractC0156f.u(mVar).getFocusOwner(), (View) AbstractC0156f.u(mVar), viewC)) ? C1723n.f18348b : C1723n.c;
            default:
                int i10 = ((C1711b) obj).f18325a;
                d1.m mVar2 = (d1.m) this.f455m;
                mVar2.getClass();
                View viewC2 = d1.j.c(mVar2);
                if (!viewC2.hasFocus()) {
                    return C1723n.f18348b;
                }
                InterfaceC1716g focusOwner = AbstractC0156f.u(mVar2).getFocusOwner();
                View view = (View) AbstractC0156f.u(mVar2);
                if (!(viewC2 instanceof ViewGroup)) {
                    if (view.requestFocus()) {
                        return C1723n.f18348b;
                    }
                    throw new IllegalStateException("host view did not take focus");
                }
                Rect rectB = d1.j.b(focusOwner, view, viewC2);
                Integer numI = AbstractC1713d.I(i10);
                int iIntValue = numI != null ? numI.intValue() : 130;
                FocusFinder focusFinder = FocusFinder.getInstance();
                View view2 = mVar2.f13327y;
                View viewFindNextFocus = view2 != null ? focusFinder.findNextFocus((ViewGroup) view, view2, iIntValue) : focusFinder.findNextFocusFromRect((ViewGroup) view, rectB, iIntValue);
                if (viewFindNextFocus != null && d1.j.a(viewC2, viewFindNextFocus)) {
                    viewFindNextFocus.requestFocus(iIntValue, rectB);
                    return C1723n.c;
                }
                if (view.requestFocus()) {
                    return C1723n.f18348b;
                }
                throw new IllegalStateException("host view did not take focus");
        }
    }
}
