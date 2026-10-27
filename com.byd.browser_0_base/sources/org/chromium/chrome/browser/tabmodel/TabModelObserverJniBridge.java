package org.chromium.chrome.browser.tabmodel;

import J.N;
import defpackage.t13;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: loaded from: classes2.dex */
public class TabModelObserverJniBridge implements t13 {
    public long e;
    public TabModel f;

    public TabModelObserverJniBridge(long j, TabModel tabModel) {
        this.e = j;
        this.f = tabModel;
    }

    @CalledByNative
    public static TabModelObserverJniBridge create(long j, TabModel tabModel) {
        TabModelObserverJniBridge tabModelObserverJniBridge = new TabModelObserverJniBridge(j, tabModel);
        tabModel.j(tabModelObserverJniBridge);
        return tabModelObserverJniBridge;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        N.M15BMjns(this.e, this, tab, i, i2);
    }

    @Override // defpackage.t13
    public final void C(Tab tab) {
        N.MkEswtJX(this.e, this, tab);
    }

    @Override // defpackage.t13
    public final void P(boolean z) {
        N.M2XM7FhU(this.e, this);
    }

    @Override // defpackage.t13
    public final void V0(Tab tab) {
        N.MHr$s7jo(this.e, this, tab.getId(), tab.isIncognito());
    }

    @Override // defpackage.t13
    public final void Y(List<Tab> list) {
        N.MNLupLtx(this.e, this, (Tab[]) list.toArray(new Tab[0]));
    }

    @Override // defpackage.t13
    public final void d1(int i, Tab tab) {
        N.MMKCgOHG(this.e, this, tab, i);
    }

    @CalledByNative
    public final void detachFromTabModel() {
        this.f.l(this);
        this.e = 0L;
        this.f = null;
    }

    @Override // defpackage.t13
    public final void f1(Tab tab, int i, int i2, boolean z) {
        N.M7iC4IGa(this.e, this, tab, i);
    }

    @Override // defpackage.t13
    public final void h0(Tab tab) {
        N.MJ8c7fEV(this.e, this, tab);
    }

    @Override // defpackage.t13
    public final void h1(int i, int i2, Tab tab) {
        N.MMKEWgan(this.e, this, tab, i, i2);
    }

    @Override // defpackage.t13
    public final void i1(Tab tab) {
        N.MRzpb9vm(this.e, this, tab);
    }

    @Override // defpackage.t13
    public final void n0(Tab tab) {
        N.M9V3veZz(this.e, this, tab);
    }

    @Override // defpackage.t13
    public final void t(Tab tab, boolean z, boolean z2) {
        N.MSSvav7n(this.e, this, tab, z);
    }

    @Override // defpackage.t13
    public final void w0(List<Tab> list, boolean z) {
        N.M8YIOTao(this.e, this, (Tab[]) list.toArray(new Tab[0]));
    }

    @Override // defpackage.t13
    public final void p0() {
    }
}
