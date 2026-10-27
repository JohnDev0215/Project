package org.chromium.components.permissions;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import defpackage.by1;
import defpackage.c12;
import defpackage.lf;
import defpackage.nw1;
import defpackage.qp;
import defpackage.vu1;
import defpackage.z7;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.omnibox.OmniboxUrlEmphasizer;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothScanningPermissionDialog {
    public final Activity a;
    public final Context b;
    public lf c;
    public final ListView d;
    public final a e;
    public boolean f;
    public final BluetoothScanningPromptAndroidDelegate g;
    public long h;

    public BluetoothScanningPermissionDialog(WindowAndroid windowAndroid, String str, int i, BluetoothScanningPromptAndroidDelegate bluetoothScanningPromptAndroidDelegate, long j) {
        this.a = (Activity) windowAndroid.k().get();
        Context context = windowAndroid.i.get();
        this.b = context;
        this.g = bluetoothScanningPromptAndroidDelegate;
        this.h = j;
        SpannableString spannableString = new SpannableString(str);
        int i2 = 1;
        boolean z = !z7.I(context);
        qp qpVarA = bluetoothScanningPromptAndroidDelegate.a();
        OmniboxUrlEmphasizer.a(spannableString, context, qpVarA, i, z, true);
        qpVarA.a();
        SpannableString spannableString2 = new SpannableString(context.getString(R$string.bluetooth_scanning_prompt_origin, str));
        TextUtils.copySpansFrom(spannableString, 0, spannableString.length(), Object.class, spannableString2, spannableString2.toString().indexOf(str));
        String string = context.getString(R$string.bluetooth_scanning_prompt_no_devices_found_prompt);
        String string2 = context.getString(R$string.bluetooth_scanning_prompt_block_button_text);
        String string3 = context.getString(R$string.bluetooth_scanning_prompt_allow_button_text);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R$layout.bluetooth_scanning_permission_dialog, (ViewGroup) null);
        TextViewWithClickableSpans textViewWithClickableSpans = (TextViewWithClickableSpans) linearLayout.findViewById(R$id.dialog_title);
        textViewWithClickableSpans.setText(spannableString2);
        textViewWithClickableSpans.setMovementMethod(LinkMovementMethod.getInstance());
        TextViewWithClickableSpans textViewWithClickableSpans2 = (TextViewWithClickableSpans) linearLayout.findViewById(R$id.not_found_message);
        textViewWithClickableSpans2.setText(string);
        textViewWithClickableSpans2.setMovementMethod(LinkMovementMethod.getInstance());
        textViewWithClickableSpans2.setVisibility(0);
        ListView listView = (ListView) linearLayout.findViewById(R$id.items);
        this.d = listView;
        a aVar = new a(R$layout.bluetooth_scanning_permission_dialog_row, context, false);
        this.e = aVar;
        aVar.setNotifyOnChange(true);
        listView.setAdapter((ListAdapter) aVar);
        listView.setEmptyView(textViewWithClickableSpans2);
        listView.setDivider(null);
        ((ProgressBar) linearLayout.findViewById(R$id.progress)).setVisibility(8);
        Button button = (Button) linearLayout.findViewById(R$id.block);
        button.setText(string2);
        button.setEnabled(true);
        button.setOnClickListener(new vu1(i2, this));
        Button button2 = (Button) linearLayout.findViewById(R$id.allow);
        button2.setText(string3);
        button2.setEnabled(true);
        button2.setOnClickListener(new by1(26, this));
        this.f = false;
        lf lfVar = new lf(this, context);
        this.c = lfVar;
        lfVar.requestWindowFeature(1);
        this.c.setCanceledOnTouchOutside(true);
        this.c.addContentView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
        this.c.setOnCancelListener(new c12(2, this));
        Window window = this.c.getWindow();
        if (!DeviceFormFactor.a(context)) {
            window.setBackgroundDrawable(new ColorDrawable(-1));
            window.setGravity(48);
            window.setLayout(-1, -2);
        }
        this.c.show();
        linearLayout.addOnLayoutChangeListener(new nw1(this, i2, linearLayout));
    }

    @CalledByNative
    public static BluetoothScanningPermissionDialog create(WindowAndroid windowAndroid, String str, int i, BluetoothScanningPromptAndroidDelegate bluetoothScanningPromptAndroidDelegate, long j) {
        return new BluetoothScanningPermissionDialog(windowAndroid, str, i, bluetoothScanningPromptAndroidDelegate, j);
    }

    @CalledByNative
    public void addOrUpdateDevice(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = this.b.getString(R$string.bluetooth_scanning_device_unknown, str);
        }
        this.e.a(null, str, str2, null);
        this.d.setVisibility(0);
    }

    @CalledByNative
    public final void closeDialog() {
        this.h = 0L;
        this.c.dismiss();
    }
}
