package org.chromium.chrome.browser.ui.autofill;

import defpackage.v62;
import java.util.Optional;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: OtpVerificationDialogProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    public static final PropertyModel.j a;
    public static final PropertyModel.l<String> b;
    public static final PropertyModel.r<a> c;
    public static final PropertyModel.r<Optional<CharSequence>> d;
    public static final PropertyModel.r<Optional<String>> e;
    public static final PropertyModel.r<String> f;
    public static final PropertyModel.n g;
    public static final v62[] h;

    /* JADX INFO: compiled from: OtpVerificationDialogProperties.java */
    public interface a {
        void a(CharSequence charSequence);

        void d();
    }

    static {
        PropertyModel.j jVar = new PropertyModel.j();
        a = jVar;
        PropertyModel.l<String> lVar = new PropertyModel.l<>();
        b = lVar;
        PropertyModel.r<a> rVar = new PropertyModel.r<>();
        c = rVar;
        PropertyModel.r<Optional<CharSequence>> rVar2 = new PropertyModel.r<>();
        d = rVar2;
        PropertyModel.r<Optional<String>> rVar3 = new PropertyModel.r<>();
        e = rVar3;
        PropertyModel.r<String> rVar4 = new PropertyModel.r<>();
        f = rVar4;
        PropertyModel.n nVar = new PropertyModel.n();
        g = nVar;
        h = new v62[]{jVar, rVar2, lVar, rVar, rVar3, rVar4, nVar};
    }
}
