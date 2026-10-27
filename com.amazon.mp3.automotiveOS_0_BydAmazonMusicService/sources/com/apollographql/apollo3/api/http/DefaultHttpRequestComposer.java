package com.apollographql.apollo3.api.http;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Upload;
import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.internal.UrlEncodeKt;
import com.apollographql.apollo3.api.json.BufferedSinkJsonWriter;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.apollographql.apollo3.api.json.JsonWriters;
import com.apollographql.apollo3.api.json.MapJsonWriter;
import com.apollographql.apollo3.api.json.internal.FileUploadAwareJsonWriter;
import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

/* JADX INFO: compiled from: DefaultHttpRequestComposer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo3/api/http/DefaultHttpRequestComposer;", "Lcom/apollographql/apollo3/api/http/HttpRequestComposer;", "serverUrl", "", "(Ljava/lang/String;)V", "compose", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "apolloRequest", "Lcom/apollographql/apollo3/api/ApolloRequest;", "Companion", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultHttpRequestComposer implements HttpRequestComposer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String serverUrl;

    /* JADX INFO: compiled from: DefaultHttpRequestComposer.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HttpMethod.values().length];
            iArr[HttpMethod.Get.ordinal()] = 1;
            iArr[HttpMethod.Post.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public DefaultHttpRequestComposer(String serverUrl) {
        Intrinsics.checkNotNullParameter(serverUrl, "serverUrl");
        this.serverUrl = serverUrl;
    }

    @Override // com.apollographql.apollo3.api.http.HttpRequestComposer
    public <D extends Operation.Data> HttpRequest compose(ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "apolloRequest");
        Operation<D> operation = apolloRequest.getOperation();
        CustomScalarAdapters customScalarAdapters = (CustomScalarAdapters) apolloRequest.getExecutionContext().get(CustomScalarAdapters.INSTANCE);
        if (customScalarAdapters == null) {
            customScalarAdapters = CustomScalarAdapters.Empty;
        }
        CustomScalarAdapters customScalarAdapters2 = customScalarAdapters;
        List listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new HttpHeader[]{new HttpHeader("X-APOLLO-OPERATION-ID", operation.id()), new HttpHeader("X-APOLLO-OPERATION-NAME", operation.name())});
        List<HttpHeader> httpHeaders = apolloRequest.getHttpHeaders();
        if (httpHeaders == null) {
            httpHeaders = CollectionsKt__CollectionsKt.emptyList();
        }
        List<HttpHeader> listPlus = CollectionsKt___CollectionsKt.plus((Collection) listListOf, (Iterable) httpHeaders);
        Boolean sendApqExtensions = apolloRequest.getSendApqExtensions();
        boolean zBooleanValue = sendApqExtensions != null ? sendApqExtensions.booleanValue() : false;
        Boolean sendDocument = apolloRequest.getSendDocument();
        boolean zBooleanValue2 = sendDocument == null ? true : sendDocument.booleanValue();
        HttpMethod httpMethod = apolloRequest.getHttpMethod();
        if (httpMethod == null) {
            httpMethod = HttpMethod.Post;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[httpMethod.ordinal()];
        if (i == 1) {
            return new HttpRequest.Builder(HttpMethod.Get, INSTANCE.buildGetUrl(this.serverUrl, operation, customScalarAdapters2, zBooleanValue, zBooleanValue2)).addHeaders(listPlus).build();
        }
        if (i == 2) {
            return new HttpRequest.Builder(HttpMethod.Post, this.serverUrl).addHeaders(listPlus).body(INSTANCE.buildPostBody(operation, customScalarAdapters2, zBooleanValue, zBooleanValue2 ? operation.document() : null)).build();
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: compiled from: DefaultHttpRequestComposer.kt */
    @Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b#\u0010$J@\u0010\r\u001a\u00020\u0004\"\b\b\u0000\u0010\u0003*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002JN\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0011\"\b\b\u0000\u0010\u0003*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0002JD\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002J\u001c\u0010\u0018\u001a\u00020\u00172\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\u001e\u0010\u001a\u001a\u00020\u0004*\u00020\u00042\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011J8\u0010\u001c\u001a\u00020\u001b\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004J,\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dR\u0014\u0010 \u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\"\u0010!¨\u0006%"}, d2 = {"Lcom/apollographql/apollo3/api/http/DefaultHttpRequestComposer$Companion;", "", "Lcom/apollographql/apollo3/api/Operation$Data;", "D", "", "serverUrl", "Lcom/apollographql/apollo3/api/Operation;", "operation", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "sendApqExtensions", "sendDocument", "buildGetUrl", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", SearchIntents.EXTRA_QUERY, "", "Lcom/apollographql/apollo3/api/Upload;", "composePostParams", "autoPersistQueries", "composeGetParams", "uploads", "Lokio/ByteString;", "buildUploadMap", "parameters", "appendQueryParameters", "Lcom/apollographql/apollo3/api/http/HttpBody;", "buildPostBody", "Lcom/apollographql/apollo3/api/ApolloRequest;", "apolloRequest", "composePayload", "HEADER_APOLLO_OPERATION_ID", "Ljava/lang/String;", "HEADER_APOLLO_OPERATION_NAME", "<init>", "()V", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <D extends Operation.Data> Map<String, Upload> composePostParams(JsonWriter writer, Operation<D> operation, CustomScalarAdapters customScalarAdapters, boolean sendApqExtensions, String query) throws IOException {
            writer.beginObject();
            writer.name("operationName");
            writer.value(operation.name());
            writer.name("variables");
            FileUploadAwareJsonWriter fileUploadAwareJsonWriter = new FileUploadAwareJsonWriter(writer);
            fileUploadAwareJsonWriter.beginObject();
            operation.serializeVariables(fileUploadAwareJsonWriter, customScalarAdapters);
            fileUploadAwareJsonWriter.endObject();
            Map<String, Upload> mapCollectedUploads = fileUploadAwareJsonWriter.collectedUploads();
            if (query != null) {
                writer.name(SearchIntents.EXTRA_QUERY);
                writer.value(query);
            }
            if (sendApqExtensions) {
                writer.name("extensions");
                writer.beginObject();
                writer.name("persistedQuery");
                writer.beginObject();
                writer.name("version").value(1);
                writer.name("sha256Hash").value(operation.id());
                writer.endObject();
                writer.endObject();
            }
            writer.endObject();
            return mapCollectedUploads;
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <D extends Operation.Data> String buildGetUrl(String serverUrl, Operation<D> operation, CustomScalarAdapters customScalarAdapters, boolean sendApqExtensions, boolean sendDocument) {
            return appendQueryParameters(serverUrl, composeGetParams(operation, customScalarAdapters, sendApqExtensions, sendDocument));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ByteString buildUploadMap(Map<String, ? extends Upload> uploads) throws IOException {
            Buffer buffer = new Buffer();
            BufferedSinkJsonWriter bufferedSinkJsonWriter = new BufferedSinkJsonWriter(buffer, null);
            Set<Map.Entry<String, ? extends Upload>> setEntrySet = uploads.entrySet();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(setEntrySet, 10));
            int i = 0;
            for (Object obj : setEntrySet) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                arrayList.add(TuplesKt.to(String.valueOf(i), CollectionsKt__CollectionsJVMKt.listOf(((Map.Entry) obj).getKey())));
                i = i2;
            }
            JsonWriters.writeAny(bufferedSinkJsonWriter, MapsKt__MapsKt.toMap(arrayList));
            return buffer.readByteString();
        }

        private final <D extends Operation.Data> Map<String, String> composeGetParams(Operation<D> operation, CustomScalarAdapters customScalarAdapters, boolean autoPersistQueries, boolean sendDocument) throws IOException {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("operationName", operation.name());
            Buffer buffer = new Buffer();
            FileUploadAwareJsonWriter fileUploadAwareJsonWriter = new FileUploadAwareJsonWriter(new BufferedSinkJsonWriter(buffer, null));
            fileUploadAwareJsonWriter.beginObject();
            operation.serializeVariables(fileUploadAwareJsonWriter, customScalarAdapters);
            fileUploadAwareJsonWriter.endObject();
            if (!fileUploadAwareJsonWriter.collectedUploads().isEmpty()) {
                throw new IllegalStateException("FileUpload and Http GET are not supported at the same time".toString());
            }
            linkedHashMap.put("variables", buffer.readUtf8());
            if (sendDocument) {
                linkedHashMap.put(SearchIntents.EXTRA_QUERY, operation.document());
            }
            if (autoPersistQueries) {
                Buffer buffer2 = new Buffer();
                BufferedSinkJsonWriter bufferedSinkJsonWriter = new BufferedSinkJsonWriter(buffer2, null);
                bufferedSinkJsonWriter.beginObject();
                bufferedSinkJsonWriter.name("persistedQuery");
                bufferedSinkJsonWriter.beginObject();
                bufferedSinkJsonWriter.name("version").value(1);
                bufferedSinkJsonWriter.name("sha256Hash").value(operation.id());
                bufferedSinkJsonWriter.endObject();
                bufferedSinkJsonWriter.endObject();
                linkedHashMap.put("extensions", buffer2.readUtf8());
            }
            return linkedHashMap;
        }

        public final String appendQueryParameters(String str, Map<String, String> parameters) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            boolean zContains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "?", false, 2, (Object) null);
            Iterator<T> it = parameters.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (zContains$default) {
                    sb.append(Typography.amp);
                } else {
                    sb.append('?');
                    zContains$default = true;
                }
                sb.append(UrlEncodeKt.urlEncode$default((String) entry.getKey(), false, 1, null));
                sb.append('=');
                sb.append(UrlEncodeKt.urlEncode$default((String) entry.getValue(), false, 1, null));
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            return string;
        }

        /* JADX WARN: Type inference failed for: r9v1, types: [T, java.util.Map] */
        public final <D extends Operation.Data> HttpBody buildPostBody(Operation<D> operation, CustomScalarAdapters customScalarAdapters, boolean autoPersistQueries, String query) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Buffer buffer = new Buffer();
            objectRef.element = DefaultHttpRequestComposer.INSTANCE.composePostParams(new BufferedSinkJsonWriter(buffer, null), operation, customScalarAdapters, autoPersistQueries, query);
            final ByteString byteString = buffer.readByteString();
            if (((Map) objectRef.element).isEmpty()) {
                return new HttpBody() { // from class: com.apollographql.apollo3.api.http.DefaultHttpRequestComposer$Companion$buildPostBody$1
                    private final long contentLength;
                    private final String contentType = "application/json";

                    {
                        this.contentLength = this.$operationByteString.size();
                    }

                    @Override // com.apollographql.apollo3.api.http.HttpBody
                    public String getContentType() {
                        return this.contentType;
                    }

                    @Override // com.apollographql.apollo3.api.http.HttpBody
                    public long getContentLength() {
                        return this.contentLength;
                    }

                    @Override // com.apollographql.apollo3.api.http.HttpBody
                    public void writeTo(BufferedSink bufferedSink) throws IOException {
                        Intrinsics.checkNotNullParameter(bufferedSink, "bufferedSink");
                        bufferedSink.write(this.$operationByteString);
                    }
                };
            }
            return new HttpBody() { // from class: com.apollographql.apollo3.api.http.DefaultHttpRequestComposer$Companion$buildPostBody$2
                private final String boundary;
                private final long contentLength;
                private final String contentType;

                {
                    UUID uuidRandomUUID = UUID.randomUUID();
                    Intrinsics.checkNotNullExpressionValue(uuidRandomUUID, "randomUUID()");
                    String string = uuidRandomUUID.toString();
                    Intrinsics.checkNotNullExpressionValue(string, "uuid4().toString()");
                    this.boundary = string;
                    this.contentType = Intrinsics.stringPlus("multipart/form-data; boundary=", string);
                    this.contentLength = -1L;
                }

                @Override // com.apollographql.apollo3.api.http.HttpBody
                public String getContentType() {
                    return this.contentType;
                }

                @Override // com.apollographql.apollo3.api.http.HttpBody
                public long getContentLength() {
                    return this.contentLength;
                }

                @Override // com.apollographql.apollo3.api.http.HttpBody
                public void writeTo(BufferedSink bufferedSink) throws IOException {
                    Intrinsics.checkNotNullParameter(bufferedSink, "bufferedSink");
                    bufferedSink.writeUtf8("--" + this.boundary + "\r\n");
                    bufferedSink.writeUtf8("Content-Disposition: form-data; name=\"operations\"\r\n");
                    bufferedSink.writeUtf8("Content-Type: application/json\r\n");
                    bufferedSink.writeUtf8("Content-Length: " + byteString.size() + "\r\n");
                    bufferedSink.writeUtf8("\r\n");
                    bufferedSink.write(byteString);
                    ByteString byteStringBuildUploadMap = DefaultHttpRequestComposer.INSTANCE.buildUploadMap(objectRef.element);
                    bufferedSink.writeUtf8("\r\n--" + this.boundary + "\r\n");
                    bufferedSink.writeUtf8("Content-Disposition: form-data; name=\"map\"\r\n");
                    bufferedSink.writeUtf8("Content-Type: application/json\r\n");
                    bufferedSink.writeUtf8("Content-Length: " + byteStringBuildUploadMap.size() + "\r\n");
                    bufferedSink.writeUtf8("\r\n");
                    bufferedSink.write(byteStringBuildUploadMap);
                    int i = 0;
                    for (Object obj : objectRef.element.values()) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                        }
                        Upload upload = (Upload) obj;
                        bufferedSink.writeUtf8("\r\n--" + this.boundary + "\r\n");
                        bufferedSink.writeUtf8("Content-Disposition: form-data; name=\"" + i + '\"');
                        if (upload.getFileName() != null) {
                            bufferedSink.writeUtf8("; filename=\"" + ((Object) upload.getFileName()) + '\"');
                        }
                        bufferedSink.writeUtf8("\r\n");
                        bufferedSink.writeUtf8("Content-Type: " + upload.getContentType() + "\r\n");
                        long contentLength = upload.getContentLength();
                        if (contentLength != -1) {
                            bufferedSink.writeUtf8("Content-Length: " + contentLength + "\r\n");
                        }
                        bufferedSink.writeUtf8("\r\n");
                        upload.writeTo(bufferedSink);
                        i = i2;
                    }
                    bufferedSink.writeUtf8("\r\n--" + this.boundary + "--\r\n");
                }
            };
        }

        public final <D extends Operation.Data> Map<String, Object> composePayload(ApolloRequest<D> apolloRequest) throws IOException {
            Intrinsics.checkNotNullParameter(apolloRequest, "apolloRequest");
            Operation<D> operation = apolloRequest.getOperation();
            Boolean sendApqExtensions = apolloRequest.getSendApqExtensions();
            boolean zBooleanValue = sendApqExtensions == null ? false : sendApqExtensions.booleanValue();
            Boolean sendDocument = apolloRequest.getSendDocument();
            boolean zBooleanValue2 = sendDocument == null ? true : sendDocument.booleanValue();
            CustomScalarAdapters customScalarAdapters = (CustomScalarAdapters) apolloRequest.getExecutionContext().get(CustomScalarAdapters.INSTANCE);
            if (customScalarAdapters == null) {
                throw new IllegalStateException("Cannot find a ResponseAdapterCache".toString());
            }
            String strDocument = zBooleanValue2 ? operation.document() : null;
            MapJsonWriter mapJsonWriter = new MapJsonWriter();
            DefaultHttpRequestComposer.INSTANCE.composePostParams(mapJsonWriter, operation, customScalarAdapters, zBooleanValue, strDocument);
            Object objRoot = mapJsonWriter.root();
            if (objRoot != null) {
                return (Map) objRoot;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
        }
    }
}
