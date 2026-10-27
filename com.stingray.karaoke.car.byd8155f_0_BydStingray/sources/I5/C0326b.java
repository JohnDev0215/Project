package I5;

import A7.C0053a;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: I5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0326b extends C0053a implements InterfaceC2772a {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f2947s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0326b(int i7, Object obj, Class cls, String str, String str2, int i10, int i11) {
        super(i7, i10, cls, obj, str, str2);
        this.f2947s = i11;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f2947s) {
            case 0:
                ((h6.p) this.l).i(false);
                break;
            case 1:
                ((h6.l) this.l).i(false);
                break;
            case 2:
                ((h6.p) this.l).i(false);
                break;
            case 3:
                ((h6.l) this.l).i(false);
                break;
            default:
                ((Z5.c) this.l).i(false);
                break;
        }
        return m7.q.f19267a;
    }
}
