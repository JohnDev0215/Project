package org.chromium.chrome.browser.feed.webfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import defpackage.bg;
import defpackage.bi1;
import defpackage.di1;
import defpackage.oj2;
import defpackage.qt;
import defpackage.v62;
import defpackage.vq3;
import defpackage.wq3;
import defpackage.xo;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;
import vq3.a;

/* JADX INFO: compiled from: WebFeedDialogCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final vq3 a;
    public Context b;

    public a(bi1 bi1Var) {
        this.a = new vq3(bi1Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v4, types: [T, java.lang.Object, java.lang.String] */
    public final void a(Context context, Runnable runnable, int i, String str, boolean z, boolean z2) {
        String string;
        Callback xoVar;
        T t;
        this.b = context;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.web_feed_dialog, (ViewGroup) null);
        oj2.U(!z ? 1 : 0, 2, "ContentSuggestions.Feed.WebFeed.PostFollowDialog.Show");
        int i2 = 3;
        if (z) {
            String string2 = this.b.getString(R$string.web_feed_post_follow_dialog_stories_ready_description, str);
            string = this.b.getString(i);
            string = z2 ? this.b.getString(R$string.close) : null;
            xoVar = new qt(runnable, i, z2);
            t = string2;
        } else {
            String string3 = this.b.getString(R$string.web_feed_post_follow_dialog_stories_not_ready_description, str);
            string = this.b.getString(R$string.ok);
            xoVar = new xo(i2);
            t = string3;
        }
        ?? string4 = this.b.getString(R$string.web_feed_post_follow_dialog_title, str);
        int i3 = R$drawable.web_feed_post_follow_illustration;
        PropertyModel.j jVar = wq3.a;
        PropertyModel.l<String> lVar = wq3.b;
        PropertyModel.l<String> lVar2 = wq3.c;
        HashMap mapE = PropertyModel.e(new v62[]{jVar, lVar, lVar2});
        int i4 = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = string4;
        mapE.put(lVar, gVar);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = t;
        mapE.put(lVar2, gVar2);
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i3;
        mapE.put(jVar, dVar);
        PropertyModel propertyModel = new PropertyModel(mapE);
        vq3 vq3Var = this.a;
        vq3Var.getClass();
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.h, viewInflate);
        bVar.e(di1.a, vq3Var.new a(xoVar));
        bVar.e(di1.b, string4);
        bVar.e(di1.j, string);
        bVar.e(di1.m, string);
        bVar.c(di1.u, string == null ? 0 : 1);
        vq3Var.b = bVar.a();
        org.chromium.ui.modelutil.j.b(propertyModel, viewInflate, new bg(7));
    }
}
