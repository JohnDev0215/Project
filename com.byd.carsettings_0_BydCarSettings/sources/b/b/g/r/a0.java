package b.b.g.r;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.byd.carsettings.R;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: ViewPager2Utils.java */
/* JADX INFO: loaded from: classes2.dex */
public class a0 {
    public static void a(ViewPager2 viewPager2) {
        if (viewPager2 == null) {
            return;
        }
        b(viewPager2, (int) viewPager2.getContext().getResources().getDimension(R.dimen.dp_100));
    }

    public static void b(ViewPager2 viewPager2, int i) {
        if (viewPager2 == null) {
            return;
        }
        try {
            Field declaredField = ViewPager2.class.getDeclaredField("mRecyclerView");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(viewPager2);
            if (obj instanceof RecyclerView) {
                Field declaredField2 = RecyclerView.class.getDeclaredField("mTouchSlop");
                declaredField2.setAccessible(true);
                declaredField2.set((RecyclerView) obj, Integer.valueOf(i));
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
    }
}
