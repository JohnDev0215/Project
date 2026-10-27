package com.android.car.ui.toolbar;

import androidx.annotation.NonNull;
import com.android.car.ui.toolbar.b;
import java.util.Objects;
import spi.b2;
import spi.e7;
import spi.f2;
import spi.hb;

/* JADX INFO: loaded from: classes2.dex */
public class MenuItemAdapterV1 {
    private final b.d mClientListener;

    @NonNull
    private final b mClientMenuItem;

    @NonNull
    private e7 mPluginMenuItem = new e7(new e7.b());

    @NonNull
    private final ToolbarControllerAdapterV1 mToolbar;

    /* JADX INFO: renamed from: com.android.car.ui.toolbar.MenuItemAdapterV1$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$android$car$ui$toolbar$MenuItem$DisplayBehavior;

        static {
            int[] iArr = new int[b.c.values().length];
            $SwitchMap$com$android$car$ui$toolbar$MenuItem$DisplayBehavior = iArr;
            try {
                iArr[b.c.ALWAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$car$ui$toolbar$MenuItem$DisplayBehavior[b.c.NEVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public MenuItemAdapterV1(@NonNull ToolbarControllerAdapterV1 toolbarControllerAdapterV1, @NonNull b bVar) {
        hb hbVar = new hb(this, 1);
        this.mClientListener = hbVar;
        this.mToolbar = toolbarControllerAdapterV1;
        this.mClientMenuItem = bVar;
        bVar.d(hbVar);
        updateMenuItem();
    }

    private static int convertDisplayBehavior(b.c cVar) {
        int i = AnonymousClass1.$SwitchMap$com$android$car$ui$toolbar$MenuItem$DisplayBehavior[cVar.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 2;
        }
        throw new IllegalArgumentException("Unknown display behavior!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(b bVar) {
        updateMenuItem();
        updateMenuItems();
    }

    private void updateMenuItem() {
        e7 e7Var = this.mPluginMenuItem;
        Objects.requireNonNull(e7Var);
        e7.b bVar = new e7.b();
        bVar.a = this.mClientMenuItem.hashCode();
        bVar.b = b2.b(this.mClientMenuItem.i);
        b bVar2 = this.mClientMenuItem;
        bVar.c = bVar2.j;
        bVar.e = bVar2.n;
        Objects.requireNonNull(bVar2);
        bVar.f = false;
        bVar.h = this.mClientMenuItem.a();
        b bVar3 = this.mClientMenuItem;
        bVar.i = bVar3.d;
        int iConvertDisplayBehavior = convertDisplayBehavior(bVar3.l);
        if (iConvertDisplayBehavior != 0 && iConvertDisplayBehavior != 1 && iConvertDisplayBehavior != 2) {
            throw new IllegalArgumentException("Invalid display behavior!");
        }
        bVar.j = iConvertDisplayBehavior;
        b bVar4 = this.mClientMenuItem;
        boolean z = bVar4.a;
        if (z) {
            bVar.k = true;
            bVar.l = bVar4.o;
        }
        boolean z2 = bVar4.b;
        if (z2) {
            bVar.m = true;
            bVar.n = bVar4.q;
        }
        if (bVar4.k != null || z2 || z || bVar4.a()) {
            b bVar5 = this.mClientMenuItem;
            Objects.requireNonNull(bVar5);
            bVar.o = new f2(bVar5, 5);
        } else {
            bVar.o = null;
        }
        this.mPluginMenuItem = new e7(bVar);
    }

    private void updateMenuItems() {
        this.mToolbar.updateMenuItems();
    }

    @NonNull
    public b getClientMenuItem() {
        return this.mClientMenuItem;
    }

    @NonNull
    public e7 getPluginMenuItem() {
        return this.mPluginMenuItem;
    }

    public boolean isVisible() {
        return this.mClientMenuItem.p;
    }
}
