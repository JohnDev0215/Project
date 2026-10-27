package com.bydux.api.common.views.asm;

import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: loaded from: classes.dex */
public class BydAsmHelper {
    private static BydAsmActivityBase mBydAsmActivity;
    private static BydAsmFragmentBase mBydAsmFragment;
    private static BydAsmTouchBase mBydAsmTouch;

    public static void onActivityCreateEnter(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityCreateEnter(obj);
        }
    }

    public static void onActivityCreateExit(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityCreateExit(obj);
        }
    }

    public static void onActivityDestroyEnter(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityDestroyEnter(obj);
        }
    }

    public static void onActivityDestroyExit(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityDestroyExit(obj);
        }
    }

    public static void onActivityPauseEnter(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityPauseEnter(obj);
        }
    }

    public static void onActivityPauseExit(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityPauseExit(obj);
        }
    }

    public static void onActivityResumeEnter(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityResumeEnter(obj);
        }
    }

    public static void onActivityResumeExit(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityResumeExit(obj);
        }
    }

    public static void onActivityStartEnter(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityStartEnter(obj);
        }
    }

    public static void onActivityStartExit(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityStartExit(obj);
        }
    }

    public static void onActivityStopEnter(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityStopEnter(obj);
        }
    }

    public static void onActivityStopExit(Object obj) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityStopExit(obj);
        }
    }

    public static void onClickEnter(View view) {
        BydAsmTouchBase bydAsmTouchBase = mBydAsmTouch;
        if (bydAsmTouchBase != null) {
            bydAsmTouchBase.onClickEnter(view);
        }
    }

    public static void onClickExit(View view) {
        BydAsmTouchBase bydAsmTouchBase = mBydAsmTouch;
        if (bydAsmTouchBase != null) {
            bydAsmTouchBase.onClickExit(view);
        }
    }

    public static void onFragmentCreateEnter(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentCreateEnter(obj);
        }
    }

    public static void onFragmentCreateExit(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentCreateExit(obj);
        }
    }

    public static void onFragmentDestroyEnter(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentDestroyEnter(obj);
        }
    }

    public static void onFragmentDestroyExit(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentDestroyExit(obj);
        }
    }

    public static void onFragmentPauseEnter(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentPauseEnter(obj);
        }
    }

    public static void onFragmentPauseExit(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentPauseExit(obj);
        }
    }

    public static void onFragmentResumeEnter(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentResumeEnter(obj);
        }
    }

    public static void onFragmentResumeExit(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentResumeExit(obj);
        }
    }

    public static void onFragmentStartEnter(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentStartEnter(obj);
        }
    }

    public static void onFragmentStartExit(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentStartExit(obj);
        }
    }

    public static void onFragmentStopEnter(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentStopEnter(obj);
        }
    }

    public static void onFragmentStopExit(Object obj) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentStopExit(obj);
        }
    }

    public static void onItemClickEnter(AdapterView<?> adapterView, View view, int i3) {
        BydAsmTouchBase bydAsmTouchBase = mBydAsmTouch;
        if (bydAsmTouchBase != null) {
            bydAsmTouchBase.onItemClickEnter(adapterView, view, i3);
        }
    }

    public static void onItemClickExit(AdapterView<?> adapterView, View view, int i3) {
        BydAsmTouchBase bydAsmTouchBase = mBydAsmTouch;
        if (bydAsmTouchBase != null) {
            bydAsmTouchBase.onItemClickExit(adapterView, view, i3);
        }
    }

    public static void setBydAsm(BydAsmTouchBase bydAsmTouchBase) {
        mBydAsmTouch = bydAsmTouchBase;
    }

    public static void setBydAsm(BydAsmActivityBase bydAsmActivityBase) {
        mBydAsmActivity = bydAsmActivityBase;
    }

    public static void setBydAsm(BydAsmFragmentBase bydAsmFragmentBase) {
        mBydAsmFragment = bydAsmFragmentBase;
    }
}
