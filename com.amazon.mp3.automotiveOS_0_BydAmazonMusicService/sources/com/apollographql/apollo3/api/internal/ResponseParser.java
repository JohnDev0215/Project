package com.apollographql.apollo3.api.internal;

import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Error;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonReaders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResponseParser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u0002H\u0002J\f\u0010\u0006\u001a\u00020\u0004*\u00020\u0002H\u0002J\u0014\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003*\u00020\u0002H\u0002J\u0014\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003*\u00020\u0002H\u0002J\f\u0010\n\u001a\u00020\b*\u00020\u0002H\u0002J4\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012\"\b\b\u0000\u0010\f*\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00022\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0011\u001a\u00020\u0010¨\u0006\u0016"}, d2 = {"Lcom/apollographql/apollo3/api/internal/ResponseParser;", "", "Lcom/apollographql/apollo3/api/json/JsonReader;", "", "Lcom/apollographql/apollo3/api/Error;", "readErrors", "readError", "readPath", "Lcom/apollographql/apollo3/api/Error$Location;", "readErrorLocations", "readErrorLocation", "Lcom/apollographql/apollo3/api/Operation$Data;", "D", "jsonReader", "Lcom/apollographql/apollo3/api/Operation;", "operation", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/ApolloResponse;", "parse", "<init>", "()V", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class ResponseParser {
    public static final ResponseParser INSTANCE = new ResponseParser();

    /* JADX INFO: compiled from: ResponseParser.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JsonReader.Token.values().length];
            iArr[JsonReader.Token.NUMBER.ordinal()] = 1;
            iArr[JsonReader.Token.LONG.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ResponseParser() {
    }

    public final <D extends Operation.Data> ApolloResponse<D> parse(JsonReader jsonReader, Operation<D> operation, CustomScalarAdapters customScalarAdapters) throws Throwable {
        ApolloResponse<D> apolloResponseBuild;
        Intrinsics.checkNotNullParameter(jsonReader, "jsonReader");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Throwable th = null;
        try {
            jsonReader.beginObject();
            Operation.Data data = null;
            List<Error> errors = null;
            Map<String, ? extends Object> map = null;
            while (jsonReader.hasNext()) {
                String strNextName = jsonReader.nextName();
                int iHashCode = strNextName.hashCode();
                if (iHashCode != -1809421292) {
                    if (iHashCode != -1294635157) {
                        if (iHashCode == 3076010 && strNextName.equals("data")) {
                            data = (Operation.Data) Adapters.m2609nullable(operation.adapter()).fromJson(jsonReader, customScalarAdapters);
                        } else {
                            jsonReader.skipValue();
                        }
                    } else if (strNextName.equals(MAPWebViewEventHelper.KEY_ERRORS)) {
                        errors = INSTANCE.readErrors(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                } else if (strNextName.equals("extensions")) {
                    Object any = JsonReaders.readAny(jsonReader);
                    map = any instanceof Map ? (Map) any : null;
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            UUID uuidRandomUUID = UUID.randomUUID();
            Intrinsics.checkNotNullExpressionValue(uuidRandomUUID, "randomUUID()");
            apolloResponseBuild = new ApolloResponse.Builder(operation, uuidRandomUUID, data).errors(errors).extensions(map).build();
        } catch (Throwable th2) {
            th = th2;
            apolloResponseBuild = null;
        }
        try {
            jsonReader.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            } else {
                ExceptionsKt__ExceptionsKt.addSuppressed(th, th3);
            }
        }
        if (th != null) {
            throw th;
        }
        Intrinsics.checkNotNull(apolloResponseBuild);
        return apolloResponseBuild;
    }

    private final List<Error> readErrors(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonReader.Token.NULL) {
            jsonReader.nextNull();
            return CollectionsKt__CollectionsKt.emptyList();
        }
        jsonReader.beginArray();
        ArrayList arrayList = new ArrayList();
        while (jsonReader.hasNext()) {
            arrayList.add(readError(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.apollographql.apollo3.api.Error readError(com.apollographql.apollo3.api.json.JsonReader r10) throws java.io.IOException {
        /*
            r9 = this;
            r10.beginObject()
            java.lang.String r0 = ""
            r1 = 0
            r3 = r0
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = r6
        Lb:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L72
            java.lang.String r2 = r10.nextName()
            int r8 = r2.hashCode()
            switch(r8) {
                case -1809421292: goto L4c;
                case -1197189282: goto L3e;
                case 3433509: goto L30;
                case 954925063: goto L1d;
                default: goto L1c;
            }
        L1c:
            goto L63
        L1d:
            java.lang.String r8 = "message"
            boolean r8 = r2.equals(r8)
            if (r8 != 0) goto L26
            goto L63
        L26:
            java.lang.String r2 = r10.nextString()
            if (r2 != 0) goto L2e
            r3 = r0
            goto Lb
        L2e:
            r3 = r2
            goto Lb
        L30:
            java.lang.String r8 = "path"
            boolean r8 = r2.equals(r8)
            if (r8 != 0) goto L39
            goto L63
        L39:
            java.util.List r5 = r9.readPath(r10)
            goto Lb
        L3e:
            java.lang.String r8 = "locations"
            boolean r8 = r2.equals(r8)
            if (r8 != 0) goto L47
            goto L63
        L47:
            java.util.List r4 = r9.readErrorLocations(r10)
            goto Lb
        L4c:
            java.lang.String r8 = "extensions"
            boolean r8 = r2.equals(r8)
            if (r8 != 0) goto L55
            goto L63
        L55:
            java.lang.Object r2 = com.apollographql.apollo3.api.json.JsonReaders.readAny(r10)
            boolean r6 = r2 instanceof java.util.Map
            if (r6 == 0) goto L61
            java.util.Map r2 = (java.util.Map) r2
            r6 = r2
            goto Lb
        L61:
            r6 = r1
            goto Lb
        L63:
            if (r7 != 0) goto L6a
            java.util.LinkedHashMap r7 = new java.util.LinkedHashMap
            r7.<init>()
        L6a:
            java.lang.Object r8 = com.apollographql.apollo3.api.json.JsonReaders.readAny(r10)
            r7.put(r2, r8)
            goto Lb
        L72:
            r10.endObject()
            com.apollographql.apollo3.api.Error r10 = new com.apollographql.apollo3.api.Error
            r2 = r10
            r2.<init>(r3, r4, r5, r6, r7)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.internal.ResponseParser.readError(com.apollographql.apollo3.api.json.JsonReader):com.apollographql.apollo3.api.Error");
    }

    private final List<Object> readPath(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonReader.Token.NULL) {
            return (List) jsonReader.nextNull();
        }
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            int i = WhenMappings.$EnumSwitchMapping$0[jsonReader.peek().ordinal()];
            if (i == 1 || i == 2) {
                arrayList.add(Integer.valueOf(jsonReader.nextInt()));
            } else {
                String strNextString = jsonReader.nextString();
                Intrinsics.checkNotNull(strNextString);
                arrayList.add(strNextString);
            }
        }
        jsonReader.endArray();
        return arrayList;
    }

    private final List<Error.Location> readErrorLocations(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonReader.Token.NULL) {
            return (List) jsonReader.nextNull();
        }
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(readErrorLocation(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    private final Error.Location readErrorLocation(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        int iNextInt = -1;
        int iNextInt2 = -1;
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (Intrinsics.areEqual(strNextName, "line")) {
                iNextInt = jsonReader.nextInt();
            } else if (Intrinsics.areEqual(strNextName, "column")) {
                iNextInt2 = jsonReader.nextInt();
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return new Error.Location(iNextInt, iNextInt2);
    }
}
