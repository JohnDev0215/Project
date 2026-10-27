package com.stingray.drivemodedetector;

import A7.x;
import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import kotlin.Metadata;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.InterfaceC2412e;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LS8/w;", "", "<anonymous>", "(LS8/w;)I"}, k = 3, mv = {2, 1, 0})
@InterfaceC2412e(c = "com.stingray.drivemodedetector.DriveModeDetector$getDriveMode$time$1$1", f = "DriveModeDetector.kt", l = {83}, m = "invokeSuspend")
public final class DriveModeDetector$getDriveMode$time$1$1 extends i implements InterfaceC2785n {
    final /* synthetic */ x $count;
    final /* synthetic */ x $gear;
    int label;
    final /* synthetic */ DriveModeDetector this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DriveModeDetector$getDriveMode$time$1$1(x xVar, x xVar2, DriveModeDetector driveModeDetector, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.$gear = xVar;
        this.$count = xVar2;
        this.this$0 = driveModeDetector;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new DriveModeDetector$getDriveMode$time$1$1(this.$gear, this.$count, this.this$0, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(InterfaceC0725w interfaceC0725w, InterfaceC2189c interfaceC2189c) {
        return ((DriveModeDetector$getDriveMode$time$1$1) create(interfaceC0725w, interfaceC2189c)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.label;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.label = 1;
            if (AbstractC0727y.i(10L, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        this.$gear.l = DriveModeDetector.getDriveMode$getGear(this.this$0);
        x xVar = this.$count;
        int i10 = xVar.l;
        xVar.l = i10 + 1;
        return new Integer(i10);
    }
}
