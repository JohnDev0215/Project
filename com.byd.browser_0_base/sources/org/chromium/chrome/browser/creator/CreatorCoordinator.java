package org.chromium.chrome.browser.creator;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.bg;
import defpackage.cg;
import defpackage.ef2;
import defpackage.g3;
import defpackage.ii2;
import defpackage.j10;
import defpackage.k10;
import defpackage.m10;
import defpackage.n10;
import defpackage.n3;
import defpackage.o10;
import defpackage.on2;
import defpackage.ps;
import defpackage.t41;
import defpackage.ul0;
import defpackage.wb;
import defpackage.wp2;
import defpackage.yk0;
import defpackage.yo;
import defpackage.yq2;
import defpackage.zj;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.feed.FeedStream;
import org.chromium.chrome.browser.feed.NativeViewListRenderer;
import org.chromium.chrome.browser.feed.f;
import org.chromium.chrome.browser.feed.g;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public final class CreatorCoordinator implements yk0, View.OnLayoutChangeListener {
    public final ViewGroup e;
    public final Activity f;
    public f g = new f();
    public final RecyclerView h;
    public NativeViewListRenderer i;
    public final PropertyModel j;
    public final wp2 k;
    public final WindowAndroid l;
    public org.chromium.components.browser_ui.bottomsheet.f m;
    public ef2 n;
    public FrameLayout o;
    public FeedStream p;
    public int q;
    public final int r;

    public static class FaviconLoader {
        public FaviconLoader(Context context) {
            new FaviconHelper();
            org.chromium.chrome.browser.ui.favicon.a.a(context);
            context.getResources().getDimensionPixelSize(R$dimen.preview_tab_favicon_size);
        }
    }

    public class a implements g.a {
        public a() {
        }

        @Override // org.chromium.chrome.browser.feed.g.a
        public final void a(ArrayList arrayList) {
            boolean z;
            if (arrayList == null) {
                return;
            }
            CreatorCoordinator creatorCoordinator = CreatorCoordinator.this;
            int i = creatorCoordinator.q;
            while (true) {
                if (i >= arrayList.size()) {
                    z = false;
                    break;
                } else {
                    if (((f.b) arrayList.get(i)).a()) {
                        z = true;
                        break;
                    }
                    i++;
                }
            }
            if (z) {
                return;
            }
            ArrayList arrayList2 = new ArrayList();
            Activity activity = creatorCoordinator.f;
            arrayList2.add(new f.c(activity.getResources().getDimensionPixelSize(R$dimen.content_previews_padding), LayoutInflater.from(activity).inflate(R$layout.creator_privacy, (ViewGroup) null), "CreatorPrivacyId"));
            creatorCoordinator.g.a(creatorCoordinator.q, arrayList2);
            creatorCoordinator.q = arrayList2.size() + creatorCoordinator.q;
            creatorCoordinator.p.c(this);
            creatorCoordinator.p.z = creatorCoordinator.q;
        }
    }

    public class b implements g.b {
        public b() {
        }

        @Override // org.chromium.chrome.browser.feed.g.b
        public final void a() {
            CreatorCoordinator creatorCoordinator = CreatorCoordinator.this;
            creatorCoordinator.h.findViewById(R$id.creator_follow_button).setEnabled(false);
            creatorCoordinator.h.findViewById(R$id.creator_following_button).setEnabled(false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v1, types: [T, java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r15v2, types: [T, java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.String] */
    public CreatorCoordinator(Activity activity, byte[] bArr, wp2 wp2Var, g3 g3Var, String str, int i, boolean z) {
        this.f = activity;
        this.k = wp2Var;
        this.l = g3Var;
        ul0.b().getClass();
        NativeViewListRenderer nativeViewListRenderer = new NativeViewListRenderer(activity);
        this.i = nativeViewListRenderer;
        RecyclerView recyclerViewD = nativeViewListRenderer.d(this.g);
        recyclerViewD.setId(R$id.creator_feed_stream_recycler_view);
        recyclerViewD.setClipToPadding(false);
        recyclerViewD.setBackgroundColor(ii2.a(activity));
        this.h = recyclerViewD;
        this.r = i;
        o10 o10Var = new o10(activity, wp2Var);
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.creator_profile, (ViewGroup) null);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new f.c(activity.getResources().getDimensionPixelSize(R$dimen.content_previews_padding), viewInflate, "CreatorProfileView"));
        this.g.a(0, arrayList);
        this.q = 1;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(activity).inflate(R$layout.creator_activity, (ViewGroup) null);
        this.e = viewGroup;
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R$id.creator_layout);
        viewGroup2.addView(recyclerViewD);
        ?? MpICpYBr = N.MpICpYBr(new GURL(str));
        HashMap mapE = PropertyModel.e(n10.i);
        PropertyModel.r<byte[]> rVar = n10.a;
        int i2 = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = bArr;
        mapE.put(rVar, gVar);
        PropertyModel.r<String> rVar2 = n10.c;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = str;
        mapE.put(rVar2, gVar2);
        PropertyModel.r<Boolean> rVar3 = n10.e;
        ?? ValueOf = Boolean.valueOf(z);
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = ValueOf;
        mapE.put(rVar3, gVar3);
        PropertyModel.r<Boolean> rVar4 = n10.h;
        ?? r15 = Boolean.FALSE;
        PropertyModel.g gVar4 = new PropertyModel.g();
        gVar4.a = r15;
        mapE.put(rVar4, gVar4);
        PropertyModel.r<String> rVar5 = n10.d;
        PropertyModel.g gVar5 = new PropertyModel.g();
        gVar5.a = MpICpYBr;
        mapE.put(rVar5, gVar5);
        PropertyModel propertyModel = new PropertyModel(mapE);
        this.j = propertyModel;
        if (bArr != 0) {
            N.MHvYsLrH((byte[]) propertyModel.j(rVar), new wb(14, this));
        }
        this.n = new ef2(activity, new j10(), viewGroup, activity.getResources().getColor(R$color.default_scrim_color));
        FrameLayout frameLayout = new FrameLayout(activity);
        this.o = frameLayout;
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        viewGroup.addView(this.o);
        int i3 = 2;
        this.m = new org.chromium.components.browser_ui.bottomsheet.f(new yo(i3, this), new ps(3), activity.getWindow(), t41.f, new yq2(i3, this));
        j.b(propertyModel, (CreatorProfileView) viewInflate, new bg(4));
        j.b(propertyModel, (CreatorToolbarView) viewGroup2, new cg(3));
        recyclerViewD.addOnScrollListener(new k10(this));
        new m10(propertyModel, o10Var);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [byte[], java.io.Serializable] */
    public final void b() {
        Activity activity = this.f;
        wp2 wp2Var = this.k;
        org.chromium.components.browser_ui.bottomsheet.f fVar = this.m;
        WindowAndroid windowAndroid = this.l;
        b bVar = new b();
        ?? r0 = (byte[]) this.j.j(n10.a);
        int i = this.r;
        FeedStream feedStream = new FeedStream(activity, wp2Var, fVar, false, windowAndroid, 3, this, bVar, new on2(r0, i));
        this.p = feedStream;
        if (i == 0) {
            feedStream.h(new a());
        }
        this.p.b(this.h, this.g, null, this.i, new n3(), this.q);
    }

    @Override // defpackage.yk0
    public final void a(int i) {
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
    }
}
