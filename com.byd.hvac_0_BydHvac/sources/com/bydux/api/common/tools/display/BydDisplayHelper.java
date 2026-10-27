package com.bydux.api.common.tools.display;

import android.app.Presentation;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: caradapter-bug */
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

    /* JADX INFO: compiled from: caradapter-bug */
    public interface BydDisplayListener {
        void onPresentationCreate();

        void onPresentationDismiss();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class BydDisplayParam {
        public int x = 0;
        public int y = 0;
        public int width = 0;
        public int height = 0;
        public int gravity = 0;

        public BydDisplayParam() {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
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
                attributes.x = BydDisplayHelper.this.mDisplayParam.x;
                attributes.y = BydDisplayHelper.this.mDisplayParam.y;
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

    public void showDisplay(int i, View view) {
        showDisplay(i, 2106, view);
    }

    public void showDisplay(int i, int i2, View view) {
        BydLogHelper.logV(this.TAG, "showDisplay", "displayId=" + i + ", winType=" + i2);
        if (i >= this.mDisplay.length) {
            BydLogHelper.logV(this.TAG, "showDisplay", "display out length=" + this.mDisplay.length);
            return;
        }
        this.mDisplayId = i;
        this.mWinType = i2;
        this.mLayout = view;
        this.mLayoutId = 0;
        if (this.mBydPresentation == null) {
            BydPresentation bydPresentation = new BydPresentation(this.mContext, this.mDisplay[this.mDisplayId]);
            this.mBydPresentation = bydPresentation;
            bydPresentation.getWindow().addFlags(8);
            this.mBydPresentation.show();
        }
    }

    public void showDisplay(int i, int i2) {
        showDisplay(i, 2106, i2);
    }

    public void showDisplay(int i, int i2, int i3) {
        BydLogHelper.logV(this.TAG, "showDisplay", "displayId=" + i + ", winType=" + i2);
        if (i >= this.mDisplay.length) {
            BydLogHelper.logV(this.TAG, "showDisplay", "display out length=" + this.mDisplay.length);
            return;
        }
        this.mDisplayId = i;
        this.mWinType = i2;
        this.mLayoutId = i3;
        this.mLayout = null;
        if (this.mBydPresentation == null) {
            BydPresentation bydPresentation = new BydPresentation(this.mContext, this.mDisplay[this.mDisplayId]);
            this.mBydPresentation = bydPresentation;
            bydPresentation.getWindow().addFlags(8);
            this.mBydPresentation.show();
        }
    }
}
