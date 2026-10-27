package android.app.slice;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Slice implements Parcelable {

    public /* synthetic */ class Builder {
        static {
            throw new NoClassDefFoundError();
        }

        public /* synthetic */ Builder(@NonNull Builder builder) {
        }

        public /* synthetic */ Builder(@NonNull Uri uri, SliceSpec sliceSpec) {
        }

        public native /* synthetic */ Builder addAction(@NonNull PendingIntent pendingIntent, @NonNull Slice slice, @Nullable String str);

        public native /* synthetic */ Builder addHints(List<String> list);

        public native /* synthetic */ Builder addIcon(Icon icon, @Nullable String str, List<String> list);

        public native /* synthetic */ Builder addText(CharSequence charSequence, @Nullable String str, List<String> list);

        public native /* synthetic */ Slice build();
    }

    static {
        throw new NoClassDefFoundError();
    }

    public native /* synthetic */ List<SliceItem> getItems();

    @Nullable
    public native /* synthetic */ SliceSpec getSpec();
}
