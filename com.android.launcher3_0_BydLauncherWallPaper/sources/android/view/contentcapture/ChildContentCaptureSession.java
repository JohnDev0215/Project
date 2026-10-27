package android.view.contentcapture;

import android.graphics.Insets;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ViewNode;

/* JADX INFO: loaded from: classes3.dex */
final class ChildContentCaptureSession extends ContentCaptureSession {
    private final ContentCaptureSession mParent;

    protected ChildContentCaptureSession(ContentCaptureSession contentCaptureSession, ContentCaptureContext contentCaptureContext) {
        super(contentCaptureContext);
        this.mParent = contentCaptureSession;
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    MainContentCaptureSession getMainCaptureSession() {
        ContentCaptureSession contentCaptureSession = this.mParent;
        if (contentCaptureSession instanceof MainContentCaptureSession) {
            return (MainContentCaptureSession) contentCaptureSession;
        }
        return contentCaptureSession.getMainCaptureSession();
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    ContentCaptureSession newChild(ContentCaptureContext contentCaptureContext) {
        ChildContentCaptureSession childContentCaptureSession = new ChildContentCaptureSession(this, contentCaptureContext);
        getMainCaptureSession().notifyChildSessionStarted(this.mId, childContentCaptureSession.mId, contentCaptureContext);
        return childContentCaptureSession;
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void flush(int i) {
        this.mParent.flush(i);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    public void updateContentCaptureContext(ContentCaptureContext contentCaptureContext) {
        getMainCaptureSession().notifyContextUpdated(this.mId, contentCaptureContext);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void onDestroy() {
        getMainCaptureSession().notifyChildSessionFinished(this.mParent.mId, this.mId);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void internalNotifyViewAppeared(ViewNode.ViewStructureImpl viewStructureImpl) {
        getMainCaptureSession().notifyViewAppeared(this.mId, viewStructureImpl);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void internalNotifyViewDisappeared(AutofillId autofillId) {
        getMainCaptureSession().notifyViewDisappeared(this.mId, autofillId);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void internalNotifyViewTextChanged(AutofillId autofillId, CharSequence charSequence) {
        getMainCaptureSession().notifyViewTextChanged(this.mId, autofillId, charSequence);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void internalNotifyViewInsetsChanged(Insets insets) {
        getMainCaptureSession().notifyViewInsetsChanged(this.mId, insets);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    public void internalNotifyViewTreeEvent(boolean z) {
        getMainCaptureSession().notifyViewTreeEvent(this.mId, z);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void internalNotifySessionResumed() {
        getMainCaptureSession().notifySessionResumed();
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void internalNotifySessionPaused() {
        getMainCaptureSession().notifySessionPaused();
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    boolean isContentCaptureEnabled() {
        return getMainCaptureSession().isContentCaptureEnabled();
    }
}
