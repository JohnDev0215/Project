package defpackage;

import J.N;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.al2;
import defpackage.fl2;
import defpackage.hk2;
import defpackage.vk2;
import defpackage.wm3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.chromium.base.BuildInfo;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.share.ShareHelper;
import org.chromium.components.browser_ui.share.a;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ShareSheetCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xk2 implements WindowAndroid.b, rq, bx, View.OnLayoutChangeListener {
    public final ji e;
    public final nw2<Tab> f;
    public final boolean g;
    public final wk2 h;
    public final LargeIconBridge i;
    public final xe3 j;
    public final Profile k;
    public long l;
    public boolean m;
    public boolean n;
    public HashSet o;
    public Activity p;
    public q2 q;
    public er r;
    public a s;
    public vk2 t;
    public WindowAndroid u;
    public jr v;
    public s71 w;
    public zk2 x;
    public final fl2 y;
    public int z = 3;
    public al2.a A = new al2.a(2, 0);

    public xk2(ji jiVar, q2 q2Var, nw2 nw2Var, LargeIconBridge largeIconBridge, boolean z, xe3 xe3Var, Profile profile) {
        this.e = jiVar;
        this.q = q2Var;
        q2Var.f(this);
        this.f = nw2Var;
        this.g = z;
        wk2 wk2Var = new wk2(this);
        this.h = wk2Var;
        jiVar.g(wk2Var);
        this.i = largeIconBridge;
        this.j = xe3Var;
        this.k = profile;
        this.y = new fl2(jiVar, this.t, this.l, this.z, this.A, new cl2(jiVar, xz.a.getPackageManager(), profile), profile);
    }

    public static void k(String str, int i, al2.a aVar, long j, Profile profile) {
        n3.X0(str);
        if (i != 3) {
            if (i == 0) {
                n3.X0("SharingHubAndroid.LinkGeneration.Success.TextShared");
            } else if (i == 1) {
                n3.X0("SharingHubAndroid.LinkGeneration.Success.LinkToTextShared");
            } else if (i == 2) {
                n3.X0("SharingHubAndroid.LinkGeneration.Failure.TextShared");
            }
            oj2.U(i, 3, "SharedHighlights.AndroidShareSheet.SharedState");
        }
        if (i == 1 || i == 0) {
            oj2.t(profile).notifyEvent("iph_shared_highlighting_used");
        }
        al2.a(aVar, "Completed");
        oj2.Y(System.currentTimeMillis() - j, "Sharing.SharingHubAndroid.TimeToShare");
    }

    @Override // defpackage.rq
    public final void a(a aVar, jr jrVar, long j) {
        this.m = true;
        d(aVar, jrVar, j);
    }

    @Override // org.chromium.ui.base.WindowAndroid.b
    public final void c() {
        vk2 vk2Var = this.t;
        if (vk2Var != null) {
            this.e.c(vk2Var, true);
        }
    }

    @Override // defpackage.rq
    public final void d(a aVar, jr jrVar, long j) {
        this.s = aVar;
        this.v = jrVar;
        this.p = (Activity) aVar.a.k().get();
        if (!m(jrVar)) {
            this.x.b(aVar, jrVar);
            zk2 zk2Var = this.x;
            int i = zk2Var.c.j;
            this.s = zk2Var.a(((i == 3 || i == 5 || i == 6) ? 1 : 0) ^ 1);
        }
        if (this.p == null) {
            return;
        }
        if (this.u == null) {
            WindowAndroid windowAndroid = aVar.a;
            this.u = windowAndroid;
            if (windowAndroid != null) {
                windowAndroid.v.a(this);
            }
        }
        vk2 vk2Var = new vk2(this.p, this.i, this, aVar, this.j);
        this.t = vk2Var;
        this.l = j;
        this.z = vk2Var.l;
        this.o = ak2.a(this.s, this.v);
        o(this.v.a, new d92(2, this));
    }

    public final void g(Runnable runnable, List list, List list2) {
        int i;
        int i2;
        int i3;
        int iW;
        vk2 vk2Var = this.t;
        HashSet hashSet = this.o;
        String str = this.s.f;
        int i4 = this.v.j;
        zk2 zk2Var = this.x;
        a aVar = vk2Var.j;
        String strA = aVar.b;
        String strMeroQv$e = N.MeroQv$e(aVar.e);
        boolean zContains = hashSet.contains(5);
        Activity activity = vk2Var.e;
        if (zContains || hashSet.contains(7)) {
            new lk2(activity, vk2Var.j.g.get(0), new lj1(23, vk2Var)).c(i9.e);
            if (TextUtils.isEmpty(strMeroQv$e)) {
                strMeroQv$e = vk2Var.x(str);
            }
        } else if (hashSet.contains(6)) {
            vk2Var.z(AppCompatResources.a(activity, R$drawable.generic_file));
            if (TextUtils.isEmpty(strMeroQv$e)) {
                strMeroQv$e = vk2Var.x(str);
            }
        } else if (hashSet.size() == 1 && (hashSet.contains(3) || hashSet.contains(2))) {
            vk2Var.z(AppCompatResources.a(activity, R$drawable.text_icon));
            strMeroQv$e = vk2Var.j.a();
            ((TextView) vk2Var.i.findViewById(R$id.subtitle_preview)).setMaxLines(2);
            strA = "";
        } else {
            String str2 = vk2Var.j.e;
            if (!str2.isEmpty()) {
                vk2Var.f.b(new GURL(str2), activity.getResources().getDimensionPixelSize(R$dimen.default_favicon_min_size), new pq2(28, vk2Var));
            }
        }
        if (zk2Var.e || zk2Var.f) {
            if (vk2Var.m == null) {
                int i5 = zk2Var.c.j;
                vk2Var.m = Integer.valueOf(((i5 == 3 || i5 == 5 || i5 == 6) ? 1 : 0) ^ 1);
            }
            if (vk2Var.m.intValue() == 0) {
                i2 = R$drawable.link;
                i3 = R$color.default_icon_color_accent1_tint_list;
                iW = R$string.link_toggle_include_link;
            } else {
                i2 = R$drawable.link_off;
                i3 = R$color.default_icon_color_tint_list;
                iW = vk2.w(i4);
            }
            if (i4 == 3) {
                int i6 = vk2Var.l;
                if (i6 == 1) {
                    iW = R$string.link_to_text_success_link_toast_message;
                } else if (i6 == 0) {
                    iW = R$string.link_to_text_success_text_toast_message;
                } else if (i6 == 2) {
                    iW = R$string.link_to_text_failure_toast_message_v2;
                }
            }
            ViewGroup viewGroup = vk2Var.i;
            int i7 = R$id.link_toggle_view;
            ImageView imageView = (ImageView) viewGroup.findViewById(i7);
            imageView.setColorFilter(bz.b(activity, i3).getDefaultColor());
            imageView.setVisibility(0);
            imageView.setImageDrawable(AppCompatResources.a(activity, i2));
            imageView.setContentDescription(null);
            imageView.setContentDescription(activity.getResources().getString(iW));
            vk2Var.c(imageView);
            if (vk2Var.m.intValue() == 1) {
                View viewFindViewById = vk2Var.i.findViewById(i7);
                int i8 = -activity.getResources().getDimensionPixelOffset(R$dimen.toggle_iph_y_inset);
                Rect rect = new Rect(0, i8, 0, i8);
                pk3 pk3Var = new pk3(activity, new Handler(Looper.getMainLooper()));
                Resources resources = activity.getResources();
                int i9 = R$string.link_toggle_iph;
                hw0 hw0Var = new hw0(resources, "IPH_SharingHubLinkToggle", i9, i9);
                hw0Var.j = viewFindViewById;
                i = 1;
                hw0Var.q = new wm3.a(1);
                hw0Var.n = rect;
                hw0Var.r = 2;
                pk3Var.a(hw0Var.a());
            } else {
                i = 1;
            }
            imageView.setOnClickListener(new ViewOnClickListenerC0202o0(i4, i, vk2Var));
        } else {
            i = 1;
        }
        if ((hashSet.contains(2) || hashSet.contains(3)) && hashSet.contains(Integer.valueOf(i))) {
            a aVar2 = vk2Var.j;
            String str3 = aVar2.l;
            String str4 = aVar2.m;
            if (str4 != null) {
                Object[] objArr = new Object[i];
                objArr[0] = str3;
                str3 = String.format(str4, objArr);
            }
            a aVar3 = vk2Var.j;
            if (str3 != null) {
                String str5 = aVar3.l;
                String str6 = aVar3.m;
                if (str6 != null) {
                    str5 = String.format(str6, str5);
                }
                strA = str5;
            } else {
                strA = aVar3.a();
            }
            c6.f((TextView) vk2Var.i.findViewById(R$id.title_preview), R$style.TextAppearance_TextMedium_Primary);
            ((TextView) vk2Var.i.findViewById(R$id.subtitle_preview)).setMaxLines(1);
        } else if (!TextUtils.isEmpty(strA)) {
            c6.f((TextView) vk2Var.i.findViewById(R$id.title_preview), R$style.TextAppearance_TextMediumThick_Primary);
        }
        TextView textView = (TextView) vk2Var.i.findViewById(R$id.title_preview);
        textView.setText(strA);
        ((TextView) vk2Var.i.findViewById(R$id.subtitle_preview)).setText(strMeroQv$e);
        if (TextUtils.isEmpty(strA)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
        vk2Var.v(list);
        ViewGroup viewGroup2 = vk2Var.i;
        int i10 = R$id.share_sheet_other_apps;
        RecyclerView recyclerView = (RecyclerView) viewGroup2.findViewById(i10);
        if (BuildInfo.a.a.m) {
            recyclerView.setVisibility(8);
            vk2Var.i.findViewById(R$id.share_sheet_divider).setVisibility(8);
        } else {
            vk2Var.y(list2, (RecyclerView) vk2Var.i.findViewById(i10), false);
            recyclerView.addOnScrollListener(new vk2.a("SharingHubAndroid.ThirdPartyAppsScrolled"));
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    public final String i(a aVar, jr jrVar) {
        if (!TextUtils.isEmpty(aVar.e)) {
            return aVar.e;
        }
        if (!jrVar.d.k()) {
            return jrVar.d.j();
        }
        nw2<Tab> nw2Var = this.f;
        return (nw2Var.f() && nw2Var.get().isInitialized()) ? nw2Var.get().getUrl().j() : "";
    }

    public final boolean m(jr jrVar) {
        nw2<Tab> nw2Var;
        return jrVar.j == 3 && (nw2Var = this.f) != null && nw2Var.f();
    }

    public final void n(a aVar, jr jrVar, long j) {
        if (m(jrVar)) {
            if (!jrVar.g) {
                oj2.U(0, 3, "SharedHighlights.LinkToTextDiagnoseStatus");
            }
            this.w = new s71(this.f.get(), this, jrVar, j, i(aVar, jrVar), aVar.a());
        }
        this.x = new zk2(aVar, jrVar, this.w);
        if (!m(jrVar)) {
            d(aVar, jrVar, j);
            return;
        }
        s71 s71Var = this.w;
        int i = 10;
        if (s71Var.g.g) {
            s71Var.B1();
            if (s71Var.j == null) {
                s71Var.z1(1);
                return;
            }
            d73 d73Var = rh3.a;
            zx1 zx1Var = new zx1(i, s71Var);
            sk skVar = bq.a;
            PostTask.b(d73Var, zx1Var, N.M37SqSAy("PreemptiveLinkToTextGeneration", "TimeoutLengthMs", 100));
            s71Var.n = 1;
            s71Var.j.c1(new u71(new td2(25, s71Var)));
            return;
        }
        if (!N.MnwPB_N7(new GURL(s71Var.i))) {
            s71Var.y1(12);
            return;
        }
        if (s71Var.f.c().y() != s71Var.f.c().U()) {
            sk skVar2 = bq.a;
            if (!N.M09VlOh_("SharedHighlightingAmp") || !N.Mt2IgP1o(new GURL(s71Var.i))) {
                s71Var.y1(10);
                return;
            }
        }
        d73 d73Var2 = rh3.a;
        f52 f52Var = new f52(4, s71Var);
        sk skVar3 = bq.a;
        PostTask.b(d73Var2, f52Var, N.M37SqSAy("PreemptiveLinkToTextGeneration", "TimeoutLengthMs", 100));
        oj2.U(1, 3, "SharedHighlights.LinkToTextDiagnoseStatus");
        s71Var.B1();
        f83 f83Var = s71Var.j;
        if (f83Var == null) {
            s71Var.y1(13);
        } else {
            s71Var.n = 1;
            f83Var.r0(new x71(new ke0(18, s71Var)));
        }
    }

    public final void o(final boolean z, d92 d92Var) {
        xk2 xk2Var;
        ArrayList arrayListG;
        Activity activity = this.p;
        a aVar = this.s;
        jr jrVar = this.v;
        HashSet hashSet = this.o;
        if (this.m) {
            arrayListG = new ArrayList();
            xk2Var = this;
        } else {
            xk2Var = this;
            xk2Var.r = new er(activity, this.u, this.f, this.e, this.t, aVar, this.g, this.l, this, this.j, i(aVar, jrVar), this.z, this.A, this.k);
            boolean zIsInMultiWindowMode = activity.isInMultiWindowMode();
            xk2Var.n = zIsInMultiWindowMode;
            arrayListG = xk2Var.r.g(hashSet, jrVar.j, zIsInMultiWindowMode);
        }
        if (BuildInfo.a.a.m) {
            xk2Var.g(d92Var, arrayListG, new ArrayList());
            return;
        }
        final Activity activity2 = xk2Var.p;
        final a aVar2 = xk2Var.s;
        HashSet hashSet2 = xk2Var.o;
        final sl0 sl0Var = new sl0(xk2Var, 1, arrayListG, d92Var);
        if (aVar2 == null) {
            PostTask.c(rh3.a, sl0Var.p1(null));
            return;
        }
        final fl2 fl2Var = xk2Var.y;
        fl2Var.getClass();
        String str = hashSet2.contains(5) ? "image" : "other";
        PackageManager packageManager = xz.a.getPackageManager();
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(hk2.e(), 0);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addFlags(524288);
        intent.setType(aVar2.f);
        listQueryIntentActivities.addAll(packageManager.queryIntentActivities(intent, 0));
        ArrayList arrayList = new ArrayList();
        final HashMap map = new HashMap();
        String packageName = xz.a.getPackageName();
        ArrayList<ResolveInfo> arrayList2 = new ArrayList();
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            if (!resolveInfo.activityInfo.packageName.equals(packageName)) {
                arrayList2.add(resolveInfo);
            }
        }
        ArrayList<ResolveInfo> arrayList3 = new ArrayList();
        for (ResolveInfo resolveInfo2 : arrayList2) {
            if (!fl2.h.contains(resolveInfo2.activityInfo.packageName)) {
                arrayList3.add(resolveInfo2);
            }
        }
        Collections.sort(arrayList3, new fl2.a());
        for (ResolveInfo resolveInfo3 : arrayList3) {
            String str2 = resolveInfo3.activityInfo.packageName + "/" + resolveInfo3.activityInfo.name;
            arrayList.add(str2);
            map.put(str2, resolveInfo3);
        }
        int i = xz.a.getResources().getDisplayMetrics().widthPixels;
        int dimensionPixelSize = activity2.getResources().getDimensionPixelSize(R$dimen.sharing_hub_tile_width);
        int dimensionPixelSize2 = activity2.getResources().getDimensionPixelSize(R$dimen.sharing_hub_tile_margin) * 2;
        int i2 = (i - dimensionPixelSize2) / (dimensionPixelSize + dimensionPixelSize2);
        N.MvxJx5iS(fl2Var.c, str, arrayList.toArray(), i2, i2, !fl2Var.c.h() && z, new t02(new Callback() { // from class: dl2
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                a aVar3;
                ResolveInfo resolveInfo4;
                Drawable drawableLoadIcon;
                int iconResource;
                final a aVar4 = aVar2;
                final boolean z2 = z;
                final fl2 fl2Var2 = fl2Var;
                fl2Var2.getClass();
                ArrayList arrayList4 = new ArrayList();
                for (String str3 : (List) obj) {
                    if (str3.equals("$more")) {
                        int i3 = R$drawable.sharing_more;
                        Activity activity3 = activity2;
                        arrayList4.add(cl2.a(AppCompatResources.a(activity3, i3), activity3.getResources().getString(R$string.sharing_more_icon_label), null, new View.OnClickListener() { // from class: el2
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                fl2 fl2Var3 = fl2Var2;
                                xk2.k("SharingHubAndroid.MoreSelected", fl2Var3.f, fl2Var3.g, fl2Var3.e, fl2Var3.c);
                                fl2Var3.a.c(fl2Var3.d, true);
                                ShareHelper.c(0);
                                a aVar5 = aVar4;
                                if (z2) {
                                    aVar5.n = new hk2.b(fl2Var3.c, aVar5.n);
                                }
                                hk2.f(aVar5.a, ShareHelper.b(aVar5), aVar5.n, null);
                                aVar5.n = null;
                            }
                        }, false));
                    } else {
                        if (!str3.equals("")) {
                            final vk2 vk2Var = fl2Var2.d;
                            final ResolveInfo resolveInfo5 = (ResolveInfo) map.get(str3);
                            final long j = fl2Var2.e;
                            final int i4 = fl2Var2.f;
                            final al2.a aVar5 = fl2Var2.g;
                            final cl2 cl2Var = fl2Var2.b;
                            cl2Var.getClass();
                            final a aVar6 = aVar4;
                            aVar3 = aVar4;
                            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: bl2
                                public final /* synthetic */ int j = -1;

                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    long j2 = j;
                                    int i5 = i4;
                                    al2.a aVar7 = aVar5;
                                    cl2 cl2Var2 = cl2Var;
                                    cl2Var2.getClass();
                                    ActivityInfo activityInfo = resolveInfo5.activityInfo;
                                    int i6 = this.j;
                                    if (i6 >= 0) {
                                        oj2.U(i6, 8, "Sharing.SharingHubAndroid.ThirdPartyAppUsage");
                                    }
                                    xk2.k("SharingHubAndroid.ThirdPartyAppSelected", i5, aVar7, j2, cl2Var2.c);
                                    ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                                    a aVar8 = aVar6;
                                    a.b bVar = aVar8.n;
                                    if (bVar != null) {
                                        bVar.a(componentName);
                                        aVar8.n = null;
                                    }
                                    cl2Var2.a.c(vk2Var, true);
                                    hk2.g(aVar8, componentName, cl2Var2.c, z2);
                                }
                            };
                            PackageManager packageManager2 = cl2Var.b;
                            try {
                                iconResource = resolveInfo5.getIconResource();
                            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
                            }
                            if (iconResource != 0) {
                                resolveInfo4 = resolveInfo5;
                                try {
                                    drawableLoadIcon = c6.c(packageManager2.getResourcesForApplication(resolveInfo4.activityInfo.packageName), iconResource, 0);
                                } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused2) {
                                    drawableLoadIcon = resolveInfo4.loadIcon(packageManager2);
                                }
                                arrayList4.add(cl2.a(drawableLoadIcon, (String) resolveInfo4.loadLabel(packageManager2), null, onClickListener, false));
                            } else {
                                resolveInfo4 = resolveInfo5;
                            }
                            drawableLoadIcon = resolveInfo4.loadIcon(packageManager2);
                            arrayList4.add(cl2.a(drawableLoadIcon, (String) resolveInfo4.loadLabel(packageManager2), null, onClickListener, false));
                        }
                        aVar4 = aVar3;
                    }
                    aVar3 = aVar4;
                    aVar4 = aVar3;
                }
                PostTask.c(rh3.a, sl0Var.p1(arrayList4));
            }
        }, 3));
    }

    @Override // defpackage.bx
    public final void onConfigurationChanged(Configuration configuration) {
        boolean zIsInMultiWindowMode;
        HashSet hashSet;
        Activity activity = this.p;
        if (activity == null || this.n == (zIsInMultiWindowMode = activity.isInMultiWindowMode()) || (hashSet = this.o) == null) {
            return;
        }
        this.n = zIsInMultiWindowMode;
        this.t.v(this.r.g(hashSet, this.v.j, zIsInMultiWindowMode));
        this.e.d(this.t, false);
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i7 - i5 == i3 - i) {
            return;
        }
        ViewGroup viewGroup = this.t.i;
        int i9 = R$id.share_sheet_chrome_apps;
        viewGroup.findViewById(i9).invalidate();
        ao3.e(this.t.i.findViewById(i9), "ShareSheetCoordinator.onLayoutChange first party view");
        ViewGroup viewGroup2 = this.t.i;
        int i10 = R$id.share_sheet_other_apps;
        viewGroup2.findViewById(i10).invalidate();
        ao3.e(this.t.i.findViewById(i10), "ShareSheetCoordinator.onLayoutChange third party view");
    }

    public final void p(al2.a aVar, int i) {
        if (this.w == null && this.x == null) {
            return;
        }
        a aVarA = this.x.a(aVar.a);
        this.s = aVarA;
        this.t.j = aVarA;
        this.z = i;
        this.A = aVar;
        this.o = ak2.a(aVarA, this.v);
        o(this.v.a, null);
    }

    @Override // org.chromium.ui.base.WindowAndroid.b
    public final void b() {
    }

    @Override // org.chromium.ui.base.WindowAndroid.b
    public final void e() {
    }
}
