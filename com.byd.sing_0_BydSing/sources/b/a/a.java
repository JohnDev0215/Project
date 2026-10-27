package b.a;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.uistyleconfig.tsimpl.UiStyleConfigManagerImpl;
import com.android.volley.BuildConfig;

/* JADX INFO: compiled from: UiStyleConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends AbsBYDAutoDevice {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f1924a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private UiStyleConfigManagerImpl f1925b;

    a(Context context) {
        super(context.getApplicationContext());
        this.f1925b = UiStyleConfigManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized a a(Context context) {
        if (f1924a == null) {
            f1924a = new a(context.getApplicationContext());
        }
        return f1924a;
    }

    public int b() {
        return this.f1925b.getUiStyle();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.f1925b.get(iArr, cls);
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
        return BuildConfig.FLAVOR;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return BuildConfig.FLAVOR;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }
}
