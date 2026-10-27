package com.amazon.alexa.voice.core.processor.superbowl;

import com.amazon.alexa.voice.core.Action;
import com.amazon.alexa.voice.core.ActionValue;
import com.amazon.alexa.voice.core.Cancellable;
import com.amazon.alexa.voice.core.Consumer;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.Executable;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.SimpleCancellable;
import com.amazon.alexa.voice.core.Task;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.Downchannel;
import com.amazon.alexa.voice.core.schedulers.Schedulers;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class SuperbowlDownchannel implements Downchannel {
    private final AccessTokenProvider accessTokenProvider;
    private boolean active;
    private Cancellable connectionExecutable;
    private final ContentProvider contentProvider;
    private final DirectiveFactory directiveFactory;
    private String endpoint;
    private final OkHttpClient httpClient;
    private List<Downchannel.Listener> listeners;
    private Cancellable processingExecutable;
    private Response response;

    public static class SimpleListener implements Downchannel.Listener {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.Downchannel.Listener
        public void onDirectiveReceived(Directive directive) {
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.Downchannel.Listener
        public void onDownchannelActivated() {
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.Downchannel.Listener
        public void onDownchannelDeactivated() {
        }
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Downchannel
    public void activate() throws IOException {
        if (isActive()) {
            return;
        }
        cleanup();
        this.response = FirebasePerfOkHttpClient.execute(directivePollingCall());
        processResponse();
    }

    public void activateAsync() {
        if (isActive()) {
            return;
        }
        cleanup();
        this.connectionExecutable = establishConnectionExecutable();
    }

    public void deactivate() {
        setActive(false);
        cleanup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setActive(boolean z) {
        boolean zIsActive = isActive();
        this.active = z;
        if (z && !zIsActive) {
            notifyDownchannelActivated();
        } else if (zIsActive && !z) {
            notifyDownchannelDeactivated();
        }
    }

    public synchronized boolean isActive() {
        return this.active;
    }

    private void cleanup() {
        Cancellable cancellable = this.processingExecutable;
        if (cancellable != null && !cancellable.isCancelled()) {
            this.processingExecutable.cancel();
            this.processingExecutable = null;
        }
        Cancellable cancellable2 = this.connectionExecutable;
        if (cancellable2 != null && !cancellable2.isCancelled()) {
            this.connectionExecutable.cancel();
            this.connectionExecutable = null;
        }
        new Executable(new Action() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.2
            @Override // com.amazon.alexa.voice.core.Action
            public void call() throws Exception {
                SuperbowlDownchannel.this.closeResponse();
                SuperbowlDownchannel.this.httpClient.connectionPool().evictAll();
            }
        }).executeOn(Schedulers.io()).execute(new Consumer<Throwable>() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.1
            @Override // com.amazon.alexa.voice.core.Consumer
            public void accept(Throwable th) throws Exception {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeResponse() {
        Response response = this.response;
        if (response != null) {
            response.close();
            this.response = null;
        }
    }

    private Cancellable establishConnectionExecutable() {
        return new Executable(new ActionValue<Task<Void>>() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.6
            @Override // com.amazon.alexa.voice.core.ActionValue
            public void call(Task<Void> task) throws Exception {
                final Call callDirectivePollingCall = SuperbowlDownchannel.this.directivePollingCall();
                task.add(new SimpleCancellable() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.6.1
                    @Override // com.amazon.alexa.voice.core.SimpleCancellable
                    public void onCancelled() {
                        callDirectivePollingCall.cancel();
                    }
                });
                SuperbowlDownchannel.this.response = FirebasePerfOkHttpClient.execute(callDirectivePollingCall);
                SuperbowlDownchannel.this.processResponse();
            }
        }).executeOn(Schedulers.io()).onCancel(new Action() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.5
            @Override // com.amazon.alexa.voice.core.Action
            public void call() {
                Logger.info("Downchannel connection is cancelling.");
                SuperbowlDownchannel.this.setActive(false);
            }
        }).execute(new Consumer<Void>() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.3
            @Override // com.amazon.alexa.voice.core.Consumer
            public void accept(Void r2) {
                SuperbowlDownchannel.this.setActive(false);
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.4
            @Override // com.amazon.alexa.voice.core.Consumer
            public void accept(Throwable th) throws Exception {
                Logger.error("Failed to establish a Downchannel connection.", th);
                SuperbowlDownchannel.this.setActive(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Call directivePollingCall() throws IOException {
        return this.httpClient.newCall(new Request.Builder().url(this.endpoint + "/v20160207/directives").addHeader(HttpHeader.AUTHORIZATION, "Bearer " + this.accessTokenProvider.getAccessToken()).get().build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processResponse() throws IOException {
        int iCode = this.response.code();
        if (iCode != 200) {
            closeResponse();
            throw new IOException("Bad status connecting Downchannel " + iCode);
        }
        this.processingExecutable = new Executable(new Action() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.10
            @Override // com.amazon.alexa.voice.core.Action
            public void call() throws Exception {
                SuperbowlDownchannel.this.setActive(true);
                SuperbowlDownchannel superbowlDownchannel = SuperbowlDownchannel.this;
                superbowlDownchannel.listenForDirectives(superbowlDownchannel.response.body());
            }
        }).executeOn(Schedulers.io()).onCancel(new Action() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.9
            @Override // com.amazon.alexa.voice.core.Action
            public void call() {
                Logger.info("Downchannel response processing is cancelling.");
                SuperbowlDownchannel.this.closeResponse();
                SuperbowlDownchannel.this.setActive(false);
            }
        }).execute(new Consumer<Void>() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.7
            @Override // com.amazon.alexa.voice.core.Consumer
            public void accept(Void r2) {
                SuperbowlDownchannel.this.closeResponse();
                SuperbowlDownchannel.this.setActive(false);
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.8
            @Override // com.amazon.alexa.voice.core.Consumer
            public void accept(Throwable th) throws Exception {
                Logger.error("Exception while processing Downchannel directive stream.", th);
                SuperbowlDownchannel.this.closeResponse();
                SuperbowlDownchannel.this.setActive(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void listenForDirectives(ResponseBody responseBody) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseBody.byteStream()));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return;
            }
            Directive directiveExtractDirective = extractDirective(line);
            if (directiveExtractDirective != null) {
                notifyDirectiveReceived(directiveExtractDirective);
            }
        }
    }

    private Directive extractDirective(String str) {
        try {
            return this.directiveFactory.create(new JSONObject(str), this.contentProvider);
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Downchannel
    public void addListener(Downchannel.Listener listener) {
        Preconditions.notNull(listener, "Downchannel listener must not be null.");
        this.listeners.add(listener);
    }

    private void notifyDirectiveReceived(final Directive directive) {
        Schedulers.main().schedule(new Runnable() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.11
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = SuperbowlDownchannel.this.listeners.iterator();
                while (it.hasNext()) {
                    ((Downchannel.Listener) it.next()).onDirectiveReceived(directive);
                }
            }
        });
    }

    private void notifyDownchannelActivated() {
        Schedulers.main().schedule(new Runnable() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.12
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = SuperbowlDownchannel.this.listeners.iterator();
                while (it.hasNext()) {
                    ((Downchannel.Listener) it.next()).onDownchannelActivated();
                }
            }
        });
    }

    private void notifyDownchannelDeactivated() {
        Schedulers.main().schedule(new Runnable() { // from class: com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDownchannel.13
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = SuperbowlDownchannel.this.listeners.iterator();
                while (it.hasNext()) {
                    ((Downchannel.Listener) it.next()).onDownchannelDeactivated();
                }
            }
        });
    }

    public boolean setEndpoint(String str) {
        if (this.endpoint.equals(str)) {
            return true;
        }
        boolean zIsActive = isActive();
        deactivate();
        this.endpoint = str;
        if (!zIsActive) {
            return true;
        }
        activateAsync();
        return true;
    }
}
