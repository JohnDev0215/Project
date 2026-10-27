package defpackage;

import J.N;
import java.nio.charset.StandardCharsets;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: CreatorMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class m10 {
    public final PropertyModel a;
    public final o10 b;

    public m10(PropertyModel propertyModel, o10 o10Var) {
        this.a = propertyModel;
        this.b = o10Var;
        PropertyModel.r<byte[]> rVar = n10.a;
        if (propertyModel.j(rVar) != null) {
            N.M6G1FmVU(new String((byte[]) propertyModel.j(rVar), StandardCharsets.UTF_8), new c3(14, this));
        }
        propertyModel.p(n10.f, new l10(0, this));
        propertyModel.p(n10.g, new aw(17, this));
    }
}
