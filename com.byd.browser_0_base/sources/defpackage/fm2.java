package defpackage;

import J.N;
import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import defpackage.di1;
import java.util.HashMap;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SignOutDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fm2 {
    public final CheckBox a;
    public final int b;
    public final a c;
    public final PropertyModel d;
    public final bi1 e;

    /* JADX INFO: compiled from: SignOutDialogCoordinator.java */
    public interface a {
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [T, em2] */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, android.view.View] */
    /* JADX WARN: Type inference failed for: r2v13, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v1, types: [T, java.lang.String] */
    public fm2(Context context, bi1 bi1Var, ClearBrowsingDataFragment clearBrowsingDataFragment) {
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        String strF = z7.D(profileD).f();
        ?? Inflate = LayoutInflater.from(context).inflate(R$layout.signout_wipe_storage_dialog, (ViewGroup) null);
        TextView textView = (TextView) Inflate.findViewById(R.id.title);
        z7 z7VarM2 = z7.m();
        Profile profileD2 = Profile.d();
        z7VarM2.getClass();
        textView.setText(!z7.v(profileD2).b(1) ? R$string.signout_title : strF != null ? R$string.signout_managed_account_title : R$string.turn_off_sync_and_signout_title);
        TextView textView2 = (TextView) Inflate.findViewById(R.id.message);
        z7 z7VarM3 = z7.m();
        Profile profileD3 = Profile.d();
        z7VarM3.getClass();
        textView2.setText(!z7.v(profileD3).b(1) ? context.getString(R$string.signout_message) : strF != null ? context.getString(R$string.signout_managed_account_message, strF) : context.getString(R$string.turn_off_sync_and_signout_message));
        CheckBox checkBox = (CheckBox) Inflate.findViewById(R$id.remove_local_data);
        this.a = checkBox;
        boolean zL = gq2.l("history.deleting_enabled");
        z7 z7VarM4 = z7.m();
        Profile profileD4 = Profile.d();
        z7VarM4.getClass();
        checkBox.setVisibility(strF == null && zL && z7.v(profileD4).b(1) ? 0 : 8);
        this.b = 0;
        this.c = clearBrowsingDataFragment;
        HashMap mapE = PropertyModel.e(di1.B);
        PropertyModel.n nVar = di1.q;
        int i = zj.a;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = true;
        mapE.put(nVar, aVar);
        PropertyModel.r<String> rVar = di1.j;
        ?? string = context.getString(R$string.continue_button);
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = string;
        mapE.put(rVar, gVar);
        PropertyModel.r<String> rVar2 = di1.m;
        ?? string2 = context.getString(R$string.cancel);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = string2;
        mapE.put(rVar2, gVar2);
        PropertyModel.r<View> rVar3 = di1.h;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = Inflate;
        mapE.put(rVar3, gVar3);
        PropertyModel.l<di1.a> lVar = di1.a;
        ?? em2Var = new em2(this);
        PropertyModel.g gVar4 = new PropertyModel.g();
        gVar4.a = em2Var;
        PropertyModel propertyModelP = C0175b.p(mapE, lVar, gVar4, mapE);
        this.d = propertyModelP;
        this.e = bi1Var;
        N.MX17n_KK(5, 0);
        bi1Var.j(propertyModelP, 1, false);
    }
}
