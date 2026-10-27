package com.bydux.api.common.views.asm;

import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: loaded from: classes.dex */
public class BydAsmHelper {
    private static BydAsmActivityBase mBydAsmActivity;
    private static BydAsmFragmentBase mBydAsmFragment;
    private static BydAsmTouchBase mBydAsmTouch;

    public static void setBydAsm(BydAsmTouchBase bydAsm) {
        mBydAsmTouch = bydAsm;
    }

    public static void setBydAsm(BydAsmActivityBase bydAsm) {
        mBydAsmActivity = bydAsm;
    }

    public static void setBydAsm(BydAsmFragmentBase bydAsm) {
        mBydAsmFragment = bydAsm;
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

    public static void onItemClickEnter(AdapterView<?> adapterView, View view, int position) {
        BydAsmTouchBase bydAsmTouchBase = mBydAsmTouch;
        if (bydAsmTouchBase != null) {
            bydAsmTouchBase.onItemClickEnter(adapterView, view, position);
        }
    }

    public static void onItemClickExit(AdapterView<?> adapterView, View view, int position) {
        BydAsmTouchBase bydAsmTouchBase = mBydAsmTouch;
        if (bydAsmTouchBase != null) {
            bydAsmTouchBase.onItemClickExit(adapterView, view, position);
        }
    }

    public static void onActivityCreateEnter(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityCreateEnter(object);
        }
    }

    public static void onActivityCreateExit(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityCreateExit(object);
        }
    }

    public static void onActivityDestroyEnter(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityDestroyEnter(object);
        }
    }

    public static void onActivityDestroyExit(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityDestroyExit(object);
        }
    }

    public static void onActivityStartEnter(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityStartEnter(object);
        }
    }

    public static void onActivityStartExit(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityStartExit(object);
        }
    }

    public static void onActivityStopEnter(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityStopEnter(object);
        }
    }

    public static void onActivityStopExit(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityStopExit(object);
        }
    }

    public static void onActivityResumeEnter(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityResumeEnter(object);
        }
    }

    public static void onActivityResumeExit(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityResumeExit(object);
        }
    }

    public static void onActivityPauseEnter(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityPauseEnter(object);
        }
    }

    public static void onActivityPauseExit(Object object) {
        BydAsmActivityBase bydAsmActivityBase = mBydAsmActivity;
        if (bydAsmActivityBase != null) {
            bydAsmActivityBase.onActivityPauseExit(object);
        }
    }

    public static void onFragmentCreateEnter(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentCreateEnter(object);
        }
    }

    public static void onFragmentCreateExit(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentCreateExit(object);
        }
    }

    public static void onFragmentDestroyEnter(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentDestroyEnter(object);
        }
    }

    public static void onFragmentDestroyExit(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentDestroyExit(object);
        }
    }

    public static void onFragmentStartEnter(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentStartEnter(object);
        }
    }

    public static void onFragmentStartExit(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentStartExit(object);
        }
    }

    public static void onFragmentStopEnter(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentStopEnter(object);
        }
    }

    public static void onFragmentStopExit(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentStopExit(object);
        }
    }

    public static void onFragmentResumeEnter(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentResumeEnter(object);
        }
    }

    public static void onFragmentResumeExit(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentResumeExit(object);
        }
    }

    public static void onFragmentPauseEnter(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentPauseEnter(object);
        }
    }

    public static void onFragmentPauseExit(Object object) {
        BydAsmFragmentBase bydAsmFragmentBase = mBydAsmFragment;
        if (bydAsmFragmentBase != null) {
            bydAsmFragmentBase.onFragmentPauseExit(object);
        }
    }
}
