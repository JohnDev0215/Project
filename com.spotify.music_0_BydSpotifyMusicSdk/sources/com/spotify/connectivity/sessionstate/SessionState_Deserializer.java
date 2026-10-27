package com.spotify.connectivity.sessionstate;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.spotify.authentication.authtriggerserviceapi.AuthLogoutReason;
import p.q71;
import p.to1;

/* JADX INFO: loaded from: classes.dex */
public final class SessionState_Deserializer extends StdDeserializer<SessionState> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: renamed from: com.spotify.connectivity.sessionstate.SessionState_Deserializer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = iArr;
            try {
                iArr[JsonToken.m.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public SessionState_Deserializer() {
        super(SessionState.class);
    }

    public boolean _deserializeboolean(q71 q71Var, DeserializationContext deserializationContext) {
        return ((Boolean) to1.a(Boolean.TYPE, "boolean").deserialize(q71Var, deserializationContext)).booleanValue();
    }

    public int _deserializeint(q71 q71Var, DeserializationContext deserializationContext) {
        return ((Integer) to1.a(Integer.TYPE, "int").deserialize(q71Var, deserializationContext)).intValue();
    }

    public AuthLogoutReason deserializeAuthLogoutReasonEnum(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.X() == JsonToken.s) {
            return null;
        }
        if (q71Var.X() != JsonToken.o) {
            deserializationContext.p(q71Var, AuthLogoutReason.class);
            throw null;
        }
        String strN0 = q71Var.N0();
        strN0.getClass();
        return AuthLogoutReason.valueOf(strN0);
    }

    public SessionState deserializeSessionState(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.X() == JsonToken.s) {
            return null;
        }
        boolean z_deserializeboolean = false;
        boolean z_deserializeboolean2 = false;
        boolean z_deserializeboolean3 = false;
        int i_deserializeint = 0;
        boolean z_deserializeboolean4 = false;
        boolean z_deserializeboolean5 = false;
        String strDeserializeString = null;
        AuthLogoutReason authLogoutReasonDeserializeAuthLogoutReasonEnum = null;
        String strDeserializeString2 = null;
        String strDeserializeString3 = null;
        String strDeserializeString4 = null;
        while (q71Var.V0() != JsonToken.e) {
            if (q71Var.X().ordinal() == 5) {
                String strJ = q71Var.J();
                strJ.getClass();
                switch (strJ) {
                    case "logged_in":
                        q71Var.W0();
                        z_deserializeboolean = _deserializeboolean(q71Var, deserializationContext);
                        break;
                    case "logging_out":
                        q71Var.W0();
                        z_deserializeboolean3 = _deserializeboolean(q71Var, deserializationContext);
                        break;
                    case "can_stream":
                        q71Var.W0();
                        z_deserializeboolean5 = _deserializeboolean(q71Var, deserializationContext);
                        break;
                    case "current_account_type":
                        q71Var.W0();
                        i_deserializeint = _deserializeint(q71Var, deserializationContext);
                        break;
                    case "connected":
                        q71Var.W0();
                        z_deserializeboolean4 = _deserializeboolean(q71Var, deserializationContext);
                        break;
                    case "logout_reason":
                        q71Var.W0();
                        authLogoutReasonDeserializeAuthLogoutReasonEnum = deserializeAuthLogoutReasonEnum(q71Var, deserializationContext);
                        break;
                    case "logging_in":
                        q71Var.W0();
                        z_deserializeboolean2 = _deserializeboolean(q71Var, deserializationContext);
                        break;
                    case "product_type":
                        q71Var.W0();
                        strDeserializeString4 = deserializeString(q71Var, deserializationContext);
                        break;
                    case "current_user":
                        q71Var.W0();
                        strDeserializeString = deserializeString(q71Var, deserializationContext);
                        break;
                    case "country_code":
                        q71Var.W0();
                        strDeserializeString2 = deserializeString(q71Var, deserializationContext);
                        break;
                    case "payment_state":
                        q71Var.W0();
                        strDeserializeString3 = deserializeString(q71Var, deserializationContext);
                        break;
                    default:
                        q71Var.W0();
                        q71Var.X0();
                        break;
                }
            }
        }
        return SessionState.create(strDeserializeString, z_deserializeboolean, z_deserializeboolean2, z_deserializeboolean3, i_deserializeint, authLogoutReasonDeserializeAuthLogoutReasonEnum, strDeserializeString2, z_deserializeboolean4, z_deserializeboolean5, strDeserializeString3, strDeserializeString4);
    }

    public String deserializeString(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.X() == JsonToken.s) {
            return null;
        }
        return _parseString(q71Var, deserializationContext);
    }

    @Override // p.h71
    public boolean isCachable() {
        return true;
    }

    @Override // p.h71
    public SessionState deserialize(q71 q71Var, DeserializationContext deserializationContext) throws JsonMappingException {
        try {
            return deserializeSessionState(q71Var, deserializationContext);
        } catch (RuntimeException e) {
            e = e;
            while (e.getCause() != null) {
                e = e.getCause();
            }
            String message = e.getMessage();
            deserializationContext.getClass();
            throw new JsonMappingException(null, message, e);
        }
    }
}
