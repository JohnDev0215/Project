package d.a.a.f.b.a;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.provider.Settings;
import java.util.Calendar;

/* JADX INFO: compiled from: BydDateTimeHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static int a(Context context, int i2) {
        Calendar calendar = Calendar.getInstance();
        int i3 = calendar.get(1);
        int i4 = calendar.get(2);
        int i5 = calendar.get(5);
        int i6 = calendar.get(11);
        int i7 = calendar.get(12);
        int i8 = calendar.get(13);
        int i9 = calendar.get(7);
        switch (i2) {
            case 0:
                return i3;
            case 1:
                return 1 + i4;
            case 2:
                return i5;
            case 3:
                if (!b(context)) {
                    if (i6 > 12) {
                        i6 -= 12;
                    } else if (i6 == 0) {
                        return 12;
                    }
                }
                return i6;
            case 4:
                return i7;
            case 5:
                return i8;
            case 6:
                return i9 - 1;
            case 7:
                if (!b(context)) {
                    return i6 >= 12 ? 2 : 1;
                }
                break;
        }
        return 0;
    }

    public static boolean b(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24);
        return string != null && string.equals(TimeManagerImpl.TIME_FORMAT_TWENTY_FOUR);
    }
}
