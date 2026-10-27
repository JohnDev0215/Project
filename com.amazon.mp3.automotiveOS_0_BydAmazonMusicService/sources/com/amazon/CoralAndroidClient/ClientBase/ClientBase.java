package com.amazon.CoralAndroidClient.ClientBase;

import android.util.Log;
import com.amazon.CoralAndroidClient.Connector.Connector;
import com.amazon.CoralAndroidClient.Connector.HttpURLConnectionFactory;
import com.amazon.CoralAndroidClient.Connector.TransmissionFilter;
import com.amazon.CoralAndroidClient.Exception.ClientException;
import com.amazon.CoralAndroidClient.Exception.CoralException;
import com.amazon.CoralAndroidClient.Exception.NativeException;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes2.dex */
public class ClientBase {
    private HttpURLConnectionFactory mHttpURLConnectionFactory;
    private Proxy mProxy;
    private Object mPropertyLock = new Object();
    private URL mEndpointURL = null;
    private int mRequestTimeout = 0;
    private int mMaxRetry = 5;
    private int mMaxRedirect = 10;
    private CopyOnWriteArraySet<TransmissionFilter> mTransmissionFilters = new CopyOnWriteArraySet<>();
    private ExecutorService mExecutor = Executors.newCachedThreadPool();

    public void invokeAsync(ClientInput clientInput, Marshaller marshaller, Unmarshaller unmarshaller, ResultHandler resultHandler) {
        try {
            invokeAsyncInternal(clientInput, marshaller, unmarshaller, resultHandler);
        } catch (NativeException e) {
            Log.e("ClientBase", "enqueue task failed.", e);
        }
    }

    private Future<ClientOutput> invokeAsyncInternal(ClientInput clientInput, Marshaller marshaller, Unmarshaller unmarshaller, ResultHandler resultHandler) throws NativeException {
        if (marshaller == null) {
            throw new NativeException("marshaller is null in invokeAsyncInternal");
        }
        ClientRequest clientRequestMarshal = marshaller.marshal(clientInput);
        if (clientRequestMarshal == null || Helper.isStringNullOrEmpty(clientRequestMarshal.getOperationName())) {
            throw new NativeException("invalid ClientRequest");
        }
        try {
            return this.mExecutor.submit(new RequestTask(clientRequestMarshal, unmarshaller, resultHandler));
        } catch (RejectedExecutionException e) {
            NativeException nativeException = new NativeException("submit task fail.", e);
            if (resultHandler != null) {
                resultHandler.onException(nativeException);
            }
            return null;
        }
    }

    public void setEndpoint(String str) throws NativeException {
        if (Helper.isStringNullOrEmpty(str)) {
            throw new NativeException(String.format("invalid endpoint: %s", str == null ? "(null)" : "(empty)"));
        }
        try {
            URL url = new URL(str);
            if (url.getProtocol() == null || url.getHost() == null) {
                throw new NativeException(String.format("invalid endpoint %s, need protocol and host", str));
            }
            String protocol = url.getProtocol();
            if (!protocol.equals("http") && !protocol.equals("https")) {
                throw new NativeException(String.format("invalid endpoint %s, only HTTP and HTTPS are acceptable", str));
            }
            String path = "/";
            if (url.getPath() != null && url.getPath().length() != 0) {
                path = url.getPath();
            }
            URL url2 = new URL(url.getProtocol(), url.getHost(), url.getPort(), path);
            synchronized (this.mPropertyLock) {
                this.mEndpointURL = url2;
            }
        } catch (MalformedURLException e) {
            throw new NativeException(String.format("invalid endpoint %s", str), e);
        }
    }

    public String getEndpoint() throws NativeException {
        URL url;
        synchronized (this.mPropertyLock) {
            url = this.mEndpointURL;
        }
        return url == null ? "" : url.toString();
    }

    public void setRequestTimeout(int i) {
        if (i < 0) {
            i = 0;
        }
        synchronized (this.mPropertyLock) {
            this.mRequestTimeout = i;
        }
    }

    public int getRequestTimeout() {
        int i;
        synchronized (this.mPropertyLock) {
            i = this.mRequestTimeout;
        }
        return i;
    }

    public void setHttpURLConnectionFactory(HttpURLConnectionFactory httpURLConnectionFactory) {
        synchronized (this.mPropertyLock) {
            this.mHttpURLConnectionFactory = httpURLConnectionFactory;
        }
    }

    public void setMaxRetry(int i) {
        if (i >= 0) {
            this.mMaxRetry = i;
        }
    }

    public int getMaxRetry() {
        return this.mMaxRetry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Connector createConnector() throws NativeException {
        return Connector.create();
    }

    protected class RequestTask implements Callable<ClientOutput> {
        private Connector mConnector;
        public final ClientRequest mRequest;
        public final ResultHandler mResultHandler;
        public final Unmarshaller mUnmarshaller;

        public RequestTask(ClientRequest clientRequest, Unmarshaller unmarshaller, ResultHandler resultHandler) {
            this.mRequest = clientRequest;
            this.mUnmarshaller = unmarshaller;
            this.mResultHandler = resultHandler;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ClientOutput call() throws NativeException, CoralException {
            initConnector();
            try {
                ClientResponse clientResponseSend = this.mConnector.send(this.mRequest, this.mUnmarshaller);
                if (clientResponseSend == null) {
                    onSuccess(null);
                    return null;
                }
                if (!clientResponseSend.isSuccessful()) {
                    CoralException exception = clientResponseSend.getException();
                    if (exception != null) {
                        onException(exception);
                        throw exception;
                    }
                    NativeException nativeException = new NativeException("Response contain empty exception");
                    onException(nativeException);
                    throw nativeException;
                }
                ClientOutput output = clientResponseSend.getOutput();
                onSuccess(output);
                return output;
            } catch (NativeException e) {
                onException(e);
                throw e;
            }
        }

        private void initConnector() throws NativeException {
            URL url;
            int i;
            Proxy proxy;
            int i2;
            int i3;
            HttpURLConnectionFactory httpURLConnectionFactory;
            synchronized (ClientBase.this.mPropertyLock) {
                url = ClientBase.this.mEndpointURL;
                i = ClientBase.this.mRequestTimeout;
                proxy = ClientBase.this.mProxy;
                i2 = ClientBase.this.mMaxRetry;
                i3 = ClientBase.this.mMaxRedirect;
                httpURLConnectionFactory = ClientBase.this.mHttpURLConnectionFactory;
            }
            Connector connectorCreateConnector = ClientBase.this.createConnector();
            this.mConnector = connectorCreateConnector;
            connectorCreateConnector.setEndpoint(url);
            this.mConnector.setConnectTimeout(i);
            this.mConnector.setReadTimeout(i);
            this.mConnector.setMaxRetry(i2);
            this.mConnector.setMaxRedirect(i3);
            this.mConnector.setProxy(proxy);
            this.mConnector.setTransmissionFilterSet(ClientBase.this.mTransmissionFilters);
            if (httpURLConnectionFactory != null) {
                this.mConnector.setHttpURLConnectionFactory(httpURLConnectionFactory);
            }
        }

        private void onException(ClientException clientException) {
            ResultHandler resultHandler = this.mResultHandler;
            if (resultHandler == null) {
                return;
            }
            resultHandler.onException(clientException);
        }

        private void onSuccess(ClientOutput clientOutput) {
            ResultHandler resultHandler = this.mResultHandler;
            if (resultHandler == null) {
                return;
            }
            resultHandler.onSuccess(clientOutput);
        }
    }
}
