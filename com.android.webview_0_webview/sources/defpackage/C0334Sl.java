package defpackage;

/* JADX INFO: renamed from: Sl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0334Sl implements InterfaceC1233nm {
    public static final InterfaceC0460Zl b = new C0298Ql();
    public final InterfaceC0460Zl a;

    public C0334Sl() {
        InterfaceC0460Zl interfaceC0460Zl;
        InterfaceC0460Zl[] interfaceC0460ZlArr = new InterfaceC0460Zl[2];
        interfaceC0460ZlArr[0] = C1836yl.a;
        try {
            interfaceC0460Zl = (InterfaceC0460Zl) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            interfaceC0460Zl = b;
        }
        interfaceC0460ZlArr[1] = interfaceC0460Zl;
        C0316Rl c0316Rl = new C0316Rl(interfaceC0460ZlArr);
        AbstractC0119Gl.b(c0316Rl, "messageInfoFactory");
        this.a = c0316Rl;
    }

    public static boolean a(InterfaceC0442Yl interfaceC0442Yl) {
        EnumC0955im enumC0955im = EnumC0955im.PROTO2;
        return ((((C1067km) interfaceC0442Yl).d & 1) == 1 ? enumC0955im : EnumC0955im.PROTO3) == enumC0955im;
    }
}
