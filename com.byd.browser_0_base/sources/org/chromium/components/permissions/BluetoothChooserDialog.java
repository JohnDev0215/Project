package org.chromium.components.permissions;

import J.N;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import defpackage.bz;
import defpackage.el3;
import defpackage.iq2;
import defpackage.qp;
import defpackage.re3;
import defpackage.tb1;
import defpackage.un1;
import defpackage.xz;
import defpackage.z7;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.omnibox.OmniboxUrlEmphasizer;
import org.chromium.components.permissions.b;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothChooserDialog implements b.c, PermissionCallback {
    public final WindowAndroid e;
    public final Activity f;
    public final Context g;
    public b h;
    public final String i;
    public final int j;
    public final BluetoothChooserAndroidDelegate k;
    public final el3 l;
    public final String m;
    public final Drawable[] n;
    public long o;
    public boolean p;
    public final BluetoothAdapter q;
    public final SpannableString r;
    public boolean s;
    public final a t = new a();

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if ("android.location.MODE_CHANGED".equals(intent.getAction())) {
                BluetoothChooserDialog bluetoothChooserDialog = BluetoothChooserDialog.this;
                if (bluetoothChooserDialog.b()) {
                    b bVar = bluetoothChooserDialog.h;
                    bVar.k.clear();
                    bVar.b(1);
                    if (bluetoothChooserDialog.s) {
                        bluetoothChooserDialog.notifyAdapterTurnedOff();
                    } else {
                        N.MsAog9yr(bluetoothChooserDialog.o);
                    }
                }
            }
        }
    }

    public BluetoothChooserDialog(WindowAndroid windowAndroid, String str, int i, BluetoothChooserAndroidDelegate bluetoothChooserAndroidDelegate, long j) {
        this.e = windowAndroid;
        this.f = (Activity) windowAndroid.k().get();
        Context context = windowAndroid.i.get();
        this.g = context;
        this.i = str;
        this.j = i;
        this.k = bluetoothChooserAndroidDelegate;
        this.o = j;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.q = defaultAdapter;
        this.l = e(R$drawable.ic_bluetooth_connected);
        this.m = context.getString(R$string.bluetooth_device_connected);
        this.n = new Drawable[]{e(R$drawable.ic_signal_cellular_0_bar), e(R$drawable.ic_signal_cellular_1_bar), e(R$drawable.ic_signal_cellular_2_bar), e(R$drawable.ic_signal_cellular_3_bar), e(R$drawable.ic_signal_cellular_4_bar)};
        if (defaultAdapter == null) {
            Log.i("cr_Bluetooth", "BluetoothChooserDialog: Default Bluetooth adapter not found.");
        }
        this.r = iq2.a(context.getString(R$string.bluetooth_adapter_off_help), new iq2.a(c(2), "<link>", "</link>"));
    }

    @CalledByNative
    public static BluetoothChooserDialog create(WindowAndroid windowAndroid, String str, int i, BluetoothChooserAndroidDelegate bluetoothChooserAndroidDelegate, long j) {
        if (!((PermissionUtil.needsNearbyDevicesPermissionForBluetooth(windowAndroid) || PermissionUtil.needsLocationPermissionForBluetooth(windowAndroid)) ? false : true) && !PermissionUtil.canRequestSystemPermissionsForBluetooth(windowAndroid)) {
            return null;
        }
        BluetoothChooserDialog bluetoothChooserDialog = new BluetoothChooserDialog(windowAndroid, str, i, bluetoothChooserAndroidDelegate, j);
        String str2 = bluetoothChooserDialog.i;
        SpannableString spannableString = new SpannableString(str2);
        Context context = bluetoothChooserDialog.g;
        boolean z = !z7.I(context);
        qp qpVarA = bluetoothChooserDialog.k.a();
        OmniboxUrlEmphasizer.a(spannableString, bluetoothChooserDialog.g, qpVarA, bluetoothChooserDialog.j, z, true);
        qpVarA.a();
        SpannableString spannableString2 = new SpannableString(context.getString(R$string.bluetooth_dialog_title, str2));
        TextUtils.copySpansFrom(spannableString, 0, spannableString.length(), Object.class, spannableString2, spannableString2.toString().indexOf(str2));
        String string = context.getString(R$string.bluetooth_not_found);
        SpannableString spannableStringA = iq2.a(context.getString(R$string.bluetooth_searching), new iq2.a(bluetoothChooserDialog.c(0), "<link>", "</link>"));
        String string2 = context.getString(R$string.bluetooth_confirm_button);
        SpannableString spannableStringA2 = iq2.a(context.getString(R$string.bluetooth_not_seeing_it_idle), new iq2.a(bluetoothChooserDialog.c(0), "<link1>", "</link1>"), new iq2.a(bluetoothChooserDialog.c(6), "<link2>", "</link2>"));
        b.C0140b c0140b = new b.C0140b(spannableString2, spannableStringA, string, spannableStringA, spannableStringA2, spannableStringA2, string2);
        Activity activity = bluetoothChooserDialog.f;
        bluetoothChooserDialog.h = new b(context, activity.getWindow(), bluetoothChooserDialog, c0140b);
        xz.g(activity, bluetoothChooserDialog.t, new IntentFilter("android.location.MODE_CHANGED"));
        bluetoothChooserDialog.p = true;
        return bluetoothChooserDialog;
    }

    @Override // org.chromium.components.permissions.b.c
    public final void a(String str) {
        if (str.isEmpty()) {
            d(1, "");
        } else {
            d(2, str);
        }
    }

    @CalledByNative
    public void addOrUpdateDevice(String str, String str2, boolean z, int i) {
        Drawable drawableNewDrawable;
        String quantityString;
        if (z) {
            drawableNewDrawable = this.l.getConstantState().newDrawable();
            quantityString = this.m;
        } else if (i != -1) {
            drawableNewDrawable = this.n[i].getConstantState().newDrawable();
            quantityString = this.g.getResources().getQuantityString(R$plurals.signal_strength_level_n_bars, i, Integer.valueOf(i));
        } else {
            drawableNewDrawable = null;
            quantityString = null;
        }
        b bVar = this.h;
        bVar.f.setVisibility(8);
        bVar.k.a(drawableNewDrawable, str, str2, quantityString);
        bVar.b(2);
    }

    public final boolean b() {
        SpannableString spannableStringA;
        WindowAndroid windowAndroid = this.e;
        boolean z = (PermissionUtil.needsNearbyDevicesPermissionForBluetooth(windowAndroid) || PermissionUtil.needsLocationPermissionForBluetooth(windowAndroid)) ? false : true;
        boolean zNeedsLocationServicesForBluetooth = PermissionUtil.needsLocationServicesForBluetooth();
        if (!z && !PermissionUtil.canRequestSystemPermissionsForBluetooth(windowAndroid)) {
            d(0, "");
            return false;
        }
        iq2.a aVar = new iq2.a(c(3), "<permission_link>", "</permission_link>");
        iq2.a aVar2 = new iq2.a(c(4), "<services_link>", "</services_link>");
        Context context = this.g;
        if (!z) {
            spannableStringA = zNeedsLocationServicesForBluetooth ? iq2.a(context.getString(R$string.bluetooth_need_location_permission_and_services_on), aVar, aVar2) : PermissionUtil.needsNearbyDevicesPermissionForBluetooth(windowAndroid) ? iq2.a(context.getString(R$string.bluetooth_need_nearby_devices_permission), aVar) : iq2.a(context.getString(R$string.bluetooth_need_location_permission), aVar);
        } else {
            if (!zNeedsLocationServicesForBluetooth) {
                return true;
            }
            spannableStringA = iq2.a(context.getString(R$string.bluetooth_need_location_services_on), aVar2);
        }
        this.h.a(spannableStringA, iq2.a(context.getString(R$string.bluetooth_need_location_permission_help), new iq2.a(c(5), "<link>", "</link>")));
        return false;
    }

    public final un1 c(int i) {
        return new un1(this.g, new tb1(i, 1, this));
    }

    @CalledByNative
    public void closeDialog() {
        this.o = 0L;
        this.h.c.dismiss();
    }

    public final void d(int i, String str) {
        if (this.p) {
            this.f.unregisterReceiver(this.t);
            this.p = false;
        }
        long j = this.o;
        if (j != 0) {
            N.Mv9CbWE_(j, i, str);
        }
    }

    public final el3 e(int i) {
        Context context = this.g;
        el3 el3VarA = re3.a(context.getResources(), i, context.getTheme());
        el3VarA.setTintList(bz.b(context, R$color.item_chooser_row_icon_color));
        return el3VarA;
    }

    @CalledByNative
    public void notifyAdapterTurnedOff() {
        this.s = true;
        if (b()) {
            this.h.a(iq2.a(this.g.getString(R$string.bluetooth_adapter_off), new iq2.a(c(1), "<link>", "</link>")), this.r);
        }
    }

    @CalledByNative
    public final void notifyAdapterTurnedOn() {
        this.s = false;
        b bVar = this.h;
        bVar.k.clear();
        bVar.b(1);
    }

    @CalledByNative
    public void notifyDiscoveryState(int i) {
        if (i == 0) {
            b();
        } else {
            if (i != 2) {
                return;
            }
            b bVar = this.h;
            bVar.f.setVisibility(8);
            bVar.b(3);
        }
    }

    @Override // org.chromium.ui.permissions.PermissionCallback
    public final void s(int[] iArr, String[] strArr) {
        if (this.o != 0 && b()) {
            b bVar = this.h;
            bVar.k.clear();
            bVar.b(1);
            if (this.s) {
                notifyAdapterTurnedOff();
            } else {
                N.MsAog9yr(this.o);
            }
        }
    }
}
