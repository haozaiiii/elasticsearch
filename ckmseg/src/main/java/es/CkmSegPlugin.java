package es;

import java.util.Collections;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

// 主要实现
public class CkmSegPlugin  extends Plugin implements AnalysisPlugin {
	
//	通过重写getTokenizers将分词器工厂放入map，通过重写getAnalyzers将分析器放入map（这里的key后面会用到）
	public CkmSegPlugin(){
		super();
	}
	
	@Override          
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
		return Collections.singletonMap("ckmseg-word", CkmSegTokenizerFactory::new);
    }
	
    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
    	return Collections.singletonMap("ckmseg", CkmSegAnalyzerProvider::new);
    }

}
