package ts.car.service.bt.phone;

import android.content.Context;
import android.text.format.DateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class CallLogItem {
    public String givenName = null;
    public String familyName = null;
    public String displayName = null;
    public int type = 0;
    public String number = null;
    public long time = 0;
    public String photo = null;
    public String photoFormat = null;

    public String getDisplayName() {
        return this.displayName;
    }

    public String getDateString(Context context) {
        return DateFormat.getDateFormat(context).format(new Date(this.time));
    }

    public String getLongDateString(Context context) {
        return DateFormat.getLongDateFormat(context).format(new Date(this.time));
    }

    public String getTimeString(Context context) {
        return DateFormat.getTimeFormat(context).format(new Date(this.time));
    }
}
