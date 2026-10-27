package defpackage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.lo0;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.modelutil.m;

/* JADX INFO: compiled from: FollowManagementCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class io0 {
    public final AppCompatActivity a;
    public final View b;

    /* JADX INFO: compiled from: FollowManagementCoordinator.java */
    public class a implements lo0.a {
        public a() {
        }
    }

    public io0(Activity activity) {
        this.a = (AppCompatActivity) activity;
        ya1 ya1Var = new ya1();
        m mVar = new m(ya1Var);
        int i = 5;
        mVar.H(0, new o61(R$layout.follow_management_item), new bg(i));
        mVar.H(1, new o61(R$layout.follow_management_empty_state), new cg(i));
        mVar.H(2, new o61(R$layout.feed_spinner), new zf(6));
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.follow_management_activity, (ViewGroup) null);
        this.b = viewInflate;
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R$id.follow_management_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(mVar);
        a aVar = new a();
        Profile profileD = Profile.d();
        new lo0(activity, ya1Var, aVar, new xq3(new LargeIconBridge(profileD), dx0.c(profileD.g(), ys0.a)));
    }
}
