package spi;

import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import java.util.Stack;

/* JADX INFO: loaded from: classes.dex */
public final class og extends AbsBYDAutoGearboxListener {
    public final Stack<y0> a = new Stack<>();

    public final void a(int i) {
        y0 y0VarPeek;
        if (this.a.size() == 0) {
            ct.b("libVehicle peekCallback callbackStack.size = 0", new Object[0]);
            y0VarPeek = null;
        } else {
            y0VarPeek = this.a.peek();
        }
        if (y0VarPeek == null) {
            ct.b("libVehicle callback is null", new Object[0]);
        } else {
            y0VarPeek.a(i == 1, i);
        }
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public final void onGearChanged(int i) {
        super.onGearChanged(i);
        ct.a("libVehicle onGearChanged raw level:%d", Integer.valueOf(i));
        a(i);
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public final void onGearboxAutoModeLevelChanged(int i) {
        super.onGearboxAutoModeLevelChanged(i);
        ct.a("libVehicle onGearboxAutoModeLevelChanged raw level:%d", Integer.valueOf(i));
        a(i);
    }
}
