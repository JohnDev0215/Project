package defpackage;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.uistyleconfig.tsimpl.UiStyleConfigManagerImpl;

/* JADX INFO: compiled from: UiStyleConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends AbsBYDAutoDevice {
    public static a a;
    public UiStyleConfigManagerImpl b;

    public a(Context context) {
        super(context.getApplicationContext());
        this.b = UiStyleConfigManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized a a(Context context) {
        if (a == null) {
            a = new a(context.getApplicationContext());
        }
        return a;
    }

    public int b() {
        return this.b.getUiStyle();
    }

    public boolean c() {
        return this.b.isDenza();
    }

    public boolean d() {
        return this.b.isDynasty();
    }

    public boolean e() {
        return this.b.isOcean();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.b.get(iArr, cls);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }
}
