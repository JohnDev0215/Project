package p;

import com.spotify.player.legacyplayer.LoggingParameters;
import com.spotify.player.model.Suppressions;
import com.spotify.player.model.command.options.PrefetchLevel;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$LoggingParametersAdapter$Adapter;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PlayerSuppressionsAdapter$Adapter;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter;
import com.squareup.moshi.JsonReader;

/* JADX INFO: loaded from: classes.dex */
public final class m20 extends com.squareup.moshi.f {
    public final /* synthetic */ int a;
    public final com.squareup.moshi.o b;

    public m20(com.squareup.moshi.o oVar, int i) {
        this.a = i;
        switch (i) {
            case 1:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
            case 2:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
            case 3:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
            case 4:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
            case 5:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
            case 6:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
            case 7:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
            case 8:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
            case 9:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
            case 10:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
            case 11:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
            default:
                v03.j(oVar, "moshi");
                this.b = oVar;
                break;
        }
    }

    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        int i = this.a;
        com.squareup.moshi.o oVar = this.b;
        switch (i) {
            case 0:
                v03.j(jsonReader, "reader");
                Object objFromJson = oVar.c(CosmosTypeAdapterFactory$LoggingParametersAdapter$Adapter.class).fromJson(jsonReader);
                if (objFromJson == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                long j = ((CosmosTypeAdapterFactory$LoggingParametersAdapter$Adapter) objFromJson).a;
                LoggingParameters loggingParameters = new LoggingParameters();
                loggingParameters.a = j;
                return loggingParameters;
            case 1:
                v03.j(jsonReader, "reader");
                Object objFromJson2 = oVar.c(CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.class).fromJson(jsonReader);
                if (objFromJson2 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter = (CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter) objFromJson2;
                return new pt1(cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.a, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.m, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.b, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.c, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.d, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.e, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.f, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.g, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.h, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.i, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.j, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.k, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.l);
            case 2:
                v03.j(jsonReader, "reader");
                Object objFromJson3 = oVar.c(CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.class).fromJson(jsonReader);
                if (objFromJson3 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter = (CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter) objFromJson3;
                return new rt1(cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.a, cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.b, cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.c, cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.d, cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.e);
            case 3:
                v03.j(jsonReader, "reader");
                Object objFromJson4 = oVar.c(CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.class).fromJson(jsonReader);
                if (objFromJson4 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter = (CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter) objFromJson4;
                String str = cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.a;
                if (str != null) {
                    return new wt1(str, cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.b, cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.c, cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.d, cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.e, cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.f);
                }
                throw new IllegalStateException("Required value was null.".toString());
            case 4:
                v03.j(jsonReader, "reader");
                Object objFromJson5 = oVar.c(CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.class).fromJson(jsonReader);
                if (objFromJson5 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter = (CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter) objFromJson5;
                return new xt1(cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.a, cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.b, cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.c, cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.d);
            case 5:
                v03.j(jsonReader, "reader");
                Object objFromJson6 = oVar.c(CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.class).fromJson(jsonReader);
                if (objFromJson6 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter = (CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter) objFromJson6;
                return new li(cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.a, cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.b, cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.c);
            case 6:
                v03.j(jsonReader, "reader");
                Object objFromJson7 = oVar.c(CosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter.class).fromJson(jsonReader);
                if (objFromJson7 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter = (CosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter) objFromJson7;
                return new mi(cosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter.a, cosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter.b, cosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter.c);
            case 7:
                v03.j(jsonReader, "reader");
                Object objFromJson8 = oVar.c(CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.class).fromJson(jsonReader);
                if (objFromJson8 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter = (CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter) objFromJson8;
                return new hu1(cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.a, cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.b, cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.c, cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.d);
            case 8:
                v03.j(jsonReader, "reader");
                Object objFromJson9 = oVar.c(CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.class).fromJson(jsonReader);
                if (objFromJson9 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter = (CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter) objFromJson9;
                return new iu1(cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.a, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.b, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.c, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.d, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.e, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.f, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.g, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.h, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.i, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.j, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.k, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.l, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.m, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.n, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.o, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.f62p, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.q, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.r, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.s, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.t, cosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.u);
            case 9:
                v03.j(jsonReader, "reader");
                Object objFromJson10 = oVar.c(CosmosTypeAdapterFactory$PlayerSuppressionsAdapter$Adapter.class).fromJson(jsonReader);
                if (objFromJson10 != null) {
                    return new nu1(((CosmosTypeAdapterFactory$PlayerSuppressionsAdapter$Adapter) objFromJson10).a);
                }
                throw new IllegalStateException("Required value was null.".toString());
            case 10:
                v03.j(jsonReader, "reader");
                String strU = jsonReader.u();
                boolean zB = v03.b(strU, "none");
                PrefetchLevel prefetchLevel = PrefetchLevel.c;
                return (!zB && v03.b(strU, "media")) ? PrefetchLevel.e : prefetchLevel;
            default:
                v03.j(jsonReader, "reader");
                Object objFromJson11 = oVar.c(CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter.class).fromJson(jsonReader);
                if (objFromJson11 != null) {
                    return Suppressions.a(((CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter) objFromJson11).a);
                }
                throw new IllegalStateException("Required value was null.".toString());
        }
    }

    @Override // com.squareup.moshi.f
    public final void toJson(com.squareup.moshi.m mVar, Object obj) {
        int i = this.a;
        com.squareup.moshi.o oVar = this.b;
        switch (i) {
            case 0:
                LoggingParameters loggingParameters = (LoggingParameters) obj;
                v03.j(mVar, "writer");
                if (loggingParameters == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$LoggingParametersAdapter$Adapter cosmosTypeAdapterFactory$LoggingParametersAdapter$Adapter = new CosmosTypeAdapterFactory$LoggingParametersAdapter$Adapter();
                cosmosTypeAdapterFactory$LoggingParametersAdapter$Adapter.a = loggingParameters.a;
                oVar.c(CosmosTypeAdapterFactory$LoggingParametersAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$LoggingParametersAdapter$Adapter);
                return;
            case 1:
                pt1 pt1Var = (pt1) obj;
                v03.j(mVar, "writer");
                if (pt1Var == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter = new CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter();
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.a = pt1Var.a;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.b = pt1Var.c;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.c = pt1Var.d;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.d = pt1Var.e;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.e = pt1Var.f;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.f = pt1Var.g;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.g = pt1Var.h;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.h = pt1Var.i;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.i = pt1Var.j;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.j = pt1Var.k;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.k = pt1Var.l;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.l = pt1Var.m;
                cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.m = pt1Var.b;
                oVar.c(CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter);
                return;
            case 2:
                rt1 rt1Var = (rt1) obj;
                v03.j(mVar, "writer");
                if (rt1Var == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter = new CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter();
                cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.a = rt1Var.c;
                cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.b = rt1Var.e;
                cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.c = rt1Var.k;
                cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.d = rt1Var.l;
                cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.e = rt1Var.m;
                oVar.c(CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter);
                return;
            case 3:
                wt1 wt1Var = (wt1) obj;
                v03.j(mVar, "writer");
                if (wt1Var == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter();
                cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.a = wt1Var.c;
                cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.b = wt1Var.k;
                cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.c = wt1Var.l;
                cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.d = wt1Var.m;
                cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.e = wt1Var.n;
                cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.f = wt1Var.e;
                oVar.c(CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter);
                return;
            case 4:
                xt1 xt1Var = (xt1) obj;
                v03.j(mVar, "writer");
                if (xt1Var == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter();
                cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.a = xt1Var.c;
                cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.b = xt1Var.e;
                cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.c = xt1Var.k;
                cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.d = xt1Var.l;
                oVar.c(CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter);
                return;
            case 5:
                a aVar = (a) obj;
                v03.j(mVar, "writer");
                if (aVar == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter();
                cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.a = aVar.c;
                cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.b = aVar.e;
                cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.c = aVar.k;
                oVar.c(CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter);
                return;
            case 6:
                b bVar = (b) obj;
                v03.j(mVar, "writer");
                if (bVar == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter();
                cosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter.a = bVar.c;
                cosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter.b = bVar.e;
                cosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter.c = bVar.k;
                oVar.c(CosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$PlayerOptionsOverridesAdapter$Adapter);
                return;
            case 7:
                hu1 hu1Var = (hu1) obj;
                v03.j(mVar, "writer");
                if (hu1Var == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter();
                cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.a = hu1Var.l;
                cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.b = hu1Var.c;
                cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.c = hu1Var.e;
                cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.d = hu1Var.k;
                oVar.c(CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter);
                return;
            case 8:
                v03.j(mVar, "writer");
                oVar.c(CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter.class).toJson(mVar, new CosmosTypeAdapterFactory$PlayerRestrictionsAdapter$Adapter());
                return;
            case 9:
                nu1 nu1Var = (nu1) obj;
                v03.j(mVar, "writer");
                if (nu1Var == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$PlayerSuppressionsAdapter$Adapter cosmosTypeAdapterFactory$PlayerSuppressionsAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerSuppressionsAdapter$Adapter();
                cosmosTypeAdapterFactory$PlayerSuppressionsAdapter$Adapter.a = nu1Var.c;
                oVar.c(CosmosTypeAdapterFactory$PlayerSuppressionsAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$PlayerSuppressionsAdapter$Adapter);
                return;
            case 10:
                PrefetchLevel prefetchLevel = (PrefetchLevel) obj;
                v03.j(mVar, "writer");
                int i2 = prefetchLevel == null ? -1 : r20.a[prefetchLevel.ordinal()];
                if (i2 == 1) {
                    mVar.W0("none");
                    return;
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    mVar.W0("media");
                    return;
                }
            default:
                Suppressions suppressions = (Suppressions) obj;
                v03.j(mVar, "writer");
                if (suppressions == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter cosmosTypeAdapterFactory$SuppressionsAdapter$Adapter = new CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter();
                cosmosTypeAdapterFactory$SuppressionsAdapter$Adapter.a = suppressions.b();
                oVar.c(CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$SuppressionsAdapter$Adapter);
                return;
        }
    }
}
