package es;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.common.settings.ClusterSettings;
import org.elasticsearch.common.settings.IndexScopedSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsFilter;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;


public class MyFirstPlugin extends Plugin implements ActionPlugin {
	private final static Logger LOGGER = LogManager.getLogger(MyFirstPlugin.class);  
    public MyFirstPlugin() {  
        super();  
        LOGGER.warn("Create the Basic Plugin and installed it into elasticsearch");  
        LOGGER.info("This is my first Plugin");
    }
	@Override
	public List<RestHandler> getRestHandlers(Settings settings,
			RestController restController, ClusterSettings clusterSettings,
			IndexScopedSettings indexScopedSettings,
			SettingsFilter settingsFilter,
			IndexNameExpressionResolver indexNameExpressionResolver,
			Supplier<DiscoveryNodes> nodesInCluster) {
		// TODO Auto-generated method stub
		
		return Collections.singletonList(new MyRestAction(settings, restController));
	}
    
    
    
    


}
