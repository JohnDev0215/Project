package com.apollographql.apollo3.api.http;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Pair;
import kotlin.collections.c;
import kotlin.jvm.internal.Ref$ObjectRef;
import okio.ByteString;
import p.cm;
import p.d70;
import p.e21;
import p.f60;
import p.fo;
import p.io;
import p.jo;
import p.lu;
import p.py0;
import p.u4;
import p.v02;
import p.v03;
import p.v81;
import p.x20;

/* JADX INFO: loaded from: classes.dex */
public final class a implements e21 {
    public final String a;
    public final String b;
    public final v81 c;
    public final /* synthetic */ Ref$ObjectRef d;
    public final /* synthetic */ ByteString e;

    public a(final Ref$ObjectRef ref$ObjectRef, ByteString byteString) {
        this.d = ref$ObjectRef;
        this.e = byteString;
        UUID uuidRandomUUID = UUID.randomUUID();
        v03.i(uuidRandomUUID, "randomUUID()");
        String string = uuidRandomUUID.toString();
        v03.i(string, "uuid4().toString()");
        this.a = string;
        this.b = "multipart/form-data; boundary=".concat(string);
        this.c = kotlin.a.c(new py0() { // from class: com.apollographql.apollo3.api.http.DefaultHttpRequestComposer$Companion$buildPostBody$2$contentLength$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() throws EOFException {
                x20 x20Var = new x20(new cm());
                v02 v02VarC = v03.c(x20Var);
                this.this$0.d(v02VarC);
                v02VarC.flush();
                long j = x20Var.e;
                Iterator<T> it = ref$ObjectRef.element.values().iterator();
                if (!it.hasNext()) {
                    return Long.valueOf(j);
                }
                u4.z(it.next());
                throw null;
            }
        });
    }

    @Override // p.e21
    public final void a(io ioVar) throws EOFException {
        d(ioVar);
    }

    @Override // p.e21
    public final String b() {
        return this.b;
    }

    @Override // p.e21
    public final long c() {
        return ((Number) this.c.getValue()).longValue();
    }

    public final void d(io ioVar) throws EOFException {
        StringBuilder sb = new StringBuilder("--");
        String str = this.a;
        sb.append(str);
        sb.append("\r\n");
        ioVar.s0(sb.toString());
        ioVar.s0("Content-Disposition: form-data; name=\"operations\"\r\n");
        ioVar.s0("Content-Type: application/json\r\n");
        StringBuilder sb2 = new StringBuilder("Content-Length: ");
        ByteString byteString = this.e;
        sb2.append(byteString.e());
        sb2.append("\r\n");
        ioVar.s0(sb2.toString());
        ioVar.s0("\r\n");
        ioVar.a0(byteString);
        int i = d70.b;
        Ref$ObjectRef ref$ObjectRef = this.d;
        Map map = (Map) ref$ObjectRef.element;
        fo foVar = new fo();
        jo joVar = new jo(foVar);
        Set setEntrySet = map.entrySet();
        ArrayList arrayList = new ArrayList(lu.W(setEntrySet));
        int i2 = 0;
        for (Object obj : setEntrySet) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                v03.P();
                throw null;
            }
            arrayList.add(new Pair(String.valueOf(i2), v03.B(((Map.Entry) obj).getKey())));
            i2 = i3;
        }
        f60.U(joVar, c.k0(arrayList));
        ByteString byteStringV = foVar.v(foVar.e);
        ioVar.s0("\r\n--" + str + "\r\n");
        ioVar.s0("Content-Disposition: form-data; name=\"map\"\r\n");
        ioVar.s0("Content-Type: application/json\r\n");
        ioVar.s0("Content-Length: " + byteStringV.e() + "\r\n");
        ioVar.s0("\r\n");
        ioVar.a0(byteStringV);
        Iterator it = ((Map) ref$ObjectRef.element).values().iterator();
        if (!it.hasNext()) {
            ioVar.s0("\r\n--" + str + "--\r\n");
            return;
        }
        u4.z(it.next());
        ioVar.s0("\r\n--" + str + "\r\n");
        ioVar.s0("Content-Disposition: form-data; name=\"0\"");
        throw null;
    }
}
