package com.android.car.settings.system;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.SystemProperties;
import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public class RegulatoryInfoDisplayActivity extends Activity implements DialogInterface.OnDismissListener {
    private static final String DEFAULT_REGULATORY_INFO_FILEPATH = "/data/misc/elabel/regulatory_info.png";
    private static final String REGULATORY_INFO_FILEPATH_TEMPLATE = "/data/misc/elabel/regulatory_info_%s.png";
    private final String mRegulatoryInfoResource = "regulatory_info";

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onCreate(android.os.Bundle r11) {
        /*
            r10 = this;
            super.onCreate(r11)
            android.content.res.Resources r0 = r10.getResources()
            int r1 = com.android.car.settings.R.bool.config_show_regulatory_info
            boolean r1 = r0.getBoolean(r1)
            if (r1 != 0) goto L12
            r10.finish()
        L12:
            android.app.AlertDialog$Builder r1 = new android.app.AlertDialog$Builder
            r1.<init>(r10)
            int r2 = com.android.car.settings.R.string.regulatory_labels
            android.app.AlertDialog$Builder r1 = r1.setTitle(r2)
            android.app.AlertDialog$Builder r1 = r1.setOnDismissListener(r10)
            r2 = 0
            java.lang.String r3 = getRegulatoryInfoImageFileName()
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeFile(r3)
            if (r4 == 0) goto L2d
            r2 = 1
        L2d:
            r5 = 0
            if (r2 != 0) goto L34
            int r5 = r10.getImageResourceId()
        L34:
            if (r5 == 0) goto L4e
            android.graphics.drawable.Drawable r6 = r10.getDrawable(r5)     // Catch: android.content.res.Resources.NotFoundException -> L4c
            int r7 = r6.getIntrinsicWidth()     // Catch: android.content.res.Resources.NotFoundException -> L4c
            r8 = 2
            if (r7 <= r8) goto L49
            int r7 = r6.getIntrinsicHeight()     // Catch: android.content.res.Resources.NotFoundException -> L4c
            if (r7 <= r8) goto L49
            r7 = 1
            goto L4a
        L49:
            r7 = 0
        L4a:
            r2 = r7
            goto L4e
        L4c:
            r6 = move-exception
            r2 = 0
        L4e:
            int r6 = com.android.car.settings.R.string.regulatory_info_text
            java.lang.CharSequence r6 = r0.getText(r6)
            if (r2 == 0) goto L79
            android.view.LayoutInflater r7 = r10.getLayoutInflater()
            int r8 = com.android.car.settings.R.layout.regulatory_info
            r9 = 0
            android.view.View r7 = r7.inflate(r8, r9)
            int r8 = com.android.car.settings.R.id.regulatory_info
            android.view.View r8 = r7.findViewById(r8)
            android.widget.ImageView r8 = (android.widget.ImageView) r8
            if (r4 == 0) goto L6f
            r8.setImageBitmap(r4)
            goto L72
        L6f:
            r8.setImageResource(r5)
        L72:
            r1.setView(r7)
            r1.show()
            goto L98
        L79:
            int r7 = r6.length()
            if (r7 <= 0) goto L95
            r1.setMessage(r6)
            android.app.AlertDialog r7 = r1.show()
            r8 = 16908299(0x102000b, float:2.387726E-38)
            android.view.View r8 = r7.findViewById(r8)
            android.widget.TextView r8 = (android.widget.TextView) r8
            r9 = 17
            r8.setGravity(r9)
            goto L98
        L95:
            r10.finish()
        L98:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.settings.system.RegulatoryInfoDisplayActivity.onCreate(android.os.Bundle):void");
    }

    private int getImageResourceId() {
        int resId = getResources().getIdentifier("regulatory_info", "drawable", getPackageName());
        String sku = getSku();
        if (!TextUtils.isEmpty(sku)) {
            String regulatory_info_res = "regulatory_info_" + sku.toLowerCase();
            int id = getResources().getIdentifier(regulatory_info_res, "drawable", getPackageName());
            if (id != 0) {
                return id;
            }
            return resId;
        }
        return resId;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        finish();
    }

    static String getSku() {
        return SystemProperties.get("ro.boot.hardware.sku", "");
    }

    static String getRegulatoryInfoImageFileName() {
        String sku = getSku();
        return TextUtils.isEmpty(sku) ? DEFAULT_REGULATORY_INFO_FILEPATH : String.format(Locale.US, REGULATORY_INFO_FILEPATH_TEMPLATE, sku.toLowerCase());
    }
}
