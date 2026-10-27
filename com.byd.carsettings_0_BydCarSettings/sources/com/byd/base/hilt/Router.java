package com.byd.base.hilt;

import android.app.Application;
import android.util.Log;
import b.b.a.a.a;
import com.byd.base.hilt.impl.BodyDeviceImpl;
import com.byd.base.hilt.impl.StudyConfigImpl;
import com.byd.base.hilt.inter.IAdasDataRepository;
import com.byd.base.hilt.inter.IStudyConfig;

/* JADX INFO: loaded from: classes.dex */
public class Router {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile Router f2427a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public StudyConfigImpl f2428b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BodyDeviceImpl f2429c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public IAdasDataRepository f2430d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Application f2431e;

    private Router() {
        Log.i("Router", "construct is run =" + hashCode());
        c((Application) a.b().a());
    }

    public static Router b() {
        if (f2427a == null) {
            synchronized (Router.class) {
                if (f2427a == null) {
                    f2427a = new Router();
                }
            }
        }
        return f2427a;
    }

    public Application a() {
        return this.f2431e;
    }

    public final void c(Application application) {
        this.f2431e = application;
        if (this.f2429c == null) {
            this.f2429c = new BodyDeviceImpl(application);
        }
        if (this.f2428b == null) {
            this.f2428b = new StudyConfigImpl(application, this.f2429c);
        }
    }

    public IAdasDataRepository d() {
        return this.f2430d;
    }

    public BodyDeviceImpl e() {
        return this.f2429c;
    }

    public IStudyConfig f() {
        return this.f2428b;
    }

    public void g() {
        StudyConfigImpl studyConfigImpl = this.f2428b;
        if (studyConfigImpl != null) {
            studyConfigImpl.j();
            this.f2428b = null;
        }
        BodyDeviceImpl bodyDeviceImpl = this.f2429c;
        if (bodyDeviceImpl != null) {
            bodyDeviceImpl.m();
            this.f2429c = null;
        }
        IAdasDataRepository iAdasDataRepository = this.f2430d;
        if (iAdasDataRepository != null) {
            iAdasDataRepository.d();
        }
        this.f2430d = null;
        this.f2431e = null;
        f2427a = null;
        Log.i("Router", "releaseRouter is end =" + hashCode());
    }

    public void h(IAdasDataRepository iAdasDataRepository) {
        this.f2430d = iAdasDataRepository;
    }
}
