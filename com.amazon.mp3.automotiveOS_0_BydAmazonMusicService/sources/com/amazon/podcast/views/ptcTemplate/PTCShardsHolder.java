package com.amazon.podcast.views.ptcTemplate;

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
final class PTCShardsHolder {
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final OwnerRegistration ownerRegistration;
    private final List<Shard> shards;
    private final List<Fragment> fragments = new ArrayList();
    private final Map<String, Fragment> fragmentMap = new HashMap();

    public PTCShardsHolder(final String ownerId, final MethodsDispatcher methodsDispatcher, final OwnerRegistration ownerRegistration, final List<Shard> shards) {
        this.shards = shards;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerRegistration = ownerRegistration;
        for (int i = 0; i < shards.size(); i++) {
            String strForTemplateShard = OwnerIdGenerator.forTemplateShard(ownerId);
            Fragment fragmentCreateFragment = createFragment(strForTemplateShard, i);
            this.fragments.add(fragmentCreateFragment);
            this.fragmentMap.put(strForTemplateShard, fragmentCreateFragment);
        }
    }

    public final int count() {
        return this.shards.size();
    }

    public final Fragment fragment(final int position) {
        return this.fragments.get(position);
    }

    public final Fragment fragment(final String ownerId) {
        return this.fragmentMap.get(ownerId);
    }

    private Fragment createFragment(final String shardOwnerId, final int position) {
        TemplateFragment templateFragment = new TemplateFragment();
        Shard shard = this.shards.get(position);
        Template create = shard.getCreate();
        if (create == null) {
            templateFragment.createAndBindTemplate(shardOwnerId, shard.getCreateAndBind(), true, null, this.methodsDispatcher, this.ownerRegistration);
        } else {
            templateFragment.createTemplate(shardOwnerId, create, true, null, this.methodsDispatcher, this.ownerRegistration);
        }
        return templateFragment;
    }
}
