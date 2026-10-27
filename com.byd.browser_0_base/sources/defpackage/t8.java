package defpackage;

import defpackage.u8;

/* JADX INFO: compiled from: AssistantVoiceSearchService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t8 extends i9<Boolean> {
    public final /* synthetic */ boolean h;
    public final /* synthetic */ u8 i;

    public t8(u8 u8Var, boolean z) {
        this.i = u8Var;
        this.h = z;
    }

    @Override // defpackage.i9
    public final Boolean b() {
        return Boolean.valueOf(this.i.o != this.h);
    }

    @Override // defpackage.i9
    public final void l(Boolean bool) {
        u8.a aVar;
        u8 u8Var = this.i;
        u8Var.o = this.h;
        if (!bool.booleanValue() || (aVar = u8Var.e) == null) {
            return;
        }
        aVar.k();
    }
}
