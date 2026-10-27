package com.spotify.player.model;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.PlaybackQuality;
import com.spotify.player.model.PlayerOptions;
import com.spotify.player.model.PlayerState;
import com.spotify.player.model.Restrictions;
import com.spotify.webapi.service.models.Search;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import p.q71;
import p.to1;

/* JADX INFO: loaded from: classes.dex */
public final class PlayerState_Deserializer extends StdDeserializer<PlayerState> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: renamed from: com.spotify.player.model.PlayerState_Deserializer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static {
            try {
                new int[JsonToken.values().length][JsonToken.m.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public PlayerState_Deserializer() {
        super(PlayerState.class);
    }

    public static BitrateLevel a(q71 q71Var, DeserializationContext deserializationContext) {
        String strN0;
        if (q71Var.X() == JsonToken.s) {
            return null;
        }
        if (q71Var.X() != JsonToken.o) {
            deserializationContext.p(q71Var, BitrateLevel.class);
            throw null;
        }
        strN0 = q71Var.N0();
        strN0.getClass();
        switch (strN0) {
            case "very_high":
                return BitrateLevel.m;
            case "normal":
                return BitrateLevel.k;
            case "low":
                return BitrateLevel.e;
            case "hifi":
                return BitrateLevel.n;
            case "high":
                return BitrateLevel.l;
            case "hifi_24":
                return BitrateLevel.o;
            case "unknown_bitrate_level":
                return BitrateLevel.c;
            default:
                return BitrateLevel.valueOf(strN0);
        }
    }

    public final boolean _deserializeboolean(q71 q71Var, DeserializationContext deserializationContext) {
        return ((Boolean) to1.a(Boolean.TYPE, "boolean").deserialize(q71Var, deserializationContext)).booleanValue();
    }

    public final ContextTrack b(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.X() == JsonToken.s) {
            return null;
        }
        ContextTrack.Builder builderA = ContextTrack.a("");
        while (q71Var.V0() != JsonToken.e) {
            if (q71Var.X().ordinal() == 5) {
                String strJ = q71Var.J();
                strJ.getClass();
                switch (strJ) {
                    case "provider":
                        q71Var.W0();
                        builderA.c(deserializeString(q71Var, deserializationContext));
                        break;
                    case "metadata":
                        q71Var.W0();
                        builderA.b(d(q71Var, deserializationContext));
                        break;
                    case "uid":
                        q71Var.W0();
                        builderA.d(deserializeString(q71Var, deserializationContext));
                        break;
                    case "uri":
                        q71Var.W0();
                        builderA.e(deserializeString(q71Var, deserializationContext));
                        break;
                    default:
                        q71Var.W0();
                        q71Var.X0();
                        break;
                }
            }
        }
        return builderA.a();
    }

    public final LinkedList c(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.X() == JsonToken.s) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        while (q71Var.V0() != JsonToken.l) {
            linkedList.add(b(q71Var, deserializationContext));
        }
        return linkedList;
    }

