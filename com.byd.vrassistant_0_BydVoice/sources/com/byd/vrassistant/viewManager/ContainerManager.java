package com.byd.vrassistant.viewManager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import com.byd.vrassistant.bean.AnimState;
import com.byd.vrassistant.bean.NetShowResult;
import com.byd.vrassistant.bean.ScreenMode;
import com.byd.vrassistant.bean.ViewBlewType;
import com.byd.vrassistant.bean.VoiceBarType;
import com.byd.vrassistant.bean.jsonparse.VrUiState;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.view.GoogleInfoPopView;
import com.byd.vrassistant.view.NetErrorView;
import com.byd.vrassistant.view.PickListView;
import com.byd.vrassistant.view.ViewContainer;
import com.byd.vrassistant.view.VoiceBar;
import com.byd.vrassistant.view.WeatherBar;
import com.byd.vrassistant.viewInf.container.IContainerManager;
import com.byd.vrassistant.viewmodel.AnimViewModel;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.byd.vrassistant.viewmodel.ViewManagerViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ContainerManager implements IContainerManager {
    private static final String TAG = "NEU_ContainerManager";
    AnimViewModel animViewModel;
    CommonViewModel commonViewModel;
    Context context;
    CompositeDisposable disposables;
    private View googleInfo;
    VrUiState lastStartUpParam;
    private WindowManager mWindowManager;
    private View netError;
    private View pickListView;
    ScreenMode screenMode;
    volatile ViewContainer viewContainer;
    ViewManagerViewModel viewManagerViewModel;
    private View weatherBar;

    /* synthetic */ ContainerManager(AnonymousClass1 anonymousClass1) {
        this();
    }

    private ContainerManager() {
        this.lastStartUpParam = VrUiState.emptyInit();
        this.commonViewModel = (CommonViewModel) ViewModelHelper.getAppViewModel(CommonViewModel.class);
        this.viewManagerViewModel = (ViewManagerViewModel) ViewModelHelper.getAppViewModel(ViewManagerViewModel.class);
        this.animViewModel = (AnimViewModel) ViewModelHelper.getAppViewModel(AnimViewModel.class);
        this.disposables = new CompositeDisposable();
    }

    @Override // com.byd.vrassistant.viewInf.common.IController
    public void init(Context context) {
        this.context = context;
        this.mWindowManager = (WindowManager) context.getSystemService(WindowManager.class);
        this.pickListView = new PickListView(context);
        this.weatherBar = new WeatherBar(context);
        VoiceBar voiceBar = new VoiceBar(context);
        this.viewContainer = new ViewContainer(context);
        this.googleInfo = new GoogleInfoPopView(context);
        this.netError = new NetErrorView(context);
        this.viewContainer.addVoiceBar(voiceBar);
        this.commonViewModel.getScreenModeLiveData().observeForever(new Observer() { // from class: com.byd.vrassistant.viewManager.ContainerManager$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m231lambda$init$0$combydvrassistantviewManagerContainerManager((ScreenMode) obj);
            }
        });
        this.viewManagerViewModel.getvrUiState().observeForever(new Observer() { // from class: com.byd.vrassistant.viewManager.ContainerManager$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m232lambda$init$1$combydvrassistantviewManagerContainerManager((VrUiState) obj);
            }
        });
        this.disposables.add(this.viewManagerViewModel.getViewBlewTypeBsj().distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.byd.vrassistant.viewManager.ContainerManager$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.m233lambda$init$2$combydvrassistantviewManagerContainerManager((ViewBlewType) obj);
            }
        }));
        Transformations.distinctUntilChanged(this.viewManagerViewModel.getShowGoogleIngo()).observeForever(new Observer() { // from class: com.byd.vrassistant.viewManager.ContainerManager$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m234lambda$init$3$combydvrassistantviewManagerContainerManager((Boolean) obj);
            }
        });
        this.disposables.add(this.viewManagerViewModel.getShowNetHintBsj().distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.byd.vrassistant.viewManager.ContainerManager$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.m235lambda$init$4$combydvrassistantviewManagerContainerManager((NetShowResult) obj);
            }
        }));
    }

    /* JADX INFO: renamed from: lambda$init$0$com-byd-vrassistant-viewManager-ContainerManager, reason: not valid java name */
    /* synthetic */ void m231lambda$init$0$combydvrassistantviewManagerContainerManager(ScreenMode screenMode) {
        this.screenMode = screenMode;
        updateViewLayout();
    }

    /* JADX INFO: renamed from: lambda$init$1$com-byd-vrassistant-viewManager-ContainerManager, reason: not valid java name */
    /* synthetic */ void m232lambda$init$1$combydvrassistantviewManagerContainerManager(VrUiState vrUiState) {
        Log.i(TAG, "getVoicebarStartupLd= " + vrUiState);
        if (TextUtils.isEmpty(vrUiState.getZone())) {
            return;
        }
        int i = AnonymousClass1.$SwitchMap$com$byd$vrassistant$bean$VoiceBarType[vrUiState.getVoiceBarType().ordinal()];
        if (i != 1) {
            if (i == 2 || i == 3) {
                showOnScreen(vrUiState);
                return;
            }
            return;
        }
        BridgeHelper.getVrUiStatus().onVrDismissStart(vrUiState.getVoiceBarType());
        if (this.viewManagerViewModel.isDismissWithAnim()) {
            dismissOnScreenAnim();
        } else {
            dismissOnScreen();
        }
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.viewManager.ContainerManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$vrassistant$bean$ViewBlewType;
        static final /* synthetic */ int[] $SwitchMap$com$byd$vrassistant$bean$VoiceBarType;

        static {
            int[] iArr = new int[VoiceBarType.values().length];
            $SwitchMap$com$byd$vrassistant$bean$VoiceBarType = iArr;
            try {
                iArr[VoiceBarType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$VoiceBarType[VoiceBarType.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$VoiceBarType[VoiceBarType.MINI.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[ViewBlewType.values().length];
            $SwitchMap$com$byd$vrassistant$bean$ViewBlewType = iArr2;
            try {
                iArr2[ViewBlewType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ViewBlewType[ViewBlewType.UDE_POI.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ViewBlewType[ViewBlewType.ROAD_MODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ViewBlewType[ViewBlewType.UDE_RECENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ViewBlewType[ViewBlewType.DRIVING_MODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ViewBlewType[ViewBlewType.PHONE_CONTACT.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ViewBlewType[ViewBlewType.PHONE_NUMBER.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ViewBlewType[ViewBlewType.VIDEO.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ViewBlewType[ViewBlewType.WEATHER.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ViewBlewType[ViewBlewType.NET_ERROR.ordinal()] = 10;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* JADX INFO: renamed from: lambda$init$2$com-byd-vrassistant-viewManager-ContainerManager, reason: not valid java name */
    /* synthetic */ void m233lambda$init$2$combydvrassistantviewManagerContainerManager(ViewBlewType viewBlewType) throws Throwable {
        switch (AnonymousClass1.$SwitchMap$com$byd$vrassistant$bean$ViewBlewType[viewBlewType.ordinal()]) {
            case 1:
                this.viewContainer.removeExternalView();
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                this.viewContainer.addChildView(this.pickListView);
                break;
            case 9:
                this.viewContainer.addChildView(this.weatherBar);
                break;
            case 10:
                this.viewContainer.addChildView(this.netError);
                break;
        }
    }

    /* JADX INFO: renamed from: lambda$init$3$com-byd-vrassistant-viewManager-ContainerManager, reason: not valid java name */
    /* synthetic */ void m234lambda$init$3$combydvrassistantviewManagerContainerManager(Boolean bool) {
        if (bool.booleanValue() && this.googleInfo.getParent() == null) {
            this.mWindowManager.addView(this.googleInfo, ViewLocationDispatcher.getPopInfo());
        } else {
            if (bool.booleanValue() || this.googleInfo.getParent() == null) {
                return;
            }
            this.mWindowManager.removeView(this.googleInfo);
        }
    }

    /* JADX INFO: renamed from: lambda$init$4$com-byd-vrassistant-viewManager-ContainerManager, reason: not valid java name */
    /* synthetic */ void m235lambda$init$4$combydvrassistantviewManagerContainerManager(NetShowResult netShowResult) throws Throwable {
        RLog.i(TAG, "showNetErr Hint: " + netShowResult);
        this.viewContainer.setNetHintVisibility(netShowResult);
    }

    @Override // com.byd.vrassistant.viewInf.common.IController
    public void unInit() {
        this.viewContainer = null;
        this.context = null;
    }

    private static class InstanceHolder {
        static ContainerManager containerManager = new ContainerManager(null);

        private InstanceHolder() {
        }
    }

    public static ContainerManager get() {
        return InstanceHolder.containerManager;
    }

    public void showOnScreen(VrUiState vrUiState) {
        Log.i(TAG, "showOnScreen: old area=" + this.lastStartUpParam.getZone() + ",newArea=" + vrUiState.getZone() + ",voiceBar is show?=" + (this.viewContainer.getParent() != null));
        if (this.viewContainer.getParent() == null) {
            this.mWindowManager.addView(this.viewContainer, ViewLocationDispatcher.getParams(vrUiState.getZone()));
            this.lastStartUpParam = vrUiState;
            BridgeHelper.getVrUiStatus().onVrShowStart(VoiceBarType.NORMAL);
            this.viewContainer.startShowVoiceBar(new Runnable() { // from class: com.byd.vrassistant.viewManager.ContainerManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    RLog.i(ContainerManager.TAG, "startShowVoiceBar: ");
                }
            });
            RLog.i(TAG, "showVoiceBar now");
            return;
        }
        this.viewContainer.cancelDismissAnim();
        if (!Objects.equals(this.lastStartUpParam, vrUiState)) {
            this.lastStartUpParam = vrUiState;
            updateViewLayout();
        }
        RLog.e(TAG, "showVoiceBar, viewContainer is still showing");
    }

    public void dismissOnScreen() {
        if (this.viewContainer == null || this.viewContainer.getParent() == null) {
            RLog.i(TAG, "dismissOnScreen: container parent is null, ha?");
            return;
        }
        RLog.i(TAG, "closeVoiceBar:");
        this.animViewModel.setAnimState(AnimState.NONE);
        this.mWindowManager.removeView(this.viewContainer);
    }

    public void dismissOnScreenAnim() {
        if (this.viewContainer == null || this.viewContainer.getParent() == null) {
            RLog.i(TAG, "dismissOnScreenAnim: container parent is null, ha?");
        } else {
            RLog.i(TAG, "closeVoiceBar anim:");
            this.viewContainer.startDismissVoiceBar(new Runnable() { // from class: com.byd.vrassistant.viewManager.ContainerManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.dismissOnScreen();
                }
            });
        }
    }

    public void updateViewLayout() {
        if (this.viewContainer.getParent() != null) {
            this.mWindowManager.updateViewLayout(this.viewContainer, ViewLocationDispatcher.getParams(this.lastStartUpParam.getZone()));
        }
    }
}
