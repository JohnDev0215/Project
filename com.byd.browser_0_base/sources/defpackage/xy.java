package defpackage;

import J.N;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import org.chromium.components.browser_ui.site_settings.R$drawable;
import org.chromium.components.browser_ui.site_settings.R$string;

/* JADX INFO: compiled from: ContentSettingsResources.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"UseSparseArrays"})
public final class xy {

    /* JADX INFO: compiled from: ContentSettingsResources.java */
    public static class a {
        public final int a;
        public final int b;
        public final Integer c;
        public final Integer d;
        public final int e;
        public final int f;

        public a(int i, int i2, Integer num, Integer num2, int i3, int i4) {
            this.a = i;
            this.b = i2;
            this.c = num;
            this.d = num2;
            this.e = i3;
            this.f = i4;
        }
    }

    public static BitmapDrawable a(Resources resources, Drawable drawable) {
        ColorFilter colorFilter = drawable.getColorFilter();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        int width = canvas.getWidth();
        drawable.setBounds(0, 0, width, width);
        drawable.draw(canvas);
        float f = width;
        float f2 = f * 0.08f;
        float f3 = f * 0.15f;
        float f4 = (f2 / 2.0f) * 0.7071f;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        float f5 = 0.5f * f4;
        paint.setStrokeWidth(1.5f * f2);
        float f6 = f - f3;
        canvas.drawLine(f3 + f5, f3 - f5, f6 + f5, f6 - f5, paint);
        paint.setColor(-16777216);
        paint.setXfermode(null);
        paint.setStrokeWidth(f2);
        canvas.drawLine(f3 - f4, f3 + f4, f6 - f4, f6 + f4, paint);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmapCreateBitmap);
        bitmapDrawable.setColorFilter(colorFilter);
        return bitmapDrawable;
    }

    public static int b(int i) {
        if (i == 1) {
            return R$string.website_settings_category_allowed;
        }
        if (i == 2) {
            return R$string.website_settings_category_blocked;
        }
        if (i != 3) {
            return 0;
        }
        return R$string.website_settings_category_ask;
    }

    public static a c(int i, zn2 zn2Var) {
        if (i == 0) {
            if (zn2Var == null) {
                return new a(0, 0, 1, 2, 0, 0);
            }
            kr krVar = (kr) zn2Var;
            return new a(krVar.c() ? R$drawable.gm_database_24 : R$drawable.permission_cookie, krVar.c() ? R$string.site_data_page_title : R$string.cookies_title, 1, 2, krVar.c() ? R$string.website_settings_site_data_page_toggle_sub_label_allow : R$string.website_settings_category_cookie_allowed, krVar.c() ? R$string.website_settings_site_data_page_toggle_sub_label_block : 0);
        }
        if (i == 15) {
            return new a(R$drawable.permission_protected_media, R$string.protected_content, 3, 2, 0, 0);
        }
        if (i == 25) {
            if (zn2Var == null) {
                return new a(0, 0, 1, 2, 0, 0);
            }
            kr krVar2 = (kr) zn2Var;
            return new a(R$drawable.web_asset, krVar2.c() ? R$string.site_settings_page_intrusive_ads_label : R$string.ads_permission_title, 1, 2, krVar2.c() ? R$string.site_settings_page_intrusive_allowed_sub_label : 0, krVar2.c() ? R$string.site_settings_page_intrusive_blocked_sub_label : R$string.website_settings_category_ads_blocked);
        }
        if (i == 30) {
            return new a(R$drawable.ic_volume_up_grey600_24dp, R$string.sound_permission_title, 1, 2, R$string.website_settings_category_sound_allowed, R$string.website_settings_category_sound_blocked);
        }
        if (i == 32) {
            int i2 = R$string.motion_sensors_permission_title;
            int i3 = R$string.website_settings_category_motion_sensors_allowed;
            int i4 = R$string.website_settings_category_motion_sensors_blocked;
            try {
                if (a8.Q() && N.MVi$blz$("GenericSensorExtraClasses")) {
                    i2 = R$string.sensors_permission_title;
                    i3 = R$string.website_settings_category_sensors_allowed;
                    i4 = R$string.website_settings_category_sensors_blocked;
                }
            } catch (IllegalArgumentException unused) {
            }
            return new a(R$drawable.settings_sensors, i2, 1, 2, i3, i4);
        }
        if (i == 35) {
            return new a(R$drawable.gm_filled_usb_24, R$string.website_settings_usb, 3, 2, R$string.website_settings_category_usb_ask, R$string.website_settings_category_usb_blocked);
        }
        if (i == 38) {
            return new a(R$drawable.gm_filled_devices_24, R$string.website_settings_idle_detection, 3, 2, R$string.website_settings_category_idle_detection_ask, R$string.website_settings_category_idle_detection_blocked);
        }
        if (i == 43) {
            return new a(R$drawable.gm_filled_bluetooth_searching_24, R$string.website_settings_bluetooth_scanning, 3, 2, R$string.website_settings_category_bluetooth_scanning_ask, 0);
        }
        if (i == 85) {
            int i5 = R$drawable.ic_account_attention;
            int i6 = R$string.anti_abuse_permission_title;
            int i7 = R$string.anti_abuse_description;
            return new a(i5, i6, 1, 2, i7, i7);
        }
        if (i == 2) {
            return new a(R$drawable.permission_javascript, R$string.javascript_permission_title, 1, 2, R$string.website_settings_category_javascript_allowed, 0);
        }
        if (i == 3) {
            return new a(R$drawable.permission_popups, R$string.popup_permission_title, 1, 2, 0, R$string.website_settings_category_popups_redirects_blocked);
        }
        if (i == 4) {
            return new a(R$drawable.gm_filled_location_on_24, R$string.website_settings_device_location, 3, 2, R$string.website_settings_category_location_ask, 0);
        }
        if (i == 5) {
            return new a(R$drawable.gm_filled_notifications_24, R$string.push_notifications_permission_title, 3, 2, R$string.website_settings_category_notifications_ask, 0);
        }
        if (i == 8) {
            return new a(R$drawable.gm_filled_mic_24, R$string.website_settings_use_mic, 3, 2, R$string.website_settings_category_mic_ask, 0);
        }
        if (i == 9) {
            return new a(R$drawable.gm_filled_videocam_24, R$string.website_settings_use_camera, 3, 2, R$string.website_settings_category_camera_ask, 0);
        }
        if (i == 12) {
            return new a(R$drawable.infobar_downloading, R$string.automatic_downloads_permission_title, 3, 2, R$string.website_settings_category_ask, 0);
        }
        if (i == 13) {
            return new a(R$drawable.gm_filled_piano_24, R$string.midi_sysex_permission_title, null, null, 0, 0);
        }
        if (i == 55) {
            return new a(R$drawable.gm_filled_cardboard_24, R$string.vr_permission_title, 3, 2, R$string.website_settings_category_vr_ask, R$string.website_settings_category_vr_blocked);
        }
        if (i == 56) {
            return new a(R$drawable.gm_filled_cardboard_24, R$string.ar_permission_title, 3, 2, R$string.website_settings_category_ar_ask, R$string.website_settings_category_ar_blocked);
        }
        switch (i) {
            case 19:
                return new a(R$drawable.gm_filled_usb_24, 0, 3, 2, 0, 0);
            case 20:
                return new a(R$drawable.settings_bluetooth, R$string.website_settings_bluetooth, 3, 2, R$string.website_settings_category_bluetooth_ask, R$string.website_settings_category_bluetooth_blocked);
            case 21:
                return new a(R$drawable.permission_background_sync, R$string.background_sync_permission_title, 1, 2, R$string.website_settings_category_allowed_recommended, 0);
            default:
                switch (i) {
                    case 50:
                        return new a(R$drawable.gm_filled_nfc_24, R$string.nfc_permission_title, 3, 2, R$string.website_settings_category_nfc_ask, R$string.website_settings_category_nfc_blocked);
                    case 51:
                        return new a(R$drawable.settings_bluetooth, 0, 3, 2, 0, 0);
                    case 52:
                        return new a(R$drawable.gm_filled_content_paste_24, R$string.clipboard_permission_title, 3, 2, R$string.website_settings_category_clipboard_ask, R$string.website_settings_category_clipboard_blocked);
                    default:
                        switch (i) {
                            case 72:
                                return new a(R$drawable.ic_brightness_medium_24dp, R$string.auto_dark_web_content_title, 1, 2, R$string.website_settings_category_auto_dark_allowed, R$string.website_settings_category_auto_dark_blocked);
                            case 73:
                                return new a(R$drawable.ic_desktop_windows, R$string.desktop_site_title, 1, 2, R$string.website_settings_category_desktop_site_allowed, R$string.website_settings_category_desktop_site_blocked);
                            case 74:
                                return new a(R$drawable.ic_account_circle_24dp, R$string.website_settings_federated_identity, 1, 2, R$string.website_settings_category_federated_identity_allowed, R$string.website_settings_category_federated_identity_blocked);
                            default:
                                return null;
                        }
                }
        }
    }

    public static int d(int i, Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 1) {
            return i == 73 ? R$string.website_settings_desktop_site_allow : R$string.website_settings_permissions_allow;
        }
        if (iIntValue != 2) {
            return 0;
        }
        return i == 73 ? R$string.website_settings_desktop_site_block : R$string.website_settings_permissions_block;
    }
}
