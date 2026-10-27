package api.requests.com.neusoft.changan.changansysapiinverse;

import api.requests.com.neusoft.changan.changansysapiinverse.ReqOnDayNightChanged;
import api.requests.com.neusoft.changan.changansysapiinverse.ReqOnDayNightModeChanged;
import api.responses.com.neusoft.changan.changansysapiinverse.ResGetSoundTypeChanged;
import com.neusoft.de.nts.OneCoreTest.communication.Communication;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.CommonUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Function;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public class ChangAnSysApiInverseRequests implements IChangAnSysApiInverseRequests {
    private final String component;
    private final ICommunicationContextUtil<?, ?, ?> contextUtil;
    private static final Logger logger = Logger.getLogger((Class<?>) ChangAnSysApiInverseRequests.class);
    private static final CompletableFuture<Void> completedVoidFuture = CompletableFuture.completedFuture(null);

    public ChangAnSysApiInverseRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = null;
    }

    public ChangAnSysApiInverseRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil, String str) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = str;
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged) {
        reqOnDayNightChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDayNightChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnDayNightChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.changan.changansysapiinverse.ChangAnSysApiInverseRequests.1
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return ChangAnSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged, final Duration duration) {
        return fireOnDayNightChanged(reqOnDayNightChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.changan.changansysapiinverse.ChangAnSysApiInverseRequests.2
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, ChangAnSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments) {
        ReqOnDayNightChanged reqOnDayNightChanged = new ReqOnDayNightChanged();
        reqOnDayNightChanged.setArguments(arguments);
        reqOnDayNightChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDayNightChanged.setComponent(str);
        }
        return fireOnDayNightChanged(reqOnDayNightChanged);
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments, final Duration duration) {
        return fireOnDayNightChanged(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.changan.changansysapiinverse.ChangAnSysApiInverseRequests.3
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, ChangAnSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged reqOnDayNightModeChanged) {
        reqOnDayNightModeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDayNightModeChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnDayNightModeChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.changan.changansysapiinverse.ChangAnSysApiInverseRequests.4
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return ChangAnSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged reqOnDayNightModeChanged, final Duration duration) {
        return fireOnDayNightModeChanged(reqOnDayNightModeChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.changan.changansysapiinverse.ChangAnSysApiInverseRequests.5
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, ChangAnSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged.Arguments arguments) {
        ReqOnDayNightModeChanged reqOnDayNightModeChanged = new ReqOnDayNightModeChanged();
        reqOnDayNightModeChanged.setArguments(arguments);
        reqOnDayNightModeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDayNightModeChanged.setComponent(str);
        }
        return fireOnDayNightModeChanged(reqOnDayNightModeChanged);
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged.Arguments arguments, final Duration duration) {
        return fireOnDayNightModeChanged(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.changan.changansysapiinverse.ChangAnSysApiInverseRequests.6
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, ChangAnSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<ResGetSoundTypeChanged> requestGetSoundTypeChanged(ReqGetSoundTypeChanged reqGetSoundTypeChanged) {
        if (reqGetSoundTypeChanged.getResponse() == null) {
            reqGetSoundTypeChanged.setResponse(new ResGetSoundTypeChanged());
        }
        String str = this.component;
        if (str != null) {
            reqGetSoundTypeChanged.setComponent(str);
            reqGetSoundTypeChanged.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSoundTypeChanged));
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<ResGetSoundTypeChanged> requestGetSoundTypeChanged(ReqGetSoundTypeChanged reqGetSoundTypeChanged, final Duration duration) {
        return requestGetSoundTypeChanged(reqGetSoundTypeChanged).thenCompose((Function<? super ResGetSoundTypeChanged, ? extends CompletionStage<U>>) new Function<ResGetSoundTypeChanged, CompletableFuture<ResGetSoundTypeChanged>>() { // from class: api.requests.com.neusoft.changan.changansysapiinverse.ChangAnSysApiInverseRequests.7
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetSoundTypeChanged> apply(ResGetSoundTypeChanged resGetSoundTypeChanged) {
                return CommonUtil.delayAsync(resGetSoundTypeChanged, duration, ChangAnSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<ResGetSoundTypeChanged> requestGetSoundTypeChanged() {
        return requestGetSoundTypeChanged(new ReqGetSoundTypeChanged());
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<ResGetSoundTypeChanged> requestGetSoundTypeChanged(Duration duration) {
        return requestGetSoundTypeChanged(new ReqGetSoundTypeChanged(), duration);
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireGetSoundTypeChanged(ReqGetSoundTypeChanged reqGetSoundTypeChanged) {
        reqGetSoundTypeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetSoundTypeChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSoundTypeChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.changan.changansysapiinverse.ChangAnSysApiInverseRequests.8
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return ChangAnSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireGetSoundTypeChanged(ReqGetSoundTypeChanged reqGetSoundTypeChanged, final Duration duration) {
        return fireGetSoundTypeChanged(reqGetSoundTypeChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.changan.changansysapiinverse.ChangAnSysApiInverseRequests.9
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, ChangAnSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireGetSoundTypeChanged() {
        return fireGetSoundTypeChanged(new ReqGetSoundTypeChanged());
    }

    @Override // api.requests.com.neusoft.changan.changansysapiinverse.IChangAnSysApiInverseRequests
    public CompletableFuture<Void> fireGetSoundTypeChanged(final Duration duration) {
        return fireGetSoundTypeChanged(new ReqGetSoundTypeChanged()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.changan.changansysapiinverse.ChangAnSysApiInverseRequests.10
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, ChangAnSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }
}
