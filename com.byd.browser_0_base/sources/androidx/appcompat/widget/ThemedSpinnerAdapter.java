package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.SpinnerAdapter;

/* JADX INFO: loaded from: classes.dex */
public interface ThemedSpinnerAdapter extends SpinnerAdapter {

    public static final class Helper {
        public Helper(Context context) {
            LayoutInflater.from(context);
        }
    }

    void b();

    Resources.Theme getDropDownViewTheme();
}
