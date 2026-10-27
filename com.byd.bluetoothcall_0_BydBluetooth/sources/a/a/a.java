package a.a;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.uistyleconfig.tsimpl.UiStyleConfigManagerImpl;

/* JADX INFO: compiled from: UiStyleConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends AbsBYDAutoDevice {

    /* JADX INFO: renamed from: a */
    private static a f0a;

    /* JADX INFO: renamed from: b */
    private UiStyleConfigManagerImpl f1b;

    a(Context context) {
        super(context.getApplicationContext());
        this.f1b = UiStyleConfigManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized a a(Context context) {
        if (f0a == null) {
            f0a = new a(context.getApplicationContext());
        }
        return f0a;
    }

    public int b() {
        return this.f1b.getUiStyle();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.f1b.get(iArr, cls);
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
