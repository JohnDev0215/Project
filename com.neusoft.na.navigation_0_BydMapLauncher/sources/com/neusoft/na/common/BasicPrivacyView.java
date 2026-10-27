package com.neusoft.na.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BasicPrivacyView extends FrameLayout {
    private static final String TAG = "BasicPrivacy";
    protected boolean isLoadStatus;
    protected boolean mIsInMultiWindowMode;
    protected IPrivacyViewListener mListener;
    protected IPrivacyViewLoadListener mLoadListener;

    public interface IPrivacyViewListener {
        void onClickAgree();

        void onClickCancel();

        void onClickChange();
    }

    public interface IPrivacyViewLoadListener {
        void onLoadDone();
    }

    public void setPermissionList(List<String> list, boolean z) {
    }

    public BasicPrivacyView(Context context) {
        super(context);
        this.isLoadStatus = true;
    }

    public BasicPrivacyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isLoadStatus = true;
    }

    public static BasicPrivacyView createPrivacy(Context context, String str) {
        Object objNewInstance;
        Log.i(TAG, "createPrivacy");
        try {
            objNewInstance = Class.forName(str).getConstructor(Context.class).newInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (objNewInstance instanceof BasicPrivacyView) {
            return (BasicPrivacyView) objNewInstance;
        }
        Log.e(TAG, "privacy must be extends BasicPrivacy");
        Log.e(TAG, "createPrivacy null");
        return null;
    }

    public void setInMultiWindowMode(boolean z) {
        this.mIsInMultiWindowMode = z;
    }

    public void setClickListener(IPrivacyViewListener iPrivacyViewListener) {
        this.mListener = iPrivacyViewListener;
    }

    public void setLoadListener(IPrivacyViewLoadListener iPrivacyViewLoadListener) {
        this.mLoadListener = iPrivacyViewLoadListener;
    }
}
