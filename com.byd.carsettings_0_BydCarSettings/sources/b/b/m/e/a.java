package b.b.m.e;

import android.content.Context;
import android.view.ViewGroup;
import b.b.m.e.g;
import b.b.m.f.d.a;
import b.b.m.f.d.d;
import com.byd.mvp.ui.dialog.DialogPagerState;

/* JADX INFO: compiled from: DialogPager.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a<P extends g> extends f<P> {
    public static final int INT_INVALID = -1;
    private static final String TAG_X = "DialogPager";
    public int height;
    public b.b.m.b.a mBtnCallback;
    public d mCallback;
    public d.a mOkListener;
    public b.b.m.f.f.b.a mRadioBtnCallback;
    private DialogPagerState mSate;
    public b.b.m.b.c mSwitchCallback;
    public volatile String restorerMethod;
    public int width;

    public a(Context context) {
        super(context);
        this.restorerMethod = null;
        this.width = initWidth(context);
        this.height = initHeight(context);
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

    public int getSubviewIndex(ViewGroup viewGroup, int i) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (i == viewGroup.getChildAt(i2).getId()) {
                return i2;
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
        b.b.m.c.a.a(TAG_X, "----onHide---" + getClass().getName());
        this.mSate = DialogPagerState.HIDE;
    }

    public int initHeight(Context context) {
        return 0;
    }

    @Override // b.b.m.e.f
    public void initState() {
        this.mSate = DialogPagerState.INIT;
    }

    @Override // b.b.m.e.f
    public void initStateCompleted() {
        this.mSate = DialogPagerState.INIT_COMPLETED;
    }

    public int initWidth(Context context) {
        return 0;
    }

    @Override // b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        this.mSate = DialogPagerState.DESTROYED;
        this.mBtnCallback = null;
        this.mSwitchCallback = null;
        this.mCallback = null;
        this.mRadioBtnCallback = null;
    }

    @Override // b.b.m.e.f
    public void onHide() {
        b.b.m.c.a.a(TAG_X, "----onHide---" + getClass().getName());
    }

    public void onRestoreOnOtherClass(String str, String str2) {
        b.b.m.c.a.a(TAG_X, "---onRestoreOnOtherClass---clazzName: " + str + ", methodName: " + str2);
    }

    public void onResume() {
        b.b.m.c.a.a(TAG_X, "----onResume---" + getClass().getName());
    }

    @Override // b.b.m.e.f
    public void restoreCompleted() {
        this.mSate = DialogPagerState.RESTORE_COMPLETED;
    }

    @Override // b.b.m.e.f
    public void restoreOnViewsLoadCompleted() {
        super.restoreOnViewsLoadCompleted();
    }

    @Override // b.b.m.e.f
    public void resume() {
        super.resume();
        b.b.m.c.a.a(TAG_X, "----resume---" + getClass().getName());
        this.mSate = DialogPagerState.RESUME;
        onResume();
    }

    public void setBtnClickedCallback(b.b.m.b.a aVar) {
        this.mBtnCallback = aVar;
    }

    public void setOnBackPressCallback(d dVar) {
        this.mCallback = dVar;
    }

    public void setOnOKClickedListener(d.a aVar) {
        this.mOkListener = aVar;
    }

    public void setOnRadioBtnChekedCallback(b.b.m.f.f.b.a aVar) {
        this.mRadioBtnCallback = aVar;
    }

    public void setOnSwitchCheckedCallback(b.b.m.b.c cVar) {
        this.mSwitchCallback = cVar;
    }

    public void setTitleText(String str) {
        b.b.m.c.a.f(TAG_X, "----setTitleText---" + getClass().getName());
    }
}
