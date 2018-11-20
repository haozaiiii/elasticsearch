package es;

import java.util.Collections;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

// ��Ҫʵ��
public class CkmSegPlugin  extends Plugin implements AnalysisPlugin {
	
//	ͨ����дgetTokenizers���ִ�����������map��ͨ����дgetAnalyzers������������map�������key������õ���
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
