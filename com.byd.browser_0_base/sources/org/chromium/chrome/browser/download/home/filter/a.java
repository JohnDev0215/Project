package org.chromium.chrome.browser.download.home.filter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import defpackage.aw;
import defpackage.gx;
import defpackage.ho;
import defpackage.l10;
import defpackage.lg2;
import defpackage.mo;
import defpackage.qm0;
import defpackage.qq1;
import defpackage.rq1;
import defpackage.sm0;
import defpackage.vh3;
import defpackage.wb;
import defpackage.xa1;
import defpackage.ya1;
import defpackage.z7;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: FilterChipsProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements qq1 {
    public final Context e;
    public final InterfaceC0079a f;
    public final rq1 g;
    public final Handler h = new Handler();
    public final ya1 i;
    public final ya1 j;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.download.home.filter.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FilterChipsProvider.java */
    public interface InterfaceC0079a {
    }

    public a(Context context, gx gxVar, lg2 lg2Var) {
        ya1 ya1Var = new ya1();
        this.i = ya1Var;
        this.j = new ya1();
        this.e = context;
        this.f = gxVar;
        this.g = lg2Var;
        wb wbVar = new wb(21, this);
        xa1 xa1VarA = mo.a(0, R$drawable.settings_all_sites, context.getString(R$string.download_manager_ui_all_downloads), wbVar);
        xa1 xa1VarA2 = mo.a(2, R$drawable.ic_videocam_24dp, context.getString(R$string.download_manager_ui_video), wbVar);
        xa1 xa1VarA3 = mo.a(3, R$drawable.ic_music_note_24dp, context.getString(R$string.download_manager_ui_audio), wbVar);
        xa1 xa1VarA4 = mo.a(4, R$drawable.ic_drive_image_24dp, context.getString(R$string.download_manager_ui_images), wbVar);
        xa1 xa1VarA5 = mo.a(1, R$drawable.ic_globe_24dp, context.getString(R$string.download_manager_ui_pages), wbVar);
        xa1 xa1VarA6 = mo.a(6, R$drawable.ic_drive_file_24dp, context.getString(R$string.download_manager_ui_other), wbVar);
        xa1VarA.b.l(ho.g, true);
        ya1Var.t(xa1VarA);
        ya1Var.t(xa1VarA2);
        ya1Var.t(xa1VarA3);
        ya1Var.t(xa1VarA4);
        ya1Var.t(xa1VarA5);
        ya1Var.t(xa1VarA6);
        lg2Var.a(this);
        a();
    }

    public final void a() {
        HashMap map = new HashMap();
        Iterator<OfflineItem> it = this.g.h().iterator();
        while (true) {
            int iIntValue = 0;
            if (!it.hasNext()) {
                break;
            }
            int iIntValue2 = z7.l(it.next()).intValue();
            if (map.containsKey(Integer.valueOf(iIntValue2))) {
                iIntValue = ((Integer) map.get(Integer.valueOf(iIntValue2))).intValue();
            }
            map.put(Integer.valueOf(iIntValue2), Integer.valueOf(iIntValue + 1));
        }
        int iIntValue3 = 0;
        for (Map.Entry entry : map.entrySet()) {
            if (((Integer) entry.getKey()).intValue() != 7) {
                iIntValue3 += ((Integer) entry.getValue()).intValue();
            }
        }
        map.put(0, Integer.valueOf(iIntValue3));
        ya1 ya1Var = this.i;
        for (xa1 xa1Var : ya1Var) {
            int iH = xa1Var.b.h(ho.f);
            boolean zContainsKey = map.containsKey(Integer.valueOf(iH));
            PropertyModel.n nVar = ho.c;
            PropertyModel propertyModel = xa1Var.b;
            propertyModel.l(nVar, zContainsKey);
            if (propertyModel.k(nVar)) {
                PropertyModel.r<String> rVar = ho.b;
                Resources resources = this.e.getResources();
                int iIntValue4 = ((Integer) map.get(Integer.valueOf(iH))).intValue();
                int i = vh3.a;
                propertyModel.p(rVar, iH != 0 ? iH != 1 ? iH != 2 ? iH != 3 ? iH != 4 ? iH != 6 ? null : resources.getQuantityString(R$plurals.accessibility_download_manager_ui_generic, iIntValue4, Integer.valueOf(iIntValue4)) : resources.getQuantityString(R$plurals.accessibility_download_manager_ui_images, iIntValue4, Integer.valueOf(iIntValue4)) : resources.getQuantityString(R$plurals.accessibility_download_manager_ui_audio, iIntValue4, Integer.valueOf(iIntValue4)) : resources.getQuantityString(R$plurals.accessibility_download_manager_ui_video, iIntValue4, Integer.valueOf(iIntValue4)) : resources.getQuantityString(R$plurals.accessibility_download_manager_ui_pages, iIntValue4, Integer.valueOf(iIntValue4)) : resources.getQuantityString(R$plurals.accessibility_download_manager_ui_generic, iIntValue4, Integer.valueOf(iIntValue4)));
            }
        }
        ya1 ya1Var2 = this.j;
        ya1Var2.clear();
        for (xa1 xa1Var2 : ya1Var) {
            if (xa1Var2.b.k(ho.c)) {
                ya1Var2.t(xa1Var2);
            }
        }
        if (ya1Var2.size() <= 2) {
            ya1Var2.clear();
        }
        for (xa1 xa1Var3 : ya1Var) {
            if (xa1Var3.b.k(ho.g) && !xa1Var3.b.k(ho.c)) {
                g(0);
                b bVar = (b) ((gx) this.f).f;
                bVar.b(bVar.b.h(sm0.b));
                return;
            }
        }
    }

    @Override // defpackage.qq1
    public final void b(Collection<OfflineItem> collection) {
        this.h.post(new aw(26, this));
    }

    @Override // defpackage.qq1
    public final void d(Collection<OfflineItem> collection) {
        this.h.post(new qm0(0, this));
    }

    @Override // defpackage.qq1
    public final void e(OfflineItem offlineItem, OfflineItem offlineItem2) {
        if (offlineItem.h == offlineItem2.h) {
            return;
        }
        this.h.post(new l10(8, this));
    }

    public final int f() {
        for (xa1 xa1Var : this.i) {
            if (xa1Var.b.k(ho.g)) {
                return xa1Var.b.h(ho.f);
            }
        }
        return 0;
    }

    public final void g(int i) {
        int i2 = 0;
        while (true) {
            ya1 ya1Var = this.i;
            if (i2 >= ya1Var.size()) {
                return;
            }
            PropertyModel propertyModel = ya1Var.get(i2).b;
            propertyModel.l(ho.g, propertyModel.h(ho.f) == i);
            i2++;
        }
    }
}
