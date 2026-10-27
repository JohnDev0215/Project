package z3;

import android.content.Context;
import android.view.ViewGroup;
import com.byd.mvp.ui.dialog.DialogPagerState;
import e4.a;
import z3.f;

/* JADX INFO: compiled from: DialogPager.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a<P extends f> extends e<P> {
    public static final int INT_INVALID = -1;
    private static final String TAG_X = "DialogPager";
    public int height;
    public w3.a mBtnCallback;
    public d mCallback;
    public e4.d mOkListener;
    public k4.d mRadioBtnCallback;
    private volatile DialogPagerState mSate;
    public w3.b mSwitchCallback;
    public volatile String restorerMethod;
    public int width;

    public a(Context context) {
        super(context);
        this.restorerMethod = null;
        this.width = initWidth(context);
        this.height = initHeight(context);
    }

    public void beforeDestroy() {
        x3.a.a(TAG_X, "beforeDestroy", getClass().getName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void finish() {
        a.b.a().c(getClass());
    }

    public String getDialogTag() {
        return TAG_X;
    }

    public int getHeight() {
        return this.height;
    }

    public int getSubviewIndex(ViewGroup viewGroup, int i8) {
        int childCount = viewGroup.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            if (i8 == viewGroup.getChildAt(i9).getId()) {
                return i9;
            }
        }
        return -1;
    }

    public int getWidth() {
        return this.width;
    }

    public DialogPagerState getmSate() {
        return this.mSate;
    }

    public void hide() {
        x3.a.a(TAG_X, "----onHide---" + getClass().getName());
        this.mSate = DialogPagerState.HIDE;
    }

    public int initHeight(Context context) {
        return 0;
    }

    @Override // z3.e
    public void initState() {
        this.mSate = DialogPagerState.INIT;
    }

    @Override // z3.e
    public void initStateCompleted() {
        super.initStateCompleted();
        this.mSate = DialogPagerState.INIT_COMPLETED;
    }

    public int initWidth(Context context) {
        return 0;
    }

    public boolean isPageInvalid() {
        return this.mSate == DialogPagerState.DESTROYED || this.mSate == DialogPagerState.INVALID;
    }

    @Override // z3.e
    public void onDestroy() {
        super.onDestroy();
        this.mSate = DialogPagerState.DESTROYED;
        this.mBtnCallback = null;
        this.mSwitchCallback = null;
        this.mCallback = null;
        this.mRadioBtnCallback = null;
    }

    @Override // z3.e
    public void onHide() {
        x3.a.a(TAG_X, "----onHide---" + getClass().getName());
    }

    public void onRestoreOnOtherClass(String str, String str2) {
        x3.a.a(TAG_X, "---onRestoreOnOtherClass---clazzName: " + str + ", methodName: " + str2);
    }

    public void onResume() {
        x3.a.a(TAG_X, "----onResume---" + getClass().getName());
    }

    @Override // z3.e
    public void restoreCompleted() {
        this.mSate = DialogPagerState.RESTORE_COMPLETED;
    }

    @Override // z3.e
    public void restoreOnViewsLoadCompleted() {
        super.restoreOnViewsLoadCompleted();
    }

    @Override // z3.e
    public void resume() {
        super.resume();
        x3.a.a(TAG_X, "----resume---" + getClass().getName());
        this.mSate = DialogPagerState.RESUME;
        onResume();
    }

    public void setBtnClickedCallback(w3.a aVar) {
        this.mBtnCallback = aVar;
    }

    public void setOnBackPressCallback(d dVar) {
        this.mCallback = dVar;
    }

    public void setOnOKClickedListener(e4.d dVar) {
        this.mOkListener = dVar;
    }

    public void setOnRadioBtnChekedCallback(k4.d dVar) {
        this.mRadioBtnCallback = dVar;
    }

    public void setOnSwitchCheckedCallback(w3.b bVar) {
        this.mSwitchCallback = bVar;
    }

    public void setTitleText(String str) {
    }
}
