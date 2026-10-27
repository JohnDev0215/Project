package O0;

import android.os.Build;
import android.text.StaticLayout;

/* JADX INFO: loaded from: classes.dex */
public final class o implements t {
    @Override // O0.t
    public StaticLayout a(u uVar) {
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(uVar.f5027a, uVar.f5028b, uVar.c, uVar.d, uVar.f5029e);
        builderObtain.setTextDirection(uVar.f5030f);
        builderObtain.setAlignment(uVar.f5031g);
        builderObtain.setMaxLines(uVar.f5032h);
        builderObtain.setEllipsize(uVar.f5033i);
        builderObtain.setEllipsizedWidth(uVar.f5034j);
        builderObtain.setLineSpacing(uVar.l, uVar.f5035k);
        builderObtain.setIncludePad(uVar.f5037n);
        builderObtain.setBreakStrategy(uVar.f5038p);
        builderObtain.setHyphenationFrequency(uVar.f5041s);
        builderObtain.setIndents(uVar.f5042t, uVar.f5043u);
        int i7 = Build.VERSION.SDK_INT;
        p.a(builderObtain, uVar.f5036m);
        q.a(builderObtain, uVar.o);
        if (i7 >= 33) {
            r.b(builderObtain, uVar.f5039q, uVar.f5040r);
        }
        return builderObtain.build();
    }
}
