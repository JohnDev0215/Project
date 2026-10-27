package defpackage;

import android.util.SparseArray;
import kotlin.jvm.internal.SourceDebugExtension;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: AsyncTabParamsManagerImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
@SourceDebugExtension({"SMAP\nAsyncTabParamsManagerImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncTabParamsManagerImpl.kt\norg/chromium/chrome/browser/tabmodel/AsyncTabParamsManagerImpl\n*L\n1#1,79:1\n45#1,8:80\n*S KotlinDebug\n*F\n+ 1 AsyncTabParamsManagerImpl.kt\norg/chromium/chrome/browser/tabmodel/AsyncTabParamsManagerImpl\n*L\n34#1:80,8\n*E\n"})
public final class g9 implements f9 {
    public final SparseArray<e9> a = new SparseArray<>();
    public boolean b;

    /* JADX INFO: compiled from: AsyncTabParamsManagerImpl.kt */
    @SourceDebugExtension({"SMAP\nAsyncTabParamsManagerImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncTabParamsManagerImpl.kt\norg/chromium/chrome/browser/tabmodel/AsyncTabParamsManagerImpl$AsyncTabsIncognitoTabHost\n+ 2 AsyncTabParamsManagerImpl.kt\norg/chromium/chrome/browser/tabmodel/AsyncTabParamsManagerImpl\n*L\n1#1,79:1\n45#2,8:80\n*S KotlinDebug\n*F\n+ 1 AsyncTabParamsManagerImpl.kt\norg/chromium/chrome/browser/tabmodel/AsyncTabParamsManagerImpl$AsyncTabsIncognitoTabHost\n*L\n59#1:80,8\n*E\n"})
    public static final class a implements cz0 {
        public final g9 a;

        public a(g9 g9Var) {
            i21.e(g9Var, "mAsyncTabParamsManager");
            this.a = g9Var;
        }

        @Override // defpackage.cz0
        public final void a() {
            SparseArray<e9> sparseArray = this.a.a;
            SparseArray<e9> sparseArrayClone = sparseArray.clone();
            i21.d(sparseArrayClone, "params.clone()");
            int size = sparseArrayClone.size();
            for (int i = 0; i < size; i++) {
                Tab tabD = sparseArrayClone.valueAt(i).d();
                if (tabD != null ? tabD.isIncognito() : false) {
                    sparseArray.removeAt(i);
                }
            }
        }

        @Override // defpackage.cz0
        public final boolean b() {
            SparseArray<e9> sparseArray = this.a.a;
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                Tab tabD = sparseArray.valueAt(i).d();
                if (tabD != null && tabD.isIncognito()) {
                    return true;
                }
            }
            return false;
        }

        @Override // defpackage.cz0
        public final boolean isActiveModel() {
            return false;
        }
    }

    @Override // defpackage.f9
    public final boolean a(int i) {
        return this.a.get(i) != null;
    }

    @Override // defpackage.f9
    public final SparseArray<e9> b() {
        return this.a;
    }

    @Override // defpackage.f9
    public final void c(int i, e9 e9Var) {
        this.a.put(i, e9Var);
        if (this.b) {
            return;
        }
        dz0 dz0VarA = dz0.a();
        dz0VarA.a.add(new a(this));
        this.b = true;
    }

    public final boolean d() {
        SparseArray<e9> sparseArray = this.a;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            if (sparseArray.valueAt(i).d() != null) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.f9
    public final e9 remove(int i) {
        SparseArray<e9> sparseArray = this.a;
        e9 e9Var = sparseArray.get(i);
        sparseArray.remove(i);
        return e9Var;
    }
}
