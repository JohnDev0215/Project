package spi;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.DimenRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.android.car.ui.R$attr;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: compiled from: CarUiUtils.java */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public final class b2 {
    public static final SparseArray<String> a = new SparseArray<>();
    public static int[] b;

    private b2() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(@NonNull View view, int[] iArr, int[] iArr2) {
        if (view instanceof n3) {
            ((n3) view).d(iArr, iArr2);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                a(viewGroup.getChildAt(i), iArr, iArr2);
            }
        }
    }

    public static String b(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return charSequence.toString();
    }

    public static <T, U> List<U> c(List<T> list, Function<T, U> function) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(function.apply(it.next()));
        }
        return arrayList;
    }

    @Nullable
    @UiThread
    public static <T extends View> T d(@NonNull View view, @IdRes int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return (T) view.findViewById(i);
        }
        if (i == -1) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        view.getResources().getValue(i, typedValue, true);
        return (T) view.findViewById(typedValue.resourceId);
    }

    @Nullable
    public static Activity e(@Nullable Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static int f(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(0, new int[]{i});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    public static float g(Resources resources, @DimenRes int i) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i, typedValue, true);
        return typedValue.getFloat();
    }

    @Nullable
    public static String h(@NonNull Resources resources, int i) {
        String string = resources.getString(i);
        if (!string.startsWith("ro.")) {
            return m(string);
        }
        SparseArray<String> sparseArray = a;
        synchronized (sparseArray) {
            if (sparseArray.indexOfKey(i) >= 0) {
                return sparseArray.get(i);
            }
            String strM = m(string);
            sparseArray.put(i, strM);
            return strM;
        }
    }

    public static boolean i(Activity activity, int i) {
        TypedArray typedArrayObtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            return typedArrayObtainStyledAttributes.getBoolean(0, false);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static void j(@NonNull View view) {
        if (b != null) {
            return;
        }
        int identifier = view.getResources().getIdentifier("state_ux_restricted", "attr", "android");
        if (identifier == 0) {
            b = new int[]{R$attr.state_ux_restricted};
        } else {
            b = new int[]{R$attr.state_ux_restricted, identifier};
        }
    }

    public static void k(@Nullable View view, boolean z, boolean z2) {
        int[] iArr;
        if (view == null) {
            return;
        }
        j(view);
        if (!z) {
            iArr = z2 ? b : null;
        } else if (z2) {
            int[] iArr2 = b;
            iArr = new int[iArr2.length + 1];
            iArr[0] = 16842910;
            System.arraycopy(iArr2, 0, iArr, 1, iArr2.length);
        } else {
            iArr = new int[]{R.attr.state_enabled};
        }
        a(view, iArr, z ? null : new int[]{R.attr.state_enabled});
    }

    public static void l(@Nullable View view, boolean z) {
        if (view == null) {
            return;
        }
        j(view);
        a(view, z ? b : null, null);
    }

    @Nullable
    @SuppressLint({"PrivateApi"})
    public static String m(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            try {
                try {
                    String str2 = (String) cls.getMethod("get", String.class).invoke(cls, str);
                    if (TextUtils.isEmpty(str2)) {
                        return null;
                    }
                    return str2;
                } catch (Exception e) {
                    Log.w("CarUiUtils", "Failed to invoke SystemProperties.get(): ", e);
                    return null;
                }
            } catch (NoSuchMethodException e2) {
                Log.w("CarUiUtils", "Cannot find SystemProperties.get(): ", e2);
                return null;
            }
        } catch (ClassNotFoundException e3) {
            Log.w("CarUiUtils", "Cannot find android.os.SystemProperties: ", e3);
            return null;
        }
    }

    @NonNull
    @UiThread
    public static <T extends View> T n(@NonNull View view, @IdRes int i) {
        T t = (T) d(view, i);
        if (t != null) {
            return t;
        }
        StringBuilder sbK = c.k("ID ");
        sbK.append(view.getResources().getResourceName(i));
        sbK.append(" does not reference a View inside this View");
        throw new IllegalArgumentException(sbK.toString());
    }
}
