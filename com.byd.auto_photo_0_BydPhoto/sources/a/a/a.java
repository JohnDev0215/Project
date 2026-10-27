package a.a;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.uistyleconfig.tsimpl.UiStyleConfigManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class a extends AbsBYDAutoDevice {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f165a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private UiStyleConfigManagerImpl f166b;

    a(Context context) {
        super(context.getApplicationContext());
        this.f166b = UiStyleConfigManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized a a(Context context) {
        if (f165a == null) {
            f165a = new a(context.getApplicationContext());
        }
        return f165a;
    }

    public int b() {
        return this.f166b.getUiStyle();
    }

    public boolean c() {
        return this.f166b.isDenza();
    }

    public boolean d() {
        return this.f166b.isDynasty();
    }

    public boolean e() {
        return this.f166b.isOcean();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.f166b.get(iArr, cls);
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