    public final HashMap d(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.X() == JsonToken.s) {
            return null;
        }
        HashMap map = new HashMap();
        while (q71Var.V0() != JsonToken.e) {
            String strDeserializeString = deserializeString(q71Var, deserializationContext);
            q71Var.W0();
            map.put(strDeserializeString, deserializeString(q71Var, deserializationContext));
        }
        return map;
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) throws JsonMappingException {
        try {
            return e(q71Var, deserializationContext);
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

    public final String deserializeString(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.X() == JsonToken.s) {
            return null;
        }
        return _parseString(q71Var, deserializationContext);
    }

    public final PlayerState e(q71 q71Var, DeserializationContext deserializationContext) {
        AudioStream audioStreamValueOf;
        PlayerOptions playerOptionsA;
        PlayOrigin playOriginA;
        AutoValue_ContextIndex autoValue_ContextIndex;
        Suppressions suppressionsA;
        PlaybackQuality playbackQualityB;
        String strN0;
        HiFiStatus hiFiStatusValueOf;
        String strN02;
        BitrateStrategy bitrateStrategyValueOf;
        LinkedList linkedList;
        if (q71Var.X() == JsonToken.s) {
            return null;
        }
        PlayerState.Builder builderB = PlayerState.b();
        while (q71Var.V0() != JsonToken.e) {
            if (q71Var.X().ordinal() == 5) {
                String strJ = q71Var.J();
                strJ.getClass();
                switch (strJ) {
                    case "duration":
                        q71Var.W0();
                        builderB.g((Long) to1.a(Long.class, "java.lang.Long").deserialize(q71Var, deserializationContext));
                        break;
                    case "audio_stream":
                        q71Var.W0();
                        if (q71Var.X() == JsonToken.s) {
                            audioStreamValueOf = null;
                        } else {
                            if (q71Var.X() != JsonToken.o) {
                                deserializationContext.p(q71Var, AudioStream.class);
                                throw null;
                            }
                            String strN03 = q71Var.N0();
                            strN03.getClass();
                            audioStreamValueOf = !strN03.equals("alarm") ? !strN03.equals("default") ? AudioStream.valueOf(strN03) : AudioStream.c : AudioStream.e;
                        }
                        builderB.a(audioStreamValueOf);
                        break;
                    case "future":
                        q71Var.W0();
                        builderB.m(c(q71Var, deserializationContext));
                        break;
                    case "is_buffering":
                        q71Var.W0();
                        builderB.i(_deserializeboolean(q71Var, deserializationContext));
                        break;
                    case "options":
                        q71Var.W0();
                        if (q71Var.X() == JsonToken.s) {
                            playerOptionsA = null;
                        } else {
                            PlayerOptions.Builder builderA = PlayerOptions.a();
                            while (q71Var.V0() != JsonToken.e) {
                                if (q71Var.X().ordinal() == 5) {
                                    String strJ2 = q71Var.J();
                                    strJ2.getClass();
                                    switch (strJ2) {
                                        case "repeating_context":
                                            q71Var.W0();
                                            builderA.c(_deserializeboolean(q71Var, deserializationContext));
                                            break;
                                        case "shuffling_context":
                                            q71Var.W0();
                                            builderA.e(_deserializeboolean(q71Var, deserializationContext));
                                            break;
                                        case "repeating_track":
                                            q71Var.W0();
                                            builderA.d(_deserializeboolean(q71Var, deserializationContext));
                                            break;
                                        case "playback_speed":
                                            q71Var.W0();
                                            builderA.b(((Float) to1.a(Float.TYPE, "float").deserialize(q71Var, deserializationContext)).floatValue());
                                            break;
                                        default:
                                            q71Var.W0();
                                            q71Var.X0();
                                            break;
                                    }
                                }
                            }
                            playerOptionsA = builderA.a();
                        }
                        builderB.n(playerOptionsA);
                        break;
                    case "restrictions":
                        q71Var.W0();
                        builderB.w(f(q71Var, deserializationContext));
                        break;
                    case "context_metadata":
                        q71Var.W0();
                        builderB.c(d(q71Var, deserializationContext));
                        break;
                    case "play_origin":
                        q71Var.W0();
                        if (q71Var.X() == JsonToken.s) {
                            playOriginA = null;
                        } else {
                            PlayOrigin.Builder builderA2 = PlayOrigin.a("");
                            while (q71Var.V0() != JsonToken.e) {
                                if (q71Var.X().ordinal() == 5) {
                                    String strJ3 = q71Var.J();
                                    strJ3.getClass();
                                    switch (strJ3) {
                                        case "feature_identifier":
                                            q71Var.W0();
                                            builderA2.e(deserializeString(q71Var, deserializationContext));
                                            break;
                                        case "device_identifier":
                                            q71Var.W0();
                                            builderA2.b(deserializeString(q71Var, deserializationContext));
                                            break;
                                        case "feature_version":
                                            q71Var.W0();
                                            builderA2.f(deserializeString(q71Var, deserializationContext));
                                            break;
                                        case "feature_classes":
                                            q71Var.W0();
                                            builderA2.d(g(q71Var, deserializationContext));
                                            break;
                                        case "restriction_identifier":
                                            q71Var.W0();
                                            builderA2.h(deserializeString(q71Var, deserializationContext));
                                            break;
                                        case "external_referrer":
                                            q71Var.W0();
                                            builderA2.c(deserializeString(q71Var, deserializationContext));
                                            break;
                                        case "referrer_identifier":
                                            q71Var.W0();
                                            builderA2.g(deserializeString(q71Var, deserializationContext));
                                            break;
                                        case "view_uri":
                                            q71Var.W0();
                                            builderA2.i(deserializeString(q71Var, deserializationContext));
                                            break;
                                        default:
                                            q71Var.W0();
                                            q71Var.X0();
                                            break;
                                    }
                                }
                            }
                            playOriginA = builderA2.a();
                        }
                        builderB.p(playOriginA);
                        break;
                    case "is_paused":
                        q71Var.W0();
                        builderB.j(_deserializeboolean(q71Var, deserializationContext));
                        break;
                    case "is_system_initiated":
                        q71Var.W0();
                        builderB.l(_deserializeboolean(q71Var, deserializationContext));
                        break;
                    case "context_uri":
                        q71Var.W0();
                        builderB.e(deserializeString(q71Var, deserializationContext));
                        break;
                    case "context_url":
                        q71Var.W0();
                        builderB.f(deserializeString(q71Var, deserializationContext));
                        break;
                    case "timestamp":
                        q71Var.W0();
                        builderB.A(((Long) to1.a(Long.TYPE, "long").deserialize(q71Var, deserializationContext)).longValue());
                        break;
                    case "index":
                        q71Var.W0();
                        if (q71Var.X() == JsonToken.s) {
                            autoValue_ContextIndex = null;
                        } else {
                            long jLongValue = 0;
                            long jLongValue2 = 0;
                            while (q71Var.V0() != JsonToken.e) {
                                if (q71Var.X().ordinal() == 5) {
                                    String strJ4 = q71Var.J();
                                    strJ4.getClass();
                                    if (strJ4.equals("page")) {
                                        q71Var.W0();
                                        jLongValue = ((Long) to1.a(Long.TYPE, "long").deserialize(q71Var, deserializationContext)).longValue();
                                    } else if (strJ4.equals(Search.Type.TRACK)) {
                                        q71Var.W0();
                                        jLongValue2 = ((Long) to1.a(Long.TYPE, "long").deserialize(q71Var, deserializationContext)).longValue();
                                    } else {
                                        q71Var.W0();
                                        q71Var.X0();
                                    }
                                }
                            }
                            autoValue_ContextIndex = new AutoValue_ContextIndex(jLongValue, jLongValue2);
                        }
                        builderB.h(autoValue_ContextIndex);
                        break;
                    case "track":
                        q71Var.W0();
                        builderB.B(b(q71Var, deserializationContext));
                        break;
                    case "queue_revision":
                        q71Var.W0();
                        builderB.v(deserializeString(q71Var, deserializationContext));
                        break;
                    case "suppressions":
                        q71Var.W0();
                        if (q71Var.X() == JsonToken.s) {
                            suppressionsA = null;
                        } else {
                            HashSet hashSetG = null;
                            while (q71Var.V0() != JsonToken.e) {
                                if (q71Var.X().ordinal() == 5) {
                                    String strJ5 = q71Var.J();
                                    strJ5.getClass();
                                    if (strJ5.equals("providers")) {
                                        q71Var.W0();
                                        hashSetG = g(q71Var, deserializationContext);
                                    } else {
                                        q71Var.W0();
                                        q71Var.X0();
                                    }
                                }
                            }
                            suppressionsA = Suppressions.a(hashSetG);
                        }
                        builderB.z(suppressionsA);
                        break;
                    case "is_playing":
                        q71Var.W0();
                        builderB.k(_deserializeboolean(q71Var, deserializationContext));
                        break;
                    case "reverse":
                        q71Var.W0();
                        builderB.u(c(q71Var, deserializationContext));
                        break;
                    case "position_as_of_timestamp":
                        q71Var.W0();
                        builderB.t((Long) to1.a(Long.class, "java.lang.Long").deserialize(q71Var, deserializationContext));
                        break;
                    case "playback_speed":
                        q71Var.W0();
                        builderB.s((Double) to1.a(Double.class, "java.lang.Double").deserialize(q71Var, deserializationContext));
                        break;
                    case "session_id":
                        q71Var.W0();
                        builderB.x(deserializeString(q71Var, deserializationContext));
                        break;
                    case "playback_id":
                        q71Var.W0();
                        builderB.q(deserializeString(q71Var, deserializationContext));
                        break;
                    case "playback_quality":
                        q71Var.W0();
                        if (q71Var.X() == JsonToken.s) {
                            playbackQualityB = null;
                        } else {
                            PlaybackQuality.Builder builderB2 = PlaybackQuality.b();
                            while (q71Var.V0() != JsonToken.e) {
                                if (q71Var.X().ordinal() == 5) {
                                    String strJ6 = q71Var.J();
                                    strJ6.getClass();
                                    switch (strJ6) {
                                        case "target_bitrate_available":
                                            q71Var.W0();
                                            builderB2.e(_deserializeboolean(q71Var, deserializationContext));
                                            break;
                                        case "hifi_status":
                                            q71Var.W0();
                                            if (q71Var.X() == JsonToken.s) {
                                                hiFiStatusValueOf = null;
                                            } else {
                                                if (q71Var.X() != JsonToken.o) {
                                                    deserializationContext.p(q71Var, HiFiStatus.class);
                                                    throw null;
                                                }
                                                strN0 = q71Var.N0();
                                                strN0.getClass();
                                                switch (strN0) {
                                                    case "on":
                                                        hiFiStatusValueOf = HiFiStatus.k;
                                                        break;
                                                    case "off":
                                                        hiFiStatusValueOf = HiFiStatus.e;
                                                        break;
                                                    case "none":
                                                        hiFiStatusValueOf = HiFiStatus.c;
                                                        break;
                                                    default:
                                                        hiFiStatusValueOf = HiFiStatus.valueOf(strN0);
                                                        break;
                                                }
                                            }
                                            builderB2.c(hiFiStatusValueOf);
                                            break;
                                        case "target_bitrate_level":
                                            q71Var.W0();
                                            builderB2.f(a(q71Var, deserializationContext));
                                            break;
                                        case "strategy":
                                            q71Var.W0();
                                            if (q71Var.X() == JsonToken.s) {
                                                bitrateStrategyValueOf = null;
                                            } else {
                                                if (q71Var.X() != JsonToken.o) {
                                                    deserializationContext.p(q71Var, BitrateStrategy.class);
                                                    throw null;
                                                }
                                                strN02 = q71Var.N0();
                                                strN02.getClass();
                                                switch (strN02) {
                                                    case "unknown_strategy":
                                                        bitrateStrategyValueOf = BitrateStrategy.c;
                                                        break;
                                                    case "offlined_file":
                                                        bitrateStrategyValueOf = BitrateStrategy.l;
                                                        break;
                                                    case "best_matching":
                                                        bitrateStrategyValueOf = BitrateStrategy.e;
                                                        break;
                                                    case "cached_file":
                                                        bitrateStrategyValueOf = BitrateStrategy.m;
                                                        break;
                                                    case "local_file":
                                                        bitrateStrategyValueOf = BitrateStrategy.n;
                                                        break;
                                                    case "backend_advised":
                                                        bitrateStrategyValueOf = BitrateStrategy.k;
                                                        break;
                                                    default:
                                                        bitrateStrategyValueOf = BitrateStrategy.valueOf(strN02);
                                                        break;
                                                }
                                            }
                                            builderB2.d(bitrateStrategyValueOf);
                                            break;
                                        case "bitrate_level":
                                            q71Var.W0();
                                            builderB2.a(a(q71Var, deserializationContext));
                                            break;
                                        default:
                                            q71Var.W0();
                                            q71Var.X0();
                                            break;
                                    }
                                }
                            }
                            playbackQualityB = builderB2.b();
                        }
                        builderB.r(playbackQualityB);
                        break;
                    case "page_metadata":
                        q71Var.W0();
                        builderB.o(d(q71Var, deserializationContext));
                        break;
                    case "context_restrictions":
                        q71Var.W0();
                        builderB.d(f(q71Var, deserializationContext));
                        break;
                    case "signals":
                        q71Var.W0();
                        if (q71Var.X() == JsonToken.s) {
                            linkedList = null;
                        } else {
                            linkedList = new LinkedList();
                            while (q71Var.V0() != JsonToken.l) {
                                linkedList.add(deserializeString(q71Var, deserializationContext));
                            }
                        }
                        builderB.y(linkedList);
                        break;
                    default:
                        q71Var.W0();
                        q71Var.X0();
                        break;
                }
            }
        }
        return builderB.b();
    }

    public final Restrictions f(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.X() == JsonToken.s) {
            return null;
        }
        Restrictions.Builder builderA = Restrictions.a();
        while (q71Var.V0() != JsonToken.e) {
            if (q71Var.X().ordinal() == 5) {
                String strJ = q71Var.J();
                strJ.getClass();
                switch (strJ) {
                    case "disallow_skipping_next_reasons":
                        q71Var.W0();
                        builderA.r(g(q71Var, deserializationContext));
                        break;
                    case "disallow_toggling_repeat_context_reasons":
                        q71Var.W0();
                        builderA.t(g(q71Var, deserializationContext));
                        break;
                    case "disallow_set_queue_reasons":
                        q71Var.W0();
                        builderA.p(g(q71Var, deserializationContext));
                        break;
                    case "disallow_toggling_shuffle_reasons":
                        q71Var.W0();
                        builderA.v(g(q71Var, deserializationContext));
                        break;
                    case "disallow_inserting_into_next_tracks_reasons":
                        q71Var.W0();
                        builderA.d(g(q71Var, deserializationContext));
                        break;
                    case "disallow_peeking_next_reasons":
                        q71Var.W0();
                        builderA.g(g(q71Var, deserializationContext));
                        break;
                    case "disallow_inserting_into_context_tracks_reasons":
                        q71Var.W0();
                        builderA.c(g(q71Var, deserializationContext));
                        break;
                    case "disallow_remote_control_reasons":
                        q71Var.W0();
                        builderA.i(g(q71Var, deserializationContext));
                        break;
                    case "disallow_interrupting_playback_reasons":
                        q71Var.W0();
                        builderA.e(g(q71Var, deserializationContext));
                        break;
                    case "disallow_reordering_in_next_tracks_reasons":
                        q71Var.W0();
                        builderA.m(g(q71Var, deserializationContext));
                        break;
                    case "disallow_pausing_reasons":
                        q71Var.W0();
                        builderA.f(g(q71Var, deserializationContext));
                        break;
                    case "disallow_resuming_reasons":
                        q71Var.W0();
                        builderA.n(g(q71Var, deserializationContext));
                        break;
                    case "disallow_removing_from_context_tracks_reasons":
                        q71Var.W0();
                        builderA.j(g(q71Var, deserializationContext));
                        break;
                    case "disallow_add_to_queue_reasons":
                        q71Var.W0();
                        builderA.b(g(q71Var, deserializationContext));
                        break;
                    case "disallow_transferring_playback_reasons":
                        q71Var.W0();
                        builderA.w(g(q71Var, deserializationContext));
                        break;
                    case "disallow_skipping_prev_reasons":
                        q71Var.W0();
                        builderA.s(g(q71Var, deserializationContext));
                        break;
                    case "disallow_toggling_repeat_track_reasons":
                        q71Var.W0();
                        builderA.u(g(q71Var, deserializationContext));
                        break;
                    case "disallow_setting_playback_speed_reasons":
                        q71Var.W0();
                        builderA.q(g(q71Var, deserializationContext));
                        break;
                    case "disallow_updating_context_reasons":
                        q71Var.W0();
                        builderA.x(g(q71Var, deserializationContext));
                        break;
                    case "disallow_removing_from_next_tracks_reasons":
                        q71Var.W0();
                        builderA.k(g(q71Var, deserializationContext));
                        break;
                    case "disallow_peeking_prev_reasons":
                        q71Var.W0();
                        builderA.h(g(q71Var, deserializationContext));
                        break;
                    case "disallow_seeking_reasons":
                        q71Var.W0();
                        builderA.o(g(q71Var, deserializationContext));
                        break;
                    case "disallow_reordering_in_context_tracks_reasons":
                        q71Var.W0();
                        builderA.l(g(q71Var, deserializationContext));
                        break;
                    default:
                        q71Var.W0();
                        q71Var.X0();
                        break;
                }
            }
        }
        return builderA.a();
    }

    public final HashSet g(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.X() == JsonToken.s) {
            return null;
        }
        HashSet hashSet = new HashSet();
        while (q71Var.V0() != JsonToken.l) {
            hashSet.add(deserializeString(q71Var, deserializationContext));
        }
        return hashSet;
    }

    @Override // p.h71
    public final boolean isCachable() {
        return true;
    }
}
