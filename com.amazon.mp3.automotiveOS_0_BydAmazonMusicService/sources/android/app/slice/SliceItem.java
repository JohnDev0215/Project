package android.app.slice;

import android.annotation.NonNull;
import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SliceItem implements Parcelable {
    static {
        throw new NoClassDefFoundError();
    }

    public native /* synthetic */ PendingIntent getAction();

    public native /* synthetic */ String getFormat();

    @NonNull
    public native /* synthetic */ List<String> getHints();

    public native /* synthetic */ Icon getIcon();

    public native /* synthetic */ CharSequence getText();
}
