package com.android.car.media;

import android.car.utils.Logs;
import com.android.car.media.MediaActivity;
import com.blankj.utilcode.util.ProcessUtils;
import com.blankj.utilcode.util.ThreadUtils;
import spi.u7;

/* JADX INFO: compiled from: PlaybackFragment.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements u7 {
    public final /* synthetic */ PlaybackFragment a;

    public e(PlaybackFragment playbackFragment) {
        this.a = playbackFragment;
    }

    @Override // spi.u7
    public final void a() {
        Logs.d("PlaybackFragment", "NetStateChangeReceiver onNetDisconnected");
    }

    @Override // spi.u7
    public final void b() {
        StringBuilder sbK = spi.c.k("NetStateChangeReceiver onNetConnected:");
        sbK.append(ProcessUtils.isMainProcess());
        sbK.append(" isMainThread = ");
        sbK.append(ThreadUtils.isMainThread());
        Logs.e("PlaybackFragment", sbK.toString());
        ProcessUtils.isMainProcess();
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    @Override // spi.u7
    public final void c(boolean z) {
        Logs.d("PlaybackFragment", "onNetRealConnected：" + z);
        try {
            if (!ProcessUtils.isMainProcess() || !z) {
                return;
            }
            try {
                try {
                    StringBuilder sb = new StringBuilder();
                    try {
                        sb.append("onNetRealConnected ");
                        try {
                            try {
                                try {
                                    try {
                                        sb.append(this.a.E.b());
                                        try {
                                            try {
                                                Logs.d("PlaybackFragment", sb.toString());
                                                try {
                                                    try {
                                                        try {
                                                            try {
                                                                if (this.a.E.b() != MediaActivity.d.PLAYBACK) {
                                                                    return;
                                                                }
                                                                try {
                                                                    try {
                                                                        try {
                                                                            try {
                                                                                try {
                                                                                    try {
                                                                                        if (!this.a.d().j.getValue().d()) {
                                                                                            return;
                                                                                        }
                                                                                        try {
                                                                                            try {
                                                                                                StringBuilder sb2 = new StringBuilder();
                                                                                                try {
                                                                                                    sb2.append("try replay 2");
                                                                                                    try {
                                                                                                        try {
                                                                                                            try {
                                                                                                                try {
                                                                                                                    sb2.append(this.a.E.b());
                                                                                                                    try {
                                                                                                                        try {
                                                                                                                            Logs.d("PlaybackFragment", sb2.toString());
                                                                                                                            try {
                                                                                                                                try {
                                                                                                                                    try {
                                                                                                                                        try {
                                                                                                                                            try {
                                                                                                                                            } catch (Throwable th) {
                                                                                                                                                th = th;
                                                                                                                                            }
                                                                                                                                        } catch (Throwable th2) {
                                                                                                                                            th = th2;
                                                                                                                                        }
                                                                                                                                    } catch (Throwable th3) {
                                                                                                                                        th = th3;
                                                                                                                                    }
                                                                                                                                } catch (Throwable th4) {
                                                                                                                                    th = th4;
                                                                                                                                }
                                                                                                                            } catch (Throwable th5) {
                                                                                                                                th = th5;
                                                                                                                            }
                                                                                                                        } catch (Throwable th6) {
                                                                                                                            th = th6;
                                                                                                                        }
                                                                                                                    } catch (Throwable th7) {
                                                                                                                        th = th7;
                                                                                                                    }
                                                                                                                } catch (Throwable th8) {
                                                                                                                    th = th8;
                                                                                                                }
                                                                                                                try {
                                                                                                                    this.a.d().i.getValue().c();
                                                                                                                } catch (Throwable th9) {
                                                                                                                    th = th9;
                                                                                                                    th.printStackTrace();
                                                                                                                }
                                                                                                            } catch (Throwable th10) {
                                                                                                                th = th10;
                                                                                                            }
                                                                                                        } catch (Throwable th11) {
                                                                                                            th = th11;
                                                                                                        }
                                                                                                    } catch (Throwable th12) {
                                                                                                        th = th12;
                                                                                                    }
                                                                                                } catch (Throwable th13) {
                                                                                                    th = th13;
                                                                                                }
                                                                                            } catch (Throwable th14) {
                                                                                                th = th14;
                                                                                            }
                                                                                        } catch (Throwable th15) {
                                                                                            th = th15;
                                                                                        }
                                                                                    } catch (Throwable th16) {
                                                                                        th = th16;
                                                                                    }
                                                                                } catch (Throwable th17) {
                                                                                    th = th17;
                                                                                }
                                                                            } catch (Throwable th18) {
                                                                                th = th18;
                                                                            }
                                                                        } catch (Throwable th19) {
                                                                            th = th19;
                                                                        }
                                                                    } catch (Throwable th20) {
                                                                        th = th20;
                                                                    }
                                                                } catch (Throwable th21) {
                                                                    th = th21;
                                                                }
                                                            } catch (Throwable th22) {
                                                                th = th22;
                                                            }
                                                        } catch (Throwable th23) {
                                                            th = th23;
                                                        }
                                                    } catch (Throwable th24) {
                                                        th = th24;
                                                    }
                                                } catch (Throwable th25) {
                                                    th = th25;
                                                }
                                            } catch (Throwable th26) {
                                                th = th26;
                                            }
                                        } catch (Throwable th27) {
                                            th = th27;
                                        }
                                    } catch (Throwable th28) {
                                        th = th28;
                                    }
                                } catch (Throwable th29) {
                                    th = th29;
                                }
                            } catch (Throwable th30) {
                                th = th30;
                            }
                        } catch (Throwable th31) {
                            th = th31;
                        }
                    } catch (Throwable th32) {
                        th = th32;
                    }
                } catch (Throwable th33) {
                    th = th33;
                }
            } catch (Throwable th34) {
                th = th34;
            }
        } catch (Throwable th35) {
            th = th35;
        }
    }
}
