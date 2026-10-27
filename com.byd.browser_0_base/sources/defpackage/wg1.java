package defpackage;

import J.N;
import defpackage.p9;
import org.chromium.components.messages.a;
import org.chromium.components.messages.b;
import org.chromium.components.webauthn.InternalAuthenticator;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class wg1 implements b.a, p9.c {
    public final /* synthetic */ Object e;

    public /* synthetic */ wg1(Object obj) {
        this.e = obj;
    }

    @Override // org.chromium.components.messages.b.a
    public final void a(int i, PropertyModel propertyModel) {
        ((a) this.e).a(i, propertyModel);
    }

    @Override // defpackage.sl
    public final void c(Boolean bool) {
        Boolean bool2 = bool;
        long j = ((InternalAuthenticator) this.e).a;
        if (j != 0) {
            N.MdPQq0XF(j, bool2.booleanValue());
        }
    }
}
