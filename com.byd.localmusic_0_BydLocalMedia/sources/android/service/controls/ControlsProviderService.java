package android.service.controls;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.service.controls.Control;
import android.service.controls.IControlsProvider;
import android.service.controls.IControlsSubscription;
import android.service.controls.actions.ControlAction;
import android.service.controls.actions.ControlActionWrapper;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.R;
import com.android.internal.util.Preconditions;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ControlsProviderService extends Service {
    public static final String ACTION_ADD_CONTROL = "android.service.controls.action.ADD_CONTROL";
    public static final String CALLBACK_BUNDLE = "CALLBACK_BUNDLE";
    public static final String CALLBACK_TOKEN = "CALLBACK_TOKEN";
    public static final String EXTRA_CONTROL = "android.service.controls.extra.CONTROL";
    public static final String SERVICE_CONTROLS = "android.service.controls.ControlsProviderService";
    public static final String TAG = "ControlsProviderService";
    private RequestHandler mHandler;
    private IBinder mToken;

    public abstract Flow.Publisher<Control> createPublisherFor(List<String> list);

    public abstract Flow.Publisher<Control> createPublisherForAllAvailable();

    public Flow.Publisher<Control> createPublisherForSuggested() {
        return null;
    }

    public abstract void performControlAction(String str, ControlAction controlAction, Consumer<Integer> consumer);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        this.mHandler = new RequestHandler(Looper.getMainLooper());
        this.mToken = intent.getBundleExtra(CALLBACK_BUNDLE).getBinder(CALLBACK_TOKEN);
        return new IControlsProvider.Stub() { // from class: android.service.controls.ControlsProviderService.1
            @Override // android.service.controls.IControlsProvider
            public void load(IControlsSubscriber iControlsSubscriber) {
                ControlsProviderService.this.mHandler.obtainMessage(1, iControlsSubscriber).sendToTarget();
            }

            @Override // android.service.controls.IControlsProvider
            public void loadSuggested(IControlsSubscriber iControlsSubscriber) {
                ControlsProviderService.this.mHandler.obtainMessage(4, iControlsSubscriber).sendToTarget();
            }

            @Override // android.service.controls.IControlsProvider
            public void subscribe(List<String> list, IControlsSubscriber iControlsSubscriber) {
                ControlsProviderService.this.mHandler.obtainMessage(2, new SubscribeMessage(list, iControlsSubscriber)).sendToTarget();
            }

            @Override // android.service.controls.IControlsProvider
            public void action(String str, ControlActionWrapper controlActionWrapper, IControlsActionCallback iControlsActionCallback) {
                ControlsProviderService.this.mHandler.obtainMessage(3, new ActionMessage(str, controlActionWrapper.getWrappedAction(), iControlsActionCallback)).sendToTarget();
            }
        };
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        this.mHandler = null;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class RequestHandler extends Handler {
        private static final int MSG_ACTION = 3;
        private static final int MSG_LOAD = 1;
        private static final int MSG_LOAD_SUGGESTED = 4;
        private static final int MSG_SUBSCRIBE = 2;

        RequestHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                ControlsProviderService.this.createPublisherForAllAvailable().subscribe(new SubscriberProxy(true, ControlsProviderService.this.mToken, (IControlsSubscriber) message.obj));
                return;
            }
            if (i == 2) {
                SubscribeMessage subscribeMessage = (SubscribeMessage) message.obj;
                ControlsProviderService.this.createPublisherFor(subscribeMessage.mControlIds).subscribe(new SubscriberProxy(false, ControlsProviderService.this.mToken, subscribeMessage.mSubscriber));
            } else if (i == 3) {
                ActionMessage actionMessage = (ActionMessage) message.obj;
                ControlsProviderService.this.performControlAction(actionMessage.mControlId, actionMessage.mAction, consumerFor(actionMessage.mControlId, actionMessage.mCb));
            } else {
                if (i != 4) {
                    return;
                }
                SubscriberProxy subscriberProxy = new SubscriberProxy(true, ControlsProviderService.this.mToken, (IControlsSubscriber) message.obj);
                Flow.Publisher<Control> publisherCreatePublisherForSuggested = ControlsProviderService.this.createPublisherForSuggested();
                if (publisherCreatePublisherForSuggested == null) {
                    Log.i(ControlsProviderService.TAG, "No publisher provided for suggested controls");
                    subscriberProxy.onComplete();
                } else {
                    publisherCreatePublisherForSuggested.subscribe(subscriberProxy);
                }
            }
        }

        private Consumer<Integer> consumerFor(final String str, final IControlsActionCallback iControlsActionCallback) {
            return new Consumer() { // from class: android.service.controls.-$$Lambda$ControlsProviderService$RequestHandler$mRK5NjhY1D2BY_5KfsEN3snmSCg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$consumerFor$0$ControlsProviderService$RequestHandler(iControlsActionCallback, str, (Integer) obj);
                }
            };
        }

        public /* synthetic */ void lambda$consumerFor$0$ControlsProviderService$RequestHandler(IControlsActionCallback iControlsActionCallback, String str, Integer num) {
            Preconditions.checkNotNull(num);
            if (!ControlAction.isValidResponse(num.intValue())) {
                Log.e(ControlsProviderService.TAG, "Not valid response result: " + num);
                num = 0;
            }
            try {
                iControlsActionCallback.accept(ControlsProviderService.this.mToken, str, num.intValue());
            } catch (RemoteException e) {
                e.rethrowAsRuntimeException();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isStatelessControl(Control control) {
        return control.getStatus() == 0 && control.getControlTemplate().getTemplateType() == 0 && TextUtils.isEmpty(control.getStatusText());
    }

    private static class SubscriberProxy implements Flow.Subscriber<Control> {
        private IControlsSubscriber mCs;
        private boolean mEnforceStateless;
        private IBinder mToken;

        SubscriberProxy(boolean z, IBinder iBinder, IControlsSubscriber iControlsSubscriber) {
            this.mEnforceStateless = z;
            this.mToken = iBinder;
            this.mCs = iControlsSubscriber;
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onSubscribe(Flow.Subscription subscription) {
            try {
                this.mCs.onSubscribe(this.mToken, new SubscriptionAdapter(subscription));
            } catch (RemoteException e) {
                e.rethrowAsRuntimeException();
            }
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onNext(Control control) {
            Preconditions.checkNotNull(control);
            try {
                if (this.mEnforceStateless && !ControlsProviderService.isStatelessControl(control)) {
                    Log.w(ControlsProviderService.TAG, "onNext(): control is not stateless. Use the Control.StatelessBuilder() to build the control.");
                    control = new Control.StatelessBuilder(control).build();
                }
                this.mCs.onNext(this.mToken, control);
            } catch (RemoteException e) {
                e.rethrowAsRuntimeException();
            }
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onError(Throwable th) {
            try {
                this.mCs.onError(this.mToken, th.toString());
            } catch (RemoteException e) {
                e.rethrowAsRuntimeException();
            }
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onComplete() {
            try {
                this.mCs.onComplete(this.mToken);
            } catch (RemoteException e) {
                e.rethrowAsRuntimeException();
            }
        }
    }

    public static void requestAddControl(Context context, ComponentName componentName, Control control) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(componentName);
        Preconditions.checkNotNull(control);
        String string = context.getString(R.string.config_controlsPackage);
        Intent intent = new Intent(ACTION_ADD_CONTROL);
        intent.putExtra(Intent.EXTRA_COMPONENT_NAME, componentName);
        intent.setPackage(string);
        if (isStatelessControl(control)) {
            intent.putExtra(EXTRA_CONTROL, control);
        } else {
            intent.putExtra(EXTRA_CONTROL, new Control.StatelessBuilder(control).build());
        }
        context.sendBroadcast(intent, Manifest.permission.BIND_CONTROLS);
    }

    private static class SubscriptionAdapter extends IControlsSubscription.Stub {
        final Flow.Subscription mSubscription;

        SubscriptionAdapter(Flow.Subscription subscription) {
            this.mSubscription = subscription;
        }

        @Override // android.service.controls.IControlsSubscription
        public void request(long j) {
            this.mSubscription.request(j);
        }

        @Override // android.service.controls.IControlsSubscription
        public void cancel() {
            this.mSubscription.cancel();
        }
    }

    private static class ActionMessage {
        final ControlAction mAction;
        final IControlsActionCallback mCb;
        final String mControlId;

        ActionMessage(String str, ControlAction controlAction, IControlsActionCallback iControlsActionCallback) {
            this.mControlId = str;
            this.mAction = controlAction;
            this.mCb = iControlsActionCallback;
        }
    }

    private static class SubscribeMessage {
        final List<String> mControlIds;
        final IControlsSubscriber mSubscriber;

        SubscribeMessage(List<String> list, IControlsSubscriber iControlsSubscriber) {
            this.mControlIds = list;
            this.mSubscriber = iControlsSubscriber;
        }
    }
}
