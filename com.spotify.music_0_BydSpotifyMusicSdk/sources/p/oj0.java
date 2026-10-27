package p;

import com.spotify.eventsender.internalerrors.EventSenderInternalError;
import com.spotify.eventsender.internalerrors.proto.EventSenderInternalErrors;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class oj0 implements pj0, qj0, cj0 {
    public final LinkedHashMap a = new LinkedHashMap();
    public final Object b = new Object();
    public final LinkedHashMap c;

    public oj0() {
        EventSenderInternalError[] eventSenderInternalErrorArrValues = EventSenderInternalError.values();
        int iB = he1.B(eventSenderInternalErrorArrValues.length);
        LinkedHashMap linkedHashMap = new LinkedHashMap(iB < 16 ? 16 : iB);
        for (EventSenderInternalError eventSenderInternalError : eventSenderInternalErrorArrValues) {
            linkedHashMap.put(eventSenderInternalError.a(), eventSenderInternalError);
        }
        this.c = linkedHashMap;
    }

    public static void f(LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2) {
        for (Map.Entry entry : linkedHashMap2.entrySet()) {
            EventSenderInternalError eventSenderInternalError = (EventSenderInternalError) entry.getKey();
            nj0 nj0Var = (nj0) entry.getValue();
            Object nj0Var2 = linkedHashMap.get(eventSenderInternalError);
            if (nj0Var2 == null) {
                nj0Var2 = new nj0();
                linkedHashMap.put(eventSenderInternalError, nj0Var2);
            }
            nj0 nj0Var3 = (nj0) nj0Var2;
            nj0Var3.a += nj0Var.a;
            nj0Var3.b += nj0Var.b;
        }
    }

    @Override // p.cj0
    public final void a(Throwable th) {
        v03.j(th, "error");
        g(EventSenderInternalError.DISK_OP_READ_FAILED);
    }

    @Override // p.cj0
    public final String b() {
        return "ESInternalErrors";
    }

    @Override // p.cj0
    public final void c(FileInputStream fileInputStream) {
        EventSenderInternalErrors eventSenderInternalErrorsH = EventSenderInternalErrors.h(he1.K(fileInputStream));
        v03.i(eventSenderInternalErrorsH, "parseFrom(fis.readBytes())");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Map mapF = eventSenderInternalErrorsH.f();
        v03.i(mapF, "protoErrors.errorToCountsMapMap");
        for (Map.Entry entry : mapF.entrySet()) {
            String str = (String) entry.getKey();
            EventSenderInternalErrors.Counts counts = (EventSenderInternalErrors.Counts) entry.getValue();
            EventSenderInternalError eventSenderInternalError = (EventSenderInternalError) this.c.get(str);
            if (eventSenderInternalError != null) {
                linkedHashMap.put(eventSenderInternalError, new nj0(counts.i(), counts.h()));
            }
        }
        synchronized (this.b) {
            f(this.a, linkedHashMap);
        }
    }

    @Override // p.cj0
    public final void d(Throwable th) {
        v03.j(th, "error");
        g(EventSenderInternalError.DISK_OP_WRITE_FAILED);
    }

    @Override // p.cj0
    public final void e(FileOutputStream fileOutputStream) {
        LinkedHashMap linkedHashMapH = h(this.a);
        if (!linkedHashMapH.isEmpty()) {
            com.spotify.eventsender.internalerrors.proto.a aVarG = EventSenderInternalErrors.g();
            for (Map.Entry entry : linkedHashMapH.entrySet()) {
                EventSenderInternalError eventSenderInternalError = (EventSenderInternalError) entry.getKey();
                nj0 nj0Var = (nj0) entry.getValue();
                com.spotify.eventsender.internalerrors.proto.b bVarJ = EventSenderInternalErrors.Counts.j();
                bVarJ.e(nj0Var.a);
                bVarJ.d(nj0Var.b);
                aVarG.d(eventSenderInternalError.a(), (EventSenderInternalErrors.Counts) bVarJ.build());
            }
            com.google.protobuf.i iVarBuild = aVarG.build();
            v03.i(iVarBuild, "errorsBuilder.build()");
            ((EventSenderInternalErrors) iVarBuild).writeTo(fileOutputStream);
        }
    }

    public final void g(EventSenderInternalError eventSenderInternalError) {
        synchronized (this.b) {
            try {
                LinkedHashMap linkedHashMap = this.a;
                Object nj0Var = linkedHashMap.get(eventSenderInternalError);
                if (nj0Var == null) {
                    nj0Var = new nj0();
                    linkedHashMap.put(eventSenderInternalError, nj0Var);
                }
                ((nj0) nj0Var).a++;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final LinkedHashMap h(LinkedHashMap linkedHashMap) {
        LinkedHashMap linkedHashMap2;
        synchronized (this.b) {
            linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap2;
    }

    public final void i(Map map) {
        synchronized (this.b) {
            for (Map.Entry entry : map.entrySet()) {
                EventSenderInternalError eventSenderInternalError = (EventSenderInternalError) entry.getKey();
                long jLongValue = ((Number) entry.getValue()).longValue();
                nj0 nj0Var = (nj0) this.a.get(eventSenderInternalError);
                if (nj0Var != null) {
                    nj0Var.b = jLongValue;
                }
            }
        }
    }
}
