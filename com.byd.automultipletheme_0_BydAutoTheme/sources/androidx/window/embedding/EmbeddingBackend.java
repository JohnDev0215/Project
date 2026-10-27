package androidx.window.embedding;

import android.app.Activity;
import androidx.core.util.Consumer;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Metadata;

/* JADX INFO: compiled from: EmbeddingBackend.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\ba\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0006H&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H&J,\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011H&J\u0016\u0010\u0014\u001a\u00020\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H&J\u001c\u0010\u0017\u001a\u00020\u000b2\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011H&¨\u0006\u0019"}, d2 = {"Landroidx/window/embedding/EmbeddingBackend;", "", "getSplitRules", "", "Landroidx/window/embedding/EmbeddingRule;", "isActivityEmbedded", "", "activity", "Landroid/app/Activity;", "isSplitSupported", "registerRule", "", "rule", "registerSplitListenerForActivity", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "setSplitRules", "rules", "unregisterRule", "unregisterSplitListenerForActivity", "consumer", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface EmbeddingBackend {
    Set<EmbeddingRule> getSplitRules();

    boolean isActivityEmbedded(Activity activity);

    boolean isSplitSupported();

    void registerRule(EmbeddingRule rule);

    void registerSplitListenerForActivity(Activity activity, Executor executor, Consumer<List<SplitInfo>> callback);

    void setSplitRules(Set<? extends EmbeddingRule> rules);

    void unregisterRule(EmbeddingRule rule);

    void unregisterSplitListenerForActivity(Consumer<List<SplitInfo>> consumer);
}
