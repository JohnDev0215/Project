package spi;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: CarControlBar.java */
/* JADX INFO: loaded from: classes.dex */
public interface c1 {
    static int c(int i, int i2) {
        if (i == 0) {
            return i2 / 2;
        }
        if (i == 1) {
            if (i2 < 3) {
                return -1;
            }
            return (i2 / 2) - 1;
        }
        if (i != 2) {
            if (i == 3) {
                return i2 - 1;
            }
            throw new IllegalArgumentException(c.d("Unknown position: ", i));
        }
        if (i2 < 2) {
            return -1;
        }
        return (i2 / 2) + 1;
    }

    void a(@Nullable View view, int i);

    ImageButton b(Drawable drawable);

    int getFocusedViewIndex();

    void setFocusAtViewIndex(int i);

    void setViews(@Nullable View[] viewArr);
}
