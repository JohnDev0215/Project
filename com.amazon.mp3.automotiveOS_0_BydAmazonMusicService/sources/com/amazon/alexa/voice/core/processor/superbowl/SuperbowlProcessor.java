package com.amazon.alexa.voice.core.processor.superbowl;

import androidx.core.app.NotificationCompat;
import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.Event;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.VoiceProcessor;
import com.amazon.alexa.voice.core.VoiceProcessorException;
import com.amazon.alexa.voice.core.http.TLSv12HttpClient;
import com.amazon.alexa.voice.core.internal.audio.BufferedAudioSource;
import com.amazon.alexa.voice.core.internal.http.Header;
import com.amazon.alexa.voice.core.internal.http.MultipartResponse;
import com.amazon.alexa.voice.core.internal.http.SpeechRequestBody;
import com.amazon.alexa.voice.core.internal.http.StreamableAudioSource;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory;
import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioClearQueueDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioPlayDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioStopDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.ExpectSpeechDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.SetEndpointDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.SynthesizeSpeechDirective;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.visualon.OSMPUtils.voMimeTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UnknownFormatConversionException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class SuperbowlProcessor implements VoiceProcessor {
    private final AccessTokenProvider accessTokenProvider;
    private final DirectiveFactory directiveFactory;
    private volatile String endpoint;
    private final OkHttpClient httpClient;
    private volatile UnsupportedDirectiveHandler unsupportedDirectiveHandler;

    public interface UnsupportedDirectiveHandler {
        boolean onUnsupportedDirective(String str, Throwable th);
    }

    private SuperbowlProcessor(Builder builder) {
        this.httpClient = builder.httpClient;
        this.endpoint = builder.endpoint;
        this.directiveFactory = builder.directiveFactory;
        this.accessTokenProvider = builder.accessTokenProvider;
    }

    public void setUnsupportedDirectiveHandler(UnsupportedDirectiveHandler unsupportedDirectiveHandler) {
        this.unsupportedDirectiveHandler = unsupportedDirectiveHandler;
    }

    @Override // com.amazon.alexa.voice.core.VoiceProcessor
    public Directive[] execute(Event event) throws IOException {
        Preconditions.notNull(event, "event == null");
        Preconditions.instance(event, SuperbowlEvent.class, "Only superbowl events are supported!");
        SuperbowlEvent superbowlEvent = (SuperbowlEvent) event;
        String accessToken = this.accessTokenProvider.getAccessToken();
        Preconditions.notNull(accessToken, "accessToken == null");
        String string = createMetadata(superbowlEvent).toString();
        Logger.debug(string);
        MultipartBody.Builder builderAddFormDataPart = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("metadata", null, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), string));
        AudioSource audioSource = superbowlEvent.getAudioSource();
        if (audioSource != null) {
            builderAddFormDataPart.addFormDataPart(voMimeTypes.VOBASE_TYPE_AUDIO, null, new SpeechRequestBody(MediaType.parse("application/octet-stream"), new StreamableAudioSource(new BufferedAudioSource(audioSource))));
        }
        try {
            Response responseExecute = FirebasePerfOkHttpClient.execute(this.httpClient.newCall(new Request.Builder().url(this.endpoint + "/v20160207/events").addHeader(HttpHeader.AUTHORIZATION, "Bearer " + accessToken).post(builderAddFormDataPart.build()).build()));
            try {
                String strMessage = responseExecute.message();
                int iCode = responseExecute.code();
                Logger.debug("Response: " + iCode + " " + strMessage);
                if (iCode == 204) {
                    SuperbowlDirective[] superbowlDirectiveArr = new SuperbowlDirective[0];
                    responseExecute.close();
                    return superbowlDirectiveArr;
                }
                if (iCode == 200) {
                    String strHeader = responseExecute.header(HttpHeader.CONTENT_TYPE);
                    if (strHeader == null) {
                        throw new IOException("No content type");
                    }
                    MultipartResponse multipartResponseCreate = MultipartResponse.create(strHeader, responseExecute.body().bytes());
                    SuperbowlDirective[] superbowlDirectiveArrCreateDirectives = createDirectives(multipartResponseCreate, createContentProvider(multipartResponseCreate));
                    responseExecute.close();
                    return superbowlDirectiveArrCreateDirectives;
                }
                throw new SuperbowlException("Unexpected code. " + strMessage, iCode);
            } finally {
            }
        } catch (IOException e) {
            throw new VoiceProcessorException(e);
        }
    }

    public void setEndpoint(String str) {
        Preconditions.notNull(str, "endpoint == null");
        this.endpoint = str;
    }

    private SuperbowlDirective[] createDirectives(MultipartResponse multipartResponse, ContentProvider contentProvider) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (MultipartResponse.PartialResponse partialResponse : multipartResponse) {
            String strHeader = partialResponse.header(HttpHeader.CONTENT_TYPE);
            if (strHeader != null) {
                Header header = Header.parse(strHeader);
                if ("application/json".equalsIgnoreCase(header.getValue()) && "utf-8".equalsIgnoreCase(header.get("charset"))) {
                    String str = new String(partialResponse.body(), partialResponse.offset(), partialResponse.size());
                    Logger.debug(str);
                    try {
                        SuperbowlDirective superbowlDirectiveCreateDirective = createDirective(str, contentProvider);
                        if (superbowlDirectiveCreateDirective != null) {
                            arrayList.add(superbowlDirectiveCreateDirective);
                        }
                    } catch (JSONException e) {
                        if (this.unsupportedDirectiveHandler == null || !this.unsupportedDirectiveHandler.onUnsupportedDirective(str, e)) {
                            throw new IOException(e);
                        }
                    }
                }
            }
        }
        return (SuperbowlDirective[]) arrayList.toArray(new SuperbowlDirective[arrayList.size()]);
    }

    private SuperbowlDirective createDirective(String str, ContentProvider contentProvider) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        SuperbowlDirective superbowlDirectiveCreate = this.directiveFactory.create(jSONObject, contentProvider);
        if (superbowlDirectiveCreate == null) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("directive").getJSONObject("header");
            UnknownFormatConversionException unknownFormatConversionException = new UnknownFormatConversionException("Unsupported directive " + jSONObject2.getString("namespace") + '.' + jSONObject2.getString("name"));
            if (this.unsupportedDirectiveHandler == null || !this.unsupportedDirectiveHandler.onUnsupportedDirective(str, unknownFormatConversionException)) {
                throw unknownFormatConversionException;
            }
        }
        return superbowlDirectiveCreate;
    }

    private ContentProvider createContentProvider(MultipartResponse multipartResponse) throws IOException {
        ContentProvider contentProvider = new ContentProvider();
        for (MultipartResponse.PartialResponse partialResponse : multipartResponse) {
            String strHeader = partialResponse.header("Content-ID");
            if (strHeader != null) {
                if (strHeader.length() > 1 && strHeader.charAt(0) == '<' && strHeader.charAt(strHeader.length() - 1) == '>') {
                    strHeader = strHeader.substring(1, strHeader.length() - 1);
                }
                String strHeader2 = partialResponse.header(HttpHeader.CONTENT_TYPE);
                if (strHeader2 != null && "application/octet-stream".equalsIgnoreCase(Header.parse(strHeader2).getValue())) {
                    contentProvider.add(new Content(strHeader, partialResponse.body(), partialResponse.offset(), partialResponse.size()));
                }
            }
        }
        return contentProvider;
    }

    private JSONArray createContext(SuperbowlEvent superbowlEvent) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Context context : superbowlEvent.getContext()) {
            jSONArray.put(context.toJsonObject());
        }
        return jSONArray;
    }

    private JSONObject createMetadata(SuperbowlEvent superbowlEvent) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("context", createContext(superbowlEvent));
            jSONObject.put(NotificationCompat.CATEGORY_EVENT, superbowlEvent.toJsonObject());
            return jSONObject;
        } catch (JSONException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static final class Builder {
        AccessTokenProvider accessTokenProvider;
        OkHttpClient httpClient;
        String endpoint = "https://avs-alexa-na.amazon.com";
        DirectiveFactory directiveFactory = new DirectiveFactory();

        public Builder() {
            addFactory("SpeechSynthesizer", "Speak", new SynthesizeSpeechDirective.Factory());
            addFactory("SpeechRecognizer", "ExpectSpeech", new ExpectSpeechDirective.Factory());
            addFactory("AudioPlayer", "Play", new AudioPlayDirective.Factory());
            addFactory("AudioPlayer", "Stop", new AudioStopDirective.Factory());
            addFactory("AudioPlayer", "ClearQueue", new AudioClearQueueDirective.Factory());
            addFactory("System", "SetEndpoint", new SetEndpointDirective.Factory());
        }

        public Builder endpoint(String str) {
            this.endpoint = str;
            return this;
        }

        public Builder httpClient(OkHttpClient okHttpClient) {
            this.httpClient = okHttpClient;
            return this;
        }

        public Builder addFactory(String str, String str2, DirectiveFactory.Factory factory) {
            this.directiveFactory.add(str, str2, factory);
            return this;
        }

        public Builder accessToken(AccessTokenProvider accessTokenProvider) {
            this.accessTokenProvider = accessTokenProvider;
            return this;
        }

        public SuperbowlProcessor build() {
            Preconditions.notNull(this.endpoint, "endpoint == null");
            Preconditions.notNull(this.accessTokenProvider, "accessTokenProvider == null");
            if (this.httpClient == null) {
                this.httpClient = TLSv12HttpClient.create();
            }
            return new SuperbowlProcessor(this);
        }
    }
}
