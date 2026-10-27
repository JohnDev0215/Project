package n1;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.whud.BYDAutoWhudDevice;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: compiled from: BydAutoNaviDevice.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends AbsBYDAutoDevice {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static b f4088a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final BYDAutoInstrumentDevice f4089b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final BYDAutoWhudDevice f4090c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList<n1.a> f4091d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AbsBYDAutoInstrumentListener f4092e;

    /* JADX INFO: compiled from: BydAutoNaviDevice.java */
    public class a extends AbsBYDAutoInstrumentListener {
        public a() {
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onINSNavDisplayMode(int i3) {
            super.onINSNavDisplayMode(i3);
            synchronized (b.this.f4091d) {
                for (int i4 = 0; i4 < b.this.f4091d.size(); i4++) {
                    if (b.this.f4091d.get(i4) != null) {
                        ((n1.a) b.this.f4091d.get(i4)).a(i3);
                    }
                }
            }
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onInsUiChanged(int i3) {
            super.onInsUiChanged(i3);
            synchronized (b.this.f4091d) {
                for (int i4 = 0; i4 < b.this.f4091d.size(); i4++) {
                    if (b.this.f4091d.get(i4) != null) {
                        ((n1.a) b.this.f4091d.get(i4)).b(i3);
                    }
                }
            }
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMapFocusChanged(int i3) {
            super.onMapFocusChanged(i3);
            synchronized (b.this.f4091d) {
                for (int i4 = 0; i4 < b.this.f4091d.size(); i4++) {
                    if (b.this.f4091d.get(i4) != null) {
                        ((n1.a) b.this.f4091d.get(i4)).c(i3);
                    }
                }
            }
        }
    }

    public b(Context context) {
        super(context.getApplicationContext());
        this.f4091d = new ArrayList<>();
        this.f4092e = new a();
        Log.e("BydAutoNaviDevice_api_0.2.35", "BydAutoNaviDevice packageName : " + context.getPackageName());
        this.f4090c = BYDAutoWhudDevice.getInstance(context);
        this.f4089b = BYDAutoInstrumentDevice.getInstance(context);
    }

    public static synchronized b c(Context context) {
        if (f4088a == null) {
            f4088a = new b(context);
        }
        return f4088a;
    }

    public int b() {
        return this.f4089b.getInsNaviDisplayMode();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public int getFocusMapType() {
        return this.f4089b.getFocusMapType();
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

    public int sendNaviPresentationState(int i3, int i4) {
        Log.d("BydAutoNaviDevice_api_0.2.35", "sendNaviPresentationState mapType : " + i3 + " , state : " + i4);
        try {
            return this.f4089b.sendNaviPresentationState(i3, i4);
        } catch (Exception unused) {
            return -2147482648;
        }
    }
}
