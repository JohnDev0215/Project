package p;

import com.spotify.player.legacyplayer.PlayOptions$AudioStream;
import com.spotify.player.legacyplayer.PlayOptions$Operation;
import com.spotify.player.legacyplayer.PlayOptions$Trigger;

/* JADX INFO: loaded from: classes.dex */
public final class pt1 {
    public final rt1 a;
    public final boolean b;
    public final Long c;
    public final boolean d;
    public final b e;
    public final nu1 f;
    public final boolean g;
    public final PlayOptions$Operation h;
    public final PlayOptions$Trigger i;
    public final String j;
    public final boolean k;
    public final PlayOptions$AudioStream l;
    public final boolean m;

    public pt1(rt1 rt1Var, boolean z, Long l, boolean z2, b bVar, nu1 nu1Var, boolean z3, PlayOptions$Operation playOptions$Operation, PlayOptions$Trigger playOptions$Trigger, String str, boolean z4, PlayOptions$AudioStream playOptions$AudioStream, boolean z5) {
        this.a = rt1Var;
        this.b = z;
        this.c = l;
        this.d = z2;
        this.e = bVar;
        this.f = nu1Var;
        this.g = z3;
        this.h = playOptions$Operation;
        this.i = playOptions$Trigger;
        this.j = str;
        this.k = z4;
        this.l = playOptions$AudioStream;
        this.m = z5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pt1)) {
            return false;
        }
        pt1 pt1Var = (pt1) obj;
        if (this.d != pt1Var.d || this.k != pt1Var.k || this.g != pt1Var.g || this.m != pt1Var.m) {
            return false;
        }
        rt1 rt1Var = pt1Var.a;
        rt1 rt1Var2 = this.a;
        if (rt1Var2 == null ? rt1Var != null : !rt1Var2.equals(rt1Var)) {
            return false;
        }
        if (this.b != pt1Var.b) {
            return false;
        }
        Long l = pt1Var.c;
        Long l2 = this.c;
        if (l2 == null ? l != null : !l2.equals(l)) {
            return false;
        }
        String str = pt1Var.j;
        String str2 = this.j;
        if (str2 == null ? str != null : !str2.equals(str)) {
            return false;
        }
        b bVar = pt1Var.e;
        b bVar2 = this.e;
        if (bVar2 == null ? bVar != null : !bVar2.equals(bVar)) {
            return false;
        }
        nu1 nu1Var = pt1Var.f;
        nu1 nu1Var2 = this.f;
        if (nu1Var2 == null ? nu1Var != null : !nu1Var2.equals(nu1Var)) {
            return false;
        }
        PlayOptions$Operation playOptions$Operation = pt1Var.h;
        PlayOptions$Operation playOptions$Operation2 = this.h;
        if (playOptions$Operation2 == null ? playOptions$Operation != null : playOptions$Operation2 != playOptions$Operation) {
            return false;
        }
        PlayOptions$AudioStream playOptions$AudioStream = pt1Var.l;
        PlayOptions$AudioStream playOptions$AudioStream2 = this.l;
        if (playOptions$AudioStream2 == null ? playOptions$AudioStream != null : playOptions$AudioStream2 != playOptions$AudioStream) {
            return false;
        }
        PlayOptions$Trigger playOptions$Trigger = pt1Var.i;
        PlayOptions$Trigger playOptions$Trigger2 = this.i;
        if (playOptions$Trigger2 != null) {
            if (playOptions$Trigger2 == playOptions$Trigger) {
                return true;
            }
        } else if (playOptions$Trigger == null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        rt1 rt1Var = this.a;
        int iHashCode = (((rt1Var != null ? rt1Var.hashCode() : 0) * 31) + (this.b ? 1 : 0)) * 31;
        Long l = this.c;
        int iHashCode2 = (((iHashCode + (l != null ? l.hashCode() : 0)) * 31) + (this.d ? 1 : 0)) * 31;
        b bVar = this.e;
        int iHashCode3 = (iHashCode2 + (bVar != null ? bVar.hashCode() : 0)) * 31;
        nu1 nu1Var = this.f;
        int iHashCode4 = (((iHashCode3 + (nu1Var != null ? nu1Var.c.hashCode() : 0)) * 31) + (this.g ? 1 : 0)) * 31;
        PlayOptions$Operation playOptions$Operation = this.h;
        int iHashCode5 = (iHashCode4 + (playOptions$Operation != null ? playOptions$Operation.hashCode() : 0)) * 31;
        PlayOptions$Trigger playOptions$Trigger = this.i;
        int iHashCode6 = (iHashCode5 + (playOptions$Trigger != null ? playOptions$Trigger.hashCode() : 0)) * 31;
        String str = this.j;
        int iHashCode7 = (iHashCode6 + (str != null ? str.hashCode() : 0)) * 31;
        PlayOptions$AudioStream playOptions$AudioStream = this.l;
        return ((((iHashCode7 + (playOptions$AudioStream != null ? playOptions$AudioStream.hashCode() : 0)) * 31) + (this.k ? 1 : 0)) * 961) + (this.m ? 1 : 0);
    }
}
