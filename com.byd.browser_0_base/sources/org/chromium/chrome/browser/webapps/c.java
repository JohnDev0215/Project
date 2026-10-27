package org.chromium.chrome.browser.webapps;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.net.Uri;
import android.text.TextUtils;
import defpackage.as3;
import defpackage.bp3;
import defpackage.ds3;
import defpackage.fs3;
import defpackage.gs3;
import defpackage.h20;
import defpackage.hy1;
import defpackage.i9;
import defpackage.k5;
import defpackage.kr2;
import defpackage.ku2;
import defpackage.ls3;
import defpackage.oj;
import defpackage.q2;
import defpackage.sz0;
import defpackage.t51;
import defpackage.xz;
import defpackage.z2;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.chrome.browser.webapps.WebappRegistry;

/* JADX INFO: compiled from: WebappActivityCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements sz0, hy1, kr2 {
    public final oj e;
    public final ls3 f;
    public final Activity g;
    public final e h;

    public c(Activity activity, oj ojVar, z2 z2Var, h20 h20Var, e eVar, q2 q2Var) {
        this.e = ojVar;
        ls3 ls3VarC = ls3.c(ojVar);
        this.f = ls3VarC;
        this.g = activity;
        this.h = eVar;
        new as3(z2Var, ojVar, h20Var);
        eVar.d.add(new bp3(this, q2Var, 1));
        q2Var.f(this);
        ku2 ku2VarT = ku2.t();
        try {
            WebappRegistry webappRegistry = WebappRegistry.b.a;
            WebappRegistry.b.a.d(ls3VarC.i());
            ku2VarT.close();
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // defpackage.hy1
    public final void f() {
        Activity activity = this.g;
        if (activity.isFinishing() || activity.getIntent() == null) {
            return;
        }
        Uri data = activity.getIntent().getData();
        int taskId = activity.getTaskId();
        if (data == null) {
            return;
        }
        String string = data.toString();
        ActivityManager activityManager = (ActivityManager) xz.a.getSystemService("activity");
        ArrayList<ActivityManager.AppTask> arrayList = new ArrayList();
        for (ActivityManager.AppTask appTask : activityManager.getAppTasks()) {
            ActivityManager.RecentTaskInfo recentTaskInfoC = k5.c(appTask);
            if (recentTaskInfoC != null) {
                int i = recentTaskInfoC.id;
                Intent intent = recentTaskInfoC.baseIntent;
                if (TextUtils.equals(string, intent == null ? null : intent.getDataString()) && (i == -1 || i != taskId)) {
                    arrayList.add(appTask);
                }
            }
        }
        for (ActivityManager.AppTask appTask2 : arrayList) {
            Objects.toString(appTask2);
            k5.c(appTask2);
            appTask2.finishAndRemoveTask();
        }
    }

    @Override // defpackage.kr2
    public final void j() {
        if (gs3.a.getAndSet(false)) {
            new fs3().c(i9.e);
        }
    }

    @Override // defpackage.sz0
    public final void q() {
        ArrayList arrayList = t51.a;
        ls3 ls3Var = this.f;
        int i = ls3Var.f().i;
        if (ls3Var.j() && i == 0) {
            ku2 ku2VarT = ku2.t();
            try {
                String strI = ls3Var.i();
                WebappRegistry webappRegistry = WebappRegistry.b.a;
                webappRegistry.d(strI);
                ds3 ds3VarC = webappRegistry.c(ls3Var.i());
                ku2VarT.close();
                if (ds3VarC == null || (i = ds3VarC.b.getInt(BYDAutoMultimediaDevice.PARAM_SOURCE, 0)) == 0) {
                    i = 11;
                }
            } catch (Throwable th) {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        t51.a.add(new t51.a(ls3Var.f().b, false, i, ls3Var));
    }

    @Override // defpackage.sz0
    public final void Y0() {
    }

    @Override // defpackage.hy1
    public final void h() {
    }

    @Override // defpackage.kr2
    public final void l() {
    }
}
