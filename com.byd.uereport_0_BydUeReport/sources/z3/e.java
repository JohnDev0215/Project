package z3;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.byd.mvp.R$dimen;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.model.BydData;
import com.byd.mvp.utils.AppConfig;
import java.lang.ref.WeakReference;
import z3.f;

/* JADX INFO: compiled from: XPager.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class e<P extends z3.f> implements g<P>, View.OnClickListener {
    private static final long CHCEK_INTERVAL = 10;
    public static final int CLICK_TIMES = 10;
    public static final long INTERVAL = 5000;
    private static final String TAG_X = "XPager";
    public static final int WHAT_UPDATE_UI = 33;
    public volatile boolean isPagerDetach;
    public volatile boolean isViewsLoadCompleted;
    public LayoutInflater lf;
    public volatile BydLayoutDirection mDirection;
    public f mHandler;
    public volatile int mItemIndex;
    public volatile int mLayoutValue;
    public volatile int mOrientationValue;
    public volatile View mRootView;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private P f9500p;
    public volatile BydLayoutDirection preDirection;
    public WeakReference<Context> ref;
    public String TAG = getClass().getSimpleName();
    public CarModel$Brand mBrand = null;

    /* JADX INFO: compiled from: XPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            x3.a.a(e.TAG_X, "--initStateCompleted--onClick--" + e.this.getClass().getName() + ", v: " + view);
            if (view instanceof EditText) {
                return;
            }
            m4.e.a((ViewGroup) e.this.mRootView);
        }
    }

    /* JADX INFO: compiled from: XPager.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ EditText f9502d;

        public b(EditText editText) {
            this.f9502d = editText;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean zLostAllFocus = e.this.lostAllFocus();
            x3.a.c(e.TAG_X, "---delayToCloseIme----lostAllFocus: " + zLostAllFocus + ", " + e.this.getClass().getName());
            EditText editText = this.f9502d;
            if (editText == null || !zLostAllFocus) {
                return;
            }
            m4.e.b(editText);
        }
    }

    /* JADX INFO: compiled from: XPager.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ EditText f9504d;

        public c(EditText editText) {
            this.f9504d = editText;
        }

        @Override // java.lang.Runnable
        public void run() {
            EditText editTextF = m4.e.f((ViewGroup) this.f9504d.getParent());
            x3.a.c(e.TAG_X, "---delayToCloseImeOnOtherPage----efFocus: " + editTextF + ", " + e.this.getClass().getName());
            EditText editText = this.f9504d;
            if (editText == null || editTextF != null) {
                return;
            }
            m4.e.b(editText);
        }
    }

    /* JADX INFO: compiled from: XPager.java */
    public class d implements View.OnLayoutChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ScrollView f9506a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f9507b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f9508c;

        public d(ScrollView scrollView, int i8, int i9) {
            this.f9506a = scrollView;
            this.f9507b = i8;
            this.f9508c = i9;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
            this.f9506a.removeOnLayoutChangeListener(this);
            this.f9506a.scrollTo(this.f9507b, this.f9508c);
        }
    }

    /* JADX INFO: renamed from: z3.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: XPager.java */
    public static /* synthetic */ class C0152e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9510a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f9511b;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            f9511b = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9511b[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[BydLayoutDirection.values().length];
            f9510a = iArr2;
            try {
                iArr2[BydLayoutDirection.LTR_Rudder_L.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9510a[BydLayoutDirection.LTR_Rudder_R.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9510a[BydLayoutDirection.RTL_Rudder_L.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9510a[BydLayoutDirection.RTL_Rudder_R.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: compiled from: XPager.java */
    public static class f extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<e> f9512a;

        public f(e eVar) {
            this.f9512a = new WeakReference<>(eVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<e> weakReference = this.f9512a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            if (33 == message.what) {
                this.f9512a.get().onHandlerUpdateUI(message);
            } else {
                this.f9512a.get().onHandlerOther(message);
            }
        }
    }

    public e(Context context) {
        if (context == null) {
            return;
        }
        initState();
        initPager(context);
    }

    private void bindUI(View view) {
    }

    private void initPager(Context context) {
        x3.a.a(TAG_X, "initPager", getClass().getName());
        this.ref = new WeakReference<>(context);
        this.lf = (LayoutInflater) context.getSystemService("layout_inflater");
        this.isViewsLoadCompleted = false;
        this.mItemIndex = 0;
        if (initHandler()) {
            this.mHandler = new f(this);
        }
        this.mBrand = AppConfig.a();
        initDataBeforeLoadLayout(context);
        if (getLayoutId() > 0) {
            this.mRootView = this.lf.inflate(getLayoutId(), (ViewGroup) null);
            this.mRootView.setTag(new y3.a(this));
            initViews(context);
            initData(context);
        }
        initStateCompleted();
        this.isViewsLoadCompleted = true;
        this.isPagerDetach = false;
        this.mLayoutValue = -1;
        this.mOrientationValue = -1;
        setLayoutDirection(context.getResources().getConfiguration());
        loadUIStyleOnCarBrand(AppConfig.a());
        restoreOnViewsLoadCompleted();
        restoreCompleted();
    }

    public void changeLayoutToVertical(LinearLayout linearLayout) {
        if (linearLayout == null || linearLayout.getChildCount() <= 0) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams();
        marginLayoutParams.width = -1;
        marginLayoutParams.height = -2;
        marginLayoutParams.setMarginStart(0);
        int dimension = (int) linearLayout.getResources().getDimension(R$dimen.dp_28);
        int dimension2 = (int) linearLayout.getResources().getDimension(R$dimen.dp_38);
        int dimension3 = (int) linearLayout.getResources().getDimension(R$dimen.dp_48);
        if (linearLayout.getContext().getResources().getConfiguration().orientation != 2) {
            dimension2 = (int) linearLayout.getResources().getDimension(R$dimen.dp_14);
            dimension3 = (int) linearLayout.getResources().getDimension(R$dimen.dp_24);
        }
        int childCount = linearLayout.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = linearLayout.getChildAt(i8);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.gravity = 1;
                layoutParams.setMarginStart(dimension2);
                layoutParams.setMarginEnd(dimension3);
                if (i8 == childCount - 1) {
                    layoutParams.setMargins(0, dimension, 0, dimension);
                } else {
                    layoutParams.setMargins(0, dimension, 0, 0);
                }
                textView.requestLayout();
            }
        }
        linearLayout.setPadding(0, 0, 0, 0);
        linearLayout.setOrientation(1);
    }

    public void changeWidth(LinearLayout linearLayout, int i8) {
        if (linearLayout == null || linearLayout.getChildCount() <= 0) {
            return;
        }
        int maxWidthTextOverView = (int) getMaxWidthTextOverView(linearLayout);
        if (maxWidthTextOverView < i8) {
            i8 = maxWidthTextOverView;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams();
        marginLayoutParams.width = i8;
        marginLayoutParams.height = -2;
        int childCount = linearLayout.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = linearLayout.getChildAt(i9);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                layoutParams.width = i8;
                layoutParams.gravity = 1;
                textView.requestLayout();
            }
        }
        linearLayout.setPadding(0, 0, 0, 0);
        linearLayout.setOrientation(1);
    }

    public void checkItemIndex(int i8) {
    }

    public void delayToCloseIme(EditText editText) {
        postDelayed(new b(editText), 50L);
    }

    public void delayToCloseImeOnOtherPage(EditText editText) {
        postDelayed(new c(editText), 50L);
    }

    public Context getContext() {
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public BydData getCurData() {
        return null;
    }

    public void getDataAfterActivityRestore(Bundle bundle) {
        x3.a.a(TAG_X, "----getDataAfterActivityRestore---" + getClass().getName());
    }

    public b4.b getDialogRestorer() {
        return b4.a.a();
    }

    public DisplayMetrics getFullSrceenDisplayMetrics(Context context) {
        if (context == null) {
            return null;
        }
        Point point = new Point();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        defaultDisplay.getRealSize(point);
        defaultDisplay.getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    public int getFullSrceenHeight(Context context) {
        DisplayMetrics fullSrceenDisplayMetrics = getFullSrceenDisplayMetrics(context);
        if (fullSrceenDisplayMetrics != null) {
            return fullSrceenDisplayMetrics.heightPixels;
        }
        return 0;
    }

    public int getFullSrceenWidth(Context context) {
        DisplayMetrics fullSrceenDisplayMetrics = getFullSrceenDisplayMetrics(context);
        if (fullSrceenDisplayMetrics != null) {
            return fullSrceenDisplayMetrics.widthPixels;
        }
        return 0;
    }

    public float getMaxWidthTextOverView(LinearLayout linearLayout) {
        if (linearLayout == null || linearLayout.getChildCount() <= 0) {
            return 0.0f;
        }
        float f8 = -1.0f;
        int childCount = linearLayout.getChildCount();
        TextView textView = null;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = linearLayout.getChildAt(i8);
            if (childAt instanceof TextView) {
                TextView textView2 = (TextView) childAt;
                if (f8 < 0.0f) {
                    f8 = textView2.getLayoutParams().width;
                }
                if (isTextOverView(textView2)) {
                    float fMeasureText = textView2.getPaint().measureText(textView2.getText().toString());
                    if (fMeasureText > f8) {
                        f8 = fMeasureText;
                    }
                }
                textView = textView2;
            }
        }
        return f8 + textView.getResources().getDimension(R$dimen.dp_12);
    }

    public String getNotNull(String str) {
        return str != null ? str : "";
    }

    @Override // z3.g
    public P getRefP() {
        return this.f9500p;
    }

    public View getRootView() {
        return this.mRootView;
    }

    public boolean hasEditText() {
        return true;
    }

    public boolean hasTextOverView(LinearLayout linearLayout) {
        if (linearLayout != null && linearLayout.getChildCount() > 0) {
            int childCount = linearLayout.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = linearLayout.getChildAt(i8);
                if ((childAt instanceof TextView) && isTextOverView((TextView) childAt)) {
                    return true;
                }
            }
        }
        return false;
    }

    public abstract void initData(Context context);

    public void initDataBeforeLoadLayout(Context context) {
    }

    public boolean initHandler() {
        return true;
    }

    public void initState() {
    }

    public void initStateCompleted() {
        boolean zHasEditText = hasEditText();
        x3.a.a(TAG_X, "--initStateCompleted--hasEditText: " + zHasEditText + ", " + getClass().getName());
        if (zHasEditText) {
            this.mRootView.setOnClickListener(new a());
        }
    }

    public abstract void initViews(Context context);

    public boolean isTextOverView(TextView textView) {
        return textView != null && textView.getPaint().measureText(textView.getText().toString()) + textView.getResources().getDimension(R$dimen.dp_8) > ((float) textView.getLayoutParams().width);
    }

    public void loadData() {
        this.mItemIndex = a4.a.a(getClass().getName());
        x3.a.a(TAG_X, "itemIndex = " + this.mItemIndex + ", " + getClass().getName());
        checkItemIndex(this.mItemIndex);
    }

    public void loadUIStyleOnCarBrand(CarModel$Brand carModel$Brand) {
        x3.a.c(TAG_X, "---loadUIStyleOnCarBrand----brand: " + carModel$Brand + ", " + getClass().getName());
        if (carModel$Brand == null) {
            return;
        }
        this.mBrand = carModel$Brand;
        boolean z7 = this.mOrientationValue == 2;
        int i8 = C0152e.f9511b[carModel$Brand.ordinal()];
        if (i8 == 1) {
            loadUIStyleOnDynastyBrand(z7);
        } else {
            if (i8 != 2) {
                return;
            }
            loadUIStyleOnDenzaBrand(z7);
        }
    }

    public void loadUIStyleOnDenzaBrand(boolean z7) {
    }

    public void loadUIStyleOnDynastyBrand(boolean z7) {
    }

    public boolean lostAllFocus() {
        return true;
    }

    public void onAttach() {
        x3.a.a(TAG_X, "----onAttach---" + getClass().getName());
    }

    public void onBydLayoutDirectionChanged(BydLayoutDirection bydLayoutDirection, boolean z7, boolean z8, boolean z9) {
        x3.a.c(TAG_X, "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z7 + ", land: " + z8 + ", rightRudder: " + z9);
        int i8 = C0152e.f9510a[bydLayoutDirection.ordinal()];
        if (i8 == 1) {
            onLtrRudderL(z8);
            return;
        }
        if (i8 == 2) {
            onLtrRudderR(z8);
        } else if (i8 == 3) {
            onRtlRudderL(z8);
        } else {
            if (i8 != 4) {
                return;
            }
            onRtlRudderR(z8);
        }
    }

    public void onDestroy() {
        x3.a.a(TAG_X, "----onDestroy---" + getClass().getName());
        f fVar = this.mHandler;
        if (fVar != null) {
            fVar.removeCallbacksAndMessages(null);
        }
        this.ref = null;
        this.lf = null;
        this.mRootView = null;
        this.isViewsLoadCompleted = false;
        if (getP() != null) {
            getP().unbindV();
        }
    }

    public void onDetach() {
        x3.a.a(TAG_X, "----onDetach---" + getClass().getName());
        this.isPagerDetach = true;
    }

    public void onHandlerOther(Message message) {
    }

    public void onHandlerUpdateUI(Message message) {
    }

    public void onHide() {
        x3.a.a(TAG_X, "----onHide---" + getClass().getName());
    }

    public void onLayoutDirectionChanged(BydLayoutDirection bydLayoutDirection, boolean z7, boolean z8, boolean z9) {
    }

    public void onLtrRudderL(boolean z7) {
    }

    public void onLtrRudderR(boolean z7) {
    }

    public void onRestoreInstanceState() {
        x3.a.a(TAG_X, "---onRestoreInstanceState----" + getClass().getName());
    }

    public void onRtlRudderL(boolean z7) {
    }

    public void onRtlRudderR(boolean z7) {
        x3.a.b(TAG_X, "---onRtlRudderR---Unsupport this situation, and do onRtlRudderL.");
        onRtlRudderL(z7);
    }

    public void onSaveInstanceState() {
        x3.a.a(TAG_X, "---onSaveInstanceState----" + getClass().getName());
    }

    public void postDelayed(Runnable runnable, long j8) {
        f fVar = this.mHandler;
        if (fVar != null) {
            fVar.postDelayed(runnable, j8);
        }
    }

    public void removeMessage(int i8) {
        f fVar = this.mHandler;
        if (fVar != null) {
            fVar.removeMessages(i8);
        }
    }

    public void removeMessages(int i8) {
        x3.a.c(TAG_X, "---removeMessages----what: " + i8 + ", " + getClass().getName());
        f fVar = this.mHandler;
        if (fVar != null) {
            fVar.removeMessages(i8);
        }
    }

    public void restoreCompleted() {
    }

    public void restoreOnViewsLoadCompleted() {
    }

    public void resume() {
        x3.a.a(TAG_X, "----resume---" + getClass().getName());
    }

    public void saveDataBeforeActivityTurning(Bundle bundle) {
        x3.a.a(TAG_X, "----saveDataBeforeActivityTurning---" + getClass().getName());
    }

    public void scrollOnLayoutCompleted(ScrollView scrollView, int i8, int i9) {
        if (scrollView == null) {
            return;
        }
        if (scrollView.getWidth() <= 0) {
            scrollView.addOnLayoutChangeListener(new d(scrollView, i8, i9));
        } else {
            scrollView.scrollTo(i8, i9);
        }
    }

    public void sendMessage(int i8, int i9, int i10, Object obj) {
        if (this.mHandler == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = i8;
        messageObtain.arg1 = i9;
        messageObtain.arg2 = i10;
        messageObtain.obj = obj;
        x3.a.c(TAG_X, "sendMessage p4", "what: " + i8 + ", " + getClass().getName());
        this.mHandler.sendMessage(messageObtain);
    }

    public void sendMessageDelay(int i8, int i9, long j8, Object obj) {
        if (this.mHandler == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = i8;
        messageObtain.arg1 = i9;
        messageObtain.obj = obj;
        x3.a.c(TAG_X, "sendMessageDelay p4", "what: " + i8 + ", " + getClass().getName());
        this.mHandler.sendMessageDelayed(messageObtain, j8);
    }

    public void sendUIMessage(int i8, Object obj) {
        sendMessage(33, i8, obj);
    }

    public void sendUIMessageDelay(int i8, long j8, Object obj) {
        sendMessageDelay(33, i8, j8, obj);
    }

    public void setLayoutDirection(Configuration configuration) {
        if (m4.e.i()) {
            return;
        }
        int layoutDirection = configuration.getLayoutDirection();
        int i8 = configuration.orientation;
        if (this.mLayoutValue == layoutDirection && this.mOrientationValue == i8) {
            return;
        }
        this.mLayoutValue = layoutDirection;
        this.mOrientationValue = i8;
        boolean z7 = layoutDirection == 1;
        boolean z8 = i8 == 2;
        boolean zC = AppConfig.c();
        BydLayoutDirection bydLayoutDirection = z7 ? zC ? BydLayoutDirection.RTL_Rudder_R : BydLayoutDirection.RTL_Rudder_L : zC ? BydLayoutDirection.LTR_Rudder_R : BydLayoutDirection.LTR_Rudder_L;
        x3.a.c(TAG_X, "---setLayoutDirection----direction: " + bydLayoutDirection + ", " + getClass().getName());
        this.mDirection = bydLayoutDirection;
        onLayoutDirectionChanged(bydLayoutDirection, z7, z8, AppConfig.c());
        if (bydLayoutDirection == this.preDirection) {
            return;
        }
        this.preDirection = bydLayoutDirection;
        onBydLayoutDirectionChanged(bydLayoutDirection, z7, z8, AppConfig.c());
    }

    @Override // z3.g
    public void setRefP(P p7) {
        this.f9500p = p7;
    }

    public void setViewGroupRtl(View view, boolean z7) {
        if (view == null) {
            return;
        }
        view.setLayoutDirection(z7 ? 1 : 0);
    }

    public void stop() {
        x3.a.a(TAG_X, "----stop---" + getClass().getName());
    }

    public void updateData(BydData bydData) {
        x3.a.a(this.TAG, getClass().getName() + ".updateData(), data = " + bydData);
    }

    public void updateUIByNoData() {
    }

    public void sendUIMessage(int i8, int i9, Object obj) {
        sendMessage(33, i8, i9, obj);
    }

    public void postDelayed(Runnable runnable, int i8, long j8) {
        if (this.mHandler != null) {
            m4.a.d(this.mHandler, "postDelayed", new Class[]{Runnable.class, Integer.TYPE, Long.TYPE}, new Object[]{runnable, Integer.valueOf(i8), Long.valueOf(j8)});
        }
    }

    public void sendMessageDelay(int i8, long j8) {
        if (this.mHandler == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = i8;
        x3.a.c(TAG_X, "sendMessageDelay p2", "what: " + i8 + ", " + getClass().getName());
        this.mHandler.sendMessageDelayed(messageObtain, j8);
    }

    public void sendMessage(int i8, int i9, Object obj) {
        sendMessage(i8, i9, -1, obj);
    }

    public void sendMessage(int i8, int i9) {
        sendMessage(i8, i9, null);
    }
}
