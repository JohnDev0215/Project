package defpackage;

import android.widget.CompoundButton;
import defpackage.e51;

/* JADX INFO: compiled from: LanguageAskPrompt.java */
/* JADX INFO: loaded from: classes.dex */
public final class d51 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ e51.a e;

    public d51(e51.a aVar) {
        this.e = aVar;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        e51.a aVar = this.e;
        if (z) {
            aVar.D.add(aVar.C);
        } else {
            aVar.D.remove(aVar.C);
        }
    }
}
