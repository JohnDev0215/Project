package defpackage;

import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: EmptyTabModel.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lh0 implements TabModel {

    /* JADX INFO: compiled from: EmptyTabModel.java */
    public static class a {
        public static final lh0 a = new lh0();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean a(Tab tab) {
        return false;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final Profile f() {
        return null;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final Tab g(int i, boolean z) {
        return null;
    }

    @Override // defpackage.w03
    public final int getCount() {
        return 0;
    }

    @Override // defpackage.w03
    public final Tab getTabAt(int i) {
        return null;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean h(Tab tab, Tab tab2, boolean z, boolean z2) {
        return false;
    }

    @Override // defpackage.w03
    public final int index() {
        return -1;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean isActiveModel() {
        return false;
    }

    @Override // defpackage.w03
    public final boolean isIncognito() {
        return false;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean m(Tab tab, boolean z, boolean z2) {
        return false;
    }

    @Override // defpackage.w03
    public final int r(Tab tab) {
        return -1;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean u() {
        return false;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean w(int i) {
        return false;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void b() {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void c() {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void d() {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void destroy() {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final w03 k() {
        return this;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void v() {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void e(boolean z) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void j(t13 t13Var) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void l(t13 t13Var) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void n(int i) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void o(Tab tab) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void p(List list) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void setActive(boolean z) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void x(int i) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void s(int i, int i2) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void q(int i, int i2, boolean z) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void i(Tab tab, int i, int i2, int i3) {
    }
}
