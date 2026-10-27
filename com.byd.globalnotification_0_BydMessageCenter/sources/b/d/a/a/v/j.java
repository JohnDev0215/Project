package b.d.a.a.v;

import android.view.View;
import b.d.a.a.v.f;

/* JADX INFO: loaded from: classes.dex */
public class j implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f2099a;

    public j(f fVar) {
        this.f2099a = fVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        f fVar = this.f2099a;
        f.e eVar = f.e.DAY;
        f.e eVar2 = f.e.YEAR;
        f.e eVar3 = fVar.a0;
        if (eVar3 == eVar2) {
            fVar.T(eVar);
        } else if (eVar3 == eVar) {
            fVar.T(eVar2);
        }
    }
}
