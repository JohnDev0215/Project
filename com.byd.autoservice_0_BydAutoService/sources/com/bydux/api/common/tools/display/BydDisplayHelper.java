package com.bydux.api.common.tools.display;

import android.app.Presentation;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class BydDisplayHelper {
    private BydPresentation mBydPresentation;
    private Context mContext;
    private Display[] mDisplay;
    private DisplayManager mDisplayManager;
    private final String TAG = getClass().getSimpleName();
    private int mDisplayId = -1;
    private int mWinType = 0;
    private int mLayoutId = 0;
    private View mLayout = null;
    private BydDisplayListener mDisplayListener = null;
    private BydDisplayParam mDisplayParam = null;

    public interface BydDisplayListener {
        void onPresentationCreate();

        void onPresentationDismiss();
    }

    public class BydDisplayParam {

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public int f1872x = 0;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f1873y = 0;
        public int width = 0;
        public int height = 0;
        public int gravity = 0;

        public BydDisplayParam() {
        }
    }

    public final class BydPresentation extends Presentation {
        public BydPresentation(Context context, Display display) {
            super(context, display);
        }

        @Override // android.app.Dialog
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.type = BydDisplayHelper.this.mWinType;
            if (BydDisplayHelper.this.mDisplayParam != null) {
                attributes.x = BydDisplayHelper.this.mDisplayParam.f1872x;
                attributes.y = BydDisplayHelper.this.mDisplayParam.f1873y;
                if (BydDisplayHelper.this.mDisplayParam.width > 0) {
                    attributes.width = BydDisplayHelper.this.mDisplayParam.width;
                }
                if (BydDisplayHelper.this.mDisplayParam.height > 0) {
                    attributes.height = BydDisplayHelper.this.mDisplayParam.height;
                }
                if (BydDisplayHelper.this.mDisplayParam.gravity > 0) {
                    attributes.gravity = BydDisplayHelper.this.mDisplayParam.gravity;
                }
            }
            if (BydDisplayHelper.this.mLayout != null) {
                setContentView(BydDisplayHelper.this.mLayout);
            } else if (BydDisplayHelper.this.mLayoutId > 0) {
                setContentView(BydDisplayHelper.this.mLayoutId);
            }
            if (BydDisplayHelper.this.mDisplayListener != null) {
                BydDisplayHelper.this.mDisplayListener.onPresentationCreate();
            }
        }
    }

    public BydDisplayHelper(Context context) {
        this.mContext = null;
        this.mDisplay = null;
        this.mContext = context;
        DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
        this.mDisplayManager = displayManager;
        this.mDisplay = displayManager.getDisplays(null);
    }

    public void closeDisplay() {
        BydLogHelper.logV(this.TAG, "closeDisplay", null);
        BydPresentation bydPresentation = this.mBydPresentation;
        if (bydPresentation != null && bydPresentation.isShowing()) {
            this.mBydPresentation.dismiss();
            BydDisplayListener bydDisplayListener = this.mDisplayListener;
            if (bydDisplayListener != null) {
                bydDisplayListener.onPresentationDismiss();
            }
        }
        this.mBydPresentation = null;
    }

    public Presentation getPresentation() {
        return this.mBydPresentation;
    }

    public boolean isShowing() {
        return this.mBydPresentation != null;
    }

    public void setBydDisplayListener(BydDisplayListener bydDisplayListener) {
        this.mDisplayListener = bydDisplayListener;
    }

    public void setBydDisplayParam(BydDisplayParam bydDisplayParam) {
        this.mDisplayParam = bydDisplayParam;
    }

    public void showDisplay(int i3, View view) {
        showDisplay(i3, 2106, view);
    }

    public void showDisplay(int i3, int i4, View view) {
        BydLogHelper.logV(this.TAG, "showDisplay", "displayId=" + i3 + ", winType=" + i4);
        Display[] displayArr = this.mDisplay;
        if (i3 >= displayArr.length) {
            BydLogHelper.logV(this.TAG, "showDisplay", "display out length=" + this.mDisplay.length);
            return;
        }
        this.mDisplayId = i3;
        this.mWinType = i4;
        this.mLayout = view;
        this.mLayoutId = 0;
        if (this.mBydPresentation == null) {
            BydPresentation bydPresentation = new BydPresentation(this.mContext, displayArr[i3]);
            this.mBydPresentation = bydPresentation;
            bydPresentation.getWindow().addFlags(8);
            this.mBydPresentation.show();
        }
    }

    public void showDisplay(int i3, int i4) {
        showDisplay(i3, 2106, i4);
    }

    public void showDisplay(int i3, int i4, int i5) {
        BydLogHelper.logV(this.TAG, "showDisplay", "displayId=" + i3 + ", winType=" + i4);
        Display[] displayArr = this.mDisplay;
        if (i3 >= displayArr.length) {
            BydLogHelper.logV(this.TAG, "showDisplay", "display out length=" + this.mDisplay.length);
            return;
        }
        this.mDisplayId = i3;
        this.mWinType = i4;
        this.mLayoutId = i5;
        this.mLayout = null;
        if (this.mBydPresentation == null) {
            BydPresentation bydPresentation = new BydPresentation(this.mContext, displayArr[i3]);
            this.mBydPresentation = bydPresentation;
            bydPresentation.getWindow().addFlags(8);
            this.mBydPresentation.show();
        }
    }
}
