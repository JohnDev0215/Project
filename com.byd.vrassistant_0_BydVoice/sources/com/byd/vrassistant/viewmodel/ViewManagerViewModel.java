package com.byd.vrassistant.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import cerence.ark.assistant.controller.domain.hmi.param.HmiFontColorParam;
import cerence.ark.assistant.controller.enumeration.CmdResultTypeEnum;
import cerence.ark.assistant.controller.enumeration.DomainEnum;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.bean.NetShowResult;
import com.byd.vrassistant.bean.ViewBlewType;
import com.byd.vrassistant.bean.VoiceBarType;
import com.byd.vrassistant.bean.jsonparse.VrUiState;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.livedata.SafeMutableLiveData;
import com.byd.vrassistant.utils.NetStatusCallback;
import com.byd.vrassistant.utils.NetworkMonitor;
import com.byd.vrassistant.utils.SystemAbility;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.Objects;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public class ViewManagerViewModel extends ViewModel {
    private static final String TAG = "NEU_ViewManagerViewModel";
    boolean dismissWithAnim;
    ViewBlewType lastShowViewType;
    ViewBlewType lastViewBlewType;
    private final PublishSubject<Object> sessionEndPsj;
    private final BehaviorSubject<NetShowResult> showNetHintBsj;
    SystemAbility systemAbility;
    private final BehaviorSubject<ViewBlewType> viewBlewTypeBsj = BehaviorSubject.create();
    private final SafeMutableLiveData<VrUiState> vrUiState = new SafeMutableLiveData<>();
    private final SafeMutableLiveData<Boolean> showGoogleIngo = new SafeMutableLiveData<>();
    private final BehaviorSubject<Boolean> inConversationBsj = BehaviorSubject.createDefault(false);
    private final PublishSubject<HmiFontColorParam> handleResultPsj = PublishSubject.create();

    public ViewManagerViewModel() {
        BehaviorSubject<NetShowResult> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(new NetShowResult(false, false));
        this.showNetHintBsj = behaviorSubjectCreateDefault;
        this.sessionEndPsj = PublishSubject.create();
        this.systemAbility = new SystemAbility();
        NetworkMonitor networkMonitor = new NetworkMonitor(ContextHolder.getApplicationContext());
        final BehaviorSubject behaviorSubjectCreateDefault2 = BehaviorSubject.createDefault(Boolean.valueOf(networkMonitor.isNetworkAvailable()));
        Objects.requireNonNull(behaviorSubjectCreateDefault2);
        networkMonitor.startNetworkCallback(new NetStatusCallback() { // from class: com.byd.vrassistant.viewmodel.ViewManagerViewModel$$ExternalSyntheticLambda0
            @Override // com.byd.vrassistant.utils.NetStatusCallback
            public final void onNetStatusChanged(boolean z) {
                behaviorSubjectCreateDefault2.onNext(Boolean.valueOf(z));
            }
        });
        final SystemAbility systemAbility = this.systemAbility;
        Objects.requireNonNull(systemAbility);
        Observable.fromCallable(new Callable() { // from class: com.byd.vrassistant.viewmodel.ViewManagerViewModel$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Boolean.valueOf(systemAbility.supportNet());
            }
        }).subscribeOn(Schedulers.io()).switchMap(new Function() { // from class: com.byd.vrassistant.viewmodel.ViewManagerViewModel$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.m237lambda$new$0$combydvrassistantviewmodelViewManagerViewModel((Boolean) obj);
            }
        }).switchMap(new Function() { // from class: com.byd.vrassistant.viewmodel.ViewManagerViewModel$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.m239lambda$new$6$combydvrassistantviewmodelViewManagerViewModel(behaviorSubjectCreateDefault2, (Boolean) obj);
            }
        }).subscribe(behaviorSubjectCreateDefault);
    }

    /* JADX INFO: renamed from: lambda$new$0$com-byd-vrassistant-viewmodel-ViewManagerViewModel, reason: not valid java name */
    /* synthetic */ ObservableSource m237lambda$new$0$combydvrassistantviewmodelViewManagerViewModel(Boolean bool) throws Throwable {
        return bool.booleanValue() ? this.inConversationBsj : Observable.just(false);
    }

    /* JADX INFO: renamed from: lambda$new$6$com-byd-vrassistant-viewmodel-ViewManagerViewModel, reason: not valid java name */
    /* synthetic */ ObservableSource m239lambda$new$6$combydvrassistantviewmodelViewManagerViewModel(BehaviorSubject behaviorSubject, Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            return this.handleResultPsj.filter(new Predicate() { // from class: com.byd.vrassistant.viewmodel.ViewManagerViewModel$$ExternalSyntheticLambda4
                @Override // io.reactivex.rxjava3.functions.Predicate
                public final boolean test(Object obj) {
                    return ViewManagerViewModel.lambda$new$1((HmiFontColorParam) obj);
                }
            }).map(new Function() { // from class: com.byd.vrassistant.viewmodel.ViewManagerViewModel$$ExternalSyntheticLambda5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return Boolean.valueOf(((HmiFontColorParam) obj).getDomain().equals(DomainEnum.DOMAIN_WEATHER));
                }
            }).withLatestFrom(behaviorSubject, (BiFunction<? super R, ? super U, ? extends R>) new BiFunction() { // from class: com.byd.vrassistant.viewmodel.ViewManagerViewModel$$ExternalSyntheticLambda6
                @Override // io.reactivex.rxjava3.functions.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return ViewManagerViewModel.lambda$new$3((Boolean) obj, (Boolean) obj2);
                }
            }).switchMap(new Function() { // from class: com.byd.vrassistant.viewmodel.ViewManagerViewModel$$ExternalSyntheticLambda7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return this.f$0.m238lambda$new$5$combydvrassistantviewmodelViewManagerViewModel((NetShowResult) obj);
                }
            });
        }
        return Observable.just(new NetShowResult(false, false));
    }

    static /* synthetic */ boolean lambda$new$1(HmiFontColorParam hmiFontColorParam) throws Throwable {
        boolean z = hmiFontColorParam.getDomain().equals(DomainEnum.DOMAIN_WEATHER) && !ContextHolder.getCurrentLocale().getLanguage().contains("zh");
        boolean z2 = !hmiFontColorParam.getDomain().equals(DomainEnum.DOMAIN_WEATHER);
        if (hmiFontColorParam.getFontColor().trim().equalsIgnoreCase("blue") && Objects.equals(hmiFontColorParam.getType(), CmdResultTypeEnum.NLU)) {
            return z || z2;
        }
        return false;
    }

    static /* synthetic */ NetShowResult lambda$new$3(Boolean bool, Boolean bool2) throws Throwable {
        return new NetShowResult(!bool2.booleanValue(), bool.booleanValue());
    }

    /* JADX INFO: renamed from: lambda$new$5$com-byd-vrassistant-viewmodel-ViewManagerViewModel, reason: not valid java name */
    /* synthetic */ ObservableSource m238lambda$new$5$combydvrassistantviewmodelViewManagerViewModel(final NetShowResult netShowResult) throws Throwable {
        return Observable.merge(Observable.just(netShowResult), this.sessionEndPsj.map(new Function() { // from class: com.byd.vrassistant.viewmodel.ViewManagerViewModel$$ExternalSyntheticLambda8
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return ViewManagerViewModel.lambda$new$4(netShowResult, obj);
            }
        }));
    }

    static /* synthetic */ NetShowResult lambda$new$4(NetShowResult netShowResult, Object obj) throws Throwable {
        return new NetShowResult(netShowResult.isShow(), false);
    }

    public void setViewBlewType(ViewBlewType viewBlewType) {
        if (viewBlewType == ViewBlewType.NONE) {
            ViewBlewType viewBlewType2 = this.lastViewBlewType;
            if (viewBlewType2 != null && viewBlewType2 != ViewBlewType.NONE) {
                BridgeHelper.getVrUiStatus().onViewBlewDismiss(this.lastViewBlewType);
            }
        } else {
            BridgeHelper.getVrUiStatus().onViewBlewShow(viewBlewType);
        }
        if (viewBlewType != ViewBlewType.NONE) {
            this.lastShowViewType = viewBlewType;
        }
        this.viewBlewTypeBsj.onNext(viewBlewType);
        this.lastViewBlewType = viewBlewType;
    }

    public void resetViewBlewType() {
        ViewBlewType viewBlewType = this.lastShowViewType;
        if (viewBlewType == null) {
            Log.e(TAG, "resetViewBlewType: lastViewType == null, wtf?");
        } else {
            this.viewBlewTypeBsj.onNext(viewBlewType);
            BridgeHelper.getVrUiStatus().onViewBlewShow(this.lastShowViewType);
        }
    }

    public BehaviorSubject<ViewBlewType> getViewBlewTypeBsj() {
        return this.viewBlewTypeBsj;
    }

    public ViewBlewType getViewBlewType() {
        if (this.viewBlewTypeBsj.getValue() == null) {
            return ViewBlewType.NONE;
        }
        return this.viewBlewTypeBsj.getValue();
    }

    public void updateVoiceBarType(VoiceBarType voiceBarType) {
        VrUiState value = this.vrUiState.getValue();
        if (value != null) {
            value.setVoiceBarType(voiceBarType);
            setStartupParam(value);
        }
    }

    public VoiceBarType getVoiceBarType() {
        if (this.vrUiState.getValue() == null) {
            return VoiceBarType.NONE;
        }
        return this.vrUiState.getValue().getVoiceBarType();
    }

    public boolean isViewShow() {
        return getVoiceBarType() != VoiceBarType.NONE;
    }

    public boolean isDismissWithAnim() {
        return this.dismissWithAnim;
    }

    public void setDismissWithAnim(boolean z) {
        this.dismissWithAnim = z;
    }

    public LiveData<VrUiState> getvrUiState() {
        return this.vrUiState;
    }

    public void setStartupParam(VrUiState vrUiState) {
        this.vrUiState.setValue(vrUiState);
    }

    public void setShowGoogleIngo(boolean z) {
        this.showGoogleIngo.setValue(Boolean.valueOf(z));
    }

    public SafeMutableLiveData<Boolean> getShowGoogleIngo() {
        return this.showGoogleIngo;
    }

    public BehaviorSubject<NetShowResult> getShowNetHintBsj() {
        return this.showNetHintBsj;
    }

    public void removeViewBlew() {
        setViewBlewType(ViewBlewType.NONE);
    }

    public void sessionEnd() {
        this.sessionEndPsj.onNext(new Object());
    }

    public void cmdHandle(HmiFontColorParam hmiFontColorParam) {
        this.handleResultPsj.onNext(hmiFontColorParam);
    }

    public void setConversationState(boolean z) {
        this.inConversationBsj.onNext(Boolean.valueOf(z));
    }
}
