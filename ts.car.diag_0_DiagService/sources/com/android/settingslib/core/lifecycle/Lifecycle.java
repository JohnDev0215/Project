package com.android.settingslib.core.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.e;
import androidx.lifecycle.f;
import androidx.lifecycle.g;
import com.android.settingslib.core.lifecycle.b.c;
import com.android.settingslib.core.lifecycle.b.d;
import com.android.settingslib.core.lifecycle.b.h;
import com.android.settingslib.core.lifecycle.b.i;
import com.android.settingslib.core.lifecycle.b.j;
import com.android.settingslib.utils.b;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class Lifecycle extends g {
    private final List<com.android.settingslib.core.lifecycle.a> h;
    private final LifecycleProxy i;

    /* JADX INFO: compiled from: caradapter-bug */
    private class LifecycleProxy implements e {
        private LifecycleProxy() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        public void onLifecycleEvent(f fVar, Lifecycle.Event event) {
            switch (a.f1345a[event.ordinal()]) {
                case 2:
                    Lifecycle.this.C();
                    break;
                case 3:
                    Lifecycle.this.B();
                    break;
                case 4:
                    Lifecycle.this.z();
                    break;
                case 5:
                    Lifecycle.this.D();
                    break;
                case 6:
                    Lifecycle.this.x();
                    break;
                case 7:
                    Log.wtf("LifecycleObserver", "Should not receive an 'ANY' event!");
                    break;
            }
        }

        /* synthetic */ LifecycleProxy(Lifecycle lifecycle, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1345a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f1345a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1345a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1345a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1345a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1345a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1345a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1345a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public Lifecycle(@NonNull f fVar) {
        super(fVar);
        this.h = new ArrayList();
        LifecycleProxy lifecycleProxy = new LifecycleProxy(this, null);
        this.i = lifecycleProxy;
        a(lifecycleProxy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.android.settingslib.core.lifecycle.a aVar = this.h.get(i);
            if (aVar instanceof h) {
                ((h) aVar).b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C() {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.android.settingslib.core.lifecycle.a aVar = this.h.get(i);
            if (aVar instanceof i) {
                ((i) aVar).c();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.android.settingslib.core.lifecycle.a aVar = this.h.get(i);
            if (aVar instanceof j) {
                ((j) aVar).g();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.android.settingslib.core.lifecycle.a aVar = this.h.get(i);
            if (aVar instanceof d) {
                ((d) aVar).l();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.android.settingslib.core.lifecycle.a aVar = this.h.get(i);
            if (aVar instanceof com.android.settingslib.core.lifecycle.b.f) {
                ((com.android.settingslib.core.lifecycle.b.f) aVar).a();
            }
        }
    }

    public void A(Menu menu) {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.android.settingslib.core.lifecycle.a aVar = this.h.get(i);
            if (aVar instanceof com.android.settingslib.core.lifecycle.b.g) {
                ((com.android.settingslib.core.lifecycle.b.g) aVar).a(menu);
            }
        }
    }

    @Override // androidx.lifecycle.g, androidx.lifecycle.Lifecycle
    public void a(e eVar) {
        b.a();
        super.a(eVar);
        if (eVar instanceof com.android.settingslib.core.lifecycle.a) {
            this.h.add((com.android.settingslib.core.lifecycle.a) eVar);
        }
    }

    @Override // androidx.lifecycle.g, androidx.lifecycle.Lifecycle
    public void c(e eVar) {
        b.a();
        super.c(eVar);
        if (eVar instanceof com.android.settingslib.core.lifecycle.a) {
            this.h.remove(eVar);
        }
    }

    public void u(Context context) {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.android.settingslib.core.lifecycle.a aVar = this.h.get(i);
            if (aVar instanceof com.android.settingslib.core.lifecycle.b.a) {
                ((com.android.settingslib.core.lifecycle.b.a) aVar).a();
            }
        }
    }

    public void v(Bundle bundle) {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.android.settingslib.core.lifecycle.a aVar = this.h.get(i);
            if (aVar instanceof com.android.settingslib.core.lifecycle.b.b) {
                ((com.android.settingslib.core.lifecycle.b.b) aVar).m(bundle);
            }
        }
    }

    public void w(Menu menu, @Nullable MenuInflater menuInflater) {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.android.settingslib.core.lifecycle.a aVar = this.h.get(i);
            if (aVar instanceof c) {
                ((c) aVar).a(menu, menuInflater);
            }
        }
    }

    public boolean y(MenuItem menuItem) {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.android.settingslib.core.lifecycle.a aVar = this.h.get(i);
            if ((aVar instanceof com.android.settingslib.core.lifecycle.b.e) && ((com.android.settingslib.core.lifecycle.b.e) aVar).a(menuItem)) {
                return true;
            }
        }
        return false;
    }
}
