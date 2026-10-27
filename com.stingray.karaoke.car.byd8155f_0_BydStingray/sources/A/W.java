package A;

import com.stingray.drivemodedetector.DriveMode;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class W extends s7.i implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ W(int i7, InterfaceC2189c interfaceC2189c, int i10) {
        super(i7, interfaceC2189c);
        this.l = i10;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws Throwable {
        switch (this.l) {
            case 0:
                long j3 = ((m0.c) obj2).f18880a;
                W w10 = new W(3, (InterfaceC2189c) obj3, 0);
                m7.q qVar = m7.q.f19267a;
                w10.invokeSuspend(qVar);
                return qVar;
            case 1:
                ((Number) obj2).floatValue();
                W w11 = new W(3, (InterfaceC2189c) obj3, 1);
                m7.q qVar2 = m7.q.f19267a;
                w11.invokeSuspend(qVar2);
                return qVar2;
            case 2:
                long j10 = ((m0.c) obj2).f18880a;
                W w12 = new W(3, (InterfaceC2189c) obj3, 2);
                m7.q qVar3 = m7.q.f19267a;
                w12.invokeSuspend(qVar3);
                return qVar3;
            case 3:
                ((Number) obj).intValue();
                new W(3, (InterfaceC2189c) obj3, 3).invokeSuspend(m7.q.f19267a);
                return Boolean.FALSE;
            case 4:
                ((Boolean) obj2).getClass();
                new W(3, (InterfaceC2189c) obj3, 4).invokeSuspend(m7.q.f19267a);
                return Boolean.FALSE;
            case 5:
                ((Boolean) obj2).getClass();
                new W(3, (InterfaceC2189c) obj3, 5).invokeSuspend(m7.q.f19267a);
                return Boolean.FALSE;
            case 6:
                ((Boolean) obj2).getClass();
                new W(3, (InterfaceC2189c) obj3, 6).invokeSuspend(m7.q.f19267a);
                return Boolean.FALSE;
            case 7:
                ((Boolean) obj2).getClass();
                new W(3, (InterfaceC2189c) obj3, 7).invokeSuspend(m7.q.f19267a);
                return Boolean.FALSE;
            case 8:
                ((Boolean) obj2).getClass();
                new W(3, (InterfaceC2189c) obj3, 8).invokeSuspend(m7.q.f19267a);
                return Boolean.FALSE;
            case 9:
                ((Boolean) obj2).getClass();
                new W(3, (InterfaceC2189c) obj3, 9).invokeSuspend(m7.q.f19267a);
                return null;
            default:
                ((Boolean) obj2).getClass();
                new W(3, (InterfaceC2189c) obj3, 10).invokeSuspend(m7.q.f19267a);
                return Boolean.TRUE;
        }
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        m7.q qVar = m7.q.f19267a;
        switch (this.l) {
            case 0:
                EnumC2303a enumC2303a = EnumC2303a.l;
                AbstractC1386c.u(obj);
                return qVar;
            case 1:
                EnumC2303a enumC2303a2 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                return qVar;
            case 2:
                EnumC2303a enumC2303a3 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                return qVar;
            case 3:
                EnumC2303a enumC2303a4 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                return Boolean.FALSE;
            case 4:
                EnumC2303a enumC2303a5 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                DriveMode driveMode = DriveMode.PARKED;
                return Boolean.FALSE;
            case 5:
                EnumC2303a enumC2303a6 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                DriveMode driveMode2 = DriveMode.PARKED;
                return Boolean.FALSE;
            case 6:
                EnumC2303a enumC2303a7 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                DriveMode driveMode3 = DriveMode.PARKED;
                return Boolean.FALSE;
            case 7:
                EnumC2303a enumC2303a8 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                DriveMode driveMode4 = DriveMode.PARKED;
                return Boolean.FALSE;
            case 8:
                EnumC2303a enumC2303a9 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                DriveMode driveMode5 = DriveMode.PARKED;
                return Boolean.FALSE;
            case 9:
                EnumC2303a enumC2303a10 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                DriveMode driveMode6 = DriveMode.PARKED;
                return null;
            default:
                EnumC2303a enumC2303a11 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                DriveMode driveMode7 = DriveMode.PARKED;
                return Boolean.TRUE;
        }
    }
}
