package b.b.m.e;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import b.b.m.e.g;
import com.byd.mvp.R$dimen;
import com.byd.mvp.R$id;
import com.byd.mvp.R$string;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.model.BydData;
import com.byd.mvp.utils.AppConfig;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: XPager.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class f<P extends g> implements h<P>, View.OnClickListener {
    private static final long CHCEK_INTERVAL = 10;
    private static final String TAG_X = "XPager";
    public static final int WHAT_UPDATE_UI = 33;
    public String TAG = getClass().getSimpleName();
    public volatile boolean isPagerDetach;
    public volatile boolean isViewsLoadCompleted;
    public LayoutInflater lf;
    public CarModel$Brand mBrand;
    public volatile BydLayoutDirection mDirection;
    public c mHandler;
    public volatile int mItemIndex;
    public volatile int mLayoutValue;
    public View mLoadView;
    public volatile int mOrientationValue;
    public volatile View mRootView;
    private P p;
    public View pgb;
    public volatile BydLayoutDirection preDirection;
    public WeakReference<Context> ref;
    public TextView tvLoadStatus;

    /* JADX INFO: compiled from: XPager.java */
    public class a implements View.OnLayoutChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ScrollView f2107a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2108b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2109c;

        public a(ScrollView scrollView, int i, int i2) {
            this.f2107a = scrollView;
            this.f2108b = i;
            this.f2109c = i2;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.f2107a.removeOnLayoutChangeListener(this);
            this.f2107a.scrollTo(this.f2108b, this.f2109c);
        }
    }

    /* JADX INFO: compiled from: XPager.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2111a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f2112b;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            f2112b = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2112b[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[BydLayoutDirection.values().length];
            f2111a = iArr2;
            try {
                iArr2[BydLayoutDirection.LTR_Rudder_L.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2111a[BydLayoutDirection.LTR_Rudder_R.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2111a[BydLayoutDirection.RTL_Rudder_L.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2111a[BydLayoutDirection.RTL_Rudder_R.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: compiled from: XPager.java */
    public static class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<f> f2113a;

        public c(f fVar) {
            this.f2113a = new WeakReference<>(fVar);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            WeakReference<f> weakReference = this.f2113a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            if (33 == message.what) {
                this.f2113a.get().onHandlerUpdateUI(message);
            } else {
                this.f2113a.get().onHandlerOther(message);
            }
        }
    }

    public f(Context context) {
        if (context == null) {
            return;
        }
        initState();
        initPager(context);
    }

    private void bindUI(View view) {
        BydLogHelper.logV(this.TAG + "----bindUI");
    }

    private void initPager(Context context) {
        b.b.m.c.a.a(TAG_X, "initPager", getClass().getName());
        this.ref = new WeakReference<>(context);
        this.lf = (LayoutInflater) context.getSystemService("layout_inflater");
        this.isViewsLoadCompleted = false;
        this.mItemIndex = 0;
        if (initHandler()) {
            this.mHandler = new c(this);
        }
        this.mBrand = AppConfig.a();
        initDataBeforeLoadLayout(context);
        if (getLayoutId() > 0) {
            this.mRootView = this.lf.inflate(getLayoutId(), (ViewGroup) null);
            this.mRootView.setTag(new b.b.m.d.a(this));
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
        for (int i = 0; i < childCount; i++) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.gravity = 1;
                layoutParams.setMarginStart(dimension2);
                layoutParams.setMarginEnd(dimension3);
                if (i == childCount - 1) {
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

    public void changeWidth(LinearLayout linearLayout, int i) {
        if (linearLayout == null || linearLayout.getChildCount() <= 0) {
            return;
        }
        int maxWidthTextOverView = (int) getMaxWidthTextOverView(linearLayout);
        if (maxWidthTextOverView < i) {
            i = maxWidthTextOverView;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams();
        marginLayoutParams.width = i;
        marginLayoutParams.height = -2;
        int childCount = linearLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = linearLayout.getChildAt(i2);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                layoutParams.width = i;
                layoutParams.gravity = 1;
                textView.requestLayout();
            }
        }
        linearLayout.setPadding(0, 0, 0, 0);
        linearLayout.setOrientation(1);
    }

    public void checkItemIndex(int i) {
        BydLogHelper.logV(this.TAG + "----checkItemIndex");
    }

    public Context getContext() {
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public BydData getCurData() {
        BydLogHelper.logV(this.TAG + "----getCurData");
        return null;
    }

    public void getDataAfterActivityRestore(Bundle bundle) {
        b.b.m.c.a.a(TAG_X, "----getDataAfterActivityRestore---" + getClass().getName());
    }

    public b.b.m.f.b getDialogRestorer() {
        return b.b.m.f.a.a();
    }

    public float getMaxWidthTextOverView(LinearLayout linearLayout) {
        if (linearLayout == null || linearLayout.getChildCount() <= 0) {
            return 0.0f;
        }
        float f2 = -1.0f;
        int childCount = linearLayout.getChildCount();
        TextView textView = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt instanceof TextView) {
                TextView textView2 = (TextView) childAt;
                if (f2 < 0.0f) {
                    f2 = textView2.getLayoutParams().width;
                }
                if (isTextOverView(textView2)) {
                    float fMeasureText = textView2.getPaint().measureText(textView2.getText().toString());
                    if (fMeasureText > f2) {
                        f2 = fMeasureText;
                    }
                }
                textView = textView2;
            }
        }
        return f2 + textView.getResources().getDimension(R$dimen.dp_12);
    }

    public String getNotNull(String str) {
        return str != null ? str : "";
    }

    @Override // b.b.m.e.h
    public P getRefP() {
        return this.p;
    }

    public View getRootView() {
        return this.mRootView;
    }

    public boolean hasTextOverView(LinearLayout linearLayout) {
        if (linearLayout != null && linearLayout.getChildCount() > 0) {
            int childCount = linearLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = linearLayout.getChildAt(i);
                if ((childAt instanceof TextView) && isTextOverView((TextView) childAt)) {
                    return true;
                }
            }
        }
        return false;
    }

    public abstract void initData(Context context);

    public void initDataBeforeLoadLayout(Context context) {
        BydLogHelper.logV(this.TAG + "----initDataBeforeLoadLayout");
    }

    public boolean initHandler() {
        return false;
    }

    public void initLoadingView(int i) {
        View viewFindViewById = this.mRootView.findViewById(i);
        this.mLoadView = viewFindViewById;
        this.pgb = viewFindViewById.findViewById(R$id.pgb_status_in_loading);
        TextView textView = (TextView) this.mLoadView.findViewById(R$id.tv_status_in_loading);
        this.tvLoadStatus = textView;
        textView.setOnClickListener(this);
    }

    public void initState() {
        BydLogHelper.logV(this.TAG + "----initState");
    }

    public void initStateCompleted() {
        BydLogHelper.logV(this.TAG + "----initStateCompleted");
    }

    public abstract void initViews(Context context);

    public boolean isTextOverView(TextView textView) {
        return textView != null && textView.getPaint().measureText(textView.getText().toString()) + textView.getResources().getDimension(R$dimen.dp_8) > ((float) textView.getLayoutParams().width);
    }

    public void loadData() {
        this.mItemIndex = b.b.m.e.i.a.d(getClass().getName());
        b.b.m.c.a.a(TAG_X, "loadData", "itemIndex: " + this.mItemIndex + ", " + getClass().getName());
        checkItemIndex(this.mItemIndex);
    }

    public void loadUIStyleOnCarBrand(CarModel$Brand carModel$Brand) {
        b.b.m.c.a.d(TAG_X, "---loadUIStyleOnCarBrand----brand: " + carModel$Brand + ", " + getClass().getName());
        if (carModel$Brand == null) {
            return;
        }
        this.mBrand = carModel$Brand;
        boolean z = this.mOrientationValue == 2;
        int i = b.f2112b[carModel$Brand.ordinal()];
        if (i == 1) {
            loadUIStyleOnDynastyBrand(z);
        } else {
            if (i != 2) {
                return;
            }
            loadUIStyleOnDenzaBrand(z);
        }
    }

    public void loadUIStyleOnDenzaBrand(boolean z) {
        BydLogHelper.logV(this.TAG + "----loadUIStyleOnDenzaBrand");
    }

    public void loadUIStyleOnDynastyBrand(boolean z) {
        BydLogHelper.logV(this.TAG + "----loadUIStyleOnDynastyBrand");
    }

    public void onAttach() {
        b.b.m.c.a.a(TAG_X, "----onAttach---" + getClass().getName());
    }

    public void onBydLayoutDirectionChanged(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        b.b.m.c.a.d(TAG_X, "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z + ", land: " + z2 + ", rightRudder: " + z3);
        int i = b.f2111a[bydLayoutDirection.ordinal()];
        if (i == 1) {
            onLtrRudderL(z2);
        } else if (i == 2) {
            onLtrRudderR(z2);
        } else {
            if (i != 3) {
                return;
            }
            onRtlRudderL(z2);
        }
    }

    public void onDestroy() {
        b.b.m.c.a.a(TAG_X, "----onDestroy---" + getClass().getName());
        c cVar = this.mHandler;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
        }
        this.ref = null;
        this.lf = null;
        this.mRootView = null;
        this.isViewsLoadCompleted = false;
        if (mo7getP() != null) {
            mo7getP().unbindV();
        }
    }

    public void onDetach() {
        b.b.m.c.a.a(TAG_X, "----onDetach---" + getClass().getName());
        this.isPagerDetach = true;
    }

    public void onHandlerOther(Message message) {
        BydLogHelper.logV(this.TAG + "----onHandlerOther");
    }

    public void onHandlerUpdateUI(Message message) {
        BydLogHelper.logV(this.TAG + "----onHandlerUpdateUI");
    }

    public void onHide() {
        b.b.m.c.a.a(TAG_X, "----onHide---" + getClass().getName());
    }

    public void onLayoutDirectionChanged(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        BydLogHelper.logV(this.TAG + "----onLayoutDirectionChanged");
    }

    public void onLoadGetdata() {
        this.mLoadView.setVisibility(8);
    }

    public void onLoadNodata() {
        Context context = this.mRootView.getContext();
        this.mLoadView.setVisibility(0);
        this.pgb.setVisibility(8);
        this.tvLoadStatus.setText(context.getString(R$string.no_data));
    }

    public void onLoading() {
        Context context = this.mRootView.getContext();
        this.mLoadView.setVisibility(0);
        this.pgb.setVisibility(0);
        this.tvLoadStatus.setText(context.getString(R$string.status_in_loading));
    }

    public void onLtrRudderL(boolean z) {
        BydLogHelper.logV(this.TAG + "----onLtrRudderL");
    }

    public void onLtrRudderR(boolean z) {
        BydLogHelper.logV(this.TAG + "----onLtrRudderR");
    }

    public void onRtlRudderL(boolean z) {
        BydLogHelper.logV(this.TAG + "----onRtlRudderL");
    }

    public void postDelayed(Runnable runnable, long j) {
        c cVar = this.mHandler;
        if (cVar != null) {
            cVar.postDelayed(runnable, j);
        }
    }

    public void reloadTextViewWidth(TextView textView, boolean z, int i) {
        if (textView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        int maxWidth = textView.getMaxWidth();
        b.b.m.c.a.a(TAG_X, "----reloadTextViewWidth---tvWidth: " + maxWidth + ", width: " + i);
        if (!z) {
            layoutParams.width = -2;
            textView.setLayoutParams(layoutParams);
        } else if (maxWidth >= i) {
            layoutParams.width = i;
            textView.setLayoutParams(layoutParams);
        }
    }

    public void removeMessage(int i) {
        c cVar = this.mHandler;
        if (cVar != null) {
            cVar.removeMessages(i);
        }
    }

    public void restoreCompleted() {
        BydLogHelper.logV(this.TAG + "----restoreCompleted");
    }

    public void restoreOnViewsLoadCompleted() {
        BydLogHelper.logV(this.TAG + "----restoreOnViewsLoadCompleted");
    }

    public void resume() {
        b.b.m.c.a.a(TAG_X, "----resume---" + getClass().getName());
    }

    public void saveDataBeforeActivityTurning(Bundle bundle) {
        b.b.m.c.a.a(TAG_X, "----saveDataBeforeActivityTurning---" + getClass().getName());
    }

    public void scrollOnLayoutCompleted(ScrollView scrollView, int i, int i2) {
        if (scrollView == null) {
            return;
        }
        if (scrollView.getWidth() <= 0) {
            scrollView.addOnLayoutChangeListener(new a(scrollView, i, i2));
        } else {
            scrollView.scrollTo(i, i2);
        }
    }

    public void sendMessage(int i, int i2, int i3, Object obj) {
        if (this.mHandler == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        messageObtain.arg1 = i2;
        messageObtain.arg2 = i3;
        messageObtain.obj = obj;
        this.mHandler.sendMessage(messageObtain);
    }

    public void sendMessageDelay(int i, int i2, long j, Object obj) {
        if (this.mHandler == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        messageObtain.arg1 = i2;
        messageObtain.obj = obj;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    public void sendUIMessage(int i, Object obj) {
        sendMessage(33, i, obj);
    }

    public void sendUIMessageDelay(int i, long j, Object obj) {
        sendMessageDelay(33, i, j, obj);
    }

    public void setLayoutDirection(Configuration configuration) {
        if (b.b.m.g.g.e()) {
            return;
        }
        int layoutDirection = configuration.getLayoutDirection();
        int i = configuration.orientation;
        if (this.mLayoutValue == layoutDirection && this.mOrientationValue == i) {
            return;
        }
        this.mLayoutValue = layoutDirection;
        this.mOrientationValue = i;
        boolean z = layoutDirection == 1;
        boolean z2 = i == 2;
        boolean zD = AppConfig.d();
        BydLayoutDirection bydLayoutDirection = z ? zD ? BydLayoutDirection.RTL_Rudder_R : BydLayoutDirection.RTL_Rudder_L : zD ? BydLayoutDirection.LTR_Rudder_R : BydLayoutDirection.LTR_Rudder_L;
        b.b.m.c.a.d(TAG_X, "---setLayoutDirection----direction: " + bydLayoutDirection + ", " + getClass().getName());
        this.mDirection = bydLayoutDirection;
        onLayoutDirectionChanged(bydLayoutDirection, z, z2, AppConfig.d());
        if (bydLayoutDirection == this.preDirection) {
            return;
        }
        this.preDirection = bydLayoutDirection;
        onBydLayoutDirectionChanged(bydLayoutDirection, z, z2, AppConfig.d());
    }

    @Override // b.b.m.e.h
    public void setRefP(P p) {
        this.p = p;
    }

    public void setViewGroupRtl(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.setLayoutDirection(z ? 1 : 0);
    }

    public void stop() {
        b.b.m.c.a.a(TAG_X, "----stop---" + getClass().getName());
    }

    public void updateData(BydData bydData) {
        b.b.m.c.a.a(this.TAG, getClass().getName() + ".updateData(), data = " + bydData);
    }

    public void updateUIByNoData() {
        BydLogHelper.logV(this.TAG + "----updateUIByNoData");
    }

    public void sendUIMessage(int i, int i2, Object obj) {
        sendMessage(33, i, i2, obj);
    }

    public void sendMessageDelay(int i, long j) {
        if (this.mHandler == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    public void sendMessage(int i, int i2, Object obj) {
        sendMessage(i, i2, -1, obj);
    }

    public void sendMessage(int i, int i2) {
        sendMessage(i, i2, null);
    }
}
