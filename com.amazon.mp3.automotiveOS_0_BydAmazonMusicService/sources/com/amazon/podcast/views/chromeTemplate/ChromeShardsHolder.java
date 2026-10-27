package com.amazon.podcast.views.chromeTemplate;

import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateShardElement;
import SOATemplateListInterface.v1_0.Shard;
import SOATemplateListInterface.v1_0.Template;
import androidx.fragment.app.Fragment;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;
import com.amazon.podcast.views.TemplateFragment;
import com.amazon.soa.core.OwnerIdGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
final class ChromeShardsHolder {
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final OwnerRegistration ownerRegistration;
    private final List<ChromeTemplateShardElement> shards;
    private final List<Fragment> fragments = new ArrayList();
    private final Map<String, Fragment> fragmentMap = new HashMap();

    public ChromeShardsHolder(final String ownerId, final MethodsDispatcher methodsDispatcher, final OwnerRegistration ownerRegistration, final List<ChromeTemplateShardElement> shards) {
        this.shards = shards;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerRegistration = ownerRegistration;
        this.ownerId = ownerId;
        for (int i = 0; i < shards.size(); i++) {
            String strForTemplateShard = OwnerIdGenerator.forTemplateShard(ownerId);
            Fragment fragmentCreateFragment = createFragment(strForTemplateShard, i);
            this.fragments.add(fragmentCreateFragment);
            this.fragmentMap.put(strForTemplateShard, fragmentCreateFragment);
        }
    }

    public int count() {
        return this.shards.size();
    }

    public String shardText(final int position) {
        return this.shards.get(position).getHeader();
    }

    public Fragment fragment(final String ownerId) {
        return this.fragmentMap.get(ownerId);
    }

    public Fragment fragment(final int position) {
        return this.fragments.get(position);
    }

    private Fragment createFragment(final String shardOwnerId, final int position) {
        TemplateFragment templateFragment = new TemplateFragment();
        Shard shard = this.shards.get(position).getShard();
        Template create = shard.getCreate();
        if (create == null) {
            templateFragment.createAndBindTemplate(shardOwnerId, shard.getCreateAndBind(), true, null, this.methodsDispatcher, this.ownerRegistration);
        } else {
            templateFragment.createTemplate(shardOwnerId, create, true, null, this.methodsDispatcher, this.ownerRegistration);
        }
        return templateFragment;
    }
}
