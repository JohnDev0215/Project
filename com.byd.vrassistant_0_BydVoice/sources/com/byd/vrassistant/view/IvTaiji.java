package com.byd.vrassistant.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import com.byd.vrassistant.base.ImageViewLifecycle;
import com.byd.vrassistant.bean.AnimState;
import com.byd.vrassistant.ui.R;

/* JADX INFO: loaded from: classes2.dex */
public class IvTaiji extends ImageViewLifecycle {
    private static final String TAG = "NEU_IvTaiji";

    @Override // com.byd.vrassistant.base.ImageViewLifecycle
    protected void initView() {
    }

    @Override // com.byd.vrassistant.base.ImageViewLifecycle
    protected void initViewModel() {
    }

    public IvTaiji(Context context) {
        super(context);
    }

    public IvTaiji(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public IvTaiji(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAnimState(AnimState animState) {
        if (getParent() == null && animState != AnimState.CYCLE) {
            Log.e(TAG, "setDialogState: parent == null,or state not changed ,so stop start anim");
        }
        switch (AnonymousClass1.$SwitchMap$com$byd$vrassistant$bean$AnimState[animState.ordinal()]) {
            case 1:
                setImageDrawable(null);
                break;
            case 2:
                setImageResource(R.mipmap.taiji);
                break;
            case 3:
                getAnimTaijiNormal().start();
                break;
            case 4:
                getAnimTaijiIdentify().start();
                break;
            case 5:
                getAnimTaijiProcessing().start();
                break;
            case 6:
                getAnimTaijiBroadcast().start();
                break;
        }
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.view.IvTaiji$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$vrassistant$bean$AnimState;

        static {
            int[] iArr = new int[AnimState.values().length];
            $SwitchMap$com$byd$vrassistant$bean$AnimState = iArr;
            try {
                iArr[AnimState.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$AnimState[AnimState.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$AnimState[AnimState.CYCLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$AnimState[AnimState.IDENTIFY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$AnimState[AnimState.PROCESSING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$AnimState[AnimState.BROADCAST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public AnimationDrawable getAnimTaijiNormal() {
        setImageResource(R.drawable.anim_taiji_normal);
        return (AnimationDrawable) getDrawable();
    }

    public AnimationDrawable getAnimTaijiIdentify() {
        setImageResource(R.drawable.anim_taiji_identify);
        return (AnimationDrawable) getDrawable();
    }

    public AnimationDrawable getAnimTaijiBroadcast() {
        setImageResource(R.drawable.anim_taiji_broadcast);
        return (AnimationDrawable) getDrawable();
    }

    public AnimationDrawable getAnimTaijiProcessing() {
        setImageResource(R.drawable.anim_taiji_processing);
        return (AnimationDrawable) getDrawable();
    }
}
