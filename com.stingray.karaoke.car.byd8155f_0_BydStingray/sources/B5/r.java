package B5;

/* JADX INFO: loaded from: classes.dex */
public final class r extends H7.H {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f732e;

    public /* synthetic */ r(int i7) {
        this.f732e = i7;
    }

    @Override // H7.H
    public final void N(String str, C5.g gVar, y yVar) {
        switch (this.f732e) {
            case 0:
                gVar.getClass();
                break;
            case 1:
                gVar.getClass();
                break;
            case 2:
                gVar.getClass();
                break;
            case 3:
                gVar.getClass();
                break;
            case 4:
                gVar.getClass();
                break;
            case 5:
                gVar.getClass();
                break;
            default:
                gVar.getClass();
                break;
        }
    }

    public final String toString() {
        switch (this.f732e) {
            case 0:
                return "Activity Created";
            case 1:
                return "Activity Started";
            case 2:
                return "Activity Resumed";
            case 3:
                return "Activity Paused";
            case 4:
                return "Activity Stopped";
            case 5:
                return "Activity Save Instance";
            default:
                return "Activity Destroyed";
        }
    }
}
