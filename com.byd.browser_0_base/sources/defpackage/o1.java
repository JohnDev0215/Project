package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.m;

/* JADX INFO: compiled from: AccountSelectionCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o1 {
    public final ji a;
    public final r1 b;

    public o1(Context context, ji jiVar, m1 m1Var) {
        this.a = jiVar;
        PropertyModel propertyModel = new PropertyModel(PropertyModel.e(w1.d));
        ya1 ya1Var = new ya1();
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R$layout.account_selection_sheet, (ViewGroup) null);
        j.b(propertyModel, linearLayout, new ee3(1));
        int i = R$id.sheet_item_list;
        RecyclerView recyclerView = (RecyclerView) linearLayout.findViewById(i);
        recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        recyclerView.setItemAnimator(null);
        m mVar = new m(ya1Var);
        mVar.H(1, new ea(7), new n1(0));
        recyclerView.setAdapter(mVar);
        RecyclerView recyclerView2 = (RecyclerView) linearLayout.findViewById(i);
        Objects.requireNonNull(recyclerView2);
        l1 l1Var = new l1(linearLayout, new yq2(23, recyclerView2));
        ProfileKey profileKeyG = Profile.d().g();
        na0 na0Var = ys0.a;
        Object obj = ThreadUtils.a;
        this.b = new r1(m1Var, propertyModel, ya1Var, jiVar, l1Var, dx0.a(2, new cx0(profileKeyG), na0Var, 512000), context.getResources().getDimensionPixelSize(R$dimen.account_selection_account_avatar_size));
    }
}
