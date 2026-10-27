package K;

import android.view.KeyEvent;
import w0.C2586a;

/* JADX INFO: loaded from: classes.dex */
public final class U implements T {
    public final /* synthetic */ int l;

    public /* synthetic */ U(int i7) {
        this.l = i7;
    }

    @Override // K.T
    public final int P(KeyEvent keyEvent) {
        int i7 = 0;
        switch (this.l) {
            case 0:
                int i10 = V.f3200s;
                if (!keyEvent.isCtrlPressed() || !keyEvent.isShiftPressed()) {
                    if (keyEvent.isCtrlPressed()) {
                        long jR = w0.c.R(keyEvent);
                        if (!(C2586a.a(jR, AbstractC0349k0.f3314b) ? true : C2586a.a(jR, AbstractC0349k0.f3325q))) {
                            if (!C2586a.a(jR, AbstractC0349k0.d)) {
                                if (!C2586a.a(jR, AbstractC0349k0.f3316f)) {
                                    if (C2586a.a(jR, AbstractC0349k0.f3313a)) {
                                        return 26;
                                    }
                                    if (!C2586a.a(jR, AbstractC0349k0.f3315e)) {
                                        return C2586a.a(jR, AbstractC0349k0.f3317g) ? 46 : 0;
                                    }
                                }
                                return 19;
                            }
                            return 18;
                        }
                        return 17;
                    }
                    if (keyEvent.isCtrlPressed()) {
                        return 0;
                    }
                    if (keyEvent.isShiftPressed()) {
                        long jA = h6.r.a(keyEvent.getKeyCode());
                        if (C2586a.a(jA, AbstractC0349k0.f3319i)) {
                            return 27;
                        }
                        if (C2586a.a(jA, AbstractC0349k0.f3320j)) {
                            return 28;
                        }
                        if (C2586a.a(jA, AbstractC0349k0.f3321k)) {
                            return 29;
                        }
                        if (C2586a.a(jA, AbstractC0349k0.l)) {
                            return 30;
                        }
                        if (C2586a.a(jA, AbstractC0349k0.f3322m)) {
                            return 31;
                        }
                        if (C2586a.a(jA, AbstractC0349k0.f3323n)) {
                            return 32;
                        }
                        if (C2586a.a(jA, AbstractC0349k0.o)) {
                            return 39;
                        }
                        if (C2586a.a(jA, AbstractC0349k0.f3324p)) {
                            return 40;
                        }
                        if (!C2586a.a(jA, AbstractC0349k0.f3325q)) {
                            return 0;
                        }
                    } else {
                        long jA2 = h6.r.a(keyEvent.getKeyCode());
                        if (C2586a.a(jA2, AbstractC0349k0.f3319i)) {
                            return 1;
                        }
                        if (C2586a.a(jA2, AbstractC0349k0.f3320j)) {
                            return 2;
                        }
                        if (C2586a.a(jA2, AbstractC0349k0.f3321k)) {
                            return 11;
                        }
                        if (C2586a.a(jA2, AbstractC0349k0.l)) {
                            return 12;
                        }
                        if (C2586a.a(jA2, AbstractC0349k0.f3322m)) {
                            return 13;
                        }
                        if (C2586a.a(jA2, AbstractC0349k0.f3323n)) {
                            return 14;
                        }
                        if (C2586a.a(jA2, AbstractC0349k0.o)) {
                            return 7;
                        }
                        if (C2586a.a(jA2, AbstractC0349k0.f3324p)) {
                            return 8;
                        }
                        if (C2586a.a(jA2, AbstractC0349k0.f3326r)) {
                            return 44;
                        }
                        if (C2586a.a(jA2, AbstractC0349k0.f3327s)) {
                            return 20;
                        }
                        if (C2586a.a(jA2, AbstractC0349k0.f3328t)) {
                            return 21;
                        }
                        if (!C2586a.a(jA2, AbstractC0349k0.f3329u)) {
                            if (!C2586a.a(jA2, AbstractC0349k0.f3330v)) {
                                if (!C2586a.a(jA2, AbstractC0349k0.f3331w)) {
                                    return C2586a.a(jA2, AbstractC0349k0.f3332x) ? 45 : 0;
                                }
                                return 17;
                            }
                            return 19;
                        }
                    }
                    return 18;
                }
                if (!C2586a.a(h6.r.a(keyEvent.getKeyCode()), AbstractC0349k0.f3317g)) {
                    return 0;
                }
                return 47;
            default:
                if (keyEvent.isShiftPressed() && keyEvent.isAltPressed()) {
                    long jA3 = h6.r.a(keyEvent.getKeyCode());
                    if (C2586a.a(jA3, AbstractC0349k0.f3319i)) {
                        i7 = 41;
                    } else if (C2586a.a(jA3, AbstractC0349k0.f3320j)) {
                        i7 = 42;
                    } else if (C2586a.a(jA3, AbstractC0349k0.f3321k)) {
                        i7 = 33;
                    } else if (C2586a.a(jA3, AbstractC0349k0.l)) {
                        i7 = 34;
                    }
                } else if (keyEvent.isAltPressed()) {
                    long jA4 = h6.r.a(keyEvent.getKeyCode());
                    if (C2586a.a(jA4, AbstractC0349k0.f3319i)) {
                        i7 = 9;
                    } else if (C2586a.a(jA4, AbstractC0349k0.f3320j)) {
                        i7 = 10;
                    } else if (C2586a.a(jA4, AbstractC0349k0.f3321k)) {
                        i7 = 15;
                    } else if (C2586a.a(jA4, AbstractC0349k0.l)) {
                        i7 = 16;
                    }
                }
                return i7 == 0 ? W.f3201a.P(keyEvent) : i7;
        }
    }
}
