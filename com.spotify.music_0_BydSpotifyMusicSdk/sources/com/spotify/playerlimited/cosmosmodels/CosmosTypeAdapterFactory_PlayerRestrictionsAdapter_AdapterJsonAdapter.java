package com.spotify.playerlimited.cosmosmodels;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.Set;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTypeAdapterFactory_PlayerRestrictionsAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;

    public CosmosTypeAdapterFactory_PlayerRestrictionsAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("disallow_add_to_queue_reasons", "disallow_inserting_into_context_tracks_reasons", "disallow_inserting_into_next_tracks_reasons", "disallow_pausing_reasons", "disallow_peeking_next_reasons", "disallow_peeking_prev_reasons", "disallow_remote_control_reasons", "disallow_removing_from_context_tracks_reasons", "disallow_removing_from_next_tracks_reasons", "disallow_reordering_in_context_tracks_reasons", "disallow_reordering_in_next_tracks_reasons", "disallow_resuming_reasons", "disallow_seeking_reasons", "disallow_set_queue_reasons", "disallow_skipping_next_reasons", "disallow_skipping_prev_reasons", "disallow_toggling_repeat_context_reasons", "disallow_toggling_repeat_track_reasons", "disallow_toggling_shuffle_reasons", "disallow_transferring_playback_reasons", "disallow_updating_context_reasons");
        v03.i(bVarA, "of(\"disallow_add_to_queu…pdating_context_reasons\")");
        this.a = bVarA;
        f fVarF = oVar.f(zo2.j(Set.class, String.class), EmptySet.c, "disallowAddToQueueReasons");
        v03.i(fVarF, "moshi.adapter(Types.newP…sallowAddToQueueReasons\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Set set = null;
        Set set2 = null;
        Set set3 = null;
        Set set4 = null;
        Set set5 = null;
        Set set6 = null;
        Set set7 = null;
        Set set8 = null;
        Set set9 = null;
        Set set10 = null;
        Set set11 = null;
        Set set12 = null;
        Set set13 = null;
        Set set14 = null;
        Set set15 = null;
        Set set16 = null;
        Set set17 = null;
        Set set18 = null;
        Set set19 = null;
        Set set20 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        boolean z12 = false;
        boolean z13 = false;
        boolean z14 = false;
        boolean z15 = false;
        boolean z16 = false;
        boolean z17 = false;
        boolean z18 = false;
        boolean z19 = false;
        boolean z20 = false;
        boolean z21 = false;
        Set set21 = null;
        while (jsonReader.hasNext()) {
            Set set22 = set13;
            int iM0 = jsonReader.M0(this.a);
            Set set23 = set12;
            f fVar = this.b;
            switch (iM0) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    set13 = set22;
                    set12 = set23;
                    break;
                case 0:
                    set = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z = true;
                    set12 = set23;
                    break;
                case 1:
                    set21 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z2 = true;
                    set12 = set23;
                    break;
                case 2:
                    set2 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z3 = true;
                    set12 = set23;
                    break;
                case 3:
                    set3 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z4 = true;
                    set12 = set23;
                    break;
                case 4:
                    set4 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z5 = true;
                    set12 = set23;
                    break;
                case 5:
                    set5 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z6 = true;
                    set12 = set23;
                    break;
                case 6:
                    set6 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z7 = true;
                    set12 = set23;
                    break;
                case 7:
                    set7 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z8 = true;
                    set12 = set23;
                    break;
                case 8:
                    set8 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z9 = true;
                    set12 = set23;
                    break;
                case 9:
                    set9 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z10 = true;
                    set12 = set23;
                    break;
                case 10:
                    set10 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z11 = true;
                    set12 = set23;
                    break;
                case 11:
                    set11 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z12 = true;
                    set12 = set23;
                    break;
                case 12:
                    set12 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z13 = true;
                    break;
                case 13:
                    set13 = (Set) fVar.fromJson(jsonReader);
                    z14 = true;
                    set12 = set23;
                    break;
                case 14:
                    set14 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z15 = true;
                    set12 = set23;
                    break;
                case 15:
                    set15 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z16 = true;
                    set12 = set23;
                    break;
                case 16:
                    set16 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z17 = true;
                    set12 = set23;
                    break;
                case 17:
                    set17 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z18 = true;
                    set12 = set23;
                    break;
                case 18:
                    set18 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z19 = true;
                    set12 = set23;
                    break;
                case 19:
                    set19 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z20 = true;
                    set12 = set23;
                    break;
                case 20:
                    set20 = (Set) fVar.fromJson(jsonReader);
                    set13 = set22;
                    z21 = true;
                    set12 = set23;
                    break;
                default:
                    set13 = set22;
                    set12 = set23;
                    break;
            }
        }
        Set set24 = set12;
        Set set25 = set13;
        jsonReader.q();
        CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.u = set;
        }
        if (z2) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.n = set21;
        }
        if (z3) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.m = set2;
        }
        if (z4) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.e = set3;
        }
        if (z5) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.b = set4;
        }
        if (z6) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.a = set5;
        }
        if (z7) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.l = set6;
        }
        if (z8) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.r = set7;
        }
        if (z9) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.q = set8;
        }
        if (z10) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.f62p = set9;
        }
        if (z11) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.o = set10;
        }
        if (z12) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.f = set11;
        }
        if (z13) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.j = set24;
        }
        if (z14) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.t = set25;
        }
        if (z15) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.d = set14;
        }
        if (z16) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.c = set15;
        }
        if (z17) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.g = set16;
        }
        if (z18) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.h = set17;
        }
        if (z19) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.i = set18;
        }
        if (z20) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.k = set19;
        }
        if (z21) {
            cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.s = set20;
        }
        return cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter) {
        CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2 = cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("disallow_add_to_queue_reasons");
        Set set = cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.u;
        f fVar = this.b;
        fVar.toJson(mVar, set);
        mVar.J0("disallow_inserting_into_context_tracks_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.n);
        mVar.J0("disallow_inserting_into_next_tracks_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.m);
        mVar.J0("disallow_pausing_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.e);
        mVar.J0("disallow_peeking_next_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.b);
        mVar.J0("disallow_peeking_prev_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.a);
        mVar.J0("disallow_remote_control_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.l);
        mVar.J0("disallow_removing_from_context_tracks_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.r);
        mVar.J0("disallow_removing_from_next_tracks_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.q);
        mVar.J0("disallow_reordering_in_context_tracks_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.f62p);
        mVar.J0("disallow_reordering_in_next_tracks_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.o);
        mVar.J0("disallow_resuming_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.f);
        mVar.J0("disallow_seeking_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.j);
        mVar.J0("disallow_set_queue_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.t);
        mVar.J0("disallow_skipping_next_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.d);
        mVar.J0("disallow_skipping_prev_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.c);
        mVar.J0("disallow_toggling_repeat_context_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.g);
        mVar.J0("disallow_toggling_repeat_track_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.h);
        mVar.J0("disallow_toggling_shuffle_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.i);
        mVar.J0("disallow_transferring_playback_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.k);
        mVar.J0("disallow_updating_context_reasons");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter2.s);
        mVar.X();
    }

    public final String toString() {
        return u4.b(80, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.PlayerRestrictionsAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
